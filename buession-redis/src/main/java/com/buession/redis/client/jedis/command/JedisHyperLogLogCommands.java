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

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.HyperLogLogCommands;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;

/**
 * Jedis HyperLogLog 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisHyperLogLogCommands extends AbstractJedisRedisCommands implements HyperLogLogCommands {

	public JedisHyperLogLogCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status pfAdd(final String key, final String... elements) {
		final CommandArguments args = CommandArguments.create(key).add(elements);
		return executeCommand(Command.PFADD, args, (cmd)->cmd.pfadd(rawKey(key), elements), new OneStatusConverter());
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements) {
		final CommandArguments args = CommandArguments.create(key).add(elements);
		return executeCommand(Command.PFADD, args, (cmd)->cmd.pfadd(rawKey(key), elements), new OneStatusConverter());
	}

	@Override
	public Long pfCount(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.PFCOUNT, args, (cmd)->cmd.pfcount(rawKeys(keys)));
	}

	@Override
	public Long pfCount(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.PFCOUNT, args, (cmd)->cmd.pfcount(rawKeys(keys)));
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.PFADD, args, (cmd)->cmd.pfmerge(rawKey(destKey), rawKeys(keys)),
				new OkStatusConverter());
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.PFADD, args, (cmd)->cmd.pfmerge(rawKey(destKey), rawKeys(keys)),
				new OkStatusConverter());
	}

}
