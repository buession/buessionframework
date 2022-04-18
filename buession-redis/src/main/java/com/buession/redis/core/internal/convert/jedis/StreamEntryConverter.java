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
package com.buession.redis.core.internal.convert.jedis;

import com.buession.core.converter.Converter;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import redis.clients.jedis.StreamEntryID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * jedis {@link redis.clients.jedis.resps.StreamEntry} 转换为 {@link StreamEntry}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class StreamEntryConverter implements Converter<redis.clients.jedis.resps.StreamEntry, StreamEntry> {

	private final static StreamEntryIdConverter STREAM_ENTRY_ID_CONVERTER = new StreamEntryIdConverter();

	@Override
	public StreamEntry convert(final redis.clients.jedis.resps.StreamEntry source){
		return new StreamEntry(STREAM_ENTRY_ID_CONVERTER.convert(source.getID()), source.getFields());
	}

	final static class MapStreamEntryConverter implements
			Converter<Map.Entry<StreamEntryID, List<redis.clients.jedis.resps.StreamEntry>>, Map<StreamEntryId, List<StreamEntry>>> {

		private final static StreamEntryConverter STREAM_ENTRY_CONVERTER = new StreamEntryConverter();

		@Override
		public Map<StreamEntryId, List<StreamEntry>> convert(
				final Map.Entry<StreamEntryID, List<redis.clients.jedis.resps.StreamEntry>> source){
			final Map<StreamEntryId, List<StreamEntry>> result = new LinkedHashMap<>();

			if(source.getValue() != null){
				final List<StreamEntry> streamEntries = source.getValue().stream()
						.map(STREAM_ENTRY_CONVERTER::convert).collect(Collectors.toList());
				result.put(STREAM_ENTRY_ID_CONVERTER.convert(source.getKey()), streamEntries);
			}else{
				result.put(STREAM_ENTRY_ID_CONVERTER.convert(source.getKey()), null);
			}

			return result;
		}

	}

}
