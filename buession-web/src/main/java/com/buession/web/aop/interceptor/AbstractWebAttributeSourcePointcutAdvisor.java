/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.aop.interceptor;

import com.buession.web.aop.AopUtils;
import com.buession.web.http.response.annotation.ContentType;
import com.buession.web.http.response.annotation.DisableHttpCache;
import com.buession.web.http.response.annotation.HttpCache;
import com.buession.web.http.response.annotation.PrimitiveCrossOrigin;
import com.buession.web.http.response.annotation.ResponseHeader;
import com.buession.web.http.response.annotation.ResponseHeaders;
import com.buession.web.mvc.view.document.DocumentMetaData;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
public abstract class AbstractWebAttributeSourcePointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

	private final static long serialVersionUID = -25452444487918871L;

	@SuppressWarnings({"unchecked"})
	private final static Class<? extends Annotation>[] WEB_ANNOTATION_CLASSES = new Class[]{ResponseHeader.class,
			ResponseHeaders.class, ContentType.class, PrimitiveCrossOrigin.class, HttpCache.class,
			DisableHttpCache.class, DocumentMetaData.class};

	@Override
	public boolean matches(Method method, Class<?> targetClass){
		Method m = method;

		if(AopUtils.hasMethodAnnotationPresent(m, WEB_ANNOTATION_CLASSES)){
			return true;
		}

		try{
			m = targetClass.getMethod(m.getName(), m.getParameterTypes());
			return AopUtils.hasMethodAnnotationPresent(m, WEB_ANNOTATION_CLASSES) || AopUtils.hasClassAnnotationPresent(targetClass, WEB_ANNOTATION_CLASSES);
		}catch(NoSuchMethodException e){
			return false;
		}
	}

}
