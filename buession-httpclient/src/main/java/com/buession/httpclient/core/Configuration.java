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
package com.buession.httpclient.core;

import com.buession.net.ssl.SslConfiguration;

import java.util.StringJoiner;

/**
 * HTTP 连接配置
 *
 * @author Yong.Teng
 */
public class Configuration {

	/**
	 * @since 2.3.0
	 */
	private Boolean connectionManagerShared;

	/**
	 * 连接失败是否重试
	 *
	 * @since 2.3.0
	 */
	private Boolean retryOnConnectionFailure;

	/**
	 * 最大连接数
	 */
	private int maxConnections = 5000;

	/**
	 * 每个路由的最大连接数
	 */
	private int maxPerRoute = 500;

	/**
	 * 空闲连接存活时长，单位：毫秒
	 */
	private int idleConnectionTime = 60 * 1000;

	/**
	 * 连接超时时间，单位：毫秒
	 */
	private int connectTimeout = 3000;

	/**
	 * 从连接池获取连接的超时时间，单位：毫秒
	 */
	private int connectionRequestTimeout = 5000;

	/**
	 * 读取超时时间，单位：毫秒
	 */
	private int readTimeout = 5000;

	/**
	 * 写超时时间，单位：毫秒；小于等于 0 时，使用原生库默认写超时时间
	 */
	private int writeTimeout = -1;

	/**
	 * 是否允许重定向
	 */
	private Boolean allowRedirects;

	/**
	 * 是否应拒绝相对重定向
	 */
	private Boolean relativeRedirectsAllowed;

	/**
	 * 是否允许循环重定向
	 */
	private Boolean circularRedirectsAllowed;

	/**
	 * 最大允许重定向次数
	 */
	private Integer maxRedirects;

	/**
	 * 是否开启 Http Basic 认证
	 */
	private boolean authenticationEnabled;

	/**
	 * 是否启用内容压缩
	 */
	private boolean contentCompressionEnabled;

	/**
	 * 是否标准化 URI
	 */
	private boolean normalizeUri;

	/**
	 * SSL 配置
	 *
	 * @since 2.3.0
	 */
	private SslConfiguration sslConfiguration;

	/**
	 * 返回链接管理器是否共享
	 *
	 * @return True / False
	 *
	 * @since 2.3.0
	 */
	public Boolean getConnectionManagerShared() {
		return connectionManagerShared;
	}

	/**
	 * 设置链接管理器是否共享
	 *
	 * @param connectionManagerShared
	 * 		链接管理器是否共享
	 *
	 * @since 2.3.0
	 */
	public void setConnectionManagerShared(Boolean connectionManagerShared) {
		this.connectionManagerShared = connectionManagerShared;
	}

	/**
	 * 返回连接失败是否重试
	 *
	 * @return 连接失败是否重试
	 *
	 * @since 2.3.0
	 */
	public Boolean getRetryOnConnectionFailure() {
		return retryOnConnectionFailure;
	}

	/**
	 * 设置连接失败是否重试
	 *
	 * @param retryOnConnectionFailure
	 * 		连接失败是否重试
	 *
	 * @since 2.3.0
	 */
	public void setRetryOnConnectionFailure(Boolean retryOnConnectionFailure) {
		this.retryOnConnectionFailure = retryOnConnectionFailure;
	}

	/**
	 * 获取最大连接数
	 *
	 * @return 最大连接数
	 */
	public int getMaxConnections() {
		return maxConnections;
	}

	/**
	 * 设置最大连接数
	 *
	 * @param maxConnections
	 * 		最大连接数
	 */
	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	/**
	 * 获取每个路由的最大连接数
	 *
	 * @return 每个路由的最大连接数
	 */
	public int getMaxPerRoute() {
		return maxPerRoute;
	}

	/**
	 * 设置每个路由的最大连接数
	 *
	 * @param maxPerRoute
	 * 		每个路由的最大连接数
	 */
	public void setMaxPerRoute(int maxPerRoute) {
		this.maxPerRoute = maxPerRoute;
	}

