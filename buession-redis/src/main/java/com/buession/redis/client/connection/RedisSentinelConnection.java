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

import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.redis.core.RedisNamedNode;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.RedisSentinelNode;
import com.buession.redis.core.RedisServer;

import java.util.List;
import java.util.Map;

/**
 * Redis 哨兵连接器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface RedisSentinelConnection extends RedisConnection {

	/**
	 * 返回哨兵节点连接超时
	 *
	 * @return 哨兵节点连接超时（单位：毫秒）
	 */
	int getSentinelConnectTimeout();

	/**
	 * 设置哨兵节点连接超时
	 *
	 * @param sentinelConnectTimeout
	 * 		哨兵节点连接超时（单位：毫秒）
	 */
	void setSentinelConnectTimeout(int sentinelConnectTimeout);

	/**
	 * 返回哨兵节点读取超时
	 *
	 * @return 哨兵节点读取超时（单位：毫秒）
	 */
	int getSentinelSoTimeout();

	/**
	 * 设置哨兵节点读取超时
	 *
	 * @param sentinelSoTimeout
	 * 		哨兵节点读取超时（单位：毫秒）
	 */
	void setSentinelSoTimeout(int sentinelSoTimeout);

	/**
	 * 获取当前 Master 节点
	 *
	 * @return 当前 Master 节点
	 */
	String myId();

	/**
	 * 返回哨兵节点
	 *
	 * @param master
	 * 		Master 节点
	 *
	 * @return 哨兵节点
	 */
	default List<RedisNode> sentinels(RedisNamedNode master) {
		Assert.isNull(master, "Redis master node cloud not be 'null' for loading sentinels.");
		return sentinels(master.getName());
	}

	/**
	 * 返回哨兵节点
	 *
	 * @param masterName
	 * 		master 名称
	 *
	 * @return 哨兵节点
	 */
	List<RedisNode> sentinels(String masterName);

	String sentinelSet(String masterName, Map<String, String> parameters);

	/**
	 * 返回 Master 节点列表
	 *
	 * @return Master 节点列表
	 */
	List<RedisServer> masters();

	/**
	 * 返回 Master 节点列表
	 *
	 * @return Master 节点列表
	 */
	RedisServer master(String masterName);

	/**
	 * 返回 Slave 节点列表
	 *
	 * @param master
	 * 		Master 节点
	 *
	 * @return Slave 节点列表
	 */
	default List<RedisServer> slaves(RedisNamedNode master) {
		Assert.isNull(master, "Redis master node cloud not be 'null' for loading slaves.");
		return slaves(master.getName());
	}

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
	 * 返回 Replica 节点列表
	 *
	 * @param masterName
	 * 		Master 节点名称
	 *
	 * @return Replica 节点列表
	 */
	List<RedisServer> replicas(String masterName);

	/**
	 * failover
	 *
	 * @param master
	 * 		Master 节点
	 *
	 * @return 操作结果
	 */
	default Status failover(RedisNamedNode master) {
		Assert.isNull(master, "Redis master node cloud not be 'null' for loading failover.");
		return failover(master.getName());
	}

	/**
	 * failover
	 *
	 * @param masterName
	 * 		Master 节点名称
	 */
	Status failover(String masterName);

	/**
	 * 监控节点
	 *
	 * @param master
	 * 		Master 节点
	 * @param ip
	 * 		-
	 * @param port
	 * 		-
	 * @param quorum
	 * 		-
	 */
	default Status monitor(RedisNamedNode master, String ip, int port, int quorum) {
		Assert.isNull(master, "Redis master node cloud not be 'null' for loading monitor.");
		return monitor(master.getName(), ip, port, quorum);
	}

	/**
	 * 监控节点
	 *
	 * @param masterName
	 * 		Master 节点名称
	 * @param ip
	 * 		-
	 * @param port
	 * 		-
	 * @param quorum
	 * 		-
	 */
	Status monitor(String masterName, String ip, int port, int quorum);

	/**
	 * 重置
	 *
	 * @param pattern
	 * 		-
	 *
	 * @return 操作结果
	 */
	Long reset(String pattern);

	/**
	 * 移除节点
	 *
	 * @param master
	 * 		节点
	 */
	default void remove(RedisNamedNode master) {
		Assert.isNull(master, "Master node cloud be 'null' when trying to remove.");
		remove(master.getName());
	}

	/**
	 * 移除节点
	 *
	 * @param masterName
	 * 		节点名称
	 *
	 * @return 操作结果
	 */
	Status remove(String masterName);

}
