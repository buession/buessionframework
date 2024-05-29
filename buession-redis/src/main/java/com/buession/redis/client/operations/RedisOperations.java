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
package com.buession.redis.client.operations;

import com.buession.core.Executor;
import com.buession.core.converter.Converter;
import com.buession.redis.client.RedisClusterClient;
import com.buession.redis.client.RedisSentinelClient;
import com.buession.redis.client.RedisStandaloneClient;
import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.RedisSentinelConnection;
import com.buession.redis.client.connection.RedisStandaloneConnection;
import com.buession.redis.core.RedisMode;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.AbstractRedisOperationsCommand;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedPipelineCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisException;

/**
 * Redis 命令操作接口
 *
 * @author Yong.Teng
 */
public interface RedisOperations {

	abstract class AbstractStandaloneCommand<CLIENT extends RedisStandaloneClient, CONN extends RedisStandaloneConnection, CXT, OSR, SR, R>
			extends AbstractRedisOperationsCommand<CLIENT, CONN, CXT, OSR, SR, R> {

		public AbstractStandaloneCommand(final CLIENT client, final ProtocolCommand command) {
			super(client, command);
		}

		public AbstractStandaloneCommand(final CLIENT client, final ProtocolCommand command,
										 final Executor<CXT, OSR> executor) {
			super(client, command, executor);
		}

		public AbstractStandaloneCommand(final CLIENT client, final ProtocolCommand command,
										 final Converter<SR, R> converter) {
			super(client, command, converter);
		}

		public AbstractStandaloneCommand(final CLIENT client, final ProtocolCommand command,
										 final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		public R execute() throws RedisException {
			final RedisMode mode = RedisConnectionUtils.getRedisMode(connection);

			if(executor == null){
				if(connection.isPipeline()){
					throw new NotSupportedPipelineCommandException(mode, getCommand());
				}else if(connection.isTransaction()){
					throw new NotSupportedTransactionCommandException(mode, getCommand());
				}else{
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.NORMAL, getCommand());
				}
			}else{
				try{
					return doExecute();
				}catch(Exception e){
					throw new RedisException(e.getMessage(), e);
				}
			}
		}

	}

	abstract class AbstractSentinelCommand<CLIENT extends RedisSentinelClient, CONN extends RedisSentinelConnection, CXT, OSR, SR, R>
			extends AbstractRedisOperationsCommand<CLIENT, CONN, CXT, OSR, SR, R> {

		public AbstractSentinelCommand(final CLIENT client, final ProtocolCommand command) {
			super(client, command);
		}

		public AbstractSentinelCommand(final CLIENT client, final ProtocolCommand command,
									   final Executor<CXT, OSR> executor) {
			super(client, command, executor);
		}

		public AbstractSentinelCommand(final CLIENT client, final ProtocolCommand command,
									   final Converter<SR, R> converter) {
			super(client, command, converter);
		}

		public AbstractSentinelCommand(final CLIENT client, ProtocolCommand command,
									   final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		public R execute() throws RedisException {
			final RedisMode mode = RedisConnectionUtils.getRedisMode(connection);

			if(executor == null){
				if(connection.isPipeline()){
					throw new NotSupportedPipelineCommandException(mode, getCommand());
				}else if(connection.isTransaction()){
					throw new NotSupportedTransactionCommandException(mode, getCommand());
				}else{
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.NORMAL, getCommand());
				}
			}else{
				try{
					return doExecute();
				}catch(Exception e){
					throw new RedisException(e.getMessage(), e);
				}
			}
		}

	}

	abstract class AbstractClusterCommand<CLIENT extends RedisClusterClient, CONN extends RedisClusterConnection, CXT, OSR, SR, R>
			extends AbstractRedisOperationsCommand<CLIENT, CONN, CXT, OSR, SR, R> {

		public AbstractClusterCommand(final CLIENT client, final ProtocolCommand command) {
			super(client, command);
		}

		public AbstractClusterCommand(final CLIENT client, final ProtocolCommand command,
									  final Executor<CXT, OSR> executor) {
			super(client, command, executor);
		}

		public AbstractClusterCommand(final CLIENT client, final ProtocolCommand command,
									  final Converter<SR, R> converter) {
			super(client, command, converter);
		}

		public AbstractClusterCommand(final CLIENT client, final ProtocolCommand command,
									  final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		@Override
		public R execute() throws RedisException {
			final RedisMode mode = RedisConnectionUtils.getRedisMode(connection);

			if(executor == null){
				if(connection.isPipeline()){
					throw new NotSupportedPipelineCommandException(mode, getCommand());
				}else if(connection.isTransaction()){
					throw new NotSupportedTransactionCommandException(mode, getCommand());
				}else{
					throw new NotSupportedCommandException(RedisConnectionUtils.getRedisMode(connection),
							NotSupportedCommandException.Type.NORMAL, getCommand());
				}
			}else{
				try{
					return doExecute();
				}catch(Exception e){
					throw new RedisException(e.getMessage(), e);
				}
			}
		}

	}

	/*
	abstract class AbstractStandalonePipelineCommand<CLIENT extends RedisClient, CONN extends RedisConnection, CXT, SR, R>
			extends AbstractRedisOperationsCommand<CLIENT, CONN, CXT, SR, R> {

		protected final Command<R> redisCommand;

		protected AbstractStandalonePipelineCommand(final CLIENT client, final ProtocolCommand command,
													final Command<R> redisCommand) {
			super(client, command);
			this.redisCommand = redisCommand;
		}

		@Override
		public R execute() throws RedisException {
			return redisCommand.execute();
		}

		protected static abstract class BaseLettuceAsyncCommand<CONN extends RedisConnection, OSR, SR, R>
				implements Command<R> {

			protected final CONN connection;

			protected final Executor<RedisAsyncCommands<byte[], byte[]>, OSR> executor;

			protected final Converter<OSR, R> converter;

			public BaseLettuceAsyncCommand(final CONN connection,
										   final Executor<RedisAsyncCommands<byte[], byte[]>, OSR> executor,
										   final Converter<OSR, R> converter) {
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
				final RedisMode mode = RedisConnectionUtils.getRedisMode(connection);

				if(executor == null){
					if(connection.isPipeline()){
						throw new NotSupportedPipelineCommandException(mode, getCommand());
					}else if(connection.isTransaction()){
						throw new NotSupportedTransactionCommandException(mode, getCommand());
					}else{
						throw new NotSupportedCommandException(mode, NotSupportedCommandException.Type.NORMAL,
								getCommand());
					}
				}

				try{
					final OSR result = doExecute();
					return result == null ? null : converter.convert(result);
				}catch(Exception e){
					throw new RedisException(e.getMessage(), e);
				}
			}

			@Override
			public R run(CommandArguments arguments) throws RedisException {
				return null;
			}

			protected abstract OSR doExecute() throws Exception;

		}

	}

	 */

}
