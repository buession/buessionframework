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
import redis.clients.jedis.resps.StreamConsumerFullInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * jedis {@link StreamConsumerFullInfo} 转换为 {@link StreamConsumerFull}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class StreamConsumerFullInfoConverter implements Converter<StreamConsumerFullInfo, StreamConsumerFull> {

	@Override
	public StreamConsumerFull convert(final StreamConsumerFullInfo source) {
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

	public static ListConverter<StreamConsumerFullInfo, StreamConsumerFull> listConverter() {
		return new ListConverter<>(new StreamConsumerFullInfoConverter());
	}

}
