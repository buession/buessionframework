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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.lettuce;

import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.RedisStandaloneConnection;
import com.buession.redis.client.connection.datasource.lettuce.LettuceDataSource;
import com.buession.redis.core.internal.lettuce.RedisClientBuilder;
import com.buession.redis.exception.LettuceRedisExceptionUtils;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.transaction.Transaction;
import io.lettuce.core.LettucePool;
import io.lettuce.core.RedisClient;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.connection.StandaloneStatefulRedisConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Lettuce 单机模式连接器
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class LettuceConnection extends AbstractLettuceRedisConnection implements RedisStandaloneConnection {

	/**
	 * 连接池
	 */
	private LettucePool pool;

	/**
	 * {@link StandaloneStatefulRedisConnection} 对象
	 */
	private StandaloneStatefulRedisConnection statefulRedisConnection;

	private final static Logger logger = LoggerFactory.getLogger(LettuceConnection.class);

	/**
	 * 构造函数
	 */
	public LettuceConnection(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public LettuceConnection(LettuceDataSource dataSource){
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
	public LettuceConnection(LettuceDataSource dataSource, int connectTimeout, int soTimeout){
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
	public LettuceConnection(LettuceDataSource dataSource, int connectTimeout, int soTimeout, int infiniteSoTimeout){
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
	public LettuceConnection(LettuceDataSource dataSource, SslConfiguration sslConfiguration){
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
	 */
	public LettuceConnection(LettuceDataSource dataSource, int connectTimeout, int soTimeout, int infiniteSoTimeout,
							 SslConfiguration sslConfiguration){
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
	public LettuceConnection(LettuceDataSource dataSource, LettucePool pool){
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
	public LettuceConnection(LettuceDataSource dataSource, LettucePool pool, int connectTimeout, int soTimeout){
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
	public LettuceConnection(LettuceDataSource dataSource, LettucePool pool, int connectTimeout, int soTimeout,
							 int infiniteSoTimeout){
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
	public LettuceConnection(LettuceDataSource dataSource, LettucePool pool, SslConfiguration sslConfiguration){
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
	public LettuceConnection(LettuceDataSource dataSource, LettucePool pool, int connectTimeout, int soTimeout,
							 SslConfiguration sslConfiguration){
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
	public LettuceConnection(LettuceDataSource dataSource, LettucePool pool, int connectTimeout, int soTimeout,
							 int infiniteSoTimeout, SslConfiguration sslConfiguration){
		super(dataSource, connectTimeout, soTimeout, infiniteSoTimeout, sslConfiguration);
		this.pool = pool;
	}

	public StandaloneStatefulRedisConnection getStandaloneStatefulRedisConnection(){
		return statefulRedisConnection;
	}

	@Override
	public Pipeline openPipeline(){
		if(pipeline == null){
			//pipeline = new JedisPipeline(jedis.pipelined());
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
			//transaction = new JedisTransaction(jedis.multi());
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
	public boolean isConnect(){
		return statefulRedisConnection != null && statefulRedisConnection.isOpen();
	}

	@Override
	public boolean isClosed(){
		return statefulRedisConnection == null || statefulRedisConnection.isOpen() == false;
	}

	@Override
	protected void internalInit(){
	}

	protected boolean isUsePool(){
		return pool != null;
	}

	@Override
	protected void doConnect() throws RedisConnectionFailureException{
		if(isUsePool()){
			try{
				statefulRedisConnection = pool.getResource();

				if(logger.isInfoEnabled()){
					logger.info("Lettuce initialized with pool success.");
				}
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("Lettuce initialized with pool failure: {}", e.getMessage(), e);
				}

				throw LettuceRedisExceptionUtils.convert(e);
			}
		}else{
			final LettuceDataSource dataSource = (LettuceDataSource) getDataSource();
			final RedisClientBuilder redisClientBuilder = RedisClientBuilder.create()
					.host(dataSource.getHost())
					.port(dataSource.getPort())
					.connectionTimeout(dataSource.getConnectTimeout())
					.soTimeout(dataSource.getSoTimeout())
					.infiniteSoTimeout(dataSource.getInfiniteSoTimeout())
					.user(dataSource.getUsername())
					.password(dataSource.getPassword())
					.database(dataSource.getDatabase())
					.clientName(dataSource.getClientName())
					.ssl(isUseSsl())
					.sslConfiguration(dataSource.getSslConfiguration());

			final RedisClient redisClient = redisClientBuilder.build();

			statefulRedisConnection = new StandaloneStatefulRedisConnection(redisClient.connect(new ByteArrayCodec()));
		}
	}

	@Override
	protected void doDestroy() throws IOException{
		super.doDestroy();

		logger.info("Lettuce destroy.");
		if(pool != null){
			logger.info("Lettuce pool for {} destroy.", pool.getClass().getName());

			try{
				pool.destroy();
			}catch(Exception ex){
				logger.warn("Cannot properly close Lettuce pool.", ex);
			}

			pool = null;
		}
	}

	@Override
	protected void doClose() throws IOException{
		super.doClose();

		logger.info("Lettuce close.");
		if(isUsePool()){
			logger.info("Lettuce close.");

			if(statefulRedisConnection != null){
				statefulRedisConnection.close();
			}
		}else{
			logger.info("Lettuce quit.");

			if(statefulRedisConnection != null){
				Exception ex = null;

				try{
					statefulRedisConnection.sync().quit();
				}catch(Exception e){
					ex = e;
				}

				/*
				try{
					statefulRedisConnection.sync().disconnect();
				}catch(Exception e){
					ex = e;
				}
				 */

				if(ex != null){
					throw new RedisException(ex);
				}
			}
		}
	}

}
