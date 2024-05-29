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
import com.buession.redis.core.StreamConsumerFull;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamFull;
import redis.clients.jedis.resps.StreamConsumerFullInfo;
import redis.clients.jedis.resps.StreamGroupFullInfo;

import java.util.List;

/**
 * Jedis {@link StreamGroupFullInfo} 转换为 {@link StreamFull.Group}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class StreamFullInfoGroupConverter implements Converter<StreamGroupFullInfo, StreamFull.Group> {

	private final ListConverter<StreamConsumerFullInfo, StreamConsumerFull> listStreamConsumerFullInfoConverter =
			StreamConsumerFullInfoConverter.listConverter();

	private final StreamEntryIDConverter streamEntryIDConverter = new StreamEntryIDConverter();

	@Override
	public StreamFull.Group convert(final StreamGroupFullInfo source) {
		final List<StreamConsumerFull> consumers = listStreamConsumerFullInfoConverter.convert(
				source.getConsumers());
		final StreamEntryId lastDeliveredId = streamEntryIDConverter.convert(source.getLastDeliveredId());

		return new StreamFull.Group(source.getName(), consumers, source.getPending(), source.getPelCount(),
				lastDeliveredId, source.getGroupFullInfo());
	}

	public static ListConverter<StreamGroupFullInfo, StreamFull.Group> listConverter() {
		return new ListConverter<>(new StreamFullInfoGroupConverter());
	}

}
