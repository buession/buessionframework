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
package com.buession.aop;

import java.lang.reflect.Method;

/**
 * 方法调用的描述，在方法调用时提供给拦截器；方法调用是一个连接点，可以被方法拦截器拦截；可以获取调用过程中的目标方法
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface MethodInvocation {

	/**
	 * 返回当前连接点静态部分的对象，一般指被代理的目标对象
	 *
	 * @return 当前连接点静态部分的对象，一般指被代理的目标对象
	 */
	Object getThis();

	/**
	 * 返回正在被调用的方法的 {@link Method} 对象
	 *
	 * @return 正在被调用的方法的 {@link Method} 对象
	 */
	Method getMethod();

	/**
	 * 返回调用目标方法的参数
	 *
	 * @return 调用目标方法的参数
	 */
	Object[] getArguments();

	/**
	 * 转到拦截器链中的下一个拦截器
	 *
	 * @return 当前拦截器的任意返回值
	 *
	 * @throws Throwable
	 * 		异常
	 */
	Object proceed() throws Throwable;

}
