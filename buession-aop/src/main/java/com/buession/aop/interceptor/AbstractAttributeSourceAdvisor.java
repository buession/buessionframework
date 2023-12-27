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
package com.buession.aop.interceptor;

import com.buession.core.utils.AnnotationUtils;
import com.buession.core.utils.Assert;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 * @since 2.1.0
 */
public abstract class AbstractAttributeSourceAdvisor extends StaticMethodMatcherPointcutAdvisor {

	private final static long serialVersionUID = 66199570932815709L;

	private final Class<? extends Annotation>[] annotations;

	public AbstractAttributeSourceAdvisor(final AnnotationsMethodInterceptor annotationsMethodInterceptor,
										  final Class<? extends Annotation>[] annotations) {
		Assert.isNull(annotationsMethodInterceptor, "AnnotationsMethodInterceptor cloud not be null.");
		Assert.isNull(annotations, "Annotations cloud not be null.");

		setAdvice(annotationsMethodInterceptor);
		this.annotations = annotations;
	}

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		Method m = method;

		if(isAnnotationPresent(m)){
			return true;
		}

		if(targetClass != null){
			try{
				m = targetClass.getMethod(m.getName(), m.getParameterTypes());
				return isAnnotationPresent(m) || isAnnotationPresent(targetClass);
			}catch(NoSuchMethodException e){
				//
			}
		}

		return false;
	}

	private boolean isAnnotationPresent(final Class<?> targetClazz) {
		return AnnotationUtils.hasClassAnnotationPresent(targetClazz, annotations);
	}

	private boolean isAnnotationPresent(final Method method) {
		return AnnotationUtils.hasMethodAnnotationPresent(method, annotations);
	}

}
