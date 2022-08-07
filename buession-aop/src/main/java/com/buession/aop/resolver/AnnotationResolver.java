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
package com.buession.aop.resolver;

import com.buession.aop.MethodInvocation;

import java.lang.annotation.Annotation;

/**
 * 注解解析器
 *
 * @author Yong.Teng
 */
@FunctionalInterface
public interface AnnotationResolver {

	/**
	 * 返回注解实例基于给定的 {@link MethodInvocation MethodInvocation} 的参数
	 *
	 * @param mi
	 * 		the intercepted method to be invoked
	 * @param clazz
	 * 		the annotation class of the annotation to find
	 * @param <A>
	 * 		注解类型
	 *
	 * @return 注解实例
	 */
	<A extends Annotation> A getAnnotation(MethodInvocation mi, Class<A> clazz);

}
