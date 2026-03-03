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
import com.buession.redis.core.DelExType;
import com.buession.redis.core.LcsResult;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.LcsArgument;
import com.buession.redis.core.command.args.MSetExArgument;
import com.buession.redis.core.command.args.SetArgument;
import com.buession.redis.core.internal.convert.jedis.params.GetExArgumentGetExParamsConverter;
import com.buession.redis.core.internal.convert.jedis.params.LcsArgumentConveter;
import com.buession.redis.core.internal.convert.jedis.params.MSetExArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.SetArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.response.LCSMatchResultConveter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import redis.clients.jedis.params.LCSParams;
import redis.clients.jedis.util.CompareCondition;

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
		return executeCommand(Command.APPEND, args, (cmd)->cmd.append(rawKey(key), value), (v)->v);
	}

	@Override
	public Long append(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.APPEND, args, (cmd)->cmd.append(rawKey(key), value), (v)->v);
	}

	@Override
	public Long decr(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DECR, args, (cmd)->cmd.decr(rawKey(key)), (v)->v);
	}

	@Override
	public Long decr(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DECR, args, (cmd)->cmd.decr(rawKey(key)), (v)->v);
	}

	@Override
	public Long decrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.DECRBY, args, (cmd)->cmd.decrBy(rawKey(key), value), (v)->v);
	}

	@Override
	public Long decrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.DECRBY, args, (cmd)->cmd.decrBy(rawKey(key), value), (v)->v);
	}

	@Override
	public Status delEx(final String key, final DelExType type, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(type, value);

		if(type == DelExType.IFEQ){
			return delEx(rawKey(key), CompareCondition.valueEq(value), args);
		}else if(type == DelExType.IFNE){
			return delEx(rawKey(key), CompareCondition.valueNe(value), args);
		}else if(type == DelExType.IFDEQ){
			return delEx(rawKey(key), CompareCondition.digestEq(value), args);
		}else if(type == DelExType.IFDNE){
			return delEx(rawKey(key), CompareCondition.digestNe(value), args);
		}else{
			return executeCommand(Command.DELEX, args);
		}
	}

	@Override
	public Status delEx(final byte[] key, final DelExType type, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(type, value);

		if(type == DelExType.IFEQ){
			return delEx(rawKey(key), CompareCondition.valueEq(value), args);
		}else if(type == DelExType.IFNE){
			return delEx(rawKey(key), CompareCondition.valueNe(value), args);
		}else if(type == DelExType.IFDEQ){
			return delEx(rawKey(key), CompareCondition.digestEq(value), args);
		}else if(type == DelExType.IFDNE){
			return delEx(rawKey(key), CompareCondition.digestNe(value), args);
		}else{
			return executeCommand(Command.DELEX, args);
		}
	}

	@Override
	public String digest(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DIGEST, args);
	}

	@Override
	public byte[] digest(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DIGEST, args);
	}

	@Override
	public String get(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GET, args, (cmd)->cmd.get(rawKey(key)), (v)->v);
	}

	@Override
	public byte[] get(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GET, args, (cmd)->cmd.get(rawKey(key)), (v)->v);
	}

	@Override
	public String getDel(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GETDEL, args, (cmd)->cmd.getDel(rawKey(key)), (v)->v);
	}

	@Override
	public byte[] getDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GETDEL, args, (cmd)->cmd.getDel(rawKey(key)), (v)->v);
	}

	@Override
	public String getEx(final String key, final GetExArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final GetExArgumentGetExParamsConverter getExArgumentGetExParamsConverter = new GetExArgumentGetExParamsConverter();
		return executeCommand(Command.GETEX, args,
				(cmd)->cmd.getEx(rawKey(key), getExArgumentGetExParamsConverter.convert(argument)), (v)->v);
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final GetExArgumentGetExParamsConverter getExArgumentGetExParamsConverter = new GetExArgumentGetExParamsConverter();
		return executeCommand(Command.GETEX, args,
				(cmd)->cmd.getEx(rawKey(key), getExArgumentGetExParamsConverter.convert(argument)), (v)->v);
	}

	@Override
	public String getRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.GETRANGE, args, (cmd)->cmd.getrange(rawKey(key), start, end), (v)->v);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.GETRANGE, args, (cmd)->cmd.getrange(rawKey(key), start, end), (v)->v);
	}

	@Override
	public String getSet(final String key, final String value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.GETSET, args, (cmd)->cmd.getSet(rawKey(key), value), (v)->v);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.GETSET, args, (cmd)->cmd.getSet(rawKey(key), value), (v)->v);
	}

	@Override
	public Long incr(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.INCR, args, (cmd)->cmd.incr(rawKey(key)), (v)->v);
	}

	@Override
	public Long incr(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.INCR, args, (cmd)->cmd.incr(rawKey(key)), (v)->v);
	}

	@Override
	public Long incrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.INCRBY, args, (cmd)->cmd.incrBy(rawKey(key), value), (v)->v);
	}

	@Override
	public Long incrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.INCRBY, args, (cmd)->cmd.incrBy(rawKey(key), value), (v)->v);
	}

	@Override
	public Double incrByFloat(final String key, final double value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.INCRBYFLOAT, args, (cmd)->cmd.incrByFloat(rawKey(key), value), (v)->v);
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.INCRBYFLOAT, args, (cmd)->cmd.incrByFloat(rawKey(key), value), (v)->v);
	}

	@Override
	public LcsResult lcs(final String key1, final String key2) {
		final CommandArguments args = CommandArguments.create(key1).add(key2);
		return lcs(key1, key2, new LCSParams(), args);
	}

	@Override
	public LcsResult lcs(final byte[] key1, final byte[] key2) {
		final CommandArguments args = CommandArguments.create(key1).add(key2);
		return lcs(key1, key2, new LCSParams(), args);
	}

	@Override
	public LcsResult lcs(final String key1, final String key2, final LcsArgument argument) {
		final CommandArguments args = CommandArguments.create(key1).add(key2).add(argument);
		final LcsArgumentConveter lcsArgumentConveter = new LcsArgumentConveter();
		return lcs(key1, key2, lcsArgumentConveter.convert(argument), args);
	}

	@Override
	public LcsResult lcs(final byte[] key1, final byte[] key2, final LcsArgument argument) {
		final CommandArguments args = CommandArguments.create(key1).add(key2).add(argument);
		final LcsArgumentConveter lcsArgumentConveter = new LcsArgumentConveter();
		return lcs(key1, key2, lcsArgumentConveter.convert(argument), args);
	}

	@Override
	public List<String> mGet(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.MGET, args, (cmd)->cmd.mget(rawKeys(keys)), (v)->v);
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.MGET, args, (cmd)->cmd.mget(rawKeys(keys)), (v)->v);
	}

	@Override
	public Status mSet(final KeyValue<String, String>... values) {
		final CommandArguments args = CommandArguments.create(values);
		return executeCommand(Command.MSET, args, (cmd)->cmd.mset(buildSetValues(values)), new OkStatusConverter());
	}

	@Override
	public Status mSetEx(final KeyValue<String, String>[] values, final MSetExArgument argument) {
		final CommandArguments args = CommandArguments.create(values).add(argument);
		final MSetExArgumentConverter mSetExArgumentConverter = new MSetExArgumentConverter();

		return executeCommand(Command.MSETEX, args,
				(cmd)->cmd.msetex(mSetExArgumentConverter.convert(argument), buildSetValues(values)),
				new BooleanStatusConverter());
	}

	@Override
	public Status mSetNx(final KeyValue<String, String>... values) {
		final CommandArguments args = CommandArguments.create(values);
		return executeCommand(Command.MSETNX, args, (cmd)->cmd.msetnx(buildSetValues(values)),
				new OneStatusConverter());
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create().add(key, value).add(lifetime);
		return executeCommand(Command.PSETEX, args, (cmd)->cmd.psetex(rawKey(key), lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create().add(key, value).add(lifetime);
		return executeCommand(Command.PSETEX, args, (cmd)->cmd.psetex(rawKey(key), lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status set(final String key, final String value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.SET, args, (cmd)->cmd.set(rawKey(key), value), new OkStatusConverter());
	}

	@Override
	public Status set(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.SET, args, (cmd)->cmd.set(rawKey(key), value), new OkStatusConverter());
	}

	@Override
	public Status set(final String key, final String value, final SetArgument argument) {
		final CommandArguments args = CommandArguments.create().add(key, value).add(argument);
		final SetArgumentConverter setArgumentConverter = new SetArgumentConverter();
		return executeCommand(Command.SET, args,
				(cmd)->cmd.set(rawKey(key), value, setArgumentConverter.convert(argument)),
				new OkStatusConverter());
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument argument) {
		final CommandArguments args = CommandArguments.create().add(key, value).add(argument);
		final SetArgumentConverter setArgumentConverter = new SetArgumentConverter();
		return executeCommand(Command.SET, args,
				(cmd)->cmd.set(rawKey(key), value, setArgumentConverter.convert(argument)),
				new OkStatusConverter());
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create().add(key, value).add(lifetime);
		return executeCommand(Command.SETEX, args, (cmd)->cmd.setex(rawKey(key), lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create().add(key, value).add(lifetime);
		return executeCommand(Command.SETEX, args, (cmd)->cmd.setex(rawKey(key), lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status setNx(final String key, final String value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.SETNX, args, (cmd)->cmd.setnx(rawKey(key), value),
				new OneStatusConverter());
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create().add(key, value);
		return executeCommand(Command.SETNX, args, (cmd)->cmd.setnx(rawKey(key), value),
				new OneStatusConverter());
	}

	@Override
	public Long setRange(final String key, final long offset, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(Command.SETRANGE, args, (cmd)->cmd.setrange(rawKey(key), offset, value), (v)->v);
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(Command.SETRANGE, args, (cmd)->cmd.setrange(rawKey(key), offset, value), (v)->v);
	}

	@Override
	public Long strlen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.STRLEN, args, (cmd)->cmd.strlen(rawKey(key)), (v)->v);
	}

	@Override
	public Long strlen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.STRLEN, args, (cmd)->cmd.strlen(rawKey(key)), (v)->v);
	}

	@Override
	public String substr(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.SUBSTR, args, (cmd)->cmd.substr(rawKey(key), (int) start, (int) end), (v)->v);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.SUBSTR, args, (cmd)->cmd.substr(rawKey(key), (int) start, (int) end), (v)->v);
	}

	private Status delEx(final String key, final CompareCondition condition, final CommandArguments args) {
		return executeCommand(Command.DELEX, args, (cmd)->cmd.delex(rawKey(key), condition), new OneStatusConverter());
	}

	private Status delEx(final byte[] key, final CompareCondition condition, final CommandArguments args) {
		return executeCommand(Command.DELEX, args, (cmd)->cmd.delex(rawKey(key), condition), new OneStatusConverter());
	}

	private LcsResult lcs(final String key1, final String key2, final LCSParams lcsParams,
						  final CommandArguments args) {
		return executeCommand(Command.LCS, args, (cmd)->cmd.lcs(rawKey(key1), rawKey(key2), lcsParams),
				new LCSMatchResultConveter());
	}

	private LcsResult lcs(final byte[] key1, final byte[] key2, final LCSParams lcsParams,
						  final CommandArguments args) {
		return executeCommand(Command.LCS, args, (cmd)->cmd.lcs(rawKey(key1), rawKey(key2), lcsParams),
				new LCSMatchResultConveter());
	}

	private String[] buildSetValues(final KeyValue<String, String>... values) {
		final String[] keysValues = new String[values.length * 2];
		int i = 0;

		for(KeyValue<String, String> kv : values){
			keysValues[i++] = rawKey(kv.getKey());
			keysValues[i++] = kv.getValue();
		}

		return keysValues;
	}

}
