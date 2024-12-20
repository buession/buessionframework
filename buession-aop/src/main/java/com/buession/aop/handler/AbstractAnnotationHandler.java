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
package com.buession.aop.handler;

import com.buession.core.utils.Assert;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;

/**
 * JSR-175 注解读取和处理器抽象类
 *
 * @param <A>
 * 		注解类型
 *
 * @author Yong.Teng
 */
public abstract class AbstractAnnotationHandler<A extends Annotation> implements AnnotationHandler<A> {

	/**
	 * 注解类
	 */
	protected Class<A> annotationClass;

	/**
	 * 占位符解析器
	 *
	 * @since 2.3.2
	 */
	protected StringValueResolver stringValueResolver;

	/**
	 * 构造函数
	 *
	 * @param annotationClass
	 * 		注解类
	 */
	@Deprecated
	public AbstractAnnotationHandler(Class<A> annotationClass) {
		setAnnotationClass(annotationClass);
	}

	/**
	 * 构造函数
	 *
	 * @param annotationClass
	 * 		注解类
	 * @param stringValueResolver
	 * 		占位符解析器
	 *
	 * @since 2.3.2
	 */
	public AbstractAnnotationHandler(Class<A> annotationClass, StringValueResolver stringValueResolver) {
		setAnnotationClass(annotationClass);
		this.stringValueResolver = stringValueResolver;
	}

	@Override
	public Class<A> getAnnotationClass() {
		return annotationClass;
	}

	@Override
	public void setAnnotationClass(Class<A> annotationClass) {
		Assert.isNull(annotationClass, "Annotation class argument could not be null");
		this.annotationClass = annotationClass;
	}

}
