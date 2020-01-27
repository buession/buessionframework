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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yong.Teng
 */
public class ReflectUtils extends ReflectionUtils {

	private final static Map<Class<?>, Object> CLASS_SETTER_CACHE = new ConcurrentHashMap<>(64, 0.8F);

	private final static Map<Class<?>, Map<String, Object>> CLASS_MAP_CACHE = new ConcurrentHashMap<>(64, 0.8F);

	private final static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

	protected ReflectUtils(){

	}

	/**
	 * 设置属性权限
	 *
	 * @param field
	 * 		属性
	 */
	public static void setFieldAccessible(Field field){
		makeAccessible(field);
	}

	/**
	 * 判断属性是否为静态属性
	 *
	 * @param field
	 * 		属性
	 *
	 * @return 属性是为静态属性，返回 true；否则返回 false
	 */
	public static boolean isStaticField(Field field){
		return field != null && Modifier.isStatic(field.getModifiers());
	}

	/**
	 * 判断方法是否为静态方法
	 *
	 * @param method
	 * 		方法
	 *
	 * @return 方法是为静态方法，返回 true；否则返回 false
	 */
	public static boolean isStaticMethod(Method method){
		return method != null && Modifier.isStatic(method.getModifiers());
	}

	/**
	 * 给对象属性的赋于新值
	 *
	 * @param object
	 * 		对象
	 * @param field
	 * 		属性
	 * @param value
	 * 		值
	 */
	public static void setField(@Nullable Object object, @Nullable Field field, Object value){
		setFieldAccessible(field);
		setField(field, object, value);
	}

	/**
	 * 给对象属性的赋于新值
	 *
	 * @param object
	 * 		对象
	 * @param fieldName
	 * 		属性名称
	 * @param value
	 * 		值
	 */
	public static void setField(@Nullable Object object, @Nullable String fieldName, Object value){
		setField(object.getClass(), object, fieldName, value);
	}

	/**
	 * 给对象属性的赋于新值
	 *
	 * @param clazz
	 * 		对象类型
	 * @param object
	 * 		对象
	 * @param fieldName
	 * 		属性类型
	 * @param value
	 * 		值
	 * @param <T>
	 * 		对象类型
	 */
	public static <T> void setField(@Nullable Class<T> clazz, @Nullable Object object, @Nullable String fieldName,
									Object value){
		Field field = findField(clazz, fieldName);
		setField(object, field, value);
	}

	/**
	 * 给对象属性的赋于新值
	 *
	 * @param object
	 * 		对象
	 * @param fieldName
	 * 		属性名称
	 * @param fieldType
	 * 		属性类型
	 * @param value
	 * 		值
	 */
	public static void setField(@Nullable Object object, @Nullable String fieldName, @Nullable Class<?> fieldType,
								Object value){
		setField(object.getClass(), object, fieldName, fieldType, value);
	}

	/**
	 * 给对象属性的赋于新值
	 *
	 * @param clazz
	 * 		对象类型
	 * @param object
	 * 		对象
	 * @param fieldName
	 * 		属性名称
	 * @param fieldType
	 * 		属性类型
	 * @param value
	 * 		值
	 * @param <T>
	 * 		对象类型
	 */
	public static <T> void setField(@Nullable Class<T> clazz, @Nullable Object object, @Nullable String fieldName,
									@Nullable Class<?> fieldType, Object value){
		Field field = findField(clazz, fieldName, fieldType);
		setField(object, field, value);
	}

	/**
	 * 获取对象属性值
	 *
	 * @param object
	 * 		对象
	 * @param field
	 * 		属性
	 * @param <T>
	 * 		对象类型
	 */
	public static <T> T getField(@Nullable Object object, @Nullable Field field){
		setFieldAccessible(field);
		return (T) getField(field, object);
	}

	/**
	 * 获取对象属性值
	 *
	 * @param object
	 * 		对象
	 * @param fieldName
	 * 		属性名称
	 * @param <T>
	 * 		对象类型
	 */
	public static <T> T getField(@Nullable Object object, @Nullable String fieldName){
		return (T) getField(object.getClass(), object, fieldName);
	}

	/**
	 * 获取对象属性值
	 *
	 * @param clazz
	 * 		对象类型
	 * @param object
	 * 		对象
	 * @param fieldName
	 * 		属性名称
	 * @param <T>
	 * 		对象类型
	 */
	public static <T> T getField(@Nullable Class<T> clazz, @Nullable Object object, @Nullable String fieldName){
		Field field = findField(clazz, fieldName);
		return (T) getField(object, field);
	}

	/**
	 * 获取对象属性值
	 *
	 * @param object
	 * 		对象
	 * @param fieldName
	 * 		属性名称
	 * @param fieldType
	 * 		属性类型
	 * @param <T>
	 * 		对象类型
	 */
	public static <T> T getField(@Nullable Object object, @Nullable String fieldName, @Nullable Class<?> fieldType){
		return (T) getField(object.getClass(), object, fieldName, fieldType);
	}

