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
package com.buession.beans;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Bean 解析器
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public interface BeanResolver {

	/**
	 * 获取 Bean 描述
	 *
	 * @param bean
	 * 		Bean 实例
	 *
	 * @return Bean 描述
	 *
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	Map<String, Object> describe(final Object bean) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException;

	/**
	 * 设置 Bean 属性值
	 *
	 * @param bean
	 * 		Bean 实例
	 * @param name
	 * 		属性名称
	 * @param value
	 * 		属性值
	 *
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	void setProperty(final Object bean, String name, final Object value) throws IllegalAccessException,
			InvocationTargetException;

	/**
	 * 获取 Bean 属性值
	 *
	 * @param bean
	 * 		Bean 实例
	 * @param name
	 * 		属性名称
	 *
	 * @return Bean 属性值
	 *
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	Object getProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException;

	/**
	 * 将 source 的属性或当 source 为 {@link java.util.Map} 时的 key，映射到 Bean bean 中的属性
	 *
	 * @param bean
	 * 		Bean 实例
	 * @param source
	 * 		源对象
	 *
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	void populate(final Object bean, final Object source) throws IllegalAccessException, InvocationTargetException;

}
