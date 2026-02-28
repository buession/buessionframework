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
import com.buession.core.converter.ListConverter;
import com.buession.lang.KeyValue;
import com.buession.redis.core.StreamEntryId;
import io.lettuce.core.StreamMessage;
import io.lettuce.core.models.stream.ClaimedMessages;

import java.util.List;

/**
 * Lettuce {@link ClaimedMessages} 转换为 {@link KeyValue}
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
 * @since 4.0.0
 */
public final class ClaimedMessagesKeyValueConverter<SK, SV, TK, TV> implements Converter<ClaimedMessages<SK, SV>,
		KeyValue<StreamEntryId, List<TV>>> {

	private final Converter<StreamMessage<SK, SV>, TV> converter;

	public ClaimedMessagesKeyValueConverter(final Converter<StreamMessage<SK, SV>, TV> converter) {
		this.converter = converter;
	}

	@Override
	public KeyValue<StreamEntryId, List<TV>> convert(final ClaimedMessages<SK, SV> source) {
		if(source == null){
			return null;
		}

		final ListConverter<StreamMessage<SK, SV>, TV> listConverter = new ListConverter<>(converter);
		return new KeyValue<>(new StreamEntryId(source.getId()), listConverter.convert(source.getMessages()));
	}


}
