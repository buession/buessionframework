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
package com.buession.web.aop.aspect;

import com.buession.aop.aspectj.AbstractAspectjAnnotationsMethodInterceptor;
import com.buession.core.utils.Assert;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractWebAnnotationAspect<T extends AbstractAspectjAnnotationsMethodInterceptor>
		implements WebAnnotationAspect {

	protected final T methodInterceptor;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractWebAnnotationAspect(final T methodInterceptor) {
		Assert.isNull(methodInterceptor, "The instance for " + getClass().getName() + " cloud not be null.");
		this.methodInterceptor = methodInterceptor;
	}

	@Override
	public void anyAnnotatedMethod() {
		annotatedMethodExecuteLog("anyAnnotatedMethod");
	}

	@Override
	public void anyAnnotatedMethodCall(JoinPoint joinPoint) {
		annotatedMethodExecuteLog("anyAnnotatedMethodCall");
	}

	protected void annotatedMethodExecuteLog(final String method) {
		if(logger.isDebugEnabled()){
			logger.debug("Call {}::{}()", getClass().getName(), method);
		}
	}

}
