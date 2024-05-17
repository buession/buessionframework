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
package com.buession.redis.client.lettuce.operations;

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
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
import com.buession.redis.core.internal.convert.lettuce.response.InfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.RedisServerTimeConverter;
import com.buession.redis.core.internal.convert.lettuce.response.RoleConverter;
import com.buession.redis.core.internal.convert.lettuce.response.SlowlogConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.utils.SafeEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lettuce 单机模式服务端命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceServerOperations extends AbstractServerOperations<LettuceStandaloneClient> {

	public LettuceServerOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public List<String> aclCat() {
		return new LettuceCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
				.run();
	}

	@Override
	public List<String> aclCat(final String categoryName) {
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		return new LettuceCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
				.run(args);
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName) {
		final CommandArguments args = CommandArguments.create("categoryName", categoryName);
		return new LettuceCommand<List<byte[]>, List<byte[]>>(client, ProtocolCommand.ACL_CAT)
				.run(args);
	}

	@Override
	public Status aclSetUser(final String username, final String... rules) {
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
				.run(args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules) {
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
				.run(args);
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create("username", username);
		return new LettuceCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
				.run(args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		final CommandArguments args = CommandArguments.create("username", username);
		return new LettuceCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
				.run(args);
	}

	@Override
	public List<String> aclUsers() {
		return new LettuceCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_USERS)
				.run();
	}

	@Override
	public String aclWhoAmI() {
		return new LettuceCommand<String, String>(client, ProtocolCommand.ACL_WHOAMI)
				.run();
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
				.run(args);
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
				.run(args);
	}

	@Override
	public String aclGenPass() {
		return new LettuceCommand<String, String>(client, ProtocolCommand.ACL_GENPASS)
				.run();
	}

	@Override
	public List<String> aclList() {
		return new LettuceCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_LIST)
				.run();
	}

	@Override
	public Status aclLoad() {
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.ACL_LOAD)
				.run();
	}

	@Override
	public List<AclLog> aclLog() {
		return new LettuceCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
				.run();
	}

	@Override
	public List<AclLog> aclLog(final long count) {
		final CommandArguments args = CommandArguments.create("count", count);
		return new LettuceCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
				.run(args);
	}

	@Override
	public Status aclLogReset() {
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.ACL_LOGREST)
				.run();
	}

	@Override
	public Status aclLogSave() {
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.ACL_LOGSAVE)
				.run();
	}

	@Override
	public String bgRewriteAof() {
		return new LettuceCommand<String, String>(client, ProtocolCommand.BGREWRITEAOF)
				.run();
	}

	@Override
	public String bgSave() {
		return new LettuceCommand<String, String>(client, ProtocolCommand.BGREWRITEAOF)
				.run();
	}

	@Override
	public Status configSet(final String parameter, final String value) {
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.CONFIG_SET, (cmd)->cmd.configSet(parameter, value),
				new OkStatusConverter())
				.run(args);
	}

	@Override
	public List<String> configGet(final String parameter) {
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		return new LettuceCommand<>(client, ProtocolCommand.CONFIG_GET, (cmd)->cmd.configGet(parameter),
				(value)->new ArrayList<>(value.values()))
				.run(args);
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter) {
		final CommandArguments args = CommandArguments.create("parameter", parameter);
		return new LettuceCommand<>(client, ProtocolCommand.CONFIG_GET,
				(cmd)->cmd.configGet(SafeEncoder.encode(parameter)),
				(v)->v.values().stream().map(SafeEncoder::encode).collect(Collectors.toList()))
				.run(args);
	}

	@Override
	public Status configResetStat() {
		return new LettuceCommand<>(client, ProtocolCommand.CONFIG_RESETSTAT, (cmd)->cmd.configResetstat(),
				new OkStatusConverter())
				.run();
	}

	@Override
	public Status configRewrite() {
		return new LettuceCommand<>(client, ProtocolCommand.CONFIG_REWRITE, (cmd)->cmd.configRewrite(),
				new OkStatusConverter())
				.run();
	}

	@Override
	public Long dbSize() {
		return new LettuceCommand<>(client, ProtocolCommand.DBSIZE, (cmd)->cmd.dbsize(), (v)->v)
				.run();
	}

	@Override
	public Status failover() {
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
				.run();
	}

	@Override
	public Status failover(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
				.run(args);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("timeout", timeout);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
				.run(args);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
				.run(args);
	}

	@Override
	public Status failover(final int timeout) {
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
				.run(args);
	}

	@Override
	public Status flushAll() {
		return new LettuceCommand<>(client, ProtocolCommand.FLUSHALL, (cmd)->cmd.flushall(), new OkStatusConverter())
				.run();
	}

	@Override
	public Status flushAll(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);
		return new LettuceCommand<>(client, ProtocolCommand.FLUSHALL, (cmd)->cmd.flushall(), new OkStatusConverter())
				.run(args);
	}

	@Override
	public Status flushDb() {
		return new LettuceCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.flushdb(), new OkStatusConverter())
				.run();
	}

	@Override
	public Status flushDb(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);
		return new LettuceCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.flushdb(), new OkStatusConverter())
				.run(args);
	}

	@Override
	public Info info() {
		return new LettuceCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.info(), new InfoConverter())
				.run();
	}

	@Override
	public Info info(final Info.Section section) {
		final CommandArguments args = CommandArguments.create("section", section);
		return new LettuceCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.info(section.name().toLowerCase()),
				new InfoConverter())
				.run(args);
	}

	@Override
	public Long lastSave() {
		return new LettuceCommand<>(client, ProtocolCommand.LASTSAVE, (cmd)->cmd.lastsave(), Date::getTime)
				.run();
	}

	@Override
	public String memoryDoctor() {
		return new LettuceCommand<String, String>(client, ProtocolCommand.MEMORY_DOCTOR)
				.run();
	}

	@Override
	public Status memoryPurge() {
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.MEMORY_PURGE)
				.run();
	}

	@Override
	public MemoryStats memoryStats() {
		return new LettuceCommand<MemoryStats, MemoryStats>(client, ProtocolCommand.MEMORY_STATS)
				.run();
	}

	@Override
	public Long memoryUsage(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
				.run(args);
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples) {
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);
		return new LettuceCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
				.run(args);
	}

	@Override
	public List<Module> moduleList() {
		return new LettuceCommand<List<Module>, List<Module>>(client, ProtocolCommand.MODULE_LIST)
				.run();
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments) {
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", (Object[]) arguments);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.MODULE_LOAD)
				.run(args);
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", (Object[]) arguments);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.MODULE_LOAD)
				.run(args);
	}

	@Override
	public Status moduleUnLoad(final String name) {
		final CommandArguments args = CommandArguments.create("name", name);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.MODULE_UNLOAD)
				.run(args);
	}

	@Override
	public Status moduleUnLoad(final byte[] name) {
		final CommandArguments args = CommandArguments.create("name", name);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.MODULE_UNLOAD)
				.run(args);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor) {
		final CommandArguments args = CommandArguments.create("redisMonitor", redisMonitor);
		new LettuceCommand<>(client, ProtocolCommand.MONITOR)
				.run(args);
	}

	@Override
	public Object pSync(final String replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return new LettuceCommand<>(client, ProtocolCommand.PSYNC)
				.run(args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return new LettuceCommand<>(client, ProtocolCommand.PSYNC)
				.run(args);
	}

	@Override
	public void sync() {
		new LettuceCommand<>(client, ProtocolCommand.SYNC)
				.run();
	}

	@Override
	public Status replicaOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.REPLICAOF)
				.run(args);
	}

	@Override
	public Status slaveOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new LettuceCommand<>(client, ProtocolCommand.SLAVEOF, (cmd)->cmd.slaveof(host, port),
				new OkStatusConverter())
				.run(args);
	}

	@Override
	public List<Role> role() {
		return new LettuceCommand<>(client, ProtocolCommand.ROLE, (cmd)->cmd.role(),
				new RoleConverter.ListRoleConverter())
				.run();
	}

	@Override
	public Status save() {
		return new LettuceCommand<>(client, ProtocolCommand.SAVE, (cmd)->cmd.save(),
				new OkStatusConverter())
				.run();
	}

	@Override
	public void shutdown() {
		new LettuceCommand<>(client, ProtocolCommand.SHUTDOWN, (cmd)->{
			cmd.shutdown(true);
			return null;
		}, (v)->v)
				.run();
	}

	@Override
	public void shutdown(final boolean save) {
		final CommandArguments args = CommandArguments.create("save", save);
		new LettuceCommand<>(client, ProtocolCommand.SHUTDOWN, (cmd)->{
			cmd.shutdown(save);
			return null;
		}, (v)->v)
				.run(args);
	}

	@Override
	public List<SlowLog> slowLogGet() {
		return new LettuceCommand<>(client, ProtocolCommand.SLOWLOG_GET, (cmd)->cmd.slowlogGet(),
				new SlowlogConverter.ListSlowlogConverter())
				.run();
	}

	@Override
	public List<SlowLog> slowLogGet(final long count) {
		final CommandArguments args = CommandArguments.create("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.SLOWLOG_GET, (cmd)->cmd.slowlogGet((int) count),
				new SlowlogConverter.ListSlowlogConverter())
				.run(args);
	}

	@Override
	public Long slowLogLen() {
		return new LettuceCommand<>(client, ProtocolCommand.SLOWLOG_LEN, (cmd)->cmd.slowlogLen(), (v)->v)
				.run();
	}

	@Override
	public Status slowLogReset() {
		return new LettuceCommand<>(client, ProtocolCommand.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
				new OkStatusConverter())
				.run();
	}

	@Override
	public Status swapdb(final int db1, final int db2) {
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);
		return new LettuceCommand<>(client, ProtocolCommand.SWAPDB, (cmd)->cmd.swapdb(db1, db2),
				new OkStatusConverter())
				.run(args);
	}

	@Override
	public RedisServerTime time() {
		return new LettuceCommand<>(client, ProtocolCommand.TIME, (cmd)->cmd.time(), new RedisServerTimeConverter())
				.run();
	}

}
