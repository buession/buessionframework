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

import com.buession.core.Executor;
import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.response.RedisResponseConverter;
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

	class JedisCommand<SR, R> extends AbstractStandaloneCommand<JedisStandaloneClient, JedisConnection, Jedis, SR, R> {

		public JedisCommand(final JedisStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisCommand(final JedisStandaloneClient client, final ProtocolCommand command,
							final Converter<SR, R> converter) {
			super(client, command, converter);
		}

		public JedisCommand(final JedisStandaloneClient client, final ProtocolCommand command,
							final Executor<Jedis, SR> executor) {
			super(client, command, executor);
		}

		public JedisCommand(final JedisStandaloneClient client, final ProtocolCommand command,
							final Executor<Jedis, SR> executor,
							final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected SR doExecute() throws Exception {
			return executor.execute(connection.getJedis());
		}

	}

	class JedisPipelineCommand<SR, R>
			extends AbstractStandaloneCommand<JedisStandaloneClient, JedisConnection, Pipeline, Response<SR>, R> {

		public JedisPipelineCommand(final JedisStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisPipelineCommand(final JedisStandaloneClient client, final ProtocolCommand command,
									final Converter<SR, R> converter) {
			super(client, command, new RedisResponseConverter<>(converter));
		}

		public JedisPipelineCommand(final JedisStandaloneClient client, final ProtocolCommand command,
									final Executor<Pipeline, Response<SR>> executor) {
			super(client, command, executor);
		}

		public JedisPipelineCommand(final JedisStandaloneClient client, final ProtocolCommand command,
									final Executor<Pipeline, Response<SR>> executor,
									final Converter<SR, R> converter) {
			super(client, command, executor, new RedisResponseConverter<>(converter));
		}

		@Override
		protected Response<SR> doExecute() throws Exception {
			return executor.execute(connection.getJedis().pipelined());
		}

	}

	class JedisTransactionCommand<SR, R>
			extends AbstractStandaloneCommand<JedisStandaloneClient, JedisConnection, Transaction, Response<SR>, R> {

		public JedisTransactionCommand(final JedisStandaloneClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisTransactionCommand(final JedisStandaloneClient client, final ProtocolCommand command,
									   final Converter<SR, R> converter) {
			super(client, command, new RedisResponseConverter<>(converter));
		}

		public JedisTransactionCommand(final JedisStandaloneClient client, final ProtocolCommand command,
									   final Executor<Transaction, Response<SR>> executor) {
			super(client, command, executor);
		}

		public JedisTransactionCommand(final JedisStandaloneClient client, final ProtocolCommand command,
									   final Executor<Transaction, Response<SR>> executor,
									   final Converter<SR, R> converter) {
			super(client, command, executor, new RedisResponseConverter<>(converter));
		}

		@Override
		protected Response<SR> doExecute() throws Exception {
			return executor.execute(connection.getJedis().multi());
		}

	}

	class JedisSentinelCommand<SR, R> extends AbstractSentinelCommand<JedisSentinelClient, JedisSentinelConnection,
			Jedis, SR, R> {

		@SuppressWarnings({"unchecked", "rawtype"})
		public JedisSentinelCommand(final JedisSentinelClient client, final ProtocolCommand command) {
			super(client, command, null, (value)->(R) value);
		}

		public JedisSentinelCommand(final JedisSentinelClient client, final ProtocolCommand command,
									final Converter<SR, R> converter) {
			super(client, command, null, converter);
		}

		@SuppressWarnings({"unchecked", "rawtype"})
		public JedisSentinelCommand(final JedisSentinelClient client, final ProtocolCommand command,
									final Executor<Jedis, SR> executor) {
			super(client, command, executor, (value)->(R) value);
		}

		public JedisSentinelCommand(final JedisSentinelClient client, final ProtocolCommand command,
									final Executor<Jedis, SR> executor,
									final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected SR doExecute() throws Exception {
			return executor.execute(connection.getJedis());
		}

	}

	class JedisSentinelPipelineCommand<SR, R>
			extends AbstractSentinelCommand<JedisSentinelClient, JedisSentinelConnection, Pipeline, Response<SR>, R> {

		public JedisSentinelPipelineCommand(final JedisSentinelClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisSentinelPipelineCommand(final JedisSentinelClient client, final ProtocolCommand command,
											final Converter<SR, R> converter) {
			super(client, command, new RedisResponseConverter<>(converter));
		}

		public JedisSentinelPipelineCommand(final JedisSentinelClient client, final ProtocolCommand command,
											final Executor<Pipeline, Response<SR>> executor) {
			super(client, command, executor);
		}

		public JedisSentinelPipelineCommand(final JedisSentinelClient client, final ProtocolCommand command,
											final Executor<Pipeline, Response<SR>> executor,
											final Converter<SR, R> converter) {
			super(client, command, executor, new RedisResponseConverter<>(converter));
		}

		@Override
		protected Response<SR> doExecute() throws Exception {
			return executor.execute(connection.getJedis().pipelined());
		}

	}

	class JedisSentinelTransactionCommand<SR, R> extends
			AbstractSentinelCommand<JedisSentinelClient, JedisSentinelConnection, Transaction, Response<SR>, R> {

		public JedisSentinelTransactionCommand(final JedisSentinelClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisSentinelTransactionCommand(final JedisSentinelClient client, final ProtocolCommand command,
											   final Converter<SR, R> converter) {
			super(client, command, new RedisResponseConverter<>(converter));
		}

		public JedisSentinelTransactionCommand(final JedisSentinelClient client, final ProtocolCommand command,
											   final Executor<Transaction, Response<SR>> executor) {
			super(client, command, executor);
		}

		public JedisSentinelTransactionCommand(final JedisSentinelClient client, final ProtocolCommand command,
											   final Executor<Transaction, Response<SR>> executor,
											   final Converter<SR, R> converter) {
			super(client, command, executor, new RedisResponseConverter<>(converter));
		}

		@Override
		protected Response<SR> doExecute() throws Exception {
			return executor.execute(connection.getJedis().multi());
		}

	}

	class JedisClusterCommand<SR, R> extends AbstractClusterCommand<JedisClusterClient, JedisClusterConnection,
			JedisCluster, SR, R> {

		@SuppressWarnings({"unchecked", "rawtype"})
		public JedisClusterCommand(final JedisClusterClient client, final ProtocolCommand command) {
			super(client, command, null, (value)->(R) value);
		}

		public JedisClusterCommand(final JedisClusterClient client, final ProtocolCommand command,
								   final Converter<SR, R> converter) {
			super(client, command, null, converter);
		}

		@SuppressWarnings({"unchecked", "rawtype"})
		public JedisClusterCommand(final JedisClusterClient client, final ProtocolCommand command,
								   final Executor<JedisCluster, SR> executor) {
			super(client, command, executor, (value)->(R) value);
		}

		public JedisClusterCommand(final JedisClusterClient client, final ProtocolCommand command,
								   final Executor<JedisCluster, SR> executor,
								   final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		protected SR doExecute() throws Exception {
			return executor.execute(connection.getCluster());
		}

	}

	class JedisClusterPipelineCommand<SR, R> extends
			AbstractClusterCommand<JedisClusterClient, JedisClusterConnection, ClusterPipeline, Response<SR>, R> {

		public JedisClusterPipelineCommand(final JedisClusterClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisClusterPipelineCommand(final JedisClusterClient client, final ProtocolCommand command,
										   final Converter<SR, R> converter) {
			super(client, command, new RedisResponseConverter<>(converter));
		}

		public JedisClusterPipelineCommand(final JedisClusterClient client, final ProtocolCommand command,
										   final Executor<ClusterPipeline, Response<SR>> executor) {
			super(client, command, executor);
		}

		public JedisClusterPipelineCommand(final JedisClusterClient client, final ProtocolCommand command,
										   final Executor<ClusterPipeline, Response<SR>> executor,
										   final Converter<SR, R> converter) {
			super(client, command, executor, new RedisResponseConverter<>(converter));
		}

		@Override
		protected Response<SR> doExecute() throws Exception {
			return executor.execute(connection.getCluster().pipelined());
		}

	}

	class JedisClusterTransactionCommand<SR, R>
			extends AbstractClusterCommand<JedisClusterClient, JedisClusterConnection, Transaction, Response<SR>, R> {

		public JedisClusterTransactionCommand(final JedisClusterClient client, final ProtocolCommand command) {
			super(client, command);
		}

		public JedisClusterTransactionCommand(final JedisClusterClient client, final ProtocolCommand command,
											  final Converter<SR, R> converter) {
			super(client, command, new RedisResponseConverter<>(converter));
		}

		public JedisClusterTransactionCommand(final JedisClusterClient client, final ProtocolCommand command,
											  final Executor<Transaction, Response<SR>> executor) {
			super(client, command, executor);
		}

		public JedisClusterTransactionCommand(final JedisClusterClient client, final ProtocolCommand command,
											  final Executor<Transaction, Response<SR>> executor,
											  final Converter<SR, R> converter) {
			super(client, command, executor, new RedisResponseConverter<>(converter));
		}

		@Override
		protected Response<SR> doExecute() throws Exception {
			return executor.execute(connection.getCluster().multi());
		}

	}

}
