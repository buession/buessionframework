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
import com.buession.redis.core.StreamConsumerFull;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamFull;
import com.buession.redis.core.internal.convert.response.BaseKeyValueConverter;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.resps.StreamConsumerFullInfo;
import redis.clients.jedis.resps.StreamFullInfo;
import redis.clients.jedis.resps.StreamGroupFullInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * jedis {@link StreamFullInfo} 转换为 {@link StreamFull}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class StreamFullInfoConverter<TK, TV>
		extends BaseKeyValueConverter<String, String, TK, TV, StreamFullInfo, StreamFull<TK, TV>> {

	private final StreamEntryIDConverter streamEntryIDConverter = new StreamEntryIDConverter();

	public StreamFullInfoConverter(final Converter<String, TK> keyConverter,
	                               final Converter<String, TV> valueConverter) {
		super(keyConverter, valueConverter);
	}

	@Override
	public StreamFull<TK, TV> convert(final StreamFullInfo source) {
		if(source == null){
			return null;
		}

		final ListConverter<StreamGroupFullInfo, StreamFull.Group> listStreamFullInfoGroupConverter = new ListConverter<>(
				new StreamFullInfoGroupConverter());
		final ListConverter<redis.clients.jedis.resps.StreamEntry, StreamEntry<TK, TV>> listStreamEntryConverter = new ListConverter<>(
				new StreamEntryConverter<>(keyConverter, valueConverter));
		final List<StreamFull.Group> groups = listStreamFullInfoGroupConverter.convert(source.getGroups());
		final StreamEntryId lastGeneratedId = streamEntryIDConverter.convert(source.getLastGeneratedId());
		final Map<String, Object> streamFullInfo = source.getStreamFullInfo();
		final StreamEntryId maxDeletedEntryId = streamFullInfoOfStreamEntryId(streamFullInfo, "max-deleted-entry-id");
		final StreamEntryId recordedFirstEntryId = streamFullInfoOfStreamEntryId(streamFullInfo,
				"recorded-first-entry-id");
		final Long entriesAdded = streamFullInfoOfLong(streamFullInfo, "entries-added");
		final Long idmpDuration = streamFullInfoOfLong(streamFullInfo, "idmp-duration");
		final Long idmpMaxsize = streamFullInfoOfLong(streamFullInfo, "idmp-maxsize");
		final Long pidsTracked = streamFullInfoOfLong(streamFullInfo, "pids-tracked");
		final Long iidsTracked = streamFullInfoOfLong(streamFullInfo, "iids-tracked");
		final Long iidsAdded = streamFullInfoOfLong(streamFullInfo, "iids-added");
		final Long iidsDuplicates = streamFullInfoOfLong(streamFullInfo, "iids-duplicates");
		final List<StreamEntry<TK, TV>> entries = listStreamEntryConverter.convert(source.getEntries());

		return new StreamFull<>(source.getLength(), groups, source.getRadixTreeKeys(), source.getRadixTreeNodes(),
				lastGeneratedId, maxDeletedEntryId, recordedFirstEntryId, entriesAdded, idmpDuration, idmpMaxsize,
				pidsTracked, iidsTracked, iidsAdded, iidsDuplicates, entries);
	}

	private StreamEntryId streamFullInfoOfStreamEntryId(final Map<String, Object> streamFullInfo, final String key) {
		final Object value = streamFullInfo.get(key);
		return value == null ? null : streamEntryIDConverter.convert((StreamEntryID) value);
	}

	private Long streamFullInfoOfLong(final Map<String, Object> streamFullInfo, final String key) {
		final Object value = streamFullInfo.get(key);
		return value == null ? null : (Long) value;
	}

	/**
	 * Jedis {@link StreamGroupFullInfo} 转换为 {@link StreamFull.Group}
	 *
	 * @author Yong.Teng
	 * @since 4.0.0
	 */
	private final static class StreamFullInfoGroupConverter
			implements Converter<StreamGroupFullInfo, StreamFull.Group> {

		@Override
		public StreamFull.Group convert(final StreamGroupFullInfo source) {
			if(source == null){
				return null;
			}

			final StreamEntryIDConverter streamEntryIDConverter = new StreamEntryIDConverter();
			final ListConverter<StreamConsumerFullInfo, StreamConsumerFull> listStreamConsumerFullInfoConverter = new ListConverter<>(
					new StreamConsumerFullInfoConverter());
			final List<StreamConsumerFull> consumers = listStreamConsumerFullInfoConverter.convert(
					source.getConsumers());
			final StreamEntryId lastDeliveredId = streamEntryIDConverter.convert(source.getLastDeliveredId());

			return new StreamFull.Group(source.getName(), consumers, source.getPending(), source.getPelCount(),
					lastDeliveredId, source.getGroupFullInfo());
		}

	}

	/**
	 * jedis {@link StreamConsumerFullInfo} 转换为 {@link StreamConsumerFull}
	 *
	 * @author Yong.Teng
	 * @since 4.0.0
	 */
	private final static class StreamConsumerFullInfoConverter
			implements Converter<StreamConsumerFullInfo, StreamConsumerFull> {

		@Override
		public StreamConsumerFull convert(final StreamConsumerFullInfo source) {
			if(source == null){
				return null;
			}

			final List<Long> pendings = new ArrayList<>();

			if(source.getPending() != null){
				for(List<Object> pending : source.getPending()){
					for(Object item : pending){
						pendings.add((Long) item);
					}
				}
			}

			return new StreamConsumerFull(source.getName(), source.getSeenTime(), source.getPelCount(), pendings,
					source.getConsumerInfo());
		}

	}

}
