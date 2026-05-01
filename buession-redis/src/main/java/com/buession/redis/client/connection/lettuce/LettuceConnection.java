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
package com.buession.redis.client.connection.lettuce;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.RedisStandaloneConnection;
import com.buession.redis.client.connection.datasource.lettuce.LettuceDataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.internal.lettuce.LettuceClientConfigBuilder;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.transaction.Transaction;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.RedisCredentialsProvider;
import io.lettuce.core.RedisStandaloneClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.StaticCredentialsProvider;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.builders.StandaloneClientBuilder;
import io.lettuce.core.codec.RedisCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Optional;

/**
 * Lettuce 单机模式连接器
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceConnection<K, V> extends AbstractLettuceRedisConnection<K, V, RedisStandaloneClient<K, V>>
		implements RedisStandaloneConnection {

	private final static Logger logger = LoggerFactory.getLogger(LettuceConnection.class);

	/**
	 * 构造函数
	 */
	public LettuceConnection() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public LettuceConnection(LettuceDataSource dataSource) {
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
	public LettuceConnection(LettuceDataSource dataSource, int connectTimeout, int soTimeout) {
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
	public LettuceConnection(LettuceDataSource dataSource, int connectTimeout, int soTimeout, int infiniteSoTimeout) {
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
	public LettuceConnection(LettuceDataSource dataSource, SslConfiguration sslConfiguration) {
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
	public LettuceConnection(LettuceDataSource dataSource, int connectTimeout, int soTimeout,
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
	public LettuceConnection(LettuceDataSource dataSource, int connectTimeout, int soTimeout, int infiniteSoTimeout,
	                         SslConfiguration sslConfiguration) {
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
	public LettuceConnection(LettuceDataSource dataSource, PoolConfig poolConfig) {
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
	public LettuceConnection(LettuceDataSource dataSource, PoolConfig poolConfig, int connectTimeout, int soTimeout) {
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
	public LettuceConnection(LettuceDataSource dataSource, PoolConfig poolConfig, int connectTimeout, int soTimeout,
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
	 */
	public LettuceConnection(LettuceDataSource dataSource, PoolConfig poolConfig, SslConfiguration sslConfiguration) {
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
	public LettuceConnection(LettuceDataSource dataSource, PoolConfig poolConfig, int connectTimeout, int soTimeout,
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
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public LettuceConnection(LettuceDataSource dataSource, PoolConfig poolConfig, int connectTimeout, int soTimeout,
	                         int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
	}

	/*
	@Override
	public RedisCommands<K, V> getRedisCommands() {
		return conn.sync();
	}

	@Override
	public RedisAsyncCommands<K, V> getRedisAsyncCommands() {
		return conn.async();
	}

	 */

	@Override
	public Transaction multi() {
		if(transaction == null){
			/*
			getRedisCommands().multi();
			transaction = new DefaultTransactionProxy<>(new LettuceTransaction<>(getRedisAsyncCommands()),
					getRedisAsyncCommands());

			 */
		}

		return transaction;
	}

	@Override
	protected void internalInit() {
		super.internalInit();
	}

	protected <K, V> StatefulRedisConnection<K, V> createStatefulRedisConnection(final RedisCodec<K, V> codec) {
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();
		final LettuceDataSource dataSource = (LettuceDataSource) getDataSource();
		final RedisURI redisURI = RedisURI.create(dataSource.getHost(), dataSource.getPort());
		final RedisCredentialsProvider redisCredentialsProvider = Validate.hasText(dataSource.getPassword()) ?
				new StaticCredentialsProvider(
						Validate.hasText(dataSource.getUsername()) ? dataSource.getUsername() : null,
						dataSource.getPassword().toCharArray()) : null;

		if(dataSource.getDatabase() >= 0){
			redisURI.setDatabase(dataSource.getDatabase());
		}

		propertyMapper.from(redisCredentialsProvider).to(redisURI::setCredentialsProvider);
		propertyMapper.from(dataSource.getClientName()).to(redisURI::setClientName);

		if(dataSource.getConnectTimeout() > 0){
			redisURI.setTimeout(Duration.ofMillis(dataSource.getConnectTimeout()));
		}

		redisURI.setSsl(dataSource.getSslConfiguration() != null);

		return null;//RedisClient.create(redisURI).connect(codec);
	}

	@Override
	protected Status doConnect() throws RedisConnectionFailureException {
		if(isConnected()){
			return Status.SUCCESS;
		}

		if(client == null){
			final LettuceDataSource dataSource = (LettuceDataSource) getDataSource();
			final LettuceClientConfig clientConfig = LettuceClientConfigBuilder
					.create(dataSource, getSslConfiguration()).connectTimeout(getConnectTimeout())
					.socketTimeout(getSoTimeout()).infiniteSoTimeout(getInfiniteSoTimeout())
					.database(dataSource.getDatabase()).build();

			final StandaloneClientBuilder<K, V, RedisStandaloneClient<K, V>> builder =
					RedisStandaloneClient.<K, V>builder().clientConfig(clientConfig)
							.hostAndPort(dataSource.getHost(), dataSource.getPort());

			Optional.ofNullable(getConnectionPoolConfig()).ifPresent(builder::poolConfig);
			//Optional.ofNullable(getCacheConfig()).ifPresent(builder::cacheConfig);

			client = builder.build();
		}

		return client == null ? Status.FAILURE : Status.SUCCESS;
	}

}
