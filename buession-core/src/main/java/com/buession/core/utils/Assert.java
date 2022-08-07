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

import com.buession.core.validator.Validate;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * 断言
 *
 * @author Yong.Teng
 */
public class Assert {

	private Assert(){

	}

	/**
	 * 如果表达式 expression 为 true，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param expression
	 * 		表达式
	 * @param message
	 * 		异常信息
	 */
	public static void isTrue(final boolean expression, final String message){
		if(expression){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果表达式 expression 为 false，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param expression
	 * 		表达式
	 * @param message
	 * 		异常信息
	 */
	public static void isFalse(final boolean expression, final String message){
		if(expression == false){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果对象 object 为 null，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param object
	 * 		对象
	 * @param message
	 * 		异常信息
	 */
	public static void isNull(final Object object, final String message){
		if(object == null){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果对象 object 不为 null，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param object
	 * 		对象
	 * @param message
	 * 		异常信息
	 */
	public static void notNull(final Object object, final String message){
		if(object != null){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果数组对象 objects 为 null 或为空，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param objects
	 * 		数组对象
	 * @param message
	 * 		异常信息
	 */
	public static void isEmpty(final Object[] objects, final String message){
		if(Validate.isEmpty(objects)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果集合对象 collection 为 null 或为空，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param collection
	 * 		集合
	 * @param message
	 * 		异常信息
	 */
	public static void isEmpty(final Collection<?> collection, final String message){
		if(Validate.isEmpty(collection)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Map 对象 map 为 null 或为空，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param map
	 * 		Map 对象
	 * @param message
	 * 		异常信息
	 */
	public static void isEmpty(final Map<?, ?> map, final String message){
		if(Validate.isEmpty(map)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果迭代器 iterator 为 null 或为空，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param iterator
	 * 		迭代器
	 * @param message
	 * 		异常信息
	 */
	public static void isEmpty(final Iterator<?> iterator, final String message){
		if(Validate.isEmpty(iterator)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果枚举 enumeration 为 null 或为空，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param enumeration
	 * 		枚举
	 * @param message
	 * 		异常信息
	 */
	public static void isEmpty(final Enumeration<?> enumeration, final String message){
		if(Validate.isEmpty(enumeration)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果数组对象 objects 不为 null 且不为空，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param objects
	 * 		数组对象
	 * @param message
	 * 		异常信息
	 */
	public static void notEmpty(final Object[] objects, final String message){
		if(Validate.isNotEmpty(objects)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果集合对象 collection 不为 null 且不为空，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param collection
	 * 		集合
	 * @param message
	 * 		异常信息
	 */
	public static void notEmpty(final Collection<?> collection, final String message){
		if(Validate.isNotEmpty(collection)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Map 对象 map 不为 null 且不为空，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param map
	 * 		Map 对象
	 * @param message
	 * 		异常信息
	 */
	public static void notEmpty(final Map<?, ?> map, final String message){
		if(Validate.isNotEmpty(map)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果迭代器 iterator 不为 null 且不为空，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param iterator
	 * 		迭代器
	 * @param message
	 * 		异常信息
	 */
	public static void notEmpty(final Iterator<?> iterator, final String message){
		if(Validate.isNotEmpty(iterator)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果枚举 enumeration 不为 null 且不为空，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param enumeration
	 * 		枚举
	 * @param message
	 * 		异常信息
	 */
	public static void notEmpty(final Enumeration<?> enumeration, final String message){
		if(Validate.isNotEmpty(enumeration)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果字符串 str 为 null 或为空字符串或空白字符串，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param str
	 * 		字符串
	 * @param message
	 * 		异常信息
	 */
	public static void isBlank(final String str, final String message){
		if(Validate.isBlank(str)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果字符串 str 不为 null 且不为空字符串且不为空白字符串，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param str
	 * 		字符串
	 * @param message
	 * 		异常信息
	 */
	public static void notBlank(final String str, final String message){
		if(Validate.hasText(str)){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Short 值为 null 或小于 0，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param value
	 * 		Short 值
	 * @param message
	 * 		异常信息
	 */
	public static void isNegative(final Short value, final String message){
		if(value == null || value < 0){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Integer 值为 null 或小于 0，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param value
	 * 		Integer 值
	 * @param message
	 * 		异常信息
	 */
	public static void isNegative(final Integer value, final String message){
		if(value == null || value < 0){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Long 值为 null 或小于 0，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param value
	 * 		Long 值
	 * @param message
	 * 		异常信息
	 */
	public static void isNegative(final Long value, final String message){
		if(value == null || value < 0){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Double 值为 null 或小于 0，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param value
	 * 		Double 值
	 * @param message
	 * 		异常信息
	 */
	public static void isNegative(final Double value, final String message){
		if(value == null || value < 0){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Float 值为 null 或小于 0，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param value
	 * 		Float 值
	 * @param message
	 * 		异常信息
	 */
	public static void isNegative(final Float value, final String message){
		if(value == null || value < 0){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Short 值为 null 或小于等于 0，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param value
	 * 		Short 值
	 * @param message
	 * 		异常信息
	 */
	public static void isZeroNegative(final Short value, final String message){
		if(value == null || value <= 0){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Integer 值为 null 或小于等于 0，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param value
	 * 		Integer 值
	 * @param message
	 * 		异常信息
	 */
	public static void isZeroNegative(final Integer value, final String message){
		if(value == null || value <= 0){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Long 值为 null 或小于等于 0，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param value
	 * 		Long 值
	 * @param message
	 * 		异常信息
	 */
	public static void isZeroNegative(final Long value, final String message){
		if(value == null || value <= 0){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Double 值为 null 或小于等于 0，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param value
	 * 		Double 值
	 * @param message
	 * 		异常信息
	 */
	public static void isZeroNegative(final Double value, final String message){
		if(value == null || value <= 0){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 如果 Float 值为 null 或小于等于 0，抛出信息为 message 的 {@link IllegalArgumentException} 异常
	 *
	 * @param value
	 * 		Float 值
	 * @param message
	 * 		异常信息
	 */
	public static void isZeroNegative(final Float value, final String message){
		if(value == null || value <= 0){
			throw new IllegalArgumentException(message);
		}
	}

}
