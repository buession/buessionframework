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
import com.buession.core.converter.MapConverter;
import com.buession.core.converter.MapEntryMapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.BfInfoOption;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.command.BloomFilterCommands;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.BFInsertArgument;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.jedis.args.JedisBFInsertParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.bloom.BFReserveParams;

import java.util.List;
import java.util.Map;

/**
 * Jedis 布隆过滤器命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisBloomFilterCommands extends AbstractJedisRedisCommands implements BloomFilterCommands {

	public JedisBloomFilterCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status bfAdd(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.BF_ADD, args, (cmd)->cmd.bfAdd(rawKey(key), item), new BooleanStatusConverter());
	}

	@Override
	public Status bfAdd(final byte[] key, final byte[] item) {
		return bfAdd(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Long bfCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.BF_CARD, args, (cmd)->cmd.bfCard(rawKey(key)), (v)->v);
	}

	@Override
	public Long bfCard(final byte[] key) {
		return bfCard(SafeEncoder.encode(key));
	}

	@Override
	public Boolean bfExists(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.BF_EXISTS, args, (cmd)->cmd.bfExists(rawKey(key), item), (v)->v);
	}

	@Override
	public Boolean bfExists(final byte[] key, final byte[] item) {
		return bfExists(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Map<String, Number> bfInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.BF_INFO, args, (cmd)->cmd.bfInfo(rawKey(key)),
				new MapConverter<>((k)->k, (v)->(Number) v));
	}

	@Override
	public Map<String, Number> bfInfo(final byte[] key) {
		return bfInfo(SafeEncoder.encode(key));
	}

	@Override
	public Number bfInfo(final String key, final BfInfoOption option) {
		final CommandArguments args = CommandArguments.create(key).add(option);
		return executeCommand(Command.BF_INFO, args, (cmd)->cmd.bfInfo(rawKey(key)), (v)->{
			Object result = null;

			if(option == BfInfoOption.CAPACITY){
				result = v.get("Capacity");
			}else if(option == BfInfoOption.SIZE){
				result = v.get("Size");
			}else if(option == BfInfoOption.FILTERS){
				result = v.get("Number of filters");
			}else if(option == BfInfoOption.ITEMS){
				result = v.get("Number of items inserted");
			}else if(option == BfInfoOption.EXPANSION){
				result = v.get("Expansion rate");
			}

			return result instanceof Number ? (Number) result : null;
		});
	}

	@Override
	public Number bfInfo(final byte[] key, final BfInfoOption option) {
		return bfInfo(SafeEncoder.encode(key), option);
	}

	@Override
	public List<Boolean> bfInsert(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.ITEMS, items);
		return executeCommand(Command.BF_INSERT, args, (cmd)->cmd.bfInsert(rawKey(key), items), (v)->v);
	}

	@Override
	public List<Boolean> bfInsert(final byte[] key, final byte[]... items) {
		return bfInsert(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> bfInsert(final String key, final BFInsertArgument argument, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.ITEMS, items);
		return executeCommand(Command.BF_INSERT, args,
				(cmd)->cmd.bfInsert(rawKey(key), new JedisBFInsertParams(argument), items), (v)->v);
	}

	@Override
	public List<Boolean> bfInsert(final byte[] key, final BFInsertArgument argument, final byte[]... items) {
		return bfInsert(SafeEncoder.encode(key), argument, SafeEncoder.encode(items));
	}

	@Override
	public Status bfLoadchunk(final String key, final long iterator, final byte[] data) {
		final CommandArguments args = CommandArguments.create(key).add(iterator).add(data);
		return executeCommand(Command.BF_LOADCHUNK, args, (cmd)->cmd.bfLoadChunk(rawKey(key), iterator, data),
				new OkStatusConverter());
	}

	@Override
	public Status bfLoadchunk(final byte[] key, final long iterator, final byte[] data) {
		return bfLoadchunk(SafeEncoder.encode(key), iterator, data);
	}

	@Override
	public List<Boolean> bfMAdd(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(Command.BF_MADD, args, (cmd)->cmd.bfMAdd(rawKey(key), items), (v)->v);
	}

	@Override
	public List<Boolean> bfMAdd(final byte[] key, final byte[]... items) {
		return bfMAdd(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> bfMExists(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(Command.BF_MEXISTS, args, (cmd)->cmd.bfMExists(rawKey(key), items), (v)->v);
	}

	@Override
	public List<Boolean> bfMExists(final byte[] key, final byte[]... items) {
		return bfMExists(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity) {
		final CommandArguments args = CommandArguments.create(key).add(errorRate).add(capacity);
		return executeCommand(Command.BF_RESERVE, args, (cmd)->cmd.bfReserve(rawKey(key), errorRate, capacity),
				new OkStatusConverter());
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity);
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity, final int expansion) {
		final CommandArguments args = CommandArguments.create(key).add(errorRate).add(capacity)
				.add("EXPANSION", expansion);
		return bfReserve(rawKey(key), errorRate, capacity, BFReserveParams.reserveParams().expansion(expansion), args);
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity, final int expansion) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity, expansion);
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity, final int expansion,
							final boolean nonScaling) {
		final CommandArguments args = CommandArguments.create(key).add(errorRate).add(capacity)
				.add("EXPANSION", expansion).add("NONSCALING");
		final BFReserveParams bfReserveParams = BFReserveParams.reserveParams().expansion(expansion);

		if(nonScaling){
			bfReserveParams.nonScaling();
		}

		return bfReserve(rawKey(key), errorRate, capacity, bfReserveParams, args);
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity, final int expansion,
							final boolean nonScaling) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity, expansion, nonScaling);
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity, final boolean nonScaling) {
		final CommandArguments args = CommandArguments.create(key).add(errorRate).add(capacity).add("NONSCALING");
		final BFReserveParams bfReserveParams = BFReserveParams.reserveParams();

		if(nonScaling){
			bfReserveParams.nonScaling();
		}

		return bfReserve(rawKey(key), errorRate, capacity, bfReserveParams, args);
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity, final boolean nonScaling) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity, nonScaling);
	}

	@Override
	public Map<Long, byte[]> bfScandump(final String key, final long iterator) {
		final CommandArguments args = CommandArguments.create(key).add(iterator);
		return executeCommand(Command.BF_SCANDUMP, args, (cmd)->cmd.bfScanDump(rawKey(key), iterator),
				new MapEntryMapConverter<>((k)->k, (v)->v));
	}

	@Override
	public Map<Long, byte[]> bfScandump(final byte[] key, final long iterator) {
		return bfScandump(SafeEncoder.encode(key), iterator);
	}

	private Status bfReserve(final String key, final double errorRate, final long capacity,
							 final BFReserveParams bfReserveParams, final CommandArguments args) {
		return executeCommand(Command.BF_RESERVE, args, (cmd)->cmd.bfReserve(key, errorRate, capacity,
				bfReserveParams), new OkStatusConverter());
	}

}
