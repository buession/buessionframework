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

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.CountMinSketchOperations;
import com.buession.redis.core.CmsInfo;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 计数最小草图命令操作抽象类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceCountMinSketchOperations extends AbstractLettuceRedisOperations
		implements CountMinSketchOperations {

	public LettuceCountMinSketchOperations(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public List<Long> cmsIncrby(final String key, final List<KeyValue<String, Long>> items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.CMS_INCRBY)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public List<Long> cmsIncrby(final byte[] key, final List<KeyValue<byte[], Long>> items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.CMS_INCRBY)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public CmsInfo cmsInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<CmsInfo, CmsInfo>newBuilder(client, Command.CMS_INFO)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public CmsInfo cmsInfo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<CmsInfo, CmsInfo>newBuilder(client, Command.CMS_INFO)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Status cmsInitByDim(final String key, final int width, final int depth) {
		final CommandArguments args = CommandArguments.create(key).add(width).add(depth);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CMS_INITBYDIM)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status cmsInitByDim(final byte[] key, final int width, final int depth) {
		final CommandArguments args = CommandArguments.create(key).add(width).add(depth);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CMS_INITBYDIM)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status cmsInitByProb(final String key, final double error, final double probability) {
		final CommandArguments args = CommandArguments.create(key).add(error).add(probability);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CMS_INITBYPROB)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status cmsInitByProb(final byte[] key, final double error, final double probability) {
		final CommandArguments args = CommandArguments.create(key).add(error).add(probability);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CMS_INITBYPROB)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status cmsMerge(final String key, final Map<String, Long> keysAndWeights) {
		final CommandArguments args = CommandArguments.create(key).add(keysAndWeights);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CMS_MERGE)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public Status cmsMerge(final byte[] key, final Map<byte[], Long> keysAndWeights) {
		final CommandArguments args = CommandArguments.create(key).add(keysAndWeights);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.CMS_MERGE)
				.arguments(args)
				.converter(okStatusConverter)
				.run();
	}

	@Override
	public List<Long> cmsQuery(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.CMS_QUERY)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public List<Long> cmsQuery(byte[] key, byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.CMS_QUERY)
				.arguments(args)
				.converter((v)->v)
				.run();
	}

}
