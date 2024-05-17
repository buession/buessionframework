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
import com.buession.redis.core.StreamEntryId;
import redis.clients.jedis.StreamEntryID;

import java.util.List;
import java.util.Map;

/**
 * Jedis {@link StreamEntryID} 转换为 {@link StreamEntryId}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class StreamEntryIDConverter implements Converter<StreamEntryID, StreamEntryId> {

	@Override
	public StreamEntryId convert(final StreamEntryID source) {
		return new StreamEntryId(source.getTime(), source.getSequence());
	}

	/**
	 * Jedis {@link byte[]} 转换为 {@link StreamEntryId}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	public final static class BinaryStreamEntryIdConverter implements Converter<byte[], StreamEntryId> {

		@Override
		public StreamEntryId convert(final byte[] source) {
			return new StreamEntryId(source);
		}

	}

	/**
	 * Jedis {@link List} {@link StreamEntryID} 转换为 {@link List} {@link StreamEntryId}
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	public final static class ListStreamEntryIDConverter extends ListConverter<StreamEntryID, StreamEntryId> {

		public ListStreamEntryIDConverter() {
			super(new StreamEntryIDConverter());
		}

	}

	/**
	 * Jedis key {@link Map.Entry<StreamEntryID, List<StreamEntryID>>} 转换为
	 * {@link Map<StreamEntryId, List<StreamEntryId>>}
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	public final static class MapEntryStreamEntryIdConverter extends
			MapEntryMapConverter<StreamEntryID, List<StreamEntryID>, StreamEntryId, List<StreamEntryId>> {

		public MapEntryStreamEntryIdConverter() {
			super(new StreamEntryIDConverter(), new ListStreamEntryIDConverter());
		}

	}

}
