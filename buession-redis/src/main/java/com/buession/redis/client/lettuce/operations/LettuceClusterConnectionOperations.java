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

import com.buession.core.converter.Converter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientAttributeOption;
import com.buession.redis.core.ClientPauseMode;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.ClientKillArgument;
import com.buession.redis.core.internal.convert.lettuce.params.ClientTypeConverter;
import com.buession.redis.core.internal.convert.lettuce.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.response.ClientConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;
import com.buession.redis.core.internal.lettuce.utils.KillArgsUtils;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.ClientListArgs;
import io.lettuce.core.KillArgs;
import io.lettuce.core.UnblockType;

import java.util.List;

/**
 * Lettuce 集群模式连接命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceClusterConnectionOperations extends AbstractConnectionOperations<LettuceClusterClient> {

	public LettuceClusterConnectionOperations(final LettuceClusterClient client) {
		super(client);
	}

	@Override
	public Status auth(final String user, final String password) {
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.AUTH, (cmd)->cmd.auth(user, password),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.AUTH, (cmd)->cmd.auth(user, password),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.AUTH, (cmd)->cmd.auth(user, password),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create("password", password);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.AUTH, (cmd)->cmd.auth(password),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.AUTH, (cmd)->cmd.auth(password),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.AUTH, (cmd)->cmd.auth(password),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String echo(final String str) {
		final CommandArguments args = CommandArguments.create("str", str);
		final byte[] msg = SafeEncoder.encode(str);

		return echo(msg, SafeEncoder::encode, args);
	}

	@Override
	public byte[] echo(final byte[] str) {
		final CommandArguments args = CommandArguments.create("str", str);
		return echo(str, (v)->v, args);
	}

	@Override
	public Status ping() {
		final PingResultConverter pingResultConverter = new PingResultConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ECHO, (cmd)->cmd.ping(),
					pingResultConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ECHO, (cmd)->cmd.ping(),
					pingResultConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.ECHO, (cmd)->cmd.ping(), pingResultConverter)
					.run();
		}
	}

	@Override
	public Status reset() {
		return notCommand(client, Command.RESET);
	}

	@Override
	public Status quit() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.QUIT, (cmd)->cmd.quit(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.QUIT, (cmd)->cmd.quit(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.QUIT, (cmd)->cmd.quit(), okStatusConverter)
					.run();
		}
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
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_ID, (cmd)->cmd.clientId(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_ID, (cmd)->cmd.clientId(),
					(v)->v)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_ID, (cmd)->cmd.clientId(), (v)->v)
					.run();
		}
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create("name", name);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_SETNAME,
					(cmd)->cmd.clientSetname(name), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_SETNAME,
					(cmd)->cmd.clientSetname(name), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_SETNAME, (cmd)->cmd.clientSetname(name),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String clientGetName() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_GETNAME,
					(cmd)->cmd.clientGetname(), SafeEncoder::encode)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_GETNAME,
					(cmd)->cmd.clientGetname(), SafeEncoder::encode)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_GETNAME, (cmd)->cmd.clientGetname(),
					SafeEncoder::encode)
					.run();
		}
	}

	@Override
	public Integer clientGetRedir() {
		return notCommand(client, Command.CLIENT_GETREDIR);
	}

	@Override
	public List<Client> clientList() {
		final ClientConverter.ClientListConverter clientListConverter = new ClientConverter.ClientListConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run();
		}
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create("clientType", clientType);
		final ClientListArgs clientListArgs = (new ClientTypeConverter()).convert(clientType);

		return clientList(clientListArgs, args);
	}

	@Override
	public List<Client> clientList(final long... clientIds) {
		final CommandArguments args = CommandArguments.create("clientIds", clientIds);
		final ClientListArgs clientListArgs = ClientListArgs.Builder.ids(clientIds);

		return clientList(clientListArgs, args);
	}

	@Override
	public Client clientInfo() {
		final ClientConverter clientConverter = new ClientConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_INFO,
					(cmd)->cmd.clientInfo(), clientConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_INFO,
					(cmd)->cmd.clientInfo(), clientConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_INFO, (cmd)->cmd.clientInfo(),
					clientConverter)
					.run();
		}
	}

	@Override
	public Status clientSetInfo(final ClientAttributeOption clientAttributeOption, final String value) {
		final CommandArguments args = CommandArguments.create("clientAttributeOption", clientAttributeOption).put(
				"value", value);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_PAUSE,
					(cmd)->cmd.clientSetinfo(clientAttributeOption.name(), value), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_PAUSE,
					(cmd)->cmd.clientSetinfo(clientAttributeOption.name(), value), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_PAUSE,
					(cmd)->cmd.clientSetinfo(clientAttributeOption.name(), value), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create("timeout", timeout);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_PAUSE, (cmd)->cmd.clientPause(timeout),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientPause(final int timeout, final ClientPauseMode pauseMode) {
		final CommandArguments args = CommandArguments.create("timeout", timeout).put("pauseMode", pauseMode);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_PAUSE, (cmd)->cmd.clientPause(timeout),
					okStatusConverter)
					.run(args);
		}
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
		final String addr = host + ':' + port;

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_KILL, (cmd)->cmd.clientKill(addr),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_KILL,
					(cmd)->cmd.clientKill(addr), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_KILL, (cmd)->cmd.clientKill(addr),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long clientKill(final ClientKillArgument... clientKillArguments) {
		final CommandArguments args = CommandArguments.create("clientKillArguments", clientKillArguments);
		final KillArgs killArgs = KillArgsUtils.fromClientKillArgumentArray(clientKillArguments);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_KILL,
					(cmd)->cmd.clientKill(killArgs), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_KILL,
					(cmd)->cmd.clientKill(killArgs), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_KILL, (cmd)->cmd.clientKill(killArgs),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Status clientUnblock(final int clientId) {
		final CommandArguments args = CommandArguments.create("clientId", clientId);
		return clientUnblock(clientId, UnblockType.ERROR, args);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type) {
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);
		final UnblockType unblockType = (new ClientUnblockTypeConverter()).convert(type);

		return clientUnblock(clientId, unblockType, args);
	}

	@Override
	public Status clientNoEvict(final boolean on) {
		final CommandArguments args = CommandArguments.create("on", on);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_NO_EVICT,
					(cmd)->cmd.clientNoEvict(on), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_NO_EVICT,
					(cmd)->cmd.clientNoEvict(on), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_NO_EVICT,
					(cmd)->cmd.clientNoEvict(on), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientNoTouch(final boolean on) {
		final CommandArguments args = CommandArguments.create("on", on);
		return notCommand(client, Command.CLIENT_NO_TOUCH, args);
	}

	private <V> V echo(final byte[] str, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.ECHO, (cmd)->cmd.echo(str), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.ECHO, (cmd)->cmd.echo(str), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.ECHO, (cmd)->cmd.echo(str), converter)
					.run(args);
		}
	}

	private List<Client> clientList(final ClientListArgs clientListArgs, final CommandArguments args) {
		final ClientConverter.ClientListConverter clientListConverter = new ClientConverter.ClientListConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_LIST,
					(cmd)->cmd.clientList(clientListArgs), clientListConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_LIST,
					(cmd)->cmd.clientList(clientListArgs), clientListConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_LIST,
					(cmd)->cmd.clientList(clientListArgs), clientListConverter)
					.run(args);
		}
	}

	private Status clientUnblock(final int clientId, final UnblockType unblockType, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.CLIENT_KILL,
					(cmd)->cmd.clientUnblock(clientId, unblockType), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.CLIENT_KILL,
					(cmd)->cmd.clientUnblock(clientId, unblockType), oneStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.CLIENT_KILL,
					(cmd)->cmd.clientUnblock(clientId, unblockType), oneStatusConverter)
					.run(args);
		}
	}

}
