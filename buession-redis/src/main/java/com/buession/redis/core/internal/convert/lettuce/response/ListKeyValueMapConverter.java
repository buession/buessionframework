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
package com.buession.redis.core.internal.convert.lettuce.response;

import com.buession.core.converter.Converter;
import com.buession.lang.KeyValue;
import org.springframework.lang.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Lettuce {@link io.lettuce.core.KeyValue} 转换为 {@link KeyValue}
 *
 * @param <SK>
 * 		原始 Key 类型
 * @param <SV>
 * 		原始值类型
 * @param <TK>
 * 		目标 Key 类型
 * @param <TV>
 * 		目标值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class ListKeyValueMapConverter<SK, SV, TK, TV> implements Converter<List<io.lettuce.core.KeyValue<SK, SV>>,
		Map<TK, TV>> {

	/**
	 * Key 转换器
	 */
	private final Converter<SK, TK> keyConverter;

	/**
	 * 值转换器
	 */
	private final Converter<SV, TV> valueConverter;

	/**
	 * 构造函数
	 *
	 * @param keyConverter
	 * 		Key 转换器
	 * @param valueConverter
	 * 		值转换器
	 */
	public ListKeyValueMapConverter(final Converter<SK, TK> keyConverter, final Converter<SV, TV> valueConverter) {
		this.keyConverter = keyConverter;
		this.valueConverter = valueConverter;
	}

	@Nullable
	@Override
	public Map<TK, TV> convert(final List<io.lettuce.core.KeyValue<SK, SV>> source) {
		if(source == null){
			return null;
		}else if(source.isEmpty()){
			return new LinkedHashMap<>();
		}else{
			return source.stream().collect(Collectors.toMap((item)->keyConverter.convert(item.getKey()),
					(item)->valueConverter.convert(item.getValue()), (oldVal, newVal)->oldVal, LinkedHashMap::new));
		}
	}

}
