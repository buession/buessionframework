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
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public class GenericJedisBinaryConfigureRedisOperations extends AbstractJedisBinaryConfigureRedisOperations<Jedis> {

	public GenericJedisBinaryConfigureRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);

		if(isTransaction()){
			return execute((Jedis cmd)->statusForOK(getTransaction().configSet(SafeEncoder.encode(parameter),
					SafeEncoder.encode(value)).get()), ProtocolCommand.CONFIG_SET, args);
		}else{
			return execute((Jedis cmd)->statusForOK(cmd.configSet(SafeEncoder.encode(parameter),
					SafeEncoder.encode(value))), ProtocolCommand.CONFIG_SET, args);
		}
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter);

		return execute(new Executor<Jedis, List<byte[]>>() {

			@Override
			public List<byte[]> execute(Jedis cmd){
				if(isTransaction()){
					final List<String> configs = getTransaction().configGet(SafeEncoder.encode(parameter)).get();

					if(configs == null){
						return null;
					}else{
						final List<byte[]> result =
								configs.stream().map(SafeEncoder::encode).collect(Collectors.toList());
						return Collections.unmodifiableList(result);
					}
				}else{
					return Collections.unmodifiableList(cmd.configGet(parameter));
				}
			}

		}, ProtocolCommand.CONFIG_GET, args);
	}

}
