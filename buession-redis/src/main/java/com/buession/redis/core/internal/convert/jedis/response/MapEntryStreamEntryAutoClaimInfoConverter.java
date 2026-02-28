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
package com.buession.redis.core.internal.convert.jedis.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.redis.core.AutoClaimInfo;
import com.buession.redis.core.XReadGroupInfo;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

/**
 * {@link Map.Entry} 转换为 {@link AutoClaimInfo}
 *
 * @param <SK>
 * 		原始 Key 类型
 * @param <TK>
 * 		目标 Key 类型
 * @param <TV>
 * 		目标值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class MapEntryStreamEntryAutoClaimInfoConverter<SK, TK, TV>
		implements Converter<Map.Entry<SK, List<redis.clients.jedis.resps.StreamEntry>>, AutoClaimInfo<TK, TV>> {

	/**
	 * key 转换器
	 */
	private final Converter<SK, TK> keyConverter;

	/**
	 * Entry key 转换器
	 */
	private final Converter<String, TK> entryKeyConverter;

	/**
	 * Entry value 转换器
	 */
	private final Converter<String, TV> entryValueConverter;

	/**
	 * 构造函数
	 *
	 * @param keyConverter
	 * 		key 转换器
	 * @param entryKeyConverter
	 * 		Entry key 转换器
	 * @param entryValueConverter
	 * 		Entry value 转换器
	 */
	public MapEntryStreamEntryAutoClaimInfoConverter(final Converter<SK, TK> keyConverter,
													 final Converter<String, TK> entryKeyConverter,
													 final Converter<String, TV> entryValueConverter) {
		this.keyConverter = keyConverter;
		this.entryKeyConverter = entryKeyConverter;
		this.entryValueConverter = entryValueConverter;
	}

	@Nullable
	@Override
	public AutoClaimInfo<TK, TV> convert(final Map.Entry<SK, List<redis.clients.jedis.resps.StreamEntry>> source) {
		if(source == null){
			return null;
		}

		final ListConverter<redis.clients.jedis.resps.StreamEntry, com.buession.redis.core.StreamEntry<TK, TV>> listConverter =
				new ListConverter<>(new StreamEntryConverter<>(entryKeyConverter, entryValueConverter));
		return new AutoClaimInfo<>(keyConverter.convert(source.getKey()), listConverter.convert(source.getValue()));
	}

}
