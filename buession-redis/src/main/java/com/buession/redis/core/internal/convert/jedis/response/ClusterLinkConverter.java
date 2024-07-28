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
package com.buession.redis.core.internal.convert.jedis.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.redis.core.ClusterLink;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * Jedis {@code CLUSTER LINKS} 命令结果转换为 {@link ClusterLink}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class ClusterLinkConverter implements Converter<Map<String, Object>, ClusterLink> {

	@Nullable
	@Override
	public ClusterLink convert(final Map<String, Object> source) {
		if(source == null){
			return null;
		}else{
			final ClusterLink clusterLink = new ClusterLink();

			clusterLink.setNode((String) source.get("node"));

			String direction = (String) source.get("direction");
			if("from".equals(direction)){
				clusterLink.setDirection(ClusterLink.Direction.FROM);
			}else if("to".equals(direction)){
				clusterLink.setDirection(ClusterLink.Direction.TO);
			}

			clusterLink.setCreateTime((Long) source.get("create-time"));

			String event = (String) source.get("events");
			if(event != null){
				boolean containsR = event.contains("r");
				boolean containsW = event.contains("w");

				if(containsR && containsW){
					clusterLink.setEvents(new ClusterLink.Event[]{ClusterLink.Event.R, ClusterLink.Event.W});
				}else if(containsR && containsW == false){
					clusterLink.setEvents(new ClusterLink.Event[]{ClusterLink.Event.R});
				}else if(containsR == false && containsW){
					clusterLink.setEvents(new ClusterLink.Event[]{ClusterLink.Event.W});
				}
			}

			clusterLink.setSendBufferAllocated((Integer) source.get("send-buffer-allocated"));
			clusterLink.setSendBufferUsed((Integer) source.get("send-buffer-used"));

			return clusterLink;
		}
	}

	public static ListConverter<Map<String, Object>, ClusterLink> listConverter() {
		return new ListConverter<>(new ClusterLinkConverter());
	}

}
