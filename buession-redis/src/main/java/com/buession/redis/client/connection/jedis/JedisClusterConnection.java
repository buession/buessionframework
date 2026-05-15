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
package com.buession.redis.client.connection.jedis;

import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.client.connection.RedisNode;
import com.buession.redis.client.connection.datasource.jedis.JedisClusterDataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.RedisMode;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.transaction.Transaction;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.RedisClusterClient;
import redis.clients.jedis.builders.ClusterClientBuilder;

import java.time.Duration;
import java.util.Optional;

/**
 * Jedis 集群模式连接器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisClusterConnection extends AbstractJedisRedisConnection<RedisClusterClient>
		implements RedisClusterConnection {

	/**
	 * 最大重定向次数
	 */
	private int maxRedirects = JedisCluster.DEFAULT_MAX_ATTEMPTS;

	/**
	 * 最大重数时长（单位：毫秒）
	 */
	private int maxTotalRetries;

	/**
	 * 定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长（单位：毫秒）
	 *
	 * @since 4.0.0
	 */
	private int topologyRefreshPeriod;

	/**
	 * 构造函数
	 */
	public JedisClusterConnection() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig) {
		super(dataSource, poolConfig);
	}

	@Override
	public int getMaxRedirects() {
		return maxRedirects;
	}

	@Override
	public void setMaxRedirects(int maxRedirects) {
		this.maxRedirects = maxRedirects;
	}

	/**
	 * 获取最大重试持续时长
	 *
	 * @return 最大重试持续时长（单位：毫秒）
	 *
	 * @since 4.0.0
	 */
	public int getMaxTotalRetries() {
		return maxTotalRetries;
	}

	/**
	 * 设置最大重试持续时长
	 *
	 * @param maxTotalRetries
	 * 		最大重试持续时长（单位：毫秒）
	 *
	 * @since 4.0.0
	 */
	public void setMaxTotalRetries(int maxTotalRetries) {
		this.maxTotalRetries = maxTotalRetries;
	}

	@Override
	public int getTopologyRefreshPeriod() {
		return topologyRefreshPeriod;
	}

	@Override
	public void setTopologyRefreshPeriod(int topologyRefreshPeriod) {
		this.topologyRefreshPeriod = topologyRefreshPeriod;
	}

	@Override
	public Transaction multi() {
		throw new NotSupportedCommandException(RedisMode.CLUSTER, RedisCommand.MULTI);
	}

	@Override
	protected void internalInit() {
		if(client == null){
			final JedisClusterDataSource dataSource = (JedisClusterDataSource) getDataSource();
			final DefaultJedisClientConfig.Builder clientConfigBuilder = DefaultJedisClientConfig.builder();

			commonClientConfigBuilder(clientConfigBuilder);

			final ClusterClientBuilder<RedisClusterClient> builder = RedisClusterClient.builder()
					.nodes(createNodes(dataSource.getNodes(), RedisNode.DEFAULT_PORT))
					.clientConfig(clientConfigBuilder.build());

			Optional.ofNullable(getConnectionPoolConfig()).ifPresent(builder::poolConfig);
			Optional.ofNullable(getCacheConfig()).ifPresent(builder::cacheConfig);

			if(getMaxRedirects() > 0){
				builder.maxAttempts(getMaxRedirects());
			}
			if(getMaxTotalRetries() > 0){
				builder.maxTotalRetriesDuration(Duration.ofMillis(getMaxTotalRetries()));
			}
			if(getTopologyRefreshPeriod() > 0){
				builder.topologyRefreshPeriod(Duration.ofMillis(getTopologyRefreshPeriod()));
			}

			client = builder.build();
		}
	}

	@Override
	protected Status doConnect() throws RedisConnectionFailureException {
		if(isConnected()){
			return Status.SUCCESS;
		}

		return client == null ? Status.FAILURE : Status.SUCCESS;
	}

}
