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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.reflect;

import com.buession.core.utils.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 类工具类 More {@link org.apache.commons.lang3.ClassUtils}
 *
 * @author Yong.Teng
 */
public class ClassUtils extends org.apache.commons.lang3.ClassUtils {

	/**
	 * 获取类的所有声明的字段，即包括 public、protected、private 和 default，但是不包括父类申明的字段
	 *
	 * @param clazz
	 * 		类
	 *
	 * @return 类的所有声明的字段
	 */
	public final static Field[] getFields(final Class<?> clazz){
		Assert.isNull(clazz, "Class cloud not be null.");
		return clazz.getDeclaredFields();
	}

	/**
	 * 获取类的所有声明的字段，即包括 public、protected、private 和 default，包括父类申明的字段
	 *
	 * @param clazz
	 * 		类
	 *
	 * @return 类的所有声明的字段
	 */
	public final static Field[] getAllFields(final Class<?> clazz){
		Assert.isNull(clazz, "Class cloud not be null.");
		final List<Field> allFields = new ArrayList<>(16);
		Class<?> currentClass = clazz;

		while(currentClass != null){
			Collections.addAll(allFields, currentClass.getDeclaredFields());
			currentClass = currentClass.getSuperclass();
		}

		return allFields.toArray(new Field[]{});
	}

	/**
	 * 调用类方法
	 *
	 * @param object
	 * 		类实例
	 * @param method
	 * 		类方法
	 *
	 * @return 返回方法执行结果
	 *
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public final static Object invoke(final Object object, final Method method) throws InvocationTargetException,
			IllegalAccessException{
		Assert.isNull(object, "Object cloud not be null.");
		Assert.isNull(method, "Object method cloud not be null.");

		method.setAccessible(true);
		return method.invoke(object);
	}

	/**
	 * 调用类方法
	 *
	 * @param object
	 * 		类实例
	 * @param method
	 * 		类方法
	 * @param arguments
	 * 		方法参数
	 *
	 * @return 返回方法执行结果
	 *
	 * @throws InvocationTargetException
	 * 		反射异常，当被调用的方法的内部抛出了异常而没有被捕获时，将由此异常接收
	 * @throws IllegalAccessException
	 *        {@link IllegalAccessException}
	 */
	public final static Object invoke(final Object object, final Method method, final Object... arguments) throws InvocationTargetException, IllegalAccessException{
		Assert.isNull(object, "Object cloud not be null.");
		Assert.isNull(method, "Object method cloud not be null.");

		method.setAccessible(true);
		return method.invoke(object, arguments);
	}

}
