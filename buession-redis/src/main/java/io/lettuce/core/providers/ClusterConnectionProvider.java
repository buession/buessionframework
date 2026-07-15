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
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.utils.IOUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Lettuce Redis 集群连接池连接提供者
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ClusterConnectionProvider<K, V> extends AbstractConnectionProvider<K, V> {

	private volatile ConnectionPool<K, V, StatefulConnection<K, V>> pool;

	public ClusterConnectionProvider(final Set<HostAndPort> clusterNodes, final LettuceClientConfig clientConfig,
	                                 final RedisCodec<K, V> redisCodec) {
		this(clusterNodes, clientConfig, null, redisCodec);
	}

	public ClusterConnectionProvider(final Set<HostAndPort> clusterNodes, final LettuceClientConfig clientConfig,
	                                 final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                 final RedisCodec<K, V> redisCodec) {
		this.pool = createPool(clusterNodes, clientConfig, poolConfig, null, null, redisCodec);
	}

	public ClusterConnectionProvider(final Set<HostAndPort> clusterNodes, final LettuceClientConfig clientConfig,
	                                 final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                 final Integer maxRedirects, final RedisCodec<K, V> redisCodec) {
		this.pool = createPool(clusterNodes, clientConfig, poolConfig, null, maxRedirects, redisCodec);
	}

	public ClusterConnectionProvider(final Set<HostAndPort> clusterNodes, final LettuceClientConfig clientConfig,
	                                 final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                 final ClusterTopologyRefreshOptions topologyRefreshOptions,
	                                 final RedisCodec<K, V> redisCodec) {
		this(clusterNodes, clientConfig, poolConfig, topologyRefreshOptions, null, redisCodec);
	}

	public ClusterConnectionProvider(final Set<HostAndPort> clusterNodes, final LettuceClientConfig clientConfig,
	                                 final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                 final ClusterTopologyRefreshOptions topologyRefreshOptions,
	                                 final Integer maxRedirects, final RedisCodec<K, V> redisCodec) {
		this.pool = createPool(clusterNodes, clientConfig, poolConfig, topologyRefreshOptions, maxRedirects,
				redisCodec);
	}

	@Override
	public StatefulConnection<K, V> getConnection() {
		return pool.getResource();
	}

	@Override
	public StatefulConnection<K, V> getConnection(CommandArgs<K, V> commandArgs) {
		return pool.getResource();
	}

	@Override
	public void close() {
		IOUtils.closeQuietly(pool);
	}

	private RedisClusterClient createRedisClusterClient(final Set<HostAndPort> clusterNodes,
	                                                    final LettuceClientConfig clientConfig,
	                                                    final ClusterTopologyRefreshOptions topologyRefreshOptions,
	                                                    final Integer maxRedirects) {
		final List<RedisURI> redisURIS = new ArrayList<>(clusterNodes.size());

		for(HostAndPort sentinel : clusterNodes){
			RedisURI redisURI = RedisURI.create(sentinel.getHostText(), sentinel.getPort());

			propertyMapper.from(clientConfig.getCredentialsProvider()).to(redisURI::setCredentialsProvider);
			propertyMapper.from(clientConfig.getClientName()).to(redisURI::setClientName);
			if(clientConfig.isSsl()){
				redisURI.setSsl(true);
			}

			if(clientConfig.getSocketTimeout() != null && clientConfig.getSocketTimeout().isNegative() == false){
				redisURI.setTimeout(clientConfig.getSocketTimeout());
			}
			redisURIS.add(redisURI);
		}

		final RedisClusterClient redisClusterClient = RedisClusterClient.create(createClientResources(clientConfig),
				redisURIS);

		propertyMapper.from(createClusterClientOptions(clientConfig, topologyRefreshOptions, maxRedirects))
				.to(redisClusterClient::setOptions);

		return redisClusterClient;
	}

	private ConnectionPool<K, V, StatefulConnection<K, V>> createPool(final Set<HostAndPort> clusterNodes,
	                                                                  final LettuceClientConfig clientConfig,
	                                                                  final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                                                  final ClusterTopologyRefreshOptions topologyRefreshOptions,
	                                                                  final Integer maxRedirects,
	                                                                  final RedisCodec<K, V> redisCodec) {
		final RedisClusterClient redisClusterClient = createRedisClusterClient(clusterNodes, clientConfig,
				topologyRefreshOptions, maxRedirects);

		if(poolConfig == null){
			return new ConnectionPool<>(redisClusterClient, redisCodec);
		}else{
			return new ConnectionPool<>(redisClusterClient, poolConfig, redisCodec);
		}
	}

	private static ClusterClientOptions createClusterClientOptions(final LettuceClientConfig clientConfig,
	                                                               final ClusterTopologyRefreshOptions topologyRefreshOptions,
	                                                               final Integer maxRedirects) {
		final ClusterClientOptions.Builder builder = ClusterClientOptions.builder();

		builder.autoReconnect(clientConfig.isAutoReconnect());
		builder.socketOptions(createSocketOptions(clientConfig));
		propertyMapper.from(clientConfig.getRequestQueueSize()).to(builder::requestQueueSize);
		propertyMapper.from(topologyRefreshOptions).to(builder::topologyRefreshOptions);
		propertyMapper.from(maxRedirects).to(builder::maxRedirects);

		if(clientConfig.isSsl()){
			propertyMapper.from(clientConfig.getSslOptions()).to(builder::sslOptions);
		}

		return builder.build();
	}


	/*

	public Map<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> getNodes() {
		return cache.getNodes();
	}

	public Map<String, ConnectionPool<K, V, StatefulRedisClusterConnection<K, V>>> getPrimaryNodes() {
		return cache.getPrimaryNodes();
	}

	public StatefulRedisClusterConnection<K, V> getConnection(HostAndPort node) {
		return node != null ? cache.setupNodeIfNotExist(node).getResource() : getConnection();
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
		if(connectionPools != null && connectionPools.isEmpty() == false){
			// pick up randomly a connection
			int idx = random.nextInt(connectionPools.size());
			return connectionPools.get(idx).getResource();
		}

		renewSlotCache();
		connectionPools = cache.getSlotReplicaPools(slot);
		if(connectionPools != null && connectionPools.isEmpty() == false){
			int idx = random.nextInt(connectionPools.size());
			return connectionPools.get(idx).getResource();
		}

		return getConnectionFromSlot(slot);
	}

	public HostAndPort getNode(int slot) {
		return slot >= 0 ? cache.getSlotNode(slot) : null;
	}

	public void renewSlotCache() {
		cache.renewClusterSlots(null);
	}

	public void renewSlotCache(StatefulRedisClusterConnection<K, V> connection) {
		cache.renewClusterSlots(connection);
	}

	 */

}
