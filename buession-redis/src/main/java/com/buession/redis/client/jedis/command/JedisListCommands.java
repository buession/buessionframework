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

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.Direction;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.args.LPosArgument;
import com.buession.redis.core.internal.convert.jedis.params.DirectionConverter;
import com.buession.redis.core.internal.convert.jedis.params.ListPositionConverter;
import com.buession.redis.core.internal.convert.jedis.response.KeyValueConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.jedis.args.JedisLPosParams;
import redis.clients.jedis.UnifiedJedis;

import java.util.List;

/**
 * Jedis 列表命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisListCommands extends AbstractJedisRedisCommands implements ListCommands {

	public JedisListCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
						 final int timeout) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(from, to).add(timeout);
		final DirectionConverter directionConverter = new DirectionConverter();
		return executeCommand(Command.BLMOVE, args,
				(cmd)->cmd.blmove(rawKey(key), rawKey(destKey), directionConverter.convert(from),
						directionConverter.convert(to), timeout), (v)->v);
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(from, to).add(timeout);
		final DirectionConverter directionConverter = new DirectionConverter();
		return executeCommand(Command.BLMOVE, args,
				(cmd)->cmd.blmove(rawKey(key), rawKey(destKey), directionConverter.convert(from),
						directionConverter.convert(to), timeout), (v)->v);
	}

	@Override
	public KeyValue<String, List<String>> blMPop(final int timeout, final String[] keys, final Direction direction) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys);
		final DirectionConverter directionConverter = new DirectionConverter();
		return executeCommand(Command.BLMPOP, args,
				(cmd)->cmd.blmpop(timeout, directionConverter.convert(direction), rawKeys(keys)),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public KeyValue<byte[], List<byte[]>> blMPop(final int timeout, final byte[][] keys, final Direction direction) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys);
		final DirectionConverter directionConverter = new DirectionConverter();
		return executeCommand(Command.BLMPOP, args,
				(cmd)->cmd.blmpop(timeout, directionConverter.convert(direction), rawKeys(keys)),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BLPOP, args, (cmd)->cmd.blpop(timeout, rawKeys(keys)), (v)->v);
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BLPOP, args, (cmd)->cmd.blpop(timeout, rawKeys(keys)), (v)->v);
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BRPOP, args, (cmd)->cmd.brpop(timeout, rawKeys(keys)), (v)->v);
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BRPOP, args, (cmd)->cmd.brpop(timeout, rawKeys(keys)), (v)->v);
	}

	@Override
	public String lIndex(final String key, final long index) {
		final CommandArguments args = CommandArguments.create(key).add(index);
		return executeCommand(Command.LINDEX, args, (cmd)->cmd.lindex(rawKey(key), index), (v)->v);
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index) {
		final CommandArguments args = CommandArguments.create(key).add(index);
		return executeCommand(Command.LINDEX, args, (cmd)->cmd.lindex(rawKey(key), index), (v)->v);
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(timeout);
		return executeCommand(Command.BRPOPLPUSH, args, (cmd)->cmd.brpoplpush(rawKey(key), rawKey(destKey), timeout),
				(v)->v);
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(timeout);
		return executeCommand(Command.BRPOPLPUSH, args, (cmd)->cmd.brpoplpush(rawKey(key), rawKey(destKey), timeout),
				(v)->v);
	}

	@Override
	public Long lInsert(final String key, final ListPosition position, final String pivot, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(position).add(pivot).add(value);
		final ListPositionConverter listPositionConverter = new ListPositionConverter();
		return executeCommand(Command.LINSERT, args,
				(cmd)->cmd.linsert(rawKey(key), listPositionConverter.convert(position), pivot, value), (v)->v);
	}

	@Override
	public Long lInsert(final byte[] key, final ListPosition position, final byte[] pivot, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(position).add(pivot).add(value);
		final ListPositionConverter listPositionConverter = new ListPositionConverter();
		return executeCommand(Command.LINSERT, args,
				(cmd)->cmd.linsert(rawKey(key), listPositionConverter.convert(position), pivot, value), (v)->v);
	}

	@Override
	public Long lLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.LLEN, args, (cmd)->cmd.llen(rawKey(key)), (v)->v);
	}

	@Override
	public Long lLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.LLEN, args, (cmd)->cmd.llen(rawKey(key)), (v)->v);
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(from).add(to);
		final DirectionConverter directionConverter = new DirectionConverter();
		return executeCommand(Command.LMOVE, args,
				(cmd)->cmd.lmove(rawKey(key), rawKey(destKey), directionConverter.convert(from),
						directionConverter.convert(to)), (v)->v);
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(from).add(to);
		final DirectionConverter directionConverter = new DirectionConverter();
		return executeCommand(Command.LMOVE, args,
				(cmd)->cmd.lmove(rawKey(key), rawKey(destKey), directionConverter.convert(from),
						directionConverter.convert(to)), (v)->v);
	}

	@Override
	public KeyValue<String, List<String>> lMPop(final int timeout, final String[] keys, final Direction direction) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys).add(direction);
		final DirectionConverter directionConverter = new DirectionConverter();
		return executeCommand(Command.LMPOP, args,
				(cmd)->cmd.lmpop(directionConverter.convert(direction), timeout, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public KeyValue<byte[], List<byte[]>> lMPop(final int timeout, final byte[][] keys, final Direction direction) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys).add(direction);
		final DirectionConverter directionConverter = new DirectionConverter();
		return executeCommand(Command.LMPOP, args,
				(cmd)->cmd.lmpop(directionConverter.convert(direction), timeout, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public String lPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return lPop((cmd)->cmd.lpop(rawKey(key)), args);
	}

	@Override
	public byte[] lPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return lPop((cmd)->cmd.lpop(rawKey(key)), args);
	}

	@Override
	public List<String> lPop(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.COUNT, count);
		return lPop((cmd)->cmd.lpop(rawKey(key), count), args);
	}

	@Override
	public List<byte[]> lPop(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.COUNT, count);
		return lPop((cmd)->cmd.lpop(rawKey(key), count), args);
	}

	@Override
	public Long lPos(final String key, final String element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(Command.LPOS, args, (cmd)->cmd.lpos(rawKey(key), element), (v)->v);
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key).add(element);
		return executeCommand(Command.LPOS, args, (cmd)->cmd.lpos(rawKey(key), element), (v)->v);
	}

	@Override
	public List<Long> lPos(final String key, final String element, final LPosArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument);
		return lPos(key, element, new JedisLPosParams(argument), Integer.MAX_VALUE, args);
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument);
		return lPos(key, element, new JedisLPosParams(argument), Integer.MAX_VALUE, args);
	}

	@Override
	public List<Long> lPos(final String key, final String element, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(Keyword.Common.COUNT, count);
		return lPos(key, element, new JedisLPosParams(), count, args);
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(Keyword.Common.COUNT, count);
		return lPos(key, element, new JedisLPosParams(), count, args);
	}

	@Override
	public List<Long> lPos(final String key, final String element, final LPosArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument)
				.add(Keyword.Common.COUNT, count);
		return lPos(key, element, new JedisLPosParams(argument), count, args);
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(element).add(argument)
				.add(Keyword.Common.COUNT, count);
		return lPos(key, element, new JedisLPosParams(argument), count, args);
	}

	@Override
	public Long lPush(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(Command.LPOP, args, (cmd)->cmd.lpush(rawKey(key), values), (v)->v);
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(Command.LPOP, args, (cmd)->cmd.lpush(rawKey(key), values), (v)->v);
	}

	@Override
	public Long lPushX(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(Command.LPUSHX, args, (cmd)->cmd.lpushx(rawKey(key), values), (v)->v);
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(Command.LPUSHX, args, (cmd)->cmd.lpushx(rawKey(key), values), (v)->v);
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.LRANGE, args, (cmd)->cmd.lrange(rawKey(key), start, end), (v)->v);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.LRANGE, args, (cmd)->cmd.lrange(rawKey(key), start, end), (v)->v);
	}

	@Override
	public Long lRem(final String key, final String value, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(count);
		return executeCommand(Command.LREM, args, (cmd)->cmd.lrem(rawKey(key), count, value), (v)->v);
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(count);
		return executeCommand(Command.LREM, args, (cmd)->cmd.lrem(rawKey(key), count, value), (v)->v);
	}

	@Override
	public Status lSet(final String key, final long index, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(index).add(value);
		return executeCommand(Command.LSET, args, (cmd)->cmd.lset(rawKey(key), index, value), new OkStatusConverter());
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(index).add(value);
		return executeCommand(Command.LSET, args, (cmd)->cmd.lset(rawKey(key), index, value), new OkStatusConverter());
	}

	@Override
	public Status lTrim(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.LTRIM, args, (cmd)->cmd.ltrim(rawKey(key), start, end), new OkStatusConverter());
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.LTRIM, args, (cmd)->cmd.ltrim(rawKey(key), start, end), new OkStatusConverter());
	}

	@Override
	public String rPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.RPOP, args, (cmd)->cmd.rpop(rawKey(key)), (v)->v);
	}

	@Override
	public byte[] rPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.RPOP, args, (cmd)->cmd.rpop(rawKey(key)), (v)->v);
	}

	@Override
	public String rPoplPush(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return executeCommand(Command.RPOPLPUSH, args, (cmd)->cmd.rpoplpush(rawKey(key), rawKey(destKey)), (v)->v);
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return executeCommand(Command.RPOPLPUSH, args, (cmd)->cmd.rpoplpush(rawKey(key), rawKey(destKey)), (v)->v);
	}

	@Override
	public Long rPush(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(Command.RPUSH, args, (cmd)->cmd.rpush(rawKey(key), values), (v)->v);
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(Command.RPUSH, args, (cmd)->cmd.rpush(rawKey(key), values), (v)->v);
	}

	@Override
	public Long rPushX(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(Command.RPUSHX, args, (cmd)->cmd.rpushx(rawKey(key), values), (v)->v);
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(Command.RPUSHX, args, (cmd)->cmd.rpushx(rawKey(key), values), (v)->v);
	}

	private <T> T lPop(final com.buession.redis.core.Command.Executor<UnifiedJedis, T> executor,
					   final CommandArguments args) {
		return executeCommand(Command.LPOP, args, executor, (v)->v);
	}

	private List<Long> lPos(final String key, final String element, final JedisLPosParams lPosParams, final int count,
							final CommandArguments args) {
		return executeCommand(Command.LPOS, args,
				(cmd)->cmd.lpos(rawKey(key), element, lPosParams, count), (v)->v);
	}

	private List<Long> lPos(final byte[] key, final byte[] element, final JedisLPosParams lPosParams, final int count,
							final CommandArguments args) {
		return executeCommand(Command.LPOS, args,
				(cmd)->cmd.lpos(rawKey(key), element, lPosParams, count), (v)->v);
	}

}
