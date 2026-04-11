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

import com.buession.core.builder.ListBuilder;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.command.args.list.Direction;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.command.args.list.Position;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.args.list.LPosArgument;
import com.buession.redis.core.internal.convert.BinaryListStringListConverter;
import com.buession.redis.core.internal.convert.lettuce.response.KeyValueConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceLPosArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.LMPopArgs;
import io.lettuce.core.LMoveArgs;

import java.util.List;

/**
 * Lettuce 列表命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceListCommands extends AbstractLettuceRedisCommands implements ListCommands {

	public LettuceListCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
	                     final int timeout) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(from, to).add(timeout);
		return executeCommand(RedisCommand.BLMOVE, args,
				(cmd)->cmd.blmove(rawBinaryKey(key), rawBinaryKey(destKey), lMoveArgs(from, to),
						timeout),
				(cmd)->cmd.blmove(rawBinaryKey(key), rawBinaryKey(destKey), lMoveArgs(from, to),
						timeout), SafeEncoder::encode);
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
	                     final int timeout) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(from, to).add(timeout);
		return executeCommand(RedisCommand.BLMOVE, args,
				(cmd)->cmd.blmove(rawKey(key), rawKey(destKey), lMoveArgs(from, to), timeout),
				(cmd)->cmd.blmove(rawKey(key), rawKey(destKey), lMoveArgs(from, to), timeout));
	}

	@Override
	public KeyValue<String, List<String>> blMPop(final int timeout, final String[] keys, final Direction direction) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(direction);
		return stringBlMPop(rawBinaryKeys(keys), timeout, lMPopArgs(direction), args);
	}

	@Override
	public KeyValue<byte[], List<byte[]>> blMPop(final int timeout, final byte[][] keys, final Direction direction) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(direction);
		return binaryBlMPop(rawKeys(keys), timeout, lMPopArgs(direction), args);
	}

	@Override
	public KeyValue<String, List<String>> blMPop(final int timeout, final String[] keys, final Direction direction,
	                                             final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(direction)
				.add(Keyword.Common.COUNT, count);
		return stringBlMPop(rawBinaryKeys(keys), timeout, lMPopArgs(direction, count), args);
	}

	@Override
	public KeyValue<byte[], List<byte[]>> blMPop(final int timeout, final byte[][] keys, final Direction direction,
	                                             final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(direction)
				.add(Keyword.Common.COUNT, count);
		return binaryBlMPop(rawKeys(keys), timeout, lMPopArgs(direction, count), args);
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BLPOP, args, (cmd)->cmd.blpop(timeout, rawBinaryKeys(keys)),
				(cmd)->cmd.blpop(timeout, rawBinaryKeys(keys)),
				(v)->ListBuilder.of(SafeEncoder.encode(v.getValue())));
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BLPOP, args, (cmd)->cmd.blpop(timeout, rawKeys(keys)),
				(cmd)->cmd.blpop(timeout, rawKeys(keys)),
				(v)->ListBuilder.of(v.getValue()));
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BRPOP, args, (cmd)->cmd.brpop(timeout, rawBinaryKeys(keys)),
				(cmd)->cmd.brpop(timeout, rawBinaryKeys(keys)),
				(v)->ListBuilder.of(SafeEncoder.encode(v.getValue())));
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BRPOP, args, (cmd)->cmd.brpop(timeout, rawKeys(keys)),
				(cmd)->cmd.brpop(timeout, rawKeys(keys)),
				(v)->ListBuilder.of(v.getValue()));
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create(key, destKey).add(timeout);
		return executeCommand(RedisCommand.BRPOPLPUSH, args,
				(cmd)->cmd.brpoplpush(timeout, rawBinaryKey(key), rawBinaryKey(destKey)),
				(cmd)->cmd.brpoplpush(timeout, rawBinaryKey(key), rawBinaryKey(destKey)), SafeEncoder::encode);
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create(key, destKey).add(timeout);
		return executeCommand(RedisCommand.BRPOPLPUSH, args,
				(cmd)->cmd.brpoplpush(timeout, rawKey(key), rawKey(destKey)),
				(cmd)->cmd.brpoplpush(timeout, rawKey(key), rawKey(destKey)));
	}

	@Override
	public String lIndex(final String key, final long index) {
		final CommandArguments args = CommandArguments.create(key).add(index);
		return executeCommand(RedisCommand.LINDEX, args,
				(cmd)->cmd.lindex(rawBinaryKey(key), index),
				(cmd)->cmd.lindex(rawBinaryKey(key), index), SafeEncoder::encode);
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index) {
		final CommandArguments args = CommandArguments.create(key).add(index);
		return executeCommand(RedisCommand.LINDEX, args, (cmd)->cmd.lindex(rawKey(key), index),
				(cmd)->cmd.lindex(rawKey(key), index));
	}

	@Override
	public Long lInsert(final String key, final Position position, final String pivot, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(position).add(pivot).add(value);
		return executeCommand(RedisCommand.LINSERT, args,
				(cmd)->cmd.linsert(rawBinaryKey(key), Position.BEFORE == position, SafeEncoder.encode(pivot),
						SafeEncoder.encode(value)),
				(cmd)->cmd.linsert(rawBinaryKey(key), Position.BEFORE == position, SafeEncoder.encode(pivot),
						SafeEncoder.encode(value)));
	}

	@Override
	public Long lInsert(final byte[] key, final Position position, final byte[] pivot, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(position).add(pivot).add(value);
		return executeCommand(RedisCommand.LINSERT, args,
				(cmd)->cmd.linsert(key, Position.BEFORE == position, pivot, value),
				(cmd)->cmd.linsert(key, Position.BEFORE == position, pivot, value));
	}

	@Override
	public Long lLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.LLEN, args, (cmd)->cmd.llen(rawBinaryKey(key)),
				(cmd)->cmd.llen(rawBinaryKey(key)));
	}

	@Override
	public Long lLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.LLEN, args, (cmd)->cmd.llen(rawKey(key)), (cmd)->cmd.llen(rawKey(key)));
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create(key, destKey).add(from, to);
		return executeCommand(RedisCommand.LMOVE, args,
				(cmd)->cmd.lmove(rawBinaryKey(key), rawBinaryKey(destKey), lMoveArgs(from, to)),
				(cmd)->cmd.lmove(rawBinaryKey(key), rawBinaryKey(destKey), lMoveArgs(from, to)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create(key, destKey).add(from, to);
		return executeCommand(RedisCommand.LMOVE, args,
				(cmd)->cmd.lmove(rawKey(key), rawKey(destKey), lMoveArgs(from, to)),
				(cmd)->cmd.lmove(rawKey(key), rawKey(destKey), lMoveArgs(from, to)));
	}

	@Override
	public KeyValue<String, List<String>> lMPop(final String[] keys, final Direction direction) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(direction);
		return stringLMPop(rawBinaryKeys(keys), lMPopArgs(direction), args);
	}

	@Override
	public KeyValue<byte[], List<byte[]>> lMPop(final byte[][] keys, final Direction direction) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(direction);
		return binaryLMPop(rawKeys(keys), lMPopArgs(direction), args);
	}

	@Override
	public KeyValue<String, List<String>> lMPop(final String[] keys, final Direction direction, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(direction)
				.add(Keyword.Common.COUNT, count);
		return stringLMPop(rawBinaryKeys(keys), lMPopArgs(direction).count(count), args);
	}

	@Override
	public KeyValue<byte[], List<byte[]>> lMPop(final byte[][] keys, final Direction direction, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(direction)
				.add(Keyword.Common.COUNT, count);
		return binaryLMPop(rawKeys(keys), lMPopArgs(direction).count(count), args);
	}

	@Override
	public String lPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.LPOP, args, (cmd)->cmd.lpop(rawBinaryKey(key)),
				(cmd)->cmd.lpop(rawBinaryKey(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] lPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.LPOP, args, (cmd)->cmd.lpop(rawKey(key)), (cmd)->cmd.lpop(rawKey(key)));
	}

	@Override
	public List<String> lPop(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.LPOP, args, (cmd)->cmd.lpop(rawBinaryKey(key), count),
				(cmd)->cmd.lpop(rawBinaryKey(key), count), new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> lPop(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.LPOP, args, (cmd)->cmd.lpop(rawKey(key), count),
				(cmd)->cmd.lpop(rawKey(key), count));
	}

	@Override
	public Long lPos(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.LPOS, args, (cmd)->cmd.lpos(rawBinaryKey(key), SafeEncoder.encode(element)),
				(cmd)->cmd.lpos(rawBinaryKey(key), SafeEncoder.encode(element)));
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key, element);
		return executeCommand(RedisCommand.LPOS, args, (cmd)->cmd.lpos(rawKey(key), element),
				(cmd)->cmd.lpos(rawKey(key), element));
	}

	@Override
	public Long lPos(final String key, final String element, final LPosArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument);
		return executeCommand(RedisCommand.LPOS, args,
				(cmd)->cmd.lpos(rawBinaryKey(key), SafeEncoder.encode(element), new LettuceLPosArgs(argument)),
				(cmd)->cmd.lpos(rawBinaryKey(key), SafeEncoder.encode(element), new LettuceLPosArgs(argument)));
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element, final LPosArgument argument) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument);
		return executeCommand(RedisCommand.LPOS, args,
				(cmd)->cmd.lpos(rawKey(key), element, new LettuceLPosArgs(argument)),
				(cmd)->cmd.lpos(rawKey(key), element, new LettuceLPosArgs(argument)));
	}

	@Override
	public List<Long> lPos(final String key, final String element, final LPosArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.LPOS, args,
				(cmd)->cmd.lpos(rawBinaryKey(key), SafeEncoder.encode(element), count, new LettuceLPosArgs(argument)),
				(cmd)->cmd.lpos(rawBinaryKey(key), SafeEncoder.encode(element), count, new LettuceLPosArgs(argument)));
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(argument)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.LPOS, args,
				(cmd)->cmd.lpos(rawKey(key), element, count, new LettuceLPosArgs(argument)),
				(cmd)->cmd.lpos(rawKey(key), element, count, new LettuceLPosArgs(argument)));
	}

	@Override
	public List<Long> lPos(final String key, final String element, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.LPOS, args,
				(cmd)->cmd.lpos(rawBinaryKey(key), SafeEncoder.encode(element), count),
				(cmd)->cmd.lpos(rawBinaryKey(key), SafeEncoder.encode(element), count));
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final int count) {
		final CommandArguments args = CommandArguments.create(key, element).add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.LPOS, args, (cmd)->cmd.lpos(rawKey(key), element, count),
				(cmd)->cmd.lpos(rawKey(key), element, count));
	}

	@Override
	public Long lPush(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.LPUSH, args,
				(cmd)->cmd.lpush(rawBinaryKey(key), SafeEncoder.encode(values)),
				(cmd)->cmd.lpush(rawBinaryKey(key), SafeEncoder.encode(values)));
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.LPUSH, args, (cmd)->cmd.lpush(rawKey(key), values),
				(cmd)->cmd.lpush(rawKey(key), values));
	}

	@Override
	public Long lPushX(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.LPUSHX, args,
				(cmd)->cmd.lpushx(rawBinaryKey(key), SafeEncoder.encode(values)),
				(cmd)->cmd.lpushx(rawBinaryKey(key), SafeEncoder.encode(values)));
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.LPUSHX, args, (cmd)->cmd.lpushx(rawKey(key), values),
				(cmd)->cmd.lpushx(rawKey(key), values));
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.LRANGE, args, (cmd)->cmd.lrange(rawBinaryKey(key), start, end),
				(cmd)->cmd.lrange(rawBinaryKey(key), start, end), new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.LRANGE, args, (cmd)->cmd.lrange(rawKey(key), start, end),
				(cmd)->cmd.lrange(rawKey(key), start, end));
	}

	@Override
	public Long lRem(final String key, final String value, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(count);
		return executeCommand(RedisCommand.LREM, args,
				(cmd)->cmd.lrem(rawBinaryKey(key), count, SafeEncoder.encode(value)),
				(cmd)->cmd.lrem(rawBinaryKey(key), count, SafeEncoder.encode(value)));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(count);
		return executeCommand(RedisCommand.LREM, args, (cmd)->cmd.lrem(rawKey(key), count, value),
				(cmd)->cmd.lrem(rawKey(key), count, value));
	}

	@Override
	public Status lSet(final String key, final long index, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(index).add(value);
		return executeCommand(RedisCommand.LSET, args,
				(cmd)->cmd.lset(rawBinaryKey(key), index, SafeEncoder.encode(value)),
				(cmd)->cmd.lset(rawBinaryKey(key), index, SafeEncoder.encode(value)), new OkStatusConverter());
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(index).add(value);
		return executeCommand(RedisCommand.LSET, args, (cmd)->cmd.lset(rawKey(key), index, value),
				(cmd)->cmd.lset(rawKey(key), index, value), new OkStatusConverter());
	}

	@Override
	public Status lTrim(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.LTRIM, args, (cmd)->cmd.ltrim(rawBinaryKey(key), start, end),
				(cmd)->cmd.ltrim(rawBinaryKey(key), start, end), new OkStatusConverter());
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.LTRIM, args, (cmd)->cmd.ltrim(rawKey(key), start, end),
				(cmd)->cmd.ltrim(rawKey(key), start, end), new OkStatusConverter());
	}

	@Override
	public String rPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.RPOP, args, (cmd)->cmd.rpop(rawBinaryKey(key)),
				(cmd)->cmd.rpop(rawBinaryKey(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] rPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.RPOP, args, (cmd)->cmd.rpop(rawKey(key)), (cmd)->cmd.rpop(rawKey(key)));
	}

	@Override
	public List<String> rPop(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.RPOP, args, (cmd)->cmd.rpop(rawBinaryKey(key), count),
				(cmd)->cmd.rpop(rawBinaryKey(key), count), new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> rPop(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.RPOP, args, (cmd)->cmd.rpop(rawKey(key), count),
				(cmd)->cmd.rpop(rawKey(key), count));
	}

	@Override
	public String rPoplPush(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key, destKey);
		return executeCommand(RedisCommand.RPOPLPUSH, args,
				(cmd)->cmd.rpoplpush(rawBinaryKey(key), rawBinaryKey(destKey)),
				(cmd)->cmd.rpoplpush(rawBinaryKey(key), rawBinaryKey(destKey)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key, destKey);
		return executeCommand(RedisCommand.RPOPLPUSH, args, (cmd)->cmd.rpoplpush(rawKey(key), rawKey(destKey)),
				(cmd)->cmd.rpoplpush(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Long rPush(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.RPUSH, args,
				(cmd)->cmd.rpush(rawBinaryKey(key), SafeEncoder.encode(values)),
				(cmd)->cmd.rpush(rawBinaryKey(key), SafeEncoder.encode(values)));
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.RPUSH, args, (cmd)->cmd.rpush(rawKey(key), values),
				(cmd)->cmd.rpush(rawKey(key), values));
	}

	@Override
	public Long rPushX(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.RPUSHX, args,
				(cmd)->cmd.rpushx(rawBinaryKey(key), SafeEncoder.encode(values)),
				(cmd)->cmd.rpushx(rawBinaryKey(key), SafeEncoder.encode(values)));
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.RPUSHX, args, (cmd)->cmd.rpushx(rawKey(key), values),
				(cmd)->cmd.rpushx(rawKey(key), values));
	}

	private KeyValue<String, List<String>> stringBlMPop(final byte[][] keys, final int timeout,
	                                                    final LMPopArgs lmPopArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.BLMPOP, args, (cmd)->cmd.blmpop(timeout, lmPopArgs, keys),
				(cmd)->cmd.blmpop(timeout, lmPopArgs, keys),
				new KeyValueConverter<>(SafeEncoder::encode, new BinaryListStringListConverter()));
	}

	private KeyValue<byte[], List<byte[]>> binaryBlMPop(final byte[][] keys, final int timeout,
	                                                    final LMPopArgs lmPopArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.BLMPOP, args, (cmd)->cmd.blmpop(timeout, lmPopArgs, keys),
				(cmd)->cmd.blmpop(timeout, lmPopArgs, keys), new KeyValueConverter<>((k)->k, (v)->v));
	}

	private KeyValue<String, List<String>> stringLMPop(final byte[][] keys, final LMPopArgs lmPopArgs,
	                                                   final CommandArguments args) {
		return executeCommand(RedisCommand.LMPOP, args, (cmd)->cmd.lmpop(lmPopArgs, keys),
				(cmd)->cmd.lmpop(lmPopArgs, keys),
				new KeyValueConverter<>(SafeEncoder::encode, new BinaryListStringListConverter()));
	}

	private KeyValue<byte[], List<byte[]>> binaryLMPop(final byte[][] keys, final LMPopArgs lmPopArgs,
	                                                   final CommandArguments args) {
		return executeCommand(RedisCommand.LMPOP, args, (cmd)->cmd.lmpop(lmPopArgs, keys),
				(cmd)->cmd.lmpop(lmPopArgs, keys), new KeyValueConverter<>((k)->k, (v)->v));
	}

	private static LMoveArgs lMoveArgs(final Direction source, final Direction destination) {
		if(source == null || destination == null){
			return null;
		}

		if(Direction.LEFT.equals(source)){
			return Direction.LEFT.equals(destination) ? LMoveArgs.Builder.leftLeft() : LMoveArgs.Builder.leftRight();
		}else{
			return Direction.LEFT.equals(destination) ? LMoveArgs.Builder.rightLeft() : LMoveArgs.Builder.rightRight();
		}
	}

	private static LMPopArgs lMPopArgs(final Direction direction) {
		return lMPopArgs(direction, null);
	}

	private static LMPopArgs lMPopArgs(final Direction direction, final Integer count) {
		if(direction == null){
			return null;
		}

		final LMPopArgs lmPopArgs = Direction.LEFT.equals(direction) ?
				LMPopArgs.Builder.left() : LMPopArgs.Builder.right();

		if(count != null){
			lmPopArgs.count(count);
		}

		return lmPopArgs;
	}

}
