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
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

import java.util.Collections;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class DefaultJedisConfigureRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisConfigureRedisOperations {

	public DefaultJedisConfigureRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status configSet(final String parameter, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					return statusForOK(getTransaction().configSet(parameter, value).get());
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).configSet(parameter, value));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
					}
				}
			}

		}, ProtocolCommand.CONFIG_SET, args);
	}

	@Override
	public List<String> configGet(final String parameter){
		final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter);

		return execute(new Executor<C, List<String>>() {

			@Override
			public List<String> execute(C cmd){
				if(isTransaction()){
					return Collections.unmodifiableList(getTransaction().configGet(parameter).get());
				}else{
					if(cmd instanceof Jedis){
						return Collections.unmodifiableList(((Jedis) cmd).configGet(parameter));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.CONFIG_GET);
					}
				}
			}

		}, ProtocolCommand.CONFIG_GET, args);
	}

	@Override
	public Status configResetStat(){
		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					return statusForOK(getTransaction().configResetStat().get());
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).configResetStat());
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.CONFIG_RESETSTAT);
					}
				}
			}

		}, ProtocolCommand.CONFIG_RESETSTAT);
	}

	@Override
	public Status configRewrite(){
		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.CONFIG_REWRITE);
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).configRewrite());
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.CONFIG_REWRITE);
					}
				}
			}

		}, ProtocolCommand.CONFIG_REWRITE);
	}

}
