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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.jedis;

import com.buession.core.Executor;
import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.StandaloneConnection;
import com.buession.redis.client.connection.SslConfiguration;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import com.buession.redis.transaction.jedis.JedisTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Client;
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
public class JedisConnection extends AbstractJedisRedisConnection<Jedis> implements JedisRedisConnection<Jedis>,
		StandaloneConnection {

	/**
	 * Jedis 连接池
	 */
	private JedisPool pool;

	/**
	 * Jedis 对象
	 */
	private Jedis jedis;

	private static Logger logger = LoggerFactory.getLogger(JedisConnection.class);

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
	 * @param useSsl
	 * 		是否启用 SSL 连接
	 */
	public JedisConnection(JedisDataSource dataSource, boolean useSsl){
		super(dataSource, useSsl);
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
	 * @param useSsl
	 * 		是否启用 SSL 连接
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisConnection(JedisDataSource dataSource, boolean useSsl, SslConfiguration sslConfiguration){
		super(dataSource, useSsl, sslConfiguration);
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
	 * @param useSsl
	 * 		是否启用 SSL 连接
	 */
	public JedisConnection(JedisDataSource dataSource, int connectTimeout, int soTimeout, boolean useSsl){
		super(dataSource, connectTimeout, soTimeout, useSsl);
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
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param useSsl
	 * 		是否启用 SSL 连接
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisConnection(JedisDataSource dataSource, int connectTimeout, int soTimeout, boolean useSsl,
			SslConfiguration sslConfiguration){
		super(dataSource, connectTimeout, soTimeout, useSsl, sslConfiguration);
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
	public JedisConnection(JedisDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout, int soTimeout){
		super(dataSource, poolConfig, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param useSsl
	 * 		是否启用 SSL 连接
	 */
	public JedisConnection(JedisDataSource dataSource, JedisPoolConfig poolConfig, boolean useSsl){
		super(dataSource, poolConfig, useSsl);
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
		super(dataSource, poolConfig, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param useSsl
	 * 		是否启用 SSL 连接
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisConnection(JedisDataSource dataSource, JedisPoolConfig poolConfig, boolean useSsl,
			SslConfiguration sslConfiguration){
		super(dataSource, poolConfig, useSsl, sslConfiguration);
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
	 * @param useSsl
	 * 		是否启用 SSL 连接
	 */
	public JedisConnection(JedisDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout, int soTimeout,
			boolean useSsl){
		super(dataSource, poolConfig, connectTimeout, soTimeout, useSsl);
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
	 * @param useSsl
	 * 		是否启用 SSL 连接
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public JedisConnection(JedisDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout, int soTimeout,
			boolean useSsl, SslConfiguration sslConfiguration){
		super(dataSource, poolConfig, connectTimeout, soTimeout, useSsl, sslConfiguration);
	}

	@Override
	public JedisPool getPool(){
		return pool;
	}

	@Override
	public boolean isTransaction(){
		return jedis == null ? false : JedisClientUtils.isInMulti(jedis);
	}

	@Override
	public Pipeline getPipeline(){
		if(pipeline == null){
			pipeline = new JedisPipeline(jedis.pipelined());
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

	protected JedisPool createPool(final JedisDataSource dataSource){
		final SslConfiguration sslConfiguration = getSslConfiguration();
		final JedisPool pool = new JedisPool(getPoolConfig(), dataSource.getHost(), dataSource.getPort(),
				getConnectTimeout(), getSoTimeout(), "".equals(dataSource.getPassword()) ? null :
				dataSource.getPassword(), dataSource.getDatabase(), dataSource.getClientName(), isUseSsl(),
				sslConfiguration.getSslSocketFactory(), sslConfiguration.getSslParameters(),
				sslConfiguration.getHostnameVerifier());

		return pool;
	}

	@Override
	protected void doConnect() throws IOException{
		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return;
		}

		JedisDataSource jedisDataSource = ((JedisDataSource) dataSource);
		if(getPoolConfig() != null){
			pool = createPool(jedisDataSource);

			try{
				jedis = pool.getResource();
			}catch(Exception e){
				throw JedisConverters.exceptionConvert(e);
			}

			logger.info("Jedis initialize with pool success.");
		}else{
			SslConfiguration sslConfiguration = getSslConfiguration();

			jedis = new Jedis(jedisDataSource.getHost(), jedisDataSource.getPort(), getConnectTimeout(),
					getSoTimeout(), isUseSsl(), sslConfiguration.getSslSocketFactory(),
					sslConfiguration.getSslParameters(), sslConfiguration.getHostnameVerifier());

			Client client = jedis.getClient();

			if("".equals(jedisDataSource.getPassword()) == false){
				client.setPassword(jedisDataSource.getPassword());
			}
			client.setDb(jedisDataSource.getDatabase());

			try{
				jedis.connect();
			}catch(Exception e){
				throw JedisConverters.exceptionConvert(e);
			}

			if(Validate.hasText(jedisDataSource.getClientName())){
				client.clientSetname(jedisDataSource.getClientName());
			}

			logger.info("Jedis initialize success.");
		}
	}

	@Override
	protected <R> R doExecute(Executor<Jedis, R> executor) throws Exception{
		return executor.execute(jedis);
	}

	@Override
	protected boolean checkConnect(){
		return jedis != null && jedis.isConnected();
	}

	@Override
	protected boolean checkClosed(){
		return jedis == null || jedis.isConnected() == false;
	}

	@Override
	protected void doDisconnect() throws IOException{
		super.doDisconnect();
		if(jedis != null){
			jedis.disconnect();
		}
	}

	@Override
	protected void doClose() throws IOException{
		super.doClose();
		if(jedis != null){
			if(pool != null){
				jedis.close();
			}else{
				jedis.quit();
				disconnect();
			}
		}
	}

}
