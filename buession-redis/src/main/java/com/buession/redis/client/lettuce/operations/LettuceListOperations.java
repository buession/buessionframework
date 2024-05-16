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

import com.buession.core.builder.ListBuilder;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.Direction;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.lettuce.LettuceLPosArgs;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Lettuce 单机模式列表命令操作
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class LettuceListOperations extends AbstractListOperations<LettuceStandaloneClient> {

	public LettuceListOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public String lIndex(final String key, final long index) {
		final CommandArguments args = CommandArguments.create("key", key).put("index", index);
		return new LettuceCommand<>(client, ProtocolCommand.LINDEX, (cmd)->cmd.lindex(SafeEncoder.encode(key), index),
				Converters.BINARY_TO_STRING_CONVERTER)
				.run(args);
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index) {
		final CommandArguments args = CommandArguments.create("key", key).put("index", index);
		return new LettuceCommand<>(client, ProtocolCommand.LINDEX, (cmd)->cmd.lindex(key, index), (v)->v)
				.run(args);
	}

	@Override
	public Long lInsert(final byte[] key, final ListPosition position, final byte[] pivot, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("position", position).put("pivot", pivot)
				.put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.LINSERT, (cmd)->cmd.linsert(key,
				ListPosition.BEFORE == position, pivot, value), (v)->v)
				.run(args);
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("index", index).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.LSET, (cmd)->cmd.lset(key, index, value),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Long lLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.LLEN, (cmd)->cmd.llen(key), (v)->v)
				.run(args);
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.LRANGE,
				(cmd)->cmd.lrange(SafeEncoder.encode(key), start, end), Converters.BINARY_LIST_TO_STRING_LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.LRANGE, (cmd)->cmd.lrange(key, start, end), (v)->v)
				.run(args);
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element), (v)->v)
				.run(args);
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument);
		return new LettuceCommand<>(client, ProtocolCommand.LPOS,
				(cmd)->cmd.lpos(key, element, LettuceLPosArgs.from(lPosArgument)), (v)->v)
				.run(args);
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument)
				.put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.LPOS,
				(cmd)->cmd.lpos(key, element, (int) count, LettuceLPosArgs.from(lPosArgument)), (v)->v)
				.run(args);
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.LREM, (cmd)->cmd.lrem(key, count, value), (v)->v)
				.run(args);
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.LTRIM, (cmd)->cmd.ltrim(key, start, end),
				OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		return new LettuceCommand<String, String>(client, ProtocolCommand.LMOVE)
				.run(args);
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		return new LettuceCommand<byte[], byte[]>(client, ProtocolCommand.LMOVE)
				.run(args);
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
						 final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		return new LettuceCommand<String, String>(client, ProtocolCommand.LMOVE)
				.run(args);
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to).put("timeout", timeout);
		return new LettuceCommand<byte[], byte[]>(client, ProtocolCommand.BLMOVE)
				.run(args);
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		return new LettuceCommand<>(client, ProtocolCommand.BLPOP, (cmd)->cmd.blpop(timeout, SafeEncoder.encode(keys)),
				(v)->ListBuilder.of(SafeEncoder.encode(v.getValue())))
				.run(args);
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		return new LettuceCommand<>(client, ProtocolCommand.BLPOP, (cmd)->cmd.blpop(timeout, keys),
				(v)->ListBuilder.of(v.getValue()))
				.run(args);
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		return new LettuceCommand<>(client, ProtocolCommand.BRPOP, (cmd)->cmd.brpop(timeout, SafeEncoder.encode(keys)),
				(v)->ListBuilder.of(SafeEncoder.encode(v.getValue())))
				.run(args);
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		return new LettuceCommand<>(client, ProtocolCommand.BRPOP, (cmd)->cmd.brpop(timeout, keys),
				(v)->ListBuilder.of(v.getValue()))
				.run(args);
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("timeout", timeout);
		return new LettuceCommand<>(client, ProtocolCommand.BRPOPLPUSH, (cmd)->cmd.brpoplpush(timeout,
				SafeEncoder.encode(key), SafeEncoder.encode(destKey)), Converters.BINARY_TO_STRING_CONVERTER)
				.run(args);
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("timeout", timeout);
		return new LettuceCommand<>(client, ProtocolCommand.BRPOPLPUSH, (cmd)->cmd.brpoplpush(timeout, key, destKey),
				(v)->v)
				.run(args);
	}

	@Override
	public String lPop(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.LPOP, (cmd)->cmd.lpop(SafeEncoder.encode(key)),
				Converters.BINARY_TO_STRING_CONVERTER)
				.run(args);
	}

	@Override
	public byte[] lPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.LPOP, (cmd)->cmd.lpop(key), (v)->v)
				.run(args);
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);
		return new LettuceCommand<>(client, ProtocolCommand.LPUSH, (cmd)->cmd.lpush(key, values), (v)->v)
				.run(args);
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);
		return new LettuceCommand<>(client, ProtocolCommand.LPUSHX, (cmd)->cmd.lpushx(key, values), (v)->v)
				.run(args);
	}

	@Override
	public String rPop(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.RPOP, (cmd)->cmd.rpop(SafeEncoder.encode(key)),
				Converters.BINARY_TO_STRING_CONVERTER)
				.run(args);
	}

	@Override
	public byte[] rPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.RPOP, (cmd)->cmd.rpop(key), (v)->v)
				.run(args);
	}

	@Override
	public String rPoplPush(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return new LettuceCommand<>(client, ProtocolCommand.RPOPLPUSH,
				(cmd)->cmd.rpoplpush(SafeEncoder.encode(key), SafeEncoder.encode(destKey)),
				Converters.BINARY_TO_STRING_CONVERTER)
				.run(args);
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return new LettuceCommand<>(client, ProtocolCommand.RPOPLPUSH, (cmd)->cmd.rpoplpush(key, destKey), (v)->v)
				.run(args);
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);
		return new LettuceCommand<>(client, ProtocolCommand.RPUSH, (cmd)->cmd.rpush(key, values), (v)->v)
				.run(args);
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);
		return new LettuceCommand<>(client, ProtocolCommand.RPUSHX, (cmd)->cmd.rpushx(key, values), (v)->v)
				.run(args);
	}

}
