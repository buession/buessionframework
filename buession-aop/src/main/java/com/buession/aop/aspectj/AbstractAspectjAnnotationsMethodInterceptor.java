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
import java.util.StringJoiner;

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

	/**
	 * 切点方法之前执行
	 *
	 * @param joinPoint
	 *        {@link JoinPoint}
	 *
	 * @throws Throwable
	 * 		异常
	 */
	public void performBeforeInterception(JoinPoint joinPoint) throws Throwable {
		performInterceptionDebug(joinPoint);

		// 1. Adapt the join point into a method invocation
		BeforeAdviceMethodInvocationAdapter mi = BeforeAdviceMethodInvocationAdapter.createFromJoinPoint(joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

	/**
	 * 切点方法之后执行
	 *
	 * @param joinPoint
	 *        {@link JoinPoint}
	 *
	 * @throws Throwable
	 * 		异常
	 */
	public void performAfterInterception(JoinPoint joinPoint) throws Throwable {
		performInterceptionDebug(joinPoint);

		// 1. Adapt the join point into a method invocation
		AfterAdviceMethodInvocationAdapter mi = AfterAdviceMethodInvocationAdapter.createFromJoinPoint(joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

	/**
	 * 切点方法的返回值并进行增强
	 *
	 * @param joinPoint
	 *        {@link JoinPoint}
	 *
	 * @throws Throwable
	 * 		异常
	 * @since 2.3.0
	 */
	public void performAfterReturningInterception(JoinPoint joinPoint) throws Throwable {
		performInterceptionDebug(joinPoint);

		// 1. Adapt the join point into a method invocation
		AfterReturningAdviceMethodInvocationAdapter mi = AfterReturningAdviceMethodInvocationAdapter.createFromJoinPoint(
				joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

	/**
	 * 切点方法的返回值后执行
	 *
	 * @param joinPoint
	 *        {@link JoinPoint}
	 *
	 * @throws Throwable
	 * 		异常
	 * @since 2.3.0
	 */
	public void performAfterThrowingInterception(JoinPoint joinPoint) throws Throwable {
		performInterceptionDebug(joinPoint);

		// 1. Adapt the join point into a method invocation
		AfterThrowingAdviceMethodInvocationAdapter mi = AfterThrowingAdviceMethodInvocationAdapter.createFromJoinPoint(
				joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

	/**
	 * 点方法抛出异常时会执行
	 *
	 * @param joinPoint
	 *        {@link JoinPoint}
	 *
	 * @throws Throwable
	 * 		异常
	 * @since 2.3.0
	 */
	public void performAroundInterception(JoinPoint joinPoint) throws Throwable {
		performInterceptionDebug(joinPoint);

		// 1. Adapt the join point into a method invocation
		AroundAdviceMethodInvocationAdapter mi = AroundAdviceMethodInvocationAdapter.createFromJoinPoint(
				joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

	protected void performInterceptionDebug(final JoinPoint joinPoint) {
		if(logger.isDebugEnabled()){
			StringJoiner joiner = new StringJoiner(System.lineSeparator());

			joiner.add("Invoking a method decorated with a annotation");
			joiner.add("\tkind       : " + joinPoint.getKind());
			joiner.add("\tjoinPoint  : " + joinPoint);
			joiner.add("\tannotations: " +
					Arrays.toString(((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotations()));
			joiner.add("\ttarget     : " + joinPoint.getTarget());

			logger.debug(joiner.toString());
		}
	}

}
