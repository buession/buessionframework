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

import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.BitMapOperations;
import com.buession.redis.core.BitCountOption;
import com.buession.redis.core.BitOperation;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Lettuce BitMap 命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractBitMapOperations<C extends LettuceRedisClient> extends AbstractLettuceRedisOperations<C>
		implements BitMapOperations {

	public AbstractBitMapOperations(final C client) {
		super(client);
	}

	@Override
	public Long bitCount(final String key) {
		return bitCount(SafeEncoder.encode(key));
	}

	@Override
	public Long bitCount(final String key, final long start, final long end) {
		return bitCount(SafeEncoder.encode(key), start, end);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end, final BitCountOption bitCountOption) {
		return bitCount(SafeEncoder.encode(key), start, end, bitCountOption);
	}

	@Override
	public List<Long> bitField(final String key, final BitFieldArgument argument) {
		return bitField(SafeEncoder.encode(key), argument);
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		return bitOp(operation, SafeEncoder.encode(destKey), SafeEncoder.encode(keys));
	}

	@Override
	public Long bitPos(final String key, final boolean value) {
		return bitPos(SafeEncoder.encode(key), value);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end) {
		return bitPos(SafeEncoder.encode(key), value, start, end);
	}

	@Override
	public Boolean getBit(final String key, final long offset) {
		return getBit(SafeEncoder.encode(key), offset);
	}

	@Override
	public Boolean setBit(final String key, final long offset, final boolean value) {
		return setBit(SafeEncoder.encode(key), offset, value);
	}

}
