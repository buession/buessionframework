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
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.RedisSentinelConnection;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.lettuce.LettuceSentinelDataSource;
import com.buession.redis.core.Constants;
import com.buession.redis.core.RedisNamedNode;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.RedisSentinelNode;
import com.buession.redis.core.RedisServer;
import com.buession.redis.core.Role;
import com.buession.redis.exception.LettuceRedisExceptionUtils;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.LettuceSentinelPool;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

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
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param pool
	 * 		连接池
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, LettuceSentinelPool pool) {
		super(dataSource);
		this.pool = pool;
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, LettuceSentinelPool pool, int connectTimeout,
									 int soTimeout) {
		super(dataSource, connectTimeout, soTimeout);
		this.pool = pool;
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, LettuceSentinelPool pool, int connectTimeout,
									 int soTimeout, int infiniteSoTimeout) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout);
		this.pool = pool;
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, LettuceSentinelPool pool,
									 SslConfiguration sslConfiguration) {
		super(dataSource, sslConfiguration);
		this.pool = pool;
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, LettuceSentinelPool pool, int connectTimeout,
									 int soTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
		this.pool = pool;
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, LettuceSentinelPool pool, int connectTimeout,
									 int soTimeout, int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.pool = pool;
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
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, LettuceSentinelPool pool, int connectTimeout,
									 int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout) {
		this(dataSource, pool, connectTimeout, soTimeout);
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
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, LettuceSentinelPool pool, int connectTimeout,
									 int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
									 int sentinelSoTimeout) {
		this(dataSource, pool, connectTimeout, soTimeout, infiniteSoTimeout);
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
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, LettuceSentinelPool pool, int connectTimeout,
									 int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout,
									 SslConfiguration sslConfiguration) {
		this(dataSource, pool, connectTimeout, soTimeout, sslConfiguration);
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
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, LettuceSentinelPool pool, int connectTimeout,
									 int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
									 int sentinelSoTimeout, SslConfiguration sslConfiguration) {
		this(dataSource, pool, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
	}

	/**
	 * 返回哨兵节点连接超时
	 *
	 * @return 哨兵节点连接超时（单位：毫秒）
	 */
	public int getSentinelConnectTimeout() {
		return sentinelConnectTimeout;
	}

	/**
	 * 设置哨兵节点连接超时
	 *
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 */
	public void setSentinelConnectTimeout(int sentinelConnectTimeout) {
		this.sentinelConnectTimeout = sentinelConnectTimeout;
	}

	/**
	 * 返回哨兵节点读取超时
	 *
	 * @return 哨兵节点读取超时（单位：毫秒）
	 */
	public int getSentinelSoTimeout() {
		return sentinelSoTimeout;
	}

	/**
	 * 设置哨兵节点读取超时
	 *
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 */
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
				(LettuceSentinelDataSource) dataSource)).sentinelMasters();
		if(masterNodes == null){
			return null;
		}

		final List<RedisServer> result = new ArrayList<>(masterNodes.size());
		RedisServer redisServer;
		Properties properties;

		for(Map<String, String> masterNode : masterNodes){
			properties = new Properties();
			properties.putAll(masterNode);

			redisServer = new RedisServer(masterNode.get("ip"), Integer.parseInt(masterNode.get("port")),
					properties);
			redisServer.setName(masterNode.get("name"));
			redisServer.setRole(Role.MASTER);

			result.add(redisServer);
		}

		return result;
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
				(LettuceSentinelDataSource) dataSource)).sentinelSlaves(masterName);
		if(slaveNodes == null){
			return null;
		}

		final List<RedisServer> result = new ArrayList<>(slaveNodes.size());
		RedisServer redisServer;
		Properties properties;

		for(Map<String, String> slaveNode : slaveNodes){
			properties = new Properties();
			properties.putAll(slaveNode);

			redisServer = new RedisServer(slaveNode.get("ip"), Integer.parseInt(slaveNode.get("port")),
					properties);
			redisServer.setName(slaveNode.get("name"));
			redisServer.setRole(Role.SLAVE);

			result.add(redisServer);
		}

		return result;
	}

	@Override
	public void failover(RedisNamedNode namedNode) {
		Assert.isNull(namedNode, "Redis master node cloud not be 'null' for failover.");
		Assert.isBlank(namedNode.getName(), "Redis master name cloud not be 'null' or empty for failover.");

		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return;
		}

		Objects.requireNonNull(createSentinelJedis((LettuceSentinelDataSource) dataSource))
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

		Objects.requireNonNull(createSentinelJedis((LettuceSentinelDataSource) dataSource))
				.sentinelMonitor(server.getName(), server.getHost(), server.getPort(), server.getQuorum());
	}

	public StatefulRedisSentinelConnection<byte[], byte[]> getStatefulRedisSentinelConnection() {
		return delegate;
	}

	@Override
	public Pipeline openPipeline() {
		if(pipeline == null){
			//pipeline = new JedisPipeline(jedis.pipelined());
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
			//transaction = new JedisTransaction(jedis.multi());
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
	}

	private Jedis createSentinelJedis(final LettuceSentinelDataSource dataSource) {
		/*
		final Set<HostAndPort> sentinels = convertToJedisSentinelSet(dataSource.getSentinels());
		final JedisClientConfigBuilder builder = JedisClientConfigBuilder.create(dataSource, getSslConfiguration());
		final JedisClientConfig clientConfig = builder.build();

		for(HostAndPort sentinel : sentinels){
			try(Jedis jedis = new Jedis(sentinel, clientConfig)){
				return jedis;
			}catch(JedisException e){
				logger.warn("Cannot get master address from sentinel running @ {}. Reason: {}. Trying next one.",
						sentinel, e);
			}
		}

		 */

		return null;
	}

	protected boolean isUsePool() {
		return pool != null;
	}

	private Set<HostAndPort> convertToJedisSentinelSet(Collection<RedisNode> sentinelNodes) {
		if(Validate.isEmpty(sentinelNodes)){
			return Collections.emptySet();
		}

		Set<HostAndPort> convertedNodes = new LinkedHashSet<>(sentinelNodes.size());

		for(RedisNode node : sentinelNodes){
			if(node != null){
				int port = node.getPort() == 0 ? RedisNode.DEFAULT_SENTINEL_PORT : node.getPort();
				convertedNodes.add(new HostAndPort(node.getHost(), port));
			}
		}

		return convertedNodes;
	}

	protected <K, V> StatefulRedisSentinelConnection<K, V> createStatefulRedisSentinelConnection(
			final RedisCodec<K, V> codec) {
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();
		final LettuceSentinelDataSource dataSource = (LettuceSentinelDataSource) getDataSource();
		final RedisURI redisURI = null;

		if(dataSource.getDatabase() >= 0){
			redisURI.setDatabase(dataSource.getDatabase());
		}

		propertyMapper.from(dataSource.getPassword()).to(redisURI::setPassword);
		propertyMapper.from(dataSource.getClientName()).to(redisURI::setClientName);

		if(dataSource.getConnectTimeout() > 0){
			redisURI.setTimeout(Duration.ofMillis(dataSource.getConnectTimeout()));
		}

		redisURI.setSsl(dataSource.getSslConfiguration() != null);

		return RedisClient.create(redisURI).connectSentinel(codec);
	}

	@Override
	protected void doConnect() throws RedisConnectionFailureException {
		if(isUsePool()){
			try{
				delegate = pool.getResource();

				if(logger.isInfoEnabled()){
					logger.info("StatefulRedisSentinelConnection initialized with pool success.");
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

	@Override
	protected void doDestroy() throws IOException {
		super.doDestroy();

		logger.info("Lettuce destroy.");
		if(pool != null){
			if(logger.isInfoEnabled()){
				logger.info("Lettuce sentinel pool for {} destroy.", pool.getClass().getName());
			}

			try{
				pool.destroy();
			}catch(Exception ex){
				logger.warn("Cannot properly close Lettuce sentinel pool.", ex);
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

}
