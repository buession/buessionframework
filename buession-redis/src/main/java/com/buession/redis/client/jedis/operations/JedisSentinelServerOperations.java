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

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.PredicateStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.Constants;
import com.buession.redis.core.Info;
import com.buession.redis.core.ObjectCommand;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.SlowLogCommand;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.OkStatusConverter;
import com.buession.redis.core.convert.RedisServerTimeConverter;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisExceptionUtils;
import com.buession.redis.utils.ClientUtil;
import com.buession.redis.utils.InfoUtil;
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
public class JedisClusterServerOperations extends AbstractServerOperations<Jedis, Pipeline> {

	public JedisClusterServerOperations(final JedisClusterClient client){
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
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.CLIENT_KILL,
				client.getConnection());
		return execute((cmd)->cmd.clientKill(host + ":" + port), new OkStatusConverter());
	}

	@Override
	public String clientGetName(){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.CLIENT_GETNAME,
				client.getConnection());
		return execute((cmd)->cmd.clientGetname());
	}

	@Override
	public List<Client> clientList(){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.CLIENT_LIST,
				client.getConnection());
		return execute((cmd)->ClientUtil.parse(cmd.clientList()));
	}

	@Override
	public Status clientPause(final long timeout){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.CLIENT_PAUSE,
				client.getConnection());
		return execute((cmd)->cmd.clientPause(timeout), new OkStatusConverter());
	}

	@Override
	public Status clientSetName(final String name){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.CLIENT_SETNAME,
				client.getConnection());
		return execute((cmd)->cmd.clientSetname(name), new OkStatusConverter());
	}

	@Override
	public Status clientSetName(final byte[] name){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.CLIENT_SETNAME,
				client.getConnection());
		return execute((cmd)->cmd.clientSetname(name), new OkStatusConverter());
	}

	@Override
	public List<String> configGet(final String parameter){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().configGet(parameter),
					new ListConverter<>((value)->SafeEncoder.encode(value))));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().configGet(parameter),
					new ListConverter<>((value)->SafeEncoder.encode(value))));
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
					new ListConverter<>((value)->SafeEncoder.encode(value))));
		}else{
			return execute((cmd)->cmd.configGet(parameter));
		}
	}

	@Override
	public Status configResetStat(){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().configResetStat(), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().configResetStat(), converter));
		}else{
			return execute((cmd)->cmd.configResetStat(), converter);
		}
	}

	@Override
	public Status configRewrite(){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.CONFIG_REWRITE,
				client.getConnection());
		return execute((cmd)->cmd.configRewrite(), new OkStatusConverter());
	}

	@Override
	public Status configSet(final String parameter, final String value){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().configSet(parameter, value), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().configSet(parameter, value), converter));
		}else{
			return execute((cmd)->cmd.configSet(parameter, value), converter);
		}
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		if(isPipeline()){
			final OkStatusConverter converter = new OkStatusConverter();

			return pipelineExecute((cmd)->newJedisResult(getPipeline().configSet(SafeEncoder.encode(parameter),
					SafeEncoder.encode(value)), converter));
		}else if(isTransaction()){
			final OkStatusConverter converter = new OkStatusConverter();

			return transactionExecute((cmd)->newJedisResult(getTransaction().configSet(SafeEncoder.encode(parameter),
					SafeEncoder.encode(value)), converter));
		}else{
			final PredicateStatusConverter<byte[]> converter =
					new PredicateStatusConverter<>((val)->Constants.OK_BINARY == val);

			return execute((cmd)->cmd.configSet(parameter, value), converter);
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
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.DEBUG_OBJECT,
				client.getConnection());
		return execute((cmd)->cmd.debug(DebugParams.OBJECT(key)));
	}

	@Override
	public byte[] debugObject(final byte[] key){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.DEBUG_OBJECT,
				client.getConnection());
		return execute((cmd)->SafeEncoder.encode(cmd.debug(DebugParams.OBJECT(SafeEncoder.encode(key)))));
	}

	@Override
	public String debugSegfault(){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.DEBUG_SEGFAULT,
				client.getConnection());
		return execute((cmd)->cmd.debug(DebugParams.SEGFAULT()));
	}

	@Override
	public Status flushAll(){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().flushAll(), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().flushAll(), converter));
		}else{
			return execute((cmd)->cmd.flushAll(), converter);
		}
	}

	@Override
	public Status flushDb(){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().flushDB(), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().flushDB(), converter));
		}else{
			return execute((cmd)->cmd.flushDB(), converter);
		}
	}

	@Override
	public Info info(final Info.Section section){
		Converter<String, Info> infoConverter = (source)->InfoUtil.convert(source);
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().info(section.name().toLowerCase()),
					infoConverter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().info(section.name().toLowerCase()),
					infoConverter));
		}else{
			return execute((cmd)->cmd.info(section.name().toLowerCase()), infoConverter);
		}
	}

	@Override
	public Info info(){
		Converter<String, Info> infoConverter = (source)->InfoUtil.convert(source);
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().info(), infoConverter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().info(), infoConverter));
		}else{
			return execute((cmd)->cmd.info(), infoConverter);
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
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.MEMORY_DOCTOR,
				client.getConnection());
		return execute((cmd)->cmd.memoryDoctor());
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.MONITOR,
				client.getConnection());
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
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().save(), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().save(), converter));
		}else{
			return execute((cmd)->cmd.save(), converter);
		}
	}

	@Override
	public void shutdown(){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.SHUTDOWN,
				client.getConnection());
		execute((cmd)->{
			cmd.shutdown();
			return null;
		});
	}

	@Override
	public Status slaveOf(final String host, final int port){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.SLAVEOF,
				client.getConnection());
		return execute((cmd)->cmd.slaveof(host, port), new OkStatusConverter());
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final String... arguments){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.SLOWLOG,
				client.getConnection());
		return execute((cmd)->JedisClientUtils.slowLog(command, cmd, arguments));
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final byte[]... arguments){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.SLOWLOG,
				client.getConnection());
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
		final RedisServerTimeConverter converter = new RedisServerTimeConverter();

		if(isPipeline()){
			return transactionExecute((cmd)->newJedisResult(getPipeline().time(), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().time(), converter));
		}else{
			return execute((cmd)->cmd.time(), converter);
		}
	}

}