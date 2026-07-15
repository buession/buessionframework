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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.converter;

import com.buession.lang.KeyValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * {@link Map} 转换为 {@link KeyValue} {@link Collection}
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
 * @since 4.0.0
 */
public class MapCollectionKeyValueConverter<SK, SV, TK, TV>
		extends BaseKeyValueConverter<SK, SV, TK, TV, Map<SK, SV>, Collection<KeyValue<TK, TV>>> {

	/**
	 * 构造函数
	 *
	 * @param keyConverter
	 * 		Key 转换器
	 * @param valueConverter
	 * 		值转换器
	 */
	public MapCollectionKeyValueConverter(final Converter<SK, TK> keyConverter,
	                                      final Converter<SV, TV> valueConverter) {
		super(keyConverter, valueConverter);
	}

	@Override
	public Collection<KeyValue<TK, TV>> convert(final Map<SK, SV> source) {
		if(source == null){
			return null;
		}else{
			final Collection<KeyValue<TK, TV>> result = new ArrayList<>(source.size());

			for(Map.Entry<SK, SV> e : source.entrySet()){
				result.add(new KeyValue<>(keyConverter.convert(e.getKey()), valueConverter.convert(e.getValue())));
			}

			return result;
		}
	}

}
