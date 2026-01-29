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
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.MapEntryMapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.BloomFilterOperations;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.internal.jedis.JedisBFInsertParams;
import com.buession.redis.core.internal.jedis.JedisBFReserveParams;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Jedis 布隆过滤命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link JedisRedisClient}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractBloomFilterOperations<C extends JedisRedisClient> extends AbstractJedisRedisOperations<C>
		implements BloomFilterOperations {

	public AbstractBloomFilterOperations(final C client) {
		super(client);
	}

	@Override
	public Status bfAdd(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return JedisCommandBuilder.<Boolean, Status>newBuilder(client, Command.BF_ADD)
				.executor((cmd)->cmd.bfAdd(key, item)).arguments(args).converter(booleanStatusConverter).run();
	}

	@Override
	public Status bfAdd(final byte[] key, final byte[] item) {
		return bfAdd(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Long bfCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.BF_CARD).executor((cmd)->cmd.bfCard(key))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long bfCard(final byte[] key) {
		return bfCard(SafeEncoder.encode(key));
	}

	@Override
	public Boolean bfExists(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return JedisCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.BF_EXISTS)
				.executor((cmd)->cmd.bfExists(key, item)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Boolean bfExists(final byte[] key, final byte[] item) {
		return bfExists(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Map<String, Object> bfInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Map<String, Object>, Map<String, Object>>newBuilder(client, Command.BF_INFO)
				.executor((cmd)->cmd.bfInfo(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Map<String, Object> bfInfo(final byte[] key) {
		return bfInfo(SafeEncoder.encode(key));
	}

	@Override
	public List<Boolean> bfInsert(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS").add(items);
		return JedisCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.BF_INSERT)
				.executor((cmd)->cmd.bfInsert(key, items)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> bfInsert(final byte[] key, final byte[]... items) {
		return bfInsert(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> bfInsert(final String key, final BFInsertArgument bfInsertArgument, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(bfInsertArgument).add("ITEMS").add(items);
		final JedisBFInsertParams bfInsertParams = JedisBFInsertParams.from(bfInsertArgument);
		return JedisCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.BF_INSERT)
				.executor((cmd)->cmd.bfInsert(key, bfInsertParams, items)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> bfInsert(final byte[] key, final BFInsertArgument bfInsertArgument, final byte[]... items) {
		return bfInsert(SafeEncoder.encode(key), bfInsertArgument, SafeEncoder.encode(items));
	}

	@Override
	public Status bfLoadchunk(final String key, final long iterator, final byte[] data) {
		final CommandArguments args = CommandArguments.create(key).add(iterator).add(SafeEncoder.encode(data));
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.BF_LOADCHUNK)
				.executor((cmd)->cmd.bfLoadChunk(key, iterator, data)).arguments(args).converter(okStatusConverter)
				.run();
	}

	@Override
	public Status bfLoadchunk(final byte[] key, final long iterator, final byte[] data) {
		return bfLoadchunk(SafeEncoder.encode(key), iterator, data);
	}

	@Override
	public List<Boolean> bfMAdd(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return JedisCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.BF_MADD)
				.executor((cmd)->cmd.bfMAdd(key, items)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> bfMAdd(final byte[] key, final byte[]... items) {
		return bfMAdd(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> bfMExists(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return JedisCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.BF_MEXISTS)
				.executor((cmd)->cmd.bfMExists(key, items)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> bfMExists(final byte[] key, final byte[]... items) {
		return bfMExists(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity) {
		final CommandArguments args = CommandArguments.create(key).add(errorRate).add(capacity);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.BF_RESERVE)
				.executor((cmd)->cmd.bfReserve(key, errorRate, capacity)).arguments(args).converter(okStatusConverter)
				.run();
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity);
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity, final int expansion) {
		final JedisBFReserveParams bfReserveParams = new JedisBFReserveParams(expansion);
		return bfReserve(key, errorRate, capacity, bfReserveParams);
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity, final int expansion) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity, expansion);
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity, final boolean nonScaling) {
		final JedisBFReserveParams bfReserveParams = new JedisBFReserveParams(nonScaling);
		return bfReserve(key, errorRate, capacity, bfReserveParams);
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity, final boolean nonScaling) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity, nonScaling);
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity, final int expansion,
							final boolean nonScaling) {
		final JedisBFReserveParams bfReserveParams = new JedisBFReserveParams(expansion, nonScaling);
		return bfReserve(key, errorRate, capacity, bfReserveParams);
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity, final int expansion,
							final boolean nonScaling) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity, expansion, nonScaling);
	}

	@Override
	public Map<Long, byte[]> bfScandump(final String key, final long iterator) {
		final CommandArguments args = CommandArguments.create(key).add(iterator);
		return JedisCommandBuilder.<Map.Entry<Long, byte[]>, Map<Long, byte[]>>newBuilder(client, Command.BF_SCANDUMP)
				.executor((cmd)->cmd.bfScanDump(key, iterator)).arguments(args)
				.converter(new MapEntryMapConverter<>((k)->k, (v)->v))
				.run();
	}

	@Override
	public Map<Long, byte[]> bfScandump(final byte[] key, final long iterator) {
		return bfScandump(SafeEncoder.encode(key), iterator);
	}

	private Status bfReserve(final String key, final double errorRate, final long capacity,
							 final JedisBFReserveParams bfReserveParams) {
		final CommandArguments args = CommandArguments.create(key).add(errorRate).add(capacity).add(bfReserveParams);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.BF_RESERVE)
				.executor((cmd)->cmd.bfReserve(key, errorRate, capacity, bfReserveParams)).arguments(args)
				.converter(okStatusConverter)
				.run();
	}

}
