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
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.lettuce.LettuceConnection;
import com.buession.redis.client.connection.lettuce.LettuceRedisConnection;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.AbstractRedisOperationsCommand;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedPipelineCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisException;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * Lettuce Redis 命令操作接口
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface LettuceRedisOperations extends RedisOperations {

	abstract class AbstractLettuceCommand<CLIENT extends LettuceRedisClient, CONN extends LettuceRedisConnection, SR, R>
			extends AbstractRedisOperationsCommand<CLIENT, CONN, R> {

		protected final Executor<RedisCommands<byte[], byte[]>, SR> executor;

		protected final Converter<SR, R> converter;

		protected AbstractLettuceCommand(final CLIENT client, final ProtocolCommand command,
										 final Executor<RedisCommands<byte[], byte[]>, SR> executor) {
			this(client, command, executor, null);
		}

		protected AbstractLettuceCommand(final CLIENT client, final ProtocolCommand command,
										 final Executor<RedisCommands<byte[], byte[]>, SR> executor,
										 final Converter<SR, R> converter) {
			super(client, command);
			this.executor = executor;
			this.converter = converter;
		}

		@Override
		public R execute() throws RedisException {
			if(connection.isPipeline()){
				if(executor == null){
					throw new NotSupportedPipelineCommandException(RedisConnectionUtils.getRedisMode(connection),
							getCommand());
				}
			}else if(connection.isTransaction()){
				if(executor == null){
					throw new NotSupportedTransactionCommandException(RedisConnectionUtils.getRedisMode(connection),
							getCommand());
				}
			}else{
				if(executor == null){
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.NORMAL, getCommand());
				}else{
					final StatefulRedisConnection<byte[], byte[]> statefulRedisConnection =
							((LettuceConnection) connection).getStatefulConnection();
					try{
						final SR result = executor.execute(statefulRedisConnection.sync());
						return result == null ? null : converter.convert(result);
					}catch(Exception e){
						throw new RedisException(e.getMessage(), e);
					}
				}
			}
			/*
			if(connection.isPipeline()){
				if(pipelineRunner == null){
				}else{
					try{
						client.getTxResults().add(pipelineRunner.run());
					}catch(Exception e){
						throw new RedisException(e.getMessage(), e);
					}
				}
			}else if(connection.isTransaction()){
				if(transactionRunner == null){
				}else{
					try{
						client.getTxResults().add(transactionRunner.run());
					}catch(Exception e){
						throw new RedisException(e.getMessage(), e);
					}
				}
			}else{
			}

			 */
			return null;
		}

		/*
		protected Runner createPipelineRunner(final Executor<Pipeline, Response<R>> executor) {
			return new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public JedisResult<R, R> run() throws Exception {
					final Pipeline jedisPipeline = ((JedisPipeline) pipeline()).primitive();
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
					final Pipeline jedisPipeline = ((JedisPipeline) pipeline()).primitive();
					return newJedisResult(executor.execute(jedisPipeline), converter);
				}

			};
		}

		protected Runner createTransactionRunner(final Executor<Transaction, Response<R>> executor) {
			return new Runner() {

				@SuppressWarnings({"unchecked"})
				@Override
				public JedisResult<R, R> run() throws Exception {
					final Transaction transaction = ((JedisTransaction) transaction()).primitive();
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
					final Transaction transaction = ((JedisTransaction) transaction()).primitive();
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

		 */

	}

	class LettuceCommand<SR, R> extends AbstractLettuceCommand<LettuceStandaloneClient, LettuceConnection, SR, R> {

		@SuppressWarnings({"unchecked", "rawtype"})
		public LettuceCommand(final LettuceStandaloneClient client, final ProtocolCommand command) {
			super(client, command, null, (value)->(R) value);
		}

		public LettuceCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
							  final Converter<SR, R> converter) {
			super(client, command, null, converter);
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

	}

	/*
	class JedisSentinelCommand<R> extends AbstractJedisCommand<JedisSentinelClient, JedisSentinelConnection, R> {

		protected JedisSentinelCommand(final JedisSentinelClient client, final ProtocolCommand command,
									   final Executor<RedisCommands<byte[], byte[]>, R> executor) {
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

	 */

}
