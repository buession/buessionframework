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

import com.buession.core.Executor;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

/**
 * @author Yong.Teng
 */
public class DefaultJedisInternalRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisInternalRedisOperations {

	public DefaultJedisInternalRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Object sync(){
		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SYNC);
				}else{
					if(cmd instanceof Jedis){
						((Jedis) cmd).sync();
						return null;
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SYNC);
					}
				}

			}
		}, ProtocolCommand.SYNC);
	}

	@Override
	public Object pSync(final String masterRunId, final long offset){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("masterRunId",
				masterRunId).put("offset", offset);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				throw new NotSupportedCommandException(ProtocolCommand.PSYNC);
			}

		}, ProtocolCommand.PSYNC, args);
	}

}