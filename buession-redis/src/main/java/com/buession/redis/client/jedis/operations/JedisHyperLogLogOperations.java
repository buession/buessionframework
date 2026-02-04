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

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.HyperLogLogOperations;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.internal.convert.Converters;

/**
 * Jedis HyperLogLog 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisHyperLogLogOperations extends AbstractJedisRedisOperations implements HyperLogLogOperations {

	public JedisHyperLogLogOperations(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status pfAdd(final String key, final String... elements) {
		final CommandArguments args = CommandArguments.create(key).add(elements);
		return JedisCommandBuilder.<Long, Status>newBuilder(client, Command.PFADD)
				.executor((cmd)->cmd.pfadd(key, elements)).arguments(args)
				.converter(Converters.oneStatusConverter())
				.run();
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements) {
		final CommandArguments args = CommandArguments.create(key).add(elements);
		return JedisCommandBuilder.<Long, Status>newBuilder(client, Command.PFADD)
				.executor((cmd)->cmd.pfadd(key, elements)).arguments(args)
				.converter(Converters.oneStatusConverter())
				.run();
	}

	@Override
	public Long pfCount(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.PFCOUNT)
				.executor((cmd)->cmd.pfcount(keys)).arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Long pfCount(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.PFMERGE)
				.executor((cmd)->cmd.pfcount(keys)).arguments(args)
				.converter((v)->v)
				.run();
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.PFADD)
				.executor((cmd)->cmd.pfmerge(destKey, keys)).arguments(args)
				.converter(Converters.okStatusConverter())
				.run();
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.PFADD)
				.executor((cmd)->cmd.pfmerge(destKey, keys)).arguments(args)
				.converter(Converters.okStatusConverter())
				.run();
	}

	@Override
	public Status pfSelftest() {
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.PFSELFTEST)
				.converter(Converters.okStatusConverter())
				.run();
	}

}
