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
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.AclSetUserArgument;
import com.buession.redis.core.internal.convert.jedis.response.AccessControlLogEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.AccessControlUserConverter;
import com.buession.redis.core.internal.convert.jedis.response.AclCategoryConverter;
import com.buession.redis.core.internal.convert.jedis.response.ProtocolCommandConverter;
import redis.clients.jedis.resps.AccessControlLogEntry;

import java.util.List;

/**
 * Jedis 哨兵模式权限命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisSentinelAclOperations extends AbstractAclOperations<JedisSentinelClient> {

	public JedisSentinelAclOperations(final JedisSentinelClient client) {
		super(client);
	}

	@Override
	public List<AclCategory> aclCat() {
		final ListConverter<String, AclCategory> converter = AclCategoryConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<AclCategory>, List<AclCategory>>(client, Command.ACL,
					SubCommand.ACL_CAT)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<AclCategory>, List<AclCategory>>(client, Command.ACL,
					SubCommand.ACL_CAT)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_CAT, (cmd)->cmd.aclCat(), converter)
					.run();
		}
	}

	@Override
	public List<Command> aclCat(final AclCategory aclCategory) {
		final CommandArguments args = CommandArguments.create(aclCategory);
		final ListConverter<String, Command> converter = ProtocolCommandConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<Command>, List<Command>>(client, Command.ACL,
					SubCommand.ACL_CAT)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<Command>, List<Command>>(client, Command.ACL,
					SubCommand.ACL_CAT)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_CAT,
					(cmd)->cmd.aclCat(aclCategory.name()), converter)
					.run(args);
		}
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, Command.ACL, SubCommand.ACL_DELUSER)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, Command.ACL, SubCommand.ACL_DELUSER)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_DELUSER,
					(cmd)->cmd.aclDelUser(usernames), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, Command.ACL, SubCommand.ACL_DELUSER)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, Command.ACL, SubCommand.ACL_DELUSER)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_DELUSER,
					(cmd)->cmd.aclDelUser(usernames), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status aclDryRun(final String username, final Command command) {
		final CommandArguments args = CommandArguments.create(username).add(command);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.ACL, SubCommand.ACL_DRYRUN)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.ACL, SubCommand.ACL_DRYRUN)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_DRYRUN,
					(cmd)->cmd.aclDryRun(username, command.getName()), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status aclDryRun(final String username, final Command command, final String... arguments) {
		final CommandArguments args = CommandArguments.create(username).add(command).add(arguments);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.ACL, SubCommand.ACL_DRYRUN)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.ACL, SubCommand.ACL_DRYRUN)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_DRYRUN,
					(cmd)->cmd.aclDryRun(username, command.getName(), arguments), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String aclGenPass() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, Command.ACL, SubCommand.ACL_GENPASS)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, Command.ACL, SubCommand.ACL_GENPASS)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_GENPASS, (cmd)->cmd.aclGenPass(),
					(v)->v)
					.run();
		}
	}

	@Override
	public String aclGenPass(final int bits) {
		final CommandArguments args = CommandArguments.create(bits);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, Command.ACL, SubCommand.ACL_GENPASS)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, Command.ACL, SubCommand.ACL_GENPASS)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_GENPASS, (cmd)->cmd.aclGenPass(bits),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create(username);
		final AccessControlUserConverter accessControlUserConverter = new AccessControlUserConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<AclUser, AclUser>(client, Command.ACL, SubCommand.ACL_GETUSER)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<AclUser, AclUser>(client, Command.ACL, SubCommand.ACL_GETUSER)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_GETUSER,
					(cmd)->cmd.aclGetUser(username), accessControlUserConverter)
					.run(args);
		}
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		final CommandArguments args = CommandArguments.create(username);
		final AccessControlUserConverter accessControlUserConverter = new AccessControlUserConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<AclUser, AclUser>(client, Command.ACL, SubCommand.ACL_GETUSER)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<AclUser, AclUser>(client, Command.ACL, SubCommand.ACL_GETUSER)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_GETUSER,
					(cmd)->cmd.aclGetUser(username), accessControlUserConverter)
					.run(args);
		}
	}

	@Override
	public List<String> aclList() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<String>, List<String>>(client, Command.ACL, SubCommand.LIST)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<String>, List<String>>(client, Command.ACL, SubCommand.LIST)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.LIST, (cmd)->cmd.aclList(), (v)->v)
					.run();
		}
	}

	@Override
	public Status aclLoad() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.ACL, SubCommand.LOAD)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.ACL, SubCommand.LOAD)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.LOAD, (cmd)->cmd.aclLoad(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog() {
		final ListConverter<AccessControlLogEntry, AclLog> listAccessControlLogEntryConverter =
				AccessControlLogEntryConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<AclLog>, List<AclLog>>(client, Command.ACL, SubCommand.ACL_LOG)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<AclLog>, List<AclLog>>(client, Command.ACL,
					SubCommand.ACL_LOG)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_LOG, (cmd)->cmd.aclLog(),
					listAccessControlLogEntryConverter)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog(final int count) {
		final CommandArguments args = CommandArguments.create(count);
		final ListConverter<AccessControlLogEntry, AclLog> listAccessControlLogEntryConverter =
				AccessControlLogEntryConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<AclLog>, List<AclLog>>(client, Command.ACL, SubCommand.ACL_LOG)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<AclLog>, List<AclLog>>(client, Command.ACL,
					SubCommand.ACL_LOG)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_LOG, (cmd)->cmd.aclLog(count),
					listAccessControlLogEntryConverter)
					.run(args);
		}
	}

	@Override
	public Status aclLogReset() {
		final CommandArguments args = CommandArguments.create(SubCommand.RESET);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.ACL, SubCommand.ACL_LOG)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.ACL, SubCommand.ACL_LOG)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_LOG, (cmd)->cmd.aclLogReset(),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status aclSave() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.ACL, SubCommand.SAVE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.ACL, SubCommand.SAVE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.SAVE, (cmd)->cmd.aclSave(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status aclSetUser(final String username, final AclSetUserArgument rules) {
		final CommandArguments args = CommandArguments.create(username).add(rules);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.ACL, SubCommand.ACL_SETUSER)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.ACL, SubCommand.ACL_SETUSER)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_SETUSER,
					(cmd)->cmd.aclSetUser(username, rules.toArray()), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status aclSetUser(final byte[] username, final AclSetUserArgument rules) {
		final CommandArguments args = CommandArguments.create(username).add(rules);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.ACL, SubCommand.ACL_SETUSER)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.ACL, SubCommand.ACL_SETUSER)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_SETUSER,
					(cmd)->cmd.aclSetUser(username, rules.toBinaryArray()), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<String> aclUsers() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<String>, List<String>>(client, Command.ACL,
					SubCommand.ACL_USERS)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<String>, List<String>>(client, Command.ACL,
					SubCommand.ACL_USERS)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_USERS, (cmd)->cmd.aclUsers(), (v)->v)
					.run();
		}
	}

	@Override
	public String aclWhoAmI() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, Command.ACL, SubCommand.ACL_WHOAMI)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, Command.ACL, SubCommand.ACL_WHOAMI)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.ACL, SubCommand.ACL_WHOAMI, (cmd)->cmd.aclWhoAmI(),
					(v)->v)
					.run();
		}
	}

}
