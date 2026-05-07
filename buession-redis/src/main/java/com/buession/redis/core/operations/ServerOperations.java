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
package com.buession.redis.core.operations;

import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.CommandDoc;
import com.buession.redis.core.CommandInfo;
import com.buession.redis.core.CommandKeyAndFlag;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.FlushMode;
import com.buession.redis.core.HotKey;
import com.buession.redis.core.Info;
import com.buession.redis.core.LatencyHistogram;
import com.buession.redis.core.LatencyHistory;
import com.buession.redis.core.LatencyLatest;
import com.buession.redis.core.MemoryStats;
import com.buession.redis.core.Module;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.ServerCommands;
import com.buession.redis.core.command.args.acl.AclSetUserArgument;
import com.buession.redis.core.command.args.server.FailoverArgument;
import com.buession.redis.core.command.args.server.HotkeysStartArgument;
import com.buession.redis.core.command.args.RestoreArgument;
import com.buession.redis.core.command.args.server.ShutdownArgument;
import com.buession.redis.utils.KeyUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 服务端运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=server" target="_blank">https://redis.io/docs/latest/commands/?group=server</a></p>
 *
 * @author Yong.Teng
 */
public interface ServerOperations extends ServerCommands, RedisOperations {

	@Override
	default Set<AclCategory> aclCat() {
		return doExecute((cmd)->cmd.aclCat());
	}

	@Override
	default Set<RedisCommand> aclCat(final String categoryName) {
		return doExecute((cmd)->cmd.aclCat(categoryName));
	}

	@Override
	default Set<RedisCommand> aclCat(final byte[] categoryName) {
		return doExecute((cmd)->cmd.aclCat(categoryName));
	}

	@Override
	default Long aclDelUser(final String... usernames) {
		return doExecute((cmd)->cmd.aclDelUser(usernames));
	}

	@Override
	default Long aclDelUser(final byte[]... username) {
		return doExecute((cmd)->cmd.aclDelUser(username));
	}

	@Override
	default Status aclDryRun(final String username, final RedisCommand command) {
		return doExecute((cmd)->cmd.aclDryRun(username, command));
	}

	@Override
	default Status aclDryRun(final byte[] username, final RedisCommand command) {
		return doExecute((cmd)->cmd.aclDryRun(username, command));
	}

	@Override
	default Status aclDryRun(final String username, final RedisCommand command, final String... arguments) {
		return doExecute((cmd)->cmd.aclDryRun(username, command, arguments));
	}

	@Override
	default Status aclDryRun(final byte[] username, final RedisCommand command, final byte[]... arguments) {
		return doExecute((cmd)->cmd.aclDryRun(username, command, arguments));
	}

	@Override
	default String aclGenPass() {
		return doExecute((cmd)->cmd.aclGenPass());
	}

	@Override
	default String aclGenPass(final int bits) {
		return doExecute((cmd)->cmd.aclGenPass(bits));
	}

	@Override
	default AclUser aclGetUser(final String username) {
		return doExecute((cmd)->cmd.aclGetUser(username));
	}

	@Override
	default AclUser aclGetUser(final byte[] username) {
		return doExecute((cmd)->cmd.aclGetUser(username));
	}

	@Override
	default List<String> aclList() {
		return doExecute((cmd)->cmd.aclList());
	}

	@Override
	default Status aclLoad() {
		return doExecute((cmd)->cmd.aclLoad());
	}

	@Override
	default List<AclLog> aclLog() {
		return doExecute((cmd)->cmd.aclLog());
	}

	@Override
	default List<AclLog> aclLog(final int count) {
		return doExecute((cmd)->cmd.aclLog(count));
	}

	@Override
	default Status aclLogReset() {
		return doExecute((cmd)->cmd.aclLogReset());
	}

	@Override
	default Status aclSave() {
		return doExecute((cmd)->cmd.aclSave());
	}

	@Override
	default Status aclSetUser(final String username, final AclSetUserArgument argument) {
		return doExecute((cmd)->cmd.aclSetUser(username, argument));
	}

	@Override
	default Status aclSetUser(final byte[] username, final AclSetUserArgument argument) {
		return doExecute((cmd)->cmd.aclSetUser(username, argument));
	}

	@Override
	default List<String> aclUsers() {
		return doExecute((cmd)->cmd.aclUsers());
	}

	@Override
	default String aclWhoAmI() {
		return doExecute((cmd)->cmd.aclWhoAmI());
	}

	@Override
	default String bgRewriteAof() {
		return doExecute((cmd)->cmd.bgRewriteAof());
	}

