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
package com.buession.httpclient.core;

import com.buession.net.ssl.SslConfiguration;

import java.net.InetAddress;
import java.util.Collection;
import java.util.StringJoiner;

/**
 * HTTP 连接配置
 *
 * @author Yong.Teng
 */
public class Configuration {

	/**
	 * 是否在多个实例之间共享连接管理器
	 *
	 * @since 3.0.0
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
	private Integer maxConnections = 5000;

	/**
	 * 每个路由的最大连接数
	 */
	private Integer maxPerRoute = 500;

	/**
	 * 最大并发请求数量
	 *
	 * @since 2.3.2
	 */
	private Integer maxRequests;

	/**
	 * 空闲连接存活时长，单位：毫秒
	 */
	private Integer idleConnectionTime = 60 * 1000;

	/**
	 * 连接超时时间，单位：毫秒
	 */
	private Integer connectTimeout = 3000;

	/**
	 * 从连接池获取连接的超时时间，单位：毫秒
	 */
	private Integer connectionRequestTimeout = 5000;

	/**
	 * The maximum time to live for persistent connections.
	 *
	 * @since 3.0.0
	 */
	private Integer connectionTimeToLive = -1;

	/**
	 * 读取超时时间，单位：毫秒
	 */
	private Integer readTimeout = 5000;

	/**
	 * 写超时时间，单位：毫秒
	 */
	private Integer writeTimeout = -1;

