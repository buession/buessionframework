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
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.utils.ReturnUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class DefaultListRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisListRedisOperations {

	public DefaultListRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Long lPush(final String key, final String... values){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"values", values);

		if(isTransaction()){
			return execute((C jc)->getTransaction().lpush(key, values).get(), ProtocolCommand.LPUSH, arguments);
		}else{
			return execute((C jc)->jc.lpush(key, values), ProtocolCommand.LPUSH, arguments);
		}
	}

	@Override
	public Long lPushX(final String key, final String... values){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"values", values);

		if(isTransaction()){
			return execute((C jc)->getTransaction().lpushx(key, values).get(), ProtocolCommand.LPUSHX, arguments);
		}else{
			return execute((C jc)->jc.lpushx(key, values), ProtocolCommand.LPUSHX, arguments);
		}
	}

	@Override
	public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"position", position).put("pivot", pivot).put("value", value);

		if(isTransaction()){
			return execute((C jc)->jc.linsert(key, JedisClientUtils.listPositionConvert(position), pivot, value),
					ProtocolCommand.LINSERT, arguments);
		}else{
			return execute((C jc)->jc.linsert(key, JedisClientUtils.listPositionConvert(position), pivot, value),
					ProtocolCommand.LINSERT, arguments);
		}
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"index", index).put("value", value);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForOK(getTransaction().lset(key, index, value).get()),
					ProtocolCommand.LSET, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForOK(jc.lset(key, index, value)), ProtocolCommand.LSET,
					arguments);
		}
	}

	@Override
	public String lIndex(final String key, final long index){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"index", index);

		if(isTransaction()){
			return execute((C jc)->getTransaction().lindex(key, index).get(), ProtocolCommand.LINDEX, arguments);
		}else{
			return execute((C jc)->jc.lindex(key, index), ProtocolCommand.LINDEX, arguments);
		}
	}

	@Override
	public String lPop(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().lpop(key).get(), ProtocolCommand.LPOP, arguments);
		}else{
			return execute((C jc)->jc.lpop(key), ProtocolCommand.LPOP, arguments);
		}
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("keys", keys).put(
				"timeout", timeout);

		return execute(new Executor<C, List<String>>() {

			@Override
			public List<String> execute(C jc){
				if(isTransaction()){
					return getTransaction().blpop(timeout, keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).blpop(timeout, keys);
					}else{
						return jc.blpop(timeout, keys[0]);
					}
				}
			}

		}, ProtocolCommand.BLPOP, arguments);
	}

	@Override
	public String rPop(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().rpop(key).get(), ProtocolCommand.RPOP, arguments);
		}else{
			return execute((C jc)->jc.rpop(key), ProtocolCommand.RPOP, arguments);
		}
	}

	@Override
	public String rPoplPush(final String source, final String destKey){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("source", source).put("destKey", destKey);

		return execute(new Executor<C, String>() {

			@Override
			public String execute(C jc){
				if(isTransaction()){
					return getTransaction().rpoplpush(source, destKey).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).rpoplpush(source, destKey);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.RPOPLPUSH);
					}
				}
			}

		}, ProtocolCommand.RPOPLPUSH, arguments);
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("keys", keys).put(
				"timeout", timeout);

		return execute(new Executor<C, List<String>>() {

			@Override
			public List<String> execute(C jc){
				if(isTransaction()){
					return getTransaction().brpop(timeout, keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).brpop(timeout, keys);
					}else{
						return Validate.isEmpty(keys) ? null : jc.brpop(timeout, keys[0]);
					}
				}
			}

		}, ProtocolCommand.BRPOP, arguments);
	}

	@Override
	public String brPoplPush(final String source, final String destKey, final int timeout){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("source", source).put("destKey", destKey).put("timeout",
						timeout);

		return execute(new Executor<C, String>() {

			@Override
			public String execute(C jc){
				if(isTransaction()){
					return getTransaction().brpoplpush(source, destKey, timeout).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).brpoplpush(source, destKey, timeout);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.BRPOPLPUSH);
					}
				}
			}

		}, ProtocolCommand.BRPOPLPUSH, arguments);
	}

	@Override
	public Long rPush(final String key, final String... values){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"values", values);

		if(isTransaction()){
			return execute((C jc)->getTransaction().rpush(key, values).get(), ProtocolCommand.RPUSH, arguments);
		}else{
			return execute((C jc)->jc.rpush(key, values), ProtocolCommand.RPUSH, arguments);
		}
	}

	@Override
	public Long rPushX(final String key, final String... values){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"values", values);

		if(isTransaction()){
			return execute((C jc)->getTransaction().rpushx(key, values).get(), ProtocolCommand.RPUSHX, arguments);
		}else{
			return execute((C jc)->jc.rpushx(key, values), ProtocolCommand.RPUSHX, arguments);
		}
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForOK(getTransaction().ltrim(key, start, end).get()),
					ProtocolCommand.LTRIM, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForOK(jc.ltrim(key, start, end)), ProtocolCommand.LTRIM,
					arguments);
		}
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"value", value).put("count", count);

		if(isTransaction()){
			return execute((C jc)->getTransaction().lrem(key, count, value).get(), ProtocolCommand.LREM, arguments);
		}else{
			return execute((C jc)->jc.lrem(key, count, value), ProtocolCommand.LREM, arguments);
		}
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		if(isTransaction()){
			return execute((C jc)->getTransaction().lrange(key, start, end).get(), ProtocolCommand.LRANGE, arguments);
		}else{
			return execute((C jc)->jc.lrange(key, start, end), ProtocolCommand.LRANGE, arguments);
		}
	}

	@Override
	public Long lLen(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().llen(key).get(), ProtocolCommand.LLEN, arguments);
		}else{
			return execute((C jc)->jc.llen(key), ProtocolCommand.LLEN, arguments);
		}
	}

}
