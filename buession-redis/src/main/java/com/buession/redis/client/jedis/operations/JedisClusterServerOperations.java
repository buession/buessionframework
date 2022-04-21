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
import com.buession.redis.client.jedis.JedisClusterClient;
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

import java.util.List;

/**
 * Jedis 集群模式服务端命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterServerOperations extends AbstractServerOperations<JedisClusterClient> {

	public JedisClusterServerOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public List<String> aclCat(){
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_CAT);
		return execute(command);
	}

	@Override
	public List<String> aclCat(final String categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_CAT);
		return execute(command, args);
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_CAT);
		return execute(command, args);
	}

	@Override
	public Status aclSetUser(final String username, final String... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", rules);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_SETUSER);
		return execute(command, args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", rules);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_SETUSER);
		return execute(command, args);
	}

	@Override
	public AclUser aclGetUser(final String username){
		final CommandArguments args = CommandArguments.create("username", username);
		final JedisClusterCommand<AclUser> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_GETUSER);
		return execute(command, args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username){
		final CommandArguments args = CommandArguments.create("username", username);
		final JedisClusterCommand<AclUser> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_GETUSER);
		return execute(command, args);
	}

	@Override
	public List<String> aclUsers(){
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_USERS);
		return execute(command);
	}

	@Override
	public String aclWhoAmI(){
		final JedisClusterCommand<String> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_WHOAMI);
		return execute(command);
	}

	@Override
	public long aclDelUser(final String... usernames){
		final CommandArguments args = CommandArguments.create("usernames", usernames);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_DELUSER);
		return execute(command, args);
	}

	@Override
	public long aclDelUser(final byte[]... usernames){
		final CommandArguments args = CommandArguments.create("usernames", usernames);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_DELUSER);
		return execute(command, args);
	}

	@Override
	public String aclGenPass(){
		final JedisClusterCommand<String> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_GENPASS);
		return execute(command);
	}

	@Override
	public List<String> aclList(){
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_LIST);
		return execute(command);
	}

	@Override
	public Status aclLoad(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_LOAD);
		return execute(command);
	}

	@Override
	public List<AclLog> aclLog(){
		final JedisClusterCommand<List<AclLog>> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_LOG);
		return execute(command);
	}

	@Override
	public List<AclLog> aclLog(final long count){
		final CommandArguments args = CommandArguments.create("count", count);
		final JedisClusterCommand<List<AclLog>> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_LOG);
		return execute(command, args);
	}

	@Override
	public Status aclLogReset(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_LOGREST);
		return execute(command);
	}

	@Override
	public Status aclLogSave(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.ACL_LOGSAVE);
		return execute(command);
	}

	@Override
	public String bgRewriteAof(){
		final JedisClusterCommand<String> command = new JedisClusterCommand<>(client, ProtocolCommand.BGREWRITEAOF);
		return execute(command);
	}

	@Override
	public String bgSave(){
		final JedisClusterCommand<String> command = new JedisClusterCommand<>(client, ProtocolCommand.BGREWRITEAOF);
		return execute(command);
	}

	@Override
	public Status configSet(final String parameter, final String value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.CONFIG_SET);
		return execute(command, args);
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.CONFIG_SET);
		return execute(command, args);
	}

	@Override
	public List<String> configGet(final String parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<>(client, ProtocolCommand.CONFIG_GET);
		return execute(command, args);
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<>(client, ProtocolCommand.CONFIG_GET);
		return execute(command, args);
	}

	@Override
	public Status configResetStat(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.CONFIG_RESETSTAT);
		return execute(command);
	}

	@Override
	public Status configRewrite(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.CONFIG_REWRITE);
		return execute(command);
	}

	@Override
	public long dbSize(){
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.DBSIZE)
				.general((cmd)->cmd.dbSize())
				.pipeline((cmd)->cmd.dbSize());
		return execute(command);
	}

	@Override
	public Status failover(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.FAILOVER);
		return execute(command);
	}

	@Override
	public Status failover(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.FAILOVER);
		return execute(command, args);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("timeout", timeout);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.FAILOVER);
		return execute(command, args);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.FAILOVER);
		return execute(command, args);
	}

	@Override
	public Status failover(final int timeout){
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.FAILOVER);
		return execute(command, args);
	}

	@Override
	public Status flushAll(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.FLUSHALL);
		return execute(command);
	}

	@Override
	public Status flushAll(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.FLUSHALL);
		return execute(command, args);
	}

	@Override
	public Status flushDb(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.FLUSHDB);
		return execute(command);
	}

	@Override
	public Status flushDb(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.FLUSHDB);
		return execute(command, args);
	}

	@Override
	public Info info(){
		final JedisClusterCommand<Info> command = new JedisClusterCommand<>(client, ProtocolCommand.INFO);
		return execute(command);
	}

	@Override
	public Info info(final Info.Section section){
		final CommandArguments args = CommandArguments.create("section", section);
		final JedisClusterCommand<Info> command = new JedisClusterCommand<>(client, ProtocolCommand.INFO);
		return execute(command, args);
	}

	@Override
	public long lastSave(){
		final JedisClusterCommand<Long> command = new JedisClusterCommand<>(client, ProtocolCommand.LASTSAVE);
		return execute(command);
	}

	@Override
	public String memoryDoctor(){
		final JedisClusterCommand<String> command = new JedisClusterCommand<>(client, ProtocolCommand.MEMORY_DOCTOR);
		return execute(command);
	}

	@Override
	public Status memoryPurge(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.MEMORY_PURGE);
		return execute(command);
	}

	@Override
	public MemoryStats memoryStats(){
		final JedisClusterCommand<MemoryStats> command = new JedisClusterCommand<>(client,
				ProtocolCommand.MEMORY_STATS);
		return execute(command);
	}

	@Override
	public long memoryUsage(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key))
				.pipeline((cmd)->cmd.memoryUsage(key))
				.transaction((cmd)->cmd.memoryUsage(key));
		return execute(command, args);
	}

	@Override
	public long memoryUsage(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key))
				.pipeline((cmd)->cmd.memoryUsage(key))
				.transaction((cmd)->cmd.memoryUsage(key));
		return execute(command, args);
	}

	@Override
	public long memoryUsage(final String key, final int samples){
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key, samples))
				.pipeline((cmd)->cmd.memoryUsage(key, samples))
				.transaction((cmd)->cmd.memoryUsage(key, samples));
		return execute(command, args);
	}

	@Override
	public long memoryUsage(final byte[] key, final int samples){
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key, samples))
				.pipeline((cmd)->cmd.memoryUsage(key, samples))
				.transaction((cmd)->cmd.memoryUsage(key, samples));
		return execute(command, args);
	}

	@Override
	public List<Module> moduleList(){
		final JedisClusterCommand<List<Module>> command = new JedisClusterCommand<>(client,
				ProtocolCommand.MODULE_LIST);
		return execute(command);
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments){
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", arguments);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.MODULE_LOAD);
		return execute(command, args);
	}

	@Override
	public Status moduleUnLoad(final String name){
		final CommandArguments args = CommandArguments.create("name", name);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.MODULE_UNLOAD);
		return execute(command, args);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		final CommandArguments args = CommandArguments.create("redisMonitor", redisMonitor);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.MONITOR);
		execute(command, args);
	}

	@Override
	public Object pSync(final String replicationId, final long offset){
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.PSYNC);
		return execute(command, args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset){
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.PSYNC);
		return execute(command, args);
	}

	@Override
	public void sync(){
		final JedisClusterCommand<Void> command = new JedisClusterCommand<Void>(client, ProtocolCommand.SYNC)
				.pipeline((cmd)->{
					cmd.sync();
					return null;
				});
		execute(command);
	}

	@Override
	public Status replicaOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.REPLICAOF);
		return execute(command, args);
	}

	@Override
	public Status slaveOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.SLAVEOF);
		return execute(command, args);
	}

	@Override
	public List<Role> role(){
		final JedisClusterCommand<List<Role>> command = new JedisClusterCommand<>(client, ProtocolCommand.ROLE);
		return execute(command);
	}

	@Override
	public Status save(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.SAVE);
		return execute(command);
	}

	@Override
	public void shutdown(){
		final JedisClusterCommand<Void> command = new JedisClusterCommand<>(client, ProtocolCommand.SHUTDOWN);
		execute(command);
	}

	@Override
	public void shutdown(final boolean save){
		final CommandArguments args = CommandArguments.create("save", save);
		final JedisClusterCommand<Void> command = new JedisClusterCommand<>(client, ProtocolCommand.SHUTDOWN);
		execute(command, args);
	}

	@Override
	public List<SlowLog> slowLogGet(){
		final JedisClusterCommand<List<SlowLog>> command = new JedisClusterCommand<>(client,
				ProtocolCommand.SLOWLOG_GET);
		return execute(command);
	}

	@Override
	public List<SlowLog> slowLogGet(final long count){
		final CommandArguments args = CommandArguments.create("count", count);
		final JedisClusterCommand<List<SlowLog>> command = new JedisClusterCommand<>(client,
				ProtocolCommand.SLOWLOG_GET);
		return execute(command, args);
	}

	@Override
	public long slowLogLen(){
		final JedisClusterCommand<Long> command = new JedisClusterCommand<>(client, ProtocolCommand.SLOWLOG_LEN);
		return execute(command);
	}

	@Override
	public Status slowLogReset(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.SLOWLOG_RESET);
		return execute(command);
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<>(client, ProtocolCommand.SWAPDB);
		return execute(command, args);
	}

	@Override
	public RedisServerTime time(){
		final JedisClusterCommand<RedisServerTime> command = new JedisClusterCommand<>(client, ProtocolCommand.TIME);
		return execute(command);
	}

}
