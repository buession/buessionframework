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

import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.Info;
import com.buession.redis.core.MemoryStats;
import com.buession.redis.core.Module;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.internal.convert.jedis.params.FlushModeConverter;
import com.buession.redis.core.internal.convert.jedis.response.MemoryStatsConverter;
import com.buession.redis.core.internal.convert.response.InfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.ModuleConverter;
import com.buession.redis.core.internal.convert.jedis.response.RedisServerTimeConverter;
import com.buession.redis.core.internal.convert.jedis.response.SlowlogConverter;
import com.buession.redis.core.internal.convert.jedis.response.RoleConverter;
import com.buession.redis.core.internal.jedis.JedisFailoverParams;
import redis.clients.jedis.JedisMonitor;
import redis.clients.jedis.args.SaveMode;
import redis.clients.jedis.params.FailoverParams;
import redis.clients.jedis.resps.Slowlog;

import java.util.List;
import java.util.Map;

/**
 * Jedis 哨兵模式服务端命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelServerOperations extends AbstractServerOperations<JedisSentinelClient> {

	public JedisSentinelServerOperations(final JedisSentinelClient client) {
		super(client);
	}

	@Override
	public String bgRewriteAof() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, Command.BGREWRITEAOF)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, Command.BGREWRITEAOF)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.BGREWRITEAOF, (cmd)->cmd.bgrewriteaof(), (v)->v)
					.run();
		}
	}

	@Override
	public String bgSave() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, Command.BGSAVE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, Command.BGSAVE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.BGSAVE, (cmd)->cmd.bgsave(), (v)->v)
					.run();
		}
	}

	@Override
	public Status configSet(final String parameter, final String value) {
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CONFIG_SET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CONFIG_SET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CONFIG_SET,
					(cmd)->cmd.configSet(parameter, value), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value) {
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CONFIG_SET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CONFIG_SET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CONFIG_SET,
					(cmd)->cmd.configSet(parameter, value), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status configSet(final Map<String, String> configs) {
		final CommandArguments args = CommandArguments.create("configs", configs);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CONFIG_SET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CONFIG_SET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CONFIG_SET, (cmd)->{
				configs.forEach(cmd::configSet);
				return Status.SUCCESS;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<String, String> configGet(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Map<String, String>, Map<String, String>>(client,
					Command.CONFIG_GET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Map<String, String>, Map<String, String>>(client,
					Command.CONFIG_GET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CONFIG_GET, (cmd)->cmd.configGet(pattern),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Map<byte[], byte[]> configGet(final byte[] parameter) {
		final CommandArguments args = CommandArguments.create("parameter", parameter);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Map<byte[], byte[]>, Map<byte[], byte[]>>(client,
					Command.CONFIG_GET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Map<byte[], byte[]>, Map<byte[], byte[]>>(client,
					Command.CONFIG_GET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CONFIG_GET, (cmd)->cmd.configGet(parameter),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Status configResetStat() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CONFIG_RESETSTAT)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CONFIG_RESETSTAT)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.CONFIG_RESETSTAT, (cmd)->cmd.configResetStat(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status configRewrite() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CONFIG_REWRITE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CONFIG_REWRITE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.CONFIG_REWRITE, (cmd)->cmd.configRewrite(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Long dbSize() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.DBSIZE, (cmd)->cmd.dbSize(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, Command.DBSIZE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.DBSIZE, (cmd)->cmd.dbSize(), (v)->v)
					.run();
		}
	}

	@Override
	public Status failover() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.FAILOVER)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.FAILOVER)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.FAILOVER, (cmd)->cmd.failover(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status failover(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final FailoverParams failoverParams = new JedisFailoverParams(host, port);

		return failover(failoverParams, args);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("timeout", timeout);
		final FailoverParams failoverParams = new JedisFailoverParams(host, port, timeout);

		return failover(failoverParams, args);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);
		final FailoverParams failoverParams = new JedisFailoverParams(host, port, timeout, isForce);

		return failover(failoverParams, args);
	}

	@Override
	public Status failover(final int timeout) {
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		final FailoverParams failoverParams = new JedisFailoverParams(timeout);

		return failover(failoverParams, args);
	}

	@Override
	public Status flushAll() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.FLUSHALL)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.FLUSHALL)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.FLUSHALL, (cmd)->cmd.flushAll(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushAll(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);
		final redis.clients.jedis.args.FlushMode flushMode = (new FlushModeConverter()).convert(mode);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.FLUSHALL)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.FLUSHALL)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.FLUSHALL, (cmd)->cmd.flushAll(flushMode),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status flushDb() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.FLUSHDB)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.FLUSHDB)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.FLUSHDB, (cmd)->cmd.flushDB(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushDb(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);
		final redis.clients.jedis.args.FlushMode flushMode = (new FlushModeConverter()).convert(mode);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.FLUSHDB)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.FLUSHDB)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.FLUSHDB, (cmd)->cmd.flushDB(flushMode),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Info info() {
		final InfoConverter infoConverter = new InfoConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Info, Info>(client, Command.INFO)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Info, Info>(client, Command.INFO)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.INFO, (cmd)->cmd.info(), infoConverter)
					.run();
		}
	}

	@Override
	public Info info(final Info.Section section) {
		final CommandArguments args = CommandArguments.create("section", section);
		final String sectionName = section.name().toLowerCase();
		final InfoConverter infoConverter = new InfoConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Info, Info>(client, Command.INFO)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Info, Info>(client, Command.INFO)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.INFO, (cmd)->cmd.info(sectionName), infoConverter)
					.run(args);
		}
	}

	@Override
	public Long lastSave() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, Command.LASTSAVE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, Command.LASTSAVE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.LASTSAVE, (cmd)->cmd.lastsave(), (v)->v)
					.run();
		}
	}

	@Override
	public String memoryDoctor() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, Command.MEMORY_DOCTOR)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, Command.MEMORY_DOCTOR)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.MEMORY_DOCTOR, (cmd)->cmd.memoryDoctor(), (v)->v)
					.run();
		}
	}

	@Override
	public Status memoryPurge() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.MEMORY_PURGE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.MEMORY_PURGE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.MEMORY_PURGE, (cmd)->cmd.memoryPurge(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public MemoryStats memoryStats() {
		final MemoryStatsConverter memoryStatsConverter = new MemoryStatsConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<MemoryStats, MemoryStats>(client, Command.MEMORY_STATS)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<MemoryStats, MemoryStats>(client, Command.MEMORY_STATS)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.MEMORY_STATS, (cmd)->cmd.memoryStats(),
					memoryStatsConverter)
					.run();
		}
	}

	@Override
	public Long memoryUsage(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final String key, final int samples) {
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples) {
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Module> moduleList() {
		final ListConverter<redis.clients.jedis.Module, Module> listModuleConverter = ModuleConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<Module>, List<Module>>(client, Command.MODULE_LIST)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<Module>, List<Module>>(client, Command.MODULE_LIST)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.MODULE_LIST, (cmd)->cmd.moduleList(),
					listModuleConverter)
					.run();
		}
	}

	@Override
	public Status moduleLoad(final String path) {
		final CommandArguments args = CommandArguments.create("path", path);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.MODULE_LOAD)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.MODULE_LOAD)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.MODULE_LOAD, (cmd)->cmd.moduleLoad(path),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments) {
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", (Object[]) arguments);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.MODULE_LOAD)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.MODULE_LOAD)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.MODULE_LOAD,
					(cmd)->cmd.moduleLoad(path, arguments), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status moduleUnLoad(final String name) {
		final CommandArguments args = CommandArguments.create("name", name);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.MODULE_UNLOAD)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.MODULE_UNLOAD)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.MODULE_UNLOAD, (cmd)->cmd.moduleUnload(name),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor) {
		final CommandArguments args = CommandArguments.create("redisMonitor", redisMonitor);

		if(isPipeline()){
			new JedisSentinelPipelineCommand<>(client, Command.MONITOR)
					.run(args);
		}else if(isTransaction()){
			new JedisSentinelTransactionCommand<>(client, Command.MONITOR)
					.run(args);
		}else{
			new JedisSentinelCommand<>(client, Command.MONITOR, (cmd)->{
				cmd.monitor(new JedisMonitor() {

					@Override
					public void onCommand(final String command) {
						redisMonitor.onCommand(command);
					}

				});
				return null;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public Object pSync(final String replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return notCommand(client, Command.PSYNC, args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create("replicationId", replicationId).put("offset", offset);
		return notCommand(client, Command.PSYNC, args);
	}

	@Override
	public void sync() {
		if(isPipeline()){
			new JedisSentinelPipelineCommand<>(client, Command.SYNC, (cmd)->{
				cmd.sync();
				return null;
			}, (v)->v)
					.run();
		}else if(isTransaction()){
			new JedisSentinelTransactionCommand<>(client, Command.SYNC)
					.run();
		}else{
			new JedisSentinelCommand<>(client, Command.SYNC)
					.run();
		}
	}

	@Override
	public Status replicaOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.REPLICAOF)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.REPLICAOF)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.REPLICAOF, (cmd)->cmd.replicaof(host, port),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status slaveOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.SLAVEOF)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.SLAVEOF)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SLAVEOF, (cmd)->cmd.slaveof(host, port),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Role role() {
		final RoleConverter roleConverter = new RoleConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Role, Role>(client, Command.ROLE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Role, Role>(client, Command.ROLE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ROLE, (cmd)->cmd.role(), roleConverter)
					.run();
		}
	}

	@Override
	public Status save() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.SAVE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.SAVE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.SAVE, (cmd)->cmd.save(), okStatusConverter)
					.run();
		}
	}

	@Override
	public void shutdown() {
		if(isPipeline()){
			new JedisSentinelPipelineCommand<>(client, Command.SHUTDOWN)
					.run();
		}else if(isTransaction()){
			new JedisSentinelTransactionCommand<>(client, Command.SHUTDOWN)
					.run();
		}else{
			new JedisSentinelCommand<>(client, Command.SHUTDOWN, (cmd)->{
				cmd.shutdown();
				return null;
			}, (v)->v)
					.run();
		}
	}

	@Override
	public void shutdown(final boolean save) {
		final CommandArguments args = CommandArguments.create("save", save);
		final SaveMode saveMode = save ? SaveMode.SAVE : SaveMode.NOSAVE;

		if(isPipeline()){
			new JedisSentinelPipelineCommand<>(client, Command.SHUTDOWN)
					.run();
		}else if(isTransaction()){
			new JedisSentinelTransactionCommand<>(client, Command.SHUTDOWN)
					.run();
		}else{
			new JedisSentinelCommand<>(client, Command.SHUTDOWN, (cmd)->{
				cmd.shutdown(saveMode);
				return null;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public List<SlowLog> slowLogGet() {
		final ListConverter<Slowlog, SlowLog> listSlowlogConverter = SlowlogConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<SlowLog>, List<SlowLog>>(client, Command.SLOWLOG_GET)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<SlowLog>, List<SlowLog>>(client,
					Command.SLOWLOG_GET)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.SLOWLOG_GET, (cmd)->cmd.slowlogGet(),
					listSlowlogConverter)
					.run();
		}
	}

	@Override
	public List<SlowLog> slowLogGet(final int count) {
		final CommandArguments args = CommandArguments.create("count", count);
		final ListConverter<Slowlog, SlowLog> listSlowlogConverter = SlowlogConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<SlowLog>, List<SlowLog>>(client, Command.SLOWLOG_GET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<SlowLog>, List<SlowLog>>(client,
					Command.SLOWLOG_GET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SLOWLOG_GET, (cmd)->cmd.slowlogGet(count),
					listSlowlogConverter)
					.run(args);
		}
	}

	@Override
	public Long slowLogLen() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, Command.SLOWLOG_LEN)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, Command.SLOWLOG_LEN)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.SLOWLOG_RESET, (cmd)->cmd.slowlogLen(), (v)->v)
					.run();
		}
	}

	@Override
	public Status slowLogReset() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.SLOWLOG_RESET)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.SLOWLOG_RESET)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status swapdb(final int db1, final int db2) {
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SWAPDB, (cmd)->cmd.swapDB(db1, db2),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.SWAPDB)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SWAPDB, (cmd)->cmd.swapDB(db1, db2),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public RedisServerTime time() {
		final RedisServerTimeConverter redisServerTimeConverter = new RedisServerTimeConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.TIME, (cmd)->cmd.time(),
					redisServerTimeConverter)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<RedisServerTime, RedisServerTime>(client, Command.TIME)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.TIME, (cmd)->cmd.time(), redisServerTimeConverter)
					.run();
		}
	}

	private Status failover(final FailoverParams failoverParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.FAILOVER)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.FAILOVER)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.FAILOVER, (cmd)->cmd.failover(failoverParams),
					okStatusConverter)
					.run(args);
		}
	}

}
