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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.reactive.method;

import org.springframework.core.MethodParameter;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;

import java.lang.annotation.Annotation;

/**
 * 用于在给定请求的上下文中将方法参数解析为参数值抽象类
 *
 * @param <A>
 * 		注解
 *
 * @author Yong.Teng
 * @since 1.2.2
 */
public abstract class AbstractHandlerMethodArgumentResolver<A extends Annotation> implements HandlerMethodArgumentResolver {

	private final Class<A> type;

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		注解类型
	 */
	public AbstractHandlerMethodArgumentResolver(final Class<A> type){
		this.type = type;
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter){
		return methodParameter.hasParameterAnnotation(type) && checkAloneSupportsParameter(methodParameter);
	}

	protected boolean checkAloneSupportsParameter(final MethodParameter methodParameter){
		return true;
	}

}