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
import com.buession.redis.core.internal.convert.jedis.response.AccessControlLogEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.AccessControlUserConverter;
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
import redis.clients.jedis.resps.AccessControlLogEntry;
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
	public List<String> aclCat() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_CAT, (cmd)->cmd.aclCat(), (v)->v)
					.run();
		}
	}

	@Override
	public List<String> aclCat(final String categoryName) {
		final CommandArguments args = CommandArguments.create(categoryName);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_CAT)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_CAT, (cmd)->cmd.aclCat(categoryName), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName) {
		final CommandArguments args = CommandArguments.create(categoryName);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<byte[]>, List<byte[]>>(client, ProtocolCommand.ACL_CAT)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<byte[]>, List<byte[]>>(client, ProtocolCommand.ACL_CAT)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_CAT, (cmd)->cmd.aclCat(categoryName), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status aclSetUser(final String username, final String... rules) {
		final CommandArguments args = CommandArguments.create(username).add(rules);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_SETUSER,
					(cmd)->cmd.aclSetUser(username, rules), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules) {
		final CommandArguments args = CommandArguments.create(username).add(rules);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_SETUSER)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_SETUSER,
					(cmd)->cmd.aclSetUser(username, rules), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create(username);
		final AccessControlUserConverter accessControlUserConverter = new AccessControlUserConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_GETUSER, (cmd)->cmd.aclGetUser(username),
					accessControlUserConverter)
					.run(args);
		}
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		final CommandArguments args = CommandArguments.create(username);
		final AccessControlUserConverter accessControlUserConverter = new AccessControlUserConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<AclUser, AclUser>(client, ProtocolCommand.ACL_GETUSER)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_GETUSER, (cmd)->cmd.aclGetUser(username),
					accessControlUserConverter)
					.run(args);
		}
	}

	@Override
	public List<String> aclUsers() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_USERS)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_USERS)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_USERS, (cmd)->cmd.aclUsers(), (v)->v)
					.run();
		}
	}

	@Override
	public String aclWhoAmI() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, ProtocolCommand.ACL_WHOAMI)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, ProtocolCommand.ACL_WHOAMI)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_WHOAMI, (cmd)->cmd.aclWhoAmI(), (v)->v)
					.run();
		}
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_DELUSER, (cmd)->cmd.aclDelUser(usernames),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ACL_DELUSER)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_DELUSER, (cmd)->cmd.aclDelUser(usernames),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public String aclGenPass() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, ProtocolCommand.ACL_GENPASS)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, ProtocolCommand.ACL_GENPASS)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_GENPASS, (cmd)->cmd.aclGenPass(), (v)->v)
					.run();
		}
	}

	@Override
	public List<String> aclList() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_LIST)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<String>, List<String>>(client, ProtocolCommand.ACL_LIST)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_LIST, (cmd)->cmd.aclList(), (v)->v)
					.run();
		}
	}

	@Override
	public Status aclLoad() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.ACL_LOAD)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_LOAD)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_LOAD, (cmd)->cmd.aclLoad(), okStatusConverter)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog() {
		final ListConverter<AccessControlLogEntry, AclLog> listAccessControlLogEntryConverter =
				AccessControlLogEntryConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_LOG, (cmd)->cmd.aclLog(),
					listAccessControlLogEntryConverter)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog(final long count) {
		final CommandArguments args = CommandArguments.create(count);
		final ListConverter<AccessControlLogEntry, AclLog> listAccessControlLogEntryConverter =
				AccessControlLogEntryConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<AclLog>, List<AclLog>>(client, ProtocolCommand.ACL_LOG)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_LOG, (cmd)->cmd.aclLog((int) count),
					listAccessControlLogEntryConverter)
					.run(args);
		}
	}

	@Override
	public Status aclLogReset() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.ACL_LOGREST)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_LOGREST)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ACL_LOGREST, (cmd)->cmd.aclLogReset(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status aclLogSave() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.ACL_LOGSAVE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.ACL_LOGSAVE)
					.run();
		}else{
			return new JedisSentinelCommand<Status, Status>(client, ProtocolCommand.ACL_LOGSAVE)
					.run();
		}
	}

	@Override
	public String bgRewriteAof() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, ProtocolCommand.BGREWRITEAOF)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, ProtocolCommand.BGREWRITEAOF)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BGREWRITEAOF, (cmd)->cmd.bgrewriteaof(), (v)->v)
					.run();
		}
	}

	@Override
	public String bgSave() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, ProtocolCommand.BGSAVE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, ProtocolCommand.BGSAVE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BGSAVE, (cmd)->cmd.bgsave(), (v)->v)
					.run();
		}
	}

	@Override
	public Status configSet(final String parameter, final String value) {
		final CommandArguments args = CommandArguments.create(parameter).add(value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CONFIG_SET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CONFIG_SET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CONFIG_SET,
					(cmd)->cmd.configSet(parameter, value), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value) {
		final CommandArguments args = CommandArguments.create(parameter).add(value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CONFIG_SET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CONFIG_SET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CONFIG_SET,
					(cmd)->cmd.configSet(parameter, value), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status configSet(final Map<String, String> configs) {
		final CommandArguments args = CommandArguments.create(configs);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CONFIG_SET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CONFIG_SET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CONFIG_SET, (cmd)->{
				configs.forEach(cmd::configSet);
				return Status.SUCCESS;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<String, String> configGet(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Map<String, String>, Map<String, String>>(client,
					ProtocolCommand.CONFIG_GET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Map<String, String>, Map<String, String>>(client,
					ProtocolCommand.CONFIG_GET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CONFIG_GET, (cmd)->cmd.configGet(pattern),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Map<byte[], byte[]> configGet(final byte[] parameter) {
		final CommandArguments args = CommandArguments.create(parameter);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Map<byte[], byte[]>, Map<byte[], byte[]>>(client,
					ProtocolCommand.CONFIG_GET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Map<byte[], byte[]>, Map<byte[], byte[]>>(client,
					ProtocolCommand.CONFIG_GET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CONFIG_GET, (cmd)->cmd.configGet(parameter),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Status configResetStat() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CONFIG_RESETSTAT)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CONFIG_RESETSTAT)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CONFIG_RESETSTAT, (cmd)->cmd.configResetStat(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status configRewrite() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.CONFIG_REWRITE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.CONFIG_REWRITE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.CONFIG_REWRITE, (cmd)->cmd.configRewrite(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Long dbSize() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.DBSIZE, (cmd)->cmd.dbSize(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.DBSIZE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.DBSIZE, (cmd)->cmd.dbSize(), (v)->v)
					.run();
		}
	}

	@Override
	public Status failover() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.FAILOVER, (cmd)->cmd.failover(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status failover(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);
		final FailoverParams failoverParams = new JedisFailoverParams(host, port);

		return failover(failoverParams, args);
	}

	@Override
	public Status failover(final String host, final int port, final int timeout) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(timeout);
		final FailoverParams failoverParams = new JedisFailoverParams(host, port, timeout);

		return failover(failoverParams, args);
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(isForce)
				.add(timeout);
		final FailoverParams failoverParams = new JedisFailoverParams(host, port, timeout, isForce);

		return failover(failoverParams, args);
	}

	@Override
	public Status failover(final int timeout) {
		final CommandArguments args = CommandArguments.create(timeout);
		final FailoverParams failoverParams = new JedisFailoverParams(timeout);

		return failover(failoverParams, args);
	}

	@Override
	public Status flushAll() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.FLUSHALL)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.FLUSHALL)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.FLUSHALL, (cmd)->cmd.flushAll(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushAll(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		final redis.clients.jedis.args.FlushMode flushMode = (new FlushModeConverter()).convert(mode);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.FLUSHALL)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.FLUSHALL)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.FLUSHALL, (cmd)->cmd.flushAll(flushMode),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status flushDb() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.FLUSHDB)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.FLUSHDB)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.flushDB(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status flushDb(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		final redis.clients.jedis.args.FlushMode flushMode = (new FlushModeConverter()).convert(mode);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.FLUSHDB)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.FLUSHDB)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.FLUSHDB, (cmd)->cmd.flushDB(flushMode),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Info info() {
		final InfoConverter infoConverter = new InfoConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Info, Info>(client, ProtocolCommand.INFO)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Info, Info>(client, ProtocolCommand.INFO)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.INFO, (cmd)->cmd.info(), infoConverter)
					.run();
		}
	}

	@Override
	public Info info(final Info.Section section) {
		final CommandArguments args = CommandArguments.create(section);
		final String sectionName = section.name().toLowerCase();
		final InfoConverter infoConverter = new InfoConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Info, Info>(client, ProtocolCommand.INFO)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Info, Info>(client, ProtocolCommand.INFO)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.INFO, (cmd)->cmd.info(sectionName), infoConverter)
					.run(args);
		}
	}

	@Override
	public Long lastSave() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.LASTSAVE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.LASTSAVE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.LASTSAVE, (cmd)->cmd.lastsave(), (v)->v)
					.run();
		}
	}

	@Override
	public String memoryDoctor() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, ProtocolCommand.MEMORY_DOCTOR)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, ProtocolCommand.MEMORY_DOCTOR)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.MEMORY_DOCTOR, (cmd)->cmd.memoryDoctor(), (v)->v)
					.run();
		}
	}

	@Override
	public Status memoryPurge() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.MEMORY_PURGE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.MEMORY_PURGE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.MEMORY_PURGE, (cmd)->cmd.memoryPurge(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public MemoryStats memoryStats() {
		final MemoryStatsConverter memoryStatsConverter = new MemoryStatsConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<MemoryStats, MemoryStats>(client, ProtocolCommand.MEMORY_STATS)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<MemoryStats, MemoryStats>(client, ProtocolCommand.MEMORY_STATS)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.MEMORY_STATS, (cmd)->cmd.memoryStats(),
					memoryStatsConverter)
					.run();
		}
	}

	@Override
	public Long memoryUsage(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.MEMORY_USAGE, (cmd)->cmd.memoryUsage(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final String key, final int samples) {
		final CommandArguments args = CommandArguments.create(key).add(samples);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples) {
		final CommandArguments args = CommandArguments.create(key).add(samples);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.MEMORY_USAGE,
					(cmd)->cmd.memoryUsage(key, samples), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Module> moduleList() {
		final ListConverter<redis.clients.jedis.Module, Module> listModuleConverter = ModuleConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<Module>, List<Module>>(client, ProtocolCommand.MODULE_LIST)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<Module>, List<Module>>(client, ProtocolCommand.MODULE_LIST)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.MODULE_LIST, (cmd)->cmd.moduleList(),
					listModuleConverter)
					.run();
		}
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments) {
		final CommandArguments args = CommandArguments.create(path).add(arguments);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.MODULE_LOAD)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.MODULE_LOAD)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.MODULE_LOAD,
					(cmd)->cmd.moduleLoad(path, arguments), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status moduleUnLoad(final String name) {
		final CommandArguments args = CommandArguments.create(name);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.MODULE_UNLOAD)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.MODULE_UNLOAD)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.MODULE_UNLOAD, (cmd)->cmd.moduleUnload(name),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor) {
		final CommandArguments args = CommandArguments.create(redisMonitor);

		if(isPipeline()){
			new JedisSentinelPipelineCommand<>(client, ProtocolCommand.MONITOR)
					.run(args);
		}else if(isTransaction()){
			new JedisSentinelTransactionCommand<>(client, ProtocolCommand.MONITOR)
					.run(args);
		}else{
			new JedisSentinelCommand<>(client, ProtocolCommand.MONITOR, (cmd)->{
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
		final CommandArguments args = CommandArguments.create(replicationId).add(offset);
		return pSync(args);
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset) {
		final CommandArguments args = CommandArguments.create(replicationId).add(offset);
		return pSync(args);
	}

	@Override
	public void sync() {
		if(isPipeline()){
			new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SYNC, (cmd)->{
				cmd.sync();
				return null;
			}, (v)->v)
					.run();
		}else if(isTransaction()){
			new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SYNC)
					.run();
		}else{
			new JedisSentinelCommand<>(client, ProtocolCommand.SYNC)
					.run();
		}
	}

	@Override
	public Status replicaOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.REPLICAOF)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.REPLICAOF)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.REPLICAOF, (cmd)->cmd.replicaof(host, port),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status slaveOf(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.SLAVEOF)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.SLAVEOF)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SLAVEOF, (cmd)->cmd.slaveof(host, port),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Role role() {
		final RoleConverter roleConverter = new RoleConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Role, Role>(client, ProtocolCommand.ROLE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Role, Role>(client, ProtocolCommand.ROLE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.ROLE, (cmd)->cmd.role(), roleConverter)
					.run();
		}
	}

	@Override
	public Status save() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.SAVE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.SAVE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SAVE, (cmd)->cmd.save(), okStatusConverter)
					.run();
		}
	}

	@Override
	public void shutdown() {
		if(isPipeline()){
			new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SHUTDOWN)
					.run();
		}else if(isTransaction()){
			new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SHUTDOWN)
					.run();
		}else{
			new JedisSentinelCommand<>(client, ProtocolCommand.SHUTDOWN, (cmd)->{
				cmd.shutdown();
				return null;
			}, (v)->v)
					.run();
		}
	}

	@Override
	public void shutdown(final boolean save) {
		final CommandArguments args = CommandArguments.create(save);
		final SaveMode saveMode = save ? SaveMode.SAVE : SaveMode.NOSAVE;

		if(isPipeline()){
			new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SHUTDOWN)
					.run();
		}else if(isTransaction()){
			new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SHUTDOWN)
					.run();
		}else{
			new JedisSentinelCommand<>(client, ProtocolCommand.SHUTDOWN, (cmd)->{
				cmd.shutdown(saveMode);
				return null;
			}, (v)->v)
					.run();
		}
	}

	@Override
	public List<SlowLog> slowLogGet() {
		final ListConverter<Slowlog, SlowLog> listSlowlogConverter = SlowlogConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<SlowLog>, List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<SlowLog>, List<SlowLog>>(client,
					ProtocolCommand.SLOWLOG_GET)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SLOWLOG_GET, (cmd)->cmd.slowlogGet(),
					listSlowlogConverter)
					.run();
		}
	}

	@Override
	public List<SlowLog> slowLogGet(final long count) {
		final CommandArguments args = CommandArguments.create(count);
		final ListConverter<Slowlog, SlowLog> listSlowlogConverter = SlowlogConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<SlowLog>, List<SlowLog>>(client, ProtocolCommand.SLOWLOG_GET)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<SlowLog>, List<SlowLog>>(client,
					ProtocolCommand.SLOWLOG_GET)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SLOWLOG_GET, (cmd)->cmd.slowlogGet(count),
					listSlowlogConverter)
					.run(args);
		}
	}

	@Override
	public Long slowLogLen() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.SLOWLOG_LEN)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.SLOWLOG_LEN)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SLOWLOG_RESET, (cmd)->cmd.slowlogLen(), (v)->v)
					.run();
		}
	}

	@Override
	public Status slowLogReset() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.SLOWLOG_RESET)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.SLOWLOG_RESET)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SLOWLOG_RESET, (cmd)->cmd.slowlogReset(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status swapdb(final int db1, final int db2) {
		final CommandArguments args = CommandArguments.create(db1).add(db2);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SWAPDB, (cmd)->cmd.swapDB(db1, db2),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.SWAPDB)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SWAPDB, (cmd)->cmd.swapDB(db1, db2),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public RedisServerTime time() {
		final RedisServerTimeConverter redisServerTimeConverter = new RedisServerTimeConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.TIME, (cmd)->cmd.time(),
					redisServerTimeConverter)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<RedisServerTime, RedisServerTime>(client, ProtocolCommand.TIME)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.TIME, (cmd)->cmd.time(), redisServerTimeConverter)
					.run();
		}
	}

	private Status failover(final FailoverParams failoverParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.FAILOVER)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.FAILOVER, (cmd)->cmd.failover(failoverParams),
					okStatusConverter)
					.run(args);
		}
	}

	private Object pSync(final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.PSYNC)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.PSYNC)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.PSYNC)
					.run(args);
		}
	}

}
