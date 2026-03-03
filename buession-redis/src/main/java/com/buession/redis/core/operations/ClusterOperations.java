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
package com.buession.redis.core.operations;

import com.buession.core.IntegerRange;
import com.buession.core.utils.Assert;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterLink;
import com.buession.redis.core.ClusterMigrationOp;
import com.buession.redis.core.ClusterRedisNode;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterShardInfo;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.ClusterSlotStat;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.command.ClusterCommands;

import java.util.List;

/**
 * 集群命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=cluster" target="_blank">https://redis.io/docs/latest/commands/?group=cluster</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface ClusterOperations extends ClusterCommands, RedisOperations {

	@Override
	default Status asking() {
		return execute((client)->client.clusterCommands().asking());
	}

	@Override
	default Status clusterAddSlots(final int... slots) {
		return execute((client)->client.clusterCommands().clusterAddSlots(slots));
	}

	@Override
	default Status clusterAddSlotsRange(final IntegerRange slots) {
		return execute((client)->client.clusterCommands().clusterAddSlotsRange(slots));
	}

	@Override
	default KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		return execute((client)->client.clusterCommands().clusterBumpEpoch());
	}

	@Override
	default Integer clusterCountFailureReports(final String nodeId) {
		return execute((client)->client.clusterCommands().clusterCountFailureReports(nodeId));
	}

	@Override
	default Integer clusterCountFailureReports(final byte[] nodeId) {
		return execute((client)->client.clusterCommands().clusterCountFailureReports(nodeId));
	}

	@Override
	default Long clusterCountKeysInSlot(final int slot) {
		return execute((client)->client.clusterCommands().clusterCountKeysInSlot(slot));
	}

	@Override
	default Status clusterDelSlots(final int... slots) {
		return execute((client)->client.clusterCommands().clusterDelSlots(slots));
	}

	@Override
	default Status clusterDelSlotsRange(final IntegerRange slots) {
		return execute((client)->client.clusterCommands().clusterDelSlotsRange(slots));
	}

	@Override
	default Status clusterFailover(final ClusterFailoverOption option) {
		return execute((client)->client.clusterCommands().clusterFailover(option));
	}

	@Override
	default Status clusterFlushSlots() {
		return execute((client)->client.clusterCommands().clusterFlushSlots());
	}

	@Override
	default Status clusterForget(final String nodeId) {
		return execute((client)->client.clusterCommands().clusterForget(nodeId));
	}

	@Override
	default Status clusterForget(final byte[] nodeId) {
		return execute((client)->client.clusterCommands().clusterForget(nodeId));
	}

	@Override
	default List<String> clusterGetKeysInSlot(final int slot, final int count) {
		return execute((client)->client.clusterCommands().clusterGetKeysInSlot(slot, count));
	}

	@Override
	default ClusterInfo clusterInfo() {
		return execute((client)->client.clusterCommands().clusterInfo());
	}

	@Override
	default Long clusterKeySlot(final String key) {
		return execute((client)->client.clusterCommands().clusterKeySlot(key));
	}

	@Override
	default Long clusterKeySlot(final byte[] key) {
		return execute((client)->client.clusterCommands().clusterKeySlot(key));
	}

	@Override
	default List<ClusterLink> clusterLinks() {
		return execute((client)->client.clusterCommands().clusterLinks());
	}

	@Override
	default Status clusterMeet(final String ip, final int port) {
		return execute((client)->client.clusterCommands().clusterMeet(ip, port));
	}

	@Override
	default Status clusterMeet(final byte[] ip, final int port) {
		return execute((client)->client.clusterCommands().clusterMeet(ip, port));
	}

	/**
	 * 用来连接不同的开启集群支持的 Redis 节点，以进入工作集群
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-meet/" target="_blank">https://redis.io/docs/latest/commands/cluster-meet/</a></p>
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
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-meet/" target="_blank">https://redis.io/docs/latest/commands/cluster-meet/</a></p>
	 *
	 * @param ip
	 * 		Redis 集群节点 IP
	 *
	 * @return 命令成功执行返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status clusterMeet(final byte[] ip) {
		return clusterMeet(ip, RedisNode.DEFAULT_PORT);
	}

	/**
	 * 用来连接不同的开启集群支持的 Redis 节点，以进入工作集群
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cluster-meet/" target="_blank">https://redis.io/docs/latest/commands/cluster-meet/</a></p>
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

	default Status clusterMigration(final IntegerRange slots) {
		return execute((client)->client.clusterCommands().clusterMigration(slots));
	}

	default Object clusterMigration(final ClusterMigrationOp option) {
		return execute((client)->client.clusterCommands().clusterMigration(option));
	}

	default Object clusterMigration(final ClusterMigrationOp option, final String id) {
		return execute((client)->client.clusterCommands().clusterMigration(option, id));
	}

	@Override
	default String clusterMyId() {
		return execute((client)->client.clusterCommands().clusterMyId());
	}

	@Override
	default String clusterMyShardId() {
		return execute((client)->client.clusterCommands().clusterMyShardId());
	}

	@Override
	default List<ClusterRedisNode> clusterNodes() {
		return execute((client)->client.clusterCommands().clusterNodes());
	}

	@Override
	default List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		return execute((client)->client.clusterCommands().clusterReplicas(nodeId));
	}

	@Override
	default List<ClusterRedisNode> clusterReplicas(final byte[] nodeId) {
		return execute((client)->client.clusterCommands().clusterReplicas(nodeId));
	}

	@Override
	default Status clusterReplicate(final String nodeId) {
		return execute((client)->client.clusterCommands().clusterReplicate(nodeId));
	}

	@Override
	default Status clusterReplicate(final byte[] nodeId) {
		return execute((client)->client.clusterCommands().clusterReplicate(nodeId));
	}

	@Override
	default Status clusterReset() {
		return execute((client)->client.clusterCommands().clusterReset());
	}

	@Override
	default Status clusterReset(final ClusterResetOption option) {
		return execute((client)->client.clusterCommands().clusterReset(option));
	}

	@Override
	default Status clusterSaveConfig() {
		return execute((client)->client.clusterCommands().clusterSaveConfig());
	}

	@Override
	default Status clusterSetConfigEpoch(final long configEpoch) {
		return execute((client)->client.clusterCommands().clusterSetConfigEpoch(configEpoch));
	}

	@Override
	default Status clusterSetSlot(final int slot, final ClusterSetSlotOption option, final String nodeId) {
		return execute((client)->client.clusterCommands().clusterSetSlot(slot, option, nodeId));
	}

	@Override
	default Status clusterSetSlot(final int slot, final ClusterSetSlotOption option, final byte[] nodeId) {
		return execute((client)->client.clusterCommands().clusterSetSlot(slot, option, nodeId));
	}

	@Override
	default List<ClusterShardInfo> clusterShards() {
		return execute((client)->client.clusterCommands().clusterShards());
	}

	@Override
	default List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		return execute((client)->client.clusterCommands().clusterSlaves(nodeId));
	}

	@Override
	default List<ClusterRedisNode> clusterSlaves(final byte[] nodeId) {
		return execute((client)->client.clusterCommands().clusterSlaves(nodeId));
	}

	@Override
	default ClusterSlotStat clusterSlotStats() {
		return execute((client)->client.clusterCommands().clusterSlotStats());
	}

	@Override
	default List<ClusterSlot> clusterSlots() {
		return execute((client)->client.clusterCommands().clusterSlots());
	}

	@Override
	default Status readOnly() {
		return execute((client)->client.clusterCommands().readOnly());
	}

	@Override
	default Status readWrite() {
		return execute((client)->client.clusterCommands().readWrite());
	}

}
