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
package com.buession.redis.client.lettuce.command;

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.TopKInfo;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.TopKCommands;

import java.util.List;

/**
 * Lettuce TOP K 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceTopKCommands extends AbstractLettuceRedisCommands implements TopKCommands {

	public LettuceTopKCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public List<String> topKAdd(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.TOPK_ADD, args);
	}

	@Override
	public List<byte[]> topKAdd(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.TOPK_ADD, args);
	}

	@Override
	public List<Long> topKCount(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.TOPK_COUNT, args);
	}

	@Override
	public List<Long> topKCount(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.TOPK_COUNT, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<String> topKIncrBy(final String key, final KeyValue<String, Long>... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.TOPK_INCRBY, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<byte[]> topKIncrBy(final byte[] key, final KeyValue<byte[], Long>... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.TOPK_INCRBY, args);
	}

	@Override
	public TopKInfo topKInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TOPK_INFO, args);
	}

	@Override
	public TopKInfo topKInfo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TOPK_INFO, args);
	}

	@Override
	public List<String> topKList(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TOPK_LIST, args);
	}

	@Override
	public List<String> topKList(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TOPK_LIST, args);
	}

	@Override
	public List<KeyValue<String, Long>> topKListWithCount(final String key) {
		final CommandArguments args = CommandArguments.create(key, "WITHCOUNT");
		return executeCommand(RedisCommand.TOPK_LIST, args);
	}

	@Override
	public List<KeyValue<byte[], Long>> topKListWithCount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key, "WITHCOUNT");
		return executeCommand(RedisCommand.TOPK_LIST, args);
	}

	@Override
	public List<Boolean> topKQuery(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.TOPK_QUERY, args);
	}

	@Override
	public List<Boolean> topKQuery(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.TOPK_QUERY, args);
	}

	@Override
	public Status topKReserve(final String key, final long topK) {
		final CommandArguments args = CommandArguments.create(key, topK);
		return executeCommand(RedisCommand.TOPK_RESERVE, args);
	}

	@Override
	public Status topKReserve(final byte[] key, final long topK) {
		final CommandArguments args = CommandArguments.create(key, topK);
		return executeCommand(RedisCommand.TOPK_RESERVE, args);
	}

	@Override
	public Status topKReserve(final String key, final long topK, final long width, final long depth,
	                          final double decay) {
		final CommandArguments args = CommandArguments.create(key, topK).add(width).add(depth).add(decay);
		return executeCommand(RedisCommand.TOPK_RESERVE, args);
	}

	@Override
	public Status topKReserve(final byte[] key, final long topK, final long width, final long depth,
	                          final double decay) {
		final CommandArguments args = CommandArguments.create(key, topK).add(width).add(depth).add(decay);
		return executeCommand(RedisCommand.TOPK_RESERVE, args);
	}

}
