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
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.DebugCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.commands.BinaryJedisCommands;

/**
 * @author Yong.Teng
 */
public class DefaultJedisBinaryDebugRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinaryDebugRedisOperations {

	public DefaultJedisBinaryDebugRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public byte[] echo(final byte[] str){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("str", str);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().echo(str).get(), ProtocolCommand.ECHO, args);
		}else{
			return execute((C cmd)->cmd.echo(str), ProtocolCommand.ECHO, args);
		}
	}

	@Override
	public Object object(final DebugCommands.ObjectCommand command, final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("command", command).put(
				"key", key);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C cmd){
				if(isTransaction()){
					switch(command){
						case ENCODING:
							return getTransaction().objectEncoding(key).get();
						case IDLETIME:
							return getTransaction().objectIdletime(key).get();
						case REFCOUNT:
							return getTransaction().objectRefcount(key).get();
						default:
							return null;
					}
				}else{
					Jedis jedis = cmd instanceof Jedis ? (Jedis) cmd : getShard((ShardedJedis) cmd, key);

					if(jedis == null){
						return null;
					}

					switch(command){
						case ENCODING:
							return jedis.objectEncoding(key);
						case IDLETIME:
							return jedis.objectIdletime(key);
						case REFCOUNT:
							return jedis.objectRefcount(key);
						default:
							return null;
					}
				}
			}

		}, ProtocolCommand.OBJECT, args);
	}

	@Override
	public Object slowLog(final DebugCommands.SlowLogCommand command, final byte[]... arguments){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("command", command).put(
				"arguments", arguments);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SLOWLOG);
				}else{
					if(cmd instanceof Jedis){
						Jedis jedis = (Jedis) cmd;

						switch(command){
							case GET:
								return jedis.slowlogGet();
							case LEN:
								return jedis.slowlogLen();
							case RESET:
								return jedis.slowlogReset();
							default:
								return null;
						}
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SLOWLOG);
					}
				}
			}

		}, ProtocolCommand.SLOWLOG, args);
	}

}