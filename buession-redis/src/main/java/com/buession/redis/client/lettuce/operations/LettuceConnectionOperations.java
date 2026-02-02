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
package com.buession.redis.client.lettuce.operations;

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.ConnectionOperations;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientInfoOption;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.Hello;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.TrackingInfo;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.internal.convert.lettuce.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.lettuce.response.TrackingInfoTrackingInfoConverter;
import com.buession.redis.core.internal.convert.response.ClientConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;
import com.buession.redis.core.internal.lettuce.CompositeArgumentUtils;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.ClientListArgs;
import io.lettuce.core.UnblockType;

import java.util.List;

/**
 * Lettuce 单机模式连接命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceConnectionOperations extends AbstractLettuceRedisOperations implements ConnectionOperations {

	public LettuceConnectionOperations(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Status auth(final String user, final String password) {
		final CommandArguments args = CommandArguments.create(user).add(password);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.AUTH)
				.executor((cmd)->cmd.auth(user, password))
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status auth(final byte[] user, final byte[] password) {
		return auth(SafeEncoder.encode(user), SafeEncoder.encode(password));
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create(password);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.AUTH)
				.executor((cmd)->cmd.auth(password))
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status auth(final byte[] password) {
		return auth(SafeEncoder.encode(password));
	}

	@Override
	public Status clientCaching(final boolean isYes) {
		final CommandArguments args = CommandArguments.create(isYes);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_CACHING)
				.executor((cmd)->cmd.clientCaching(isYes)).arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public String clientGetName() {
		return LettuceCommandBuilder.<byte[], String>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_GETNAME)
				.executor((cmd)->cmd.clientGetname()).converter(binaryToStringConverter).run();
	}

	@Override
	public Integer clientGetRedir() {
		return LettuceCommandBuilder.<Long, Integer>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_GETREDIR)
				.executor((cmd)->cmd.clientGetredir()).converter(Long::intValue).run();
	}

	@Override
	public Long clientId() {
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_ID)
				.executor((cmd)->cmd.clientId()).converter((v)->v).run();
	}

	@Override
	public Client clientInfo() {
		return LettuceCommandBuilder.<String, Client>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_INFO)
				.executor((cmd)->cmd.clientInfo()).converter(new ClientConverter()).run();
	}

	@Override
	public Status clientKill(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_KILL)
				.executor((cmd)->cmd.clientKill(host + ':' + port)).arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clientKill(final byte[] host, final int port) {
		return clientKill(SafeEncoder.encode(host), port);
	}

	@Override
	public List<Client> clientList() {
		return LettuceCommandBuilder.<String, List<Client>>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_LIST)
				.executor((cmd)->cmd.clientList()).converter(new ClientConverter.ClientListConverter()).run();
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create("TYPE").add(clientType);
		ClientListArgs listArgs = null;
		switch(clientType){
			case NORMAL:
				listArgs = ClientListArgs.Builder.typeNormal();
				break;
			case MASTER:
				listArgs = ClientListArgs.Builder.typeMaster();
				break;
			case SLAVE:
			case REPLICA:
				listArgs = ClientListArgs.Builder.typeReplica();
				break;
			case PUBSUB:
				listArgs = ClientListArgs.Builder.typePubsub();
				break;
			default:
				break;
		}

		final ClientListArgs finalListArgs = listArgs;
		return LettuceCommandBuilder.<String, List<Client>>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_LIST)
				.executor((cmd)->cmd.clientList(finalListArgs)).arguments(args)
				.converter(new ClientConverter.ClientListConverter()).run();
	}

	@Override
	public List<Client> clientList(final long... ids) {
		final CommandArguments args = CommandArguments.create("ID").add(ids);
		return LettuceCommandBuilder.<String, List<Client>>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_LIST)
				.executor((cmd)->cmd.clientList(ClientListArgs.Builder.ids(ids))).arguments(args)
				.converter(new ClientConverter.ClientListConverter()).run();
	}

	@Override
	public Status clientNoEvict(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_NO_EVICT)
				.executor((cmd)->cmd.clientNoEvict(on)).arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clientNoTouch(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_NO_TOUCH)
				.arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create(timeout);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_PAUSE)
				.executor((cmd)->cmd.clientPause(timeout)).arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clientReply(final ClientReply option) {
		final CommandArguments args = CommandArguments.create(option);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_REPLY)
				.arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clientSetInfo(final ClientInfoOption option, final String value) {
		final CommandArguments args = CommandArguments.create(option).add(value);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_SETINFO)
				.executor((cmd)->cmd.clientSetinfo(option.getValue(), value)).arguments(args)
				.converter(okStatusConverter).run();
	}

	@Override
	public Status clientSetName(final String name) {
		return clientSetName(SafeEncoder.encode(name));
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create(name);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_SETNAME)
				.executor((cmd)->cmd.clientSetname(name)).arguments(args).converter(okStatusConverter).run();
	}

	@Override
	public Status clientTracking(final boolean on, final TrackingArgument argument) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF)
				.add(argument);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_TRACKING)
				.executor((cmd)->cmd.clientTracking(
						CompositeArgumentUtils.trackingArgs(argument).enabled(on))).arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public TrackingInfo clientTrackingInfo() {
		return LettuceCommandBuilder.<io.lettuce.core.TrackingInfo, TrackingInfo>newBuilder(client, Command.CLIENT,
						SubCommand.CLIENT_TRACKINGINFO).executor((cmd)->cmd.clientTrackinginfo())
				.converter(new TrackingInfoTrackingInfoConverter()).run();
	}

	@Override
	public Status clientUnblock(final int clientId) {
		final CommandArguments args = CommandArguments.create(clientId);
		return LettuceCommandBuilder.<Long, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_TRACKINGINFO)
				.executor((cmd)->cmd.clientUnblock(clientId, UnblockType.ERROR))
				.arguments(args)
				.converter(oneStatusConverter).run();
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type) {
		final CommandArguments args = CommandArguments.create(clientId).add(type);
		final UnblockType unblockType = (new ClientUnblockTypeConverter()).convert(type);
		return LettuceCommandBuilder.<Long, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_UNBLOCK)
				.executor((cmd)->cmd.clientUnblock(clientId, unblockType))
				.arguments(args)
				.converter(oneStatusConverter).run();
	}

	@Override
	public Status clientUnpause() {
		return LettuceCommandBuilder.<Long, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_UNPAUSE)
				.converter(oneStatusConverter).run();
	}

	@Override
	public String echo(final String str) {
		final CommandArguments args = CommandArguments.create(str);
		return LettuceCommandBuilder.<byte[], String>newBuilder(client, Command.ECHO)
				.executor((cmd)->cmd.echo(SafeEncoder.encode(str)))
				.arguments(args)
				.converter(binaryToStringConverter).run();
	}

	@Override
	public byte[] echo(final byte[] str) {
		final CommandArguments args = CommandArguments.create(str);
		return LettuceCommandBuilder.<byte[], byte[]>newBuilder(client, Command.ECHO)
				.executor((cmd)->cmd.echo(str))
				.arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Hello hello() {
		return hello(null);
	}

	@Override
	public Hello hello(int protover) {
		final CommandArguments args = CommandArguments.create(protover);
		return hello(args);
	}

	@Override
	public Hello hello(int protover, String password) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(password);
		return hello(args);
	}

	@Override
	public Hello hello(int protover, byte[] password) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(password);
		return hello(args);
	}

	@Override
	public Hello hello(int protover, String username, String password) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(username).add(password);
		return hello(args);
	}

	@Override
	public Hello hello(int protover, byte[] username, byte[] password) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(username).add(password);
		return hello(args);
	}

	@Override
	public Hello hello(int protover, String username, String password, String clientName) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(username).add(password).add(
				"SETNAME").add(clientName);
		return hello(args);
	}

	@Override
	public Hello hello(int protover, byte[] username, byte[] password, byte[] clientName) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(username).add(password).add(
				"SETNAME").add(clientName);
		return hello(args);
	}

	@Override
	public Status ping() {
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.PING)
				.executor((cmd)->cmd.ping())
				.converter(new PingResultConverter())
				.run();
	}

	@Override
	public Status quit() {
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.QUIT)
				.executor((cmd)->cmd.quit())
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status reset() {
		return LettuceCommandBuilder.<Status, Status>newBuilder(client, Command.RESET)
				.converter((v)->v)
				.run();
	}

	@Override
	public Status select(final int db) {
		final CommandArguments args = CommandArguments.create(db);
		return LettuceCommandBuilder.<Status, Status>newBuilder(client, Command.SELECT)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	private Hello hello(final CommandArguments args) {
		return LettuceCommandBuilder.<Hello, Hello>newBuilder(client, Command.HELLO)
				.converter((v)->v)
				.run();
	}

}
