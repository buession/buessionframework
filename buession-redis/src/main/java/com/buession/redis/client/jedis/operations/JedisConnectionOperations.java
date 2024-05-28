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
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.params.ClientTypeConverter;
import com.buession.redis.core.internal.convert.jedis.params.ClientUnblockTypeConverter;
import com.buession.redis.core.internal.convert.response.ClientConverter;
import com.buession.redis.core.internal.convert.response.PingResultConverter;
import redis.clients.jedis.args.UnblockType;

import java.util.List;

/**
 * Jedis 单机模式连接命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisConnectionOperations extends AbstractConnectionOperations<JedisStandaloneClient> {

	public JedisConnectionOperations(final JedisStandaloneClient client) {
		super(client);
	}

	@Override
	public Status auth(final String user, final String password) {
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.AUTH)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.AUTH)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.AUTH, (cmd)->cmd.auth(user, password),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create("password", password);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.AUTH)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.AUTH)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.AUTH, (cmd)->cmd.auth(password),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String echo(final String str) {
		final CommandArguments args = CommandArguments.create("str", str);

		if(isPipeline()){
			return new JedisPipelineCommand<String, String>(client, ProtocolCommand.ECHO)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<String, String>(client, ProtocolCommand.ECHO)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ECHO, (cmd)->cmd.echo(str), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] echo(final byte[] str) {
		final CommandArguments args = CommandArguments.create("str", str);

		if(isPipeline()){
			return new JedisPipelineCommand<byte[], byte[]>(client, ProtocolCommand.ECHO)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<byte[], byte[]>(client, ProtocolCommand.ECHO)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ECHO, (cmd)->cmd.echo(str), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status ping() {
		final PingResultConverter pingResultConverter = new PingResultConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.PING)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.PING)
					.run();
		}else{
			return new JedisCommand<>(client, ProtocolCommand.PING, (cmd)->cmd.ping(), pingResultConverter)
					.run();
		}
	}

	@Override
	public Status reset() {
		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.RESET)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.RESET)
					.run();
		}else{
			return new JedisCommand<Status, Status>(client, ProtocolCommand.RESET)
					.run();
		}
	}

	@Override
	public Status quit() {
		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.QUIT)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.QUIT)
					.run();
		}else{
			return new JedisCommand<>(client, ProtocolCommand.QUIT, (cmd)->cmd.quit(), okStatusConverter)
					.run();
		}
	}

	@Override
	public Status select(final int db) {
		final CommandArguments args = CommandArguments.create("db", db);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SELECT, (cmd)->cmd.select(db),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.SELECT)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SELECT, (cmd)->cmd.select(db), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientCaching(final boolean isYes) {
		final CommandArguments args = CommandArguments.create("isYes", isYes);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_CACHING)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_CACHING)
					.run(args);
		}else{
			return new JedisCommand<Status, Status>(client, ProtocolCommand.CLIENT_CACHING)
					.run(args);
		}
	}

	@Override
	public Long clientId() {
		if(isPipeline()){
			return new JedisPipelineCommand<Long, Long>(client, ProtocolCommand.CLIENT_ID)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Long, Long>(client, ProtocolCommand.CLIENT_ID)
					.run();
		}else{
			return new JedisCommand<>(client, ProtocolCommand.CLIENT_ID, (cmd)->cmd.clientId(), (v)->v)
					.run();
		}
	}

	@Override
	public Status clientSetName(final String name) {
		final CommandArguments args = CommandArguments.create("name", name);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_SETNAME)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_SETNAME)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.CLIENT_SETNAME,
					(cmd)->cmd.clientSetname(name), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create("name", name);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_SETNAME)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_SETNAME)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.CLIENT_SETNAME,
					(cmd)->cmd.clientSetname(name), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String clientGetName() {
		if(isPipeline()){
			return new JedisPipelineCommand<String, String>(client, ProtocolCommand.CLIENT_GETNAME)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<String, String>(client, ProtocolCommand.CLIENT_GETNAME)
					.run();
		}else{
			return new JedisCommand<>(client, ProtocolCommand.CLIENT_GETNAME,
					(cmd)->cmd.clientGetname(), (v)->v)
					.run();
		}
	}

	@Override
	public Integer clientGetRedir() {
		if(isPipeline()){
			return new JedisPipelineCommand<Integer, Integer>(client, ProtocolCommand.CLIENT_GETREDIR)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Integer, Integer>(client, ProtocolCommand.CLIENT_GETREDIR)
					.run();
		}else{
			return new JedisCommand<Integer, Integer>(client, ProtocolCommand.CLIENT_GETREDIR)
					.run();
		}
	}

	@Override
	public List<Client> clientList() {
		final ClientConverter.ClientListConverter clientListConverter = new ClientConverter.ClientListConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<List<Client>, List<Client>>(client, ProtocolCommand.CLIENT_LIST)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<Client>, List<Client>>(client, ProtocolCommand.CLIENT_LIST)
					.run();
		}else{
			return new JedisCommand<>(client, ProtocolCommand.CLIENT_LIST, (cmd)->cmd.clientList(), clientListConverter)
					.run();
		}
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create("clientType", clientType);
		final redis.clients.jedis.args.ClientType jClientType = (new ClientTypeConverter()).convert(clientType);
		final ClientConverter.ClientListConverter clientListConverter = new ClientConverter.ClientListConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<List<Client>, List<Client>>(client, ProtocolCommand.CLIENT_LIST)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<Client>, List<Client>>(client, ProtocolCommand.CLIENT_LIST)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.CLIENT_LIST, (cmd)->cmd.clientList(jClientType),
					clientListConverter)
					.run(args);
		}
	}

	@Override
	public Client clientInfo() {
		final ClientConverter clientConverter = new ClientConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<Client, Client>(client, ProtocolCommand.CLIENT_LIST)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Client, Client>(client, ProtocolCommand.CLIENT_LIST)
					.run();
		}else{
			return new JedisCommand<>(client, ProtocolCommand.CLIENT_LIST, (cmd)->cmd.clientInfo(),
					clientConverter)
					.run();
		}
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create("timeout", timeout);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_PAUSE)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_PAUSE)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.CLIENT_PAUSE, (cmd)->cmd.clientInfo(),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientReply(final ClientReply option) {
		final CommandArguments args = CommandArguments.create("option", option);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_REPLY)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_REPLY)
					.run(args);
		}else{
			return new JedisCommand<Status, Status>(client, ProtocolCommand.CLIENT_REPLY)
					.run(args);
		}
	}

	@Override
	public Status clientKill(final String host, final int port) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		final String addr = host + ':' + port;

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_KILL)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_KILL)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.CLIENT_KILL, (cmd)->cmd.clientKill(addr),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientUnblock(final int clientId) {
		final CommandArguments args = CommandArguments.create("clientId", clientId);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.CLIENT_UNBLOCK, (cmd)->cmd.clientUnblock(clientId),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type) {
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);
		final UnblockType unblockType = (new ClientUnblockTypeConverter()).convert(type);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.CLIENT_UNBLOCK,
					(cmd)->cmd.clientUnblock(clientId, unblockType), oneStatusConverter)
					.run(args);
		}
	}

}
