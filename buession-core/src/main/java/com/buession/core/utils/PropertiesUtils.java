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

import java.util.Properties;

/**
 * Properties 工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class PropertiesUtils {

	/**
	 * 获取 {@link Properties} 指定 key 的值
	 *
	 * @param properties
	 *        {@link Properties}
	 * @param key
	 * 		key
	 *
	 * @return {@link Properties} 指定 key 的值
	 */
	public static String get(final Properties properties, final String key){
		return properties.getProperty(key);
	}

	/**
	 * 获取 {@link Properties} 指定 key 的 Short 类型值
	 *
	 * @param properties
	 *        {@link Properties}
	 * @param key
	 * 		key
	 *
	 * @return {@link Properties} 指定 key 的 Short 类型值
	 */
	public static Short getShort(final Properties properties, final String key){
		String str = properties.getProperty(key);
		return Validate.hasText(str) ? Short.parseShort(str) : null;
	}

	/**
	 * 获取 {@link Properties} 指定 key 的 Integer 类型值
	 *
	 * @param properties
	 *        {@link Properties}
	 * @param key
	 * 		key
	 *
	 * @return {@link Properties} 指定 key 的 Integer 类型值
	 */
	public static Integer getInt(final Properties properties, final String key){
		return getInteger(properties, key);
	}

	/**
	 * 获取 {@link Properties} 指定 key 的 Integer 类型值
	 *
	 * @param properties
	 *        {@link Properties}
	 * @param key
	 * 		key
	 *
	 * @return {@link Properties} 指定 key 的 Integer 类型值
	 */
	public static Integer getInteger(final Properties properties, final String key){
		String str = properties.getProperty(key);
		return Validate.hasText(str) ? Integer.parseInt(str) : null;
	}

	/**
	 * 获取 {@link Properties} 指定 key 的 Long 类型值
	 *
	 * @param properties
	 *        {@link Properties}
	 * @param key
	 * 		key
	 *
	 * @return {@link Properties} 指定 key 的 Long 类型值
	 */
	public static Long getLong(final Properties properties, final String key){
		String str = properties.getProperty(key);
		return Validate.hasText(str) ? Long.parseLong(str) : null;
	}

	/**
	 * 获取 {@link Properties} 指定 key 的 Float 类型值
	 *
	 * @param properties
	 *        {@link Properties}
	 * @param key
	 * 		key
	 *
	 * @return {@link Properties} 指定 key 的 Float 类型值
	 */
	public static Float getFloat(final Properties properties, final String key){
		String str = properties.getProperty(key);
		return Validate.hasText(str) ? Float.parseFloat(str) : null;
	}

	/**
	 * 获取 {@link Properties} 指定 key 的 Double 类型值
	 *
	 * @param properties
	 *        {@link Properties}
	 * @param key
	 * 		key
	 *
	 * @return {@link Properties} 指定 key 的 Double 类型值
	 */
	public static Double getDouble(final Properties properties, final String key){
		String str = properties.getProperty(key);
		return Validate.hasText(str) ? Double.parseDouble(str) : null;
	}

	/**
	 * 获取 {@link Properties} 指定 key 的 Boolean 类型值
	 *
	 * @param properties
	 *        {@link Properties}
	 * @param key
	 * 		key
	 *
	 * @return {@link Properties} 指定 key 的 Boolean 类型值
	 */
	public static Boolean getBoolean(final Properties properties, final String key){
		String str = properties.getProperty(key);

		if(Validate.hasText(str) == false){
			return null;
		}

		if(Boolean.parseBoolean(str)){
			return true;
		}

		return "0".equals(str) == false || Validate.isNumeric(str) && Integer.parseInt(str) != 0;
	}

}
