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

import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.BitCountOption;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.lettuce.LettuceBitFieldArgs;
import io.lettuce.core.BitFieldArgs;

import java.util.List;

/**
 * Lettuce 单机模式 BitMap 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceBitMapOperations extends AbstractBitMapOperations<LettuceStandaloneClient> {

	public LettuceBitMapOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Long bitCount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key, start, end),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end, final BitCountOption bitCountOption) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end)
				.add(bitCountOption);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key, start, end),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> bitField(final byte[] key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final BitFieldArgs bitFieldArgs = LettuceBitFieldArgs.from(argument);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.BITFIELD,
					(cmd)->cmd.bitfield(key, bitFieldArgs), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.BITFIELD,
					(cmd)->cmd.bitfield(key, bitFieldArgs), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.BITFIELD, (cmd)->cmd.bitfield(key, bitFieldArgs),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> bitFieldRo(final String key, final String... arguments) {
		final CommandArguments args = CommandArguments.create(key).add(arguments);
		return bitFieldRo(args);
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create(key).add(arguments);
		return bitFieldRo(args);
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey)
				.add(keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.BITOP, (cmd)->{
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
			}, (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.BITOP, (cmd)->{
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
			}, (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.BITOP, (cmd)->{
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
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.BITPOS, (cmd)->cmd.bitpos(key, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.BITPOS, (cmd)->cmd.bitpos(key, value),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.BITPOS, (cmd)->cmd.bitpos(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start)
				.add(end);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.BITPOS,
					(cmd)->cmd.bitpos(key, value, start, end), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.BITPOS,
					(cmd)->cmd.bitpos(key, value, start, end), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.BITPOS, (cmd)->cmd.bitpos(key, value, start, end),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean getBit(final byte[] key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.GETBIT, (cmd)->cmd.getbit(key, offset),
					oneBooleanConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.GETBIT, (cmd)->cmd.getbit(key, offset),
					oneBooleanConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.GETBIT, (cmd)->cmd.getbit(key, offset),
					oneBooleanConverter)
					.run(args);
		}
	}

	@Override
	public Boolean setBit(final byte[] key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);
		final int iValue = value ? 1 : 0;

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SETBIT,
					(cmd)->cmd.setbit(key, offset, iValue), oneBooleanConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SETBIT,
					(cmd)->cmd.setbit(key, offset, iValue), oneBooleanConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SETBIT, (cmd)->cmd.setbit(key, offset, iValue),
					oneBooleanConverter)
					.run(args);
		}
	}

	private List<Long> bitFieldRo(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<List<Long>, List<Long>>(client, ProtocolCommand.BITFIELD_RO)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<Long>, List<Long>>(client, ProtocolCommand.BITFIELD_RO)
					.run(args);
		}else{
			return new LettuceCommand<List<Long>, List<Long>>(client, ProtocolCommand.BITFIELD_RO)
					.run(args);
		}
	}

}
