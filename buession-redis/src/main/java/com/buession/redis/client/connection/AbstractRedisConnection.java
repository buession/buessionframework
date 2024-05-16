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

import com.buession.lang.Status;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.core.Constants;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.exception.RedisConnectionFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Redis 连接对象抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractRedisConnection implements RedisConnection {

	/**
	 * Redis 数据源
	 */
	private DataSource dataSource;

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
	 * SSL 配置
	 */
	private SslConfiguration sslConfiguration;

	private volatile boolean initialized = false;

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
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 */
	public AbstractRedisConnection(DataSource dataSource, int connectTimeout, int soTimeout) {
		this.dataSource = dataSource;
		this.connectTimeout = connectTimeout;
		this.soTimeout = soTimeout;
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
	public AbstractRedisConnection(DataSource dataSource, int connectTimeout, int soTimeout,
								   int infiniteSoTimeout) {
		this(dataSource, connectTimeout, soTimeout);
		this.infiniteSoTimeout = infiniteSoTimeout;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public AbstractRedisConnection(DataSource dataSource, SslConfiguration sslConfiguration) {
		this.dataSource = dataSource;
		this.sslConfiguration = sslConfiguration;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout（单位：毫秒）
	 * 		连接超时
	 * @param soTimeout（单位：毫秒）
	 * 		读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public AbstractRedisConnection(DataSource dataSource, int connectTimeout, int soTimeout,
								   SslConfiguration sslConfiguration) {
		this(dataSource, connectTimeout, soTimeout);
		this.sslConfiguration = sslConfiguration;
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
	 * @param infiniteSoTimeout（单位：毫秒）
	 * 		Infinite 读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 2.0.0
	 */
	public AbstractRedisConnection(DataSource dataSource, int connectTimeout, int soTimeout,
								   int infiniteSoTimeout, SslConfiguration sslConfiguration) {
		this(dataSource, connectTimeout, soTimeout, sslConfiguration);
		this.infiniteSoTimeout = infiniteSoTimeout;
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
	public boolean isUseSsl() {
		return sslConfiguration != null;
	}

	@Override
	public SslConfiguration getSslConfiguration() {
		return sslConfiguration;
	}

	@Override
	public void setSslConfiguration(SslConfiguration sslConfiguration) {
		this.sslConfiguration = sslConfiguration;
	}

	@Override
	public Status connect() throws RedisConnectionFailureException {
		logger.info("Connection redis server.");

		try{
			initialized();
			doConnect();

			return Status.SUCCESS;
		}catch(Exception e){
			logger.error("Connection redis server error: {}.", e.getMessage());
			throw new RedisConnectionFailureException(e.getMessage(), e);
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

	protected abstract void doConnect() throws RedisConnectionFailureException;

	protected abstract void doDestroy() throws IOException;

	protected abstract void doClose() throws IOException;

}
