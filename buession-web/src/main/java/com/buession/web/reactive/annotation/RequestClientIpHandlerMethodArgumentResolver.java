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
package com.buession.web.reactive.annotation;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.web.http.request.annotation.RequestClientIp;
import com.buession.web.http.request.annotation.RequestClientIpAnnotationUtils;
import com.buession.web.reactive.http.request.RequestUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.reactive.result.method.annotation.AbstractNamedValueSyncArgumentResolver;
import org.springframework.web.server.ServerWebExchange;

/**
 * 方法参数注解 {@link RequestClientIp} 解析器
 *
 * @author Yong.Teng
 */
public class RequestClientIpHandlerMethodArgumentResolver extends AbstractNamedValueSyncArgumentResolver {

	public RequestClientIpHandlerMethodArgumentResolver(@Nullable ConfigurableBeanFactory factory,
														ReactiveAdapterRegistry registry) {
		super(factory, registry);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return RequestClientIpAnnotationUtils.checkSupports(parameter);
	}

	@Override
	protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
		RequestClientIp requestClientIp = parameter.getParameterAnnotation(RequestClientIp.class);
		Assert.isNull(requestClientIp, "No RequestClientIp annotation");
		return new RequestClientIpNamedValueInfo(requestClientIp);
	}

	@Nullable
	@Override
	protected Object resolveNamedValue(String name, MethodParameter parameter, ServerWebExchange exchange) {
		return RequestClientIpAnnotationUtils.resolveNamedValue(parameter,
				(methodParameter)->getClientIp(exchange.getRequest(), methodParameter));
	}

	@Nullable
	private String getClientIp(final ServerHttpRequest request, final MethodParameter parameter) {
		RequestClientIp requestClientIp = parameter.getParameterAnnotation(RequestClientIp.class);

		if(requestClientIp != null){
			if(Validate.isNotEmpty(requestClientIp.headerName())){
				HttpHeaders httpHeaders = request.getHeaders();
				String clientIp;

				for(String headerName : requestClientIp.headerName()){
					if(Validate.hasText(headerName) && ValueConstants.DEFAULT_NONE.equals(headerName) == false){
						clientIp = httpHeaders.getFirst(headerName);
						if(Validate.hasText(clientIp)){
							return clientIp;
						}
					}
				}
			}
		}

		return RequestUtils.getClientIp(request);
	}

	private final static class RequestClientIpNamedValueInfo extends NamedValueInfo {

		private RequestClientIpNamedValueInfo(RequestClientIp annotation) {
			super(RequestClientIp.class.getName(), true, null);
		}

	}

}
