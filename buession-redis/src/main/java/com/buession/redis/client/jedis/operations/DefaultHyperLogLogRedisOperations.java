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
import com.buession.redis.utils.ReturnUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

/**
 * @author Yong.Teng
 */
public class DefaultHyperLogLogRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisHyperLogLogRedisOperations {

	public DefaultHyperLogLogRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status pfAdd(final String key, final String... elements){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"elements", elements);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().pfadd(key, elements).get() > 0),
					ProtocolCommand.PFADD, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.pfadd(key, elements) > 0), ProtocolCommand.PFADD,
					arguments);
		}
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C jc){
				String ret;

				if(isTransaction()){
					ret = getTransaction().pfmerge(destKey, keys).get();
				}else{
					if(jc instanceof Jedis){
						ret = ((Jedis) jc).pfmerge(destKey, keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.PFMERGE);
					}
				}

				return ReturnUtils.statusForOK(ret);
			}

		}, ProtocolCommand.PFMERGE, arguments);
	}

	@Override
	public Long pfCount(final String... keys){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().pfcount(keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).pfcount(keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.PFCOUNT);
					}
				}
			}

		}, ProtocolCommand.PFCOUNT, arguments);
	}

}
