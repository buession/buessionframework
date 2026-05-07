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
package com.buession.redis.client.jedis.command;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.MapEntryMapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CuckooFilterCommands;
import com.buession.redis.core.command.args.cuckoofilter.CFReserveArgument;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.jedis.args.JedisCFInsertParams;
import com.buession.redis.core.internal.jedis.args.JedisCFReserveParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.bloom.CFInsertParams;

import java.util.List;
import java.util.Map;

/**
 * Jedis 布谷鸟过滤器命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisCuckooFilterCommands extends AbstractJedisRedisCommands implements CuckooFilterCommands {

	public JedisCuckooFilterCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status cfAdd(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key, item);
		return executeCommand(RedisCommand.CF_ADD, args, (cmd)->cmd.cfAdd(key, item),
				(cmd)->cmd.cfAdd(key, item), (cmd)->cmd.cfAdd(key, item),
				new BooleanStatusConverter());
	}

	@Override
	public Status cfAdd(final byte[] key, final byte[] item) {
		return cfAdd(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Status cfAddNx(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key, item);
		return executeCommand(RedisCommand.CF_ADDNX, args, (cmd)->cmd.cfAddNx(key, item),
				(cmd)->cmd.cfAdd(key, item), (cmd)->cmd.cfAdd(key, item),
				new BooleanStatusConverter());
	}

	@Override
	public Status cfAddNx(final byte[] key, final byte[] item) {
		return cfAddNx(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Long cfCount(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key, item);
		return executeCommand(RedisCommand.CF_COUNT, args, (cmd)->cmd.cfCount(key, item),
				(cmd)->cmd.cfCount(key, item), (cmd)->cmd.cfCount(key, item));
	}

	@Override
	public Long cfCount(final byte[] key, final byte[] item) {
		return cfCount(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Status cfDel(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.CF_DEL, args, (cmd)->cmd.cfDel(key, item),
				(cmd)->cmd.cfDel(key, item), (cmd)->cmd.cfDel(key, item),
				new BooleanStatusConverter());
	}

	@Override
	public Status cfDel(final byte[] key, final byte[] item) {
		return cfDel(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Boolean cfExists(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key, item);
		return executeCommand(RedisCommand.CF_EXISTS, args, (cmd)->cmd.cfExists(key, item),
				(cmd)->cmd.cfExists(key, item), (cmd)->cmd.cfExists(key, item));
	}

	@Override
	public Boolean cfExists(final byte[] key, final byte[] item) {
		return cfExists(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Map<String, Object> cfInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.CF_INFO, args, (cmd)->cmd.cfInfo(key),
				(cmd)->cmd.cfInfo(key), (cmd)->cmd.cfInfo(key));
	}

	@Override
	public Map<String, Object> cfInfo(final byte[] key) {
		return cfInfo(SafeEncoder.encode(key));
	}

	@Override
	public List<Boolean> cfInsert(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.ITEMS, items);
		return executeCommand(RedisCommand.CF_INSERT, args, (cmd)->cmd.cfInsert(key, items),
				(cmd)->cmd.cfInsert(key, items), (cmd)->cmd.cfInsert(key, items));
	}

	@Override
	public List<Boolean> cfInsert(final byte[] key, final byte[]... items) {
		return cfInsert(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> cfInsert(final String key, final Long capacity, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("CAPACITY", capacity)
				.add(Keyword.Common.ITEMS, items);
		return cfInsert(key, new JedisCFInsertParams(capacity), items, args);
	}

	@Override
	public List<Boolean> cfInsert(final byte[] key, final Long capacity, final byte[]... items) {
		return cfInsert(SafeEncoder.encode(key), capacity, SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> cfInsert(final String key, final Long capacity, final boolean noCreate,
	                              final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("CAPACITY", capacity)
				.add(noCreate ? "NOCREATE" : null).add(Keyword.Common.ITEMS, items);
		return cfInsert(key, new JedisCFInsertParams(capacity, noCreate), items, args);
	}

	@Override
	public List<Boolean> cfInsert(final byte[] key, final Long capacity, final boolean noCreate,
	                              final byte[]... items) {
		return cfInsert(SafeEncoder.encode(key), capacity, noCreate, SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> cfInsert(final String key, final boolean noCreate, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(noCreate ? "NOCREATE" : null)
				.add(Keyword.Common.ITEMS, items);
		return cfInsert(key, new JedisCFInsertParams(noCreate), items, args);
	}

	@Override
	public List<Boolean> cfInsert(final byte[] key, final boolean noCreate, final byte[]... items) {
		return cfInsert(SafeEncoder.encode(key), noCreate, SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> cfInsertNx(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.ITEMS, items);
		return executeCommand(RedisCommand.CF_INSERTNX, args, (cmd)->cmd.cfInsertNx(key, items),
				(cmd)->cmd.cfInsertNx(key, items), (cmd)->cmd.cfInsertNx(key, items));
	}

	@Override
	public List<Boolean> cfInsertNx(final byte[] key, final byte[]... items) {
		return cfInsertNx(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> cfInsertNx(final String key, final Long capacity, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("CAPACITY", capacity)
				.add(Keyword.Common.ITEMS, items);
		return cfInsertNx(key, new JedisCFInsertParams(capacity), items, args);
	}

	@Override
	public List<Boolean> cfInsertNx(final byte[] key, final Long capacity, final byte[]... items) {
		return cfInsertNx(SafeEncoder.encode(key), capacity, SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> cfInsertNx(final String key, final Long capacity, final boolean noCreate,
	                                final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("CAPACITY", capacity)
				.add(noCreate ? "NOCREATE" : null).add(Keyword.Common.ITEMS, items);
		return cfInsertNx(key, new JedisCFInsertParams(capacity, noCreate), items, args);
	}

	@Override
	public List<Boolean> cfInsertNx(final byte[] key, final Long capacity, final boolean noCreate,
	                                final byte[]... items) {
		return cfInsertNx(SafeEncoder.encode(key), capacity, noCreate, SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> cfInsertNx(final String key, final boolean noCreate, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(noCreate ? "NOCREATE" : null)
				.add(Keyword.Common.ITEMS, items);
		return cfInsertNx(key, new JedisCFInsertParams(noCreate), items, args);
	}

	@Override
	public List<Boolean> cfInsertNx(final byte[] key, final boolean noCreate, final byte[]... items) {
		return cfInsertNx(SafeEncoder.encode(key), noCreate, SafeEncoder.encode(items));
	}

	@Override
	public Status cfLoadchunk(final String key, final long iterator, final byte[] data) {
		final CommandArguments args = CommandArguments.create(key).add(iterator).add(data);
		return executeCommand(RedisCommand.CF_LOADCHUNK, args, (cmd)->cmd.cfLoadChunk(key, iterator, data),
				(cmd)->cmd.cfLoadChunk(key, iterator, data), (cmd)->cmd.cfLoadChunk(key, iterator, data),
				new OkStatusConverter());
	}

	@Override
	public Status cfLoadchunk(final byte[] key, final long iterator, final byte[] data) {
		return cfLoadchunk(SafeEncoder.encode(key), iterator, data);
	}

	@Override
	public List<Boolean> cfMExists(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.CF_MEXISTS, args, (cmd)->cmd.cfMExists(key, items),
				(cmd)->cmd.cfMExists(key, items), (cmd)->cmd.cfMExists(key, items));
	}

	@Override
	public List<Boolean> cfMExists(final byte[] key, final byte[]... items) {
		return cfMExists(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public Status cfReserve(final String key, final long capacity) {
		final CommandArguments args = CommandArguments.create(key, capacity);
		return executeCommand(RedisCommand.CF_RESERVE, args, (cmd)->cmd.cfReserve(key, capacity),
				(cmd)->cmd.cfReserve(key, capacity), (cmd)->cmd.cfReserve(key, capacity),
				new OkStatusConverter());
	}

	@Override
	public Status cfReserve(final byte[] key, final long capacity) {
		return cfReserve(SafeEncoder.encode(key), capacity);
	}

	@Override
	public Status cfReserve(final String key, final long capacity, final CFReserveArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(RedisCommand.CF_RESERVE, args,
				(cmd)->cmd.cfReserve(key, capacity, new JedisCFReserveParams(argument)),
				(cmd)->cmd.cfReserve(key, capacity, new JedisCFReserveParams(argument)),
				(cmd)->cmd.cfReserve(key, capacity, new JedisCFReserveParams(argument)),
				new OkStatusConverter());
	}

	@Override
	public Status cfReserve(final byte[] key, final long capacity, final CFReserveArgument argument) {
		return cfReserve(SafeEncoder.encode(key), capacity, argument);
	}

	@Override
	public Map<Long, byte[]> cfScanDump(final String key, final long iterator) {
		final CommandArguments args = CommandArguments.create(key).add(iterator);
		return executeCommand(RedisCommand.CF_SCANDUMP, args, (cmd)->cmd.cfScanDump(key, iterator),
				(cmd)->cmd.cfScanDump(key, iterator), (cmd)->cmd.cfScanDump(key, iterator),
				new MapEntryMapConverter<>((k)->k, (v)->v));
	}

	@Override
	public Map<Long, byte[]> cfScanDump(final byte[] key, final long iterator) {
		return cfScanDump(SafeEncoder.encode(key), iterator);
	}

	private List<Boolean> cfInsert(final String key, final CFInsertParams cfInsertParams, final String[] items,
	                               final CommandArguments args) {
		return executeCommand(RedisCommand.CF_INSERT, args, (cmd)->cmd.cfInsert(key, cfInsertParams, items),
				(cmd)->cmd.cfInsert(key, cfInsertParams, items), (cmd)->cmd.cfInsert(key, cfInsertParams, items));
	}

	private List<Boolean> cfInsertNx(final String key, final CFInsertParams cfInsertParams, final String[] items,
	                                 final CommandArguments args) {
		return executeCommand(RedisCommand.CF_INSERTNX, args, (cmd)->cmd.cfInsertNx(key, cfInsertParams, items),
				(cmd)->cmd.cfInsertNx(key, cfInsertParams, items), (cmd)->cmd.cfInsertNx(key, cfInsertParams, items));
	}

}
