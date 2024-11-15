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
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.response.PingResultConverter;

import java.util.List;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterConnectionOperations extends AbstractConnectionOperations<JedisClusterClient> {

	public JedisClusterConnectionOperations(final JedisClusterClient client) {
		super(client);
	}

	@Override
	public Status auth(final String user, final String password) {
		final CommandArguments args = CommandArguments.create(user).add(password);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.AUTH)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.AUTH)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.AUTH)
					.run(args);
		}
	}

	@Override
	public Status auth(final String password) {
		final CommandArguments args = CommandArguments.create(password);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.AUTH)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.AUTH)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.AUTH)
					.run(args);
		}
	}

	@Override
	public Status clientCaching(final boolean isYes) {
		final CommandArguments args = CommandArguments.create(isYes);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_CACHING)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_CACHING)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLIENT_CACHING)
					.run(args);
		}
	}

	@Override
	public String clientGetName() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<String, String>(client, ProtocolCommand.CLIENT_GETNAME)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<String, String>(client, ProtocolCommand.CLIENT_GETNAME)
					.run();
		}else{
			return new JedisClusterCommand<String, String>(client, ProtocolCommand.CLIENT_GETNAME)
					.run();
		}
	}

	@Override
	public Integer clientGetRedir() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Integer, Integer>(client, ProtocolCommand.CLIENT_GETREDIR)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Integer, Integer>(client, ProtocolCommand.CLIENT_GETREDIR)
					.run();
		}else{
			return new JedisClusterCommand<Integer, Integer>(client, ProtocolCommand.CLIENT_GETREDIR)
					.run();
		}
	}

	@Override
	public Long clientId() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Long, Long>(client, ProtocolCommand.CLIENT_ID)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Long, Long>(client, ProtocolCommand.CLIENT_ID)
					.run();
		}else{
			return new JedisClusterCommand<Long, Long>(client, ProtocolCommand.CLIENT_ID)
					.run();
		}
	}

	@Override
	public Client clientInfo() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Client, Client>(client, ProtocolCommand.CLIENT_LIST)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Client, Client>(client, ProtocolCommand.CLIENT_LIST)
					.run();
		}else{
			return new JedisClusterCommand<Client, Client>(client, ProtocolCommand.CLIENT_LIST)
					.run();
		}
	}

	@Override
	public Status clientKill(final String host, final int port) {
		final CommandArguments args = CommandArguments.create(host).add(port);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_KILL)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_KILL)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLIENT_KILL)
					.run(args);
		}
	}

	@Override
	public List<Client> clientList() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<Client>, List<Client>>(client, ProtocolCommand.CLIENT_LIST)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<Client>, List<Client>>(client, ProtocolCommand.CLIENT_LIST)
					.run();
		}else{
			return new JedisClusterCommand<List<Client>, List<Client>>(client, ProtocolCommand.CLIENT_LIST)
					.run();
		}
	}

	@Override
	public List<Client> clientList(final ClientType clientType) {
		final CommandArguments args = CommandArguments.create(clientType);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<Client>, List<Client>>(client, ProtocolCommand.CLIENT_LIST)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<Client>, List<Client>>(client, ProtocolCommand.CLIENT_LIST)
					.run(args);
		}else{
			return new JedisClusterCommand<List<Client>, List<Client>>(client, ProtocolCommand.CLIENT_LIST)
					.run(args);
		}
	}

	@Override
	public Status clientPause(final int timeout) {
		final CommandArguments args = CommandArguments.create(timeout);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_PAUSE)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_PAUSE)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLIENT_PAUSE)
					.run(args);
		}
	}

	@Override
	public Status clientReply(final ClientReply option) {
		final CommandArguments args = CommandArguments.create(option);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_REPLY)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_REPLY)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLIENT_REPLY)
					.run(args);
		}
	}

	@Override
	public Status clientSetName(final String name) {
		final CommandArguments args = CommandArguments.create(name);
		return clientSetName(args);
	}

	@Override
	public Status clientSetName(final byte[] name) {
		final CommandArguments args = CommandArguments.create(name);
		return clientSetName(args);
	}

	@Override
	public Status clientUnblock(final int clientId) {
		final CommandArguments args = CommandArguments.create(clientId);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
					.run(args);
		}
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type) {
		final CommandArguments args = CommandArguments.create(clientId).add(type);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLIENT_UNBLOCK)
					.run(args);
		}
	}

	@Override
	public String echo(final String str) {
		final CommandArguments args = CommandArguments.create(str);
		return echo(args);
	}

	@Override
	public byte[] echo(final byte[] str) {
		final CommandArguments args = CommandArguments.create(str);
		return echo(args);
	}

	@Override
	public Status ping() {
		final PingResultConverter pingResultConverter = new PingResultConverter();

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.PING)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.PING)
					.run();
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PING, (cmd)->cmd.ping(), pingResultConverter)
					.run();
		}
	}

	@Override
	public Status quit() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.QUIT)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.QUIT)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.QUIT)
					.run();
		}
	}

	@Override
	public Status reset() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.RESET)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.RESET)
					.run();
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.RESET)
					.run();
		}
	}

	@Override
	public Status select(final int db) {
		final CommandArguments args = CommandArguments.create(db);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.SELECT)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.SELECT)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.SELECT)
					.run(args);
		}
	}

	private <V> V echo(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<V, V>(client, ProtocolCommand.ECHO)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<V, V>(client, ProtocolCommand.ECHO)
					.run(args);
		}else{
			return new JedisClusterCommand<V, V>(client, ProtocolCommand.ECHO)
					.run(args);
		}
	}

	private Status clientSetName(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Status, Status>(client, ProtocolCommand.CLIENT_SETNAME)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Status, Status>(client, ProtocolCommand.CLIENT_SETNAME)
					.run(args);
		}else{
			return new JedisClusterCommand<Status, Status>(client, ProtocolCommand.CLIENT_SETNAME)
					.run(args);
		}
	}

}
