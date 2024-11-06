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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterLink;
import com.buession.redis.core.ClusterRedisNode;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterRedisShard;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.SlotRange;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.SubCommand;

import java.util.List;

/**
 * Jedis 集群模式集群命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterClusterOperations extends AbstractClusterOperations<JedisClusterClient> {

	public JedisClusterClusterOperations(final JedisClusterClient client) {
		super(client);
	}

	@Override
	public Status asking() {
		return notCommand(client, Command.ASKING);
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_ADDSLOTS, args);
	}

	@Override
	public Status clusterAddSlotsRange(final SlotRange... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_ADDSLOTSRANGE, args);
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_BUMPEPOCH);
	}

	@Override
	public Long clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_COUNTFAILUREREPORTS, args);
	}

	@Override
	public Long clusterCountFailureReports(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_COUNTFAILUREREPORTS, args);
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create(slot);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_COUNTKEYSINSLOT, args);
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_DELSLOTS, args);
	}

	@Override
	public Status clusterDelSlotsRange(final SlotRange... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_DELSLOTSRANGE, args);
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption) {
		final CommandArguments args = CommandArguments.create(clusterFailoverOption);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_FAILOVER, args);
	}

	@Override
	public Status clusterFlushSlots() {
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_FLUSHSLOTS);
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_FORGET, args);
	}

	@Override
	public Status clusterForget(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_FORGET, args);
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final int count) {
		final CommandArguments args = CommandArguments.create(slot).add(count);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_GETKEYSINSLOT, args);
	}

	@Override
	public ClusterInfo clusterInfo() {
		return notCommand(client, Command.CLUSTER, SubCommand.INFO);
	}

	@Override
	public Long clusterKeySlot(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_KEYSLOT, args);
	}

	@Override
	public Long clusterKeySlot(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_KEYSLOT, args);
	}

	@Override
	public List<ClusterLink> clusterLinks() {
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_LINKS);
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create(ip).add(port);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_MEET, args);
	}

	@Override
	public String clusterMyId() {
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_MYID);
	}

	@Override
	public String clusterMyShardId() {
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_MYSHARDID);
	}

	@Override
	public List<ClusterRedisNode> clusterNodes() {
		return notCommand(client, Command.CLUSTER, SubCommand.NODES);
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_REPLICAS, args);
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_REPLICAS, args);
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_REPLICATE, args);
	}

	@Override
	public Status clusterReplicate(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_REPLICATE, args);
	}

	@Override
	public Status clusterReset() {
		return notCommand(client, Command.CLUSTER, SubCommand.RESET);
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption) {
		final CommandArguments args = CommandArguments.create(clusterResetOption);
		return notCommand(client, Command.CLUSTER, SubCommand.RESET, args);
	}

	@Override
	public Status clusterSaveConfig() {
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_SAVECONFIG);
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create(configEpoch);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_SETCONFIGEPOCH, args);
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId) {
		final CommandArguments args = CommandArguments.create(slot).add(setSlotOption).add(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_SETSLOT, args);
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(slot).add(setSlotOption).add(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_SETSLOT, args);
	}

	@Override
	public List<ClusterRedisShard> clusterShards() {
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_SHARDS);
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.SLAVES, args);
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		return notCommand(client, Command.CLUSTER, SubCommand.SLAVES, args);
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		return notCommand(client, Command.CLUSTER, SubCommand.CLUSTER_SLOTS);
	}

	@Override
	public Status readOnly() {
		return notCommand(client, Command.READONLY);
	}

	@Override
	public Status readWrite() {
		return notCommand(client, Command.READWRITE);
	}

}
