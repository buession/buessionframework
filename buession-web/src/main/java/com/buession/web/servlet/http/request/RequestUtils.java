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
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.servlet.http.request;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.web.http.HttpHeader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP 请求工具类 Servlet 的实现
 *
 * @author Yong.Teng
 */
public class RequestUtils extends com.buession.web.http.request.RequestUtils {

	private RequestUtils(){
	}

	/**
	 * 获取当前请求 {@link HttpServletResponse} 实例
	 *
	 * @return 当前请求 {@link HttpServletResponse} 实例
	 *
	 * @since 2.1.0
	 */
	public static HttpServletResponse getResponse(){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attributes.getResponse();
	}

	/**
	 * 获取客户端真实 IP 地址
	 *
	 * @param request
	 * 		HttpServletRequest
	 *
	 * @return 客户端真实 IP 地址
	 */
	public static String getClientIp(final HttpServletRequest request){
		Assert.isNull(request, "HttpServletRequest cloud not be null.");
		return getClientIp(request::getHeader, request.getRemoteAddr());
	}

	/**
	 * 判断是否为 Ajax 请求
	 *
	 * @param request
	 * 		HttpServletRequest
	 *
	 * @return 是否为 Ajax 请求
	 */
	public static boolean isAjaxRequest(final HttpServletRequest request){
		return isAjaxRequest(request.getHeader(HttpHeader.X_REQUESTED_WITH.getValue()));
	}

	/**
	 * 判断是否为移动端请求
	 *
	 * @param request
	 * 		HttpServletRequest
	 *
	 * @return 是否为移动端请求
	 */
	public static boolean isMobile(final HttpServletRequest request){
		return isMobile(request.getHeader(HttpHeader.USER_AGENT.getValue()),
				request.getHeader(HttpHeader.ACCEPT.getValue()));
	}

	/**
	 * 获取请求的真实 Scheme
	 *
	 * @param request
	 * 		HttpServletRequest
	 *
	 * @return 请求的真实 Scheme
	 */
	public static String getScheme(final HttpServletRequest request){
		String scheme = getScheme(request::getHeader);
		return Validate.hasText(scheme) ? scheme : request.getScheme();
	}

	/**
	 * 获取请求的真实主机地址
	 *
	 * @param request
	 * 		HttpServletRequest
	 *
	 * @return 请求的真实主机地址
	 *
	 * @since 1.2.0
	 */
	public static String getHost(final HttpServletRequest request){
		String host = getHost(request::getHeader);
		return Validate.hasText(host) ? host : request.getServerName();
	}

	/**
	 * 获取请求的真实域名
	 *
	 * @param request
	 * 		HttpServletRequest
	 *
	 * @return 请求的真实域名
	 */
	@Deprecated
	public static String getDomain(final HttpServletRequest request){
		return getHost(request);
	}

	/**
	 * 获取请求的真实端口
	 *
	 * @param request
	 * 		HttpServletRequest
	 *
	 * @return 请求的真实端口
	 *
	 * @since 1.2.0
	 */
	public static int getPort(final HttpServletRequest request){
		String forwardedPort = request.getHeader(HttpHeader.X_FORWARDED_PORT.getValue());
		if(Validate.hasText(forwardedPort)){
			return Integer.parseInt(forwardedPort);
		}

		return request.getServerPort();
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
	public static String getAuthority(final HttpServletRequest request){
		final String host = getHost(request);
		return getAuthority(getScheme(request), host, host.contains(":") ? 0 : getPort(request));
	}

}
