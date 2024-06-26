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

import com.buession.core.collect.Arrays;
import com.buession.core.utils.Assert;

/**
 * 数组转换器
 *
 * @param <S>
 * 		原类型
 * @param <T>
 * 		目标类型
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ArrayConverter<S, T> implements Converter<S[], T[]> {

	/**
	 * 数组 item 转换器
	 */
	private final Converter<S, T> itemConverter;

	private final Class<T> clazz;

	/**
	 * 构造函数
	 *
	 * @param itemConverter
	 * 		List item 转换器
	 * @param clazz
	 * 		目标数组类型
	 */
	public ArrayConverter(final Converter<S, T> itemConverter, final Class<T> clazz){
		Assert.isNull(itemConverter, "ItemConverter cloud not be null.");
		Assert.isNull(clazz, "Target clazz cloud not be null.");
		this.itemConverter = itemConverter;
		this.clazz = clazz;
	}

	@Override
	public T[] convert(final S[] source){
		return Arrays.map(source, clazz, itemConverter::convert);
	}

}
