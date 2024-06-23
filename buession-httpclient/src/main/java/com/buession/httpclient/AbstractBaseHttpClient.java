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
package com.buession.httpclient;

import com.buession.httpclient.conn.IConnectionManager;
import com.buession.httpclient.core.ProtocolVersion;

/**
 * HttpClient 基类
 *
 * @param <CM>
 * 		连接管理器类型
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
abstract class AbstractBaseHttpClient<CM extends IConnectionManager> implements IBaseHttpClient, IHttpClient {

	/**
	 * 连接管理器
	 */
	private CM connectionManager;

	/**
	 * HTTP 协议版本
	 */
	private ProtocolVersion httpVersion;

	/**
	 * 构造函数
	 */
	public AbstractBaseHttpClient() {
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 */
	public AbstractBaseHttpClient(CM connectionManager) {
		this.connectionManager = connectionManager;
	}

	public CM getConnectionManager() {
		return connectionManager;
	}
	
	public void setConnectionManager(CM connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public ProtocolVersion getHttpVersion() {
		return httpVersion;
	}

	@Override
	public void setHttpVersion(ProtocolVersion httpVersion) {
		this.httpVersion = httpVersion;
	}

}
