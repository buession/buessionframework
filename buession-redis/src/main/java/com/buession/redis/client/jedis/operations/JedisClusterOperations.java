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
package com.buession.redis.client.jedis.operations;

import com.buession.core.IntegerRange;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.ClusterOperations;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterLink;
import com.buession.redis.core.ClusterRedisNode;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterShardInfo;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.ClusterSlotStat;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.internal.convert.response.BumpEpochConverter;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Jedis 集群命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterOperations extends AbstractJedisRedisOperations implements
		ClusterOperations {

	public JedisClusterOperations(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status asking() {
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.ASKING)
				.converter(okStatusConverter).run();
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_ADDSLOTS)
				.converter(okStatusConverter).run();
	}

	@Override
	public Status clusterAddSlotsRange(final IntegerRange slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_ADDSLOTSRANGE)
				.arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		return JedisCommandBuilder.<String, KeyValue<BumpEpoch, Integer>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_BUMPEPOCH)
				.converter(new BumpEpochConverter()).run();
	}

	@Override
	public Integer clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return JedisCommandBuilder.<Integer, Integer>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_COUNTFAILUREREPORTS)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Integer clusterCountFailureReports(final byte[] nodeId) {
		return clusterCountFailureReports(SafeEncoder.encode(nodeId));
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create(slot);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_COUNTKEYSINSLOT)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_DELSLOTS)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Status clusterDelSlotsRange(final IntegerRange slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_DELSLOTSRANGE)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption) {
		final CommandArguments args = CommandArguments.create(clusterFailoverOption);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_FAILOVER)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Status clusterFlushSlots() {
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_FLUSHSLOTS)
				.converter((v)->v).run();
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_FORGET)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Status clusterForget(final byte[] nodeId) {
		return clusterForget(SafeEncoder.encode(nodeId));
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count) {
		final CommandArguments args = CommandArguments.create(slot).add(count);
		return JedisCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_GETKEYSINSLOT)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public ClusterInfo clusterInfo() {
		return JedisCommandBuilder.<ClusterInfo, ClusterInfo>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_INFO)
				.converter((v)->v).run();
	}

	@Override
	public Long clusterKeySlot(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_KEYSLOT)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long clusterKeySlot(final byte[] key) {
		return clusterKeySlot(SafeEncoder.encode(key));
	}

	@Override
	public List<ClusterLink> clusterLinks() {
		return JedisCommandBuilder.<List<ClusterLink>, List<ClusterLink>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_LINKS)
				.converter((v)->v).run();
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create(ip).add(port);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_MEET)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Status clusterMigration(final IntegerRange slots) {
		final CommandArguments args = CommandArguments.create("IMPORT").add(slots);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_MIGRATION)
				.arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public Object clusterMigration(final ClusterMigrationOp migrationOp) {
		final CommandArguments args = CommandArguments.create(migrationOp).add("ALL");
		return JedisCommandBuilder.newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_MIGRATION)
				.arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public Object clusterMigration(final ClusterMigrationOp migrationOp, final String id) {
		final CommandArguments args = CommandArguments.create(migrationOp).add("ID").add(id);
		return JedisCommandBuilder.newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_MIGRATION)
				.arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public String clusterMyId() {
		return JedisCommandBuilder.<String, String>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_MYID)
				.converter((v)->v)
				.run();
	}

	@Override
	public String clusterMyShardId() {
		return JedisCommandBuilder.<String, String>newBuilder(client, Command.CLUSTER, SubCommand.CLUSTER_MYSHARDID)
				.converter((v)->v)
				.run();
	}

	@Override
	public List<ClusterRedisNode> clusterNodes() {
		return JedisCommandBuilder.<List<ClusterRedisNode>, List<ClusterRedisNode>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_NODES)
				.converter((v)->v)
				.run();
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return JedisCommandBuilder.<List<ClusterRedisNode>, List<ClusterRedisNode>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_REPLICAS)
				.arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final byte[] nodeId) {
		return clusterReplicas(SafeEncoder.encode(nodeId));
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_REPLICATE)
				.arguments(args).converter((v)->v)
				.run();
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
	public Status clusterReset(final ClusterResetOption clusterResetOption) {
		final CommandArguments args = CommandArguments.create(clusterResetOption);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_RESET)
				.arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public Status clusterSaveConfig() {
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SAVECONFIG)
				.converter((v)->v)
				.run();
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create(configEpoch);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SETCONFIGEPOCH)
				.arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId) {
		final CommandArguments args = CommandArguments.create(slot).add(setSlotOption).add(nodeId);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SETSLOT)
				.arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final byte[] nodeId) {
		return clusterSetSlot(slot, setSlotOption, SafeEncoder.encode(nodeId));
	}

	@Override
	public List<ClusterShardInfo> clusterShards() {
		return JedisCommandBuilder.<List<ClusterShardInfo>, List<ClusterShardInfo>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SHARDS)
				.converter((v)->v).run();
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return JedisCommandBuilder.<List<ClusterRedisNode>, List<ClusterRedisNode>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SLAVES)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final byte[] nodeId) {
		return clusterSlaves(SafeEncoder.encode(nodeId));
	}

	@Override
	public ClusterSlotStat clusterSlotStats() {
		return JedisCommandBuilder.<ClusterSlotStat, ClusterSlotStat>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SLOT_STATS)
				.converter((v)->v).run();
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		return JedisCommandBuilder.<List<ClusterSlot>, List<ClusterSlot>>newBuilder(client, Command.CLUSTER,
						SubCommand.CLUSTER_SLOTS)
				.converter((v)->v).run();
	}

	@Override
	public Status readOnly() {
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.READONLY)
				.converter((v)->v).run();
	}

	@Override
	public Status readWrite() {
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.READWRITE)
				.converter((v)->v).run();
	}

}
