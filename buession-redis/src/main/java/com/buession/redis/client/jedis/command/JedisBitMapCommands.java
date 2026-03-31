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

import com.buession.core.collect.Arrays;
import com.buession.core.utils.StringUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.args.bitmap.BitOperation;
import com.buession.redis.core.command.args.bitmap.BitType;
import com.buession.redis.core.command.BitMapCommands;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.bitmap.BitFieldArgument;
import com.buession.redis.core.command.args.bitmap.BitFieldRoArgument;
import com.buession.redis.core.internal.convert.jedis.params.BitOperationConverter;
import com.buession.redis.core.internal.convert.jedis.params.BitTypeConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.params.BitPosParams;

import java.util.List;

/**
 * Jedis BitMap 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisBitMapCommands extends AbstractJedisRedisCommands implements BitMapCommands {

	public JedisBitMapCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Long bitCount(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.BITCOUNT, args, (cmd)->cmd.bitcount(rawKey(key)));
	}

	@Override
	public Long bitCount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.BITCOUNT, args, (cmd)->cmd.bitcount(rawKey(key)));
	}

	@Override
	public Long bitCount(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.BITCOUNT, args, (cmd)->cmd.bitcount(rawKey(key), start, end));
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.BITCOUNT, args, (cmd)->cmd.bitcount(rawKey(key), start, end));
	}

	@Override
	public Long bitCount(final String key, final long start, final long end, final BitType type) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type);
		final BitTypeConverter bitTypeConverter = new BitTypeConverter();
		return executeCommand(RedisCommand.BITCOUNT, args,
				(cmd)->cmd.bitcount(rawKey(key), start, end, bitTypeConverter.convert(type)));
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end, final BitType type) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type);
		final BitTypeConverter bitTypeConverter = new BitTypeConverter();
		return executeCommand(RedisCommand.BITCOUNT, args,
				(cmd)->cmd.bitcount(rawKey(key), start, end, bitTypeConverter.convert(type)));
	}

	@Override
	public List<Long> bitField(final String key, final BitFieldArgument... arguments) {
		final CommandArguments args = CommandArguments.create(key).add(arguments);
		final String[] temp = StringUtils.split(StringUtils.join(arguments, " "), " ");
		return executeCommand(RedisCommand.BITFIELD, args, (cmd)->cmd.bitfield(rawKey(key), temp));
	}

	@Override
	public List<Long> bitField(final byte[] key, final BitFieldArgument... arguments) {
		final CommandArguments args = CommandArguments.create(key).add(arguments);
		final byte[][] temp = Arrays.map(StringUtils.split(StringUtils.join(arguments, " "), " "), byte[].class,
				SafeEncoder::encode);
		return executeCommand(RedisCommand.BITFIELD, args, (cmd)->cmd.bitfield(rawKey(key), temp));
	}

	@Override
	public List<Long> bitFieldRo(final String key, final BitFieldRoArgument... arguments) {
		final CommandArguments args = CommandArguments.create(key).add(arguments);
		final String[] temp = StringUtils.split(StringUtils.join(arguments, " "), " ");
		return executeCommand(RedisCommand.BITFIELD_RO, args, (cmd)->cmd.bitfieldReadonly(rawKey(key), temp));
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final BitFieldRoArgument... arguments) {
		final CommandArguments args = CommandArguments.create(key).add(arguments);
		final byte[][] temp = Arrays.map(StringUtils.split(StringUtils.join(arguments, " "), " "), byte[].class,
				SafeEncoder::encode);
		return executeCommand(RedisCommand.BITFIELD_RO, args, (cmd)->cmd.bitfieldReadonly(rawKey(key), temp));
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey).add(keys);
		final BitOperationConverter bitOperationConverter = new BitOperationConverter();
		return executeCommand(RedisCommand.BITOP, args,
				(cmd)->cmd.bitop(bitOperationConverter.convert(operation), rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey).add(keys);
		final BitOperationConverter bitOperationConverter = new BitOperationConverter();
		return executeCommand(RedisCommand.BITOP, args,
				(cmd)->cmd.bitop(bitOperationConverter.convert(operation), rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long bitPos(final String key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.BITPOS, args, (cmd)->cmd.bitpos(rawKey(key), value));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.BITPOS, args, (cmd)->cmd.bitpos(rawKey(key), value));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start);
		return executeCommand(RedisCommand.BITPOS, args,
				(cmd)->cmd.bitpos(rawKey(key), value, new BitPosParams(start)));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start);
		return executeCommand(RedisCommand.BITPOS, args,
				(cmd)->cmd.bitpos(rawKey(key), value, new BitPosParams(start)));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start, end);
		return executeCommand(RedisCommand.BITPOS, args,
				(cmd)->cmd.bitpos(rawKey(key), value, new BitPosParams(start, end)));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start, end);
		return executeCommand(RedisCommand.BITPOS, args,
				(cmd)->cmd.bitpos(rawKey(key), value, new BitPosParams(start, end)));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end, final BitType type) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start, end).add(type);
		final BitTypeConverter bitTypeConverter = new BitTypeConverter();
		final BitPosParams bitPosParams = new BitPosParams(start, end);

		bitPosParams.modifier(bitTypeConverter.convert(type));

		return executeCommand(RedisCommand.BITPOS, args, (cmd)->cmd.bitpos(rawKey(key), value, bitPosParams));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end, final BitType type) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start, end).add(type);
		final BitTypeConverter bitTypeConverter = new BitTypeConverter();
		final BitPosParams bitPosParams = new BitPosParams(start, end);

		bitPosParams.modifier(bitTypeConverter.convert(type));

		return executeCommand(RedisCommand.BITPOS, args, (cmd)->cmd.bitpos(rawKey(key), value, bitPosParams));
	}

	@Override
	public Boolean getBit(final String key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);
		return executeCommand(RedisCommand.GETBIT, args, (cmd)->cmd.getbit(rawKey(key), offset));
	}

	@Override
	public Boolean getBit(final byte[] key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);
		return executeCommand(RedisCommand.GETBIT, args, (cmd)->cmd.getbit(rawKey(key), offset));
	}

	@Override
	public Boolean setBit(final String key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(RedisCommand.SETBIT, args, (cmd)->cmd.setbit(rawKey(key), offset, value));
	}

	@Override
	public Boolean setBit(final byte[] key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(RedisCommand.SETBIT, args, (cmd)->cmd.setbit(rawKey(key), offset, value));
	}

}
