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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.ClientConverter;
import com.buession.redis.core.internal.convert.jedis.JedisConverters;
import com.buession.redis.core.internal.convert.jedis.params.ClientTypeConverter;
import com.buession.redis.core.internal.convert.jedis.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.jedis.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;

import java.util.List;

/**
 * Jedis 单机模式连接命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisConnectionOperations extends AbstractConnectionOperations<JedisConnection> {

	public JedisConnectionOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public Status auth(final String user, final String password){
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.AUTH)
				.general((cmd)->cmd.auth(user, password), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status auth(final String password){
		final CommandArguments args = CommandArguments.create("password", password);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.AUTH)
				.general((cmd)->cmd.auth(password), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public String echo(final String str){
		final CommandArguments args = CommandArguments.create("str", str);
		final JedisCommand<String> command = JedisCommand.<String>create(ProtocolCommand.ECHO)
				.general((cmd)->cmd.echo(str));
		return execute(command, args);
	}

	@Override
	public byte[] echo(final byte[] str){
		final CommandArguments args = CommandArguments.create("str", str);
		final JedisCommand<byte[]> command = JedisCommand.<byte[]>create(ProtocolCommand.ECHO)
				.general((cmd)->cmd.echo(str));
		return execute(command, args);
	}

	@Override
	public Status ping(){
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.PING)
				.general((cmd)->cmd.ping(), PingResultConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status reset(){
		final JedisCommand<Status> command = JedisCommand.create(ProtocolCommand.RESET);
		return execute(command);
	}

	@Override
	public Status quit(){
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.QUIT)
				.general((cmd)->cmd.quit(), OkStatusConverter.INSTANCE);
		return execute(command);
	}


	@Override
	public Status select(final int db){
		final CommandArguments args = CommandArguments.create("db", db);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.SELECT)
				.pipeline((cmd)->cmd.select(db), OkStatusConverter.INSTANCE)
				.general((cmd)->cmd.select(db), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clientCaching(final boolean isYes){
		final CommandArguments args = CommandArguments.create("isYes", isYes);
		final JedisCommand<Status> command = JedisCommand.create(ProtocolCommand.CLIENT_CACHING);
		return execute(command, args);
	}

	@Override
	public long clientId(){
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.CLIENT_ID)
				.general((cmd)->cmd.clientId());
		return execute(command);
	}

	@Override
	public Status clientSetName(final String name){
		final CommandArguments args = CommandArguments.create("name", name);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.CLIENT_SETNAME)
				.general((cmd)->cmd.clientSetname(name), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clientSetName(final byte[] name){
		final CommandArguments args = CommandArguments.create("name", name);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.CLIENT_SETNAME)
				.general((cmd)->cmd.clientSetname(name), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public String clientGetName(){
		final JedisCommand<String> command = JedisCommand.<String>create(ProtocolCommand.CLIENT_GETNAME)
				.general((cmd)->cmd.clientGetname());
		return execute(command);
	}

	@Override
	public Integer clientGetRedir(){
		final JedisCommand<Integer> command = JedisCommand.create(ProtocolCommand.CLIENT_GETREDIR);
		return execute(command);
	}

	@Override
	public List<Client> clientList(){
		final JedisCommand<List<Client>> command = JedisCommand.<List<Client>>create(ProtocolCommand.CLIENT_LIST)
				.general((cmd)->cmd.clientList(), JedisConverters.CLIENT_LIST_RESULT_CONVERTER);
		return execute(command);
	}

	@Override
	public List<Client> clientList(final ClientType clientType){
		final CommandArguments args = CommandArguments.create("clientType", clientType);
		final JedisCommand<List<Client>> command = JedisCommand.<List<Client>>create(ProtocolCommand.CLIENT_LIST)
				.general((cmd)->cmd.clientList(ClientTypeConverter.INSTANCE.convert(clientType)),
						JedisConverters.CLIENT_LIST_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Client clientInfo(){
		final JedisCommand<Client> command = JedisCommand.<Client>create(ProtocolCommand.CLIENT_INFO)
				.general((cmd)->cmd.clientInfo(), ClientConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status clientPause(final int timeout){
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.CLIENT_PAUSE)
				.general((cmd)->cmd.clientPause(timeout), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clientReply(final ClientReply option){
		final CommandArguments args = CommandArguments.create("option", option);
		final JedisCommand<Status> command = JedisCommand.create(ProtocolCommand.CLIENT_REPLY);
		return execute(command, args);
	}

	@Override
	public Status clientKill(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.CLIENT_PAUSE)
				.general((cmd)->cmd.clientKill(host + ":" + port), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status clientUnblock(final int clientId){
		final CommandArguments args = CommandArguments.create("clientId", clientId);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.CLIENT_UNBLOCK)
				.general((cmd)->cmd.clientUnblock(clientId, null), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.CLIENT_UNBLOCK)
				.general((cmd)->cmd.clientUnblock(clientId, ClientUnblockTypeConverter.INSTANCE.convert(type)),
						JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

}
