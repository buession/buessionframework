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

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.Suggestion;
import com.buession.redis.core.command.AutoSuggestCommands;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.autosuggest.SugGetArgument;
import com.buession.redis.core.internal.convert.lettuce.response.SuggestionConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceSugAddArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceSugGetArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.search.arguments.SugAddArgs;

import java.util.List;

/**
 * Lettuce 自动提示命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceAutoSuggestCommands extends AbstractLettuceRedisCommands implements AutoSuggestCommands {

	public LettuceAutoSuggestCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Long ftSugAdd(final String key, final String value, final double score) {
		final CommandArguments args = CommandArguments.create(key, value).add(score);
		return executeCommand(RedisCommand.FT_SUGADD, args,
				(cmd)->cmd.ftSugadd(rawBinaryKey(key), SafeEncoder.encode(value), score));
	}

	@Override
	public Long ftSugAdd(final byte[] key, final byte[] value, final double score) {
		final CommandArguments args = CommandArguments.create(key, value).add(score);
		return executeCommand(RedisCommand.FT_SUGADD, args, (cmd)->cmd.ftSugadd(rawKey(key), value, score));
	}

	@Override
	public Long ftSugAdd(final String key, final String value, final double score, final boolean incr) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add(incr ? "INCR" : null);
		return ftSugAdd(rawBinaryKey(key), SafeEncoder.encode(value), score, new LettuceSugAddArgs<>(incr), args);
	}

	@Override
	public Long ftSugAdd(final byte[] key, final byte[] value, final double score, final boolean incr) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add(incr ? "INCR" : null);
		return ftSugAdd(rawKey(key), value, score, new LettuceSugAddArgs<>(incr), args);
	}

	@Override
	public Long ftSugAdd(final String key, final String value, final double score, final boolean incr,
	                     final String payload) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add(incr ? "INCR" : null)
				.add("PAYLOAD", payload);
		return ftSugAdd(rawBinaryKey(key), SafeEncoder.encode(value), score,
				new LettuceSugAddArgs<>(incr, SafeEncoder.encode(payload)), args);
	}

	@Override
	public Long ftSugAdd(final byte[] key, final byte[] value, final double score, final boolean incr,
	                     final byte[] payload) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add(incr ? "INCR" : null)
				.add("PAYLOAD", payload);
		return ftSugAdd(rawKey(key), value, score, new LettuceSugAddArgs<>(incr, payload), args);
	}

	@Override
	public Long ftSugAdd(final String key, final String value, final double score, final String payload) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add("PAYLOAD", payload);
		return ftSugAdd(rawBinaryKey(key), SafeEncoder.encode(value), score,
				new LettuceSugAddArgs<>(SafeEncoder.encode(payload)), args);
	}

	@Override
	public Long ftSugAdd(final byte[] key, final byte[] value, final double score, final byte[] payload) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add("PAYLOAD", payload);
		return ftSugAdd(rawKey(key), value, score, new LettuceSugAddArgs<>(payload), args);
	}

	@Override
	public Status ftSugDel(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.FT_SUGDEL, args,
				(cmd)->cmd.ftSugdel(rawBinaryKey(key), SafeEncoder.encode(value)), new BooleanStatusConverter());
	}

	@Override
	public Status ftSugDel(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.FT_SUGDEL, args, (cmd)->cmd.ftSugdel(rawKey(key), value),
				new BooleanStatusConverter());
	}

	@Override
	public List<Suggestion> ftSugGet(final String key, final String prefix) {
		final CommandArguments args = CommandArguments.create(key, prefix);
		return executeCommand(RedisCommand.FT_SUGGET, args,
				(cmd)->cmd.ftSugget(rawBinaryKey(key), SafeEncoder.encode(prefix)),
				new ListConverter<>(new SuggestionConverter()));
	}

	@Override
	public List<Suggestion> ftSugGet(final byte[] key, final byte[] prefix) {
		final CommandArguments args = CommandArguments.create(key, prefix);
		return executeCommand(RedisCommand.FT_SUGGET, args, (cmd)->cmd.ftSugget(rawKey(key), prefix),
				new ListConverter<>(new SuggestionConverter()));
	}

	@Override
	public List<Suggestion> ftSugGet(final String key, final String prefix, final SugGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key, prefix).add(argument);
		return executeCommand(RedisCommand.FT_SUGGET, args,
				(cmd)->cmd.ftSugget(rawBinaryKey(key), SafeEncoder.encode(prefix), new LettuceSugGetArgs<>(argument)),
				new ListConverter<>(new SuggestionConverter()));
	}

	@Override
	public List<Suggestion> ftSugGet(final byte[] key, final byte[] prefix, final SugGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key, prefix).add(argument);
		return executeCommand(RedisCommand.FT_SUGGET, args,
				(cmd)->cmd.ftSugget(rawKey(key), prefix, new LettuceSugGetArgs<>(argument)),
				new ListConverter<>(new SuggestionConverter()));
	}

	@Override
	public Long ftSugLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.FT_SUGLEN, args, (cmd)->cmd.ftSuglen(rawBinaryKey(key)));
	}

	@Override
	public Long ftSugLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.FT_SUGLEN, args, (cmd)->cmd.ftSuglen(rawKey(key)));
	}

	private Long ftSugAdd(final byte[] key, final byte[] value, final double score,
	                      final SugAddArgs<byte[], byte[]> sugAddArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.FT_SUGADD, args, (cmd)->cmd.ftSugadd(key, value, score, sugAddArgs));
	}

}
