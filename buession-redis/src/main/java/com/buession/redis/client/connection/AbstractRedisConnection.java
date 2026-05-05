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
package com.buession.redis.client.connection;

import com.buession.core.Executor;
import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.lang.Status;
import com.buession.redis.core.Constants;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.SslOptions;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.exception.NotMultiRedisException;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Redis 连接对象抽象类
 *
 * @param <C>
 * 		原生客户端
 *
 * @author Yong.Teng
 */
public abstract class AbstractRedisConnection<C> implements RedisConnection {

	protected final static PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();

	/**
	 * Redis 数据源
	 */
	private DataSource dataSource;

	/**
	 * 连接池配置
	 *
	 * @since 3.0.0
	 */
	private PoolConfig poolConfig;

	/**
	 * 连接超时（单位：毫秒）
	 */
	private int connectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

	/**
	 * 读取超时（单位：毫秒）
	 */
	private int soTimeout = Constants.DEFAULT_SO_TIMEOUT;

	/**
	 * Infinite 读取超时（单位：毫秒）
	 *
	 * @since 2.0.0
	 */
	private int infiniteSoTimeout = Constants.DEFAULT_INFINITE_SO_TIMEOUT;

	/**
	 * 是否自动重连
	 *
	 * @since 4.0.0
	 */
	private boolean autoReconnect = true;

	/**
	 * SSL 配置
	 *
	 * @since 4.0.0
	 */
	private SslOptions sslOptions;

	/**
	 * 原生客户端
	 *
	 * @since 4.0.0
	 */
	protected C client;

	private volatile boolean initialized = false;

	/**
	 * 事务
	 */
	protected volatile Transaction transaction;

	/**
	 * 管道
	 */
	protected volatile Pipeline pipeline;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 构造函数
	 */
	public AbstractRedisConnection() {
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public AbstractRedisConnection(DataSource dataSource) {
		this.dataSource = dataSource;
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
	public AbstractRedisConnection(DataSource dataSource, PoolConfig poolConfig) {
		this(dataSource);
		this.poolConfig = poolConfig;
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public PoolConfig getPoolConfig() {
		return poolConfig;
	}

	@Override
	public void setPoolConfig(PoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	@Override
	public int getConnectTimeout() {
		return connectTimeout;
	}

	@Override
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	@Override
	public int getSoTimeout() {
		return soTimeout;
	}

	@Override
	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

	@Override
	public int getInfiniteSoTimeout() {
		return infiniteSoTimeout;
	}

	@Override
	public void setInfiniteSoTimeout(int infiniteSoTimeout) {
		this.infiniteSoTimeout = infiniteSoTimeout;
	}

	@Override
	public boolean getAutoReconnect() {
		return autoReconnect;
	}

	@Override
	public void setAutoReconnect(boolean autoReconnect) {
		this.autoReconnect = autoReconnect;
	}

	@Override
	public SslOptions getSslOptions() {
		return sslOptions;
	}

	@Override
	public void setSslOptions(SslOptions sslOptions) {
		this.sslOptions = sslOptions;
	}

	public C getClient() {
		return client;
	}

	@Override
	public Status connect() throws RedisConnectionFailureException {
		logger.info("Connection redis server.");

		try{
			initialized();
			return doConnect();
		}catch(Exception e){
			logger.error("Connection redis server error: {}.", e.getMessage());
			throw new RedisConnectionFailureException(e.getMessage(), e);
		}
	}

	@Override
	public <R> R execute(final Executor<RedisConnection, R> executor) throws RedisException {
		try{
			return executor.execute(this);
		}catch(Exception e){
			throw executeException(e);
		}
	}

	@Override
	public boolean isPipeline() {
		return pipeline != null;
	}

	@Override
	public void closePipeline() {
		if(pipeline != null){
			pipeline.close();
			pipeline = null;
		}
	}

	@Override
	public boolean isTransaction() {
		return transaction != null;
	}

	@Override
	public Transaction getTransaction() {
		return transaction;
	}

	@Override
	public Pipeline getPipeline() {
		return pipeline;
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
			throw new NotMultiRedisException(RedisCommand.EXEC);
		}
	}

	@Override
	public Status discard() throws RedisException {
		if(transaction != null){
			final OkStatusConverter okStatusConverter = new OkStatusConverter();
			String result = transaction.discard();
			transaction = null;
			return okStatusConverter.convert(result);
		}else{
			throw new NotMultiRedisException(RedisCommand.DISCARD);
		}
	}

	@Override
	public void destroy() throws IOException {
		logger.info("Destroy redis server.");
		doDestroy();
	}

	@Override
	public void close() throws IOException {
		logger.info("Closing redis server.");
		doClose();
	}

	protected void initialized() {
		if(initialized == false){
			synchronized(this){
				if(initialized == false){
					internalInit();
					initialized = true;
				}
			}
		}
	}

	protected abstract void internalInit();

	protected abstract Status doConnect() throws RedisConnectionFailureException;

	protected abstract RedisException executeException(final Exception e);

	protected abstract void doDestroy() throws IOException;

	protected abstract void doClose() throws IOException;

}
