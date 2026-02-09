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

/**
 * 集群中节点与其他集群节点之间建立的所有 TCP 连接（即 cluster bus 链路）的详细信息
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public record ClusterLink(
		/*
		  连接方向
		 */
		Direction direction,

		/*
		  对端节点的唯一 ID（40 字符十六进制字符串）
		 */
		String node,

		/*
		  创建时间
		 */
		Long createTime,

		/*
		  事件
		 */
		Event[] events,

		/*
		  Allocated size of the link's send buffer, which is used to buffer outgoing messages toward the peer.
		 */
		Integer sendBufferAllocated,

		/*
		  Size of the portion of the link's send buffer that is currently holding data.
		 */
		Integer sendBufferUsed
) {

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("direction", direction)
				.add("node", node)
				.add("create_time", createTime)
				.add("events", events)
				.add("send_buffer_allocated", sendBufferAllocated)
				.add("send_buffer_used", sendBufferUsed)
				.build();
	}

	public enum Direction {
		FROM,

		TO
	}

}
