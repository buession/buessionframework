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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.conn;

import com.buession.httpclient.core.Configuration;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.URIScheme;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.util.Timeout;

/**
 * Apache HttpComponents Client 5 连接管理器
 *
 * @author Yong.Teng
 */
public class Apache5ClientConnectionManager extends ApacheBaseClientConnectionManager<HttpClientConnectionManager>
		implements com.buession.httpclient.apache.ApacheClientConnectionManager {

	/**
	 * 构造函数，创建驱动默认连接管理器
	 */
	public Apache5ClientConnectionManager() {
		super();
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param configuration
	 * 		配置
	 */
	public Apache5ClientConnectionManager(Configuration configuration) {
		super(configuration);
	}

	/**
	 * 构造函数
	 *
	 * @param clientConnectionManager
	 * 		原生连接管理器
	 */
	public Apache5ClientConnectionManager(HttpClientConnectionManager clientConnectionManager) {
		super(clientConnectionManager);
	}

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		配置
	 * @param clientConnectionManager
	 * 		原生连接管理器
	 */
	public Apache5ClientConnectionManager(Configuration configuration,
										  HttpClientConnectionManager clientConnectionManager) {
		super(configuration, clientConnectionManager);
	}

	/**
	 * 创建驱动默认连接管理器
	 *
	 * @return 连接管理器
	 */
	@Override
	protected HttpClientConnectionManager createDefaultClientConnectionManager() {
		final PoolingHttpClientConnectionManager connectionManager =
				getConfiguration().getConnectionTimeToLive() != null ? new PoolingHttpClientConnectionManager(
						getDefaultRegistry(), PoolConcurrencyPolicy.STRICT,
						Timeout.ofMilliseconds(getConfiguration().getConnectionTimeToLive()),
						null) : new PoolingHttpClientConnectionManager(getDefaultRegistry());

		// 最大连接数
		propertyMapper.from(getConfiguration().getMaxConnections()).to(connectionManager::setMaxTotal);
		// 每个路由的最大连接数
		propertyMapper.from(getConfiguration().getMaxPerRoute()).to(connectionManager::setDefaultMaxPerRoute);
		// 空闲连接存活时长
		propertyMapper.from(getConfiguration().getIdleConnectionTime()).as(Timeout::ofMilliseconds)
				.to(connectionManager::closeIdle);

		connectionManager.setDefaultConnectionConfig(createDefaultConnectionConfig());

		return connectionManager;
	}

	protected ConnectionConfig createDefaultConnectionConfig() {
		final ConnectionConfig.Builder connectionConfigBuilder = ConnectionConfig.custom();

		propertyMapper.from(getConfiguration().getConnectTimeout()).as(Timeout::ofMilliseconds)
				.to(connectionConfigBuilder::setConnectTimeout);
		propertyMapper.from(getConfiguration().getReadTimeout()).as(Timeout::ofMilliseconds)
				.to(connectionConfigBuilder::setSocketTimeout);
		propertyMapper.from(getConfiguration().getConnectionTimeToLive()).as(Timeout::ofMilliseconds)
				.to(connectionConfigBuilder::setTimeToLive);

		return connectionConfigBuilder.build();
	}

	private static Registry<ConnectionSocketFactory> getDefaultRegistry() {
		return RegistryBuilder.<ConnectionSocketFactory>create()
				.register(URIScheme.HTTP.id, PlainConnectionSocketFactory.getSocketFactory())
				.register(URIScheme.HTTPS.id, SSLConnectionSocketFactory.getSocketFactory())
				.build();
	}

}
