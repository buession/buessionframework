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
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.Command;
import com.buession.redis.core.command.ProtocolCommand;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * Jedis Redis 命令操作接口
 *
 * @param <C>
 * 		连接对象
 *
 * @author Yong.Teng
 */
public interface JedisRedisOperations<C extends JedisRedisConnection> extends RedisOperations<C> {

	abstract class AbstractJedisCommand<C extends JedisRedisConnection, R> implements Command<C, R> {

		private final ProtocolCommand command;

		public AbstractJedisCommand(final ProtocolCommand command){
			this.command = command;
		}

		@Override
		public ProtocolCommand getCommand(){
			return command;
		}

		/*
		public AbstractJedisCommand<C, SR, R> pipeline(Executor<Jedis, R> executor){
			return this;
		}

		public AbstractJedisCommand<C, SR, R> transaction(Executor<Jedis, R> executor){
			return this;
		}

		 */

	}

	class JedisCommand<R> extends AbstractJedisCommand<JedisConnection, R> {

		private Executor<Jedis, R> executor;

		private Executor<Pipeline, Response<R>> pipelineExecutor;

		public JedisCommand(final ProtocolCommand command){
			super(command);
		}

		public static <R> JedisCommand<R> create(final ProtocolCommand command){
			return new JedisCommand<>(command);
		}

		public JedisCommand<R> pipeline(final Executor<Pipeline, Response<R>> executor){
			this.pipelineExecutor = executor;
			return this;
		}

		public <SR> JedisCommand<R> pipeline(final Executor<Pipeline, Response<SR>> executor,
											 final Converter<SR, R> converter){
			this.pipelineExecutor = context->converter.convert(executor.execute(context));
			return this;
		}

		public JedisCommand<R> general(final Executor<Jedis, R> executor){
			this.executor = executor;
			return this;
		}

		public <SR> JedisCommand<R> general(final Executor<Jedis, SR> executor, final Converter<SR, R> converter){
			this.executor = context->converter.convert(executor.execute(context));
			return this;
		}

		@Override
		public R execute(final JedisConnection connection){
			if(connection.isPipeline()){
				pipelineExecutor.execute(null);
			}else if(connection.isTransaction()){
			}else{
				return executor.execute(null);
			}
			return null;
		}

	}

	class JedisSentinelCommand<R> extends AbstractJedisCommand<JedisSentinelConnection, R> {

		protected Executor<Jedis, R> executor;

		private Executor<Pipeline, Response<R>> pipelineExecutor;

		public JedisSentinelCommand(final ProtocolCommand command){
			super(command);
		}

		public static <R> JedisSentinelCommand<R> create(final ProtocolCommand command){
			return new JedisSentinelCommand<>(command);
		}

		public JedisSentinelCommand<R> pipeline(final Executor<Pipeline, Response<R>> executor){
			this.pipelineExecutor = executor;
			return this;
		}

		public <SR> JedisSentinelCommand<R> pipeline(final Executor<Pipeline, Response<SR>> executor,
													 final Converter<SR, R> converter){
			this.pipelineExecutor = context->converter.convert(executor.execute(context));
			return this;
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

		@Override
		public R execute(final JedisSentinelConnection connection){
			if(connection.isPipeline()){
				pipelineExecutor.execute(null);
			}else if(connection.isTransaction()){
			}else{
				return executor.execute(null);
			}
			return null;
		}

	}

	class JedisClusterCommand<R> extends AbstractJedisCommand<JedisClusterConnection, R> {

		protected Executor<JedisCluster, R> executor;

		private Executor<Pipeline, Response<R>> pipelineExecutor;

		public JedisClusterCommand(final ProtocolCommand command){
			super(command);
		}

		public static <R> JedisClusterCommand<R> create(final ProtocolCommand command){
			return new JedisClusterCommand<>(command);
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
			this.pipelineExecutor = context->converter.convert(executor.execute(context));
			return this;
		}

		@Override
		public R execute(final JedisClusterConnection connection){
			if(connection.isPipeline()){
				pipelineExecutor.execute(null);
			}else if(connection.isTransaction()){
			}else{
				return executor.execute(null);
			}
			return null;
		}

	}

}
