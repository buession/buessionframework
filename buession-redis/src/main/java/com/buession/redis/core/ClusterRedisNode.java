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

import java.util.Set;

/**
 * 集群节点
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
@Deprecated
public class ClusterRedisNode extends RedisClusterNode {

	private final static long serialVersionUID = -6841072654023820951L;

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		节点ID
	 * @param ip
	 * 		客户端与节点通信使用的地址
	 * @param port
	 * 		客户端与节点通信使用的端口
	 * @param flags
	 * 		标记位
	 * @param masterId
	 * 		Master 节点 Id
	 * @param pingSent
	 * 		最近一次发送 ping 的时间（unix 毫秒时间戳）
	 * @param pongSent
	 * 		最近一次收到 pong 的时间（unix 毫秒时间戳）
	 * @param configEpoch
	 * 		节点的 epoch 值
	 * @param linkState
	 * 		node-to-node 集群总线使用的链接的状态
	 * @param slot
	 * 		哈希槽值或者一个哈希槽范围
	 */
	public ClusterRedisNode(final String id, final String ip, final int port, final Set<Flag> flags,
	                        final String masterId, final long pingSent, final long pongSent, final long configEpoch,
	                        final LinkState linkState, final SlotRange slot) {
		super(id, ip, port, flags, masterId, pingSent, pongSent, configEpoch, linkState, slot);
	}

}
