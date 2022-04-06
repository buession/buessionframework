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
import com.buession.redis.core.AclLog;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.Info;
import com.buession.redis.core.MemoryStats;
import com.buession.redis.core.Module;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;
import redis.clients.jedis.args.SaveMode;
import redis.clients.jedis.params.FailoverParams;

import java.util.List;

/**
 * Jedis 哨兵模式服务端命令操作
 *
 * @author Yong.Teng
 */
public final class JedisSentinelServerOperations extends AbstractServerOperations<Jedis> {

	public JedisSentinelServerOperations(final JedisSentinelClient client){
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
	public Status failover(final byte[] host, final int port){
		return failover(SafeEncoder.encode(host), port);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("timeout", timeout);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.FAILOVER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.FAILOVER, args);
		}else{
			final FailoverParams failoverParams = FailoverParams.failoverParams().to(host, port).timeout(timeout);
			return execute((cmd)->cmd.failover(failoverParams), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.FAILOVER, args);
		}
	}

	@Override
	public Status failover(final byte[] host, final int port, final int timeout){
		return failover(SafeEncoder.encode(host), port, timeout);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.FAILOVER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.FAILOVER, args);
		}else{
			final FailoverParams failoverParams = FailoverParams.failoverParams().to(host, port).timeout(timeout);

			if(isForce){
				failoverParams.force();
			}

			return execute((cmd)->cmd.failover(failoverParams), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.FAILOVER, args);
		}
	}

	@Override
	public Status failover(final byte[] host, final int port, final boolean isForce, final int timeout){
		return failover(SafeEncoder.encode(host), port, isForce, timeout);
	}

	@Override
	public Status failover(final int timeout){
		final CommandArguments args = CommandArguments.create("timeout", timeout);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.FAILOVER, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.FAILOVER, args);
		}else{
			final FailoverParams failoverParams = FailoverParams.failoverParams().timeout(timeout);
			return execute((cmd)->cmd.failover(failoverParams), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.FAILOVER, args);
		}
	}

	@Override
	public Status flushAll(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().flushAll(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.FLUSHALL);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().flushAll(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.FLUSHALL);
		}else{
			return execute((cmd)->cmd.flushAll(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.FLUSHALL);
		}
	}

	@Override
	public Status flushAll(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		final redis.clients.jedis.args.FlushMode flushMode = FLUSH_MODE_JEDIS_CONVERTER.convert(mode);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().flushAll(flushMode), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.FLUSHALL, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().flushAll(flushMode), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.FLUSHALL, args);
		}else{
			return execute((cmd)->cmd.flushAll(flushMode), Converters.OK_STATUS_CONVERTER, ProtocolCommand.FLUSHALL,
					args);
		}
	}

	@Override
	public Status flushDb(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().flushDB(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.FLUSHDB);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().flushDB(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.FLUSHDB);
		}else{
			return execute((cmd)->cmd.flushDB(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.FLUSHDB);
		}
	}

	@Override
	public Status flushDb(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		final redis.clients.jedis.args.FlushMode flushMode = FLUSH_MODE_JEDIS_CONVERTER.convert(mode);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().flushDB(flushMode), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.FLUSHDB, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().flushDB(flushMode), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.FLUSHDB, args);
		}else{
			return execute((cmd)->cmd.flushDB(flushMode), Converters.OK_STATUS_CONVERTER, ProtocolCommand.FLUSHDB,
					args);
		}
	}

	@Override
	public Info info(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().info(), Converters.INFO_CONVERTER),
					ProtocolCommand.INFO);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().info(), Converters.INFO_CONVERTER),
					ProtocolCommand.INFO);
		}else{
			return execute((cmd)->cmd.info(), Converters.INFO_CONVERTER, ProtocolCommand.INFO);
		}
	}

	@Override
	public Info info(final Info.Section section){
		final CommandArguments args = CommandArguments.create("section", section);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().info(section.name().toLowerCase()),
					Converters.INFO_CONVERTER), ProtocolCommand.INFO, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().info(section.name().toLowerCase()),
					Converters.INFO_CONVERTER), ProtocolCommand.INFO, args);
		}else{
			return execute((cmd)->cmd.info(section.name().toLowerCase()), Converters.INFO_CONVERTER,
					ProtocolCommand.INFO, args);
		}
	}

	@Override
	public Long lastSave(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lastsave()), ProtocolCommand.LASTSAVE);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lastsave()), ProtocolCommand.LASTSAVE);
		}else{
			return execute((cmd)->cmd.lastsave(), ProtocolCommand.LASTSAVE);
		}
	}

	@Override
	public String memoryDoctor(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.MEMORY_DOCTOR);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.MEMORY_DOCTOR);
		}else{
			return execute((cmd)->cmd.memoryDoctor(), ProtocolCommand.MEMORY_DOCTOR);
		}
	}

	@Override
	public String memoryMallocStats(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.MEMORY);
	}

	@Override
	public Status memoryPurge(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.MEMORY);
	}

	@Override
	public MemoryStats memoryStats(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.MEMORY);
	}

	@Override
	public Long memoryUsage(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.MEMORY, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.MEMORY, args);
		}else{
			return execute((cmd)->cmd.memoryUsage(key), ProtocolCommand.MEMORY, args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.MEMORY, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.MEMORY, args);
		}else{
			return execute((cmd)->cmd.memoryUsage(key), ProtocolCommand.MEMORY, args);
		}
	}

	@Override
	public Long memoryUsage(final String key, final int samples){
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.MEMORY, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.MEMORY, args);
		}else{
			return execute((cmd)->cmd.memoryUsage(key, samples), ProtocolCommand.MEMORY, args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples){
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.MEMORY, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.MEMORY, args);
		}else{
			return execute((cmd)->cmd.memoryUsage(key, samples), ProtocolCommand.MEMORY, args);
		}
	}

	@Override
	public List<Module> moduleList(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().moduleList(), LIST_MODULE_EXPOSE_CONVERTER),
					ProtocolCommand.MODULE);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().moduleList(), LIST_MODULE_EXPOSE_CONVERTER),
					ProtocolCommand.MODULE);
		}else{
			return execute((cmd)->cmd.moduleList(), LIST_MODULE_EXPOSE_CONVERTER, ProtocolCommand.MODULE);
		}
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments){
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", arguments);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().moduleLoad(path), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.MODULE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().moduleLoad(path), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.MODULE, args);
		}else{
			return execute((cmd)->cmd.moduleLoad(path), Converters.OK_STATUS_CONVERTER, ProtocolCommand.MODULE, args);
		}
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments){
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", arguments);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().moduleLoad(SafeEncoder.encode(path)),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MODULE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().moduleLoad(SafeEncoder.encode(path)),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MODULE, args);
		}else{
			return execute((cmd)->cmd.moduleLoad(SafeEncoder.encode(path)), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.MODULE, args);
		}
	}

	@Override
	public Status moduleUnLoad(final String name){
		final CommandArguments args = CommandArguments.create("name", name);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().moduleUnload(name), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.MODULE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().moduleUnload(name), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.MODULE, args);
		}else{
			return execute((cmd)->cmd.moduleUnload(name), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.MODULE, args);
		}
	}

	@Override
	public Status moduleUnLoad(final byte[] name){
		return moduleUnLoad(SafeEncoder.encode(name));
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		final CommandArguments args = CommandArguments.create("redisMonitor", redisMonitor);

		if(isPipeline()){
			execute(CommandNotSupported.PIPELINE, ProtocolCommand.MONITOR, args);
		}else if(isTransaction()){
			execute(CommandNotSupported.TRANSACTION, ProtocolCommand.MONITOR, args);
		}else{
			execute((cmd)->{
				cmd.monitor(new JedisMonitor() {

					@Override
					public void onCommand(final String command){
						redisMonitor.onCommand(command);
					}

				});
				return null;
			}, ProtocolCommand.MONITOR, args);
		}
	}

	@Override
	public Object pSync(final String replicationId, final long offset){
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return execute(CommandNotSupported.ALL, ProtocolCommand.PSYNC, args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset){
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return execute(CommandNotSupported.ALL, ProtocolCommand.PSYNC, args);
	}

	@Override
	public void sync(){
		if(isPipeline()){
			transactionExecute((cmd)->{
				getPipeline().sync();
				return null;
			}, ProtocolCommand.SYNC);
		}else if(isTransaction()){
			execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SYNC);
		}else{
			execute((cmd)->{
				cmd.sync();
				return null;
			}, ProtocolCommand.SYNC);
		}
	}

	@Override
	public Status replicaOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return execute(CommandNotSupported.ALL, ProtocolCommand.REPLICAOF, args);
	}

	@Override
	public Status slaveOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SLAVEOF, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SLAVEOF, args);
		}else{
			return execute((cmd)->cmd.slaveof(host, port), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.SLAVEOF, args);
		}
	}

	@Override
	public Role role(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ROLE);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ROLE);
		}else{
			return execute((cmd)->cmd.role(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.ROLE);
		}
	}

	@Override
	public Status save(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().save(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SAVE);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().save(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SAVE);
		}else{
			return execute((cmd)->cmd.save(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.SAVE);
		}
	}

	@Override
	public Status shutdown(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().shutdown(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SHUTDOWN);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().shutdown(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SHUTDOWN);
		}else{
			return execute((cmd)->cmd.shutdown(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.SHUTDOWN);
		}
	}

	@Override
	public void shutdown(final boolean save){
		final CommandArguments args = CommandArguments.create("save", save);
		final SaveMode saveMode = save == true ? SaveMode.SAVE : SaveMode.NOSAVE;

		if(isPipeline()){
			pipelineExecute((cmd)->newJedisResult(getPipeline().shutdown(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SHUTDOWN, args);
		}else if(isTransaction()){
			transactionExecute(
					(cmd)->newJedisResult(getTransaction().shutdown(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SHUTDOWN, args);
		}else{
			execute((cmd)->{
				cmd.shutdown(saveMode);
				return null;
			}, ProtocolCommand.SHUTDOWN, args);
		}
	}

	@Override
	public List<SlowLog> slowLogGet(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SLOWLOG);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SLOWLOG);
		}else{
			return execute((cmd)->cmd.slowlogGet(), LIST_SLOW_LOG_EXPOSE_CONVERTER, ProtocolCommand.SLOWLOG);
		}
	}

	@Override
	public List<SlowLog> slowLogGet(final long count){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SLOWLOG);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SLOWLOG);
		}else{
			return execute((cmd)->cmd.slowlogGet(count), LIST_SLOW_LOG_EXPOSE_CONVERTER, ProtocolCommand.SLOWLOG);
		}
	}

	@Override
	public Long slowLogLen(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SLOWLOG);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SLOWLOG);
		}else{
			return execute((cmd)->cmd.slowlogLen(), ProtocolCommand.SLOWLOG);
		}
	}

	@Override
	public Status slowLogReset(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SLOWLOG);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SLOWLOG);
		}else{
			return execute((cmd)->cmd.slowlogReset(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.SLOWLOG);
		}
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().swapDB(db1, db2), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SWAPDB, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().swapDB(db1, db2), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SWAPDB, args);
		}else{
			return execute((cmd)->cmd.swapDB(db1, db2), Converters.OK_STATUS_CONVERTER, ProtocolCommand.SWAPDB, args);
		}
	}

	@Override
	public RedisServerTime time(){
		if(isPipeline()){
			return transactionExecute((cmd)->newJedisResult(getPipeline().time(), REDIS_SERVER_TIME_CONVERTER),
					ProtocolCommand.TIME);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().time(), REDIS_SERVER_TIME_CONVERTER),
					ProtocolCommand.TIME);
		}else{
			return execute((cmd)->cmd.time(), REDIS_SERVER_TIME_CONVERTER, ProtocolCommand.TIME);
		}
	}

}
