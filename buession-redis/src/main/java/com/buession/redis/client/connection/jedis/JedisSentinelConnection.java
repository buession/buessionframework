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
import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisSentinelDataSource;
import com.buession.redis.core.Constants;
import com.buession.redis.core.RedisNamedNode;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.RedisSentinelNode;
import com.buession.redis.core.RedisServer;
import com.buession.redis.exception.RedisExceptionUtils;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Jedis 哨兵模式连接器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisSentinelConnection extends AbstractJedisRedisConnection implements RedisSentinelConnection {

	/**
	 * Infinite 读取超时
	 *
	 * @since 2.0.0
	 */
	private int infiniteSoTimeout;

	/**
	 * Sentinel 连接超时（单位：秒）
	 */
	private int sentinelConnectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

	/**
	 * Sentinel 读取超时（单位：秒）
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
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
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
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param sentinelConnectTimeout
	 * 		Sentinel 连接超时
	 * @param sentinelSoTimeout
	 * 		Sentinel 读取超时
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
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
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
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param sentinelConnectTimeout
	 * 		Sentinel 连接超时
	 * @param sentinelSoTimeout
	 * 		Sentinel 读取超时
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
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 2.0.0
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
								   int infiniteSoTimeout, SslConfiguration sslConfiguration){
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
		this.infiniteSoTimeout = infiniteSoTimeout;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时
	 * @param sentinelConnectTimeout
	 * 		Sentinel 连接超时
	 * @param sentinelSoTimeout
	 * 		Sentinel 读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 2.0.0
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, int connectTimeout, int soTimeout,
								   int infiniteSoTimeout, int sentinelConnectTimeout, int sentinelSoTimeout,
								   SslConfiguration sslConfiguration){
		this(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
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
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisPoolConfig poolConfig){
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
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout,
								   int soTimeout){
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
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param sentinelConnectTimeout
	 * 		Sentinel 连接超时
	 * @param sentinelSoTimeout
	 * 		Sentinel 读取超时
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout){
		this(dataSource, poolConfig, connectTimeout, soTimeout);
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
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout){
		this(dataSource, poolConfig, connectTimeout, soTimeout);
		this.infiniteSoTimeout = infiniteSoTimeout;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时
	 * @param sentinelConnectTimeout
	 * 		Sentinel 连接超时
	 * @param sentinelSoTimeout
	 * 		Sentinel 读取超时
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
								   int sentinelSoTimeout){
		this(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout);
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
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisPoolConfig poolConfig,
								   SslConfiguration sslConfiguration){
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
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout,
								   int soTimeout, SslConfiguration sslConfiguration){
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
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param sentinelConnectTimeout
	 * 		Sentinel 连接超时
	 * @param sentinelSoTimeout
	 * 		Sentinel 读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int sentinelConnectTimeout, int sentinelSoTimeout,
								   SslConfiguration sslConfiguration){
		this(dataSource, poolConfig, connectTimeout, soTimeout, sslConfiguration);
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
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, SslConfiguration sslConfiguration){
		this(dataSource, poolConfig, connectTimeout, soTimeout, sslConfiguration);
		this.infiniteSoTimeout = infiniteSoTimeout;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时
	 * @param sentinelConnectTimeout
	 * 		Sentinel 连接超时
	 * @param sentinelSoTimeout
	 * 		Sentinel 读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout,
								   int soTimeout, int infiniteSoTimeout, int sentinelConnectTimeout,
								   int sentinelSoTimeout, SslConfiguration sslConfiguration){
		this(dataSource, poolConfig, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.sentinelConnectTimeout = sentinelConnectTimeout;
		this.sentinelSoTimeout = sentinelSoTimeout;
	}

	/**
	 * 返回 Infinite 读取超时
	 *
	 * @return Infinite 读取超时
	 */
	public int getInfiniteSoTimeout(){
		return infiniteSoTimeout;
	}

	/**
	 * 设置 Infinite 读取超时
	 *
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时
	 */
	public void setInfiniteSoTimeout(int infiniteSoTimeout){
		this.infiniteSoTimeout = infiniteSoTimeout;
	}

	/**
	 * 返回 Sentinel 连接超时
	 *
	 * @return Sentinel 连接超时
	 */
	public int getSentinelConnectTimeout(){
		return sentinelConnectTimeout;
	}

	/**
	 * 设置 Sentinel 连接超时
	 *
	 * @param sentinelConnectTimeout
	 * 		Sentinel 连接超时
	 */
	public void setSentinelConnectTimeout(int sentinelConnectTimeout){
		this.sentinelConnectTimeout = sentinelConnectTimeout;
	}

	/**
	 * 返回 Sentinel 读取超时
	 *
	 * @return Sentinel 读取超时
	 */
	public int getSentinelSoTimeout(){
		return sentinelSoTimeout;
	}

	/**
	 * 设置 Sentinel 读取超时
	 *
	 * @param sentinelSoTimeout
	 * 		Sentinel 读取超时
	 */
	public void setSentinelSoTimeout(int sentinelSoTimeout){
		this.sentinelSoTimeout = sentinelSoTimeout;
	}

	@Override
	public List<RedisServer> masters(){
		jedis.sentinelMasters();
		return null;
	}

	@Override
	public List<RedisServer> slaves(RedisNamedNode master){
		Assert.isNull(master, "Redis master node cloud not be 'null' for loading slaves.");
		return slaves(master.getName());
	}

	@Override
	public List<RedisServer> slaves(String masterName){
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading slaves.");
		jedis.sentinelSlaves(masterName);
		return null;
	}

	@Override
	public void failover(RedisNamedNode namedNode){
		Assert.isNull(namedNode, "Redis master node cloud not be 'null' for failover.");
		Assert.isBlank(namedNode.getName(), "Redis master name cloud not be 'null' or empty for failover.");

		jedis.sentinelFailover(namedNode.getName());
	}

	@Override
	public void monitor(RedisSentinelNode server){
		Assert.isNull(server, "Cannot monitor 'null' server.");
		Assert.isBlank(server.getName(), "Name of server to monitor must not be 'null' or empty.");
		Assert.isBlank(server.getHost(), "Host must not be 'null' for server to monitor.");

		jedis.sentinelMonitor(server.getName(), server.getHost(), server.getPort(), server.getQuorum());
	}

	@Override
	public boolean isTransaction(){
		return transaction != null;
	}

	public Pipeline pipeline(){
		if(pipeline == null){
			pipeline = new JedisPipeline(jedis.pipelined());
		}

		return pipeline;
	}

	@Override
	public void multi(){
		transaction = jedis.multi();
	}

	@Override
	public List<Object> exec(){
		return transaction != null ? transaction.exec() : null;
	}

	@Override
	public void discard(){
		if(transaction != null){
			transaction.discard();
			transaction = null;
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
		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return;
		}

		JedisSentinelDataSource jedisSentinelDataSource = (JedisSentinelDataSource) dataSource;
		if(isUsePool()){
			pool = createPool(jedisSentinelDataSource);
		}else{
			jedis = createJedis(jedisSentinelDataSource);
		}
	}

	protected JedisSentinelPool createPool(final JedisSentinelDataSource dataSource){
		final SslConfiguration sslConfiguration = getSslConfiguration();
		final Set<HostAndPort> sentinels = convertToJedisSentinelSet(dataSource.getSentinels());
		final DefaultJedisClientConfig.Builder builder = DefaultJedisClientConfig.builder();
		final DefaultJedisClientConfig.Builder sentinelBuilder = DefaultJedisClientConfig.builder();

		builder.connectionTimeoutMillis(getConnectTimeout()).socketTimeoutMillis(getSoTimeout())
				.blockingSocketTimeoutMillis(getInfiniteSoTimeout()).database(dataSource.getDatabase());

		if(Validate.hasText(dataSource.getUser())){
			builder.user(dataSource.getUser());
		}

		if(Validate.hasText(dataSource.getPassword())){
			builder.password(dataSource.getPassword());
		}

		if(Validate.hasText(dataSource.getClientName())){
			builder.clientName(dataSource.getClientName());
		}

		sentinelBuilder.connectionTimeoutMillis(getSentinelConnectTimeout())
				.socketTimeoutMillis(getSentinelSoTimeout());

		if(Validate.hasText(dataSource.getSentinelUser())){
			sentinelBuilder.user(dataSource.getSentinelUser());
		}

		if(Validate.hasText(dataSource.getSentinelPassword())){
			sentinelBuilder.password(dataSource.getSentinelPassword());
		}

		if(Validate.hasText(dataSource.getSentinelClientName())){
			sentinelBuilder.clientName(dataSource.getSentinelClientName());
		}

		if(sslConfiguration == null){
			logger.debug("Create jedis sentinel pool.");
		}else{
			logger.debug("Create jedis sentinel pool with ssl.");
			builder.ssl(isUseSsl()).sslSocketFactory(sslConfiguration.getSslSocketFactory())
					.sslParameters(sslConfiguration.getSslParameters())
					.hostnameVerifier(sslConfiguration.getHostnameVerifier());
			sentinelBuilder.ssl(isUseSsl()).sslSocketFactory(sslConfiguration.getSslSocketFactory())
					.sslParameters(sslConfiguration.getSslParameters())
					.hostnameVerifier(sslConfiguration.getHostnameVerifier());
		}

		return new JedisSentinelPool(dataSource.getMaster().getName(), sentinels, getPoolConfig(), builder.build(),
				sentinelBuilder.build());
	}

	private Set<HostAndPort> convertToJedisSentinelSet(Collection<RedisNode> nodes){
		if(Validate.isEmpty(nodes)){
			return Collections.emptySet();
		}

		Set<HostAndPort> convertedNodes = new LinkedHashSet<>(nodes.size());

		for(RedisNode node : nodes){
			if(node != null){
				convertedNodes.add(new HostAndPort(node.getHost(), node.getPort()));
			}
		}

		return convertedNodes;
	}

	protected Jedis createJedis(final JedisSentinelDataSource dataSource){
		SslConfiguration sslConfiguration = getSslConfiguration();

		for(RedisNode node : dataSource.getSentinels()){
			Jedis jedis = new Jedis(node.getHost(), node.getPort(), getConnectTimeout(), getSoTimeout());

			try{
				if(Constants.PONG.equalsIgnoreCase(jedis.ping())){
					if(Validate.hasText(node.getName())){
						jedis.clientSetname(node.getName());
					}
					return jedis;
				}
			}catch(Exception ex){
				logger.warn("Ping failed for sentinel host: {}", node.getHost(), ex);
			}
		}

		throw new InvalidDataAccessResourceUsageException("No Sentinel found");
	}

	protected boolean isUsePool(){
		return getPoolConfig() != null;
	}

	@Override
	protected void doConnect() throws IOException{
		boolean usePool = isUsePool();

		try{
			if(usePool){
				jedis = pool.getResource();

				if(logger.isInfoEnabled()){
					logger.info("Jedis initialized with pool success.");
				}
			}else{
				JedisDataSource dataSource = (JedisDataSource) getDataSource();

				if(jedis.isConnected() == false){
					jedis.connect();

					if(Validate.hasText(dataSource.getPassword())){
						jedis.auth(dataSource.getPassword());
					}

					if(dataSource.getDatabase() != DEFAULT_DB){
						jedis.select(dataSource.getDatabase());
					}

					if(Validate.hasText(dataSource.getClientName())){
						jedis.clientSetname(dataSource.getClientName());
					}
				}

				if(logger.isInfoEnabled()){
					logger.info("Jedis initialized success.");
				}
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				if(usePool){
					logger.error("Jedis initialized with pool failure: {}", e.getMessage(), e);
				}else{
					logger.error("Jedis initialized failure: {}", e.getMessage(), e);
				}
			}

			throw RedisExceptionUtils.convert(e);
		}
	}

	@Override
	protected void doDestroy() throws IOException{
		super.doDestroy();

		logger.info("Jedis destroy.");
		if(pool != null){
			logger.info("Jedis Pool for {} destroy.", pool.getClass().getName());

			try{
				pool.destroy();
			}catch(Exception ex){
				logger.warn("Cannot properly close Jedis pool.", ex);
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
			logger.info("Jedis quit and disconnect.");

			if(jedis != null){
				jedis.quit();
				jedis.disconnect();
			}
		}
	}

}
