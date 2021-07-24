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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.jedis;

import com.buession.core.Executor;
import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.RedisStandaloneConnection;
import com.buession.redis.client.connection.SslConfiguration;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.exception.RedisException;
import com.buession.redis.exception.RedisExceptionUtils;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import com.buession.redis.pipeline.jedis.StandaloneJedisPipeline;
import com.buession.redis.transaction.jedis.JedisTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.List;

/**
 * Jedis 单机模式连接器
 *
 * @author Yong.Teng
 */
public class JedisConnection extends AbstractJedisRedisConnection implements RedisStandaloneConnection {

	/**
	 * 连接池配置
	 *
	 * @since 1.3.0
	 */
	private JedisPoolConfig poolConfig;

	/**
	 * 连接池
	 */
	private JedisPool pool;

	/**
	 * Jedis 对象
	 */
	private Jedis jedis;

	private final static Logger logger = LoggerFactory.getLogger(JedisConnection.class);

	/**
	 * 构造函数
	 */
	public JedisConnection(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public JedisConnection(JedisDataSource dataSource){
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
	public JedisConnection(JedisDataSource dataSource, int connectTimeout, int soTimeout){
		super(dataSource, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisConnection(JedisDataSource dataSource, SslConfiguration sslConfiguration){
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
	public JedisConnection(JedisDataSource dataSource, int connectTimeout, int soTimeout,
						   SslConfiguration sslConfiguration){
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisConnection(JedisDataSource dataSource, JedisPoolConfig poolConfig){
		super(dataSource);
		this.poolConfig = poolConfig;
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
	public JedisConnection(JedisDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout, int soTimeout){
		super(dataSource, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
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
	public JedisConnection(JedisDataSource dataSource, JedisPoolConfig poolConfig, SslConfiguration sslConfiguration){
		super(dataSource, sslConfiguration);
		this.poolConfig = poolConfig;
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
	public JedisConnection(JedisDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout, int soTimeout,
						   SslConfiguration sslConfiguration){
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
		this.poolConfig = poolConfig;
	}

	/**
	 * 返回连接池配置 {@link JedisPoolConfig}
	 *
	 * @return 连接池配置
	 */
	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	/**
	 * 设置连接池配置 {@link JedisPoolConfig}
	 *
	 * @param poolConfig
	 * 		连接池配置
	 */
	public void setPoolConfig(JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

	@Override
	public <C, R> R execute(Executor<C, R> executor) throws RedisException{
		try{
			return executor.execute((C) jedis);
		}catch(Exception e){
			logger.error("Redis execute command failure: ", e);
			throw RedisExceptionUtils.convert(e);
		}
	}

	@Override
	public boolean isTransaction(){
		return JedisClientUtils.isInMulti(jedis);
	}

	@Override
	public Pipeline getPipeline(){
		if(pipeline == null){
			pipeline = new JedisPipeline(new StandaloneJedisPipeline(jedis.pipelined()));
		}

		return pipeline;
	}

	@Override
	public void multi(){
		transaction = new JedisTransaction(jedis.multi());
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

		JedisDataSource jedisDataSource = (JedisDataSource) dataSource;
		if(isUsePool()){
			pool = createPool(jedisDataSource);
		}else{
			jedis = createJedis(jedisDataSource);
		}
	}

	protected JedisPool createPool(final JedisDataSource dataSource){
		final SslConfiguration sslConfiguration = getSslConfiguration();

		if(sslConfiguration == null){
			logger.debug("Create jedis pool.");
			return new JedisPool(getPoolConfig(), dataSource.getHost(), dataSource.getPort(), getConnectTimeout(),
					getSoTimeout(), redisPassword(dataSource.getPassword()), dataSource.getDatabase(),
					dataSource.getClientName(), isUseSsl());
		}else{
			logger.debug("Create jedis pool with ssl.");
			return new JedisPool(getPoolConfig(), dataSource.getHost(), dataSource.getPort(), getConnectTimeout(),
					getSoTimeout(), redisPassword(dataSource.getPassword()), dataSource.getDatabase(),
					dataSource.getClientName(), isUseSsl(), sslConfiguration.getSslSocketFactory(),
					sslConfiguration.getSslParameters(), sslConfiguration.getHostnameVerifier());
		}
	}

	protected Jedis createJedis(final JedisDataSource dataSource){
		SslConfiguration sslConfiguration = getSslConfiguration();

		if(sslConfiguration == null){
			logger.debug("Create jedis.");
			return new Jedis(dataSource.getHost(), dataSource.getPort(), getConnectTimeout(), getSoTimeout(),
					isUseSsl());
		}else{
			logger.debug("Create jedis with ssl.");
			return new Jedis(dataSource.getHost(), dataSource.getPort(), getConnectTimeout(), getSoTimeout(),
					isUseSsl(), sslConfiguration.getSslSocketFactory(), sslConfiguration.getSslParameters(),
					sslConfiguration.getHostnameVerifier());
		}
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
	protected void doDisconnect() throws IOException{
		super.doDisconnect();

		logger.info("Jedis disconnect.");
		jedis.disconnect();
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
