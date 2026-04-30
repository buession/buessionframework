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
import com.buession.redis.core.command.AbstractRedisCommands;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.RedisSubCommand;
import com.buession.redis.core.internal.lettuce.LettuceResult;
import com.buession.redis.exception.NotMultiRedisException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.PipelineProxy;
import com.buession.redis.transaction.TransactionProxy;
import io.lettuce.core.BaseRedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * Lettuce Redis 命令抽象类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractLettuceRedisCommands extends AbstractRedisCommands<LettuceRedisClient>
		implements LettuceRedisCommands, com.buession.redis.core.command.RedisCommands {

	public AbstractLettuceRedisCommands(final LettuceRedisClient client) {
		super(client);
	}

	protected <R> R executeCommand(final RedisCommand command) {
		if(isMulti()){
			return client.execute(new LettuceAsyncCommand<>(client, command));
		}else{
			return client.execute(new LettuceCommand<>(client, command));
		}
	}

	protected <R> R executeCommand(final RedisCommand command,
	                               final Command.Executor<RedisCommands<byte[], byte[]>, R> executor,
	                               final Command.Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<R>> asyncExecutor) {
		return executeCommand(command, executor, asyncExecutor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command,
	                                   final Command.Executor<RedisCommands<byte[], byte[]>, SR> executor,
	                                   final Command.Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> asyncExecutor,
	                                   final Converter<SR, R> converter) {
		if(isMulti()){
			return client.execute(new LettuceAsyncCommand<>(client, command, asyncExecutor, converter));
		}else{
			return client.execute(new LettuceCommand<>(client, command, executor, converter));
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final CommandArguments args) {
		if(isMulti()){
			return client.execute(new LettuceAsyncCommand<>(client, command), args);
		}else{
			return client.execute(new LettuceCommand<>(client, command), args);
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final CommandArguments args,
	                               final Command.Executor<RedisCommands<byte[], byte[]>, R> executor,
	                               final Command.Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<R>> asyncExecutor) {
		return executeCommand(command, args, executor, asyncExecutor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command, final CommandArguments args,
	                                   final Command.Executor<RedisCommands<byte[], byte[]>, SR> executor,
	                                   final Command.Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> asyncExecutor,
	                                   final Converter<SR, R> converter) {
		if(isMulti()){
			return client.execute(new LettuceAsyncCommand<>(client, command, asyncExecutor, converter), args);
		}else{
			return client.execute(new LettuceCommand<>(client, command, executor, converter), args);
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand) {
		if(isMulti()){
			return client.execute(new LettuceAsyncCommand<>(client, command, subCommand));
		}else{
			return client.execute(new LettuceCommand<>(client, command, subCommand));
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                               final Command.Executor<RedisCommands<byte[], byte[]>, R> executor,
	                               final Command.Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<R>> asyncExecutor) {
		return executeCommand(command, subCommand, executor, asyncExecutor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                                   final Command.Executor<RedisCommands<byte[], byte[]>, SR> executor,
	                                   final Command.Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> asyncExecutor,
	                                   final Converter<SR, R> converter) {
		if(isMulti()){
			return client.execute(new LettuceAsyncCommand<>(client, command, subCommand, asyncExecutor, converter));
		}else{
			return client.execute(new LettuceCommand<>(client, command, subCommand, executor, converter));
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                               final CommandArguments args) {
		if(isMulti()){
			return client.execute(new LettuceAsyncCommand<>(client, command, subCommand), args);
		}else{
			return client.execute(new LettuceCommand<>(client, command, subCommand), args);
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                               final CommandArguments args,
	                               final Command.Executor<RedisCommands<byte[], byte[]>, R> executor,
	                               final Command.Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<R>> asyncExecutor) {
		return executeCommand(command, subCommand, args, executor, asyncExecutor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                                   final CommandArguments args,
	                                   final Command.Executor<RedisCommands<byte[], byte[]>, SR> executor,
	                                   final Command.Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> asyncExecutor,
	                                   final Converter<SR, R> converter) {
		if(isMulti()){
			return client.execute(new LettuceAsyncCommand<>(client, command, subCommand, asyncExecutor, converter),
					args);
		}else{
			return client.execute(new LettuceCommand<>(client, command, subCommand, executor, converter), args);
		}
	}

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
	protected final static class LettuceCommand<SR, R> extends
			AbstractCommand<LettuceRedisClient, RedisCommands<byte[], byte[]>, SR, SR, R> {

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
			final LettuceRedisConnection<byte[], byte[], BaseRedisClient<byte[], byte[]>> lettuceRedisConnection =
					(LettuceRedisConnection<byte[], byte[], BaseRedisClient<byte[], byte[]>>) conn;
			final SR result = executor.execute(lettuceRedisConnection.getClient().getRedisCommands());
			return result == null ? null : converter.convert(result);
		}

	}

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
	protected final static class LettuceAsyncCommand<SR, R> extends
			AbstractCommand<LettuceRedisClient, RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>, SR, R> {

		public LettuceAsyncCommand(final LettuceRedisClient client, final RedisCommand command) {
			super(client, command);
		}

		public LettuceAsyncCommand(final LettuceRedisClient client, final RedisCommand command,
		                           final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
		                           final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		public LettuceAsyncCommand(final LettuceRedisClient client, final RedisCommand command,
		                           final RedisSubCommand subCommand) {
			super(client, command, subCommand);
		}

		public LettuceAsyncCommand(final LettuceRedisClient client, final RedisCommand command,
		                           final RedisSubCommand subCommand,
		                           final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
		                           final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute(final RedisConnection conn) throws RedisException {
			final com.buession.redis.transaction.Transaction transaction = conn.getTransaction();

			if(transaction != null){
				final TransactionProxy<RedisAsyncCommands<byte[], byte[]>, LettuceResult<SR, R>> transactionProxy =
						(TransactionProxy<RedisAsyncCommands<byte[], byte[]>, LettuceResult<SR, R>>) transaction;
				final RedisFuture<SR> future = executor.execute(transactionProxy.getObject());
				final LettuceResult<SR, R> result =
						converter == null ? newLettuceResult(future) : newLettuceResult(future, converter);

				transactionProxy.getTxResults().add(result);
				return null;
			}

			final com.buession.redis.pipeline.Pipeline pipeline = conn.getPipeline();

			if(pipeline != null){
				final PipelineProxy<RedisAsyncCommands<byte[], byte[]>, LettuceResult<SR, R>> pipelineProxy =
						(PipelineProxy<RedisAsyncCommands<byte[], byte[]>, LettuceResult<SR, R>>) pipeline;
				final RedisFuture<SR> future = executor.execute(pipelineProxy.getObject());
				final LettuceResult<SR, R> result =
						converter == null ? newLettuceResult(future) : newLettuceResult(future, converter);

				pipelineProxy.getTxResults().add(result);
				return null;
			}

			throw new NotMultiRedisException(getCommand(), getSubCommand());
		}

		protected LettuceResult<SR, R> newLettuceResult(final RedisFuture<SR> future) {
			return LettuceResult.Builder.<SR, R>fromRedisFuture(future).build();
		}

		protected LettuceResult<SR, R> newLettuceResult(final RedisFuture<SR> future,
		                                                final Converter<SR, R> converter) {
			return LettuceResult.Builder.<SR, R>fromRedisFuture(future).mappedWith(converter).build();
		}

	}

}
