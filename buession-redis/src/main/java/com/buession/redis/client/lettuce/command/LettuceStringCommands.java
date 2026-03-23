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
import com.buession.redis.core.command.args.string.DelExType;
import com.buession.redis.core.LcsResult;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.string.LcsArgument;
import com.buession.redis.core.command.args.string.MSetExArgument;
import com.buession.redis.core.command.args.string.SetArgument;
import com.buession.redis.core.internal.convert.lettuce.response.StringMatchResultConveter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceGetExArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceLcsArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceMSetExArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceSetArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.CompareCondition;
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
		return executeCommand(Command.APPEND, args, (cmd)->cmd.append(rawBinaryKey(key), SafeEncoder.encode(value)));
	}

	@Override
	public Long append(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.APPEND, args, (cmd)->cmd.append(rawKey(key), value));
	}

	@Override
	public Long decr(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DECR, args, (cmd)->cmd.decr(rawBinaryKey(key)));
	}

	@Override
	public Long decr(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DECR, args, (cmd)->cmd.decr(rawKey(key)));
	}

	@Override
	public Long decrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.DECRBY, args, (cmd)->cmd.decrby(rawBinaryKey(key), value));
	}

	@Override
	public Long decrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.DECRBY, args, (cmd)->cmd.decrby(rawKey(key), value));
	}

	@Override
	public Status delEx(final String key, final DelExType type, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(type, value);

		if(type == DelExType.IFEQ){
			return delEx(rawBinaryKey(key), CompareCondition.valueEq(SafeEncoder.encode(value)), args);
		}else if(type == DelExType.IFNE){
			return delEx(rawBinaryKey(key), CompareCondition.valueNe(SafeEncoder.encode(value)), args);
		}else if(type == DelExType.IFDEQ){
			return delEx(rawBinaryKey(key), CompareCondition.digestEq(value), args);
		}else if(type == DelExType.IFDNE){
			return delEx(rawBinaryKey(key), CompareCondition.digestNe(value), args);
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
			return delEx(rawKey(key), CompareCondition.digestEq(SafeEncoder.encode(value)), args);
		}else if(type == DelExType.IFDNE){
			return delEx(rawKey(key), CompareCondition.digestNe(SafeEncoder.encode(value)), args);
		}else{
			return executeCommand(Command.DELEX, args);
		}
	}

	@Override
	public String digest(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DIGEST, args, (cmd)->cmd.digest(rawKey(key)));
	}

	@Override
	public byte[] digest(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DIGEST, args, (cmd)->cmd.digest(rawKey(key)), SafeEncoder::encode);
	}

	@Override
	public String get(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GET, args, (cmd)->cmd.get(rawBinaryKey(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] get(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GET, args, (cmd)->cmd.get(rawKey(key)));
	}

	@Override
	public String getDel(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GETDEL, args, (cmd)->cmd.getdel(rawBinaryKey(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] getDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GETDEL, args, (cmd)->cmd.getdel(rawKey(key)));
	}

	@Override
	public String getEx(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GETEX, args,
				(cmd)->cmd.getex(rawBinaryKey(key), new LettuceGetExArgs()),
				SafeEncoder::encode);
	}

	@Override
	public byte[] getEx(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GETEX, args, (cmd)->cmd.getex(rawKey(key), new LettuceGetExArgs()));
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create(key).add(getExArgument);
		return executeCommand(Command.GETEX, args,
				(cmd)->cmd.getex(rawBinaryKey(key), new LettuceGetExArgs(getExArgument)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create(key).add(getExArgument);
		return executeCommand(Command.GETEX, args,
				(cmd)->cmd.getex(rawKey(key), new LettuceGetExArgs(getExArgument)));
	}

	@Override
	public String getRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.GETRANGE, args, (cmd)->cmd.getrange(rawBinaryKey(key), start, end),
				SafeEncoder::encode);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.GETRANGE, args, (cmd)->cmd.getrange(rawKey(key), start, end));
	}

	@Override
	public String getSet(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.GETSET, args, (cmd)->cmd.getset(rawBinaryKey(key), SafeEncoder.encode(value)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.GETSET, args, (cmd)->cmd.getset(rawKey(key), value));
	}

	@Override
	public Long incr(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.INCR, args, (cmd)->cmd.incr(rawBinaryKey(key)));
	}

	@Override
	public Long incr(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.INCR, args, (cmd)->cmd.incr(rawKey(key)));
	}

	@Override
	public Long incrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.INCRBY, args, (cmd)->cmd.incrby(rawBinaryKey(key), value));
	}

	@Override
	public Long incrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.INCRBY, args, (cmd)->cmd.incrby(rawKey(key), value));
	}

	@Override
	public Double incrByFloat(final String key, final double value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.INCRBYFLOAT, args, (cmd)->cmd.incrbyfloat(rawBinaryKey(key), value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.INCRBYFLOAT, args, (cmd)->cmd.incrbyfloat(rawKey(key), value));
	}

	@Override
	public LcsResult lcs(final String key1, final String key2) {
		final CommandArguments args = CommandArguments.create(key1, key2);
		return executeCommand(Command.LCS, args, (cmd)->cmd.lcs(new LettuceLcsArgs(rawKey(key1), rawKey(key2))),
				new StringMatchResultConveter());
	}

	@Override
	public LcsResult lcs(byte[] key1, byte[] key2) {
		final CommandArguments args = CommandArguments.create(key1, key2);
		return executeCommand(Command.LCS, args, (cmd)->cmd.lcs(new LettuceLcsArgs(rawKey(key1), rawKey(key2))),
				new StringMatchResultConveter());
	}

	@Override
	public LcsResult lcs(final String key1, final String key2, final LcsArgument argument) {
		final CommandArguments args = CommandArguments.create(key1, key2).add(argument);
		return executeCommand(Command.LCS, args,
				(cmd)->cmd.lcs(new LettuceLcsArgs(rawKey(key1), rawKey(key2), argument)),
				new StringMatchResultConveter());
	}

	@Override
	public LcsResult lcs(final byte[] key1, final byte[] key2, final LcsArgument argument) {
		final CommandArguments args = CommandArguments.create(key1, key2).add(argument);
		return executeCommand(Command.LCS, args,
				(cmd)->cmd.lcs(new LettuceLcsArgs(rawKey(key1), rawKey(key2), argument)),
				new StringMatchResultConveter());
	}

	@Override
	public List<String> mGet(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.MGET, args, (cmd)->cmd.mget(rawBinaryKeys(keys)),
				new ListConverter<>((v)->SafeEncoder.encode(v.getValue())));
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.MGET, args, (cmd)->cmd.mget(rawKeys(keys)), new ListConverter<>(Value::getValue));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSet(final KeyValue<String, String>... values) {
		final CommandArguments args = CommandArguments.create(values);
		return executeCommand(Command.MSET, args, (cmd)->cmd.mset(buildSetValues(values)),
				new OkStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetEx(final KeyValue<String, String>... values) {
		final CommandArguments args = CommandArguments.create(values);
		return executeCommand(Command.MSETNX, args, (cmd)->cmd.msetex(buildSetValues(values),
				new LettuceMSetExArgs()), new BooleanStatusConverter());
	}

	@Override
	public Status mSetEx(final KeyValue<String, String>[] values, final MSetExArgument argument) {
		final CommandArguments args = CommandArguments.create(values).add(argument);
		return executeCommand(Command.MSETNX, args, (cmd)->cmd.msetex(buildSetValues(values),
				new LettuceMSetExArgs(argument)), new BooleanStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status mSetNx(final KeyValue<String, String>... values) {
		final CommandArguments args = CommandArguments.create(values);
		return executeCommand(Command.MSETNX, args, (cmd)->cmd.msetnx(buildSetValues(values)),
				new BooleanStatusConverter());
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key, value).add(lifetime);
		return executeCommand(Command.PSETEX, args,
				(cmd)->cmd.psetex(rawBinaryKey(key), lifetime, SafeEncoder.encode(value)),
				new OkStatusConverter());
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key, value).add(lifetime);
		return executeCommand(Command.PSETEX, args, (cmd)->cmd.psetex(rawKey(key), lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status set(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.SET, args, (cmd)->cmd.set(rawBinaryKey(key), SafeEncoder.encode(value)),
				new OkStatusConverter());
	}

	@Override
	public Status set(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.SET, args, (cmd)->cmd.set(rawKey(key), value), new OkStatusConverter());
	}

	@Override
	public Status set(final String key, final String value, final SetArgument argument) {
		final CommandArguments args = CommandArguments.create(key, value).add(argument);
		return executeCommand(Command.SET, args,
				(cmd)->cmd.set(rawBinaryKey(key), SafeEncoder.encode(value), new LettuceSetArgs(argument)),
				new OkStatusConverter());
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument argument) {
		final CommandArguments args = CommandArguments.create(key, value).add(argument);
		return executeCommand(Command.SET, args,
				(cmd)->cmd.set(rawKey(key), value, new LettuceSetArgs(argument)), new OkStatusConverter());
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key, value).add(lifetime);
		return executeCommand(Command.SETEX, args,
				(cmd)->cmd.setex(rawBinaryKey(key), lifetime, SafeEncoder.encode(value)),
				new OkStatusConverter());
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key, value).add(lifetime);
		return executeCommand(Command.SETEX, args, (cmd)->cmd.setex(rawKey(key), lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status setNx(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.SETNX, args, (cmd)->cmd.setnx(rawBinaryKey(key), SafeEncoder.encode(value)),
				new BooleanStatusConverter());
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.SETNX, args, (cmd)->cmd.setnx(rawKey(key), value), new BooleanStatusConverter());
	}

	@Override
	public Long setRange(final String key, final long offset, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(Command.SETRANGE, args,
				(cmd)->cmd.setrange(rawBinaryKey(key), offset, SafeEncoder.encode(value)));
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(Command.SETRANGE, args, (cmd)->cmd.setrange(rawKey(key), offset, value));
	}

	@Override
	public Long strlen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.STRLEN, args, (cmd)->cmd.strlen(rawBinaryKey(key)));
	}

	@Override
	public Long strlen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.STRLEN, args, (cmd)->cmd.strlen(rawKey(key)));
	}

	@Override
	public String substr(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.SUBSTR, args, (cmd)->cmd.getrange(rawBinaryKey(key), start, end),
				SafeEncoder::encode);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.SUBSTR, args, (cmd)->cmd.getrange(rawKey(key), start, end));
	}

	private Status delEx(final byte[] key, final CompareCondition<byte[]> condition, final CommandArguments args) {
		return executeCommand(Command.DELEX, args, (cmd)->cmd.delex(key, condition), new OneStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	private Map<byte[], byte[]> buildSetValues(final KeyValue<String, String>... values) {
		final ArrayKeyValueMapConverter<String, String, byte[], byte[]> arrayKeyValueMapConverter =
				new ArrayKeyValueMapConverter<>(this::rawBinaryKey, SafeEncoder::encode);
		return arrayKeyValueMapConverter.convert(values);
	}

}
