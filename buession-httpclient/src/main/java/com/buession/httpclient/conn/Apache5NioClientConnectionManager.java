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
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 											   |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.conn;

import com.buession.httpclient.conn.nio.IOReactorConfig;
import com.buession.httpclient.core.Configuration;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManager;
import org.apache.hc.client5.http.nio.AsyncClientConnectionManager;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import java.util.concurrent.ThreadFactory;

/**
 * Apache HttpComponents 5 异步连接管理器
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public class Apache5NioClientConnectionManager extends ApacheBaseClientConnectionManager<AsyncClientConnectionManager>
		implements com.buession.httpclient.apache.ApacheNioClientConnectionManager {

	private IOReactorConfig ioReactorConfig;

	/**
	 * 线程工厂
	 */
	private ThreadFactory threadFactory;

	/**
	 * 构造函数，创建驱动默认连接管理器
	 */
	public Apache5NioClientConnectionManager() {
		this(new IOReactorConfig());
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param configuration
	 * 		连接对象
	 */
	public Apache5NioClientConnectionManager(Configuration configuration) {
		this(configuration, new IOReactorConfig());
	}

	/**
	 * 构造函数
	 *
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 */
	public Apache5NioClientConnectionManager(AsyncClientConnectionManager clientConnectionManager) {
		this(clientConnectionManager, new IOReactorConfig());
	}

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		连接对象
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 */
	public Apache5NioClientConnectionManager(Configuration configuration,
											 AsyncClientConnectionManager clientConnectionManager) {
		this(configuration, clientConnectionManager, new IOReactorConfig());
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param ioReactorConfig
	 *        {@link IOReactorConfig}
	 */
	public Apache5NioClientConnectionManager(IOReactorConfig ioReactorConfig) {
		super();
		this.ioReactorConfig = ioReactorConfig;
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param configuration
	 * 		连接对象
	 * @param ioReactorConfig
	 *        {@link IOReactorConfig}
	 */
	public Apache5NioClientConnectionManager(Configuration configuration, IOReactorConfig ioReactorConfig) {
		super(configuration);
		this.ioReactorConfig = ioReactorConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 * @param ioReactorConfig
	 *        {@link IOReactorConfig}
	 */
	public Apache5NioClientConnectionManager(AsyncClientConnectionManager clientConnectionManager,
											 IOReactorConfig ioReactorConfig) {
		super(clientConnectionManager);
		this.ioReactorConfig = ioReactorConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		连接对象
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 * @param ioReactorConfig
	 *        {@link IOReactorConfig}
	 */
	public Apache5NioClientConnectionManager(Configuration configuration,
											 AsyncClientConnectionManager clientConnectionManager,
											 IOReactorConfig ioReactorConfig) {
		super(configuration, clientConnectionManager);
		this.ioReactorConfig = ioReactorConfig;
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param threadFactory
	 * 		线程工厂
	 */
	public Apache5NioClientConnectionManager(ThreadFactory threadFactory) {
		super();
		this.threadFactory = threadFactory;
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param configuration
	 * 		连接对象
	 * @param threadFactory
	 * 		线程工厂
	 */
	public Apache5NioClientConnectionManager(Configuration configuration, ThreadFactory threadFactory) {
		super(configuration);
		this.threadFactory = threadFactory;
	}

	/**
	 * 构造函数
	 *
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 * @param threadFactory
	 * 		线程工厂
	 */
	public Apache5NioClientConnectionManager(AsyncClientConnectionManager clientConnectionManager,
											 ThreadFactory threadFactory) {
		super(clientConnectionManager);
		this.threadFactory = threadFactory;
	}

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		连接对象
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 * @param threadFactory
	 * 		线程工厂
	 */
	public Apache5NioClientConnectionManager(Configuration configuration,
											 AsyncClientConnectionManager clientConnectionManager,
											 ThreadFactory threadFactory) {
		super(configuration, clientConnectionManager);
		this.threadFactory = threadFactory;
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param ioReactorConfig
	 *        {@link IOReactorConfig}
	 * @param threadFactory
	 * 		线程工厂
	 */
	public Apache5NioClientConnectionManager(IOReactorConfig ioReactorConfig, ThreadFactory threadFactory) {
		super();
		this.ioReactorConfig = ioReactorConfig;
		this.threadFactory = threadFactory;
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param configuration
	 * 		连接对象
	 * @param ioReactorConfig
	 *        {@link IOReactorConfig}
	 * @param threadFactory
	 * 		线程工厂
	 */
	public Apache5NioClientConnectionManager(Configuration configuration, IOReactorConfig ioReactorConfig,
											 ThreadFactory threadFactory) {
		this(configuration, ioReactorConfig);
		this.threadFactory = threadFactory;
	}

	/**
	 * 构造函数
	 *
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 * @param ioReactorConfig
	 *        {@link IOReactorConfig}
	 * @param threadFactory
	 * 		线程工厂
	 */
	public Apache5NioClientConnectionManager(AsyncClientConnectionManager clientConnectionManager,
											 IOReactorConfig ioReactorConfig, ThreadFactory threadFactory) {
		this(clientConnectionManager, ioReactorConfig);
		this.threadFactory = threadFactory;
	}

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		连接对象
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 * @param ioReactorConfig
	 *        {@link IOReactorConfig}
	 * @param threadFactory
	 * 		线程工厂
	 */
	public Apache5NioClientConnectionManager(Configuration configuration,
											 AsyncClientConnectionManager clientConnectionManager,
											 IOReactorConfig ioReactorConfig, ThreadFactory threadFactory) {
		this(configuration, clientConnectionManager, ioReactorConfig);
		this.threadFactory = threadFactory;
	}

	@Override
	public IOReactorConfig getIoReactorConfig() {
		return ioReactorConfig;
	}

	@Override
	public void setIoReactorConfig(IOReactorConfig ioReactorConfig) {
		this.ioReactorConfig = ioReactorConfig;
	}

	/**
	 * 返回线程工厂
	 *
	 * @return 线程工厂
	 */
	public ThreadFactory getThreadFactory() {
		return threadFactory;
	}

	/**
	 * 设置线程工厂
	 *
	 * @param threadFactory
	 * 		线程工厂
	 */
	public void setThreadFactory(ThreadFactory threadFactory) {
		this.threadFactory = threadFactory;
	}

	/**
	 * 创建驱动默认连接管理器
	 *
	 * @return 连接管理器
	 */
	@Override
	protected AsyncClientConnectionManager createDefaultClientConnectionManager() {
		final PoolingAsyncClientConnectionManager connectionManager = new PoolingAsyncClientConnectionManager();

		//最大连接数
		connectionManager.setMaxTotal(getConfiguration().getMaxConnections());
		//并发数
		connectionManager.setDefaultMaxPerRoute(getConfiguration().getMaxPerRoute());
		// 空闲连接存活时长
		connectionManager.closeIdle(Timeout.ofMilliseconds(getConfiguration().getIdleConnectionTime()));

		final ConnectionConfig.Builder connectionConfigBuilder = ConnectionConfig.custom();

		connectionConfigBuilder.setConnectTimeout(Timeout.ofMilliseconds(getConfiguration().getConnectTimeout()));
		connectionConfigBuilder.setSocketTimeout(Timeout.ofMilliseconds(getConfiguration().getReadTimeout()));

		if(getConfiguration().getConnectionTimeToLive() > -1){
			connectionConfigBuilder.setTimeToLive(
					TimeValue.ofMilliseconds(getConfiguration().getConnectionTimeToLive()));
		}

		connectionManager.setDefaultConnectionConfig(connectionConfigBuilder.build());

		return connectionManager;
	}

}
