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
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.internal.lettuce.LettuceResult;
import com.buession.redis.exception.RedisException;
import com.buession.redis.exception.RedisPipelineException;
import com.buession.redis.exception.RedisTransactionException;
import com.buession.redis.pipeline.PipelineProxy;
import com.buession.redis.transaction.TransactionProxy;
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

	/**
	 * Lettuce 单机模式常规命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class LettuceCommand<SR, R> extends
			AbstractStandaloneCommand<LettuceStandaloneClient, LettuceConnection, RedisCommands<byte[], byte[]>, SR, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public LettuceCommand(final LettuceStandaloneClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceCommand(final LettuceStandaloneClient client, final Command command,
							  final Executor<RedisCommands<byte[], byte[]>, SR> executor,
							  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public LettuceCommand(final LettuceStandaloneClient client, final Command command,
							  final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceCommand(final LettuceStandaloneClient client, final Command command, final SubCommand subCommand,
							  final Executor<RedisCommands<byte[], byte[]>, SR> executor,
							  final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getStatefulConnection().sync());
			return result == null ? null : converter.convert(result);
		}

	}

	/**
	 * Lettuce 单机模式管道模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class LettucePipelineCommand<SR, R> extends
			AbstractStandaloneCommand<LettuceStandaloneClient, LettuceConnection, RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public LettucePipelineCommand(final LettuceStandaloneClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettucePipelineCommand(final LettuceStandaloneClient client, final Command command,
									  final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
									  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public LettucePipelineCommand(final LettuceStandaloneClient client, final Command command,
									  final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettucePipelineCommand(final LettuceStandaloneClient client, final Command command,
									  final SubCommand subCommand,
									  final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
									  final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>> pipelineFactory =
						(PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>>) pipeline;

				final Runner runner = new PipelineRunner<>((context)->{
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

	/**
	 * Lettuce 单机模式事务模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class LettuceTransactionCommand<SR, R> extends
			AbstractStandaloneCommand<LettuceStandaloneClient, LettuceConnection, RedisAsyncCommands<byte[], byte[]>,
					RedisFuture<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public LettuceTransactionCommand(final LettuceStandaloneClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceTransactionCommand(final LettuceStandaloneClient client, final Command command,
										 final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
										 final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public LettuceTransactionCommand(final LettuceStandaloneClient client, final Command command,
										 final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link LettuceStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceTransactionCommand(final LettuceStandaloneClient client, final Command command,
										 final SubCommand subCommand,
										 final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
										 final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.transaction.Transaction transaction = transaction();

			if(transaction instanceof TransactionProxy){
				final TransactionProxy<RedisCommands<byte[], byte[]>, LettuceResult<Object, Object>> transactionFactory =
						(TransactionProxy<RedisCommands<byte[], byte[]>, LettuceResult<Object, Object>>) transaction;

				final Runner runner = new TransactionRunner<>(
						(context)->executor.execute(connection.getStatefulConnection().async()), transactionFactory,
						converter);

				transactionFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisTransactionException("ERR EXEC without MULTI. Did you forget to call multi?");
			}
		}

	}

	/**
	 * Lettuce 哨兵模式常规命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class LettuceSentinelCommand<SR, R> extends
			AbstractSentinelCommand<LettuceSentinelClient, LettuceSentinelConnection, RedisSentinelCommands<byte[], byte[]>, SR, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public LettuceSentinelCommand(final LettuceSentinelClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceSentinelCommand(final LettuceSentinelClient client, final Command command,
									  final Executor<RedisSentinelCommands<byte[], byte[]>, SR> executor,
									  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public LettuceSentinelCommand(final LettuceSentinelClient client, final Command command,
									  final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceSentinelCommand(final LettuceSentinelClient client, final Command command,
									  final SubCommand subCommand,
									  final Executor<RedisSentinelCommands<byte[], byte[]>, SR> executor,
									  final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getStatefulRedisSentinelConnection().sync());
			return result == null ? null : converter.convert(result);
		}

	}

	/**
	 * Lettuce 哨兵模式管道模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class LettuceSentinelPipelineCommand<SR, R> extends
			AbstractSentinelCommand<LettuceSentinelClient, LettuceSentinelConnection, RedisSentinelAsyncCommands<byte[], byte[]>, RedisFuture<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public LettuceSentinelPipelineCommand(final LettuceSentinelClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceSentinelPipelineCommand(final LettuceSentinelClient client, final Command command,
											  final Executor<RedisSentinelAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
											  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public LettuceSentinelPipelineCommand(final LettuceSentinelClient client, final Command command,
											  final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceSentinelPipelineCommand(final LettuceSentinelClient client, final Command command,
											  final SubCommand subCommand,
											  final Executor<RedisSentinelAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
											  final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>> pipelineFactory =
						(PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>>) pipeline;

				final Runner runner = new PipelineRunner<>((context)->{
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

	/**
	 * Lettuce 哨兵模式事务模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class LettuceSentinelTransactionCommand<SR, R> extends
			AbstractSentinelCommand<LettuceSentinelClient, LettuceSentinelConnection, RedisSentinelAsyncCommands<byte[], byte[]>,
					RedisFuture<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public LettuceSentinelTransactionCommand(final LettuceSentinelClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceSentinelTransactionCommand(final LettuceSentinelClient client, final Command command,
												 final Executor<RedisSentinelAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
												 final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public LettuceSentinelTransactionCommand(final LettuceSentinelClient client, final Command command,
												 final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link LettuceSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceSentinelTransactionCommand(final LettuceSentinelClient client, final Command command,
												 final SubCommand subCommand,
												 final Executor<RedisSentinelAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
												 final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.transaction.Transaction transaction = transaction();

			if(transaction instanceof TransactionProxy){
				final TransactionProxy<RedisCommands<byte[], byte[]>, LettuceResult<Object, Object>> transactionFactory =
						(TransactionProxy<RedisCommands<byte[], byte[]>, LettuceResult<Object, Object>>) transaction;

				final Runner runner = new TransactionRunner<>(
						(context)->executor.execute(connection.getStatefulRedisSentinelConnection().async()),
						transactionFactory, converter);

				transactionFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisTransactionException("ERR EXEC without MULTI. Did you forget to call multi?");
			}
		}

	}

	/**
	 * Lettuce 集群模式常规命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class LettuceClusterCommand<SR, R> extends
			AbstractClusterCommand<LettuceClusterClient, LettuceClusterConnection, RedisClusterCommands<byte[], byte[]>, SR, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public LettuceClusterCommand(final LettuceClusterClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceClusterCommand(final LettuceClusterClient client, final Command command,
									 final Executor<RedisClusterCommands<byte[], byte[]>, SR> executor,
									 final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public LettuceClusterCommand(final LettuceClusterClient client, final Command command,
									 final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceClusterCommand(final LettuceClusterClient client, final Command command,
									 final SubCommand subCommand,
									 final Executor<RedisClusterCommands<byte[], byte[]>, SR> executor,
									 final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getStatefulRedisClusterConnection().sync());
			return result == null ? null : converter.convert(result);
		}

	}

	/**
	 * Lettuce 集群模式管道模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class LettuceClusterPipelineCommand<SR, R> extends
			AbstractClusterCommand<LettuceClusterClient, LettuceClusterConnection, RedisClusterAsyncCommands<byte[], byte[]>, RedisFuture<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public LettuceClusterPipelineCommand(final LettuceClusterClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceClusterPipelineCommand(final LettuceClusterClient client, final Command command,
											 final Executor<RedisClusterAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
											 final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public LettuceClusterPipelineCommand(final LettuceClusterClient client, final Command command,
											 final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceClusterPipelineCommand(final LettuceClusterClient client, final Command command,
											 final SubCommand subCommand,
											 final Executor<RedisClusterAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
											 final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>> pipelineFactory =
						(PipelineProxy<FlushEachCommand, LettuceResult<Object, Object>>) pipeline;

				final Runner runner = new PipelineRunner<>((context)->{
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

	/**
	 * Lettuce 集群模式集群模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class LettuceClusterTransactionCommand<SR, R> extends
			AbstractClusterCommand<LettuceClusterClient, LettuceClusterConnection, RedisClusterAsyncCommands<byte[], byte[]>,
					RedisFuture<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public LettuceClusterTransactionCommand(final LettuceClusterClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceClusterTransactionCommand(final LettuceClusterClient client, final Command command,
												final Executor<RedisClusterAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
												final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public LettuceClusterTransactionCommand(final LettuceClusterClient client, final Command command,
												final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link LettuceClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public LettuceClusterTransactionCommand(final LettuceClusterClient client, final Command command,
												final SubCommand subCommand,
												final Executor<RedisClusterAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
												final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.transaction.Transaction transaction = transaction();

			if(transaction instanceof TransactionProxy){
				final TransactionProxy<RedisCommands<byte[], byte[]>, LettuceResult<Object, Object>> transactionFactory =
						(TransactionProxy<RedisCommands<byte[], byte[]>, LettuceResult<Object, Object>>) transaction;

				final Runner runner = new TransactionRunner<>(
						(context)->executor.execute(connection.getStatefulRedisClusterConnection().async()),
						transactionFactory, converter);

				transactionFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisTransactionException("ERR EXEC without MULTI. Did you forget to call multi?");
			}
		}

	}

	abstract class PtRunner<T, SR, R> implements com.buession.redis.core.Command.Runner {

		protected final com.buession.redis.core.Command.Executor<T, RedisFuture<SR>> executor;

		protected final Converter<SR, R> converter;

		public PtRunner(final com.buession.redis.core.Command.Executor<T, RedisFuture<SR>> executor,
						final Converter<SR, R> converter) {
			this.executor = executor;
			this.converter = converter;
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

		private final PipelineProxy<T, LettuceResult<Object, Object>> pipelineFactory;

		public PipelineRunner(final com.buession.redis.core.Command.Executor<T, RedisFuture<SR>> executor,
							  final PipelineProxy<T, LettuceResult<Object, Object>> pipelineFactory,
							  final Converter<SR, R> converter) {
			super(executor, converter);
			this.pipelineFactory = pipelineFactory;
		}

		@SuppressWarnings({"unchecked"})
		@Override
		public LettuceResult<SR, R> run() throws RedisException {
			final RedisFuture<SR> future = executor.execute(pipelineFactory.getObject());
			return converter == null ? newLettuceResult(future) : newLettuceResult(future, converter);
		}

	}

	final class TransactionRunner<T, SR, R> extends PtRunner<T, SR, R> {

		private final TransactionProxy<T, LettuceResult<Object, Object>> transactionProxy;

		public TransactionRunner(final com.buession.redis.core.Command.Executor<T, RedisFuture<SR>> executor,
								 final TransactionProxy<T, LettuceResult<Object, Object>> transactionProxy,
								 final Converter<SR, R> converter) {
			super(executor, converter);
			this.transactionProxy = transactionProxy;
		}

		@SuppressWarnings({"unchecked"})
		@Override
		public LettuceResult<SR, R> run() throws RedisException {
			final RedisFuture<SR> future = executor.execute(transactionProxy.getObject());
			return converter == null ? newLettuceResult(future) : newLettuceResult(future, converter);
		}

	}

}
