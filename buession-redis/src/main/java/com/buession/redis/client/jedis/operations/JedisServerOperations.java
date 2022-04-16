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

import com.buession.core.collect.Arrays;
import com.buession.lang.Status;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.Info;
import com.buession.redis.core.MemoryStats;
import com.buession.redis.core.Module;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.JedisConverters;
import com.buession.redis.core.internal.jedis.JedisFailoverParams;
import redis.clients.jedis.JedisMonitor;
import redis.clients.jedis.args.SaveMode;

import java.util.List;

/**
 * Jedis 单机模式服务端命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisServerOperations extends AbstractServerOperations<JedisConnection> {

	public JedisServerOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public List<String> aclCat(){
		final JedisCommand<List<String>> command = JedisCommand.<List<String>>create(ProtocolCommand.ACL_CAT)
				.general((cmd)->cmd.aclCat());
		return execute(command);
	}

	@Override
	public List<String> aclCat(final String categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		final JedisCommand<List<String>> command = JedisCommand.<List<String>>create(ProtocolCommand.ACL_CAT)
				.general((cmd)->cmd.aclCat(categoryName));
		return execute(command, args);
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		final JedisCommand<List<byte[]>> command = JedisCommand.<List<byte[]>>create(ProtocolCommand.ACL_CAT)
				.general((cmd)->cmd.aclCat(categoryName));
		return execute(command, args);
	}

	@Override
	public Status aclSetUser(final String username, final String... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", rules);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.ACL_SETUSER)
				.general((cmd)->cmd.aclSetUser(username, rules), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", rules);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.ACL_SETUSER)
				.general((cmd)->cmd.aclSetUser(username, rules), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public AclUser aclGetUser(final String username){
		final CommandArguments args = CommandArguments.create("username", username);
		final JedisCommand<AclUser> command = JedisCommand.<AclUser>create(ProtocolCommand.ACL_GETUSER)
				.general((cmd)->cmd.aclGetUser(username), JedisConverters.ACL_USER_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username){
		final CommandArguments args = CommandArguments.create("username", username);
		final JedisCommand<AclUser> command = JedisCommand.<AclUser>create(ProtocolCommand.ACL_GETUSER)
				.general((cmd)->cmd.aclGetUser(username), JedisConverters.ACL_USER_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<String> aclUsers(){
		final JedisCommand<List<String>> command = JedisCommand.<List<String>>create(ProtocolCommand.ACL_USERS)
				.general((cmd)->cmd.aclUsers());
		return execute(command);
	}

	@Override
	public String aclWhoAmI(){
		final JedisCommand<String> command = JedisCommand.<String>create(ProtocolCommand.ACL_WHOAMI)
				.general((cmd)->cmd.aclWhoAmI());
		return execute(command);
	}

	@Override
	public long aclDelUser(final String... usernames){
		final CommandArguments args = CommandArguments.create("usernames", usernames);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.ACL_DELUSER)
				.general((cmd)->{
					if(usernames.length > 1){
						return cmd.aclDelUser(usernames[0], Arrays.subarray(usernames, 1, usernames.length - 1));
					}else{
						return cmd.aclDelUser(usernames[0]);
					}
				});
		return execute(command, args);
	}

	@Override
	public long aclDelUser(final byte[]... usernames){
		final CommandArguments args = CommandArguments.create("usernames", usernames);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.ACL_DELUSER)
				.general((cmd)->{
					if(usernames.length > 1){
						return cmd.aclDelUser(usernames[0], Arrays.subarray(usernames, 1, usernames.length - 1));
					}else{
						return cmd.aclDelUser(usernames[0]);
					}
				});
		return execute(command, args);
	}

	@Override
	public String aclGenPass(){
		final JedisCommand<String> command = JedisCommand.<String>create(ProtocolCommand.ACL_GENPASS)
				.general((cmd)->cmd.aclGenPass());
		return execute(command);
	}

	@Override
	public List<String> aclList(){
		final JedisCommand<List<String>> command = JedisCommand.<List<String>>create(ProtocolCommand.ACL_LIST)
				.general((cmd)->cmd.aclList());
		return execute(command);
	}

	@Override
	public Status aclLoad(){
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.ACL_LOAD)
				.general((cmd)->cmd.aclLoad(), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command);
	}

	@Override
	public List<AclLog> aclLog(){
		final JedisCommand<List<AclLog>> command = JedisCommand.<List<AclLog>>create(ProtocolCommand.ACL_LOG)
				.general((cmd)->cmd.aclLog(), JedisConverters.LIST_ACL_LOG_RESULT_CONVERTER);
		return execute(command);
	}

	@Override
	public List<AclLog> aclLog(final long count){
		final CommandArguments args = CommandArguments.create("count", count);
		final JedisCommand<List<AclLog>> command = JedisCommand.<List<AclLog>>create(ProtocolCommand.ACL_LOG)
				.general((cmd)->cmd.aclLog((int) count), JedisConverters.LIST_ACL_LOG_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status aclLogReset(){
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.ACL_LOGREST)
				.general((cmd)->cmd.aclLogReset(), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command);
	}

	@Override
	public Status aclLogSave(){
		final JedisCommand<Status> command = JedisCommand.create(ProtocolCommand.ACL_LOGSAVE);
		return execute(command);
	}

	@Override
	public String bgRewriteAof(){
		final JedisCommand<String> command = JedisCommand.<String>create(ProtocolCommand.BGREWRITEAOF)
				.general((cmd)->cmd.bgrewriteaof());
		return execute(command);
	}

	@Override
	public String bgSave(){
		final JedisCommand<String> command = JedisCommand.<String>create(ProtocolCommand.BGREWRITEAOF)
				.general((cmd)->cmd.bgsave());
		return execute(command);
	}

	@Override
	public Status configSet(final String parameter, final String value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.CONFIG_SET)
				.general((cmd)->cmd.configSet(parameter, value), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.CONFIG_SET)
				.general((cmd)->cmd.configSet(parameter, value), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<String> configGet(final String parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		final JedisCommand<List<String>> command = JedisCommand.<List<String>>create(ProtocolCommand.CONFIG_GET)
				.general((cmd)->cmd.configGet(parameter));
		return execute(command, args);
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		final JedisCommand<List<byte[]>> command = JedisCommand.<List<byte[]>>create(ProtocolCommand.CONFIG_GET)
				.general((cmd)->cmd.configGet(parameter));
		return execute(command, args);
	}

	@Override
	public Status configResetStat(){
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.CONFIG_RESETSTAT)
				.general((cmd)->cmd.configResetStat(), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command);
	}

	@Override
	public Status configRewrite(){
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.CONFIG_REWRITE)
				.general((cmd)->cmd.configRewrite(), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command);
	}

	@Override
	public long dbSize(){
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.DBSIZE)
				.general((cmd)->cmd.dbSize()).pipeline((cmd)->cmd.dbSize());
		return execute(command);
	}

	@Override
	public Status failover(){
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.FAILOVER)
				.general((cmd)->cmd.failover(), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command);
	}

	@Override
	public Status failover(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.FAILOVER)
				.general((cmd)->cmd.failover(new JedisFailoverParams(host, port)), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("timeout", timeout);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.FAILOVER)
				.general((cmd)->cmd.failover(new JedisFailoverParams(host, port, timeout)),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.FAILOVER)
				.general((cmd)->cmd.failover(new JedisFailoverParams(host, port, timeout, isForce)),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status failover(final int timeout){
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.FAILOVER)
				.general((cmd)->cmd.failover(new JedisFailoverParams(timeout)),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status flushAll(){
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.FLUSHALL)
				.general((cmd)->cmd.flushAll(), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command);
	}

	@Override
	public Status flushAll(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.FLUSHALL)
				.general((cmd)->cmd.flushAll(JedisConverters.FLUSH_MODE_CONVERTER.convert(mode)),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status flushDb(){
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.FLUSHDB)
				.general((cmd)->cmd.flushDB(), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command);
	}

	@Override
	public Status flushDb(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.FLUSHDB)
				.general((cmd)->cmd.flushDB(JedisConverters.FLUSH_MODE_CONVERTER.convert(mode)),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Info info(){
		final JedisCommand<Info> command = JedisCommand.<Info>create(ProtocolCommand.INFO)
				.general((cmd)->cmd.info(), JedisConverters.INFO_CONVERTER);
		return execute(command);
	}

	@Override
	public Info info(final Info.Section section){
		final CommandArguments args = CommandArguments.create("section", section);
		final JedisCommand<Info> command = JedisCommand.<Info>create(ProtocolCommand.INFO)
				.general((cmd)->cmd.info(section.name().toLowerCase()), JedisConverters.INFO_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long lastSave(){
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.LASTSAVE)
				.general((cmd)->cmd.lastsave());
		return execute(command);
	}

	@Override
	public String memoryDoctor(){
		final JedisCommand<String> command = JedisCommand.<String>create(ProtocolCommand.MEMORY_DOCTOR)
				.general((cmd)->cmd.memoryDoctor());
		return execute(command);
	}

	@Override
	public Status memoryPurge(){
		final JedisCommand<Status> command = JedisCommand.create(ProtocolCommand.MEMORY_PURGE);
		return execute(command);
	}

	@Override
	public MemoryStats memoryStats(){
		final JedisCommand<MemoryStats> command = JedisCommand.create(ProtocolCommand.MEMORY_STATS);
		return execute(command);
	}

	@Override
	public long memoryUsage(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key)).pipeline((cmd)->cmd.memoryUsage(key))
				.transaction((cmd)->cmd.memoryUsage(key));
		return execute(command, args);
	}

	@Override
	public long memoryUsage(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key)).pipeline((cmd)->cmd.memoryUsage(key))
				.transaction((cmd)->cmd.memoryUsage(key));
		return execute(command, args);
	}

	@Override
	public long memoryUsage(final String key, final int samples){
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key, samples)).pipeline((cmd)->cmd.memoryUsage(key, samples))
				.transaction((cmd)->cmd.memoryUsage(key, samples));
		return execute(command, args);
	}

	@Override
	public long memoryUsage(final byte[] key, final int samples){
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key, samples)).pipeline((cmd)->cmd.memoryUsage(key, samples))
				.transaction((cmd)->cmd.memoryUsage(key, samples));
		return execute(command, args);
	}

	@Override
	public List<Module> moduleList(){
		final JedisCommand<List<Module>> command = JedisCommand.<List<Module>>create(ProtocolCommand.MODULE_LIST)
				.general((cmd)->cmd.moduleList(), JedisConverters.LIST_MODULE_RESULT_CONVERTER);
		return execute(command);
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments){
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", arguments);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MODULE_LOAD)
				.general((cmd)->cmd.moduleLoad(path, arguments), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status moduleUnLoad(final String name){
		final CommandArguments args = CommandArguments.create("name", name);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MODULE_UNLOAD)
				.general((cmd)->cmd.moduleUnload(name), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		final CommandArguments args = CommandArguments.create("redisMonitor", redisMonitor);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MONITOR)
				.general((cmd)->{
					cmd.monitor(new JedisMonitor() {

						@Override
						public void onCommand(final String command){
							redisMonitor.onCommand(command);
						}

					});
					return null;
				});
		execute(command, args);
	}

	@Override
	public Object pSync(final String replicationId, final long offset){
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		final JedisCommand<Object> command = JedisCommand.create(ProtocolCommand.PSYNC);
		return execute(command, args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset){
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		final JedisCommand<Object> command = JedisCommand.create(ProtocolCommand.PSYNC);
		return execute(command, args);
	}

	@Override
	public void sync(){
		final JedisCommand<Void> command = JedisCommand.<Void>create(ProtocolCommand.SYNC)
				.pipeline((cmd)->{
					cmd.sync();
					return null;
				});
		execute(command);
	}

	@Override
	public Status replicaOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.REPLICAOF)
				.general((cmd)->cmd.replicaof(host, port), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status slaveOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.SLAVEOF)
				.general((cmd)->cmd.slaveof(host, port), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Role> role(){
		final JedisCommand<List<Role>> command = JedisCommand.<List<Role>>create(ProtocolCommand.ROLE)
				.general((cmd)->cmd.role(), JedisConverters.LIST_ROLE_RESULT_CONVERTER);
		return execute(command);
	}

	@Override
	public Status save(){
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.SAVE)
				.general((cmd)->cmd.save(), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command);
	}

	@Override
	public void shutdown(){
		final JedisCommand<Void> command = JedisCommand.<Void>create(ProtocolCommand.SHUTDOWN)
				.general((cmd)->{
					cmd.shutdown();
					return null;
				});
		execute(command);
	}

	@Override
	public void shutdown(final boolean save){
		final CommandArguments args = CommandArguments.create("save", save);
		final JedisCommand<Void> command = JedisCommand.<Void>create(ProtocolCommand.SHUTDOWN)
				.general((cmd)->{
					final SaveMode saveMode = save ? SaveMode.SAVE : SaveMode.NOSAVE;
					cmd.shutdown(saveMode);
					return null;
				});
		execute(command, args);
	}

	@Override
	public List<SlowLog> slowLogGet(){
		final JedisCommand<List<SlowLog>> command = JedisCommand.<List<SlowLog>>create(ProtocolCommand.SLOWLOG_GET)
				.general((cmd)->cmd.slowlogGet(), JedisConverters.LIST_SLOW_LOG_RESULT_CONVERTER);
		return execute(command);
	}

	@Override
	public List<SlowLog> slowLogGet(final long count){
		final CommandArguments args = CommandArguments.create("count", count);
		final JedisCommand<List<SlowLog>> command = JedisCommand.<List<SlowLog>>create(ProtocolCommand.SLOWLOG_GET)
				.general((cmd)->cmd.slowlogGet(count), JedisConverters.LIST_SLOW_LOG_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long slowLogLen(){
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SLOWLOG_LEN)
				.general((cmd)->cmd.slowlogLen());
		return execute(command);
	}

	@Override
	public Status slowLogReset(){
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.SLOWLOG_RESET)
				.general((cmd)->cmd.slowlogReset(), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command);
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.SWAPDB)
				.general((cmd)->cmd.swapDB(db1, db2), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.swapDB(db1, db2), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public RedisServerTime time(){
		final JedisCommand<RedisServerTime> command = JedisCommand.<RedisServerTime>create(ProtocolCommand.TIME)
				.general((cmd)->cmd.time(), JedisConverters.REDIS_SERVER_TIME_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.time(), JedisConverters.REDIS_SERVER_TIME_RESULT_CONVERTER);
		return execute(command);
	}

}