	/**
	 * Determines whether the 'Expect: 100-Continue' handshake is enabled for entity enclosing methods.
	 *
	 * @since 3.0.0
	 */
	private Boolean expectContinueEnabled;

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
	 * Determines whether request cancellation, such as through {@code Future#cancel(boolean)}, should kill the
	 * underlying connection. If this option is set to false, the client will attempt to preserve the underlying
	 * connection by allowing the request to complete in the background, discarding the response.
	 *
	 * @since 3.0.0
	 */
	private Boolean hardCancellationEnabled;

	/**
	 * 是否开启 Http Basic 认证
	 */
	private Boolean authenticationEnabled;

	/**
	 * Determines the order of preference for supported authentication schemes by their names when authenticating with the target host.
	 *
	 * @since 3.0.0
	 */
	private Collection<String> targetPreferredAuthSchemes;

	/**
	 * Determines the order of preference for supported authentication schemes by their names when authenticating with the proxy host.
	 *
	 * @since 3.0.0
	 */
	private Collection<String> proxyPreferredAuthSchemes;

	/**
	 * 是否启用内容压缩
	 */
	private Boolean contentCompressionEnabled;

	/**
	 * 是否标准化 URI
	 */
	private Boolean normalizeUri;

	/**
	 * Determines the name of the cookie specification to be used for HTTP state management.
	 *
	 * @since 3.0.0
	 */
	private String cookieSpec;

	/**
	 * SSL 配置
	 *
	 * @since 2.3.0
	 */
	private SslConfiguration sslConfiguration;

	/**
	 * 代理配置
	 *
	 * @since 3.0.0
	 */
	private Proxy proxy;

	/**
	 * 返回是否在多个实例之间共享连接管理器
	 *
	 * @return True / False
	 *
	 * @since 3.0.0
	 */
	public Boolean isConnectionManagerShared() {
		return getConnectionManagerShared();
	}

	/**
	 * 返回是否在多个实例之间共享连接管理器
	 *
	 * @return True / False
	 *
	 * @since 3.0.0
	 */
	public Boolean getConnectionManagerShared() {
		return connectionManagerShared;
	}

	/**
	 * 设置是否在多个实例之间共享连接管理器
	 *
	 * @param connectionManagerShared
	 * 		是否在多个实例之间共享连接管理器
	 *
	 * @since 3.0.0
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
	public Boolean isRetryOnConnectionFailure() {
		return getRetryOnConnectionFailure();
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
	public Integer getMaxConnections() {
		return maxConnections;
	}

	/**
	 * 设置最大连接数
	 *
	 * @param maxConnections
	 * 		最大连接数
	 */
	public void setMaxConnections(Integer maxConnections) {
		this.maxConnections = maxConnections;
	}

	/**
	 * 获取每个路由的最大连接数
	 *
	 * @return 每个路由的最大连接数
	 */
	public Integer getMaxPerRoute() {
		return maxPerRoute;
	}

	/**
	 * 设置每个路由的最大连接数
	 *
	 * @param maxPerRoute
	 * 		每个路由的最大连接数
	 */
	public void setMaxPerRoute(Integer maxPerRoute) {
		this.maxPerRoute = maxPerRoute;
	}

	/**
	 * 返回最大并发请求数量
	 *
	 * @return 最大并发请求数量
	 */
	public Integer getMaxRequests() {
		return maxRequests;
	}

	/**
	 * 设置最大并发请求数量
	 *
	 * @param maxRequests
	 * 		最大并发请求数量
	 */
	public void setMaxRequests(Integer maxRequests) {
		this.maxRequests = maxRequests;
	}

	/**
	 * 获取空闲连接存活时长，单位：毫秒
	 *
	 * @return 空闲连接存活时长
	 */
	public Integer getIdleConnectionTime() {
		return idleConnectionTime;
	}

	/**
	 * 设置空闲连接存活时长
	 *
	 * @param idleConnectionTime
	 * 		空闲连接存活时长，单位：毫秒
	 */
	public void setIdleConnectionTime(Integer idleConnectionTime) {
		this.idleConnectionTime = idleConnectionTime;
	}

	/**
	 * 获取连接超时时间，单位：毫秒
	 *
	 * @return 连接超时时间
	 */
	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * 设置连接超时时间
	 *
	 * @param connectTimeout
	 * 		连接超时时间，单位：毫秒
	 */
	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * 获取从连接池获取连接的超时时间，单位：毫秒
	 *
	 * @return 从连接池获取连接的超时时间
	 */
	public Integer getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	/**
	 * 设置从连接池获取连接的超时时间
	 *
	 * @param connectionRequestTimeout
	 * 		从连接池获取连接的超时时间，单位：毫秒
	 */
	public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	/**
	 * Return maximum time to live for persistent connections.
	 *
	 * @return The maximum time to live for persistent connections.
	 *
	 * @since 3.0.0
	 */
	public Integer getConnectionTimeToLive() {
		return connectionTimeToLive;
	}

	/**
	 * Sets maximum time to live for persistent connections.
	 *
	 * @param connectionTimeToLive
	 * 		The maximum time to live for persistent connections.
	 *
	 * @since 3.0.0
	 */
	public void setConnectionTimeToLive(Integer connectionTimeToLive) {
		this.connectionTimeToLive = connectionTimeToLive;
	}

	/**
	 * 获取读取超时时间，单位：毫秒
	 *
	 * @return 读取超时时间
	 */
	public Integer getReadTimeout() {
		return readTimeout;
	}

	/**
	 * 设置读取超时时间，单位：毫秒
	 *
	 * @param readTimeout
	 * 		读取超时时间，单位：毫秒
	 */
	public void setReadTimeout(Integer readTimeout) {
		this.readTimeout = readTimeout;
	}

	/**
	 * 返回写超时时间，单位：毫秒；小于等于 0 时，使用原生库默认写超时时间
	 *
	 * @return 写超时时间，单位：毫秒
	 *
	 * @since 2.3.0
	 */
	public Integer getWriteTimeout() {
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
	public void setWriteTimeout(Integer writeTimeout) {
		this.writeTimeout = writeTimeout;
	}

	/**
	 * Return determines whether the 'Expect: 100-Continue' handshake is enabled for entity enclosing methods.
	 *
	 * @return true / false
	 *
	 * @since 3.0.0
	 */
	public Boolean isExpectContinueEnabled() {
		return getExpectContinueEnabled();
	}

	/**
	 * Return determines whether the 'Expect: 100-Continue' handshake is enabled for entity enclosing methods.
	 *
	 * @return true / false
	 *
	 * @since 3.0.0
	 */
	public Boolean getExpectContinueEnabled() {
		return expectContinueEnabled;
	}

	/**
	 * Sets determines whether the 'Expect: 100-Continue' handshake is enabled for entity enclosing methods.
	 *
	 * @param expectContinueEnabled
	 * 		true / false
	 *
	 * @since 3.0.0
	 */
	public void setExpectContinueEnabled(Boolean expectContinueEnabled) {
		this.expectContinueEnabled = expectContinueEnabled;
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
	 * Return determines whether request cancellation, such as through {@code Future#cancel(boolean)}, should kill the
	 * underlying connection. If this option is set to false, the client will attempt to preserve the underlying
	 * connection by allowing the request to complete in the background, discarding the response.
	 *
	 * @return true / false
	 *
	 * @since 3.0.0
	 */
	public Boolean isHardCancellationEnabled() {
		return getHardCancellationEnabled();
	}

	/**
	 * Return determines whether request cancellation, such as through {@code Future#cancel(boolean)}, should kill the
	 * underlying connection. If this option is set to false, the client will attempt to preserve the underlying
	 * connection by allowing the request to complete in the background, discarding the response.
	 *
	 * @return true / false
	 *
	 * @since 3.0.0
	 */
	public Boolean getHardCancellationEnabled() {
		return hardCancellationEnabled;
	}

	/**
	 * Sets determines whether request cancellation, such as through {@code Future#cancel(boolean)}, should kill the
	 * underlying connection. If this option is set to false, the client will attempt to preserve the underlying
	 * connection by allowing the request to complete in the background, discarding the response.
	 *
	 * @param hardCancellationEnabled
	 * 		Enabled hard cancellation
	 *
	 * @since 3.0.0
	 */
	public void setHardCancellationEnabled(Boolean hardCancellationEnabled) {
		this.hardCancellationEnabled = hardCancellationEnabled;
	}

	/**
	 * 获取是否开启 Http Basic 认证
	 *
	 * @return 是否开启 Http Basic 认证
	 */
	public Boolean isAuthenticationEnabled() {
		return getAuthenticationEnabled();
	}

	/**
	 * 获取是否开启 Http Basic 认证
	 *
	 * @return 是否开启 Http Basic 认证
	 */
	public Boolean getAuthenticationEnabled() {
		return authenticationEnabled;
	}

	/**
	 * 设置是否开启 Http Basic 认证
	 *
	 * @param authenticationEnabled
	 * 		是否开启 Http Basic 认证
	 */
	public void setAuthenticationEnabled(Boolean authenticationEnabled) {
		this.authenticationEnabled = authenticationEnabled;
	}

	/**
	 * Return determines the order of preference for supported authentication schemes by their names when
	 * authenticating with the target host.
	 *
	 * @return Determines the order of preference for supported authentication schemes by their names when authenticating with the target host.
	 *
	 * @since 3.0.0
	 */
	public Collection<String> getTargetPreferredAuthSchemes() {
		return targetPreferredAuthSchemes;
	}

	/**
	 * Sets determines the order of preference for supported authentication schemes by their names when
	 * authenticating with the target host.
	 *
	 * @param targetPreferredAuthSchemes
	 * 		Determines the order of preference for supported authentication schemes by their names when authenticating with the target host.
	 *
	 * @since 3.0.0
	 */
	public void setTargetPreferredAuthSchemes(Collection<String> targetPreferredAuthSchemes) {
		this.targetPreferredAuthSchemes = targetPreferredAuthSchemes;
	}

	/**
	 * Return determines the order of preference for supported authentication schemes by their names when
	 * authenticating with the proxy host.
	 *
	 * @return Determines the order of preference for supported authentication schemes by their names when authenticating with the proxy host.
	 *
	 * @since 3.0.0
	 */
	public Collection<String> getProxyPreferredAuthSchemes() {
		return proxyPreferredAuthSchemes;
	}

	/**
	 * Sets determines the order of preference for supported authentication schemes by their names when
	 * authenticating with the proxy host.
	 *
	 * @param proxyPreferredAuthSchemes
	 * 		Determines the order of preference for supported authentication schemes by their names when authenticating with the proxy host.
	 *
	 * @since 3.0.0
	 */
	public void setProxyPreferredAuthSchemes(Collection<String> proxyPreferredAuthSchemes) {
		this.proxyPreferredAuthSchemes = proxyPreferredAuthSchemes;
	}

	/**
	 * 获取是否启用内容压缩
	 *
	 * @return 是否启用内容压缩
	 */
	public Boolean isContentCompressionEnabled() {
		return getContentCompressionEnabled();
	}

	/**
	 * 获取是否启用内容压缩
	 *
	 * @return 是否启用内容压缩
	 */
	public Boolean getContentCompressionEnabled() {
		return contentCompressionEnabled;
	}

	/**
	 * 设置是否启用内容压缩
	 *
	 * @param contentCompressionEnabled
	 * 		是否启用内容压缩
	 */
	public void setContentCompressionEnabled(Boolean contentCompressionEnabled) {
		this.contentCompressionEnabled = contentCompressionEnabled;
	}

	/**
	 * 返回是否标准化 URI
	 *
	 * @return 是否标准化 URI
	 */
	public Boolean isNormalizeUri() {
		return getNormalizeUri();
	}

	/**
	 * 返回是否标准化 URI
	 *
	 * @return 是否标准化 URI
	 */
	public Boolean getNormalizeUri() {
		return normalizeUri;
	}

	/**
	 * 设置是否标准化 URI
	 *
	 * @param normalizeUri
	 * 		是否标准化 URI
	 */
	public void setNormalizeUri(Boolean normalizeUri) {
		this.normalizeUri = normalizeUri;
	}

	/**
	 * Return determines the name of the cookie specification to be used for HTTP state management.
	 *
	 * @return Determines the name of the cookie specification to be used for HTTP state management.
	 *
	 * @since 3.0.0
	 */
	public String getCookieSpec() {
		return cookieSpec;
	}

	/**
	 * Sets determines the name of the cookie specification to be used for HTTP state management.
	 *
	 * @param cookieSpec
	 * 		Determines the name of the cookie specification to be used for HTTP state management.
	 *
	 * @since 3.0.0
	 */
	public void setCookieSpec(String cookieSpec) {
		this.cookieSpec = cookieSpec;
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

	/**
	 * 返回代理配置
	 *
	 * @return 代理配置
	 *
	 * @since 3.0.0
	 */
	public Proxy getProxy() {
		return proxy;
	}

	/**
	 * 设置代理配置
	 *
	 * @param proxy
	 * 		代理配置
	 *
	 * @since 3.0.0
	 */
	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ")
				.add("connectionManagerShared: " + connectionManagerShared)
				.add("retryOnConnectionFailure: " + retryOnConnectionFailure)
				.add("maxConnections: " + maxConnections)
				.add("maxPerRoute: " + maxPerRoute)
				.add("maxRequests:" + maxRequests)
				.add("idleConnectionTime: " + idleConnectionTime)
				.add("connectTimeout: " + connectTimeout)
				.add("connectionRequestTimeout: " + connectionRequestTimeout)
				.add("connectionTimeToLive: " + connectionTimeToLive)
				.add("readTimeout: " + readTimeout)
				.add("writeTimeout: " + writeTimeout)
				.add("expectContinueEnabled: " + expectContinueEnabled)
				.add("allowRedirects: " + allowRedirects)
				.add("relativeRedirectsAllowed: " + relativeRedirectsAllowed)
				.add("circularRedirectsAllowed: " + circularRedirectsAllowed)
				.add("maxRedirects: " + maxRedirects)
				.add("hardCancellationEnabled: " + hardCancellationEnabled)
				.add("authenticationEnabled: " + authenticationEnabled)
				.add("targetPreferredAuthSchemes: " + targetPreferredAuthSchemes)
				.add("proxyPreferredAuthSchemes: " + proxyPreferredAuthSchemes)
				.add("contentCompressionEnabled: " + contentCompressionEnabled)
				.add("normalizeUri: " + normalizeUri)
				.add("cookieSpec: " + cookieSpec)
				.add("sslConfiguration: " + sslConfiguration)
				.add("proxy: " + proxy)
				.toString();
	}

	/**
	 * 代理配置
	 *
	 * @author yong.teng
	 * @since 3.0.0
	 */
	public final static class Proxy {

		/**
		 * The name of the scheme.
		 */
		private Scheme scheme;

		/**
		 * The inet address.
		 */
		private InetAddress address;

		/**
		 * The hostname (IP or DNS name).
		 */
		private String hostname;

		/**
		 * The port number. {@code -1} indicates the scheme default port.
		 */
		private int port;

		/**
		 * Return the name of the scheme.
		 *
		 * @return The name of the scheme.
		 */
		public Scheme getScheme() {
			return scheme;
		}

		/**
		 * Sets the name of the scheme.
		 *
		 * @param scheme
		 * 		The name of the scheme.
		 */
		public void setScheme(Scheme scheme) {
			this.scheme = scheme;
		}

		/**
		 * Return the inet address.
		 *
		 * @return The inet address.
		 */
		public InetAddress getAddress() {
			return address;
		}

		/**
		 * Sets the inet address.
		 *
		 * @param address
		 * 		The inet address.
		 */
		public void setAddress(InetAddress address) {
			this.address = address;
		}

		/**
		 * Return the hostname (IP or DNS name).
		 *
		 * @return The hostname (IP or DNS name).
		 */
		public String getHostname() {
			return hostname;
		}

		/**
		 * Sets the hostname (IP or DNS name).
		 *
		 * @param hostname
		 * 		The hostname (IP or DNS name).
		 */
		public void setHostname(String hostname) {
			this.hostname = hostname;
		}

		/**
		 * Return the port number. {@code -1} indicates the scheme default port.
		 *
		 * @return The port number.
		 */
		public int getPort() {
			return port;
		}

		/**
		 * Sets the port number. {@code -1} indicates the scheme default port.
		 *
		 * @param port
		 * 		The port number.
		 */
		public void setPort(int port) {
			this.port = port;
		}

		@Override
		public String toString() {
			return new StringJoiner(", ")
					.add("scheme: " + scheme)
					.add("address: " + address)
					.add("hostname: " + hostname)
					.add("port: " + port)
					.toString();
		}

		public enum Scheme {

			HTTP,

			HTTPS

		}

	}

}
