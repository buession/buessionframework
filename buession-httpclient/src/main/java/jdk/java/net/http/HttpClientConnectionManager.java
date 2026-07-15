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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package jdk.java.net.http;

import java.io.Closeable;
import java.io.IOException;

/**
 * JDK {@link java.net.http.HttpClient} 连接管理器
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class HttpClientConnectionManager implements Closeable {

	/**
	 * 连接池中最大空闲连接数
	 */
	private int maxIdleConnections = 5;

	/**
	 * 闲连接存活时长，单位：毫秒
	 */
	private int idleConnectionTime = 5 * 60 * 1000;

	/**
	 * 默认的最大并发请求数量
	 */
	private int maxRequests;

	/**
	 * 同时请求相同主机的请求数量最大值
	 */
	private int maxRequestsPerHost;

	/**
	 * 构造函数
	 */
	public HttpClientConnectionManager() {
	}

	/**
	 * 返回连接池中最大空闲连接数
	 *
	 * @return 连接池中最大空闲连接数
	 */
	public int getMaxIdleConnections() {
		return maxIdleConnections;
	}

	/**
	 * 设置连接池中最大空闲连接数
	 *
	 * @param maxIdleConnections
	 * 		连接池中最大空闲连接数
	 */
	public void setMaxIdleConnections(int maxIdleConnections) {
		this.maxIdleConnections = maxIdleConnections;
	}

	/**
	 * 返回空闲连接存活时长，单位：毫秒
	 *
	 * @return 空闲连接存活时长
	 */
	public int getIdleConnectionTime() {
		return idleConnectionTime;
	}

	/**
	 * 设置闲连接存活时长，单位：毫秒
	 *
	 * @param idleConnectionTime
	 * 		闲连接存活时长
	 */
	public void setIdleConnectionTime(int idleConnectionTime) {
		this.idleConnectionTime = idleConnectionTime;
	}

	/**
	 * 返回默认的最大并发请求数量
	 *
	 * @return 默认的最大并发请求数量
	 */
	public int getMaxRequests() {
		return maxRequests;
	}

	/**
	 * 设置默认的最大并发请求数量
	 *
	 * @param maxRequests
	 * 		默认的最大并发请求数量
	 */
	public void setMaxRequests(int maxRequests) {
		this.maxRequests = maxRequests;
	}

	/**
	 * 返回同时请求相同主机的请求数量最大值
	 *
	 * @return 同时请求相同主机的请求数量最大值
	 */
	public int getMaxRequestsPerHost() {
		return maxRequestsPerHost;
	}

	/**
	 * 设置同时请求相同主机的请求数量最大值
	 *
	 * @param maxRequestsPerHost
	 * 		同时请求相同主机的请求数量最大值
	 *
	 * @since 2.3.0
	 */
	public void setMaxRequestsPerHost(int maxRequestsPerHost) {
		this.maxRequestsPerHost = maxRequestsPerHost;
	}

	@Override
	public void close() throws IOException {
	}

}
