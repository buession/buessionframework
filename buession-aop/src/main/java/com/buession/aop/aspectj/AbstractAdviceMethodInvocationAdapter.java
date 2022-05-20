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
package com.buession.aop.aspectj;

import com.buession.aop.MethodInvocation;

import java.lang.reflect.Method;

/**
 * 方法调用的描述，在方法调用时提供给拦截器适配器抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractAdviceMethodInvocationAdapter implements MethodInvocation {

	/**
	 * 当前连接点静态部分的对象，一般指被代理的目标对象
	 */
	private final Object object;

	/**
	 * 正在被调用的方法的 {@link Method} 对象
	 */
	private final Method method;

	/**
	 * 调用目标方法的参数
	 */
	private final Object[] arguments;

	/**
	 * 构造函数
	 *
	 * @param object
	 * 		当前连接点静态部分的对象，一般指被代理的目标对象
	 * @param method
	 * 		正在被调用的方法的 {@link Method} 对象
	 * @param arguments
	 * 		调用目标方法的参数
	 */
	public AbstractAdviceMethodInvocationAdapter(Object object, Method method, Object[] arguments){
		this.object = object;
		this.method = method;
		this.arguments = arguments;
	}

	@Override
	public Object getThis(){
		return object;
	}

	@Override
	public Method getMethod(){
		return method;
	}

	@Override
	public Object[] getArguments(){
		return arguments;
	}

	@Override
	public Object proceed() throws Throwable{
		return null;
	}

}
