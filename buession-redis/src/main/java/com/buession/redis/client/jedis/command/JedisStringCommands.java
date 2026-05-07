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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.command;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.args.GetExType;
import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.core.command.args.PxExType;
import com.buession.redis.core.command.args.string.CompareCondition;
import com.buession.redis.core.LcsResult;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.command.args.string.LcsArgument;
import com.buession.redis.core.command.args.string.SetType;
import com.buession.redis.core.internal.convert.jedis.response.LCSMatchResultConveter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.jedis.args.JedisGetExParams;
import com.buession.redis.core.internal.jedis.args.JedisLCSParams;
import com.buession.redis.core.internal.jedis.args.JedisMSetExParams;
import com.buession.redis.core.internal.jedis.args.JedisSetParams;
import redis.clients.jedis.params.GetExParams;
import redis.clients.jedis.params.LCSParams;
import redis.clients.jedis.params.MSetExParams;
import redis.clients.jedis.params.SetParams;

import java.util.List;

/**
 * Jedis 字符串命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisStringCommands extends AbstractJedisRedisCommands implements StringCommands {

	public JedisStringCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Long append(final String key, final String value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(RedisCommand.APPEND, args, (cmd)->cmd.append(key, value),
				(cmd)->cmd.append(key, value), (cmd)->cmd.append(key, value));
	}

	@Override
	public Long append(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(RedisCommand.APPEND, args, (cmd)->cmd.append(key, value),
				(cmd)->cmd.append(key, value), (cmd)->cmd.append(key, value));
	}

	@Override
	public Long decr(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.DECR, args, (cmd)->cmd.decr(key), (cmd)->cmd.decr(key),
				(cmd)->cmd.decr(key));
	}

	@Override
	public Long decr(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.DECR, args, (cmd)->cmd.decr(key), (cmd)->cmd.decr(key),
				(cmd)->cmd.decr(key));
	}

	@Override
	public Long decrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(RedisCommand.DECRBY, args, (cmd)->cmd.decrBy(key, value),
				(cmd)->cmd.decrBy(key, value), (cmd)->cmd.decrBy(key, value));
	}

	@Override
	public Long decrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(RedisCommand.DECRBY, args, (cmd)->cmd.decrBy(key, value),
				(cmd)->cmd.decrBy(key, value), (cmd)->cmd.decrBy(key, value));
	}

	@Override
	public Status delEx(final String key, final CompareCondition type, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(type, value);

		if(type == CompareCondition.IFEQ){
			return delEx(key, redis.clients.jedis.util.CompareCondition.valueEq(value), args);
		}else if(type == CompareCondition.IFNE){
			return delEx(key, redis.clients.jedis.util.CompareCondition.valueNe(value), args);
		}else if(type == CompareCondition.IFDEQ){
			return delEx(key, redis.clients.jedis.util.CompareCondition.digestEq(value), args);
		}else if(type == CompareCondition.IFDNE){
			return delEx(key, redis.clients.jedis.util.CompareCondition.digestNe(value), args);
		}else{
			return executeCommand(RedisCommand.DELEX, args);
		}
	}

	@Override
	public Status delEx(final byte[] key, final CompareCondition type, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(type, value);

		if(type == CompareCondition.IFEQ){
			return delEx(key, redis.clients.jedis.util.CompareCondition.valueEq(value), args);
		}else if(type == CompareCondition.IFNE){
			return delEx(key, redis.clients.jedis.util.CompareCondition.valueNe(value), args);
		}else if(type == CompareCondition.IFDEQ){
			return delEx(key, redis.clients.jedis.util.CompareCondition.digestEq(value), args);
		}else if(type == CompareCondition.IFDNE){
			return delEx(key, redis.clients.jedis.util.CompareCondition.digestNe(value), args);
		}else{
			return executeCommand(RedisCommand.DELEX, args);
		}
	}

	@Override
	public String digest(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.DIGEST, args, (cmd)->cmd.digestKey(key),
				(cmd)->cmd.digestKey(key), (cmd)->cmd.digestKey(key));
	}

	@Override
	public byte[] digest(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.DIGEST, args, (cmd)->cmd.digestKey(key),
				(cmd)->cmd.digestKey(key), (cmd)->cmd.digestKey(key));
	}

	@Override
	public String get(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.GET, args, (cmd)->cmd.get(key), (cmd)->cmd.get(key),
				(cmd)->cmd.get(key));
	}

	@Override
	public byte[] get(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.GET, args, (cmd)->cmd.get(key), (cmd)->cmd.get(key),
				(cmd)->cmd.get(key));
	}

	@Override
	public String getDel(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.GETDEL, args, (cmd)->cmd.getDel(key), (cmd)->cmd.getDel(key),
				(cmd)->cmd.getDel(key));
	}

	@Override
	public byte[] getDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.GETDEL, args, (cmd)->cmd.getDel(key), (cmd)->cmd.getDel(key),
				(cmd)->cmd.getDel(key));
	}

	@Override
	public String getEx(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return getEx(key, new JedisGetExParams(), args);
	}

	@Override
	public byte[] getEx(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return getEx(key, new JedisGetExParams(), args);
	}

	@Override
	public String getEx(final String key, final GetExType exType, final long expires) {
		final CommandArguments args = CommandArguments.create(key).add(exType, expires);
		return getEx(key, new JedisGetExParams(exType, expires), args);
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExType exType, final long expires) {
		final CommandArguments args = CommandArguments.create(key).add(exType, expires);
		return getEx(key, new JedisGetExParams(exType, expires), args);
	}

	@Override
	public String getRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.GETRANGE, args, (cmd)->cmd.getrange(key, start, end),
				(cmd)->cmd.getrange(key, start, end), (cmd)->cmd.getrange(key, start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.GETRANGE, args, (cmd)->cmd.getrange(key, start, end),
				(cmd)->cmd.getrange(key, start, end), (cmd)->cmd.getrange(key, start, end));
	}

	@Override
	public String getSet(final String key, final String value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(RedisCommand.GETSET, args, (cmd)->cmd.getSet(key, value),
				(cmd)->cmd.getSet(key, value), (cmd)->cmd.getSet(key, value));
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(RedisCommand.GETSET, args, (cmd)->cmd.getSet(key, value),
				(cmd)->cmd.getSet(key, value), (cmd)->cmd.getSet(key, value));
	}

	@Override
	public Long incr(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.INCR, args, (cmd)->cmd.incr(key), (cmd)->cmd.incr(key),
				(cmd)->cmd.incr(key));
	}

	@Override
	public Long incr(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.INCR, args, (cmd)->cmd.incr(key), (cmd)->cmd.incr(key),
				(cmd)->cmd.incr(key));
	}

	@Override
	public Long incrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.INCRBY, args, (cmd)->cmd.incrBy(key, value),
				(cmd)->cmd.incrBy(key, value), (cmd)->cmd.incrBy(key, value));
	}

	@Override
	public Long incrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.INCRBY, args, (cmd)->cmd.incrBy(key, value),
				(cmd)->cmd.incrBy(key, value), (cmd)->cmd.incrBy(key, value));
	}

	@Override
	public Double incrByFloat(final String key, final double value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.INCRBYFLOAT, args, (cmd)->cmd.incrByFloat(key, value),
				(cmd)->cmd.incrByFloat(key, value), (cmd)->cmd.incrByFloat(key, value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.INCRBYFLOAT, args, (cmd)->cmd.incrByFloat(key, value),
				(cmd)->cmd.incrByFloat(key, value), (cmd)->cmd.incrByFloat(key, value));
	}

	@Override
	public LcsResult lcs(final String key1, final String key2) {
		final CommandArguments args = CommandArguments.create(key1, key2);
		return lcs(key1, key2, new JedisLCSParams(), args);
	}

	@Override
	public LcsResult lcs(final byte[] key1, final byte[] key2) {
		final CommandArguments args = CommandArguments.create(key1, key2);
		return lcs(key1, key2, new JedisLCSParams(), args);
	}

	@Override
	public LcsResult lcs(final String key1, final String key2, final LcsArgument argument) {
		final CommandArguments args = CommandArguments.create(key1, key2).add(argument);
		return lcs(key1, key2, new JedisLCSParams(argument), args);
	}

	@Override
	public LcsResult lcs(final byte[] key1, final byte[] key2, final LcsArgument argument) {
		final CommandArguments args = CommandArguments.create(key1, key2).add(argument);
		return lcs(key1, key2, new JedisLCSParams(argument), args);
	}

	@Override
	public List<String> mGet(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.MGET, args, (cmd)->cmd.mget(keys), (cmd)->cmd.mget(keys),
				(cmd)->cmd.mget(keys));
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.MGET, args, (cmd)->cmd.mget(keys), (cmd)->cmd.mget(keys),
				(cmd)->cmd.mget(keys));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSet(final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data);
		return executeCommand(RedisCommand.MSET, args, (cmd)->cmd.mset(buildSetValues(data)),
				(cmd)->cmd.mset(buildSetValues(data)), (cmd)->cmd.mset(buildSetValues(data)),
				new OkStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetEx(final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data);
		return executeCommand(RedisCommand.MSETEX, args,
				(cmd)->cmd.msetex(new JedisMSetExParams(), buildSetValues(data)),
				(cmd)->cmd.msetex(new JedisMSetExParams(), buildSetValues(data)),
				(cmd)->cmd.msetex(new JedisMSetExParams(), buildSetValues(data)), new BooleanStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetEx(final NxXx nxXx, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data).add(nxXx);
		return mSetEx(new JedisMSetExParams(nxXx), data, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetEx(final NxXx nxXx, final PxExType exType, final long expires,
	                     final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data).add(nxXx).add(exType, expires);
		return mSetEx(new JedisMSetExParams(nxXx, exType, expires), data, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetEx(final PxExType exType, final long expires, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data).add(exType, expires);
		return mSetEx(new JedisMSetExParams(exType, expires), data, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetNx(final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data);
		return executeCommand(RedisCommand.MSETNX, args, (cmd)->cmd.msetnx(buildSetValues(data)),
				(cmd)->cmd.msetnx(buildSetValues(data)), (cmd)->cmd.msetnx(buildSetValues(data)),
				new OneStatusConverter());
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key, value).add(lifetime);
		return executeCommand(RedisCommand.PSETEX, args, (cmd)->cmd.psetex(key, lifetime, value),
				(cmd)->cmd.psetex(key, lifetime, value), (cmd)->cmd.psetex(key, lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key, value).add(lifetime);
		return executeCommand(RedisCommand.PSETEX, args, (cmd)->cmd.psetex(key, lifetime, value),
				(cmd)->cmd.psetex(key, lifetime, value), (cmd)->cmd.psetex(key, lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status set(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.SET, args, (cmd)->cmd.set(key, value),
				(cmd)->cmd.set(key, value), (cmd)->cmd.set(key, value), new OkStatusConverter());
	}

	@Override
	public Status set(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.SET, args, (cmd)->cmd.set(key, value),
				(cmd)->cmd.set(key, value), (cmd)->cmd.set(key, value), new OkStatusConverter());
	}

	@Override
	public Status set(final String key, final String value, final SetType setType) {
		final CommandArguments args = CommandArguments.create(key, value).add(setType);
		return set(key, value, new JedisSetParams(setType), args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetType setType) {
		final CommandArguments args = CommandArguments.create(key, value).add(setType);
		return set(key, value, new JedisSetParams(setType), args);
	}

	@Override
	public Status set(final String key, final String value, final SetType setType, final PxExType pxExType,
	                  final long expires) {
		final CommandArguments args = CommandArguments.create(key, value).add(setType).add(pxExType, expires);
		return set(key, value, new JedisSetParams(setType, pxExType, expires), args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetType setType, final PxExType pxExType,
	                  final long expires) {
		final CommandArguments args = CommandArguments.create(key, value).add(setType).add(pxExType, expires);
		return set(key, value, new JedisSetParams(setType, pxExType, expires), args);
	}

	@Override
	public Status set(final String key, final String value, final PxExType pxExType, final long expires) {
		final CommandArguments args = CommandArguments.create(key, value).add(pxExType, expires);
		return set(key, value, new JedisSetParams(pxExType, expires), args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final PxExType pxExType, final long expires) {
		final CommandArguments args = CommandArguments.create(key, value).add(pxExType, expires);
		return set(key, value, new JedisSetParams(pxExType, expires), args);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key, value).add(lifetime);
		return executeCommand(RedisCommand.SETEX, args, (cmd)->cmd.setex(key, lifetime, value),
				(cmd)->cmd.setex(key, lifetime, value), (cmd)->cmd.setex(key, lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key, value).add(lifetime);
		return executeCommand(RedisCommand.SETEX, args, (cmd)->cmd.setex(key, lifetime, value),
				(cmd)->cmd.setex(key, lifetime, value), (cmd)->cmd.setex(key, lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status setNx(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.SETNX, args, (cmd)->cmd.setnx(key, value),
				(cmd)->cmd.setnx(key, value), (cmd)->cmd.setnx(key, value), new OneStatusConverter());
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.SETNX, args, (cmd)->cmd.setnx(key, value),
				(cmd)->cmd.setnx(key, value), (cmd)->cmd.setnx(key, value), new OneStatusConverter());
	}

	@Override
	public Long setRange(final String key, final long offset, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(RedisCommand.SETRANGE, args, (cmd)->cmd.setrange(key, offset, value),
				(cmd)->cmd.setrange(key, offset, value), (cmd)->cmd.setrange(key, offset, value));
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(RedisCommand.SETRANGE, args, (cmd)->cmd.setrange(key, offset, value),
				(cmd)->cmd.setrange(key, offset, value), (cmd)->cmd.setrange(key, offset, value));
	}

	@Override
	public Long strlen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.STRLEN, args, (cmd)->cmd.strlen(key), (cmd)->cmd.strlen(key),
				(cmd)->cmd.strlen(key));
	}

	@Override
	public Long strlen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.STRLEN, args, (cmd)->cmd.strlen(key), (cmd)->cmd.strlen(key),
				(cmd)->cmd.strlen(key));
	}

	@Override
	public String substr(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.SUBSTR, args, (cmd)->cmd.substr(key, (int) start, (int) end),
				(cmd)->cmd.substr(key, (int) start, (int) end), (cmd)->cmd.substr(key, (int) start, (int) end));
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.SUBSTR, args, (cmd)->cmd.substr(key, (int) start, (int) end),
				(cmd)->cmd.substr(key, (int) start, (int) end), (cmd)->cmd.substr(key, (int) start, (int) end));
	}

	private String getEx(final String key, final GetExParams getExParams, final CommandArguments args) {
		return executeCommand(RedisCommand.GETEX, args, (cmd)->cmd.getEx(key, getExParams),
				(cmd)->cmd.getEx(key, getExParams), (cmd)->cmd.getEx(key, getExParams));
	}

	private byte[] getEx(final byte[] key, final GetExParams getExParams, final CommandArguments args) {
		return executeCommand(RedisCommand.GETEX, args, (cmd)->cmd.getEx(key, getExParams),
				(cmd)->cmd.getEx(key, getExParams), (cmd)->cmd.getEx(key, getExParams));
	}

	private Status delEx(final String key, final redis.clients.jedis.util.CompareCondition condition,
	                     final CommandArguments args) {
		return executeCommand(RedisCommand.DELEX, args, (cmd)->cmd.delex(key, condition),
				(cmd)->cmd.delex(key, condition), (cmd)->cmd.delex(key, condition),
				new OneStatusConverter());
	}

	private Status delEx(final byte[] key, final redis.clients.jedis.util.CompareCondition condition,
	                     final CommandArguments args) {
		return executeCommand(RedisCommand.DELEX, args, (cmd)->cmd.delex(key, condition),
				(cmd)->cmd.delex(key, condition), (cmd)->cmd.delex(key, condition),
				new OneStatusConverter());
	}

	private LcsResult lcs(final String key1, final String key2, final LCSParams lcsParams,
	                      final CommandArguments args) {
		return executeCommand(RedisCommand.LCS, args, (cmd)->cmd.lcs(key1, key2, lcsParams),
				(cmd)->cmd.lcs(key1, key2, lcsParams), (cmd)->cmd.lcs(key1, key2, lcsParams),
				new LCSMatchResultConveter());
	}

	private LcsResult lcs(final byte[] key1, final byte[] key2, final LCSParams lcsParams,
	                      final CommandArguments args) {
		return executeCommand(RedisCommand.LCS, args, (cmd)->cmd.lcs(key1, key2, lcsParams),
				(cmd)->cmd.lcs(key1, key2, lcsParams), (cmd)->cmd.lcs(key1, key2, lcsParams),
				new LCSMatchResultConveter());
	}

	private Status mSetEx(final MSetExParams mSetExParams, final KeyValue<String, String>[] data,
	                      final CommandArguments args) {
		return executeCommand(RedisCommand.MSETEX, args, (cmd)->cmd.msetex(mSetExParams, buildSetValues(data)),
				(cmd)->cmd.msetex(mSetExParams, buildSetValues(data)),
				(cmd)->cmd.msetex(mSetExParams, buildSetValues(data)), new BooleanStatusConverter());
	}

	private Status set(final String key, final String value, final SetParams setParams, final CommandArguments args) {
		return executeCommand(RedisCommand.SET, args, (cmd)->cmd.set(key, value, setParams),
				(cmd)->cmd.set(key, value, setParams), (cmd)->cmd.set(key, value, setParams),
				new OkStatusConverter());
	}

	private Status set(final byte[] key, final byte[] value, final SetParams setParams, final CommandArguments args) {
		return executeCommand(RedisCommand.SET, args, (cmd)->cmd.set(key, value, setParams),
				(cmd)->cmd.set(key, value, setParams), (cmd)->cmd.set(key, value, setParams),
				new OkStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	private String[] buildSetValues(final KeyValue<String, String>... data) {
		if(data == null){
			return null;
		}else if(data.length == 0){
			return new String[0];
		}else{
			final String[] newData = new String[data.length * 2];
			int i = 0;

			for(KeyValue<String, String> kv : data){
				newData[i++] = kv.getKey();
				newData[i++] = kv.getValue();
			}

			return newData;
		}
	}

}
