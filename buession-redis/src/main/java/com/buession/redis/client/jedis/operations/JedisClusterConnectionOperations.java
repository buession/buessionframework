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
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.JedisCluster;

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

}
