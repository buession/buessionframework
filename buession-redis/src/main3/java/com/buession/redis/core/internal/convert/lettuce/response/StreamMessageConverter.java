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
package com.buession.redis.core.internal.convert.lettuce.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.internal.convert.Converters;
import io.lettuce.core.StreamMessage;
import org.springframework.lang.Nullable;

/**
 * Lettuce {@link StreamMessage} 转换为 {@link StreamEntry}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class StreamMessageConverter implements Converter<StreamMessage<byte[], byte[]>, StreamEntry> {

	private final MapConverter<byte[], byte[], String, String> binaryToStringMapConverter =
			Converters.mapBinaryToString();

	@Nullable
	@Override
	public StreamEntry convert(final StreamMessage<byte[], byte[]> source) {
		return new StreamEntry(new StreamEntryId(source.getId()), binaryToStringMapConverter.convert(source.getBody()));
	}

	public static ListConverter<StreamMessage<byte[], byte[]>, StreamEntry> listConverter() {
		return new ListConverter<>(new StreamMessageConverter());
	}

	/**
	 * Lettuce  {@link StreamMessage} 转换为 {@link StreamEntryId}
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	public final static class StreamMessageStreamEntryIdConverter implements Converter<StreamMessage<byte[], byte[]>,
			StreamEntryId> {

		@Override
		public StreamEntryId convert(final StreamMessage<byte[], byte[]> source) {
			return new StreamEntryId(source.getId());
		}

		public static ListConverter<StreamMessage<byte[], byte[]>, StreamEntryId> listConverter() {
			return new ListConverter<>(new StreamMessageStreamEntryIdConverter());
		}

	}

}