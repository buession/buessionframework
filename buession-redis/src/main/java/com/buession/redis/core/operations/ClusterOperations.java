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
import com.buession.lang.KeyValue;
import com.buession.lang.Order;
import com.buession.lang.Status;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.RedisClusterNode;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.cluster.FailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterLink;
import com.buession.redis.core.command.args.cluster.MigrationOperation;
import com.buession.redis.core.command.args.cluster.ResetOption;
import com.buession.redis.core.command.args.cluster.SetSlotOption;
import com.buession.redis.core.ClusterShardInfo;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.ClusterSlotStat;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.command.ClusterCommands;
import com.buession.redis.utils.KeyUtils;

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
		return doExecute((cmd)->cmd.asking());
	}

	@Override
	default Status clusterAddSlots(final int... slots) {
		return doExecute((cmd)->cmd.clusterAddSlots(slots));
	}

	@Override
	default Status clusterAddSlotsRange(final IntegerRange... slots) {
		return doExecute((cmd)->cmd.clusterAddSlotsRange(slots));
	}

	@Override
	default KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		return doExecute((cmd)->cmd.clusterBumpEpoch());
	}

	@Override
	default Integer clusterCountFailureReports(final String nodeId) {
		return doExecute((cmd)->cmd.clusterCountFailureReports(nodeId));
	}

	@Override
	default Integer clusterCountFailureReports(final byte[] nodeId) {
		return doExecute((cmd)->cmd.clusterCountFailureReports(nodeId));
	}

	@Override
	default Long clusterCountKeysInSlot(final int slot) {
		return doExecute((cmd)->cmd.clusterCountKeysInSlot(slot));
	}

	@Override
	default Status clusterDelSlots(final int... slots) {
		return doExecute((cmd)->cmd.clusterDelSlots(slots));
	}

	@Override
	default Status clusterDelSlotsRange(final IntegerRange... slots) {
		return doExecute((cmd)->cmd.clusterDelSlotsRange(slots));
	}

	@Override
	default Status clusterFailover(final FailoverOption option) {
		return doExecute((cmd)->cmd.clusterFailover(option));
	}

	@Override
	default Status clusterFlushSlots() {
		return doExecute((cmd)->cmd.clusterFlushSlots());
	}

	@Override
	default Status clusterForget(final String nodeId) {
		return doExecute((cmd)->cmd.clusterForget(nodeId));
	}

	@Override
	default Status clusterForget(final byte[] nodeId) {
		return doExecute((cmd)->cmd.clusterForget(nodeId));
	}

	@Override
	default List<String> clusterGetKeysInSlot(final int slot, final int count) {
		return doExecute((cmd)->cmd.clusterGetKeysInSlot(slot, count));
	}

	@Override
	default ClusterInfo clusterInfo() {
		return doExecute((cmd)->cmd.clusterInfo());
	}

	@Override
	default Long clusterKeySlot(final String key) {
		return doExecute((cmd)->cmd.clusterKeySlot(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long clusterKeySlot(final byte[] key) {
		return doExecute((cmd)->cmd.clusterKeySlot(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<ClusterLink> clusterLinks() {
		return doExecute((cmd)->cmd.clusterLinks());
	}

	@Override
	default Status clusterMeet(final String ip, final int port) {
		return doExecute((cmd)->cmd.clusterMeet(ip, port));
	}

	@Override
	default Status clusterMeet(final byte[] ip, final int port) {
		return doExecute((cmd)->cmd.clusterMeet(ip, port));
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
		return clusterMeet(node.getHost(), node.getPort());
	}

	@Override
	default Status clusterMigration(final IntegerRange... slots) {
		return doExecute((cmd)->cmd.clusterMigration(slots));
	}

	@Override
	default Object clusterMigration(final MigrationOperation option) {
		return doExecute((cmd)->cmd.clusterMigration(option));
	}

	@Override
	default Object clusterMigration(final MigrationOperation option, final String id) {
		return doExecute((cmd)->cmd.clusterMigration(option, id));
	}

	@Override
	default Object clusterMigration(final MigrationOperation option, final byte[] id) {
		return doExecute((cmd)->cmd.clusterMigration(option, id));
	}

	@Override
	default String clusterMyId() {
		return doExecute((cmd)->cmd.clusterMyId());
	}

	@Override
	default String clusterMyShardId() {
		return doExecute((cmd)->cmd.clusterMyShardId());
	}

	@Override
	default List<RedisClusterNode> clusterNodes() {
		return doExecute((cmd)->cmd.clusterNodes());
	}

	@Override
	default List<RedisClusterNode> clusterReplicas(final String nodeId) {
		return doExecute((cmd)->cmd.clusterReplicas(nodeId));
	}

	@Override
	default List<RedisClusterNode> clusterReplicas(final byte[] nodeId) {
		return doExecute((cmd)->cmd.clusterReplicas(nodeId));
	}

	@Override
	default Status clusterReplicate(final String nodeId) {
		return doExecute((cmd)->cmd.clusterReplicate(nodeId));
	}

	@Override
	default Status clusterReplicate(final byte[] nodeId) {
		return doExecute((cmd)->cmd.clusterReplicate(nodeId));
	}

	@Override
	default Status clusterReset() {
		return doExecute((cmd)->cmd.clusterReset());
	}

	@Override
	default Status clusterReset(final ResetOption option) {
		return doExecute((cmd)->cmd.clusterReset(option));
	}

	@Override
	default Status clusterSaveConfig() {
		return doExecute((cmd)->cmd.clusterSaveConfig());
	}

	@Override
	default Status clusterSetConfigEpoch(final long configEpoch) {
		return doExecute((cmd)->cmd.clusterSetConfigEpoch(configEpoch));
	}

	@Override
	default Status clusterSetSlot(final int slot, final SetSlotOption option, final String nodeId) {
		return doExecute((cmd)->cmd.clusterSetSlot(slot, option, nodeId));
	}

	@Override
	default Status clusterSetSlot(final int slot, final SetSlotOption option, final byte[] nodeId) {
		return doExecute((cmd)->cmd.clusterSetSlot(slot, option, nodeId));
	}

	@Override
	default List<ClusterShardInfo> clusterShards() {
		return doExecute((cmd)->cmd.clusterShards());
	}

	@Override
	default List<RedisClusterNode> clusterSlaves(final String nodeId) {
		return doExecute((cmd)->cmd.clusterSlaves(nodeId));
	}

	@Override
	default List<RedisClusterNode> clusterSlaves(final byte[] nodeId) {
		return doExecute((cmd)->cmd.clusterSlaves(nodeId));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats() {
		return doExecute((cmd)->cmd.clusterSlotStats());
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final IntegerRange slot) {
		return doExecute((cmd)->cmd.clusterSlotStats(slot));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final IntegerRange slot, final int limit) {
		return doExecute((cmd)->cmd.clusterSlotStats(slot, limit));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final IntegerRange slot, final int limit, final Order order) {
		return doExecute((cmd)->cmd.clusterSlotStats(slot, limit, order));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final String metric) {
		return doExecute((cmd)->cmd.clusterSlotStats(metric));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final byte[] metric) {
		return doExecute((cmd)->cmd.clusterSlotStats(metric));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final String metric, final int limit) {
		return doExecute((cmd)->cmd.clusterSlotStats(metric, limit));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final byte[] metric, final int limit) {
		return doExecute((cmd)->cmd.clusterSlotStats(metric, limit));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final String metric, final int limit, final Order order) {
		return doExecute((cmd)->cmd.clusterSlotStats(metric, limit, order));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final byte[] metric, final int limit, final Order order) {
		return doExecute((cmd)->cmd.clusterSlotStats(metric, limit, order));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final int limit) {
		return doExecute((cmd)->cmd.clusterSlotStats(limit));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final int limit, final Order order) {
		return doExecute((cmd)->cmd.clusterSlotStats(limit, order));
	}

	@Override
	default List<ClusterSlotStat> clusterSlotStats(final Order order) {
		return doExecute((cmd)->cmd.clusterSlotStats(order));
	}

	@Override
	default List<ClusterSlot> clusterSlots() {
		return doExecute((cmd)->cmd.clusterSlots());
	}

	@Override
	default Status readOnly() {
		return doExecute((cmd)->cmd.readOnly());
	}

	@Override
	default Status readWrite() {
		return doExecute((cmd)->cmd.readWrite());
	}

	private <R> R doExecute(final Command.Executor<ClusterCommands, R> executor) {
		return execute((client)->executor.execute(client.clusterCommands()));
	}

}
