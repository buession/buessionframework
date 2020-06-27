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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection;

import com.buession.core.utils.Assert;
import com.buession.redis.core.ClusterRedisNode;
import com.buession.redis.core.NamedNode;

import java.util.Set;

/**
 * @author Yong.Teng
 */
public interface RedisSentinelConnection extends RedisConnection {

	/**
	 * 获取 Master 节点
	 *
	 * @return Master 节点
	 */
	Set<ClusterRedisNode> masters();

	/**
	 * 获取 Slave 节点
	 *
	 * @param master
	 * 		Master 节点
	 *
	 * @return Slave 节点
	 */
	default Set<ClusterRedisNode> slaves(NamedNode master){
		Assert.isNull(master, "Master node cannot be 'null' when loading slaves.");
		return slaves(master.getName());
	}

	/**
	 * 获取 Slave 节点
	 *
	 * @param masterName
	 * 		Master 节点名称
	 *
	 * @return Slave 节点
	 */
	Set<ClusterRedisNode> slaves(String masterName);

	/**
	 * 监控 Redis 节点
	 *
	 * @param node
	 * 		待监控的 Redis 节点
	 */
	void monitor(ClusterRedisNode node);

	/**
	 * 删除 Master 节点
	 *
	 * @param master
	 * 		待删除 Master 节点
	 */
	default void remove(NamedNode master){
		Assert.isNull(master, "Master node cloud not be null when trying to remove.");
		remove(master.getName());
	}

	/**
	 * 删除 Master 节点
	 *
	 * @param masterName
	 * 		待删除 Master 节点名称
	 */
	void remove(String masterName);

	default void failover(NamedNode master){
		Assert.isNull(master, "Redis node master must not be null for failover.");
		failover(master.getName());
	}

	void failover(String masterName);

}
