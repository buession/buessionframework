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
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.RedisClusterServer;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.args.ClusterResetType;

import java.util.List;
import java.util.Map;

/**
 * Jedis 哨兵模式集群命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelClusterOperations extends AbstractClusterOperations<Jedis> {

	public JedisSentinelClusterOperations(final JedisSentinelClient client){
		super(client);
	}

	@Override
	public String clusterMyId(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_MY_ID);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_MY_ID);
		}else{
			return execute((cmd)->cmd.clusterMyId(), ProtocolCommand.CLUSTER_MY_ID);
		}
	}

	@Override
	public Status clusterAddSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_ADDSLOTS, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_ADDSLOTS, args);
		}else{
			return execute((cmd)->cmd.clusterAddSlots(slots), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLUSTER_ADDSLOTS, args);
		}
	}

	@Override
	public Map<Integer, RedisClusterServer> clusterSlots(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_SLOTS);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_SLOTS);
		}else{
			return execute((cmd)->cmd.clusterSlots(), ProtocolCommand.CLUSTER_SLOTS);
		}
	}

	@Override
	public long clusterCountKeysInSlot(final int slot){
		final CommandArguments args = CommandArguments.create("slot", slot);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_COUNTKEYSINSLOT, args);
		}else{
			return execute((cmd)->cmd.clusterCountKeysInSlot(slot), ProtocolCommand.CLUSTER_COUNTKEYSINSLOT, args);
		}
	}

	@Override
	public Status clusterDelSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_DELSLOTS, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_DELSLOTS, args);
		}else{
			return execute((cmd)->cmd.clusterDelSlots(slots), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLUSTER_DELSLOTS, args);
		}
	}

	@Override
	public Status clusterFlushSlots(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_FLUSHSLOTS);
		}else if(isTransaction()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_FLUSHSLOTS);
		}else{
			return execute((cmd)->cmd.clusterFlushSlots(), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLUSTER_FLUSHSLOTS);
		}
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption){
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_FAILOVER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_FAILOVER, args);
		}else{
			final redis.clients.jedis.args.ClusterFailoverOption failoverOption = CLUSTER_FAILOVER_OPTION_JEDIS_CONVERTER.convert(
					clusterFailoverOption);
			return execute((cmd)->cmd.clusterFailover(failoverOption), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLUSTER_FAILOVER, args);
		}
	}

	@Override
	public Status clusterForget(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_FORGET, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_FORGET, args);
		}else{
			return execute((cmd)->cmd.clusterForget(nodeId), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLUSTER_FORGET, args);
		}
	}

	@Override
	public Status clusterForget(final byte[] nodeId){
		return clusterForget(SafeEncoder.encode(nodeId));
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count){
		final CommandArguments args = CommandArguments.create("slot", slot).put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_GETKEYSINSLOT, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_GETKEYSINSLOT, args);
		}else{
			return execute((cmd)->cmd.clusterGetKeysInSlot(slot, (int) count), ProtocolCommand.CLUSTER_GETKEYSINSLOT,
					args);
		}
	}

	@Override
	public long clusterKeySlot(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_KEYSLOT, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_KEYSLOT, args);
		}else{
			return execute((cmd)->cmd.clusterKeySlot(key), ProtocolCommand.CLUSTER_KEYSLOT, args);
		}
	}

	@Override
	public long clusterKeySlot(final byte[] key){
		return clusterKeySlot(SafeEncoder.encode(key));
	}

	@Override
	public ClusterInfo clusterInfo(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_INFO);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_INFO);
		}else{
			return execute((cmd)->cmd.clusterInfo(), ProtocolCommand.CLUSTER_INFO);
		}
	}

	@Override
	public Status clusterMeet(final String ip, final int port){
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_MEET, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_MEET, args);
		}else{
			return execute((cmd)->cmd.clusterMeet(ip, port), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLUSTER_MEET, args);
		}
	}

	@Override
	public Status clusterMeet(final byte[] ip, final int port){
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_MEET, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_MEET, args);
		}else{
			return execute((cmd)->cmd.clusterMeet(SafeEncoder.encode(ip), port), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLUSTER_MEET, args);
		}
	}

	@Override
	public List<RedisClusterServer> clusterNodes(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_NODES);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_NODES);
		}else{
			return execute((cmd)->cmd.clusterNodes(), ProtocolCommand.CLUSTER_NODES);
		}
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_SLAVES, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_SLAVES, args);
		}else{
			return execute((cmd)->cmd.clusterSlaves(nodeId), ProtocolCommand.CLUSTER_SLAVES, args);
		}
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final byte[] nodeId){
		return clusterSlaves(SafeEncoder.encode(nodeId));
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_REPLICAS, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_REPLICAS, args);
		}else{
			return execute((cmd)->cmd.clusterReplicas(nodeId), ProtocolCommand.CLUSTER_REPLICAS, args);
		}
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final byte[] nodeId){
		return clusterReplicas(SafeEncoder.encode(nodeId));
	}

	@Override
	public Status clusterReplicate(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_REPLICATE, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_REPLICATE, args);
		}else{
			return execute((cmd)->cmd.clusterReplicate(nodeId), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLUSTER_REPLICATE, args);
		}
	}

	@Override
	public Status clusterReplicate(final byte[] nodeId){
		return clusterReplicate(SafeEncoder.encode(nodeId));
	}

	@Override
	public Status clusterReset(){
		return clusterReset(ClusterResetOption.SOFT);
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption){
		final CommandArguments args = CommandArguments.create("clusterResetOption", clusterResetOption);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_RESET, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_RESET, args);
		}else{
			final ClusterResetType resetType = CLUSTER_RESET_OPTION_JEDIS_CONVERTER.convert(clusterResetOption);
			return execute((cmd)->cmd.clusterReset(resetType), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLUSTER_RESET, args);
		}
	}

	@Override
	public Status clusterSaveConfig(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_SAVECONFIG);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_SAVECONFIG);
		}else{
			return execute((cmd)->cmd.clusterSaveConfig(), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLUSTER_SAVECONFIG);
		}
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId){
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER_SETSLOT, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER_SETSLOT, args);
		}else{
			switch(setSlotOption){
				case IMPORTING:
					return execute((cmd)->cmd.clusterSetSlotImporting(slot, nodeId), Converters.OK_STATUS_CONVERTER,
							ProtocolCommand.CLUSTER_SETSLOT, args);
				case MIGRATING:
					return execute((cmd)->cmd.clusterSetSlotMigrating(slot, nodeId), Converters.OK_STATUS_CONVERTER,
							ProtocolCommand.CLUSTER_SETSLOT, args);
				case STABLE:
					return execute((cmd)->cmd.clusterSetSlotStable(slot), Converters.OK_STATUS_CONVERTER,
							ProtocolCommand.CLUSTER_SETSLOT, args);
				case NODE:
					return execute((cmd)->cmd.clusterSetSlotNode(slot, nodeId), Converters.OK_STATUS_CONVERTER,
							ProtocolCommand.CLUSTER_SETSLOT, args);
				default:
					return Status.FAILURE;
			}
		}
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final byte[] nodeId){
		return clusterSetSlot(slot, setSlotOption, SafeEncoder.encode(nodeId));
	}

	@Override
	public Status asking(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ASKING);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ASKING);
		}else{
			return execute((cmd)->cmd.asking(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.ASKING);
		}
	}

	@Override
	public Status readWrite(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.READWRITE);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.READWRITE);
		}else{
			return execute((cmd)->cmd.readwrite(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.READWRITE);
		}
	}

	@Override
	public Status readOnly(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.READONLY);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.READONLY);
		}else{
			return execute((cmd)->cmd.readonly(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.READONLY);
		}
	}

}
