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

import java.io.Serializable;

/**
 * 集群中节点与其他集群节点之间建立的所有 TCP 连接（即 cluster bus 链路）的详细信息
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ClusterLink implements Serializable {

	private static final long serialVersionUID = -1140855693744878434L;

	/**
	 * 连接方向
	 */
	private Direction direction;

	/**
	 * 对端节点的唯一 ID（40 字符十六进制字符串）
	 */
	private String node;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 事件
	 */
	private Event[] events;

	/**
	 * Allocated size of the link's send buffer, which is used to buffer outgoing messages toward the peer.
	 */
	private Integer sendBufferAllocated;

	/**
	 * Size of the portion of the link's send buffer that is currently holding data.
	 */
	private Integer sendBufferUsed;

	/**
	 * 返回连接方向
	 *
	 * @return 连接方向
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * 设置连接方向
	 *
	 * @param direction
	 * 		连接方向
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * 返回对端节点的唯一 ID
	 *
	 * @return 对端节点的唯一 ID
	 */
	public String getNode() {
		return node;
	}

	/**
	 * 设置对端节点的唯一 ID
	 *
	 * @param node
	 * 		对端节点的唯一 ID
	 */
	public void setNode(String node) {
		this.node = node;
	}

	/**
	 * 返回创建时间
	 *
	 * @return 创建时间
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime
	 * 		创建时间
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回事件
	 *
	 * @return 事件
	 */
	public Event[] getEvents() {
		return events;
	}

	/**
	 * 设置事件
	 *
	 * @param events
	 * 		事件
	 */
	public void setEvents(Event[] events) {
		this.events = events;
	}

	/**
	 * Return allocated size of the link's send buffer, which is used to buffer outgoing messages toward the peer.
	 *
	 * @return Allocated size of the link's send buffer, which is used to buffer outgoing messages toward the peer.
	 */
	public Integer getSendBufferAllocated() {
		return sendBufferAllocated;
	}

	/**
	 * Sets allocated size of the link's send buffer, which is used to buffer outgoing messages toward the peer.
	 *
	 * @param sendBufferAllocated
	 * 		Allocated size of the link's send buffer, which is used to buffer outgoing messages toward the peer.
	 */
	public void setSendBufferAllocated(Integer sendBufferAllocated) {
		this.sendBufferAllocated = sendBufferAllocated;
	}

	/**
	 * Return size of the portion of the link's send buffer that is currently holding data.
	 *
	 * @return Size of the portion of the link's send buffer that is currently holding data.
	 */
	public Integer getSendBufferUsed() {
		return sendBufferUsed;
	}

	/**
	 * Sets size of the portion of the link's send buffer that is currently holding data.
	 *
	 * @param sendBufferUsed
	 * 		Size of the portion of the link's send buffer that is currently holding data.
	 */
	public void setSendBufferUsed(Integer sendBufferUsed) {
		this.sendBufferUsed = sendBufferUsed;
	}

	public enum Direction {
		FROM,

		TO
	}

	public enum Event implements Keyword {
		R("r"),

		W("w");

		private final String value;

		Event(final String value) {
			this.value = value;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

}
