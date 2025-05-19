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
import com.buession.core.utils.StringUtils;
import com.buession.redis.core.Client;
import com.buession.redis.core.SlowLog;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Lettuce 慢日志对象转换为 {@link SlowLog}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class SlowlogConverter implements Converter<Object, SlowLog> {

	@SuppressWarnings({"unchecked"})
	@Override
	public SlowLog convert(final Object source) {
		if(source instanceof List){
			final List<Object> tmp = (List<Object>) source;

			if(tmp.size() == 6){
				final Client client = parseHostAndPort(tmp.get(4));
				final String clientName = parseClientName(tmp.get(5));

				return new SlowLog((long) tmp.get(0), (long) tmp.get(1), (long) tmp.get(2), parseArgs(tmp.get(3)),
						client, clientName);
			}
		}

		return null;
	}

	private static Client parseHostAndPort(final Object value) {
		final Client client = new Client();

		if(value != null){
			String[] hostAndPort = StringUtils.split(new String((byte[]) value), ':');
			client.setHost(hostAndPort[0]);
			client.setPort(Integer.parseInt(hostAndPort[1]));
		}

		return client;
	}

	private static String parseClientName(final Object value) {
		return value == null ? null : new String((byte[]) value);
	}

	@SuppressWarnings({"unchecked"})
	private static List<String> parseArgs(final Object value) {
		if(value == null){
			return null;
		}

		final List<byte[]> tmp = (List<byte[]>) value;
		return tmp.stream().map((v)->v == null ? null : new String(v)).collect(Collectors.toList());
	}

	public static ListConverter<Object, SlowLog> listConverter() {
		return new ListConverter<>(new SlowlogConverter());
	}

}
