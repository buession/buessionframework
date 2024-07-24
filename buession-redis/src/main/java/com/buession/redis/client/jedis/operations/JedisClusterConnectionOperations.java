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

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientAttributeOption;
import com.buession.redis.core.ClientPauseMode;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.ClientKillArgument;
import com.buession.redis.core.internal.convert.response.PingResultConverter;

import java.util.List;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterConnectionOperations extends AbstractConnectionOperations<JedisClusterClient> {

	public JedisClusterConnectionOperations(final JedisClusterClient client) {
		super(client);
	}

	@Override
	public Status auth(final String user, final String password) {
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);
		return notCommand(client, Command.AUTH, args);
	}

	@Override
	public Status auth(final byte[] user, final byte[] password) {
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);
		return notCommand(client, Command.AUTH, args);
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create("password", password);
		return notCommand(client, Command.AUTH, args);
	}

	@Override
	public Status auth(final byte[] password) {
		final CommandArguments args = CommandArguments.create("password", password);
		return notCommand(client, Command.AUTH, args);
	}

	@Override
	public String echo(final String str) {
		final CommandArguments args = CommandArguments.create("str", str);
		return notCommand(client, Command.ECHO, args);
	}

	@Override
	public byte[] echo(final byte[] str) {
		final CommandArguments args = CommandArguments.create("str", str);
		return notCommand(client, Command.ECHO, args);
	}

	@Override
	public Status ping() {
		final PingResultConverter pingResultConverter = new PingResultConverter();

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, Command.PING)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, Command.PING)
					.run();
		}else{
			return new JedisClusterCommand<>(client, Command.PING, (cmd)->cmd.ping(), pingResultConverter)
					.run();
		}
	}

	@Override
	public Status reset() {
		return notCommand(client, Command.RESET);
	}

	@Override
	public Status quit() {
		return notCommand(client, Command.QUIT);
	}

	@Override
	public Status select(final int db) {
		final CommandArguments args = CommandArguments.create("db", db);
		return notCommand(client, Command.SELECT, args);
	}

	@Override
	public Status clientCaching(final boolean isYes) {
		final CommandArguments args = CommandArguments.create("isYes", isYes);
		return notCommand(client, Command.CLIENT_CACHING, args);
	}

	@Override
	public Long clientId() {
		return notCommand(client, Command.CLIENT_ID);
	}

	@Override
	public Status clientSetName(final String name) {
		final CommandArguments args = CommandArguments.create("name", name);
		return notCommand(client, Command.CLIENT_SETNAME, args);
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create("name", name);
		return notCommand(client, Command.CLIENT_SETNAME, args);
	}

	@Override
	public String clientGetName() {
		return notCommand(client, Command.CLIENT_GETNAME);
	}

	@Override
	public Integer clientGetRedir() {
		return notCommand(client, Command.CLIENT_GETREDIR);
	}

	@Override
	public List<Client> clientList() {
		return notCommand(client, Command.CLIENT_LIST);
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create("clientType", clientType);
		return notCommand(client, Command.CLIENT_LIST, args);
	}

	@Override
	public List<Client> clientList(final long... clientIds) {
		final CommandArguments args = CommandArguments.create("clientIds", clientIds);
		return notCommand(client, Command.CLIENT_LIST, args);
	}

	@Override
	public Client clientInfo() {
		return notCommand(client, Command.CLIENT_INFO);
	}

	@Override
	public Status clientSetInfo(final ClientAttributeOption clientAttributeOption, final String value) {
		final CommandArguments args = CommandArguments.create("clientAttributeOption", clientAttributeOption).put(
				"value", value);
		return notCommand(client, Command.CLIENT_SET_INFO, args);
	}

	@Override
	public Status clientSetInfo(final ClientAttributeOption clientAttributeOption, final byte[] value) {
		final CommandArguments args = CommandArguments.create("clientAttributeOption", clientAttributeOption).put(
				"value", value);
		return notCommand(client, Command.CLIENT_SET_INFO, args);
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		return notCommand(client, Command.CLIENT_PAUSE, args);
	}

	@Override
	public Status clientPause(final int timeout, final ClientPauseMode pauseMode) {
		final CommandArguments args = CommandArguments.create("timeout", timeout).put("pauseMode", pauseMode);
		return notCommand(client, Command.CLIENT_PAUSE, args);
	}

	@Override
	public Status clientUnPause() {
		return notCommand(client, Command.CLIENT_UNPAUSE);
	}

	@Override
	public Status clientReply(final ClientReply option) {
		final CommandArguments args = CommandArguments.create("option", option);
		return notCommand(client, Command.CLIENT_REPLY, args);
	}

	@Override
	public Status clientKill(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return notCommand(client, Command.CLIENT_KILL, args);
	}

	@Override
	public Long clientKill(final ClientKillArgument... clientKillArguments) {
		final CommandArguments args = CommandArguments.create("clientKillArguments", clientKillArguments);
		return notCommand(client, Command.CLIENT_KILL, args);
	}

	@Override
	public Status clientUnblock(final int clientId) {
		final CommandArguments args = CommandArguments.create("clientId", clientId);
		return notCommand(client, Command.CLIENT_UNBLOCK, args);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type) {
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);
		return notCommand(client, Command.CLIENT_UNBLOCK, args);
	}

	@Override
	public Status clientNoEvict(final boolean on) {
		final CommandArguments args = CommandArguments.create("on", on);
		return notCommand(client, Command.CLIENT_NO_EVICT, args);
	}

	@Override
	public Status clientNoTouch(final boolean on) {
		final CommandArguments args = CommandArguments.create("on", on);
		return notCommand(client, Command.CLIENT_NO_TOUCH, args);
	}

}
