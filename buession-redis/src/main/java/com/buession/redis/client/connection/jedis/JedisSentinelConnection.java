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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.jedis;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.RedisSentinelConnection;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisSentinelDataSource;
import com.buession.redis.core.Constants;
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
import com.buession.redis.transaction.Transaction;
import com.buession.redis.transaction.jedis.JedisTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisException;

import java.io.IOException;
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
	 * 连接池
	 */
	private JedisSentinelPool pool;

	/**
	 * Jedis 对象
	 */
	private Jedis jedis;

	private final static Logger logger = LoggerFactory.getLogger(JedisSentinelConnection.class);

	/**
	 * 构造函数
	 */
	public JedisSentinelConnection(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, SslConfiguration sslConfiguration){
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
	 *
	 * @since 2.0.0
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
								   int infiniteSoTimeout, SslConfiguration sslConfiguration){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool,
								   SslConfiguration sslConfiguration){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, SslConfiguration sslConfiguration){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, SslConfiguration sslConfiguration){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
								   int sentinelConnectTimeout, int sentinelSoTimeout){
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
								   int infiniteSoTimeout, int sentinelConnectTimeout, int sentinelSoTimeout){
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
								   SslConfiguration sslConfiguration){
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
								   SslConfiguration sslConfiguration){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
								   int sentinelSoTimeout){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout,
								   SslConfiguration sslConfiguration){
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
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisSentinelPool pool, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
								   int sentinelSoTimeout, SslConfiguration sslConfiguration){
		this(dataSource, pool, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
	}

	/**
	 * 返回哨兵节点连接超时
	 *
	 * @return 哨兵节点连接超时（单位：毫秒）
	 */
	public int getSentinelConnectTimeout(){
		return sentinelConnectTimeout;
	}

	/**
	 * 设置哨兵节点连接超时
	 *
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 */
	public void setSentinelConnectTimeout(int sentinelConnectTimeout){
		this.sentinelConnectTimeout = sentinelConnectTimeout;
	}

	/**
	 * 返回哨兵节点读取超时
	 *
	 * @return 哨兵节点读取超时（单位：毫秒）
	 */
	public int getSentinelSoTimeout(){
		return sentinelSoTimeout;
	}

	/**
	 * 设置哨兵节点读取超时
	 *
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 */
	public void setSentinelSoTimeout(int sentinelSoTimeout){
		this.sentinelSoTimeout = sentinelSoTimeout;
	}

	@Override
	public List<RedisServer> masters(){
		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return null;
		}

		final List<Map<String, String>> masterNodes = Objects.requireNonNull(createSentinelJedis(
				(JedisSentinelDataSource) dataSource)).sentinelMasters();
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
	public List<RedisServer> slaves(RedisNamedNode master){
		Assert.isNull(master, "Redis master node cloud not be 'null' for loading slaves.");
		return slaves(master.getName());
	}

	@Override
	public List<RedisServer> slaves(String masterName){
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading slaves.");

		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return null;
		}

		final List<Map<String, String>> slaveNodes = Objects.requireNonNull(createSentinelJedis(
				(JedisSentinelDataSource) dataSource)).sentinelSlaves(masterName);
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
	public void failover(RedisNamedNode namedNode){
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
	public void monitor(RedisSentinelNode server){
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

	public Jedis getJedis(){
		return jedis;
	}

	@Override
	public Pipeline openPipeline(){
		if(pipeline == null){
			pipeline = new JedisPipeline(jedis.pipelined());
		}

		return pipeline;
	}

	@Override
	public void closePipeline(){
		pipeline.close();
		pipeline = null;
	}

	@Override
	public Transaction multi(){
		if(transaction == null){
			transaction = new JedisTransaction(jedis.multi());
		}

		return transaction;
	}

	@Override
	public List<Object> exec() throws RedisException{
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
	public void discard() throws RedisException{
		if(transaction != null){
			transaction.discard();
			transaction = null;
		}else{
			throw new RedisException("ERR DISCARD without MULTI. Did you forget to call multi?");
		}
	}

	@Override
	public void remove(RedisNamedNode master){
		Assert.isNull(master, "Master node cloud be 'null' when trying to remove.");
		remove(master.getName());
	}

	@Override
	public void remove(String masterName){
		Assert.isBlank(masterName, "Redis master name cloud be 'null' or empty when trying to remove.");
		jedis.sentinelRemove(masterName);
	}

	@Override
	public boolean isConnect(){
		return jedis != null && jedis.isConnected();
	}

	@Override
	public boolean isClosed(){
		return jedis == null || jedis.isConnected() == false;
	}

	@Override
	protected void internalInit(){
	}

	private Jedis createSentinelJedis(final JedisSentinelDataSource dataSource){
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

		return null;
	}

	protected boolean isUsePool(){
		return pool != null;
	}

	private Set<HostAndPort> convertToJedisSentinelSet(Collection<RedisNode> sentinelNodes){
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

	protected Jedis createJedis(final JedisSentinelDataSource dataSource){
		final DefaultJedisClientConfig clientConfig = createJedisClientConfigBuilder(dataSource,
				getSentinelConnectTimeout(), getSentinelSoTimeout(), getInfiniteSoTimeout()).build();
		boolean sentinelAvailable = false;

		for(RedisNode node : dataSource.getSentinels()){
			int port = node.getPort() == 0 ? RedisNode.DEFAULT_SENTINEL_PORT : node.getPort();
			try(Jedis jedis = new Jedis(new HostAndPort(node.getHost(), port), clientConfig)){
				List<String> masterAddr = jedis.sentinelGetMasterAddrByName(dataSource.getMasterName());
				sentinelAvailable = true;

				if(masterAddr == null || masterAddr.size() != 2){
					logger.warn("Can not get master addr, master name: {}. Sentinel: {}", dataSource.getMasterName(),
							node);
					continue;
				}

				final JedisClientConfigBuilder builder = JedisClientConfigBuilder.create(dataSource,
						getSslConfiguration());

				builder.database(dataSource.getDatabase());

				return new Jedis(new HostAndPort(masterAddr.get(0), Integer.parseInt(masterAddr.get(1))),
						builder.build());
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

	@Override
	protected void doConnect() throws RedisConnectionFailureException{
		if(isUsePool()){
			try{
				jedis = pool.getResource();

				if(logger.isInfoEnabled()){
					logger.info("Jedis initialized with pool success.");
				}
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("Jedis initialized with pool failure: {}", e.getMessage(), e);
				}

				throw JedisRedisExceptionUtils.convert(e);
			}
		}else{
			final JedisSentinelDataSource dataSource = (JedisSentinelDataSource) getDataSource();
			jedis = createJedis(dataSource);
		}
	}

	@Override
	protected void doDestroy() throws IOException{
		super.doDestroy();

		logger.info("Jedis destroy.");
		if(pool != null){
			logger.info("Jedis sentinel pool for {} destroy.", pool.getClass().getName());

			try{
				pool.destroy();
			}catch(Exception ex){
				logger.warn("Cannot properly close Jedis sentinel pool.", ex);
			}

			pool = null;
		}
	}

	@Override
	protected void doClose() throws IOException{
		super.doClose();

		logger.info("Jedis close.");
		if(isUsePool()){
			logger.info("Jedis close.");

			if(jedis != null){
				jedis.close();
			}
		}else{
			logger.info("Jedis quit.");

			if(jedis != null){
				Exception ex = null;

				try{
					jedis.quit();
				}catch(Exception e){
					ex = e;
				}

				try{
					jedis.disconnect();
				}catch(Exception e){
					ex = e;
				}

				if(ex != null){
					throw new RedisException(ex);
				}
			}
		}
	}

}
