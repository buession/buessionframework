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
		return notCommand(client, ProtocolCommand.CLUSTER_MY_ID);
	}

	@Override
	public Status clusterAddSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create("slots", slots);
		return notCommand(client, ProtocolCommand.CLUSTER_ADDSLOTS, args);
	}

	@Override
	public List<ClusterSlot> clusterSlots() {
		return notCommand(client, ProtocolCommand.CLUSTER_SLOTS);
	}

	@Override
	public Long clusterCountFailureReports(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return notCommand(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS, args);
	}

	@Override
	public Long clusterCountFailureReports(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return notCommand(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS, args);
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot) {
		final CommandArguments args = CommandArguments.create("slot", slot);
		return notCommand(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT, args);
	}

	@Override
	public Status clusterDelSlots(final int... slots) {
		final CommandArguments args = CommandArguments.create("slots", slots);
		return notCommand(client, ProtocolCommand.CLUSTER_DELSLOTS, args);
	}

	@Override
	public Status clusterFlushSlots() {
		return notCommand(client, ProtocolCommand.CLUSTER_FLUSHSLOTS);
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption) {
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);
		return notCommand(client, ProtocolCommand.CLUSTER_FAILOVER, args);
	}

	@Override
	public Status clusterForget(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return notCommand(client, ProtocolCommand.CLUSTER_FORGET, args);
	}

	@Override
	public Status clusterForget(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return notCommand(client, ProtocolCommand.CLUSTER_FORGET, args);
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final int count) {
		final CommandArguments args = CommandArguments.create("slot", slot).put("count", count);
		return notCommand(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT, args);
	}

	@Override
	public Long clusterKeySlot(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT, args);
	}

	@Override
	public Long clusterKeySlot(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT, args);
	}

	@Override
	public ClusterInfo clusterInfo() {
		return notCommand(client, ProtocolCommand.CLUSTER_INFO);
	}

	@Override
	public Status clusterMeet(final String ip, final int port) {
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);
		return notCommand(client, ProtocolCommand.CLUSTER_MEET, args);
	}

	@Override
	public List<ClusterRedisNode> clusterNodes() {
		return notCommand(client, ProtocolCommand.CLUSTER_NODES);
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return notCommand(client, ProtocolCommand.CLUSTER_SLAVES, args);
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return notCommand(client, ProtocolCommand.CLUSTER_SLAVES, args);
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return notCommand(client, ProtocolCommand.CLUSTER_REPLICAS, args);
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return notCommand(client, ProtocolCommand.CLUSTER_REPLICAS, args);
	}

	@Override
	public Status clusterReplicate(final String nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return notCommand(client, ProtocolCommand.CLUSTER_REPLICATE, args);
	}

	@Override
	public Status clusterReplicate(final byte[] nodeId) {
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return notCommand(client, ProtocolCommand.CLUSTER_REPLICATE, args);
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption) {
		final CommandArguments args = CommandArguments.create("clusterResetOption", clusterResetOption);
		return notCommand(client, ProtocolCommand.CLUSTER_RESET, args);
	}

	@Override
	public Status clusterSaveConfig() {
		return notCommand(client, ProtocolCommand.CLUSTER_SAVECONFIG);
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch) {
		final CommandArguments args = CommandArguments.create("configEpoch", configEpoch);
		return notCommand(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH, args);
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch() {
		return notCommand(client, ProtocolCommand.CLUSTER_BUMPEPOCH);
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId) {
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);
		return notCommand(client, ProtocolCommand.CLUSTER_SETSLOT, args);
	}

	@Override
	public Status asking() {
		return notCommand(client, ProtocolCommand.ASKING);
	}

	@Override
	public Status readWrite() {
		return notCommand(client, ProtocolCommand.READWRITE);
	}

	@Override
	public Status readOnly() {
		return notCommand(client, ProtocolCommand.READONLY);
	}

}
