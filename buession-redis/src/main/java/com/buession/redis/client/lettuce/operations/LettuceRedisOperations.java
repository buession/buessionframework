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

import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.lettuce.LettuceRedisConnection;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.internal.lettuce.LettuceResult;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.PipelineProxy;
import com.buession.redis.transaction.TransactionProxy;
import io.lettuce.core.RedisCommands;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulConnection;

/**
 * Lettuce Redis 命令操作接口
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface LettuceRedisOperations extends RedisOperations {

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
	final class LettuceCommand<SR, R> extends
			AbstractRedisOperationsCommand<LettuceRedisClient, LettuceRedisConnection<? extends StatefulConnection<byte[], byte[]>>, RedisCommands<byte[],
					byte[]>, SR, SR, R> {

		public LettuceCommand(final LettuceRedisClient client, final com.buession.redis.core.command.Command command) {
			super(client, command);
		}

		public LettuceCommand(final LettuceRedisClient client, final com.buession.redis.core.command.Command command,
							  final Executor<RedisCommands<byte[], byte[]>, SR> executor,
							  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		public LettuceCommand(final LettuceRedisClient client, final com.buession.redis.core.command.Command command,
							  final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		public LettuceCommand(final LettuceRedisClient client, final com.buession.redis.core.command.Command command,
							  final SubCommand subCommand,
							  final Executor<RedisCommands<byte[], byte[]>, SR> executor,
							  final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getConn());
			return converter.convert(result);
		}

	}

	/**
	 * Lettuce 命令构建器
	 *
	 * @param <SR>
	 * 		原始类型
	 * @param <R>
	 * 		返回类型
	 *
	 * @since 4.0.0
	 */
	final class LettuceCommandBuilder<SR, R> extends BaseCommandBuilder<LettuceRedisClient, RedisCommands<byte[],
			byte[]>, SR, R> {

		private LettuceCommandBuilder(final LettuceRedisClient client, final Command command) {
			super(client, command);
		}

		private LettuceCommandBuilder(final LettuceRedisClient client, final Command command,
									  final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		public static <SR, R> LettuceCommandBuilder<SR, R> newBuilder(final LettuceRedisClient client,
																	  final Command command) {
			return new LettuceCommandBuilder<>(client, command);
		}

		public static <SR, R> LettuceCommandBuilder<SR, R> newBuilder(final LettuceRedisClient client,
																	  final Command command,
																	  final SubCommand subCommand) {
			return new LettuceCommandBuilder<>(client, command, subCommand);
		}

		@Override
		public R run() {
			final LettuceCommand<SR, R> command = new LettuceCommand<>(this.client, this.command, this.subCommand,
					this.executor, this.converter);
			return command.run(this.arguments);
		}

	}

	abstract class PtRunner<T, SR, R> implements com.buession.redis.core.Command.Runner<LettuceResult<SR, R>> {

		protected final com.buession.redis.core.Command.Executor<T, RedisFuture<SR>> executor;

		protected final T context;

		protected final Converter<SR, R> converter;

		public PtRunner(final com.buession.redis.core.Command.Executor<T, RedisFuture<SR>> executor, final T context,
						final Converter<SR, R> converter) {
			this.executor = executor;
			this.context = context;
			this.converter = converter;
		}

		@Override
		public LettuceResult<SR, R> run() throws RedisException {
			final RedisFuture<SR> future = executor.execute(context);
			return converter == null ? newLettuceResult(future) : newLettuceResult(future, converter);
		}

		protected LettuceResult<SR, R> newLettuceResult(final RedisFuture<SR> future) {
			return LettuceResult.Builder.<SR, R>fromRedisFuture(future).build();
		}

		protected LettuceResult<SR, R> newLettuceResult(final RedisFuture<SR> future,
														final Converter<SR, R> converter) {
			return LettuceResult.Builder.<SR, R>fromRedisFuture(future).mappedWith(converter).build();
		}

	}

	final class PipelineRunner<T, SR, R> extends PtRunner<T, SR, R> {

		public PipelineRunner(final com.buession.redis.core.Command.Executor<T, RedisFuture<SR>> executor,
							  final PipelineProxy<T, LettuceResult<SR, R>> pipelineFactory,
							  final Converter<SR, R> converter) {
			super(executor, pipelineFactory.getObject(), converter);
		}

	}

	final class TransactionRunner<T, SR, R> extends PtRunner<T, SR, R> {

		public TransactionRunner(final com.buession.redis.core.Command.Executor<T, RedisFuture<SR>> executor,
								 final TransactionProxy<T, LettuceResult<SR, R>> transactionProxy,
								 final Converter<SR, R> converter) {
			super(executor, transactionProxy.getObject(), converter);
		}

	}

}
