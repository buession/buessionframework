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

import com.buession.aop.interceptor.AbstractAnnotationsMethodInterceptor;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yong.Teng
 * @since 2.1.0
 */
public abstract class AbstractAspectAnnotationsMethodInterceptor extends AbstractAnnotationsMethodInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractAspectAnnotationsMethodInterceptor(){
		super();
	}

	public void performBeforeInterception(JoinPoint joinPoint) throws Throwable{
		if(logger.isDebugEnabled()){
			AspectjAnnotationsMethodInterceptorLogUtils.performBeforeInterceptionDebug(logger, joinPoint);
		}

		// 1. Adapt the join point into a method invocation
		BeforeAdviceMethodInvocationAdapter mi = BeforeAdviceMethodInvocationAdapter.createFromJoinPoint(joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

	public void performAfterInterception(JoinPoint joinPoint) throws Throwable{
		if(logger.isDebugEnabled()){
			AspectjAnnotationsMethodInterceptorLogUtils.performAfterInterceptionDebug(logger, joinPoint);
		}

		// 1. Adapt the join point into a method invocation
		AfterAdviceMethodInvocationAdapter mi = AfterAdviceMethodInvocationAdapter.createFromJoinPoint(joinPoint);
		// 2. Delegate the authorization of the method call to the super class
		super.invoke(mi);
	}

}
