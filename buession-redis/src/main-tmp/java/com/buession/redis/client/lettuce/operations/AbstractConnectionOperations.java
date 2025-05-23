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
package com.buession.redis.client.lettuce.operations;

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.ConnectionOperations;
import com.buession.redis.core.ClientAttributeOption;
import com.buession.redis.core.command.args.ClientKillArgument;
import com.buession.redis.core.internal.lettuce.LettuceKillArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.KillArgs;

import java.util.Optional;

/**
 * Jedis 连接命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractConnectionOperations<C extends LettuceRedisClient>
		extends AbstractLettuceRedisOperations<C> implements ConnectionOperations {

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

	@Override
	public Status clientSetInfo(final ClientAttributeOption clientAttributeOption, final byte[] value) {
		return clientSetInfo(clientAttributeOption, SafeEncoder.encode(value));
	}

	@Override
	public Status clientSetName(final String name) {
		return clientSetName(SafeEncoder.encode(name));
	}

	protected static KillArgs createKillArgsFromClientKillArgument(final ClientKillArgument... clientKillArguments) {
		if(clientKillArguments != null && clientKillArguments.length > 0){
			final LettuceKillArgs killArgs = LettuceKillArgs.from(clientKillArguments[0]);
			ClientKillArgument clientKillArgument;

			for(int i = 1; i < clientKillArguments.length; i++){
				clientKillArgument = clientKillArguments[i];

				Optional.ofNullable(clientKillArgument.getClientId()).ifPresent(killArgs::id);
				Optional.ofNullable(clientKillArgument.getClientType()).ifPresent(killArgs::type);
				Optional.ofNullable(clientKillArgument.getUsername()).ifPresent(killArgs::user);
				Optional.ofNullable(clientKillArgument.getAddr()).ifPresent(killArgs::addr);
				Optional.ofNullable(clientKillArgument.getLaddr()).ifPresent(killArgs::laddr);
				Optional.ofNullable(clientKillArgument.getSkipMe()).ifPresent(killArgs::skipme);
			}

			return killArgs;
		}else{
			return null;
		}
	}

}
