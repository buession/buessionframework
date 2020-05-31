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
import com.buession.redis.client.BinaryStringRedisOperations;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.BinaryJedisCommands;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class DefaultBinaryStringRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements BinaryStringRedisOperations {

	public DefaultBinaryStringRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->ReturnUtils.statusForOK(isTransaction() ? getTransaction().set(key, value).get() :
				jc.set(key, value)), ProtocolCommand.SET, arguments);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final StringCommands.SetArgument setArgument){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("setArgument", setArgument);

		return execute((C jc)->ReturnUtils.statusForOK(isTransaction() ? getTransaction().set(key, value,
				JedisClientUtils.setArgumentConvert(setArgument)).get() : jc.set(key, value,
				JedisClientUtils.setArgumentConvert(setArgument))), ProtocolCommand.SET, arguments);
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("lifetime", lifetime);

		return execute((C jc)->ReturnUtils.statusForOK(isTransaction() ?
				getTransaction().setex(key, lifetime, value).get() : jc.setex(key, lifetime, value)),
				ProtocolCommand.SETEX, arguments);
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("lifetime", lifetime);

		return execute((C jc)->ReturnUtils.statusForOK(isTransaction() ?
				getTransaction().psetex(key, lifetime, value).get() : jc.psetex(key, lifetime, value)),
				ProtocolCommand.PSETEX, arguments);
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->ReturnUtils.statusForBool(isTransaction() ?
				getTransaction().setnx(key, value).get() > 0 : jc.setnx(key, value) > 0), ProtocolCommand.SETNX,
				arguments);
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().append(key, value).get() : jc.append(key, value),
				ProtocolCommand.APPEND, arguments);
	}

	@Override
	public byte[] get(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().get(key).get() : jc.get(key), ProtocolCommand.GET,
				arguments);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().getSet(key, value).get() : jc.getSet(key, value),
				ProtocolCommand.GETSET, arguments);
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<C, List<byte[]>>() {

			@Override
			public List<byte[]> execute(final C jc){
				if(isTransaction()){
					return getTransaction().mget(keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).mget(keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.MGET);
					}
				}
			}
		}, ProtocolCommand.MGET, arguments);
	}

	@Override
	public Long incr(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().incr(key).get() : jc.incr(key), ProtocolCommand.INCR
				, arguments);
	}

	@Override
	public Long incrBy(final byte[] key, final int value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().incrBy(key, value).get() : jc.incrBy(key, value),
				ProtocolCommand.INCRBY, arguments);
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().incrBy(key, value).get() : jc.incrBy(key, value),
				ProtocolCommand.INCRBY, arguments);
	}

	@Override
	public Double incrByFloat(final byte[] key, final float value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().incrByFloat(key, value).get() : jc.incrByFloat(key,
				value), ProtocolCommand.INCRBYFLOAT, arguments);
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().incrByFloat(key, value).get() : jc.incrByFloat(key,
				value), ProtocolCommand.INCRBYFLOAT, arguments);
	}

	@Override
	public Long decr(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().decr(key).get() : jc.decr(key), ProtocolCommand.DECR
				, arguments);
	}

	@Override
	public Long decrBy(final byte[] key, final int value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().decrBy(key, value).get() : jc.decrBy(key, value),
				ProtocolCommand.DECRBY, arguments);
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().decrBy(key, value).get() : jc.decrBy(key, value),
				ProtocolCommand.DECRBY, arguments);
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"offset", offset).put("value", value);

		return execute((C jc)->isTransaction() ? getTransaction().setrange(key, offset, value).get() : jc.setrange(key
				, offset, value), ProtocolCommand.SETRANGE, arguments);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		return execute((C jc)->isTransaction() ? getTransaction().getrange(key, start, end).get() : jc.getrange(key,
				start, end), ProtocolCommand.GETRANGE, arguments);
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		return execute((C jc)->isTransaction() ? SafeEncoder.encode(getTransaction().substr(key, start, end).get()) :
				jc.substr(key, start, end), ProtocolCommand.SUBSTR, arguments);
	}

	@Override
	public Long strlen(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().strlen(key).get() : jc.strlen(key),
				ProtocolCommand.STRLEN, arguments);
	}

}
