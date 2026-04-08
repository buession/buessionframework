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
package com.buession.redis.client.jedis.command;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.Suggestion;
import com.buession.redis.core.command.AutoSuggestCommands;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.autosuggest.SugGetArgument;
import com.buession.redis.core.internal.convert.jedis.response.TupleSuggestionConverter;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Jedis 自动提示命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisAutoSuggestCommands extends AbstractJedisRedisCommands implements AutoSuggestCommands {

	public JedisAutoSuggestCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Long ftSugAdd(final String key, final String value, final double score) {
		final CommandArguments args = CommandArguments.create(key, value).add(score);
		return ftSugAdd(rawKey(key), value, score, args);
	}

	@Override
	public Long ftSugAdd(final byte[] key, final byte[] value, final double score) {
		final CommandArguments args = CommandArguments.create(key, value).add(score);
		return ftSugAdd(rawStringKey(key), SafeEncoder.encode(value), score, args);
	}

	@Override
	public Long ftSugAdd(final String key, final String value, final double score, final boolean incr) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add(incr ? "INCR" : null);

		if(incr){
			return executeCommand(RedisCommand.FT_SUGADD, args,
					(cmd)->cmd.ftSugAddIncr(rawKey(key), value, score),
					(cmd)->cmd.ftSugAddIncr(rawKey(key), value, score),
					(cmd)->cmd.ftSugAddIncr(rawKey(key), value, score));
		}else{
			return ftSugAdd(rawKey(key), value, score, args);
		}
	}

	@Override
	public Long ftSugAdd(final byte[] key, final byte[] value, final double score, final boolean incr) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add(incr ? "INCR" : null);

		if(incr){
			return executeCommand(RedisCommand.FT_SUGADD, args,
					(cmd)->cmd.ftSugAddIncr(rawStringKey(key), SafeEncoder.encode(value), score),
					(cmd)->cmd.ftSugAddIncr(rawStringKey(key), SafeEncoder.encode(value), score),
					(cmd)->cmd.ftSugAddIncr(rawStringKey(key), SafeEncoder.encode(value), score));
		}else{
			return ftSugAdd(rawStringKey(key), SafeEncoder.encode(value), score, args);
		}
	}

	@Override
	public Long ftSugAdd(final String key, final String value, final double score, final boolean incr,
	                     final String payload) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add(incr ? "INCR" : null)
				.add("PAYLOAD", payload);

		if(incr){
			return executeCommand(RedisCommand.FT_SUGADD, args, (cmd)->cmd.ftSugAddIncr(rawKey(key), value, score),
					(cmd)->cmd.ftSugAddIncr(rawKey(key), value, score),
					(cmd)->cmd.ftSugAddIncr(rawKey(key), value, score));
		}else{
			return ftSugAdd(rawKey(key), value, score, args);
		}
	}

	@Override
	public Long ftSugAdd(final byte[] key, final byte[] value, final double score, final boolean incr,
	                     final byte[] payload) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add(incr ? "INCR" : null)
				.add("PAYLOAD", payload);

		if(incr){
			return executeCommand(RedisCommand.FT_SUGADD, args,
					(cmd)->cmd.ftSugAddIncr(rawStringKey(key), SafeEncoder.encode(value), score),
					(cmd)->cmd.ftSugAddIncr(rawStringKey(key), SafeEncoder.encode(value), score),
					(cmd)->cmd.ftSugAddIncr(rawStringKey(key), SafeEncoder.encode(value), score));
		}else{
			return ftSugAdd(rawStringKey(key), SafeEncoder.encode(value), score, args);
		}
	}

	@Override
	public Long ftSugAdd(final String key, final String value, final double score, final String payload) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add("PAYLOAD", payload);
		return ftSugAdd(rawKey(key), value, score, args);
	}

	@Override
	public Long ftSugAdd(final byte[] key, final byte[] value, final double score, final byte[] payload) {
		final CommandArguments args = CommandArguments.create(key, value).add(score).add("PAYLOAD", payload);
		return ftSugAdd(rawStringKey(key), SafeEncoder.encode(value), score, args);
	}

	@Override
	public Status ftSugDel(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.FT_SUGDEL, args, (cmd)->cmd.ftSugDel(rawKey(key), value),
				(cmd)->cmd.ftSugDel(rawKey(key), value), (cmd)->cmd.ftSugDel(rawKey(key), value),
				new BooleanStatusConverter());
	}

	@Override
	public Status ftSugDel(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.FT_SUGDEL, args,
				(cmd)->cmd.ftSugDel(rawStringKey(key), SafeEncoder.encode(value)),
				(cmd)->cmd.ftSugDel(rawStringKey(key), SafeEncoder.encode(value)),
				(cmd)->cmd.ftSugDel(rawStringKey(key), SafeEncoder.encode(value)), new BooleanStatusConverter());
	}

	@Override
	public List<Suggestion> ftSugGet(final String key, final String prefix) {
		final CommandArguments args = CommandArguments.create(key, prefix);
		return executeCommand(RedisCommand.FT_SUGGET, args, (cmd)->cmd.ftSugGet(rawKey(key), prefix),
				(cmd)->cmd.ftSugGet(rawKey(key), prefix), (cmd)->cmd.ftSugGet(rawKey(key), prefix),
				(v)->v.stream().map((item)->new Suggestion(item, null)).collect(Collectors.toList()));
	}

	@Override
	public List<Suggestion> ftSugGet(final byte[] key, final byte[] prefix) {
		final CommandArguments args = CommandArguments.create(key, prefix);
		return executeCommand(RedisCommand.FT_SUGGET, args,
				(cmd)->cmd.ftSugGet(rawStringKey(key), SafeEncoder.encode(prefix)),
				(cmd)->cmd.ftSugGet(rawStringKey(key), SafeEncoder.encode(prefix)),
				(cmd)->cmd.ftSugGet(rawStringKey(key), SafeEncoder.encode(prefix)),
				(v)->v.stream().map((item)->new Suggestion(item, null)).collect(Collectors.toList()));
	}

	@Override
	public List<Suggestion> ftSugGet(final String key, final String prefix, final SugGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key, prefix).add(argument);
		return ftSugGet(rawKey(key), prefix, argument, args);
	}

	@Override
	public List<Suggestion> ftSugGet(final byte[] key, final byte[] prefix, final SugGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key, prefix).add(argument);
		return ftSugGet(rawStringKey(key), SafeEncoder.encode(prefix), argument, args);
	}

	@Override
	public Long ftSugLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.FT_SUGLEN, args, (cmd)->cmd.ftSugLen(rawKey(key)),
				(cmd)->cmd.ftSugLen(rawKey(key)), (cmd)->cmd.ftSugLen(rawKey(key)));
	}

	@Override
	public Long ftSugLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.FT_SUGLEN, args, (cmd)->cmd.ftSugLen(rawStringKey(key)),
				(cmd)->cmd.ftSugLen(rawStringKey(key)), (cmd)->cmd.ftSugLen(rawStringKey(key)));
	}

	private Long ftSugAdd(final String key, final String value, final double score, final CommandArguments args) {
		return executeCommand(RedisCommand.FT_SUGADD, args, (cmd)->cmd.ftSugAdd(key, value, score),
				(cmd)->cmd.ftSugAdd(key, value, score), (cmd)->cmd.ftSugAdd(key, value, score));
	}

	private List<Suggestion> ftSugGet(final String key, final String prefix, final SugGetArgument argument,
	                                  final CommandArguments args) {
		int max = argument.getMax().intValue();
		if(argument != null && Boolean.TRUE.equals(argument.getWithScores())){
			return executeCommand(RedisCommand.FT_SUGGET, args,
					(cmd)->cmd.ftSugGetWithScores(key, prefix, argument.getFuzzy(), max),
					(cmd)->cmd.ftSugGetWithScores(key, prefix, argument.getFuzzy(), max),
					(cmd)->cmd.ftSugGetWithScores(key, prefix, argument.getFuzzy(), max),
					new ListConverter<>(new TupleSuggestionConverter()));
		}else{
			return executeCommand(RedisCommand.FT_SUGGET, args,
					(cmd)->cmd.ftSugGet(key, prefix, argument.getFuzzy(), max),
					(cmd)->cmd.ftSugGet(key, prefix, argument.getFuzzy(), max),
					(cmd)->cmd.ftSugGet(key, prefix, argument.getFuzzy(), max),
					(v)->v.stream().map((item)->new Suggestion(item, null)).collect(Collectors.toList()));
		}
	}

}
