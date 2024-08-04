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
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientAttributeOption;
import com.buession.redis.core.ClientPauseMode;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientTrackingInfo;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.RedisServer;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.ClientKillArgument;
import com.buession.redis.core.command.args.ClientTracking;
import com.buession.redis.core.command.args.HelloArgument;
import com.buession.redis.core.internal.convert.jedis.params.ClientAttributeOptionConverter;
import com.buession.redis.core.internal.convert.jedis.params.ClientPauseModeConverter;
import com.buession.redis.core.internal.convert.jedis.params.ClientTypeConverter;
import com.buession.redis.core.internal.convert.jedis.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.response.ClientConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;
import redis.clients.jedis.args.UnblockType;
import redis.clients.jedis.params.ClientKillParams;

import java.util.List;

/**
 * Jedis 哨兵模式连接命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelConnectionOperations extends AbstractConnectionOperations<JedisSentinelClient> {

	public JedisSentinelConnectionOperations(final JedisSentinelClient client) {
		super(client);
	}

	@Override
	public Status auth(final String user, final String password) {
		final CommandArguments args = CommandArguments.create(user).add(password);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.AUTH)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.AUTH)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.AUTH, (cmd)->cmd.auth(user, password), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create(password);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.AUTH)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.AUTH)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.AUTH, (cmd)->cmd.auth(password), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientCaching() {
		return notCommand(client, Command.CLIENT, SubCommand.CLIENT_CACHING);
	}

	@Override
	public Status clientCaching(final boolean isYes) {
		final CommandArguments args = CommandArguments.create(isYes ? Keyword.Common.YES : Keyword.Common.NO);
		return notCommand(client, Command.CLIENT, SubCommand.CLIENT_CACHING, args);
	}

	@Override
	public String clientGetName() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, Command.CLIENT, SubCommand.CLIENT_GETNAME)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, Command.CLIENT,
					SubCommand.CLIENT_GETNAME)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_GETNAME,
					(cmd)->cmd.clientGetname(), (v)->v)
					.run();
		}
	}

	@Override
	public Integer clientGetRedir() {
		return notCommand(client, Command.CLIENT, SubCommand.CLIENT_GETREDIR);
	}

	@Override
	public Long clientId() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, Command.CLIENT, SubCommand.CLIENT_ID)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, Command.CLIENT, SubCommand.CLIENT_ID)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_ID, (cmd)->cmd.clientId(),
					(v)->v)
					.run();
		}
	}

	@Override
	public Client clientInfo() {
		final ClientConverter clientConverter = new ClientConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Client, Client>(client, Command.CLIENT, SubCommand.CLIENT_INFO)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Client, Client>(client, Command.CLIENT, SubCommand.CLIENT_INFO)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_INFO, (cmd)->cmd.clientInfo(),
					clientConverter)
					.run();
		}
	}

	@Override
	public Status clientKill(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);
		final String addr = host + ':' + port;

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_KILL)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_KILL)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_KILL,
					(cmd)->cmd.clientKill(addr), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long clientKill(final ClientKillArgument... clientKillArguments) {
		final CommandArguments args = CommandArguments.create(clientKillArguments);
		final ClientKillParams clientKillParams = createClientKillParamsFromClientKillArgument(clientKillArguments);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Long, Long>(client, Command.CLIENT, SubCommand.CLIENT_KILL)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Long, Long>(client, Command.CLIENT, SubCommand.CLIENT_KILL)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_KILL,
					(cmd)->cmd.clientKill(clientKillParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Client> clientList() {
		final ClientConverter.ClientListConverter clientListConverter = new ClientConverter.ClientListConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<Client>, List<Client>>(client, Command.CLIENT,
					SubCommand.CLIENT_LIST)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<Client>, List<Client>>(client, Command.CLIENT,
					SubCommand.CLIENT_LIST)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run();
		}
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create(clientType);
		final redis.clients.jedis.args.ClientType jClientType = (new ClientTypeConverter()).convert(clientType);
		final ClientConverter.ClientListConverter clientListConverter = new ClientConverter.ClientListConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<Client>, List<Client>>(client, Command.CLIENT,
					SubCommand.CLIENT_LIST)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<Client>, List<Client>>(client, Command.CLIENT,
					SubCommand.CLIENT_LIST)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_LIST,
					(cmd)->cmd.clientList(jClientType), clientListConverter)
					.run(args);
		}
	}

	@Override
	public List<Client> clientList(final long... clientIds) {
		final CommandArguments args = CommandArguments.create(clientIds);
		final ClientConverter.ClientListConverter clientListConverter = new ClientConverter.ClientListConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<List<Client>, List<Client>>(client, Command.CLIENT,
					SubCommand.CLIENT_LIST)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<List<Client>, List<Client>>(client, Command.CLIENT,
					SubCommand.CLIENT_LIST)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_LIST,
					(cmd)->cmd.clientList(clientIds), clientListConverter)
					.run(args);
		}
	}

	@Override
	public Status clientNoEvict(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_NO_EVICT)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT,
					SubCommand.CLIENT_NO_EVICT)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_NO_EVICT,
					(cmd)->(on ? cmd.clientNoEvictOn() : cmd.clientNoEvictOff()), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientNoTouch(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_NO_TOUCH)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT,
					SubCommand.CLIENT_NO_TOUCH)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_NO_TOUCH,
					(cmd)->(on ? cmd.clientNoTouchOn() : cmd.clientNoTouchOff()), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create(timeout);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE, (cmd)->cmd.clientInfo(),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientPause(final int timeout, final ClientPauseMode pauseMode) {
		final CommandArguments args = CommandArguments.create(timeout).add(pauseMode);
		final redis.clients.jedis.args.ClientPauseMode jClientPauseMode = (new ClientPauseModeConverter()).convert(
				pauseMode);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout, jClientPauseMode), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientReply(final ClientReply option) {
		final CommandArguments args = CommandArguments.create(option);
		return notCommand(client, Command.CLIENT, SubCommand.CLIENT_REPLY, args);
	}

	@Override
	public Status clientSetInfo(final ClientAttributeOption clientAttributeOption, final String value) {
		final CommandArguments args = CommandArguments.create(clientAttributeOption).add(value);
		final redis.clients.jedis.args.ClientAttributeOption jClientAttributeOption =
				(new ClientAttributeOptionConverter()).convert(clientAttributeOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_SETINFO)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT,
					SubCommand.CLIENT_SETINFO)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_SETINFO,
					(cmd)->cmd.clientSetInfo(jClientAttributeOption, value), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientSetInfo(final ClientAttributeOption clientAttributeOption, final byte[] value) {
		final CommandArguments args = CommandArguments.create(clientAttributeOption).add(value);
		final redis.clients.jedis.args.ClientAttributeOption jClientAttributeOption =
				(new ClientAttributeOptionConverter()).convert(clientAttributeOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_SETINFO)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT,
					SubCommand.CLIENT_SETINFO)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_SETINFO,
					(cmd)->cmd.clientSetInfo(jClientAttributeOption, value), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientSetName(final String name) {
		final CommandArguments args = CommandArguments.create(name);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_SETNAME)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT,
					SubCommand.CLIENT_SETNAME)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_SETNAME,
					(cmd)->cmd.clientSetname(name), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create(name);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_SETNAME)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT,
					SubCommand.CLIENT_SETNAME)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_SETNAME,
					(cmd)->cmd.clientSetname(name), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientTracking(final ClientTracking clientTracking) {
		final CommandArguments args = CommandArguments.create(clientTracking);
		return notCommand(client, Command.CLIENT, SubCommand.CLIENT_TRACKING, args);
	}

	@Override
	public ClientTrackingInfo clientTrackingInfo() {
		return notCommand(client, Command.CLIENT, SubCommand.CLIENT_TRACKINGINFO);
	}

	@Override
	public Status clientUnblock(final int clientId) {
		final CommandArguments args = CommandArguments.create(clientId);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_UNBLOCK)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT,
					SubCommand.CLIENT_UNBLOCK)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_UNBLOCK,
					(cmd)->cmd.clientUnblock(clientId), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type) {
		final CommandArguments args = CommandArguments.create(clientId).add(type);
		final UnblockType unblockType = (new ClientUnblockTypeConverter()).convert(type);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_UNBLOCK)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT,
					SubCommand.CLIENT_UNBLOCK)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_UNBLOCK,
					(cmd)->cmd.clientUnblock(clientId, unblockType), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientUnPause() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.CLIENT, SubCommand.CLIENT_UNPAUSE)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.CLIENT,
					SubCommand.CLIENT_UNPAUSE)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.CLIENT, SubCommand.CLIENT_UNPAUSE,
					(cmd)->cmd.clientUnpause(), okStatusConverter)
					.run();
		}
	}

	@Override
	public String echo(final String str) {
		final CommandArguments args = CommandArguments.create(str);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<String, String>(client, Command.ECHO)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<String, String>(client, Command.ECHO)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.ECHO, (cmd)->cmd.echo(str), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] echo(final byte[] str) {
		final CommandArguments args = CommandArguments.create(str);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<byte[], byte[]>(client, Command.ECHO)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<byte[], byte[]>(client, Command.ECHO)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.ECHO, (cmd)->cmd.echo(str), (v)->v)
					.run(args);
		}
	}

	@Override
	public RedisServer hello(final HelloArgument helloArgument) {
		final CommandArguments args = CommandArguments.create(helloArgument);
		return notCommand(client, Command.HELLO, args);
	}

	@Override
	public Status ping() {
		final PingResultConverter pingResultConverter = new PingResultConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.PING)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.PING)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.PING, (cmd)->cmd.ping(), pingResultConverter)
					.run();
		}
	}

	@Override
	public Status quit() {
		return notCommand(client, Command.QUIT);
	}

	@Override
	public Status reset() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<Status, Status>(client, Command.RESET)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.RESET)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.RESET, (cmd)->cmd.reset(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status select(final int db) {
		final CommandArguments args = CommandArguments.create(db);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SELECT, (cmd)->cmd.select(db),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.SELECT)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SELECT, (cmd)->cmd.select(db), okStatusConverter)
					.run(args);
		}
	}

}
