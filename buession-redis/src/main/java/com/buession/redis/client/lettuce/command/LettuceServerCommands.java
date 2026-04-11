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
package com.buession.redis.client.lettuce.command;

import com.buession.core.converter.ListConverter;
import com.buession.core.converter.SetConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.CommandDoc;
import com.buession.redis.core.CommandInfo;
import com.buession.redis.core.CommandKeyAndFlag;
import com.buession.redis.core.command.args.FlushMode;
import com.buession.redis.core.HotKey;
import com.buession.redis.core.Info;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.LatencyHistogram;
import com.buession.redis.core.LatencyHistory;
import com.buession.redis.core.LatencyLatest;
import com.buession.redis.core.MemoryStats;
import com.buession.redis.core.Module;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ServerCommands;
import com.buession.redis.core.command.RedisSubCommand;
import com.buession.redis.core.command.args.acl.AclSetUserArgument;
import com.buession.redis.core.command.args.server.FailoverArgument;
import com.buession.redis.core.command.args.server.HotkeysStartArgument;
import com.buession.redis.core.command.args.RestoreArgument;
import com.buession.redis.core.command.args.server.ShutdownArgument;
import com.buession.redis.core.internal.convert.StringMapBinaryMapConverter;
import com.buession.redis.core.internal.convert.lettuce.params.FlushModeConverter;
import com.buession.redis.core.internal.convert.lettuce.response.AclCategoryConverter;
import com.buession.redis.core.internal.convert.lettuce.response.AclLogConverter;
import com.buession.redis.core.internal.convert.lettuce.response.AclUserConverter;
import com.buession.redis.core.internal.convert.lettuce.response.CommandInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.CommandTypeConverter;
import com.buession.redis.core.internal.convert.lettuce.response.RedisServerTimeConverter;
import com.buession.redis.core.internal.convert.lettuce.response.RoleConverter;
import com.buession.redis.core.internal.convert.lettuce.response.SlowlogConverter;
import com.buession.redis.core.internal.convert.response.InfoConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceAclSetuserArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceShutdownArgs;
import com.buession.redis.utils.SafeEncoder;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Lettuce 服务端命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceServerCommands extends AbstractLettuceRedisCommands implements ServerCommands {

	public LettuceServerCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Set<AclCategory> aclCat() {
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_CAT, (cmd)->cmd.aclCat(),
				(cmd)->cmd.aclCat(), new SetConverter<>(new AclCategoryConverter()));
	}

	@Override
	public Set<RedisCommand> aclCat(final String categoryName) {
		final CommandArguments args = CommandArguments.create(categoryName);
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_CAT, args,
				(cmd)->cmd.aclCat(Enum.valueOf(io.lettuce.core.AclCategory.class, categoryName)),
				(cmd)->cmd.aclCat(Enum.valueOf(io.lettuce.core.AclCategory.class, categoryName)),
				new SetConverter<>(new CommandTypeConverter()));
	}

	@Override
	public Set<RedisCommand> aclCat(final byte[] categoryName) {
		return aclCat(SafeEncoder.encode(categoryName));
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_DELUSER, args, (cmd)->cmd.aclDeluser(usernames),
				(cmd)->cmd.aclDeluser(usernames));
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		return aclDelUser(SafeEncoder.encode(usernames));
	}

	@Override
	public Status aclDryRun(final String username, final RedisCommand command) {
		final CommandArguments args = CommandArguments.create(username).add(command);
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_DRYRUN, args,
				(cmd)->cmd.aclDryRun(username, command.getName()), (cmd)->cmd.aclDryRun(username, command.getName()),
				new OkStatusConverter());
	}

	@Override
	public Status aclDryRun(final byte[] username, final RedisCommand command) {
		return aclDryRun(SafeEncoder.encode(username), command);
	}

	@Override
	public Status aclDryRun(final String username, final RedisCommand command, final String... arguments) {
		final CommandArguments args = CommandArguments.create(username).add(command).add(arguments);
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_DRYRUN, args,
				(cmd)->cmd.aclDryRun(username, command.getName(), arguments),
				(cmd)->cmd.aclDryRun(username, command.getName(), arguments), new OkStatusConverter());
	}

	@Override
	public Status aclDryRun(final byte[] username, final RedisCommand command, final byte[]... args) {
		return aclDryRun(SafeEncoder.encode(username), command, SafeEncoder.encode(args));
	}

	@Override
	public String aclGenPass() {
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_GENPASS, (cmd)->cmd.aclGenpass(),
				(cmd)->cmd.aclGenpass());
	}

	@Override
	public String aclGenPass(final int bits) {
		final CommandArguments args = CommandArguments.create(bits);
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_GENPASS, args, (cmd)->cmd.aclGenpass(bits),
				(cmd)->cmd.aclGenpass(bits));
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create(username);
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_GETUSER, args, (cmd)->cmd.aclGetuser(username),
				(cmd)->cmd.aclGetuser(username), new AclUserConverter());
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		return aclGetUser(SafeEncoder.encode(username));
	}

	@Override
	public List<String> aclList() {
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_LIST, (cmd)->cmd.aclList(), (cmd)->cmd.aclList());
	}

	@Override
	public Status aclLoad() {
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_LOAD, (cmd)->cmd.aclLoad(), (cmd)->cmd.aclLoad(),
				new OkStatusConverter());
	}

	@Override
	public List<AclLog> aclLog() {
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_LOG, (cmd)->cmd.aclLog(), (cmd)->cmd.aclLog(),
				new ListConverter<>(new AclLogConverter()));
	}

	@Override
	public List<AclLog> aclLog(final int count) {
		final CommandArguments args = CommandArguments.create(count);
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_LOG, args, (cmd)->cmd.aclLog(count),
				(cmd)->cmd.aclLog(count), new ListConverter<>(new AclLogConverter()));
	}

	@Override
	public Status aclLogReset() {
		final CommandArguments args = CommandArguments.create(Keyword.Conn.RESET);
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_LOG, args,
				(cmd)->cmd.aclLogReset(), (cmd)->cmd.aclLogReset(), new OkStatusConverter());
	}

	@Override
	public Status aclSave() {
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_SAVE, (cmd)->cmd.aclSave(), (cmd)->cmd.aclSave(),
				new OkStatusConverter());
	}

	@Override
	public Status aclSetUser(final String username, final AclSetUserArgument argument) {
		final CommandArguments args = CommandArguments.create(username).add(argument);
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_SETUSER, args,
				(cmd)->cmd.aclSetuser(username, new LettuceAclSetuserArgs(argument)),
				(cmd)->cmd.aclSetuser(username, new LettuceAclSetuserArgs(argument)),
				new OkStatusConverter());
	}

	@Override
	public Status aclSetUser(final byte[] username, final AclSetUserArgument argument) {
		return aclSetUser(SafeEncoder.encode(username), argument);
	}

	@Override
	public List<String> aclUsers() {
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_USERS, (cmd)->cmd.aclUsers(),
				(cmd)->cmd.aclUsers());
	}

	@Override
	public String aclWhoAmI() {
		return executeCommand(RedisCommand.ACL, RedisSubCommand.ACL_WHOAMI, (cmd)->cmd.aclWhoami(),
				(cmd)->cmd.aclWhoami());
	}

	@Override
	public String bgRewriteAof() {
		return executeCommand(RedisCommand.BGREWRITEAOF);
	}

	@Override
	public String bgSave() {
		return executeCommand(RedisCommand.BGSAVE, (cmd)->cmd.bgsave(), (cmd)->cmd.bgsave());
	}

	@Override
	public Integer commandCount() {
		return executeCommand(RedisCommand.COMMAND, RedisSubCommand.COMMAND_COUNT, (cmd)->cmd.commandCount(),
				(cmd)->cmd.commandCount(), Long::intValue);
	}

	@Override
	public List<CommandDoc> commandDocs() {
		return executeCommand(RedisCommand.COMMAND, RedisSubCommand.COMMAND_DOCS);
	}

	@Override
	public List<CommandDoc> commandDocs(final RedisCommand... commands) {
		final CommandArguments args = CommandArguments.create(commands);
		return executeCommand(RedisCommand.COMMAND, RedisSubCommand.COMMAND_DOCS, args);
	}

	@Override
	public List<String> commandGetKeys(final RedisCommand command) {
		final CommandArguments args = CommandArguments.create(command);
		return executeCommand(RedisCommand.COMMAND, RedisSubCommand.COMMAND_GETKEYS, args);
	}

	@Override
	public List<String> commandGetKeys(final RedisCommand command, final String... arguments) {
		final CommandArguments args = CommandArguments.create(command).add(arguments);
		return executeCommand(RedisCommand.COMMAND, RedisSubCommand.COMMAND_GETKEYS, args);
	}

	@Override
	public List<CommandKeyAndFlag> commandGetKeysAndFlags(final RedisCommand command) {
		final CommandArguments args = CommandArguments.create(command);
		return executeCommand(RedisCommand.COMMAND, RedisSubCommand.COMMAND_GETKEYSANDFLAGS, args);
	}

	@Override
	public List<CommandKeyAndFlag> commandGetKeysAndFlags(final RedisCommand command, final String... arguments) {
		final CommandArguments args = CommandArguments.create(command).add(arguments);
		return executeCommand(RedisCommand.COMMAND, RedisSubCommand.COMMAND_GETKEYSANDFLAGS, args);
	}

	@Override
	public List<CommandInfo> commandInfo(final RedisCommand... commands) {
		final CommandArguments args = CommandArguments.create(commands);
		final String[] commandInfoArgs = new String[commands.length];

		for(int i = 0; i < commands.length; i++){
			commandInfoArgs[i] = commands[i].toString();
		}

		return executeCommand(RedisCommand.COMMAND, RedisSubCommand.COMMAND_INFO, args,
				(cmd)->cmd.commandInfo(commandInfoArgs), (cmd)->cmd.commandInfo(commandInfoArgs),
				new ListConverter<>(new CommandInfoConverter()));
	}

	@Override
	public List<RedisCommand> commandList() {
		return executeCommand(RedisCommand.COMMAND, RedisSubCommand.COMMAND_LIST);
	}

	@Override
	public Map<String, String> configGet(final String... parameters) {
		final CommandArguments args = CommandArguments.create(parameters);
		return executeCommand(RedisCommand.CONFIG_GET, args, (cmd)->cmd.configGet(parameters),
				(cmd)->cmd.configGet(parameters));
	}

	@Override
	public Map<byte[], byte[]> configGet(final byte[]... parameters) {
		final CommandArguments args = CommandArguments.create(parameters);
		return executeCommand(RedisCommand.CONFIG_GET, args, (cmd)->cmd.configGet(SafeEncoder.encode(parameters)),
				(cmd)->cmd.configGet(SafeEncoder.encode(parameters)), new StringMapBinaryMapConverter());
	}

	@Override
	public Status configResetStat() {
		return executeCommand(RedisCommand.CONFIG_RESETSTAT, (cmd)->cmd.configResetstat(), (cmd)->cmd.configResetstat(),
				new OkStatusConverter());
	}

	@Override
	public Status configRewrite() {
		return executeCommand(RedisCommand.CONFIG_REWRITE, (cmd)->cmd.configRewrite(), (cmd)->cmd.configRewrite(),
				new OkStatusConverter());
	}

	@Override
	public Status configSet(final String parameter, final String value) {
		final CommandArguments args = CommandArguments.create(parameter).add(value);
		return executeCommand(RedisCommand.CONFIG_SET, args, (cmd)->cmd.configSet(parameter, value),
				(cmd)->cmd.configSet(parameter, value), new OkStatusConverter());
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value) {
		return configSet(SafeEncoder.encode(parameter), SafeEncoder.encode(value));
	}

	@Override
	public Status configSet(final Map<String, String> configs) {
		final CommandArguments args = CommandArguments.create(configs);
		return executeCommand(RedisCommand.CONFIG_SET, args, (cmd)->cmd.configSet(configs),
				(cmd)->cmd.configSet(configs), new OkStatusConverter());
	}

	@Override
	public Long dbSize() {
		return executeCommand(RedisCommand.DBSIZE, (cmd)->cmd.dbsize(), (cmd)->cmd.dbsize());
	}

	@Override
	public Status failover() {
		return executeCommand(RedisCommand.FAILOVER);
	}

	@Override
	public Status failover(final FailoverArgument argument) {
		final CommandArguments args = CommandArguments.create(argument);
		return executeCommand(RedisCommand.FAILOVER, args);
	}

	@Override
	public Status flushAll() {
		return executeCommand(RedisCommand.FLUSHALL, (cmd)->cmd.flushall(), (cmd)->cmd.flushall(),
				new OkStatusConverter());
	}

	@Override
	public Status flushAll(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		final FlushModeConverter flushModeConverter = new FlushModeConverter();
		return executeCommand(RedisCommand.FLUSHALL, args, (cmd)->cmd.flushall(flushModeConverter.convert(mode)),
				(cmd)->cmd.flushall(flushModeConverter.convert(mode)), new OkStatusConverter());
	}

	@Override
	public Status flushDb() {
		return executeCommand(RedisCommand.FLUSHDB, (cmd)->cmd.flushdb(), (cmd)->cmd.flushdb(),
				new OkStatusConverter());
	}

	@Override
	public Status flushDb(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		final FlushModeConverter flushModeConverter = new FlushModeConverter();
		return executeCommand(RedisCommand.FLUSHDB, args, (cmd)->cmd.flushdb(flushModeConverter.convert(mode)),
				(cmd)->cmd.flushdb(flushModeConverter.convert(mode)), new OkStatusConverter());
	}

	@Override
	public List<HotKey> hotkeysGet() {
		return executeCommand(RedisCommand.HOTKEYS, RedisSubCommand.HOTKEYS_GET);
	}

	@Override
	public Status hotkeysReset() {
		return executeCommand(RedisCommand.HOTKEYS, RedisSubCommand.HOTKEYS_RESET);
	}

	@Override
	public Status hotkeysStart(final int count) {
		final CommandArguments args = CommandArguments.create("METRICS").add(count);
		return executeCommand(RedisCommand.HOTKEYS, RedisSubCommand.HOTKEYS_START, args);
	}

	@Override
	public Status hotkeysStart(final int count, final HotkeysStartArgument argument) {
		final CommandArguments args = CommandArguments.create("METRICS").add(count).add(argument);
		return executeCommand(RedisCommand.HOTKEYS, RedisSubCommand.HOTKEYS_START, args);
	}

	@Override
	public Status hotkeysStop() {
		return executeCommand(RedisCommand.HOTKEYS, RedisSubCommand.HOTKEYS_STOP);
	}

	@Override
	public Info info() {
		return executeCommand(RedisCommand.INFO, (cmd)->cmd.info(), (cmd)->cmd.info(), new InfoConverter());
	}

	@Override
	public Info info(final Info.Section section) {
		final CommandArguments args = CommandArguments.create(section);
		return executeCommand(RedisCommand.INFO, args, (cmd)->cmd.info(section.name().toLowerCase()),
				(cmd)->cmd.info(section.name().toLowerCase()), new InfoConverter());
	}

	@Override
	public Long lastSave() {
		return executeCommand(RedisCommand.LASTSAVE, (cmd)->cmd.lastsave(), (cmd)->cmd.lastsave(), Date::getTime);
	}

	@Override
	public String latencyDoctor() {
		return executeCommand(RedisCommand.LATENCY, RedisSubCommand.LATENCY_DOCTOR);
	}

	@Override
	public String latencyGraph() {
		return executeCommand(RedisCommand.LATENCY, RedisSubCommand.LATENCY_GRAPH);
	}

	@Override
	public List<LatencyHistogram> latencyHistogram() {
		return executeCommand(RedisCommand.LATENCY, RedisSubCommand.LATENCY_HISTOGRAM);
	}

	@Override
	public List<LatencyHistogram> latencyHistogram(final RedisCommand... commands) {
		final CommandArguments args = CommandArguments.create(commands);
		return executeCommand(RedisCommand.LATENCY, RedisSubCommand.LATENCY_HISTOGRAM, args);
	}

	@Override
	public List<LatencyHistory> latencyHistory(final String event) {
		final CommandArguments args = CommandArguments.create(event);
		return executeCommand(RedisCommand.LATENCY, RedisSubCommand.LATENCY_HISTORY, args);
	}

	@Override
	public List<LatencyLatest> latencyLatest() {
		return executeCommand(RedisCommand.LATENCY, RedisSubCommand.LATENCY_LATEST);
	}

	@Override
	public Status latencyReset() {
		return executeCommand(RedisCommand.LATENCY, RedisSubCommand.LATENCY_RESET);
	}

	@Override
	public Status latencyReset(final String... events) {
		final CommandArguments args = CommandArguments.create(events);
		return executeCommand(RedisCommand.LATENCY, RedisSubCommand.LATENCY_RESET, args);
	}

	@Override
	public String lolwut() {
		return executeCommand(RedisCommand.LOLWUT);
	}

	@Override
	public String lolwut(final String version) {
		final CommandArguments args = CommandArguments.create("VERSION", version);
		return executeCommand(RedisCommand.LOLWUT, args);
	}

	@Override
	public String memoryDoctor() {
		return executeCommand(RedisCommand.MEMORY, RedisSubCommand.MEMORY_DOCTOR);
	}

	@Override
	public String memoryMallocStats() {
		return executeCommand(RedisCommand.MEMORY, RedisSubCommand.MEMORY_MALLOC_STATS);
	}

	@Override
	public Status memoryPurge() {
		return executeCommand(RedisCommand.MEMORY, RedisSubCommand.MEMORY_PURGE);
	}

	@Override
	public MemoryStats memoryStats() {
		return executeCommand(RedisCommand.MEMORY, RedisSubCommand.MEMORY_STATS);
	}

	@Override
	public Long memoryUsage(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.MEMORY, RedisSubCommand.MEMORY_USAGE, args,
				(cmd)->cmd.memoryUsage(rawBinaryKey(key)), (cmd)->cmd.memoryUsage(rawBinaryKey(key)));
	}

	@Override
	public Long memoryUsage(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.MEMORY, RedisSubCommand.MEMORY_USAGE, args,
				(cmd)->cmd.memoryUsage(rawKey(key)), (cmd)->cmd.memoryUsage(rawKey(key)));
	}

	@Override
	public Long memoryUsage(final String key, final int samples) {
		final CommandArguments args = CommandArguments.create(key).add("SAMPLES", samples);
		return executeCommand(RedisCommand.MEMORY, RedisSubCommand.MEMORY_USAGE, args,
				(cmd)->cmd.memoryUsage(rawBinaryKey(key)), (cmd)->cmd.memoryUsage(rawBinaryKey(key)));
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples) {
		final CommandArguments args = CommandArguments.create(key).add("SAMPLES", samples);
		return executeCommand(RedisCommand.MEMORY, RedisSubCommand.MEMORY_USAGE, args,
				(cmd)->cmd.memoryUsage(rawKey(key)), (cmd)->cmd.memoryUsage(rawKey(key)));
	}

	@Override
	public List<Module> moduleList() {
		return executeCommand(RedisCommand.MODULE, RedisSubCommand.MODULE_LIST);
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments) {
		final CommandArguments args = CommandArguments.create(path).add(arguments);
		return executeCommand(RedisCommand.MODULE, RedisSubCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create(path).add(arguments);
		return executeCommand(RedisCommand.MODULE, RedisSubCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoadex(final String path, final Map<String, String> configs, final String... arguments) {
		final CommandArguments args = CommandArguments.create(path).add(configs).add(arguments);
		return executeCommand(RedisCommand.MODULE, RedisSubCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoadex(final byte[] path, final Map<byte[], byte[]> configs, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create(path).add(configs).add(arguments);
		return executeCommand(RedisCommand.MODULE, RedisSubCommand.MODULE_LOAD, args);
	}

	@Override
	public Status moduleUnLoad(final String name) {
		final CommandArguments args = CommandArguments.create(name);
		return executeCommand(RedisCommand.MODULE, RedisSubCommand.MODULE_UNLOAD, args);
	}

	@Override
	public Status moduleUnLoad(final byte[] name) {
		final CommandArguments args = CommandArguments.create(name);
		return executeCommand(RedisCommand.MODULE, RedisSubCommand.MODULE_UNLOAD, args);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor) {
		executeCommand(RedisCommand.MONITOR);
	}

	@Override
	public Object pSync(final String replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create(replicationId).add(offset);
		return executeCommand(RedisCommand.PSYNC, args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create(replicationId).add(offset);
		return executeCommand(RedisCommand.PSYNC, args);
	}

	@Override
	public Status replconf() {
		return executeCommand(RedisCommand.REPLCONF);
	}

	@Override
	public Status replicaOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);
		return executeCommand(RedisCommand.REPLICAOF, args);
	}

	@Override
	public Status restoreAsking(final String key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(serializedValue);
		return executeCommand(RedisCommand.RESTORE_ASKING, args);
	}

	@Override
	public Status restoreAsking(final byte[] key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(serializedValue);
		return executeCommand(RedisCommand.RESTORE_ASKING, args);
	}

	@Override
	public Status restoreAsking(final String key, final byte[] serializedValue, final int ttl,
	                            final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(serializedValue).add(argument);
		return executeCommand(RedisCommand.RESTORE_ASKING, args);
	}

	@Override
	public Status restoreAsking(final byte[] key, byte[] serializedValue, final int ttl,
	                            final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(serializedValue).add(argument);
		return executeCommand(RedisCommand.RESTORE_ASKING, args);
	}

	@Override
	public Role role() {
		return executeCommand(RedisCommand.ROLE, (cmd)->cmd.role(), (cmd)->cmd.role(), new RoleConverter());
	}

	@Override
	public Status save() {
		return executeCommand(RedisCommand.SAVE, (cmd)->cmd.save(), (cmd)->cmd.save(), new OkStatusConverter());
	}

	@Override
	public void shutdown() {
		executeCommand(RedisCommand.SHUTDOWN, (cmd)->{
			cmd.shutdown(true);
			return null;
		}, (cmd)->{
			cmd.shutdown(true);
			return null;
		});
	}

	@Override
	public void shutdown(final ShutdownArgument argument) {
		final CommandArguments args = CommandArguments.create(argument);
		executeCommand(RedisCommand.SHUTDOWN, args, (cmd)->{
			cmd.shutdown(new LettuceShutdownArgs(argument));
			return null;
		}, (cmd)->{
			cmd.shutdown(new LettuceShutdownArgs(argument));
			return null;
		});
	}

	@Override
	public Status slaveOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host, port);
		return executeCommand(RedisCommand.SAVE, args, (cmd)->cmd.slaveof(host, port), (cmd)->cmd.slaveof(host, port),
				new OkStatusConverter());
	}

	@Override
	public List<SlowLog> slowLogGet() {
		return executeCommand(RedisCommand.SLOWLOG, RedisSubCommand.SLOWLOG_GET, (cmd)->cmd.slowlogGet(),
				(cmd)->cmd.slowlogGet(), new ListConverter<>(new SlowlogConverter()));
	}

	@Override
	public List<SlowLog> slowLogGet(final int count) {
		final CommandArguments args = CommandArguments.create(count);
		return executeCommand(RedisCommand.SLOWLOG, RedisSubCommand.SLOWLOG_GET, args, (cmd)->cmd.slowlogGet(count),
				(cmd)->cmd.slowlogGet(count), new ListConverter<>(new SlowlogConverter()));
	}

	@Override
	public Long slowLogLen() {
		return executeCommand(RedisCommand.SLOWLOG, RedisSubCommand.SLOWLOG_LEN, (cmd)->cmd.slowlogLen(),
				(cmd)->cmd.slowlogLen());
	}

	@Override
	public Status slowLogReset() {
		return executeCommand(RedisCommand.SLOWLOG, RedisSubCommand.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
				(cmd)->cmd.slowlogReset(), new OkStatusConverter());
	}

	@Override
	public Status swapdb(final int db1, final int db2) {
		final CommandArguments args = CommandArguments.create(db1).add(db2);
		return executeCommand(RedisCommand.SWAPDB, args);
	}

	@Override
	public void sync() {
		executeCommand(RedisCommand.SYNC);
	}

	@Override
	public RedisServerTime time() {
		return executeCommand(RedisCommand.TIME, (cmd)->cmd.time(), (cmd)->cmd.time(), new RedisServerTimeConverter());
	}

}
