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
package com.buession.redis.core;

import com.buession.redis.core.command.RedisCommandGroup;
import com.buession.redis.utils.ObjectStringBuilder;

import java.util.List;
import java.util.Set;

/**
 * 命令文档
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public record CommandInfo(String name, RedisCommandGroup group, Integer arity, Set<Flag> flags, Integer firstKey,
                          Integer lastKey, Integer step, Set<String> aclCategories,
                          List<CommandDoc.History> history, List<CommandDoc.Argument> arguments) {

	@Override
	public String toString() {
		return ObjectStringBuilder.create().add("name", name).add("group", group).add("flags", flags)
				.add("First key", firstKey).add("Las key", lastKey).add("step", step)
				.add("ACL categories", aclCategories)
				.add("history", history).add("arguments", arguments).build();
	}

	public enum Flag implements Keyword {

		ADMIN,

		ASKING,

		BLOCKING,

		DENYOOM,

		FAST,

		LOADING,

		MOVABLEKEYS,

		NO_AUTH,

		NO_ASYNC_LOADING,

		NO_MANDATORY_KEYS,

		NO_MULTI,

		NOSCRIPT,

		PUBSUB,

		RANDOM,

		READONLY,

		SORT_FOR_SCRIPT,

		SKIP_MONITOR,

		SORT_SLOWLOG,

		STALE,

		WRITE;

		@Override
		public String getValue() {
			return name().toLowerCase();
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

	public record History(String version, String commont) {

		@Override
		public String toString() {
			return ObjectStringBuilder.create().add("version", version).add("commont", commont).build();
		}

	}

	public record Argument(String name, String type, String displayText, String token, String since) {

		@Override
		public String toString() {
			return ObjectStringBuilder.create().add("name", name).add("type", type).add("display_text", displayText)
					.add("token", token).add("since", since).build();
		}

	}

}
