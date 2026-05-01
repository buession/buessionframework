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

import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.AbstractRedisConnection;
import com.buession.redis.client.connection.datasource.lettuce.LettuceRedisDataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.exception.LettuceRedisExceptionUtils;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import io.lettuce.core.BaseRedisClient;
import io.lettuce.core.api.PipeliningFlushPolicy;
import io.lettuce.core.api.PipeliningFlushState;

import java.io.IOException;

/**
 * Lettuce Redis 连接对象抽象类
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractLettuceRedisConnection<K, V, C extends BaseRedisClient<K, V>>
		extends AbstractRedisConnection<C> implements LettuceRedisConnection<K, V, C> {

	private final PipeliningFlushPolicy pipeliningFlushPolicy = PipeliningFlushPolicy.flushEachCommand();

	/**
	 * 构造函数
	 */
	public AbstractLettuceRedisConnection() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource) {
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
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, int connectTimeout, int soTimeout) {
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
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, int connectTimeout, int soTimeout,
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
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, SslConfiguration sslConfiguration) {
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
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, int connectTimeout, int soTimeout,
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
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, int connectTimeout, int soTimeout,
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
	 */
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, PoolConfig poolConfig) {
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
	 */
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
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
	 */
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
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
	 */
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, PoolConfig poolConfig,
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
	 */
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
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
	 */
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
	                                      int soTimeout, int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
	}

	/*
	@SuppressWarnings({"unchecked"})
	@Override
	public RedisCommands<K, V> getRedisCommands() {
		if(redisCommands == null){
			redisCommands = (RedisCommands<K, V>) Proxy.newProxyInstance(RedisCommands.class.getClassLoader(),
					new Class[]{RedisCommands.class}, createRedisCommandsInvocationHandler());
		}

		return redisCommands;
	}

	@Override
	public RedisAsyncCommands<K, V> getRedisAsyncCommands() {
		return null;
	}

	 */

	@Override
	public Pipeline openPipeline() {
		if(pipeline == null){
			final PipeliningFlushState flushState = pipeliningFlushPolicy.newPipeline();
			//pipeline = new DefaultPipelineProxy<>(new LettucePipeline(conn, flushState), getRedisAsyncCommands());
		}

		return pipeline;
	}

	@Override
	public boolean isConnected() {
		return false;//conn != null && conn.isOpen();
	}

	@Override
	public boolean isClosed() {
		return false;// conn == null || conn.isOpen() == false;
	}

	@Override
	protected RedisException executeException(final Exception e) {
		return LettuceRedisExceptionUtils.convert(e);
	}

	@Override
	protected void doDestroy() throws IOException {
		if(pipeline != null){
			pipeline.close();
			pipeline = null;
		}
	}

	@Override
	protected void doClose() throws IOException {
		if(pipeline != null){
			pipeline.close();
			pipeline = null;
		}

		logger.debug("Lettuce close.");

		/*
		if(conn != null){
			conn.close();
		}

		 */
	}

}
