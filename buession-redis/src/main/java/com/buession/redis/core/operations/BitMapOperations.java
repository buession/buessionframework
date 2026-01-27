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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.operations;

import com.buession.redis.core.BitCountOption;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.command.BitMapCommands;

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
		return execute((client)->client.bitMapOperations().bitCount(key));
	}

	@Override
	default Long bitCount(final byte[] key) {
		return execute((client)->client.bitMapOperations().bitCount(key));
	}

	@Override
	default Long bitCount(final String key, final long start, final long end) {
		return execute((client)->client.bitMapOperations().bitCount(key, start, end));
	}

	@Override
	default Long bitCount(final byte[] key, final long start, final long end) {
		return execute((client)->client.bitMapOperations().bitCount(key, start, end));
	}

	@Override
	default Long bitCount(final String key, final long start, final long end, final BitCountOption bitCountOption) {
		return execute((client)->client.bitMapOperations().bitCount(key, start, end, bitCountOption));
	}

	@Override
	default Long bitCount(final byte[] key, final long start, final long end, final BitCountOption bitCountOption) {
		return execute((client)->client.bitMapOperations().bitCount(key, start, end, bitCountOption));
	}

	@Override
	default List<Long> bitField(final String key, final BitFieldArgument argument) {
		return execute((client)->client.bitMapOperations().bitField(key, argument));
	}

	@Override
	default List<Long> bitField(final byte[] key, final BitFieldArgument argument) {
		return execute((client)->client.bitMapOperations().bitField(key, argument));
	}

	@Override
	default List<Long> bitFieldRo(final String key, final String... arguments) {
		return execute((client)->client.bitMapOperations().bitFieldRo(key, arguments));
	}

	@Override
	default List<Long> bitFieldRo(final byte[] key, final byte[]... arguments) {
		return execute((client)->client.bitMapOperations().bitFieldRo(key, arguments));
	}

	@Override
	default Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		return execute((client)->client.bitMapOperations().bitOp(operation, destKey, keys));
	}

	@Override
	default Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.bitMapOperations().bitOp(operation, destKey, keys));
	}

	@Override
	default Long bitPos(final String key, final boolean value) {
		return execute((client)->client.bitMapOperations().bitPos(key, value));
	}

	@Override
	default Long bitPos(final byte[] key, final boolean value) {
		return execute((client)->client.bitMapOperations().bitPos(key, value));
	}

	@Override
	default Long bitPos(final String key, final boolean value, final long start, final long end) {
		return execute((client)->client.bitMapOperations().bitPos(key, value, start, end));
	}

	@Override
	default Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		return execute((client)->client.bitMapOperations().bitPos(key, value, start, end));
	}

	@Override
	default Boolean getBit(final String key, final long offset) {
		return execute((client)->client.bitMapOperations().getBit(key, offset));
	}

	@Override
	default Boolean getBit(final byte[] key, final long offset) {
		return execute((client)->client.bitMapOperations().getBit(key, offset));
	}

	@Override
	default Boolean setBit(final String key, final long offset, final boolean value) {
		return execute((client)->client.bitMapOperations().setBit(key, offset, value));
	}

	@Override
	default Boolean setBit(final byte[] key, final long offset, final boolean value) {
		return execute((client)->client.bitMapOperations().setBit(key, offset, value));
	}

}
