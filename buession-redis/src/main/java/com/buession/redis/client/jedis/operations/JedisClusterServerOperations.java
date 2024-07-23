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

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.AclCategory;
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
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Jedis 集群模式服务端命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterServerOperations extends AbstractServerOperations<JedisClusterClient> {

	public JedisClusterServerOperations(final JedisClusterClient client) {
		super(client);
	}

	@Override
	public List<AclCategory> aclCat() {
		return notCommand(client, ProtocolCommand.ACL_CAT);
	}

	@Override
	public List<ProtocolCommand> aclCat(final AclCategory aclCategory) {
		final CommandArguments args = CommandArguments.create("aclCategory", aclCategory);
		return notCommand(client, ProtocolCommand.ACL_CAT, args);
	}

	@Override
	public Status aclSetUser(final String username, final String... rules) {
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		return notCommand(client, ProtocolCommand.ACL_SETUSER, args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules) {
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		return notCommand(client, ProtocolCommand.ACL_SETUSER, args);
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create("username", username);
		return notCommand(client, ProtocolCommand.ACL_GETUSER, args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		final CommandArguments args = CommandArguments.create("username", username);
		return notCommand(client, ProtocolCommand.ACL_GETUSER, args);
	}

	@Override
	public List<String> aclUsers() {
		return notCommand(client, ProtocolCommand.ACL_USERS);
	}

	@Override
	public String aclWhoAmI() {
		return notCommand(client, ProtocolCommand.ACL_WHOAMI);
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return notCommand(client, ProtocolCommand.ACL_DELUSER, args);
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return notCommand(client, ProtocolCommand.ACL_DELUSER, args);
	}

	@Override
	public String aclGenPass() {
		return notCommand(client, ProtocolCommand.ACL_GENPASS);
	}

	@Override
	public List<String> aclList() {
		return notCommand(client, ProtocolCommand.ACL_LIST);
	}

	@Override
	public Status aclLoad() {
		return notCommand(client, ProtocolCommand.ACL_LOAD);
	}

	@Override
	public List<AclLog> aclLog() {
		return notCommand(client, ProtocolCommand.ACL_LOG);
	}

	@Override
	public List<AclLog> aclLog(final int count) {
		final CommandArguments args = CommandArguments.create("count", count);
		return notCommand(client, ProtocolCommand.ACL_LOG, args);
	}

	@Override
	public Status aclLogReset() {
		return notCommand(client, ProtocolCommand.ACL_LOGREST);
	}

	@Override
	public Status aclLogSave() {
		return notCommand(client, ProtocolCommand.ACL_LOGSAVE);
	}

	@Override
	public String bgRewriteAof() {
		return notCommand(client, ProtocolCommand.BGREWRITEAOF);
	}

	@Override
	public String bgSave() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<String, String>(client, ProtocolCommand.BGSAVE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<String, String>(client, ProtocolCommand.BGSAVE)
					.run();
		}else{
			return new JedisClusterCommand<String, String>(client, ProtocolCommand.BGSAVE)
					.run();
		}
	}

	@Override
	public Status configSet(final String parameter, final String value) {
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		return configSet(parameter, value, args);
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value) {
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		final String sParameter = SafeEncoder.encode(parameter);
		final String sValue = SafeEncoder.encode(value);

		return configSet(sParameter, sValue, args);
	}

	@Override
	public Status configSet(final Map<String, String> configs) {
		final CommandArguments args = CommandArguments.create("configs", configs);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CONFIG_SET)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CONFIG_SET)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.CONFIG_SET, (cmd)->{
				configs.forEach(cmd::configSet);
				return Status.SUCCESS;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<String, String> configGet(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return notCommand(client, ProtocolCommand.CONFIG_GET, args);
	}

	@Override
	public Map<byte[], byte[]> configGet(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return notCommand(client, ProtocolCommand.CONFIG_GET, args);
	}

	@Override
	public Status configResetStat() {
		return notCommand(client, ProtocolCommand.CONFIG_RESETSTAT);
	}

	@Override
	public Status configRewrite() {
		return notCommand(client, ProtocolCommand.CONFIG_REWRITE);
	}

	@Override
	public Long dbSize() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Long, Long>(client, ProtocolCommand.DBSIZE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Long, Long>(client, ProtocolCommand.DBSIZE)
					.run();
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.DBSIZE, (cmd)->cmd.dbSize(), (v)->v)
					.run();
		}
	}

	@Override
	public Status failover() {
		return notCommand(client, ProtocolCommand.FAILOVER);
	}

	@Override
	public Status failover(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return notCommand(client, ProtocolCommand.FAILOVER, args);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("timeout", timeout);
		return notCommand(client, ProtocolCommand.FAILOVER, args);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);
		return notCommand(client, ProtocolCommand.FAILOVER, args);
	}

	@Override
	public Status failover(final int timeout) {
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		return notCommand(client, ProtocolCommand.FAILOVER, args);
	}

	@Override
	public Status flushAll() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.FLUSHALL)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.FLUSHALL)
					.run();
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.FLUSHALL, (cmd)->cmd.flushAll(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushAll(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.FLUSHALL)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.FLUSHALL)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.FLUSHALL, (cmd)->cmd.flushAll(),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status flushDb() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.FLUSHDB)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.FLUSHDB)
					.run();
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.flushDB(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushDb(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.FLUSHDB)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.FLUSHDB)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.flushDB(),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Info info() {
		return notCommand(client, ProtocolCommand.INFO);
	}

	@Override
	public Info info(final Info.Section section) {
		final CommandArguments args = CommandArguments.create("section", section);
		return notCommand(client, ProtocolCommand.INFO, args);
	}

	@Override
	public Long lastSave() {
		return notCommand(client, ProtocolCommand.LASTSAVE);
	}

	@Override
	public String memoryDoctor() {
		return notCommand(client, ProtocolCommand.MEMORY_DOCTOR);
	}

	@Override
	public Status memoryPurge() {
		return notCommand(client, ProtocolCommand.MEMORY_PURGE);
	}

	@Override
	public MemoryStats memoryStats() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<MemoryStats, MemoryStats>(client, ProtocolCommand.MEMORY_STATS)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<MemoryStats, MemoryStats>(client, ProtocolCommand.MEMORY_STATS)
					.run();
		}else{
			return new JedisClusterCommand<MemoryStats, MemoryStats>(client, ProtocolCommand.MEMORY_STATS)
					.run();
		}
	}

	@Override
	public Long memoryUsage(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final String key, final int samples) {
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key, samples),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples) {
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key, samples),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Module> moduleList() {
		return notCommand(client, ProtocolCommand.MODULE_LIST);
	}

	@Override
	public Status moduleLoad(final String path) {
		final CommandArguments args = CommandArguments.create("path", path);
		return notCommand(client, ProtocolCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoad(final byte[] path) {
		final CommandArguments args = CommandArguments.create("path", path);
		return notCommand(client, ProtocolCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments) {
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", (Object[]) arguments);
		return notCommand(client, ProtocolCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", (Object[]) arguments);
		return notCommand(client, ProtocolCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleUnLoad(final String name) {
		final CommandArguments args = CommandArguments.create("name", name);
		return notCommand(client, ProtocolCommand.MODULE_UNLOAD, args);
	}

	@Override
	public Status moduleUnLoad(final byte[] name) {
		final CommandArguments args = CommandArguments.create("name", name);
		return notCommand(client, ProtocolCommand.MODULE_UNLOAD, args);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor) {
		final CommandArguments args = CommandArguments.create("redisMonitor", redisMonitor);
		notCommand(client, ProtocolCommand.MONITOR, args);
	}

	@Override
	public Object pSync(final String replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return notCommand(client, ProtocolCommand.PSYNC, args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return notCommand(client, ProtocolCommand.PSYNC, args);
	}

	@Override
	public void sync() {
		if(isPipeline()){
			new JedisClusterPipelineCommand<>(client, ProtocolCommand.SYNC, (cmd)->{
				cmd.sync();
				return null;
			}, (v)->v)
					.run();
		}else if(isTransaction()){
			new JedisClusterTransactionCommand<>(client, ProtocolCommand.SYNC)
					.run();
		}else{
			new JedisClusterCommand<>(client, ProtocolCommand.SYNC)
					.run();
		}
	}

	@Override
	public Status replicaOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return notCommand(client, ProtocolCommand.REPLICAOF, args);
	}

	@Override
	public Status slaveOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return notCommand(client, ProtocolCommand.SLAVEOF, args);
	}

	@Override
	public Role role() {
		return notCommand(client, ProtocolCommand.ROLE);
	}

	@Override
	public Status save() {
		return notCommand(client, ProtocolCommand.SAVE);
	}

	@Override
	public void shutdown() {
		notCommand(client, ProtocolCommand.SHUTDOWN);
	}

	@Override
	public void shutdown(final boolean save) {
		final CommandArguments args = CommandArguments.create("save", save);
		notCommand(client, ProtocolCommand.SHUTDOWN, args);
	}

	@Override
	public List<SlowLog> slowLogGet() {
		return notCommand(client, ProtocolCommand.SLOWLOG_GET);
	}

	@Override
	public List<SlowLog> slowLogGet(final int count) {
		final CommandArguments args = CommandArguments.create("count", count);
		return notCommand(client, ProtocolCommand.SLOWLOG_GET, args);
	}

	@Override
	public Long slowLogLen() {
		return notCommand(client, ProtocolCommand.SLOWLOG_LEN);
	}

	@Override
	public Status slowLogReset() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.SLOWLOG_RESET)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.SLOWLOG_RESET)
					.run();
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status swapdb(final int db1, final int db2) {
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);
		return notCommand(client, ProtocolCommand.SWAPDB, args);
	}

	@Override
	public RedisServerTime time() {
		return notCommand(client, ProtocolCommand.TIME);
	}

	private Status configSet(final String parameter, final String value, final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CONFIG_SET)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CONFIG_SET)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.CONFIG_SET, (cmd)->cmd.configSet(parameter, value),
					okStatusConverter)
					.run(args);
		}
	}

}
