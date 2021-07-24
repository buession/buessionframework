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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.ShardedJedisClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.Info;
import com.buession.redis.core.ObjectCommand;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.SlowLogCommand;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisExceptionUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class ShardedJedisServerOperations extends AbstractServerOperations<ShardedJedis, ShardedJedisPipeline> {

	public ShardedJedisServerOperations(final ShardedJedisClient client){
		super(client);
	}

	@Override
	public String bgRewriteAof(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.BGREWRITEAOF, client.getConnection());
		return null;
	}

	@Override
	public String bgSave(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.BGSAVE, client.getConnection());
		return null;
	}

	@Override
	public Status clientKill(final String host, final int port){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CLIENT_KILL, client.getConnection());
		return null;
	}

	@Override
	public String clientGetName(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CLIENT_GETNAME, client.getConnection());
		return null;
	}

	@Override
	public List<Client> clientList(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CLIENT_LIST, client.getConnection());
		return null;
	}

	@Override
	public Status clientPause(final long timeout){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CLIENT_PAUSE, client.getConnection());
		return null;
	}

	@Override
	public Status clientSetName(final String name){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CLIENT_SETNAME, client.getConnection());
		return null;
	}

	@Override
	public Status clientSetName(final byte[] name){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CLIENT_SETNAME, client.getConnection());
		return null;
	}

	@Override
	public List<String> configGet(final String parameter){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CONFIG_GET, client.getConnection());
		return null;
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CONFIG_GET, client.getConnection());
		return null;
	}

	@Override
	public Status configResetStat(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CONFIG_RESETSTAT, client.getConnection());
		return null;
	}

	@Override
	public Status configRewrite(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CONFIG_REWRITE, client.getConnection());
		return null;
	}

	@Override
	public Status configSet(final String parameter, final String value){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CONFIG_SET, client.getConnection());
		return null;
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.CONFIG_SET, client.getConnection());
		return null;
	}

	@Override
	public Long dbSize(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.DBSIZE, client.getConnection());
		return null;
	}

	@Override
	public String debugObject(final String key){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.DEBUG_OBJECT, client.getConnection());
		return null;
	}

	@Override
	public byte[] debugObject(final byte[] key){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.DEBUG_OBJECT, client.getConnection());
		return null;
	}

	@Override
	public String debugSegfault(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.DEBUG_SEGFAULT, client.getConnection());
		return null;
	}

	@Override
	public Status flushAll(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.FLUSHALL, client.getConnection());
		return null;
	}

	@Override
	public Status flushDb(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.FLUSHDB, client.getConnection());
		return null;
	}

	@Override
	public Info info(final Info.Section section){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.INFO, client.getConnection());
		return null;
	}

	@Override
	public Info info(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.INFO, client.getConnection());
		return null;
	}

	@Override
	public Long lastSave(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.LASTSAVE, client.getConnection());
		return null;
	}

	@Override
	public String memoryDoctor(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.MEMORY_DOCTOR, client.getConnection());
		return null;
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.MONITOR, client.getConnection());
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
	public Status save(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SAVE, client.getConnection());
		return null;
	}

	@Override
	public void shutdown(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SHUTDOWN, client.getConnection());
	}

	@Override
	public Status slaveOf(final String host, final int port){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SLAVEOF, client.getConnection());
		return null;
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final String... arguments){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SLOWLOG, client.getConnection());
		return null;
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final byte[]... arguments){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SLOWLOG, client.getConnection());
		return null;
	}

	@Override
	public Object sync(){
		if(isPipeline()){
			return transactionExecute((cmd)->{
				getPipeline().sync();
				return null;
			});
		}

		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SLOWLOG, client.getConnection());
		return null;
	}

	@Override
	public RedisServerTime time(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.TIME, client.getConnection());
		return null;
	}

}
