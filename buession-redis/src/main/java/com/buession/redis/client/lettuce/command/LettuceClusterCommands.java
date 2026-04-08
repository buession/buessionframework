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
package com.buession.redis.client.lettuce.command;

import com.buession.core.IntegerRange;
import com.buession.core.converter.ListConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Order;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
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
import com.buession.redis.core.internal.convert.lettuce.params.RangeRangeConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ClusterShardInfoConverter;
import com.buession.redis.core.internal.convert.response.BumpEpochConverter;
import com.buession.redis.core.internal.convert.response.ClusterInfoConverter;
import com.buession.redis.core.internal.convert.response.ClusterLinkConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodeConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodesConverter;
import com.buession.redis.core.internal.convert.response.ClusterSlotConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Range;

import java.util.List;

/**
 * Lettuce 集群命令操作
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceClusterCommands extends AbstractLettuceRedisCommands implements ClusterCommands {

	public LettuceClusterCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Status asking() {
		return executeCommand(RedisCommand.ASKING, (cmd)->cmd.asking(), new OkStatusConverter());
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_ADDSLOTS, args,
				(cmd)->cmd.clusterAddSlots(slots),
				new OkStatusConverter());
	}

	@Override
	public Status clusterAddSlotsRange(final IntegerRange... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_ADDSLOTSRANGE, args,
				(cmd)->cmd.clusterAddSlotsRange(createRangs(slots)), new OkStatusConverter());
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_BUMPEPOCH, (cmd)->cmd.clusterBumpepoch(),
				new BumpEpochConverter());
	}

	@Override
	public Integer clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_COUNTFAILUREREPORTS, args,
				(cmd)->cmd.clusterCountFailureReports(nodeId), Long::intValue);
	}

	@Override
	public Integer clusterCountFailureReports(final byte[] nodeId) {
		return clusterCountFailureReports(SafeEncoder.encode(nodeId));
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create(slot);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_COUNTKEYSINSLOT, args,
				(cmd)->cmd.clusterCountKeysInSlot(slot));
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_DELSLOTS, args,
				(cmd)->cmd.clusterDelSlots(slots),
				new OkStatusConverter());
	}

	@Override
	public Status clusterDelSlotsRange(final IntegerRange... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_DELSLOTSRANGE, args,
				(cmd)->cmd.clusterDelSlotsRange(createRangs(slots)), new OkStatusConverter());
	}

	@Override
	public Status clusterFailover(final FailoverOption option) {
		final CommandArguments args = CommandArguments.create(option);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_FAILOVER, args,
				(cmd)->cmd.clusterFailover(FailoverOption.FORCE == option), new OkStatusConverter());
	}

	@Override
	public Status clusterFlushSlots() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_FLUSHSLOTS, (cmd)->cmd.clusterFlushslots(),
				new OkStatusConverter());
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_FORGET, args,
				(cmd)->cmd.clusterForget(nodeId),
				new OkStatusConverter());
	}

	@Override
	public Status clusterForget(final byte[] nodeId) {
		return clusterForget(SafeEncoder.encode(nodeId));
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final int count) {
		final CommandArguments args = CommandArguments.create(slot).add(count);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_GETKEYSINSLOT, args,
				(cmd)->cmd.clusterGetKeysInSlot(slot, count), new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public ClusterInfo clusterInfo() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_INFO, (cmd)->cmd.clusterInfo(),
				new ClusterInfoConverter());
	}

	@Override
	public Long clusterKeySlot(final String key) {
		return clusterKeySlot(SafeEncoder.encode(key));
	}

	@Override
	public Long clusterKeySlot(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_KEYSLOT, args,
				(cmd)->cmd.clusterKeyslot(key));
	}

	@Override
	public List<ClusterLink> clusterLinks() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_LINKS, (cmd)->cmd.clusterLinks(),
				new ListConverter<>(new ClusterLinkConverter()));
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create().add(ip, port);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MEET, args,
				(cmd)->cmd.clusterMeet(ip, port),
				new OkStatusConverter());
	}

	@Override
	public Status clusterMeet(final byte[] ip, final int port) {
		return clusterMeet(SafeEncoder.encode(ip), port);
	}

	@Override
	public Status clusterMigration(final IntegerRange... slots) {
		final CommandArguments args = CommandArguments.create().add("IMPORT", slots);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MIGRATION, args);
	}

	@Override
	public Object clusterMigration(final MigrationOperation option) {
		final CommandArguments args = CommandArguments.create(option).add(Keyword.Common.ALL);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MIGRATION, args);
	}

	@Override
	public Object clusterMigration(final MigrationOperation option, final String id) {
		final CommandArguments args = CommandArguments.create(option).add("ID", id);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MIGRATION, args);
	}

	@Override
	public Object clusterMigration(final MigrationOperation option, final byte[] id) {
		final CommandArguments args = CommandArguments.create(option).add("ID", id);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MIGRATION, args);
	}

	@Override
	public String clusterMyId() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MYID, (cmd)->cmd.clusterMyId());
	}

	@Override
	public String clusterMyShardId() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_MYSHARDID, (cmd)->cmd.clusterMyShardId());
	}

	@Override
	public List<RedisClusterNode> clusterNodes() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_NODES, (cmd)->cmd.clusterNodes(),
				new ClusterNodesConverter());
	}

	@Override
	public List<RedisClusterNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(
				RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_REPLICAS, args, (cmd)->cmd.clusterReplicas(nodeId),
				new ListConverter<>(new ClusterNodeConverter()));
	}

	@Override
	public List<RedisClusterNode> clusterReplicas(final byte[] nodeId) {
		return clusterReplicas(SafeEncoder.encode(nodeId));
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(
				RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_REPLICATE, args, (cmd)->cmd.clusterReplicate(nodeId),
				new OkStatusConverter());
	}

	@Override
	public Status clusterReplicate(final byte[] nodeId) {
		return clusterReplicate(SafeEncoder.encode(nodeId));
	}

	@Override
	public Status clusterReset() {
		return clusterReset(ResetOption.SOFT);
	}

	@Override
	public Status clusterReset(final ResetOption option) {
		final CommandArguments args = CommandArguments.create(option);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_RESET, args,
				(cmd)->cmd.clusterReset(ResetOption.HARD == option), new OkStatusConverter());
	}

	@Override
	public Status clusterSaveConfig() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SAVECONFIG, (cmd)->cmd.clusterSaveconfig(),
				new OkStatusConverter());
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create(configEpoch);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SETCONFIGEPOCH, args,
				(cmd)->cmd.clusterSetConfigEpoch(configEpoch), new OkStatusConverter());
	}

	@Override
	public Status clusterSetSlot(final int slot, final SetSlotOption option, final String nodeId) {
		final CommandArguments args = CommandArguments.create(slot).add(option).add(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SETSLOT, args, (cmd)->switch(option){
			case IMPORTING -> cmd.clusterSetSlotImporting(slot, nodeId);
			case MIGRATING -> cmd.clusterSetSlotMigrating(slot, nodeId);
			case STABLE -> cmd.clusterSetSlotStable(slot);
			case NODE -> cmd.clusterSetSlotNode(slot, nodeId);
		}, new OkStatusConverter());
	}

	@Override
	public Status clusterSetSlot(final int slot, final SetSlotOption option, final byte[] nodeId) {
		return clusterSetSlot(slot, option, SafeEncoder.encode(nodeId));
	}

	@Override
	public List<ClusterShardInfo> clusterShards() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SHARDS, (cmd)->cmd.clusterShards(),
				new ListConverter<>(new ClusterShardInfoConverter()));
	}

	@Override
	public List<RedisClusterNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLAVES, args,
				(cmd)->cmd.clusterSlaves(nodeId), new ListConverter<>(new ClusterNodeConverter()));
	}

	@Override
	public List<RedisClusterNode> clusterSlaves(final byte[] nodeId) {
		return clusterSlaves(SafeEncoder.encode(nodeId));
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final IntegerRange slot) {
		final CommandArguments args = CommandArguments.create().add("SLOTSRANGE", slot);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final IntegerRange slot, final int limit) {
		final CommandArguments args = CommandArguments.create().add("SLOTSRANGE", slot)
				.add(Keyword.Common.LIMIT, limit);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final IntegerRange slot, final int limit, final Order order) {
		final CommandArguments args = CommandArguments.create().add("SLOTSRANGE", slot)
				.add(Keyword.Common.LIMIT, limit).add(order);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final String metric) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY", metric);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final byte[] metric) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY", metric);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final String metric, final int limit) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY", metric).add(Keyword.Common.LIMIT, limit);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final byte[] metric, final int limit) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY", metric).add(Keyword.Common.LIMIT, limit);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final String metric, final int limit, final Order order) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY", metric).add(Keyword.Common.LIMIT, limit)
				.add(order);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final byte[] metric, final int limit, final Order order) {
		final CommandArguments args = CommandArguments.create().add("ORDERBY", metric).add(Keyword.Common.LIMIT, limit)
				.add(order);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final int limit) {
		final CommandArguments args = CommandArguments.create().add(Keyword.Common.LIMIT, limit);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final int limit, final Order order) {
		final CommandArguments args = CommandArguments.create().add(Keyword.Common.LIMIT, limit).add(order);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlotStat> clusterSlotStats(final Order order) {
		final CommandArguments args = CommandArguments.create(order);
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOT_STATS, args);
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		return executeCommand(RedisCommand.CLUSTER, RedisSubCommand.CLUSTER_SLOTS, (cmd)->cmd.clusterSlots(),
				new ListConverter<>(new ClusterSlotConverter()));
	}

	@Override
	public Status readOnly() {
		return executeCommand(RedisCommand.READONLY, (cmd)->cmd.readOnly(), new OkStatusConverter());
	}

	@Override
	public Status readWrite() {
		return executeCommand(RedisCommand.READWRITE, (cmd)->cmd.readWrite(), new OkStatusConverter());
	}

	public Range<Integer>[] createRangs(final IntegerRange... slots) {
		final RangeRangeConverter<Integer> rangeRangeConverter = new RangeRangeConverter<>();
		final Range<Integer>[] ranges = new Range[slots.length];

		for(int i = 0; i < slots.length; i++){
			ranges[i] = rangeRangeConverter.convert(slots[i]);
		}

		return ranges;
	}

}
