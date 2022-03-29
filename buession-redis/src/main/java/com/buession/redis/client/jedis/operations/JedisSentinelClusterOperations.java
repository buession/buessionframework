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
import com.buession.redis.core.convert.Converters;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * Jedis 哨兵模式集群命令操作抽象类
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
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER);
		}else{
			return execute((cmd)->cmd.clusterMyId(), ProtocolCommand.CLUSTER);
		}
	}

	@Override
	public Status clusterAddSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().clusterAddSlots(slots), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.CLUSTER, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().clusterAddSlots(slots), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.CLUSTER, args);
		}else{
			return execute((cmd)->cmd.clusterAddSlots(slots), Converters.OK_STATUS_CONVERTER, ProtocolCommand.CLUSTER,
					args);
		}
	}

	@Override
	public Map<Integer, RedisClusterServer> clusterSlots(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER);
		}else{
			return execute((cmd)->cmd.clusterSlots(), ProtocolCommand.CLUSTER);
		}
	}

	@Override
	public int clusterCountKeysInSlot(final int slot){
		final CommandArguments args = CommandArguments.create("slot", slot);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER, args);
		}else{
			return execute((cmd)->cmd.clusterCountKeysInSlot(slot).intValue(), ProtocolCommand.CLUSTER, args);
		}
	}

	@Override
	public Status clusterDelSlots(final int... slots){
		final CommandArguments args = CommandArguments.create("slots", slots);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().clusterDelSlots(slots), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.CLUSTER, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().clusterDelSlots(slots), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.CLUSTER, args);
		}else{
			return execute((cmd)->cmd.clusterDelSlots(slots), Converters.OK_STATUS_CONVERTER, ProtocolCommand.CLUSTER,
					args);
		}
	}

	@Override
	public Status clusterFlushSlots(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER);
		}else if(isTransaction()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER);
		}else{
			return execute((cmd)->cmd.clusterFlushSlots(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.CLUSTER);
		}
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption){
		final CommandArguments args = CommandArguments.create("clusterFailoverOption", clusterFailoverOption);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER, args);
		}else{
			return execute(
					(cmd)->cmd.clusterFailover(CLUSTER_FAILOVER_OPTION_JEDIS_CONVERTER.convert(clusterFailoverOption)),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.CLUSTER, args);
		}
	}

	@Override
	public Status clusterForget(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER, args);
		}else{
			return execute((cmd)->cmd.clusterForget(nodeId), Converters.OK_STATUS_CONVERTER, ProtocolCommand.CLUSTER,
					args);
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
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().clusterGetKeysInSlot(slot, (int) count)),
					ProtocolCommand.CLUSTER, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().clusterGetKeysInSlot(slot, (int) count)),
					ProtocolCommand.CLUSTER, args);
		}else{
			return execute((cmd)->cmd.clusterGetKeysInSlot(slot, (int) count), ProtocolCommand.CLUSTER, args);
		}
	}

	@Override
	public ClusterInfo clusterInfo(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().clusterInfo()), ProtocolCommand.CLUSTER);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().clusterInfo()), ProtocolCommand.CLUSTER);
		}else{
			return execute((cmd)->cmd.clusterInfo(), ProtocolCommand.CLUSTER);
		}
	}

	@Override
	public long clusterKeySlot(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER, args);
		}else{
			return execute((cmd)->cmd.clusterKeySlot(key), ProtocolCommand.CLUSTER, args);
		}
	}

	@Override
	public long clusterKeySlot(final byte[] key){
		return clusterKeySlot(SafeEncoder.encode(key));
	}

	@Override
	public Status clusterMeet(final String ip, final int port){
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().clusterMeet(ip, port), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.CLUSTER);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().clusterMeet(ip, port), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.CLUSTER);
		}else{
			return execute((cmd)->cmd.clusterMeet(ip, port), Converters.OK_STATUS_CONVERTER, ProtocolCommand.CLUSTER,
					args);
		}
	}

	@Override
	public Status clusterMeet(final byte[] ip, final int port){
		final CommandArguments args = CommandArguments.create("ip", ip).put("port", port);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().clusterMeet(SafeEncoder.encode(ip), port),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.CLUSTER);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().clusterMeet(SafeEncoder.encode(ip), port),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.CLUSTER);
		}else{
			return execute((cmd)->cmd.clusterMeet(SafeEncoder.encode(ip), port), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLUSTER, args);
		}
	}

	@Override
	public List<RedisClusterServer> clusterNodes(){
		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().clusterNodes()), ProtocolCommand.CLUSTER);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().clusterNodes()), ProtocolCommand.CLUSTER);
		}else{
			return execute((cmd)->cmd.clusterNodes(), ProtocolCommand.CLUSTER);
		}
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final String nodeId){
		final CommandArguments args = CommandArguments.create("nodeId", nodeId);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER, args);
		}else{
			return execute((cmd)->cmd.clusterSlaves(nodeId), ProtocolCommand.CLUSTER, args);
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
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER, args);
		}else{
			return execute((cmd)->cmd.clusterReplicas(nodeId), ProtocolCommand.CLUSTER, args);
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
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER, args);
		}else{
			return execute((cmd)->cmd.clusterReplicate(nodeId), Converters.OK_STATUS_CONVERTER, ProtocolCommand.CLUSTER,
					args);
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
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER, args);
		}else{
			return execute((cmd)->cmd.clusterReset(CLUSTER_RESET_OPTION_JEDIS_CONVERTER.convert(clusterResetOption)),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.CLUSTER, args);
		}
	}

	@Override
	public Status clusterSaveConfig(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLUSTER);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLUSTER);
		}else{
			return execute((cmd)->cmd.clusterSaveConfig(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.CLUSTER);
		}
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId){
		final CommandArguments args = CommandArguments.create("slot", slot).put("setSlotOption", setSlotOption)
				.put("nodeId", nodeId);

		if(isPipeline()){
			switch(setSlotOption){
				case IMPORTING:
					return pipelineExecute(
							(cmd)->newJedisResult(getPipeline().clusterSetSlotImporting(slot, nodeId),
									Converters.OK_STATUS_CONVERTER), ProtocolCommand.CLUSTER, args);
				case MIGRATING:
					return pipelineExecute(
							(cmd)->newJedisResult(getPipeline().clusterSetSlotMigrating(slot, nodeId),
									Converters.OK_STATUS_CONVERTER), ProtocolCommand.CLUSTER, args);
				case STABLE:
					return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
				case NODE:
					return pipelineExecute(
							(cmd)->newJedisResult(getPipeline().clusterSetSlotNode(slot, nodeId),
									Converters.OK_STATUS_CONVERTER), ProtocolCommand.CLUSTER, args);
				default:
					return Status.FAILURE;
			}
		}else if(isTransaction()){
			switch(setSlotOption){
				case IMPORTING:
					return transactionExecute(
							(cmd)->newJedisResult(getTransaction().clusterSetSlotImporting(slot, nodeId),
									Converters.OK_STATUS_CONVERTER), ProtocolCommand.CLUSTER, args);
				case MIGRATING:
					return transactionExecute(
							(cmd)->newJedisResult(getTransaction().clusterSetSlotMigrating(slot, nodeId),
									Converters.OK_STATUS_CONVERTER), ProtocolCommand.CLUSTER, args);
				case STABLE:
					return execute(CommandNotSupported.ALL, ProtocolCommand.CLUSTER, args);
				case NODE:
					return transactionExecute(
							(cmd)->newJedisResult(getTransaction().clusterSetSlotNode(slot, nodeId),
									Converters.OK_STATUS_CONVERTER), ProtocolCommand.CLUSTER, args);
				default:
					return Status.FAILURE;
			}
		}else{
			switch(setSlotOption){
				case IMPORTING:
					return execute((cmd)->cmd.clusterSetSlotImporting(slot, nodeId), Converters.OK_STATUS_CONVERTER,
							ProtocolCommand.CLUSTER, args);
				case MIGRATING:
					return execute((cmd)->cmd.clusterSetSlotMigrating(slot, nodeId), Converters.OK_STATUS_CONVERTER,
							ProtocolCommand.CLUSTER, args);
				case STABLE:
					return execute((cmd)->cmd.clusterSetSlotStable(slot), Converters.OK_STATUS_CONVERTER,
							ProtocolCommand.CLUSTER, args);
				case NODE:
					return execute((cmd)->cmd.clusterSetSlotNode(slot, nodeId), Converters.OK_STATUS_CONVERTER,
							ProtocolCommand.CLUSTER, args);
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
	public Status readwrite(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.READWRITE);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.READWRITE);
		}else{
			return execute((cmd)->cmd.readwrite(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.READWRITE);
		}
	}

	@Override
	public Status readonly(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.READONLY);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.READONLY);
		}else{
			return execute((cmd)->cmd.readonly(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.READONLY);
		}
	}

}
