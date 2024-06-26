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

import com.buession.aop.MethodInvocation;
import com.buession.aop.handler.AnnotationHandler;
import com.buession.aop.resolver.AnnotationResolver;
import com.buession.aop.resolver.DefaultAnnotationResolver;
import com.buession.core.utils.Assert;

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
	public AbstractAnnotationMethodInterceptor(AnnotationHandler<A> handler) {
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
	public AbstractAnnotationMethodInterceptor(AnnotationHandler<A> handler, AnnotationResolver resolver) {
		Assert.isNull(handler, "AnnotationHandler cloud not be null.");

		setHandler(handler);
		setResolver(Optional.of(resolver).orElse(new DefaultAnnotationResolver()));
	}

	/**
	 * 返回 JSR-175 注解读取和处理器
	 *
	 * @return JSR-175 注解读取和处理器
	 */
	public AnnotationHandler<A> getHandler() {
		return handler;
	}

	/**
	 * 设置 JSR-175 注解读取和处理器
	 *
	 * @param handler
	 * 		JSR-175 注解读取和处理器
	 */
	public void setHandler(AnnotationHandler<A> handler) {
		this.handler = handler;
	}

	/**
	 * 返回注解解析器
	 *
	 * @return 注解解析器
	 */
	public AnnotationResolver getResolver() {
		return resolver;
	}

	/**
	 * 设置注解解析器
	 *
	 * @param resolver
	 * 		注解解析器
	 */
	public void setResolver(AnnotationResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public boolean isSupport(MethodInvocation mi) {
		return getAnnotation(mi) != null;
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		execute(mi);
		return mi.proceed();
	}

	@Override
	public void execute(MethodInvocation mi) throws Throwable {
		getHandler().execute(mi, getAnnotation(mi));
	}

	protected A getAnnotation(MethodInvocation mi) {
		return getResolver().getAnnotation(mi, getHandler().getAnnotationClass());
	}

}
