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
package com.buession.redis.client.operations;

import com.buession.core.converter.Converter;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.core.RedisMode;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedPipelineCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.transaction.Transaction;

/**
 * Redis 命令操作接口
 *
 * @author Yong.Teng
 */
public interface RedisOperations {

	/**
	 * Redis 运算命令
	 * *
	 * * @param <CLIENT>
	 * * 		Redis 客户端 {@link RedisClient}
	 * * @param <CONN>
	 * * 		Redis 连接对象 {@link RedisConnection}
	 *
	 * @param <R>
	 * 		返回值类型
	 *
	 * @author Yong.Teng
	 * @since 4.0.0
	 */
	interface RedisOperationsCommand<CLIENT extends RedisClient, CONN extends RedisConnection, R> extends
			com.buession.redis.core.Command<R> {

	}

	/**
	 *
	 * Redis 运算命令抽象类
	 *
	 * @param <CLIENT>
	 * 		Redis 客户端 {@link RedisClient}
	 * @param <CONN>
	 * 		Redis 连接对象 {@link RedisConnection}
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
	abstract class AbstractRedisOperationsCommand<CLIENT extends RedisClient, CONN extends RedisConnection, CXT,
			OSR, SR, R> implements RedisOperationsCommand<CLIENT, CONN, R> {

		/**
		 * Redis 连接对象 {@link RedisConnection} 实例
		 */
		protected final CONN connection;

		/**
		 * {@link RedisClient} 实例
		 */
		protected final CLIENT client;

		/**
		 * Redis 协议命令
		 */
		protected final com.buession.redis.core.command.Command command;

		/**
		 * Redis 协议子命令
		 *
		 * @since 4.0.0
		 */
		protected final SubCommand subCommand;

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
		public AbstractRedisOperationsCommand(final CLIENT client, final Command command) {
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
		public AbstractRedisOperationsCommand(final CLIENT client, final Command command,
											  final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
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
		public AbstractRedisOperationsCommand(final CLIENT client, final Command command, final SubCommand subCommand) {
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
		@SuppressWarnings({"unchecked"})
		public AbstractRedisOperationsCommand(final CLIENT client, final Command command, final SubCommand subCommand,
											  final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			this.client = client;
			this.command = command;
			this.subCommand = subCommand;
			this.connection = (CONN) client.getConnection();
			this.executor = executor;
			this.converter = converter;
		}

		@Override
		public com.buession.redis.core.command.Command getCommand() {
			return command;
		}

		@Override
		public SubCommand getSubCommand() {
			return subCommand;
		}

		@Override
		public R run(final CommandArguments arguments) throws RedisException {
			return client.execute(this, arguments);
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
			}else{
				return doExecute();
			}
		}

		protected Pipeline pipeline() {
			return connection.openPipeline();
		}

		protected Transaction transaction() {
			return connection.multi();
		}

		protected abstract R doExecute() throws RedisException;

	}

	abstract class BaseCommandBuilder<C extends RedisClient, O, SR, R> {

		protected final C client;

		protected final Command command;

		protected SubCommand subCommand;

		protected com.buession.redis.core.Command.Executor<O, SR> executor;

		protected CommandArguments arguments;

		protected Converter<SR, R> converter;

		protected BaseCommandBuilder(final C client, final Command command) {
			this.client = client;
			this.command = command;
		}

		protected BaseCommandBuilder(final C client, final Command command, final SubCommand subCommand) {
			this(client, command);
			this.subCommand = subCommand;
		}

		public BaseCommandBuilder<C, O, SR, R> executor(com.buession.redis.core.Command.Executor<O, SR> executor) {
			this.executor = executor;
			return this;
		}

		public BaseCommandBuilder<C, O, SR, R> arguments(CommandArguments arguments) {
			this.arguments = arguments;
			return this;
		}

		public BaseCommandBuilder<C, O, SR, R> converter(Converter<SR, R> converter) {
			this.converter = converter;
			return this;
		}

		abstract public R run();

	}

}
