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

import com.buession.lang.Status;
import com.buession.redis.core.command.args.bloomfilter.BfInfoOption;
import com.buession.redis.core.command.BloomFilterCommands;
import com.buession.redis.core.command.args.bloomfilter.InsertArgument;
import com.buession.redis.utils.KeyUtils;

import java.util.List;
import java.util.Map;

/**
 * 布隆过滤器命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=bf" target="_blank">https://redis.io/docs/latest/commands/?group=bf</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface BloomFilterOperations extends BloomFilterCommands, RedisOperations {

	@Override
	default Status bfAdd(final String key, final String item) {
		return execute((client)->client.bloomFilterCommands().bfAdd(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Status bfAdd(final byte[] key, final byte[] item) {
		return execute((client)->client.bloomFilterCommands().bfAdd(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Long bfCard(final String key) {
		return execute((client)->client.bloomFilterCommands().bfCard(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long bfCard(final byte[] key) {
		return execute((client)->client.bloomFilterCommands().bfCard(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Boolean bfExists(final String key, final String item) {
		return execute((client)->client.bloomFilterCommands().bfExists(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Boolean bfExists(final byte[] key, final byte[] item) {
		return execute((client)->client.bloomFilterCommands().bfExists(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Map<String, Number> bfInfo(final String key) {
		return execute((client)->client.bloomFilterCommands().bfInfo(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Map<String, Number> bfInfo(final byte[] key) {
		return execute((client)->client.bloomFilterCommands().bfInfo(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Number bfInfo(final String key, final BfInfoOption option) {
		return execute((client)->client.bloomFilterCommands().bfInfo(KeyUtils.rawKey(this, key), option));
	}

	@Override
	default Number bfInfo(final byte[] key, final BfInfoOption option) {
		return execute((client)->client.bloomFilterCommands().bfInfo(KeyUtils.rawKey(this, key), option));
	}

	@Override
	default List<Boolean> bfInsert(final String key, final String... items) {
		return execute((client)->client.bloomFilterCommands().bfInsert(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default List<Boolean> bfInsert(final byte[] key, final byte[]... items) {
		return execute((client)->client.bloomFilterCommands().bfInsert(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default List<Boolean> bfInsert(final String key, final InsertArgument argument, final String... items) {
		return execute((client)->client.bloomFilterCommands().bfInsert(KeyUtils.rawKey(this, key), argument, items));
	}

	@Override
	default List<Boolean> bfInsert(final byte[] key, final InsertArgument argument, final byte[]... items) {
		return execute((client)->client.bloomFilterCommands().bfInsert(KeyUtils.rawKey(this, key), argument, items));
	}

	@Override
	default Status bfLoadchunk(final String key, final long iterator, final byte[] data) {
		return execute((client)->client.bloomFilterCommands().bfLoadchunk(KeyUtils.rawKey(this, key), iterator, data));
	}

	@Override
	default Status bfLoadchunk(final byte[] key, final long iterator, final byte[] data) {
		return execute((client)->client.bloomFilterCommands().bfLoadchunk(KeyUtils.rawKey(this, key), iterator, data));
	}

	@Override
	default List<Boolean> bfMAdd(final String key, final String... items) {
		return execute((client)->client.bloomFilterCommands().bfMAdd(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default List<Boolean> bfMAdd(final byte[] key, final byte[]... items) {
		return execute((client)->client.bloomFilterCommands().bfMAdd(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default List<Boolean> bfMExists(final String key, final String... items) {
		return execute((client)->client.bloomFilterCommands().bfMExists(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default List<Boolean> bfMExists(final byte[] key, final byte[]... items) {
		return execute((client)->client.bloomFilterCommands().bfMExists(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default Status bfReserve(final String key, final double errorRate, final long capacity) {
		return execute(
				(client)->client.bloomFilterCommands().bfReserve(KeyUtils.rawKey(this, key), errorRate, capacity));
	}

	@Override
	default Status bfReserve(final byte[] key, final double errorRate, final long capacity) {
		return execute(
				(client)->client.bloomFilterCommands().bfReserve(KeyUtils.rawKey(this, key), errorRate, capacity));
	}

	@Override
	default Status bfReserve(final String key, final double errorRate, final long capacity, final int expansion) {
		return execute((client)->client.bloomFilterCommands()
				.bfReserve(KeyUtils.rawKey(this, key), errorRate, capacity, expansion));
	}

	@Override
	default Status bfReserve(final byte[] key, final double errorRate, final long capacity, final int expansion) {
		return execute((client)->client.bloomFilterCommands()
				.bfReserve(KeyUtils.rawKey(this, key), errorRate, capacity, expansion));
	}

	@Override
	default Status bfReserve(final String key, final double errorRate, final long capacity, final int expansion,
	                         final boolean nonScaling) {
		return execute((client)->client.bloomFilterCommands()
				.bfReserve(KeyUtils.rawKey(this, key), errorRate, capacity, expansion, nonScaling));
	}

	@Override
	default Status bfReserve(final byte[] key, final double errorRate, final long capacity, final int expansion,
	                         final boolean nonScaling) {
		return execute((client)->client.bloomFilterCommands()
				.bfReserve(KeyUtils.rawKey(this, key), errorRate, capacity, expansion, nonScaling));
	}

	@Override
	default Status bfReserve(final String key, final double errorRate, final long capacity, final boolean nonScaling) {
		return execute((client)->client.bloomFilterCommands()
				.bfReserve(KeyUtils.rawKey(this, key), errorRate, capacity, nonScaling));
	}

	@Override
	default Status bfReserve(final byte[] key, final double errorRate, final long capacity, final boolean nonScaling) {
		return execute((client)->client.bloomFilterCommands()
				.bfReserve(KeyUtils.rawKey(this, key), errorRate, capacity, nonScaling));
	}

	@Override
	default Map<Long, byte[]> bfScandump(final String key, final long iterator) {
		return execute((client)->client.bloomFilterCommands().bfScandump(KeyUtils.rawKey(this, key), iterator));
	}

	@Override
	default Map<Long, byte[]> bfScandump(final byte[] key, final long iterator) {
		return execute((client)->client.bloomFilterCommands().bfScandump(KeyUtils.rawKey(this, key), iterator));
	}

}
