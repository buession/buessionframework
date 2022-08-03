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
import com.buession.aop.handler.AnnotationHandler;
import com.buession.aop.resolver.AnnotationResolver;
import com.buession.aop.resolver.DefaultAnnotationResolver;
import com.buession.core.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * 方法注解拦截器抽象类
 *
 * @param <A>
 * 		注解类型
 *
 * @author Yong.Teng
 */
public abstract class AbstractAnnotationMethodInterceptor<A extends Annotation> extends AbstractMethodInterceptor
		implements AnnotationMethodInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * JSR-175 注解读取和处理器
	 */
	private AnnotationHandler<A> handler;

	/**
	 * 注解解析器
	 */
	private AnnotationResolver resolver;

	/**
	 * 构造函数
	 *
	 * @param handler
	 * 		JSR-175 注解读取和处理器
	 */
	public AbstractAnnotationMethodInterceptor(AnnotationHandler<A> handler){
		this(handler, new DefaultAnnotationResolver());
	}

	/**
	 * 构造函数
	 *
	 * @param handler
	 * 		JSR-175 注解读取和处理器
	 * @param resolver
	 * 		注解解析器
	 */
	public AbstractAnnotationMethodInterceptor(AnnotationHandler<A> handler, AnnotationResolver resolver){
		Assert.isNull(handler, "AnnotationHandler cloud not be null.");

		setHandler(handler);
		setResolver(Optional.ofNullable(resolver).orElse(new DefaultAnnotationResolver()));
	}

	public AnnotationHandler<A> getHandler(){
		return handler;
	}

	public void setHandler(AnnotationHandler<A> handler){
		this.handler = handler;
	}

	public AnnotationResolver getResolver(){
		return resolver;
	}

	public void setResolver(AnnotationResolver resolver){
		this.resolver = resolver;
	}

	@Override
	public boolean isSupport(MethodInvocation mi){
		return getAnnotation(mi) != null;
	}

	protected A getAnnotation(MethodInvocation mi){
		return getResolver().getAnnotation(mi, getHandler().getAnnotationClass());
	}

	@Override
	protected void doInvoke(MethodInvocation mi) throws Throwable{
		AnnotationHandler<A> annotationHandler = getHandler();

		if(logger.isDebugEnabled()){
			logger.debug("{} MethodInvocation: {} do invoke.", getClass().getName(), mi.getClass().getName());
		}

		annotationHandler.execute(mi, getAnnotation(mi));
	}

}
