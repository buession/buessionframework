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

import com.buession.redis.client.jedis.JedisStandaloneClient;
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
 * Jedis 单机模式 BitMap 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisBitMapOperations extends AbstractBitMapOperations<JedisStandaloneClient> {

	public JedisBitMapOperations(final JedisStandaloneClient client) {
		super(client);
	}

	@Override
	public Long bitCount(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Long>(client, ProtocolCommand.BITCOUNT)
				.general((cmd)->cmd.bitcount(key))
				.pipeline((cmd)->cmd.bitcount(key))
				.transaction((cmd)->cmd.bitcount(key))
				.run(args);
	}

	@Override
	public Long bitCount(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Long>(client, ProtocolCommand.BITCOUNT)
				.general((cmd)->cmd.bitcount(key))
				.pipeline((cmd)->cmd.bitcount(key))
				.transaction((cmd)->cmd.bitcount(key))
				.run(args);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new JedisCommand<Long>(client, ProtocolCommand.BITCOUNT)
				.general((cmd)->cmd.bitcount(key, start, end))
				.pipeline((cmd)->cmd.bitcount(key, start, end))
				.transaction((cmd)->cmd.bitcount(key, start, end))
				.run(args);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new JedisCommand<Long>(client, ProtocolCommand.BITCOUNT)
				.general((cmd)->cmd.bitcount(key, start, end))
				.pipeline((cmd)->cmd.bitcount(key, start, end))
				.transaction((cmd)->cmd.bitcount(key, start, end))
				.run(args);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end, final BitCountOption bitCountOption) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end)
				.put("bitCountOption", bitCountOption);
		final redis.clients.jedis.args.BitCountOption option = (new BitCountOptionConverter()).convert(
				bitCountOption);
		return new JedisCommand<Long>(client, ProtocolCommand.BITCOUNT)
				.general((cmd)->cmd.bitcount(key, start, end, option))
				.pipeline((cmd)->cmd.bitcount(key, start, end, option))
				.transaction((cmd)->cmd.bitcount(key, start, end, option))
				.run(args);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end, final BitCountOption bitCountOption) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end)
				.put("bitCountOption", bitCountOption);
		final redis.clients.jedis.args.BitCountOption option = (new BitCountOptionConverter()).convert(
				bitCountOption);
		return new JedisCommand<Long>(client, ProtocolCommand.BITCOUNT)
				.general((cmd)->cmd.bitcount(key, start, end, option))
				.pipeline((cmd)->cmd.bitcount(key, start, end, option))
				.transaction((cmd)->cmd.bitcount(key, start, end, option))
				.run(args);
	}

	@Override
	public List<Long> bitField(final String key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create("key", key).put("arguments", argument);
		final String[] arguments = (new BitFieldArgumentConverter()).convert(argument);
		return new JedisCommand<List<Long>>(client, ProtocolCommand.BITFIELD)
				.general((cmd)->cmd.bitfield(key, arguments))
				.pipeline((cmd)->cmd.bitfield(key, arguments))
				.transaction((cmd)->cmd.bitfield(key, arguments))
				.run(args);
	}

	@Override
	public List<Long> bitField(final byte[] key, final BitFieldArgument argument) {
		final CommandArguments args = CommandArguments.create("key", key).put("arguments", argument);
		final byte[][] arguments = SafeEncoder.encode((new BitFieldArgumentConverter()).convert(argument));
		return new JedisCommand<List<Long>>(client, ProtocolCommand.BITFIELD)
				.general((cmd)->cmd.bitfield(key, arguments))
				.pipeline((cmd)->cmd.bitfield(key, arguments))
				.transaction((cmd)->cmd.bitfield(key, arguments))
				.run(args);
	}

	@Override
	public List<Long> bitFieldRo(final String key, final String... arguments) {
		final CommandArguments args = CommandArguments.create("key", key).put("arguments", (Object[]) arguments);
		return new JedisCommand<List<Long>>(client, ProtocolCommand.BITFIELD_RO)
				.general((cmd)->cmd.bitfieldReadonly(key, arguments))
				.pipeline((cmd)->cmd.bitfieldReadonly(key, arguments))
				.transaction((cmd)->cmd.bitfieldReadonly(key, arguments))
				.run(args);
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final byte[]... arguments) {
		final CommandArguments args = CommandArguments.create("key", key).put("arguments", (Object[]) arguments);
		return new JedisCommand<List<Long>>(client, ProtocolCommand.BITFIELD_RO)
				.general((cmd)->cmd.bitfieldReadonly(key, arguments))
				.pipeline((cmd)->cmd.bitfieldReadonly(key, arguments))
				.transaction((cmd)->cmd.bitfieldReadonly(key, arguments))
				.run(args);
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create("operation", operation).put("destKey", destKey)
				.put("keys", (Object[]) keys);
		final BitOP bitOP = (new BitOperationConverter()).convert(operation);
		return new JedisCommand<Long>(client, ProtocolCommand.BITOP)
				.general((cmd)->cmd.bitop(bitOP, destKey, keys))
				.pipeline((cmd)->cmd.bitop(bitOP, destKey, keys))
				.transaction((cmd)->cmd.bitop(bitOP, destKey, keys))
				.run(args);
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("operation", operation).put("destKey", destKey)
				.put("keys", (Object[]) keys);
		final BitOP bitOP = (new BitOperationConverter()).convert(operation);
		return new JedisCommand<Long>(client, ProtocolCommand.BITOP)
				.general((cmd)->cmd.bitop(bitOP, destKey, keys))
				.pipeline((cmd)->cmd.bitop(bitOP, destKey, keys))
				.transaction((cmd)->cmd.bitop(bitOP, destKey, keys))
				.run(args);
	}

	@Override
	public Long bitPos(final String key, final boolean value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisCommand<Long>(client, ProtocolCommand.BITPOS)
				.general((cmd)->cmd.bitpos(key, value))
				.pipeline((cmd)->cmd.bitpos(key, value))
				.transaction((cmd)->cmd.bitpos(key, value))
				.run(args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisCommand<Long>(client, ProtocolCommand.BITPOS)
				.general((cmd)->cmd.bitpos(key, value))
				.pipeline((cmd)->cmd.bitpos(key, value))
				.transaction((cmd)->cmd.bitpos(key, value))
				.run(args);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("start", start)
				.put("end", end);
		final BitPosParams params = new BitPosParams(start, end);
		return new JedisCommand<Long>(client, ProtocolCommand.BITPOS)
				.general((cmd)->cmd.bitpos(key, value, params))
				.pipeline((cmd)->cmd.bitpos(key, value, params))
				.transaction((cmd)->cmd.bitpos(key, value, params))
				.run(args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("start", start)
				.put("end", end);
		final BitPosParams params = new BitPosParams(start, end);
		return new JedisCommand<Long>(client, ProtocolCommand.BITPOS)
				.general((cmd)->cmd.bitpos(key, value, params))
				.pipeline((cmd)->cmd.bitpos(key, value, params))
				.transaction((cmd)->cmd.bitpos(key, value, params))
				.run(args);
	}

	@Override
	public Boolean getBit(final String key, final long offset) {
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset);
		return new JedisCommand<Boolean>(client, ProtocolCommand.GETBIT)
				.general((cmd)->cmd.getbit(key, offset))
				.pipeline((cmd)->cmd.getbit(key, offset))
				.transaction((cmd)->cmd.getbit(key, offset))
				.run(args);
	}

	@Override
	public Boolean getBit(final byte[] key, final long offset) {
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset);
		return new JedisCommand<Boolean>(client, ProtocolCommand.GETBIT)
				.general((cmd)->cmd.getbit(key, offset))
				.pipeline((cmd)->cmd.getbit(key, offset))
				.transaction((cmd)->cmd.getbit(key, offset))
				.run(args);
	}

	@Override
	public Boolean setBit(final String key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return new JedisCommand<Boolean>(client, ProtocolCommand.SETBIT)
				.general((cmd)->cmd.setbit(key, offset, value))
				.pipeline((cmd)->cmd.setbit(key, offset, value))
				.transaction((cmd)->cmd.setbit(key, offset, value))
				.run(args);
	}

	@Override
	public Boolean setBit(final byte[] key, final long offset, final boolean value) {
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return new JedisCommand<Boolean>(client, ProtocolCommand.SETBIT)
				.general((cmd)->cmd.setbit(key, offset, value))
				.pipeline((cmd)->cmd.setbit(key, offset, value))
				.transaction((cmd)->cmd.setbit(key, offset, value))
				.run(args);
	}

}
