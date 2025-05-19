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
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.Command;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.jedis.JedisResult;
import com.buession.redis.exception.RedisException;
import com.buession.redis.exception.RedisPipelineException;
import com.buession.redis.exception.RedisTransactionException;
import com.buession.redis.pipeline.PipelineProxy;
import com.buession.redis.transaction.TransactionProxy;
import redis.clients.jedis.ClusterPipeline;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

/**
 * Jedis Redis 命令操作接口
 *
 * @author Yong.Teng
 */
public interface JedisRedisOperations extends RedisOperations {

	class JedisCommand<SR, R> extends
			AbstractStandaloneCommand<JedisStandaloneClient, JedisConnection, Jedis, SR, SR, R> {

		public JedisCommand(final JedisStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisCommand(final JedisStandaloneClient client, final ProtocolCommand command,
							final Executor<Jedis, SR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getJedis());
			return converter.convert(result);
		}

	}

	class JedisPipelineCommand<SR, R> extends
			AbstractStandaloneCommand<JedisStandaloneClient, JedisConnection, Pipeline, Response<SR>, SR, R> {

		public JedisPipelineCommand(final JedisStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisPipelineCommand(final JedisStandaloneClient client, final ProtocolCommand command,
									final Executor<Pipeline, Response<SR>> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<Pipeline, JedisResult<SR, R>> pipelineFactory =
						(PipelineProxy<Pipeline, JedisResult<SR, R>>) pipeline;
				final Runner<JedisResult<SR, R>> runner = new PipelineRunner<>(executor, pipelineFactory, converter);

				pipelineFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisPipelineException("ERR EXEC without pipeline. Did you forget to call openPipeline?");
			}
		}

	}

