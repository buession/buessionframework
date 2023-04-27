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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.response.ClientConverter;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.jedis.params.ClientTypeConverter;
import com.buession.redis.core.internal.convert.jedis.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;

import java.util.List;

/**
 * Jedis 单机模式连接命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisConnectionOperations extends AbstractConnectionOperations<JedisStandaloneClient> {

	public JedisConnectionOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public Status auth(final String user, final String password){
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);
		return new JedisCommand<Status>(client, ProtocolCommand.AUTH)
				.general((cmd)->cmd.auth(user, password), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status auth(final String password){
		final CommandArguments args = CommandArguments.create("password", password);
		return new JedisCommand<Status>(client, ProtocolCommand.AUTH)
				.general((cmd)->cmd.auth(password), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public String echo(final String str){
		final CommandArguments args = CommandArguments.create("str", str);
		return new JedisCommand<String>(client, ProtocolCommand.ECHO)
				.general((cmd)->cmd.echo(str))
				.run(args);
	}

	@Override
	public byte[] echo(final byte[] str){
		final CommandArguments args = CommandArguments.create("str", str);
		return new JedisCommand<byte[]>(client, ProtocolCommand.ECHO)
				.general((cmd)->cmd.echo(str))
				.run(args);
	}

	@Override
	public Status ping(){
		return new JedisCommand<Status>(client, ProtocolCommand.PING)
				.general((cmd)->cmd.ping(), PingResultConverter.INSTANCE)
				.run();
	}

	@Override
	public Status reset(){
		return new JedisCommand<Status>(client, ProtocolCommand.RESET)
				.run();
	}

	@Override
	public Status quit(){
		return new JedisCommand<Status>(client, ProtocolCommand.QUIT)
				.general((cmd)->cmd.quit(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public Status select(final int db){
		final CommandArguments args = CommandArguments.create("db", db);
		return new JedisCommand<Status>(client, ProtocolCommand.SELECT)
				.general((cmd)->cmd.select(db), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.select(db), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clientCaching(final boolean isYes){
		final CommandArguments args = CommandArguments.create("isYes", isYes);
		return new JedisCommand<Status>(client, ProtocolCommand.CLIENT_CACHING)
				.run(args);
	}

	@Override
	public Long clientId(){
		return new JedisCommand<Long>(client, ProtocolCommand.CLIENT_ID)
				.general((cmd)->cmd.clientId())
				.run();
	}

	@Override
	public Status clientSetName(final String name){
		final CommandArguments args = CommandArguments.create("name", name);
		return new JedisCommand<Status>(client, ProtocolCommand.CLIENT_SETNAME)
				.general((cmd)->cmd.clientSetname(name), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clientSetName(final byte[] name){
		final CommandArguments args = CommandArguments.create("name", name);
		return new JedisCommand<Status>(client, ProtocolCommand.CLIENT_SETNAME)
				.general((cmd)->cmd.clientSetname(name), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public String clientGetName(){
		return new JedisCommand<String>(client, ProtocolCommand.CLIENT_GETNAME)
				.general((cmd)->cmd.clientGetname())
				.run();
	}

	@Override
	public Integer clientGetRedir(){
		return new JedisCommand<Integer>(client, ProtocolCommand.CLIENT_GETREDIR)
				.run();
	}

	@Override
	public List<Client> clientList(){
		return new JedisCommand<List<Client>>(client, ProtocolCommand.CLIENT_LIST)
				.general((cmd)->cmd.clientList(), ClientConverter.ClientListConverter.INSTANCE)
				.run();
	}

	@Override
	public List<Client> clientList(final ClientType clientType){
		final CommandArguments args = CommandArguments.create("clientType", clientType);
		return new JedisCommand<List<Client>>(client, ProtocolCommand.CLIENT_LIST)
				.general((cmd)->cmd.clientList(ClientTypeConverter.INSTANCE.convert(clientType)),
						ClientConverter.ClientListConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Client clientInfo(){
		return new JedisCommand<Client>(client, ProtocolCommand.CLIENT_INFO)
				.general((cmd)->cmd.clientInfo(), ClientConverter.INSTANCE)
				.run();
	}

	@Override
	public Status clientPause(final int timeout){
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		return new JedisCommand<Status>(client, ProtocolCommand.CLIENT_PAUSE)
				.general((cmd)->cmd.clientPause(timeout), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clientReply(final ClientReply option){
		final CommandArguments args = CommandArguments.create("option", option);
		return new JedisCommand<Status>(client, ProtocolCommand.CLIENT_REPLY)
				.run(args);
	}

	@Override
	public Status clientKill(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new JedisCommand<Status>(client, ProtocolCommand.CLIENT_PAUSE)
				.general((cmd)->cmd.clientKill(host + ":" + port), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status clientUnblock(final int clientId){
		final CommandArguments args = CommandArguments.create("clientId", clientId);
		return new JedisCommand<Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
				.general((cmd)->cmd.clientUnblock(clientId, null), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);
		return new JedisCommand<Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
				.general((cmd)->cmd.clientUnblock(clientId, ClientUnblockTypeConverter.INSTANCE.convert(type)),
						Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

}
