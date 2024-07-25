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
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.internal.convert.lettuce.response.AclCategoryConverter;
import com.buession.redis.core.internal.convert.lettuce.response.CommandTypeConverter;
import io.lettuce.core.AclSetuserArgs;
import io.lettuce.core.protocol.CommandType;

import java.util.List;

/**
 * Lettuce 单机模式权限命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceAclOperations extends AbstractAclOperations<LettuceStandaloneClient> {

	public LettuceAclOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public List<AclCategory> aclCat() {
		final SetListConverter<io.lettuce.core.AclCategory, AclCategory> converter = new SetListConverter<>(
				new AclCategoryConverter());

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ACL_CAT, (cmd)->cmd.aclCat(), converter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ACL_CAT, (cmd)->cmd.aclCat(), converter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.ACL_CAT, (cmd)->cmd.aclCat(), converter)
					.run();
		}
	}

	@Override
	public List<Command> aclCat(final AclCategory aclCategory) {
		final CommandArguments args = CommandArguments.create("aclCategory", aclCategory);
		final io.lettuce.core.AclCategory aclCate =
				(new com.buession.redis.core.internal.convert.lettuce.params.AclCategoryConverter()).convert(
						aclCategory);
		final SetListConverter<CommandType, Command> converter = new SetListConverter<>(
				new CommandTypeConverter());

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ACL_CAT, (cmd)->cmd.aclCat(aclCate),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ACL_CAT, (cmd)->cmd.aclCat(aclCate),
					converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ACL_CAT, (cmd)->cmd.aclCat(aclCate), converter)
					.run(args);
		}
	}

	@Override
	public Status aclSetUser(final String username, final String... rules) {
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		final AclSetuserArgs aclSetuserArgs = new AclSetuserArgs();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ACL_SETUSER,
					(cmd)->cmd.aclSetuser(username, aclSetuserArgs), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ACL_SETUSER,
					(cmd)->cmd.aclSetuser(username, aclSetuserArgs), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ACL_SETUSER,
					(cmd)->cmd.aclSetuser(username, aclSetuserArgs), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules) {
		final CommandArguments args = CommandArguments.create("username", username).put("rules", (Object[]) rules);
		final AclSetuserArgs aclSetuserArgs = new AclSetuserArgs();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ACL_SETUSER,
					(cmd)->cmd.aclSetuser(username.toString(), aclSetuserArgs), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ACL_SETUSER,
					(cmd)->cmd.aclSetuser(username.toString(), aclSetuserArgs), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ACL_SETUSER,
					(cmd)->cmd.aclSetuser(username.toString(), aclSetuserArgs), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public AclUser aclGetUser(final String username) {
		final CommandArguments args = CommandArguments.create("username", username);
		return aclGetUser(args);
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		final CommandArguments args = CommandArguments.create("username", username);
		return aclGetUser(args);
	}

	@Override
	public List<String> aclUsers() {
		if(isPipeline()){
			return new LettucePipelineCommand<List<String>, List<String>>(client, Command.ACL_USERS)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<String>, List<String>>(client, Command.ACL_USERS)
					.run();
		}else{
			return new LettuceCommand<List<String>, List<String>>(client, Command.ACL_USERS)
					.run();
		}
	}

	@Override
	public String aclWhoAmI() {
		if(isPipeline()){
			return new LettucePipelineCommand<String, String>(client, Command.ACL_WHOAMI)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<String, String>(client, Command.ACL_WHOAMI)
					.run();
		}else{
			return new LettuceCommand<String, String>(client, Command.ACL_WHOAMI)
					.run();
		}
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return aclDelUser(args);
	}

	@Override
	public Long aclDelUser(final byte[]... usernames) {
		final CommandArguments args = CommandArguments.create("usernames", (Object[]) usernames);
		return aclDelUser(args);
	}

	@Override
	public String aclGenPass() {
		if(isPipeline()){
			return new LettucePipelineCommand<String, String>(client, Command.ACL_GENPASS)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<String, String>(client, Command.ACL_GENPASS)
					.run();
		}else{
			return new LettuceCommand<String, String>(client, Command.ACL_GENPASS)
					.run();
		}
	}

	@Override
	public List<String> aclList() {
		if(isPipeline()){
			return new LettucePipelineCommand<List<String>, List<String>>(client, Command.ACL_LIST)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<String>, List<String>>(client, Command.ACL_LIST)
					.run();
		}else{
			return new LettuceCommand<List<String>, List<String>>(client, Command.ACL_LIST)
					.run();
		}
	}

	@Override
	public Status aclLoad() {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, Command.ACL_LOAD)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, Command.ACL_LOAD)
					.run();
		}else{
			return new LettuceCommand<Status, Status>(client, Command.ACL_LOAD)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog() {
		if(isPipeline()){
			return new LettucePipelineCommand<List<AclLog>, List<AclLog>>(client, Command.ACL_LOG)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<AclLog>, List<AclLog>>(client, Command.ACL_LOG)
					.run();
		}else{
			return new LettuceCommand<List<AclLog>, List<AclLog>>(client, Command.ACL_LOG)
					.run();
		}
	}

	@Override
	public List<AclLog> aclLog(final int count) {
		final CommandArguments args = CommandArguments.create("count", count);

		if(isPipeline()){
			return new LettucePipelineCommand<List<AclLog>, List<AclLog>>(client, Command.ACL_LOG)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<AclLog>, List<AclLog>>(client, Command.ACL_LOG)
					.run(args);
		}else{
			return new LettuceCommand<List<AclLog>, List<AclLog>>(client, Command.ACL_LOG)
					.run(args);
		}
	}

	@Override
	public Status aclLogReset() {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, Command.ACL_LOGREST)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, Command.ACL_LOGREST)
					.run();
		}else{
			return new LettuceCommand<Status, Status>(client, Command.ACL_LOGREST)
					.run();
		}
	}

	@Override
	public Status aclLogSave() {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, Command.ACL_LOGSAVE)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, Command.ACL_LOGSAVE)
					.run();
		}else{
			return new LettuceCommand<Status, Status>(client, Command.ACL_LOGSAVE)
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

}
