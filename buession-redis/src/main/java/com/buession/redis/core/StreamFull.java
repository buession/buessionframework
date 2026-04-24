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

import java.util.List;
import java.util.Map;

/**
 * Stream 完整元数据
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 * @param length
 * 		Stream 中消息的总条数
 * @param groups
 * 		关联的消费者组数量
 * @param radixTreeKeys
 * 		Stream 内部 Radix Tree 结构信息
 * @param radixTreeNodes
 * 		Stream 内部 Radix Tree 结构信息
 * @param lastGeneratedId
 * 		-
 * @param maxDeletedEntryId
 * 		-
 * @param recordedFirstEntryId
 * 		-
 * @param entriesAdded
 * 		-
 * @param idmpDuration
 * 		-
 * @param idmpMaxsize
 * 		-
 * @param pidsTracked
 * 		-
 * @param iidsTracked
 * 		-
 * @param iidsAdded
 * 		-
 * @param iidsDuplicates
 * 		-
 * @param entries
 * 		-
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public record StreamFull<K, V>(Long length, List<StreamFull.Group> groups, Long radixTreeKeys, Long radixTreeNodes,
                               StreamEntryId lastGeneratedId, StreamEntryId maxDeletedEntryId,
                               StreamEntryId recordedFirstEntryId, Long entriesAdded, Long idmpDuration,
                               Long idmpMaxsize, Long pidsTracked, Long iidsTracked,
                               Long iidsAdded, Long iidsDuplicates, List<StreamEntry<K, V>> entries) {

	@Override
	public String toString() {
		return ObjectStringBuilder.create().add("length", length).add("groups", groups)
				.add("radix-tree-keys", radixTreeKeys).add("radix-tree-nodes", radixTreeNodes)
				.add("last-generated-id", lastGeneratedId).add("max-deleted-entry-id", maxDeletedEntryId)
				.add("recorded-first-entry-id", recordedFirstEntryId).add("entries-added", entriesAdded)
				.add("idmp-duration", idmpDuration).add("idmp-maxsize", idmpMaxsize).add("pids-tracked", pidsTracked)
				.add("iids-tracked", iidsTracked).add("iids-added", iidsAdded).add("iids-duplicates", iidsDuplicates)
				.add("entries", entries).build();
	}

	/**
	 * @since 2.0.1
	 */
	public record Group(String name, List<StreamConsumerFull> consumers, List<List<Object>> pending, Long pelCount,
	                    StreamEntryId lastDeliveredId, Map<String, Object> infos) {

		@Override
		public String toString() {
			return ObjectStringBuilder.create().add("name", name).add("consumers", consumers).add("pending", pending)
					.add("pel count", pelCount).add("last delivered id", lastDeliveredId).add("infos", infos).build();
		}

	}

}
