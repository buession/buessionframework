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
package com.buession.redis.core;

import com.buession.redis.utils.ObjectStringBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Acl 日志
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AclLog implements Serializable {

	private final static long serialVersionUID = -1362912160889551004L;

	// Redis 7.2
	public final static String ENTRY_ID = "entry-id";

	public final static String COUNT = "count";

	public final static String REASON = "reason";

	public final static String CONTEXT = "context";

	public final static String OBJECT = "object";

	public final static String USERNAME = "username";

	public final static String AGE_SECONDS = "age-seconds";

	public final static String CLIENT_INFO = "client-info";

	public final static String TIMESTAMP_CREATED = "timestamp-created";

	public final static String TIMESTAMP_LAST_UPDATED = "timestamp-last-updated";

	private final long entryId;

	private final long count;

	private final String reason;

	private final String context;

	private final String object;

	private final String username;

	private final Double ageSeconds;

	private final Client clientInfo;

	private final long timestampCreated;

	private final long timestampLastUpdated;

	private final Map<String, Object> logEntry;

	public AclLog(final long entryId, final long count, final String reason, final String context, final String object,
				  final String username, final Double ageSeconds, final Client clientInfo, final long timestampCreated,
				  final long timestampLastUpdated, final Map<String, Object> logEntry) {
		this.entryId = entryId;
		this.count = count;
		this.reason = reason;
		this.context = context;
		this.object = object;
		this.username = username;
		this.ageSeconds = ageSeconds;
		this.clientInfo = clientInfo;
		this.timestampCreated = timestampCreated;
		this.timestampLastUpdated = timestampLastUpdated;
		this.logEntry = logEntry;
	}

	public long getEntryId() {
		return entryId;
	}

	public long getCount() {
		return count;
	}

	public String getReason() {
		return reason;
	}

	public String getContext() {
		return context;
	}

	public String getObject() {
		return object;
	}

	public String getUsername() {
		return username;
	}

	public Double getAgeSeconds() {
		return ageSeconds;
	}

	public Client getClientInfo() {
		return clientInfo;
	}

	public long getTimestampCreated() {
		return timestampCreated;
	}

	public long getTimestampLastUpdated() {
		return timestampLastUpdated;
	}

	public Map<String, Object> getLogEntry() {
		return logEntry;
	}

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add(ENTRY_ID, entryId)
				.add(COUNT, count)
				.add(REASON, reason)
				.add(CONTEXT, context)
				.add(OBJECT, object)
				.add(USERNAME, username)
				.add(AGE_SECONDS, ageSeconds)
				.add(CLIENT_INFO, clientInfo)
				.add(TIMESTAMP_CREATED, timestampCreated)
				.add(TIMESTAMP_LAST_UPDATED, timestampLastUpdated)
				.build();
	}

}
