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
package com.buession.redis.client.lettuce.command;

import com.buession.core.converter.Converter;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.command.AbstractRedisCommands;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.RedisCommands;
import com.buession.redis.core.command.RedisSubCommand;

/**
 * Lettuce Redis 命令抽象类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractLettuceRedisCommands extends AbstractRedisCommands<LettuceRedisClient>
		implements LettuceRedisCommands, RedisCommands {

	public AbstractLettuceRedisCommands(final LettuceRedisClient client) {
		super(client);
	}

	protected <R> R executeCommand(final RedisCommand command) {
		return client.execute(new LettuceCommand<>(client, command));
	}

	protected <R> R executeCommand(final RedisCommand command,
	                               final Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, R> executor) {
		return executeCommand(command, executor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command,
	                                   final Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, SR> executor,
	                                   final Converter<SR, R> converter) {
		return client.execute(new LettuceCommand<>(client, command, executor, converter));
	}

	protected <R> R executeCommand(final RedisCommand command, final CommandArguments args) {
		return client.execute(new LettuceCommand<>(client, command), args);
	}

	protected <R> R executeCommand(final RedisCommand command, final CommandArguments args,
	                               final Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, R> executor) {
		return executeCommand(command, args, executor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command, final CommandArguments args,
	                                   final Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, SR> executor,
	                                   final Converter<SR, R> converter) {
		return client.execute(new LettuceCommand<>(client, command, executor, converter), args);
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand) {
		return client.execute(new LettuceCommand<>(client, command, subCommand));
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                               final Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, R> executor) {
		return executeCommand(command, subCommand, executor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                                   final Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, SR> executor,
	                                   final Converter<SR, R> converter) {
		return client.execute(new LettuceCommand<>(client, command, subCommand, executor, converter));
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                               final CommandArguments args) {
		return client.execute(new LettuceCommand<>(client, command, subCommand), args);
	}

	protected <R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                               final CommandArguments args,
	                               final Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, R> executor) {
		return executeCommand(command, subCommand, args, executor, (v)->v);
	}

	protected <SR, R> R executeCommand(final RedisCommand command, final RedisSubCommand subCommand,
	                                   final CommandArguments args,
	                                   final Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, SR> executor,
	                                   final Converter<SR, R> converter) {
		return client.execute(new LettuceCommand<>(client, command, subCommand, executor, converter), args);
	}

}