	@Override
	default String bgSave() {
		return doExecute((cmd)->cmd.bgSave());
	}

	@Override
	default Integer commandCount() {
		return doExecute((cmd)->cmd.commandCount());
	}

	@Override
	default List<CommandDoc> commandDocs() {
		return doExecute((cmd)->cmd.commandDocs());
	}

	@Override
	default List<CommandDoc> commandDocs(final RedisCommand... commands) {
		return doExecute((cmd)->cmd.commandDocs(commands));
	}

	@Override
	default List<String> commandGetKeys(final RedisCommand command) {
		return doExecute((cmd)->cmd.commandGetKeys(command));
	}

	@Override
	default List<String> commandGetKeys(final RedisCommand command, final String... arguments) {
		return doExecute((cmd)->cmd.commandGetKeys(command, arguments));
	}

	@Override
	default List<CommandKeyAndFlag> commandGetKeysAndFlags(final RedisCommand command) {
		return doExecute((cmd)->cmd.commandGetKeysAndFlags(command));
	}

	@Override
	default List<CommandKeyAndFlag> commandGetKeysAndFlags(final RedisCommand command, final String... arguments) {
		return doExecute((cmd)->cmd.commandGetKeysAndFlags(command, arguments));
	}

	@Override
	default List<CommandInfo> commandInfo(final RedisCommand... commands) {
		return doExecute((cmd)->cmd.commandInfo(commands));
	}

	@Override
	default List<RedisCommand> commandList() {
		return doExecute((cmd)->cmd.commandList());
	}

	@Override
	default Map<String, String> configGet(final String... parameters) {
		return doExecute((cmd)->cmd.configGet(parameters));
	}

	@Override
	default Map<byte[], byte[]> configGet(final byte[]... parameters) {
		return doExecute((cmd)->cmd.configGet(parameters));
	}

	@Override
	default Status configResetStat() {
		return doExecute((cmd)->cmd.configResetStat());
	}

	@Override
	default Status configRewrite() {
		return doExecute((cmd)->cmd.configRewrite());
	}

	@Override
	default Status configSet(final String parameter, final String value) {
		return doExecute((cmd)->cmd.configSet(parameter, value));
	}

	@Override
	default Status configSet(final byte[] parameter, final byte[] value) {
		return doExecute((cmd)->cmd.configSet(parameter, value));
	}

	@Override
	default Status configSet(final Map<String, String> configs) {
		return doExecute((cmd)->cmd.configSet(configs));
	}

	@Override
	default Long dbSize() {
		return doExecute((cmd)->cmd.dbSize());
	}

	@Override
	default Status failover() {
		return doExecute((cmd)->cmd.failover());
	}

	@Override
	default Status failover(final FailoverArgument argument) {
		return doExecute((cmd)->cmd.failover(argument));
	}

	@Override
	default Status flushAll() {
		return doExecute((cmd)->cmd.flushAll());
	}

	@Override
	default Status flushAll(final FlushMode mode) {
		return doExecute((cmd)->cmd.flushAll(mode));
	}

	@Override
	default Status flushDb() {
		return doExecute((cmd)->cmd.flushDb());
	}

	@Override
	default Status flushDb(final FlushMode mode) {
		return doExecute((cmd)->cmd.flushDb(mode));
	}

	@Override
	default List<HotKey> hotkeysGet() {
		return doExecute((cmd)->cmd.hotkeysGet());
	}

	@Override
	default Status hotkeysReset() {
		return doExecute((cmd)->cmd.hotkeysReset());
	}

	@Override
	default Status hotkeysStart(final int count) {
		return doExecute((cmd)->cmd.hotkeysStart(count));
	}

	@Override
	default Status hotkeysStart(final int count, final HotkeysStartArgument argument) {
		return doExecute((cmd)->cmd.hotkeysStart(count, argument));
	}

	@Override
	default Status hotkeysStop() {
		return doExecute((cmd)->cmd.hotkeysStop());
	}

	@Override
	default Info info() {
		return doExecute((cmd)->cmd.info());
	}

	@Override
	default Info info(final Info.Section section) {
		return doExecute((cmd)->cmd.info(section));
	}

	@Override
	default Long lastSave() {
		return doExecute((cmd)->cmd.lastSave());
	}

	/**
	 * 获取最近一次 Redis 成功将数据保存到磁盘上的时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/lastsave.html" target="_blank">http://redisdoc.com/persistence/lastsave.html</a></p>
	 *
	 * @return 最近一次成功将数据保存到磁盘上的时间
	 */
	default Date lastSaveAt() {
		return new Date(lastSave());
	}

