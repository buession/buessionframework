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
package com.buession.redis.client.lettuce.operations;

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterRedisNode;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.ClusterReplicasConverter;
import com.buession.redis.core.internal.convert.response.BumpEpochConverter;
import com.buession.redis.core.internal.convert.response.ClusterInfoConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodeConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodesConverter;
import com.buession.redis.core.internal.convert.response.ClusterSlotConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;

import java.util.List;

/**
 * Lettuce 单机模式集群命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceClusterOperations extends AbstractClusterOperations<LettuceStandaloneClient> {

	public LettuceClusterOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public String clusterMyId() {
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_MY_ID, (cmd)->cmd.clusterMyId(), (v)->v)
				.run();
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create("slots", slots);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_ADDSLOTS, (cmd)->cmd.clusterAddSlots(slots),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_SLOTS, (cmd)->cmd.clusterSlots(),
				ClusterSlotConverter.LIST_CONVERTER)
				.run();
	}

	@Override
	public Integer clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS,
				(cmd)->cmd.clusterCountFailureReports(nodeId).intValue(), (v)->v)
				.run(args);
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create("slot", slot);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT,
				(cmd)->cmd.clusterCountKeysInSlot(slot), (v)->v)
				.run(args);
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create("slots", slots);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_DELSLOTS, (cmd)->cmd.clusterDelSlots(slots),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clusterFlushSlots() {
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS, (cmd)->cmd.clusterFlushslots(),
				OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption) {
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_FAILOVER,
				(cmd)->cmd.clusterFailover(ClusterFailoverOption.FORCE == clusterFailoverOption),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_FORGET, (cmd)->cmd.clusterForget(nodeId),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count) {
		final CommandArguments args = CommandArguments.create("slot", slot).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT,
				(cmd)->cmd.clusterGetKeysInSlot(slot, (int) count), Converters.BINARY_LIST_TO_STRING_LIST_CONVERTER)
				.run(args);
	}

	@Override
	public Long clusterKeySlot(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT, (cmd)->cmd.clusterKeyslot(key),
				(v)->v)
				.run(args);
	}

	@Override
	public ClusterInfo clusterInfo() {
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_INFO, (cmd)->cmd.clusterInfo(),
				ClusterInfoConverter.INSTANCE)
				.run();
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_MEET, (cmd)->cmd.clusterMeet(ip, port),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public List<ClusterRedisNode> clusterNodes() {
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_NODES, (cmd)->cmd.clusterNodes(),
				ClusterNodesConverter.INSTANCE)
				.run();
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_SLAVES, (cmd)->cmd.clusterSlaves(nodeId),
				ClusterNodeConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_REPLICAS, (cmd)->cmd.clusterReplicate(nodeId),
				ClusterReplicasConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_REPLICATE, (cmd)->cmd.clusterReplicate(nodeId),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption) {
		final CommandArguments args = CommandArguments.create("clusterResetOption", clusterResetOption);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_RESET,
				(cmd)->cmd.clusterReset(ClusterResetOption.HARD == clusterResetOption), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clusterSaveConfig() {
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_SAVECONFIG, (cmd)->cmd.clusterSaveconfig(),
				OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create("configEpoch", configEpoch);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH,
				(cmd)->cmd.clusterSetConfigEpoch(configEpoch), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_BUMPEPOCH, (cmd)->cmd.clusterBumpepoch(),
				BumpEpochConverter.INSTANCE)
				.run();
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId) {
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);
		return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_SETSLOT, (cmd)->{
			switch(setSlotOption){
				case IMPORTING:
					return cmd.clusterSetSlotImporting(slot, nodeId);
				case MIGRATING:
					return cmd.clusterSetSlotMigrating(slot, nodeId);
				case STABLE:
					return cmd.clusterSetSlotStable(slot);
				case NODE:
					return cmd.clusterSetSlotNode(slot, nodeId);
				default:
					return null;
			}
		}, OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status asking() {
		return new LettuceCommand<>(client, ProtocolCommand.ASKING, (cmd)->cmd.asking(),
				OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status readWrite() {
		return new LettuceCommand<>(client, ProtocolCommand.READWRITE, (cmd)->cmd.readWrite(),
				OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status readOnly() {
		return new LettuceCommand<>(client, ProtocolCommand.READONLY, (cmd)->cmd.readOnly(),
				OkStatusConverter.INSTANCE)
				.run();
	}

}
