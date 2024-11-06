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
package com.buession.httpclient.conn;

import com.buession.httpclient.core.Configuration;
import okhttp3.nio.NioHttpClientConnectionManager;

/**
 * Apache HttpComponents 异步连接管理器
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class OkHttpNioClientConnectionManager extends OkHttpBaseClientConnectionManager<NioHttpClientConnectionManager>
		implements com.buession.httpclient.okhttp.OkHttpNioClientConnectionManager {

	/**
	 * 构造函数，创建驱动默认连接管理器
	 */
	public OkHttpNioClientConnectionManager() {
		super();
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param configuration
	 * 		配置
	 */
	public OkHttpNioClientConnectionManager(Configuration configuration) {
		super(configuration);
	}

	/**
	 * 构造函数
	 *
	 * @param clientConnectionManager
	 * 		原生连接管理器
	 */
	public OkHttpNioClientConnectionManager(NioHttpClientConnectionManager clientConnectionManager) {
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
	public OkHttpNioClientConnectionManager(Configuration configuration,
											NioHttpClientConnectionManager clientConnectionManager) {
		super(configuration, clientConnectionManager);
	}

	/**
	 * 创建驱动默认连接管理器
	 *
	 * @return 连接管理器
	 */
	@Override
	protected NioHttpClientConnectionManager createDefaultClientConnectionManager() {
		final NioHttpClientConnectionManager connectionManager = new NioHttpClientConnectionManager();

		// 最大连接数
		propertyMapper.from(getConfiguration().getMaxConnections()).to(connectionManager::setMaxConnections);
		// 同时请求相同主机的请求数量最大值
		propertyMapper.from(getConfiguration().getMaxPerRoute()).to(connectionManager::setMaxRequestsPerHost);
		// 默认的最大并发请求数量
		propertyMapper.from(getConfiguration().getMaxRequests()).to(connectionManager::setMaxRequests);
		// 空闲连接存活时长
		propertyMapper.from(getConfiguration().getIdleConnectionTime()).to(connectionManager::setIdleConnectionTime);

		return connectionManager;
	}

}
