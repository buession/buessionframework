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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.ShardedJedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisExceptionUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

/**
 * @author Yong.Teng
 */
public class ShardedJedisConnectionOperations extends AbstractConnectionOperations<ShardedJedis, ShardedJedisPipeline> {

	public ShardedJedisConnectionOperations(final ShardedJedisClient client){
		super(client);
	}

	@Override
	public byte[] echo(final byte[] str){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().echo(str)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().echo(str)));
		}else{
			return execute((cmd)->cmd.echo(str));
		}
	}

	@Override
	public Status auth(final String password){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.AUTH, client.getConnection());
		return null;
	}

	@Override
	public Status auth(final byte[] password){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.AUTH, client.getConnection());
		return null;
	}

	@Override
	public Status ping(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.PING, client.getConnection());
		return null;
	}

	@Override
	public Status quit(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.QUIT, client.getConnection());
		return null;
	}

	@Override
	public Status select(final int db){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SELECT, client.getConnection());
		return null;
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SWAPDB, client.getConnection());
		return null;
	}

}
