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
import com.buession.core.utils.ListUtils;
import com.buession.lang.Status;
import com.buession.redis.client.StringRedisOperations;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.utils.ReturnUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class DefaultStringRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements StringRedisOperations {

	public DefaultStringRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status set(final String key, final String value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->ReturnUtils.statusForOK(isTransaction() ? getTransaction().set(key, value).get() :
				jc.set(key, value)), ProtocolCommand.SET, arguments);
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("setArgument", setArgument);

		return execute((C jc)->ReturnUtils.statusForOK(isTransaction() ? getTransaction().set(key, value,
				JedisClientUtils.setArgumentConvert(setArgument)).get() : jc.set(key, value,
				JedisClientUtils.setArgumentConvert(setArgument))), ProtocolCommand.SET, arguments);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("lifetime", lifetime);

		return execute((C jc)->ReturnUtils.statusForOK(isTransaction() ?
				getTransaction().setex(key, lifetime, value).get() : jc.setex(key, lifetime, value)),
				ProtocolCommand.SETEX, arguments);
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("lifetime", lifetime);

		return execute((C jc)->ReturnUtils.statusForOK(isTransaction() ?
				getTransaction().psetex(key, lifetime, value).get() : jc.psetex(key, lifetime, value)),
				ProtocolCommand.PSETEX, arguments);
	}

	@Override
	public Status setNx(final String key, final String value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->ReturnUtils.statusForBool(isTransaction() ?
				getTransaction().setnx(key, value).get() > 0 : jc.setnx(key, value) > 0), ProtocolCommand.SETNX,
				arguments);
	}

	@Override
	public Long append(final String key, final String value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().append(key, value).get() : jc.append(key, value),
				ProtocolCommand.APPEND, arguments);
	}

	@Override
	public String get(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().get(key).get() : jc.get(key), ProtocolCommand.GET,
				arguments);
	}

	@Override
	public String getSet(final String key, final String value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().getSet(key, value).get() : jc.getSet(key, value),
				ProtocolCommand.GETSET, arguments);
	}

	@Override
	public Status mSet(final Map<String, String> values){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("values", values);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(final C jc){
				List<String> temp = new ArrayList<>(values.size() * 2);
				String ret;

				values.forEach((key, value)->{
					temp.add(key);
					temp.add(value);
				});

				if(isTransaction()){
					ret = getTransaction().mset(ListUtils.toArray(temp, new String[]{})).get();
				}else{
					if(jc instanceof Jedis){
						ret = ((Jedis) jc).mset(ListUtils.toArray(temp, new String[]{}));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.MSET);
					}
				}

				return ReturnUtils.statusForOK(ret);
			}

		}, ProtocolCommand.MSET, arguments);
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("values", values);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(final C jc){
				List<String> temp = new ArrayList<>(values.size() * 2);
				Long ret;

				values.forEach((key, value)->{
					temp.add(key);
					temp.add(value);
				});

				if(isTransaction()){
					ret = getTransaction().msetnx(ListUtils.toArray(temp, new String[]{})).get();
				}else{
					if(jc instanceof Jedis){
						ret = ((Jedis) jc).msetnx(ListUtils.toArray(temp, new String[]{}));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.MSETNX);
					}
				}

				return ReturnUtils.statusForBool(ret > 0);
			}

		}, ProtocolCommand.MSETNX, arguments);
	}

	@Override
	public List<String> mGet(final String... keys){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<C, List<String>>() {

			@Override
			public List<String> execute(final C jc){
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
	public Long incr(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().incr(key).get() : jc.incr(key), ProtocolCommand.INCR
				, arguments);
	}

	@Override
	public Long incrBy(final String key, final int value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().incrBy(key, value).get() : jc.incrBy(key, value),
				ProtocolCommand.INCRBY, arguments);
	}

	@Override
	public Long incrBy(final String key, final long value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().incrBy(key, value).get() : jc.incrBy(key, value),
				ProtocolCommand.INCRBY, arguments);
	}

	@Override
	public Double incrByFloat(final String key, final float value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().incrByFloat(key, value).get() : jc.incrByFloat(key,
				value), ProtocolCommand.INCRBYFLOAT, arguments);
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().incrByFloat(key, value).get() : jc.incrByFloat(key,
				value), ProtocolCommand.INCRBYFLOAT, arguments);
	}

	@Override
	public Long decr(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().decr(key).get() : jc.decr(key), ProtocolCommand.DECR
				, arguments);
	}

	@Override
	public Long decrBy(final String key, final int value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().decrBy(key, value).get() : jc.decrBy(key, value),
				ProtocolCommand.DECRBY, arguments);
	}

	@Override
	public Long decrBy(final String key, final long value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value);

		return execute((C jc)->isTransaction() ? getTransaction().decrBy(key, value).get() : jc.decrBy(key, value),
				ProtocolCommand.DECRBY, arguments);
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"offset", offset).put("value", value);

		return execute((C jc)->isTransaction() ? getTransaction().setrange(key, offset, value).get() : jc.setrange(key
				, offset, value), ProtocolCommand.SETRANGE, arguments);
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		return execute((C jc)->isTransaction() ? getTransaction().getrange(key, start, end).get() : jc.getrange(key,
				start, end), ProtocolCommand.GETRANGE, arguments);
	}

	@Override
	public String substr(final String key, final int start, final int end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		return execute((C jc)->isTransaction() ? getTransaction().substr(key, start, end).get() : jc.substr(key, start
				, end), ProtocolCommand.SUBSTR, arguments);
	}

	@Override
	public Long strlen(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().strlen(key).get() : jc.strlen(key),
				ProtocolCommand.STRLEN, arguments);
	}

}
