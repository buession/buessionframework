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
package com.buession.core.utils;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 注解工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AnnotationUtils extends org.springframework.core.annotation.AnnotationUtils {

	/**
	 * 检测一个类是否含有 annotations 中的任意一个注解
	 *
	 * @param clazz
	 * 		待验证的类
	 * @param annotations
	 * 		检测是否含有的注解
	 *
	 * @return 类包含有 annotations 中的任意一个注解返回 true；否则返回 false
	 */
	public static boolean hasClassAnnotationPresent(final Class<?> clazz,
													final Class<? extends Annotation>[] annotations){
		Assert.isNull(clazz, "Find annotation class cloud not be null.");

		if(Validate.isNotEmpty(annotations)){
			for(Class<? extends Annotation> annotationClazz : annotations){
				Annotation annotation = findAnnotation(clazz, annotationClazz);
				if(annotation != null){
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 检测一个方法是否含有 annotations 中的任意一个注解
	 *
	 * @param method
	 * 		待验证的方法
	 * @param annotations
	 * 		检测是否含有的注解
	 *
	 * @return 方法包含有 annotations 中的任意一个注解返回 true；否则返回 false
	 */
	public static boolean hasMethodAnnotationPresent(Method method, final Class<? extends Annotation>[] annotations){
		Assert.isNull(method, "Find annotation method cloud not be null.");

		if(Validate.isNotEmpty(annotations)){
			for(Class<? extends Annotation> annotationClazz : annotations){
				Annotation annotation = findAnnotation(method, annotationClazz);
				if(annotation != null){
					return true;
				}
			}
		}

		return false;
	}

}
