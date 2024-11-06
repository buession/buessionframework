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
import com.buession.redis.core.AclLog;
import com.buession.redis.core.Client;
import com.buession.redis.core.internal.convert.response.ClientConverter;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * Lettuce {@code ACL LOG} 命令结果转换为 {@link AclLog}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class AclLogConverter extends ListConverter<Map<String, Object>, AclLog> {

	public AclLogConverter() {
		super(new AclLogEntryConverter());
	}

	private final static class AclLogEntryConverter implements Converter<Map<String, Object>, AclLog> {

		private final static ClientConverter clientConverter = new ClientConverter();

		@Nullable
		@Override
		public AclLog convert(final Map<String, Object> source) {
			final long entryId = (long) source.get(AclLog.ENTRY_ID);
			final long count = (long) source.get(AclLog.COUNT);
			final String reason = (String) source.get(AclLog.REASON);
			final String context = (String) source.get(AclLog.CONTEXT);
			final String object = (String) source.get(AclLog.OBJECT);
			final String username = (String) source.get(AclLog.USERNAME);
			final Double ageSeconds = (Double) source.get(AclLog.AGE_SECONDS);
			final Client client = clientConverter.convert((String) source.get(AclLog.CLIENT_INFO));
			final long timestampCreated = (long) source.get(AclLog.TIMESTAMP_CREATED);
			final long timestampLastUpdated = (long) source.get(AclLog.TIMESTAMP_LAST_UPDATED);

			return new AclLog(entryId, count, reason, context, object, username, ageSeconds, client,
					timestampCreated, timestampLastUpdated, source);
		}

	}

}
