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

import com.buession.lang.Status;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.RedisClusterServer;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.JedisConverters;
import com.buession.redis.core.internal.convert.jedis.params.ClusterFailoverOptionConverter;
import com.buession.redis.core.internal.convert.jedis.response.BumpEpochConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterNodesConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterReplicasConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterResetOptionConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterSlaveConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterSlotConverter;
import com.buession.redis.core.internal.convert.jedis.response.OkStatusConverter;

import java.util.List;

/**
 * Jedis 哨兵模式集群命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelClusterOperations extends AbstractClusterOperations<JedisSentinelConnection> {

	public JedisSentinelClusterOperations(final JedisSentinelClient client){
		super(client);
	}

	@Override
	public String clusterMyId(){
		final JedisSentinelCommand<String> command = JedisSentinelCommand.<String>create(ProtocolCommand.CLUSTER_MY_ID)
				.general((cmd)->cmd.clusterMyId());
		return execute(command);
	}

	@Override
	public Status clusterAddSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(
						ProtocolCommand.CLUSTER_ADDSLOTS)
				.general((cmd)->cmd.clusterAddSlots(slots), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public List<ClusterSlot> clusterSlots(){
		final JedisSentinelCommand<List<ClusterSlot>> command = JedisSentinelCommand.<List<ClusterSlot>>create(
				ProtocolCommand.CLUSTER_SLOTS).general((cmd)->cmd.clusterSlots(), ClusterSlotConverter.LIST_CONVERTER);
		return execute(command);
	}

	@Override
	public int clusterCountFailureReports(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisSentinelCommand<Integer> command = JedisSentinelCommand.create(
				ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS);
		return execute(command, args);
	}

	@Override
	public int clusterCountFailureReports(final byte[] nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisSentinelCommand<Integer> command = JedisSentinelCommand.create(
				ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS);
		return execute(command, args);
	}

	@Override
	public long clusterCountKeysInSlot(final int slot){
		final CommandArguments args = CommandArguments.create("slot", slot);
		final JedisSentinelCommand<Long> command = JedisSentinelCommand.<Long>create(
				ProtocolCommand.CLUSTER_COUNTKEYSINSLOT).general((cmd)->cmd.clusterCountKeysInSlot(slot));
		return execute(command, args);
	}

	@Override
	public Status clusterDelSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(
						ProtocolCommand.CLUSTER_DELSLOTS)
				.general((cmd)->cmd.clusterDelSlots(slots), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clusterFlushSlots(){
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(
						ProtocolCommand.CLUSTER_FLUSHSLOTS)
				.general((cmd)->cmd.clusterFlushSlots(), OkStatusConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption){
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(
						ProtocolCommand.CLUSTER_FAILOVER)
				.general((cmd)->cmd.clusterFailover(
								ClusterFailoverOptionConverter.INSTANCE.convert(clusterFailoverOption)),
						OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clusterForget(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(
						ProtocolCommand.CLUSTER_FORGET)
				.general((cmd)->cmd.clusterForget(nodeId), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count){
		final CommandArguments args = CommandArguments.create("slot", slot).put("count", count);
		final JedisSentinelCommand<List<String>> command = JedisSentinelCommand.<List<String>>create(
				ProtocolCommand.CLUSTER_GETKEYSINSLOT).general((cmd)->cmd.clusterGetKeysInSlot(slot, (int) count));
		return execute(command, args);
	}

	@Override
	public long clusterKeySlot(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisSentinelCommand<Long> command = JedisSentinelCommand.<Long>create(
				ProtocolCommand.CLUSTER_GETKEYSINSLOT).general((cmd)->cmd.clusterKeySlot(key));
		return execute(command, args);
	}

	@Override
	public ClusterInfo clusterInfo(){
		final JedisSentinelCommand<ClusterInfo> command = JedisSentinelCommand.<ClusterInfo>create(
						ProtocolCommand.CLUSTER_INFO)
				.general((cmd)->cmd.clusterInfo(), ClusterInfoConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status clusterMeet(final String ip, final int port){
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(ProtocolCommand.CLUSTER_MEET)
				.general((cmd)->cmd.clusterMeet(ip, port), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public List<RedisClusterServer> clusterNodes(){
		final JedisSentinelCommand<List<RedisClusterServer>> command = JedisSentinelCommand.<List<RedisClusterServer>>create(
						ProtocolCommand.CLUSTER_NODES)
				.general((cmd)->cmd.clusterNodes(), ClusterNodesConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisSentinelCommand<List<RedisClusterServer>> command = JedisSentinelCommand.<List<RedisClusterServer>>create(
						ProtocolCommand.CLUSTER_SLAVES)
				.general((cmd)->cmd.clusterSlaves(nodeId), ClusterSlaveConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisSentinelCommand<List<RedisClusterServer>> command = JedisSentinelCommand.<List<RedisClusterServer>>create(
				ProtocolCommand.CLUSTER_REPLICAS).general((cmd)->cmd.clusterReplicas(nodeId),
				ClusterReplicasConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clusterReplicate(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(
						ProtocolCommand.CLUSTER_REPLICATE)
				.general((cmd)->cmd.clusterReplicate(nodeId), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption){
		final CommandArguments args = CommandArguments.create("clusterResetOption", clusterResetOption);
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(ProtocolCommand.CLUSTER_RESET)
				.general((cmd)->cmd.clusterReset(
								ClusterResetOptionConverter.INSTANCE.convert(clusterResetOption)),
						OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clusterSaveConfig(){
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(
						ProtocolCommand.CLUSTER_SAVECONFIG)
				.general((cmd)->cmd.clusterSaveConfig(), OkStatusConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch){
		final CommandArguments args = CommandArguments.create("configEpoch", configEpoch);
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(
						ProtocolCommand.CLUSTER_SETCONFIGEPOCH)
				.general((cmd)->cmd.clusterSetConfigEpoch(configEpoch), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public BumpEpoch clusterBumpEpoch(){
		final JedisSentinelCommand<BumpEpoch> command = JedisSentinelCommand.<BumpEpoch>create(
						ProtocolCommand.CLUSTER_BUMPEPOCH)
				.general((cmd)->cmd.clusterBumpEpoch(), BumpEpochConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId){
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(
						ProtocolCommand.CLUSTER_SETSLOT)
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
				}, OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status asking(){
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(ProtocolCommand.ASKING)
				.general((cmd)->cmd.asking(), OkStatusConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status readWrite(){
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(ProtocolCommand.ASKING)
				.general((cmd)->cmd.readwrite(), OkStatusConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status readOnly(){
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(ProtocolCommand.ASKING)
				.general((cmd)->cmd.readonly(), OkStatusConverter.INSTANCE);
		return execute(command);
	}

}
