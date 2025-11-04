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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import com.buession.redis.utils.ObjectStringBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Acl 日志
 *
 * @param entryId
 * 		条目 ID
 * @param count
 * 		该事件发生的次数
 * @param reason
 * 		失败原因
 * @param context
 * 		上下文
 * @param object
 * 		涉及的对象，如被拒绝的命令名或 key 名
 * @param username
 * 		尝试操作的用户名
 * @param ageSeconds
 * 		事件发生距今的秒数
 * @param clientInfo
 * 		客户端连接信息（IP、端口、客户端 ID 等）
 * @param timestampCreated
 * 		-
 * @param timestampLastUpdated
 * 		-
 * @param logEntry
 * 		-
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public record AclLog(long entryId, long count, String reason, String context, String object, String username,
					 Double ageSeconds, Client clientInfo, long timestampCreated, long timestampLastUpdated,
					 Map<String, Object> logEntry) implements Serializable {

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
