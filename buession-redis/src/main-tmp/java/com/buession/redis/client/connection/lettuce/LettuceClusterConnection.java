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
import com.buession.net.HostAndPort;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.client.connection.datasource.lettuce.LettuceClusterDataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.internal.lettuce.LettuceClientConfigBuilder;
import com.buession.redis.exception.LettuceRedisExceptionUtils;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.lettuce.LettucePipeline;
import com.buession.redis.pipeline.lettuce.LettucePipelineProxy;
import com.buession.redis.transaction.Transaction;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.LettuceClusterPool;
import io.lettuce.core.LettucePoolConfig;
import io.lettuce.core.RedisCredentialsProvider;
import io.lettuce.core.RedisURI;
import io.lettuce.core.StaticCredentialsProvider;
import io.lettuce.core.api.PipeliningFlushPolicy;
import io.lettuce.core.api.PipeliningFlushState;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.support.ConnectionPoolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Lettuce 集群模式连接器
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceClusterConnection extends AbstractLettuceRedisConnection implements RedisClusterConnection {

	/**
	 * 最大重定向次数
	 */
	private int maxRedirects;

	/**
	 * 最大重数时长（单位：毫秒）
	 */
	private int maxTotalRetriesDuration;

	/**
	 * 连接池
	 */
	private LettuceClusterPool pool;

	/**
	 * {@link StatefulRedisClusterConnection} 实例
	 */
	private StatefulRedisClusterConnection<byte[], byte[]> delegate;

	private final PipeliningFlushPolicy pipeliningFlushPolicy = PipeliningFlushPolicy.flushEachCommand();

	private final static Logger logger = LoggerFactory.getLogger(LettuceClusterConnection.class);

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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, int connectTimeout, int soTimeout) {
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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, int connectTimeout, int soTimeout,
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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, SslConfiguration sslConfiguration) {
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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, int connectTimeout, int soTimeout,
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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, int connectTimeout, int soTimeout,
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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig) {
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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig,
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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
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
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
									int soTimeout, int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
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
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 */
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, int connectTimeout, int soTimeout,
									int maxRedirects, int maxTotalRetriesDuration) {
		super(dataSource, connectTimeout, soTimeout);
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
	 * 		Infinite 读取超时
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 */
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, int connectTimeout, int soTimeout,
									int infiniteSoTimeout, int maxRedirects, int maxTotalRetriesDuration) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout);
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
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, int connectTimeout, int soTimeout,
									int maxRedirects, int maxTotalRetriesDuration, SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
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
	 * 		Infinite 读取超时
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, int connectTimeout, int soTimeout,
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
	 */
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
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
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 */
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
									int soTimeout, int infiniteSoTimeout, int maxRedirects,
									int maxTotalRetriesDuration) {
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
	 * @param maxRedirects
	 * 		最大重定向次数
	 * @param maxTotalRetriesDuration
	 * 		最大重试时长（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
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
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public LettuceClusterConnection(LettuceClusterDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
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

	public StatefulRedisClusterConnection<byte[], byte[]> getStatefulRedisClusterConnection() {
		return delegate;
	}

	@Override
	public Pipeline openPipeline() {
		if(pipeline == null){
			final PipeliningFlushState flushState = pipeliningFlushPolicy.newPipeline();
			final LettucePipelineProxy<PipeliningFlushState> lettucePipelineProxy =
					new LettucePipelineProxy<>(flushState);

			lettucePipelineProxy.setTarget(
					new LettucePipeline(delegate, flushState, lettucePipelineProxy.getTxResults()));
			pipeline = lettucePipelineProxy;
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
			//final RedisSentinelCommands<byte[], byte[]> commands = delegate.sync();
			//transaction = new LettuceTransactionProxy(new LettuceTransaction(commands), commands);
		}

		return transaction;
	}

	@Override
	public List<Object> exec() throws RedisException {
		if(pipeline != null){
			final List<Object> result = pipeline.syncAndReturnAll();

			pipeline.close();
			pipeline = null;

			return result;
		}else if(transaction != null){
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
		return delegate != null && delegate.isOpen();
	}

	@Override
	public boolean isClosed() {
		return delegate == null || delegate.isOpen() == false;
	}

	@Override
	protected void internalInit() {
		if(pool == null && getPoolConfig() != null){
			pool = createPool();
		}
	}

	protected boolean isUsePool() {
		return pool != null;
	}

	protected <K, V> StatefulRedisClusterConnection<K, V> createStatefulRedisClusterConnection(
			final RedisCodec<K, V> codec) {
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();
		final LettuceClusterDataSource dataSource = (LettuceClusterDataSource) getDataSource();
		final RedisCredentialsProvider redisCredentialsProvider = Validate.hasText(dataSource.getPassword()) ?
				new StaticCredentialsProvider(Validate.hasText(dataSource.getUsername()) ? dataSource.getUsername() :
						null, dataSource.getPassword().toCharArray()) : null;
		final Set<RedisURI> redisURIs = dataSource.getNodes().stream().map((node)->{
			int port = node.getPort() == 0 ? RedisNode.DEFAULT_PORT : node.getPort();
			final RedisURI redisURI = RedisURI.create(node.getHost(), port);

			propertyMapper.from(redisCredentialsProvider).to(redisURI::setCredentialsProvider);
			propertyMapper.from(dataSource.getClientName()).to(redisURI::setClientName);

			if(dataSource.getConnectTimeout() > 0){
				redisURI.setTimeout(Duration.ofMillis(dataSource.getConnectTimeout()));
			}

			redisURI.setSsl(dataSource.getSslConfiguration() != null);

			return redisURI;
		}).collect(Collectors.toSet());

		return RedisClusterClient.create(redisURIs).connect(codec);
	}

	@Override
	protected void doConnect() throws RedisConnectionFailureException {
		if(isUsePool()){
			try{
				delegate = pool.getResource();

				if(logger.isInfoEnabled()){
					logger.info("StatefulRedisClusterConnection initialized with pool success.");
				}
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("StatefulRedisClusterConnection initialized with pool failure: {}", e.getMessage(),
							e);
				}

				throw LettuceRedisExceptionUtils.convert(e);
			}
		}else{
			delegate = createStatefulRedisClusterConnection(new ByteArrayCodec());
		}
	}

	protected LettuceClusterPool createPool() {
		final LettuceClusterDataSource dataSource = (LettuceClusterDataSource) getDataSource();
		final LettucePoolConfig<byte[], byte[], StatefulRedisClusterConnection<byte[], byte[]>> lettucePoolConfig = new LettucePoolConfig<>();
		final LettuceClientConfig clientConfig = LettuceClientConfigBuilder.create(dataSource,
						getSslConfiguration())
				.connectTimeout(getConnectTimeout())
				.socketTimeout(getSoTimeout())
				.infiniteSoTimeout(getInfiniteSoTimeout())
				.build();
		final Set<HostAndPort> nodes = createHostAndPorts(dataSource);

		getPoolConfig().toGenericObjectPoolConfig(lettucePoolConfig);

		if(getSslConfiguration() == null){
			logger.debug("Create LettuceClusterPool.");
		}else{
			logger.debug("Create LettuceClusterPool with ssl.");
		}

		return ConnectionPoolUtils.createLettuceClusterPool(lettucePoolConfig, nodes, clientConfig);
	}

	@Override
	protected void doDestroy() throws IOException {
		super.doDestroy();

		logger.info("Lettuce destroy.");
		if(pool != null){
			if(logger.isInfoEnabled()){
				logger.info("Lettuce cluster pool for {} destroy.", pool.getClass().getName());
			}

			try{
				pool.destroy();
			}catch(Exception ex){
				logger.warn("Cannot properly close Lettuce cluster pool.", ex);
			}

			pool = null;
		}
	}

	@Override
	protected void doClose() throws IOException {
		super.doClose();

		logger.info("Lettuce close.");

		if(delegate != null){
			delegate.close();
		}
	}

	private static Set<HostAndPort> createHostAndPorts(final LettuceClusterDataSource clusterDataSource) {
		return clusterDataSource.getNodes().stream().map((node)->{
			int port = node.getPort() == 0 ? RedisNode.DEFAULT_PORT : node.getPort();
			return new HostAndPort(node.getHost(), port);
		}).collect(Collectors.toSet());
	}

}
