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

import com.buession.core.converter.ArrayKeyValueMapConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.TopKInfo;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.TopKCommands;
import com.buession.redis.core.internal.convert.StringListBinaryListConverter;
import com.buession.redis.core.internal.convert.jedis.response.TopKInfoConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.utils.SafeEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Jedis TOP K 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisTopKCommands extends AbstractJedisRedisCommands implements TopKCommands {

	public JedisTopKCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public List<String> topKAdd(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.TOPK_ADD, args, (cmd)->cmd.topkAdd(rawKey(key), items));
	}

	@Override
	public List<byte[]> topKAdd(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(
				RedisCommand.TOPK_ADD, args, (cmd)->cmd.topkAdd(rawStringKey(key), SafeEncoder.encode(items)),
				new StringListBinaryListConverter());
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
		final ArrayKeyValueMapConverter<String, Long, String, Long> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.TOPK_INCRBY, args,
				(cmd)->cmd.topkIncrBy(rawKey(key), arrayKeyValueMapConverter.convert(items)));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<byte[]> topKIncrBy(final byte[] key, final KeyValue<byte[], Long>... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		final ArrayKeyValueMapConverter<byte[], Long, String, Long> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				SafeEncoder::encode, (v)->v);
		return executeCommand(RedisCommand.TOPK_INCRBY, args,
				(cmd)->cmd.topkIncrBy(rawStringKey(key), arrayKeyValueMapConverter.convert(items)),
				new StringListBinaryListConverter());
	}

	@Override
	public TopKInfo topKInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TOPK_INFO, args, (cmd)->cmd.topkInfo(rawKey(key)), new TopKInfoConverter());
	}

	@Override
	public TopKInfo topKInfo(final byte[] key) {
		return topKInfo(SafeEncoder.encode(key));
	}

	@Override
	public List<String> topKList(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TOPK_LIST, args, (cmd)->cmd.topkList(rawKey(key)));
	}

	@Override
	public List<String> topKList(final byte[] key) {
		return topKList(SafeEncoder.encode(key));
	}

	@Override
	public List<KeyValue<String, Long>> topKListWithCount(final String key) {
		final CommandArguments args = CommandArguments.create(key, "WITHCOUNT");
		return executeCommand(RedisCommand.TOPK_LIST, args, (cmd)->cmd.topkListWithCount(rawKey(key)), (v)->{
			final List<KeyValue<String, Long>> result = new ArrayList<>(v.size());

			for(Map.Entry<String, Long> e : v.entrySet()){
				result.add(new KeyValue<>(e.getKey(), e.getValue()));
			}

			return result;
		});
	}

	@Override
	public List<KeyValue<byte[], Long>> topKListWithCount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key, "WITHCOUNT");
		return executeCommand(RedisCommand.TOPK_LIST, args, (cmd)->cmd.topkListWithCount(rawStringKey(key)), (v)->{
			final List<KeyValue<byte[], Long>> result = new ArrayList<>(v.size());

			for(Map.Entry<String, Long> e : v.entrySet()){
				result.add(new KeyValue<>(SafeEncoder.encode(e.getKey()), e.getValue()));
			}

			return result;
		});
	}

	@Override
	public List<Boolean> topKQuery(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.TOPK_QUERY, args, (cmd)->cmd.topkQuery(rawKey(key), items));
	}

	@Override
	public List<Boolean> topKQuery(final byte[] key, final byte[]... items) {
		return topKQuery(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public Status topKReserve(final String key, final long topK) {
		final CommandArguments args = CommandArguments.create(key, topK);
		return executeCommand(RedisCommand.TOPK_RESERVE, args, (cmd)->cmd.topkReserve(rawKey(key), topK),
				new OkStatusConverter());
	}

	@Override
	public Status topKReserve(final byte[] key, final long topK) {
		return topKReserve(SafeEncoder.encode(key), topK);
	}

	@Override
	public Status topKReserve(final String key, final long topK, final long width, final long depth,
	                          final double decay) {
		final CommandArguments args = CommandArguments.create(key, topK).add(width).add(depth).add(decay);
		return executeCommand(
				RedisCommand.TOPK_QUERY, args, (cmd)->cmd.topkReserve(rawKey(key), topK, width, depth, decay),
				new OkStatusConverter());
	}

	@Override
	public Status topKReserve(final byte[] key, final long topK, final long width, final long depth,
	                          final double decay) {
		return topKReserve(SafeEncoder.encode(key), topK, width, depth, decay);
	}

}
