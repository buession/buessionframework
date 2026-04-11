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

import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.AbstractRedisCommands;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.RedisCommands;
import com.buession.redis.core.command.RedisSubCommand;
import com.buession.redis.core.internal.jedis.JedisResult;
import com.buession.redis.exception.NotMultiRedisException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.PipelineProxy;
import com.buession.redis.transaction.TransactionProxy;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.UnifiedJedis;

/**
 * Jedis Redis 命令抽象类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractJedisRedisCommands extends AbstractRedisCommands<JedisRedisClient>
		implements JedisRedisCommands, RedisCommands {

	/**
	 * 构造函数
	 *
	 * @param client
	 *        {@link JedisRedisClient}
	 */
	public AbstractJedisRedisCommands(final JedisRedisClient client) {
		super(client);
	}

	protected <R> R executeCommand(final RedisCommand command) {
		if(isTransaction()){
			return client.execute(new JedisTransactionCommand<>(client, command));
		}else if(isPipeline()){
			return client.execute(new JedisPipelineCommand<>(client, command));
		}else{
			return client.execute(new JedisCommand<>(client, command));
		}
	}

	protected <R> R executeCommand(final RedisCommand command,
	                               final Command.Executor<Transaction, Response<R>> transactionExecutor,
	                               final Command.Executor<Pipeline, Response<R>> pipelineExecutor,
	                               final Command.Executor<UnifiedJedis, R> executor) {
		return executeCommand(command, transactionExecutor, pipelineExecutor, executor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command,
	                                   final Command.Executor<Transaction, Response<SR>> transactionExecutor,
	                                   final Command.Executor<Pipeline, Response<SR>> pipelineExecutor,
	                                   final Command.Executor<UnifiedJedis, SR> executor,
	                                   final Converter<SR, R> converter) {
		if(isTransaction()){
			return client.execute(new JedisTransactionCommand<>(client, command, transactionExecutor, converter));
		}else if(isPipeline()){
			return client.execute(new JedisPipelineCommand<>(client, command, pipelineExecutor, converter));
		}else{
			return client.execute(new JedisCommand<>(client, command, executor, converter));
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final CommandArguments args) {
		if(isTransaction()){
			return client.execute(new JedisTransactionCommand<>(client, command), args);
		}else if(isPipeline()){
			return client.execute(new JedisPipelineCommand<>(client, command), args);
		}else{
			return client.execute(new JedisCommand<>(client, command), args);
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final CommandArguments args,
	                               final Command.Executor<Transaction, Response<R>> transactionExecutor,
	                               final Command.Executor<Pipeline, Response<R>> pipelineExecutor,
	                               final Command.Executor<UnifiedJedis, R> executor) {
		return executeCommand(command, args, transactionExecutor, pipelineExecutor, executor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command, final CommandArguments args,
	                                   final Command.Executor<Transaction, Response<SR>> transactionExecutor,
	                                   final Command.Executor<Pipeline, Response<SR>> pipelineExecutor,
	                                   final Command.Executor<UnifiedJedis, SR> executor,
	                                   final Converter<SR, R> converter) {
		if(isTransaction()){
			return client.execute(new JedisTransactionCommand<>(client, command, transactionExecutor, converter), args);
		}else if(isPipeline()){
			return client.execute(new JedisPipelineCommand<>(client, command, pipelineExecutor, converter), args);
		}else{
			return client.execute(new JedisCommand<>(client, command, executor, converter), args);
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand) {
		if(isTransaction()){
			return client.execute(new JedisTransactionCommand<>(client, command, subCommand));
		}else if(isPipeline()){
			return client.execute(new JedisPipelineCommand<>(client, command, subCommand));
		}else{
			return client.execute(new JedisCommand<>(client, command, subCommand));
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                               final Command.Executor<Transaction, Response<R>> transactionExecutor,
	                               final Command.Executor<Pipeline, Response<R>> pipelineExecutor,
	                               final Command.Executor<UnifiedJedis, R> executor) {
		return executeCommand(command, subCommand, transactionExecutor, pipelineExecutor, executor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                                   final Command.Executor<Transaction, Response<SR>> transactionExecutor,
	                                   final Command.Executor<Pipeline, Response<SR>> pipelineExecutor,
	                                   final Command.Executor<UnifiedJedis, SR> executor,
	                                   final Converter<SR, R> converter) {
		if(isTransaction()){
			return client.execute(
					new JedisTransactionCommand<>(client, command, subCommand, transactionExecutor, converter));
		}else if(isPipeline()){
			return client.execute(new JedisPipelineCommand<>(client, command, subCommand, pipelineExecutor, converter));
		}else{
			return client.execute(new JedisCommand<>(client, command, subCommand, executor, converter));
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                               final CommandArguments args) {
		if(isTransaction()){
			return client.execute(new JedisTransactionCommand<>(client, command, subCommand), args);
		}else if(isPipeline()){
			return client.execute(new JedisPipelineCommand<>(client, command, subCommand), args);
		}else{
			return client.execute(new JedisCommand<>(client, command, subCommand), args);
		}
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                               final CommandArguments args,
	                               final Command.Executor<Transaction, Response<R>> transactionExecutor,
	                               final Command.Executor<Pipeline, Response<R>> pipelineExecutor,
	                               final Command.Executor<UnifiedJedis, R> executor) {
		return executeCommand(command, subCommand, args, transactionExecutor, pipelineExecutor, executor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                                   final CommandArguments args,
	                                   final Command.Executor<Transaction, Response<SR>> transactionExecutor,
	                                   final Command.Executor<Pipeline, Response<SR>> pipelineExecutor,
	                                   final Command.Executor<UnifiedJedis, SR> executor,
	                                   final Converter<SR, R> converter) {
		if(isTransaction()){
			return client.execute(
					new JedisTransactionCommand<>(client, command, subCommand, transactionExecutor, converter), args);
		}else if(isPipeline()){
			return client.execute(
					new JedisPipelineCommand<>(client, command, subCommand, pipelineExecutor, converter), args);
		}else{
			return client.execute(new JedisCommand<>(client, command, subCommand, executor, converter), args);
		}
	}

	private static abstract class AbstractPipelineCommand<CXT, OSR, SR, R>
			extends AbstractCommand<JedisRedisClient, CXT,
			OSR, SR, R> {

		public AbstractPipelineCommand(final JedisRedisClient client, final RedisCommand command) {
			super(client, command);
		}

		public AbstractPipelineCommand(final JedisRedisClient client, final RedisCommand command,
		                               final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		public AbstractPipelineCommand(final JedisRedisClient client, final RedisCommand command,
		                               final RedisSubCommand subCommand) {
			super(client, command, subCommand);
		}

		public AbstractPipelineCommand(final JedisRedisClient client, final RedisCommand command,
		                               final RedisSubCommand subCommand, final Executor<CXT, OSR> executor,
		                               final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		protected JedisResult<SR, R> newJedisResult(final Response<SR> response) {
			return JedisResult.Builder.<SR, R>fromResponse(response).build();
		}

		protected JedisResult<SR, R> newJedisResult(final Response<SR> response, final Converter<SR, R> converter) {
			return JedisResult.Builder.<SR, R>fromResponse(response).mappedWith(converter).build();
		}

	}

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
	protected final static class JedisCommand<SR, R>
			extends AbstractCommand<JedisRedisClient, UnifiedJedis, SR, SR, R> {

		public JedisCommand(final JedisRedisClient client, final RedisCommand command) {
			super(client, command);
		}

		public JedisCommand(final JedisRedisClient client, final RedisCommand command,
		                    final Executor<UnifiedJedis, SR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		public JedisCommand(final JedisRedisClient client, final RedisCommand command,
		                    final RedisSubCommand subCommand) {
			super(client, command, subCommand);
		}

		public JedisCommand(final JedisRedisClient client, final RedisCommand command, final RedisSubCommand subCommand,
		                    final Executor<UnifiedJedis, SR> executor, final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute(final RedisConnection conn) throws RedisException {
			final SR result = executor.execute(((JedisRedisConnection<? extends UnifiedJedis>) conn).getClient());
			return result == null ? null : converter.convert(result);
		}

	}

	/**
	 * Jedis 事务命令
	 *
	 * @param <SR>
	 * 		原始类型
	 * @param <R>
	 * 		返回类型
	 *
	 * @since 4.0.0
	 */
	protected final static class JedisTransactionCommand<SR, R>
			extends AbstractPipelineCommand<Transaction, Response<SR>, SR, R> {

		public JedisTransactionCommand(final JedisRedisClient client, final RedisCommand command) {
			super(client, command);
		}

		public JedisTransactionCommand(final JedisRedisClient client, final RedisCommand command,
		                               final Executor<Transaction, Response<SR>> executor,
		                               final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		public JedisTransactionCommand(final JedisRedisClient client, final RedisCommand command,
		                               final RedisSubCommand subCommand) {
			super(client, command, subCommand);
		}

		public JedisTransactionCommand(final JedisRedisClient client, final RedisCommand command,
		                               final RedisSubCommand subCommand,
		                               final Executor<Transaction, Response<SR>> executor,
		                               final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute(final RedisConnection conn) throws RedisException {
			com.buession.redis.transaction.Transaction transaction = conn.getTransaction();

			if(transaction == null){
				throw new NotMultiRedisException(getCommand(), getSubCommand());
			}

			final TransactionProxy<Transaction, JedisResult<SR, R>> transactionProxy = (TransactionProxy<Transaction,
					JedisResult<SR, R>>) transaction;
			final Response<SR> response = executor.execute(transactionProxy.getObject());
			final JedisResult<SR, R> result =
					converter == null ? newJedisResult(response) : newJedisResult(response, converter);

			transactionProxy.getTxResults().add(result);
			return null;
		}

	}

	/**
	 * Jedis 管道命令
	 *
	 * @param <SR>
	 * 		原始类型
	 * @param <R>
	 * 		返回类型
	 *
	 * @since 4.0.0
	 */
	protected final static class JedisPipelineCommand<SR, R> extends AbstractPipelineCommand<Pipeline, Response<SR>, SR,
			R> {

		public JedisPipelineCommand(final JedisRedisClient client, final RedisCommand command) {
			super(client, command);
		}

		public JedisPipelineCommand(final JedisRedisClient client, final RedisCommand command,
		                            final Executor<Pipeline, Response<SR>> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		public JedisPipelineCommand(final JedisRedisClient client, final RedisCommand command,
		                            final RedisSubCommand subCommand) {
			super(client, command, subCommand);
		}

		public JedisPipelineCommand(final JedisRedisClient client, final RedisCommand command,
		                            final RedisSubCommand subCommand, final Executor<Pipeline, Response<SR>> executor,
		                            final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute(final RedisConnection conn) throws RedisException {
			com.buession.redis.pipeline.Pipeline pipeline = conn.getPipeline();

			if(pipeline == null){
				throw new NotMultiRedisException(getCommand(), getSubCommand());
			}

			final PipelineProxy<Pipeline, JedisResult<SR, R>> pipelineProxy =
					(PipelineProxy<redis.clients.jedis.Pipeline, JedisResult<SR, R>>) pipeline;
			final Response<SR> response = executor.execute(pipelineProxy.getObject());
			final JedisResult<SR, R> result =
					converter == null ? newJedisResult(response) : newJedisResult(response, converter);

			pipelineProxy.getTxResults().add(result);
			return null;
		}

	}

}
