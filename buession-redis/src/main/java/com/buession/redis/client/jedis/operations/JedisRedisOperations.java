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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.Executor;
import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.AbstractRedisOperationsCommand;
import com.buession.redis.core.internal.jedis.JedisResult;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedPipelineCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import com.buession.redis.transaction.jedis.JedisTransaction;
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

	abstract class AbstractJedisCommand<CLIENT extends JedisRedisClient, CONN extends JedisRedisConnection, R>
			extends AbstractRedisOperationsCommand<CLIENT, CONN, R> {

		protected AbstractJedisCommand(final CLIENT client, final ProtocolCommand command) {
			super(client, command);
		}

		@Override
		public R execute() throws RedisException {
			if(connection.isPipeline()){
				if(pipelineRunner == null){
					throw new NotSupportedPipelineCommandException(
							RedisConnectionUtils.getRedisMode(client.getConnection()), getCommand());
				}else{
					try{
						client.getTxResults().add(pipelineRunner.run());
					}catch(Exception e){
						throw new RedisException(e.getMessage(), e);
					}
				}
			}else if(connection.isTransaction()){
				if(transactionRunner == null){
					throw new NotSupportedTransactionCommandException(
							RedisConnectionUtils.getRedisMode(client.getConnection()), getCommand());
				}else{
					try{
						client.getTxResults().add(transactionRunner.run());
					}catch(Exception e){
						throw new RedisException(e.getMessage(), e);
					}
				}
			}else{
				if(runner == null){
					throw new NotSupportedCommandException(
							RedisConnectionUtils.getRedisMode(client.getConnection()),
							NotSupportedCommandException.Type.NORMAL, getCommand());
				}else{
					try{
						return runner.run();
					}catch(Exception e){
						throw new RedisException(e.getMessage(), e);
					}
				}
			}
			return null;
		}

		protected Runner createPipelineRunner(final Executor<Pipeline, Response<R>> executor) {
			return new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public JedisResult<R, R> run() throws Exception {
					final redis.clients.jedis.Pipeline jedisPipeline = ((JedisPipeline) pipeline()).primitive();
					return newJedisResult(executor.execute(jedisPipeline));
				}

			};
		}

		protected <SR> Runner createPipelineRunner(final Executor<Pipeline, Response<SR>> executor,
												   final Converter<SR, R> converter) {
			return new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public JedisResult<SR, R> run() throws Exception {
					final redis.clients.jedis.Pipeline jedisPipeline = ((JedisPipeline) pipeline()).primitive();
					return newJedisResult(executor.execute(jedisPipeline), converter);
				}

			};
		}

		protected Runner createTransactionRunner(final Executor<Transaction, Response<R>> executor) {
			return new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public JedisResult<R, R> run() throws Exception {
					final redis.clients.jedis.Transaction transaction = ((JedisTransaction) transaction()).primitive();
					return newJedisResult(executor.execute(transaction));
				}

			};
		}

		protected <SR> Runner createTransactionRunner(final Executor<Transaction, Response<SR>> executor,
													  final Converter<SR, R> converter) {
			return new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public JedisResult<SR, R> run() throws Exception {
					final redis.clients.jedis.Transaction transaction = ((JedisTransaction) transaction()).primitive();
					return newJedisResult(executor.execute(transaction), converter);
				}

			};
		}

		protected <SV, TV> JedisResult<SV, TV> newJedisResult(final Response<SV> response) {
			return JedisResult.Builder.<SV, TV>fromResponse(response).build();
		}

		protected <SV, TV> JedisResult<SV, TV> newJedisResult(final Response<SV> response,
															  final Converter<SV, TV> converter) {
			return JedisResult.Builder.<SV, TV>fromResponse(response).mappedWith(converter).build();
		}

	}

	class JedisCommand<R> extends AbstractJedisCommand<JedisStandaloneClient, JedisConnection, R> {

		public JedisCommand(final JedisStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisCommand<R> general(final Executor<Jedis, R> executor) {
			this.runner = new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public R run() throws Exception {
					return executor.execute(connection.getJedis());
				}

			};

			return this;
		}

		public <SR> JedisCommand<R> general(final Executor<Jedis, SR> executor, final Converter<SR, R> converter) {
			this.runner = new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public R run() throws Exception {
					return converter.convert(executor.execute(connection.getJedis()));
				}

			};

			return this;
		}

		public JedisCommand<R> pipeline(final Executor<Pipeline, Response<R>> executor) {
			this.pipelineRunner = createPipelineRunner(executor);
			return this;
		}

		public <SR> JedisCommand<R> pipeline(final Executor<Pipeline, Response<SR>> executor,
											 final Converter<SR, R> converter) {
			this.pipelineRunner = createPipelineRunner(executor, converter);
			return this;
		}

		public JedisCommand<R> transaction(final Executor<Transaction, Response<R>> executor) {
			this.transactionRunner = createTransactionRunner(executor);
			return this;
		}

		public <SR> JedisCommand<R> transaction(final Executor<Transaction, Response<SR>> executor,
												final Converter<SR, R> converter) {
			this.transactionRunner = createTransactionRunner(executor, converter);
			return this;
		}

	}

	class JedisSentinelCommand<R> extends AbstractJedisCommand<JedisSentinelClient, JedisSentinelConnection, R> {

		protected JedisSentinelCommand(final JedisSentinelClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisSentinelCommand<R> general(Executor<Jedis, R> executor) {
			this.runner = new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public R run() throws Exception {
					return executor.execute(connection.getJedis());
				}

			};

			return this;
		}

		public <SR> JedisSentinelCommand<R> general(final Executor<Jedis, SR> executor,
													final Converter<SR, R> converter) {
			this.runner = new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public R run() throws Exception {
					return converter.convert(executor.execute(connection.getJedis()));
				}

			};

			return this;
		}

		public JedisSentinelCommand<R> pipeline(final Executor<Pipeline, Response<R>> executor) {
			this.pipelineRunner = createPipelineRunner(executor);
			return this;
		}

		public <SR> JedisSentinelCommand<R> pipeline(final Executor<Pipeline, Response<SR>> executor,
													 final Converter<SR, R> converter) {
			this.pipelineRunner = createPipelineRunner(executor, converter);
			return this;
		}

		public JedisSentinelCommand<R> transaction(final Executor<Transaction, Response<R>> executor) {
			this.transactionRunner = createTransactionRunner(executor);
			return this;
		}

		public <SR> JedisSentinelCommand<R> transaction(final Executor<Transaction, Response<SR>> executor,
														final Converter<SR, R> converter) {
			this.transactionRunner = createTransactionRunner(executor, converter);
			return this;
		}

	}

	class JedisClusterCommand<R> extends AbstractJedisCommand<JedisClusterClient, JedisClusterConnection, R> {

		protected JedisClusterCommand(final JedisClusterClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisClusterCommand<R> general(Executor<JedisCluster, R> executor) {
			this.runner = new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public R run() throws Exception {
					return executor.execute(connection.getCluster());
				}

			};

			return this;
		}

		public <SR> JedisClusterCommand<R> general(final Executor<JedisCluster, SR> executor,
												   final Converter<SR, R> converter) {
			this.runner = new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public R run() throws Exception {
					return converter.convert(executor.execute(connection.getCluster()));
				}

			};

			return this;
		}

		public JedisClusterCommand<R> pipeline(final Executor<Pipeline, Response<R>> executor) {
			this.pipelineRunner = createPipelineRunner(executor);
			return this;
		}

		public <SR> JedisClusterCommand<R> pipeline(final Executor<Pipeline, Response<SR>> executor,
													final Converter<SR, R> converter) {
			this.pipelineRunner = createPipelineRunner(executor, converter);
			return this;
		}

		public JedisClusterCommand<R> transaction(final Executor<Transaction, Response<R>> executor) {
			this.transactionRunner = createTransactionRunner(executor);
			return this;
		}

		public <SR> JedisClusterCommand<R> transaction(final Executor<Transaction, Response<SR>> executor,
													   final Converter<SR, R> converter) {
			this.transactionRunner = createTransactionRunner(executor, converter);
			return this;
		}

	}

}
