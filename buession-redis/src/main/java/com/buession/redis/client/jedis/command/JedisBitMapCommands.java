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
import com.buession.redis.core.BitCountOption;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.command.BitMapCommands;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.BitFieldArgument;
import com.buession.redis.core.command.args.BitFieldRoArgument;
import com.buession.redis.core.internal.convert.jedis.params.BitCountOptionConverter;
import com.buession.redis.core.internal.convert.jedis.params.BitOperationConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.UnifiedJedis;
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
		return bitCount((cmd)->cmd.bitcount(rawKey(key)), args);
	}

	@Override
	public Long bitCount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return bitCount((cmd)->cmd.bitcount(rawKey(key)), args);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return bitCount((cmd)->cmd.bitcount(rawKey(key), start, end), args);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return bitCount((cmd)->cmd.bitcount(rawKey(key), start, end), args);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end, final BitCountOption option) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(option);
		final BitCountOptionConverter optionConverter = new BitCountOptionConverter();
		return bitCount((cmd)->cmd.bitcount(rawKey(key), start, end, optionConverter.convert(option)), args);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end, final BitCountOption option) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(option);
		final BitCountOptionConverter optionConverter = new BitCountOptionConverter();
		return bitCount((cmd)->cmd.bitcount(rawKey(key), start, end, optionConverter.convert(option)), args);
	}

	@Override
	public List<Long> bitField(final String key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final String[] temp = StringUtils.split(argument.toString(), " ");
		return executeCommand(Command.BITFIELD, args, (cmd)->cmd.bitfield(rawKey(key), temp), (v)->v);
	}

	@Override
	public List<Long> bitField(final byte[] key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final byte[][] temp = Arrays.map(StringUtils.split(argument.toString(), " "), byte[].class,
				SafeEncoder::encode);
		return executeCommand(Command.BITFIELD, args, (cmd)->cmd.bitfield(rawKey(key), temp), (v)->v);
	}

	@Override
	public List<Long> bitFieldRo(final String key, final BitFieldRoArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final String[] temp = StringUtils.split(argument.toString(), " ");
		return executeCommand(Command.BITFIELD_RO, args, (cmd)->cmd.bitfieldReadonly(rawKey(key), temp), (v)->v);
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final BitFieldRoArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final byte[][] temp = Arrays.map(StringUtils.split(argument.toString(), " "), byte[].class,
				SafeEncoder::encode);
		return executeCommand(Command.BITFIELD_RO, args, (cmd)->cmd.bitfieldReadonly(rawKey(key), temp), (v)->v);
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey).add(keys);
		final BitOperationConverter bitOperationConverter = new BitOperationConverter();
		return executeCommand(Command.BITOP, args,
				(cmd)->cmd.bitop(bitOperationConverter.convert(operation), rawKey(destKey), rawKeys(keys)), (v)->v);
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey).add(keys);
		final BitOperationConverter bitOperationConverter = new BitOperationConverter();
		return executeCommand(Command.BITOP, args,
				(cmd)->cmd.bitop(bitOperationConverter.convert(operation), rawKey(destKey), rawKeys(keys)), (v)->v);
	}

	@Override
	public Long bitPos(final String key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.BITOP, args, (cmd)->cmd.bitpos(rawKey(key), value), (v)->v);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.BITOP, args, (cmd)->cmd.bitpos(rawKey(key), value), (v)->v);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(end);
		return executeCommand(Command.BITOP, args, (cmd)->cmd.bitpos(rawKey(key), value, new BitPosParams(start, end)),
				(v)->v);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(end);
		return executeCommand(Command.BITOP, args, (cmd)->cmd.bitpos(rawKey(key), value, new BitPosParams(start, end)),
				(v)->v);
	}

	@Override
	public Boolean getBit(final String key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);
		return executeCommand(Command.GETBIT, args, (cmd)->cmd.getbit(rawKey(key), offset), (v)->v);
	}

	@Override
	public Boolean getBit(final byte[] key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);
		return executeCommand(Command.GETBIT, args, (cmd)->cmd.getbit(rawKey(key), offset), (v)->v);
	}

	@Override
	public Boolean setBit(final String key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(Command.SETBIT, args, (cmd)->cmd.setbit(rawKey(key), offset, value), (v)->v);
	}

	@Override
	public Boolean setBit(final byte[] key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(Command.SETBIT, args, (cmd)->cmd.setbit(rawKey(key), offset, value), (v)->v);
	}

	private Long bitCount(final com.buession.redis.core.Command.Executor<UnifiedJedis, Long> executor,
						  final CommandArguments args) {
		return executeCommand(Command.BITCOUNT, args, executor, (v)->v);
	}

}
