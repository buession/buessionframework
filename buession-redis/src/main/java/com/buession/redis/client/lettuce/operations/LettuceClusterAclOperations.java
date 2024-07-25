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

import com.buession.core.converter.SetListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.AclSetUserArgument;
import com.buession.redis.core.internal.convert.lettuce.response.AclCategoryConverter;
import com.buession.redis.core.internal.convert.lettuce.response.AclGetUserConverter;
import com.buession.redis.core.internal.convert.lettuce.response.AclLogConverter;
import com.buession.redis.core.internal.convert.lettuce.response.CommandTypeConverter;
import com.buession.redis.core.internal.lettuce.LettuceAclSetuserArgs;
import io.lettuce.core.AclSetuserArgs;
import io.lettuce.core.protocol.CommandType;

import java.util.List;

/**
 * Lettuce 集群模式权限命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceClusterAclOperations extends AbstractAclOperations<LettuceClusterClient> {

	public LettuceClusterAclOperations(final LettuceClusterClient client) {
		super(client);
	}

	@Override
	public List<AclCategory> aclCat() {
		final SetListConverter<io.lettuce.core.AclCategory, AclCategory> converter = AclCategoryConverter.setListConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.ACL_CAT, (cmd)->cmd.aclCat(),
					converter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.ACL_CAT, (cmd)->cmd.aclCat(),
					converter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_CAT, (cmd)->cmd.aclCat(), converter)
					.run();
		}
	}

	@Override
	public List<Command> aclCat(final AclCategory aclCategory) {
		final CommandArguments args = CommandArguments.create(aclCategory);
		final io.lettuce.core.AclCategory aclCate =
				(new com.buession.redis.core.internal.convert.lettuce.params.AclCategoryConverter()).convert(
						aclCategory);
		final SetListConverter<CommandType, Command> converter = CommandTypeConverter.setListConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.ACL_CAT,
					(cmd)->cmd.aclCat(aclCate), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.ACL_CAT,
					(cmd)->cmd.aclCat(aclCate), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_CAT, (cmd)->cmd.aclCat(aclCate),
					converter)
					.run(args);
		}
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create(usernames);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.ACL_DELUSER,
					(cmd)->cmd.aclDeluser(usernames), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.ACL_DELUSER,
					(cmd)->cmd.aclDeluser(usernames), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_DELUSER,
					(cmd)->cmd.aclDeluser(usernames), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status aclDryRun(final String username, final Command command) {
		final CommandArguments args = CommandArguments.create(username).add(command);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.ACL_DRYRUN,
					(cmd)->cmd.aclDryRun(username, command.getName()), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.ACL_DRYRUN,
					(cmd)->cmd.aclDryRun(username, command.getName()), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_DRYRUN,
					(cmd)->cmd.aclDryRun(username, command.getName()), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status aclDryRun(final String username, final Command command, final String... arguments) {
		final CommandArguments args = CommandArguments.create(username).add(command).add(arguments);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.ACL_DRYRUN,
					(cmd)->cmd.aclDryRun(username, command.getName(), arguments), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.ACL_DRYRUN,
					(cmd)->cmd.aclDryRun(username, command.getName(), arguments), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_DRYRUN,
					(cmd)->cmd.aclDryRun(username, command.getName(), arguments), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String aclGenPass() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.ACL_GENPASS,
					(cmd)->cmd.aclGenpass(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.ACL_GENPASS,
					(cmd)->cmd.aclGenpass(), (v)->v)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_GENPASS, (cmd)->cmd.aclGenpass(),
					(v)->v)
					.run();
		}
	}

	@Override
	public String aclGenPass(final int bits) {
		final CommandArguments args = CommandArguments.create(bits);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.ACL_GENPASS,
					(cmd)->cmd.aclGenpass(bits), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.ACL_GENPASS,
					(cmd)->cmd.aclGenpass(bits), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_GENPASS, (cmd)->cmd.aclGenpass(bits),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create(username);
		final AclGetUserConverter aclGetUserConverter = new AclGetUserConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<AclUser, AclUser>(client, Command.ACL, SubCommand.ACL_GETUSER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<AclUser, AclUser>(client, Command.ACL, SubCommand.ACL_GETUSER)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_GETUSER,
					(cmd)->cmd.aclGetuser(username), aclGetUserConverter)
					.run(args);
		}
	}

	@Override
	public List<String> aclList() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.LIST, (cmd)->cmd.aclList(),
					(v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.LIST, (cmd)->cmd.aclList(),
					(v)->v)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.LIST, (cmd)->cmd.aclList(), (v)->v)
					.run();
		}
	}

	@Override
	public Status aclLoad() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.LOAD, (cmd)->cmd.aclLoad(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.LOAD, (cmd)->cmd.aclLoad(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.LOAD, (cmd)->cmd.aclLoad(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog() {
		final AclLogConverter aclLogConverter = new AclLogConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<List<AclLog>, List<AclLog>>(client, Command.ACL,
					SubCommand.ACL_LOG)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<List<AclLog>, List<AclLog>>(client, Command.ACL,
					SubCommand.ACL_LOG)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_LOG, (cmd)->cmd.aclLog(),
					aclLogConverter)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog(final int count) {
		final CommandArguments args = CommandArguments.create(count);
		final AclLogConverter aclLogConverter = new AclLogConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<List<AclLog>, List<AclLog>>(client, Command.ACL,
					SubCommand.ACL_LOG)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<List<AclLog>, List<AclLog>>(client, Command.ACL,
					SubCommand.ACL_LOG)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_LOG, (cmd)->cmd.aclLog(count),
					aclLogConverter)
					.run(args);
		}
	}

	@Override
	public Status aclLogReset() {
		final CommandArguments args = CommandArguments.create(SubCommand.RESET);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.ACL_LOG,
					(cmd)->cmd.aclLogReset(), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.ACL_LOG,
					(cmd)->cmd.aclLogReset(), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_LOG, (cmd)->cmd.aclLogReset(),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status aclSave() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.SAVE, (cmd)->cmd.aclSave(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.SAVE, (cmd)->cmd.aclSave(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.SAVE, (cmd)->cmd.aclSave(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status aclSetUser(final String username, final AclSetUserArgument rules) {
		final CommandArguments args = CommandArguments.create(username).add(rules);
		final AclSetuserArgs aclSetuserArgs = LettuceAclSetuserArgs.from(rules);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.ACL_SETUSER,
					(cmd)->cmd.aclSetuser(username, aclSetuserArgs), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.ACL_SETUSER,
					(cmd)->cmd.aclSetuser(username, aclSetuserArgs), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_SETUSER,
					(cmd)->cmd.aclSetuser(username, aclSetuserArgs), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<String> aclUsers() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.ACL_USERS, (cmd)->cmd.aclUsers(),
					(v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.ACL_USERS,
					(cmd)->cmd.aclUsers(), (v)->v)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_USERS, (cmd)->cmd.aclUsers(),
					(v)->v)
					.run();
		}
	}

	@Override
	public String aclWhoAmI() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ACL, SubCommand.ACL_WHOAMI,
					(cmd)->cmd.aclWhoami(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ACL, SubCommand.ACL_WHOAMI,
					(cmd)->cmd.aclWhoami(), (v)->v)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.ACL, SubCommand.ACL_WHOAMI, (cmd)->cmd.aclWhoami(),
					(v)->v)
					.run();
		}
	}

}
