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

import com.buession.core.converter.Converter;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.RedisClusterClient;
import com.buession.redis.client.RedisSentinelClient;
import com.buession.redis.client.RedisStandaloneClient;
import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.RedisSentinelConnection;
import com.buession.redis.client.connection.RedisStandaloneConnection;
import com.buession.redis.core.AbstractRedisCommand;
import com.buession.redis.core.RedisMode;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.SubCommand;
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

	/**
	 * Redis 运算命令
	 *
	 * @param <CLIENT>
	 * 		Redis 客户端 {@link RedisClient}
	 * @param <CONN>
	 * 		Redis 连接对象 {@link RedisConnection}
	 *
	 * @since 3.0.0
	 */
	interface RedisOperationsCommand<CLIENT extends RedisClient, CONN extends RedisConnection> {

	}

	/**
	 * Redis 单机模式运算命令
	 *
	 * @param <CLIENT>
	 * 		Redis 单机客户端 {@link RedisStandaloneClient}
	 * @param <CONN>
	 * 		Redis 单机连接对象 {@link RedisStandaloneConnection}
	 *
	 * @since 3.0.0
	 */
	interface RedisStandaloneOperationsCommand<CLIENT extends RedisStandaloneClient,
			CONN extends RedisStandaloneConnection> extends RedisOperationsCommand<CLIENT, CONN> {

	}

	/**
	 * Redis 哨兵模式运算命令
	 *
	 * @param <CLIENT>
	 * 		Redis 哨兵客户端 {@link RedisSentinelClient}
	 * @param <CONN>
	 * 		Redis 哨兵连接对象 {@link RedisSentinelConnection}
	 *
	 * @since 3.0.0
	 */
	interface RedisSentinelOperationsCommand<CLIENT extends RedisSentinelClient,
			CONN extends RedisSentinelConnection> extends RedisOperationsCommand<CLIENT, CONN> {

	}

	/**
	 * Redis 集群模式运算命令
	 *
	 * @param <CLIENT>
	 * 		Redis 集群客户端 {@link RedisClusterClient}
	 * @param <CONN>
	 * 		Redis 集群连接对象 {@link RedisClusterConnection}
	 *
	 * @since 3.0.0
	 */
	interface RedisClusterOperationsCommand<CLIENT extends RedisClusterClient,
			CONN extends RedisClusterConnection> extends RedisOperationsCommand<CLIENT, CONN> {

	}

	/**
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
	 * @since 3.0.0
	 */
	abstract class AbstractRedisOperationsCommand<CLIENT extends RedisClient, CONN extends RedisConnection, CXT,
			OSR, SR, R> extends AbstractRedisCommand<CLIENT, R> implements RedisOperationsCommand<CLIENT, CONN> {

		/**
		 * Redis 连接对象 {@link RedisConnection} 实例
		 */
		protected final CONN connection;

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
		@SuppressWarnings({"unchecked"})
		public AbstractRedisOperationsCommand(final CLIENT client, final Command command,
											  final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command);
			connection = (CONN) client.getConnection();
			this.executor = executor;
			this.converter = converter;
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 客户端 {@link RedisClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
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
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		@SuppressWarnings({"unchecked"})
		public AbstractRedisOperationsCommand(final CLIENT client, final Command command, final SubCommand subCommand,
											  final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command, subCommand);
			connection = (CONN) client.getConnection();
			this.executor = executor;
			this.converter = converter;
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

		protected abstract R doExecute() throws RedisException;

	}

	/**
	 * Redis 单机模式命令
	 *
	 * @param <CLIENT>
	 * 		Redis 单机客户端 {@link RedisStandaloneClient}
	 * @param <CONN>
	 * 		Redis 单机连接对象 {@link RedisStandaloneConnection}
	 * @param <CXT>
	 * 		Redis 提供者原生类型
	 * @param <OSR>
	 * 		-
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	abstract class AbstractStandaloneCommand<CLIENT extends RedisStandaloneClient, CONN extends RedisStandaloneConnection, CXT, OSR, SR, R>
			extends AbstractRedisOperationsCommand<CLIENT, CONN, CXT, OSR, SR, R>
			implements RedisStandaloneOperationsCommand<CLIENT, CONN> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link RedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public AbstractStandaloneCommand(final CLIENT client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link RedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public AbstractStandaloneCommand(final CLIENT client, final Command command,
										 final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link RedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public AbstractStandaloneCommand(final CLIENT client, final Command command, final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 单机客户端 {@link RedisStandaloneClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public AbstractStandaloneCommand(final CLIENT client, final Command command, final SubCommand subCommand,
										 final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

	}

	/**
	 * Redis 哨兵模式命令
	 *
	 * @param <CLIENT>
	 * 		Redis 哨兵客户端 {@link RedisSentinelClient}
	 * @param <CONN>
	 * 		Redis 哨兵连接对象 {@link RedisSentinelConnection}
	 * @param <CXT>
	 * 		Redis 提供者原生类型
	 * @param <OSR>
	 * 		-
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	abstract class AbstractSentinelCommand<CLIENT extends RedisSentinelClient, CONN extends RedisSentinelConnection, CXT, OSR, SR, R>
			extends AbstractRedisOperationsCommand<CLIENT, CONN, CXT, OSR, SR, R>
			implements RedisSentinelOperationsCommand<CLIENT, CONN> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link RedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public AbstractSentinelCommand(final CLIENT client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link RedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public AbstractSentinelCommand(final CLIENT client, final Command command,
									   final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link RedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public AbstractSentinelCommand(final CLIENT client, final Command command, final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 哨兵客户端 {@link RedisSentinelClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public AbstractSentinelCommand(final CLIENT client, final Command command, final SubCommand subCommand,
									   final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

	}

	/**
	 * Redis 集群模式命令
	 *
	 * @param <CLIENT>
	 * 		Redis 集群客户端 {@link RedisClusterClient}
	 * @param <CONN>
	 * 		Redis 集群连接对象 {@link RedisClusterConnection}
	 * @param <CXT>
	 * 		Redis 提供者原生类型
	 * @param <OSR>
	 * 		-
	 * @param <SR>
	 * 		原生结果类型
	 * @param <R>
	 * 		结果类型
	 */
	abstract class AbstractClusterCommand<CLIENT extends RedisClusterClient, CONN extends RedisClusterConnection, CXT, OSR, SR, R>
			extends AbstractRedisOperationsCommand<CLIENT, CONN, CXT, OSR, SR, R>
			implements RedisClusterOperationsCommand<CLIENT, CONN> {

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link RedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 */
		public AbstractClusterCommand(final CLIENT client, final Command command) {
			super(client, command);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link RedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public AbstractClusterCommand(final CLIENT client, final Command command,
									  final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command, executor, converter);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link RedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 */
		public AbstractClusterCommand(final CLIENT client, final Command command, final SubCommand subCommand) {
			super(client, command, subCommand);
		}

		/**
		 * 构造函数
		 *
		 * @param client
		 * 		Redis 集群客户端 {@link RedisClusterClient} 实例
		 * @param command
		 * 		Redis 命令
		 * @param subCommand
		 * 		Redis 子命令
		 * @param executor
		 * 		Redis 命令执行器
		 * @param converter
		 * 		结果转换器
		 */
		public AbstractClusterCommand(final CLIENT client, final Command command, final SubCommand subCommand,
									  final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
			super(client, command, subCommand, executor, converter);
		}

	}

}
