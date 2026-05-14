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

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.AbstractRedisConnection;
import com.buession.redis.client.connection.RedisNode;
import com.buession.redis.client.connection.datasource.lettuce.LettuceRedisDataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.exception.LettuceRedisExceptionUtils;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.DefaultPipelineProxy;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.lettuce.LettucePipeline;
import com.buession.redis.transaction.DefaultTransactionProxy;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.transaction.lettuce.LettuceTransaction;
import io.lettuce.core.BaseRedisClient;
import io.lettuce.core.ConnectionPoolConfig;
import io.lettuce.core.DefaultLettuceClientConfig;
import io.lettuce.core.SslOptions;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.internal.HostAndPort;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

	private RedisCodec<K, V> codec;

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
	 * @param codec
	 * 		Redis 编解码器
	 */
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, RedisCodec<K, V> codec) {
		super(dataSource);
		this.codec = codec;
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
	public AbstractLettuceRedisConnection(LettuceRedisDataSource dataSource, PoolConfig poolConfig,
	                                      RedisCodec<K, V> codec) {
		super(dataSource, poolConfig);
		this.codec = codec;
	}

	@Override
	public RedisCodec<K, V> getCodec() {
		return codec;
	}

	@Override
	public void setCodec(RedisCodec<K, V> codec) {
		this.codec = codec;
	}

	@Override
	public Pipeline openPipeline() {
		if(pipeline == null){
			pipeline = new DefaultPipelineProxy<>(new LettucePipeline<>(client.pipelined()),
					client.getRedisAsyncCommands());
		}

		return pipeline;
	}

	@Override
	public Transaction multi() {
		if(transaction == null){
			client.multi();
			transaction = new DefaultTransactionProxy<>(new LettuceTransaction<>(client.getRedisAsyncCommands()),
					client.getRedisAsyncCommands());
		}

		return transaction;
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
	protected void internalInit() {
		Assert.isNull(getCodec(), "Key and value codec cloud not be null.");
	}

	protected static <N extends RedisNode> Set<HostAndPort> createNodes(final Set<N> nodes, final int defaultPort) {
		return nodes.stream().filter(Objects::nonNull).map((node)->{
			int port = node.getPort() == 0 ? defaultPort : node.getPort();
			return HostAndPort.of(node.getHost(), port);
		}).collect(Collectors.toSet());
	}

	protected void commonClientConfigBuilder(final DefaultLettuceClientConfig.Builder builder) {
		if(builder == null){
			return;
		}

		LettuceRedisDataSource dataSource = (LettuceRedisDataSource) getDataSource();

		builder.connectionTimeoutMillis(getConnectTimeout()).socketTimeoutMillis(getSoTimeout());
		builder.autoReconnect(isAutoReconnect());
		if(getReconnectDelay() > 0){
			builder.reconnectDelayMillis(getReconnectDelay());
		}

		if(Validate.hasText(dataSource.getPassword())){
			builder.password(dataSource.getPassword());
			if(Validate.hasText(dataSource.getUsername())){
				builder.user(dataSource.getUsername());
			}
		}

		propertyMapper.from(dataSource.getClientName()).to(builder::clientName);

		if(dataSource.getComputationThreadPoolSize() > 0){
			builder.computationThreadPoolSize(dataSource.getComputationThreadPoolSize());
		}

		if(dataSource.getIoThreadPoolSize() > 0){
			builder.ioThreadPoolSize(dataSource.getIoThreadPoolSize());
		}

		if(dataSource.getRequestQueueSize() > 0){
			builder.requestQueueSize(dataSource.getRequestQueueSize());
		}

		if(getSslOptions() != null){
			builder.ssl(getSslOptions().isEnabled());

			if(getSslOptions().isEnabled()){
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

				if(Validate.hasText(getSslOptions().getTrustStore())){
					if(Validate.hasText(getSslOptions().getTrustStorePassword())){
						sslOptionsBuilder.truststore(new File(getSslOptions().getTrustStore()),
								getSslOptions().getTrustStorePassword());
					}else{
						sslOptionsBuilder.truststore(new File(getSslOptions().getTrustStore()));
					}
				}

				if(Validate.hasText(getSslOptions().getProtocol())){
					sslOptionsBuilder.protocols(getSslOptions().getProtocol());
				}
				if(getSslOptions().getSslParameters() != null){
					sslOptionsBuilder.sslParameters(()->getSslOptions().getSslParameters());
				}

				propertyMapper.from(getSslOptions().getCipherSuites()).to(sslOptionsBuilder::cipherSuites);

				if(getSslOptions().getHandshakeTimeout() > 0){
					sslOptionsBuilder.handshakeTimeout(Duration.ofMillis(getSslOptions().getHandshakeTimeout()));
				}

				builder.sslOptions(sslOptionsBuilder.build());
			}
		}
	}

	protected ConnectionPoolConfig<K, V> getConnectionPoolConfig() {
		if(getPoolConfig() == null){
			return null;
		}

		final ConnectionPoolConfig<K, V> connectionPoolConfig = new ConnectionPoolConfig<>();

		getPoolConfig().toGenericObjectPoolConfig(connectionPoolConfig);

		return connectionPoolConfig;
	}

	@Override
	protected RedisException executeException(final Exception e) {
		return LettuceRedisExceptionUtils.convert(e);
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
