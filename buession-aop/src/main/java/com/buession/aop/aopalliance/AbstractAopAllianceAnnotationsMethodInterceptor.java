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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.aop.aopalliance;

import com.buession.aop.interceptor.AbstractAnnotationsMethodInterceptor;
import com.buession.aop.interceptor.AnnotationsMethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Aop Alliance 方法注解拦截器抽象类
 *
 * @author Yong.Teng
 * @since 2.1.0
 */
public abstract class AbstractAopAllianceAnnotationsMethodInterceptor extends AbstractAnnotationsMethodInterceptor
		implements AnnotationsMethodInterceptor {

	/**
	 * 构造函数
	 */
	public AbstractAopAllianceAnnotationsMethodInterceptor() {
		super();
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		com.buession.aop.MethodInvocation mi = createMethodInvocation(methodInvocation);
		return super.invoke(mi);
	}

	/**
	 * Create instance for {@link com.buession.aop.MethodInvocation}.
	 *
	 * @param mi
	 * 		The method invocation joinpoint
	 *
	 * @return The instance of {@link com.buession.aop.MethodInvocation}
	 */
	protected com.buession.aop.MethodInvocation createMethodInvocation(final MethodInvocation mi) {
		return new com.buession.aop.MethodInvocation() {

			@Override
			public Object getThis() {
				return mi.getThis();
			}

			@Override
			public Method getMethod() {
				return mi.getMethod();
			}

			@Override
			public Object[] getArguments() {
				return mi.getArguments();
			}

			@Override
			public Object proceed() throws Throwable {
				return mi.proceed();
			}

			@Override
			public String toString() {
				return "Method invocation [" + mi.getMethod() + ']';
			}

		};
	}

}
