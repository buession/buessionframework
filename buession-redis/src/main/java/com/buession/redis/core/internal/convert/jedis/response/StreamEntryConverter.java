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
package com.buession.redis.core.internal.convert.jedis.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapEntryMapConverter;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;

import java.util.List;
import java.util.Map;

/**
 * jedis {@link redis.clients.jedis.resps.StreamEntry} 转换为 {@link StreamEntry}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class StreamEntryConverter implements Converter<redis.clients.jedis.resps.StreamEntry, StreamEntry> {

	private final StreamEntryIDConverter streamEntryIDConverter = new StreamEntryIDConverter();

	@Override
	public StreamEntry convert(final redis.clients.jedis.resps.StreamEntry source) {
		final StreamEntryId id = streamEntryIDConverter.convert(source.getID());
		return new StreamEntry(id, source.getFields());
	}

	/**
	 * Jedis {@link List} {@link redis.clients.jedis.resps.StreamEntry} 转换为 {@link List} {@link StreamEntry}
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	public final static class ListStreamEntryConverter extends ListConverter<redis.clients.jedis.resps.StreamEntry,
			StreamEntry> {

		public ListStreamEntryConverter() {
			super(new StreamEntryConverter());
		}

	}

	/**
	 * Jedis key {@link Map.Entry<SK, List<redis.clients.jedis.resps.StreamEntry>>} 转换为
	 * {@link Map<TK, List<StreamEntry>>}
	 *
	 * @param <SK>
	 * 		转换器 key 类型
	 * @param <TK>
	 * 		目标 key 类型
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	public final static class MapEntryStreamEntryConverter<SK, TK> extends
			MapEntryMapConverter<SK, List<redis.clients.jedis.resps.StreamEntry>, TK, List<StreamEntry>> {

		public MapEntryStreamEntryConverter(final Converter<SK, TK> keyConverter) {
			super(keyConverter, new StreamEntryConverter.ListStreamEntryConverter());
		}

	}

	/**
	 * Jedis {@link List} key {@link Map.Entry<SK, List<redis.clients.jedis.resps.StreamEntry>>} 转换为
	 * {@link List} {@link Map<TK, List<StreamEntry>>}
	 *
	 * @param <SK>
	 * 		转换器 key 类型
	 * @param <TK>
	 * 		目标 key 类型
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	public final static class ListMapEntryStreamEntryConverter<SK, TK> extends
			ListConverter<Map.Entry<SK, List<redis.clients.jedis.resps.StreamEntry>>, Map<TK, List<StreamEntry>>> {

		public ListMapEntryStreamEntryConverter(final Converter<SK, TK> keyConverter) {
			super(new MapEntryStreamEntryConverter<>(keyConverter));
		}

	}

}
