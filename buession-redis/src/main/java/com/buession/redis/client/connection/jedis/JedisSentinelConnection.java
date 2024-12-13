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

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisSentinelConnection;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisSentinelDataSource;
import com.buession.redis.core.Constants;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.RedisNamedNode;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.RedisSentinelNode;
import com.buession.redis.core.RedisServer;
import com.buession.redis.core.Role;
import com.buession.redis.core.internal.jedis.JedisClientConfigBuilder;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.exception.JedisRedisExceptionUtils;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import com.buession.redis.pipeline.jedis.JedisPipelineProxy;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.transaction.jedis.JedisTransaction;
import com.buession.redis.transaction.jedis.JedisTransactionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisException;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Jedis 哨兵模式连接器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisSentinelConnection extends AbstractJedisRedisConnection implements RedisSentinelConnection {

	/**
	 * 哨兵节点连接超时（单位：毫秒）
	 */
	private int sentinelConnectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

	/**
	 * 哨兵节点读取超时（单位：毫秒）
	 */
	private int sentinelSoTimeout = Constants.DEFAULT_SO_TIMEOUT;

	/**
	 * Jedis 对象
	 */
	private Jedis jedis;

	private final static Logger logger = LoggerFactory.getLogger(JedisSentinelConnection.class);

	/**
	 * 构造函数
	 */
	public JedisSentinelConnection() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource) {
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout) {
		super(dataSource, connectTimeout, soTimeout);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
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
	 *
	 * @since 2.0.0
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
								   int infiniteSoTimeout) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, SslConfiguration sslConfiguration) {
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
								   SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
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
	 *
	 * @since 2.0.0
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
								   int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param pool
	 * 		连接池
	 */
	@Deprecated
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool) {
		super(dataSource);
		dataSource.setPool(pool);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param pool
	 * 		连接池
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 */
	@Deprecated
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout) {
		super(dataSource, connectTimeout, soTimeout);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
		dataSource.setPool(pool);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param pool
	 * 		连接池
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 */
	@Deprecated
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
		dataSource.setPool(pool);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param pool
	 * 		连接池
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	@Deprecated
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool,
								   SslConfiguration sslConfiguration) {
		super(dataSource, sslConfiguration);
		dataSource.setPool(pool);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param pool
	 * 		连接池
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	@Deprecated
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
		dataSource.setPool(pool);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param pool
	 * 		连接池
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
		dataSource.setPool(pool);
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
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
								   int sentinelConnectTimeout, int sentinelSoTimeout) {
		super(dataSource, connectTimeout, soTimeout);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
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
	 * 		Infinite 读取超时
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
								   int infiniteSoTimeout, int sentinelConnectTimeout, int sentinelSoTimeout) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
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
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
								   int sentinelConnectTimeout, int sentinelSoTimeout,
								   SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
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
	 * 		Infinite 读取超时
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
								   int infiniteSoTimeout, int sentinelConnectTimeout, int sentinelSoTimeout,
								   SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param pool
	 * 		连接池
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 */
	@Deprecated
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout) {
		this(dataSource, pool, connectTimeout, soTimeout);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
		dataSource.setPool(pool);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param pool
	 * 		连接池
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 */
	@Deprecated
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
								   int sentinelSoTimeout) {
		this(dataSource, pool, connectTimeout, soTimeout, infiniteSoTimeout);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
		dataSource.setPool(pool);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param pool
	 * 		连接池
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	@Deprecated
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout,
								   SslConfiguration sslConfiguration) {
		this(dataSource, pool, connectTimeout, soTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
		dataSource.setPool(pool);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param pool
	 * 		连接池
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	@Deprecated
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
								   int sentinelSoTimeout, SslConfiguration sslConfiguration) {
		this(dataSource, pool, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
		dataSource.setPool(pool);
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, PoolConfig poolConfig) {
		super(dataSource, poolConfig);
		dataSource.setPool(createPool());
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								   int soTimeout) {
		super(dataSource, poolConfig, connectTimeout, soTimeout);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
		dataSource.setPool(createPool());
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
		dataSource.setPool(createPool());
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, PoolConfig poolConfig,
								   SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, sslConfiguration);
		dataSource.setPool(createPool());
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								   int soTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, sslConfiguration);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
		dataSource.setPool(createPool());
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
		dataSource.setPool(createPool());
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
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 *
	 * @since 3.0.0
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout) {
		super(dataSource, poolConfig, connectTimeout, soTimeout);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
		dataSource.setPool(createPool());
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
	 * 		Infinite 读取超时
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 *
	 * @since 3.0.0
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
								   int sentinelSoTimeout) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
		dataSource.setPool(createPool());
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
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout,
								   SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
		dataSource.setPool(createPool());
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
	 * 		Infinite 读取超时
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
								   int sentinelSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
		dataSource.setPool(createPool());
	}

	@Override
	public int getSentinelConnectTimeout() {
		return sentinelConnectTimeout;
	}

	@Override
	public void setSentinelConnectTimeout(int sentinelConnectTimeout) {
		this.sentinelConnectTimeout = sentinelConnectTimeout;
	}

	@Override
	public int getSentinelSoTimeout() {
		return sentinelSoTimeout;
	}

	@Override
	public void setSentinelSoTimeout(int sentinelSoTimeout) {
		this.sentinelSoTimeout = sentinelSoTimeout;
	}

	@Override
	public List<RedisServer> masters() {
		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return null;
		}

		final List<Map<String, String>> masterNodes = Objects.requireNonNull(createSentinelJedis(
				(JedisSentinelDataSource) dataSource)).sentinelMasters();
		return parseRedisServer(masterNodes, Role.MASTER);
	}

	@Override
	public List<RedisServer> slaves(RedisNamedNode master) {
		Assert.isNull(master, "Redis master node cloud not be 'null' for loading slaves.");
		return slaves(master.getName());
	}

	@Override
	public List<RedisServer> slaves(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading slaves.");

		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return null;
		}

		final List<Map<String, String>> slaveNodes = Objects.requireNonNull(createSentinelJedis(
				(JedisSentinelDataSource) dataSource)).sentinelSlaves(masterName);
		return parseRedisServer(slaveNodes, Role.SLAVE);
	}

	@Override
	public void failover(RedisNamedNode namedNode) {
		Assert.isNull(namedNode, "Redis master node cloud not be 'null' for failover.");
		Assert.isBlank(namedNode.getName(), "Redis master name cloud not be 'null' or empty for failover.");

		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return;
		}

		Objects.requireNonNull(createSentinelJedis((JedisSentinelDataSource) dataSource))
				.sentinelFailover(namedNode.getName());
	}

	@Override
	public void monitor(RedisSentinelNode server) {
		Assert.isNull(server, "Cannot monitor 'null' server.");
		Assert.isBlank(server.getName(), "Name of server to monitor must not be 'null' or empty.");
		Assert.isBlank(server.getHost(), "Host must not be 'null' for server to monitor.");

		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return;
		}

		Objects.requireNonNull(createSentinelJedis((JedisSentinelDataSource) dataSource))
				.sentinelMonitor(server.getName(), server.getHost(), server.getPort(), server.getQuorum());
	}

	public Jedis getJedis() {
		return jedis;
	}

	@Override
	public Pipeline openPipeline() {
		if(pipeline == null){
			final redis.clients.jedis.Pipeline pipelineObject = jedis.pipelined();
			pipeline = new JedisPipelineProxy<>(new JedisPipeline(pipelineObject), pipelineObject);
		}

		return pipeline;
	}

	@Override
	public void closePipeline() {
		pipeline.close();
		pipeline = null;
	}

	@Override
	public Transaction multi() {
		if(transaction == null){
			final redis.clients.jedis.Transaction tran = jedis.multi();
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
	public void remove(RedisNamedNode master) {
		Assert.isNull(master, "Master node cloud be 'null' when trying to remove.");
		remove(master.getName());
	}

	@Override
	public void remove(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud be 'null' or empty when trying to remove.");
		jedis.sentinelRemove(masterName);
	}

	@Override
	public boolean isConnected() {
		return jedis != null && jedis.isConnected();
	}

	@Override
	public boolean isClosed() {
		return jedis == null || jedis.isConnected() == false;
	}

	@Override
	protected void internalInit() {
		final JedisSentinelDataSource dataSource = (JedisSentinelDataSource) getDataSource();
		setUsePool(dataSource.getPool() != null);
	}

	private Jedis createSentinelJedis(final JedisSentinelDataSource dataSource) {
		final JedisClientConfig sentinelClientConfig = createSentinelJedisClientConfig(dataSource);

		for(RedisNode node : dataSource.getSentinels()){
			int port = node.getPort() == 0 ? RedisNode.DEFAULT_SENTINEL_PORT : node.getPort();
			HostAndPort sentinel = new HostAndPort(node.getHost(), port);
			try(Jedis jedis = new Jedis(sentinel, sentinelClientConfig)){
				return jedis;
			}catch(JedisException e){
				logger.warn("Cannot get master address from sentinel running @ {}. Reason: {}. Trying next one.",
						sentinel, e.getMessage());
			}
		}

		return null;
	}

	protected Jedis createJedis() {
		final JedisSentinelDataSource dataSource = (JedisSentinelDataSource) getDataSource();
		final JedisClientConfig sentinelClientConfig = createSentinelJedisClientConfig(dataSource);
		final DefaultJedisClientConfig clientConfig = JedisClientConfigBuilder.create(dataSource,
						getSslConfiguration())
				.connectTimeout(getConnectTimeout())
				.socketTimeout(getSoTimeout())
				.infiniteSoTimeout(getInfiniteSoTimeout())
				.database(dataSource.getDatabase())
				.build();
		boolean sentinelAvailable = false;

		for(RedisNode node : dataSource.getSentinels()){
			int port = node.getPort() == 0 ? RedisNode.DEFAULT_SENTINEL_PORT : node.getPort();
			HostAndPort sentinel = new HostAndPort(node.getHost(), port);
			try(Jedis jedis = new Jedis(sentinel, sentinelClientConfig)){
				List<String> masterAddr = jedis.sentinelGetMasterAddrByName(dataSource.getMasterName());
				sentinelAvailable = true;

				if(masterAddr == null || masterAddr.size() != 2){
					logger.warn("Can not get master addr, master name: {}. Sentinel: {}", dataSource.getMasterName(),
							node);
					continue;
				}

				return new Jedis(new HostAndPort(masterAddr.get(0), Integer.parseInt(masterAddr.get(1))),
						clientConfig);
			}
		}

		if(sentinelAvailable){
			throw new RedisConnectionFailureException("Can connect to sentinel, but " + dataSource.getMasterName()
					+ " seems to be not monitored...");
		}else{
			throw new RedisConnectionFailureException("All sentinels down, cannot determine where is "
					+ dataSource.getMasterName() + " master is running...");
		}
	}

	protected JedisSentinelPool createPool() {
		final JedisSentinelDataSource dataSource = (JedisSentinelDataSource) getDataSource();

		if(dataSource.getPool() == null){
			final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			final Set<HostAndPort> sentinels = createSentinelHosts(dataSource.getSentinels());
			final JedisClientConfig clientConfig = JedisClientConfigBuilder.create(dataSource, getSslConfiguration())
					.connectTimeout(getConnectTimeout())
					.socketTimeout(getSoTimeout())
					.infiniteSoTimeout(getInfiniteSoTimeout())
					.database(dataSource.getDatabase())
					.build();
			final JedisClientConfig sentinelClientConfig = createSentinelJedisClientConfig(dataSource);

			getPoolConfig().toGenericObjectPoolConfig(jedisPoolConfig);

			if(getSslConfiguration() == null){
				logger.debug("Create JedisSentinelPool.");
			}else{
				logger.debug("Create JedisSentinelPool with ssl.");
			}

			return new JedisSentinelPool(dataSource.getMasterName(), sentinels, jedisPoolConfig, clientConfig,
					sentinelClientConfig);
		}else{
			return dataSource.getPool();
		}
	}

	@Override
	protected Status doConnect() throws RedisConnectionFailureException {
		if(isConnected()){
			return Status.SUCCESS;
		}

		if(isUsePool()){
			final JedisSentinelDataSource dataSource = (JedisSentinelDataSource) getDataSource();

			try{
				jedis = dataSource.getPool().getResource();

				if(logger.isDebugEnabled()){
					logger.debug("Jedis initialized with pool success.");
				}
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("Jedis initialized with pool failure: {}", e.getMessage(), e);
				}

				throw JedisRedisExceptionUtils.convert(e);
			}
		}else{
			jedis = createJedis();
		}

		return jedis == null ? Status.FAILURE : Status.SUCCESS;
	}

	@Override
	protected void doDestroy() throws IOException {
		final JedisSentinelDataSource dataSource = (JedisSentinelDataSource) getDataSource();
		super.doDestroy();

		logger.debug("Jedis destroy.");
		if(dataSource.getPool() != null){
			if(logger.isDebugEnabled()){
				logger.debug("Jedis sentinel pool for {} destroy.", dataSource.getPool().getClass().getName());
			}

			try{
				dataSource.getPool().destroy();
			}catch(Exception e){
				if(logger.isWarnEnabled()){
					logger.warn("Cannot properly close Jedis sentinel pool.", e);
				}
				throw new RedisException(e);
			}

			dataSource.setPool(null);
		}
	}

	@Override
	protected void doClose() throws IOException {
		super.doClose();

		if(isUsePool()){
			logger.debug("Jedis close.");

			if(jedis != null){
				jedis.close();
			}
		}else{
			logger.debug("Jedis disconnect.");

			if(jedis != null){
				try{
					jedis.disconnect();
				}catch(Exception e){
					if(logger.isWarnEnabled()){
						logger.warn("Cannot properly disconnect Jedis.", e);
					}
					throw new RedisException(e);
				}
			}
		}
	}

	protected JedisClientConfig createSentinelJedisClientConfig(final JedisSentinelDataSource dataSource) {
		return JedisClientConfigBuilder.create(dataSource, getSslConfiguration())
				.connectTimeout(getSentinelConnectTimeout())
				.socketTimeout(getSentinelSoTimeout())
				.infiniteSoTimeout(getInfiniteSoTimeout())
				.clientName(dataSource.getSentinelClientName())
				.build();
	}

	protected Set<HostAndPort> createSentinelHosts(final Collection<RedisNode> sentinelNodes) {
		if(Validate.isEmpty(sentinelNodes)){
			return Collections.emptySet();
		}

		return sentinelNodes.stream().filter(Objects::nonNull).map(node->{
			int port = node.getPort() == 0 ? RedisNode.DEFAULT_SENTINEL_PORT : node.getPort();
			return new HostAndPort(node.getHost(), port);
		}).collect(Collectors.toSet());
	}

	protected List<RedisServer> parseRedisServer(final List<Map<String, String>> nodes, final Role role) {
		if(nodes == null){
			return null;
		}

		return nodes.stream().filter(Objects::nonNull).map((node)->{
			Properties properties = new Properties();
			properties.putAll(node);

			RedisServer redisServer = new RedisServer(node.get("ip"), Integer.parseInt(node.get("port")),
					properties);
			redisServer.setName(node.get("name"));
			redisServer.setRole(role);

			return redisServer;
		}).collect(Collectors.toList());
	}

}
