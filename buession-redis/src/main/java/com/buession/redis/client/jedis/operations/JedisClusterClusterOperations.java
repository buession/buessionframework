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
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.RedisClusterServer;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

import java.util.List;

/**
 * Jedis 集群模式集群命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterClusterOperations extends AbstractClusterOperations<JedisClusterConnection> {

	public JedisClusterClusterOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public String clusterMyId(){
		final JedisClusterCommand<String> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_MY_ID);
		return execute(command);
	}

	@Override
	public Status clusterAddSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_ADDSLOTS);
		return execute(command, args);
	}

	@Override
	public List<ClusterSlot> clusterSlots(){
		final JedisClusterCommand<List<ClusterSlot>> command = JedisClusterCommand.create(
				ProtocolCommand.CLUSTER_SLOTS);
		return execute(command);
	}

	@Override
	public int clusterCountFailureReports(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisClusterCommand<Integer> command = JedisClusterCommand.create(
				ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS);
		return execute(command, args);
	}

	@Override
	public int clusterCountFailureReports(final byte[] nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisClusterCommand<Integer> command = JedisClusterCommand.create(
				ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS);
		return execute(command, args);
	}

	@Override
	public long clusterCountKeysInSlot(final int slot){
		final CommandArguments args = CommandArguments.create("slot", slot);
		final JedisClusterCommand<Long> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_COUNTKEYSINSLOT);
		return execute(command, args);
	}

	@Override
	public Status clusterDelSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_DELSLOTS);
		return execute(command, args);
	}

	@Override
	public Status clusterFlushSlots(){
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_FLUSHSLOTS);
		return execute(command);
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption){
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_FAILOVER);
		return execute(command, args);
	}

	@Override
	public Status clusterForget(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_FORGET);
		return execute(command, args);
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count){
		final CommandArguments args = CommandArguments.create("slot", slot).put("count", count);
		final JedisClusterCommand<List<String>> command = JedisClusterCommand.create(
				ProtocolCommand.CLUSTER_GETKEYSINSLOT);
		return execute(command, args);
	}

	@Override
	public long clusterKeySlot(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_GETKEYSINSLOT);
		return execute(command, args);
	}

	@Override
	public ClusterInfo clusterInfo(){
		final JedisClusterCommand<ClusterInfo> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_INFO);
		return execute(command);
	}

	@Override
	public Status clusterMeet(final String ip, final int port){
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_MEET);
		return execute(command, args);
	}

	@Override
	public List<RedisClusterServer> clusterNodes(){
		final JedisClusterCommand<List<RedisClusterServer>> command = JedisClusterCommand.create(
				ProtocolCommand.CLUSTER_NODES);
		return execute(command);
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisClusterCommand<List<RedisClusterServer>> command = JedisClusterCommand.create(
				ProtocolCommand.CLUSTER_SLAVES);
		return execute(command, args);
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisClusterCommand<List<RedisClusterServer>> command = JedisClusterCommand.create(
				ProtocolCommand.CLUSTER_REPLICAS);
		return execute(command, args);
	}

	@Override
	public Status clusterReplicate(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_REPLICATE);
		return execute(command, args);
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption){
		final CommandArguments args = CommandArguments.create("clusterResetOption", clusterResetOption);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_RESET);
		return execute(command, args);
	}

	@Override
	public Status clusterSaveConfig(){
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_SAVECONFIG);
		return execute(command);
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch){
		final CommandArguments args = CommandArguments.create("configEpoch", configEpoch);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_SETCONFIGEPOCH);
		return execute(command, args);
	}

	@Override
	public BumpEpoch clusterBumpEpoch(){
		final JedisClusterCommand<BumpEpoch> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_BUMPEPOCH);
		return execute(command);
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId){
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLUSTER_SETSLOT);
		return execute(command, args);
	}

	@Override
	public Status asking(){
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.ASKING);
		return execute(command);
	}

	@Override
	public Status readWrite(){
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.ASKING);
		return execute(command);
	}

	@Override
	public Status readOnly(){
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.ASKING);
		return execute(command);
	}

}
