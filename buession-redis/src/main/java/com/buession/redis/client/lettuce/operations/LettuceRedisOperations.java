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
package com.buession.redis.client.lettuce.operations;

import com.buession.core.Executor;
import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.lettuce.LettuceConnection;
import com.buession.redis.client.connection.lettuce.LettuceRedisConnection;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.Command;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.AbstractRedisOperationsCommand;
import com.buession.redis.core.internal.jedis.JedisResult;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import com.buession.redis.transaction.jedis.JedisTransaction;
import io.lettuce.core.api.sync.RedisCommands;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

/**
 * Lettuce Redis 命令操作接口
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public interface LettuceRedisOperations extends RedisOperations {

	abstract class AbstractLettuceCommand<CLIENT extends LettuceRedisClient, CONN extends LettuceRedisConnection, R>
			extends AbstractRedisOperationsCommand<CLIENT, CONN, R> {

		protected AbstractLettuceCommand(final CLIENT client, final ProtocolCommand command){
			super(client, command);
		}

		@Override
		public R execute() throws RedisException{
			if(connection.isPipeline()){
				if(pipelineRunner == null){
					throw throwNotSupportedCommandException(NotSupportedCommandException.Type.PIPELINE);
				}else{
					try{
						client.getTxResults().add(pipelineRunner.run());
					}catch(Exception e){
						logger.error(e.getMessage());
					}
				}
			}else if(connection.isTransaction()){
				if(transactionRunner == null){
					throw throwNotSupportedCommandException(NotSupportedCommandException.Type.TRANSACTION);
				}else{
					try{
						client.getTxResults().add(transactionRunner.run());
					}catch(Exception e){
						logger.error(e.getMessage());
					}
				}
			}else{
				if(runner == null){
					throw throwNotSupportedCommandException(NotSupportedCommandException.Type.NORMAL);
				}else{
					try{
						return runner.run();
					}catch(Exception e){
						logger.error(e.getMessage());
					}
				}
			}
			return null;
		}

		protected Runner createPipelineRunner(final Executor<Pipeline, Response<R>> executor){
			return new Runner() {

				@Override
				public JedisResult<R, R> run() throws Exception{
					final redis.clients.jedis.Pipeline jedisPipeline = ((JedisPipeline) pipeline()).primitive();
					return newJedisResult(executor.execute(jedisPipeline));
				}

			};
		}

		protected <SR> Runner createPipelineRunner(final Executor<Pipeline, Response<SR>> executor,
												   final Converter<SR, R> converter){
			return new Runner() {

				@Override
				public JedisResult<SR, R> run() throws Exception{
					final redis.clients.jedis.Pipeline jedisPipeline = ((JedisPipeline) pipeline()).primitive();
					return newJedisResult(executor.execute(jedisPipeline), converter);
				}

			};
		}

		protected Runner createTransactionRunner(final Executor<Transaction, Response<R>> executor){
			return new Runner() {

				@Override
				public JedisResult<R, R> run() throws Exception{
					final redis.clients.jedis.Transaction transaction = ((JedisTransaction) transaction()).primitive();
					return newJedisResult(executor.execute(transaction));
				}

			};
		}

		protected <SR> Runner createTransactionRunner(final Executor<Transaction, Response<SR>> executor,
													  final Converter<SR, R> converter){
			return new Runner() {

				@Override
				public JedisResult<SR, R> run() throws Exception{
					final redis.clients.jedis.Transaction transaction = ((JedisTransaction) transaction()).primitive();
					return newJedisResult(executor.execute(transaction), converter);
				}

			};
		}

		protected <SV, TV> JedisResult<SV, TV> newJedisResult(final Response<SV> response){
			return JedisResult.Builder.<SV, TV>forResponse(response).build();
		}

		protected <SV, TV> JedisResult<SV, TV> newJedisResult(final Response<SV> response,
															  final Converter<SV, TV> converter){
			return JedisResult.Builder.<SV, TV>forResponse(response).mappedWith(converter).build();
		}

	}

	class LettuceCommand<R> extends AbstractLettuceCommand<LettuceStandaloneClient, LettuceConnection, R> {

		public LettuceCommand(final LettuceStandaloneClient client, final ProtocolCommand command){
			super(client, command);
		}

		public LettuceCommand<R> general(final Executor<RedisCommands<byte[], byte[]>, R> executor){
			this.runner = new Command.Runner() {

				@Override
				public R run() throws Exception{
					return executor.execute(connection.getStandaloneStatefulRedisConnection().sync());
				}

			};

			return this;
		}

		public <SR> LettuceCommand<R> general(final Executor<RedisCommands<byte[], byte[]>, SR> executor,
											  final Converter<SR, R> converter){
			this.runner = new Runner() {

				@Override
				public R run() throws Exception{
					return converter.convert(
							executor.execute(connection.getStandaloneStatefulRedisConnection().sync()));
				}

			};

			return this;
		}

		public LettuceCommand<R> pipeline(final Executor<Pipeline, Response<R>> executor){
			this.pipelineRunner = createPipelineRunner(executor);
			return this;
		}

		public <SR> LettuceCommand<R> pipeline(final Executor<Pipeline, Response<SR>> executor,
											   final Converter<SR, R> converter){
			this.pipelineRunner = createPipelineRunner(executor, converter);
			return this;
		}

		public LettuceCommand<R> transaction(final Executor<Transaction, Response<R>> executor){
			this.transactionRunner = createTransactionRunner(executor);
			return this;
		}

		public <SR> LettuceCommand<R> transaction(final Executor<Transaction, Response<SR>> executor,
												  final Converter<SR, R> converter){
			this.transactionRunner = createTransactionRunner(executor, converter);
			return this;
		}

	}

}
