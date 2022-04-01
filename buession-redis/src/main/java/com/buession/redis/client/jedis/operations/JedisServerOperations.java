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

import com.buession.core.converter.ListConverter;
import com.buession.core.converter.PredicateStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.Client;
import com.buession.redis.core.Constants;
import com.buession.redis.core.Info;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.SlowLogCommand;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.InfoConverter;
import com.buession.redis.core.internal.convert.OkStatusConverter;
import com.buession.redis.core.internal.convert.RedisServerTimeConverter;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisExceptionUtils;
import com.buession.redis.utils.ClientUtil;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;

import java.util.Collections;
import java.util.List;

/**
 * Jedis 哨兵模式服务端命令操作
 *
 * @author Yong.Teng
 */
public final class JedisServerOperations extends AbstractServerOperations<Jedis> {

	public JedisServerOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public List<String> aclCat(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL);
		}else{
			return execute((cmd)->cmd.aclCat(), ProtocolCommand.ACL);
		}
	}

	@Override
	public List<String> aclCat(final String categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL, args);
		}else{
			return execute((cmd)->cmd.aclCat(categoryName), ProtocolCommand.ACL, args);
		}
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL, args);
		}else{
			return execute((cmd)->cmd.aclCat(categoryName), ProtocolCommand.ACL, args);
		}
	}

	@Override
	public Status aclSetUser(final String username, final String... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", rules);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL, args);
		}else{
			return execute((cmd)->cmd.aclSetUser(username, rules), Converters.OK_STATUS_CONVERTER, ProtocolCommand.ACL,
					args);
		}
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", rules);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL, args);
		}else{
			return execute((cmd)->cmd.aclSetUser(username, rules), Converters.OK_STATUS_CONVERTER, ProtocolCommand.ACL,
					args);
		}
	}

	@Override
	public List<String> aclUsers(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL);
		}else{
			return execute((cmd)->cmd.aclUsers(), ProtocolCommand.ACL);
		}
	}

	@Override
	public String aclWhoAmI(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL);
		}else{
			return execute((cmd)->cmd.aclWhoAmI(), ProtocolCommand.ACL);
		}
	}

	@Override
	public AclUser aclGetUser(final String username){
		final CommandArguments args = CommandArguments.create("username", username);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL, args);
		}else{
			return execute((cmd)->cmd.aclGetUser(username), USER_EXPOSE_CONVERTER, ProtocolCommand.ACL, args);
		}
	}

	@Override
	public AclUser aclGetUser(final byte[] username){
		final CommandArguments args = CommandArguments.create("username", username);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL, args);
		}else{
			return execute((cmd)->cmd.aclGetUser(username), USER_EXPOSE_CONVERTER, ProtocolCommand.ACL, args);
		}
	}

	@Override
	public Status aclDelUser(final String username){
		final CommandArguments args = CommandArguments.create("username", username);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL, args);
		}else{
			return execute((cmd)->cmd.aclDelUser(username) >= 1 ? Status.SUCCESS : Status.FAILURE, ProtocolCommand.ACL,
					args);
		}
	}

	@Override
	public Status aclDelUser(final byte[] username){
		final CommandArguments args = CommandArguments.create("username", username);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL, args);
		}else{
			return execute((cmd)->cmd.aclDelUser(username) >= 1 ? Status.SUCCESS : Status.FAILURE, ProtocolCommand.ACL,
					args);
		}
	}

	@Override
	public String aclGenPass(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL);
		}else{
			return execute((cmd)->cmd.aclGenPass(), ProtocolCommand.ACL);
		}
	}

	@Override
	public List<String> aclList(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL);
		}else{
			return execute((cmd)->cmd.aclList(), ProtocolCommand.ACL);
		}
	}

	@Override
	public Status aclLoad(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL);
		}else{
			return execute((cmd)->cmd.aclLoad(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.ACL);
		}
	}

	@Override
	public List<AclLog> aclLog(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL);
		}else{
			return execute((cmd)->cmd.aclLog(), LIST_ACL_LOG_EXPOSE_CONVERTER, ProtocolCommand.ACL);
		}
	}

	@Override
	public List<AclLog> aclLog(final long count){
		final CommandArguments args = CommandArguments.create("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL, args);
		}else{
			return execute((cmd)->cmd.aclLog(), LIST_ACL_LOG_EXPOSE_CONVERTER, ProtocolCommand.ACL, args);
		}
	}

	@Override
	public Status aclLogReset(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ACL);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ACL);
		}else{
			return execute((cmd)->cmd.aclLogReset(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.ACL);
		}
	}

	@Override
	public Status aclLogSave(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL);
	}

	@Override
	public String bgRewriteAof(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bgrewriteaof()), ProtocolCommand.BGREWRITEAOF);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bgrewriteaof()),
					ProtocolCommand.BGREWRITEAOF);
		}else{
			return execute((cmd)->cmd.bgrewriteaof(), ProtocolCommand.BGREWRITEAOF);
		}
	}

	@Override
	public String bgSave(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bgrewriteaof()), ProtocolCommand.BGSAVE);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bgrewriteaof()), ProtocolCommand.BGSAVE);
		}else{
			return execute((cmd)->cmd.bgsave(), ProtocolCommand.BGSAVE);
		}
	}

	@Override
	public Status configSet(final String parameter, final String value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().configSet(parameter, value), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.CONFIG_SET, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().configSet(parameter, value), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.CONFIG_SET, args);
		}else{
			return execute((cmd)->cmd.configSet(parameter, value), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CONFIG_SET);
		}
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(
							getPipeline().configSet(SafeEncoder.encode(parameter), SafeEncoder.encode(value)),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.CONFIG_SET, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(
							getTransaction().configSet(SafeEncoder.encode(parameter), SafeEncoder.encode(value)),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.CONFIG_SET, args);
		}else{
			return execute((cmd)->cmd.configSet(parameter, value), Converters.BINARY_OK_STATUS_CONVERTER,
					ProtocolCommand.CONFIG_SET);
		}
	}

	@Override
	public List<String> configGet(final String parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().configGet(parameter)),
					ProtocolCommand.CONFIG_GET, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().configGet(parameter)),
					ProtocolCommand.CONFIG_GET, args);
		}else{
			return execute((cmd)->cmd.configGet(parameter), ProtocolCommand.CONFIG_GET, args);
		}
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().configGet(SafeEncoder.encode(parameter))),
					ProtocolCommand.CONFIG_GET, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().configGet(SafeEncoder.encode(parameter))),
					ProtocolCommand.CONFIG_GET, args);
		}else{
			return execute((cmd)->cmd.configGet(parameter), ProtocolCommand.CONFIG_GET, args);
		}
	}

	@Override
	public Status configResetStat(){
		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().configResetStat(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.CONFIG_RESETSTAT);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().configResetStat(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.CONFIG_RESETSTAT);
		}else{
			return execute((cmd)->cmd.configResetStat(), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CONFIG_RESETSTAT);
		}
	}

	@Override
	public Status configRewrite(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CONFIG_REWRITE);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CONFIG_REWRITE);
		}else{
			return execute((cmd)->cmd.configRewrite(), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CONFIG_REWRITE);
		}
	}

	@Override
	public Long dbSize(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().dbSize()), ProtocolCommand.DBSIZE);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().dbSize()), ProtocolCommand.DBSIZE);
		}else{
			return execute((cmd)->cmd.dbSize(), ProtocolCommand.DBSIZE);
		}
	}

	@Override
	public Status clientKill(final String host, final int port){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.CLIENT_KILL,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.clientKill(host + ":" + port), new OkStatusConverter());
	}

	@Override
	public String clientGetName(){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.CLIENT_GETNAME,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.clientGetname());
	}

	@Override
	public List<Client> clientList(){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.CLIENT_LIST,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->ClientUtil.parse(cmd.clientList()));
	}

	@Override
	public Status clientPause(final int timeout){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.CLIENT_PAUSE,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.clientPause(timeout), new OkStatusConverter());
	}

	@Override
	public Status clientSetName(final String name){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.CLIENT_SETNAME,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.clientSetname(name), new OkStatusConverter());
	}

	@Override
	public Status clientSetName(final byte[] name){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.CLIENT_SETNAME,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.clientSetname(name), new OkStatusConverter());
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
		final InfoConverter converter = new InfoConverter();
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().info(section.name().toLowerCase()),
					converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().info(section.name().toLowerCase()),
					converter));
		}else{
			return execute((cmd)->cmd.info(section.name().toLowerCase()), converter);
		}
	}

	@Override
	public Info info(){
		final InfoConverter converter = new InfoConverter();
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().info(), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().info(), converter));
		}else{
			return execute((cmd)->cmd.info(), converter);
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
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.MEMORY_DOCTOR,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.memoryDoctor());
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.MONITOR,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
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
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SHUTDOWN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		execute((cmd)->{
			cmd.shutdown();
			return null;
		});
	}

	@Override
	public Status slaveOf(final String host, final int port){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SLAVEOF,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.slaveof(host, port), new OkStatusConverter());
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final String... arguments){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SLOWLOG,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->JedisClientUtils.slowLog(command, cmd, arguments));
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final byte[]... arguments){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SLOWLOG,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
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
