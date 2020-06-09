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

import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.BitMapCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.CommandArguments;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Jedis;

/**
 * @author Yong.Teng
 */
public class GenericJedisBinaryBitMapRedisOperations extends AbstractJedisBinaryBitMapRedisOperations<Jedis> {

	public GenericJedisBinaryBitMapRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);

		return execute((Jedis cmd)->isTransaction() ? getTransaction().bitpos(key, value).get() : cmd.bitpos(key,
				value), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("start",
				start).put("end", end);
		final BitPosParams bitPosParams = new BitPosParams(start, end);

		return execute((Jedis cmd)->isTransaction() ? getTransaction().bitpos(key, value, bitPosParams).get() :
				cmd.bitpos(key, value, bitPosParams), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long bitOp(final BitMapCommands.Operation operation, final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("operation", operation).put("destKey",
				destKey).put("keys", keys);

		return execute((Jedis cmd)->isTransaction() ?
				getTransaction().bitop(JedisClientUtils.bitOperationConvert(operation), destKey, keys).get() :
				cmd.bitop(JedisClientUtils.bitOperationConvert(operation), destKey, keys), ProtocolCommand.BITOP,
				args);
	}

}
