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
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.BinaryJedisCommands;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class DefaultJedisBinaryStringRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinaryStringRedisOperations {

	public DefaultJedisBinaryStringRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((C cmd)->statusForOK(getTransaction().set(key, value).get()), ProtocolCommand.SET, args);
		}else{
			return execute((C cmd)->statusForOK(cmd.set(key, value)), ProtocolCommand.SET, args);
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final StringCommands.SetArgument setArgument){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value).put("setArgument", setArgument);

		if(isTransaction()){
			return execute((C cmd)->statusForOK(getTransaction().set(key, value,
					JedisClientUtils.setArgumentConvert(setArgument)).get()), ProtocolCommand.SET, args);
		}else{
			return execute((C cmd)->statusForOK(cmd.set(key, value, JedisClientUtils.setArgumentConvert(setArgument)))
					, ProtocolCommand.SET, args);
		}
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value).put("lifetime", lifetime);

		if(isTransaction()){
			return execute((C cmd)->statusForOK(getTransaction().setex(key, lifetime, value).get()),
					ProtocolCommand.SETEX, args);
		}else{
			return execute((C cmd)->statusForOK(cmd.setex(key, lifetime, value)), ProtocolCommand.SETEX, args);
		}
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value).put("lifetime", lifetime);

		if(isTransaction()){
			return execute((C cmd)->statusForOK(getTransaction().psetex(key, lifetime, value).get()),
					ProtocolCommand.PSETEX, args);
		}else{
			return execute((C cmd)->statusForOK(cmd.psetex(key, lifetime, value)), ProtocolCommand.PSETEX, args);
		}
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().setnx(key, value).get() > 0), ProtocolCommand.SETNX
					, args);
		}else{
			return execute((C cmd)->statusForBool(cmd.setnx(key, value) > 0), ProtocolCommand.SETNX, args);
		}
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().append(key, value).get(), ProtocolCommand.APPEND, args);
		}else{
			return execute((C cmd)->cmd.append(key, value), ProtocolCommand.APPEND, args);
		}
	}

	@Override
	public byte[] get(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().get(key).get(), ProtocolCommand.GET, args);
		}else{
			return execute((C cmd)->cmd.get(key), ProtocolCommand.GET, args);
		}
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().getSet(key, value).get(), ProtocolCommand.GETSET, args);
		}else{
			return execute((C cmd)->cmd.getSet(key, value), ProtocolCommand.GETSET, args);
		}
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<C, List<byte[]>>() {

			@Override
			public List<byte[]> execute(C cmd){
				if(isTransaction()){
					return getTransaction().mget(keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).mget(keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.MGET);
					}
				}
			}
		}, ProtocolCommand.MGET, args);
	}

	@Override
	public Long incr(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().incr(key).get(), ProtocolCommand.INCR, args);
		}else{
			return execute((C cmd)->cmd.incr(key), ProtocolCommand.INCR, args);
		}
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().incrBy(key, value).get(), ProtocolCommand.INCRBY, args);
		}else{
			return execute((C cmd)->cmd.incrBy(key, value), ProtocolCommand.INCRBY, args);
		}
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().incrByFloat(key, value).get(), ProtocolCommand.INCRBYFLOAT, args);
		}else{
			return execute((C cmd)->cmd.incrByFloat(key, value), ProtocolCommand.INCRBYFLOAT, args);
		}
	}

	@Override
	public Long decr(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().decr(key).get(), ProtocolCommand.DECR, args);
		}else{
			return execute((C cmd)->cmd.decr(key), ProtocolCommand.DECR, args);
		}
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().decrBy(key, value).get(), ProtocolCommand.DECRBY, args);
		}else{
			return execute((C cmd)->cmd.decrBy(key, value), ProtocolCommand.DECRBY, args);
		}
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("offset",
				offset).put("value", value);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().setrange(key, offset, value).get(), ProtocolCommand.SETRANGE,
					args);
		}else{
			return execute((C cmd)->cmd.setrange(key, offset, value), ProtocolCommand.SETRANGE, args);
		}
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().getrange(key, start, end).get(), ProtocolCommand.GETRANGE, args);
		}else{
			return execute((C cmd)->cmd.getrange(key, start, end), ProtocolCommand.GETRANGE, args);
		}
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((C cmd)->SafeEncoder.encode(getTransaction().substr(key, start, end).get()),
					ProtocolCommand.SUBSTR, args);
		}else{
			return execute((C cmd)->cmd.substr(key, start, end), ProtocolCommand.SUBSTR, args);
		}
	}

	@Override
	public Long strlen(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().strlen(key).get(), ProtocolCommand.STRLEN, args);
		}else{
			return execute((C cmd)->cmd.strlen(key), ProtocolCommand.STRLEN, args);
		}
	}

}
