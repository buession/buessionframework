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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
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

import java.util.List;

/**
 * Lettuce 单机模式连接命令操作
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public final class LettuceConnectionOperations extends AbstractConnectionOperations<LettuceStandaloneClient> {

	public LettuceConnectionOperations(final LettuceStandaloneClient client){
		super(client);
	}

	@Override
	public Status auth(final String user, final String password){
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);
		return new LettuceCommand<Status>(client, ProtocolCommand.AUTH)
				.general((cmd)->cmd.auth(password), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status auth(final String password){
		final CommandArguments args = CommandArguments.create("password", password);
		return new LettuceCommand<Status>(client, ProtocolCommand.AUTH)
				.general((cmd)->cmd.auth(password), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public String echo(final String str){
		return SafeEncoder.encode(echo(SafeEncoder.encode(str)));
	}

	@Override
	public byte[] echo(final byte[] str){
		final CommandArguments args = CommandArguments.create("str", str);
		return new LettuceCommand<byte[]>(client, ProtocolCommand.ECHO)
				.general((cmd)->cmd.echo(str))
				.pipeline((cmd)->cmd.echo(str))
				.transaction((cmd)->cmd.echo(str))
				.run(args);
	}

	@Override
	public Status ping(){
		return new LettuceCommand<Status>(client, ProtocolCommand.PING)
				.general((cmd)->cmd.ping(), PingResultConverter.INSTANCE)
				.pipeline((cmd)->cmd.ping(), PingResultConverter.INSTANCE)
				.transaction((cmd)->cmd.ping(), PingResultConverter.INSTANCE)
				.run();
	}

	@Override
	public Status reset(){
		new LettuceCommand<Void>(client, ProtocolCommand.RESET)
				.general((cmd)->{
					cmd.reset();
					return null;
				})
				.pipeline((cmd)->{
					cmd.reset();
					return null;
				})
				.transaction((cmd)->{
					cmd.reset();
					return null;
				})
				.run();
		return Status.SUCCESS;
	}

	@Override
	public Status quit(){
		return new LettuceCommand<Status>(client, ProtocolCommand.QUIT)
				.general((cmd)->cmd.quit(), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.quit(), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.quit(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status select(final int db){
		final CommandArguments args = CommandArguments.create("db", db);
		return new LettuceCommand<Status>(client, ProtocolCommand.SELECT)
				.general((cmd)->cmd.select(db), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.select(db), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.select(db), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clientCaching(final boolean isYes){
		final CommandArguments args = CommandArguments.create("isYes", isYes);
		return new LettuceCommand<Status>(client, ProtocolCommand.CLIENT_CACHING)
				.run(args);
	}

	@Override
	public Long clientId(){
		return new LettuceCommand<Long>(client, ProtocolCommand.CLIENT_ID)
				.general((cmd)->cmd.clientId())
				.pipeline((cmd)->cmd.clientId())
				.transaction((cmd)->cmd.clientId())
				.run();
	}

	@Override
	public Status clientSetName(final String name){
		return clientSetName(SafeEncoder.encode(name));
	}

	@Override
	public Status clientSetName(final byte[] name){
		final CommandArguments args = CommandArguments.create("name", name);
		return new LettuceCommand<Status>(client, ProtocolCommand.CLIENT_SETNAME)
				.general((cmd)->cmd.clientSetname(name), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.clientSetname(name), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.clientSetname(name), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public String clientGetName(){
		return new LettuceCommand<String>(client, ProtocolCommand.CLIENT_GETNAME)
				.general((cmd)->SafeEncoder.encode(cmd.clientGetname()))
				.pipeline((cmd)->SafeEncoder.encode(cmd.clientGetname()))
				.transaction((cmd)->SafeEncoder.encode(cmd.clientGetname()))
				.run();
	}

	@Override
	public Integer clientGetRedir(){
		return new LettuceCommand<Integer>(client, ProtocolCommand.CLIENT_GETREDIR)
				.run();
	}

	@Override
	public List<Client> clientList(){
		return new LettuceCommand<List<Client>>(client, ProtocolCommand.CLIENT_LIST)
				.general((cmd)->cmd.clientList(), ClientConverter.ClientListConverter.INSTANCE)
				.pipeline((cmd)->cmd.clientList(), ClientConverter.ClientListConverter.INSTANCE)
				.transaction((cmd)->cmd.clientList(), ClientConverter.ClientListConverter.INSTANCE)
				.run();
	}

	@Override
	public List<Client> clientList(final ClientType clientType){
		final CommandArguments args = CommandArguments.create("clientType", clientType);
		return new LettuceCommand<List<Client>>(client, ProtocolCommand.CLIENT_LIST)
				.general((cmd)->cmd.clientList(),
						ClientConverter.ClientListConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Client clientInfo(){
		return new LettuceCommand<Client>(client, ProtocolCommand.CLIENT_INFO)
				.run();
	}

	@Override
	public Status clientPause(final int timeout){
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		return new LettuceCommand<Status>(client, ProtocolCommand.CLIENT_PAUSE)
				.general((cmd)->cmd.clientPause(timeout), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.clientPause(timeout), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.clientPause(timeout), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clientReply(final ClientReply option){
		final CommandArguments args = CommandArguments.create("option", option);
		return new LettuceCommand<Status>(client, ProtocolCommand.CLIENT_REPLY)
				.run(args);
	}

	@Override
	public Status clientKill(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new LettuceCommand<Status>(client, ProtocolCommand.CLIENT_PAUSE)
				.general((cmd)->cmd.clientKill(host + ":" + port), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.clientKill(host + ":" + port), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.clientKill(host + ":" + port), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clientUnblock(final int clientId){
		final CommandArguments args = CommandArguments.create("clientId", clientId);
		return new LettuceCommand<Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
				.general((cmd)->cmd.clientUnblock(clientId, null), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.clientUnblock(clientId, null), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.clientUnblock(clientId, null), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);
		return new LettuceCommand<Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
				.general((cmd)->cmd.clientUnblock(clientId, ClientUnblockTypeConverter.INSTANCE.convert(type)),
						Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.clientUnblock(clientId, ClientUnblockTypeConverter.INSTANCE.convert(type)),
						Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.clientUnblock(clientId, ClientUnblockTypeConverter.INSTANCE.convert(type)),
						Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

}
