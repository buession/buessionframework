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

/**
 * 方法拦截器，所有的通知均需要转换为 {@link MethodInterceptor} 类型的，最终多个 {@link MethodInterceptor} 组成一个方法拦截器连
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
@FunctionalInterface
public interface MethodInterceptor extends Interceptor {

	/**
	 * 拦截目标方法的执行，可以在这个方法内部实现需要增强的逻辑，以及主动调用目标方法
	 *
	 * @param mi
	 * 		方法调用的描述
	 *
	 * @return 目标方法的执行结果
	 *
	 * @throws Throwable
	 * 		目标方法的执行异常时抛出
	 */
	Object invoke(MethodInvocation mi) throws Throwable;

}
