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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.ClusterMode;
import com.buession.redis.core.command.ProtocolCommand;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

/**
 * @author Yong.Teng
 */
public class ShardedJedisConnectionOperations extends AbstractConnectionOperations<ShardedJedis, ShardedJedisPipeline> {

	public ShardedJedisConnectionOperations(final JedisRedisClient<ShardedJedis> client){
		super(client, ClusterMode.SHARDED);
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
		commandAllNotSupportedException(ProtocolCommand.AUTH);
		return null;
	}

	@Override
	public Status auth(final byte[] password){
		commandAllNotSupportedException(ProtocolCommand.AUTH);
		return null;
	}

	@Override
	public Status ping(){
		commandAllNotSupportedException(ProtocolCommand.PING);
		return null;
	}

	@Override
	public Status quit(){
		commandAllNotSupportedException(ProtocolCommand.QUIT);
		return null;
	}

	@Override
	public Status select(final int db){
		commandAllNotSupportedException(ProtocolCommand.SELECT);
		return null;
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		commandAllNotSupportedException(ProtocolCommand.SWAPDB);
		return null;
	}

}
