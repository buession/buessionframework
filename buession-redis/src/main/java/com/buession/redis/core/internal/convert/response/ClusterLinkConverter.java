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
		final ClusterLink clusterLink = new ClusterLink();

		propertyMapper.from(source.get("direction"))
				.as((v)->Enum.valueOf(ClusterLink.Direction.class, v.toString().toUpperCase()))
				.to(clusterLink::direction);
		propertyMapper.from(source.get("node")).as(Object::toString).to(clusterLink::setNode);
		propertyMapper.from(source.get("create-time")).as((v)->(Long) v).to(clusterLink::setCreateTime);
		propertyMapper.from(source.get("events")).as((v)->{
			if(Objects.equals(v, "rw")){
				return new ClusterLink.Event[]{ClusterLink.Event.R, ClusterLink.Event.W};
			}else if(Objects.equals(v, "r")){
				return new ClusterLink.Event[]{ClusterLink.Event.R};
			}else if(Objects.equals(v, "w")){
				return new ClusterLink.Event[]{ClusterLink.Event.W};
			}else{
				return new ClusterLink.Event[]{};
			}
		}).to(clusterLink::setEvents);
		propertyMapper.from(source.get("send-buffer-allocated")).as((v)->(Integer) v)
				.to(clusterLink::setSendBufferAllocated);
		propertyMapper.from(source.get("send-buffer-used")).as((v)->(Integer) v).to(clusterLink::setSendBufferUsed);

		return clusterLink;
	}

}
