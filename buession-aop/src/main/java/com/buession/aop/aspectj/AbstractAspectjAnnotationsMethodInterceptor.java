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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.aop.aspectj;

import com.buession.aop.interceptor.AbstractAnnotationsMethodInterceptor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Aspectj 方法注解拦截器抽象类
 *
 * @author Yong.Teng
 * @since 2.1.0
 */
public abstract class AbstractAspectjAnnotationsMethodInterceptor extends AbstractAnnotationsMethodInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractAspectjAnnotationsMethodInterceptor() {
		super();
	}

	public void performBeforeInterception(JoinPoint joinPoint) throws Throwable {
		if(logger.isDebugEnabled()){
			performInterceptionDebug(joinPoint);
		}

		// 1. Adapt the join point into a method invocation
		BeforeAdviceMethodInvocationAdapter mi = BeforeAdviceMethodInvocationAdapter.createFromJoinPoint(joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

	public void performAfterInterception(JoinPoint joinPoint) throws Throwable {
		if(logger.isDebugEnabled()){
			performInterceptionDebug(joinPoint);
		}

		// 1. Adapt the join point into a method invocation
		AfterAdviceMethodInvocationAdapter mi = AfterAdviceMethodInvocationAdapter.createFromJoinPoint(joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

	/**
	 * @since 2.3.0
	 */
	public void performAfterReturningInterception(JoinPoint joinPoint) throws Throwable {
		if(logger.isDebugEnabled()){
			performInterceptionDebug(joinPoint);
		}

		// 1. Adapt the join point into a method invocation
		AfterReturningAdviceMethodInvocationAdapter mi = AfterReturningAdviceMethodInvocationAdapter.createFromJoinPoint(
				joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

	/**
	 * @since 2.3.0
	 */
	public void performAfterThrowingInterception(JoinPoint joinPoint) throws Throwable {
		if(logger.isDebugEnabled()){
			performInterceptionDebug(joinPoint);
		}

		// 1. Adapt the join point into a method invocation
		AfterThrowingAdviceMethodInvocationAdapter mi = AfterThrowingAdviceMethodInvocationAdapter.createFromJoinPoint(
				joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

	/**
	 * @since 2.3.0
	 */
	public void performAroundInterception(JoinPoint joinPoint) throws Throwable {
		if(logger.isDebugEnabled()){
			performInterceptionDebug(joinPoint);
		}

		// 1. Adapt the join point into a method invocation
		AroundAdviceMethodInvocationAdapter mi = AroundAdviceMethodInvocationAdapter.createFromJoinPoint(
				joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

	protected void performInterceptionDebug(final JoinPoint joinPoint) {
		final StringBuilder message = new StringBuilder(255);

		message.append("Invoking a method decorated with a annotation").append(System.lineSeparator());
		message.append("\tkind       : ").append(joinPoint.getKind()).append(System.lineSeparator());
		message.append("\tjoinPoint  : ").append(joinPoint).append(System.lineSeparator());
		message.append("\tannotations: ")
				.append(Arrays.toString(((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotations()))
				.append(System.lineSeparator());
		message.append("\ttarget     : ").append(joinPoint.getTarget());

		logger.debug(message.toString());
	}

}
