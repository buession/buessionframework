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
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.SubCommand;
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

	/**
	 * Jedis 单机模式常规命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class JedisCommand<SR, R> extends
			AbstractStandaloneCommand<JedisStandaloneClient, JedisConnection, Jedis, SR, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public JedisCommand(final JedisStandaloneClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisCommand(final JedisStandaloneClient client, final Command command,
							final Executor<Jedis, SR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public JedisCommand(final JedisStandaloneClient client, final Command command, final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisCommand(final JedisStandaloneClient client, final Command command, final SubCommand subCommand,
							final Executor<Jedis, SR> executor, final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getJedis());
			return result == null ? null : converter.convert(result);
		}

	}

	/**
	 * Jedis 单机模式管道模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class JedisPipelineCommand<SR, R> extends
			AbstractStandaloneCommand<JedisStandaloneClient, JedisConnection, Pipeline, Response<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public JedisPipelineCommand(final JedisStandaloneClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisPipelineCommand(final JedisStandaloneClient client, final Command command,
									final Executor<Pipeline, Response<SR>> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public JedisPipelineCommand(final JedisStandaloneClient client, final Command command,
									final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisPipelineCommand(final JedisStandaloneClient client, final Command command,
									final SubCommand subCommand, final Executor<Pipeline, Response<SR>> executor,
									final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<Pipeline, JedisResult<Object, Object>> pipelineFactory =
						(PipelineProxy<Pipeline, JedisResult<Object, Object>>) pipeline;
				final Runner runner = new PipelineRunner<>(executor, pipelineFactory, converter);

				pipelineFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisPipelineException("ERR EXEC without pipeline. Did you forget to call openPipeline?");
			}
		}

	}

	/**
	 * Jedis 单机模式事务模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class JedisTransactionCommand<SR, R> extends
			AbstractStandaloneCommand<JedisStandaloneClient, JedisConnection, Transaction, Response<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public JedisTransactionCommand(final JedisStandaloneClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisTransactionCommand(final JedisStandaloneClient client, final Command command,
									   final Executor<Transaction, Response<SR>> executor,
									   final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public JedisTransactionCommand(final JedisStandaloneClient client, final Command command,
									   final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link JedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisTransactionCommand(final JedisStandaloneClient client, final Command command,
									   final SubCommand subCommand, final Executor<Transaction, Response<SR>> executor,
									   final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.transaction.Transaction transaction = transaction();

			if(transaction instanceof TransactionProxy){
				final TransactionProxy<Transaction, JedisResult<Object, Object>> transactionFactory =
						(TransactionProxy<Transaction, JedisResult<Object, Object>>) transaction;
				final Runner runner = new TransactionRunner<>(executor, transactionFactory, converter);

				transactionFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisTransactionException("ERR EXEC without multi. Did you forget to call multi?");
			}
		}

	}

	/**
	 * Jedis 哨兵模式常规命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class JedisSentinelCommand<SR, R> extends
			AbstractSentinelCommand<JedisSentinelClient, JedisSentinelConnection, Jedis, SR, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public JedisSentinelCommand(final JedisSentinelClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisSentinelCommand(final JedisSentinelClient client, final Command command,
									final Executor<Jedis, SR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public JedisSentinelCommand(final JedisSentinelClient client, final Command command,
									final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisSentinelCommand(final JedisSentinelClient client, final Command command,
									final SubCommand subCommand,
									final Executor<Jedis, SR> executor, final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getJedis());
			return result == null ? null : converter.convert(result);
		}

	}

	/**
	 * Jedis 哨兵模式管道模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class JedisSentinelPipelineCommand<SR, R> extends
			AbstractSentinelCommand<JedisSentinelClient, JedisSentinelConnection, Pipeline, Response<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public JedisSentinelPipelineCommand(final JedisSentinelClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisSentinelPipelineCommand(final JedisSentinelClient client, final Command command,
											final Executor<Pipeline, Response<SR>> executor,
											final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public JedisSentinelPipelineCommand(final JedisSentinelClient client, final Command command,
											final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisSentinelPipelineCommand(final JedisSentinelClient client, final Command command,
											final SubCommand subCommand,
											final Executor<Pipeline, Response<SR>> executor,
											final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<Pipeline, JedisResult<Object, Object>> pipelineFactory = (PipelineProxy<Pipeline, JedisResult<Object, Object>>) pipeline;
				final Runner runner = new PipelineRunner<>(executor, pipelineFactory, converter);

				pipelineFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisPipelineException("ERR EXEC without pipeline. Did you forget to call openPipeline?");
			}
		}

	}

	/**
	 * Jedis 哨兵模式事务模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class JedisSentinelTransactionCommand<SR, R> extends
			AbstractSentinelCommand<JedisSentinelClient, JedisSentinelConnection, Transaction, Response<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public JedisSentinelTransactionCommand(final JedisSentinelClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisSentinelTransactionCommand(final JedisSentinelClient client, final Command command,
											   final Executor<Transaction, Response<SR>> executor,
											   final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public JedisSentinelTransactionCommand(final JedisSentinelClient client, final Command command,
											   final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link JedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisSentinelTransactionCommand(final JedisSentinelClient client, final Command command,
											   final SubCommand subCommand,
											   final Executor<Transaction, Response<SR>> executor,
											   final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.transaction.Transaction transaction = transaction();

			if(transaction instanceof TransactionProxy){
				final TransactionProxy<Transaction, JedisResult<Object, Object>> transactionFactory =
						(TransactionProxy<Transaction, JedisResult<Object, Object>>) transaction;
				final Runner runner = new TransactionRunner<>(executor, transactionFactory, converter);

				transactionFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisTransactionException("ERR EXEC without multi. Did you forget to call multi?");
			}
		}

	}

	/**
	 * Jedis 集群模式常规命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class JedisClusterCommand<SR, R> extends
			AbstractClusterCommand<JedisClusterClient, JedisClusterConnection, JedisCluster, SR, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public JedisClusterCommand(final JedisClusterClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisClusterCommand(final JedisClusterClient client, final Command command,
								   final Executor<JedisCluster, SR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public JedisClusterCommand(final JedisClusterClient client, final Command command,
								   final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisClusterCommand(final JedisClusterClient client, final Command command,
								   final SubCommand subCommand,
								   final Executor<JedisCluster, SR> executor, final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final SR result = executor.execute(connection.getCluster());
			return result == null ? null : converter.convert(result);
		}

	}

	/**
	 * Jedis 集群模式管道模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class JedisClusterPipelineCommand<SR, R> extends
			AbstractClusterCommand<JedisClusterClient, JedisClusterConnection, ClusterPipeline, Response<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public JedisClusterPipelineCommand(final JedisClusterClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisClusterPipelineCommand(final JedisClusterClient client, final Command command,
										   final Executor<ClusterPipeline, Response<SR>> executor,
										   final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public JedisClusterPipelineCommand(final JedisClusterClient client, final Command command,
										   final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisClusterPipelineCommand(final JedisClusterClient client, final Command command,
										   final SubCommand subCommand,
										   final Executor<ClusterPipeline, Response<SR>> executor,
										   final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@SuppressWarnings({"unchecked"})
		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.pipeline.Pipeline pipeline = pipeline();

			if(pipeline instanceof PipelineProxy){
				final PipelineProxy<ClusterPipeline, JedisResult<Object, Object>> pipelineFactory = (PipelineProxy<ClusterPipeline,
						JedisResult<Object, Object>>) pipeline;
				final Runner runner = new PipelineRunner<>(executor, pipelineFactory, converter);

				pipelineFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisPipelineException("ERR EXEC without pipeline. Did you forget to call openPipeline?");
			}
		}

	}

	/**
	 * Jedis 集群模式集群模式命令
	 *
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	class JedisClusterTransactionCommand<SR, R> extends
			AbstractClusterCommand<JedisClusterClient, JedisClusterConnection, Transaction, Response<SR>, SR, R> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public JedisClusterTransactionCommand(final JedisClusterClient client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisClusterTransactionCommand(final JedisClusterClient client, final Command command,
											  final Executor<Transaction, Response<SR>> executor,
											  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public JedisClusterTransactionCommand(final JedisClusterClient client, final Command command,
											  final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link JedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public JedisClusterTransactionCommand(final JedisClusterClient client, final Command command,
											  final SubCommand subCommand,
											  final Executor<Transaction, Response<SR>> executor,
											  final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

		@Override
		protected R doExecute() throws RedisException {
			final com.buession.redis.transaction.Transaction transaction = transaction();

			if(transaction instanceof TransactionProxy){
				final TransactionProxy<Transaction, JedisResult<Object, Object>> transactionFactory =
						(TransactionProxy<Transaction, JedisResult<Object, Object>>) transaction;
				final Runner runner = new TransactionRunner<>(executor, transactionFactory, converter);

				transactionFactory.getTxResults().add(runner.run());

				return null;
			}else{
				throw new RedisTransactionException("ERR EXEC without multi. Did you forget to call multi?");
			}
		}

	}

	abstract class PtRunner<T, SR, R> implements com.buession.redis.core.Command.Runner {

		protected final com.buession.redis.core.Command.Executor<T, Response<SR>> executor;

		protected final Converter<SR, R> converter;

		public PtRunner(final com.buession.redis.core.Command.Executor<T, Response<SR>> executor,
						final Converter<SR, R> converter) {
			this.executor = executor;
			this.converter = converter;
		}

		protected JedisResult<SR, R> newJedisResult(final Response<SR> response) {
			return JedisResult.Builder.<SR, R>fromResponse(response).build();
		}

		protected JedisResult<SR, R> newJedisResult(final Response<SR> response, final Converter<SR, R> converter) {
			return JedisResult.Builder.<SR, R>fromResponse(response).mappedWith(converter).build();
		}

	}

	final class PipelineRunner<T, SR, R> extends PtRunner<T, SR, R> {

		private final PipelineProxy<T, JedisResult<Object, Object>> pipelineFactory;

		public PipelineRunner(final com.buession.redis.core.Command.Executor<T, Response<SR>> executor,
							  final PipelineProxy<T, JedisResult<Object, Object>> pipelineFactory,
							  final Converter<SR, R> converter) {
			super(executor, converter);
			this.pipelineFactory = pipelineFactory;
		}

		@SuppressWarnings({"unchecked"})
		@Override
		public JedisResult<SR, R> run() throws RedisException {
			final Response<SR> response = executor.execute(pipelineFactory.getObject());
			return converter == null ? newJedisResult(response) : newJedisResult(response, converter);
		}

	}

	final class TransactionRunner<T, SR, R> extends PtRunner<T, SR, R> {

		private final TransactionProxy<T, JedisResult<Object, Object>> transactionFactory;

		public TransactionRunner(final com.buession.redis.core.Command.Executor<T, Response<SR>> executor,
								 final TransactionProxy<T, JedisResult<Object, Object>> transactionFactory,
								 final Converter<SR, R> converter) {
			super(executor, converter);
			this.transactionFactory = transactionFactory;
		}

		@SuppressWarnings({"unchecked"})
		@Override
		public JedisResult<SR, R> run() throws RedisException {
			final Response<SR> response = executor.execute(transactionFactory.getObject());
			return converter == null ? newJedisResult(response) : newJedisResult(response, converter);
		}

	}

}
