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
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.RedisClusterServer;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.params.ClusterFailoverOptionConverter;
import com.buession.redis.core.internal.convert.jedis.response.BumpEpochConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterNodesConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterReplicasConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterResetOptionConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterSlaveConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterSlotConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;

import java.util.List;

/**
 * Jedis 单机模式集群命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterOperations extends AbstractClusterOperations<JedisStandaloneClient> {

	public JedisClusterOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public String clusterMyId(){
		final JedisCommand<String> command = new JedisCommand<String>(client, ProtocolCommand.CLUSTER_MY_ID)
				.general((cmd)->cmd.clusterMyId());
		return execute(command);
	}

	@Override
	public Status clusterAddSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_ADDSLOTS)
				.general((cmd)->cmd.clusterAddSlots(slots), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public List<ClusterSlot> clusterSlots(){
		final JedisCommand<List<ClusterSlot>> command = new JedisCommand<List<ClusterSlot>>(client,
				ProtocolCommand.CLUSTER_SLOTS).general((cmd)->cmd.clusterSlots(), ClusterSlotConverter.LIST_CONVERTER);
		return execute(command);
	}

	@Override
	public int clusterCountFailureReports(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisCommand<Integer> command = new JedisCommand<>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS);
		return execute(command, args);
	}

	@Override
	public int clusterCountFailureReports(final byte[] nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisCommand<Integer> command = new JedisCommand<>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS);
		return execute(command, args);
	}

	@Override
	public long clusterCountKeysInSlot(final int slot){
		final CommandArguments args = CommandArguments.create("slot", slot);
		final JedisCommand<Long> command = new JedisCommand<Long>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT)
				.general((cmd)->cmd.clusterCountKeysInSlot(slot));
		return execute(command, args);
	}

	@Override
	public Status clusterDelSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_DELSLOTS)
				.general((cmd)->cmd.clusterDelSlots(slots), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clusterFlushSlots(){
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS)
				.general((cmd)->cmd.clusterFlushSlots(), OkStatusConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption){
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_FAILOVER)
				.general((cmd)->cmd.clusterFailover(
								ClusterFailoverOptionConverter.INSTANCE.convert(clusterFailoverOption)),
						OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clusterForget(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_FORGET)
				.general((cmd)->cmd.clusterForget(nodeId), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count){
		final CommandArguments args = CommandArguments.create("slot", slot).put("count", count);
		final JedisCommand<List<String>> command = new JedisCommand<List<String>>(client,
				ProtocolCommand.CLUSTER_GETKEYSINSLOT).general((cmd)->cmd.clusterGetKeysInSlot(slot, (int) count));
		return execute(command, args);
	}

	@Override
	public long clusterKeySlot(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = new JedisCommand<Long>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT)
				.general((cmd)->cmd.clusterKeySlot(key));
		return execute(command, args);
	}

	@Override
	public ClusterInfo clusterInfo(){
		final JedisCommand<ClusterInfo> command = new JedisCommand<ClusterInfo>(client, ProtocolCommand.CLUSTER_INFO)
				.general((cmd)->cmd.clusterInfo(), ClusterInfoConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status clusterMeet(final String ip, final int port){
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_MEET)
				.general((cmd)->cmd.clusterMeet(ip, port), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public List<RedisClusterServer> clusterNodes(){
		final JedisCommand<List<RedisClusterServer>> command = new JedisCommand<List<RedisClusterServer>>(client,
				ProtocolCommand.CLUSTER_NODES)
				.general((cmd)->cmd.clusterNodes(), ClusterNodesConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisCommand<List<RedisClusterServer>> command = new JedisCommand<List<RedisClusterServer>>(client,
				ProtocolCommand.CLUSTER_SLAVES)
				.general((cmd)->cmd.clusterSlaves(nodeId), ClusterSlaveConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisCommand<List<RedisClusterServer>> command = new JedisCommand<List<RedisClusterServer>>(client,
				ProtocolCommand.CLUSTER_REPLICAS)
				.general((cmd)->cmd.clusterReplicas(nodeId), ClusterReplicasConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clusterReplicate(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_REPLICATE)
				.general((cmd)->cmd.clusterReplicate(nodeId), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption){
		final CommandArguments args = CommandArguments.create("clusterResetOption", clusterResetOption);
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_RESET)
				.general((cmd)->cmd.clusterReset(ClusterResetOptionConverter.INSTANCE.convert(clusterResetOption)),
						OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clusterSaveConfig(){
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_SAVECONFIG)
				.general((cmd)->cmd.clusterSaveConfig(), OkStatusConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch){
		final CommandArguments args = CommandArguments.create("configEpoch", configEpoch);
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH)
				.general((cmd)->cmd.clusterSetConfigEpoch(configEpoch), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public BumpEpoch clusterBumpEpoch(){
		final JedisCommand<BumpEpoch> command = new JedisCommand<BumpEpoch>(client, ProtocolCommand.CLUSTER_BUMPEPOCH)
				.general((cmd)->cmd.clusterBumpEpoch(), BumpEpochConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId){
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.CLUSTER_SETSLOT)
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
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.ASKING)
				.general((cmd)->cmd.asking(), OkStatusConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status readWrite(){
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.ASKING)
				.general((cmd)->cmd.readwrite(), OkStatusConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status readOnly(){
		final JedisCommand<Status> command = new JedisCommand<Status>(client, ProtocolCommand.ASKING)
				.general((cmd)->cmd.readonly(), OkStatusConverter.INSTANCE);
		return execute(command);
	}

}
