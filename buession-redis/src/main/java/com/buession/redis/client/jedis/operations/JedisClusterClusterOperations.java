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

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterRedisNode;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

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
	public String clusterMyId() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<String, String>(client, ProtocolCommand.CLUSTER_MY_ID)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<String, String>(client, ProtocolCommand.CLUSTER_MY_ID)
					.run();
		}else{
			return new JedisClusterCommand<String, String>(client, ProtocolCommand.CLUSTER_MY_ID)
					.run();
		}
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create("slots", slots);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_ADDSLOTS)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_ADDSLOTS)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLUSTER_ADDSLOTS)
					.run(args);
		}
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<ClusterSlot>, List<ClusterSlot>>(client,
					ProtocolCommand.CLUSTER_SLOTS)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<ClusterSlot>, List<ClusterSlot>>(client,
					ProtocolCommand.CLUSTER_SLOTS)
					.run();
		}else{
			return new JedisClusterCommand<List<ClusterSlot>, List<ClusterSlot>>(client,
					ProtocolCommand.CLUSTER_SLOTS)
					.run();
		}
	}

	@Override
	public Integer clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Integer, Integer>(client,
					ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Integer, Integer>(client,
					ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS)
					.run(args);
		}else{
			return new JedisClusterCommand<Integer, Integer>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS)
					.run(args);
		}
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create("slot", slot);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Long, Long>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Long, Long>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT)
					.run(args);
		}else{
			return new JedisClusterCommand<Long, Long>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT)
					.run(args);
		}
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create("slots", slots);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_DELSLOTS)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_DELSLOTS)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLUSTER_DELSLOTS)
					.run(args);
		}
	}

	@Override
	public Status clusterFlushSlots() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS)
					.run();
		}
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption) {
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FAILOVER)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FAILOVER)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FAILOVER)
					.run(args);
		}
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FORGET)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FORGET)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLUSTER_FORGET)
					.run(args);
		}
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count) {
		final CommandArguments args = CommandArguments.create("slot", slot).put("count", count);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<String>, List<String>>(client,
					ProtocolCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<String>, List<String>>(client,
					ProtocolCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}else{
			return new JedisClusterCommand<List<String>, List<String>>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}
	}

	@Override
	public Long clusterKeySlot(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Long, Long>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Long, Long>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}else{
			return new JedisClusterCommand<Long, Long>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT)
					.run(args);
		}
	}

	@Override
	public ClusterInfo clusterInfo() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<ClusterInfo, ClusterInfo>(client, ProtocolCommand.CLUSTER_INFO)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<ClusterInfo, ClusterInfo>(client, ProtocolCommand.CLUSTER_INFO)
					.run();
		}else{
			return new JedisClusterCommand<ClusterInfo, ClusterInfo>(client, ProtocolCommand.CLUSTER_INFO)
					.run();
		}
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_MEET)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_MEET)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLUSTER_MEET)
					.run(args);
		}
	}

	@Override
	public List<ClusterRedisNode> clusterNodes() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_NODES)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_NODES)
					.run();
		}else{
			return new JedisClusterCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_NODES)
					.run();
		}
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_SLAVES)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_SLAVES)
					.run(args);
		}else{
			return new JedisClusterCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_SLAVES)
					.run(args);
		}
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_REPLICAS)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_REPLICAS)
					.run(args);
		}else{
			return new JedisClusterCommand<List<ClusterRedisNode>, List<ClusterRedisNode>>(client,
					ProtocolCommand.CLUSTER_REPLICAS)
					.run(args);
		}
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_REPLICATE)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_REPLICATE)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLUSTER_REPLICATE)
					.run(args);
		}
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption) {
		final CommandArguments args = CommandArguments.create("clusterResetOption", clusterResetOption);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_RESET)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_RESET)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLUSTER_RESET)
					.run(args);
		}
	}

	@Override
	public Status clusterSaveConfig() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SAVECONFIG)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SAVECONFIG)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SAVECONFIG)
					.run();
		}
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create("configEpoch", configEpoch);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH)
					.run(args);
		}
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<KeyValue<BumpEpoch, Integer>, KeyValue<BumpEpoch, Integer>>(client,
					ProtocolCommand.CLUSTER_BUMPEPOCH)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<KeyValue<BumpEpoch, Integer>, KeyValue<BumpEpoch, Integer>>(
					client, ProtocolCommand.CLUSTER_BUMPEPOCH)
					.run();
		}else{
			return new JedisClusterCommand<KeyValue<BumpEpoch, Integer>, KeyValue<BumpEpoch, Integer>>(
					client, ProtocolCommand.CLUSTER_BUMPEPOCH)
					.run();
		}
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId) {
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SETSLOT)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SETSLOT)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLUSTER_SETSLOT)
					.run(args);
		}
	}

	@Override
	public Status asking() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.ASKING)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.ASKING)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.ASKING)
					.run();
		}
	}

	@Override
	public Status readWrite() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.READWRITE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.READWRITE)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.READWRITE)
					.run();
		}
	}

	@Override
	public Status readOnly() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.READONLY)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.READONLY)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.READONLY)
					.run();
		}
	}

}
