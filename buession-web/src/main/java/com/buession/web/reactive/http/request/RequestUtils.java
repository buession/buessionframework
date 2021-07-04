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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.reactive.http.request;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.web.http.HttpHeader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;

/**
 * HTTP 请求工具类 Reactive 的实现
 *
 * @author Yong.Teng
 */
public class RequestUtils extends com.buession.web.http.request.RequestUtils {

	private RequestUtils(){
	}

	/**
	 * 获取客户端真实 IP 地址
	 *
	 * @param request
	 * 		ServerHttpRequest
	 *
	 * @return 客户端真实 IP 地址
	 */
	public final static String getClientIp(final ServerHttpRequest request){
		Assert.isNull(request, "HttpServletRequest cloud not be null.");
		HttpHeaders httpHeaders = request.getHeaders();
		List<String> values;

		for(String header : CLIENT_IP_HEADERS){
			values = httpHeaders.get(header);
			if(values == null){
				continue;
			}

			for(String ip : values){
				if(Validate.hasText(ip) && "unknown".equalsIgnoreCase(ip) == false){
					return ip;
				}
			}
		}

		String ip = request.getRemoteAddress().getAddress().getHostAddress();
		if(Validate.hasText(ip) == false || "unknown".equalsIgnoreCase(ip)){
			ip = DEFAULT_IP;
		}

		return ip;
	}

	/**
	 * 判断是否为 Ajax 请求
	 *
	 * @param request
	 * 		ServerHttpRequest
	 *
	 * @return 是否为 Ajax 请求
	 */
	public final static boolean isAjaxRequest(final ServerHttpRequest request){
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
	public final static boolean isMobile(final ServerHttpRequest request){
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
	public final static String getScheme(final ServerHttpRequest request){
		HttpHeaders httpHeaders = request.getHeaders();
		String scheme = httpHeaders.getFirst(HttpHeader.X_FORWARDED_PROTOCOL.getValue());
		if(Validate.hasText(scheme)){
			return scheme;
		}

		scheme = httpHeaders.getFirst(HttpHeader.X_FORWARDED_PROTO.getValue());
		if(Validate.hasText(scheme)){
			return scheme;
		}

		return request.getURI().getScheme();
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
	public final static String getHost(final ServerHttpRequest request){
		HttpHeaders httpHeaders = request.getHeaders();
		String host = httpHeaders.getFirst(HttpHeader.X_FORWARDED_HOST.getValue());
		if(Validate.hasText(host)){
			return host;
		}

		host = httpHeaders.getFirst(HttpHeader.HOST.getValue());
		if(Validate.hasText(host)){
			return host;
		}

		return request.getURI().getHost();
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
	public final static String getDomain(final ServerHttpRequest request){
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
	public final static int getPort(final ServerHttpRequest request){
		HttpHeaders httpHeaders = request.getHeaders();
		String forwardedPort = httpHeaders.getFirst(HttpHeader.X_FORWARDED_PORT.getValue());
		if(Validate.hasText(forwardedPort)){
			return Integer.parseInt(forwardedPort);
		}

		return request.getURI().getPort();
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
	public final static String getAuthority(final ServerHttpRequest request){
		final String host = getHost(request);
		return getAuthority(getScheme(request), host, host.contains(":") ? 0 : getPort(request));
	}

}

