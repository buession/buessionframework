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
import com.buession.redis.core.Stream;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamGroup;
import redis.clients.jedis.resps.StreamInfo;

/**
 * jedis {@link StreamInfo} 转换为 {@link StreamGroup}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class StreamInfoConverter<K, V> implements Converter<StreamInfo, Stream<K, V>> {

	private final Converter<String, K> keyConverter;

	private final Converter<String, V> valueConverter;

	public StreamInfoConverter(final Converter<String, K> keyConverter,
							   final Converter<String, V> valueConverter) {
		this.keyConverter = keyConverter;
		this.valueConverter = valueConverter;
	}

	@Override
	public Stream<K, V> convert(final StreamInfo source) {
		if(source == null){
			return null;
		}

		final StreamEntryIDConverter streamEntryIDConverter = new StreamEntryIDConverter();
		final StreamEntryConverter<K, V> streamEntryConverter = new StreamEntryConverter<>(keyConverter,
				valueConverter);
		final StreamEntryId lastGeneratedId = streamEntryIDConverter.convert(source.getLastGeneratedId());
		final StreamEntry<K, V> firstEntry = streamEntryConverter.convert(source.getFirstEntry());
		final StreamEntry<K, V> lastEntry = streamEntryConverter.convert(source.getLastEntry());

		return new Stream<>(source.getLength(), source.getRadixTreeKeys(), source.getRadixTreeNodes(),
				source.getGroups(), lastGeneratedId, firstEntry, lastEntry, source.getStreamInfo());
	}

}
