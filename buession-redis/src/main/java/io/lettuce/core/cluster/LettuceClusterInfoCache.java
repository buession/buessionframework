/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package io.lettuce.core.cluster;

import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.ConnectionPool;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.RedisException;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.internal.HostAndPort;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class LettuceClusterInfoCache<K, V> implements AutoCloseable {

	private final static int CLUSTER_HASHSLOTS = 16384;

	private final static int MASTER_NODE_INDEX = 2;

	private final Map<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> nodes = new HashMap<>();

	private final HostAndPort[] slotNodes = new HostAndPort[CLUSTER_HASHSLOTS];

	private final Set<HostAndPort> startNodes;

	private final LettuceClientConfig clientConfig;

	private final GenericObjectPoolConfig<StatefulRedisClusterConnection<K, V>> poolConfig;

	private final RedisCodec<K, V> redisCodec;

	private final Map<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> primaryNodesCache = new HashMap<>();

	private final ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>[] slots = new ConnectionPool[CLUSTER_HASHSLOTS];

	private final List<ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>>[] replicaSlots;

	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	private final Lock r = rwl.readLock();

	private final Lock w = rwl.writeLock();

	private final Lock rediscoverLock = new ReentrantLock();

	/**
	 * The single thread executor for the topology refresh task.
	 */
	private ScheduledExecutorService topologyRefreshExecutor = null;

	private static final Logger logger = LoggerFactory.getLogger(LettuceClusterInfoCache.class);

	public LettuceClusterInfoCache(final Set<HostAndPort> startNodes, final LettuceClientConfig clientConfig,
	                               final RedisCodec<K, V> redisCodec) {
		this(startNodes, clientConfig, null, redisCodec);
	}

	public LettuceClusterInfoCache(final Set<HostAndPort> startNodes, final LettuceClientConfig clientConfig,
	                               final GenericObjectPoolConfig<StatefulRedisClusterConnection<K, V>> poolConfig,
	                               final RedisCodec<K, V> redisCodec) {
		this(startNodes, clientConfig, poolConfig, null, redisCodec);
	}

	public LettuceClusterInfoCache(final Set<HostAndPort> startNodes, final LettuceClientConfig clientConfig,
	                               final GenericObjectPoolConfig<StatefulRedisClusterConnection<K, V>> poolConfig,
	                               final Duration topologyRefreshPeriod,
	                               final RedisCodec<K, V> redisCodec) {
		this.poolConfig = poolConfig;
		this.clientConfig = clientConfig;
		this.startNodes = startNodes;
		if(topologyRefreshPeriod != null){
			logger.info("Cluster topology refresh start, period: {}, startNodes: {}", topologyRefreshPeriod,
					startNodes);
			this.topologyRefreshExecutor = Executors.newSingleThreadScheduledExecutor();
			this.topologyRefreshExecutor.scheduleWithFixedDelay(new TopologyRefreshTask(),
					topologyRefreshPeriod.toMillis(), topologyRefreshPeriod.toMillis(), TimeUnit.MILLISECONDS);
		}
		this.replicaSlots = clientConfig.isReadOnlyForRedisClusterReplicas() ? new ArrayList[CLUSTER_HASHSLOTS] : null;
		this.redisCodec = redisCodec;
	}

	public void discoverClusterNodesAndSlots(StatefulRedisClusterConnection<K, V> connection) {
		List<Object> slotsInfo = executeClusterSlots(connection);

		if(slotsInfo.isEmpty()){
			throw new LettuceClusterOperationException("Cluster slots list is empty.");
		}
		if(checkClusterSlotSequence(slotsInfo) == false){
			throw new LettuceClusterOperationException("Cluster slots have holes.");
		}

		w.lock();
		try{
			reset();
			for(Object slotInfoObj : slotsInfo){
				List<Object> slotInfo = (List<Object>) slotInfoObj;

				if(slotInfo.size() <= MASTER_NODE_INDEX){
					continue;
				}

				List<Integer> slotNums = getAssignedSlotArray(slotInfo);

				// hostInfos
				int size = slotInfo.size();
				for(int i = MASTER_NODE_INDEX; i < size; i++){
					List<Object> hostInfos = (List<Object>) slotInfo.get(i);
					if(hostInfos.isEmpty()){
						continue;
					}

					HostAndPort targetNode = generateHostAndPort(hostInfos);
					setupNodeIfNotExist(targetNode);
					if(i == MASTER_NODE_INDEX){
						primaryNodesCache.put(getNodeKey(targetNode), getNode(targetNode));
						assignSlotsToNode(slotNums, targetNode);
					}else if(clientConfig.isReadOnlyForRedisClusterReplicas()){
						assignSlotsToReplicaNode(slotNums, targetNode);
					}
				}
			}
		}finally{
			w.unlock();
		}
	}

	public void renewClusterSlots(StatefulRedisClusterConnection<K, V> connection) {
		// If rediscovering is already in process - no need to start one more same rediscovering, just return
		if(rediscoverLock.tryLock()){
			try{
				if(connection != null){
					try{
						discoverClusterSlots(connection);
						return;
					}catch(RedisException e){
						// try nodes from all pools
					}
				}

				// Then, we use startNodes to try, as long as startNodes is available,
				// whether it is vip, domain, or physical ip, it will succeed.
				if(startNodes != null){
					for(HostAndPort hostAndPort : startNodes){
						RedisURI redisURI = RedisURI.create(hostAndPort.getHostText(), hostAndPort.getPort());
						io.lettuce.core.cluster.RedisClusterClient redisClusterClient = RedisClusterClient.create(
								redisURI);
						try(StatefulRedisClusterConnection<K, V> conn = redisClusterClient.connect(redisCodec)){
							discoverClusterSlots(conn);
							return;
						}catch(RedisException e){
							// try next nodes
						}
					}
				}

				// Finally, we go back to the ShuffledNodesPool and try the remaining physical nodes.
				for(ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> jp : getShuffledNodesPool()){
					try(StatefulRedisClusterConnection<K, V> conn = jp.getResource()){
						// If already tried in startNodes, skip this node.
						//if(startNodes != null && startNodes.contains(j.getHostAndPort())){
						//continue;
						//}
						discoverClusterSlots(conn);
						return;
					}catch(RedisException e){
						// try next nodes
					}
				}
			}finally{
				rediscoverLock.unlock();
			}
		}
	}

	public ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> setupNodeIfNotExist(final HostAndPort node) {
		w.lock();
		try{
			String nodeKey = getNodeKey(node);
			ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> existingPool = nodes.get(nodeKey);

			if(existingPool != null){
				return existingPool;
			}

			ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> nodePool = createNodePool(node);
			nodes.put(nodeKey, nodePool);
			return nodePool;
		}finally{
			w.unlock();
		}
	}

	public void assignSlotToNode(int slot, HostAndPort targetNode) {
		w.lock();
		try{
			ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> targetPool = setupNodeIfNotExist(targetNode);
			slots[slot] = targetPool;
			slotNodes[slot] = targetNode;
		}finally{
			w.unlock();
		}
	}

	public void assignSlotsToNode(List<Integer> targetSlots, HostAndPort targetNode) {
		w.lock();
		try{
			ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> targetPool = setupNodeIfNotExist(targetNode);
			for(Integer slot : targetSlots){
				slots[slot] = targetPool;
				slotNodes[slot] = targetNode;
			}
		}finally{
			w.unlock();
		}
	}

	public void assignSlotsToReplicaNode(List<Integer> targetSlots, HostAndPort targetNode) {
		w.lock();
		try{
			ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> targetPool = setupNodeIfNotExist(targetNode);
			for(Integer slot : targetSlots){
				if(replicaSlots[slot] == null){
					replicaSlots[slot] = new ArrayList<>();
				}
				replicaSlots[slot].add(targetPool);
			}
		}finally{
			w.unlock();
		}
	}

	public ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> getNode(String nodeKey) {
		r.lock();
		try{
			return nodes.get(nodeKey);
		}finally{
			r.unlock();
		}
	}

	public ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> getNode(HostAndPort node) {
		return getNode(getNodeKey(node));
	}

	public Map<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> getNodes() {
		r.lock();
		try{
			return new HashMap<>(nodes);
		}finally{
			r.unlock();
		}
	}

	public Map<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> getPrimaryNodes() {
		r.lock();
		try{
			return new HashMap<>(primaryNodesCache);
		}finally{
			r.unlock();
		}
	}

	public HostAndPort getSlotNode(int slot) {
		r.lock();
		try{
			return slotNodes[slot];
		}finally{
			r.unlock();
		}
	}

	public ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> getSlotPool(int slot) {
		r.lock();
		try{
			return slots[slot];
		}finally{
			r.unlock();
		}
	}

	public List<ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> getSlotReplicaPools(int slot) {
		r.lock();
		try{
			return replicaSlots[slot];
		}finally{
			r.unlock();
		}
	}

	public List<ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> getShuffledPrimaryNodesPool() {
		r.lock();
		try{
			List<ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> pools = new ArrayList<>(
					primaryNodesCache.values());
			Collections.shuffle(pools);
			return pools;
		}finally{
			r.unlock();
		}
	}

	public List<ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> getShuffledNodesPool() {
		r.lock();
		try{
			List<ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> pools = new ArrayList<>(nodes.values());
			Collections.shuffle(pools);
			return pools;
		}finally{
			r.unlock();
		}
	}

	/**
	 * Clear discovered nodes collections and gently release allocated resources
	 */
	public void reset() {
		w.lock();
		try{
			resetNodes();
			resetSlots();
		}finally{
			w.unlock();
		}
	}

	@Override
	public void close() {
		reset();
		if(topologyRefreshExecutor != null){
			logger.info("Cluster topology refresh shutdown, startNodes: {}", startNodes);
			topologyRefreshExecutor.shutdownNow();
		}
	}

	private static String getNodeKey(HostAndPort hnp) {
		return hnp.toString();
	}

	private HostAndPort generateHostAndPort(List<Object> hostInfos) {
		String host = SafeEncoder.encode((byte[]) hostInfos.get(0));
		int port = ((Long) hostInfos.get(1)).intValue();
		return HostAndPort.of(host, port);
	}

	private ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> createNodePool(HostAndPort node) {
		if(poolConfig == null){
			//return new ConnectionPool<>(node, clientConfig, redisCodec);
		}else{
			//return new ConnectionPool<>(node, clientConfig, poolConfig, redisCodec);
		}
		return null;
	}

	private void discoverClusterSlots(StatefulRedisClusterConnection<K, V> connection) {
		List<Object> slotsInfo = executeClusterSlots(connection);
		if(slotsInfo.isEmpty()){
			throw new LettuceClusterOperationException("Cluster slots list is empty.");
		}
		if(!checkClusterSlotSequence(slotsInfo)){
			throw new LettuceClusterOperationException("Cluster slots have holes.");
		}

		w.lock();
		try{
			resetSlots();
			Set<String> hostAndPortKeys = new HashSet<>();

			for(Object slotInfoObj : slotsInfo){
				List<Object> slotInfo = (List<Object>) slotInfoObj;

				if(slotInfo.size() <= MASTER_NODE_INDEX){
					continue;
				}

				List<Integer> slotNums = getAssignedSlotArray(slotInfo);

				int size = slotInfo.size();
				for(int i = MASTER_NODE_INDEX; i < size; i++){
					List<Object> hostInfos = (List<Object>) slotInfo.get(i);
					if(hostInfos.isEmpty()){
						continue;
					}

					HostAndPort targetNode = generateHostAndPort(hostInfos);
					hostAndPortKeys.add(getNodeKey(targetNode));
					setupNodeIfNotExist(targetNode);
					if(i == MASTER_NODE_INDEX){
						assignSlotsToNode(slotNums, targetNode);
					}else if(clientConfig.isReadOnlyForRedisClusterReplicas()){
						assignSlotsToReplicaNode(slotNums, targetNode);
					}
				}
			}

			// Remove dead nodes according to the latest query
			Iterator<Map.Entry<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>>> entryIt = nodes.entrySet()
					.iterator();
			while(entryIt.hasNext()){
				Map.Entry<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> entry = entryIt.next();
				if(hostAndPortKeys.contains(entry.getKey()) == false){
					ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> pool = entry.getValue();
					try{
						if(pool != null){
							pool.destroy();
						}
					}catch(Exception e){
						// pass, may be this node dead
					}
					entryIt.remove();
				}
			}
		}finally{
			w.unlock();
		}
	}

	/**
	 * Check whether the number and order of slots in the cluster topology are equal to CLUSTER_HASHSLOTS
	 *
	 * @param slotsInfo
	 * 		the cluster topology
	 *
	 * @return if slots is ok, return true, elese return false.
	 */
	private boolean checkClusterSlotSequence(List<Object> slotsInfo) {
		List<Integer> slots = new ArrayList<>();

		for(Object slotInfo : slotsInfo){
			slots.addAll(getAssignedSlotArray((List<Object>) slotInfo));
		}

		Collections.sort(slots);

		if(slots.size() != CLUSTER_HASHSLOTS){
			return false;
		}

		for(int i = 0; i < CLUSTER_HASHSLOTS; ++i){
			if(i != slots.get(i)){
				return false;
			}
		}

		return true;
	}

	private List<Object> executeClusterSlots(StatefulRedisClusterConnection<K, V> connection) {
		return connection.sync().clusterSlots();
	}

	private void resetNodes() {
		for(ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> pool : nodes.values()){
			try{
				if(pool != null){
					pool.destroy();
				}
			}catch(RuntimeException e){
				// pass
			}
		}
		nodes.clear();
		primaryNodesCache.clear();
	}

	private void resetSlots() {
		Arrays.fill(slots, null);
		Arrays.fill(slotNodes, null);
		resetReplicaSlots();
	}

	private void resetReplicaSlots() {
		if(replicaSlots == null){
			return;
		}

		Arrays.stream(replicaSlots).filter(Objects::nonNull).forEach(List::clear);
		Arrays.fill(replicaSlots, null);
	}

	private List<Integer> getAssignedSlotArray(List<Object> slotInfo) {
		List<Integer> slotNums = new ArrayList<>();

		for(int slot = ((Long) slotInfo.get(0)).intValue(), slots = ((Long) slotInfo.get(1)).intValue();
		    slot <= slots; slot++){
			slotNums.add(slot);
		}

		return slotNums;
	}

	class TopologyRefreshTask implements Runnable {

		@Override
		public void run() {
			logger.debug("Cluster topology refresh run, old nodes: {}", nodes.keySet());
			renewClusterSlots(null);
			logger.debug("Cluster topology refresh run, new nodes: {}", nodes.keySet());
		}

	}

}
