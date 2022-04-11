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
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
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
public final class JedisClusterConnectionOperations extends AbstractConnectionOperations<JedisClusterConnection> {

	public JedisClusterConnectionOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public Status auth(final String user, final String password){
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.AUTH);
		return execute(command, args);
	}

	@Override
	public Status auth(final String password){
		final CommandArguments args = CommandArguments.create("password", password);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.AUTH);
		return execute(command, args);
	}

	@Override
	public String echo(final String str){
		final CommandArguments args = CommandArguments.create("str", str);
		final JedisClusterCommand<String> command = JedisClusterCommand.create(ProtocolCommand.ECHO);
		return execute(command, args);
	}

	@Override
	public byte[] echo(final byte[] str){
		final CommandArguments args = CommandArguments.create("str", str);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.create(ProtocolCommand.ECHO);
		return execute(command, args);
	}

	@Override
	public Status ping(){
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.PING);
		return execute(command);
	}

	@Override
	public Status reset(){
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.RESET);
		return execute(command);
	}

	@Override
	public Status quit(){
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.QUIT);
		return execute(command);
	}


	@Override
	public Status select(final int db){
		final CommandArguments args = CommandArguments.create("db", db);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.SELECT);
		return execute(command, args);
	}

	@Override
	public Status clientCaching(final boolean isYes){
		final CommandArguments args = CommandArguments.create("isYes", isYes);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_CACHING);
		return execute(command, args);
	}

	@Override
	public long clientId(){
		final JedisClusterCommand<Long> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_ID);
		return execute(command);
	}

	@Override
	public Status clientSetName(final String name){
		final CommandArguments args = CommandArguments.create("name", name);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_SETNAME);
		return execute(command, args);
	}

	@Override
	public Status clientSetName(final byte[] name){
		final CommandArguments args = CommandArguments.create("name", name);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_SETNAME);
		return execute(command, args);
	}

	@Override
	public String clientGetName(){
		final JedisClusterCommand<String> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_GETNAME);
		return execute(command);
	}

	@Override
	public Integer clientGetRedir(){
		final JedisClusterCommand<Integer> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_GETREDIR);
		return execute(command);
	}

	@Override
	public List<Client> clientList(){
		final JedisClusterCommand<List<Client>> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_LIST);
		return execute(command);
	}

	@Override
	public List<Client> clientList(final ClientType clientType){
		final CommandArguments args = CommandArguments.create("clientType", clientType);
		final JedisClusterCommand<List<Client>> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_LIST);
		return execute(command, args);
	}

	@Override
	public Client clientInfo(){
		final JedisClusterCommand<Client> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_INFO);
		return execute(command);
	}

	@Override
	public Status clientPause(final int timeout){
		final CommandArguments args = CommandArguments.create("timeout", timeout);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.CLIENT_PAUSE);
		return execute(command, args);
	}

	@Override
	public Status clientReply(final ClientReply option){
		final CommandArguments args = CommandArguments.create("option", option);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_REPLY);
		return execute(command, args);
	}

	@Override
	public Status clientKill(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.CLIENT_PAUSE);
		return execute(command, args);
	}

	@Override
	public Status clientUnblock(final int clientId){
		final CommandArguments args = CommandArguments.create("clientId", clientId);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_UNBLOCK);
		return execute(command, args);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);
		final JedisClusterCommand<Status> command = JedisClusterCommand.create(ProtocolCommand.CLIENT_UNBLOCK);
		return execute(command, args);
	}

}
