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

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应头过滤器，设置响应头
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ResponseHeaderFilter extends OncePerRequestFilter {

	/**
	 * 响应头名称
	 */
	private String name;

	/**
	 * 响应头值
	 */
	private String value;

	/**
	 * 构造函数
	 */
	public ResponseHeaderFilter(){
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		响应头名称
	 * @param value
	 * 		响应头值
	 */
	public ResponseHeaderFilter(final String name, final String value){
		setName(name);
		this.value = value;
	}

	/**
	 * 返回响应头名称
	 *
	 * @return 响应头名称
	 */
	public String getName(){
		return name;
	}

	/**
	 * 设置响应头名称
	 *
	 * @param name
	 * 		响应头名称
	 */
	public void setName(String name){
		Assert.isBlank(name, "HTTP Response header name cloud not be empty or null");
		this.name = name;
	}

	/**
	 * 返回响应头值
	 *
	 * @return 响应头值
	 */
	public String getValue(){
		return value;
	}

	/**
	 * 设置响应头值
	 *
	 * @param value
	 * 		响应头值
	 */
	public void setValue(String value){
		this.value = value;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException{
		if(Validate.hasText(getName())){
			response.addHeader(getName(), getValue());
		}

		filterChain.doFilter(request, response);
	}

}
