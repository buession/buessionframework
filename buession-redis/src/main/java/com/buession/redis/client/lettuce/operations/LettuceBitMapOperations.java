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
package com.buession.redis.client.lettuce.operations;

import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.BitMapOperations;
import com.buession.redis.core.BitCountOption;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.BitFieldArgument;
import com.buession.redis.core.command.args.BitFieldRoArgument;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.lettuce.CompositeArgumentUtils;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Lettuce BitMap 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceBitMapOperations extends AbstractLettuceRedisOperations implements
		BitMapOperations {

	public LettuceBitMapOperations(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Long bitCount(final String key) {
		return bitCount(SafeEncoder.encode(key));
	}

	@Override
	public Long bitCount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.BITCOUNT)
				.executor((cmd)->cmd.bitcount(key))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Long bitCount(final String key, final long start, final long end) {
		return bitCount(SafeEncoder.encode(key), start, end);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.BITCOUNT)
				.executor((cmd)->cmd.bitcount(key, start, end))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Long bitCount(final String key, final long start, final long end, final BitCountOption option) {
		return bitCount(SafeEncoder.encode(key), start, end, option);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end, final BitCountOption option) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(option);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.BITCOUNT)
				.executor((cmd)->cmd.bitcount(key, start, end))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public List<Long> bitField(final String key, final BitFieldArgument argument) {
		return bitField(SafeEncoder.encode(key), argument);
	}

	@Override
	public List<Long> bitField(final byte[] key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.BITFIELD)
				.executor((cmd)->cmd.bitfield(key, CompositeArgumentUtils.bitFieldArgs(argument)))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public List<Long> bitFieldRo(final String key, final BitFieldRoArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.BITFIELD_RO)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final BitFieldRoArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.BITFIELD_RO)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		return bitOp(operation, SafeEncoder.encode(destKey), SafeEncoder.encode(keys));
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey)
				.add(keys);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.BITOP)
				.executor((cmd)->{
					if(operation == BitOperation.AND){
						return cmd.bitopAnd(destKey, keys);
					}else if(operation == BitOperation.OR){
						return cmd.bitopOr(destKey, keys);
					}else if(operation == BitOperation.NOT){
						return cmd.bitopNot(destKey, keys[0]);
					}else if(operation == BitOperation.XOR){
						return cmd.bitopXor(destKey, keys);
					}else{
						return null;
					}
				})
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Long bitPos(final String key, final boolean value) {
		return bitPos(SafeEncoder.encode(key), value);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.BITPOS)
				.executor((cmd)->cmd.bitpos(key, value))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end) {
		return bitPos(SafeEncoder.encode(key), value, start, end);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start)
				.add(end);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.BITPOS)
				.executor((cmd)->cmd.bitpos(key, value, start, end))
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Boolean getBit(final String key, final long offset) {
		return getBit(SafeEncoder.encode(key), offset);
	}

	@Override
	public Boolean getBit(final byte[] key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);
		return LettuceCommandBuilder.<Long, Boolean>newBuilder(client, Command.GETBIT)
				.executor((cmd)->cmd.getbit(key, offset))
				.arguments(args)
				.converter(Converters.oneBooleanConverter())
				.run();
	}

	@Override
	public Boolean setBit(final String key, final long offset, final boolean value) {
		return setBit(SafeEncoder.encode(key), offset, value);
	}

	@Override
	public Boolean setBit(final byte[] key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return LettuceCommandBuilder.<Long, Boolean>newBuilder(client, Command.SETBIT)
				.executor((cmd)->cmd.setbit(key, offset, value ? 1 : 0))
				.arguments(args)
				.converter(Converters.oneBooleanConverter())
				.run();
	}

}
