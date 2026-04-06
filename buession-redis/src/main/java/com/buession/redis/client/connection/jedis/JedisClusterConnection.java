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
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.client.connection.datasource.jedis.JedisClusterDataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.internal.jedis.JedisClientConfigBuilder;
import com.buession.redis.exception.RedisConnectionFailureException;
import redis.clients.jedis.DefaultJedisClientConfig;
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
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout) {
		super(dataSource, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout,
	                              int infiniteSoTimeout) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout,
	                              int infiniteSoTimeout, int maxRedirects) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout);
		this.maxRedirects = maxRedirects;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout,
	                              int infiniteSoTimeout, int maxRedirects, Duration maxTotalRetriesDuration) {
		this(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, maxRedirects);
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 * @param topologyRefreshPeriod
	 * 		定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长
	 *
	 * @since 4.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout,
	                              int infiniteSoTimeout, int maxRedirects, Duration maxTotalRetriesDuration,
	                              Duration topologyRefreshPeriod) {
		this(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, maxRedirects, maxTotalRetriesDuration);
		this.topologyRefreshPeriod = topologyRefreshPeriod;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, SslConfiguration sslConfiguration) {
		super(dataSource, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout,
	                              SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout,
	                              int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout,
	                              int infiniteSoTimeout, int maxRedirects, SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.maxRedirects = maxRedirects;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout,
	                              int infiniteSoTimeout, int maxRedirects, Duration maxTotalRetriesDuration,
	                              SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.maxRedirects = maxRedirects;
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 * @param topologyRefreshPeriod
	 * 		定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout,
	                              int infiniteSoTimeout, int maxRedirects, Duration maxTotalRetriesDuration,
	                              Duration topologyRefreshPeriod, SslConfiguration sslConfiguration) {
		this(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, maxRedirects, maxTotalRetriesDuration,
				sslConfiguration);
		this.topologyRefreshPeriod = topologyRefreshPeriod;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param maxRedirects
	 * 		最大重试次数
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int maxRedirects) {
		super(dataSource);
		this.maxRedirects = maxRedirects;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param maxRedirects
	 * 		最大重试次数
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int maxRedirects,
	                              SslConfiguration sslConfiguration) {
		this(dataSource, sslConfiguration);
		this.maxRedirects = maxRedirects;
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

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout) {
		super(dataSource, poolConfig, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout, int infiniteSoTimeout) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig,
	                              SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout, int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param maxRedirects
	 * 		最大重试次数
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int maxRedirects) {
		super(dataSource, poolConfig);
		this.maxRedirects = maxRedirects;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param maxRedirects
	 * 		最大重试次数
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int maxRedirects,
	                              SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, sslConfiguration);
		this.maxRedirects = maxRedirects;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数（单位：毫秒）
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout, int maxRedirects, Duration maxTotalRetriesDuration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout);
		this.maxRedirects = maxRedirects;
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数（单位：毫秒）
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 * @param topologyRefreshPeriod
	 * 		定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长
	 *
	 * @since 4.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout, int maxRedirects, Duration maxTotalRetriesDuration,
	                              Duration topologyRefreshPeriod) {
		this(dataSource, poolConfig, connectTimeout, soTimeout, maxRedirects, maxTotalRetriesDuration);
		this.topologyRefreshPeriod = topologyRefreshPeriod;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout, int maxRedirects, Duration maxTotalRetriesDuration,
	                              SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, sslConfiguration);
		this.maxRedirects = maxRedirects;
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 * @param topologyRefreshPeriod
	 * 		定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 4.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout, int maxRedirects, Duration maxTotalRetriesDuration,
	                              Duration topologyRefreshPeriod, SslConfiguration sslConfiguration) {
		this(dataSource, poolConfig, connectTimeout, soTimeout, maxRedirects, maxTotalRetriesDuration,
				sslConfiguration);
		this.topologyRefreshPeriod = topologyRefreshPeriod;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout, int infiniteSoTimeout, int maxRedirects,
	                              Duration maxTotalRetriesDuration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout);
		this.maxRedirects = maxRedirects;
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 * @param topologyRefreshPeriod
	 * 		定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长
	 *
	 * @since 4.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout, int infiniteSoTimeout, int maxRedirects,
	                              Duration maxTotalRetriesDuration, Duration topologyRefreshPeriod) {
		this(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, maxRedirects,
				maxTotalRetriesDuration);
		this.topologyRefreshPeriod = topologyRefreshPeriod;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout, int infiniteSoTimeout, int maxRedirects,
	                              Duration maxTotalRetriesDuration, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.maxRedirects = maxRedirects;
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长
	 * @param topologyRefreshPeriod
	 * 		定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 4.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                              int soTimeout, int infiniteSoTimeout, int maxRedirects,
	                              Duration maxTotalRetriesDuration, Duration topologyRefreshPeriod,
	                              SslConfiguration sslConfiguration) {
		this(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, maxRedirects,
				maxTotalRetriesDuration, sslConfiguration);
		this.topologyRefreshPeriod = topologyRefreshPeriod;
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
	protected Status doConnect() throws RedisConnectionFailureException {
		if(isConnected()){
			return Status.SUCCESS;
		}

		if(client == null){
			final JedisClusterDataSource dataSource = (JedisClusterDataSource) getDataSource();
			final DefaultJedisClientConfig clientConfig = JedisClientConfigBuilder.create(dataSource,
							getSslConfiguration()).connectTimeout(getConnectTimeout()).socketTimeout(getSoTimeout())
					.infiniteSoTimeout(getInfiniteSoTimeout()).build();

			final ClusterClientBuilder<RedisClusterClient> builder = RedisClusterClient.builder()
					.clientConfig(clientConfig);

			Optional.ofNullable(getConnectionPoolConfig()).ifPresent(builder::poolConfig);
			Optional.ofNullable(getCacheConfig()).ifPresent(builder::cacheConfig);
			Optional.ofNullable(getMaxTotalRetriesDuration()).ifPresent(builder::maxTotalRetriesDuration);
			Optional.ofNullable(getTopologyRefreshPeriod()).ifPresent(builder::topologyRefreshPeriod);

			if(getMaxRedirects() > 0){
				builder.maxAttempts(getMaxRedirects());
			}

			client = builder.build();
		}

		return client == null ? Status.FAILURE : Status.SUCCESS;
	}

}
