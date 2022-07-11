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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.conn;

import com.buession.httpclient.core.Configuration;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.concurrent.TimeUnit;

/**
 * Apache HttpComponents 连接管理器
 *
 * @author Yong.Teng
 */
public class ApacheClientConnectionManager extends AbstractConnectionManager<HttpClientConnectionManager> {

	/**
	 * 构造函数，创建驱动默认连接管理器
	 */
	public ApacheClientConnectionManager(){
		super();
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param configuration
	 * 		连接对象
	 */
	public ApacheClientConnectionManager(Configuration configuration){
		super(configuration);
	}

	/**
	 * 构造函数
	 *
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 */
	public ApacheClientConnectionManager(HttpClientConnectionManager clientConnectionManager){
		super(clientConnectionManager);
	}

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		连接对象
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 */
	public ApacheClientConnectionManager(Configuration configuration,
										 HttpClientConnectionManager clientConnectionManager){
		super(configuration, clientConnectionManager);
	}

	/**
	 * 创建驱动默认连接管理器
	 *
	 * @return 连接管理器
	 */
	@Override
	protected HttpClientConnectionManager createDefaultClientConnectionManager(){
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

		//最大连接数
		connectionManager.setMaxTotal(getConfiguration().getMaxConnections());
		//并发数
		connectionManager.setDefaultMaxPerRoute(getConfiguration().getMaxPerRoute());
		// 空闲连接存活时长
		connectionManager.closeIdleConnections(getConfiguration().getIdleConnectionTime(), TimeUnit.MILLISECONDS);

		return connectionManager;
	}

}
