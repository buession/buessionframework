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

import com.buession.core.converter.ListConverter;
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
import com.buession.redis.core.internal.convert.lettuce.response.ClusterReplicasConverter;
import com.buession.redis.core.internal.convert.response.BumpEpochConverter;
import com.buession.redis.core.internal.convert.response.ClusterInfoConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodeConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodesConverter;
import com.buession.redis.core.internal.convert.response.ClusterSlotConverter;

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
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_MY_ID, (cmd)->cmd.clusterMyId(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_MY_ID, (cmd)->cmd.clusterMyId(),
					(v)->v)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_MY_ID, (cmd)->cmd.clusterMyId(), (v)->v)
					.run();
		}
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_ADDSLOTS,
					(cmd)->cmd.clusterAddSlots(slots), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_ADDSLOTS,
					(cmd)->cmd.clusterAddSlots(slots), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_ADDSLOTS, (cmd)->cmd.clusterAddSlots(slots),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		final ListConverter<Object, ClusterSlot> listClusterSlotConverter = ClusterSlotConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_SLOTS, (cmd)->cmd.clusterSlots(),
					listClusterSlotConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_SLOTS, (cmd)->cmd.clusterSlots(),
					listClusterSlotConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_SLOTS, (cmd)->cmd.clusterSlots(),
					listClusterSlotConverter)
					.run();
		}
	}

	@Override
	public Integer clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS,
					(cmd)->cmd.clusterCountFailureReports(nodeId), Long::intValue)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS,
					(cmd)->cmd.clusterCountFailureReports(nodeId), Long::intValue)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS,
					(cmd)->cmd.clusterCountFailureReports(nodeId), Long::intValue)
					.run(args);
		}
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create(slot);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT,
					(cmd)->cmd.clusterCountKeysInSlot(slot), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT,
					(cmd)->cmd.clusterCountKeysInSlot(slot), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT,
					(cmd)->cmd.clusterCountKeysInSlot(slot), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_DELSLOTS,
					(cmd)->cmd.clusterDelSlots(slots), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_DELSLOTS,
					(cmd)->cmd.clusterDelSlots(slots), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_DELSLOTS, (cmd)->cmd.clusterDelSlots(slots),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterFlushSlots() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS,
					(cmd)->cmd.clusterFlushslots(), okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS,
					(cmd)->cmd.clusterFlushslots(), okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS, (cmd)->cmd.clusterFlushslots(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption) {
		final CommandArguments args = CommandArguments.create(clusterFailoverOption);
		final boolean force = ClusterFailoverOption.FORCE == clusterFailoverOption;

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_FAILOVER,
					(cmd)->cmd.clusterFailover(force), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_FAILOVER,
					(cmd)->cmd.clusterFailover(force), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_FAILOVER, (cmd)->cmd.clusterFailover(force),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_FORGET,
					(cmd)->cmd.clusterForget(nodeId), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_FORGET,
					(cmd)->cmd.clusterForget(nodeId), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_FORGET, (cmd)->cmd.clusterForget(nodeId),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count) {
		final CommandArguments args = CommandArguments.create(slot).put("count", count);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT,
					(cmd)->cmd.clusterGetKeysInSlot(slot, (int) count), binaryToStringListConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT,
					(cmd)->cmd.clusterGetKeysInSlot(slot, (int) count), binaryToStringListConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT,
					(cmd)->cmd.clusterGetKeysInSlot(slot, (int) count), binaryToStringListConverter)
					.run(args);
		}
	}

	@Override
	public Long clusterKeySlot(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT,
					(cmd)->cmd.clusterKeyslot(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT,
					(cmd)->cmd.clusterKeyslot(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT, (cmd)->cmd.clusterKeyslot(key),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public ClusterInfo clusterInfo() {
		final ClusterInfoConverter clusterInfoConverter = new ClusterInfoConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_INFO, (cmd)->cmd.clusterInfo(),
					clusterInfoConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_INFO, (cmd)->cmd.clusterInfo(),
					clusterInfoConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_INFO, (cmd)->cmd.clusterInfo(),
					clusterInfoConverter)
					.run();
		}
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create(ip).put("port", port);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_MEET, (cmd)->cmd.clusterMeet(ip, port),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_MEET,
					(cmd)->cmd.clusterMeet(ip, port), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_MEET, (cmd)->cmd.clusterMeet(ip, port),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<ClusterRedisNode> clusterNodes() {
		final ClusterNodesConverter clusterNodesConverter = new ClusterNodesConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_NODES, (cmd)->cmd.clusterNodes(),
					clusterNodesConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_NODES, (cmd)->cmd.clusterNodes(),
					clusterNodesConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_NODES, (cmd)->cmd.clusterNodes(),
					clusterNodesConverter)
					.run();
		}
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		final ListConverter<String, ClusterRedisNode> listClusterNodeConverter = ClusterNodeConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_SLAVES,
					(cmd)->cmd.clusterSlaves(nodeId), listClusterNodeConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_SLAVES,
					(cmd)->cmd.clusterSlaves(nodeId), listClusterNodeConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_SLAVES, (cmd)->cmd.clusterSlaves(nodeId),
					listClusterNodeConverter)
					.run(args);
		}
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		final ClusterReplicasConverter clusterReplicasConverter = new ClusterReplicasConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_REPLICAS,
					(cmd)->cmd.clusterReplicate(nodeId), clusterReplicasConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_REPLICAS,
					(cmd)->cmd.clusterReplicate(nodeId), clusterReplicasConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_REPLICAS, (cmd)->cmd.clusterReplicate(nodeId),
					clusterReplicasConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_REPLICATE,
					(cmd)->cmd.clusterReplicate(nodeId), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_REPLICATE,
					(cmd)->cmd.clusterReplicate(nodeId), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_REPLICATE, (cmd)->cmd.clusterReplicate(nodeId),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption) {
		final CommandArguments args = CommandArguments.create(clusterResetOption);
		final boolean hard = ClusterResetOption.HARD == clusterResetOption;

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_RESET, (cmd)->cmd.clusterReset(hard),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_RESET, (cmd)->cmd.clusterReset(hard),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_RESET, (cmd)->cmd.clusterReset(hard),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterSaveConfig() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_SAVECONFIG,
					(cmd)->cmd.clusterSaveconfig(), okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_SAVECONFIG,
					(cmd)->cmd.clusterSaveconfig(), okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_SAVECONFIG, (cmd)->cmd.clusterSaveconfig(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create(configEpoch);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH,
					(cmd)->cmd.clusterSetConfigEpoch(configEpoch), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH,
					(cmd)->cmd.clusterSetConfigEpoch(configEpoch), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH,
					(cmd)->cmd.clusterSetConfigEpoch(configEpoch), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		final BumpEpochConverter bumpEpochConverter = new BumpEpochConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_BUMPEPOCH,
					(cmd)->cmd.clusterBumpepoch(), bumpEpochConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_BUMPEPOCH,
					(cmd)->cmd.clusterBumpepoch(), bumpEpochConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CLUSTER_BUMPEPOCH, (cmd)->cmd.clusterBumpepoch(),
					bumpEpochConverter)
					.run();
		}
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId) {
		final CommandArguments args = CommandArguments.create(slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CLUSTER_SETSLOT, (cmd)->{
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
			}, okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CLUSTER_SETSLOT, (cmd)->{
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
			}, okStatusConverter)
					.run(args);
		}else{
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
			}, okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status asking() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.ASKING, (cmd)->cmd.asking(), okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.ASKING, (cmd)->cmd.asking(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.ASKING, (cmd)->cmd.asking(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status readWrite() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.READWRITE, (cmd)->cmd.readWrite(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.READWRITE, (cmd)->cmd.readWrite(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.READWRITE, (cmd)->cmd.readWrite(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status readOnly() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.READONLY, (cmd)->cmd.readOnly(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.READONLY, (cmd)->cmd.readOnly(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.READONLY, (cmd)->cmd.readOnly(), okStatusConverter)
					.run();
		}
	}

}
