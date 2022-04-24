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
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

import java.util.List;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterConnectionOperations extends AbstractConnectionOperations<JedisClusterClient> {

	public JedisClusterConnectionOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public Status auth(final String user, final String password){
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.AUTH)
				.run(args);
	}

	@Override
	public Status auth(final String password){
		final CommandArguments args = CommandArguments.create("password", password);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.AUTH)
				.run(args);
	}

	@Override
	public String echo(final String str){
		final CommandArguments args = CommandArguments.create("str", str);
		return new JedisClusterCommand<String>(client, ProtocolCommand.ECHO)
				.run(args);
	}

	@Override
	public byte[] echo(final byte[] str){
		final CommandArguments args = CommandArguments.create("str", str);
		return new JedisClusterCommand<byte[]>(client, ProtocolCommand.ECHO)
				.run(args);
	}

	@Override
	public Status ping(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.PING)
				.run();
	}

	@Override
	public Status reset(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.RESET)
				.run();
	}

	@Override
	public Status quit(){
		return new JedisClusterCommand<Status>(client, ProtocolCommand.QUIT)
				.run();
	}


	@Override
	public Status select(final int db){
		final CommandArguments args = CommandArguments.create("db", db);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.SELECT)
				.run(args);
	}

	@Override
	public Status clientCaching(final boolean isYes){
		final CommandArguments args = CommandArguments.create("isYes", isYes);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLIENT_CACHING)
				.run(args);
	}

	@Override
	public Long clientId(){
		return new JedisClusterCommand<Long>(client, ProtocolCommand.CLIENT_ID)
				.run();
	}

	@Override
	public Status clientSetName(final String name){
		final CommandArguments args = CommandArguments.create("name", name);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLIENT_SETNAME)
				.run(args);
	}

	@Override
	public Status clientSetName(final byte[] name){
		final CommandArguments args = CommandArguments.create("name", name);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLIENT_SETNAME)
				.run(args);
	}

	@Override
	public String clientGetName(){
		return new JedisClusterCommand<String>(client, ProtocolCommand.CLIENT_GETNAME)
				.run();
	}

	@Override
	public Integer clientGetRedir(){
		return new JedisClusterCommand<Integer>(client, ProtocolCommand.CLIENT_GETREDIR)
				.run();
	}

	@Override
	public List<Client> clientList(){
		return new JedisClusterCommand<List<Client>>(client, ProtocolCommand.CLIENT_LIST)
				.run();
	}

	@Override
	public List<Client> clientList(final ClientType clientType){
		final CommandArguments args = CommandArguments.create("clientType", clientType);
		return new JedisClusterCommand<List<Client>>(client, ProtocolCommand.CLIENT_LIST)
				.run(args);
	}

	@Override
	public Client clientInfo(){
		return new JedisClusterCommand<Client>(client, ProtocolCommand.CLIENT_INFO)
				.run();
	}

	@Override
	public Status clientPause(final int timeout){
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLIENT_PAUSE)
				.run(args);
	}

	@Override
	public Status clientReply(final ClientReply option){
		final CommandArguments args = CommandArguments.create("option", option);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLIENT_REPLY)
				.run(args);
	}

	@Override
	public Status clientKill(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLIENT_PAUSE)
				.run(args);
	}

	@Override
	public Status clientUnblock(final int clientId){
		final CommandArguments args = CommandArguments.create("clientId", clientId);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
				.run(args);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
				.run(args);
	}

}
