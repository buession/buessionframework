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
import com.buession.redis.client.connection.datasource.ClusterDataSource;
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
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
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
	private int maxRedirects = ClusterDataSource.DEFAULT_MAX_ATTEMPTS;

	/**
	 * timeout for rate-limit adaptive topology updates（单位：毫秒）
	 */
	private int adaptiveRefreshTimeout = (int) ClusterTopologyRefreshOptions.DEFAULT_ADAPTIVE_REFRESH_TIMEOUT;

	/**
	 * 定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长（单位：毫秒）
	 *
	 * @since 4.0.0
	 */
	private int topologyRefreshPeriod = (int) ClusterTopologyRefreshOptions.DEFAULT_REFRESH_PERIOD;

	/**
	 * Whether to discover and query all cluster nodes for obtaining the
	 * cluster topology. When set to false, only the initial seed nodes are
	 * used as sources for topology discovery.
	 *
	 * @since 4.0.0
	 */
	private boolean dynamicRefreshSources = ClusterTopologyRefreshOptions.DEFAULT_DYNAMIC_REFRESH_SOURCES;

	/**
	 * Whether adaptive topology refreshing using all available refresh
	 * triggers should be used.
	 *
	 * @since 4.0.0
	 */
	private boolean adaptive;

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
		setDynamicRefreshSources(dataSource.isDynamicRefreshSources());
		setAdaptive(dataSource.isAdaptive());
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
		setDynamicRefreshSources(dataSource.isDynamicRefreshSources());
		setAdaptive(dataSource.isAdaptive());
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
		setDynamicRefreshSources(dataSource.isDynamicRefreshSources());
		setAdaptive(dataSource.isAdaptive());
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
		setDynamicRefreshSources(dataSource.isDynamicRefreshSources());
		setAdaptive(dataSource.isAdaptive());
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
	 * Returns the timeout for adaptive topology updates. This timeout is to rate-limit topology updates initiated by refresh
	 * triggers to one topology refresh per timeout. Defaults to {@literal 30 SECONDS}. See
	 * {@link io.lettuce.core.cluster.ClusterTopologyRefreshOptions#DEFAULT_REFRESH_PERIOD}
	 * and {@link io.lettuce.core.cluster.ClusterTopologyRefreshOptions#DEFAULT_REFRESH_PERIOD_UNIT}.
	 *
	 * @return The imeout for rate-limit adaptive topology updates
	 *
	 * @since 4.0.0
	 */
	public int getAdaptiveRefreshTimeout() {
		return adaptiveRefreshTimeout;
	}

	/**
	 * Set the timeout for adaptive topology updates. This timeout is to rate-limit topology updates initiated by refresh
	 * triggers to one topology refresh per timeout. Defaults to {@literal 30 SECONDS}. See
	 * {@link io.lettuce.core.cluster.ClusterTopologyRefreshOptions#DEFAULT_REFRESH_PERIOD}
	 * and {@link io.lettuce.core.cluster.ClusterTopologyRefreshOptions#DEFAULT_REFRESH_PERIOD_UNIT}.
	 *
	 * @param adaptiveRefreshTimeout
	 * 		timeout for rate-limit adaptive topology updates, must be greater than {@literal 0}.
	 *
	 * @since 4.0.0
	 */
	public void setAdaptiveRefreshTimeout(int adaptiveRefreshTimeout) {
		this.adaptiveRefreshTimeout = adaptiveRefreshTimeout;
	}

	@Override
	public int getTopologyRefreshPeriod() {
		return topologyRefreshPeriod;
	}

	@Override
	public void setTopologyRefreshPeriod(int topologyRefreshPeriod) {
		this.topologyRefreshPeriod = topologyRefreshPeriod;
	}

	public boolean isDynamicRefreshSources() {
		return getDynamicRefreshSources();
	}

	public boolean getDynamicRefreshSources() {
		return dynamicRefreshSources;
	}

	public void setDynamicRefreshSources(boolean dynamicRefreshSources) {
		this.dynamicRefreshSources = dynamicRefreshSources;
	}

	public boolean isAdaptive() {
		return getAdaptive();
	}

	public boolean getAdaptive() {
		return adaptive;
	}

	public void setAdaptive(boolean adaptive) {
		this.adaptive = adaptive;
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
					.clientConfig(clientConfigBuilder.build()).codec(getCodec()).adaptive(isAdaptive())
					.dynamicRefreshSources(getDynamicRefreshSources());

			Optional.ofNullable(getConnectionPoolConfig()).ifPresent(builder::poolConfig);
			//Optional.ofNullable(getCacheConfig()).ifPresent(builder::cacheConfig);
			if(getMaxRedirects() > 0){
				builder.maxRedirects(getMaxRedirects());
			}
			if(getAdaptiveRefreshTimeout() > 0){
				builder.adaptiveRefreshTimeout(Duration.ofMillis(getAdaptiveRefreshTimeout()));
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
