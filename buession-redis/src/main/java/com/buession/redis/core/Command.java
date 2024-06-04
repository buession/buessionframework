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
package com.buession.redis.core;

import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisException;

/**
 * Redis 命令接口
 *
 * @param <R>
 * 		命令执行结果类型
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface Command<R> {

	/**
	 * 返回协议命令
	 *
	 * @return 协议命令
	 */
	ProtocolCommand getCommand();

	/**
	 * 执行 Redis 命令
	 *
	 * @return Redis 命令执行结果
	 *
	 * @throws RedisException
	 * 		Redis 命令执行异常
	 */
	R execute() throws RedisException;

	/**
	 * 运行 Redis 命令
	 *
	 * @return Redis 命令运行结果
	 *
	 * @throws RedisException
	 * 		Redis 命令运行异常
	 */
	default R run() throws RedisException {
		return run(null);
	}

	/**
	 * 运行 Redis 命令
	 *
	 * @param arguments
	 * 		Redis 命令参数
	 *
	 * @return Redis 命令运行结果
	 *
	 * @throws RedisException
	 * 		Redis 命令运行异常
	 */
	R run(final CommandArguments arguments) throws RedisException;

	@FunctionalInterface
	interface Executor<C, R> extends com.buession.core.Executor<C, R> {

		@Override
		R execute(C context) throws RedisException;

	}

	/**
	 * Redis 命令运行器
	 *
	 * @author Yong.Teng
	 */
	@FunctionalInterface
	interface Runner {

		/**
		 * 运行 Redis 命令
		 *
		 * @param <R>
		 * 		命令运行结果类型
		 *
		 * @return Redis 命令运行结果
		 *
		 * @throws RedisException
		 * 		Redis 命令运行异常
		 */
		<R> R run() throws RedisException;

	}

}
