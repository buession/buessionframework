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
import com.buession.redis.client.operations.CuckooFilterOperations;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.CFInsertArgument;
import com.buession.redis.core.command.args.CFReserveArgument;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 单机模式布谷鸟过滤器命令操作抽象类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceCuckooFilterOperations extends AbstractLettuceRedisOperations implements
		CuckooFilterOperations {

	public LettuceCuckooFilterOperations(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Status cfAdd(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return LettuceCommandBuilder.<Long, Status>newBuilder(client, Command.CF_ADD).arguments(args)
				.converter(oneStatusConverter).run();
	}

	@Override
	public Status cfAdd(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return LettuceCommandBuilder.<Long, Status>newBuilder(client, Command.CF_ADD).arguments(args)
				.converter(oneStatusConverter).run();
	}

	@Override
	public Status cfAddNx(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return LettuceCommandBuilder.<Long, Status>newBuilder(client, Command.CF_ADDNX).arguments(args)
				.converter(oneStatusConverter).run();
	}

	@Override
	public Status cfAddNx(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return LettuceCommandBuilder.<Long, Status>newBuilder(client, Command.CF_ADDNX).arguments(args)
				.converter(oneStatusConverter).run();
	}

	@Override
	public Long cfCount(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.CF_COUNT).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Long cfCount(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.CF_COUNT).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Status cfDel(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Boolean, Status>newBuilder(client, Command.CF_DEL)
				.arguments(args).converter(booleanStatusConverter).run();
	}

	@Override
	public Status cfDel(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Boolean, Status>newBuilder(client, Command.CF_DEL)
				.arguments(args).converter(booleanStatusConverter).run();
	}

	@Override
	public Boolean cfExists(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return LettuceCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.CF_EXISTS).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Boolean cfExists(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return LettuceCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.CF_EXISTS).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Map<String, Object> cfInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Map<String, Object>, Map<String, Object>>newBuilder(client, Command.CF_INFO)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Map<String, Object> cfInfo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Map<String, Object>, Map<String, Object>>newBuilder(client, Command.CF_INFO)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsert(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS").add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_INSERT).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsert(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS").add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_INSERT).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsert(final String key, final CFInsertArgument argument, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("ITEMS").add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.BF_INSERT).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsert(final byte[] key, final CFInsertArgument argument, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("ITEMS").add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.BF_INSERT).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsertNx(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS").add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_INSERTNX)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsertNx(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS").add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_INSERTNX)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsertNx(final String key, final CFInsertArgument argument, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("ITEMS").add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_INSERTNX)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsertNx(final byte[] key, final CFInsertArgument argument, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("ITEMS").add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_INSERTNX)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Status cfLoadchunk(final String key, final long iterator, final byte[] data) {
		final CommandArguments args = CommandArguments.create(key).add(iterator).add(data);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CF_LOADCHUNK).arguments(args)
				.converter(okStatusConverter).run();
	}

	@Override
	public Status cfLoadchunk(final byte[] key, final long iterator, final byte[] data) {
		final CommandArguments args = CommandArguments.create(key).add(iterator).add(data);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CF_LOADCHUNK).arguments(args)
				.converter(okStatusConverter).run();
	}

	@Override
	public List<Boolean> cfMExists(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_MEXISTS)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfMExists(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return LettuceCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_MEXISTS)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Status cfReserve(final String key, final CFReserveArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CF_RESERVE).arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status cfReserve(final byte[] key, final CFReserveArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CF_RESERVE).arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Map<Long, byte[]> cfScandump(final String key, final long iterator) {
		final CommandArguments args = CommandArguments.create(key).add(iterator);
		return LettuceCommandBuilder.<Map.Entry<Long, byte[]>, Map<Long, byte[]>>newBuilder(client, Command.CF_SCANDUMP)
				.arguments(args)
				.converter(new MapEntryMapConverter<>((k)->k, (v)->v))
				.run();
	}

	@Override
	public Map<Long, byte[]> cfScandump(final byte[] key, final long iterator) {
		final CommandArguments args = CommandArguments.create(key).add(iterator);
		return LettuceCommandBuilder.<Map.Entry<Long, byte[]>, Map<Long, byte[]>>newBuilder(client, Command.CF_SCANDUMP)
				.arguments(args)
				.converter(new MapEntryMapConverter<>((k)->k, (v)->v))
				.run();
	}

}
