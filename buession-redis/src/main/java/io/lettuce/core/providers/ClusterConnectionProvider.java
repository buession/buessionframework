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
package io.lettuce.core.providers;

import io.lettuce.core.ConnectionPool;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.LettuceClusterInfoCache;
import io.lettuce.core.RedisConnectionException;
import io.lettuce.core.RedisException;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.protocol.CommandArgs;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Lettuce Redis 集群连接池连接提供者
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ClusterConnectionProvider<K, V> implements ConnectionProvider<K, V> {

	private final LettuceClusterInfoCache<K, V> cache;

	public ClusterConnectionProvider(final Set<HostAndPort> clusterNodes, final LettuceClientConfig clientConfig,
	                                 final RedisCodec<K, V> redisCodec) {
		this.cache = new LettuceClusterInfoCache<>(clusterNodes, clientConfig, redisCodec);
		initializeSlotsCache(clusterNodes, clientConfig, redisCodec);
	}

	public ClusterConnectionProvider(final Set<HostAndPort> clusterNodes, final LettuceClientConfig clientConfig,
	                                 final GenericObjectPoolConfig<StatefulRedisClusterConnection<K, V>> poolConfig,
	                                 final RedisCodec<K, V> redisCodec) {
		this.cache = new LettuceClusterInfoCache<>(clusterNodes, clientConfig, poolConfig, redisCodec);
		initializeSlotsCache(clusterNodes, clientConfig, redisCodec);
	}

	public ClusterConnectionProvider(final Set<HostAndPort> clusterNodes, final LettuceClientConfig clientConfig,
	                                 final GenericObjectPoolConfig<StatefulRedisClusterConnection<K, V>> poolConfig,
	                                 final Duration topologyRefreshPeriod, final RedisCodec<K, V> redisCodec) {
		this.cache = new LettuceClusterInfoCache<>(clusterNodes, clientConfig, poolConfig, topologyRefreshPeriod,
				redisCodec);
		initializeSlotsCache(clusterNodes, clientConfig, redisCodec);
	}

	private void initializeSlotsCache(Set<HostAndPort> startNodes, LettuceClientConfig clientConfig,
	                                  RedisCodec<K, V> redisCodec) {
		if(startNodes.isEmpty()){
			throw new RedisConnectionException("No nodes to initialize cluster slots cache.");
		}

		ArrayList<HostAndPort> startNodeList = new ArrayList<>(startNodes);
		Collections.shuffle(startNodeList);

		RedisException firstException = null;
		for(HostAndPort hostAndPort : startNodeList){
			RedisURI redisURI = RedisURI.create(hostAndPort.getHostText(), hostAndPort.getPort());
			RedisClusterClient redisClusterClient = RedisClusterClient.create(redisURI);
			try(StatefulRedisClusterConnection<K, V> conn = redisClusterClient.connect(redisCodec)){
				cache.discoverClusterNodesAndSlots(conn);
				return;
			}catch(RedisException e){
				if(firstException == null){
					firstException = e;
				}
			}
		}

		RedisConnectionException uninitializedException = new RedisConnectionException(
				"Could not initialize cluster slots cache.");
		if(firstException != null){
			uninitializedException.addSuppressed(firstException);
		}
		throw uninitializedException;
	}

	public Map<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> getNodes() {
		return cache.getNodes();
	}

	public Map<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> getPrimaryNodes() {
		return cache.getPrimaryNodes();
	}

	public StatefulRedisClusterConnection<K, V> getConnection(HostAndPort node) {
		return node != null ? cache.setupNodeIfNotExist(node).getResource() : getConnection();
	}

	@Override
	public StatefulRedisClusterConnection<K, V> getConnection() {
		List<ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> pools = cache.getShuffledPrimaryNodesPool();

		RedisException suppressed = null;
		for(ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> pool : pools){
			StatefulRedisClusterConnection<K, V> connection = null;
			try{
				connection = pool.getResource();
				if(connection == null){
					continue;
				}

				connection.sync().ping();
				return connection;
			}catch(RedisException ex){
				if(suppressed == null){ // remembering first suppressed exception
					suppressed = ex;
				}
				if(connection != null){
					connection.close();
				}
			}
		}

		RedisConnectionException noReachableNode = new RedisConnectionException("No reachable node in cluster.");
		if(suppressed != null){
			noReachableNode.addSuppressed(suppressed);
		}
		throw noReachableNode;
	}

	@Override
	public StatefulRedisClusterConnection<K, V> getConnection(CommandArgs<K, V> commandArgs) {
		//final int slot = ((ClusterCommandArguments) commandArgs).getCommandHashSlot();
		//return slot >= 0 ? getConnectionFromSlot(slot) : getConnection();
		return getConnection();
	}

	public StatefulRedisClusterConnection<K, V> getReplicaConnection(CommandArgs<K, V> commandArgs) {
		//final int slot = ((ClusterCommandArguments) commandArgs).getCommandHashSlot();
		//return slot >= 0 ? getReplicaConnectionFromSlot(slot) : getConnection();
		return getConnection();
	}

	public StatefulRedisClusterConnection<K, V> getConnectionFromSlot(int slot) {
		ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>> connectionPool = cache.getSlotPool(slot);
		if(connectionPool != null){
			// It can't guaranteed to get valid connection because of node assignment
			return connectionPool.getResource();
		}else{
			// It's abnormal situation for cluster mode that we have just nothing for slot.
			// Try to rediscover state
			renewSlotCache();
			connectionPool = cache.getSlotPool(slot);
			if(connectionPool != null){
				return connectionPool.getResource();
			}else{
				// no choice, fallback to new connection to random node
				return getConnection();
			}
		}
	}

	public StatefulRedisClusterConnection<K, V> getReplicaConnectionFromSlot(int slot) {
		List<ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> connectionPools = cache.getSlotReplicaPools(
				slot);
		ThreadLocalRandom random = ThreadLocalRandom.current();
		if(connectionPools != null && !connectionPools.isEmpty()){
			// pick up randomly a connection
			int idx = random.nextInt(connectionPools.size());
			return connectionPools.get(idx).getResource();
		}

		renewSlotCache();
		connectionPools = cache.getSlotReplicaPools(slot);
		if(connectionPools != null && !connectionPools.isEmpty()){
			int idx = random.nextInt(connectionPools.size());
			return connectionPools.get(idx).getResource();
		}

		return getConnectionFromSlot(slot);
	}

	@Override
	public Map<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> getConnectionMap() {
		return Collections.unmodifiableMap(getNodes());
	}

	@Override
	public Map<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> getPrimaryNodesConnectionMap() {
		return Collections.unmodifiableMap(getPrimaryNodes());
	}

	public HostAndPort getNode(int slot) {
		return slot >= 0 ? cache.getSlotNode(slot) : null;
	}

	public void renewSlotCache() {
		cache.renewClusterSlots(null);
	}

	public void renewSlotCache(StatefulRedisClusterConnection<K, V> jedis) {
		cache.renewClusterSlots(jedis);
	}

	@Override
	public void close() {
		cache.close();
	}

}
