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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.ServerOperations;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.CommandDoc;
import com.buession.redis.core.CommandInfo;
import com.buession.redis.core.CommandKeyAndFlag;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.HotKey;
import com.buession.redis.core.Info;
import com.buession.redis.core.LatencyHistogram;
import com.buession.redis.core.LatencyHistory;
import com.buession.redis.core.LatencyLatest;
import com.buession.redis.core.MemoryStats;
import com.buession.redis.core.Module;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.AclSetUserArgument;
import com.buession.redis.core.command.args.FailoverArgument;
import com.buession.redis.core.command.args.HotkeysStartArgument;
import com.buession.redis.core.command.args.RestoreArgument;
import com.buession.redis.core.command.args.ShutdownArgument;
import com.buession.redis.core.internal.convert.response.InfoConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis 服务端命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisServerOperations extends AbstractJedisRedisOperations implements ServerOperations {

	public JedisServerOperations(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Set<AclCategory> aclCat() {
		return executeCommand(Command.ACL, SubCommand.ACL_CAT);
	}

	@Override
	public Set<Command> aclCat(final String categoryName) {
		final CommandArguments args = CommandArguments.create(categoryName);
		return executeCommand(Command.ACL, SubCommand.ACL_CAT, args);
	}

	@Override
	public Set<Command> aclCat(final byte[] categoryName) {
		final CommandArguments args = CommandArguments.create(categoryName);
		return executeCommand(Command.ACL, SubCommand.ACL_CAT, args);
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);
		return executeCommand(Command.ACL, SubCommand.ACL_DELUSER, args);
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);
		return executeCommand(Command.ACL, SubCommand.ACL_DELUSER, args);
	}

	@Override
	public Status aclDryRun(final String username, final Command command) {
		final CommandArguments args = CommandArguments.create(username).add(command);
		return executeCommand(Command.ACL, SubCommand.ACL_DRYRUN, args);
	}

	@Override
	public Status aclDryRun(final byte[] username, final Command command) {
		final CommandArguments args = CommandArguments.create(username).add(command);
		return executeCommand(Command.ACL, SubCommand.ACL_DRYRUN, args);
	}

	@Override
	public Status aclDryRun(final String username, final Command command, final String... args) {
		final CommandArguments args1 = CommandArguments.create(username).add(command).add(args);
		return executeCommand(Command.ACL, SubCommand.ACL_DRYRUN, args1);
	}

	@Override
	public Status aclDryRun(final byte[] username, final Command command, final byte[]... args) {
		final CommandArguments args1 = CommandArguments.create(username).add(command).add(args);
		return executeCommand(Command.ACL, SubCommand.ACL_DRYRUN, args1);
	}

	@Override
	public String aclGenPass() {
		return executeCommand(Command.ACL, SubCommand.ACL_GENPASS);
	}

	@Override
	public String aclGenPass(final int bits) {
		final CommandArguments args = CommandArguments.create(bits);
		return executeCommand(Command.ACL, SubCommand.ACL_GENPASS, args);
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create(username);
		return executeCommand(Command.ACL, SubCommand.ACL_GETUSER, args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		final CommandArguments args = CommandArguments.create(username);
		return executeCommand(Command.ACL, SubCommand.ACL_GETUSER, args);
	}

	@Override
	public List<String> aclList() {
		return executeCommand(Command.ACL, SubCommand.ACL_LIST);
	}

	@Override
	public Status aclLoad() {
		return executeCommand(Command.ACL, SubCommand.ACL_LOAD);
	}

	@Override
	public List<AclLog> aclLog() {
		return executeCommand(Command.ACL, SubCommand.ACL_LOG);
	}

	@Override
	public List<AclLog> aclLog(final long count) {
		final CommandArguments args = CommandArguments.create(count);
		return executeCommand(Command.ACL, SubCommand.ACL_LOG, args);
	}

	@Override
	public Status aclLogReset() {
		final CommandArguments args = CommandArguments.create("RESET");
		return executeCommand(Command.ACL, SubCommand.ACL_LOG, args);
	}

	@Override
	public Status aclSave() {
		return executeCommand(Command.ACL, SubCommand.ACL_SAVE);
	}

	@Override
	public Status aclSetUser(final String username, final AclSetUserArgument argument) {
		final CommandArguments args = CommandArguments.create(username).add(argument);
		return executeCommand(Command.ACL, SubCommand.ACL_SETUSER, args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final AclSetUserArgument argument) {
		final CommandArguments args = CommandArguments.create(username).add(argument);
		return executeCommand(Command.ACL, SubCommand.ACL_SETUSER, args);
	}

	@Override
	public List<String> aclUsers() {
		return executeCommand(Command.ACL, SubCommand.ACL_USERS);
	}

	@Override
	public String aclWhoAmI() {
		return executeCommand(Command.ACL, SubCommand.ACL_WHOAMI);
	}

	@Override
	public String bgRewriteAof() {
		return executeCommand(Command.BGREWRITEAOF);
	}

	@Override
	public String bgSave() {
		return executeCommand(Command.BGSAVE);
	}

	@Override
	public Integer commandCount() {
		return executeCommand(Command.COMMAND, SubCommand.COMMAND_COUNT);
	}

	@Override
	public List<CommandDoc> commandDocs() {
		return executeCommand(Command.COMMAND, SubCommand.COMMAND_DOCS);
	}

	@Override
	public List<CommandDoc> commandDocs(final Command... commands) {
		final CommandArguments args = CommandArguments.create(commands);
		return executeCommand(Command.COMMAND, SubCommand.COMMAND_DOCS, args);
	}

	@Override
	public List<String> commandGetKeys(final Command command) {
		final CommandArguments args = CommandArguments.create(command);
		return executeCommand(Command.COMMAND, SubCommand.COMMAND_GETKEYS, args);
	}

	@Override
	public List<String> commandGetKeys(final Command command, final String... args) {
		final CommandArguments args1 = CommandArguments.create(command).add(args);
		return executeCommand(Command.COMMAND, SubCommand.COMMAND_GETKEYS, args1);
	}

	@Override
	public List<CommandKeyAndFlag> commandGetKeysAndFlags(final Command command) {
		final CommandArguments args = CommandArguments.create(command);
		return executeCommand(Command.COMMAND, SubCommand.COMMAND_GETKEYSANDFLAGS, args);
	}

	@Override
	public List<CommandKeyAndFlag> commandGetKeysAndFlags(final Command command, final String... args) {
		final CommandArguments args1 = CommandArguments.create(command).add(args);
		return executeCommand(Command.COMMAND, SubCommand.COMMAND_GETKEYSANDFLAGS, args1);
	}

	@Override
	public List<CommandInfo> commandInfo(final Command... commands) {
		final CommandArguments args = CommandArguments.create(commands);
		return executeCommand(Command.COMMAND, SubCommand.COMMAND_INFO, args);
	}

	@Override
	public List<Command> commandList() {
		return executeCommand(Command.COMMAND, SubCommand.COMMAND_LIST);
	}

	@Override
	public Map<String, String> configGet(final String... parameters) {
		final CommandArguments args = CommandArguments.create(parameters);
		return executeCommand(Command.CONFIG_GET, args);
	}

	@Override
	public Map<byte[], byte[]> configGet(final byte[]... parameters) {
		final CommandArguments args = CommandArguments.create(parameters);
		return executeCommand(Command.CONFIG_GET, args);
	}

	@Override
	public Status configResetStat() {
		return executeCommand(Command.CONFIG_RESETSTAT);
	}

	@Override
	public Status configRewrite() {
		return executeCommand(Command.CONFIG_REWRITE);
	}

	@Override
	public Status configSet(final String parameter, final String value) {
		final CommandArguments args = CommandArguments.create(parameter).add(value);
		return executeCommand(Command.CONFIG_SET, args);
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value) {
		final CommandArguments args = CommandArguments.create(parameter).add(value);
		return executeCommand(Command.CONFIG_SET, args);
	}

	@Override
	public Status configSet(final Map<String, String> configs) {
		final CommandArguments args = CommandArguments.create(configs);
		return executeCommand(Command.CONFIG_SET, args);
	}

	@Override
	public Long dbSize() {
		return executeCommand(Command.DBSIZE, (cmd)->cmd.dbSize(), (v)->v);
	}

	@Override
	public Status failover() {
		return executeCommand(Command.FAILOVER);
	}

	@Override
	public Status failover(final FailoverArgument argument) {
		final CommandArguments args = CommandArguments.create(argument);
		return executeCommand(Command.FAILOVER, args);
	}

	@Override
	public Status flushAll() {
		return executeCommand(Command.FLUSHALL, (cmd)->cmd.flushAll(), new OkStatusConverter());
	}

	@Override
	public Status flushAll(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		return executeCommand(Command.FLUSHALL, args, (cmd)->cmd.flushAll(), new OkStatusConverter());
	}

	@Override
	public Status flushDb() {
		return executeCommand(Command.FLUSHDB, (cmd)->cmd.flushDB(), new OkStatusConverter());
	}

	@Override
	public Status flushDb(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		return executeCommand(Command.FLUSHDB, args, (cmd)->cmd.flushDB(), new OkStatusConverter());
	}

	@Override
	public List<HotKey> hotkeysGet() {
		return executeCommand(Command.HOTKEYS, SubCommand.HOTKEYS_GET);
	}

	@Override
	public Status hotkeysReset() {
		return executeCommand(Command.HOTKEYS, SubCommand.HOTKEYS_RESET);
	}

	@Override
	public Status hotkeysStart(final int count) {
		final CommandArguments args = CommandArguments.create("METRICS").add(count);
		return executeCommand(Command.HOTKEYS, SubCommand.HOTKEYS_START, args);
	}

	@Override
	public Status hotkeysStart(final int count, final HotkeysStartArgument argument) {
		final CommandArguments args = CommandArguments.create("METRICS").add(count).add(argument);
		return executeCommand(Command.HOTKEYS, SubCommand.HOTKEYS_START, args);
	}

	@Override
	public Status hotkeysStop() {
		return executeCommand(Command.HOTKEYS, SubCommand.HOTKEYS_STOP);
	}

	@Override
	public Info info() {
		return executeCommand(Command.INFO, (cmd)->cmd.info(), new InfoConverter());
	}

	@Override
	public Info info(final Info.Section section) {
		final CommandArguments args = CommandArguments.create(section);
		return executeCommand(Command.INFO, args, (cmd)->cmd.info(section.name().toLowerCase()), new InfoConverter());
	}

	@Override
	public Long lastSave() {
		return executeCommand(Command.LASTSAVE);
	}

	@Override
	public String latencyDoctor() {
		return executeCommand(Command.LATENCY, SubCommand.LATENCY_DOCTOR);
	}

	@Override
	public String latencyGraph() {
		return executeCommand(Command.LATENCY, SubCommand.LATENCY_GRAPH);
	}

	@Override
	public List<LatencyHistogram> latencyHistogram() {
		return executeCommand(Command.LATENCY, SubCommand.LATENCY_HISTOGRAM);
	}

	@Override
	public List<LatencyHistogram> latencyHistogram(final Command... commands) {
		final CommandArguments args = CommandArguments.create(commands);
		return executeCommand(Command.LATENCY, SubCommand.LATENCY_HISTOGRAM, args);
	}

	@Override
	public List<LatencyHistory> latencyHistory(final String event) {
		final CommandArguments args = CommandArguments.create(event);
		return executeCommand(Command.LATENCY, SubCommand.LATENCY_HISTORY, args);
	}

	@Override
	public List<LatencyLatest> latencyLatest() {
		return executeCommand(Command.LATENCY, SubCommand.LATENCY_LATEST);
	}

	@Override
	public Status latencyReset() {
		return executeCommand(Command.LATENCY, SubCommand.LATENCY_RESET);
	}

	@Override
	public Status latencyReset(final String... events) {
		final CommandArguments args = CommandArguments.create(events);
		return executeCommand(Command.LATENCY, SubCommand.LATENCY_RESET, args);
	}

	@Override
	public String lolwut() {
		return executeCommand(Command.LOLWUT);
	}

	@Override
	public String lolwut(final String version) {
		final CommandArguments args = CommandArguments.create("VERSION").add(version);
		return executeCommand(Command.LOLWUT, args);
	}

	@Override
	public String memoryDoctor() {
		return executeCommand(Command.MEMORY, SubCommand.MEMORY_DOCTOR);
	}

	@Override
	public String memoryMallocStats() {
		return executeCommand(Command.MEMORY, SubCommand.MEMORY_MALLOC_STATS);
	}

	@Override
	public Status memoryPurge() {
		return executeCommand(Command.MEMORY, SubCommand.MEMORY_PURGE);
	}

	@Override
	public MemoryStats memoryStats() {
		return executeCommand(Command.MEMORY, SubCommand.MEMORY_STATS);
	}

	@Override
	public Long memoryUsage(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.MEMORY, SubCommand.MEMORY_USAGE, args, (cmd)->cmd.memoryUsage(key), (v)->v);
	}

	@Override
	public Long memoryUsage(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.MEMORY, SubCommand.MEMORY_USAGE, args, (cmd)->cmd.memoryUsage(key), (v)->v);
	}

	@Override
	public Long memoryUsage(final String key, final int samples) {
		final CommandArguments args = CommandArguments.create(key).add("SAMPLES", samples);
		return executeCommand(Command.MEMORY, SubCommand.MEMORY_USAGE, args, (cmd)->cmd.memoryUsage(key, samples),
				(v)->v);
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples) {
		final CommandArguments args = CommandArguments.create(key).add("SAMPLES", samples);
		return executeCommand(Command.MEMORY, SubCommand.MEMORY_USAGE, args, (cmd)->cmd.memoryUsage(key, samples),
				(v)->v);
	}

	@Override
	public List<Module> moduleList() {
		return executeCommand(Command.MODULE, SubCommand.MODULE_LIST);
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments) {
		final CommandArguments args = CommandArguments.create(path).add(arguments);
		return executeCommand(Command.MODULE, SubCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create(path).add(arguments);
		return executeCommand(Command.MODULE, SubCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoadex(final String path, final Map<String, String> configs, final String... arguments) {
		final CommandArguments args = CommandArguments.create(path).add(configs).add(arguments);
		return executeCommand(Command.MODULE, SubCommand.MODULE_LOADEX, args);
	}

	@Override
	public Status moduleLoadex(final byte[] path, final Map<byte[], byte[]> configs, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create(path).add(configs).add(arguments);
		return executeCommand(Command.MODULE, SubCommand.MODULE_LOADEX, args);
	}

	@Override
	public Status moduleUnLoad(final String name) {
		final CommandArguments args = CommandArguments.create(name);
		return executeCommand(Command.MODULE, SubCommand.MODULE_UNLOAD, args);
	}

	@Override
	public Status moduleUnLoad(final byte[] name) {
		final CommandArguments args = CommandArguments.create(name);
		return executeCommand(Command.MODULE, SubCommand.MODULE_UNLOAD, args);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor) {
		executeCommand(Command.MONITOR);
	}

	@Override
	public Object pSync(final String replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create(replicationId).add(offset);
		return executeCommand(Command.PSYNC, args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create(replicationId).add(offset);
		return executeCommand(Command.PSYNC, args);
	}

	@Override
	public Status replconf() {
		return executeCommand(Command.REPLCONF);
	}

	@Override
	public Status replicaOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);
		return executeCommand(Command.REPLICAOF, args);
	}

	@Override
	public Status restoreAsking(final String key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(serializedValue);
		return executeCommand(Command.RESTORE_ASKING, args);
	}

	@Override
	public Status restoreAsking(final byte[] key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(serializedValue);
		return executeCommand(Command.RESTORE_ASKING, args);
	}

	@Override
	public Status restoreAsking(final String key, final byte[] serializedValue, final int ttl,
								final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(serializedValue).add(argument);
		return executeCommand(Command.RESTORE_ASKING, args);
	}

	@Override
	public Status restoreAsking(final byte[] key, byte[] serializedValue, final int ttl,
								final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(serializedValue).add(argument);
		return executeCommand(Command.RESTORE_ASKING, args);
	}

	@Override
	public Role role() {
		return executeCommand(Command.ROLE);
	}

	@Override
	public Status save() {
		return executeCommand(Command.SAVE);
	}

	@Override
	public void shutdown() {
		executeCommand(Command.SHUTDOWN);
	}

	@Override
	public void shutdown(final ShutdownArgument argument) {
		final CommandArguments args = CommandArguments.create(argument);
		executeCommand(Command.SHUTDOWN, args);
	}

	@Override
	public Status slaveOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);
		return executeCommand(Command.SLAVEOF, args);
	}

	@Override
	public List<SlowLog> slowLogGet() {
		return executeCommand(Command.SLOWLOG, SubCommand.SLOWLOG_GET);
	}

	@Override
	public List<SlowLog> slowLogGet(final long count) {
		final CommandArguments args = CommandArguments.create(count);
		return executeCommand(Command.SLOWLOG, SubCommand.SLOWLOG_GET, args);
	}

	@Override
	public Long slowLogLen() {
		return executeCommand(Command.SLOWLOG, SubCommand.SLOWLOG_LEN);
	}

	@Override
	public Status slowLogReset() {
		return executeCommand(Command.SLOWLOG, SubCommand.SLOWLOG_RESET);
	}

	@Override
	public Status swapdb(final int db1, final int db2) {
		final CommandArguments args = CommandArguments.create(db1).add(db2);
		return executeCommand(Command.SWAPDB, args);
	}

	@Override
	public void sync() {
		executeCommand(Command.SYNC);
	}

	@Override
	public RedisServerTime time() {
		return executeCommand(Command.TIME);
	}

}
