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

import com.buession.core.converter.ListConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisStandaloneClient;
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
import com.buession.redis.core.internal.convert.jedis.params.ClusterFailoverOptionConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterLinkConverter;
import com.buession.redis.core.internal.convert.response.BumpEpochConverter;
import com.buession.redis.core.internal.convert.response.ClusterInfoConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodesConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterReplicasConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterResetOptionConverter;
import com.buession.redis.core.internal.convert.response.ClusterNodeConverter;
import com.buession.redis.core.internal.convert.response.ClusterSlotConverter;
import redis.clients.jedis.args.ClusterResetType;

import java.util.List;
import java.util.Map;

/**
 * Jedis 单机模式集群命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterOperations extends AbstractClusterOperations<JedisStandaloneClient> {

	public JedisClusterOperations(final JedisStandaloneClient client) {
		super(client);
	}

	@Override
	public Status asking() {
		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.ASKING)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.ASKING)
					.run();
		}else{
			return new JedisCommand<>(client, Command.ASKING, (cmd)->cmd.asking(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_ADDSLOTS)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_ADDSLOTS)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_ADDSLOTS,
					(cmd)->cmd.clusterAddSlots(slots), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterAddSlotsRange(final SlotRange... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		final int[] slotRanges = numberRangeArray(slots);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_ADDSLOTSRANGE)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER,
					SubCommand.CLUSTER_ADDSLOTSRANGE)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_ADDSLOTSRANGE,
					(cmd)->cmd.clusterAddSlotsRange(slotRanges), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		final BumpEpochConverter bumpEpochConverter = new BumpEpochConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<KeyValue<BumpEpoch, Integer>, KeyValue<BumpEpoch, Integer>>(client,
					Command.CLUSTER, SubCommand.CLUSTER_BUMPEPOCH)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<KeyValue<BumpEpoch, Integer>, KeyValue<BumpEpoch, Integer>>(client,
					Command.CLUSTER, SubCommand.CLUSTER_BUMPEPOCH)
					.run();
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_BUMPEPOCH,
					(cmd)->cmd.clusterBumpEpoch(), bumpEpochConverter)
					.run();
		}
	}

	@Override
	public Long clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);

		if(isPipeline()){
			return new JedisPipelineCommand<Long, Long>(client, Command.CLUSTER, SubCommand.CLUSTER_COUNTFAILUREREPORTS)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Long, Long>(client, Command.CLUSTER,
					SubCommand.CLUSTER_COUNTFAILUREREPORTS)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_COUNTFAILUREREPORTS,
					(cmd)->cmd.clusterCountFailureReports(nodeId), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create(slot);

		if(isPipeline()){
			return new JedisPipelineCommand<Long, Long>(client, Command.CLUSTER, SubCommand.CLUSTER_COUNTKEYSINSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Long, Long>(client, Command.CLUSTER, SubCommand.CLUSTER_COUNTKEYSINSLOT)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_COUNTKEYSINSLOT,
					(cmd)->cmd.clusterCountKeysInSlot(slot), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create(slots);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_DELSLOTS)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_DELSLOTS)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_DELSLOTS,
					(cmd)->cmd.clusterDelSlots(slots), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterDelSlotsRange(final SlotRange... slots) {
		final CommandArguments args = CommandArguments.create(slots);
		final int[] slotRanges = numberRangeArray(slots);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_DELSLOTSRANGE)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER,
					SubCommand.CLUSTER_DELSLOTSRANGE)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_DELSLOTSRANGE,
					(cmd)->cmd.clusterDelSlots(slotRanges), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption) {
		final CommandArguments args = CommandArguments.create(clusterFailoverOption);
		final redis.clients.jedis.args.ClusterFailoverOption failoverOption =
				(new ClusterFailoverOptionConverter()).convert(clusterFailoverOption);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_FAILOVER)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_FAILOVER)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_FAILOVER,
					(cmd)->cmd.clusterFailover(failoverOption), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterFlushSlots() {
		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_FLUSHSLOTS)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_FLUSHSLOTS)
					.run();
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_FLUSHSLOTS,
					(cmd)->cmd.clusterFlushSlots(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_FORGET)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_FORGET)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_FORGET,
					(cmd)->cmd.clusterForget(nodeId), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final int count) {
		final CommandArguments args = CommandArguments.create(slot).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<List<String>, List<String>>(client, Command.CLUSTER,
					SubCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<String>, List<String>>(client, Command.CLUSTER,
					SubCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_GETKEYSINSLOT,
					(cmd)->cmd.clusterGetKeysInSlot(slot, count), (v)->v)
					.run(args);
		}
	}

	@Override
	public ClusterInfo clusterInfo() {
		final ClusterInfoConverter clusterInfoConverter = new ClusterInfoConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<ClusterInfo, ClusterInfo>(client, Command.CLUSTER, SubCommand.INFO)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<ClusterInfo, ClusterInfo>(client, Command.CLUSTER, SubCommand.INFO)
					.run();
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.INFO, (cmd)->cmd.clusterInfo(),
					clusterInfoConverter)
					.run();
		}
	}

	@Override
	public Long clusterKeySlot(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<Long, Long>(client, Command.CLUSTER, SubCommand.CLUSTER_KEYSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Long, Long>(client, Command.CLUSTER, SubCommand.CLUSTER_KEYSLOT)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_KEYSLOT,
					(cmd)->cmd.clusterKeySlot(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<ClusterLink> clusterLinks() {
		final ListConverter<Map<String, Object>, ClusterLink> listConverter = ClusterLinkConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<List<ClusterLink>, List<ClusterLink>>(client, Command.CLUSTER,
					SubCommand.CLUSTER_LINKS)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<ClusterLink>, List<ClusterLink>>(client, Command.CLUSTER,
					SubCommand.CLUSTER_LINKS)
					.run();
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_LINKS, (cmd)->cmd.clusterLinks(),
					listConverter)
					.run();
		}
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create(ip).add(port);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_MEET)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_MEET)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_MEET,
					(cmd)->cmd.clusterMeet(ip, port), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String clusterMyId() {
		if(isPipeline()){
			return new JedisPipelineCommand<String, String>(client, Command.CLUSTER, SubCommand.CLUSTER_MYID)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<String, String>(client, Command.CLUSTER, SubCommand.CLUSTER_MYID)
					.run();
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_MYID, (cmd)->cmd.clusterMyId(),
					(v)->v)
					.run();
		}
	}

	@Override
	public String clusterMyShardId() {
		if(isPipeline()){
			return new JedisPipelineCommand<String, String>(client, Command.CLUSTER, SubCommand.CLUSTER_MYSHARDID)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<String, String>(client, Command.CLUSTER, SubCommand.CLUSTER_MYSHARDID)
					.run();
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_MYSHARDID,
					(cmd)->cmd.clusterMyShardId(), (v)->v)
					.run();
		}
	}

	@Override
	public List<ClusterRedisNode> clusterNodes() {
		final ClusterNodesConverter clusterNodesConverter = new ClusterNodesConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					Command.CLUSTER, SubCommand.NODES)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					Command.CLUSTER, SubCommand.NODES)
					.run();
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.NODES, (cmd)->cmd.clusterNodes(),
					clusterNodesConverter)
					.run();
		}
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		final ListConverter<String, ClusterRedisNode> listClusterReplicasConverter = ClusterReplicasConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					Command.CLUSTER, SubCommand.CLUSTER_REPLICAS)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					Command.CLUSTER, SubCommand.CLUSTER_REPLICAS)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_REPLICAS,
					(cmd)->cmd.clusterReplicas(nodeId), listClusterReplicasConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_REPLICATE)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_REPLICATE)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_REPLICATE,
					(cmd)->cmd.clusterReplicate(nodeId), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterReset() {
		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.RESET)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER, SubCommand.RESET)
					.run();
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.RESET, (cmd)->cmd.clusterReset(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption) {
		final CommandArguments args = CommandArguments.create(clusterResetOption);
		final ClusterResetType clusterResetType = (new ClusterResetOptionConverter()).convert(clusterResetOption);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.RESET)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER, SubCommand.RESET)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.RESET,
					(cmd)->cmd.clusterReset(clusterResetType), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterSaveConfig() {
		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_SAVECONFIG)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_SAVECONFIG)
					.run();
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_SAVECONFIG,
					(cmd)->cmd.clusterSaveConfig(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create(configEpoch);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_SETCONFIGEPOCH)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER,
					SubCommand.CLUSTER_SETCONFIGEPOCH)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_SETCONFIGEPOCH,
					(cmd)->cmd.clusterSetConfigEpoch(configEpoch), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId) {
		final CommandArguments args = CommandArguments.create(slot).add(setSlotOption).add(nodeId);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_SETSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.CLUSTER, SubCommand.CLUSTER_SETSLOT)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_SETSLOT, (cmd)->{
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
	public List<ClusterRedisShard> clusterShards() {
		if(isPipeline()){
			return new JedisPipelineCommand<List<ClusterRedisShard>, List<ClusterRedisShard>>(client, Command.CLUSTER,
					SubCommand.CLUSTER_SHARDS)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<ClusterRedisShard>, List<ClusterRedisShard>>(client,
					Command.CLUSTER,
					SubCommand.CLUSTER_SHARDS)
					.run();
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_SHARDS, (cmd)->cmd.clusterShards(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create(nodeId);
		final ListConverter<String, ClusterRedisNode> listClusterNodeConverter = ClusterNodeConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client, Command.CLUSTER,
					SubCommand.SLAVES)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client, Command.CLUSTER,
					SubCommand.SLAVES)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.SLAVES, (cmd)->cmd.clusterSlaves(nodeId),
					listClusterNodeConverter)
					.run(args);
		}
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		final ListConverter<Object, ClusterSlot> listClusterSlotConverter = ClusterSlotConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<List<ClusterSlot>, List<ClusterSlot>>(client, Command.CLUSTER,
					SubCommand.CLUSTER_SLOTS)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<ClusterSlot>, List<ClusterSlot>>(client,
					Command.CLUSTER, SubCommand.CLUSTER_SLOTS)
					.run();
		}else{
			return new JedisCommand<>(client, Command.CLUSTER, SubCommand.CLUSTER_SLOTS, (cmd)->cmd.clusterSlots(),
					listClusterSlotConverter)
					.run();
		}
	}

	@Override
	public Status readOnly() {
		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.READONLY)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.READONLY)
					.run();
		}else{
			return new JedisCommand<>(client, Command.READONLY, (cmd)->cmd.readonly(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status readWrite() {
		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, Command.READWRITE)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, Command.READWRITE)
					.run();
		}else{
			return new JedisCommand<>(client, Command.READWRITE, (cmd)->cmd.readwrite(), okStatusConverter)
					.run();
		}
	}

}
