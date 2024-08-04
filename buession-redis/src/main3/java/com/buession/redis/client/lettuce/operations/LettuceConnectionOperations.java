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
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
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
import com.buession.redis.core.internal.convert.lettuce.params.ClientTypeConverter;
import com.buession.redis.core.internal.convert.lettuce.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.response.ClientConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;
import com.buession.redis.core.internal.lettuce.LettuceTrackingArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.ClientListArgs;
import io.lettuce.core.KillArgs;
import io.lettuce.core.TrackingArgs;
import io.lettuce.core.UnblockType;

import java.util.List;

/**
 * Lettuce 单机模式连接命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceConnectionOperations extends AbstractConnectionOperations<LettuceStandaloneClient> {

	public LettuceConnectionOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Status auth(final String user, final String password) {
		final CommandArguments args = CommandArguments.create(user).add(password);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.AUTH, (cmd)->cmd.auth(user, password),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.AUTH, (cmd)->cmd.auth(user, password),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.AUTH, (cmd)->cmd.auth(user, password),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create(password);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.AUTH, (cmd)->cmd.auth(password), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.AUTH, (cmd)->cmd.auth(password), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.AUTH, (cmd)->cmd.auth(password), okStatusConverter)
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
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_GETNAME,
					(cmd)->cmd.clientGetname(), SafeEncoder::encode)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_GETNAME,
					(cmd)->cmd.clientGetname(), SafeEncoder::encode)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_GETNAME, (cmd)->cmd.clientGetname(),
					SafeEncoder::encode)
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
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_ID, (cmd)->cmd.clientId(),
					(v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_ID, (cmd)->cmd.clientId(),
					(v)->v)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_ID, (cmd)->cmd.clientId(), (v)->v)
					.run();
		}
	}

	@Override
	public Client clientInfo() {
		final ClientConverter clientConverter = new ClientConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_INFO, (cmd)->cmd.clientInfo(),
					clientConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_INFO,
					(cmd)->cmd.clientInfo(), clientConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_INFO, (cmd)->cmd.clientInfo(),
					clientConverter)
					.run();
		}
	}

	@Override
	public Status clientKill(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);
		final String addr = host + ':' + port;

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_KILL,
					(cmd)->cmd.clientKill(addr), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_KILL,
					(cmd)->cmd.clientKill(addr), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_KILL, (cmd)->cmd.clientKill(addr),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long clientKill(final ClientKillArgument... clientKillArguments) {
		final CommandArguments args = CommandArguments.create(clientKillArguments);
		final KillArgs killArgs = createKillArgsFromClientKillArgument(clientKillArguments);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_KILL,
					(cmd)->cmd.clientKill(killArgs), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_KILL,
					(cmd)->cmd.clientKill(killArgs), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_KILL, (cmd)->cmd.clientKill(killArgs),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Client> clientList() {
		final ClientConverter.ClientListConverter clientListConverter = new ClientConverter.ClientListConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_LIST,
					(cmd)->cmd.clientList(), clientListConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run();
		}
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create(clientType);
		final ClientListArgs clientListArgs = (new ClientTypeConverter()).convert(clientType);

		return clientList(clientListArgs, args);
	}

	@Override
	public List<Client> clientList(final long... clientIds) {
		final CommandArguments args = CommandArguments.create(clientIds);
		final ClientListArgs clientListArgs = ClientListArgs.Builder.ids(clientIds);

		return clientList(clientListArgs, args);
	}

	@Override
	public Status clientNoEvict(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_NO_EVICT,
					(cmd)->cmd.clientNoEvict(on), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_NO_EVICT,
					(cmd)->cmd.clientNoEvict(on), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_NO_EVICT,
					(cmd)->cmd.clientNoEvict(on), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientNoTouch(final boolean on) {
		final CommandArguments args = CommandArguments.create(on ? Keyword.Common.ON : Keyword.Common.OFF);
		return notCommand(client, Command.CLIENT, SubCommand.CLIENT_NO_TOUCH, args);
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create(timeout);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientPause(final int timeout, final ClientPauseMode pauseMode) {
		final CommandArguments args = CommandArguments.create(timeout).add(pauseMode);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
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

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_SETINFO,
					(cmd)->cmd.clientSetinfo(clientAttributeOption.name(), value), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_SETINFO,
					(cmd)->cmd.clientSetinfo(clientAttributeOption.name(), value), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_SETINFO,
					(cmd)->cmd.clientSetinfo(clientAttributeOption.name(), value), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create(name);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_SETNAME,
					(cmd)->cmd.clientSetname(name), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_SETNAME,
					(cmd)->cmd.clientSetname(name), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_SETNAME,
					(cmd)->cmd.clientSetname(name), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientTracking(final ClientTracking clientTracking) {
		final CommandArguments args = CommandArguments.create(clientTracking);
		final TrackingArgs trackingArgs = LettuceTrackingArgs.from(clientTracking);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_TRACKING,
					(cmd)->cmd.clientTracking(trackingArgs), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_TRACKING,
					(cmd)->cmd.clientTracking(trackingArgs), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_TRACKING,
					(cmd)->cmd.clientTracking(trackingArgs), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public ClientTrackingInfo clientTrackingInfo() {
		return notCommand(client, Command.CLIENT, SubCommand.CLIENT_TRACKINGINFO);
	}

	@Override
	public Status clientUnblock(final int clientId) {
		final CommandArguments args = CommandArguments.create(clientId);
		return clientUnblock(clientId, UnblockType.TIMEOUT, args);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type) {
		final CommandArguments args = CommandArguments.create(clientId).add(type);
		final UnblockType unblockType = (new ClientUnblockTypeConverter()).convert(type);

		return clientUnblock(clientId, unblockType, args);
	}

	@Override
	public Status clientUnPause() {
		return notCommand(client, Command.CLIENT, SubCommand.CLIENT_UNPAUSE);
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
	public RedisServer hello(final HelloArgument helloArgument) {
		final CommandArguments args = CommandArguments.create(helloArgument);
		return notCommand(client, Command.HELLO, args);
	}

	@Override
	public Status ping() {
		final PingResultConverter pingResultConverter = new PingResultConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ECHO, (cmd)->cmd.ping(), pingResultConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ECHO, (cmd)->cmd.ping(), pingResultConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.ECHO, (cmd)->cmd.ping(), pingResultConverter)
					.run();
		}
	}

	@Override
	public Status quit() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.QUIT, (cmd)->cmd.quit(), okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.QUIT, (cmd)->cmd.quit(), okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.QUIT, (cmd)->cmd.quit(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status reset() {
		if(isPipeline()){
			new LettucePipelineCommand<>(client, Command.RESET, (cmd)->{
				cmd.reset();
				return Status.SUCCESS;
			}, (v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.RESET, (cmd)->{
				cmd.reset();
				return Status.SUCCESS;
			}, (v)->v)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.RESET, (cmd)->{
				cmd.reset();
				return Status.SUCCESS;
			}, (v)->v)
					.run();
		}
	}

	@Override
	public Status select(final int db) {
		final CommandArguments args = CommandArguments.create(db);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SELECT, (cmd)->cmd.select(db),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SELECT, (cmd)->cmd.select(db),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.SELECT, (cmd)->cmd.select(db), okStatusConverter)
					.run(args);
		}
	}

	private List<Client> clientList(final ClientListArgs clientListArgs, final CommandArguments args) {
		final ClientConverter.ClientListConverter clientListConverter = new ClientConverter.ClientListConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_LIST,
					(cmd)->cmd.clientList(clientListArgs), clientListConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_LIST,
					(cmd)->cmd.clientList(clientListArgs), clientListConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_LIST,
					(cmd)->cmd.clientList(clientListArgs), clientListConverter)
					.run(args);
		}
	}

	private Status clientUnblock(final int clientId, final UnblockType unblockType, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.CLIENT, SubCommand.CLIENT_UNBLOCK,
					(cmd)->cmd.clientUnblock(clientId, unblockType), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.CLIENT, SubCommand.CLIENT_UNBLOCK,
					(cmd)->cmd.clientUnblock(clientId, unblockType), oneStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.CLIENT, SubCommand.CLIENT_UNBLOCK,
					(cmd)->cmd.clientUnblock(clientId, unblockType), oneStatusConverter)
					.run(args);
		}
	}

	private <V> V echo(final byte[] str, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ECHO, (cmd)->cmd.echo(str), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ECHO, (cmd)->cmd.echo(str), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ECHO, (cmd)->cmd.echo(str), converter)
					.run(args);
		}
	}

}
