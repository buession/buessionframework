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
package com.buession.redis.core.internal.convert.lettuce.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.MapConverter;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import io.lettuce.core.StreamMessage;

/**
 * Lettuce {@link StreamMessage} 转换为 {@link StreamEntry}
 *
 * @param <SK>
 * 		原始 Key 类型
 * @param <SV>
 * 		原始值类型
 * @param <TK>
 * 		目标 Key 类型
 * @param <TV>
 * 		目标值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class StreamMessageConverter<SK, SV, TK, TV>
		implements Converter<StreamMessage<SK, SV>, StreamEntry<TK, TV>> {

	private final Converter<SK, TK> keyConverter;

	private final Converter<SV, TV> valueConverter;

	public StreamMessageConverter(final Converter<SK, TK> keyConverter, final Converter<SV, TV> valueConverter) {
		this.keyConverter = keyConverter;
		this.valueConverter = valueConverter;
	}

	@Override
	public StreamEntry<TK, TV> convert(final StreamMessage<SK, SV> source) {
		if(source == null){
			return null;
		}

		final MapConverter<SK, SV, TK, TV> mapConverter = new MapConverter<>(keyConverter, valueConverter);
		return new StreamEntry<>(new StreamEntryId(source.getId()), mapConverter.convert(source.getBody()));
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
			return source == null ? null : new StreamEntryId(source.getId());
		}

	}

}
