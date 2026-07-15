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
import com.buession.redis.core.CmsInfo;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CountMinSketchCommands;
import com.buession.redis.core.internal.convert.response.CmsInfoConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Jedis 计数最小草图命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisCountMinSketchCommands extends AbstractJedisRedisCommands implements CountMinSketchCommands {

	public JedisCountMinSketchCommands(final JedisRedisClient client) {
		super(client);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<Long> cmsIncrby(final String key, final KeyValue<String, Long>... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		final ArrayKeyValueMapConverter<String, Long, String, Long> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.CMS_INCRBY, args,
				(cmd)->cmd.cmsIncrBy(key, arrayKeyValueMapConverter.convert(items)),
				(cmd)->cmd.cmsIncrBy(key, arrayKeyValueMapConverter.convert(items)),
				(cmd)->cmd.cmsIncrBy(key, arrayKeyValueMapConverter.convert(items)));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<Long> cmsIncrby(final byte[] key, final KeyValue<byte[], Long>... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		final ArrayKeyValueMapConverter<byte[], Long, String, Long> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				SafeEncoder::encode, (v)->v);
		return executeCommand(RedisCommand.CMS_INCRBY, args,
				(cmd)->cmd.cmsIncrBy(SafeEncoder.encode(key), arrayKeyValueMapConverter.convert(items)),
				(cmd)->cmd.cmsIncrBy(SafeEncoder.encode(key), arrayKeyValueMapConverter.convert(items)),
				(cmd)->cmd.cmsIncrBy(SafeEncoder.encode(key), arrayKeyValueMapConverter.convert(items)));
	}

	@Override
	public CmsInfo cmsInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.CMS_INFO, args, (cmd)->cmd.cmsInfo(key),
				(cmd)->cmd.cmsInfo(key), (cmd)->cmd.cmsInfo(key), new CmsInfoConverter());
	}

	@Override
	public CmsInfo cmsInfo(final byte[] key) {
		return cmsInfo(SafeEncoder.encode(key));
	}

	@Override
	public Status cmsInitByDim(final String key, final int width, final int depth) {
		final CommandArguments args = CommandArguments.create(key).add(width).add(depth);
		return executeCommand(RedisCommand.CMS_INITBYDIM, args, (cmd)->cmd.cmsInitByDim(key, width, depth),
				(cmd)->cmd.cmsInitByDim(key, width, depth), (cmd)->cmd.cmsInitByDim(key, width, depth),
				new OkStatusConverter());
	}

	@Override
	public Status cmsInitByDim(final byte[] key, final int width, final int depth) {
		return cmsInitByDim(SafeEncoder.encode(key), width, depth);
	}

	@Override
	public Status cmsInitByProb(final String key, final double error, final double probability) {
		final CommandArguments args = CommandArguments.create(key).add(error).add(probability);
		return executeCommand(RedisCommand.CMS_INITBYPROB, args, (cmd)->cmd.cmsInitByProb(key, error, probability),
				(cmd)->cmd.cmsInitByProb(key, error, probability), (cmd)->cmd.cmsInitByProb(key, error, probability),
				new OkStatusConverter());
	}

	@Override
	public Status cmsInitByProb(final byte[] key, final double error, final double probability) {
		return cmsInitByProb(SafeEncoder.encode(key), error, probability);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status cmsMerge(final String key, final KeyValue<String, Long>... data) {
		final ArrayKeyValueMapConverter<String, Long, String, Long> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		final Map<String, Long> keysAndWeights = arrayKeyValueMapConverter.convert(data);
		final CommandArguments args = CommandArguments.create(key).add(data.length).add(keysAndWeights.keySet())
				.add("WEIGHTS").add(keysAndWeights.values());

		return executeCommand(RedisCommand.CMS_MERGE, args, (cmd)->cmd.cmsMerge(key, keysAndWeights),
				(cmd)->cmd.cmsMerge(key, keysAndWeights), (cmd)->cmd.cmsMerge(key, keysAndWeights),
				new OkStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status cmsMerge(final byte[] key, final KeyValue<byte[], Long>... data) {
		final ArrayKeyValueMapConverter<byte[], Long, String, Long> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				SafeEncoder::encode, (v)->v);
		final Map<String, Long> keysAndWeights = arrayKeyValueMapConverter.convert(data);
		final CommandArguments args = CommandArguments.create(key).add(data.length).add(keysAndWeights.keySet())
				.add("WEIGHTS").add(keysAndWeights.values());

		return executeCommand(RedisCommand.CMS_MERGE, args,
				(cmd)->cmd.cmsMerge(SafeEncoder.encode(key), keysAndWeights),
				(cmd)->cmd.cmsMerge(SafeEncoder.encode(key), keysAndWeights),
				(cmd)->cmd.cmsMerge(SafeEncoder.encode(key), keysAndWeights), new OkStatusConverter());
	}

	@Override
	public List<Long> cmsQuery(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(RedisCommand.CMS_QUERY, args, (cmd)->cmd.cmsQuery(key, items),
				(cmd)->cmd.cmsQuery(key, items), (cmd)->cmd.cmsQuery(key, items));
	}

	@Override
	public List<Long> cmsQuery(byte[] key, byte[]... items) {
		return cmsQuery(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

}
