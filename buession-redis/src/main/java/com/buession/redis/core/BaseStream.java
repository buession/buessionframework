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

import java.io.Serializable;
import java.util.Map;

/**
 * Stream 元数据基类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
class BaseStream implements Serializable {

	private final static long serialVersionUID = 2904055326472770982L;

	/**
	 * Stream 中消息的总条数
	 */
	private final long length;

	/**
	 * Stream 内部 Radix Tree 结构信息
	 */
	private final long radixTreeKeys;

	/**
	 * Stream 内部 Radix Tree 结构信息
	 */
	private final long radixTreeNodes;

	private final StreamEntryId lastGeneratedId;

	private final Map<String, Object> infos;

	/**
	 * 构造函数
	 *
	 * @param length
	 * 		Stream 中消息的总条数
	 * @param radixTreeKeys
	 * 		Stream 内部 Radix Tree 结构信息
	 * @param radixTreeNodes
	 * 		Stream 内部 Radix Tree 结构信息
	 * @param lastGeneratedId
	 * 		-
	 * @param infos
	 * 		-
	 */
	public BaseStream(final long length, final long radixTreeKeys, final long radixTreeNodes,
					  final StreamEntryId lastGeneratedId, final Map<String, Object> infos) {
		this.length = length;
		this.radixTreeKeys = radixTreeKeys;
		this.radixTreeNodes = radixTreeNodes;
		this.lastGeneratedId = lastGeneratedId;
		this.infos = infos;
	}

	/**
	 * 返回 Stream 中消息的总条数
	 *
	 * @return Stream 中消息的总条数
	 */
	public long getLength() {
		return length;
	}

	/**
	 * 返回 Stream 内部 Radix Tree 结构信息
	 *
	 * @return Stream 内部 Radix Tree 结构信息
	 */
	public long getRadixTreeKeys() {
		return radixTreeKeys;
	}

	/**
	 * 返回 Stream 内部 Radix Tree 结构信息
	 *
	 * @return Stream 内部 Radix Tree 结构信息
	 */
	public long getRadixTreeNodes() {
		return radixTreeNodes;
	}

	public StreamEntryId getLastGeneratedId() {
		return lastGeneratedId;
	}

	public Map<String, Object> getInfos() {
		return infos;
	}

}
