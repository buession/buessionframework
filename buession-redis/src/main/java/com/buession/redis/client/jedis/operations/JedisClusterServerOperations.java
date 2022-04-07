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
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * Jedis 集群模式服务端命令操作
 *
 * @author Yong.Teng
 */
public final class JedisClusterServerOperations extends AbstractServerOperations<JedisCluster> {

	public JedisClusterServerOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public List<String> aclCat(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_CAT);
	}

	@Override
	public List<String> aclCat(final String categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_CAT, args);
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName){
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_CAT, args);
	}

	@Override
	public Status aclSetUser(final String username, final String... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", rules);
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_SETUSER, args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules){
		final CommandArguments args = CommandArguments.create("username", username).put("rules", rules);
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_SETUSER, args);
	}

	@Override
	public AclUser aclGetUser(final String username){
		final CommandArguments args = CommandArguments.create("username", username);
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_GETUSER, args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username){
		final CommandArguments args = CommandArguments.create("username", username);
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_GETUSER, args);
	}

	@Override
	public List<String> aclUsers(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_USERS);
	}

	@Override
	public String aclWhoAmI(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_WHOAMI);
	}

	@Override
	public Status aclDelUser(final String username){
		final CommandArguments args = CommandArguments.create("username", username);
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_DELUSER, args);
	}

	@Override
	public Status aclDelUser(final byte[] username){
		final CommandArguments args = CommandArguments.create("username", username);
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_DELUSER, args);
	}

	@Override
	public String aclGenPass(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_GENPASS);
	}

	@Override
	public List<String> aclList(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_LIST);
	}

	@Override
	public Status aclLoad(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_LOAD);
	}

	@Override
	public List<AclLog> aclLog(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_LOG);
	}

	@Override
	public List<AclLog> aclLog(final long count){
		final CommandArguments args = CommandArguments.create("count", count);
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_LOG, args);
	}

	@Override
	public Status aclLogReset(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_LOGREST);
	}

	@Override
	public Status aclLogSave(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ACL_LOGSAVE);
	}

	@Override
	public String bgRewriteAof(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.BGREWRITEAOF);
	}

	@Override
	public String bgSave(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.BGSAVE);
	}

	@Override
	public Status configSet(final String parameter, final String value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CONFIG_SET, args);
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CONFIG_SET, args);
	}

	@Override
	public List<String> configGet(final String parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CONFIG_GET, args);
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CONFIG_GET, args);
	}

	@Override
	public Status configResetStat(){
		return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CONFIG_RESETSTAT);
	}

	@Override
	public Status configRewrite(){
		return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CONFIG_REWRITE);
	}

	@Override
	public Long dbSize(){
		return execute(CommandNotSupported.PIPELINE, ProtocolCommand.DBSIZE);
	}

	@Override
	public Status failover(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.FAILOVER);
	}

	@Override
	public Status failover(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return execute(CommandNotSupported.ALL, ProtocolCommand.FAILOVER, args);
	}

	@Override
	public Status failover(final byte[] host, final int port){
		return failover(SafeEncoder.encode(host), port);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("timeout", timeout);
		return execute(CommandNotSupported.ALL, ProtocolCommand.FAILOVER, args);
	}

	@Override
	public Status failover(final byte[] host, final int port, final int timeout){
		return failover(SafeEncoder.encode(host), port, timeout);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);
		return execute(CommandNotSupported.ALL, ProtocolCommand.FAILOVER, args);
	}

	@Override
	public Status failover(final byte[] host, final int port, final boolean isForce, final int timeout){
		return failover(SafeEncoder.encode(host), port, isForce, timeout);
	}

	@Override
	public Status failover(final int timeout){
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		return execute(CommandNotSupported.ALL, ProtocolCommand.FAILOVER, args);
	}

	@Override
	public Status flushAll(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.FLUSHALL);
	}

	@Override
	public Status flushAll(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		return execute(CommandNotSupported.ALL, ProtocolCommand.FLUSHALL, args);
	}

	@Override
	public Status flushDb(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.FLUSHDB);
	}

	@Override
	public Status flushDb(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		return execute(CommandNotSupported.ALL, ProtocolCommand.FLUSHDB, args);
	}

	@Override
	public Info info(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.INFO);
	}

	@Override
	public Info info(final Info.Section section){
		final CommandArguments args = CommandArguments.create("section", section);
		return execute(CommandNotSupported.ALL, ProtocolCommand.INFO, args);
	}

	@Override
	public Long lastSave(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.LASTSAVE);
	}

	@Override
	public String memoryDoctor(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.MEMORY_DOCTOR);
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
		return execute(CommandNotSupported.ALL, ProtocolCommand.MODULE_LIST);
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments){
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", arguments);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments){
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", arguments);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleUnLoad(final String name){
		final CommandArguments args = CommandArguments.create("name", name);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MODULE_UNLOAD, args);
	}

	@Override
	public Status moduleUnLoad(final byte[] name){
		final CommandArguments args = CommandArguments.create("name", name);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MODULE_UNLOAD, args);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		final CommandArguments args = CommandArguments.create("redisMonitor", redisMonitor);
		execute(CommandNotSupported.ALL, ProtocolCommand.MONITOR, args);
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
		execute(CommandNotSupported.ALL, ProtocolCommand.SYNC);
	}

	@Override
	public Status replicaOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return execute(CommandNotSupported.ALL, ProtocolCommand.REPLICAOF, args);
	}

	@Override
	public Status slaveOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return execute(CommandNotSupported.ALL, ProtocolCommand.SLAVEOF, args);
	}

	@Override
	public Role role(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.ROLE);
	}

	@Override
	public Status save(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.SAVE);
	}

	@Override
	public void shutdown(){
		execute(CommandNotSupported.ALL, ProtocolCommand.SHUTDOWN);
	}

	@Override
	public void shutdown(final boolean save){
		final CommandArguments args = CommandArguments.create("save", save);
		execute(CommandNotSupported.ALL, ProtocolCommand.SHUTDOWN, args);
	}

	@Override
	public List<SlowLog> slowLogGet(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.SLOWLOG);
	}

	@Override
	public List<SlowLog> slowLogGet(final long count){
		final CommandArguments args = CommandArguments.create("count", count);
		return execute(CommandNotSupported.ALL, ProtocolCommand.SLOWLOG, args);
	}

	@Override
	public Long slowLogLen(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.SLOWLOG);
	}

	@Override
	public Status slowLogReset(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.SLOWLOG);
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);
		return execute(CommandNotSupported.ALL, ProtocolCommand.SWAPDB, args);
	}

	@Override
	public RedisServerTime time(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.TIME);
	}

}
