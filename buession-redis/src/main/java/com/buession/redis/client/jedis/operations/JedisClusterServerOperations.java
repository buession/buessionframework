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
	public String bgRewriteAof() {
		return notCommand(client, Command.BGREWRITEAOF);
	}

	@Override
	public String bgSave() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<String, String>(client, Command.BGSAVE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<String, String>(client, Command.BGSAVE)
					.run();
		}else{
			return new JedisClusterCommand<String, String>(client, Command.BGSAVE)
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
			return new JedisClusterPipelineCommand<Status, Status>(client, Command.CONFIG_SET)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, Command.CONFIG_SET)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.CONFIG_SET, (cmd)->{
				configs.forEach(cmd::configSet);
				return Status.SUCCESS;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<String, String> configGet(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return notCommand(client, Command.CONFIG_GET, args);
	}

	@Override
	public Map<byte[], byte[]> configGet(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return notCommand(client, Command.CONFIG_GET, args);
	}

	@Override
	public Status configResetStat() {
		return notCommand(client, Command.CONFIG_RESETSTAT);
	}

	@Override
	public Status configRewrite() {
		return notCommand(client, Command.CONFIG_REWRITE);
	}

	@Override
	public Long dbSize() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Long, Long>(client, Command.DBSIZE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Long, Long>(client, Command.DBSIZE)
					.run();
		}else{
			return new JedisClusterCommand<>(client, Command.DBSIZE, (cmd)->cmd.dbSize(), (v)->v)
					.run();
		}
	}

	@Override
	public Status failover() {
		return notCommand(client, Command.FAILOVER);
	}

	@Override
	public Status failover(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return notCommand(client, Command.FAILOVER, args);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("timeout", timeout);
		return notCommand(client, Command.FAILOVER, args);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);
		return notCommand(client, Command.FAILOVER, args);
	}

	@Override
	public Status failover(final int timeout) {
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		return notCommand(client, Command.FAILOVER, args);
	}

	@Override
	public Status flushAll() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, Command.FLUSHALL)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, Command.FLUSHALL)
					.run();
		}else{
			return new JedisClusterCommand<>(client, Command.FLUSHALL, (cmd)->cmd.flushAll(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushAll(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, Command.FLUSHALL)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, Command.FLUSHALL)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.FLUSHALL, (cmd)->cmd.flushAll(),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status flushDb() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, Command.FLUSHDB)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, Command.FLUSHDB)
					.run();
		}else{
			return new JedisClusterCommand<>(client, Command.FLUSHDB, (cmd)->cmd.flushDB(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushDb(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, Command.FLUSHDB)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, Command.FLUSHDB)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.FLUSHDB, (cmd)->cmd.flushDB(),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Info info() {
		return notCommand(client, Command.INFO);
	}

	@Override
	public Info info(final Info.Section section) {
		final CommandArguments args = CommandArguments.create("section", section);
		return notCommand(client, Command.INFO, args);
	}

	@Override
	public Long lastSave() {
		return notCommand(client, Command.LASTSAVE);
	}

	@Override
	public String memoryDoctor() {
		return notCommand(client, Command.MEMORY_DOCTOR);
	}

	@Override
	public Status memoryPurge() {
		return notCommand(client, Command.MEMORY_PURGE);
	}

	@Override
	public MemoryStats memoryStats() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<MemoryStats, MemoryStats>(client, Command.MEMORY_STATS)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<MemoryStats, MemoryStats>(client, Command.MEMORY_STATS)
					.run();
		}else{
			return new JedisClusterCommand<MemoryStats, MemoryStats>(client, Command.MEMORY_STATS)
					.run();
		}
	}

	@Override
	public Long memoryUsage(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final String key, final int samples) {
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key, samples),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples) {
		final CommandArguments args = CommandArguments.create("key", key).put("samples", samples);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key, samples),
					(v)->v)
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
		notCommand(client, Command.MONITOR, args);
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
			new JedisClusterPipelineCommand<>(client, Command.SYNC, (cmd)->{
				cmd.sync();
				return null;
			}, (v)->v)
					.run();
		}else if(isTransaction()){
			new JedisClusterTransactionCommand<>(client, Command.SYNC)
					.run();
		}else{
			new JedisClusterCommand<>(client, Command.SYNC)
					.run();
		}
	}

	@Override
	public Status replicaOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return notCommand(client, Command.REPLICAOF, args);
	}

	@Override
	public Status slaveOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return notCommand(client, Command.SLAVEOF, args);
	}

	@Override
	public Role role() {
		return notCommand(client, Command.ROLE);
	}

	@Override
	public Status save() {
		return notCommand(client, Command.SAVE);
	}

	@Override
	public void shutdown() {
		notCommand(client, Command.SHUTDOWN);
	}

	@Override
	public void shutdown(final boolean save) {
		final CommandArguments args = CommandArguments.create("save", save);
		notCommand(client, Command.SHUTDOWN, args);
	}

	@Override
	public List<SlowLog> slowLogGet() {
		return notCommand(client, Command.SLOWLOG_GET);
	}

	@Override
	public List<SlowLog> slowLogGet(final int count) {
		final CommandArguments args = CommandArguments.create("count", count);
		return notCommand(client, Command.SLOWLOG_GET, args);
	}

	@Override
	public Long slowLogLen() {
		return notCommand(client, Command.SLOWLOG_LEN);
	}

	@Override
	public Status slowLogReset() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, Command.SLOWLOG_RESET)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, Command.SLOWLOG_RESET)
					.run();
		}else{
			return new JedisClusterCommand<>(client, Command.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status swapdb(final int db1, final int db2) {
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);
		return notCommand(client, Command.SWAPDB, args);
	}

	@Override
	public RedisServerTime time() {
		return notCommand(client, Command.TIME);
	}

	private Status configSet(final String parameter, final String value, final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, Command.CONFIG_SET)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, Command.CONFIG_SET)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.CONFIG_SET, (cmd)->cmd.configSet(parameter, value),
					okStatusConverter)
					.run(args);
		}
	}

}
