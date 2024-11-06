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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.converter;

import com.buession.core.builder.MapBuilder;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * {@link Map.Entry} 到 {@link Map} 转换器
 *
 * @param <SK>
 * 		Map 原 key 类型
 * @param <SV>
 * 		Map 原 value 类型
 * @param <TK>
 * 		Map 目标 key 类型
 * @param <TV>
 * 		Map 目标 value 类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class MapEntryMapConverter<SK, SV, TK, TV> implements Converter<Map.Entry<SK, SV>, Map<TK, TV>> {

	/**
	 * Map key 转换器
	 */
	private final Converter<SK, TK> keyConverter;

	/**
	 * Map value 转换器
	 */
	private final Converter<SV, TV> valueConverter;

	/**
	 * 构造函数
	 *
	 * @param keyConverter
	 * 		Map key 转换器
	 * @param valueConverter
	 * 		Map value 转换器
	 */
	public MapEntryMapConverter(final Converter<SK, TK> keyConverter, final Converter<SV, TV> valueConverter) {
		this.keyConverter = keyConverter;
		this.valueConverter = valueConverter;
	}

	@Nullable
	@Override
	public Map<TK, TV> convert(final Map.Entry<SK, SV> source) {
		if(source == null){
			return null;
		}else{
			return MapBuilder.of(keyConverter.convert(source.getKey()), valueConverter.convert(source.getValue()));
		}
	}

}
