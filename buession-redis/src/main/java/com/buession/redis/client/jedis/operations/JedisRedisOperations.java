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

import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.internal.jedis.JedisResult;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.PipelineProxy;
import com.buession.redis.transaction.TransactionProxy;
import redis.clients.jedis.Response;
import redis.clients.jedis.UnifiedJedis;

/**
 * Jedis Redis 命令操作接口
 *
 * @author Yong.Teng
 */
public interface JedisRedisOperations extends RedisOperations {

	/**
	 * Jedis 命令
	 *
	 * @param <SR>
	 * 		原始类型
	 * @param <R>
	 * 		返回类型
	 *
	 * @since 4.0.0
	 */
	final class JedisCommand<SR, R> extends
			AbstractRedisOperationsCommand<JedisRedisClient, JedisRedisConnection<? extends UnifiedJedis>, UnifiedJedis,
					SR, SR, R> {

		public JedisCommand(final JedisRedisClient client, final Command command) {
			super(client, command);
		}

		public JedisCommand(final JedisRedisClient client, final Command command,
							final Executor<UnifiedJedis, SR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		public JedisCommand(final JedisRedisClient client, final Command command, final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		public JedisCommand(final JedisRedisClient client, final Command command, final SubCommand subCommand,
							final Executor<UnifiedJedis, SR> executor, final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getClient());
			return converter.convert(result);
		}

	}

	/**
	 * Jedis 命令构建器
	 *
	 * @param <SR>
	 * 		原始类型
	 * @param <R>
	 * 		返回类型
	 *
	 * @since 4.0.0
	 */
	final class JedisCommandBuilder<SR, R> extends BaseCommandBuilder<JedisRedisClient, UnifiedJedis, SR, R> {

		private JedisCommandBuilder(final JedisRedisClient client, final Command command) {
			super(client, command);
		}

		private JedisCommandBuilder(final JedisRedisClient client, final Command command, final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		public static <SR, R> JedisCommandBuilder<SR, R> newBuilder(final JedisRedisClient client,
																	final Command command) {
			return new JedisCommandBuilder<>(client, command);
		}

		public static <SR, R> JedisCommandBuilder<SR, R> newBuilder(final JedisRedisClient client,
																	final Command command,
																	final SubCommand subCommand) {
			return new JedisCommandBuilder<>(client, command, subCommand);
		}

		@Override
		public R run() {
			final JedisCommand<SR, R> command = new JedisCommand<>(this.client, this.command, this.subCommand,
					this.executor, this.converter);
			return command.run(this.arguments);
		}

	}

	abstract class PtRunner<T, SR, R> implements com.buession.redis.core.Command.Runner<JedisResult<SR, R>> {

		protected final com.buession.redis.core.Command.Executor<T, Response<SR>> executor;

		protected final T context;

		protected final Converter<SR, R> converter;

		public PtRunner(final com.buession.redis.core.Command.Executor<T, Response<SR>> executor, final T context,
						final Converter<SR, R> converter) {
			this.executor = executor;
			this.context = context;
			this.converter = converter;
		}

		@Override
		public JedisResult<SR, R> run() throws RedisException {
			final Response<SR> response = executor.execute(context);
			return converter == null ? newJedisResult(response) : newJedisResult(response, converter);
		}

		protected JedisResult<SR, R> newJedisResult(final Response<SR> response) {
			return JedisResult.Builder.<SR, R>fromResponse(response).build();
		}

		protected JedisResult<SR, R> newJedisResult(final Response<SR> response, final Converter<SR, R> converter) {
			return JedisResult.Builder.<SR, R>fromResponse(response).mappedWith(converter).build();
		}

	}

	final class PipelineRunner<T, SR, R> extends PtRunner<T, SR, R> {

		public PipelineRunner(final com.buession.redis.core.Command.Executor<T, Response<SR>> executor,
							  final PipelineProxy<T, JedisResult<SR, R>> pipelineFactory,
							  final Converter<SR, R> converter) {
			super(executor, pipelineFactory.getObject(), converter);
		}

	}

	final class TransactionRunner<T, SR, R> extends PtRunner<T, SR, R> {

		public TransactionRunner(final com.buession.redis.core.Command.Executor<T, Response<SR>> executor,
								 final TransactionProxy<T, JedisResult<SR, R>> transactionFactory,
								 final Converter<SR, R> converter) {
			super(executor, transactionFactory.getObject(), converter);
		}

	}

}
