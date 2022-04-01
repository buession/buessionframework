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
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.Converters;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;

/**
 * Jedis 哨兵模式连接命令操作
 *
 * @author Yong.Teng
 */
public final class JedisSentinelConnectionOperations extends AbstractConnectionOperations<Jedis> {

	public JedisSentinelConnectionOperations(final JedisSentinelClient client){
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
			return pipelineExecute((cmd)->newJedisResult(getPipeline().echo(str)), ProtocolCommand.ECHO, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().echo(str)), ProtocolCommand.ECHO, args);
		}else{
			return execute((cmd)->cmd.echo(str), ProtocolCommand.ECHO, args);
		}
	}

	@Override
	public byte[] echo(final byte[] str){
		final CommandArguments args = CommandArguments.create("str", str);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().echo(str)), ProtocolCommand.ECHO, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().echo(str)), ProtocolCommand.ECHO, args);
		}else{
			return execute((cmd)->cmd.echo(str), ProtocolCommand.ECHO, args);
		}
	}

	@Override
	public Status ping(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().ping(), PING_RESULT_CONVERTER),
					ProtocolCommand.PING);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().ping(), PING_RESULT_CONVERTER),
					ProtocolCommand.PING);
		}else{
			return execute((cmd)->cmd.ping(), PING_RESULT_CONVERTER, ProtocolCommand.PING);
		}
	}

	@Override
	public Status quit(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().ping(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.PING);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().ping(), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.PING);
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
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().select(db), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SELECT, args);
		}else{
			return execute((cmd)->cmd.select(db), Converters.OK_STATUS_CONVERTER, ProtocolCommand.SELECT, args);
		}
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		final CommandArguments args = CommandArguments.create("db1", db1).put("db2", db2);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().swapDB(db1, db2), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SWAPDB, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().swapDB(db1, db2), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.SWAPDB, args);
		}else{
			return execute((cmd)->cmd.swapDB(db1, db2), Converters.OK_STATUS_CONVERTER, ProtocolCommand.SWAPDB, args);
		}
	}

}
