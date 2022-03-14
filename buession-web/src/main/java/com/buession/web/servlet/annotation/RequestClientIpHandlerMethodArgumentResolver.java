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
package com.buession.web.servlet.annotation;

import com.buession.core.utils.Assert;
import com.buession.net.utils.InetAddressUtils;
import com.buession.web.http.request.annotation.RequestClientIp;
import com.buession.web.servlet.method.AbstractHandlerMethodArgumentResolver;
import com.buession.web.servlet.http.request.RequestUtils;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Resolves method arguments annotated with an {@link RequestClientIp}
 *
 * @author Yong.Teng
 */
public class RequestClientIpHandlerMethodArgumentResolver extends AbstractHandlerMethodArgumentResolver<RequestClientIp> {

	/**
	 * 构造函数
	 *
	 * @since 1.2.2
	 */
	public RequestClientIpHandlerMethodArgumentResolver(){
		super(RequestClientIp.class);
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception{
		HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		Assert.isNull(servletRequest, "No HttpServletRequest");

		Class<?> clazz = methodParameter.nestedIfOptional().getNestedParameterType();
		if(Long.class.isAssignableFrom(clazz)){
			final String ip = RequestUtils.getClientIp(servletRequest);
			return InetAddressUtils.ip2long(ip);
		}else if(CharSequence.class.isAssignableFrom(clazz)){
			return RequestUtils.getClientIp(servletRequest);
		}else{
			return null;
		}
	}

	@Override
	protected boolean checkAloneSupportsParameter(final MethodParameter methodParameter){
		Class<?> clazz = methodParameter.nestedIfOptional().getNestedParameterType();
		return String.class.isAssignableFrom(clazz) || Long.class.isAssignableFrom(clazz);
	}

}
