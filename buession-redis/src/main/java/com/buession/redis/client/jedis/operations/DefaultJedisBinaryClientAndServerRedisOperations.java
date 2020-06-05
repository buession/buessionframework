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
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.BinaryJedisCommands;

/**
 * @author Yong.Teng
 */
public class DefaultJedisBinaryClientAndServerRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinaryClientAndServerRedisOperations {

	public DefaultJedisBinaryClientAndServerRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status auth(final byte[] password){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("password", "******");

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.AUTH);
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).auth(SafeEncoder.encode(password)));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.AUTH);
					}
				}
			}

		}, ProtocolCommand.AUTH, args);
	}

	@Override
	public Status clientSetName(final byte[] name){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("name", name);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.CLIENT_SETNAME);
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).clientSetname(name));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.CLIENT_SETNAME);
					}
				}
			}

		}, ProtocolCommand.CLIENT_SETNAME, args);
	}

}
