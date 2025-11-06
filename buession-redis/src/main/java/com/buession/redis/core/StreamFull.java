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
 * Stream 完整元数据
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class StreamFull extends BaseStream {

	private final static long serialVersionUID = -4336316668706617743L;

	private final List<StreamEntry> entries;

	/**
	 * 关联的消费者组数量
	 */
	private final List<StreamFull.Group> groups;

	/**
	 * 构造函数
	 *
	 * @param length
	 * 		Stream 中消息的总条数
	 * @param radixTreeKeys
	 * 		Stream 内部 Radix Tree 结构信息
	 * @param radixTreeNodes
	 * 		Stream 内部 Radix Tree 结构信息
	 * @param groups
	 * 		关联的消费者组数量
	 * @param lastGeneratedId
	 * 		-
	 * @param entries
	 * 		-
	 * @param infos
	 * 		-
	 */
	public StreamFull(final long length, final long radixTreeKeys, final long radixTreeNodes,
					  final List<Group> groups, final StreamEntryId lastGeneratedId,
					  final List<StreamEntry> entries, final Map<String, Object> infos) {
		super(length, radixTreeKeys, radixTreeNodes, lastGeneratedId, infos);
		this.groups = groups;
		this.entries = entries;
	}

	/**
	 * 返回关联的消费者组数量
	 *
	 * @return 关联的消费者组数量
	 */
	public List<StreamFull.Group> getGroups() {
		return groups;
	}

	public List<StreamEntry> getEntries() {
		return entries;
	}

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("length", getLength())
				.add("radixTreeKeys", getEntries())
				.add("radixTreeNodes", getRadixTreeNodes())
				.add("groups", getGroups())
				.add("lastGeneratedId", getLastGeneratedId())
				.add("entries", getEntries())
				.add("infos", getInfos())
				.build();
	}

	/**
	 * @since 2.0.1
	 */
	public final static class Group implements Serializable {

		private static final long serialVersionUID = 560117056010590768L;

		private final String name;

		private final List<StreamConsumerFull> consumers;

		private final List<List<Object>> pending;

		private final Long pelCount;

		private final StreamEntryId lastDeliveredId;

		private final Map<String, Object> infos;

		public Group(final String name, final List<StreamConsumerFull> consumers, final List<List<Object>> pending,
					 final Long pelCount, final StreamEntryId lastDeliveredId,
					 final Map<String, Object> infos) {
			this.name = name;
			this.consumers = consumers;
			this.pending = pending;
			this.pelCount = pelCount;
			this.lastDeliveredId = lastDeliveredId;
			this.infos = infos;
		}

		public String getName() {
			return name;
		}

		public List<StreamConsumerFull> getConsumers() {
			return consumers;
		}

		public List<List<Object>> getPending() {
			return pending;
		}

		public Long getPelCount() {
			return pelCount;
		}

		public StreamEntryId getLastDeliveredId() {
			return lastDeliveredId;
		}

		public Map<String, Object> getInfos() {
			return infos;
		}

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
