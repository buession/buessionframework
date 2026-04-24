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

import com.buession.redis.core.command.RedisCommand;
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
public record CommandInfo(String name, RedisCommand redisCommand, RedisCommandGroup group, Integer arity,
                          Set<Flag> flags, Integer firstKey, Integer lastKey, Integer step,
                          Set<AclCategory> aclCategories, Tips tips, List<KeySpecification> keySpecifications,
                          List<String> subcommands) {

	@Override
	public String toString() {
		return ObjectStringBuilder.create().add("name", name).add("group", group).add("flags", flags)
				.add("First key", firstKey).add("Las key", lastKey).add("step", step)
				.add("ACL categories", aclCategories).add("tips", tips).add("key specifications", keySpecifications)
				.add("subcommands", subcommands).build();
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

	public record Tips(Boolean nondeterministicOutput, Boolean nondeterministicOutputOrder, RequestPolicy requestPolicy,
	                   ResponsePolicy responsePolicy) {

		@Override
		public String toString() {
			return ObjectStringBuilder.create()
					.append(nondeterministicOutput == null ? null : "nondeterministic_output")
					.append(nondeterministicOutputOrder == null ? null : "nondeterministic_output_order")
					.add("request_policy", requestPolicy).add("response_policy", responsePolicy).build();
		}

		public enum RequestPolicy implements Keyword {

			ALL_NODES,

			ALL_SHARDS,

			MULTI_SHARD,

			SPECIAL;

			@Override
			public String getValue() {
				return name().toLowerCase();
			}

			@Override
			public String toString() {
				return getValue();
			}

		}

		public enum ResponsePolicy implements Keyword {

			ONE_SUCCEEDED,

			ALL_SUCCEEDED,

			AGG_LOGICAL_AND,

			AGG_LOGICAL_OR,

			AGG_MIN,

			AGG_MAX,

			AGG_SUM,

			SPECIAL;

			@Override
			public String getValue() {
				return name().toLowerCase();
			}

			@Override
			public String toString() {
				return getValue();
			}

		}

	}

	public record KeySpecification(String notes, Flag flags, String beginSearch, String findKeys) {

		@Override
		public String toString() {
			return ObjectStringBuilder.create().add("notes", notes).add("flags", flags).add("begin_search", beginSearch)
					.add("find_keys", findKeys).build();
		}

		public record Flag(AccessType accessType, LogicalOperation logicalOperation, Miscellaneous miscellaneous) {

			@Override
			public String toString() {
				return ObjectStringBuilder.create().add("Access type flags", accessType)
						.add("Logical operation flags", logicalOperation).add("Miscellaneous flags", miscellaneous)
						.build();
			}

			public enum AccessType implements Keyword {

				RW,

				RO,

				OW,

				RM;

				@Override
				public String getValue() {
					return name();
				}

				@Override
				public String toString() {
					return getValue();
				}

			}

			public enum LogicalOperation implements Keyword {

				ACCESS,

				UPDATE,

				INSERT,

				DELETE;

				@Override
				public String getValue() {
					return name().toLowerCase();
				}

				@Override
				public String toString() {
					return getValue();
				}

			}

			public enum Miscellaneous implements Keyword {

				NOT_KEY,

				INCOMPLETE,

				VARIABLE_FLAGS;

				@Override
				public String getValue() {
					return name().toLowerCase();
				}

				@Override
				public String toString() {
					return getValue();
				}

			}

		}

	}

}
