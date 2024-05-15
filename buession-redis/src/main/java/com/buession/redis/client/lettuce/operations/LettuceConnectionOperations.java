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
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.response.ClientConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.UnblockType;

import java.util.List;

/**
 * Lettuce 单机模式连接命令操作
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class LettuceConnectionOperations extends AbstractConnectionOperations<LettuceStandaloneClient> {

	public LettuceConnectionOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Status auth(final String user, final String password) {
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);
		return new LettuceCommand<>(client, ProtocolCommand.AUTH, (cmd)->cmd.auth(password), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create("password", password);
		return new LettuceCommand<>(client, ProtocolCommand.AUTH, (cmd)->cmd.auth(password), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public String echo(final String str) {
		final CommandArguments args = CommandArguments.create("str", str);
		return new LettuceCommand<>(client, ProtocolCommand.ECHO, (cmd)->cmd.echo(SafeEncoder.encode(str)),
				Converters.BINARY_TO_STRING_CONVERTER)
				.run(args);
	}

	@Override
	public byte[] echo(final byte[] str) {
		final CommandArguments args = CommandArguments.create("str", str);
		return new LettuceCommand<>(client, ProtocolCommand.ECHO, (cmd)->cmd.echo(str), (v)->v)
				.run(args);
	}

	@Override
	public Status ping() {
		return new LettuceCommand<>(client, ProtocolCommand.ECHO, (cmd)->cmd.ping(), PingResultConverter.INSTANCE)
				.run();
	}

	@Override
	public Status reset() {
		return new LettuceCommand<>(client, ProtocolCommand.RESET, (cmd)->{
			cmd.reset();
			return Status.SUCCESS;
		}, (v)->v)
				.run();
	}

	@Override
	public Status quit() {
		return new LettuceCommand<>(client, ProtocolCommand.QUIT, (cmd)->cmd.quit(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status select(final int db) {
		final CommandArguments args = CommandArguments.create("db", db);
		return new LettuceCommand<>(client, ProtocolCommand.SELECT, (cmd)->cmd.select(db), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clientCaching(final boolean isYes) {
		final CommandArguments args = CommandArguments.create("isYes", isYes);
		return new LettuceCommand<>(client, ProtocolCommand.CLIENT_CACHING, OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Long clientId() {
		return new LettuceCommand<>(client, ProtocolCommand.CLIENT_ID, (cmd)->cmd.clientId(), (v)->v)
				.run();
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create("name", name);
		return new LettuceCommand<>(client, ProtocolCommand.CLIENT_SETNAME, (cmd)->cmd.clientSetname(name),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public String clientGetName() {
		return new LettuceCommand<>(client, ProtocolCommand.CLIENT_GETNAME, (cmd)->cmd.clientGetname(),
				Converters.BINARY_TO_STRING_CONVERTER)
				.run();
	}

	@Override
	public Integer clientGetRedir() {
		return new LettuceCommand<Integer, Integer>(client, ProtocolCommand.CLIENT_GETREDIR)
				.run();
	}

	@Override
	public List<Client> clientList() {
		return new LettuceCommand<>(client, ProtocolCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
				ClientConverter.ClientListConverter.INSTANCE)
				.run();
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create("clientType", clientType);
		return new LettuceCommand<>(client, ProtocolCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
				ClientConverter.ClientListConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Client clientInfo() {
		return new LettuceCommand<Client, Client>(client, ProtocolCommand.CLIENT_INFO)
				.run();
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		return new LettuceCommand<>(client, ProtocolCommand.CLIENT_PAUSE, (cmd)->cmd.clientPause(timeout),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clientReply(final ClientReply option) {
		final CommandArguments args = CommandArguments.create("option", option);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.CLIENT_REPLY)
				.run(args);
	}

	@Override
	public Status clientKill(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new LettuceCommand<>(client, ProtocolCommand.CLIENT_KILL, (cmd)->cmd.clientKill(host + ':' + port),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clientUnblock(final int clientId) {
		final CommandArguments args = CommandArguments.create("clientId", clientId);
		return new LettuceCommand<>(client, ProtocolCommand.CLIENT_KILL, (cmd)->cmd.clientUnblock(clientId,
				UnblockType.ERROR), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type) {
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);
		return new LettuceCommand<>(client, ProtocolCommand.CLIENT_KILL, (cmd)->cmd.clientUnblock(clientId,
				ClientUnblockTypeConverter.INSTANCE.convert(type)), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

}
