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
 * Stream 消费者组
 *
 * @param name
 * 		消费者组名称
 * @param consumers
 * 		消费者的数量
 * @param pending
 * 		已投递但未被 ACK（确认）的消息数量
 * @param lastDeliveredId
 * 		最后投递给消费者的消息 ID
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public record StreamGroup(String name, Long consumers, Long pending, StreamEntryId lastDeliveredId,
						  Map<String, Object> infos) {

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("name", name)
				.add("consumers", consumers)
				.add("pending", pending)
				.add("last delivered id", lastDeliveredId)
				.add("infos", infos)
				.build();
	}

}
