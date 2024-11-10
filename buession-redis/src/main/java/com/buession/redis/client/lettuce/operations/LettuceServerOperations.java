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
	public List<String> aclCat() {
		if(isPipeline()){
			return new LettucePipelineCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
					.run();
		}else{
			return new LettuceCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
					.run();
		}
	}

	@Override
	public List<String> aclCat(final String categoryName) {
		final CommandArguments args = CommandArguments.create(categoryName);
		return aclCat(args);
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName) {
		final CommandArguments args = CommandArguments.create(categoryName);
		return aclCat(args);
	}

	@Override
	public Status aclSetUser(final String username, final String... rules) {
		final CommandArguments args = CommandArguments.create(username).put("rules", (Object[]) rules);
		return aclSetUser(args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules) {
		final CommandArguments args = CommandArguments.create(username).put("rules", (Object[]) rules);
		return aclSetUser(args);
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create(username);
		return aclGetUser(args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		final CommandArguments args = CommandArguments.create(username);
		return aclGetUser(args);
	}

	@Override
	public List<String> aclUsers() {
		if(isPipeline()){
			return new LettucePipelineCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_USERS)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_USERS)
					.run();
		}else{
			return new LettuceCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_USERS)
					.run();
		}
	}

	@Override
	public String aclWhoAmI() {
		if(isPipeline()){
			return new LettucePipelineCommand<String, String>(client, ProtocolCommand.ACL_WHOAMI)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<String, String>(client, ProtocolCommand.ACL_WHOAMI)
					.run();
		}else{
			return new LettuceCommand<String, String>(client, ProtocolCommand.ACL_WHOAMI)
					.run();
		}
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);
		return aclDelUser(args);
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);
		return aclDelUser(args);
	}

	@Override
	public String aclGenPass() {
		if(isPipeline()){
			return new LettucePipelineCommand<String, String>(client, ProtocolCommand.ACL_GENPASS)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<String, String>(client, ProtocolCommand.ACL_GENPASS)
					.run();
		}else{
			return new LettuceCommand<String, String>(client, ProtocolCommand.ACL_GENPASS)
					.run();
		}
	}

	@Override
	public List<String> aclList() {
		if(isPipeline()){
			return new LettucePipelineCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_LIST)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_LIST)
					.run();
		}else{
			return new LettuceCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_LIST)
					.run();
		}
	}

	@Override
	public Status aclLoad() {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.ACL_LOAD)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_LOAD)
					.run();
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.ACL_LOAD)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog() {
		if(isPipeline()){
			return new LettucePipelineCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run();
		}else{
			return new LettuceCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog(final long count) {
		final CommandArguments args = CommandArguments.create(count);

		if(isPipeline()){
			return new LettucePipelineCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run(args);
		}else{
			return new LettuceCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run(args);
		}
	}

	@Override
	public Status aclLogReset() {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.ACL_LOGREST)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_LOGREST)
					.run();
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.ACL_LOGREST)
					.run();
		}
	}

	@Override
	public Status aclLogSave() {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.ACL_LOGSAVE)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_LOGSAVE)
					.run();
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.ACL_LOGSAVE)
					.run();
		}
	}

	@Override
	public String bgRewriteAof() {
		if(isPipeline()){
			return new LettucePipelineCommand<String, String>(client, ProtocolCommand.BGREWRITEAOF)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<String, String>(client, ProtocolCommand.BGREWRITEAOF)
					.run();
		}else{
			return new LettuceCommand<String, String>(client, ProtocolCommand.BGREWRITEAOF)
					.run();
		}
	}

	@Override
	public String bgSave() {
		if(isPipeline()){
			return new LettucePipelineCommand<String, String>(client, ProtocolCommand.BGSAVE)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<String, String>(client, ProtocolCommand.BGSAVE)
					.run();
		}else{
			return new LettuceCommand<String, String>(client, ProtocolCommand.BGSAVE)
					.run();
		}
	}

	@Override
	public Status configSet(final String parameter, final String value) {
		final CommandArguments args = CommandArguments.create(parameter).add(value);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CONFIG_SET,
					(cmd)->cmd.configSet(parameter, value), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CONFIG_SET,
					(cmd)->cmd.configSet(parameter, value), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CONFIG_SET, (cmd)->cmd.configSet(parameter, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status configSet(final Map<String, String> configs) {
		final CommandArguments args = CommandArguments.create(configs);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CONFIG_SET,
					(cmd)->cmd.configSet(configs), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CONFIG_SET,
					(cmd)->cmd.configSet(configs), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CONFIG_SET, (cmd)->cmd.configSet(configs),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Map<String, String> configGet(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CONFIG_GET,
					(cmd)->cmd.configGet(pattern), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CONFIG_GET,
					(cmd)->cmd.configGet(pattern), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CONFIG_GET, (cmd)->cmd.configGet(pattern), (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<byte[], byte[]> configGet(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		final String sPattern = SafeEncoder.encode(pattern);
		final MapConverter<String, String, byte[], byte[]> converter = Converters.mapStringToBinary();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CONFIG_GET, (cmd)->cmd.configGet(sPattern),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CONFIG_GET, (cmd)->cmd.configGet(sPattern),
					converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CONFIG_GET, (cmd)->cmd.configGet(sPattern),
					converter)
					.run(args);
		}
	}

	@Override
	public Status configResetStat() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CONFIG_RESETSTAT, (cmd)->cmd.configResetstat(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CONFIG_RESETSTAT,
					(cmd)->cmd.configResetstat(), okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CONFIG_RESETSTAT, (cmd)->cmd.configResetstat(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status configRewrite() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.CONFIG_REWRITE, (cmd)->cmd.configRewrite(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.CONFIG_REWRITE, (cmd)->cmd.configRewrite(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.CONFIG_REWRITE, (cmd)->cmd.configRewrite(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Long dbSize() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.DBSIZE, (cmd)->cmd.dbsize(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.DBSIZE, (cmd)->cmd.dbsize(), (v)->v)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.DBSIZE, (cmd)->cmd.dbsize(), (v)->v)
					.run();
		}
	}

	@Override
	public Status failover() {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run();
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run();
		}
	}

	@Override
	public Status failover(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).put("port", port);
		return failover(args);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout) {
		final CommandArguments args = CommandArguments.create(host).put("port", port).put("timeout", timeout);
		return failover(args);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout) {
		final CommandArguments args = CommandArguments.create(host).put("port", port).put("isForce", isForce)
				.put("timeout", timeout);
		return failover(args);
	}

	@Override
	public Status failover(final int timeout) {
		final CommandArguments args = CommandArguments.create(timeout);
		return failover(args);
	}

	@Override
	public Status flushAll() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.FLUSHALL, (cmd)->cmd.flushall(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.FLUSHALL, (cmd)->cmd.flushall(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.FLUSHALL, (cmd)->cmd.flushall(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushAll(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.FLUSHALL,
					(cmd)->mode == FlushMode.ASYNC ? cmd.flushallAsync() : cmd.flushall(), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.FLUSHALL,
					(cmd)->mode == FlushMode.ASYNC ? cmd.flushallAsync() : cmd.flushall(), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.FLUSHALL,
					(cmd)->mode == FlushMode.ASYNC ? cmd.flushallAsync() : cmd.flushall(), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status flushDb() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.flushdb(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.flushdb(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.flushdb(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushDb(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->mode == FlushMode.ASYNC ?
					cmd.flushdbAsync() : cmd.flushdb(), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->mode == FlushMode.ASYNC ?
					cmd.flushdbAsync() : cmd.flushdb(), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->mode == FlushMode.ASYNC ?
					cmd.flushdbAsync() : cmd.flushdb(), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Info info() {
		final InfoConverter infoConverter = new InfoConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.info(), infoConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.info(), infoConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.info(), infoConverter)
					.run();
		}
	}

	@Override
	public Info info(final Info.Section section) {
		final CommandArguments args = CommandArguments.create(section);
		final String sectionName = section.name().toLowerCase();
		final InfoConverter infoConverter = new InfoConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.info(sectionName),
					infoConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.info(sectionName),
					infoConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.info(sectionName), infoConverter)
					.run(args);
		}
	}

	@Override
	public Long lastSave() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.LASTSAVE, (cmd)->cmd.lastsave(), Date::getTime)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.LASTSAVE, (cmd)->cmd.lastsave(),
					Date::getTime)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.LASTSAVE, (cmd)->cmd.lastsave(), Date::getTime)
					.run();
		}
	}

	@Override
	public String memoryDoctor() {
		if(isPipeline()){
			return new LettucePipelineCommand<String, String>(client, ProtocolCommand.MEMORY_DOCTOR)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<String, String>(client, ProtocolCommand.MEMORY_DOCTOR)
					.run();
		}else{
			return new LettuceCommand<String, String>(client, ProtocolCommand.MEMORY_DOCTOR)
					.run();
		}
	}

	@Override
	public Status memoryPurge() {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.MEMORY_PURGE)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.MEMORY_PURGE)
					.run();
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.MEMORY_PURGE)
					.run();
		}
	}

	@Override
	public MemoryStats memoryStats() {
		if(isPipeline()){
			return new LettucePipelineCommand<MemoryStats, MemoryStats>(client, ProtocolCommand.MEMORY_STATS)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<MemoryStats, MemoryStats>(client, ProtocolCommand.MEMORY_STATS)
					.run();
		}else{
			return new LettuceCommand<MemoryStats, MemoryStats>(client, ProtocolCommand.MEMORY_STATS)
					.run();
		}
	}

	@Override
	public Long memoryUsage(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples) {
		final CommandArguments args = CommandArguments.create(key).put("samples", samples);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Module> moduleList() {
		if(isPipeline()){
			return new LettucePipelineCommand<List<Module>, List<Module>>(client, ProtocolCommand.MODULE_LIST)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<Module>, List<Module>>(client, ProtocolCommand.MODULE_LIST)
					.run();
		}else{
			return new LettuceCommand<List<Module>, List<Module>>(client, ProtocolCommand.MODULE_LIST)
					.run();
		}
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments) {
		final CommandArguments args = CommandArguments.create(path).put("arguments", (Object[]) arguments);
		return moduleLoad(args);
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create(path).put("arguments", (Object[]) arguments);
		return moduleLoad(args);
	}

	@Override
	public Status moduleUnLoad(final String name) {
		final CommandArguments args = CommandArguments.create(name);
		return moduleUnLoad(args);
	}

	@Override
	public Status moduleUnLoad(final byte[] name) {
		final CommandArguments args = CommandArguments.create(name);
		return moduleUnLoad(args);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor) {
		final CommandArguments args = CommandArguments.create(redisMonitor);

		if(isPipeline()){
			new LettucePipelineCommand<>(client, ProtocolCommand.MONITOR)
					.run(args);
		}else if(isTransaction()){
			new LettuceTransactionCommand<>(client, ProtocolCommand.MONITOR)
					.run(args);
		}else{
			new LettuceCommand<>(client, ProtocolCommand.MONITOR)
					.run(args);
		}
	}

	@Override
	public Object pSync(final String replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create(replicationId).put("offset", offset);
		return pSync(args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create(replicationId).put("offset", offset);
		return pSync(args);
	}

	@Override
	public void sync() {
		if(isPipeline()){
			new LettucePipelineCommand<>(client, ProtocolCommand.SYNC)
					.run();
		}else if(isTransaction()){
			new LettuceTransactionCommand<>(client, ProtocolCommand.SYNC)
					.run();
		}else{
			new LettuceCommand<>(client, ProtocolCommand.SYNC)
					.run();
		}
	}

	@Override
	public Status replicaOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).put("port", port);

		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.REPLICAOF)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.REPLICAOF)
					.run(args);
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.REPLICAOF)
					.run(args);
		}
	}

	@Override
	public Status slaveOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).put("port", port);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SLAVEOF, (cmd)->cmd.slaveof(host, port),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SLAVEOF, (cmd)->cmd.slaveof(host, port),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SLAVEOF, (cmd)->cmd.slaveof(host, port),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Role role() {
		final RoleConverter roleConverter = new RoleConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.ROLE, (cmd)->cmd.role(), roleConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.ROLE, (cmd)->cmd.role(), roleConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.ROLE, (cmd)->cmd.role(), roleConverter)
					.run();
		}
	}

	@Override
	public Status save() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SAVE, (cmd)->cmd.save(), okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SAVE, (cmd)->cmd.save(), okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SAVE, (cmd)->cmd.save(), okStatusConverter)
					.run();
		}
	}

	@Override
	public void shutdown() {
		if(isPipeline()){
			new LettucePipelineCommand<>(client, ProtocolCommand.SHUTDOWN, (cmd)->{
				cmd.shutdown(true);
				return null;
			}, (v)->v)
					.run();
		}else if(isTransaction()){
			new LettuceTransactionCommand<>(client, ProtocolCommand.SHUTDOWN, (cmd)->{
				cmd.shutdown(true);
				return null;
			}, (v)->v)
					.run();
		}else{
			new LettuceCommand<>(client, ProtocolCommand.SHUTDOWN, (cmd)->{
				cmd.shutdown(true);
				return null;
			}, (v)->v)
					.run();
		}
	}

	@Override
	public void shutdown(final boolean save) {
		final CommandArguments args = CommandArguments.create(save);

		if(isPipeline()){
			new LettucePipelineCommand<>(client, ProtocolCommand.SHUTDOWN, (cmd)->{
				cmd.shutdown(save);
				return null;
			}, (v)->v)
					.run(args);
		}else if(isTransaction()){
			new LettuceTransactionCommand<>(client, ProtocolCommand.SHUTDOWN, (cmd)->{
				cmd.shutdown(save);
				return null;
			}, (v)->v)
					.run(args);
		}else{
			new LettuceCommand<>(client, ProtocolCommand.SHUTDOWN, (cmd)->{
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
			return new LettucePipelineCommand<>(client, ProtocolCommand.SLOWLOG_GET, (cmd)->cmd.slowlogGet(),
					listSlowlogConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SLOWLOG_GET, (cmd)->cmd.slowlogGet(),
					listSlowlogConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SLOWLOG_GET, (cmd)->cmd.slowlogGet(),
					listSlowlogConverter)
					.run();
		}
	}

	@Override
	public List<SlowLog> slowLogGet(final long count) {
		final CommandArguments args = CommandArguments.create(count);
		final ListConverter<Object, SlowLog> listSlowlogConverter = SlowlogConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SLOWLOG_GET, (cmd)->cmd.slowlogGet((int) count),
					listSlowlogConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SLOWLOG_GET,
					(cmd)->cmd.slowlogGet((int) count),
					listSlowlogConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SLOWLOG_GET, (cmd)->cmd.slowlogGet((int) count),
					listSlowlogConverter)
					.run(args);
		}
	}

	@Override
	public Long slowLogLen() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SLOWLOG_LEN, (cmd)->cmd.slowlogLen(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SLOWLOG_LEN, (cmd)->cmd.slowlogLen(), (v)->v)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SLOWLOG_LEN, (cmd)->cmd.slowlogLen(), (v)->v)
					.run();
		}
	}

	@Override
	public Status slowLogReset() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status swapdb(final int db1, final int db2) {
		final CommandArguments args = CommandArguments.create(db1).put("db2", db2);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SWAPDB, (cmd)->cmd.swapdb(db1, db2),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SWAPDB, (cmd)->cmd.swapdb(db1, db2),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SWAPDB, (cmd)->cmd.swapdb(db1, db2), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public RedisServerTime time() {
		final RedisServerTimeConverter redisServerTimeConverter = new RedisServerTimeConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.TIME, (cmd)->cmd.time(),
					redisServerTimeConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.TIME, (cmd)->cmd.time(),
					redisServerTimeConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.TIME, (cmd)->cmd.time(), redisServerTimeConverter)
					.run();
		}
	}

	private <V> List<V> aclCat(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ACL_CAT)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ACL_CAT)
					.run(args);
		}else{
			return new LettuceCommand<List<V>, List<V>>(client, ProtocolCommand.ACL_CAT)
					.run(args);
		}
	}

	private Status aclSetUser(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
					.run(args);
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
					.run(args);
		}
	}

	private AclUser aclGetUser(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
					.run(args);
		}else{
			return new LettuceCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
					.run(args);
		}
	}

	private Long aclDelUser(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
					.run(args);
		}else{
			return new LettuceCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
					.run(args);
		}
	}

	private Status failover(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run(args);
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run(args);
		}
	}

	private Status moduleLoad(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.MODULE_LOAD)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.MODULE_LOAD)
					.run(args);
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.MODULE_LOAD)
					.run(args);
		}
	}

	private Status moduleUnLoad(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.MODULE_UNLOAD)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.MODULE_UNLOAD)
					.run(args);
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.MODULE_UNLOAD)
					.run(args);
		}
	}

	private Object pSync(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.PSYNC)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.PSYNC)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PSYNC)
					.run(args);
		}
	}

}
