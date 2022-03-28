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
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.RedisClusterServer;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Map;

/**
 * Jedis 集群模式集群命令操作抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterClusterOperations extends AbstractClusterOperations<JedisCluster> {

	public JedisClusterClusterOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public String clusterMyId(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER);
	}

	@Override
	public Status clusterAddSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Map<Integer, RedisClusterServer> clusterSlots(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER);
	}

	@Override
	public int clusterCountKeysInSlot(final int slot){
		final CommandArguments args = CommandArguments.create("slot", slot);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status clusterDelSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status clusterFlushSlots(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption){
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status clusterForget(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status clusterForget(final byte[] nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count){
		final CommandArguments args = CommandArguments.create("slot", slot).put("count", count);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public ClusterInfo clusterInfo(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER);
	}

	@Override
	public long clusterKeySlot(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public long clusterKeySlot(final byte[] key){
		return clusterKeySlot(SafeEncoder.encode(key));
	}

	@Override
	public Status clusterMeet(final String ip, final int port){
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status clusterMeet(final byte[] ip, final int port){
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public List<RedisClusterServer> clusterNodes(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER);
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final byte[] nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final byte[] nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status clusterReplicate(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status clusterReplicate(final byte[] nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status clusterReset(){
		return clusterReset(ClusterResetOption.SOFT);
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption){
		final CommandArguments args = CommandArguments.create("clusterResetOption", clusterResetOption);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status clusterSaveConfig(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER);
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId){
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final byte[] nodeId){
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
	}

	@Override
	public Status asking(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ASKING);
	}

	@Override
	public Status readwrite(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.READWRITE);
	}

	@Override
	public Status readonly(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.READONLY);
	}

}
