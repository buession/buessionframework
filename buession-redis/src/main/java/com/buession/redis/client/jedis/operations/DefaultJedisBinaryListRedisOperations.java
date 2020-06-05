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
import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.commands.BinaryJedisCommands;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public class DefaultJedisBinaryListRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinaryListRedisOperations {

	public DefaultJedisBinaryListRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("values",
				values);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().lpush(key, values).get(), ProtocolCommand.LPUSH, args);
		}else{
			return execute((C cmd)->cmd.lpush(key, values), ProtocolCommand.LPUSH, args);
		}
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("values",
				values);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().lpushx(key, values).get(), ProtocolCommand.LPUSHX, args);
		}else{
			return execute((C cmd)->cmd.lpushx(key, values), ProtocolCommand.LPUSHX, args);
		}
	}

	@Override
	public Long lInsert(final byte[] key, final byte[] value, final ListCommands.ListPosition position,
			final byte[] pivot){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("position"
				, position).put("pivot", pivot).put("value", value);

		if(isTransaction()){
			return execute((C cmd)->cmd.linsert(key, JedisClientUtils.listPositionConvert(position), pivot, value),
					ProtocolCommand.LINSERT, args);
		}else{
			return execute((C cmd)->cmd.linsert(key, JedisClientUtils.listPositionConvert(position), pivot, value),
					ProtocolCommand.LINSERT, args);
		}
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("index",
				index).put("value", value);

		if(isTransaction()){
			return execute((C cmd)->statusForOK(getTransaction().lset(key, index, value).get()), ProtocolCommand.LSET,
					args);
		}else{
			return execute((C cmd)->statusForOK(cmd.lset(key, index, value)), ProtocolCommand.LSET, args);
		}
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("index",
				index);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().lindex(key, index).get(), ProtocolCommand.LINDEX, args);
		}else{
			return execute((C cmd)->cmd.lindex(key, index), ProtocolCommand.LINDEX, args);
		}
	}

	@Override
	public byte[] lPop(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().lpop(key).get(), ProtocolCommand.LPOP, args);
		}else{
			return execute((C cmd)->cmd.lpop(key), ProtocolCommand.LPOP, args);
		}
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("keys", keys).put(
				"timeout", timeout);

		return execute(new Executor<C, List<byte[]>>() {

			@Override
			public List<byte[]> execute(C cmd){
				List<String> ret;

				if(isTransaction()){
					ret = getTransaction().blpop(timeout, keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).blpop(timeout, keys);
					}else{
						String[] strKeys = SafeEncoder.encode(keys);
						ret = ((ShardedJedis) cmd).blpop(timeout, strKeys[0]);
					}
				}

				return ret == null ? null : ret.stream().map(SafeEncoder::encode).collect(Collectors.toList());
			}

		}, ProtocolCommand.BLPOP, args);
	}

	@Override
	public byte[] rPop(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().rpop(key).get(), ProtocolCommand.RPOP, args);
		}else{
			return execute((C cmd)->cmd.rpop(key), ProtocolCommand.RPOP, args);
		}
	}

	@Override
	public byte[] rPoplPush(final byte[] source, final byte[] destKey){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("source", source).put(
				"destKey", destKey);

		return execute(new Executor<C, byte[]>() {

			@Override
			public byte[] execute(C cmd){
				if(isTransaction()){
					return getTransaction().rpoplpush(source, destKey).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).rpoplpush(source, destKey);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.RPOPLPUSH);
					}
				}
			}

		}, ProtocolCommand.RPOPLPUSH, args);
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("keys", keys).put(
				"timeout", timeout);

		return execute(new Executor<C, List<byte[]>>() {

			@Override
			public List<byte[]> execute(C cmd){
				List<String> ret;

				if(isTransaction()){
					ret = getTransaction().brpop(timeout, keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).brpop(timeout, keys);
					}else if(cmd instanceof ShardedJedis){
						String[] strKeys = SafeEncoder.encode(keys);

						ret = ((ShardedJedis) cmd).brpop(timeout, strKeys[0]);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.BRPOP);
					}
				}

				return ret == null ? null : ret.stream().map(SafeEncoder::encode).collect(Collectors.toList());
			}

		}, ProtocolCommand.BRPOP, args);
	}

	@Override
	public byte[] brPoplPush(final byte[] source, final byte[] destKey, final int timeout){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("source", source).put(
				"destKey", destKey).put("timeout", timeout);

		return execute(new Executor<C, byte[]>() {

			@Override
			public byte[] execute(C cmd){
				if(isTransaction()){
					return getTransaction().brpoplpush(source, destKey, timeout).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).brpoplpush(source, destKey, timeout);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.BRPOPLPUSH);
					}
				}
			}

		}, ProtocolCommand.BRPOPLPUSH, args);
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("values",
				values);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().rpush(key, values).get(), ProtocolCommand.RPUSH, args);
		}else{
			return execute((C cmd)->cmd.rpush(key, values), ProtocolCommand.RPUSH, args);
		}
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("values",
				values);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().rpushx(key, values).get(), ProtocolCommand.RPUSHX, args);
		}else{
			return execute((C cmd)->cmd.rpushx(key, values), ProtocolCommand.RPUSHX, args);
		}
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((C cmd)->statusForOK(getTransaction().ltrim(key, start, end).get()), ProtocolCommand.LTRIM,
					args);
		}else{
			return execute((C cmd)->statusForOK(cmd.ltrim(key, start, end)), ProtocolCommand.LTRIM, args);
		}
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value).put("count", count);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().lrem(key, count, value).get(), ProtocolCommand.LREM, args);
		}else{
			return execute((C cmd)->cmd.lrem(key, count, value), ProtocolCommand.LREM, args);
		}
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().lrange(key, start, end).get(), ProtocolCommand.LRANGE, args);
		}else{
			return execute((C cmd)->cmd.lrange(key, start, end), ProtocolCommand.LRANGE, args);
		}
	}

	@Override
	public Long lLen(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().llen(key).get(), ProtocolCommand.LLEN, args);
		}else{
			return execute((C cmd)->cmd.llen(key), ProtocolCommand.LLEN, args);
		}
	}

}
