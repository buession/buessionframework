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
package com.buession.redis.core.internal.convert.jedis.params;

import com.buession.core.converter.ArrayConverter;
import com.buession.core.converter.Converter;
import com.buession.core.converter.MapConverter;
import com.buession.redis.core.StreamEntryId;
import redis.clients.jedis.StreamEntryID;

/**
 * {@link StreamEntryId} 转换为 jedis {@link StreamEntryID}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class StreamEntryIdConverter implements Converter<StreamEntryId, StreamEntryID> {

	public final static StreamEntryIdConverter INSTANCE = new StreamEntryIdConverter();

	public final static ArrayConverter<StreamEntryId, StreamEntryID> ARRAY_CONVERTER = new ArrayConverter<>(INSTANCE);

	@Override
	public StreamEntryID convert(final StreamEntryId source){
		return new StreamEntryID(source.getTime(), source.getSequence());
	}

	public final static class MapStreamEntryIdConverter<SK, TK>
			extends MapConverter<SK, StreamEntryId, TK, StreamEntryID> {

		public final static MapStreamEntryIdConverter<String, String> STRING_MAP_CONVERTER = new MapStreamEntryIdConverter<>(
				(key)->key);

		public final static MapStreamEntryIdConverter<byte[], byte[]> BINARY_MAP_CONVERTER = new MapStreamEntryIdConverter<>(
				(key)->key);

		public MapStreamEntryIdConverter(Converter<SK, TK> keyConverter){
			super(keyConverter, StreamEntryIdConverter.INSTANCE);
		}

	}

}
