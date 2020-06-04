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
import com.buession.redis.core.command.BitMapCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.commands.BinaryJedisCommands;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class DefaultBinaryBitMapRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinaryBitMapRedisOperations {

	public DefaultBinaryBitMapRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
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
	public Status setBit(final byte[] key, final long offset, final boolean value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"offset", offset).put("value", value);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().setbit(SafeEncoder.encode(key), offset,
					value).get()), ProtocolCommand.SETBIT, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.setbit(key, offset, value)), ProtocolCommand.SETBIT,
					arguments);
		}
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
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
	public Long bitPos(final byte[] key, final boolean value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(final C jc){
				if(isTransaction()){
					return getTransaction().bitpos(key, value).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).bitpos(key, value);
					}else if(jc instanceof ShardedJedis){
						return ((ShardedJedis) jc).bitpos(SafeEncoder.encode(key), value);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.BITPOS);
					}
				}
			}
		}, ProtocolCommand.BITPOS, arguments);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("start", start).put("end", end);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(final C jc){
				if(isTransaction()){
					return getTransaction().bitpos(key, value, new BitPosParams(start, end)).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).bitpos(key, value, new BitPosParams(start, end));
					}else if(jc instanceof ShardedJedis){
						return ((ShardedJedis) jc).bitpos(SafeEncoder.encode(key), value, new BitPosParams(start,
								end));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.BITPOS);
					}
				}
			}
		}, ProtocolCommand.BITPOS, arguments);
	}

	@Override
	public Long bitOp(final BitMapCommands.Operation operation, final byte[] destKey, final byte[]... keys){
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
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"arguments", arguments);

		if(isTransaction()){
			return execute((C jc)->getTransaction().bitfield(key, arguments).get(), ProtocolCommand.BITFIELD, args);
		}else{
			return execute((C jc)->jc.bitfield(key, arguments), ProtocolCommand.BITFIELD, args);
		}
	}

	@Override
	public Long bitCount(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().bitcount(key).get(), ProtocolCommand.BITCOUNT, arguments);
		}else{
			return execute((C jc)->jc.bitcount(key), ProtocolCommand.BITCOUNT, arguments);
		}
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
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
