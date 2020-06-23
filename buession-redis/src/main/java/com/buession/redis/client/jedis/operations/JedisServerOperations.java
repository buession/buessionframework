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

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.Info;
import com.buession.redis.core.InfoSection;
import com.buession.redis.core.ObjectCommand;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.SlowLogCommand;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.utils.ClientUtil;
import com.buession.redis.utils.InfoUtil;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.DebugParams;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;
import redis.clients.jedis.Pipeline;

import java.util.Collections;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class JedisServerOperations extends AbstractServerOperations<Jedis, Pipeline> {

	public JedisServerOperations(final JedisRedisClient<Jedis> client){
		super(client);
	}

	@Override
	public String bgRewriteAof(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bgrewriteaof()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bgrewriteaof()));
		}else{
			return execute((cmd)->cmd.bgrewriteaof());
		}
	}

	@Override
	public String bgSave(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bgsave()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bgsave()));
		}else{
			return execute((cmd)->cmd.bgsave());
		}
	}

	@Override
	public Status clientKill(final String host, final int port){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.CLIENT_KILL);
		return execute((cmd)->ReturnUtils.statusForOK(cmd.clientKill(host + ":" + port)));
	}

	@Override
	public String clientGetName(){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.CLIENT_GETNAME);
		return execute((cmd)->cmd.clientGetname());
	}

	@Override
	public List<Client> clientList(){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.CLIENT_LIST);
		return execute((cmd)->ClientUtil.parse(cmd.clientList()));
	}

	@Override
	public Status clientPause(final long timeout){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.CLIENT_PAUSE);
		return execute((cmd)->ReturnUtils.statusForOK(cmd.clientPause(timeout)));
	}

	@Override
	public Status clientSetName(final String name){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.CLIENT_SETNAME);
		return execute((cmd)->ReturnUtils.statusForOK(cmd.clientSetname(name)));
	}

	@Override
	public Status clientSetName(final byte[] name){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.CLIENT_SETNAME);
		return execute((cmd)->ReturnUtils.statusForOK(cmd.clientSetname(name)));
	}

	@Override
	public List<String> configGet(final String parameter){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().configGet(parameter),
					STRING_TO_BINARY_LIST_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().configGet(parameter),
					STRING_TO_BINARY_LIST_CONVERTER));
		}else{
			return execute((cmd)->Collections.unmodifiableList(cmd.configGet(parameter)));
		}
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().configGet(SafeEncoder.encode(parameter))));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().configGet(SafeEncoder.encode(parameter)),
					STRING_TO_BINARY_LIST_CONVERTER));
		}else{
			return execute((cmd)->cmd.configGet(parameter));
		}
	}

	@Override
	public Status configResetStat(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().configResetStat()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().configResetStat()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.configResetStat()));
		}
	}

	@Override
	public Status configRewrite(){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.CONFIG_REWRITE);
		return execute((cmd)->ReturnUtils.statusForOK(cmd.configRewrite()));
	}

	@Override
	public Status configSet(final String parameter, final String value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().configSet(parameter, value),
					OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().configSet(parameter, value),
					OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.configSet(parameter, value)));
		}
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().configSet(SafeEncoder.encode(parameter),
					SafeEncoder.encode(value)), OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().configSet(SafeEncoder.encode(parameter),
					SafeEncoder.encode(value)), OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.configSet(parameter, value)));
		}
	}

	@Override
	public Long dbSize(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().dbSize()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().dbSize()));
		}else{
			return execute((cmd)->cmd.dbSize());
		}
	}

	@Override
	public String debugObject(final String key){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.DEBUG_OBJECT);
		return execute((cmd)->cmd.debug(DebugParams.OBJECT(key)));
	}

	@Override
	public byte[] debugObject(final byte[] key){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.DEBUG_OBJECT);
		return execute((cmd)->SafeEncoder.encode(cmd.debug(DebugParams.OBJECT(SafeEncoder.encode(key)))));
	}

	@Override
	public String debugSegfault(){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.DEBUG_SEGFAULT);
		return execute((cmd)->cmd.debug(DebugParams.SEGFAULT()));
	}

	@Override
	public Status flushAll(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().flushAll(), OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().flushAll(), OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.flushAll()));
		}
	}

	@Override
	public Status flushDb(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().flushDB(), OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().flushDB(), OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.flushDB()));
		}
	}

	@Override
	public Info info(final InfoSection section){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().info(section.name().toLowerCase()),
					JedisConverters.infoConvert()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().info(section.name().toLowerCase()),
					JedisConverters.infoConvert()));
		}else{
			return execute((cmd)->InfoUtil.convert(cmd.info(section.name().toLowerCase())));
		}
	}

	@Override
	public Info info(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().info(), JedisConverters.infoConvert()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().info(), JedisConverters.infoConvert()));
		}else{
			return execute((cmd)->InfoUtil.convert(cmd.info()));
		}
	}

	@Override
	public Long lastSave(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lastsave()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lastsave()));
		}else{
			return execute((cmd)->cmd.lastsave());
		}
	}

	@Override
	public String memoryDoctor(){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.MEMORY_DOCTOR);
		return execute((cmd)->cmd.memoryDoctor());
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.MONITOR);
		execute((cmd)->{
			cmd.monitor(new JedisMonitor() {

				@Override
				public void onCommand(final String command){
					redisMonitor.onCommand(command);
				}
			});
			return null;
		});
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
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().save(), OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().save(), OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.save()));
		}
	}

	@Override
	public void shutdown(){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SHUTDOWN);
		execute((cmd)->{
			cmd.shutdown();
			return null;
		});
	}

	@Override
	public Status slaveOf(final String host, final int port){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SLAVEOF);
		return execute((cmd)->ReturnUtils.statusForOK(cmd.slaveof(host, port)));
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final String... arguments){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SLOWLOG);
		return execute((cmd)->JedisClientUtils.slowLog(command, cmd, arguments));
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final byte[]... arguments){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SLOWLOG);
		return execute((cmd)->JedisClientUtils.slowLog(command, cmd, arguments));
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
			return execute((cmd)->{
				cmd.sync();
				return null;
			});
		}
	}

	@Override
	public RedisServerTime time(){
		if(isPipeline()){
			return transactionExecute((cmd)->newJedisResult(getPipeline().time(),
					JedisConverters.redisServerTimeConvert()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().time(),
					JedisConverters.redisServerTimeConvert()));
		}else{
			return execute((cmd)->ReturnUtils.redisServerTime(cmd.time()));
		}
	}

}
