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
package com.buession.redis.client.jedis.command;

import com.buession.core.IntegerRange;
import com.buession.lang.KeyValue;
import com.buession.lang.Order;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.RedisClusterNode;
import com.buession.redis.core.command.args.cluster.FailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterLink;
import com.buession.redis.core.command.args.cluster.MigrationOperation;
import com.buession.redis.core.command.args.cluster.ResetOption;
import com.buession.redis.core.command.args.cluster.SetSlotOption;
import com.buession.redis.core.ClusterShardInfo;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.ClusterSlotStat;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.command.ClusterCommands;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.RedisSubCommand;

import java.util.List;

/**
 * Jedis 集群命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisClusterCommands extends AbstractJedisRedisCommands implements ClusterCommands {

	public JedisClusterCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status asking() {
		return executeCommand(RedisCommand.ASKING);
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_ADDSLOTS, args);
	}

	@Override
	public Status clusterAddSlotsRange(final IntegerRange... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_ADDSLOTSRANGE, args);
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_BUMPEPOCH);
	}

	@Override
	public Integer clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_COUNTFAILUREREPORTS, args);
	}

	@Override
	public Integer clusterCountFailureReports(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_COUNTFAILUREREPORTS, args);
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create(slot);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_COUNTKEYSINSLOT, args);
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_DELSLOTS, args);
	}

	@Override
	public Status clusterDelSlotsRange(final IntegerRange... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_DELSLOTSRANGE, args);
	}

	@Override
	public Status clusterFailover(final FailoverOption option) {
		final CommandArguments args = CommandArguments.create(option);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_FAILOVER, args);
	}

	@Override
	public Status clusterFlushSlots() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_FLUSHSLOTS);
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_FORGET, args);
	}

	@Override
	public Status clusterForget(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_FORGET, args);
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final int count) {
		final CommandArguments args = CommandArguments.create(slot).add(count);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_GETKEYSINSLOT, args);
	}

	@Override
	public ClusterInfo clusterInfo() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_INFO);
	}

	@Override
	public Long clusterKeySlot(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_KEYSLOT, args);
	}

	@Override
	public Long clusterKeySlot(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_KEYSLOT, args);
	}

	@Override
	public List<ClusterLink> clusterLinks() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_LINKS);
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create().add(ip).add(port);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MEET, args);
	}

	@Override
	public Status clusterMeet(final byte[] ip, final int port) {
		final CommandArguments args = CommandArguments.create().add(ip).add(port);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MEET, args);
	}

	@Override
	public Status clusterMigration(final IntegerRange... slots) {
		final CommandArguments args = CommandArguments.create().add("IMPORT").add(slots);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MIGRATION, args);
	}

	@Override
	public Object clusterMigration(final MigrationOperation migrationOperation) {
		final CommandArguments args = CommandArguments.create(migrationOperation).add(Keyword.Common.ALL);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MIGRATION, args);
	}

	@Override
	public Object clusterMigration(final MigrationOperation migrationOperation, final String id) {
		final CommandArguments args = CommandArguments.create(migrationOperation).add("ID", id);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MIGRATION, args);
	}

	@Override
	public Object clusterMigration(final MigrationOperation migrationOperation, final byte[] id) {
		final CommandArguments args = CommandArguments.create(migrationOperation).add("ID").add(id);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MIGRATION, args);
	}

	@Override
	public String clusterMyId() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MYID);
	}

	@Override
	public String clusterMyShardId() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MYSHARDID);
	}

	@Override
	public List<RedisClusterNode> clusterNodes() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_NODES);
	}

	@Override
	public List<RedisClusterNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_REPLICAS, args);
	}

	@Override
	public List<RedisClusterNode> clusterReplicas(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_REPLICAS, args);
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_REPLICATE, args);
	}

	@Override
	public Status clusterReplicate(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_REPLICATE, args);
	}

	@Override
	public Status clusterReset() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_RESET);
	}

	@Override
	public Status clusterReset(final ResetOption option) {
		final CommandArguments args = CommandArguments.create(option);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_RESET, args);
	}

	@Override
	public Status clusterSaveConfig() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SAVECONFIG);
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create(configEpoch);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SETCONFIGEPOCH, args);
	}

	@Override
	public Status clusterSetSlot(final int slot, final SetSlotOption option, final String nodeId) {
		final CommandArguments args = CommandArguments.create(slot).add(option).add(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SETSLOT, args);
	}

	@Override
	public Status clusterSetSlot(final int slot, final SetSlotOption option, final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(slot).add(option).add(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SETSLOT, args);
	}

	@Override
	public List<ClusterShardInfo> clusterShards() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SHARDS);
	}

	@Override
	public List<RedisClusterNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLAVES, args);
	}

	@Override
	public List<RedisClusterNode> clusterSlaves(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLAVES, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final IntegerRange slot) {
		final CommandArguments args = CommandArguments.create().add("SLOTSRANGE").add(slot);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final IntegerRange slot, final int limit) {
		final CommandArguments args = CommandArguments.create().add("SLOTSRANGE").add(slot)
				.add(Keyword.Common.LIMIT).add(limit);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final IntegerRange slot, final int limit, final Order order) {
		final CommandArguments args = CommandArguments.create().add("SLOTSRANGE").add(slot)
				.add(Keyword.Common.LIMIT).add(limit).add(order);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final String metric) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY", metric);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final byte[] metric) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY").add(metric);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final String metric, final int limit) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY", metric).add(Keyword.Common.LIMIT)
				.add(limit);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final byte[] metric, final int limit) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY").add(metric).add(Keyword.Common.LIMIT)
				.add(limit);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final String metric, final int limit, final Order order) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY", metric).add(Keyword.Common.LIMIT)
				.add(limit).add(order);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final byte[] metric, final int limit, final Order order) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY").add(metric).add(Keyword.Common.LIMIT)
				.add(limit).add(order);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final int limit) {
		final CommandArguments args = CommandArguments.create().add(Keyword.Common.LIMIT).add(limit);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final int limit, final Order order) {
		final CommandArguments args = CommandArguments.create().add(Keyword.Common.LIMIT).add(limit).add(order);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final Order order) {
		final CommandArguments args = CommandArguments.create(order);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOTS);
	}

	@Override
	public Status readOnly() {
		return executeCommand(RedisCommand.READONLY);
	}

	@Override
	public Status readWrite() {
		return executeCommand(RedisCommand.READWRITE);
	}

}
