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
package com.buession.web.bind.converter;

import com.buession.core.utils.EnumUtils;
import com.buession.core.validator.Validate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

/**
 * 忽略大小写将字符串转换为枚举值
 *
 * @param <E>
 * 		枚举类型
 *
 * @author Yong.Teng
 * @since 1.2.2
 */
public abstract class AbstractIgnoreCaseEnumConverter<E extends Enum<E>> implements Converter<String, E> {

	/**
	 * 枚举类型
	 */
	private final Class<E> enumType;

	/**
	 * 构造函数
	 *
	 * @param enumType
	 * 		枚举类型
	 */
	public AbstractIgnoreCaseEnumConverter(final Class<E> enumType){
		this.enumType = enumType;
	}

	@Override
	@Nullable
	public E convert(@Nullable String source){
		if(Validate.isEmpty(source)){
			return null;
		}

		return EnumUtils.getEnumIgnoreCase(enumType, source);
	}

}
