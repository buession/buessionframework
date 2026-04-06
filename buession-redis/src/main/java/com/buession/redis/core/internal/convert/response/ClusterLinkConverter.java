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
package com.buession.redis.core.internal.convert.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.redis.core.ClusterLink;
import com.buession.redis.core.Event;

import java.util.Map;
import java.util.Objects;

/**
 * Cluster Links 命令结果转换为 {@link ClusterLink}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ClusterLinkConverter implements Converter<Map<String, Object>, ClusterLink> {

	@Override
	public ClusterLink convert(final Map<String, Object> source) {
		if(source == null){
			return null;
		}

		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final ClusterLinkBuilder clusterLinkBuilder = new ClusterLinkBuilder();

		propertyMapper.from(source.get("direction"))
				.as((v)->Enum.valueOf(ClusterLink.Direction.class, v.toString().toUpperCase()))
				.to(clusterLinkBuilder::setDirection);
		propertyMapper.from(source.get("node")).as(Object::toString).to(clusterLinkBuilder::setNode);
		propertyMapper.from(source.get("create-time")).as((v)->(Long) v).to(clusterLinkBuilder::setCreateTime);
		propertyMapper.from(source.get("events")).as((v)->{
			if(Objects.equals(v, "rw")){
				return new Event[]{Event.R, Event.W};
			}else if(Objects.equals(v, "r")){
				return new Event[]{Event.R};
			}else if(Objects.equals(v, "w")){
				return new Event[]{Event.W};
			}else{
				return new Event[]{};
			}
		}).to(clusterLinkBuilder::setEvents);
		propertyMapper.from(source.get("send-buffer-allocated")).as((v)->(Integer) v)
				.to(clusterLinkBuilder::setSendBufferAllocated);
		propertyMapper.from(source.get("send-buffer-used")).as((v)->(Integer) v)
				.to(clusterLinkBuilder::setSendBufferUsed);

		return clusterLinkBuilder.build();
	}

	private final static class ClusterLinkBuilder {

		private ClusterLink.Direction direction;

		private String node;

		private Long createTime;

		private Event[] events;

		private Integer sendBufferAllocated;

		private Integer sendBufferUsed;

		private ClusterLinkBuilder() {

		}

		public ClusterLink.Direction getDirection() {
			return direction;
		}

		public void setDirection(ClusterLink.Direction direction) {
			this.direction = direction;
		}

		public String getNode() {
			return node;
		}

		public void setNode(String node) {
			this.node = node;
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

		public ClusterLink build() {
			return new ClusterLink(direction, node, createTime, events, sendBufferAllocated, sendBufferUsed);
		}

	}

}
