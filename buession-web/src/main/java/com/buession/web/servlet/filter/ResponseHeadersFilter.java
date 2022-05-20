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
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.servlet.filter;

import com.buession.core.validator.Validate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 响应头过滤器，批量设置响应头
 *
 * @author Yong.Teng
 */
public class ResponseHeadersFilter extends OncePerRequestFilter {

	/**
	 * Key =&gt; Value 形式的响应头
	 */
	private Map<String, String> headers;

	/**
	 * 构造函数
	 */
	public ResponseHeadersFilter(){
	}

	/**
	 * 构造函数
	 *
	 * @param headers
	 * 		响应头，Key =&gt; Value 形式的响应头
	 *
	 * @since 2.0.0
	 */
	public ResponseHeadersFilter(Map<String, String> headers){
		this.headers = headers;
	}

	/**
	 * 返回响应头
	 *
	 * @return 响应头，Key =&gt; Value 形式
	 */
	public Map<String, String> getHeaders(){
		return headers;
	}

	/**
	 * 设置响应头
	 *
	 * @param headers
	 * 		响应头，Key =&gt; Value 形式
	 */
	public void setHeaders(Map<String, String> headers){
		this.headers = headers;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException{
		Map<String, String> headers = getHeaders();
		if(Validate.isNotEmpty(headers)){
			headers.forEach(response::addHeader);
		}

		filterChain.doFilter(request, response);
	}

}
