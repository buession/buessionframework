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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class ClusterLink {

	private String node;

	private Direction direction;

	private Long createTime;

	private Event[] events;

	private Integer sendBufferAllocated;

	private Integer sendBufferUsed;

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Event[] getEvents() {
		return events;
	}

	public void setEvents(Event[] events) {
		this.events = events;
	}

	public Integer getSendBufferAllocated() {
		return sendBufferAllocated;
	}

	public void setSendBufferAllocated(Integer sendBufferAllocated) {
		this.sendBufferAllocated = sendBufferAllocated;
	}

	public Integer getSendBufferUsed() {
		return sendBufferUsed;
	}

	public void setSendBufferUsed(Integer sendBufferUsed) {
		this.sendBufferUsed = sendBufferUsed;
	}

	public enum Direction {
		FROM,

		TO
	}

	public enum Event {
		R,

		W
	}

}
