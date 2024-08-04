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
package com.buession.redis.core.operations;

import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.command.ClusterCommands;

/**
 * 集群命令
 *
 * <p>详情说明 <a href="http://doc.redisfans.com/topic/cluster-tutorial.html" target="_blank">http://doc.redisfans.com/topic/cluster-tutorial.html</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface ClusterOperations extends ClusterCommands, RedisOperations {

	/**
	 * 用来连接不同的开启集群支持的 Redis 节点，以进入工作集群
	 *
	 * <p>详情说明
	 * <a href="http://www.redis.cn/commands/cluster-meet.html" target="_blank">http://www.redis.cn/commands/cluster-meet.html</a></p>
	 *
	 * @param ip
	 * 		Redis 集群节点 IP
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status clusterMeet(final String ip){
		return clusterMeet(ip, RedisNode.DEFAULT_PORT);
	}

	/**
	 * 用来连接不同的开启集群支持的 Redis 节点，以进入工作集群
	 *
	 * <p>详情说明
	 * <a href="http://www.redis.cn/commands/cluster-meet.html" target="_blank">http://www.redis.cn/commands/cluster-meet.html</a></p>
	 *
	 * @param node
	 * 		Redis 集群节点
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status clusterMeet(final RedisNode node){
		Assert.isNull(node, "Redis cluster node cloud not be null");
		Assert.isBlank(node.getHost(), "Redis cluster host cloud not be null or empty");

		return clusterMeet(node.getHost(), node.getPort());
	}

}
