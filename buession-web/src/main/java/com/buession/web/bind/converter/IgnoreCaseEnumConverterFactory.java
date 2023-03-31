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
package com.buession.web.bind.converter;

import com.buession.core.utils.Assert;
import com.buession.core.utils.EnumUtils;
import com.buession.core.validator.Validate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.Nullable;

/**
 * 忽略大小写将字符串转换为枚举值
 *
 * @author Yong.Teng
 * @since 2.2.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class IgnoreCaseEnumConverterFactory implements ConverterFactory<String, Enum> {

	@Override
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType){
		return new StringToEnum(getEnumType(targetType));
	}

	private static Class<?> getEnumType(Class<?> targetType){
		Class<?> enumType = targetType;

		while(enumType != null && !enumType.isEnum()){
			enumType = enumType.getSuperclass();
		}

		Assert.isNull(enumType, "The target type " + targetType.getName() + " does not refer to an enum");

		return enumType;
	}


	private static class StringToEnum<T extends Enum<T>> implements Converter<String, T> {

		private final Class<T> enumType;

		public StringToEnum(Class<T> enumType){
			this.enumType = enumType;
		}

		@Override
		@Nullable
		public T convert(String source){
			if(Validate.isEmpty(source)){
				return null;
			}

			return (T) EnumUtils.getEnumIgnoreCase(enumType, source.trim());
		}
	}

}
