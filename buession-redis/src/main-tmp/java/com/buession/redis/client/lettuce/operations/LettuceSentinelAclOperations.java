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
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.AclSetUserArgument;

import java.util.List;

/**
 * Lettuce 哨兵模式权限命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelAclOperations extends AbstractAclOperations<LettuceSentinelClient> {

	public LettuceSentinelAclOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public List<AclCategory> aclCat() {
		return notCommand(client, Command.ACL, SubCommand.ACL_CAT);
	}

	@Override
	public List<Command> aclCat(final AclCategory aclCategory) {
		final CommandArguments args = CommandArguments.create(aclCategory);
		return notCommand(client, Command.ACL, SubCommand.ACL_CAT, args);
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);
		return notCommand(client, Command.ACL, SubCommand.ACL_DELUSER, args);
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);
		return notCommand(client, Command.ACL, SubCommand.ACL_DELUSER, args);
	}

	@Override
	public Status aclDryRun(final String username, final Command command) {
		final CommandArguments args = CommandArguments.create(username).add(command);
		return notCommand(client, Command.ACL, SubCommand.ACL_DRYRUN, args);
	}

	@Override
	public Status aclDryRun(final byte[] username, final Command command) {
		final CommandArguments args = CommandArguments.create(username).add(command);
		return notCommand(client, Command.ACL, SubCommand.ACL_DRYRUN, args);
	}

	@Override
	public Status aclDryRun(final String username, final Command command, final String... arguments) {
		final CommandArguments args = CommandArguments.create(username).add(command).add(arguments);
		return notCommand(client, Command.ACL, SubCommand.ACL_DRYRUN, args);
	}

	@Override
	public Status aclDryRun(final byte[] username, final Command command, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create(username).add(command).add(arguments);
		return notCommand(client, Command.ACL, SubCommand.ACL_DRYRUN, args);
	}

	@Override
	public String aclGenPass() {
		return notCommand(client, Command.ACL, SubCommand.ACL_GENPASS);
	}

	@Override
	public String aclGenPass(final int bits) {
		final CommandArguments args = CommandArguments.create(bits);
		return notCommand(client, Command.ACL, SubCommand.ACL_GENPASS, args);
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create(username);
		return notCommand(client, Command.ACL, SubCommand.ACL_GETUSER, args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		final CommandArguments args = CommandArguments.create(username);
		return notCommand(client, Command.ACL, SubCommand.ACL_GETUSER, args);
	}

	@Override
	public List<String> aclList() {
		return notCommand(client, Command.ACL, SubCommand.LIST);
	}

	@Override
	public Status aclLoad() {
		return notCommand(client, Command.ACL, SubCommand.LOAD);
	}

	@Override
	public List<AclLog> aclLog() {
		return notCommand(client, Command.ACL, SubCommand.ACL_LOG);
	}

	@Override
	public List<AclLog> aclLog(final int count) {
		final CommandArguments args = CommandArguments.create(count);
		return notCommand(client, Command.ACL, SubCommand.ACL_LOG, args);
	}

	@Override
	public Status aclLogReset() {
		final CommandArguments args = CommandArguments.create(SubCommand.RESET);
		return notCommand(client, Command.ACL, SubCommand.ACL_LOG, args);
	}

	@Override
	public Status aclSave() {
		return notCommand(client, Command.ACL, SubCommand.SAVE);
	}

	@Override
	public Status aclSetUser(final String username, final AclSetUserArgument rules) {
		final CommandArguments args = CommandArguments.create(username).add(rules);
		return notCommand(client, Command.ACL, SubCommand.ACL_SETUSER, args);
	}

	@Override
	public Status aclSetUser(final byte[] username, final AclSetUserArgument rules) {
		final CommandArguments args = CommandArguments.create(username).add(rules);
		return notCommand(client, Command.ACL, SubCommand.ACL_SETUSER, args);
	}

	@Override
	public List<String> aclUsers() {
		return notCommand(client, Command.ACL, SubCommand.ACL_USERS);
	}

	@Override
	public String aclWhoAmI() {
		return notCommand(client, Command.ACL, SubCommand.ACL_WHOAMI);
	}

}
