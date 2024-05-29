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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.ListConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterRedisNode;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.params.ClusterFailoverOptionConverter;
import com.buession.redis.core.internal.convert.response.BumpEpochConverter;
import com.buession.redis.core.internal.convert.response.ClusterInfoConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodesConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterReplicasConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterResetOptionConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodeConverter;
import com.buession.redis.core.internal.convert.response.ClusterSlotConverter;
import redis.clients.jedis.args.ClusterResetType;

import java.util.List;

/**
 * Jedis 哨兵模式集群命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelClusterOperations extends AbstractClusterOperations<JedisSentinelClient> {

	public JedisSentinelClusterOperations(final JedisSentinelClient client) {
		super(client);
	}

	@Override
	public String clusterMyId() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, ProtocolCommand.CLUSTER_MY_ID)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, ProtocolCommand.CLUSTER_MY_ID)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_MY_ID, (cmd)->cmd.clusterMyId(), (v)->v)
					.run();
		}
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create("slots", slots);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_ADDSLOTS)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_ADDSLOTS)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_ADDSLOTS,
					(cmd)->cmd.clusterAddSlots(slots), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		final ListConverter<Object, ClusterSlot> listClusterSlotConverter = ClusterSlotConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<ClusterSlot>, List<ClusterSlot>>(client,
					ProtocolCommand.CLUSTER_SLOTS)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<ClusterSlot>, List<ClusterSlot>>(client,
					ProtocolCommand.CLUSTER_SLOTS)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_SLOTS, (cmd)->cmd.clusterSlots(),
					listClusterSlotConverter)
					.run();
		}
	}

	@Override
	public Integer clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Integer, Integer>(client,
					ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Integer, Integer>(client,
					ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS,
					(cmd)->cmd.clusterCountFailureReports(nodeId), Long::intValue)
					.run(args);
		}
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create("slot", slot);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT,
					(cmd)->cmd.clusterCountKeysInSlot(slot), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create("slots", slots);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_DELSLOTS)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_DELSLOTS)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_DELSLOTS,
					(cmd)->cmd.clusterDelSlots(slots), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterFlushSlots() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS,
					(cmd)->cmd.clusterFlushSlots(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption) {
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);
		final redis.clients.jedis.args.ClusterFailoverOption failoverOption =
				(new ClusterFailoverOptionConverter()).convert(clusterFailoverOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FAILOVER)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FAILOVER)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_FAILOVER,
					(cmd)->cmd.clusterFailover(failoverOption), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FORGET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FORGET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_FORGET, (cmd)->cmd.clusterForget(nodeId),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count) {
		final CommandArguments args = CommandArguments.create("slot", slot).put("count", count);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<String>, List<String>>(client,
					ProtocolCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<String>, List<String>>(client,
					ProtocolCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT,
					(cmd)->cmd.clusterGetKeysInSlot(slot, (int) count), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long clusterKeySlot(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT,
					(cmd)->cmd.clusterKeySlot(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public ClusterInfo clusterInfo() {
		final ClusterInfoConverter clusterInfoConverter = new ClusterInfoConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<ClusterInfo, ClusterInfo>(client, ProtocolCommand.CLUSTER_INFO)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<ClusterInfo, ClusterInfo>(client, ProtocolCommand.CLUSTER_INFO)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_INFO, (cmd)->cmd.clusterInfo(),
					clusterInfoConverter)
					.run();
		}
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_MEET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_MEET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_MEET, (cmd)->cmd.clusterMeet(ip, port),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<ClusterRedisNode> clusterNodes() {
		final ClusterNodesConverter clusterNodesConverter = new ClusterNodesConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_NODES)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_NODES)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_NODES, (cmd)->cmd.clusterNodes(),
					clusterNodesConverter)
					.run();
		}
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final ListConverter<String, ClusterRedisNode> listClusterNodeConverter = ClusterNodeConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_SLAVES)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_SLAVES)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_SLAVES, (cmd)->cmd.clusterSlaves(nodeId),
					listClusterNodeConverter)
					.run(args);
		}
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final ListConverter<String, ClusterRedisNode> listClusterReplicasConverter = ClusterReplicasConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_REPLICAS)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_REPLICAS)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_REPLICAS,
					(cmd)->cmd.clusterReplicas(nodeId), listClusterReplicasConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_REPLICATE)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_REPLICATE)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_REPLICATE,
					(cmd)->cmd.clusterReplicate(nodeId), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption) {
		final CommandArguments args = CommandArguments.create("clusterResetOption", clusterResetOption);
		final ClusterResetType clusterResetType = (new ClusterResetOptionConverter()).convert(clusterResetOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_RESET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_RESET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_RESET,
					(cmd)->cmd.clusterReset(clusterResetType), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterSaveConfig() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SAVECONFIG)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SAVECONFIG)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_SAVECONFIG,
					(cmd)->cmd.clusterSaveConfig(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create("configEpoch", configEpoch);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH,
					(cmd)->cmd.clusterSetConfigEpoch(configEpoch), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		final BumpEpochConverter bumpEpochConverter = new BumpEpochConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<KeyValue<BumpEpoch, Integer>, KeyValue<BumpEpoch, Integer>>(client,
					ProtocolCommand.CLUSTER_BUMPEPOCH)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<KeyValue<BumpEpoch, Integer>, KeyValue<BumpEpoch, Integer>>(
					client,
					ProtocolCommand.CLUSTER_BUMPEPOCH)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_BUMPEPOCH, (cmd)->cmd.clusterBumpEpoch(),
					bumpEpochConverter)
					.run();
		}
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId) {
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SETSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SETSLOT)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CLUSTER_SETSLOT, (cmd)->{
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
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.ASKING)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.ASKING)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ASKING, (cmd)->cmd.asking(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status readWrite() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.READWRITE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.READWRITE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.READWRITE, (cmd)->cmd.readwrite(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status readOnly() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.READONLY)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.READONLY)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.READONLY, (cmd)->cmd.readonly(),
					okStatusConverter)
					.run();
		}
	}

}
