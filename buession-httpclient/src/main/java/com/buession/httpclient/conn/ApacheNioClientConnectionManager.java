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

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.httpclient.conn.nio.IOReactorConfig;
import com.buession.httpclient.core.Configuration;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.conn.NHttpClientConnectionManager;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Apache HttpComponents 4 异步连接管理器
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class ApacheNioClientConnectionManager extends ApacheBaseClientConnectionManager<NHttpClientConnectionManager>
		implements com.buession.httpclient.apache.ApacheNioClientConnectionManager {

	private IOReactorConfig ioReactorConfig;

	/**
	 * 线程工厂
	 */
	private ThreadFactory threadFactory;

	/**
	 * 构造函数，创建驱动默认连接管理器
	 */
	public ApacheNioClientConnectionManager() {
		this(new IOReactorConfig());
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param configuration
	 * 		连接对象
	 */
	public ApacheNioClientConnectionManager(Configuration configuration) {
		this(configuration, new IOReactorConfig());
	}

	/**
	 * 构造函数
	 *
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 */
	public ApacheNioClientConnectionManager(NHttpClientConnectionManager clientConnectionManager) {
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
	public ApacheNioClientConnectionManager(Configuration configuration,
											NHttpClientConnectionManager clientConnectionManager) {
		this(configuration, clientConnectionManager, new IOReactorConfig());
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param ioReactorConfig
	 *        {@link IOReactorConfig}
	 */
	public ApacheNioClientConnectionManager(IOReactorConfig ioReactorConfig) {
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
	public ApacheNioClientConnectionManager(Configuration configuration, IOReactorConfig ioReactorConfig) {
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
	public ApacheNioClientConnectionManager(NHttpClientConnectionManager clientConnectionManager,
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
	public ApacheNioClientConnectionManager(Configuration configuration,
											NHttpClientConnectionManager clientConnectionManager,
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
	public ApacheNioClientConnectionManager(ThreadFactory threadFactory) {
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
	public ApacheNioClientConnectionManager(Configuration configuration, ThreadFactory threadFactory) {
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
	public ApacheNioClientConnectionManager(NHttpClientConnectionManager clientConnectionManager,
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
	public ApacheNioClientConnectionManager(Configuration configuration,
											NHttpClientConnectionManager clientConnectionManager,
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
	public ApacheNioClientConnectionManager(IOReactorConfig ioReactorConfig, ThreadFactory threadFactory) {
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
	public ApacheNioClientConnectionManager(Configuration configuration, IOReactorConfig ioReactorConfig,
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
	public ApacheNioClientConnectionManager(NHttpClientConnectionManager clientConnectionManager,
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
	public ApacheNioClientConnectionManager(Configuration configuration,
											NHttpClientConnectionManager clientConnectionManager,
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
	protected NHttpClientConnectionManager createDefaultClientConnectionManager() {
		final PoolingNHttpClientConnectionManager connectionManager =
				getConfiguration().getConnectionTimeToLive() > -1 ? new PoolingNHttpClientConnectionManager(
						createConnectingIOReactor(), null, getDefaultRegistry(), null, null,
						getConfiguration().getConnectionTimeToLive(), TimeUnit.MILLISECONDS) :
						new PoolingNHttpClientConnectionManager(createConnectingIOReactor());
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenPositiveNumber();

		// 最大连接数
		propertyMapper.from(getConfiguration().getMaxConnections()).to(connectionManager::setMaxTotal);
		// 每个路由的最大连接数
		propertyMapper.from(getConfiguration().getMaxPerRoute()).to(connectionManager::setDefaultMaxPerRoute);
		// 连接池中最大连接数
		propertyMapper.from(getConfiguration().getMaxRequests()).to(connectionManager::setMaxTotal);
		// 空闲连接存活时长
		if(getConfiguration().getIdleConnectionTime() > 0){
			connectionManager.closeIdleConnections(getConfiguration().getIdleConnectionTime(), TimeUnit.MILLISECONDS);
		}

		return connectionManager;
	}

	protected ConnectingIOReactor createConnectingIOReactor() {
		org.apache.http.impl.nio.reactor.IOReactorConfig ioReactorConfig = null;

		if(getIoReactorConfig() != null){
			final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
			final org.apache.http.impl.nio.reactor.IOReactorConfig.Builder ioReactorConfigBuilder =
					org.apache.http.impl.nio.reactor.IOReactorConfig.custom();

			propertyMapper.from(getIoReactorConfig().getSelectInterval()).to(ioReactorConfigBuilder::setSelectInterval);
			propertyMapper.from(getIoReactorConfig().getShutdownGracePeriod())
					.to(ioReactorConfigBuilder::setShutdownGracePeriod);
			propertyMapper.from(getIoReactorConfig().isInterestOpQueued())
					.to(ioReactorConfigBuilder::setInterestOpQueued);
			propertyMapper.from(getIoReactorConfig().getIoThreadCount()).to(ioReactorConfigBuilder::setIoThreadCount);
			propertyMapper.from(getIoReactorConfig().getSoTimeout()).to(ioReactorConfigBuilder::setSoTimeout);
			propertyMapper.from(getIoReactorConfig().isSoReuseAddress()).to(ioReactorConfigBuilder::setSoReuseAddress);
			propertyMapper.from(getIoReactorConfig().getSoLinger()).to(ioReactorConfigBuilder::setSoLinger);
			propertyMapper.from(getIoReactorConfig().isSoKeepAlive()).to(ioReactorConfigBuilder::setSoKeepAlive);
			propertyMapper.from(getIoReactorConfig().isTcpNoDelay()).to(ioReactorConfigBuilder::setTcpNoDelay);
			propertyMapper.from(getIoReactorConfig().getConnectTimeout()).to(ioReactorConfigBuilder::setConnectTimeout);
			propertyMapper.from(getIoReactorConfig().getSndBufSize()).to(ioReactorConfigBuilder::setSndBufSize);
			propertyMapper.from(getIoReactorConfig().getRcvBufSize()).to(ioReactorConfigBuilder::setRcvBufSize);
			propertyMapper.from(getIoReactorConfig().getBacklogSize()).to(ioReactorConfigBuilder::setBacklogSize);

			ioReactorConfig = ioReactorConfigBuilder.build();
		}

		try{
			return new DefaultConnectingIOReactor(ioReactorConfig, getThreadFactory());
		}catch(IOReactorException e){
			throw new IllegalStateException(e);
		}
	}

	private static Registry<SchemeIOSessionStrategy> getDefaultRegistry() {
		return RegistryBuilder.<SchemeIOSessionStrategy>create()
				.register("http", NoopIOSessionStrategy.INSTANCE)
				.register("https", SSLIOSessionStrategy.getDefaultStrategy())
				.build();
	}

}
