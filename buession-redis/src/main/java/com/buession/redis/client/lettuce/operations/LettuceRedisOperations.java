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

import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.lettuce.LettuceClusterConnection;
import com.buession.redis.client.connection.lettuce.LettuceConnection;
import com.buession.redis.client.connection.lettuce.LettuceSentinelConnection;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.Command;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.lettuce.LettuceResult;
import com.buession.redis.exception.RedisException;
import com.buession.redis.exception.RedisPipelineException;
import com.buession.redis.pipeline.PipelineProxy;
import io.lettuce.core.FlushEachCommand;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.api.async.RedisClusterAsyncCommands;
import io.lettuce.core.cluster.api.sync.RedisClusterCommands;
import io.lettuce.core.sentinel.api.async.RedisSentinelAsyncCommands;
import io.lettuce.core.sentinel.api.sync.RedisSentinelCommands;

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
							  final Executor<RedisCommands<byte[], byte[]>, SR> executor,
							  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
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
									  final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
									  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>> pipelineFactory =
						(PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>>) pipeline;

				final Runner runner = new PtRunner<>((context)->{
					context.onCommand(connection.getStatefulConnection());
					return executor.execute(connection.getStatefulConnection().async());
				}, pipelineFactory, converter);

				pipelineFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisPipelineException("ERR EXEC without pipeline. Did you forget to call openPipeline?");
			}
		}

	}

	class LettuceTransactionCommand<SR, R> extends
			AbstractSentinelCommand<LettuceSentinelClient, LettuceSentinelConnection, RedisAsyncCommands<byte[], byte[]>,
					RedisFuture<SR>, SR, R> {

		public LettuceTransactionCommand(final LettuceSentinelClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettuceTransactionCommand(final LettuceSentinelClient client, final ProtocolCommand command,
										 final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
										 final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			//executor.execute(connection.getStatefulConnection().async());
			return null;
		}

	}

	class LettuceSentinelCommand<SR, R> extends
			AbstractSentinelCommand<LettuceSentinelClient, LettuceSentinelConnection, RedisSentinelCommands<byte[], byte[]>, SR, SR, R> {

		public LettuceSentinelCommand(final LettuceSentinelClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettuceSentinelCommand(final LettuceSentinelClient client, final ProtocolCommand command,
									  final Executor<RedisSentinelCommands<byte[], byte[]>, SR> executor,
									  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getStatefulRedisSentinelConnection().sync());
			return result == null ? null : converter.convert(result);
		}

	}

	class LettuceSentinelPipelineCommand<SR, R> extends
			AbstractSentinelCommand<LettuceSentinelClient, LettuceSentinelConnection, RedisSentinelAsyncCommands<byte[], byte[]>, RedisFuture<SR>, SR, R> {

		public LettuceSentinelPipelineCommand(final LettuceSentinelClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettuceSentinelPipelineCommand(final LettuceSentinelClient client, final ProtocolCommand command,
											  final Executor<RedisSentinelAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
											  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>> pipelineFactory =
						(PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>>) pipeline;

				final Runner runner = new PtRunner<>((context)->{
					context.onCommand(connection.getStatefulRedisSentinelConnection());
					return executor.execute(connection.getStatefulRedisSentinelConnection().async());
				}, pipelineFactory, converter);

				pipelineFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisPipelineException("ERR EXEC without pipeline. Did you forget to call openPipeline?");
			}
		}

	}

	class LettuceSentinelTransactionCommand<SR, R> extends
			AbstractSentinelCommand<LettuceSentinelClient, LettuceSentinelConnection, RedisSentinelAsyncCommands<byte[], byte[]>,
					RedisFuture<SR>, SR, R> {

		public LettuceSentinelTransactionCommand(final LettuceSentinelClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettuceSentinelTransactionCommand(final LettuceSentinelClient client, final ProtocolCommand command,
												 final Executor<RedisSentinelAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
												 final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			//executor.execute(connection.getStatefulConnection().async());
			return null;
		}

	}

	class LettuceClusterCommand<SR, R> extends
			AbstractClusterCommand<LettuceClusterClient, LettuceClusterConnection, RedisClusterCommands<byte[], byte[]>, SR, SR, R> {

		public LettuceClusterCommand(final LettuceClusterClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettuceClusterCommand(final LettuceClusterClient client, final ProtocolCommand command,
									 final Executor<RedisClusterCommands<byte[], byte[]>, SR> executor,
									 final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getStatefulRedisClusterConnection().sync());
			return result == null ? null : converter.convert(result);
		}

	}

	class LettuceClusterPipelineCommand<SR, R> extends
			AbstractClusterCommand<LettuceClusterClient, LettuceClusterConnection, RedisClusterAsyncCommands<byte[], byte[]>, RedisFuture<SR>, SR, R> {

		public LettuceClusterPipelineCommand(final LettuceClusterClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettuceClusterPipelineCommand(final LettuceClusterClient client, final ProtocolCommand command,
											 final Executor<RedisClusterAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
											 final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>> pipelineFactory =
						(PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>>) pipeline;

				final Runner runner = new PtRunner<>((context)->{
					context.onCommand(connection.getStatefulRedisClusterConnection());
					return executor.execute(connection.getStatefulRedisClusterConnection().async());
				}, pipelineFactory, converter);

				pipelineFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisPipelineException("ERR EXEC without pipeline. Did you forget to call openPipeline?");
			}
		}

	}

	class LettuceClusterTransactionCommand<SR, R> extends
			AbstractClusterCommand<LettuceClusterClient, LettuceClusterConnection, RedisClusterAsyncCommands<byte[], byte[]>,
					RedisFuture<SR>, SR, R> {

		public LettuceClusterTransactionCommand(final LettuceClusterClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public LettuceClusterTransactionCommand(final LettuceClusterClient client, final ProtocolCommand command,
												final Executor<RedisClusterAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
												final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			//executor.execute(connection.getStatefulConnection().async());
			return null;
		}

	}

	final class PtRunner<T, SR, R> implements Command.Runner {

		private final Command.Executor<T, RedisFuture<SR>> executor;

		private final PipelineProxy<T, LettuceResult<Object, Object>> pipelineFactory;

		private final Converter<SR, R> converter;

		public PtRunner(final Command.Executor<T, RedisFuture<SR>> executor,
						final PipelineProxy<T, LettuceResult<Object, Object>> pipelineFactory,
						final Converter<SR, R> converter) {
			this.executor = executor;
			this.pipelineFactory = pipelineFactory;
			this.converter = converter;
		}

		@SuppressWarnings({"unchecked"})
		@Override
		public LettuceResult<SR, R> run() throws RedisException {
			final RedisFuture<SR> future = executor.execute(pipelineFactory.getObject());
			return converter == null ? newLettuceResult(future) : newLettuceResult(future, converter);
		}

		private LettuceResult<SR, R> newLettuceResult(final RedisFuture<SR> future) {
			return LettuceResult.Builder.<SR, R>fromRedisFuture(future).build();
		}

		private LettuceResult<SR, R> newLettuceResult(final RedisFuture<SR> future,
													  final Converter<SR, R> converter) {
			return LettuceResult.Builder.<SR, R>fromRedisFuture(future).mappedWith(converter).build();
		}

	}

}
