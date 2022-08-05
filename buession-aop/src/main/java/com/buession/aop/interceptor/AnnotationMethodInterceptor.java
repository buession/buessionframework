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
package com.buession.aop.interceptor;

import com.buession.aop.MethodInvocation;

import java.lang.annotation.Annotation;

/**
 * 方法注解拦截器
 *
 * @author Yong.Teng
 * @see MethodInterceptor
 */
public interface AnnotationMethodInterceptor extends MethodInterceptor {

	/**
	 * 判断 {@link MethodInvocation} 是否支持注解
	 *
	 * @param mi
	 *        {@link MethodInvocation}
	 *
	 * @return {@link MethodInvocation} 支持注解返回 true；否则，返回 false
	 */
	boolean isSupport(MethodInvocation mi);

	/**
	 * 增强方法执行
	 *
	 * @param mi
	 * 		方法调用的描述
	 *
	 * @throws Throwable
	 * 		增强方法执行异常时抛出
	 */
	void execute(MethodInvocation mi) throws Throwable;

}
