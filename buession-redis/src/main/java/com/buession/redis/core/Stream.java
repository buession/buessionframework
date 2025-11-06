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

import java.util.Map;

/**
 * Stream 元数据
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Stream extends BaseStream {

	private final static long serialVersionUID = -4336316668706617743L;

	private final StreamEntry firstEntry;

	private final StreamEntry lastEntry;

	private final long groups;

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
	 * @param firstEntry
	 * 		-
	 * @param lastEntry
	 * 		-
	 * @param infos
	 * 		-
	 */
	public Stream(final long length, final long radixTreeKeys, final long radixTreeNodes, final long groups,
				  final StreamEntryId lastGeneratedId, final StreamEntry firstEntry, final StreamEntry lastEntry,
				  final Map<String, Object> infos) {
		super(length, radixTreeKeys, radixTreeNodes, lastGeneratedId, infos);
		this.firstEntry = firstEntry;
		this.lastEntry = lastEntry;
		this.groups = groups;
	}

	public StreamEntry getFirstEntry() {
		return firstEntry;
	}

	public StreamEntry getLastEntry() {
		return lastEntry;
	}

	public long getGroups() {
		return groups;
	}

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("length", getLength())
				.add("radixTreeKeys", getRadixTreeKeys())
				.add("radixTreeNodes", getRadixTreeNodes())
				.add("groups", groups)
				.add("lastGeneratedId", getLastGeneratedId())
				.add("firstEntry", firstEntry)
				.add("lastEntry", lastEntry)
				.add("infos", getInfos())
				.build();
	}

}
