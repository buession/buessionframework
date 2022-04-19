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

/**
 * 枚举转换器，将字符串转换为枚举
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
public class EnumConverter<E extends Enum<E>> implements Converter<String, E> {

	private final Class<E> enumType;

	/**
	 * 构造函数
	 *
	 * @param enumType
	 * 		枚举类型
	 */
	public EnumConverter(final Class<E> enumType){
		this.enumType = enumType;
	}

	@Override
	public E convert(final String source){
		Assert.isNull(source, "String cloud not be null.");

		E result = EnumUtils.valueOf(enumType, source);

		if(result == null){
			result = EnumUtils.valueOf(enumType, source.toUpperCase());
		}

		if(result == null){
			result = EnumUtils.valueOf(enumType, source.toLowerCase());
		}

		return result;
	}

}
