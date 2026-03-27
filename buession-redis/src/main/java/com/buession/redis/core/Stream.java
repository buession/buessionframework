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

import com.buession.redis.utils.ObjectStringBuilder;

import java.util.Map;

/**
 * Stream 元数据
 *
 * @param length
 * 		Stream 中消息的总条数
 * @param groups
 * 		Stream 组数量
 * @param radixTreeKeys
 * 		Stream 内部 Radix Tree 结构信息
 * @param radixTreeNodes
 * 		Stream 内部 Radix Tree 结构信息
 * @param lastGeneratedId
 * 		-
 * @param firstEntry
 * 		-
 * @param lastEntry
 * 		-
 * @param infos
 * 		-
 * @param <K>
 * 		-
 * @param <V>
 * 		-
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public record Stream<K, V>(
		Long length,

		Long groups,

		Long radixTreeKeys,

		Long radixTreeNodes,

		StreamEntryId lastGeneratedId,

		StreamEntry<K, V> firstEntry,

		StreamEntry<K, V> lastEntry,

		Map<String, Object> infos
) {

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("length", length)
				.add("groups", groups)
				.add("radixTreeKeys", radixTreeKeys)
				.add("radixTreeNodes", radixTreeNodes)
				.add("lastGeneratedId", lastGeneratedId)
				.add("firstEntry", firstEntry)
				.add("lastEntry", lastEntry)
				.add("infos", infos)
				.build();
	}

}
