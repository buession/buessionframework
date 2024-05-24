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
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.lettuce.LettuceConnection;
import com.buession.redis.client.connection.lettuce.LettuceRedisConnection;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.core.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.AbstractRedisOperationsCommand;
import com.buession.redis.core.internal.convert.lettuce.response.RedisFutureConverter;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedPipelineCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisException;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
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
				}else{
				}
			}else if(connection.isTransaction()){
				if(executor == null){
					throw new NotSupportedTransactionCommandException(RedisConnectionUtils.getRedisMode(connection),
							getCommand());
				}else{
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

			return null;
		}

	}

	abstract class AbstractLettuceAsyncCommand<CLIENT extends LettuceRedisClient, CONN extends LettuceRedisConnection, SR, R>
			extends AbstractRedisOperationsCommand<CLIENT, CONN, R> {

		protected final Command<R> redisCommand;

		protected AbstractLettuceAsyncCommand(final CLIENT client, final ProtocolCommand command,
											  final Command<R> redisCommand) {
			super(client, command);
			this.redisCommand = redisCommand;
		}

		@Override
		public R execute() throws RedisException {
			return redisCommand.execute();
		}

		protected final static class GeneralCommand<CONN extends RedisConnection, SR, R> implements Command<R> {

			private final CONN connection;

			private final Executor<RedisAsyncCommands<byte[], byte[]>, SR> executor;

			private final Converter<SR, R> converter;

			public GeneralCommand(final CONN connection,
								  final Executor<RedisAsyncCommands<byte[], byte[]>, SR> executor,
								  final Converter<SR, R> converter) {
				this.connection = connection;
				this.executor = executor;
				this.converter = converter;
			}

			@Override
			public ProtocolCommand getCommand() {
				return null;
			}

			@Override
			public R execute() throws RedisException {
				if(connection.isPipeline()){
					if(executor == null){
						throw new NotSupportedPipelineCommandException(RedisConnectionUtils.getRedisMode(connection),
								getCommand());
					}else{
						final StatefulRedisConnection<byte[], byte[]> statefulRedisConnection =
								((LettuceConnection) connection).getStatefulConnection();
						try{
							final SR result = executor.execute(statefulRedisConnection.async());
							return result == null ? null : converter.convert(result);
						}catch(Exception e){
							throw new RedisException(e.getMessage(), e);
						}
					}
				}else if(connection.isTransaction()){
					if(executor == null){
						throw new NotSupportedTransactionCommandException(RedisConnectionUtils.getRedisMode(connection),
								getCommand());
					}else{
						final StatefulRedisConnection<byte[], byte[]> statefulRedisConnection =
								((LettuceConnection) connection).getStatefulConnection();
						try{
							final SR result = executor.execute(statefulRedisConnection.async());
							return result == null ? null : converter.convert(result);
						}catch(Exception e){
							throw new RedisException(e.getMessage(), e);
						}
					}
				}else{
				}

				return null;
			}

			@Override
			public R run(CommandArguments arguments) throws RedisException {
				return null;
			}

		}

		protected final static class AsyncCommand<CONN extends RedisConnection, SR, R> implements Command<R> {

			private final CONN connection;

			private final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor;

			private final Converter<RedisFuture<SR>, R> converter;

			public AsyncCommand(final CONN connection,
								final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
								final Converter<RedisFuture<SR>, R> converter) {
				this.connection = connection;
				this.executor = executor;
				this.converter = converter;
			}

			@Override
			public ProtocolCommand getCommand() {
				return null;
			}

			@Override
			public R execute() throws RedisException {
				if(connection.isPipeline()){
					if(executor == null){
						throw new NotSupportedPipelineCommandException(RedisConnectionUtils.getRedisMode(connection),
								getCommand());
					}else{
						final StatefulRedisConnection<byte[], byte[]> statefulRedisConnection =
								((LettuceConnection) connection).getStatefulConnection();
						try{
							final RedisFuture<SR> result = executor.execute(statefulRedisConnection.async());
							return result == null ? null : converter.convert(result);
						}catch(Exception e){
							throw new RedisException(e.getMessage(), e);
						}
					}
				}else if(connection.isTransaction()){
					if(executor == null){
						throw new NotSupportedTransactionCommandException(RedisConnectionUtils.getRedisMode(connection),
								getCommand());
					}else{
						final StatefulRedisConnection<byte[], byte[]> statefulRedisConnection =
								((LettuceConnection) connection).getStatefulConnection();
						try{
							final RedisFuture<SR> result = executor.execute(statefulRedisConnection.async());
							return result == null ? null : converter.convert(result);
						}catch(Exception e){
							throw new RedisException(e.getMessage(), e);
						}
					}
				}else{
				}

				return null;
			}

			@Override
			public R run(CommandArguments arguments) throws RedisException {
				return null;
			}

		}

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

		@SuppressWarnings({"unchecked", "rawtype"})
		public LettuceCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
							  final Executor<RedisCommands<byte[], byte[]>, SR> executor) {
			super(client, command, executor, (value)->(R) value);
		}

		public LettuceCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
							  final Executor<RedisCommands<byte[], byte[]>, SR> executor,
							  final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

	}

	class LettuceAsyncCommand<SR, R> extends AbstractLettuceAsyncCommand<LettuceStandaloneClient, LettuceConnection,
			SR, R> {

		@SuppressWarnings({"unchecked", "rawtype"})
		public LettuceAsyncCommand(final LettuceStandaloneClient client, final ProtocolCommand command) {
			super(client, command, new AsyncCommand<>(client.getConnection(), null, (value)->(R) value));
		}

		public LettuceAsyncCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
								   final Converter<SR, R> converter) {
			super(client, command,
					new AsyncCommand<>(client.getConnection(), null, new RedisFutureConverter<>(converter)));

		}

		@SuppressWarnings({"unchecked", "rawtype"})
		public LettuceAsyncCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
								   final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor) {
			super(client, command, new AsyncCommand<>(client.getConnection(), executor, (value)->(R) value));
		}

		public LettuceAsyncCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
								   final Executor<RedisAsyncCommands<byte[], byte[]>, RedisFuture<SR>> executor,
								   final Converter<SR, R> converter) {
			super(client, command, new AsyncCommand<>(client.getConnection(), executor,
					new RedisFutureConverter<>(converter)));
		}

		public LettuceAsyncCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
								   final Executor<RedisAsyncCommands<byte[], byte[]>, SR> executor,
								   final Converter<SR, R> converter, final boolean tmp) {
			super(client, command, new GeneralCommand<>(client.getConnection(), executor, converter));
		}

	}

}
