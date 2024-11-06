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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.ConnectionOperations;
import com.buession.redis.core.command.args.ClientKillArgument;
import com.buession.redis.core.internal.jedis.JedisClientKillParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.params.ClientKillParams;

import java.util.Optional;

/**
 * Jedis 连接命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link JedisRedisClient}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractConnectionOperations<C extends JedisRedisClient>
		extends AbstractJedisRedisOperations<C> implements ConnectionOperations {

	public AbstractConnectionOperations(final C client) {
		super(client);
	}

	@Override
	public Status auth(final byte[] user, final byte[] password) {
		return auth(SafeEncoder.encode(user), SafeEncoder.encode(password));
	}

	@Override
	public Status auth(final byte[] password) {
		return auth(SafeEncoder.encode(password));
	}


	protected static ClientKillParams createClientKillParamsFromClientKillArgument(
			final ClientKillArgument... clientKillArguments) {
		if(clientKillArguments != null && clientKillArguments.length > 0){
			final JedisClientKillParams clientKillParams = JedisClientKillParams.from(clientKillArguments[0]);
			ClientKillArgument clientKillArgument;

			for(int i = 1; i < clientKillArguments.length; i++){
				clientKillArgument = clientKillArguments[i];

				Optional.ofNullable(clientKillArgument.getClientId()).ifPresent(clientKillParams::id);
				Optional.ofNullable(clientKillArgument.getClientType()).ifPresent(clientKillParams::type);
				Optional.ofNullable(clientKillArgument.getUsername()).ifPresent(clientKillParams::user);
				Optional.ofNullable(clientKillArgument.getAddr()).ifPresent(clientKillParams::addr);
				Optional.ofNullable(clientKillArgument.getLaddr()).ifPresent(clientKillParams::laddr);
				Optional.ofNullable(clientKillArgument.getSkipMe()).ifPresent(clientKillParams::skipMe);
			}

			return clientKillParams;
		}else{
			return null;
		}
	}

}
