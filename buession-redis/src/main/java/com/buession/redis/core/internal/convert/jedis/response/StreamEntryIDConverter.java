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
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.StreamEntryID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * jedis {@link StreamEntryID} 转换为 {@link StreamEntryId}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class StreamEntryIDConverter implements Converter<StreamEntryID, StreamEntryId> {

	public final static StreamEntryIDConverter INSTANCE = new StreamEntryIDConverter();

	public final static ListConverter<StreamEntryID, StreamEntryId> LIST_CONVERTER = new ListConverter<>(INSTANCE);

	@Override
	public StreamEntryId convert(final StreamEntryID source){
		return new StreamEntryId(source.getTime(), source.getSequence());
	}

	public final static class BinaryStreamEntryIdConverter implements Converter<byte[], StreamEntryId> {

		public final static BinaryStreamEntryIdConverter INSTANCE = new BinaryStreamEntryIdConverter();

		@Override
		public StreamEntryId convert(final byte[] source){
			return new StreamEntryId(SafeEncoder.encode(source));
		}

	}

	public final static class MapStreamEntryIdConverter implements
			Converter<Map.Entry<StreamEntryID, List<StreamEntryID>>, Map<StreamEntryId, List<StreamEntryId>>> {

		public final static MapStreamEntryIdConverter INSTANCE = new MapStreamEntryIdConverter();

		private final static ListConverter<StreamEntryID, StreamEntryId> LIST_ENTRY_ID_CONVERTER = new ListConverter<>(
				StreamEntryIDConverter.INSTANCE);

		@Override
		public Map<StreamEntryId, List<StreamEntryId>> convert(
				final Map.Entry<StreamEntryID, List<StreamEntryID>> source){
			final Map<StreamEntryId, List<StreamEntryId>> result = new LinkedHashMap<>();

			if(source.getValue() != null){
				final List<StreamEntryId> streamEntryIdS = LIST_ENTRY_ID_CONVERTER.convert(source.getValue());
				result.put(StreamEntryIDConverter.INSTANCE.convert(source.getKey()), streamEntryIdS);
			}else{
				result.put(StreamEntryIDConverter.INSTANCE.convert(source.getKey()), null);
			}

			return result;
		}

	}

}
