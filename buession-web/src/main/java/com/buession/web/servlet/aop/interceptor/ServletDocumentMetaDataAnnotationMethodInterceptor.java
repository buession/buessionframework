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
package com.buession.web.servlet.aop.interceptor;

import com.buession.aop.resolver.AnnotationResolver;
import com.buession.web.aop.interceptor.AbstractDocumentMetaDataAnnotationMethodInterceptor;
import com.buession.web.mvc.view.document.DocumentMetaData;
import com.buession.web.servlet.aop.handler.ServletDocumentMetaDataAnnotationHandler;
import org.springframework.util.StringValueResolver;

/**
 * {@link DocumentMetaData} 注解拦截器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ServletDocumentMetaDataAnnotationMethodInterceptor
		extends AbstractDocumentMetaDataAnnotationMethodInterceptor {

	/**
	 * 构造函数
	 */
	@Deprecated
	public ServletDocumentMetaDataAnnotationMethodInterceptor() {
		super(new ServletDocumentMetaDataAnnotationHandler());
	}

	/**
	 * 构造函数
	 *
	 * @param resolver
	 * 		注解解析器
	 */
	@Deprecated
	public ServletDocumentMetaDataAnnotationMethodInterceptor(AnnotationResolver resolver) {
		super(new ServletDocumentMetaDataAnnotationHandler(), resolver);
	}

	/**
	 * 构造函数
	 *
	 * @param stringValueResolver
	 * 		占位符解析器
	 *
	 * @since 2.3.2
	 */
	public ServletDocumentMetaDataAnnotationMethodInterceptor(StringValueResolver stringValueResolver) {
		super(new ServletDocumentMetaDataAnnotationHandler(stringValueResolver));
	}

	/**
	 * 构造函数
	 *
	 * @param resolver
	 * 		注解解析器
	 * @param stringValueResolver
	 * 		占位符解析器
	 *
	 * @since 2.3.2
	 */
	public ServletDocumentMetaDataAnnotationMethodInterceptor(AnnotationResolver resolver,
															  StringValueResolver stringValueResolver) {
		super(new ServletDocumentMetaDataAnnotationHandler(stringValueResolver), resolver);
	}

}
