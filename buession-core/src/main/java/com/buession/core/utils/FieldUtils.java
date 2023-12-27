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
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 类属性工具类
 *
 * @author Yong.Teng
 * @see org.apache.commons.lang3.reflect.FieldUtils
 * @since 1.2.0
 */
public class FieldUtils extends org.apache.commons.lang3.reflect.FieldUtils {

	/**
	 * 判断属性是否为静态属性
	 *
	 * @param field
	 * 		属性
	 *
	 * @return 属性是为静态属性，返回 true；否则返回 false
	 */
	public static boolean isStatic(Field field) {
		Assert.isNull(field, "The field cloud not be null.");
		return Modifier.isStatic(field.getModifiers());
	}

	/**
	 * 设置属性权限
	 *
	 * @param field
	 * 		属性
	 */
	public static void setAccessible(Field field) {
		Assert.isNull(field, "The field cloud not be null.");

		if(MemberUtils.isNotAccessible(field) && field.isAccessible() == false){
			field.setAccessible(true);
		}
	}

	/**
	 * 检测一个属性是否含有 annotations 中的任意一个注解
	 *
	 * @param field
	 * 		待验证的属性
	 * @param annotations
	 * 		检测是否含有的注解
	 *
	 * @return 属性包含有 annotations 中的任意一个注解返回 true；否则返回 false
	 *
	 * @since 2.3.2
	 */
	public static boolean hasAnnotation(final Field field, final Class<? extends Annotation>[] annotations) {
		return AnnotationUtils.hasFieldAnnotationPresent(field, annotations);
	}

}