	/**
	 * 获取空闲连接存活时长，单位：毫秒
	 *
	 * @return 空闲连接存活时长
	 */
	public int getIdleConnectionTime() {
		return idleConnectionTime;
	}

	/**
	 * 设置空闲连接存活时长
	 *
	 * @param idleConnectionTime
	 * 		空闲连接存活时长，单位：毫秒
	 */
	public void setIdleConnectionTime(int idleConnectionTime) {
		this.idleConnectionTime = idleConnectionTime;
	}

	/**
	 * 获取连接超时时间，单位：毫秒
	 *
	 * @return 连接超时时间
	 */
	public int getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * 设置连接超时时间
	 *
	 * @param connectTimeout
	 * 		连接超时时间，单位：毫秒
	 */
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * 获取从连接池获取连接的超时时间，单位：毫秒
	 *
	 * @return 从连接池获取连接的超时时间
	 */
	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	/**
	 * 设置从连接池获取连接的超时时间
	 *
	 * @param connectionRequestTimeout
	 * 		从连接池获取连接的超时时间，单位：毫秒
	 */
	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	/**
	 * 获取读取超时时间，单位：毫秒
	 *
	 * @return 读取超时时间
	 */
	public int getReadTimeout() {
		return readTimeout;
	}

	/**
	 * 设置读取超时时间，单位：毫秒
	 *
	 * @param readTimeout
	 * 		读取超时时间，单位：毫秒
	 */
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	/**
	 * 返回写超时时间，单位：毫秒；小于等于 0 时，使用原生库默认写超时时间
	 *
	 * @return 写超时时间，单位：毫秒
	 *
	 * @since 2.3.0
	 */
	public int getWriteTimeout() {
		return writeTimeout;
	}

	/**
	 * 设置写超时时间，单位：毫秒；小于等于 0 时，使用原生库默认写超时时间
	 *
	 * @param writeTimeout
	 * 		写超时时间，单位：毫秒
	 *
	 * @since 2.3.0
	 */
	public void setWriteTimeout(int writeTimeout) {
		this.writeTimeout = writeTimeout;
	}

	/**
	 * 获取是否允许重定向
	 *
	 * @return 是否允许重定向
	 */
	public Boolean isAllowRedirects() {
		return getAllowRedirects();
	}

	/**
	 * 获取是否允许重定向
	 *
	 * @return 是否允许重定向
	 */
	public Boolean getAllowRedirects() {
		return allowRedirects;
	}

	/**
	 * 设置是否允许重定向
	 *
	 * @param allowRedirects
	 * 		是否允许重定向
	 */
	public void setAllowRedirects(Boolean allowRedirects) {
		this.allowRedirects = allowRedirects;
	}

	/**
	 * 返回是否应拒绝相对重定向
	 *
	 * @return 是否应拒绝相对重定向
	 */
	public Boolean isRelativeRedirectsAllowed() {
		return getRelativeRedirectsAllowed();
	}

	/**
	 * 返回是否应拒绝相对重定向
	 *
	 * @return 是否应拒绝相对重定向
	 */
	public Boolean getRelativeRedirectsAllowed() {
		return relativeRedirectsAllowed;
	}

	/**
	 * 设置是否应拒绝相对重定向
	 *
	 * @param relativeRedirectsAllowed
	 * 		是否应拒绝相对重定向
	 */
	public void setRelativeRedirectsAllowed(Boolean relativeRedirectsAllowed) {
		this.relativeRedirectsAllowed = relativeRedirectsAllowed;
	}

	/**
	 * 返回是否允许循环重定向
	 *
	 * @return 是否允许循环重定向
	 */
	public Boolean isCircularRedirectsAllowed() {
		return getCircularRedirectsAllowed();
	}

	/**
	 * 返回是否允许循环重定向
	 *
	 * @return 是否允许循环重定向
	 */
	public Boolean getCircularRedirectsAllowed() {
		return circularRedirectsAllowed;
	}

	/**
	 * 设置是否允许循环重定向
	 *
	 * @param circularRedirectsAllowed
	 * 		是否允许循环重定向
	 */
	public void setCircularRedirectsAllowed(Boolean circularRedirectsAllowed) {
		this.circularRedirectsAllowed = circularRedirectsAllowed;
	}

