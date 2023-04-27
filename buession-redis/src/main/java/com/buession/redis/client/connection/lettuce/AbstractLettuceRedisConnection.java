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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.lettuce;

import com.buession.core.validator.Validate;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.AbstractRedisConnection;
import com.buession.redis.client.connection.datasource.jedis.JedisRedisDataSource;
import com.buession.redis.client.connection.datasource.lettuce.LettuceRedisDataSource;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.transaction.Transaction;
import redis.clients.jedis.DefaultJedisClientConfig;

import java.io.IOException;

/**
 * Jedis Redis 连接对象抽象类
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public abstract class AbstractLettuceRedisConnection extends AbstractRedisConnection implements LettuceRedisConnection {

	/**
	 * 事务
	 */
	protected Transaction transaction;

	/**
	 * 管道
	 */
	protected volatile Pipeline pipeline;

	/**
	 * 构造函数
	 */
	public AbstractLettuceRedisConnection(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource){
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
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, int connectTimeout, int soTimeout){
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
										  int infiniteSoTimeout){
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
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, SslConfiguration sslConfiguration){
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
										  SslConfiguration sslConfiguration){
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
										  int infiniteSoTimeout, SslConfiguration sslConfiguration){
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
	}

	@Override
	public boolean isPipeline(){
		return pipeline != null;
	}

	@Override
	public boolean isTransaction(){
		return transaction != null;
	}

	protected DefaultJedisClientConfig.Builder createJedisClientConfigBuilder(final JedisRedisDataSource dataSource,
																			  final int connectTimeout,
																			  final int soTimeout,
																			  final int infiniteSoTimeout){
		final DefaultJedisClientConfig.Builder builder = DefaultJedisClientConfig.builder()
				.connectionTimeoutMillis(connectTimeout)
				.socketTimeoutMillis(soTimeout)
				.blockingSocketTimeoutMillis(infiniteSoTimeout)
				.ssl(isUseSsl());

		if(Validate.hasText(dataSource.getClientName())){
			builder.clientName(dataSource.getClientName());
		}

		if(getSslConfiguration() != null){
			builder.sslSocketFactory(getSslConfiguration().getSslSocketFactory())
					.sslParameters(getSslConfiguration().getSslParameters())
					.hostnameVerifier(getSslConfiguration().getHostnameVerifier());
		}

		return builder;
	}

	@Override
	protected void doDestroy() throws IOException{
		if(pipeline != null){
			pipeline.close();
			pipeline = null;
		}
	}

	@Override
	protected void doClose() throws IOException{
		if(pipeline != null){
			pipeline.close();
			pipeline = null;
		}
	}

}
