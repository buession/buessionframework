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

import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.lettuce.LettuceRedisConnection;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.RedisSubCommand;
import com.buession.redis.core.internal.lettuce.LettuceResult;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.PipelineProxy;
import com.buession.redis.transaction.TransactionProxy;
import io.lettuce.core.RedisCommands;
import io.lettuce.core.RedisCommandsInvocationHandler;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.StatefulRedisClusterCommandsHandler;
import io.lettuce.core.StatefulRedisCommandsHandler;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;

import java.lang.reflect.Proxy;

/**
 * Lettuce Redis 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface LettuceRedisCommands extends com.buession.redis.core.command.RedisCommands {

	/**
	 * Lettuce 命令
	 *
	 * @param <SR>
	 * 		原始类型
	 * @param <R>
	 * 		返回类型
	 *
	 * @since 4.0.0
	 */
	final class LettuceCommand<SR, R>
			extends AbstractCommand<LettuceRedisClient, RedisCommands<byte[], byte[]>, SR, SR, R> {

		public LettuceCommand(final LettuceRedisClient client, final RedisCommand command) {
			super(client, command);
		}

		public LettuceCommand(final LettuceRedisClient client, final RedisCommand command,
		                      final Executor<RedisCommands<byte[], byte[]>, SR> executor,
		                      final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		public LettuceCommand(final LettuceRedisClient client, final RedisCommand command,
		                      final RedisSubCommand subCommand) {
			super(client, command, subCommand);
		}

		public LettuceCommand(final LettuceRedisClient client, final RedisCommand command,
		                      final RedisSubCommand subCommand,
		                      final Executor<RedisCommands<byte[], byte[]>, SR> executor,
		                      final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute(final RedisConnection conn) throws RedisException {
			final StatefulConnection<byte[], byte[]> connection =
					((LettuceRedisConnection<StatefulConnection<byte[], byte[]>>) conn).getConn();
			final SR result = executor.execute(createRedisCommands(connection));
			return result == null ? null : converter.convert(result);
		}

		@SuppressWarnings({"unchecked"})
		private static <K, V> RedisCommands<K, V> createRedisCommands(final StatefulConnection<K, V> connection) {
			RedisCommandsInvocationHandler<K, V> handler;

			if(connection instanceof StatefulRedisClusterConnection){
				handler = new StatefulRedisClusterCommandsHandler<>((StatefulRedisClusterConnection<K, V>) connection);
			}else if(connection instanceof StatefulRedisSentinelConnection){
				handler = new StatefulRedisCommandsHandler<>((StatefulRedisConnection<K, V>) connection);
			}else{
				handler = new StatefulRedisCommandsHandler<>((StatefulRedisConnection<K, V>) connection);
			}

			return (RedisCommands<K, V>) Proxy.newProxyInstance(RedisCommands.class.getClassLoader(),
					new Class[]{RedisCommands.class}, handler);
		}

	}

}
