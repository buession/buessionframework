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
import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.AbstractRedisConnection;
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
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.SslOptions;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.csc.CacheConfig;
import redis.clients.jedis.providers.ConnectionProvider;

import java.io.File;
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
	 * @param poolConfig
	 * 		连接池配置
	 *
	 * @since 3.0.0
	 */
	public AbstractJedisRedisConnection(DataSource dataSource, PoolConfig poolConfig) {
		super(dataSource, poolConfig);
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

	protected void commonClientConfigBuilder(final DefaultJedisClientConfig.Builder builder) {
		if(builder == null){
			return;
		}

		builder.connectionTimeoutMillis(getConnectTimeout())
				.socketTimeoutMillis(getSoTimeout())
				.blockingSocketTimeoutMillis(getInfiniteSoTimeout());

		if(Validate.hasText(getDataSource().getPassword())){
			builder.password(getDataSource().getPassword());
			propertyMapper.from(getDataSource().getUsername()).to(builder::user);
		}

		propertyMapper.from(getDataSource().getClientName()).to(builder::clientName);
		if(getSslOptions() != null){
			builder.ssl(getSslOptions().isEnabled());

			if(getSslOptions().isEnabled()){
				builder.sslSocketFactory(getSslOptions().getSslSocketFactory())
						.sslParameters(getSslOptions().getSslParameters())
						.hostnameVerifier(getSslOptions().getHostnameVerifier());

				SslOptions.Builder sslOptionsBuilder = SslOptions.builder();

				propertyMapper.from(getSslOptions().getKeyStoreType()).to(sslOptionsBuilder::keyStoreType);

				if(Validate.hasText(getSslOptions().getKeyStore())){
					if(Validate.hasText(getSslOptions().getKeyStorePassword())){
						sslOptionsBuilder.keystore(new File(getSslOptions().getKeyStore()),
								getSslOptions().getKeyStorePassword().toCharArray());
					}else{
						sslOptionsBuilder.keystore(new File(getSslOptions().getKeyStore()));
					}
				}

				propertyMapper.from(getSslOptions().getTrustStoreType()).to(sslOptionsBuilder::trustStoreType);

				if(Validate.hasText(getSslOptions().getTrustStore())){
					if(Validate.hasText(getSslOptions().getTrustStorePassword())){
						sslOptionsBuilder.truststore(new File(getSslOptions().getTrustStore()),
								getSslOptions().getTrustStorePassword().toCharArray());
					}else{
						sslOptionsBuilder.truststore(new File(getSslOptions().getTrustStore()));
					}
				}

				propertyMapper.from(getSslOptions().getProtocol()).to(sslOptionsBuilder::sslProtocol);
				propertyMapper.from(getSslOptions().getSslParameters()).to(sslOptionsBuilder::sslParameters);

				if(getSslOptions().getSslVerifyMode() == com.buession.redis.core.SslOptions.SslVerifyMode.CA){
					sslOptionsBuilder.sslVerifyMode(redis.clients.jedis.SslVerifyMode.CA);
				}else if(getSslOptions().getSslVerifyMode() == com.buession.redis.core.SslOptions.SslVerifyMode.FULL){
					sslOptionsBuilder.sslVerifyMode(redis.clients.jedis.SslVerifyMode.FULL);
				}else if(getSslOptions().getSslVerifyMode() ==
						com.buession.redis.core.SslOptions.SslVerifyMode.INSECURE){
					sslOptionsBuilder.sslVerifyMode(redis.clients.jedis.SslVerifyMode.INSECURE);
				}

				builder.sslOptions(sslOptionsBuilder.build());
			}
		}
	}

	protected ConnectionProvider getConnectionProvider() {
		if(connectionProvider == null){
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
		doClose1();

		if(logger.isInfoEnabled()){
			logger.debug("{} destroy.", getClass().getSimpleName());
		}
	}

	@Override
	protected void doClose() throws IOException {
		doClose1();

		if(logger.isInfoEnabled()){
			logger.debug("{} close.", getClass().getSimpleName());
		}
	}

	private void doClose1() {
		if(pipeline != null){
			pipeline.close();
			pipeline = null;
		}
		if(transaction != null){
			transaction.close();
			transaction = null;
		}
	}

}
