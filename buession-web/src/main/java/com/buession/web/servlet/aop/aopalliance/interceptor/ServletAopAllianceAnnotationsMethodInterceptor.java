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
package com.buession.web.servlet.aop.aopalliance.interceptor;

import com.buession.aop.interceptor.AnnotationMethodInterceptor;
import com.buession.aop.resolver.SpringAnnotationResolver;
import com.buession.web.aop.interceptor.AbstractAopAllianceAnnotationsMethodInterceptor;
import com.buession.web.servlet.aop.interceptor.ServletContentTypeAnnotationMethodInterceptor;
import com.buession.web.servlet.aop.interceptor.ServletDocumentMetaDataAnnotationMethodInterceptor;
import com.buession.web.servlet.aop.interceptor.ServletHttpCacheAnnotationMethodInterceptor;
import com.buession.web.servlet.aop.interceptor.ServletResponseHeaderAnnotationMethodInterceptor;
import com.buession.web.servlet.aop.interceptor.ServletResponseHeadersAnnotationMethodInterceptor;

import java.util.ArrayDeque;
import java.util.Collection;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ServletAopAllianceAnnotationsMethodInterceptor extends AbstractAopAllianceAnnotationsMethodInterceptor {

	public ServletAopAllianceAnnotationsMethodInterceptor(){
		super();

		final Collection<AnnotationMethodInterceptor> methodInterceptors = new ArrayDeque<>(5);
		final SpringAnnotationResolver springAnnotationResolver = new SpringAnnotationResolver();

		methodInterceptors.add(new ServletContentTypeAnnotationMethodInterceptor(springAnnotationResolver));
		methodInterceptors.add(new ServletDocumentMetaDataAnnotationMethodInterceptor(springAnnotationResolver));
		methodInterceptors.add(new ServletHttpCacheAnnotationMethodInterceptor(springAnnotationResolver));
		methodInterceptors.add(new ServletResponseHeaderAnnotationMethodInterceptor(springAnnotationResolver));
		methodInterceptors.add(new ServletResponseHeadersAnnotationMethodInterceptor(springAnnotationResolver));

		setMethodInterceptors(methodInterceptors);
	}

}
