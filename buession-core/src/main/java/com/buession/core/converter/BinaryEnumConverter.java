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
package com.buession.core.converter;

import com.buession.core.utils.Assert;
import com.buession.core.utils.EnumUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 枚举转换器，将 byte 数组转换为枚举
 *
 * @param <E>
 * 		枚举类型
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
public class BinaryEnumConverter<E extends Enum<E>> implements Converter<byte[], E> {

	private final Class<E> enumType;

	private final Charset charset;

	/**
	 * 构造函数
	 *
	 * @param enumType
	 * 		枚举类型
	 */
	public BinaryEnumConverter(final Class<E> enumType){
		this(enumType, StandardCharsets.UTF_8);
	}

	/**
	 * 构造函数
	 *
	 * @param enumType
	 * 		枚举类型
	 * @param charset
	 * 		字符串
	 */
	public BinaryEnumConverter(final Class<E> enumType, final Charset charset){
		this.enumType = enumType;
		this.charset = charset;
	}

	@Override
	public E convert(final byte[] source){
		Assert.isNull(source, "Source byte cloud not be null.");
		final String str = new String(source, charset);

		E result = EnumUtils.valueOf(enumType, str);

		if(result == null){
			result = EnumUtils.valueOf(enumType, str.toUpperCase());
		}

		if(result == null){
			result = EnumUtils.valueOf(enumType, str.toLowerCase());
		}

		return result;
	}

}
