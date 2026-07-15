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

import com.buession.core.converter.ArrayKeyValueMapConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.CmsInfo;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CountMinSketchCommands;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 计数最小草图命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceCountMinSketchCommands extends AbstractLettuceRedisCommands
		implements CountMinSketchCommands {

	public LettuceCountMinSketchCommands(final LettuceRedisClient client) {
		super(client);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<Long> cmsIncrby(final String key, final KeyValue<String, Long>... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.CMS_INCRBY, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<Long> cmsIncrby(final byte[] key, final KeyValue<byte[], Long>... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.CMS_INCRBY, args);
	}

	@Override
	public CmsInfo cmsInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.CMS_INFO, args);
	}

	@Override
	public CmsInfo cmsInfo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.CMS_INFO, args);
	}

	@Override
	public Status cmsInitByDim(final String key, final int width, final int depth) {
		final CommandArguments args = CommandArguments.create(key).add(width).add(depth);
		return executeCommand(RedisCommand.CMS_INITBYDIM, args);
	}

	@Override
	public Status cmsInitByDim(final byte[] key, final int width, final int depth) {
		final CommandArguments args = CommandArguments.create(key).add(width).add(depth);
		return executeCommand(RedisCommand.CMS_INITBYDIM, args);
	}

	@Override
	public Status cmsInitByProb(final String key, final double error, final double probability) {
		final CommandArguments args = CommandArguments.create(key).add(error).add(probability);
		return executeCommand(RedisCommand.CMS_INITBYPROB, args);
	}

	@Override
	public Status cmsInitByProb(final byte[] key, final double error, final double probability) {
		final CommandArguments args = CommandArguments.create(key).add(error).add(probability);
		return executeCommand(RedisCommand.CMS_INITBYPROB, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status cmsMerge(final String key, final KeyValue<String, Long>... data) {
		final ArrayKeyValueMapConverter<String, Long, String, Long> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		final Map<String, Long> keysAndWeights = arrayKeyValueMapConverter.convert(data);
		final CommandArguments args = CommandArguments.create(key).add(data.length).add(keysAndWeights.keySet())
				.add("WEIGHTS").add(keysAndWeights.values());
		return executeCommand(RedisCommand.CMS_MERGE, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status cmsMerge(final byte[] key, final KeyValue<byte[], Long>... data) {
		final ArrayKeyValueMapConverter<byte[], Long, String, Long> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				SafeEncoder::encode, (v)->v);
		final Map<String, Long> keysAndWeights = arrayKeyValueMapConverter.convert(data);
		final CommandArguments args = CommandArguments.create(key).add(data.length).add(keysAndWeights.keySet())
				.add("WEIGHTS").add(keysAndWeights.values());
		return executeCommand(RedisCommand.CMS_MERGE, args);
	}

	@Override
	public List<Long> cmsQuery(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.CMS_QUERY, args);
	}

	@Override
	public List<Long> cmsQuery(byte[] key, byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.CMS_QUERY, args);
	}

}
