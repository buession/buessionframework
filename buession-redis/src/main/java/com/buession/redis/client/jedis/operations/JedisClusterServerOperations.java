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
import redis.clients.jedis.args.SaveMode;

import java.util.List;

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
	public List<String> aclCat() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
					.run();
		}else{
			return new JedisClusterCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
					.run();
		}
	}

	@Override
	public List<String> aclCat(final String categoryName) {
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		return aclCat(args);
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName) {
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		return aclCat(args);
	}

	@Override
	public Status aclSetUser(final String username, final String... rules) {
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		return aclSetUser(args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules) {
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		return aclSetUser(args);
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create("username", username);
		return aclGetUser(args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		final CommandArguments args = CommandArguments.create("username", username);
		return aclGetUser(args);
	}

	@Override
	public List<String> aclUsers() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_USERS)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_USERS)
					.run();
		}else{
			return new JedisClusterCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_USERS)
					.run();
		}
	}

	@Override
	public String aclWhoAmI() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<String, String>(client, ProtocolCommand.ACL_WHOAMI)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<String, String>(client, ProtocolCommand.ACL_WHOAMI)
					.run();
		}else{
			return new JedisClusterCommand<String, String>(client, ProtocolCommand.ACL_WHOAMI)
					.run();
		}
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return aclDelUser(args);
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return aclDelUser(args);
	}

	@Override
	public String aclGenPass() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<String, String>(client, ProtocolCommand.ACL_GENPASS)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<String, String>(client, ProtocolCommand.ACL_GENPASS)
					.run();
		}else{
			return new JedisClusterCommand<String, String>(client, ProtocolCommand.ACL_GENPASS)
					.run();
		}
	}

	@Override
	public List<String> aclList() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_LIST)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_LIST)
					.run();
		}else{
			return new JedisClusterCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_LIST)
					.run();
		}
	}

	@Override
	public Status aclLoad() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.ACL_LOAD)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_LOAD)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.ACL_LOAD)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run();
		}else{
			return new JedisClusterCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog(final long count) {
		final CommandArguments args = CommandArguments.create("count", count);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run(args);
		}else{
			return new JedisClusterCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run(args);
		}
	}

	@Override
	public Status aclLogReset() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.ACL_LOGREST)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_LOGREST)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.ACL_LOGREST)
					.run();
		}
	}

	@Override
	public Status aclLogSave() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.ACL_LOGSAVE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_LOGSAVE)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.ACL_LOGSAVE)
					.run();
		}
	}

	@Override
	public String bgRewriteAof() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<String, String>(client, ProtocolCommand.BGREWRITEAOF)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<String, String>(client, ProtocolCommand.BGREWRITEAOF)
					.run();
		}else{
			return new JedisClusterCommand<String, String>(client, ProtocolCommand.BGREWRITEAOF)
					.run();
		}
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
	public List<String> configGet(final String parameter) {
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		return configGet(args);
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter) {
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		return configGet(args);
	}

	@Override
	public Status configResetStat() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CONFIG_RESETSTAT)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CONFIG_RESETSTAT)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CONFIG_RESETSTAT)
					.run();
		}
	}

	@Override
	public Status configRewrite() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CONFIG_REWRITE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CONFIG_REWRITE)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CONFIG_REWRITE)
					.run();
		}
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
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run();
		}
	}

	@Override
	public Status failover(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return failover(args);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("timeout", timeout);
		return failover(args);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);
		return failover(args);
	}

	@Override
	public Status failover(final int timeout) {
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		return failover(args);
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
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Info, Info>(client, ProtocolCommand.INFO)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Info, Info>(client, ProtocolCommand.INFO)
					.run();
		}else{
			return new JedisClusterCommand<Info, Info>(client, ProtocolCommand.INFO)
					.run();
		}
	}

	@Override
	public Info info(final Info.Section section) {
		final CommandArguments args = CommandArguments.create("section", section);
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Info, Info>(client, ProtocolCommand.INFO)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Info, Info>(client, ProtocolCommand.INFO)
					.run(args);
		}else{
			return new JedisClusterCommand<Info, Info>(client, ProtocolCommand.INFO)
					.run(args);
		}
	}

	@Override
	public Long lastSave() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Long, Long>(client, ProtocolCommand.LASTSAVE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Long, Long>(client, ProtocolCommand.LASTSAVE)
					.run();
		}else{
			return new JedisClusterCommand<Long, Long>(client, ProtocolCommand.LASTSAVE)
					.run();
		}
	}

	@Override
	public String memoryDoctor() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<String, String>(client, ProtocolCommand.MEMORY_DOCTOR)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<String, String>(client, ProtocolCommand.MEMORY_DOCTOR)
					.run();
		}else{
			return new JedisClusterCommand<String, String>(client, ProtocolCommand.MEMORY_DOCTOR)
					.run();
		}
	}

	@Override
	public Status memoryPurge() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.MEMORY_PURGE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.MEMORY_PURGE)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.MEMORY_PURGE)
					.run();
		}
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
					(cmd)->cmd.memoryUsage(key),
					(v)->v)
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
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<Module>, List<Module>>(client, ProtocolCommand.MODULE_LIST)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<Module>, List<Module>>(client, ProtocolCommand.MODULE_LIST)
					.run();
		}else{
			return new JedisClusterCommand<List<Module>, List<Module>>(client, ProtocolCommand.MODULE_LIST)
					.run();
		}
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments) {
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", (Object[]) arguments);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.MODULE_LOAD)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.MODULE_LOAD)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.MODULE_LOAD)
					.run(args);
		}
	}

	@Override
	public Status moduleUnLoad(final String name) {
		final CommandArguments args = CommandArguments.create("name", name);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.MODULE_UNLOAD)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.MODULE_UNLOAD)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.MODULE_UNLOAD)
					.run(args);
		}
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor) {
		final CommandArguments args = CommandArguments.create("redisMonitor", redisMonitor);

		if(isPipeline()){
			new JedisClusterPipelineCommand<>(client, ProtocolCommand.MONITOR)
					.run(args);
		}else if(isTransaction()){
			new JedisClusterTransactionCommand<>(client, ProtocolCommand.MONITOR)
					.run(args);
		}else{
			new JedisClusterCommand<>(client, ProtocolCommand.MONITOR)
					.run(args);
		}
	}

	@Override
	public Object pSync(final String replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return pSync(args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return pSync(args);
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

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.REPLICAOF)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.REPLICAOF)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.REPLICAOF)
					.run(args);
		}
	}

	@Override
	public Status slaveOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.SLAVEOF)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.SLAVEOF)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.SLAVEOF)
					.run(args);
		}
	}

	@Override
	public Role role() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Role, Role>(client, ProtocolCommand.ROLE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Role, Role>(client, ProtocolCommand.ROLE)
					.run();
		}else{
			return new JedisClusterCommand<Role, Role>(client, ProtocolCommand.ROLE)
					.run();
		}
	}

	@Override
	public Status save() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.SAVE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.SAVE)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.SAVE)
					.run();
		}
	}

	@Override
	public void shutdown() {
		if(isPipeline()){
			new JedisClusterPipelineCommand<>(client, ProtocolCommand.SHUTDOWN)
					.run();
		}else if(isTransaction()){
			new JedisClusterTransactionCommand<>(client, ProtocolCommand.SHUTDOWN)
					.run();
		}else{
			new JedisClusterCommand<>(client, ProtocolCommand.SHUTDOWN)
					.run();
		}
	}

	@Override
	public void shutdown(final boolean save) {
		final CommandArguments args = CommandArguments.create("save", save);
		final SaveMode saveMode = save ? SaveMode.SAVE : SaveMode.NOSAVE;

		if(isPipeline()){
			new JedisClusterPipelineCommand<>(client, ProtocolCommand.SHUTDOWN)
					.run();
		}else if(isTransaction()){
			new JedisClusterTransactionCommand<>(client, ProtocolCommand.SHUTDOWN)
					.run();
		}else{
			new JedisClusterCommand<>(client, ProtocolCommand.SHUTDOWN)
					.run();
		}
	}

	@Override
	public List<SlowLog> slowLogGet() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<SlowLog>, List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<SlowLog>, List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
					.run();
		}else{
			return new JedisClusterCommand<List<SlowLog>, List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
					.run();
		}
	}

	@Override
	public List<SlowLog> slowLogGet(final long count) {
		final CommandArguments args = CommandArguments.create("count", count);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<SlowLog>, List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<SlowLog>, List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
					.run(args);
		}else{
			return new JedisClusterCommand<List<SlowLog>, List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
					.run(args);
		}
	}

	@Override
	public Long slowLogLen() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Long, Long>(client, ProtocolCommand.SLOWLOG_LEN)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Long, Long>(client, ProtocolCommand.SLOWLOG_LEN)
					.run();
		}else{
			return new JedisClusterCommand<Long, Long>(client, ProtocolCommand.SLOWLOG_LEN)
					.run();
		}
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

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.SWAPDB)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.SWAPDB)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.SWAPDB)
					.run(args);
		}
	}

	@Override
	public RedisServerTime time() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<RedisServerTime, RedisServerTime>(client, ProtocolCommand.TIME)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<RedisServerTime, RedisServerTime>(client, ProtocolCommand.TIME)
					.run();
		}else{
			return new JedisClusterCommand<RedisServerTime, RedisServerTime>(client, ProtocolCommand.TIME)
					.run();
		}
	}

	private <V> List<V> aclCat(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ACL_CAT)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ACL_CAT)
					.run();
		}else{
			return new JedisClusterCommand<List<V>, List<V>>(client, ProtocolCommand.ACL_CAT)
					.run(args);
		}
	}

	private Status aclSetUser(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
					.run(args);
		}
	}

	private AclUser aclGetUser(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
					.run(args);
		}else{
			return new JedisClusterCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
					.run(args);
		}
	}

	private Long aclDelUser(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
					.run();
		}else{
			return new JedisClusterCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
					.run(args);
		}
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

	private <V> List<V> configGet(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.CONFIG_GET)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.CONFIG_GET)
					.run(args);
		}else{
			return new JedisClusterCommand<List<V>, List<V>>(client, ProtocolCommand.CONFIG_GET)
					.run(args);
		}
	}

	private Status failover(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run(args);
		}
	}

	private Object pSync(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.PSYNC)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.PSYNC)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PSYNC)
					.run(args);
		}
	}

}
