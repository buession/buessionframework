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
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AclLog implements Serializable {

	private final static long serialVersionUID = -1362912160889551004L;

	private final long count;

	private final String reason;

	private final String context;

	private final String object;

	private final String username;

	private final Double ageSeconds;

	private final Client clientInfo;

	private final Map<String, Object> logEntry;

	public AclLog(final long count, final String reason, final String context, final String object,
				  final String username, final Double ageSeconds, final Client clientInfo,
				  final Map<String, Object> logEntry) {
		this.count = count;
		this.reason = reason;
		this.context = context;
		this.object = object;
		this.username = username;
		this.ageSeconds = ageSeconds;
		this.clientInfo = clientInfo;
		this.logEntry = logEntry;
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

	public Map<String, Object> getLogEntry() {
		return logEntry;
	}

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("count", count)
				.add("reason", reason)
				.add("context", context)
				.add("object", object)
				.add("username", username)
				.add("ageSeconds", ageSeconds)
				.add("clientInfo", clientInfo)
				.add("logEntry", logEntry)
				.build();
	}

}
