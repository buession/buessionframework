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

import com.buession.redis.client.RedisClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redis 命令抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractRedisCommand<C extends RedisClient, R> implements com.buession.redis.core.Command<R> {

	/**
	 * Redis 客户端 {@link RedisClient} 实例
	 */
	protected final C client;

	/**
	 * Redis 命令
	 */
	private final Command command;

	/**
	 * Redis 子命令
	 */
	private final SubCommand subCommand;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 构造函数
	 *
	 * @param client
	 * 		Redis 客户端 {@link RedisClient} 实例
	 * @param command
	 * 		Redis 命令
	 */
	protected AbstractRedisCommand(final C client, final Command command) {
		this(client, command, null);
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
	protected AbstractRedisCommand(final C client, final Command command, final SubCommand subCommand) {
		this.client = client;
		this.command = command;
		this.subCommand = subCommand;
	}

	@Override
	public Command getCommand() {
		return command;
	}

	public SubCommand getSubCommand() {
		return subCommand;
	}

	@Override
	public R run(final CommandArguments arguments) throws RedisException {
		return client.execute(this, arguments);
	}

	protected Pipeline pipeline() {
		return client.getConnection().openPipeline();
	}

	protected Transaction transaction() {
		return client.getConnection().multi();
	}

}
