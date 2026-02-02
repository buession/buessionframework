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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
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
import com.buession.redis.core.internal.convert.jedis.params.ClientTypeConverter;
import com.buession.redis.core.internal.convert.jedis.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.response.ClientConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.args.UnblockType;

import java.util.List;

/**
 * Jedis 连接命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisConnectionOperations extends AbstractJedisRedisOperations implements ConnectionOperations {

	public JedisConnectionOperations(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status auth(final String user, final String password) {
		final CommandArguments args = CommandArguments.create(user).add(password);
		return auth(args);
	}

	@Override
	public Status auth(final byte[] user, final byte[] password) {
		final CommandArguments args = CommandArguments.create(user).add(password);
		return auth(args);
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create(password);
		return auth(args);
	}

	@Override
	public Status auth(final byte[] password) {
		final CommandArguments args = CommandArguments.create(password);
		return auth(args);
	}

	@Override
	public Status clientCaching(final boolean isYes) {
		final CommandArguments args = CommandArguments.create(isYes);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_CACHING)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public String clientGetName() {
		return JedisCommandBuilder.<String, String>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_GETNAME)
				.converter((v)->v)
				.run();
	}

	@Override
	public Integer clientGetRedir() {
		return JedisCommandBuilder.<Integer, Integer>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_GETREDIR)
				.converter((v)->v)
				.run();
	}

	@Override
	public Long clientId() {
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_ID)
				.converter((v)->v)
				.run();
	}

	@Override
	public Client clientInfo() {
		return JedisCommandBuilder.<String, Client>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_INFO)
				.converter(new ClientConverter())
				.run();
	}

	@Override
	public Status clientKill(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_KILL)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Status clientKill(final byte[] host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_KILL)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public List<Client> clientList() {
		return clientList((CommandArguments) null);
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create("TYPE").add(clientType);
		final redis.clients.jedis.args.ClientType jClientType = (new ClientTypeConverter()).convert(clientType);
		return clientList(args);
	}

	@Override
	public List<Client> clientList(final long... ids) {
		final CommandArguments args = CommandArguments.create("ID").add(ids);
		return clientList(args);
	}

	@Override
	public Status clientNoEvict(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_NO_EVICT)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status clientNoTouch(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_NO_TOUCH)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create(timeout);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_PAUSE)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status clientReply(final ClientReply option) {
		final CommandArguments args = CommandArguments.create(option);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_REPLY)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status clientSetInfo(final ClientInfoOption option, final String value) {
		final CommandArguments args = CommandArguments.create(option).add(value);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_SETINFO)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status clientSetName(final String name) {
		final CommandArguments args = CommandArguments.create(name);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_SETNAME)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create(name);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_SETNAME)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status clientTracking(final boolean on, final TrackingArgument argument) {
		final CommandArguments args =
				CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF).add(argument);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_TRACKING)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public TrackingInfo clientTrackingInfo() {
		return JedisCommandBuilder.<TrackingInfo, TrackingInfo>newBuilder(client, Command.CLIENT,
						SubCommand.CLIENT_TRACKINGINFO)
				.converter((v)->v)
				.run();
	}

	@Override
	public Status clientUnblock(final int clientId) {
		final CommandArguments args = CommandArguments.create(clientId);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_UNBLOCK)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type) {
		final CommandArguments args = CommandArguments.create(clientId).add(type);
		final UnblockType unblockType = (new ClientUnblockTypeConverter()).convert(type);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_UNBLOCK)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Status clientUnpause() {
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_UNPAUSE)
				.converter((v)->v)
				.run();
	}

	@Override
	public String echo(final String str) {
		final CommandArguments args = CommandArguments.create(str);
		return JedisCommandBuilder.<String, String>newBuilder(client, Command.ECHO)
				.executor((cmd)->cmd.echo(str))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public byte[] echo(final byte[] str) {
		final String s = SafeEncoder.encode(str);
		final CommandArguments args = CommandArguments.create(s);
		return JedisCommandBuilder.<String, byte[]>newBuilder(client, Command.ECHO)
				.executor((cmd)->cmd.echo(s))
				.arguments(args)
				.converter(stringToBinaryConverter)
				.run();
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
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.HELLO)
				.executor((cmd)->cmd.ping())
				.converter(new PingResultConverter())
				.run();
	}

	@Override
	public Status quit() {
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.QUIT)
				.converter((v)->v)
				.run();
	}

	@Override
	public Status reset() {
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.RESET)
				.converter((v)->v)
				.run();
	}

	@Override
	public Status select(final int db) {
		final CommandArguments args = CommandArguments.create(db);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.SELECT)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	private Status auth(final CommandArguments args) {
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.AUTH)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	private List<Client> clientList(final CommandArguments args) {
		return JedisCommandBuilder.<String, List<Client>>newBuilder(client, Command.CLIENT, SubCommand.CLIENT_LIST)
				.arguments(args)
				.converter(new ClientConverter.ClientListConverter())
				.run();
	}

	private Hello hello(final CommandArguments args) {
		return JedisCommandBuilder.<Hello, Hello>newBuilder(client, Command.HELLO)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

}
