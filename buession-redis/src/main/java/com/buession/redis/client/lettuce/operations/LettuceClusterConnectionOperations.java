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
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.lettuce.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.response.ClientConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.UnblockType;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.output.StatusOutput;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.CommandKeyword;
import io.lettuce.core.protocol.CommandType;

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
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.AUTH,
					(cmd)->client.getConnection().getStatefulRedisClusterConnection().async().dispatch(CommandType.AUTH,
							new StatusOutput<>(ByteArrayCodec.INSTANCE),
							new CommandArgs<>(ByteArrayCodec.INSTANCE).add(password)), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.AUTH,
					(cmd)->client.getConnection().getStatefulRedisClusterConnection().async().dispatch(CommandType.AUTH,
							new StatusOutput<>(ByteArrayCodec.INSTANCE),
							new CommandArgs<>(ByteArrayCodec.INSTANCE).add(password)), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.AUTH, (cmd)->cmd.auth(password),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create("password", password);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.AUTH,
					(cmd)->client.getConnection().getStatefulRedisClusterConnection().async().dispatch(CommandType.AUTH,
							new StatusOutput<>(ByteArrayCodec.INSTANCE),
							new CommandArgs<>(ByteArrayCodec.INSTANCE).add(password)), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.AUTH,
					(cmd)->client.getConnection().getStatefulRedisClusterConnection().async().dispatch(CommandType.AUTH,
							new StatusOutput<>(ByteArrayCodec.INSTANCE),
							new CommandArgs<>(ByteArrayCodec.INSTANCE).add(password)), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.AUTH, (cmd)->cmd.auth(password),
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
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.ECHO, (cmd)->cmd.ping(),
					pingResultConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.ECHO, (cmd)->cmd.ping(),
					pingResultConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.ECHO, (cmd)->cmd.ping(), pingResultConverter)
					.run();
		}
	}

	@Override
	public Status reset() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.RESET,
					(cmd)->client.getConnection().getStatefulRedisClusterConnection().async()
							.dispatch(CommandKeyword.RESET,
									new StatusOutput<>(ByteArrayCodec.INSTANCE),
									new CommandArgs<>(ByteArrayCodec.INSTANCE)), okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.RESET,
					(cmd)->client.getConnection().getStatefulRedisClusterConnection().async()
							.dispatch(CommandKeyword.RESET,
									new StatusOutput<>(ByteArrayCodec.INSTANCE),
									new CommandArgs<>(ByteArrayCodec.INSTANCE)), okStatusConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.RESET, (cmd)->{
				cmd.reset();
				return Status.SUCCESS;
			}, (v)->v)
					.run();
		}
	}

	@Override
	public Status quit() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.QUIT, (cmd)->cmd.quit(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.QUIT, (cmd)->cmd.quit(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.QUIT, (cmd)->cmd.quit(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status select(final int db) {
		final CommandArguments args = CommandArguments.create("db", db);
		return notCommand(client, ProtocolCommand.SELECT, args);
	}

	@Override
	public Status clientCaching(final boolean isYes) {
		final CommandArguments args = CommandArguments.create("isYes", isYes);
		return notCommand(client, ProtocolCommand.CLIENT_CACHING, args);
	}

	@Override
	public Long clientId() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.CLIENT_ID, (cmd)->cmd.clientId(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.CLIENT_ID, (cmd)->cmd.clientId(),
					(v)->v)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.CLIENT_ID, (cmd)->cmd.clientId(), (v)->v)
					.run();
		}
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create("name", name);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.CLIENT_SETNAME,
					(cmd)->cmd.clientSetname(name), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.CLIENT_SETNAME,
					(cmd)->cmd.clientSetname(name), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.CLIENT_SETNAME, (cmd)->cmd.clientSetname(name),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String clientGetName() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.CLIENT_GETNAME,
					(cmd)->cmd.clientGetname(), SafeEncoder::encode)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.CLIENT_GETNAME,
					(cmd)->cmd.clientGetname(), SafeEncoder::encode)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.CLIENT_GETNAME, (cmd)->cmd.clientGetname(),
					SafeEncoder::encode)
					.run();
		}
	}

	@Override
	public Integer clientGetRedir() {
		return notCommand(client, ProtocolCommand.CLIENT_GETREDIR);
	}

	@Override
	public List<Client> clientList() {
		final ClientConverter.ClientListConverter clientListConverter = new ClientConverter.ClientListConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run();
		}
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create("clientType", clientType);
		final ClientConverter.ClientListConverter clientListConverter = new ClientConverter.ClientListConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.CLIENT_LIST, (cmd)->cmd.clientList(),
					clientListConverter)
					.run(args);
		}
	}

	@Override
	public Client clientInfo() {
		return notCommand(client, ProtocolCommand.CLIENT_INFO);
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create("timeout", timeout);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.CLIENT_PAUSE,
					(cmd)->cmd.clientPause(timeout), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.CLIENT_PAUSE, (cmd)->cmd.clientPause(timeout),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientReply(final ClientReply option) {
		final CommandArguments args = CommandArguments.create("option", option);
		return notCommand(client, ProtocolCommand.CLIENT_REPLY, args);
	}

	@Override
	public Status clientKill(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final String addr = host + ':' + port;

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.CLIENT_KILL, (cmd)->cmd.clientKill(addr),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.CLIENT_KILL,
					(cmd)->cmd.clientKill(addr), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.CLIENT_KILL, (cmd)->cmd.clientKill(addr),
					okStatusConverter)
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

	private <V> V echo(final byte[] str, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.ECHO, (cmd)->cmd.echo(str), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.ECHO, (cmd)->cmd.echo(str), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.ECHO, (cmd)->cmd.echo(str), converter)
					.run(args);
		}
	}

	private Status clientUnblock(final int clientId, final UnblockType unblockType, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.CLIENT_KILL,
					(cmd)->cmd.clientUnblock(clientId, unblockType), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.CLIENT_KILL,
					(cmd)->cmd.clientUnblock(clientId, unblockType), oneStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.CLIENT_KILL,
					(cmd)->cmd.clientUnblock(clientId, unblockType), oneStatusConverter)
					.run(args);
		}
	}

}
