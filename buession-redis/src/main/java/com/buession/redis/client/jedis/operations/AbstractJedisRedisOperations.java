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

import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.AbstractRedisOperations;
import com.buession.redis.core.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.jedis.JedisResult;
import com.buession.redis.exception.RedisException;
import redis.clients.jedis.Response;

/**
 * Jedis Redis 命令操作抽象类
 *
 * @param <C>
 * 		连接对象
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractJedisRedisOperations<C extends JedisRedisConnection> extends AbstractRedisOperations<C>
		implements JedisRedisOperations<C> {

	protected JedisRedisClient client;

	public AbstractJedisRedisOperations(final JedisRedisClient client){
		this.client = client;
	}

	@Override
	public <R> R execute(final Command<C, R> command) throws RedisException{
		return execute(command, null);
	}

	@Override
	public <R> R execute(final Command<C, R> command, final CommandArguments arguments) throws RedisException{
		return client.execute(new Command<RedisConnection, R>() {

			@Override
			public ProtocolCommand getCommand(){
				return command.getCommand();
			}

			@Override
			public R execute(final RedisConnection connection){
				return command.execute((C) connection);
			}

		}, arguments);
	}

	protected <SV, TV> JedisResult<SV, TV> newJedisResult(final Response<SV> response){
		return JedisResult.Builder.<SV, TV>forResponse(response).build();
	}

	protected <SV, TV> JedisResult<SV, TV> newJedisResult(final Response<SV> response,
														  final Converter<SV, TV> converter){
		return JedisResult.Builder.<SV, TV>forResponse(response).mappedWith(converter).build();
	}

}
