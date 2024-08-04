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
package com.buession.redis.core.operations;

import com.buession.core.utils.Assert;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterRedisNode;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.command.ClusterCommands;

import java.util.List;

/**
 * 集群命令
 *
 * <p>详情说明 <a href="http://doc.redisfans.com/topic/cluster-tutorial.html" target="_blank">http://doc.redisfans.com/topic/cluster-tutorial.html</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface ClusterOperations extends ClusterCommands, RedisOperations {


	@Override
	default String clusterMyId() {
		return execute((client)->client.clusterOperations().clusterMyId());
	}

	@Override
	default Status clusterAddSlots(final int... slots) {
		return execute((client)->client.clusterOperations().clusterAddSlots(slots));
	}

	@Override
	default List<ClusterSlot> clusterSlots() {
		return execute((client)->client.clusterOperations().clusterSlots());
	}

	@Override
	default Integer clusterCountFailureReports(final String nodeId) {
		return execute((client)->client.clusterOperations().clusterCountFailureReports(nodeId));
	}

	@Override
	default Integer clusterCountFailureReports(final byte[] nodeId) {
		return execute((client)->client.clusterOperations().clusterCountFailureReports(nodeId));
	}

	@Override
	default Long clusterCountKeysInSlot(final int slot) {
		return execute((client)->client.clusterOperations().clusterCountKeysInSlot(slot));
	}

	@Override
	default Status clusterDelSlots(final int... slots) {
		return execute((client)->client.clusterOperations().clusterDelSlots(slots));
	}

	@Override
	default Status clusterFlushSlots() {
		return execute((client)->client.clusterOperations().clusterFlushSlots());
	}

	@Override
	default Status clusterFailover(final ClusterFailoverOption clusterFailoverOption) {
		return execute((client)->client.clusterOperations().clusterFailover(clusterFailoverOption));
	}

	@Override
	default Status clusterForget(final String nodeId) {
		return execute((client)->client.clusterOperations().clusterForget(nodeId));
	}

	@Override
	default Status clusterForget(final byte[] nodeId) {
		return execute((client)->client.clusterOperations().clusterForget(nodeId));
	}

	@Override
	default List<String> clusterGetKeysInSlot(final int slot, final long count) {
		return execute((client)->client.clusterOperations().clusterGetKeysInSlot(slot, count));
	}

	@Override
	default ClusterInfo clusterInfo() {
		return execute((client)->client.clusterOperations().clusterInfo());
	}

	@Override
	default Status clusterMeet(final String ip, final int port) {
		return execute((client)->client.clusterOperations().clusterMeet(ip, port));
	}

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
	default Status clusterMeet(final String ip) {
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
	default Status clusterMeet(final RedisNode node) {
		Assert.isNull(node, "Redis cluster node cloud not be null");
		Assert.isBlank(node.getHost(), "Redis cluster host cloud not be null or empty");

		return clusterMeet(node.getHost(), node.getPort());
	}

	@Override
	default List<ClusterRedisNode> clusterNodes() {
		return execute((client)->client.clusterOperations().clusterNodes());
	}

	@Override
	default List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		return execute((client)->client.clusterOperations().clusterSlaves(nodeId));
	}

	@Override
	default List<ClusterRedisNode> clusterSlaves(final byte[] nodeId) {
		return execute((client)->client.clusterOperations().clusterSlaves(nodeId));
	}

	@Override
	default List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		return execute((client)->client.clusterOperations().clusterReplicas(nodeId));
	}

	@Override
	default List<ClusterRedisNode> clusterReplicas(final byte[] nodeId) {
		return execute((client)->client.clusterOperations().clusterReplicas(nodeId));
	}

	@Override
	default Status clusterReplicate(final String nodeId) {
		return execute((client)->client.clusterOperations().clusterReplicate(nodeId));
	}

	@Override
	default Status clusterReplicate(final byte[] nodeId) {
		return execute((client)->client.clusterOperations().clusterReplicate(nodeId));
	}

	@Override
	default Status clusterReset() {
		return execute((client)->client.clusterOperations().clusterReset());
	}

	@Override
	default Status clusterReset(final ClusterResetOption clusterResetOption) {
		return execute((client)->client.clusterOperations().clusterReset(clusterResetOption));
	}

	@Override
	default Status clusterSaveConfig() {
		return execute((client)->client.clusterOperations().clusterSaveConfig());
	}

	@Override
	default Status clusterSetConfigEpoch(final long configEpoch) {
		return execute((client)->client.clusterOperations().clusterSetConfigEpoch(configEpoch));
	}

	@Override
	default KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		return execute((client)->client.clusterOperations().clusterBumpEpoch());
	}

	@Override
	default Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId) {
		return execute((client)->client.clusterOperations().clusterSetSlot(slot, setSlotOption, nodeId));
	}

	@Override
	default Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final byte[] nodeId) {
		return execute((client)->client.clusterOperations().clusterSetSlot(slot, setSlotOption, nodeId));
	}

	@Override
	default Status asking() {
		return execute((client)->client.clusterOperations().asking());
	}

	@Override
	default Status readWrite() {
		return execute((client)->client.clusterOperations().readWrite());
	}

	@Override
	default Status readOnly() {
		return execute((client)->client.clusterOperations().readOnly());
	}

}
