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
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public record StreamFull(long length, long radixTreeKeys, long radixTreeNodes, List<Group> groups,
						 StreamEntryId lastGeneratedId, List<StreamEntry> entries, Map<String, Object> infos)
		implements Serializable {

	private final static long serialVersionUID = -4336316668706617743L;

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("length", length)
				.add("radixTreeKeys", radixTreeKeys)
				.add("radixTreeNodes", radixTreeNodes)
				.add("groups", groups)
				.add("lastGeneratedId", lastGeneratedId)
				.add("entries", entries)
				.add("infos", infos)
				.build();
	}

	/**
	 * @since 2.0.1
	 */
	public record Group(String name, List<StreamConsumerFull> consumers, List<List<Object>> pending, Long pelCount,
						StreamEntryId lastDeliveredId, Map<String, Object> infos) implements Serializable {

		private static final long serialVersionUID = 560117056010590768L;

		@Override
		public String toString() {
			return ObjectStringBuilder.create()
					.add("name", name)
					.add("consumers", consumers)
					.add("pending", pending)
					.add("pelCount", pelCount)
					.add("lastDeliveredId", lastDeliveredId)
					.add("infos", infos)
					.build();
		}

	}

}
