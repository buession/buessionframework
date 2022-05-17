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
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 响应头过滤器，批量设置响应头
 *
 * @author Yong.Teng
 */
public class ResponseHeadersFilter implements WebFilter {

	private Map<String, String> headers;

	public Map<String, String> getHeaders(){
		return headers;
	}

	public void setHeaders(Map<String, String> headers){
		this.headers = headers;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain){
		Map<String, String> headers = getHeaders();
		if(Validate.isNotEmpty(headers)){
			HttpHeaders httpHeaders = exchange.getResponse().getHeaders();
			httpHeaders.setAll(headers);
		}

		return chain.filter(exchange);
	}
}
