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
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.internal.HostAndPort;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.ClusterCommandArguments;
import redis.clients.jedis.CommandArguments;
import redis.clients.jedis.csc.Cache;
import redis.clients.jedis.exceptions.JedisException;

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

	protected final LettuceClusterInfoCache<K, V> cache;

	public ClusterConnectionProvider(Set<HostAndPort> clusterNodes, LettuceClientConfig clientConfig) {
		this.cache = new LettuceClusterInfoCache<>(clientConfig, clusterNodes);
		initializeSlotsCache(clusterNodes, clientConfig);
	}

	public ClusterConnectionProvider(Set<HostAndPort> clusterNodes, LettuceClientConfig clientConfig,
	                                 Cache clientSideCache) {
		this.cache = new LettuceClusterInfoCache<>(clientConfig, clientSideCache, clusterNodes);
		initializeSlotsCache(clusterNodes, clientConfig);
	}

	public ClusterConnectionProvider(Set<HostAndPort> clusterNodes, LettuceClientConfig clientConfig,
	                                 GenericObjectPoolConfig<StatefulRedisClusterConnection<K, V>> poolConfig) {
		this.cache = new LettuceClusterInfoCache<>(clientConfig, poolConfig, clusterNodes);
		initializeSlotsCache(clusterNodes, clientConfig);
	}

	public ClusterConnectionProvider(Set<HostAndPort> clusterNodes, LettuceClientConfig clientConfig,
	                                 Cache clientSideCache,
	                                 GenericObjectPoolConfig<StatefulRedisClusterConnection<K, V>> poolConfig) {
		this.cache = new LettuceClusterInfoCache<>(clientConfig, clientSideCache, poolConfig, clusterNodes);
		initializeSlotsCache(clusterNodes, clientConfig);
	}

	public ClusterConnectionProvider(Set<HostAndPort> clusterNodes, LettuceClientConfig clientConfig,
	                                 GenericObjectPoolConfig<StatefulRedisClusterConnection<K, V>> poolConfig,
	                                 Duration topologyRefreshPeriod) {
		this.cache = new LettuceClusterInfoCache<>(clientConfig, poolConfig, clusterNodes, topologyRefreshPeriod);
		initializeSlotsCache(clusterNodes, clientConfig);
	}

	public ClusterConnectionProvider(Set<HostAndPort> clusterNodes, LettuceClientConfig clientConfig,
	                                 Cache clientSideCache,
	                                 GenericObjectPoolConfig<StatefulRedisClusterConnection<K, V>> poolConfig,
	                                 Duration topologyRefreshPeriod) {
		this.cache = new LettuceClusterInfoCache<>(clientConfig, clientSideCache, poolConfig, clusterNodes,
				topologyRefreshPeriod);
		initializeSlotsCache(clusterNodes, clientConfig);
	}

	@Override
	public void close() {
		cache.close();
	}

	public void renewSlotCache() {
		cache.renewClusterSlots(null);
	}

	public void renewSlotCache(StatefulRedisClusterConnection<K, V> jedis) {
		cache.renewClusterSlots(jedis);
	}

	public Map<String, ConnectionPool<K, V>> getNodes() {
		return cache.getNodes();
	}

	public Map<String, ConnectionPool<K, V>> getPrimaryNodes() {
		return cache.getPrimaryNodes();
	}

	public HostAndPort getNode(int slot) {
		return slot >= 0 ? cache.getSlotNode(slot) : null;
	}

	public StatefulRedisClusterConnection<K, V> getConnection(HostAndPort node) {
		return node != null ? cache.setupNodeIfNotExist(node).getResource() : getConnection();
	}

	@Override
	public StatefulRedisClusterConnection<K, V> getConnection(CommandArguments args) {
		final int slot = ((ClusterCommandArguments) args).getCommandHashSlot();
		return slot >= 0 ? getConnectionFromSlot(slot) : getConnection();
	}

	public StatefulRedisClusterConnection<K, V> getReplicaConnection(CommandArguments args) {
		final int slot = ((ClusterCommandArguments) args).getCommandHashSlot();
		return slot >= 0 ? getReplicaConnectionFromSlot(slot) : getConnection();
	}

	@Override
	public StatefulRedisClusterConnection<K, V> getConnection() {
		List<ConnectionPool<K, V>> pools = cache.getShuffledPrimaryNodesPool();

		JedisException suppressed = null;
		for(ConnectionPool<K, V> pool : pools){
			StatefulRedisClusterConnection<K, V> jedis = null;
			try{
				jedis = pool.getResource();
				if(jedis == null){
					continue;
				}

				jedis.ping();
				return jedis;

			}catch(JedisException ex){
				if(suppressed == null){ // remembering first suppressed exception
					suppressed = ex;
				}
				if(jedis != null){
					jedis.close();
				}
			}
		}

		RedisConnectionException noReachableNode = new RedisConnectionException("No reachable node in cluster.");
		if(suppressed != null){
			noReachableNode.addSuppressed(suppressed);
		}
		throw noReachableNode;
	}

	public StatefulRedisClusterConnection<K, V> getConnectionFromSlot(int slot) {
		ConnectionPool<K, V> connectionPool = cache.getSlotPool(slot);
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
		List<ConnectionPool<K, V>> connectionPools = cache.getSlotReplicaPools(slot);
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
	public Map<String, ConnectionPool<K, V>> getConnectionMap() {
		return Collections.unmodifiableMap(getNodes());
	}

	@Override
	public Map<String, ConnectionPool<K, V>> getPrimaryNodesConnectionMap() {
		return Collections.unmodifiableMap(getPrimaryNodes());
	}

	private void initializeSlotsCache(Set<HostAndPort> startNodes, LettuceClientConfig clientConfig) {
		if(startNodes.isEmpty()){
			throw new RedisConnectionException("No nodes to initialize cluster slots cache.");
		}

		ArrayList<HostAndPort> startNodeList = new ArrayList<>(startNodes);
		Collections.shuffle(startNodeList);

		JedisException firstException = null;
		for(HostAndPort hostAndPort : startNodeList){
			try(Connection jedis = new Connection(hostAndPort, clientConfig)){
				cache.discoverClusterNodesAndSlots(jedis);
				return;
			}catch(JedisException e){
				if(firstException == null){
					firstException = e;
				}
				// try next nodes
			}
		}

		RedisConnectionException uninitializedException
				= new RedisConnectionException("Could not initialize cluster slots cache.");
		uninitializedException.addSuppressed(firstException);
		throw uninitializedException;
	}

}
