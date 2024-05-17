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
import com.buession.redis.client.jedis.JedisStandaloneClient;
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
import com.buession.redis.core.internal.convert.response.OkStatusConverter;

import java.util.List;

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
	public String clusterMyId() {
		return new JedisCommand<String>(client, ProtocolCommand.CLUSTER_MY_ID)
				.general((cmd)->cmd.clusterMyId())
				.run();
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create("slots", slots);
		return new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_ADDSLOTS)
				.general((cmd)->cmd.clusterAddSlots(slots), new OkStatusConverter())
				.run(args);
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		return new JedisCommand<List<ClusterSlot>>(client, ProtocolCommand.CLUSTER_SLOTS)
				.general((cmd)->cmd.clusterSlots(), new ClusterSlotConverter.ListClusterSlotConverter())
				.run();
	}

	@Override
	public Integer clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisCommand<Integer>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS)
				.run(args);
	}

	@Override
	public Integer clusterCountFailureReports(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisCommand<Integer>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS)
				.run(args);
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create("slot", slot);
		return new JedisCommand<Long>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT)
				.general((cmd)->cmd.clusterCountKeysInSlot(slot))
				.run(args);
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create("slots", slots);
		return new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_DELSLOTS)
				.general((cmd)->cmd.clusterDelSlots(slots), new OkStatusConverter())
				.run(args);
	}

	@Override
	public Status clusterFlushSlots() {
		return new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS)
				.general((cmd)->cmd.clusterFlushSlots(), new OkStatusConverter())
				.run();
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption) {
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);
		return new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_FAILOVER)
				.general((cmd)->cmd.clusterFailover(
								(new ClusterFailoverOptionConverter()).convert(clusterFailoverOption)),
						new OkStatusConverter())
				.run(args);
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_FORGET)
				.general((cmd)->cmd.clusterForget(nodeId), new OkStatusConverter())
				.run(args);
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count) {
		final CommandArguments args = CommandArguments.create("slot", slot).put("count", count);
		return new JedisCommand<List<String>>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT)
				.general((cmd)->cmd.clusterGetKeysInSlot(slot, (int) count))
				.run(args);
	}

	@Override
	public Long clusterKeySlot(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Long>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT)
				.general((cmd)->cmd.clusterKeySlot(key))
				.run(args);
	}

	@Override
	public ClusterInfo clusterInfo() {
		return new JedisCommand<ClusterInfo>(client, ProtocolCommand.CLUSTER_INFO)
				.general((cmd)->cmd.clusterInfo(), new ClusterInfoConverter())
				.run();
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);
		return new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_MEET)
				.general((cmd)->cmd.clusterMeet(ip, port), new OkStatusConverter())
				.run(args);
	}

	@Override
	public List<ClusterRedisNode> clusterNodes() {
		return new JedisCommand<List<ClusterRedisNode>>(client, ProtocolCommand.CLUSTER_NODES)
				.general((cmd)->cmd.clusterNodes(), new ClusterNodesConverter())
				.run();
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisCommand<List<ClusterRedisNode>>(client, ProtocolCommand.CLUSTER_SLAVES)
				.general((cmd)->cmd.clusterSlaves(nodeId), new ClusterNodeConverter.ListClusterNodeConverter())
				.run(args);
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisCommand<List<ClusterRedisNode>>(client, ProtocolCommand.CLUSTER_REPLICAS)
				.general((cmd)->cmd.clusterReplicas(nodeId),
						new ClusterReplicasConverter.ListClusterReplicasConverter())
				.run(args);
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_REPLICATE)
				.general((cmd)->cmd.clusterReplicate(nodeId), new OkStatusConverter())
				.run(args);
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption) {
		final CommandArguments args = CommandArguments.create("clusterResetOption", clusterResetOption);
		return new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_RESET)
				.general((cmd)->cmd.clusterReset((new ClusterResetOptionConverter()).convert(clusterResetOption)),
						new OkStatusConverter())
				.run(args);
	}

	@Override
	public Status clusterSaveConfig() {
		return new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_SAVECONFIG)
				.general((cmd)->cmd.clusterSaveConfig(), new OkStatusConverter())
				.run();
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create("configEpoch", configEpoch);
		return new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH)
				.general((cmd)->cmd.clusterSetConfigEpoch(configEpoch), new OkStatusConverter())
				.run(args);
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		return new JedisCommand<KeyValue<BumpEpoch, Integer>>(client, ProtocolCommand.CLUSTER_BUMPEPOCH)
				.general((cmd)->cmd.clusterBumpEpoch(), new BumpEpochConverter())
				.run();
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId) {
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);
		return new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_SETSLOT)
				.general((cmd)->{
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
				}, new OkStatusConverter())
				.run(args);
	}

	@Override
	public Status asking() {
		return new JedisCommand<Status>(client, ProtocolCommand.ASKING)
				.general((cmd)->cmd.asking(), new OkStatusConverter())
				.run();
	}

	@Override
	public Status readWrite() {
		return new JedisCommand<Status>(client, ProtocolCommand.READWRITE)
				.general((cmd)->cmd.readwrite(), new OkStatusConverter())
				.run();
	}

	@Override
	public Status readOnly() {
		return new JedisCommand<Status>(client, ProtocolCommand.READONLY)
				.general((cmd)->cmd.readonly(), new OkStatusConverter())
				.run();
	}

}
