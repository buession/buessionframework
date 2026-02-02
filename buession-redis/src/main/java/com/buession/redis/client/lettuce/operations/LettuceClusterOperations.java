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
package com.buession.redis.client.lettuce.operations;

import com.buession.core.IntegerRange;
import com.buession.core.converter.ListConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.ClusterOperations;
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
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.internal.convert.lettuce.response.ClusterShardInfoConverter;
import com.buession.redis.core.internal.convert.response.BumpEpochConverter;
import com.buession.redis.core.internal.convert.response.ClusterInfoConverter;
import com.buession.redis.core.internal.convert.response.ClusterLinkConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodeConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodesConverter;
import com.buession.redis.core.internal.convert.response.ClusterSlotConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Range;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 集群命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceClusterOperations extends AbstractLettuceRedisOperations implements
		ClusterOperations {

	public LettuceClusterOperations(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Status asking() {
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.ASKING).executor((cmd)->cmd.asking())
				.converter(okStatusConverter).run();
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_ADDSLOTS)
				.executor((cmd)->cmd.clusterAddSlots(slots))
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status clusterAddSlotsRange(final IntegerRange slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_ADDSLOTSRANGE)
				.executor((cmd)->cmd.clusterAddSlotsRange(Range.create(slots.getStart(), slots.getEnd())))
				.arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		return LettuceCommandBuilder.<String, KeyValue<BumpEpoch, Integer>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_BUMPEPOCH).executor((cmd)->cmd.clusterBumpepoch())
				.converter(new BumpEpochConverter()).run();
	}

	@Override
	public Integer clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return LettuceCommandBuilder.<Long, Integer>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_COUNTFAILUREREPORTS).executor((cmd)->cmd.clusterCountFailureReports(nodeId))
				.arguments(args).converter(Long::intValue).run();
	}

	@Override
	public Integer clusterCountFailureReports(final byte[] nodeId) {
		return clusterCountFailureReports(SafeEncoder.encode(nodeId));
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create(slot);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_COUNTKEYSINSLOT)
				.executor((cmd)->cmd.clusterCountKeysInSlot(slot)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_DELSLOTS)
				.executor((cmd)->cmd.clusterDelSlots(slots)).arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clusterDelSlotsRange(final IntegerRange slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_DELSLOTSRANGE)
				.executor((cmd)->cmd.clusterDelSlotsRange(Range.create(slots.getStart(), slots.getEnd())))
				.arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption option) {
		final CommandArguments args = CommandArguments.create(option);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_FAILOVER)
				.executor((cmd)->cmd.clusterFailover(ClusterFailoverOption.FORCE == option))
				.arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clusterFlushSlots() {
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_FLUSHSLOTS)
				.executor((cmd)->cmd.clusterFlushslots()).converter(okStatusConverter).run();
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_FORGET)
				.executor((cmd)->cmd.clusterForget(nodeId)).arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clusterForget(final byte[] nodeId) {
		return clusterForget(SafeEncoder.encode(nodeId));
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count) {
		final CommandArguments args = CommandArguments.create(slot).add(count);
		return LettuceCommandBuilder.<List<byte[]>, List<String>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_GETKEYSINSLOT).executor((cmd)->cmd.clusterGetKeysInSlot(slot, (int) count))
				.arguments(args).converter(binaryToStringListConverter).run();
	}

	@Override
	public ClusterInfo clusterInfo() {
		final ClusterInfoConverter clusterInfoConverter = new ClusterInfoConverter();
		return LettuceCommandBuilder.<String, ClusterInfo>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_INFO)
				.executor((cmd)->cmd.clusterInfo()).converter(clusterInfoConverter).run();
	}

	@Override
	public Long clusterKeySlot(final String key) {
		return clusterKeySlot(SafeEncoder.encode(key));
	}

	@Override
	public Long clusterKeySlot(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_KEYSLOT)
				.executor((cmd)->cmd.clusterKeyslot(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<ClusterLink> clusterLinks() {
		return LettuceCommandBuilder.<List<Map<String, Object>>, List<ClusterLink>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_LINKS).executor((cmd)->cmd.clusterLinks())
				.converter(new ListConverter<>(new ClusterLinkConverter())).run();
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create(ip).add(port);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_MEET)
				.executor((cmd)->cmd.clusterMeet(ip, port)).arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clusterMigration(final IntegerRange slots) {
		final CommandArguments args = CommandArguments.create("IMPORT").add(slots);
		return LettuceCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_MIGRATION)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Object clusterMigration(final ClusterMigrationOp option) {
		final CommandArguments args = CommandArguments.create(option).add("ALL");
		return LettuceCommandBuilder.newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_MIGRATION).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Object clusterMigration(final ClusterMigrationOp option, final String id) {
		final CommandArguments args = CommandArguments.create(option).add("ID").add(id);
		return LettuceCommandBuilder.newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_MIGRATION).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public String clusterMyId() {
		return LettuceCommandBuilder.<String, String>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_MYID)
				.executor((cmd)->cmd.clusterMyId()).converter((v)->v).run();
	}

	@Override
	public String clusterMyShardId() {
		return LettuceCommandBuilder.<String, String>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_MYSHARDID)
				.executor((cmd)->cmd.clusterMyShardId()).converter((v)->v).run();
	}

	@Override
	public List<ClusterRedisNode> clusterNodes() {
		return LettuceCommandBuilder.<String, List<ClusterRedisNode>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_NODES).executor((cmd)->cmd.clusterNodes()).converter(new ClusterNodesConverter())
				.run();
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return LettuceCommandBuilder.<List<String>, List<ClusterRedisNode>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_REPLICAS).executor((cmd)->cmd.clusterReplicas(nodeId)).arguments(args)
				.converter(new ListConverter<>(new ClusterNodeConverter())).run();
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final byte[] nodeId) {
		return clusterReplicas(SafeEncoder.encode(nodeId));
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_REPLICATE)
				.executor((cmd)->cmd.clusterReplicate(nodeId)).arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clusterReplicate(final byte[] nodeId) {
		return clusterReplicate(SafeEncoder.encode(nodeId));
	}

	@Override
	public Status clusterReset() {
		return clusterReset(ClusterResetOption.SOFT);
	}

	@Override
	public Status clusterReset(final ClusterResetOption option) {
		final CommandArguments args = CommandArguments.create(option);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_RESET)
				.executor((cmd)->cmd.clusterReset(ClusterResetOption.HARD == option)).arguments(args)
				.converter(okStatusConverter).run();
	}

	@Override
	public Status clusterSaveConfig() {
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_SAVECONFIG)
				.executor((cmd)->cmd.clusterSaveconfig()).converter(okStatusConverter).run();
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create(configEpoch);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SETCONFIGEPOCH).executor((cmd)->cmd.clusterSetConfigEpoch(configEpoch))
				.arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption option, final String nodeId) {
		final CommandArguments args = CommandArguments.create(slot).add(option).add(nodeId);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_SETSLOT)
				.executor((cmd)->switch(option){
					case IMPORTING -> cmd.clusterSetSlotImporting(slot, nodeId);
					case MIGRATING -> cmd.clusterSetSlotMigrating(slot, nodeId);
					case STABLE -> cmd.clusterSetSlotStable(slot);
					case NODE -> cmd.clusterSetSlotNode(slot, nodeId);
				}).arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption option, final byte[] nodeId) {
		return clusterSetSlot(slot, option, SafeEncoder.encode(nodeId));
	}

	@Override
	public List<ClusterShardInfo> clusterShards() {
		return LettuceCommandBuilder.<List<Object>, List<ClusterShardInfo>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SHARDS).executor((cmd)->cmd.clusterShards())
				.converter(new ListConverter<>(new ClusterShardInfoConverter())).run();
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return LettuceCommandBuilder.<List<String>, List<ClusterRedisNode>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SLAVES).executor((cmd)->cmd.clusterSlaves(nodeId))
				.arguments(args).converter(new ListConverter<>(new ClusterNodeConverter())).run();
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final byte[] nodeId) {
		return clusterSlaves(SafeEncoder.encode(nodeId));
	}

	@Override
	public ClusterSlotStat clusterSlotStats() {
		return LettuceCommandBuilder.<ClusterSlotStat, ClusterSlotStat>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SLOT_STATS)
				.converter((v)->v).run();
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		return LettuceCommandBuilder.<List<Object>, List<ClusterSlot>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SLOTS)
				.executor((cmd)->cmd.clusterSlots())
				.converter(new ListConverter<>(new ClusterSlotConverter()))
				.run();
	}

	@Override
	public Status readOnly() {
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.READONLY)
				.executor((cmd)->cmd.readOnly())
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status readWrite() {
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.READWRITE)
				.executor((cmd)->cmd.readWrite())
				.converter(okStatusConverter)
				.run();
	}

}
