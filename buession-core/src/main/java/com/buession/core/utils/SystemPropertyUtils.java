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

/**
 * System Property 工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class SystemPropertyUtils {

	/**
	 * 设置 short 类型系统属性
	 *
	 * @param name
	 * 		属性名称
	 * @param value
	 * 		属性值
	 *
	 * @return short 类型字符串值
	 */
	public static String setProperty(final String name, final short value){
		return setProperty(name, Short.toString(value));
	}

	/**
	 * 设置 int 类型系统属性
	 *
	 * @param name
	 * 		属性名称
	 * @param value
	 * 		属性值
	 *
	 * @return int 类型字符串值
	 */
	public static String setProperty(final String name, final int value){
		return setProperty(name, Integer.toString(value));
	}

	/**
	 * 设置 long 类型系统属性
	 *
	 * @param name
	 * 		属性名称
	 * @param value
	 * 		属性值
	 *
	 * @return long 类型字符串值
	 */
	public static String setProperty(final String name, final long value){
		return setProperty(name, Long.toString(value));
	}

	/**
	 * 设置布尔型系统属性
	 *
	 * @param name
	 * 		属性名称
	 * @param value
	 * 		属性值
	 *
	 * @return 布尔型字符串值
	 */
	public static String setProperty(final String name, final boolean value){
		return setProperty(name, Boolean.toString(value));
	}

	/**
	 * 设置 char 类型系统属性
	 *
	 * @param name
	 * 		属性名称
	 * @param value
	 * 		属性值
	 *
	 * @return char 类型字符串值
	 */
	public static String setProperty(final String name, final char value){
		return setProperty(name, Character.toString(value));
	}

	/**
	 * 设置系统属性
	 *
	 * @param name
	 * 		属性名称
	 * @param value
	 * 		属性值
	 *
	 * @return 属性值
	 */
	public static String setProperty(final String name, final String value){
		return System.setProperty(name, value);
	}

	/**
	 * 设置系统属性
	 *
	 * @param name
	 * 		属性名称
	 * @param value
	 * 		属性值
	 *
	 * @return 属性值
	 */
	public static String setPropertyIfPresent(final String name, final String value){
		if(value != null){
			return System.setProperty(name, value);
		}

		return value;
	}

}
