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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.collect.Arrays;
import com.buession.lang.Status;
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
import com.buession.redis.core.internal.convert.jedis.response.AccessControlLogEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.AccessControlUserConverter;
import com.buession.redis.core.internal.convert.jedis.params.FlushModeConverter;
import com.buession.redis.core.internal.convert.jedis.response.InfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.ModuleConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.jedis.response.RedisServerTimeConverter;
import com.buession.redis.core.internal.convert.jedis.response.SlowlogConverter;
import com.buession.redis.core.internal.convert.jedis.response.RoleConverter;
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
public final class JedisServerOperations extends AbstractServerOperations<JedisStandaloneClient> {

	public JedisServerOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public List<String> aclCat(){
		return new JedisCommand<List<String>>(client, ProtocolCommand.ACL_CAT)
				.general((cmd)->cmd.aclCat())
				.run();
	}

	@Override
	public List<String> aclCat(final String categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		return new JedisCommand<List<String>>(client, ProtocolCommand.ACL_CAT)
				.general((cmd)->cmd.aclCat(categoryName))
				.run(args);
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		return new JedisCommand<List<byte[]>>(client, ProtocolCommand.ACL_CAT)
				.general((cmd)->cmd.aclCat(categoryName))
				.run(args);
	}

	@Override
	public Status aclSetUser(final String username, final String... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		return new JedisCommand<Status>(client, ProtocolCommand.ACL_SETUSER)
				.general((cmd)->cmd.aclSetUser(username, rules), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		return new JedisCommand<Status>(client, ProtocolCommand.ACL_SETUSER)
				.general((cmd)->cmd.aclSetUser(username, rules), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public AclUser aclGetUser(final String username){
		final CommandArguments args = CommandArguments.create("username", username);
		return new JedisCommand<AclUser>(client, ProtocolCommand.ACL_GETUSER)
				.general((cmd)->cmd.aclGetUser(username), AccessControlUserConverter.INSTANCE)
				.run(args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username){
		final CommandArguments args = CommandArguments.create("username", username);
		return new JedisCommand<AclUser>(client, ProtocolCommand.ACL_GETUSER)
				.general((cmd)->cmd.aclGetUser(username), AccessControlUserConverter.INSTANCE)
				.run(args);
	}

	@Override
	public List<String> aclUsers(){
		return new JedisCommand<List<String>>(client, ProtocolCommand.ACL_USERS)
				.general((cmd)->cmd.aclUsers())
				.run();
	}

	@Override
	public String aclWhoAmI(){
		return new JedisCommand<String>(client, ProtocolCommand.ACL_WHOAMI)
				.general((cmd)->cmd.aclWhoAmI())
				.run();
	}

	@Override
	public Long aclDelUser(final String... usernames){
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return new JedisCommand<Long>(client, ProtocolCommand.ACL_DELUSER)
				.general((cmd)->{
					if(usernames.length > 1){
						return cmd.aclDelUser(usernames[0], Arrays.subarray(usernames, 1, usernames.length - 1));
					}else{
						return cmd.aclDelUser(usernames[0]);
					}
				})
				.run(args);
	}

	@Override
	public Long aclDelUser(final byte[]... usernames){
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return new JedisCommand<Long>(client, ProtocolCommand.ACL_DELUSER)
				.general((cmd)->{
					if(usernames.length > 1){
						return cmd.aclDelUser(usernames[0], Arrays.subarray(usernames, 1, usernames.length - 1));
					}else{
						return cmd.aclDelUser(usernames[0]);
					}
				})
				.run(args);
	}

	@Override
	public String aclGenPass(){
		return new JedisCommand<String>(client, ProtocolCommand.ACL_GENPASS)
				.general((cmd)->cmd.aclGenPass())
				.run();
	}

	@Override
	public List<String> aclList(){
		return new JedisCommand<List<String>>(client, ProtocolCommand.ACL_LIST)
				.general((cmd)->cmd.aclList())
				.run();
	}

	@Override
	public Status aclLoad(){
		return new JedisCommand<Status>(client, ProtocolCommand.ACL_LOAD)
				.general((cmd)->cmd.aclLoad(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public List<AclLog> aclLog(){
		return new JedisCommand<List<AclLog>>(client, ProtocolCommand.ACL_LOG)
				.general((cmd)->cmd.aclLog(), AccessControlLogEntryConverter.LIST_CONVERTER)
				.run();
	}

	@Override
	public List<AclLog> aclLog(final long count){
		final CommandArguments args = CommandArguments.create("count", count);
		return new JedisCommand<List<AclLog>>(client, ProtocolCommand.ACL_LOG)
				.general((cmd)->cmd.aclLog((int) count), AccessControlLogEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public Status aclLogReset(){
		return new JedisCommand<Status>(client, ProtocolCommand.ACL_LOGREST)
				.general((cmd)->cmd.aclLogReset(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status aclLogSave(){
		return new JedisCommand<Status>(client, ProtocolCommand.ACL_LOGSAVE)
				.run();
	}

	@Override
	public String bgRewriteAof(){
		return new JedisCommand<String>(client, ProtocolCommand.BGREWRITEAOF)
				.general((cmd)->cmd.bgrewriteaof())
				.run();
	}

	@Override
	public String bgSave(){
		return new JedisCommand<String>(client, ProtocolCommand.BGREWRITEAOF)
				.general((cmd)->cmd.bgsave())
				.run();
	}

	@Override
	public Status configSet(final String parameter, final String value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		return new JedisCommand<Status>(client, ProtocolCommand.CONFIG_SET)
				.general((cmd)->cmd.configSet(parameter, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		return new JedisCommand<Status>(client, ProtocolCommand.CONFIG_SET)
				.general((cmd)->cmd.configSet(parameter, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public List<String> configGet(final String parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		return new JedisCommand<List<String>>(client, ProtocolCommand.CONFIG_GET)
				.general((cmd)->cmd.configGet(parameter))
				.run(args);
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		return new JedisCommand<List<byte[]>>(client, ProtocolCommand.CONFIG_GET)
				.general((cmd)->cmd.configGet(parameter))
				.run(args);
	}

	@Override
	public Status configResetStat(){
		return new JedisCommand<Status>(client, ProtocolCommand.CONFIG_RESETSTAT)
				.general((cmd)->cmd.configResetStat(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status configRewrite(){
		return new JedisCommand<Status>(client, ProtocolCommand.CONFIG_REWRITE)
				.general((cmd)->cmd.configRewrite(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Long dbSize(){
		return new JedisCommand<Long>(client, ProtocolCommand.DBSIZE)
				.general((cmd)->cmd.dbSize()).pipeline((cmd)->cmd.dbSize())
				.run();
	}

	@Override
	public Status failover(){
		return new JedisCommand<Status>(client, ProtocolCommand.FAILOVER)
				.general((cmd)->cmd.failover(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status failover(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final JedisFailoverParams params = new JedisFailoverParams(host, port);
		return new JedisCommand<Status>(client, ProtocolCommand.FAILOVER)
				.general((cmd)->cmd.failover(params), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("timeout", timeout);
		final JedisFailoverParams params = new JedisFailoverParams(host, port, timeout);
		return new JedisCommand<Status>(client, ProtocolCommand.FAILOVER)
				.general((cmd)->cmd.failover(params), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);
		final JedisFailoverParams params = new JedisFailoverParams(host, port, timeout, isForce);
		return new JedisCommand<Status>(client, ProtocolCommand.FAILOVER)
				.general((cmd)->cmd.failover(params), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status failover(final int timeout){
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		final JedisFailoverParams params = new JedisFailoverParams(timeout);
		return new JedisCommand<Status>(client, ProtocolCommand.FAILOVER)
				.general((cmd)->cmd.failover(params), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status flushAll(){
		return new JedisCommand<Status>(client, ProtocolCommand.FLUSHALL)
				.general((cmd)->cmd.flushAll(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status flushAll(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		return new JedisCommand<Status>(client, ProtocolCommand.FLUSHALL)
				.general((cmd)->cmd.flushAll(FlushModeConverter.INSTANCE.convert(mode)), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status flushDb(){
		return new JedisCommand<Status>(client, ProtocolCommand.FLUSHDB)
				.general((cmd)->cmd.flushDB(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status flushDb(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		return new JedisCommand<Status>(client, ProtocolCommand.FLUSHDB)
				.general((cmd)->cmd.flushDB(FlushModeConverter.INSTANCE.convert(mode)), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Info info(){
		return new JedisCommand<Info>(client, ProtocolCommand.INFO)
				.general((cmd)->cmd.info(), InfoConverter.INSTANCE)
				.run();
	}

	@Override
	public Info info(final Info.Section section){
		final CommandArguments args = CommandArguments.create("section", section);
		return new JedisCommand<Info>(client, ProtocolCommand.INFO)
				.general((cmd)->cmd.info(section.name().toLowerCase()), InfoConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Long lastSave(){
		return new JedisCommand<Long>(client, ProtocolCommand.LASTSAVE)
				.general((cmd)->cmd.lastsave())
				.run();
	}

	@Override
	public String memoryDoctor(){
		return new JedisCommand<String>(client, ProtocolCommand.MEMORY_DOCTOR)
				.general((cmd)->cmd.memoryDoctor())
				.run();
	}

	@Override
	public Status memoryPurge(){
		return new JedisCommand<Status>(client, ProtocolCommand.MEMORY_PURGE)
				.run();
	}

	@Override
	public MemoryStats memoryStats(){
		return new JedisCommand<MemoryStats>(client, ProtocolCommand.MEMORY_STATS)
				.run();
	}

	@Override
	public Long memoryUsage(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key))
				.pipeline((cmd)->cmd.memoryUsage(key))
				.transaction((cmd)->cmd.memoryUsage(key))
				.run(args);
	}

	@Override
	public Long memoryUsage(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key))
				.pipeline((cmd)->cmd.memoryUsage(key))
				.transaction((cmd)->cmd.memoryUsage(key))
				.run(args);
	}

	@Override
	public Long memoryUsage(final String key, final int samples){
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);
		return new JedisCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key, samples))
				.pipeline((cmd)->cmd.memoryUsage(key, samples))
				.transaction((cmd)->cmd.memoryUsage(key, samples))
				.run(args);
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples){
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);
		return new JedisCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key, samples))
				.pipeline((cmd)->cmd.memoryUsage(key, samples))
				.transaction((cmd)->cmd.memoryUsage(key, samples))
				.run(args);
	}

	@Override
	public List<Module> moduleList(){
		return new JedisCommand<List<Module>>(client, ProtocolCommand.MODULE_LIST)
				.general((cmd)->cmd.moduleList(), ModuleConverter.LIST_CONVERTER)
				.run();
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments){
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", (Object[]) arguments);
		return new JedisCommand<Status>(client, ProtocolCommand.MODULE_LOAD)
				.general((cmd)->cmd.moduleLoad(path, arguments), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status moduleUnLoad(final String name){
		final CommandArguments args = CommandArguments.create("name", name);
		return new JedisCommand<Status>(client, ProtocolCommand.MODULE_UNLOAD)
				.general((cmd)->cmd.moduleUnload(name), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		final CommandArguments args = CommandArguments.create("redisMonitor", redisMonitor);
		new JedisCommand<Status>(client, ProtocolCommand.MONITOR)
				.general((cmd)->{
					cmd.monitor(new JedisMonitor() {

						@Override
						public void onCommand(final String command){
							redisMonitor.onCommand(command);
						}

					});
					return null;
				})
				.run(args);
	}

	@Override
	public Object pSync(final String replicationId, final long offset){
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return new JedisCommand<>(client, ProtocolCommand.PSYNC)
				.run(args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset){
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return new JedisCommand<>(client, ProtocolCommand.PSYNC)
				.run(args);
	}

	@Override
	public void sync(){
		new JedisCommand<Void>(client, ProtocolCommand.SYNC)
				.pipeline((cmd)->{
					cmd.sync();
					return null;
				})
				.run();
	}

	@Override
	public Status replicaOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new JedisCommand<Status>(client, ProtocolCommand.REPLICAOF)
				.general((cmd)->cmd.replicaof(host, port), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status slaveOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new JedisCommand<Status>(client, ProtocolCommand.SLAVEOF)
				.general((cmd)->cmd.slaveof(host, port), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public List<Role> role(){
		return new JedisCommand<List<Role>>(client, ProtocolCommand.ROLE)
				.general((cmd)->cmd.role(), RoleConverter.LIST_CONVERTER)
				.run();
	}

	@Override
	public Status save(){
		return new JedisCommand<Status>(client, ProtocolCommand.SAVE)
				.general((cmd)->cmd.save(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public void shutdown(){
		new JedisCommand<Void>(client, ProtocolCommand.SHUTDOWN)
				.general((cmd)->{
					cmd.shutdown();
					return null;
				})
				.run();
	}

	@Override
	public void shutdown(final boolean save){
		final CommandArguments args = CommandArguments.create("save", save);
		new JedisCommand<Void>(client, ProtocolCommand.SHUTDOWN)
				.general((cmd)->{
					final SaveMode saveMode = save ? SaveMode.SAVE : SaveMode.NOSAVE;
					cmd.shutdown(saveMode);
					return null;
				})
				.run(args);
	}

	@Override
	public List<SlowLog> slowLogGet(){
		return new JedisCommand<List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
				.general((cmd)->cmd.slowlogGet(), SlowlogConverter.LIST_CONVERTER)
				.run();
	}

	@Override
	public List<SlowLog> slowLogGet(final long count){
		final CommandArguments args = CommandArguments.create("count", count);
		return new JedisCommand<List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
				.general((cmd)->cmd.slowlogGet(count), SlowlogConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public Long slowLogLen(){
		return new JedisCommand<Long>(client, ProtocolCommand.SLOWLOG_LEN)
				.general((cmd)->cmd.slowlogLen())
				.run();
	}

	@Override
	public Status slowLogReset(){
		return new JedisCommand<Status>(client, ProtocolCommand.SLOWLOG_RESET)
				.general((cmd)->cmd.slowlogReset(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);
		return new JedisCommand<Status>(client, ProtocolCommand.SWAPDB)
				.general((cmd)->cmd.swapDB(db1, db2), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.swapDB(db1, db2), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public RedisServerTime time(){
		return new JedisCommand<RedisServerTime>(client, ProtocolCommand.TIME)
				.general((cmd)->cmd.time(), RedisServerTimeConverter.INSTANCE)
				.pipeline((cmd)->cmd.time(), RedisServerTimeConverter.INSTANCE)
				.run();
	}

}
