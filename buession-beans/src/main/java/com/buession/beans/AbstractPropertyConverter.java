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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.beans;

import com.buession.core.exception.ConversionException;
import com.buession.core.utils.StringUtils;
import com.buession.lang.Primitive;

import java.lang.reflect.ParameterizedType;

/**
 * Bean 属性转换器抽象类
 *
 * @param <T>
 * 		目标类型
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public abstract class AbstractPropertyConverter<T> implements BeanPropertyConverter<T> {

	private final static String PROPERTY_CONVERTER_PACKAGE = "com.buession.beans.converters.";

	@Override
	public T convert(Object value) {
		if(value == null){
			return null;
		}

		Class<T> type = getType();

		final Class<?> sourceType = value.getClass();
		final Class<T> targetType = Primitive.primitiveToWrapper(type);

		if(targetType.equals(sourceType)){
			return targetType.cast(value);
		}else{
			return targetType.cast(convertToType(value.getClass(), value, type));
		}
	}

	protected abstract T convertToType(final Class<?> sourceType, final Object value, final Class<T> targetType);

	protected ConversionException conversionException(final Class<?> sourceTYpe, final Object value,
													  final Class<T> targetType, final Object... args) {
		return new ConversionException(
				String.format("Can't convert value '" + value + "' to type " + targetType, args));
	}

	@SuppressWarnings("unchecked")
	private Class<T> getType() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected static String toString(final Class<?> type) {
		if(type == null){
			return "null";
		}

		String typeName;

		if(type.isArray()){
			Class<?> elementType = type.getComponentType();
			int count = 1;

			while(elementType.isArray()){
				elementType = elementType.getComponentType();
				count++;
			}

			typeName = elementType.getName() + StringUtils.repeat("[]", count);
		}else{
			typeName = type.getName();
		}

		if(typeName.startsWith("java.lang.") || typeName.startsWith("java.util.") ||
				typeName.startsWith("java.math.")){
			typeName = typeName.substring("java.lang.".length());
		}else if(typeName.startsWith(PROPERTY_CONVERTER_PACKAGE)){
			typeName = typeName.substring(PROPERTY_CONVERTER_PACKAGE.length());
		}

		return typeName;
	}

}
