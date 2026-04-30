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

import com.buession.core.utils.FieldUtils;
import com.buession.redis.client.connection.AbstractRedisConnection;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisRedisDataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.exception.JedisRedisExceptionUtils;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import com.buession.redis.pipeline.DefaultPipelineProxy;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.transaction.jedis.JedisTransaction;
import com.buession.redis.transaction.DefaultTransactionProxy;
import redis.clients.jedis.Connection;
import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.csc.CacheConfig;
import redis.clients.jedis.providers.ConnectionProvider;

import java.io.IOException;

/**
 * Jedis Redis 连接对象抽象类
 *
 * @param <C>
 *        {@link UnifiedJedis} 类型
 *
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisConnection<C extends UnifiedJedis> extends AbstractRedisConnection<C>
		implements JedisRedisConnection<C> {

	private ConnectionProvider connectionProvider;

	/**
	 * 缓存配置
	 *
	 * @since 4.0.0
	 */
	private CacheConfig cacheConfig;

	private boolean connectionProviderInitialized = false;

	/**
	 * 构造函数
	 */
	public AbstractJedisRedisConnection() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource dataSource) {
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
	public AbstractJedisRedisConnection(JedisRedisDataSource dataSource, int connectTimeout, int soTimeout) {
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
	 *
	 * @since 2.0.0
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource dataSource, int connectTimeout, int soTimeout,
	                                    int infiniteSoTimeout) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource dataSource, SslConfiguration sslConfiguration) {
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
	public AbstractJedisRedisConnection(JedisRedisDataSource dataSource, int connectTimeout, int soTimeout,
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
	 *
	 * @since 2.0.0
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource dataSource, int connectTimeout, int soTimeout,
	                                    int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
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
	public AbstractJedisRedisConnection(DataSource dataSource, PoolConfig poolConfig) {
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
	public AbstractJedisRedisConnection(DataSource dataSource, PoolConfig poolConfig, int connectTimeout,
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
	public AbstractJedisRedisConnection(DataSource dataSource, PoolConfig poolConfig, int connectTimeout, int soTimeout,
	                                    int infiniteSoTimeout) {
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
	public AbstractJedisRedisConnection(DataSource dataSource, PoolConfig poolConfig,
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
	 * @param connectTimeout（单位：毫秒）
	 * 		连接超时
	 * @param soTimeout（单位：毫秒）
	 * 		读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public AbstractJedisRedisConnection(DataSource dataSource, PoolConfig poolConfig, int connectTimeout, int soTimeout,
	                                    SslConfiguration sslConfiguration) {
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
	 * @param infiniteSoTimeout（单位：毫秒）
	 * 		Infinite 读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 3.0.0
	 */
	public AbstractJedisRedisConnection(DataSource dataSource, PoolConfig poolConfig, int connectTimeout, int soTimeout,
	                                    int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
	}

	@Override
	public CacheConfig getCacheConfig() {
		return cacheConfig;
	}

	@Override
	public void setCacheConfig(CacheConfig cacheConfig) {
		this.cacheConfig = cacheConfig;
	}

	@Override
	public Pipeline openPipeline() {
		if(this.pipeline == null){
			final redis.clients.jedis.AbstractPipeline pipeline = client.pipelined();
			this.pipeline = new DefaultPipelineProxy<>(new JedisPipeline(pipeline), pipeline);
		}

		return this.pipeline;
	}

	@Override
	public Transaction multi() {
		if(this.transaction == null){
			final redis.clients.jedis.AbstractTransaction transaction = client.multi();
			this.transaction = new DefaultTransactionProxy<>(new JedisTransaction(transaction), transaction);
		}

		return this.transaction;
	}

	@Override
	public boolean isConnected() {
		if(client == null){
			return false;
		}

		final ConnectionProvider connectionProvider = getConnectionProvider();
		if(connectionProvider == null){
			return false;
		}

		final Connection connection = connectionProvider.getConnection();
		if(connection == null){
			return false;
		}

		return connection.isConnected();
	}

	@Override
	public boolean isClosed() {
		if(client == null){
			return true;
		}

		final ConnectionProvider connectionProvider = getConnectionProvider();
		if(connectionProvider == null){
			return true;
		}

		final Connection connection = connectionProvider.getConnection();
		if(connection == null){
			return true;
		}

		return connection.isConnected() == false;
	}

	protected ConnectionPoolConfig getConnectionPoolConfig() {
		if(getPoolConfig() == null){
			return null;
		}

		final ConnectionPoolConfig connectionPoolConfig = new ConnectionPoolConfig();

		getPoolConfig().toGenericObjectPoolConfig(connectionPoolConfig);

		return connectionPoolConfig;
	}

	@Override
	protected RedisException executeException(final Exception e) {
		return JedisRedisExceptionUtils.convert(e);
	}

	@Override
	protected void internalInit() {
	}

	protected ConnectionProvider getConnectionProvider() {
		if(connectionProvider == null && connectionProviderInitialized == false){
			connectionProviderInitialized = true;
			try{
				connectionProvider = (ConnectionProvider) FieldUtils.readField(client, "provider", true);
			}catch(IllegalAccessException e){
				//
			}
		}
		return connectionProvider;
	}

	@Override
	protected void doDestroy() throws IOException {
		if(client != null){
			client.close();
		}
		doClose();

		if(logger.isInfoEnabled()){
			logger.debug("{} destroy.", getClass().getSimpleName());
		}
	}

	@Override
	protected void doClose() throws IOException {
		if(pipeline != null){
			pipeline.close();
			pipeline = null;
		}
		if(transaction != null){
			transaction.close();
			transaction = null;
		}

		if(logger.isInfoEnabled()){
			logger.debug("{} close.", getClass().getSimpleName());
		}
	}

}
