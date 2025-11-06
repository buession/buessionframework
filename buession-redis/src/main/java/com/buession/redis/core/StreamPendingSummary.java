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
 * Pending 消息的摘要信息
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class StreamPendingSummary implements Serializable {

	private final static long serialVersionUID = 4352578196582945851L;

	/**
	 * Pending 消息总数
	 */
	private final long total;

	/**
	 * 最小消息 ID
	 */
	private final StreamEntryId minId;

	private final StreamEntryId maxId;

	/**
	 * 各消费者的 pending 消息数统计
	 */
	private final Map<String, Long> consumerMessageCount;

	/**
	 * 构造函数
	 *
	 * @param total
	 * 		Pending 消息总数
	 * @param minId
	 * 		最小消息 ID
	 * @param maxId
	 * 		最大消息 ID
	 * @param consumerMessageCount
	 * 		各消费者的 pending 消息数统计
	 */
	public StreamPendingSummary(final long total, final StreamEntryId minId, final StreamEntryId maxId,
								final Map<String, Long> consumerMessageCount) {
		this.total = total;
		this.minId = minId;
		this.maxId = maxId;
		this.consumerMessageCount = consumerMessageCount;
	}

	/**
	 * 返回 Pending 消息总数
	 *
	 * @return Pending 消息总数
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 返回最小消息 ID
	 *
	 * @return 最小消息 ID
	 */
	public StreamEntryId getMinId() {
		return minId;
	}

	/**
	 * 返回最大消息 ID
	 *
	 * @return 最大消息 ID
	 */
	public StreamEntryId getMaxId() {
		return maxId;
	}

	/**
	 * 返回各消费者的 pending 消息数统计
	 *
	 * @return 各消费者的 pending 消息数统计
	 */
	public Map<String, Long> getConsumerMessageCount() {
		return consumerMessageCount;
	}

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("total", total)
				.add("minId", minId)
				.add("maxId", maxId)
				.add("consumerMessageCount", consumerMessageCount)
				.build();
	}

}
