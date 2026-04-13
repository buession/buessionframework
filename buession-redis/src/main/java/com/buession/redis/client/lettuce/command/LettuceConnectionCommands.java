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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.command;

import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.command.args.connection.ClientInfoOption;
import com.buession.redis.core.command.args.connection.ClientPauseMode;
import com.buession.redis.core.command.args.connection.ClientReply;
import com.buession.redis.core.command.args.connection.ClientType;
import com.buession.redis.core.command.args.connection.ClientUnblockType;
import com.buession.redis.core.Hello;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.TrackingInfo;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ConnectionCommands;
import com.buession.redis.core.command.RedisSubCommand;
import com.buession.redis.core.command.args.connection.TrackingArgument;
import com.buession.redis.core.internal.convert.lettuce.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.lettuce.response.TrackingInfoTrackingInfoConverter;
import com.buession.redis.core.internal.convert.response.ClientConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceClientListArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceTrackingArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.UnblockType;

import java.util.List;

/**
 * Lettuce 连接命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceConnectionCommands extends AbstractLettuceRedisCommands implements ConnectionCommands {

	public LettuceConnectionCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Status auth(final String user, final String password) {
		final CommandArguments args = CommandArguments.create(user, password);
		return executeCommand(RedisCommand.AUTH, args, (cmd)->cmd.auth(user, password), (cmd)->cmd.auth(user, password),
				new OkStatusConverter());
	}

	@Override
	public Status auth(final byte[] user, final byte[] password) {
		return auth(SafeEncoder.encode(user), SafeEncoder.encode(password));
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create(password);
		return executeCommand(RedisCommand.AUTH, args, (cmd)->cmd.auth(password), (cmd)->cmd.auth(password),
				new OkStatusConverter());
	}

	@Override
	public Status auth(final byte[] password) {
		return auth(SafeEncoder.encode(password));
	}

	@Override
	public Status clientCaching(final boolean isYes) {
		final CommandArguments args = CommandArguments.create(isYes ? Keyword.Common.YES : Keyword.Common.NO);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_CACHING, args,
				(cmd)->cmd.clientCaching(isYes), (cmd)->cmd.clientCaching(isYes),
				new OkStatusConverter());
	}

	@Override
	public String clientGetName() {
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_GETNAME, (cmd)->cmd.clientGetname(),
				(cmd)->cmd.clientGetname(), SafeEncoder::encode);
	}

	@Override
	public Integer clientGetRedir() {
		return executeCommand(
				RedisCommand.CLIENT, RedisSubCommand.CLIENT_GETREDIR, (cmd)->cmd.clientGetredir(),
				(cmd)->cmd.clientGetredir(), Long::intValue);
	}

	@Override
	public Long clientId() {
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_ID, (cmd)->cmd.clientId(),
				(cmd)->cmd.clientId(), (v)->v);
	}

	@Override
	public Client clientInfo() {
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_INFO,
				(cmd)->cmd.clientInfo(), (cmd)->cmd.clientInfo(),
				new ClientConverter());
	}

	@Override
	public Status clientKill(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host, port);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_KILL, args,
				(cmd)->cmd.clientKill(host + ':' + port), (cmd)->cmd.clientKill(host + ':' + port),
				new OkStatusConverter());
	}

	@Override
	public Status clientKill(final byte[] host, final int port) {
		return clientKill(SafeEncoder.encode(host), port);
	}

	@Override
	public List<Client> clientList() {
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
				(cmd)->cmd.clientList(), new ClientConverter.ClientListConverter());
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create("TYPE", clientType);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_LIST, args, (cmd)->switch(clientType){
			case NORMAL -> cmd.clientList(LettuceClientListArgs.Builder.typeNormal());
			case MASTER -> cmd.clientList(LettuceClientListArgs.Builder.typeMaster());
			case SLAVE, REPLICA -> cmd.clientList(LettuceClientListArgs.Builder.typeReplica());
			case PUBSUB -> cmd.clientList(LettuceClientListArgs.Builder.typePubsub());
		}, (cmd)->switch(clientType){
			case NORMAL -> cmd.clientList(LettuceClientListArgs.Builder.typeNormal());
			case MASTER -> cmd.clientList(LettuceClientListArgs.Builder.typeMaster());
			case SLAVE, REPLICA -> cmd.clientList(LettuceClientListArgs.Builder.typeReplica());
			case PUBSUB -> cmd.clientList(LettuceClientListArgs.Builder.typePubsub());
		}, new ClientConverter.ClientListConverter());
	}

	@Override
	public List<Client> clientList(final long... ids) {
		final CommandArguments args = CommandArguments.create().add("ID", ids);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_LIST, args,
				(cmd)->cmd.clientList(new LettuceClientListArgs(ids)),
				(cmd)->cmd.clientList(new LettuceClientListArgs(ids)), new ClientConverter.ClientListConverter());
	}

	@Override
	public Status clientNoEvict(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_NO_EVICT, args, (cmd)->cmd.clientNoEvict(on),
				(cmd)->cmd.clientNoEvict(on), new OkStatusConverter());
	}

	@Override
	public Status clientNoTouch(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_NO_TOUCH, args);
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create(timeout);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_PAUSE, args, (cmd)->cmd.clientPause(timeout),
				(cmd)->cmd.clientPause(timeout), new OkStatusConverter());
	}

	@Override
	public Status clientPause(final int timeout, final ClientPauseMode pauseMode) {
		final CommandArguments args = CommandArguments.create(timeout).add(pauseMode);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_PAUSE, args, (cmd)->cmd.clientPause(timeout),
				(cmd)->cmd.clientPause(timeout), new OkStatusConverter());
	}

	@Override
	public Status clientReply(final ClientReply option) {
		final CommandArguments args = CommandArguments.create(option);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_REPLY, args);
	}

	@Override
	public Status clientSetInfo(final ClientInfoOption option, final String value) {
		final CommandArguments args = CommandArguments.create(option).add(value);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_SETINFO, args,
				(cmd)->cmd.clientSetinfo(option.getValue(), value), (cmd)->cmd.clientSetinfo(option.getValue(), value),
				new OkStatusConverter());
	}

	@Override
	public Status clientSetName(final String name) {
		return clientSetName(SafeEncoder.encode(name));
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create(name);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_SETNAME, args, (cmd)->cmd.clientSetname(name),
				(cmd)->cmd.clientSetname(name), new OkStatusConverter());
	}

	@Override
	public Status clientTracking(final boolean on, final TrackingArgument argument) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF)
				.add(argument);
		final LettuceTrackingArgs lettuceTrackingArgs = new LettuceTrackingArgs(argument);

		lettuceTrackingArgs.enabled(on);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_TRACKING, args,
				(cmd)->cmd.clientTracking(lettuceTrackingArgs), (cmd)->cmd.clientTracking(lettuceTrackingArgs),
				new OkStatusConverter());
	}

	@Override
	public TrackingInfo clientTrackingInfo() {
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_TRACKINGINFO, (cmd)->cmd.clientTrackinginfo(),
				(cmd)->cmd.clientTrackinginfo(), new TrackingInfoTrackingInfoConverter());
	}

	@Override
	public Status clientUnblock(final int clientId) {
		final CommandArguments args = CommandArguments.create(clientId);
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_TRACKINGINFO, args,
				(cmd)->cmd.clientUnblock(clientId, UnblockType.ERROR),
				(cmd)->cmd.clientUnblock(clientId, UnblockType.ERROR), new OneStatusConverter());
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type) {
		final CommandArguments args = CommandArguments.create(clientId).add(type);
		final ClientUnblockTypeConverter clientUnblockTypeConverter = new ClientUnblockTypeConverter();
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_UNBLOCK, args,
				(cmd)->cmd.clientUnblock(clientId, clientUnblockTypeConverter.convert(type)),
				(cmd)->cmd.clientUnblock(clientId, clientUnblockTypeConverter.convert(type)), new OneStatusConverter());
	}

	@Override
	public Status clientUnpause() {
		return executeCommand(RedisCommand.CLIENT, RedisSubCommand.CLIENT_UNPAUSE);
	}

	@Override
	public String echo(final String str) {
		final CommandArguments args = CommandArguments.create(str);
		return executeCommand(RedisCommand.ECHO, args, (cmd)->cmd.echo(SafeEncoder.encode(str)),
				(cmd)->cmd.echo(SafeEncoder.encode(str)), SafeEncoder::encode);
	}

	@Override
	public byte[] echo(final byte[] str) {
		final CommandArguments args = CommandArguments.create(str);
		return executeCommand(RedisCommand.ECHO, args, (cmd)->cmd.echo(str), (cmd)->cmd.echo(str));
	}

	@Override
	public Hello hello() {
		return executeCommand(RedisCommand.HELLO);
	}

	@Override
	public Hello hello(int protover) {
		final CommandArguments args = CommandArguments.create(protover);
		return executeCommand(RedisCommand.HELLO, args);
	}

	@Override
	public Hello hello(int protover, String password) {
		final CommandArguments args = CommandArguments.create(protover).add(Keyword.Conn.AUTH).add(password);
		return executeCommand(RedisCommand.HELLO, args);
	}

	@Override
	public Hello hello(int protover, byte[] password) {
		final CommandArguments args = CommandArguments.create(protover).add(Keyword.Conn.AUTH).add(password);
		return executeCommand(RedisCommand.HELLO, args);
	}

	@Override
	public Hello hello(int protover, String username, String password) {
		final CommandArguments args = CommandArguments.create(protover).add(Validate.isEmpty(username) ?
				Keyword.Conn.AUTH : Keyword.Conn.AUTH2).add(username, password);
		return executeCommand(RedisCommand.HELLO, args);
	}

	@Override
	public Hello hello(int protover, byte[] username, byte[] password) {
		final CommandArguments args = CommandArguments.create(protover).add(Validate.isEmpty(username) ?
				Keyword.Conn.AUTH : Keyword.Conn.AUTH2).add(username, password);
		return executeCommand(RedisCommand.HELLO, args);
	}

	@Override
	public Hello hello(int protover, String username, String password, String clientName) {
		final CommandArguments args = CommandArguments.create(protover).add(Validate.isEmpty(username) ?
				Keyword.Conn.AUTH : Keyword.Conn.AUTH2).add(username, password).add("SETNAME", clientName);
		return executeCommand(RedisCommand.HELLO, args);
	}

	@Override
	public Hello hello(int protover, byte[] username, byte[] password, byte[] clientName) {
		final CommandArguments args = CommandArguments.create(protover).add(Validate.isEmpty(username) ?
				Keyword.Conn.AUTH : Keyword.Conn.AUTH2).add(username, password).add("SETNAME", clientName);
		return executeCommand(RedisCommand.HELLO, args);
	}

	@Override
	public Status ping() {
		return executeCommand(RedisCommand.PING, (cmd)->cmd.ping(), (cmd)->cmd.ping(), new PingResultConverter());
	}

	@Override
	public Status quit() {
		return executeCommand(RedisCommand.QUIT, (cmd)->cmd.quit(), (cmd)->cmd.quit(), new OkStatusConverter());
	}

	@Override
	public Status reset() {
		return executeCommand(RedisCommand.RESET);
	}

	@Override
	public Status select(final int db) {
		final CommandArguments args = CommandArguments.create(db);
		return executeCommand(RedisCommand.SELECT, args);
	}

}
