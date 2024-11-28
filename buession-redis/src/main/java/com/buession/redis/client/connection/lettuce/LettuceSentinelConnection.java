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
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.net.HostAndPort;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.RedisSentinelConnection;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.lettuce.LettuceSentinelDataSource;
import com.buession.redis.core.Constants;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.RedisNamedNode;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.RedisSentinelNode;
import com.buession.redis.core.RedisServer;
import com.buession.redis.core.Role;
import com.buession.redis.core.internal.lettuce.LettuceClientConfigBuilder;
import com.buession.redis.exception.LettuceRedisExceptionUtils;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.lettuce.LettucePipeline;
import com.buession.redis.pipeline.lettuce.LettucePipelineProxy;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.LettucePoolConfig;
import io.lettuce.core.LettuceSentinelPool;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisCredentialsProvider;
import io.lettuce.core.RedisURI;
import io.lettuce.core.StaticCredentialsProvider;
import io.lettuce.core.api.PipeliningFlushPolicy;
import io.lettuce.core.api.PipeliningFlushState;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import io.lettuce.core.sentinel.api.sync.RedisSentinelCommands;
import io.lettuce.core.support.ConnectionPoolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Lettuce 哨兵模式连接器
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceSentinelConnection extends AbstractLettuceRedisConnection implements RedisSentinelConnection {

	/**
	 * 哨兵节点连接超时（单位：毫秒）
	 */
	private int sentinelConnectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

	/**
	 * 哨兵节点读取超时（单位：毫秒）
	 */
	private int sentinelSoTimeout = Constants.DEFAULT_SO_TIMEOUT;

	/**
	 * 连接池
	 */
	private LettuceSentinelPool pool;

	/**
	 * {@link StatefulRedisSentinelConnection} 实例
	 */
	private StatefulRedisSentinelConnection<byte[], byte[]> delegate;

	private final PipeliningFlushPolicy pipeliningFlushPolicy = PipeliningFlushPolicy.flushEachCommand();

	private final static Logger logger = LoggerFactory.getLogger(LettuceSentinelConnection.class);

	/**
	 * 构造函数
	 */
	public LettuceSentinelConnection() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource) {
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, int connectTimeout, int soTimeout) {
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
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, int connectTimeout, int soTimeout,
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, SslConfiguration sslConfiguration) {
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, int connectTimeout, int soTimeout,
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
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, int connectTimeout, int soTimeout,
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
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, int connectTimeout, int soTimeout,
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, int connectTimeout, int soTimeout,
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, int connectTimeout, int soTimeout,
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, int connectTimeout, int soTimeout,
									 int infiniteSoTimeout, int sentinelConnectTimeout, int sentinelSoTimeout,
									 SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		连接池配置
	 */
	public LettuceSentinelConnection(PoolConfig poolConfig) {
		super(poolConfig);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig) {
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
									 int soTimeout) {
		super(dataSource, poolConfig, connectTimeout, soTimeout);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
									 int soTimeout, int infiniteSoTimeout) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig,
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
									 int soTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, sslConfiguration);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
									 int soTimeout, int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = connectTimeout;
		this.sentinelSoTimeout = soTimeout;
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
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
									 int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout) {
		super(dataSource, poolConfig, connectTimeout, soTimeout);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
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
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
									 int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
									 int sentinelSoTimeout) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
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
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
									 int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout,
									 SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
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
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig, int connectTimeout,
									 int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
									 int sentinelSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
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

		final List<Map<byte[], byte[]>> masterNodes = getSentinelCommands(
				(LettuceSentinelDataSource) dataSource).masters();
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

		final List<Map<byte[], byte[]>> slaveNodes = getSentinelCommands(
				(LettuceSentinelDataSource) dataSource).slaves(SafeEncoder.encode(masterName));
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

		Objects.requireNonNull(getSentinelCommands((LettuceSentinelDataSource) dataSource))
				.failover(SafeEncoder.encode(namedNode.getName()));
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

		Objects.requireNonNull(getSentinelCommands((LettuceSentinelDataSource) dataSource))
				.monitor(SafeEncoder.encode(server.getName()), server.getHost(), server.getPort(), server.getQuorum());
	}

	public StatefulRedisSentinelConnection<byte[], byte[]> getStatefulRedisSentinelConnection() {
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
			final RedisSentinelCommands<byte[], byte[]> commands = delegate.sync();
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
	public void remove(RedisNamedNode master) {
		Assert.isNull(master, "Master node cloud be 'null' when trying to remove.");
		remove(master.getName());
	}

	@Override
	public void remove(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud be 'null' or empty when trying to remove.");
		delegate.async().remove(SafeEncoder.encode(masterName));
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

	private RedisSentinelCommands<byte[], byte[]> getSentinelCommands(final LettuceSentinelDataSource dataSource) {
		return delegate.sync();
	}

	protected boolean isUsePool() {
		return pool != null;
	}

	protected <K, V> StatefulRedisSentinelConnection<K, V> createStatefulRedisSentinelConnection(
			final RedisCodec<K, V> codec) {
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();
		final LettuceSentinelDataSource dataSource = (LettuceSentinelDataSource) getDataSource();
		final RedisURI.Builder redisURIBuilder = RedisURI.builder();
		final RedisCredentialsProvider redisCredentialsProvider = Validate.hasText(dataSource.getPassword()) ?
				new StaticCredentialsProvider(Validate.hasText(dataSource.getUsername()) ? dataSource.getUsername() :
						null, dataSource.getPassword().toCharArray()) : null;

		if(dataSource.getDatabase() >= 0){
			redisURIBuilder.withDatabase(dataSource.getDatabase());
		}

		propertyMapper.from(redisCredentialsProvider).to(redisURIBuilder::withAuthentication);
		propertyMapper.from(dataSource.getClientName()).to(redisURIBuilder::withClientName);

		if(dataSource.getConnectTimeout() > 0){
			redisURIBuilder.withTimeout(Duration.ofMillis(dataSource.getConnectTimeout()));
		}

		redisURIBuilder.withSsl(dataSource.getSslConfiguration() != null);

		return RedisClient.create(redisURIBuilder.build()).connectSentinel(codec);
	}

	@Override
	protected void doConnect() throws RedisConnectionFailureException {
		if(isUsePool()){
			try{
				delegate = pool.getResource();

				if(logger.isDebugEnabled()){
					logger.debug("StatefulRedisSentinelConnection initialized with pool success.");
				}
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("StatefulRedisSentinelConnection initialized with pool failure: {}", e.getMessage(),
							e);
				}

				throw LettuceRedisExceptionUtils.convert(e);
			}
		}else{
			delegate = createStatefulRedisSentinelConnection(new ByteArrayCodec());
		}
	}

	protected LettuceSentinelPool createPool() {
		final LettuceSentinelDataSource dataSource = (LettuceSentinelDataSource) getDataSource();
		final LettucePoolConfig<byte[], byte[], StatefulRedisSentinelConnection<byte[], byte[]>> lettucePoolConfig = new LettucePoolConfig<>();
		final Set<HostAndPort> sentinels = createSentinelHosts(dataSource.getSentinels());
		final LettuceClientConfig clientConfig = LettuceClientConfigBuilder.create(dataSource,
						getSslConfiguration())
				.connectTimeout(getConnectTimeout())
				.socketTimeout(getSoTimeout())
				.infiniteSoTimeout(getInfiniteSoTimeout())
				.database(dataSource.getDatabase())
				.build();
		final LettuceClientConfig sentinelClientConfig = createSentinelLettuceClientConfig(dataSource);

		getPoolConfig().toGenericObjectPoolConfig(lettucePoolConfig);

		if(getSslConfiguration() == null){
			logger.debug("Create LettuceSentinelPool.");
		}else{
			logger.debug("Create LettuceSentinelPool with ssl.");
		}

		return ConnectionPoolUtils.createLettuceSentinelPool(dataSource.getMasterName(), lettucePoolConfig,
				sentinels, clientConfig, sentinelClientConfig);
	}

	@Override
	protected void doDestroy() throws IOException {
		super.doDestroy();

		logger.debug("Lettuce destroy.");
		if(pool != null){
			if(logger.isDebugEnabled()){
				logger.debug("Lettuce sentinel pool for {} destroy.", pool.getClass().getName());
			}

			try{
				pool.destroy();
			}catch(Exception ex){
				if(logger.isWarnEnabled()){
					logger.warn("Cannot properly close Lettuce sentinel pool.", ex);
				}
			}

			pool = null;
		}
	}

	@Override
	protected void doClose() throws IOException {
		super.doClose();

		logger.debug("Lettuce close.");

		if(delegate != null){
			delegate.close();
		}
	}

	protected LettuceClientConfig createSentinelLettuceClientConfig(final LettuceSentinelDataSource dataSource) {
		return LettuceClientConfigBuilder.create(dataSource, getSslConfiguration())
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

	protected List<RedisServer> parseRedisServer(final List<Map<byte[], byte[]>> nodes, final Role role) {
		if(nodes == null){
			return null;
		}

		return nodes.stream().filter(Objects::nonNull).map((node)->{
			final Map<String, String> sNodes = new HashMap<>(node.size());
			final Properties properties = new Properties();

			node.forEach((key, value)->{
				sNodes.put(SafeEncoder.encode(key), SafeEncoder.encode(value));
			});

			properties.putAll(sNodes);

			final RedisServer redisServer = new RedisServer(sNodes.get("ip"),
					Integer.parseInt(sNodes.get("port")), properties);
			redisServer.setName(sNodes.get("name"));
			redisServer.setRole(role);

			return redisServer;
		}).collect(Collectors.toList());
	}

}