	@Override
	default String latencyDoctor() {
		return doExecute((cmd)->cmd.latencyDoctor());
	}

	@Override
	default String latencyGraph() {
		return doExecute((cmd)->cmd.latencyGraph());
	}

	@Override
	default List<LatencyHistogram> latencyHistogram() {
		return doExecute((cmd)->cmd.latencyHistogram());
	}

	@Override
	default List<LatencyHistogram> latencyHistogram(final RedisCommand... commands) {
		return doExecute((cmd)->cmd.latencyHistogram(commands));
	}

	@Override
	default List<LatencyHistory> latencyHistory(final String event) {
		return doExecute((cmd)->cmd.latencyHistory(event));
	}

	@Override
	default List<LatencyLatest> latencyLatest() {
		return doExecute((cmd)->cmd.latencyLatest());
	}

	@Override
	default Status latencyReset() {
		return doExecute((cmd)->cmd.latencyReset());
	}

	@Override
	default Status latencyReset(final String... events) {
		return doExecute((cmd)->cmd.latencyReset(events));
	}

	@Override
	default String lolwut() {
		return doExecute((cmd)->cmd.lolwut());
	}

	@Override
	default String lolwut(final String version) {
		return doExecute((cmd)->cmd.lolwut(version));
	}

	@Override
	default String memoryDoctor() {
		return doExecute((cmd)->cmd.memoryDoctor());
	}

	@Override
	default String memoryMallocStats() {
		return doExecute((cmd)->cmd.memoryMallocStats());
	}

	@Override
	default Status memoryPurge() {
		return doExecute((cmd)->cmd.memoryPurge());
	}

	@Override
	default MemoryStats memoryStats() {
		return doExecute((cmd)->cmd.memoryStats());
	}

