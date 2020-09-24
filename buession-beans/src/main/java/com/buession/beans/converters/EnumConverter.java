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
package com.buession.beans.converters;

import com.buession.core.utils.EnumUtils;

import java.lang.reflect.Field;

/**
 * {@link com.buession.beans.converters.Converter} 的 枚举对象的实现，处理 <b>{@link java.lang.Enum}</b> 对象之间的转换的实现。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public final class EnumConverter extends AbstractConverter<Enum> {

	public EnumConverter(){
		super();
	}

	@Override
	@SuppressWarnings({"unchecked"})
	protected Enum convertToType(final Class<?> sourceType, final Class<?> targetType, final Object value) throws Throwable{
		Class<Enum> targetClazz = (Class<Enum>) targetType;

		if(Short.class.isAssignableFrom(sourceType) || Integer.class.isAssignableFrom(sourceType) || Long.class.isAssignableFrom(sourceType)){
			int currentValue = Integer.parseInt(value.toString());
			Field[] fields = targetType.getFields();
			Enum<?>[] enumConstants = targetClazz.getEnumConstants();

			for(int i = 0; i < fields.length; i++){
				if(i == currentValue){
					return enumConstants[i];
				}
			}
		}else if(String.class.isAssignableFrom(sourceType)){
			return EnumUtils.valueOf(targetClazz, value.toString());
		}

		throw conversionException(targetType, value);
	}

}
