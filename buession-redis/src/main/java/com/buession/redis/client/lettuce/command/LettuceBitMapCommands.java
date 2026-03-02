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

import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.BitCountOption;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.command.BitMapCommands;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.BitFieldArgument;
import com.buession.redis.core.command.args.BitFieldRoArgument;
import com.buession.redis.core.internal.convert.lettuce.params.BitFieldArgumentConverter;
import com.buession.redis.core.internal.convert.response.OneBooleanConverter;

import java.util.Arrays;
import java.util.List;

/**
 * Lettuce BitMap 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceBitMapCommands extends AbstractLettuceRedisCommands implements BitMapCommands {

	public LettuceBitMapCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Long bitCount(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.BITCOUNT, args, (cmd)->cmd.bitcount(rawBinaryKey(key)), (v)->v);
	}

	@Override
	public Long bitCount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.BITCOUNT, args, (cmd)->cmd.bitcount(rawKey(key)), (v)->v);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.BITCOUNT, args, (cmd)->cmd.bitcount(rawBinaryKey(key), start, end), (v)->v);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.BITCOUNT, args, (cmd)->cmd.bitcount(rawKey(key), start, end), (v)->v);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end, final BitCountOption option) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(option);
		return executeCommand(Command.BITCOUNT, args, (cmd)->cmd.bitcount(rawBinaryKey(key), start, end), (v)->v);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end, final BitCountOption option) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(option);
		return executeCommand(Command.BITCOUNT, args, (cmd)->cmd.bitcount(rawKey(key), start, end), (v)->v);
	}

	@Override
	public List<Long> bitField(final String key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return bitField(rawBinaryKey(key), argument, args);
	}

	@Override
	public List<Long> bitField(final byte[] key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return bitField(rawKey(key), argument, args);
	}

	@Override
	public List<Long> bitFieldRo(final String key, final BitFieldRoArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.BITFIELD_RO, args);
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final BitFieldRoArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.BITFIELD_RO, args);
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey).add(keys);
		return bitOp(operation, rawBinaryKey(destKey), rawBinaryKeys(keys), args);
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey).add(keys);
		return bitOp(operation, rawKey(destKey), rawKeys(keys), args);
	}

	@Override
	public Long bitPos(final String key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.BITPOS, args, (cmd)->cmd.bitpos(rawBinaryKey(key), value), (v)->v);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.BITPOS, args, (cmd)->cmd.bitpos(rawKey(key), value), (v)->v);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(end);
		return executeCommand(Command.BITPOS, args, (cmd)->cmd.bitpos(rawBinaryKey(key), value, start, end), (v)->v);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start).add(end);
		return executeCommand(Command.BITPOS, args, (cmd)->cmd.bitpos(rawKey(key), value, start, end), (v)->v);
	}

	@Override
	public Boolean getBit(final String key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);
		return executeCommand(Command.GETBIT, args, (cmd)->cmd.getbit(rawBinaryKey(key), offset),
				new OneBooleanConverter());
	}

	@Override
	public Boolean getBit(final byte[] key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);
		return executeCommand(Command.GETBIT, args, (cmd)->cmd.getbit(rawKey(key), offset), new OneBooleanConverter());
	}

	@Override
	public Boolean setBit(final String key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(Command.SETBIT, args, (cmd)->cmd.setbit(rawBinaryKey(key), offset, value ? 1 : 0),
				new OneBooleanConverter());
	}

	@Override
	public Boolean setBit(final byte[] key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		return executeCommand(Command.SETBIT, args, (cmd)->cmd.setbit(rawKey(key), offset, value ? 1 : 0),
				new OneBooleanConverter());
	}

	private List<Long> bitField(final byte[] key, final BitFieldArgument argument, final CommandArguments args) {
		final BitFieldArgumentConverter bitFieldArgumentConverter = new BitFieldArgumentConverter();
		return executeCommand(Command.BITFIELD, args,
				(cmd)->cmd.bitfield(key, bitFieldArgumentConverter.convert(argument)), (v)->v);
	}

	private Long bitOp(final BitOperation operation, final byte[] destKey, final byte[][] keys,
					   final CommandArguments args) {
		return executeCommand(Command.BITOP, args, (cmd)->{
			if(operation == BitOperation.AND){
				return cmd.bitopAnd(destKey, keys);
			}else if(operation == BitOperation.OR){
				return cmd.bitopOr(destKey, keys);
			}else if(operation == BitOperation.XOR){
				return cmd.bitopXor(destKey, keys);
			}else if(operation == BitOperation.NOT){
				return cmd.bitopNot(destKey, keys[0]);
			}else if(operation == BitOperation.DIFF){
				return cmd.bitopDiff(destKey, keys[0], Arrays.copyOfRange(keys, 1, keys.length));
			}else if(operation == BitOperation.DIFF1){
				return cmd.bitopDiff1(destKey, keys[0], Arrays.copyOfRange(keys, 1, keys.length));
			}else if(operation == BitOperation.ANDOR){
				return cmd.bitopAndor(destKey, keys[0], Arrays.copyOfRange(keys, 1, keys.length));
			}else if(operation == BitOperation.ONE){
				return cmd.bitopOne(destKey, keys);
			}else{
				return null;
			}
		}, (v)->v);
	}

}
