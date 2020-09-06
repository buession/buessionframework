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
package com.buession.core.converter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Map 转换器
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
 * @see 1.3.0
 */
public class MapConverter<SK, SV, TK, TV> implements Converter<Map<SK, SV>, Map<TK, TV>> {

	/**
	 * Map key 转换器
	 */
	private Converter<SK, TK> keyConverter;

	/**
	 * Map value 转换器
	 */
	private Converter<SV, TV> valueConverter;

	/**
	 * 构造函数
	 *
	 * @param keyConverter
	 * 		Map key 转换器
	 * @param valueConverter
	 * 		Map value 转换器
	 */
	public MapConverter(final Converter<SK, TK> keyConverter, final Converter<SV, TV> valueConverter){
		this.keyConverter = keyConverter;
		this.valueConverter = valueConverter;
	}

	@Override
	public Map<TK, TV> convert(final Map<SK, SV> source){
		if(source == null){
			return null;
		}else{
			return source.entrySet().stream().collect(Collectors.toMap(e->keyConverter.convert(e.getKey()),
					e->valueConverter.convert(e.getValue()), (a, b)->a, source instanceof LinkedHashMap ?
							LinkedHashMap::new : HashMap::new));
		}
	}

}
