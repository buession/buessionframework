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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.Executor;
import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.NotSupportedCommandException;
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

	abstract class AbstractJedisCommand<C extends JedisRedisClient, T, R> extends AbstractRedisCommand<C, R> {

		protected Executor<T, R> executor;

		protected Executor<Pipeline, Response<R>> pipelineExecutor;

		protected Executor<Transaction, Response<R>> transactionExecutor;

		protected AbstractJedisCommand(final C client, final ProtocolCommand command){
			super(client, command);
		}

	}

	class JedisCommand<R> extends AbstractJedisCommand<JedisStandaloneClient, Jedis, R> {

		public JedisCommand(final JedisStandaloneClient client, final ProtocolCommand command){
			super(client, command);
		}

		public JedisCommand<R> general(final Executor<Jedis, R> executor){
			this.executor = executor;
			return this;
		}

		public <SR> JedisCommand<R> general(final Executor<Jedis, SR> executor, final Converter<SR, R> converter){
			this.executor = context->converter.convert(executor.execute(context));
			return this;
		}

		public JedisCommand<R> pipeline(final Executor<Pipeline, Response<R>> executor){
			this.pipelineExecutor = executor;
			return this;
		}

		public <SR> JedisCommand<R> pipeline(final Executor<Pipeline, Response<SR>> executor,
											 final Converter<SR, R> converter){
			//this.pipelineExecutor = context->converter.convert(executor.execute(context));
			return this;
		}

		public JedisCommand<R> transaction(final Executor<Transaction, Response<R>> executor){
			this.transactionExecutor = executor;
			return this;
		}

		public <SR> JedisCommand<R> transaction(final Executor<Transaction, Response<SR>> executor,
												final Converter<SR, R> converter){
			//this.transactionExecutor = context->converter.convert(executor.execute(context));
			return this;
		}

		@Override
		public R execute(){
			final RedisConnection connection = client.getConnection();
			if(connection.isPipeline()){
				if(pipelineExecutor == null){
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.PIPELINE, getCommand());
				}else{
					//client.getTxResults().add(pipelineExecutor.execute(null));
				}
			}else if(connection.isTransaction()){
				if(transactionExecutor == null){
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.TRANSACTION, getCommand());
				}else{
					transactionExecutor.execute(null);
				}
			}else{
				if(executor == null){
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.NORMAL, getCommand());
				}else{
					return executor.execute(null);
				}
			}
			return null;
		}

	}

	class JedisSentinelCommand<R> extends AbstractJedisCommand<JedisSentinelClient, Jedis, R> {

		protected JedisSentinelCommand(final JedisSentinelClient client, final ProtocolCommand command){
			super(client, command);
		}

		public JedisSentinelCommand<R> general(Executor<Jedis, R> executor){
			this.executor = executor;
			return this;
		}

		public <SR> JedisSentinelCommand<R> general(final Executor<Jedis, SR> executor,
													final Converter<SR, R> converter){
			this.executor = context->converter.convert(executor.execute(context));
			return this;
		}

		public JedisSentinelCommand<R> pipeline(final Executor<Pipeline, Response<R>> executor){
			this.pipelineExecutor = executor;
			return this;
		}

		public <SR> JedisSentinelCommand<R> pipeline(final Executor<Pipeline, Response<SR>> executor,
													 final Converter<SR, R> converter){
			//this.pipelineExecutor = context->converter.convert(executor.execute(context));
			return this;
		}

		public JedisSentinelCommand<R> transaction(final Executor<Transaction, Response<R>> executor){
			this.transactionExecutor = executor;
			return this;
		}

		public <SR> JedisSentinelCommand<R> transaction(final Executor<Transaction, Response<SR>> executor,
														final Converter<SR, R> converter){
			//this.transactionExecutor = context->converter.convert(executor.execute(context));
			return this;
		}

		@Override
		public R execute(){
			final RedisConnection connection = client.getConnection();
			if(connection.isPipeline()){
				if(pipelineExecutor == null){
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.PIPELINE, getCommand());
				}else{
					pipelineExecutor.execute(null);
				}
			}else if(connection.isTransaction()){
				if(transactionExecutor == null){
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.TRANSACTION, getCommand());
				}else{
					transactionExecutor.execute(null);
				}
			}else{
				if(executor == null){
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.NORMAL, getCommand());
				}else{
					return executor.execute(null);
				}
			}
			return null;
		}

	}

	class JedisClusterCommand<R> extends AbstractJedisCommand<JedisClusterClient, JedisCluster, R> {

		protected JedisClusterCommand(final JedisClusterClient client, final ProtocolCommand command){
			super(client, command);
		}

		public JedisClusterCommand<R> general(Executor<JedisCluster, R> executor){
			this.executor = executor;
			return this;
		}

		public <SR> JedisClusterCommand<R> general(final Executor<JedisCluster, SR> executor,
												   final Converter<SR, R> converter){
			this.executor = context->converter.convert(executor.execute(context));
			return this;
		}

		public JedisClusterCommand<R> pipeline(final Executor<Pipeline, Response<R>> executor){
			this.pipelineExecutor = executor;
			return this;
		}

		public <SR> JedisClusterCommand<R> pipeline(final Executor<Pipeline, Response<SR>> executor,
													final Converter<SR, R> converter){
			//this.pipelineExecutor = context->converter.convert(executor.execute(context));
			return this;
		}

		public JedisClusterCommand<R> transaction(final Executor<Transaction, Response<R>> executor){
			this.transactionExecutor = executor;
			return this;
		}

		public <SR> JedisClusterCommand<R> transaction(final Executor<Transaction, Response<SR>> executor,
													   final Converter<SR, R> converter){
			//this.transactionExecutor = context->converter.convert(executor.execute(context));
			return this;
		}

		@Override
		public R execute(){
			final RedisConnection connection = client.getConnection();
			if(connection.isPipeline()){
				if(pipelineExecutor == null){
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.PIPELINE, getCommand());
				}else{
					pipelineExecutor.execute(null);
				}
			}else if(connection.isTransaction()){
				if(transactionExecutor == null){
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.TRANSACTION, getCommand());
				}else{
					transactionExecutor.execute(null);
				}
			}else{
				if(executor == null){
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.NORMAL, getCommand());
				}else{
					return executor.execute(null);
				}
			}
			return null;
		}

	}

}
