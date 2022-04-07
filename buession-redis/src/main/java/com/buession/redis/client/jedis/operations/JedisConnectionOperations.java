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
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.args.UnblockType;

import java.util.List;

/**
 * Jedis 单机模式连接命令操作
 *
 * @author Yong.Teng
 */
public final class JedisConnectionOperations extends AbstractConnectionOperations<Jedis> {

	public JedisConnectionOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public Status auth(final String user, final String password){
		final CommandArguments args = CommandArguments.create("user", user).put("password", password);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.AUTH, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.AUTH, args);
		}else{
			return execute((cmd)->cmd.auth(user, password), Converters.OK_STATUS_CONVERTER, ProtocolCommand.AUTH, args);
		}
	}

	@Override
	public Status auth(final byte[] user, final byte[] password){
		return auth(SafeEncoder.encode(user), SafeEncoder.encode(password));
	}

	@Override
	public Status auth(final String password){
		final CommandArguments args = CommandArguments.create("password", password);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.AUTH, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.AUTH, args);
		}else{
			return execute((cmd)->cmd.auth(password), Converters.OK_STATUS_CONVERTER, ProtocolCommand.AUTH, args);
		}
	}

	@Override
	public Status auth(final byte[] password){
		return auth(SafeEncoder.encode(password));
	}

	@Override
	public String echo(final String str){
		final CommandArguments args = CommandArguments.create("str", str);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ECHO, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ECHO, args);
		}else{
			return execute((cmd)->cmd.echo(str), ProtocolCommand.ECHO, args);
		}
	}

	@Override
	public byte[] echo(final byte[] str){
		final CommandArguments args = CommandArguments.create("str", str);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.ECHO, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.ECHO, args);
		}else{
			return execute((cmd)->cmd.echo(str), ProtocolCommand.ECHO, args);
		}
	}

	@Override
	public Status ping(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.PING);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.PING);
		}else{
			return execute((cmd)->cmd.ping(), PING_RESULT_CONVERTER, ProtocolCommand.PING);
		}
	}

	@Override
	public Status quit(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.QUIT);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.QUIT);
		}else{
			return execute((cmd)->cmd.quit(), Converters.OK_STATUS_CONVERTER, ProtocolCommand.QUIT);
		}
	}

	@Override
	public Status select(final int db){
		final CommandArguments args = CommandArguments.create("db", db);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().select(db), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SELECT, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SELECT, args);
		}else{
			return execute((cmd)->cmd.select(db), Converters.OK_STATUS_CONVERTER, ProtocolCommand.SELECT, args);
		}
	}

	@Override
	public Status clientCaching(final boolean isYes){
		final CommandArguments args = CommandArguments.create("isYes", isYes);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_CACHING, args);
	}

	@Override
	public Long clientId(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLIENT_ID);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLIENT_ID);
		}else{
			return execute((cmd)->cmd.clientId(), ProtocolCommand.CLIENT_ID);
		}
	}

	@Override
	public Status clientSetName(final String name){
		final CommandArguments args = CommandArguments.create("name", name);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLIENT_SETNAME, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLIENT_SETNAME, args);
		}else{
			return execute((cmd)->cmd.clientSetname(name), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLIENT_SETNAME, args);
		}
	}

	@Override
	public Status clientSetName(final byte[] name){
		final CommandArguments args = CommandArguments.create("name", name);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLIENT_SETNAME, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLIENT_SETNAME, args);
		}else{
			return execute((cmd)->cmd.clientSetname(name), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLIENT_SETNAME, args);
		}
	}

	@Override
	public String clientGetName(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLIENT_GETNAME);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLIENT_GETNAME);
		}else{
			return execute((cmd)->cmd.clientGetname(), ProtocolCommand.CLIENT_GETNAME);
		}
	}

	@Override
	public Integer clientGetRedir(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_GETREDIR);
	}

	@Override
	public List<Client> clientList(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLIENT_LIST);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLIENT_LIST);
		}else{
			return execute((cmd)->cmd.clientList(), CLIENT_LIST_CONVERTER, ProtocolCommand.CLIENT_LIST);
		}
	}

	@Override
	public List<Client> clientList(final ClientType clientType){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLIENT_LIST);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLIENT_LIST);
		}else{
			final redis.clients.jedis.args.ClientType jedisClientType = CLIENT_TYPE_JEDIS_CONVERTER.convert(clientType);
			return execute((cmd)->cmd.clientList(jedisClientType), CLIENT_LIST_CONVERTER, ProtocolCommand.CLIENT_LIST);
		}
	}

	@Override
	public Client clientInfo(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLIENT_INFO);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLIENT_INFO);
		}else{
			return execute((cmd)->cmd.clientInfo(), Converters.INFO_CONVERTER, ProtocolCommand.CLIENT_INFO);
		}
	}

	@Override
	public Status clientPause(final int timeout){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLIENT_PAUSE);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLIENT_PAUSE);
		}else{
			return execute((cmd)->cmd.clientPause(timeout), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLIENT_PAUSE);
		}
	}

	@Override
	public Status clientReply(final ClientReply option){
		final CommandArguments args = CommandArguments.create("option", option);
		return execute(CommandNotSupported.ALL, ProtocolCommand.CLIENT_REPLY, args);
	}

	@Override
	public Status clientKill(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLIENT_KILL, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLIENT_KILL, args);
		}else{
			return execute((cmd)->cmd.clientKill(host + ":" + port), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.CLIENT_KILL, args);
		}
	}

	@Override
	public Status clientUnblock(final int clientId){
		final CommandArguments args = CommandArguments.create("clientId", clientId);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLIENT_UNBLOCK, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLIENT_UNBLOCK, args);
		}else{
			return execute((cmd)->cmd.clientUnblock(clientId, null), Converters.LONG_STATUS_CONVERTER,
					ProtocolCommand.CLIENT_UNBLOCK, args);
		}
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.CLIENT_UNBLOCK, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.CLIENT_UNBLOCK, args);
		}else{
			final UnblockType unblockType = CLIENT_UNBLOCK_JEDIS_CONVERTER.convert(type);
			return execute((cmd)->cmd.clientUnblock(clientId, unblockType), Converters.LONG_STATUS_CONVERTER,
					ProtocolCommand.CLIENT_UNBLOCK, args);
		}
	}

}
