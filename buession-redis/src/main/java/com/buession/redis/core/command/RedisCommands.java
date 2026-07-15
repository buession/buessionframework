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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

import com.buession.core.converter.Converter;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.core.RedisMode;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedPipelineCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisException;

/**
 * @author Yong.Teng
 */
public interface RedisCommands {

	/**
	 *
	 * Redis 运算命令抽象类
	 *
	 * @param <CLIENT>
	 * 		Redis 客户端 {@link RedisClient}
	 * @param <CXT>
	 * 		Redis 提供者原生类型
	 * @param <OSR>
	 * 		-
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 *
	 * @author Yong.Teng
	 * @since 4.0.0
	 */
	abstract class AbstractCommand<CLIENT extends RedisClient, CXT, OSR, SR, R>
			implements Command<RedisConnection, R> {

		/**
		 * {@link RedisClient} 实例
		 */
		protected final CLIENT client;

		/**
		 * Redis 协议命令
		 */
		protected final RedisCommand command;

		/**
		 * Redis 协议子命令
		 *
		 * @since 4.0.0
		 */
		protected final RedisSubCommand subCommand;

		/**
		 * Redis 命令执行器
		 */
		protected final Executor<CXT, OSR> executor;

		/**
		 * 结果转换器
		 */
		protected final Converter<SR, R> converter;

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 客户端 {@link RedisClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		@SuppressWarnings({"unchecked"})
		public AbstractCommand(final CLIENT client, final RedisCommand command) {
			this(client, command, null, (value)->(R) value);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 客户端 {@link RedisClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public AbstractCommand(final CLIENT client, final RedisCommand command, final Executor<CXT, OSR> executor,
		                       final Converter<SR, R> converter) {
			this(client, command, null, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 客户端 {@link RedisClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 协议子命令
		 *
		 * @since 4.0.0
		 */
		@SuppressWarnings({"unchecked"})
		public AbstractCommand(final CLIENT client, final RedisCommand command, final RedisSubCommand subCommand) {
			this(client, command, subCommand, null, (value)->(R) value);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 客户端 {@link RedisClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 协议子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 *
		 * @since 4.0.0
		 */
		public AbstractCommand(final CLIENT client, final RedisCommand command, final RedisSubCommand subCommand,
		                       final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			this.client = client;
			this.command = command;
			this.subCommand = subCommand;
			this.executor = executor;
			this.converter = converter;
		}

		@Override
		public RedisCommand getCommand() {
			return command;
		}

		@Override
		public RedisSubCommand getSubCommand() {
			return subCommand;
		}

		@Override
		public R execute(final RedisConnection conn, final CommandArguments arguments) throws RedisException {
			final RedisMode mode = RedisConnectionUtils.getRedisMode(conn);

			if(executor == null){
				if(conn.isPipeline()){
					throw new NotSupportedPipelineCommandException(mode, getCommand());
				}else if(conn.isTransaction()){
					throw new NotSupportedTransactionCommandException(mode, getCommand());
				}else{
					throw new NotSupportedCommandException(mode, NotSupportedCommandException.Type.NORMAL,
							getCommand());
				}
			}else{
				return doExecute(conn);
			}
		}

		protected abstract R doExecute(final RedisConnection conn) throws RedisException;

	}

}
