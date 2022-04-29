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
		return new JedisClusterCommand<List<String>>(client, ProtocolCommand.ACL_CAT)
				.run();
	}

	@Override
	public List<String> aclCat(final String categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		return new JedisClusterCommand<List<String>>(client, ProtocolCommand.ACL_CAT)
				.run(args);
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		return new JedisClusterCommand<List<byte[]>>(client, ProtocolCommand.ACL_CAT)
				.run(args);
	}

	@Override
	public Status aclSetUser(final String username, final String... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", rules);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.ACL_SETUSER)
				.run(args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", rules);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.ACL_SETUSER)
				.run(args);
	}

	@Override
	public AclUser aclGetUser(final String username){
		final CommandArguments args = CommandArguments.create("username", username);
		return new JedisClusterCommand<AclUser>(client, ProtocolCommand.ACL_GETUSER)
				.run(args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username){
		final CommandArguments args = CommandArguments.create("username", username);
		return new JedisClusterCommand<AclUser>(client, ProtocolCommand.ACL_GETUSER)
				.run(args);
	}

	@Override
	public List<String> aclUsers(){
		return new JedisClusterCommand<List<String>>(client, ProtocolCommand.ACL_USERS)
				.run();
	}

	@Override
	public String aclWhoAmI(){
		return new JedisClusterCommand<String>(client, ProtocolCommand.ACL_WHOAMI)
				.run();
	}

	@Override
	public Long aclDelUser(final String... usernames){
		final CommandArguments args = CommandArguments.create("usernames", usernames);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.ACL_DELUSER)
				.run(args);
	}

	@Override
	public Long aclDelUser(final byte[]... usernames){
		final CommandArguments args = CommandArguments.create("usernames", usernames);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.ACL_DELUSER)
				.run(args);
	}

	@Override
	public String aclGenPass(){
		return new JedisClusterCommand<String>(client, ProtocolCommand.ACL_GENPASS)
				.run();
	}

	@Override
	public List<String> aclList(){
		return new JedisClusterCommand<List<String>>(client, ProtocolCommand.ACL_LIST)
				.run();
	}

	@Override
	public Status aclLoad(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.ACL_LOAD)
				.run();
	}

	@Override
	public List<AclLog> aclLog(){
		return new JedisClusterCommand<List<AclLog>>(client, ProtocolCommand.ACL_LOG)
				.run();
	}

	@Override
	public List<AclLog> aclLog(final long count){
		final CommandArguments args = CommandArguments.create("count", count);
		return new JedisClusterCommand<List<AclLog>>(client, ProtocolCommand.ACL_LOG)
				.run(args);
	}

	@Override
	public Status aclLogReset(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.ACL_LOGREST)
				.run();
	}

	@Override
	public Status aclLogSave(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.ACL_LOGSAVE)
				.run();
	}

	@Override
	public String bgRewriteAof(){
		return new JedisClusterCommand<String>(client, ProtocolCommand.BGREWRITEAOF)
				.run();
	}

	@Override
	public String bgSave(){
		return new JedisClusterCommand<String>(client, ProtocolCommand.BGREWRITEAOF)
				.run();
	}

	@Override
	public Status configSet(final String parameter, final String value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CONFIG_SET)
				.run(args);
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CONFIG_SET)
				.run(args);
	}

	@Override
	public List<String> configGet(final String parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		return new JedisClusterCommand<List<String>>(client, ProtocolCommand.CONFIG_GET)
				.run(args);
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		return new JedisClusterCommand<List<byte[]>>(client, ProtocolCommand.CONFIG_GET)
				.run(args);
	}

	@Override
	public Status configResetStat(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CONFIG_RESETSTAT)
				.run();
	}

	@Override
	public Status configRewrite(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CONFIG_REWRITE)
				.run();
	}

	@Override
	public Long dbSize(){
		return new JedisClusterCommand<Long>(client, ProtocolCommand.DBSIZE)
				.general((cmd)->cmd.dbSize())
				.pipeline((cmd)->cmd.dbSize())
				.run();
	}

	@Override
	public Status failover(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.FAILOVER)
				.run();
	}

	@Override
	public Status failover(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.FAILOVER)
				.run(args);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("timeout", timeout);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.FAILOVER)
				.run(args);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.FAILOVER)
				.run(args);
	}

	@Override
	public Status failover(final int timeout){
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.FAILOVER)
				.run(args);
	}

	@Override
	public Status flushAll(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.FLUSHALL)
				.run();
	}

	@Override
	public Status flushAll(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.FLUSHALL)
				.run(args);
	}

	@Override
	public Status flushDb(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.FLUSHDB)
				.run();
	}

	@Override
	public Status flushDb(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.FLUSHDB)
				.run(args);
	}

	@Override
	public Info info(){
		return new JedisClusterCommand<Info>(client, ProtocolCommand.INFO)
				.run();
	}

	@Override
	public Info info(final Info.Section section){
		final CommandArguments args = CommandArguments.create("section", section);
		return new JedisClusterCommand<Info>(client, ProtocolCommand.INFO)
				.run(args);
	}

	@Override
	public Long lastSave(){
		return new JedisClusterCommand<Long>(client, ProtocolCommand.LASTSAVE)
				.run();
	}

	@Override
	public String memoryDoctor(){
		return new JedisClusterCommand<String>(client, ProtocolCommand.MEMORY_DOCTOR)
				.run();
	}

	@Override
	public Status memoryPurge(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MEMORY_PURGE)
				.run();
	}

	@Override
	public MemoryStats memoryStats(){
		return new JedisClusterCommand<MemoryStats>(client, ProtocolCommand.MEMORY_STATS)
				.run();
	}

	@Override
	public Long memoryUsage(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key))
				.pipeline((cmd)->cmd.memoryUsage(key))
				.transaction((cmd)->cmd.memoryUsage(key))
				.run(args);
	}

	@Override
	public Long memoryUsage(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key))
				.pipeline((cmd)->cmd.memoryUsage(key))
				.transaction((cmd)->cmd.memoryUsage(key))
				.run(args);
	}

	@Override
	public Long memoryUsage(final String key, final int samples){
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key, samples))
				.pipeline((cmd)->cmd.memoryUsage(key, samples))
				.transaction((cmd)->cmd.memoryUsage(key, samples))
				.run(args);
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples){
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.MEMORY_USAGE)
				.general((cmd)->cmd.memoryUsage(key, samples))
				.pipeline((cmd)->cmd.memoryUsage(key, samples))
				.transaction((cmd)->cmd.memoryUsage(key, samples))
				.run(args);
	}

	@Override
	public List<Module> moduleList(){
		return new JedisClusterCommand<List<Module>>(client, ProtocolCommand.MODULE_LIST)
				.run();
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments){
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", arguments);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MODULE_LOAD)
				.run(args);
	}

	@Override
	public Status moduleUnLoad(final String name){
		final CommandArguments args = CommandArguments.create("name", name);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MODULE_UNLOAD)
				.run(args);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		final CommandArguments args = CommandArguments.create("redisMonitor", redisMonitor);
		new JedisClusterCommand<Void>(client, ProtocolCommand.MONITOR)
				.run(args);
	}

	@Override
	public Object pSync(final String replicationId, final long offset){
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return new JedisClusterCommand<>(client, ProtocolCommand.PSYNC)
				.run(args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset){
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return new JedisClusterCommand<>(client, ProtocolCommand.PSYNC)
				.run(args);
	}

	@Override
	public void sync(){
		new JedisClusterCommand<Void>(client, ProtocolCommand.SYNC)
				.pipeline((cmd)->{
					cmd.sync();
					return null;
				})
				.run();
	}

	@Override
	public Status replicaOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.REPLICAOF)
				.run(args);
	}

	@Override
	public Status slaveOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.SLAVEOF)
				.run(args);
	}

	@Override
	public List<Role> role(){
		return new JedisClusterCommand<List<Role>>(client, ProtocolCommand.ROLE)
				.run();
	}

	@Override
	public Status save(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.SAVE)
				.run();
	}

	@Override
	public void shutdown(){
		new JedisClusterCommand<Void>(client, ProtocolCommand.SHUTDOWN)
				.run();
	}

	@Override
	public void shutdown(final boolean save){
		final CommandArguments args = CommandArguments.create("save", save);
		new JedisClusterCommand<Void>(client, ProtocolCommand.SHUTDOWN)
				.run(args);
	}

	@Override
	public List<SlowLog> slowLogGet(){
		return new JedisClusterCommand<List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
				.run();
	}

	@Override
	public List<SlowLog> slowLogGet(final long count){
		final CommandArguments args = CommandArguments.create("count", count);
		return new JedisClusterCommand<List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
				.run(args);
	}

	@Override
	public Long slowLogLen(){
		return new JedisClusterCommand<Long>(client, ProtocolCommand.SLOWLOG_LEN)
				.run();
	}

	@Override
	public Status slowLogReset(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.SLOWLOG_RESET)
				.run();
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.SWAPDB)
				.run(args);
	}

	@Override
	public RedisServerTime time(){
		return new JedisClusterCommand<RedisServerTime>(client, ProtocolCommand.TIME)
				.run();
	}

}
