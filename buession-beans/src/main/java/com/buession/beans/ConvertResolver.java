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

import com.buession.beans.converters.Converter;

/**
 * 转换解析器
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public interface ConvertResolver {

	/**
	 * 注册数据类型转换器
	 *
	 * @param clazz
	 * 		数据转换类型 class
	 * @param converter
	 * 		数据类型转换器
	 * @param <T>
	 * 		数据转换类型
	 */
	<T> void register(Class<T> clazz, Converter<T> converter);

	/**
	 * 移除注册的数据类型转换器
	 *
	 * @param clazz
	 * 		数据转换类型 class
	 * @param <T>
	 * 		数据转换类型
	 */
	<T> void unregister(Class<T> clazz);

	String convert(final Object value);

	Object convert(final Class<?> clazz, final String value);

	Object convert(final Class<?> clazz, final String[] values);

	Object convert(final Class<?> targetType, final Object value);

}
