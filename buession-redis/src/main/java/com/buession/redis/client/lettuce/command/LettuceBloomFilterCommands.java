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
import com.buession.redis.core.command.BloomFilterCommands;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.BFInsertArgument;
import com.buession.redis.core.command.args.BFReserveArgument;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 布隆过滤器命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceBloomFilterCommands extends AbstractLettuceRedisCommands implements
		BloomFilterCommands {

	public LettuceBloomFilterCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Status bfAdd(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.BF_ADD, args);
	}

	@Override
	public Status bfAdd(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.BF_ADD, args);
	}

	@Override
	public Long bfCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.BF_CARD, args);
	}

	@Override
	public Long bfCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.BF_CARD, args);
	}

	@Override
	public Boolean bfExists(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.BF_EXISTS, args);
	}

	@Override
	public Boolean bfExists(final byte[] key, final byte[] item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return executeCommand(Command.BF_EXISTS, args);
	}

	@Override
	public Map<String, Object> bfInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.BF_INFO, args);
	}

	@Override
	public Map<String, Object> bfInfo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.BF_INFO, args);
	}

	@Override
	public List<Boolean> bfInsert(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS").add(items);
		return executeCommand(Command.BF_INSERT, args);
	}

	@Override
	public List<Boolean> bfInsert(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS").add(items);
		return executeCommand(Command.BF_INSERT, args);
	}

	@Override
	public List<Boolean> bfInsert(final String key, final BFInsertArgument argument, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("ITEMS").add(items);
		return executeCommand(Command.BF_INSERT, args);
	}

	@Override
	public List<Boolean> bfInsert(final byte[] key, final BFInsertArgument argument, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("ITEMS").add(items);
		return executeCommand(Command.BF_INSERT, args);
	}

	@Override
	public Status bfLoadchunk(final String key, final long iterator, final byte[] data) {
		final CommandArguments args = CommandArguments.create(key).add(iterator).add(data);
		return executeCommand(Command.BF_LOADCHUNK, args);
	}

	@Override
	public Status bfLoadchunk(final byte[] key, final long iterator, final byte[] data) {
		final CommandArguments args = CommandArguments.create(key).add(iterator).add(data);
		return executeCommand(Command.BF_LOADCHUNK, args);
	}

	@Override
	public List<Boolean> bfMAdd(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(Command.BF_MADD, args);
	}

	@Override
	public List<Boolean> bfMAdd(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(Command.BF_MADD, args);
	}

	@Override
	public List<Boolean> bfMExists(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(Command.BF_MEXISTS, args);
	}

	@Override
	public List<Boolean> bfMExists(final byte[] key, final byte[]... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return executeCommand(Command.BF_MEXISTS, args);
	}

	@Override
	public Status bfReserve(final String key, final BFReserveArgument bfInsertArgument) {
		final CommandArguments args = CommandArguments.create(key).add(bfInsertArgument);
		return executeCommand(Command.BF_RESERVE, args);
	}

	@Override
	public Status bfReserve(final byte[] key, final BFReserveArgument bfInsertArgument) {
		final CommandArguments args = CommandArguments.create(key).add(bfInsertArgument);
		return executeCommand(Command.BF_RESERVE, args);
	}


	@Override
	public Map<Long, byte[]> bfScandump(final String key, final long iterator) {
		final CommandArguments args = CommandArguments.create(key).add(iterator);
		return executeCommand(Command.BF_SCANDUMP, args);
	}

	@Override
	public Map<Long, byte[]> bfScandump(final byte[] key, final long iterator) {
		final CommandArguments args = CommandArguments.create(key).add(iterator);
		return executeCommand(Command.BF_SCANDUMP, args);
	}

}
