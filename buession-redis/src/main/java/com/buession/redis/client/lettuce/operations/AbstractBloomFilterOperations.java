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
package com.buession.redis.client.lettuce.operations;

import com.buession.core.converter.MapEntryMapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.BloomFilterOperations;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.internal.lettuce.LettuceBFReserveArgs;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 布隆过滤命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractBloomFilterOperations<C extends LettuceRedisClient>
		extends AbstractLettuceRedisOperations<C> implements BloomFilterOperations {

	public AbstractBloomFilterOperations(final C client) {
		super(client);
	}

	@Override
	public Status bfAdd(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return LettuceCommandBuilder.<Long, Status>newBuilder(client, Command.BF_ADD).arguments(args)
				.converter(oneStatusConverter).run();
	}

	@Override
	public Status bfAdd(final byte[] key, final byte[] item) {
		return bfAdd(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Long bfCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.BF_CARD).arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public Long bfCard(final byte[] key) {
		return bfCard(SafeEncoder.encode(key));
	}

	@Override
	public Boolean bfExists(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return LettuceCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.BF_EXISTS).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Boolean bfExists(final byte[] key, final byte[] item) {
		return bfExists(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Map<String, Object> bfInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Map<String, Object>, Map<String, Object>>newBuilder(client, Command.BF_INFO)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Map<String, Object> bfInfo(final byte[] key) {
		return bfInfo(SafeEncoder.encode(key));
	}

	@Override
	public List<Boolean> bfInsert(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS").add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.BF_INSERT).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Boolean> bfInsert(final byte[] key, final byte[]... items) {
		return bfInsert(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> bfInsert(final String key, final BFInsertArgument bfInsertArgument, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(bfInsertArgument).add("ITEMS").add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.BF_INSERT).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Boolean> bfInsert(final byte[] key, final BFInsertArgument bfInsertArgument, final byte[]... items) {
		return bfInsert(SafeEncoder.encode(key), bfInsertArgument, SafeEncoder.encode(items));
	}

	@Override
	public Status bfLoadchunk(final String key, final long iterator, final byte[] data) {
		final CommandArguments args = CommandArguments.create(key).add(iterator).add(SafeEncoder.encode(data));
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.BF_LOADCHUNK).arguments(args)
				.converter(okStatusConverter).run();
	}

	@Override
	public Status bfLoadchunk(final byte[] key, final long iterator, final byte[] data) {
		return bfLoadchunk(SafeEncoder.encode(key), iterator, data);
	}

	@Override
	public List<Boolean> bfMAdd(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.BF_MADD)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> bfMAdd(final byte[] key, final byte[]... items) {
		return bfMAdd(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> bfMExists(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.BF_MEXISTS)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> bfMExists(final byte[] key, final byte[]... items) {
		return bfMExists(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity) {
		final CommandArguments args = CommandArguments.create(key).add(errorRate).add(capacity);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.BF_RESERVE)
				.arguments(args).converter(okStatusConverter)
				.run();
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity);
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity, final int expansion) {
		final LettuceBFReserveArgs bfReserveArgs = new LettuceBFReserveArgs(expansion);
		final CommandArguments args = CommandArguments.create(key).add(errorRate).add(capacity).add(bfReserveArgs);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.BF_RESERVE)
				.arguments(args).converter(okStatusConverter)
				.run();
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity, final int expansion) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity, expansion);
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity, final boolean nonScaling) {
		final LettuceBFReserveArgs bfReserveArgs = new LettuceBFReserveArgs(nonScaling);
		final CommandArguments args = CommandArguments.create(key).add(errorRate).add(capacity).add(bfReserveArgs);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.BF_RESERVE)
				.arguments(args).converter(okStatusConverter)
				.run();
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity, final boolean nonScaling) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity, nonScaling);
	}

	@Override
	public Status bfReserve(final String key, final double errorRate, final long capacity, final int expansion,
							final boolean nonScaling) {
		final LettuceBFReserveArgs bfReserveArgs = new LettuceBFReserveArgs(expansion, nonScaling);
		final CommandArguments args = CommandArguments.create(key).add(errorRate).add(capacity).add(bfReserveArgs);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.BF_RESERVE)
				.arguments(args).converter(okStatusConverter)
				.run();
	}

	@Override
	public Status bfReserve(final byte[] key, final double errorRate, final long capacity, final int expansion,
							final boolean nonScaling) {
		return bfReserve(SafeEncoder.encode(key), errorRate, capacity, expansion, nonScaling);
	}

	@Override
	public Map<Long, byte[]> bfScandump(final String key, final long iterator) {
		final CommandArguments args = CommandArguments.create(key).add(iterator);
		return LettuceCommandBuilder.<Map.Entry<Long, byte[]>, Map<Long, byte[]>>newBuilder(client, Command.BF_SCANDUMP)
				.arguments(args)
				.converter(new MapEntryMapConverter<>((k)->k, (v)->v))
				.run();
	}

	@Override
	public Map<Long, byte[]> bfScandump(final byte[] key, final long iterator) {
		return bfScandump(SafeEncoder.encode(key), iterator);
	}

}
