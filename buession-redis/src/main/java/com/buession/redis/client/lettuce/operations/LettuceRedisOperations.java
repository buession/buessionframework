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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.operations;

import com.buession.core.Executor;
import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.lettuce.LettuceConnection;
import com.buession.redis.client.connection.lettuce.LettuceRedisConnection;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.Command;
import com.buession.redis.core.RedisMode;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.AbstractRedisOperationsCommand;
import com.buession.redis.core.internal.convert.lettuce.response.RedisFutureConverter;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedPipelineCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisException;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * Lettuce Redis 命令操作接口
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface LettuceRedisOperations extends RedisOperations {

	class LettuceCommand<SR, R> extends
			AbstractStandaloneCommand<LettuceStandaloneClient, LettuceConnection, RedisCommands<byte[], byte[]>, SR, SR, R> {

		public LettuceCommand(final LettuceStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettuceCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
							  final Converter<SR, R> converter) {
			super(client, command, converter);
		}

		public LettuceCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
							  final Executor<RedisCommands<byte[], byte[]>, SR> executor) {
			super(client, command, executor);
		}

		public LettuceCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
							  final Executor<RedisCommands<byte[], byte[]>, SR> executor,
							  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws Exception {
			final SR result = executor.execute(connection.getStatefulConnection().sync());
			return result == null ? null : converter.convert(result);
		}

	}

	class LettucePipelineCommand<SR, R> extends
			AbstractStandaloneCommand<LettuceStandaloneClient, LettuceConnection, RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>, SR, R> {

		public LettucePipelineCommand(final LettuceStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettucePipelineCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
									  final Converter<SR, R> converter) {
			super(client, command, converter);
		}

		public LettucePipelineCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
									  final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor) {
			super(client, command, executor);
		}

		public LettucePipelineCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
									  final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
									  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws Exception {
			executor.execute(connection.getStatefulConnection().async());
			return null;
		}

	}

	class LettucePipelineCommand2<SR, R> extends
			AbstractStandaloneCommand<LettuceStandaloneClient, LettuceConnection, RedisAsyncCommands<byte[], byte[]>, SR, SR, R> {

		public LettucePipelineCommand2(final LettuceStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettucePipelineCommand2(final LettuceStandaloneClient client, final ProtocolCommand command,
									   final Converter<SR, R> converter) {
			super(client, command, converter);
		}

		public LettucePipelineCommand2(final LettuceStandaloneClient client, final ProtocolCommand command,
									   final Executor<RedisAsyncCommands<byte[], byte[]>, SR> executor) {
			super(client, command, executor);
		}

		public LettucePipelineCommand2(final LettuceStandaloneClient client, final ProtocolCommand command,
									   final Executor<RedisAsyncCommands<byte[], byte[]>, SR> executor,
									   final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws Exception {
			executor.execute(connection.getStatefulConnection().async());
			return null;
		}

	}

	class LettuceTransactionCommand<SR, R> extends
			AbstractStandaloneCommand<LettuceStandaloneClient, LettuceConnection, RedisAsyncCommands<byte[], byte[]>,
					RedisFuture<SR>, SR, R> {

		public LettuceTransactionCommand(final LettuceStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettuceTransactionCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
										 final Converter<SR, R> converter) {
			super(client, command, converter);
		}

		public LettuceTransactionCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
										 final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor) {
			super(client, command, executor);
		}

		public LettuceTransactionCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
										 final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
										 final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws Exception {
			executor.execute(connection.getStatefulConnection().async());
			return null;
		}

	}

	class LettuceTransactionCommand2<SR, R> extends
			AbstractStandaloneCommand<LettuceStandaloneClient, LettuceConnection, RedisAsyncCommands<byte[], byte[]>, SR, SR, R> {

		public LettuceTransactionCommand2(final LettuceStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettuceTransactionCommand2(final LettuceStandaloneClient client, final ProtocolCommand command,
										  final Converter<SR, R> converter) {
			super(client, command, converter);
		}

		public LettuceTransactionCommand2(final LettuceStandaloneClient client, final ProtocolCommand command,
										  final Executor<RedisAsyncCommands<byte[], byte[]>, SR> executor) {
			super(client, command, executor);
		}

		public LettuceTransactionCommand2(final LettuceStandaloneClient client, final ProtocolCommand command,
										  final Executor<RedisAsyncCommands<byte[], byte[]>, SR> executor,
										  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws Exception {
			executor.execute(connection.getStatefulConnection().async());
			return null;
		}

	}

}
