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
package com.buession.redis.client.connection;

import com.buession.redis.core.RedisNamedNode;
import com.buession.redis.core.RedisSentinelNode;
import com.buession.redis.core.RedisServer;

import java.util.List;

/**
 * Redis 哨兵连接器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface RedisSentinelConnection extends RedisConnection {

	/**
	 * 返回 Master 节点列表
	 *
	 * @return Master 节点列表
	 */
	List<RedisServer> masters();

	/**
	 * 返回 Slave 节点列表
	 *
	 * @param master
	 * 		Master 节点
	 *
	 * @return Slave 节点列表
	 */
	List<RedisServer> slaves(RedisNamedNode master);

	/**
	 * 返回 Slave 节点列表
	 *
	 * @param masterName
	 * 		Master 节点名称
	 *
	 * @return Slave 节点列表
	 */
	List<RedisServer> slaves(String masterName);

	/**
	 * failover
	 *
	 * @param namedNode
	 * 		节点
	 */
	void failover(RedisNamedNode namedNode);

	/**
	 * 监控节点
	 *
	 * @param server
	 * 		节点
	 */
	void monitor(RedisSentinelNode server);

	/**
	 * 移除节点
	 *
	 * @param master
	 * 		节点
	 */
	void remove(RedisNamedNode master);

	/**
	 * 移除节点
	 *
	 * @param masterName
	 * 		节点名称
	 */
	void remove(String masterName);

}
