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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.aop.interceptor;

import com.buession.aop.interceptor.AbstractMethodInterceptor;
import com.buession.aop.interceptor.AnnotationMethodInterceptor;
import com.buession.core.validator.Validate;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Collection;

/**
 * @author Yong.Teng
 */
public abstract class AbstractAnnotationsMethodInterceptor extends AbstractMethodInterceptor {

	private Collection<AnnotationMethodInterceptor> methodInterceptors;

	public AbstractAnnotationsMethodInterceptor(){
	}

	public AbstractAnnotationsMethodInterceptor(Collection<AnnotationMethodInterceptor> methodInterceptors){
		this.methodInterceptors = methodInterceptors;
	}

	public Collection<AnnotationMethodInterceptor> getMethodInterceptors(){
		return methodInterceptors;
	}

	public void setMethodInterceptors(Collection<AnnotationMethodInterceptor> methodInterceptors){
		this.methodInterceptors = methodInterceptors;
	}

	protected Object continueInvocation(Object aopAllianceMethodInvocation) throws Throwable{
		MethodInvocation mi = (MethodInvocation) aopAllianceMethodInvocation;
		return mi.proceed();
	}

	@Override
	protected void doInvoke(MethodInvocation mi) throws Throwable{
		Collection<AnnotationMethodInterceptor> methodInterceptors = getMethodInterceptors();

		if(Validate.isNotEmpty(methodInterceptors)){
			for(AnnotationMethodInterceptor methodInterceptor : methodInterceptors){
				if(methodInterceptor.isSupport(mi)){
					methodInterceptor.invoke(mi);
				}
			}
		}
	}

}
