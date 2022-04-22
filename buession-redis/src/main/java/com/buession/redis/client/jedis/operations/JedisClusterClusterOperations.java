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
public final class JedisClusterClusterOperations extends AbstractClusterOperations<JedisClusterClient> {

	public JedisClusterClusterOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public String clusterMyId(){
		return new JedisClusterCommand<String>(client, ProtocolCommand.CLUSTER_MY_ID)
				.run();
	}

	@Override
	public Status clusterAddSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLUSTER_ADDSLOTS)
				.run(args);
	}

	@Override
	public List<ClusterSlot> clusterSlots(){
		return new JedisClusterCommand<List<ClusterSlot>>(client, ProtocolCommand.CLUSTER_SLOTS)
				.run();
	}

	@Override
	public int clusterCountFailureReports(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisClusterCommand<Integer>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS)
				.run(args);
	}

	@Override
	public int clusterCountFailureReports(final byte[] nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisClusterCommand<Integer>(client, ProtocolCommand.CLUSTER_COUNTFAILUREREPORTS)
				.run(args);
	}

	@Override
	public long clusterCountKeysInSlot(final int slot){
		final CommandArguments args = CommandArguments.create("slot", slot);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT)
				.run(args);
	}

	@Override
	public Status clusterDelSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLUSTER_DELSLOTS).run(args);
	}

	@Override
	public Status clusterFlushSlots(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLUSTER_FLUSHSLOTS)
				.run();
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption){
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLUSTER_FAILOVER)
				.run(args);
	}

	@Override
	public Status clusterForget(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLUSTER_FORGET)
				.run(args);
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count){
		final CommandArguments args = CommandArguments.create("slot", slot).put("count", count);
		return new JedisClusterCommand<List<String>>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT)
				.run(args);
	}

	@Override
	public long clusterKeySlot(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.CLUSTER_GETKEYSINSLOT)
				.run(args);
	}

	@Override
	public ClusterInfo clusterInfo(){
		return new JedisClusterCommand<ClusterInfo>(client, ProtocolCommand.CLUSTER_INFO)
				.run();
	}

	@Override
	public Status clusterMeet(final String ip, final int port){
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLUSTER_MEET)
				.run(args);
	}

	@Override
	public List<RedisClusterServer> clusterNodes(){
		return new JedisClusterCommand<List<RedisClusterServer>>(client, ProtocolCommand.CLUSTER_NODES)
				.run();
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisClusterCommand<List<RedisClusterServer>>(client, ProtocolCommand.CLUSTER_SLAVES)
				.run(args);
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisClusterCommand<List<RedisClusterServer>>(client, ProtocolCommand.CLUSTER_REPLICAS)
				.run(args);
	}

	@Override
	public Status clusterReplicate(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLUSTER_REPLICATE)
				.run(args);
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption){
		final CommandArguments args = CommandArguments.create("clusterResetOption", clusterResetOption);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLUSTER_RESET)
				.run(args);
	}

	@Override
	public Status clusterSaveConfig(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLUSTER_SAVECONFIG)
				.run();
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch){
		final CommandArguments args = CommandArguments.create("configEpoch", configEpoch);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLUSTER_SETCONFIGEPOCH)
				.run(args);
	}

	@Override
	public BumpEpoch clusterBumpEpoch(){
		return new JedisClusterCommand<BumpEpoch>(client, ProtocolCommand.CLUSTER_BUMPEPOCH)
				.run();
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId){
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLUSTER_SETSLOT)
				.run(args);
	}

	@Override
	public Status asking(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.ASKING)
				.run();
	}

	@Override
	public Status readWrite(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.READWRITE)
				.run();
	}

	@Override
	public Status readOnly(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.READONLY)
				.run();
	}

}
