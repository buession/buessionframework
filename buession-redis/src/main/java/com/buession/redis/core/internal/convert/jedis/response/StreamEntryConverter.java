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
package com.buession.redis.core.internal.convert.jedis.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import redis.clients.jedis.StreamEntryID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * jedis {@link redis.clients.jedis.resps.StreamEntry} 转换为 {@link StreamEntry}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class StreamEntryConverter implements Converter<redis.clients.jedis.resps.StreamEntry, StreamEntry> {

	public final static StreamEntryConverter INSTANCE = new StreamEntryConverter();

	public final static ListConverter<redis.clients.jedis.resps.StreamEntry, StreamEntry> LIST_CONVERTER = new ListConverter<>(
			INSTANCE);

	@Override
	public StreamEntry convert(final redis.clients.jedis.resps.StreamEntry source){
		final StreamEntryId id = StreamEntryIDConverter.INSTANCE.convert(source.getID());
		return new StreamEntry(id, source.getFields());
	}

	public final static class MapStreamEntryConverter<SK, TK> implements
			Converter<Map.Entry<SK, List<redis.clients.jedis.resps.StreamEntry>>, Map<TK, List<StreamEntry>>> {

		public final static MapStreamEntryConverter<StreamEntryID, StreamEntryId> STREAMENTRYID_KEY_MAP_CONVERTER = new MapStreamEntryConverter<>(
				StreamEntryIDConverter.INSTANCE);

		public final static MapStreamEntryConverter<String, String> STRING_KEY_MAP_CONVERTER = new MapStreamEntryConverter<>(
				(key)->key);

		private final Converter<SK, TK> keyConverter;

		public MapStreamEntryConverter(final Converter<SK, TK> keyConverter){
			this.keyConverter = keyConverter;
		}

		@Override
		public Map<TK, List<StreamEntry>> convert(
				final Map.Entry<SK, List<redis.clients.jedis.resps.StreamEntry>> source){
			final Map<TK, List<StreamEntry>> result = new LinkedHashMap<>();
			final TK key = keyConverter.convert(source.getKey());

			if(source.getValue() != null){
				result.put(key, StreamEntryConverter.LIST_CONVERTER.convert(source.getValue()));
			}else{
				result.put(key, null);
			}

			return result;
		}

	}

	public final static class ListMapStreamEntryConverter<SK, TK> extends
			ListConverter<Map.Entry<SK, List<redis.clients.jedis.resps.StreamEntry>>, Map<TK, List<StreamEntry>>> {

		public final static ListMapStreamEntryConverter<String, String> STRING_KEY_MAP_CONVERTER = new ListMapStreamEntryConverter<>(
				(key)->key);

		public final static ListMapStreamEntryConverter<byte[], byte[]> BINARY_KEY_MAP_CONVERTER = new ListMapStreamEntryConverter<>(
				(key)->key);

		public ListMapStreamEntryConverter(final Converter<SK, TK> keyConverter){
			super((item)->{
				final MapStreamEntryConverter<SK, TK> converter = new MapStreamEntryConverter<>(keyConverter);
				return converter.convert(item);
			});
		}

	}

}
