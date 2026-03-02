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
package com.buession.redis.client.jedis.command;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientInfoOption;
import com.buession.redis.core.ClientPauseMode;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.Hello;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.TrackingInfo;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ConnectionCommands;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.TrackingArgument;
import com.buession.redis.core.internal.convert.jedis.params.ClientTypeConverter;
import com.buession.redis.core.internal.convert.jedis.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.args.UnblockType;

import java.util.List;

/**
 * Jedis 连接命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisConnectionCommands extends AbstractJedisRedisCommands implements ConnectionCommands {

	public JedisConnectionCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status auth(final String user, final String password) {
		final CommandArguments args = CommandArguments.create(user).add(password);
		return executeCommand(Command.AUTH, args);
	}

	@Override
	public Status auth(final byte[] user, final byte[] password) {
		final CommandArguments args = CommandArguments.create(user).add(password);
		return executeCommand(Command.AUTH, args);
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create(password);
		return executeCommand(Command.AUTH, args);
	}

	@Override
	public Status auth(final byte[] password) {
		final CommandArguments args = CommandArguments.create(password);
		return executeCommand(Command.AUTH, args);
	}

	@Override
	public Status clientCaching(final boolean isYes) {
		final CommandArguments args = CommandArguments.create(isYes ? Keyword.Common.YES : Keyword.Common.NO);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_CACHING, args);
	}

	@Override
	public String clientGetName() {
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_GETNAME);
	}

	@Override
	public Integer clientGetRedir() {
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_GETREDIR);
	}

	@Override
	public Long clientId() {
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_ID);
	}

	@Override
	public Client clientInfo() {
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_INFO);
	}

	@Override
	public Status clientKill(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_KILL, args);
	}

	@Override
	public Status clientKill(final byte[] host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_KILL, args);
	}

	@Override
	public List<Client> clientList() {
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_LIST);
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create("TYPE").add(clientType);
		final redis.clients.jedis.args.ClientType jClientType = (new ClientTypeConverter()).convert(clientType);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_LIST, args);
	}

	@Override
	public List<Client> clientList(final long... ids) {
		final CommandArguments args = CommandArguments.create("ID").add(ids);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_LIST, args);
	}

	@Override
	public Status clientNoEvict(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_NO_EVICT, args);
	}

	@Override
	public Status clientNoTouch(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_NO_TOUCH, args);
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create(timeout);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_PAUSE, args);
	}

	@Override
	public Status clientPause(final int timeout, final ClientPauseMode pauseMode) {
		final CommandArguments args = CommandArguments.create(timeout).add(pauseMode);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_PAUSE, args);
	}

	@Override
	public Status clientReply(final ClientReply option) {
		final CommandArguments args = CommandArguments.create(option);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_REPLY, args);
	}

	@Override
	public Status clientSetInfo(final ClientInfoOption option, final String value) {
		final CommandArguments args = CommandArguments.create(option).add(value);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_SETINFO, args);
	}

	@Override
	public Status clientSetName(final String name) {
		final CommandArguments args = CommandArguments.create(name);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_SETNAME, args);
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create(name);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_SETNAME, args);
	}

	@Override
	public Status clientTracking(final boolean on, final TrackingArgument argument) {
		final CommandArguments args =
				CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF).add(argument);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_TRACKING, args);
	}

	@Override
	public TrackingInfo clientTrackingInfo() {
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_TRACKINGINFO);
	}

	@Override
	public Status clientUnblock(final int clientId) {
		final CommandArguments args = CommandArguments.create(clientId);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_UNBLOCK, args);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type) {
		final CommandArguments args = CommandArguments.create(clientId).add(type);
		final UnblockType unblockType = (new ClientUnblockTypeConverter()).convert(type);
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_UNBLOCK, args);
	}

	@Override
	public Status clientUnpause() {
		return executeCommand(Command.CLIENT, SubCommand.CLIENT_UNPAUSE);
	}

	@Override
	public String echo(final String str) {
		final CommandArguments args = CommandArguments.create(str);
		return executeCommand(Command.ECHO, args, (cmd)->cmd.echo(str), (v)->v);
	}

	@Override
	public byte[] echo(final byte[] str) {
		final String s = SafeEncoder.encode(str);
		final CommandArguments args = CommandArguments.create(s);
		return executeCommand(Command.ECHO, args, (cmd)->cmd.echo(s), SafeEncoder::encode);
	}

	@Override
	public Hello hello() {
		return executeCommand(Command.HELLO);
	}

	@Override
	public Hello hello(int protover) {
		final CommandArguments args = CommandArguments.create(protover);
		return executeCommand(Command.HELLO, args);
	}

	@Override
	public Hello hello(int protover, String password) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(password);
		return executeCommand(Command.HELLO, args);
	}

	@Override
	public Hello hello(int protover, byte[] password) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(password);
		return executeCommand(Command.HELLO, args);
	}

	@Override
	public Hello hello(int protover, String username, String password) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(username).add(password);
		return executeCommand(Command.HELLO, args);
	}

	@Override
	public Hello hello(int protover, byte[] username, byte[] password) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(username).add(password);
		return executeCommand(Command.HELLO, args);
	}

	@Override
	public Hello hello(int protover, String username, String password, String clientName) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(username).add(password).add(
				"SETNAME").add(clientName);
		return executeCommand(Command.HELLO, args);
	}

	@Override
	public Hello hello(int protover, byte[] username, byte[] password, byte[] clientName) {
		final CommandArguments args = CommandArguments.create(protover).add("AUTH").add(username).add(password).add(
				"SETNAME").add(clientName);
		return executeCommand(Command.HELLO, args);
	}

	@Override
	public Status ping() {
		return executeCommand(Command.PING, (cmd)->cmd.ping(), new PingResultConverter());
	}

	@Override
	public Status quit() {
		return executeCommand(Command.QUIT);
	}

	@Override
	public Status reset() {
		return executeCommand(Command.RESET);
	}

	@Override
	public Status select(final int db) {
		final CommandArguments args = CommandArguments.create(db);
		return executeCommand(Command.SELECT, args);
	}

}
