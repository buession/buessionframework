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

import java.util.List;

/**
 * 慢日志信息，更多信息 <a href="http://www.redis.cn/commands/slowlog.html" target="_blank">http://www.redis.cn/commands/slowlog.html</a>
 *
 * @param id
 * 		每个慢查询条目的唯一的递增标识符
 * @param timestamp
 * 		处理记录命令的unix时间戳
 * @param executionTime
 * 		命令执行所需的总时间（单位：毫秒）
 * @param args
 * 		组成该命令的参数的数组
 * @param client
 * 		客户端信息
 * @param clientName
 * 		客户端名称
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public record SlowLog(Long id, Long timestamp, Long executionTime, List<String> args, Client client,
					  String clientName) {

	@Override
	public String toString() {
		return ObjectStringBuilder.create().add("id", id).add("timestamp", timestamp)
				.add("executionTime", executionTime).add("args", args).add("client", client)
				.add("clientName", clientName).build();
	}

}
