/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.reactive.http.request;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.web.http.HttpHeader;
import com.buession.web.reactive.context.request.ReactiveRequestAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

/**
 * HTTP 请求工具类 Reactive 的实现
 *
 * @author Yong.Teng
 */
public class RequestUtils extends com.buession.web.http.request.RequestUtils {

	private RequestUtils() {
	}

	/**
	 * 获取当前请求 {@link ServerHttpResponse} 实例
	 *
	 * @return 当前请求 {@link ServerHttpResponse} 实例
	 *
	 * @since 2.1.0
	 */
	public static ServerHttpResponse getResponse() {
		ReactiveRequestAttributes requestAttributes = (ReactiveRequestAttributes) RequestContextHolder.getRequestAttributes();
		return requestAttributes.getResponse();
	}

	/**
	 * 获取客户端真实 IP 地址
	 *
	 * @param request
	 * 		ServerHttpRequest
	 *
	 * @return 客户端真实 IP 地址
	 */
	public static String getClientIp(final ServerHttpRequest request) {
		Assert.isNull(request, "HttpServletRequest cloud not be null.");
		HttpHeaders httpHeaders = request.getHeaders();

		return getClientIp((headerName)->{
			List<String> values = httpHeaders.get(headerName);

			if(values != null){
				for(String ip : values){
					if(Validate.hasText(ip) && "unknown".equalsIgnoreCase(ip) == false){
						return ip;
					}
				}
			}

			return null;
		}, request.getRemoteAddress() == null ? null : request.getRemoteAddress().getAddress().getHostAddress());
	}

	/**
	 * 判断是否为 Ajax 请求
	 *
	 * @param request
	 * 		ServerHttpRequest
	 *
	 * @return 是否为 Ajax 请求
	 */
	public static boolean isAjaxRequest(final ServerHttpRequest request) {
		HttpHeaders httpHeaders = request.getHeaders();
		return isAjaxRequest(httpHeaders.getFirst(HttpHeader.X_REQUESTED_WITH.getValue()));
	}

	/**
	 * 判断是否为移动端请求
	 *
	 * @param request
	 * 		ServerHttpRequest
	 *
	 * @return 是否为移动端请求
	 */
	public static boolean isMobile(final ServerHttpRequest request) {
		HttpHeaders httpHeaders = request.getHeaders();
		return isMobile(httpHeaders.getFirst(HttpHeader.USER_AGENT.getValue()),
				httpHeaders.getFirst(HttpHeader.ACCEPT.getValue()));
	}

	/**
	 * 获取请求的真实 Scheme
	 *
	 * @param request
	 * 		ServerHttpRequest
	 *
	 * @return 请求的真实 Scheme
	 */
	public static String getScheme(final ServerHttpRequest request) {
		HttpHeaders httpHeaders = request.getHeaders();
		String scheme = getScheme(httpHeaders::getFirst);
		return Validate.hasText(scheme) ? scheme : request.getURI().getScheme();
	}

	/**
	 * 获取请求的真实主机地址
	 *
	 * @param request
	 * 		ServerHttpRequest
	 *
	 * @return 请求的真实主机地址
	 *
	 * @since 1.2.0
	 */
	public static String getHost(final ServerHttpRequest request) {
		HttpHeaders httpHeaders = request.getHeaders();
		String host = getScheme(httpHeaders::getFirst);
		return Validate.hasText(host) ? host : request.getURI().getHost();
	}

	/**
	 * 获取请求的真实域名
	 *
	 * @param request
	 * 		ServerHttpRequest
	 *
	 * @return 请求的真实域名
	 */
	@Deprecated
	public static String getDomain(final ServerHttpRequest request) {
		return getHost(request);
	}

	/**
	 * 获取请求的真实端口
	 *
	 * @param request
	 * 		ServerHttpRequest
	 *
	 * @return 请求的真实端口
	 *
	 * @since 1.2.0
	 */
	public static int getPort(final ServerHttpRequest request) {
		HttpHeaders httpHeaders = request.getHeaders();
		String forwardedPort = httpHeaders.getFirst(HttpHeader.X_FORWARDED_PORT.getValue());
		return Validate.hasText(forwardedPort) ? Integer.parseInt(forwardedPort) : request.getURI().getPort();
	}

	/**
	 * 获取请求的 URL 的权威部分
	 *
	 * @param request
	 * 		HttpServletRequest
	 *
	 * @return 请求的 URL 的权威部分
	 *
	 * @since 1.2.0
	 */
	public static String getAuthority(final ServerHttpRequest request) {
		final String host = getHost(request);
		return getAuthority(getScheme(request), host, host.contains(":") ? 0 : getPort(request));
	}

}

