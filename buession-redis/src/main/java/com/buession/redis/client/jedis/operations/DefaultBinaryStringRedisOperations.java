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
public class DefaultBinaryStringRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinaryStringRedisOperations {

	public DefaultBinaryStringRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForOK(getTransaction().set(key, value).get()),
					ProtocolCommand.SET, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForOK(jc.set(key, value)), ProtocolCommand.SET, arguments);
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final StringCommands.SetArgument setArgument){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("setArgument", setArgument);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForOK(getTransaction().set(key, value,
					JedisClientUtils.setArgumentConvert(setArgument)).get()), ProtocolCommand.SET, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForOK(jc.set(key, value,
					JedisClientUtils.setArgumentConvert(setArgument))), ProtocolCommand.SET, arguments);
		}
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("lifetime", lifetime);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForOK(getTransaction().setex(key, lifetime, value).get()),
					ProtocolCommand.SETEX, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForOK(jc.setex(key, lifetime, value)), ProtocolCommand.SETEX,
					arguments);
		}
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("lifetime", lifetime);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForOK(getTransaction().psetex(key, lifetime, value).get()),
					ProtocolCommand.PSETEX, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForOK(jc.psetex(key, lifetime, value)), ProtocolCommand.PSETEX,
					arguments);
		}
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().setnx(key, value).get() > 0),
					ProtocolCommand.SETNX, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.setnx(key, value) > 0), ProtocolCommand.SETNX,
					arguments);
		}
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		if(isTransaction()){
			return execute((C jc)->getTransaction().append(key, value).get(), ProtocolCommand.APPEND, arguments);
		}else{
			return execute((C jc)->jc.append(key, value), ProtocolCommand.APPEND, arguments);
		}
	}

	@Override
	public byte[] get(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().get(key).get(), ProtocolCommand.GET, arguments);
		}else{
			return execute((C jc)->jc.get(key), ProtocolCommand.GET, arguments);
		}
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		if(isTransaction()){
			return execute((C jc)->getTransaction().getSet(key, value).get(), ProtocolCommand.GETSET, arguments);
		}else{
			return execute((C jc)->jc.getSet(key, value), ProtocolCommand.GETSET, arguments);
		}
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

		if(isTransaction()){
			return execute((C jc)->getTransaction().incr(key).get(), ProtocolCommand.INCR, arguments);
		}else{
			return execute((C jc)->jc.incr(key), ProtocolCommand.INCR, arguments);
		}
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		if(isTransaction()){
			return execute((C jc)->getTransaction().incrBy(key, value).get(), ProtocolCommand.INCRBY, arguments);
		}else{
			return execute((C jc)->jc.incrBy(key, value), ProtocolCommand.INCRBY, arguments);
		}
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		if(isTransaction()){
			return execute((C jc)->getTransaction().incrByFloat(key, value).get(), ProtocolCommand.INCRBYFLOAT,
					arguments);
		}else{
			return execute((C jc)->jc.incrByFloat(key, value), ProtocolCommand.INCRBYFLOAT, arguments);
		}
	}

	@Override
	public Long decr(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().decr(key).get(), ProtocolCommand.DECR, arguments);
		}else{
			return execute((C jc)->jc.decr(key), ProtocolCommand.DECR, arguments);
		}
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		if(isTransaction()){
			return execute((C jc)->getTransaction().decrBy(key, value).get(), ProtocolCommand.DECRBY, arguments);
		}else{
			return execute((C jc)->jc.decrBy(key, value), ProtocolCommand.DECRBY, arguments);
		}
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"offset", offset).put("value", value);

		if(isTransaction()){
			return execute((C jc)->getTransaction().setrange(key, offset, value).get(), ProtocolCommand.SETRANGE,
					arguments);
		}else{
			return execute((C jc)->jc.setrange(key, offset, value), ProtocolCommand.SETRANGE, arguments);
		}
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		if(isTransaction()){
			return execute((C jc)->getTransaction().getrange(key, start, end).get(), ProtocolCommand.GETRANGE,
					arguments);
		}else{
			return execute((C jc)->jc.getrange(key, start, end), ProtocolCommand.GETRANGE, arguments);
		}
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		if(isTransaction()){
			return execute((C jc)->SafeEncoder.encode(getTransaction().substr(key, start, end).get()),
					ProtocolCommand.SUBSTR, arguments);
		}else{
			return execute((C jc)->jc.substr(key, start, end), ProtocolCommand.SUBSTR, arguments);
		}
	}

	@Override
	public Long strlen(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().strlen(key).get(), ProtocolCommand.STRLEN, arguments);
		}else{
			return execute((C jc)->jc.strlen(key), ProtocolCommand.STRLEN, arguments);
		}
	}

}
