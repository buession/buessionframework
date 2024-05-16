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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.operations;

import com.buession.core.utils.StringUtils;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.lettuce.LettuceSetArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Value;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Lettuce 单机模式字符串命令操作
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class LettuceStringOperations extends AbstractStringOperations<LettuceStandaloneClient> {

	public LettuceStringOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Long append(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.APPEND, (cmd)->cmd.append(key, value), (v)->v)
				.run(args);
	}

	@Override
	public Long incr(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.INCR, (cmd)->cmd.incr(key), (v)->v)
				.run(args);
	}

	@Override
	public Long incrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.INCRBY, (cmd)->cmd.incrby(key, value), (v)->v)
				.run(args);
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.INCRBYFLOAT, (cmd)->cmd.incrbyfloat(key, value), (v)->v)
				.run(args);
	}

	@Override
	public Long decr(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.DECR, (cmd)->cmd.decr(key), (v)->v)
				.run(args);
	}

	@Override
	public Long decrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.DECRBY, (cmd)->cmd.decrby(key, value), (v)->v)
				.run(args);
	}

	@Override
	public String get(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.GET, (cmd)->cmd.get(SafeEncoder.encode(key)),
				Converters.BINARY_TO_STRING_CONVERTER)
				.run(args);
	}

	@Override
	public byte[] get(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.GET, (cmd)->cmd.get(key), (v)->v)
				.run(args);
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		return new LettuceCommand<String, String>(client, ProtocolCommand.GETEX)
				.run(args);
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		return new LettuceCommand<byte[], byte[]>(client, ProtocolCommand.GETEX)
				.run(args);
	}

	@Override
	public String getSet(final String key, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.GETSET, (cmd)->cmd.getset(SafeEncoder.encode(key),
				SafeEncoder.encode(value)), Converters.BINARY_TO_STRING_CONVERTER)
				.run(args);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.GETSET, (cmd)->cmd.getset(key, value), (v)->v)
				.run(args);
	}

	@Override
	public String getDel(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<String, String>(client, ProtocolCommand.GETDEL)
				.run(args);
	}

	@Override
	public byte[] getDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<byte[], byte[]>(client, ProtocolCommand.GETDEL)
				.run(args);
	}

	@Override
	public List<String> mGet(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.MGET, (cmd)->cmd.mget(SafeEncoder.encode(keys)),
				(v)->v.stream().map((r)->SafeEncoder.encode(r.getValue())).collect(Collectors.toList()))
				.run(args);
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.MGET, (cmd)->cmd.mget(keys),
				(v)->v.stream().map(Value::getValue).collect(Collectors.toList()))
				.run(args);
	}

	@Override
	public Status mSet(final Map<String, String> values) {
		final CommandArguments args = CommandArguments.create("values", values);
		return new LettuceCommand<>(client, ProtocolCommand.MSET,
				(cmd)->cmd.mset(Converters.STRING_MAP_TO_BINARY_MAP_CONVERTER.convert(values)),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status mSetNx(final Map<String, String> values) {
		final CommandArguments args = CommandArguments.create("values", values);
		return new LettuceCommand<>(client, ProtocolCommand.MSETNX,
				(cmd)->cmd.msetnx(Converters.STRING_MAP_TO_BINARY_MAP_CONVERTER.convert(values)),
				Converters.BOOLEAN_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return new LettuceCommand<>(client, ProtocolCommand.PSETEX, (cmd)->cmd.psetex(key, lifetime, value),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.SET, (cmd)->cmd.set(key, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.SET,
				(cmd)->cmd.set(key, value, LettuceSetArgs.from(setArgument)), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return new LettuceCommand<>(client, ProtocolCommand.SETEX, (cmd)->cmd.setex(key, lifetime, value),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.SETNX, (cmd)->cmd.setnx(key, value),
				Converters.BOOLEAN_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.SETRANGE, (cmd)->cmd.setrange(key, offset, value), (v)->v)
				.run(args);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.GETRANGE, (cmd)->cmd.getrange(key, start, end), (v)->v)
				.run(args);
	}

	@Override
	public Long strlen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.STRLEN, (cmd)->cmd.strlen(key), (v)->v)
				.run(args);
	}

	@Override
	public String substr(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.SUBSTR, (cmd)->cmd.get(SafeEncoder.encode(key)),
				(value)->StringUtils.substring(SafeEncoder.encode(value), (int) start, (int) end))
				.run(args);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.SUBSTR, (cmd)->cmd.get(key),
				(value)->StringUtils.substring(SafeEncoder.encode(value), (int) start, (int) end)
						.getBytes(StandardCharsets.UTF_8))
				.run(args);
	}

}
