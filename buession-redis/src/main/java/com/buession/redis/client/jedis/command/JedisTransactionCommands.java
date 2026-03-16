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
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.TransactionCommands;

import java.util.List;

/**
 * Jedis 事务命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisTransactionCommands extends AbstractJedisRedisCommands implements
		TransactionCommands {

	public JedisTransactionCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status discard() {
		return executeCommand(Command.DISCARD, (cmd)->{
			RedisConnection connection = client.getConnection();
			return connection.discard();
		});
	}

	@Override
	public List<Object> exec() {
		return executeCommand(Command.EXEC, (cmd)->{
			RedisConnection connection = client.getConnection();
			return connection.exec();
			/*
			return new Response<>(new Builder<List<Object>>() {

				@Override
				public List<Object> build(Object data) {
					return connection.exec();
				}

			});

			 */
		}, (v)->v);
	}

	@Override
	public Status multi() {
		return executeCommand(Command.MULTI, (cmd)->{
			cmd.multi();
			return Status.SUCCESS;
		});
	}

	@Override
	public Status unwatch() {
		return executeCommand(Command.UNWATCH, (cmd)->{
			//cmd.unwatch();
			return Status.SUCCESS;
		});
	}

	@Override
	public Status watch(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.WATCH, args, (cmd)->{
			//cmd.unwatch();
			return Status.SUCCESS;
		});
	}

	@Override
	public Status watch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.WATCH, args, (cmd)->{
			//cmd.unwatch();
			return Status.SUCCESS;
		});
	}

}