	class JedisTransactionCommand<SR, R> extends
			AbstractStandaloneCommand<JedisStandaloneClient, JedisConnection, Transaction, Response<SR>, SR, R> {

		public JedisTransactionCommand(final JedisStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisTransactionCommand(final JedisStandaloneClient client, final ProtocolCommand command,
									   final Executor<Transaction, Response<SR>> executor,
									   final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.transaction.Transaction transaction = transaction();

			if(transaction instanceof TransactionProxy){
				final TransactionProxy<Transaction, JedisResult<SR, R>> transactionFactory =
						(TransactionProxy<Transaction, JedisResult<SR, R>>) transaction;
				final Runner<JedisResult<SR, R>> runner = new TransactionRunner<>(executor, transactionFactory,
						converter);

				transactionFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisTransactionException("ERR EXEC without multi. Did you forget to call multi?");
			}
		}

	}

	class JedisSentinelCommand<SR, R> extends
			AbstractSentinelCommand<JedisSentinelClient, JedisSentinelConnection, Jedis, SR, SR, R> {

		public JedisSentinelCommand(final JedisSentinelClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisSentinelCommand(final JedisSentinelClient client, final ProtocolCommand command,
									final Executor<Jedis, SR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getJedis());
			return converter.convert(result);
		}

	}

	class JedisSentinelPipelineCommand<SR, R> extends
			AbstractSentinelCommand<JedisSentinelClient, JedisSentinelConnection, Pipeline, Response<SR>, SR, R> {

		public JedisSentinelPipelineCommand(final JedisSentinelClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisSentinelPipelineCommand(final JedisSentinelClient client, final ProtocolCommand command,
											final Executor<Pipeline, Response<SR>> executor,
											final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<Pipeline, JedisResult<SR, R>> pipelineFactory = (PipelineProxy<Pipeline, JedisResult<SR, R>>) pipeline;
				final Runner<JedisResult<SR, R>> runner = new PipelineRunner<>(executor, pipelineFactory, converter);

				pipelineFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisPipelineException("ERR EXEC without pipeline. Did you forget to call openPipeline?");
			}
		}

	}

	class JedisSentinelTransactionCommand<SR, R> extends
			AbstractSentinelCommand<JedisSentinelClient, JedisSentinelConnection, Transaction, Response<SR>, SR, R> {

		public JedisSentinelTransactionCommand(final JedisSentinelClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisSentinelTransactionCommand(final JedisSentinelClient client, final ProtocolCommand command,
											   final Executor<Transaction, Response<SR>> executor,
											   final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.transaction.Transaction transaction = transaction();

			if(transaction instanceof TransactionProxy){
				final TransactionProxy<Transaction, JedisResult<SR, R>> transactionFactory =
						(TransactionProxy<Transaction, JedisResult<SR, R>>) transaction;
				final Runner<JedisResult<SR, R>> runner = new TransactionRunner<>(executor, transactionFactory,
						converter);

				transactionFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisTransactionException("ERR EXEC without multi. Did you forget to call multi?");
			}
		}

	}

	class JedisClusterCommand<SR, R> extends
			AbstractClusterCommand<JedisClusterClient, JedisClusterConnection, JedisCluster, SR, SR, R> {

		public JedisClusterCommand(final JedisClusterClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisClusterCommand(final JedisClusterClient client, final ProtocolCommand command,
								   final Executor<JedisCluster, SR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getCluster());
			return converter.convert(result);
		}

	}

	class JedisClusterPipelineCommand<SR, R> extends
			AbstractClusterCommand<JedisClusterClient, JedisClusterConnection, ClusterPipeline, Response<SR>, SR, R> {

		public JedisClusterPipelineCommand(final JedisClusterClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisClusterPipelineCommand(final JedisClusterClient client, final ProtocolCommand command,
										   final Executor<ClusterPipeline, Response<SR>> executor,
										   final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<ClusterPipeline, JedisResult<SR, R>> pipelineFactory = (PipelineProxy<ClusterPipeline,
						JedisResult<SR, R>>) pipeline;
				final Runner<JedisResult<SR, R>> runner = new PipelineRunner<>(executor, pipelineFactory, converter);

				pipelineFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisPipelineException("ERR EXEC without pipeline. Did you forget to call openPipeline?");
			}
		}

	}

	class JedisClusterTransactionCommand<SR, R> extends
			AbstractClusterCommand<JedisClusterClient, JedisClusterConnection, Transaction, Response<SR>, SR, R> {

		public JedisClusterTransactionCommand(final JedisClusterClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisClusterTransactionCommand(final JedisClusterClient client, final ProtocolCommand command,
											  final Executor<Transaction, Response<SR>> executor,
											  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.transaction.Transaction transaction = transaction();

			if(transaction instanceof TransactionProxy){
				final TransactionProxy<Transaction, JedisResult<SR, R>> transactionFactory =
						(TransactionProxy<Transaction, JedisResult<SR, R>>) transaction;
				final Runner<JedisResult<SR, R>> runner = new TransactionRunner<>(executor, transactionFactory,
						converter);

				transactionFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisTransactionException("ERR EXEC without multi. Did you forget to call multi?");
			}
		}

	}

	abstract class PtRunner<T, SR, R> implements Command.Runner<JedisResult<SR, R>> {

		protected final Command.Executor<T, Response<SR>> executor;

		protected final T context;

		protected final Converter<SR, R> converter;

		public PtRunner(final Command.Executor<T, Response<SR>> executor, final T context,
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

		public PipelineRunner(final Command.Executor<T, Response<SR>> executor,
							  final PipelineProxy<T, JedisResult<SR, R>> pipelineFactory,
							  final Converter<SR, R> converter) {
			super(executor, pipelineFactory.getObject(), converter);
		}

	}

	final class TransactionRunner<T, SR, R> extends PtRunner<T, SR, R> {

		public TransactionRunner(final Command.Executor<T, Response<SR>> executor,
								 final TransactionProxy<T, JedisResult<SR, R>> transactionFactory,
								 final Converter<SR, R> converter) {
			super(executor, transactionFactory.getObject(), converter);
		}

	}

}
