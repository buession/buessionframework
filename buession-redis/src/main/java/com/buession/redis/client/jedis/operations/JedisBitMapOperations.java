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
package com.buession.redis.client.jedis.operations;

import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.BitMapOperations;
import com.buession.redis.core.BitCountOption;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.internal.convert.jedis.params.BitCountOptionConverter;
import com.buession.redis.core.internal.convert.jedis.params.BitFieldArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.BitFieldRoArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.BitOperationConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.params.BitPosParams;

import java.util.List;

/**
 * Jedis BitMap 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisBitMapOperations extends AbstractJedisRedisOperations implements BitMapOperations {

	public JedisBitMapOperations(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Long bitCount(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return bitCount((cmd)->cmd.bitcount(key), args);
	}

	@Override
	public Long bitCount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return bitCount((cmd)->cmd.bitcount(key), args);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return bitCount((cmd)->cmd.bitcount(key, start, end), args);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return bitCount((cmd)->cmd.bitcount(key, start, end), args);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end, final BitCountOption option) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(option);
		final BitCountOptionConverter optionConverter = new BitCountOptionConverter();
		return bitCount((cmd)->cmd.bitcount(key, start, end, optionConverter.convert(option)), args);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end, final BitCountOption option) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(option);
		final BitCountOptionConverter optionConverter = new BitCountOptionConverter();
		return bitCount((cmd)->cmd.bitcount(key, start, end, optionConverter.convert(option)), args);
	}

	@Override
	public List<Long> bitField(final String key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final BitFieldArgumentConverter bitFieldArgumentConverter = new BitFieldArgumentConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.BITFIELD)
				.executor((cmd)->cmd.bitfield(key, bitFieldArgumentConverter.convert(argument)))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public List<Long> bitField(final byte[] key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final BitFieldArgumentConverter bitFieldArgumentConverter = new BitFieldArgumentConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.BITFIELD)
				.executor((cmd)->cmd.bitfield(key, SafeEncoder.encode(bitFieldArgumentConverter.convert(argument))))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public List<Long> bitFieldRo(final String key, final BitFieldRoArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final BitFieldRoArgumentConverter bitFieldRoArgumentConverter = new BitFieldRoArgumentConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.BITFIELD_RO)
				.executor((cmd)->cmd.bitfieldReadonly(key, bitFieldRoArgumentConverter.convert(argument)))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final BitFieldRoArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final BitFieldRoArgumentConverter bitFieldRoArgumentConverter = new BitFieldRoArgumentConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.BITFIELD_RO)
				.executor((cmd)->cmd.bitfieldReadonly(key,
						SafeEncoder.encode(bitFieldRoArgumentConverter.convert(argument))))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey).add(keys);
		final BitOperationConverter bitOperationConverter = new BitOperationConverter();
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.BITOP)
				.executor((cmd)->cmd.bitop(bitOperationConverter.convert(operation), destKey, keys))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey).add(keys);
		final BitOperationConverter bitOperationConverter = new BitOperationConverter();
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.BITOP)
				.executor((cmd)->cmd.bitop(bitOperationConverter.convert(operation), destKey, keys))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Long bitPos(final String key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return bitPos((cmd)->cmd.bitpos(key, value), args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return bitPos((cmd)->cmd.bitpos(key, value), args);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(end);
		return bitPos((cmd)->cmd.bitpos(key, value, new BitPosParams(start, end)), args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(end);
		return bitPos((cmd)->cmd.bitpos(key, value, new BitPosParams(start, end)), args);
	}

	@Override
	public Boolean getBit(final String key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);
		return JedisCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.GETBIT)
				.executor((cmd)->cmd.getbit(key, offset))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Boolean getBit(final byte[] key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);
		return JedisCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.GETBIT)
				.executor((cmd)->cmd.getbit(key, offset))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Boolean setBit(final String key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return JedisCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.SETBIT)
				.executor((cmd)->cmd.setbit(key, offset, value))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Boolean setBit(final byte[] key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return JedisCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.SETBIT)
				.executor((cmd)->cmd.setbit(key, offset, value))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	private Long bitCount(final com.buession.redis.core.Command.Executor<UnifiedJedis, Long> executor,
						  final CommandArguments args) {
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.BITCOUNT)
				.executor(executor)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	private Long bitPos(final com.buession.redis.core.Command.Executor<UnifiedJedis, Long> executor,
						final CommandArguments args) {
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.BITPOS)
				.executor(executor)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

}