	/**
	 * 获取对象属性值
	 *
	 * @param clazz
	 * 		对象类型
	 * @param object
	 * 		对象
	 * @param fieldName
	 * 		属性名称
	 * @param fieldType
	 * 		属性类型
	 * @param <T>
	 * 		对象类型
	 */
	public static <T> T getField(@Nullable Class<T> clazz, @Nullable Object object, @Nullable String fieldName,
								 @Nullable Class<?> fieldType){
		Field field = findField(clazz, fieldName, fieldType);
		return (T) getField(object, field);
	}

	public static <E> E setter(@Nullable Map<String, Object> data, @Nullable Class<E> clazz){
		Assert.isNull(data, "Data cloud not be null.");
		Assert.isNull(clazz, "Class cloud not be null.");

		try{
			return setter(data, clazz, clazz.newInstance());
		}catch(InstantiationException e){
			logger.error("{}", e.getMessage());
		}catch(IllegalAccessException e){
			logger.error("{}", e.getMessage());
		}

		return null;
	}

	public static <E> E setter(@Nullable Map<String, Object> data, @Nullable E object){
		Assert.isNull(object, "Object cloud not be null.");
		return setter(data, (Class<E>) object.getClass(), object);
	}

	public static <E> E setter(@Nullable Map<String, Object> data, @Nullable Class<E> clazz, @Nullable E object){
		Assert.isNull(data, "Data cloud not be null.");
		Assert.isNull(object, "Object cloud not be null.");

		if(clazz == null){
			clazz = (Class<E>) object.getClass();
		}

		object = (E) CLASS_SETTER_CACHE.get(clazz);
		if(object != null){
			return object;
		}

		try{
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for(PropertyDescriptor propertyDescriptor : propertyDescriptors){
				String propertyName = propertyDescriptor.getName();

				if(data.containsKey(propertyName) == false){
					continue;
				}

				Method method = propertyDescriptor.getWriteMethod();
				Type genericParameterType = method.getGenericParameterTypes()[0];
				Object value = data.get(propertyName);
				boolean isSet = false;

				try{
					if((genericParameterType == Short.TYPE || genericParameterType == Short.class)){
						if((value instanceof Short || value instanceof Integer || value instanceof Long)){
							long lv = ((Number) value).longValue();

							if(lv >= Short.MIN_VALUE && lv <= Short.MAX_VALUE){
								invokeMethod(method, object, new Object[]{((Number) value).shortValue()});
								isSet = true;
							}
						}
					}else if((genericParameterType == Integer.TYPE || genericParameterType == Integer.class)){
						if((value instanceof Short || value instanceof Integer || value instanceof Long)){
							long lv = ((Number) value).longValue();

							if(lv >= Integer.MIN_VALUE && lv <= Integer.MAX_VALUE){
								invokeMethod(method, object, new Object[]{((Number) value).intValue()});
								isSet = true;
							}
						}
					}else if((genericParameterType == Long.TYPE || genericParameterType == Long.class)){
						if((value instanceof Short || value instanceof Integer || value instanceof Long)){
							long lv = ((Number) value).longValue();

							if(lv >= Long.MIN_VALUE && lv <= Long.MAX_VALUE){
								invokeMethod(method, object, new Object[]{((Number) value).longValue()});
								isSet = true;
							}
						}
					}else if((genericParameterType == Float.TYPE || genericParameterType == Float.class)){
						if((value instanceof Short || value instanceof Integer || value instanceof Long || value
								instanceof Double)){
							invokeMethod(method, object, new Object[]{((Number) value).floatValue()});
							isSet = true;
						}
					}else if((genericParameterType == Double.TYPE || genericParameterType == Double.class)){
						if((value instanceof Short || value instanceof Integer || value instanceof Long || value
								instanceof Float || value instanceof Double)){
							invokeMethod(method, object, new Object[]{((Number) value).doubleValue()});
							isSet = true;
						}
					}

					if(isSet == false){
						invokeMethod(method, object, new Object[]{value});
					}
				}catch(IllegalArgumentException e){
					logger.error("Get {} failure: {}", propertyName, e.getMessage());
				}
			}

			CLASS_SETTER_CACHE.put(clazz, object);
		}catch(IntrospectionException e){
			logger.error("{}", e.getMessage());
		}

		return object;
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
	public static <T> Map<String, Object> classConvertMap(final T object){
		return classConvertMap(null, object);
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
	public static <T> Map<String, Object> classConvertMap(Class<T> clazz, T object){
		if(object == null){
			return null;
		}

		if(clazz == null){
			clazz = (Class<T>) object.getClass();
		}

		Map<String, Object> result = CLASS_MAP_CACHE.get(clazz);
		if(result != null){
			return result;
		}

		try{
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			result = new HashMap<>(propertyDescriptors.length);

			for(PropertyDescriptor propertyDescriptor : propertyDescriptors){
				String key = propertyDescriptor.getName();

				if("class".equals(key) == false){
					Method getter = propertyDescriptor.getReadMethod();
					result.put(key, invokeMethod(getter, object));
				}
			}

			CLASS_MAP_CACHE.put(clazz, result);
		}catch(IntrospectionException e){
			logger.error("{}", e.getMessage());
		}

		return null;
	}

}
