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

import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
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
import com.buession.redis.core.command.Command;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.RedisServerTimeConverter;
import com.buession.redis.core.internal.convert.lettuce.response.RoleConverter;
import com.buession.redis.core.internal.convert.lettuce.response.SlowlogConverter;
import com.buession.redis.core.internal.convert.response.InfoConverter;
import com.buession.redis.utils.SafeEncoder;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	public String bgRewriteAof() {
		if(isPipeline()){
			return new LettucePipelineCommand<String, String>(client, Command.BGREWRITEAOF)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<String, String>(client, Command.BGREWRITEAOF)
					.run();
		}else{
			return new LettuceCommand<String, String>(client, Command.BGREWRITEAOF)
					.run();
		}
	}

	@Override
	public String bgSave() {
		if(isPipeline()){
			return new LettucePipelineCommand<String, String>(client, Command.BGSAVE)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<String, String>(client, Command.BGSAVE)
					.run();
		}else{
			return new LettuceCommand<String, String>(client, Command.BGSAVE)
					.run();
		}
	}

	@Override
	public Status configSet(final String parameter, final String value) {
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CONFIG_SET,
					(cmd)->cmd.configSet(parameter, value), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CONFIG_SET,
					(cmd)->cmd.configSet(parameter, value), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CONFIG_SET, (cmd)->cmd.configSet(parameter, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status configSet(final Map<String, String> configs) {
		final CommandArguments args = CommandArguments.create("configs", configs);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CONFIG_SET,
					(cmd)->cmd.configSet(configs), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CONFIG_SET,
					(cmd)->cmd.configSet(configs), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CONFIG_SET, (cmd)->cmd.configSet(configs),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Map<String, String> configGet(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CONFIG_GET,
					(cmd)->cmd.configGet(pattern), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CONFIG_GET,
					(cmd)->cmd.configGet(pattern), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CONFIG_GET, (cmd)->cmd.configGet(pattern), (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<byte[], byte[]> configGet(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		final String sPattern = SafeEncoder.encode(pattern);
		final MapConverter<String, String, byte[], byte[]> converter = Converters.mapStringToBinary();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CONFIG_GET, (cmd)->cmd.configGet(sPattern),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CONFIG_GET, (cmd)->cmd.configGet(sPattern),
					converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CONFIG_GET, (cmd)->cmd.configGet(sPattern),
					converter)
					.run(args);
		}
	}

	@Override
	public Status configResetStat() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CONFIG_RESETSTAT, (cmd)->cmd.configResetstat(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CONFIG_RESETSTAT,
					(cmd)->cmd.configResetstat(), okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.CONFIG_RESETSTAT, (cmd)->cmd.configResetstat(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status configRewrite() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CONFIG_REWRITE, (cmd)->cmd.configRewrite(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CONFIG_REWRITE, (cmd)->cmd.configRewrite(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.CONFIG_REWRITE, (cmd)->cmd.configRewrite(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Long dbSize() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.DBSIZE, (cmd)->cmd.dbsize(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.DBSIZE, (cmd)->cmd.dbsize(), (v)->v)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.DBSIZE, (cmd)->cmd.dbsize(), (v)->v)
					.run();
		}
	}

	@Override
	public Status failover() {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, Command.FAILOVER)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, Command.FAILOVER)
					.run();
		}else{
			return new LettuceCommand<Status, Status>(client, Command.FAILOVER)
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
			return new LettucePipelineCommand<>(client, Command.FLUSHALL, (cmd)->cmd.flushall(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.FLUSHALL, (cmd)->cmd.flushall(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.FLUSHALL, (cmd)->cmd.flushall(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushAll(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.FLUSHALL,
					(cmd)->mode == FlushMode.ASYNC ? cmd.flushallAsync() : cmd.flushall(), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.FLUSHALL,
					(cmd)->mode == FlushMode.ASYNC ? cmd.flushallAsync() : cmd.flushall(), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.FLUSHALL,
					(cmd)->mode == FlushMode.ASYNC ? cmd.flushallAsync() : cmd.flushall(), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status flushDb() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.FLUSHDB, (cmd)->cmd.flushdb(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.FLUSHDB, (cmd)->cmd.flushdb(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.FLUSHDB, (cmd)->cmd.flushdb(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushDb(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.FLUSHDB, (cmd)->mode == FlushMode.ASYNC ?
					cmd.flushdbAsync() : cmd.flushdb(), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.FLUSHDB, (cmd)->mode == FlushMode.ASYNC ?
					cmd.flushdbAsync() : cmd.flushdb(), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.FLUSHDB, (cmd)->mode == FlushMode.ASYNC ?
					cmd.flushdbAsync() : cmd.flushdb(), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Info info() {
		final InfoConverter infoConverter = new InfoConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.FLUSHDB, (cmd)->cmd.info(), infoConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.FLUSHDB, (cmd)->cmd.info(), infoConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.FLUSHDB, (cmd)->cmd.info(), infoConverter)
					.run();
		}
	}

	@Override
	public Info info(final Info.Section section) {
		final CommandArguments args = CommandArguments.create("section", section);
		final String sectionName = section.name().toLowerCase();
		final InfoConverter infoConverter = new InfoConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.FLUSHDB, (cmd)->cmd.info(sectionName),
					infoConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.FLUSHDB, (cmd)->cmd.info(sectionName),
					infoConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.FLUSHDB, (cmd)->cmd.info(sectionName), infoConverter)
					.run(args);
		}
	}

	@Override
	public Long lastSave() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.LASTSAVE, (cmd)->cmd.lastsave(), Date::getTime)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.LASTSAVE, (cmd)->cmd.lastsave(),
					Date::getTime)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.LASTSAVE, (cmd)->cmd.lastsave(), Date::getTime)
					.run();
		}
	}

	@Override
	public String memoryDoctor() {
		if(isPipeline()){
			return new LettucePipelineCommand<String, String>(client, Command.MEMORY_DOCTOR)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<String, String>(client, Command.MEMORY_DOCTOR)
					.run();
		}else{
			return new LettuceCommand<String, String>(client, Command.MEMORY_DOCTOR)
					.run();
		}
	}

	@Override
	public Status memoryPurge() {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, Command.MEMORY_PURGE)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, Command.MEMORY_PURGE)
					.run();
		}else{
			return new LettuceCommand<Status, Status>(client, Command.MEMORY_PURGE)
					.run();
		}
	}

	@Override
	public MemoryStats memoryStats() {
		if(isPipeline()){
			return new LettucePipelineCommand<MemoryStats, MemoryStats>(client, Command.MEMORY_STATS)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<MemoryStats, MemoryStats>(client, Command.MEMORY_STATS)
					.run();
		}else{
			return new LettuceCommand<MemoryStats, MemoryStats>(client, Command.MEMORY_STATS)
					.run();
		}
	}

	@Override
	public Long memoryUsage(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples) {
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Module> moduleList() {
		return notCommand(client, Command.MODULE_LIST);
	}

	@Override
	public Status moduleLoad(final String path) {
		final CommandArguments args = CommandArguments.create("path", path);
		return notCommand(client, Command.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoad(final byte[] path) {
		final CommandArguments args = CommandArguments.create("path", path);
		return notCommand(client, Command.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments) {
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", (Object[]) arguments);
		return notCommand(client, Command.MODULE_LOAD, args);
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create("path", path).put("arguments", (Object[]) arguments);
		return notCommand(client, Command.MODULE_LOAD, args);
	}

	@Override
	public Status moduleUnLoad(final String name) {
		final CommandArguments args = CommandArguments.create("name", name);
		return notCommand(client, Command.MODULE_UNLOAD, args);
	}

	@Override
	public Status moduleUnLoad(final byte[] name) {
		final CommandArguments args = CommandArguments.create("name", name);
		return notCommand(client, Command.MODULE_UNLOAD, args);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor) {
		final CommandArguments args = CommandArguments.create("redisMonitor", redisMonitor);

		if(isPipeline()){
			new LettucePipelineCommand<>(client, Command.MONITOR)
					.run(args);
		}else if(isTransaction()){
			new LettuceTransactionCommand<>(client, Command.MONITOR)
					.run(args);
		}else{
			new LettuceCommand<>(client, Command.MONITOR)
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
			new LettucePipelineCommand<>(client, Command.SYNC)
					.run();
		}else if(isTransaction()){
			new LettuceTransactionCommand<>(client, Command.SYNC)
					.run();
		}else{
			new LettuceCommand<>(client, Command.SYNC)
					.run();
		}
	}

	@Override
	public Status replicaOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);

		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, Command.REPLICAOF)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, Command.REPLICAOF)
					.run(args);
		}else{
			return new LettuceCommand<Status, Status>(client, Command.REPLICAOF)
					.run(args);
		}
	}

	@Override
	public Status slaveOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SLAVEOF, (cmd)->cmd.slaveof(host, port),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SLAVEOF, (cmd)->cmd.slaveof(host, port),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.SLAVEOF, (cmd)->cmd.slaveof(host, port),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Role role() {
		final RoleConverter roleConverter = new RoleConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ROLE, (cmd)->cmd.role(), roleConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ROLE, (cmd)->cmd.role(), roleConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.ROLE, (cmd)->cmd.role(), roleConverter)
					.run();
		}
	}

	@Override
	public Status save() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SAVE, (cmd)->cmd.save(), okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SAVE, (cmd)->cmd.save(), okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.SAVE, (cmd)->cmd.save(), okStatusConverter)
					.run();
		}
	}

	@Override
	public void shutdown() {
		if(isPipeline()){
			new LettucePipelineCommand<>(client, Command.SHUTDOWN, (cmd)->{
				cmd.shutdown(true);
				return null;
			}, (v)->v)
					.run();
		}else if(isTransaction()){
			new LettuceTransactionCommand<>(client, Command.SHUTDOWN, (cmd)->{
				cmd.shutdown(true);
				return null;
			}, (v)->v)
					.run();
		}else{
			new LettuceCommand<>(client, Command.SHUTDOWN, (cmd)->{
				cmd.shutdown(true);
				return null;
			}, (v)->v)
					.run();
		}
	}

	@Override
	public void shutdown(final boolean save) {
		final CommandArguments args = CommandArguments.create("save", save);

		if(isPipeline()){
			new LettucePipelineCommand<>(client, Command.SHUTDOWN, (cmd)->{
				cmd.shutdown(save);
				return null;
			}, (v)->v)
					.run(args);
		}else if(isTransaction()){
			new LettuceTransactionCommand<>(client, Command.SHUTDOWN, (cmd)->{
				cmd.shutdown(save);
				return null;
			}, (v)->v)
					.run(args);
		}else{
			new LettuceCommand<>(client, Command.SHUTDOWN, (cmd)->{
				cmd.shutdown(save);
				return null;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public List<SlowLog> slowLogGet() {
		final ListConverter<Object, SlowLog> listSlowlogConverter = SlowlogConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SLOWLOG_GET, (cmd)->cmd.slowlogGet(),
					listSlowlogConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SLOWLOG_GET, (cmd)->cmd.slowlogGet(),
					listSlowlogConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.SLOWLOG_GET, (cmd)->cmd.slowlogGet(),
					listSlowlogConverter)
					.run();
		}
	}

	@Override
	public List<SlowLog> slowLogGet(final int count) {
		final CommandArguments args = CommandArguments.create("count", count);
		final ListConverter<Object, SlowLog> listSlowlogConverter = SlowlogConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SLOWLOG_GET, (cmd)->cmd.slowlogGet((int) count),
					listSlowlogConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SLOWLOG_GET,
					(cmd)->cmd.slowlogGet((int) count),
					listSlowlogConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.SLOWLOG_GET, (cmd)->cmd.slowlogGet((int) count),
					listSlowlogConverter)
					.run(args);
		}
	}

	@Override
	public Long slowLogLen() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SLOWLOG_LEN, (cmd)->cmd.slowlogLen(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SLOWLOG_LEN, (cmd)->cmd.slowlogLen(), (v)->v)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.SLOWLOG_LEN, (cmd)->cmd.slowlogLen(), (v)->v)
					.run();
		}
	}

	@Override
	public Status slowLogReset() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status swapdb(final int db1, final int db2) {
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SWAPDB, (cmd)->cmd.swapdb(db1, db2),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SWAPDB, (cmd)->cmd.swapdb(db1, db2),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.SWAPDB, (cmd)->cmd.swapdb(db1, db2), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public RedisServerTime time() {
		final RedisServerTimeConverter redisServerTimeConverter = new RedisServerTimeConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.TIME, (cmd)->cmd.time(),
					redisServerTimeConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.TIME, (cmd)->cmd.time(),
					redisServerTimeConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.TIME, (cmd)->cmd.time(), redisServerTimeConverter)
					.run();
		}
	}

	private AclUser aclGetUser(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<AclUser, AclUser>(client, Command.ACL_GETUSER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<AclUser, AclUser>(client, Command.ACL_GETUSER)
					.run(args);
		}else{
			return new LettuceCommand<AclUser, AclUser>(client, Command.ACL_GETUSER)
					.run(args);
		}
	}

	private Long aclDelUser(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<Long, Long>(client, Command.ACL_DELUSER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Long, Long>(client, Command.ACL_DELUSER)
					.run(args);
		}else{
			return new LettuceCommand<Long, Long>(client, Command.ACL_DELUSER)
					.run(args);
		}
	}

	private Status failover(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, Command.FAILOVER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, Command.FAILOVER)
					.run(args);
		}else{
			return new LettuceCommand<Status, Status>(client, Command.FAILOVER)
					.run(args);
		}
	}

	private Object pSync(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PSYNC)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PSYNC)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PSYNC)
					.run(args);
		}
	}

}