	@Override
	default Long memoryUsage(final String key) {
		return doExecute((cmd)->cmd.memoryUsage(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long memoryUsage(final byte[] key) {
		return doExecute((cmd)->cmd.memoryUsage(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long memoryUsage(final String key, final int samples) {
		return doExecute((cmd)->cmd.memoryUsage(KeyUtils.rawKey(this, key), samples));
	}

	@Override
	default Long memoryUsage(final byte[] key, final int samples) {
		return doExecute((cmd)->cmd.memoryUsage(KeyUtils.rawKey(this, key), samples));
	}

	@Override
	default List<Module> moduleList() {
		return doExecute((cmd)->cmd.moduleList());
	}

	@Override
	default Status moduleLoad(final String path, final String... arguments) {
		return doExecute((cmd)->cmd.moduleLoad(path, arguments));
	}

	@Override
	default Status moduleLoad(final byte[] path, final byte[]... arguments) {
		return doExecute((cmd)->cmd.moduleLoad(path, arguments));
	}

	@Override
	default Status moduleLoadex(final String path, final Map<String, String> configs, final String... arguments) {
		return doExecute((cmd)->cmd.moduleLoadex(path, configs, arguments));
	}

	@Override
	default Status moduleLoadex(final byte[] path, final Map<byte[], byte[]> configs, final byte[]... arguments) {
		return doExecute((cmd)->cmd.moduleLoadex(path, configs, arguments));
	}

	@Override
	default Status moduleUnLoad(final String name) {
		return doExecute((cmd)->cmd.moduleUnLoad(name));
	}

	@Override
	default Status moduleUnLoad(final byte[] name) {
		return doExecute((cmd)->cmd.moduleUnLoad(name));
	}

	@Override
	default void monitor(final RedisMonitor redisMonitor) {
		doExecute((cmd)->{
			cmd.monitor(redisMonitor);
			return null;
		});
	}

	@Override
	default Object pSync(final String replicationId, final long offset) {
		return doExecute((cmd)->cmd.pSync(replicationId, offset));
	}

	@Override
	default Object pSync(final byte[] replicationId, final long offset) {
		return doExecute((cmd)->cmd.pSync(replicationId, offset));
	}

	@Override
	default Status replconf() {
		return doExecute((cmd)->cmd.replconf());
	}

	@Override
	default Status replicaOf(final String host, final int port) {
		return doExecute((cmd)->cmd.replicaOf(host, port));
	}

	/**
	 * 用于在 Redis 运行时动态地修改复制(replication)功能的行为；
	 * 可以将当前服务器转变为指定服务器的从属服务器(slave server)
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/replicaof/" target="_blank">https://redis.io/commands/replicaof/</a></p>
	 *
	 * @param host
	 * 		Redis Slave Server 主机地址
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	default Status replicaOf(final String host) {
		return replicaOf(host, RedisNode.DEFAULT_PORT);
	}

	/**
	 * 可以在线修改当前服务器的复制设置
	 * 如果当前服务器已经是副本服务器，会将当前服务器转变为某一服务器的副本服务器；
	 * 如果当前服务器已经是某个主服务器的副本服务器，那么将使当前服务器停止对原主服务器的同步，丢弃旧数据集，转而开始对新主服务器进行同步
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/replicaof/" target="_blank">https://redis.io/commands/replicaof/</a></p>
	 *
	 * @param server
	 * 		Redis 主机
	 *
	 * @return 操作结果
	 */
	default Status replicaOf(final RedisNode server) {
		Assert.isNull(server, "Redis server cloud not be null.");
		return replicaOf(server.getHost(), server.getPort());
	}

	@Override
	default Status restoreAsking(final String key, final byte[] serializedValue, final int ttl) {
		return doExecute((cmd)->cmd.restoreAsking(KeyUtils.rawKey(this, key), serializedValue, ttl));
	}

	@Override
	default Status restoreAsking(final byte[] key, final byte[] serializedValue, final int ttl) {
		return doExecute((cmd)->cmd.restoreAsking(KeyUtils.rawKey(this, key), serializedValue, ttl));
	}

	@Override
	default Status restoreAsking(final String key, final byte[] serializedValue, final int ttl,
	                             final RestoreArgument argument) {
		return doExecute((cmd)->cmd.restoreAsking(KeyUtils.rawKey(this, key), serializedValue, ttl, argument));
	}

	@Override
	default Status restoreAsking(final byte[] key, final byte[] serializedValue, final int ttl,
	                             final RestoreArgument argument) {
		return doExecute((cmd)->cmd.restoreAsking(KeyUtils.rawKey(this, key), serializedValue, ttl, argument));
	}

	@Override
	default Role role() {
		return doExecute((cmd)->cmd.role());
	}

	@Override
	default Status save() {
		return doExecute((cmd)->cmd.save());
	}

	@Override
	default void shutdown() {
		doExecute((cmd)->{
			cmd.shutdown();
			return null;
		});
	}

	@Override
	default void shutdown(final ShutdownArgument argument) {
		doExecute((cmd)->{
			cmd.shutdown(argument);
			return null;
		});
	}

	@Override
	default Status slaveOf(final String host, final int port) {
		return doExecute((cmd)->cmd.slaveOf(host, port));
	}

	/**
	 * 用于在 Redis 运行时动态地修改复制(replication)功能的行为；
	 * 可以将当前服务器转变为指定服务器的从属服务器(slave server)
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/replication/slaveof.html" target="_blank">http://redisdoc.com/replication/slaveof.html</a></p>
	 *
	 * @param host
	 * 		Redis Slave Server 主机地址
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	default Status slaveOf(final String host) {
		return slaveOf(host, RedisNode.DEFAULT_PORT);
	}

	/**
	 * 用于在 Redis 运行时动态地修改复制(replication)功能的行为；
	 * 可以将当前服务器转变为指定服务器的从属服务器(slave server)
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/replication/slaveof.html" target="_blank">http://redisdoc.com/replication/slaveof.html</a></p>
	 *
	 * @param server
	 * 		Redis Slave Server 主机地址
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	default Status slaveOf(final RedisNode server) {
		Assert.isNull(server, "Redis server cloud not be null.");
		return replicaOf(server.getHost(), server.getPort());
	}

	@Override
	default List<SlowLog> slowLogGet() {
		return doExecute((cmd)->cmd.slowLogGet());
	}

	@Override
	default List<SlowLog> slowLogGet(final int count) {
		return doExecute((cmd)->cmd.slowLogGet(count));
	}

	@Override
	default Long slowLogLen() {
		return doExecute((cmd)->cmd.slowLogLen());
	}

	@Override
	default Status slowLogReset() {
		return doExecute((cmd)->cmd.slowLogReset());
	}

	@Override
	default Status swapdb(final int db1, final int db2) {
		return doExecute((cmd)->cmd.swapdb(db1, db2));
	}

	@Override
	default void sync() {
		doExecute((cmd)->{
			cmd.sync();
			return null;
		});
	}

	@Override
	default RedisServerTime time() {
		return doExecute((cmd)->cmd.time());
	}

	private <R> R doExecute(final Command.Executor<ServerCommands, R> executor) {
		return execute((client)->executor.execute(client.serverCommands()));
	}

}
