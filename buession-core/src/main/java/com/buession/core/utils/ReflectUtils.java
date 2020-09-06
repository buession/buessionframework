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
package com.buession.core.utils;

import com.buession.core.exception.InsteadException;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射工具类 More {@link ReflectionUtils}
 *
 * @author Yong.Teng
 */
public class ReflectUtils extends ReflectionUtils {

	/**
	 * Map 转换成 pojo 类
	 *
	 * @param data
	 * 		Map 对象
	 * @param clazz
	 * 		pojo 类类型 Class
	 * @param <E>
	 * 		pojo 类类型
	 *
	 * @return pojo 类对象
	 */
	@Deprecated
	public static <E> E mapConvertObject(@Nullable Map<String, Object> data, @Nullable Class<E> clazz){
		throw new InsteadException(MethodUtils.getAccessibleMethod(ReflectUtils.class, "mapConvertObject"), "com" +
				".buession", "buession-beans", "1.2.0", "com.buession.beans.DefaultBeanResolver", "populate");
	}

	@Deprecated
	public static <E> E setter(@Nullable Map<String, Object> data, @Nullable Class<E> clazz){
		return mapConvertObject(data, clazz);
	}

	@Deprecated
	public static <E> E setter(@Nullable Map<String, Object> data, @Nullable E object){
		return mapConvertObject(data, null);
	}

	@Deprecated
	public static <E> E setter(@Nullable Map<String, Object> data, @Nullable Class<E> clazz, @Nullable E object){
		return setter(data, object);
	}

	/**
	 * 将对象转换为 Map
	 *
	 * @param object
	 * 		对象
	 * @param <T>
	 * 		对象类型
	 *
	 * @return Map 对象
	 */
	public static <T> Map<String, Object> objectConvertMap(final T object){
		BeanMap map = new BeanMap(object);
		Map<String, Object> result = new HashMap<>(map.size() - 1);

		map.forEach((key, value)->{
			String k = String.valueOf(key);

			if("class".equals(k) == false){
				result.put(k, value);
			}
		});

		return Collections.unmodifiableMap(result);
	}

	/**
	 * 将对象转换为 Map
	 *
	 * @param object
	 * 		对象
	 * @param <T>
	 * 		对象类型
	 *
	 * @return Map 对象
	 */
	@Deprecated
	public static <T> Map<String, Object> classConvertMap(final T object){
		return objectConvertMap(object);
	}

	/**
	 * 将对象转换为 Map
	 *
	 * @param clazz
	 * 		对象类型
	 * @param object
	 * 		对象
	 * @param <T>
	 * 		对象类型
	 *
	 * @return Map 对象
	 */
	@Deprecated
	public static <T> Map<String, Object> classConvertMap(Class<T> clazz, T object){
		return classConvertMap(object);
	}

}
