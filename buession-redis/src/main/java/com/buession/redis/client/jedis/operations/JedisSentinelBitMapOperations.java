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
package com.buession.redis.client.jedis.operations;

import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.BitCountOption;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.params.BitCountOptionConverter;
import com.buession.redis.core.internal.convert.jedis.params.BitFieldArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.BitOperationConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.args.BitOP;
import redis.clients.jedis.params.BitPosParams;

import java.util.List;

/**
 * Jedis 哨兵模式 BitMap 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelBitMapOperations extends AbstractBitMapOperations<JedisSentinelClient> {

	public JedisSentinelBitMapOperations(final JedisSentinelClient client) {
		super(client);
	}

	@Override
	public Long bitCount(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitCount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitCount(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key, start, end),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITCOUNT, (cmd)->cmd.bitcount(key, start, end),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitCount(final String key, final long start, final long end, final BitCountOption bitCountOption) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end)
				.add(bitCountOption);
		final redis.clients.jedis.args.BitCountOption option = (new BitCountOptionConverter()).convert(
				bitCountOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end, option), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end, option), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end, option), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end, final BitCountOption bitCountOption) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end)
				.add(bitCountOption);
		final redis.clients.jedis.args.BitCountOption option = (new BitCountOptionConverter()).convert(
				bitCountOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end, option), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end, option), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITCOUNT,
					(cmd)->cmd.bitcount(key, start, end, option), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> bitField(final String key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final String[] arguments = (new BitFieldArgumentConverter()).convert(argument);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITFIELD,
					(cmd)->cmd.bitfield(key, arguments), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITFIELD,
					(cmd)->cmd.bitfield(key, arguments), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITFIELD, (cmd)->cmd.bitfield(key, arguments),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> bitField(final byte[] key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final byte[][] arguments = SafeEncoder.encode((new BitFieldArgumentConverter()).convert(argument));

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITFIELD,
					(cmd)->cmd.bitfield(key, arguments), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITFIELD,
					(cmd)->cmd.bitfield(key, arguments), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITFIELD, (cmd)->cmd.bitfield(key, arguments),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> bitFieldRo(final String key, final String... arguments) {
		final CommandArguments args = CommandArguments.create(key).add(arguments);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITFIELD_RO,
					(cmd)->cmd.bitfieldReadonly(key, arguments), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITFIELD_RO,
					(cmd)->cmd.bitfieldReadonly(key, arguments), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITFIELD_RO,
					(cmd)->cmd.bitfieldReadonly(key, arguments), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create(key).add(arguments);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITFIELD_RO,
					(cmd)->cmd.bitfieldReadonly(key, arguments), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITFIELD_RO,
					(cmd)->cmd.bitfieldReadonly(key, arguments), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITFIELD_RO,
					(cmd)->cmd.bitfieldReadonly(key, arguments), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey)
				.add(keys);
		final BitOP bitOP = (new BitOperationConverter()).convert(operation);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITOP,
					(cmd)->cmd.bitop(bitOP, destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITOP,
					(cmd)->cmd.bitop(bitOP, destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITOP, (cmd)->cmd.bitop(bitOP, destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(operation).add(destKey)
				.add(keys);
		final BitOP bitOP = (new BitOperationConverter()).convert(operation);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITOP,
					(cmd)->cmd.bitop(bitOP, destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITOP,
					(cmd)->cmd.bitop(bitOP, destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITOP, (cmd)->cmd.bitop(bitOP, destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitPos(final String key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITPOS, (cmd)->cmd.bitpos(key, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITPOS, (cmd)->cmd.bitpos(key, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITPOS, (cmd)->cmd.bitpos(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITPOS, (cmd)->cmd.bitpos(key, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITPOS, (cmd)->cmd.bitpos(key, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITPOS, (cmd)->cmd.bitpos(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start)
				.add(end);
		final BitPosParams bitPosParams = new BitPosParams(start, end);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITPOS,
					(cmd)->cmd.bitpos(key, value, bitPosParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITPOS,
					(cmd)->cmd.bitpos(key, value, bitPosParams), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITPOS,
					(cmd)->cmd.bitpos(key, value, bitPosParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(start)
				.add(end);
		final BitPosParams bitPosParams = new BitPosParams(start, end);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.BITPOS,
					(cmd)->cmd.bitpos(key, value, bitPosParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.BITPOS,
					(cmd)->cmd.bitpos(key, value, bitPosParams), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.BITPOS,
					(cmd)->cmd.bitpos(key, value, bitPosParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean getBit(final String key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.GETBIT, (cmd)->cmd.getbit(key, offset),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.GETBIT, (cmd)->cmd.getbit(key, offset),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.GETBIT, (cmd)->cmd.getbit(key, offset), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean getBit(final byte[] key, final long offset) {
		final CommandArguments args = CommandArguments.create(key).add(offset);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.GETBIT, (cmd)->cmd.getbit(key, offset),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.GETBIT, (cmd)->cmd.getbit(key, offset),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.GETBIT, (cmd)->cmd.getbit(key, offset), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean setBit(final String key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SETBIT,
					(cmd)->cmd.setbit(key, offset, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SETBIT,
					(cmd)->cmd.setbit(key, offset, value), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SETBIT, (cmd)->cmd.setbit(key, offset, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean setBit(final byte[] key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create(key).add(offset).add(value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SETBIT,
					(cmd)->cmd.setbit(key, offset, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SETBIT,
					(cmd)->cmd.setbit(key, offset, value), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SETBIT, (cmd)->cmd.setbit(key, offset, value),
					(v)->v)
					.run(args);
		}
	}

}
