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
package com.buession.redis.core.internal.convert.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.RedisServer;
import com.buession.redis.utils.SafeEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Cluster Slots 命令结果转换为 {@link ClusterSlot}
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public final class ClusterSlotConverter implements Converter<Object, ClusterSlot> {

	@Override
	@SuppressWarnings({"unchecked"})
	public ClusterSlot convert(final Object source) {
		if(source instanceof List){
			List<Object> data = (List<Object>) source;

			if(data.size() >= 3){
				final long start = (long) data.get(0);
				final long end = (long) data.get(1);
				final List<RedisServer> masterNodes = new ArrayList<>(data.size() - 2);
				List<Object> masterNode;

				for(int i = 2; i < data.size(); i++){
					masterNode = (List<Object>) data.get(i);

					RedisServer redisServer = new RedisServer(
							SafeEncoder.encode((byte[]) masterNode.get(0)), ((Long) masterNode.get(1)).intValue());

					redisServer.setId(SafeEncoder.encode((byte[]) masterNode.get(2)));

					masterNodes.add(redisServer);
				}

				return new ClusterSlot(start, end, masterNodes);
			}
		}

		return null;
	}

	public static ListConverter<Object, ClusterSlot> listConverter() {
		return new ListConverter<>(new ClusterSlotConverter());
	}

}
