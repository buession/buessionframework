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
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.pubsub.jedis.DefaultJedisPubSub;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

/**
 * @author Yong.Teng
 */
public class DefaultJedisPubSubRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisPubSubRedisOperations {

	public DefaultJedisPubSubRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Long publish(final String channel, final String message){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("channel", channel).put(
				"message", message);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().publish(channel, message).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).publish(channel, message);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.PUBLISH);
					}
				}
			}

		}, ProtocolCommand.PUBLISH, args);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		final OperationsCommandArguments args =
				OperationsCommandArguments.getInstance().put("channels", channels).put("pubSubListener",
						pubSubListener);

		execute(new Executor<C, Void>() {

			@Override
			public Void execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SUBSCRIBE);
				}else{
					if(cmd instanceof Jedis){
						((Jedis) cmd).subscribe(new DefaultJedisPubSub(pubSubListener), channels);

						return null;
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SUBSCRIBE);
					}
				}
			}

		}, ProtocolCommand.SUBSCRIBE, args);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		final OperationsCommandArguments args =
				OperationsCommandArguments.getInstance().put("patterns", patterns).put("pubSubListener",
						pubSubListener);

		execute(new Executor<C, Void>() {

			@Override
			public Void execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.PSUBSCRIBE);
				}else{
					if(cmd instanceof Jedis){
						((Jedis) cmd).psubscribe(new DefaultJedisPubSub(pubSubListener), patterns);

						return null;
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.PSUBSCRIBE);
					}
				}
			}

		}, ProtocolCommand.PSUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe(){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final String... channels){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(){
		throw new NotSupportedCommandException(ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		throw new NotSupportedCommandException(ProtocolCommand.PUNSUBSCRIBE);
	}

}