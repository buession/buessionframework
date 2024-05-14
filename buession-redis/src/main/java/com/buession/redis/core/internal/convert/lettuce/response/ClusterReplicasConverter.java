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
package com.buession.redis.core.internal.convert.lettuce.response;

import com.buession.core.converter.Converter;
import com.buession.core.utils.EnumUtils;
import com.buession.core.utils.StringUtils;
import com.buession.redis.core.ClusterRedisNode;
import com.buession.redis.core.RedisClusterServer;
import com.buession.redis.core.SlotRange;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Lettuce Cluster Replicas 命令结果转换为 {@link RedisClusterServer} 列表
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class ClusterReplicasConverter implements Converter<String, List<ClusterRedisNode>> {

	public final static ClusterReplicasConverter INSTANCE = new ClusterReplicasConverter();

	@Override
	public List<ClusterRedisNode> convert(final String source) {
		String[] rows = source.split("\r\n");
		return Arrays.stream(rows).map(this::rowConvert).collect(Collectors.toList());
	}

	private ClusterRedisNode rowConvert(final String row) {
		String[] values = StringUtils.split(row, " ");
		String[] hostAndPort = StringUtils.split(values[1], ":");
		String host = hostAndPort[0];
		String port = hostAndPort[1].substring(0, hostAndPort[1].indexOf('@'));
		String[] flagsValues = StringUtils.split(values[2], ":");
		Set<ClusterRedisNode.Flag> flags = new HashSet<>(flagsValues.length);

		for(String flagsValue : flagsValues){
			flags.add(EnumUtils.getEnumIgnoreCase(ClusterRedisNode.Flag.class, flagsValue));
		}

		ClusterRedisNode.LinkState linkState = EnumUtils.getEnumIgnoreCase(ClusterRedisNode.LinkState.class, values[7]);
		SlotRange slotRange = null;

		if(values.length == 9){
			String[] slotRangeValues = StringUtils.split(values[8], '-');
			slotRange = new SlotRange(Integer.parseInt(slotRangeValues[0]), Integer.parseInt(slotRangeValues[1]));
		}

		return new ClusterRedisNode(values[0], host, Integer.parseInt(port), flags, values[3],
				Long.parseLong(values[4]), Long.parseLong(values[5]), Long.parseLong(values[6]), linkState, slotRange);
	}

}
