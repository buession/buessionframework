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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.reactive.aop.handler;

import com.buession.core.validator.Validate;
import com.buession.web.aop.handler.AbstractResponseHeadersAnnotationHandler;
import com.buession.web.http.HttpHeader;
import com.buession.web.http.response.annotation.ResponseHeader;
import com.buession.web.http.response.annotation.ResponseHeaders;
import com.buession.web.reactive.aop.AopUtils;
import com.buession.web.reactive.http.ServerHttp;
import com.buession.web.reactive.http.response.ResponseUtils;
import com.buession.web.reactive.aop.MethodUtils;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
public class ReactiveResponseHeadersAnnotationHandler extends AbstractResponseHeadersAnnotationHandler {

	private final static Logger logger = LoggerFactory.getLogger(ReactiveResponseHeadersAnnotationHandler.class);

	public ReactiveResponseHeadersAnnotationHandler(){
		super();
	}

	@Override
	public Void execute(MethodInvocation mi, ResponseHeaders responseHeaders){
		doExecute(AopUtils.getServerHttp(mi), responseHeaders);
		return null;
	}

	@Override
	public Void execute(Object target, Method method, Object[] arguments, ResponseHeaders responseHeaders){
		doExecute(MethodUtils.createServerHttpFromArguments(arguments), responseHeaders);
		return null;
	}

	private final static void doExecute(final ServerHttp serverHttp, final ResponseHeaders responseHeaders){
		if(serverHttp == null || serverHttp.getResponse() == null){
			logger.debug("{} is null.", serverHttp == null ? "ServerHttp" : "ServerHttpResponse");
			return;
		}

		ResponseHeader[] headers = responseHeaders.value();

		if(Validate.isEmpty(headers) == false){
			ServerHttpResponse response = serverHttp.getResponse();
			HttpHeaders httpHeaders = response.getHeaders();

			for(ResponseHeader header : headers){
				if(HttpHeader.EXPIRES.getValue().equalsIgnoreCase(header.name()) == true){
					if(Validate.isNumeric(header.value())){
						ResponseUtils.httpCache(response, Integer.parseInt(header.value()));
					}else{
						ResponseUtils.httpCache(response, header.value());
					}
				}else{
					httpHeaders.set(header.name(), header.value());
				}
			}
		}
	}

}
