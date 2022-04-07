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
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * Jedis 集群模式连接命令操作
 *
 * @author Yong.Teng
 */
public final class JedisClusterConnectionOperations extends AbstractConnectionOperations<JedisCluster> {

	public JedisClusterConnectionOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public Status auth(final String user, final String password){
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);
		return execute(CommandNotSupported.ALL, ProtocolCommand.AUTH, args);
	}

	@Override
	public Status auth(final byte[] user, final byte[] password){
		return auth(SafeEncoder.encode(user), SafeEncoder.encode(password));
	}

	@Override
	public Status auth(final String password){
		final CommandArguments args = CommandArguments.create("password", password);
		return execute(CommandNotSupported.ALL, ProtocolCommand.AUTH, args);
	}

	@Override
	public Status auth(final byte[] password){
		return auth(SafeEncoder.encode(password));
	}

	@Override
	public String echo(final String str){
		final CommandArguments args = CommandArguments.create("str", str);
		return execute(CommandNotSupported.ALL, ProtocolCommand.ECHO, args);
	}

	@Override
	public byte[] echo(final byte[] str){
		final CommandArguments args = CommandArguments.create("str", str);
		return execute(CommandNotSupported.ALL, ProtocolCommand.ECHO, args);
	}

	@Override
	public Status ping(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.PING);
	}

	@Override
	public Status quit(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.QUIT);
	}

	@Override
	public Status select(final int db){
		final CommandArguments args = CommandArguments.create("db", db);
		return execute(CommandNotSupported.ALL, ProtocolCommand.SELECT, args);
	}

	@Override
	public Status clientCaching(final boolean isYes){
		final CommandArguments args = CommandArguments.create("isYes", isYes);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_CACHING, args);
	}

	@Override
	public Long clientId(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_ID);
	}

	@Override
	public Status clientSetName(final String name){
		final CommandArguments args = CommandArguments.create("name", name);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_SETNAME, args);
	}

	@Override
	public Status clientSetName(final byte[] name){
		final CommandArguments args = CommandArguments.create("name", name);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_SETNAME, args);
	}

	@Override
	public String clientGetName(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_GETNAME);
	}

	@Override
	public Integer clientGetRedir(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_GETREDIR);
	}

	@Override
	public List<Client> clientList(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_LIST);
	}

	@Override
	public List<Client> clientList(final ClientType clientType){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_LIST);
	}

	@Override
	public Client clientInfo(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_INFO);
	}

	@Override
	public Status clientPause(final int timeout){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_PAUSE);
	}

	@Override
	public Status clientReply(final ClientReply option){
		final CommandArguments args = CommandArguments.create("option", option);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_REPLY, args);
	}

	@Override
	public Status clientKill(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_KILL, args);
	}

	@Override
	public Status clientUnblock(final int clientId){
		final CommandArguments args = CommandArguments.create("clientId", clientId);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_UNBLOCK, args);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_UNBLOCK, args);
	}

}
