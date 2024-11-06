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
 * 哈希槽和 Redis 实例映射关系,详细信息请看 <a href="http://www.redis.cn/commands/cluster-slots.html" target="_blank">http://www.redis.cn/commands/cluster-slots.html</a>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ClusterSlot implements Serializable {

	private final static long serialVersionUID = -809787517243228575L;

	/**
	 * 哈希槽起始编号
	 */
	private final long start;

	/**
	 * 哈希槽结束编号
	 */
	private final long end;

	/**
	 * master 节点副本列表
	 */
	private final List<RedisServer> masterNodes;

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		哈希槽起始编号
	 * @param end
	 * 		哈希槽结束编号
	 * @param masterNodes
	 * 		master 节点副本列表
	 */
	public ClusterSlot(final long start, final long end, final List<RedisServer> masterNodes){
		this.start = start;
		this.end = end;
		this.masterNodes = masterNodes;
	}

	/**
	 * 返回哈希槽起始编号
	 *
	 * @return 哈希槽起始编号
	 */
	public long getStart(){
		return start;
	}

	/**
	 * 返回哈希槽结束编号
	 *
	 * @return 哈希槽结束编号
	 */
	public long getEnd(){
		return end;
	}

	/**
	 * 返回 master 节点副本列表
	 *
	 * @return master 节点副本列表
	 */
	public List<RedisServer> getMasterNodes(){
		return masterNodes;
	}

	@Override
	public String toString(){
		return ObjectStringBuilder.create()
				.add("start", start)
				.add("end", end)
				.add("masterNodes", masterNodes)
				.build();
	}

}
