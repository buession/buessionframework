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
package com.buession.redis.client.lettuce.command;

import com.buession.core.converter.ArrayKeyValueMapConverter;
import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
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
import com.buession.redis.core.internal.convert.lettuce.response.StringMatchResultConveter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceGetExArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceLcsArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceMSetExArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceSetArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.GetExArgs;
import io.lettuce.core.MSetExArgs;
import io.lettuce.core.SetArgs;
import io.lettuce.core.Value;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 字符串命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceStringCommands extends AbstractLettuceRedisCommands implements StringCommands {

	public LettuceStringCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Long append(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.APPEND, args,
				(cmd)->cmd.append(SafeEncoder.encode(key), SafeEncoder.encode(value)),
				(cmd)->cmd.append(SafeEncoder.encode(key), SafeEncoder.encode(value)));
	}

	@Override
	public Long append(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.APPEND, args, (cmd)->cmd.append(key, value),
				(cmd)->cmd.append(key, value));
	}

	@Override
	public Long decr(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.DECR, args, (cmd)->cmd.decr(SafeEncoder.encode(key)),
				(cmd)->cmd.decr(SafeEncoder.encode(key)));
	}

	@Override
	public Long decr(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.DECR, args, (cmd)->cmd.decr(key), (cmd)->cmd.decr(key));
	}

	@Override
	public Long decrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.DECRBY, args, (cmd)->cmd.decrby(SafeEncoder.encode(key), value),
				(cmd)->cmd.decrby(SafeEncoder.encode(key), value));
	}

	@Override
	public Long decrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.DECRBY, args, (cmd)->cmd.decrby(key, value),
				(cmd)->cmd.decrby(key, value));
	}

	@Override
	public Status delEx(final String key, final CompareCondition type, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(type).add(value);

		if(type == CompareCondition.IFEQ){
			return delEx(SafeEncoder.encode(key), io.lettuce.core.CompareCondition.valueEq(SafeEncoder.encode(value)),
					args);
		}else if(type == CompareCondition.IFNE){
			return delEx(SafeEncoder.encode(key), io.lettuce.core.CompareCondition.valueNe(SafeEncoder.encode(value)),
					args);
		}else if(type == CompareCondition.IFDEQ){
			return delEx(SafeEncoder.encode(key), io.lettuce.core.CompareCondition.digestEq(value), args);
		}else if(type == CompareCondition.IFDNE){
			return delEx(SafeEncoder.encode(key), io.lettuce.core.CompareCondition.digestNe(value), args);
		}else{
			return executeCommand(RedisCommand.DELEX, args);
		}
	}

	@Override
	public Status delEx(final byte[] key, final CompareCondition type, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(type).add(value);

		if(type == CompareCondition.IFEQ){
			return delEx(key, io.lettuce.core.CompareCondition.valueEq(value), args);
		}else if(type == CompareCondition.IFNE){
			return delEx(key, io.lettuce.core.CompareCondition.valueNe(value), args);
		}else if(type == CompareCondition.IFDEQ){
			return delEx(key, io.lettuce.core.CompareCondition.digestEq(SafeEncoder.encode(value)), args);
		}else if(type == CompareCondition.IFDNE){
			return delEx(key, io.lettuce.core.CompareCondition.digestNe(SafeEncoder.encode(value)), args);
		}else{
			return executeCommand(RedisCommand.DELEX, args);
		}
	}

	@Override
	public String digest(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.DIGEST, args, (cmd)->cmd.digest(key), null);
	}

	@Override
	public byte[] digest(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.DIGEST, args, (cmd)->cmd.digest(key), null, SafeEncoder::encode);
	}

	@Override
	public String get(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.GET, args, (cmd)->cmd.get(SafeEncoder.encode(key)),
				(cmd)->cmd.get(SafeEncoder.encode(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] get(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.GET, args, (cmd)->cmd.get(key), (cmd)->cmd.get(key));
	}

	@Override
	public String getDel(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.GETDEL, args, (cmd)->cmd.getdel(SafeEncoder.encode(key)),
				(cmd)->cmd.getdel(SafeEncoder.encode(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] getDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.GETDEL, args, (cmd)->cmd.getdel(key),
				(cmd)->cmd.getdel(key));
	}

	@Override
	public String getEx(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return getStringEx(SafeEncoder.encode(key), new LettuceGetExArgs(), args);
	}

	@Override
	public byte[] getEx(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return getBinaryEx(key, new LettuceGetExArgs(), args);
	}

	@Override
	public String getEx(final String key, final GetExType exType, final long expires) {
		final CommandArguments args = CommandArguments.create(key).add(exType).add(expires);
		return getStringEx(SafeEncoder.encode(key), new LettuceGetExArgs(exType, expires), args);
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExType exType, final long expires) {
		final CommandArguments args = CommandArguments.create(key).add(exType).add(expires);
		return getBinaryEx(key, new LettuceGetExArgs(exType, expires), args);
	}

	@Override
	public String getRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.GETRANGE, args, (cmd)->cmd.getrange(SafeEncoder.encode(key), start, end),
				(cmd)->cmd.getrange(SafeEncoder.encode(key), start, end), SafeEncoder::encode);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.GETRANGE, args, (cmd)->cmd.getrange(key, start, end),
				(cmd)->cmd.getrange(key, start, end));
	}

	@Override
	public String getSet(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.GETSET, args,
				(cmd)->cmd.getset(SafeEncoder.encode(key), SafeEncoder.encode(value)),
				(cmd)->cmd.getset(SafeEncoder.encode(key), SafeEncoder.encode(value)), SafeEncoder::encode);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.GETSET, args, (cmd)->cmd.getset(key, value),
				(cmd)->cmd.getset(key, value));
	}

	@Override
	public Long incr(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.INCR, args, (cmd)->cmd.incr(SafeEncoder.encode(key)),
				(cmd)->cmd.incr(SafeEncoder.encode(key)));
	}

	@Override
	public Long incr(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.INCR, args, (cmd)->cmd.incr(key), (cmd)->cmd.incr(key));
	}

	@Override
	public Long incrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.INCRBY, args, (cmd)->cmd.incrby(SafeEncoder.encode(key), value),
				(cmd)->cmd.incrby(SafeEncoder.encode(key), value));
	}

	@Override
	public Long incrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.INCRBY, args, (cmd)->cmd.incrby(key, value),
				(cmd)->cmd.incrby(key, value));
	}

	@Override
	public Double incrByFloat(final String key, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.INCRBYFLOAT, args, (cmd)->cmd.incrbyfloat(SafeEncoder.encode(key), value),
				(cmd)->cmd.incrbyfloat(SafeEncoder.encode(key), value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.INCRBYFLOAT, args, (cmd)->cmd.incrbyfloat(key, value),
				(cmd)->cmd.incrbyfloat(key, value));
	}

	@Override
	public LcsResult lcs(final String key1, final String key2) {
		final CommandArguments args = CommandArguments.create(key1, key2);
		return executeCommand(RedisCommand.LCS, args, (cmd)->cmd.lcs(new LettuceLcsArgs(key1, key2)),
				(cmd)->cmd.lcs(new LettuceLcsArgs(key1, key2)), new StringMatchResultConveter());
	}

	@Override
	public LcsResult lcs(byte[] key1, byte[] key2) {
		final CommandArguments args = CommandArguments.create(key1, key2);
		return executeCommand(RedisCommand.LCS, args, (cmd)->cmd.lcs(new LettuceLcsArgs(key1, key2)),
				(cmd)->cmd.lcs(new LettuceLcsArgs(key1, key2)), new StringMatchResultConveter());
	}

	@Override
	public LcsResult lcs(final String key1, final String key2, final LcsArgument argument) {
		final CommandArguments args = CommandArguments.create(key1, key2).add(argument);
		return executeCommand(RedisCommand.LCS, args,
				(cmd)->cmd.lcs(new LettuceLcsArgs(key1, key2, argument)),
				(cmd)->cmd.lcs(new LettuceLcsArgs(key1, key2, argument)),
				new StringMatchResultConveter());
	}

	@Override
	public LcsResult lcs(final byte[] key1, final byte[] key2, final LcsArgument argument) {
		final CommandArguments args = CommandArguments.create(key1, key2).add(argument);
		return executeCommand(RedisCommand.LCS, args,
				(cmd)->cmd.lcs(new LettuceLcsArgs(key1, key2, argument)),
				(cmd)->cmd.lcs(new LettuceLcsArgs(key1, key2, argument)),
				new StringMatchResultConveter());
	}

	@Override
	public List<String> mGet(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.MGET, args, (cmd)->cmd.mget(SafeEncoder.encode(keys)),
				(cmd)->cmd.mget(SafeEncoder.encode(keys)), new ListConverter<>((v)->SafeEncoder.encode(v.getValue())));
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.MGET, args, (cmd)->cmd.mget(keys), (cmd)->cmd.mget(keys),
				new ListConverter<>(Value::getValue));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSet(final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data);
		return executeCommand(RedisCommand.MSET, args, (cmd)->cmd.mset(buildSetValues(data)),
				(cmd)->cmd.mset(buildSetValues(data)), new OkStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetEx(final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data);
		return executeCommand(RedisCommand.MSETNX, args,
				(cmd)->cmd.msetex(buildSetValues(data), new LettuceMSetExArgs()),
				(cmd)->cmd.msetex(buildSetValues(data), new LettuceMSetExArgs()), new BooleanStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetEx(final NxXx nxXx, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data).add(nxXx);
		return mSetEx(new LettuceMSetExArgs(nxXx), data, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetEx(final NxXx nxXx, final PxExType exType, final long expires,
	                     final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data).add(nxXx).add(exType).add(expires);
		return mSetEx(new LettuceMSetExArgs(nxXx, exType, expires), data, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetEx(final PxExType exType, final long expires, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data).add(exType).add(expires);
		return mSetEx(new LettuceMSetExArgs(exType, expires), data, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetNx(final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(data);
		return executeCommand(RedisCommand.MSETNX, args, (cmd)->cmd.msetnx(buildSetValues(data)),
				(cmd)->cmd.msetnx(buildSetValues(data)), new BooleanStatusConverter());
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(lifetime);
		return executeCommand(RedisCommand.PSETEX, args,
				(cmd)->cmd.psetex(SafeEncoder.encode(key), lifetime, SafeEncoder.encode(value)),
				(cmd)->cmd.psetex(SafeEncoder.encode(key), lifetime, SafeEncoder.encode(value)),
				new OkStatusConverter());
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(lifetime);
		return executeCommand(RedisCommand.PSETEX, args, (cmd)->cmd.psetex(key, lifetime, value),
				(cmd)->cmd.psetex(key, lifetime, value), new OkStatusConverter());
	}

	@Override
	public Status set(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.SET, args,
				(cmd)->cmd.set(SafeEncoder.encode(key), SafeEncoder.encode(value)),
				(cmd)->cmd.set(SafeEncoder.encode(key), SafeEncoder.encode(value)), new OkStatusConverter());
	}

	@Override
	public Status set(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.SET, args, (cmd)->cmd.set(key, value),
				(cmd)->cmd.set(key, value), new OkStatusConverter());
	}

	@Override
	public Status set(final String key, final String value, final SetType setType) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(setType);
		return set(SafeEncoder.encode(key), value, new LettuceSetArgs(setType), args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetType setType) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(setType);
		return set(key, value, new LettuceSetArgs(setType), args);
	}

	@Override
	public Status set(final String key, final String value, final SetType setType, final PxExType pxExType,
	                  final long expires) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(setType).add(pxExType).add(expires);
		return set(SafeEncoder.encode(key), value, new LettuceSetArgs(setType, pxExType, expires), args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetType setType, final PxExType pxExType,
	                  final long expires) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(setType).add(pxExType).add(expires);
		return set(key, value, new LettuceSetArgs(setType, pxExType, expires), args);
	}

	@Override
	public Status set(final String key, final String value, final PxExType pxExType, final long expires) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(pxExType).add(expires);
		return set(SafeEncoder.encode(key), value, new LettuceSetArgs(pxExType, expires), args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final PxExType pxExType, final long expires) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(pxExType).add(expires);
		return set(key, value, new LettuceSetArgs(pxExType, expires), args);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(lifetime);
		return executeCommand(RedisCommand.SETEX, args,
				(cmd)->cmd.setex(SafeEncoder.encode(key), lifetime, SafeEncoder.encode(value)),
				(cmd)->cmd.setex(SafeEncoder.encode(key), lifetime, SafeEncoder.encode(value)),
				new OkStatusConverter());
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(lifetime);
		return executeCommand(RedisCommand.SETEX, args, (cmd)->cmd.setex(key, lifetime, value),
				(cmd)->cmd.setex(key, lifetime, value), new OkStatusConverter());
	}

	@Override
	public Status setNx(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.SETNX, args,
				(cmd)->cmd.setnx(SafeEncoder.encode(key), SafeEncoder.encode(value)),
				(cmd)->cmd.setnx(SafeEncoder.encode(key), SafeEncoder.encode(value)), new BooleanStatusConverter());
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.SETNX, args, (cmd)->cmd.setnx(key, value),
				(cmd)->cmd.setnx(key, value), new BooleanStatusConverter());
	}

	@Override
	public Long setRange(final String key, final long offset, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(RedisCommand.SETRANGE, args,
				(cmd)->cmd.setrange(SafeEncoder.encode(key), offset, SafeEncoder.encode(value)),
				(cmd)->cmd.setrange(SafeEncoder.encode(key), offset, SafeEncoder.encode(value)));
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(RedisCommand.SETRANGE, args, (cmd)->cmd.setrange(key, offset, value),
				(cmd)->cmd.setrange(key, offset, value));
	}

	@Override
	public Long strlen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.STRLEN, args, (cmd)->cmd.strlen(SafeEncoder.encode(key)),
				(cmd)->cmd.strlen(SafeEncoder.encode(key)));
	}

	@Override
	public Long strlen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.STRLEN, args, (cmd)->cmd.strlen(key),
				(cmd)->cmd.strlen(key));
	}

	@Override
	public String substr(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.SUBSTR, args, (cmd)->cmd.getrange(SafeEncoder.encode(key), start, end),
				(cmd)->cmd.getrange(SafeEncoder.encode(key), start, end), SafeEncoder::encode);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.SUBSTR, args, (cmd)->cmd.getrange(key, start, end),
				(cmd)->cmd.getrange(key, start, end));
	}

	private String getStringEx(final byte[] key, final GetExArgs getExArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.GETEX, args, (cmd)->cmd.getex(key, getExArgs),
				(cmd)->cmd.getex(key, getExArgs), SafeEncoder::encode);
	}

	private byte[] getBinaryEx(final byte[] key, final GetExArgs getExArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.GETEX, args, (cmd)->cmd.getex(key, getExArgs),
				(cmd)->cmd.getex(key, getExArgs));
	}

	private Status delEx(final byte[] key, final io.lettuce.core.CompareCondition<byte[]> condition,
	                     final CommandArguments args) {
		return executeCommand(RedisCommand.DELEX, args, (cmd)->cmd.delex(key, condition),
				(cmd)->cmd.delex(key, condition), new OneStatusConverter());
	}

	private Status mSetEx(final MSetExArgs mSetExArgs, final KeyValue<String, String>[] data,
	                      final CommandArguments args) {
		return executeCommand(RedisCommand.MSETEX, args, (cmd)->cmd.msetex(buildSetValues(data), mSetExArgs),
				(cmd)->cmd.msetex(buildSetValues(data), mSetExArgs), new BooleanStatusConverter());
	}

	private Status set(final byte[] key, final String value, final SetArgs setArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.SET, args, (cmd)->cmd.set(key, SafeEncoder.encode(value), setArgs),
				(cmd)->cmd.set(key, SafeEncoder.encode(value), setArgs), new OkStatusConverter());
	}

	private Status set(final byte[] key, final byte[] value, final SetArgs setArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.SET, args, (cmd)->cmd.set(key, value, setArgs),
				(cmd)->cmd.set(key, value, setArgs), new OkStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	private Map<byte[], byte[]> buildSetValues(final KeyValue<String, String>... data) {
		final ArrayKeyValueMapConverter<String, String, byte[], byte[]> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				SafeEncoder::encode, SafeEncoder::encode);
		return arrayKeyValueMapConverter.convert(data);
	}

}
