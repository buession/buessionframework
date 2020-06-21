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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.utils.NumberUtils;
import com.buession.lang.Status;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.Info;
import com.buession.redis.core.InfoSection;
import com.buession.redis.core.ObjectCommand;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLogCommand;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class ShardedJedisServerOperations extends AbstractServerOperations<ShardedJedis, ShardedJedisPipeline> {

	public ShardedJedisServerOperations(final RedisClient client){
		super(client);
	}

	@Override
	public String bgRewriteAof(){
		commandAllNotSupportedException(ProtocolCommand.BGREWRITEAOF);
		return null;
	}

	@Override
	public String bgSave(){
		commandAllNotSupportedException(ProtocolCommand.BGSAVE);
		return null;
	}

	@Override
	public Status clientKill(final String host, final int port){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_KILL);
		return null;
	}

	@Override
	public String clientGetName(){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_GETNAME);
		return null;
	}

	@Override
	public String clientId(){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_ID);
		return null;
	}

	@Override
	public List<Client> clientList(){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_LIST);
		return null;
	}

	@Override
	public Status clientPause(final int timeout){
		return clientPause((long) timeout);
	}

	@Override
	public Status clientPause(final long timeout){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_PAUSE);
		return null;
	}

	@Override
	public Status clientReply(final ClientReply option){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_REPLY);
		return null;
	}

	@Override
	public Status clientSetName(final String name){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_SETNAME);
		return null;
	}

	@Override
	public Status clientSetName(final byte[] name){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_SETNAME);
		return null;
	}

	@Override
	public Status clientUnblock(final int clientId){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_UNBLOCK);
		return null;
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_UNBLOCK);
		return null;
	}

	@Override
	public List<String> configGet(final String parameter){
		commandAllNotSupportedException(ProtocolCommand.CONFIG_GET);
		return null;
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		commandAllNotSupportedException(ProtocolCommand.CONFIG_GET);
		return null;
	}

	@Override
	public Status configResetStat(){
		commandAllNotSupportedException(ProtocolCommand.CONFIG_RESETSTAT);
		return null;
	}

	@Override
	public Status configRewrite(){
		commandAllNotSupportedException(ProtocolCommand.CONFIG_REWRITE);
		return null;
	}

	@Override
	public Status configSet(final String parameter, final float value){
		return configSet(parameter, Float.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final float value){
		return configSet(parameter, NumberUtils.float2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final double value){
		return configSet(parameter, Double.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final double value){
		return configSet(parameter, NumberUtils.double2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final int value){
		return configSet(parameter, Integer.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final long value){
		return configSet(parameter, NumberUtils.long2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final long value){
		return configSet(parameter, Long.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final int value){
		return configSet(parameter, NumberUtils.int2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final String value){
		commandAllNotSupportedException(ProtocolCommand.CONFIG_SET);
		return null;
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		commandAllNotSupportedException(ProtocolCommand.CONFIG_SET);
		return null;
	}

	@Override
	public Long dbSize(){
		commandAllNotSupportedException(ProtocolCommand.DBSIZE);
		return null;
	}

	@Override
	public String debugObject(final String key){
		commandAllNotSupportedException(ProtocolCommand.DEBUG_OBJECT);
		return null;
	}

	@Override
	public byte[] debugObject(final byte[] key){
		commandAllNotSupportedException(ProtocolCommand.DEBUG_OBJECT);
		return null;
	}

	@Override
	public String debugSegfault(){
		commandAllNotSupportedException(ProtocolCommand.DEBUG_SEGFAULT);
		return null;
	}

	@Override
	public Status flushAll(){
		commandAllNotSupportedException(ProtocolCommand.FLUSHALL);
		return null;
	}

	@Override
	public Status flushDb(){
		commandAllNotSupportedException(ProtocolCommand.FLUSHDB);
		return null;
	}

	@Override
	public Info info(final InfoSection section){
		commandAllNotSupportedException(ProtocolCommand.INFO);
		return null;
	}

	@Override
	public Info info(){
		commandAllNotSupportedException(ProtocolCommand.INFO);
		return null;
	}

	@Override
	public Long lastSave(){
		commandAllNotSupportedException(ProtocolCommand.LASTSAVE);
		return null;
	}

	@Override
	public String memoryDoctor(){
		commandAllNotSupportedException(ProtocolCommand.MEMORY_DOCTOR);
		return null;
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		commandAllNotSupportedException(ProtocolCommand.MONITOR);
	}

	@Override
	public Object object(final ObjectCommand command, final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(JedisClientUtils.objectDebug(command, getPipeline(), key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(JedisClientUtils.objectDebug(command, getTransaction(),
					key)));
		}else{
			return execute((cmd)->JedisClientUtils.objectDebug(command, cmd, key));
		}
	}

	@Override
	public Object object(final ObjectCommand command, final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(JedisClientUtils.objectDebug(command, getPipeline(), key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(JedisClientUtils.objectDebug(command, getTransaction(),
					key)));
		}else{
			return execute((cmd)->JedisClientUtils.objectDebug(command, cmd, key));
		}
	}

	@Override
	public Object pSync(final String masterRunId, final int offset){
		commandAllNotSupportedException(ProtocolCommand.PSYNC);
		return null;
	}

	@Override
	public Object pSync(final byte[] masterRunId, final int offset){
		commandAllNotSupportedException(ProtocolCommand.PSYNC);
		return null;
	}

	@Override
	public Object pSync(final String masterRunId, final long offset){
		commandAllNotSupportedException(ProtocolCommand.PSYNC);
		return null;
	}

	@Override
	public Object pSync(final byte[] masterRunId, final long offset){
		commandAllNotSupportedException(ProtocolCommand.PSYNC);
		return null;
	}

	@Override
	public Status replicaOf(final String host, final int port){
		commandAllNotSupportedException(ProtocolCommand.REPLICAOF);
		return null;
	}

	@Override
	public Role role(){
		commandAllNotSupportedException(ProtocolCommand.ROLE);
		return null;
	}

	@Override
	public Status save(){
		commandAllNotSupportedException(ProtocolCommand.SAVE);
		return null;
	}

	@Override
	public void shutdown(){
		commandAllNotSupportedException(ProtocolCommand.SHUTDOWN);
	}

	@Override
	public void shutdown(final boolean save){
		shutdown();
	}

	@Override
	public Status slaveOf(final String host, final int port){
		commandAllNotSupportedException(ProtocolCommand.SLAVEOF);
		return null;
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final String... arguments){
		commandAllNotSupportedException(ProtocolCommand.SLOWLOG);
		return null;
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final byte[]... arguments){
		commandAllNotSupportedException(ProtocolCommand.SLOWLOG);
		return null;
	}

	@Override
	public Object sync(){
		if(isPipeline()){
			return transactionExecute((cmd)->{
				getPipeline().sync();
				return null;
			});
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SYNC);
		}else{
			throw new NotSupportedCommandException(ProtocolCommand.SYNC);
		}
	}

	@Override
	public RedisServerTime time(){
		commandAllNotSupportedException(ProtocolCommand.TIME);
		return null;
	}

}
