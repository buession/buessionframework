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
package com.buession.aop.handler;

import com.buession.aop.MethodInvocation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * JSR-175 注解读取和处理接口
 *
 * @param <A>
 * 		注解类型
 *
 * @author Yong.Teng
 */
public interface AnnotationHandler<A extends Annotation> {

	/**
	 * 返回注解类
	 *
	 * @return 注解类
	 */
	Class<A> getAnnotationClass();

	/**
	 * 设置注解类
	 *
	 * @param annotationClass
	 * 		注解类
	 */
	void setAnnotationClass(Class<A> annotationClass);

	Object execute(MethodInvocation mi, A annotation);

	Object execute(Object target, Method method, Object[] arguments, A annotation);

}
