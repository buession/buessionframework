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
package com.buession.redis.client.connection.lettuce;

import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.client.connection.RedisNode;
import com.buession.redis.client.connection.datasource.lettuce.LettuceClusterDataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.RedisMode;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.transaction.Transaction;
import io.lettuce.core.DefaultLettuceClientConfig;
import io.lettuce.core.RedisClusterClient;
import io.lettuce.core.builders.ClusterClientBuilder;
import io.lettuce.core.codec.RedisCodec;

import java.time.Duration;
import java.util.Optional;

/**
 * Lettuce 集群模式连接器
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceClusterConnection<K, V> extends AbstractLettuceRedisConnection<K, V, RedisClusterClient<K, V>>
		implements RedisClusterConnection {

	/**
	 * 最大重定向次数
	 */
	private int maxRedirects;

	/**
	 * 最大重数时长
	 */
	private Duration maxTotalRetriesDuration;

	/**
	 * 定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长
	 *
	 * @since 4.0.0
	 */
	private Duration topologyRefreshPeriod;

	/**
	 * 构造函数
	 */
	public LettuceClusterConnection() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public LettuceClusterConnection(LettuceClusterDataSource dataSource) {
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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig) {
		super(dataSource, poolConfig);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param codec
	 * 		Redis 编解码器
	 */
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, RedisCodec<K, V> codec) {
		super(dataSource, codec);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param codec
	 * 		Redis 编解码器
	 */
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig,
	                                RedisCodec<K, V> codec) {
		super(dataSource, poolConfig, codec);
	}

	@Override
	public int getMaxRedirects() {
		return maxRedirects;
	}

	@Override
	public void setMaxRedirects(int maxRedirects) {
		this.maxRedirects = maxRedirects;
	}

	@Override
	public Duration getMaxTotalRetriesDuration() {
		return maxTotalRetriesDuration;
	}

	@Override
	public void setMaxTotalRetriesDuration(Duration maxTotalRetriesDuration) {
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	@Override
	public Duration getTopologyRefreshPeriod() {
		return topologyRefreshPeriod;
	}

	@Override
	public void setTopologyRefreshPeriod(Duration topologyRefreshPeriod) {
		this.topologyRefreshPeriod = topologyRefreshPeriod;
	}

	@Override
	public Transaction multi() {
		throw new NotSupportedCommandException(RedisMode.CLUSTER, RedisCommand.MULTI);
	}


	@Override
	protected void internalInit() {
		super.internalInit();
		if(client == null){
			final LettuceClusterDataSource dataSource = (LettuceClusterDataSource) getDataSource();
			final DefaultLettuceClientConfig.Builder clientConfigBuilder = DefaultLettuceClientConfig.builder();

			commonClientConfigBuilder(clientConfigBuilder);

			final ClusterClientBuilder<K, V, RedisClusterClient<K, V>> builder = RedisClusterClient.<K, V>builder()
					.nodes(createNodes(dataSource.getNodes(), RedisNode.DEFAULT_PORT))
					.clientConfig(clientConfigBuilder.build()).codec(getCodec());

			Optional.ofNullable(getConnectionPoolConfig()).ifPresent(builder::poolConfig);
			//Optional.ofNullable(getCacheConfig()).ifPresent(builder::cacheConfig);
			Optional.ofNullable(getMaxTotalRetriesDuration()).ifPresent(builder::maxTotalRetriesDuration);
			Optional.ofNullable(getTopologyRefreshPeriod()).ifPresent(builder::topologyRefreshPeriod);

			if(getMaxRedirects() > 0){
				builder.maxRedirects(getMaxRedirects());
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
