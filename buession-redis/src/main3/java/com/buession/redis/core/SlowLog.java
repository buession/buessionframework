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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import com.buession.redis.utils.ObjectStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 慢日志信息，更多信息 <a href="http://www.redis.cn/commands/slowlog.html" target="_blank">http://www.redis.cn/commands/slowlog.html</a>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class SlowLog implements Serializable {

	private final static long serialVersionUID = 4948377124212583969L;

	/**
	 * 每个慢查询条目的唯一的递增标识符
	 */
	private final long id;

	/**
	 * 处理记录命令的unix时间戳
	 */
	private final long timestamp;

	/**
	 * 命令执行所需的总时间（单位：毫秒）
	 */
	private final long executionTime;

	/**
	 * 组成该命令的参数的数组
	 */
	private final List<String> args;

	/**
	 * 客户端信息
	 */
	private final Client client;

	/**
	 * 客户端名称
	 */
	private final String clientName;

	/**
	 * 构造函数
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
	 */
	public SlowLog(final long id, final long timestamp, final long executionTime, final List<String> args,
				   final Client client, final String clientName){
		this.id = id;
		this.timestamp = timestamp;
		this.executionTime = executionTime;
		this.args = args;
		this.client = client;
		this.clientName = clientName;
	}

	/**
	 * 返回每个慢查询条目的唯一的递增标识符
	 *
	 * @return 每个慢查询条目的唯一的递增标识符
	 */
	public long getId(){
		return id;
	}

	/**
	 * 返回处理记录命令的unix时间戳
	 *
	 * @return 处理记录命令的unix时间戳
	 */
	public long getTimestamp(){
		return timestamp;
	}

	/**
	 * 返回命令执行所需的总时间（单位：毫秒）
	 *
	 * @return 命令执行所需的总时间
	 */
	public long getExecutionTime(){
		return executionTime;
	}

	/**
	 * 返回组成该命令的参数的数组
	 *
	 * @return 组成该命令的参数的数组
	 */
	public List<String> getArgs(){
		return args;
	}

	/**
	 * 返回客户端信息
	 *
	 * @return 客户端信息
	 */
	public Client getClient(){
		return client;
	}

	/**
	 * 返回客户端名称
	 *
	 * @return 客户端名称
	 */
	public String getClientName(){
		return clientName;
	}

	@Override
	public String toString(){
		return ObjectStringBuilder.create()
				.add("id", id)
				.add("timestamp", timestamp)
				.add("executionTime", executionTime)
				.add("args", args)
				.add("client", client)
				.add("clientName", clientName)
				.build();
	}

}
