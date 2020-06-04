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
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class DefaultBitMapRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisBitMapRedisOperations {

	public DefaultBitMapRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status setBit(final String key, final long offset, final String value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"offset", offset).put("value", value);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().setbit(SafeEncoder.encode(key), offset,
					SafeEncoder.encode(value)).get()), ProtocolCommand.SETBIT, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.setbit(key, offset, value)), ProtocolCommand.SETBIT,
					arguments);
		}
	}

	@Override
	public Status setBit(final String key, final long offset, final boolean value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"offset", offset).put("value", value);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().setbit(key, offset, value).get()),
					ProtocolCommand.SETBIT, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.setbit(key, offset, value)), ProtocolCommand.SETBIT,
					arguments);
		}
	}

	@Override
	public Status getBit(final String key, final long offset){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"offset", offset);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().getbit(key, offset).get()),
					ProtocolCommand.GETBIT, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.getbit(key, offset)), ProtocolCommand.GETBIT,
					arguments);
		}
	}

	@Override
	public Long bitPos(final String key, final boolean value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		if(isTransaction()){
			return execute((C jc)->getTransaction().bitpos(key, value).get(), ProtocolCommand.BITPOS, arguments);
		}else{
			return execute((C jc)->jc.bitpos(key, value), ProtocolCommand.BITPOS, arguments);
		}
	}

	@Override
	public Long bitPos(final String key, final boolean value, final int start, final int end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("start", start).put("end", end);

		if(isTransaction()){
			return execute((C jc)->getTransaction().bitpos(key, value, new BitPosParams(start, end)).get(),
					ProtocolCommand.BITPOS, arguments);
		}else{
			return execute((C jc)->jc.bitpos(key, value, new BitPosParams(start, end)), ProtocolCommand.BITPOS,
					arguments);
		}
	}

	@Override
	public Long bitOp(final Operation operation, final String destKey, final String... keys){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("operation",
				operation).put("destKey", destKey).put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(final C jc){
				if(isTransaction()){
					return getTransaction().bitop(JedisClientUtils.bitOperationConvert(operation), destKey, keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).bitop(JedisClientUtils.bitOperationConvert(operation), destKey, keys);
					}else if(jc instanceof ShardedJedis){
						return getShard((ShardedJedis) jc, destKey).bitop(JedisClientUtils.bitOperationConvert(operation), destKey, keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.BITOP);
					}
				}
			}
		}, ProtocolCommand.BITOP, arguments);
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"arguments", arguments);

		if(isTransaction()){
			return execute((C jc)->getTransaction().bitfield(key, arguments).get(), ProtocolCommand.BITFIELD, args);
		}else{
			return execute((C jc)->jc.bitfield(key, arguments), ProtocolCommand.BITFIELD, args);
		}
	}

	@Override
	public Long bitCount(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().bitcount(key).get(), ProtocolCommand.BITCOUNT, arguments);
		}else{
			return execute((C jc)->jc.bitcount(key), ProtocolCommand.BITCOUNT, arguments);
		}
	}

	@Override
	public Long bitCount(final String key, final long start, final long end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		if(isTransaction()){
			return execute((C jc)->getTransaction().bitcount(key, start, end).get(), ProtocolCommand.BITCOUNT,
					arguments);
		}else{
			return execute((C jc)->jc.bitcount(key, start, end), ProtocolCommand.BITCOUNT, arguments);
		}
	}

}
