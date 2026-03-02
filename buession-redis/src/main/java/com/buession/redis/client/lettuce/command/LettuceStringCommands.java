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

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.DelExType;
import com.buession.redis.core.LcsResult;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.LcsArgument;
import com.buession.redis.core.command.args.MSetExArgument;
import com.buession.redis.core.command.args.SetArgument;
import com.buession.redis.core.internal.convert.StringMapBinaryMapConverter;
import com.buession.redis.core.internal.convert.lettuce.params.GetExArgumentHGetExArgsConverter;
import com.buession.redis.core.internal.convert.lettuce.params.LcsArgumentConveter;
import com.buession.redis.core.internal.convert.lettuce.params.MSetExArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.params.SetArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StringMatchResultConveter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.CompareCondition;
import io.lettuce.core.LcsArgs;
import io.lettuce.core.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
		return executeCommand(Command.APPEND, args,
				(cmd)->cmd.append(SafeEncoder.encode(key), SafeEncoder.encode(value)), (v)->v);
	}

	@Override
	public Long append(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.APPEND, args, (cmd)->cmd.append(key, value), (v)->v);
	}

	@Override
	public Long decr(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DECR, args, (cmd)->cmd.decr(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long decr(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DECR, args, (cmd)->cmd.decr(key), (v)->v);
	}

	@Override
	public Long decrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.DECRBY, args, (cmd)->cmd.decrby(SafeEncoder.encode(key), value), (v)->v);
	}

	@Override
	public Long decrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.DECRBY, args, (cmd)->cmd.decrby(key, value), (v)->v);
	}

	@Override
	public Status delEx(final String key, final DelExType type, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(type, value);
		final byte[] sKey = SafeEncoder.encode(key);

		if(type == DelExType.IFEQ){
			return delEx(sKey, CompareCondition.valueEq(SafeEncoder.encode(value)), args);
		}else if(type == DelExType.IFNE){
			return delEx(sKey, CompareCondition.valueNe(SafeEncoder.encode(value)), args);
		}else if(type == DelExType.IFDEQ){
			return delEx(sKey, CompareCondition.digestEq(value), args);
		}else if(type == DelExType.IFDNE){
			return delEx(sKey, CompareCondition.digestNe(value), args);
		}else{
			return executeCommand(Command.DELEX, args);
		}
	}

	@Override
	public Status delEx(final byte[] key, final DelExType type, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(type, value);

		if(type == DelExType.IFEQ){
			return delEx(key, CompareCondition.valueEq(value), args);
		}else if(type == DelExType.IFNE){
			return delEx(key, CompareCondition.valueNe(value), args);
		}else if(type == DelExType.IFDEQ){
			return delEx(key, CompareCondition.digestEq(SafeEncoder.encode(value)), args);
		}else if(type == DelExType.IFDNE){
			return delEx(key, CompareCondition.digestNe(SafeEncoder.encode(value)), args);
		}else{
			return executeCommand(Command.DELEX, args);
		}
	}

	@Override
	public String digest(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DIGEST, args, (cmd)->cmd.digest(key), (v)->v);
	}

	@Override
	public byte[] digest(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DIGEST, args, (cmd)->cmd.digest(key), SafeEncoder::encode);
	}

	@Override
	public String get(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GET, args, (cmd)->cmd.get(SafeEncoder.encode(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] get(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GET, args, (cmd)->cmd.get(key), (v)->v);
	}

	@Override
	public String getDel(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GETDEL, args, (cmd)->cmd.getdel(SafeEncoder.encode(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] getDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.GETDEL, args, (cmd)->cmd.getdel(key), (v)->v);
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create(key).add(getExArgument);
		final GetExArgumentHGetExArgsConverter getExArgumentHGetExArgsConverter = new GetExArgumentHGetExArgsConverter();
		return executeCommand(Command.GETEX, args,
				(cmd)->cmd.getex(SafeEncoder.encode(key), getExArgumentHGetExArgsConverter.convert(getExArgument))
				, SafeEncoder::encode);
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create(key).add(getExArgument);
		final GetExArgumentHGetExArgsConverter getExArgumentHGetExArgsConverter = new GetExArgumentHGetExArgsConverter();
		return executeCommand(Command.GETEX, args,
				(cmd)->cmd.getex(key, getExArgumentHGetExArgsConverter.convert(getExArgument))
				, (v)->v);
	}

	@Override
	public String getRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.GETRANGE, args, (cmd)->cmd.getrange(SafeEncoder.encode(key), start, end),
				SafeEncoder::encode);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.GETRANGE, args, (cmd)->cmd.getrange(key, start, end), (v)->v);
	}

	@Override
	public String getSet(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.GETSET, args, (cmd)->cmd.getset(SafeEncoder.encode(key),
				SafeEncoder.encode(value)), SafeEncoder::encode);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.GETSET, args, (cmd)->cmd.getset(key, value), (v)->v);
	}

	@Override
	public Long incr(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.INCR, args, (cmd)->cmd.incr(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long incr(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.INCR, args, (cmd)->cmd.incr(key), (v)->v);
	}

	@Override
	public Long incrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.INCRBY, args, (cmd)->cmd.incrby(SafeEncoder.encode(key), value), (v)->v);
	}

	@Override
	public Long incrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.INCRBY, args, (cmd)->cmd.incrby(key, value), (v)->v);
	}

	@Override
	public Double incrByFloat(final String key, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.INCRBYFLOAT, args, (cmd)->cmd.incrbyfloat(SafeEncoder.encode(key), value),
				(v)->v);
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.INCRBYFLOAT, args, (cmd)->cmd.incrbyfloat(key, value), (v)->v);
	}

	@Override
	public LcsResult lcs(final String key1, final String key2) {
		final CommandArguments args = CommandArguments.create(key1).add(key2);
		return lcs(LcsArgs.Builder.keys(key1, key2), args);
	}

	@Override
	public LcsResult lcs(byte[] key1, byte[] key2) {
		return lcs(SafeEncoder.encode(key1), SafeEncoder.encode(key2));
	}

	@Override
	public LcsResult lcs(final String key1, final String key2, final LcsArgument argument) {
		final CommandArguments args = CommandArguments.create(key1).add(key2).add(argument);
		final LcsArgumentConveter lcsArgumentConveter = new LcsArgumentConveter();
		final LcsArgs lcsArgs = Optional.ofNullable(lcsArgumentConveter.convert(argument)).orElse(new LcsArgs());

		lcsArgs.by(key1, key2);
		return lcs(lcsArgs, args);
	}

	@Override
	public LcsResult lcs(final byte[] key1, final byte[] key2, final LcsArgument argument) {
		return lcs(SafeEncoder.encode(key1), SafeEncoder.encode(key2), argument);
	}

	@Override
	public List<String> mGet(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.MGET, args, (cmd)->cmd.mget(SafeEncoder.encode(keys)),
				new ListConverter<>((v)->SafeEncoder.encode(v.getValue())));
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.MGET, args, (cmd)->cmd.mget(keys), new ListConverter<>(Value::getValue));
	}

	@Override
	public Status mSet(final Map<String, String> values) {
		final CommandArguments args = CommandArguments.create(values);
		final StringMapBinaryMapConverter stringMapBinaryMapConverter = new StringMapBinaryMapConverter();

		return executeCommand(Command.MSET, args, (cmd)->cmd.mset(stringMapBinaryMapConverter.convert(values)),
				new OkStatusConverter());
	}

	@Override
	public Status mSetEx(final Map<String, String> values, final MSetExArgument argument) {
		final CommandArguments args = CommandArguments.create(values).add(argument);
		final StringMapBinaryMapConverter stringMapBinaryMapConverter = new StringMapBinaryMapConverter();
		final MSetExArgumentConverter mSetExArgumentConverter = new MSetExArgumentConverter();

		return executeCommand(Command.MSETNX, args, (cmd)->cmd.msetex(stringMapBinaryMapConverter.convert(values),
				mSetExArgumentConverter.convert(argument)), new BooleanStatusConverter());
	}

	@Override
	public Status mSetNx(final Map<String, String> values) {
		final CommandArguments args = CommandArguments.create(values);
		final StringMapBinaryMapConverter stringMapBinaryMapConverter = new StringMapBinaryMapConverter();

		return executeCommand(Command.MSETNX, args, (cmd)->cmd.msetnx(stringMapBinaryMapConverter.convert(values)),
				new BooleanStatusConverter());
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(lifetime);
		return executeCommand(Command.PSETEX, args,
				(cmd)->cmd.psetex(SafeEncoder.encode(key), lifetime, SafeEncoder.encode(value)),
				new OkStatusConverter());
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(lifetime);
		return executeCommand(Command.PSETEX, args, (cmd)->cmd.psetex(key, lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status set(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.SET, args, (cmd)->cmd.set(SafeEncoder.encode(key), SafeEncoder.encode(value)),
				new OkStatusConverter());
	}

	@Override
	public Status set(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.SET, args, (cmd)->cmd.set(key, value), new OkStatusConverter());
	}

	@Override
	public Status set(final String key, final String value, final SetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(argument);
		return set(SafeEncoder.encode(key), SafeEncoder.encode(value), argument, args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(argument);
		return set(key, value, argument, args);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(lifetime);
		return executeCommand(Command.SETEX, args,
				(cmd)->cmd.setex(SafeEncoder.encode(key), lifetime, SafeEncoder.encode(value)),
				new OkStatusConverter());
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(lifetime);
		return executeCommand(Command.SETEX, args, (cmd)->cmd.setex(key, lifetime, value),
				new OkStatusConverter());
	}

	@Override
	public Status setNx(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.SETNX, args, (cmd)->cmd.setnx(SafeEncoder.encode(key), SafeEncoder.encode(value)),
				new BooleanStatusConverter());
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.SETNX, args, (cmd)->cmd.setnx(key, value),
				new BooleanStatusConverter());
	}

	@Override
	public Long setRange(final String key, final long offset, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(Command.SETRANGE, args,
				(cmd)->cmd.setrange(SafeEncoder.encode(key), offset, SafeEncoder.encode(value)), (v)->v);
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(Command.SETRANGE, args, (cmd)->cmd.setrange(key, offset, value), (v)->v);
	}

	@Override
	public Long strlen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.STRLEN, args, (cmd)->cmd.strlen(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long strlen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.STRLEN, args, (cmd)->cmd.strlen(key), (v)->v);
	}

	@Override
	public String substr(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.SUBSTR, args, (cmd)->cmd.getrange(SafeEncoder.encode(key), start, end),
				SafeEncoder::encode);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.SUBSTR, args, (cmd)->cmd.getrange(key, start, end), (v)->v);
	}

	private Status delEx(final byte[] key, final CompareCondition<byte[]> condition, final CommandArguments args) {
		return executeCommand(Command.DELEX, args, (cmd)->cmd.delex(key, condition), new OneStatusConverter());
	}

	private LcsResult lcs(final LcsArgs lcsArgs, final CommandArguments args) {
		return executeCommand(Command.LCS, args, (cmd)->cmd.lcs(lcsArgs),
				new StringMatchResultConveter());
	}

	private Status set(final byte[] key, final byte[] value, final SetArgument argument, final CommandArguments args) {
		final SetArgumentConverter setArgumentConverter = new SetArgumentConverter();
		return executeCommand(Command.SET, args, (cmd)->cmd.set(key, value, setArgumentConverter.convert(argument)),
				new OkStatusConverter());
	}

}
