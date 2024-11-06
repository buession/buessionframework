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

import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.BitType;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.BitFieldArgument;
import com.buession.redis.core.command.args.BitFieldRoArgument;

import java.util.List;

/**
 * Lettuce 哨兵模式 BitMap 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelBitMapOperations extends AbstractBitMapOperations<LettuceSentinelClient> {

	public LettuceSentinelBitMapOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public Long bitCount(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.BITCOUNT, args);
	}

	@Override
	public Long bitCount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.BITCOUNT, args);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return notCommand(client, Command.BITCOUNT, args);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return notCommand(client, Command.BITCOUNT, args);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end, final BitType bitType) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(bitType);
		return notCommand(client, Command.BITCOUNT, args);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end, final BitType bitType) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(bitType);
		return notCommand(client, Command.BITCOUNT, args);
	}

	@Override
	public List<Long> bitField(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.BITFIELD, args);
	}

	@Override
	public List<Long> bitField(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.BITFIELD, args);
	}

	@Override
	public List<Long> bitField(final String key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return notCommand(client, Command.BITFIELD, args);
	}

	@Override
	public List<Long> bitField(final byte[] key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return notCommand(client, Command.BITFIELD, args);
	}

	@Override
	public List<Long> bitFieldRo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.BITFIELD_RO, args);
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.BITFIELD_RO, args);
	}

	@Override
	public List<Long> bitFieldRo(final String key, final BitFieldRoArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return notCommand(client, Command.BITFIELD_RO, args);
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final BitFieldRoArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return notCommand(client, Command.BITFIELD_RO, args);
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey).add(keys);
		return notCommand(client, Command.BITOP, args);
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey).add(keys);
		return notCommand(client, Command.BITOP, args);
	}

	@Override
	public Long bitPos(final String key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return notCommand(client, Command.BITPOS, args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return notCommand(client, Command.BITPOS, args);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start);
		return notCommand(client, Command.BITPOS, args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start);
		return notCommand(client, Command.BITPOS, args);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final BitType bitType) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(bitType);
		return notCommand(client, Command.BITPOS, args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final BitType bitType) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(bitType);
		return notCommand(client, Command.BITPOS, args);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(end);
		return notCommand(client, Command.BITPOS, args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(end);
		return notCommand(client, Command.BITPOS, args);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end,
					   final BitType bitType) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(end).add(bitType);
		return notCommand(client, Command.BITPOS, args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end,
					   final BitType bitType) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(end).add(bitType);
		return notCommand(client, Command.BITPOS, args);
	}

	@Override
	public Boolean getBit(final String key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);
		return notCommand(client, Command.GETBIT, args);
	}

	@Override
	public Boolean getBit(final byte[] key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);
		return notCommand(client, Command.GETBIT, args);
	}

	@Override
	public Boolean setBit(final String key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return notCommand(client, Command.SETBIT, args);
	}

	@Override
	public Boolean setBit(final byte[] key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return notCommand(client, Command.SETBIT, args);
	}

}
