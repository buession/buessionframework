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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.jedis;

import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.client.connection.datasource.jedis.JedisClusterDataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.internal.jedis.JedisClientConfigBuilder;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.jedis.JedisClusterPipeline;
import com.buession.redis.pipeline.jedis.JedisPipelineProxy;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.transaction.jedis.JedisTransaction;
import com.buession.redis.transaction.jedis.JedisTransactionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.providers.ClusterConnectionProvider;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Jedis 集群模式连接器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisClusterConnection extends AbstractJedisRedisConnection implements RedisClusterConnection {

	/**
	 * 最大重定向次数
	 */
	private int maxRedirects;

	/**
	 * 最大重数时长（单位：毫秒）
	 */
	private int maxTotalRetriesDuration;

	/**
	 * 连接提供者
	 *
	 * @since 3.0.0
	 */
	private ClusterConnectionProvider connectionProvider;

	/**
	 * JedisCluster 对象
	 */
	private JedisCluster cluster;

	private final static Logger logger = LoggerFactory.getLogger(JedisClusterConnection.class);

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
	 * 		最大重试时长（单位：毫秒）
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout,
								  int infiniteSoTimeout, int maxRedirects, int maxTotalRetriesDuration) {
		this(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, maxRedirects);
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
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
	 * 		最大重试时长（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, int connectTimeout, int soTimeout,
								  int infiniteSoTimeout, int maxRedirects, int maxTotalRetriesDuration,
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
	 * @param cluster
	 *        {@link JedisCluster}
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster) {
		super(dataSource);
		this.cluster = cluster;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param cluster
	 *        {@link JedisCluster}
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster, int connectTimeout,
								  int soTimeout) {
		super(dataSource, connectTimeout, soTimeout);
		this.cluster = cluster;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param cluster
	 *        {@link JedisCluster}
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster, int connectTimeout,
								  int soTimeout, int infiniteSoTimeout) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout);
		this.cluster = cluster;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param cluster
	 *        {@link JedisCluster}
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster,
								  SslConfiguration sslConfiguration) {
		super(dataSource, sslConfiguration);
		this.cluster = cluster;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param cluster
	 *        {@link JedisCluster}
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster, int connectTimeout,
								  int soTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
		this.cluster = cluster;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param cluster
	 *        {@link JedisCluster}
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster, int connectTimeout,
								  int soTimeout, int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.cluster = cluster;
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
	 * @param cluster
	 *        {@link JedisCluster}
	 * @param maxRedirects
	 * 		最大重试次数
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster, int maxRedirects) {
		this(dataSource, cluster);
		this.maxRedirects = maxRedirects;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param cluster
	 *        {@link JedisCluster}
	 * @param maxRedirects
	 * 		最大重试次数
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster, int maxRedirects,
								  SslConfiguration sslConfiguration) {
		this(dataSource, cluster, sslConfiguration);
		this.maxRedirects = maxRedirects;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param cluster
	 *        {@link JedisCluster}
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数（单位：毫秒）
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster, int connectTimeout,
								  int soTimeout, int maxRedirects, int maxTotalRetriesDuration) {
		this(dataSource, cluster, connectTimeout, soTimeout);
		this.maxRedirects = maxRedirects;
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param cluster
	 *        {@link JedisCluster}
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster, int connectTimeout,
								  int soTimeout, int maxRedirects, int maxTotalRetriesDuration,
								  SslConfiguration sslConfiguration) {
		this(dataSource, cluster, connectTimeout, soTimeout, sslConfiguration);
		this.maxRedirects = maxRedirects;
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param cluster
	 *        {@link JedisCluster}
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster, int connectTimeout,
								  int soTimeout, int infiniteSoTimeout, int maxRedirects, int maxTotalRetriesDuration) {
		this(dataSource, cluster, connectTimeout, soTimeout, infiniteSoTimeout);
		this.maxRedirects = maxRedirects;
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param cluster
	 *        {@link JedisCluster}
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	@Deprecated
	public JedisClusterConnection(JedisClusterDataSource dataSource, JedisCluster cluster, int connectTimeout,
								  int soTimeout, int infiniteSoTimeout, int maxRedirects, int maxTotalRetriesDuration,
								  SslConfiguration sslConfiguration) {
		this(dataSource, cluster, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.maxRedirects = maxRedirects;
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		连接池配置
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(PoolConfig poolConfig) {
		super(poolConfig);
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
	 * 		最大重试时长（单位：毫秒）
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								  int soTimeout, int maxRedirects, int maxTotalRetriesDuration) {
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
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								  int soTimeout, int maxRedirects, int maxTotalRetriesDuration,
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
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								  int soTimeout, int infiniteSoTimeout, int maxRedirects, int maxTotalRetriesDuration) {
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
	 * 		最大重试时长（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public JedisClusterConnection(JedisClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								  int soTimeout, int infiniteSoTimeout, int maxRedirects, int maxTotalRetriesDuration,
								  SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.maxRedirects = maxRedirects;
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
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
	public int getMaxTotalRetriesDuration() {
		return maxTotalRetriesDuration;
	}

	@Override
	public void setMaxTotalRetriesDuration(int maxTotalRetriesDuration) {
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	public JedisCluster getCluster() {
		return cluster;
	}

	@Override
	public Pipeline openPipeline() {
		if(pipeline == null){
			final redis.clients.jedis.ClusterPipeline pipelineObject = cluster.pipelined();
			pipeline = new JedisPipelineProxy<>(new JedisClusterPipeline(pipelineObject), pipelineObject);
		}

		return pipeline;
	}

	@Override
	public Transaction multi() {
		if(transaction == null){
			final redis.clients.jedis.Transaction tran = cluster.multi();
			transaction = new JedisTransactionProxy(new JedisTransaction(tran), tran);
		}

		return transaction;
	}

	@Override
	public List<Object> exec() throws RedisException {
		if(transaction != null){
			final List<Object> result = transaction.exec();

			transaction.close();
			transaction = null;

			return result;
		}else{
			throw new RedisException("ERR EXEC without MULTI. Did you forget to call multi?");
		}
	}

	@Override
	public void discard() throws RedisException {
		if(transaction != null){
			transaction.discard();
			transaction = null;
		}else{
			throw new RedisException("ERR DISCARD without MULTI. Did you forget to call multi?");
		}
	}

	@Override
	public boolean isConnect() {
		return cluster != null;
	}

	@Override
	public boolean isClosed() {
		return cluster == null;
	}

	@Override
	protected void internalInit() {
		if(cluster == null){
			connectionProvider = createConnectionProvider();
		}
	}

	protected boolean isUsePool() {
		return getPoolConfig() != null;
	}

	@Override
	protected void doConnect() throws RedisConnectionFailureException {
		cluster = new JedisCluster(connectionProvider, getMaxRedirects(),
				Duration.ofMillis(getMaxTotalRetriesDuration()));
	}

	protected ClusterConnectionProvider createConnectionProvider() {
		final JedisClusterDataSource dataSource = (JedisClusterDataSource) getDataSource();
		final JedisClientConfig clientConfig = JedisClientConfigBuilder.create(dataSource, getSslConfiguration())
				.connectTimeout(getConnectTimeout())
				.socketTimeout(getSoTimeout())
				.infiniteSoTimeout(getInfiniteSoTimeout())
				.build();
		final Set<HostAndPort> nodes = createHostAndPorts(dataSource);

		if(isUsePool()){
			final ConnectionPoolConfig connectionPoolConfig = new ConnectionPoolConfig();

			getPoolConfig().toGenericObjectPoolConfig(connectionPoolConfig);

			return new ClusterConnectionProvider(nodes, clientConfig, connectionPoolConfig);
		}else{
			return new ClusterConnectionProvider(nodes, clientConfig);
		}
	}

	@Override
	protected void doDestroy() throws IOException {
		super.doDestroy();
		logger.info("Jedis cluster destroy.");
	}

	@Override
	protected void doClose() throws IOException {
		super.doClose();

		logger.info("Jedis cluster close.");
		if(cluster != null){
			cluster.close();
		}
	}

	private static Set<HostAndPort> createHostAndPorts(final JedisClusterDataSource clusterDataSource) {
		return clusterDataSource.getNodes().stream().map((node)->{
			int port = node.getPort() == 0 ? RedisNode.DEFAULT_PORT : node.getPort();
			return new HostAndPort(node.getHost(), port);
		}).collect(Collectors.toSet());
	}

}
