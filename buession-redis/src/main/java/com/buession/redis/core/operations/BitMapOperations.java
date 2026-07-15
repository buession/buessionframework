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
package com.buession.redis.core.operations;

import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.bitmap.BitOperation;
import com.buession.redis.core.command.args.bitmap.BitType;
import com.buession.redis.core.command.BitMapCommands;
import com.buession.redis.core.command.args.bitmap.BitFieldArgument;
import com.buession.redis.core.command.args.bitmap.BitFieldRoArgument;
import com.buession.redis.utils.KeyUtils;

import java.util.List;

/**
 * BitMap 命令
 *
 * <p>详情说明 <a href="https://redis.io/commands/?group=bitmap" target="_blank">https://redis.io/commands/?group=bitmap</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface BitMapOperations extends BitMapCommands, RedisOperations {

	@Override
	default Long bitCount(final String key) {
		return doExecute((cmd)->cmd.bitCount(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long bitCount(final byte[] key) {
		return doExecute((cmd)->cmd.bitCount(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long bitCount(final String key, final long start, final long end) {
		return doExecute((cmd)->cmd.bitCount(KeyUtils.rawKey(this, key), start, end));
	}

	@Override
	default Long bitCount(final byte[] key, final long start, final long end) {
		return doExecute((cmd)->cmd.bitCount(KeyUtils.rawKey(this, key), start, end));
	}

	@Override
	default Long bitCount(final String key, final long start, final long end, final BitType type) {
		return doExecute((cmd)->cmd.bitCount(KeyUtils.rawKey(this, key), start, end, type));
	}

	@Override
	default Long bitCount(final byte[] key, final long start, final long end, final BitType type) {
		return doExecute((cmd)->cmd.bitCount(KeyUtils.rawKey(this, key), start, end, type));
	}

	@Override
	default List<Long> bitField(final String key, final BitFieldArgument... arguments) {
		return doExecute((cmd)->cmd.bitField(KeyUtils.rawKey(this, key), arguments));
	}

	@Override
	default List<Long> bitField(final byte[] key, final BitFieldArgument... arguments) {
		return doExecute((cmd)->cmd.bitField(KeyUtils.rawKey(this, key), arguments));
	}

	@Override
	default List<Long> bitFieldRo(final String key, final BitFieldRoArgument... arguments) {
		return doExecute((cmd)->cmd.bitFieldRo(KeyUtils.rawKey(this, key), arguments));
	}

	@Override
	default List<Long> bitFieldRo(final byte[] key, final BitFieldRoArgument... arguments) {
		return doExecute((cmd)->cmd.bitFieldRo(KeyUtils.rawKey(this, key), arguments));
	}

	@Override
	default Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		return doExecute((cmd)->cmd
				.bitOp(operation, KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		return doExecute((cmd)->cmd
				.bitOp(operation, KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Long bitPos(final String key, final boolean value) {
		return doExecute((cmd)->cmd.bitPos(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Long bitPos(final byte[] key, final boolean value) {
		return doExecute((cmd)->cmd.bitPos(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Long bitPos(final String key, final boolean value, final long start) {
		return doExecute((cmd)->cmd.bitPos(KeyUtils.rawKey(this, key), value, start));
	}

	@Override
	default Long bitPos(final byte[] key, final boolean value, final long start) {
		return doExecute((cmd)->cmd.bitPos(KeyUtils.rawKey(this, key), value, start));
	}

	@Override
	default Long bitPos(final String key, final boolean value, final long start, final long end) {
		return doExecute((cmd)->cmd.bitPos(KeyUtils.rawKey(this, key), value, start, end));
	}

	@Override
	default Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		return doExecute((cmd)->cmd.bitPos(KeyUtils.rawKey(this, key), value, start, end));
	}

	@Override
	default Long bitPos(final String key, final boolean value, final long start, final long end, final BitType type) {
		return doExecute((cmd)->cmd.bitPos(KeyUtils.rawKey(this, key), value, start, end, type));
	}

	@Override
	default Long bitPos(final byte[] key, final boolean value, final long start, final long end, final BitType type) {
		return doExecute((cmd)->cmd.bitPos(KeyUtils.rawKey(this, key), value, start, end, type));
	}

	@Override
	default Boolean getBit(final String key, final long offset) {
		return doExecute((cmd)->cmd.getBit(KeyUtils.rawKey(this, key), offset));
	}

	@Override
	default Boolean getBit(final byte[] key, final long offset) {
		return doExecute((cmd)->cmd.getBit(KeyUtils.rawKey(this, key), offset));
	}

	@Override
	default Boolean setBit(final String key, final long offset, final boolean value) {
		return doExecute((cmd)->cmd.setBit(KeyUtils.rawKey(this, key), offset, value));
	}

	@Override
	default Boolean setBit(final byte[] key, final long offset, final boolean value) {
		return doExecute((cmd)->cmd.setBit(KeyUtils.rawKey(this, key), offset, value));
	}

	private <R> R doExecute(final Command.Executor<BitMapCommands, R> executor) {
		return execute((client)->executor.execute(client.bitMapCommands()));
	}

}
