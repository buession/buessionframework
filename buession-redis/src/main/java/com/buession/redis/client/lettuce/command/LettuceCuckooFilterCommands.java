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

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CuckooFilterCommands;
import com.buession.redis.core.command.args.CFInsertArgument;
import com.buession.redis.core.command.args.CFReserveArgument;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 布谷鸟过滤器命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceCuckooFilterCommands extends AbstractLettuceRedisCommands implements
		CuckooFilterCommands {

	public LettuceCuckooFilterCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Status cfAdd(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.CF_ADD, args);
	}

	@Override
	public Status cfAdd(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.CF_ADD, args);
	}

	@Override
	public Status cfAddNx(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.CF_ADDNX, args);
	}

	@Override
	public Status cfAddNx(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.CF_ADDNX, args);
	}

	@Override
	public Long cfCount(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.CF_COUNT, args);
	}

	@Override
	public Long cfCount(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.CF_COUNT, args);
	}

	@Override
	public Status cfDel(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.CF_DEL, args);
	}

	@Override
	public Status cfDel(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.CF_DEL, args);
	}

	@Override
	public Boolean cfExists(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.CF_EXISTS, args);
	}

	@Override
	public Boolean cfExists(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.CF_EXISTS, args);
	}

	@Override
	public Map<String, Object> cfInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.CF_INFO, args);
	}

	@Override
	public Map<String, Object> cfInfo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.CF_INFO, args);
	}

	@Override
	public List<Boolean> cfInsert(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS", items);
		return executeCommand(Command.CF_INSERT, args);
	}

	@Override
	public List<Boolean> cfInsert(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS", items);
		return executeCommand(Command.CF_INSERT, args);
	}

	@Override
	public List<Boolean> cfInsert(final String key, final CFInsertArgument argument, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("ITEMS", items);
		return executeCommand(Command.CF_INSERT, args);
	}

	@Override
	public List<Boolean> cfInsert(final byte[] key, final CFInsertArgument argument, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("ITEMS", items);
		return executeCommand(Command.CF_INSERT, args);
	}

	@Override
	public List<Boolean> cfInsertNx(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS", items);
		return executeCommand(Command.CF_INSERTNX, args);
	}

	@Override
	public List<Boolean> cfInsertNx(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS", items);
		return executeCommand(Command.CF_INSERTNX, args);
	}

	@Override
	public List<Boolean> cfInsertNx(final String key, final CFInsertArgument argument, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("ITEMS", items);
		return executeCommand(Command.CF_INSERTNX, args);
	}

	@Override
	public List<Boolean> cfInsertNx(final byte[] key, final CFInsertArgument argument, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("ITEMS", items);
		return executeCommand(Command.CF_INSERTNX, args);
	}

	@Override
	public Status cfLoadchunk(final String key, final long iterator, final byte[] data) {
		final CommandArguments args = CommandArguments.create(key).add(iterator).add(data);
		return executeCommand(Command.CF_LOADCHUNK, args);
	}

	@Override
	public Status cfLoadchunk(final byte[] key, final long iterator, final byte[] data) {
		final CommandArguments args = CommandArguments.create(key).add(iterator).add(data);
		return executeCommand(Command.CF_LOADCHUNK, args);
	}

	@Override
	public List<Boolean> cfMExists(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(Command.CF_MEXISTS, args);
	}

	@Override
	public List<Boolean> cfMExists(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(Command.CF_MEXISTS, args);
	}

	@Override
	public Status cfReserve(final String key, final CFReserveArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.CF_RESERVE, args);
	}

	@Override
	public Status cfReserve(final byte[] key, final CFReserveArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.CF_RESERVE, args);
	}

	@Override
	public Map<Long, byte[]> cfScandump(final String key, final long iterator) {
		final CommandArguments args = CommandArguments.create(key).add(iterator);
		return executeCommand(Command.CF_SCANDUMP, args);
	}

	@Override
	public Map<Long, byte[]> cfScandump(final byte[] key, final long iterator) {
		final CommandArguments args = CommandArguments.create(key).add(iterator);
		return executeCommand(Command.CF_SCANDUMP, args);
	}

}
