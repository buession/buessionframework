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
 * | Copyright @ 2013-2026 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.servlet.filter;

import com.buession.core.utils.Assert;
import com.buession.web.http.response.IServerInfoFilter;
import com.buession.web.utils.ServerInfoFilterUtils;
import com.buession.web.utils.ServerUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Server 信息过滤器
 *
 * @author Yong.Teng
 */
public class ServerInfoFilter extends OncePerRequestFilter implements IServerInfoFilter {

	/**
	 * 响应头名称
	 */
	private String headerName;

	/**
	 * 添加前缀
	 *
	 * @since 5.0.1
	 */
	private String prefix;

	/**
	 * 去除前缀
	 *
	 * @since 5.0.1
	 */
	private String stripPrefix;

	/**
	 * 添加后缀
	 *
	 * @since 5.0.1
	 */
	private String suffix;

	/**
	 * 去除后缀
	 *
	 * @since 5.0.1
	 */
	private String stripSuffix;

	/**
	 * 构造函数
	 */
	public ServerInfoFilter() {
		this(SERVER_NAME_HEADER_NAME);
	}

	/**
	 * 构造函数
	 *
	 * @param headerName
	 * 		响应头名称
	 */
	public ServerInfoFilter(final String headerName) {
		super();
		setHeaderName(headerName);
	}

	@Override
	public String getHeaderName() {
		return headerName;
	}

	@Override
	public void setHeaderName(String headerName) {
		Assert.isBlank(headerName, "Server info response header name cloud not be null or empty");
		this.headerName = headerName;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String getStripPrefix() {
		return stripPrefix;
	}

	@Override
	public void setStripPrefix(String stripPrefix) {
		this.stripPrefix = stripPrefix;
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public String getStripSuffix() {
		return stripSuffix;
	}

	@Override
	public void setStripSuffix(String stripSuffix) {
		this.stripSuffix = stripSuffix;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.setHeader(getHeaderName(), format(ServerUtils.getHostName()));
		filterChain.doFilter(request, response);
	}

	protected String format(final String computerName) {
		return ServerInfoFilterUtils.format(computerName, getPrefix(), getStripPrefix(), getSuffix(), getStripSuffix());
	}

}
