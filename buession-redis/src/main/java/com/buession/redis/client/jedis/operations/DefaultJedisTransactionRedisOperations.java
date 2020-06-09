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
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.transaction.jedis.JedisTransaction;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

/**
 * @author Yong.Teng
 */
public class DefaultJedisTransactionRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisTransactionRedisOperations {

	public DefaultJedisTransactionRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Transaction multi(){
		return execute(new Executor<C, Transaction>() {

			@Override
			public Transaction execute(C cmd){
				if(cmd instanceof Jedis){
					return new JedisTransaction(((Jedis) cmd).multi());
				}else{
					throw new NotSupportedCommandException(ProtocolCommand.MULTI);
				}
			}

		}, ProtocolCommand.MULTI);
	}

	@Override
	public void exec(final Transaction transaction){
		final CommandArguments args = CommandArguments.getInstance().put("transaction", transaction);

		execute(new Executor<C, Void>() {

			@Override
			public Void execute(C cmd){
				if(cmd instanceof Jedis){
					transaction.exec();
					return null;
				}else{
					throw new NotSupportedCommandException(ProtocolCommand.EXEC);
				}
			}

		}, ProtocolCommand.EXEC, args);
	}

	@Override
	public void discard(final Transaction transaction){
		final CommandArguments args = CommandArguments.getInstance().put("transaction", transaction);

		execute(new Executor<C, Void>() {

			@Override
			public Void execute(C cmd){
				if(cmd instanceof Jedis){
					transaction.discard();
					return null;
				}else{
					throw new NotSupportedCommandException(ProtocolCommand.DISCARD);
				}
			}

		}, ProtocolCommand.DISCARD, args);
	}

	@Override
	public Status watch(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(cmd instanceof Jedis){
					return statusForOK(((Jedis) cmd).watch(keys));
				}else{
					throw new NotSupportedCommandException(ProtocolCommand.WATCH);
				}
			}

		}, ProtocolCommand.WATCH, args);
	}

	@Override
	public Status unwatch(){
		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(cmd instanceof Jedis){
					return statusForOK(((Jedis) cmd).unwatch());
				}else{
					throw new NotSupportedCommandException(ProtocolCommand.UNWATCH);
				}
			}

		}, ProtocolCommand.UNWATCH);
	}

}