	/**
	 * 获取最大允许重定向次数
	 *
	 * @return 最大允许重定向次数
	 */
	public Integer getMaxRedirects() {
		return maxRedirects;
	}

	/**
	 * 设置最大允许重定向次数
	 *
	 * @param maxRedirects
	 * 		最大允许重定向次数
	 */
	public void setMaxRedirects(Integer maxRedirects) {
		this.maxRedirects = maxRedirects;
	}

	/**
	 * 获取是否开启 Http Basic 认证
	 *
	 * @return 是否开启 Http Basic 认证
	 */
	public boolean isAuthenticationEnabled() {
		return getAuthenticationEnabled();
	}

	/**
	 * 获取是否开启 Http Basic 认证
	 *
	 * @return 是否开启 Http Basic 认证
	 */
	public boolean getAuthenticationEnabled() {
		return authenticationEnabled;
	}

	/**
	 * 设置是否开启 Http Basic 认证
	 *
	 * @param authenticationEnabled
	 * 		是否开启 Http Basic 认证
	 */
	public void setAuthenticationEnabled(boolean authenticationEnabled) {
		this.authenticationEnabled = authenticationEnabled;
	}

	/**
	 * 获取是否启用内容压缩
	 *
	 * @return 是否启用内容压缩
	 */
	public boolean isContentCompressionEnabled() {
		return getContentCompressionEnabled();
	}

	/**
	 * 获取是否启用内容压缩
	 *
	 * @return 是否启用内容压缩
	 */
	public boolean getContentCompressionEnabled() {
		return contentCompressionEnabled;
	}

	/**
	 * 设置是否启用内容压缩
	 *
	 * @param contentCompressionEnabled
	 * 		是否启用内容压缩
	 */
	public void setContentCompressionEnabled(boolean contentCompressionEnabled) {
		this.contentCompressionEnabled = contentCompressionEnabled;
	}

	/**
	 * 返回是否标准化 URI
	 *
	 * @return 是否标准化 URI
	 */
	public boolean isNormalizeUri() {
		return getNormalizeUri();
	}

	/**
	 * 返回是否标准化 URI
	 *
	 * @return 是否标准化 URI
	 */
	public boolean getNormalizeUri() {
		return normalizeUri;
	}

	/**
	 * 设置是否标准化 URI
	 *
	 * @param normalizeUri
	 * 		是否标准化 URI
	 */
	public void setNormalizeUri(boolean normalizeUri) {
		this.normalizeUri = normalizeUri;
	}

	/**
	 * 返回 SSL 配置
	 *
	 * @return SSL 配置
	 */
	public SslConfiguration getSslConfiguration() {
		return sslConfiguration;
	}

	/**
	 * 设置 SSL 配置
	 *
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public void setSslConfiguration(SslConfiguration sslConfiguration) {
		this.sslConfiguration = sslConfiguration;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ")
				.add("connectionManagerShared: " + connectionManagerShared)
				.add("retryOnConnectionFailure: " + retryOnConnectionFailure)
				.add("maxConnections: " + maxConnections)
				.add("maxPerRoute: " + maxPerRoute)
				.add("idleConnectionTime: " + idleConnectionTime)
				.add("connectTimeout: " + connectTimeout)
				.add("connectionRequestTimeout: " + connectionRequestTimeout)
				.add("readTimeout: " + readTimeout)
				.add("writeTimeout: " + writeTimeout)
				.add("allowRedirects: " + allowRedirects)
				.add("relativeRedirectsAllowed: " + relativeRedirectsAllowed)
				.add("circularRedirectsAllowed: " + circularRedirectsAllowed)
				.add("maxRedirects: " + maxRedirects)
				.add("authenticationEnabled: " + authenticationEnabled)
				.add("contentCompressionEnabled: " + contentCompressionEnabled)
				.add("normalizeUri: " + normalizeUri)
				.add("sslConfiguration: " + sslConfiguration)
				.toString();
	}

}
