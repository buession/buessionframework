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
import com.buession.core.validator.Validate;

import java.util.Collection;

/**
 * 方法注解拦截器抽象类
 *
 * @author Yong.Teng
 * @since 2.1.0
 */
public abstract class AbstractAnnotationsMethodInterceptor extends AbstractMethodInterceptor {

	/**
	 * 注解方法拦截器
	 */
	private Collection<AnnotationMethodInterceptor> methodInterceptors;

	/**
	 * 构造函数
	 */
	public AbstractAnnotationsMethodInterceptor(){
	}

	/**
	 * 返回注解方法拦截器
	 *
	 * @return 注解方法拦截器
	 */
	public Collection<AnnotationMethodInterceptor> getMethodInterceptors(){
		return methodInterceptors;
	}

	/**
	 * 设置注解方法拦截器
	 *
	 * @param methodInterceptors
	 * 		注解方法拦截器
	 */
	public void setMethodInterceptors(Collection<AnnotationMethodInterceptor> methodInterceptors){
		this.methodInterceptors = methodInterceptors;
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable{
		doInvoke(mi);
		return mi.proceed();
	}

	protected void doInvoke(MethodInvocation mi) throws Throwable{
		Collection<AnnotationMethodInterceptor> amis = getMethodInterceptors();
		if(Validate.isNotEmpty(amis)){
			for(AnnotationMethodInterceptor ami : amis){
				if(ami.isSupport(mi)){
					ami.execute(mi);
				}
			}
		}
	}

}
