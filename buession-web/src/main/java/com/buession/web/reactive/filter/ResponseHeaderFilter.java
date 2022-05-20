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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.reactive.filter;

import com.buession.core.validator.Validate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 响应头过滤器，设置响应头
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ResponseHeaderFilter implements WebFilter {

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
	public ResponseHeaderFilter(String name, String value){
		this.name = name;
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
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain){
		if(Validate.hasText(getName())){
			exchange.getResponse().getHeaders().set(getName(), getValue());
		}

		return chain.filter(exchange);
	}

}
