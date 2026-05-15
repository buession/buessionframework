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

import com.buession.core.converter.ArrayConverter;
import com.buession.core.converter.ArrayListConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.deserializer.ByteArrayDeserializer;
import com.buession.core.deserializer.DefaultByteArrayDeserializer;
import com.buession.core.deserializer.DeserializerException;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.JsonType;
import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.JsonCommands;
import com.buession.redis.core.command.RedisSubCommand;
import com.buession.redis.core.command.args.json.JsonGetArgument;
import com.buession.redis.core.command.args.json.KeyPathValue;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.JsonTypeConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.lettuce.LettuceJsonPath;
import com.buession.redis.core.internal.lettuce.args.LettuceJsonGetArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceJsonRangeArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceJsonSetArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.json.DefaultJsonParser;
import io.lettuce.core.json.JsonParser;
import io.lettuce.core.json.JsonPath;
import io.lettuce.core.json.arguments.JsonMsetArgs;

import java.util.List;

/**
 * Lettuce JSON 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceJsonCommands extends AbstractLettuceRedisCommands implements JsonCommands {

	public LettuceJsonCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public List<Long> jsonArrAppend(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.JSON_ARRAPPEND, args,
				(cmd)->cmd.jsonArrappend(SafeEncoder.encode(key), values),
				(cmd)->cmd.jsonArrappend(SafeEncoder.encode(key), values));
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.JSON_ARRAPPEND, args,
				(cmd)->cmd.jsonArrappend(key, SafeEncoder.encode(values)),
				(cmd)->cmd.jsonArrappend(key, SafeEncoder.encode(values)));
	}

	@Override
	public List<Long> jsonArrAppend(final String key, final String path, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(values);
		return executeCommand(RedisCommand.JSON_ARRAPPEND, args,
				(cmd)->cmd.jsonArrappend(SafeEncoder.encode(key), new LettuceJsonPath(path), values),
				(cmd)->cmd.jsonArrappend(SafeEncoder.encode(key), new LettuceJsonPath(path), values));
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[] path, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(values);
		return executeCommand(RedisCommand.JSON_ARRAPPEND, args,
				(cmd)->cmd.jsonArrappend(key, new LettuceJsonPath(path), SafeEncoder.encode(values)),
				(cmd)->cmd.jsonArrappend(key, new LettuceJsonPath(path), SafeEncoder.encode(values)));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path, value);
		return executeCommand(RedisCommand.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value),
				(cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value));
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path, value);
		return executeCommand(RedisCommand.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value)),
				(cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value)));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start) {
		final CommandArguments args = CommandArguments.create(key).add(path, value).add(start);
		return executeCommand(RedisCommand.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value,
						new LettuceJsonRangeArgs(start)),
				(cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value,
						new LettuceJsonRangeArgs(start)));
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start) {
		final CommandArguments args = CommandArguments.create(key).add(path, value).add(start);
		return executeCommand(RedisCommand.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value),
						new LettuceJsonRangeArgs(start)),
				(cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value),
						new LettuceJsonRangeArgs(start)));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start,
	                               final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path, value).add(start).add(stop);
		return executeCommand(RedisCommand.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value,
						new LettuceJsonRangeArgs(start, stop)),
				(cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value,
						new LettuceJsonRangeArgs(start, stop)));
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start,
	                               final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path, value).add(start).add(stop);
		return executeCommand(RedisCommand.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value),
						new LettuceJsonRangeArgs(start, stop)),
				(cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value),
						new LettuceJsonRangeArgs(start, stop)));
	}

	@Override
	public List<Long> jsonArrInsert(final String key, final String path, final int index, final String... values) {
		final CommandArguments args = CommandArguments.create(key, path).add(index).add(values);
		return executeCommand(RedisCommand.JSON_ARRINSERT, args,
				(cmd)->cmd.jsonArrinsert(SafeEncoder.encode(key), new LettuceJsonPath(path), index, values),
				(cmd)->cmd.jsonArrinsert(SafeEncoder.encode(key), new LettuceJsonPath(path), index, values));
	}

	@Override
	public List<Long> jsonArrInsert(final byte[] key, final byte[] path, final int index, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key, path).add(index).add(values);
		return executeCommand(RedisCommand.JSON_ARRINSERT, args,
				(cmd)->cmd.jsonArrinsert(key, new LettuceJsonPath(path), index, SafeEncoder.encode(values)),
				(cmd)->cmd.jsonArrinsert(key, new LettuceJsonPath(path), index, SafeEncoder.encode(values)));
	}

	@Override
	public Long jsonArrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_ARRLEN, args, (cmd)->cmd.jsonArrlen(SafeEncoder.encode(key)),
				(cmd)->cmd.jsonArrlen(SafeEncoder.encode(key)), Converters.list0Converter());
	}

	@Override
	public Long jsonArrLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_ARRLEN, args, (cmd)->cmd.jsonArrlen(key),
				(cmd)->cmd.jsonArrlen(key), Converters.list0Converter());
	}

	@Override
	public List<Long> jsonArrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(RedisCommand.JSON_ARRLEN, args,
				(cmd)->cmd.jsonArrlen(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				(cmd)->cmd.jsonArrlen(SafeEncoder.encode(key), new LettuceJsonPath(path)));
	}

	@Override
	public List<Long> jsonArrLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(RedisCommand.JSON_ARRLEN, args,
				(cmd)->cmd.jsonArrlen(key, new LettuceJsonPath(path)),
				(cmd)->cmd.jsonArrlen(key, new LettuceJsonPath(path)));
	}

	@Override
	public String jsonArrPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_ARRPOP, args, (cmd)->cmd.jsonArrpopRaw(SafeEncoder.encode(key)),
				(cmd)->cmd.jsonArrpopRaw(SafeEncoder.encode(key)), Converters.list0Converter());
	}

	@Override
	public byte[] jsonArrPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_ARRPOP, args, (cmd)->cmd.jsonArrpopRaw(key),
				(cmd)->cmd.jsonArrpopRaw(key), (v)->Validate.isEmpty(v) ? null : SafeEncoder.encode(v.get(0)));
	}

	@Override
	public List<String> jsonArrPop(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_ARRPOP, args,
				(cmd)->cmd.jsonArrpopRaw(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				(cmd)->cmd.jsonArrpopRaw(SafeEncoder.encode(key), new LettuceJsonPath(path)));
	}

	@Override
	public List<byte[]> jsonArrPop(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_ARRPOP, args,
				(cmd)->cmd.jsonArrpopRaw(key, new LettuceJsonPath(path)),
				(cmd)->cmd.jsonArrpopRaw(key, new LettuceJsonPath(path)),
				Converters.stringListBinaryListConverter());
	}

	@Override
	public List<String> jsonArrPop(final String key, final String path, final int index) {
		final CommandArguments args = CommandArguments.create(key, path).add(index);
		return executeCommand(RedisCommand.JSON_ARRPOP, args,
				(cmd)->cmd.jsonArrpopRaw(SafeEncoder.encode(key), new LettuceJsonPath(path), index),
				(cmd)->cmd.jsonArrpopRaw(SafeEncoder.encode(key), new LettuceJsonPath(path), index));
	}

	@Override
	public List<byte[]> jsonArrPop(final byte[] key, final byte[] path, final int index) {
		final CommandArguments args = CommandArguments.create(key, path).add(index);
		return executeCommand(RedisCommand.JSON_ARRPOP, args,
				(cmd)->cmd.jsonArrpopRaw(key, new LettuceJsonPath(path), index),
				(cmd)->cmd.jsonArrpopRaw(key, new LettuceJsonPath(path), index),
				Converters.stringListBinaryListConverter());
	}

	@Override
	public List<Long> jsonArrTrim(final String key, final String path, final int start, final int stop) {
		final CommandArguments args = CommandArguments.create(key, path).add(start).add(stop);
		return executeCommand(RedisCommand.JSON_ARRTRIM, args,
				(cmd)->cmd.jsonArrtrim(SafeEncoder.encode(key), new LettuceJsonPath(path),
						new LettuceJsonRangeArgs(start, stop)),
				(cmd)->cmd.jsonArrtrim(SafeEncoder.encode(key), new LettuceJsonPath(path),
						new LettuceJsonRangeArgs(start, stop)));
	}

	@Override
	public List<Long> jsonArrTrim(final byte[] key, final byte[] path, final int start, final int stop) {
		final CommandArguments args = CommandArguments.create(key, path).add(start).add(stop);
		return executeCommand(RedisCommand.JSON_ARRTRIM, args,
				(cmd)->cmd.jsonArrtrim(key, new LettuceJsonPath(path), new LettuceJsonRangeArgs(start, stop)),
				(cmd)->cmd.jsonArrtrim(key, new LettuceJsonPath(path), new LettuceJsonRangeArgs(start, stop)));
	}

	@Override
	public Long jsonClear(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_CLEAR, args, (cmd)->cmd.jsonClear(SafeEncoder.encode(key)),
				(cmd)->cmd.jsonClear(SafeEncoder.encode(key)));
	}

	@Override
	public Long jsonClear(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_CLEAR, args, (cmd)->cmd.jsonClear(key),
				(cmd)->cmd.jsonClear(key));
	}

	@Override
	public Long jsonClear(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_CLEAR, args,
				(cmd)->cmd.jsonClear(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				(cmd)->cmd.jsonClear(SafeEncoder.encode(key), new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonClear(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_CLEAR, args,
				(cmd)->cmd.jsonClear(key, new LettuceJsonPath(path)),
				(cmd)->cmd.jsonClear(key, new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonDebugMemory(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_DEBUG, RedisSubCommand.JSON_DEBUG_MEMORY, args);
	}

	@Override
	public Long jsonDebugMemory(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_DEBUG, RedisSubCommand.JSON_DEBUG_MEMORY, args);
	}

	@Override
	public List<Long> jsonDebugMemory(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(RedisCommand.JSON_DEBUG, RedisSubCommand.JSON_DEBUG_MEMORY, args);
	}

	@Override
	public List<Long> jsonDebugMemory(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(RedisCommand.JSON_DEBUG, RedisSubCommand.JSON_DEBUG_MEMORY, args);
	}

	@Override
	public Long jsonDel(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_DEL, args, (cmd)->cmd.jsonDel(SafeEncoder.encode(key)),
				(cmd)->cmd.jsonDel(SafeEncoder.encode(key)));
	}

	@Override
	public Long jsonDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_DEL, args, (cmd)->cmd.jsonDel(key),
				(cmd)->cmd.jsonDel(key));
	}

	@Override
	public Long jsonDel(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(RedisCommand.JSON_DEL, args,
				(cmd)->cmd.jsonDel(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				(cmd)->cmd.jsonDel(SafeEncoder.encode(key), new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonDel(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(RedisCommand.JSON_DEL, args, (cmd)->cmd.jsonDel(key, new LettuceJsonPath(path)),
				(cmd)->cmd.jsonDel(key, new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonForget(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_FORGET, args, (cmd)->cmd.jsonDel(SafeEncoder.encode(key)),
				(cmd)->cmd.jsonDel(SafeEncoder.encode(key)));
	}

	@Override
	public Long jsonForget(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_FORGET, args, (cmd)->cmd.jsonDel(key),
				(cmd)->cmd.jsonDel(key));
	}

	@Override
	public Long jsonForget(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_FORGET, args,
				(cmd)->cmd.jsonDel(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				(cmd)->cmd.jsonDel(SafeEncoder.encode(key), new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonForget(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_FORGET, args,
				(cmd)->cmd.jsonDel(key, new LettuceJsonPath(path)),
				(cmd)->cmd.jsonDel(key, new LettuceJsonPath(path)));
	}

	@Override
	public String jsonGet(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_GET, args, (cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key)),
				(cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key)), Converters.list0Converter());
	}

	@Override
	public byte[] jsonGet(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_GET, args, (cmd)->cmd.jsonGetRaw(key),
				(cmd)->cmd.jsonGetRaw(key), (v)->Validate.isEmpty(v) ? null : SafeEncoder.encode(v.get(0)));
	}

	@Override
	public String jsonGet(final String key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(RedisCommand.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key), new LettuceJsonGetArgs(argument)),
				(cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key), new LettuceJsonGetArgs(argument)),
				Converters.list0Converter());
	}

	@Override
	public byte[] jsonGet(final byte[] key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(RedisCommand.JSON_ARRINSERT, args,
				(cmd)->cmd.jsonGetRaw(key, new LettuceJsonGetArgs(argument)),
				(cmd)->cmd.jsonGetRaw(key, new LettuceJsonGetArgs(argument)),
				(v)->Validate.isEmpty(v) ? null : SafeEncoder.encode(v.get(0)));
	}

	@Override
	public List<String> jsonGet(final String key, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<String, LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return executeCommand(RedisCommand.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key), arrayConverter.convert(path)),
				(cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key), arrayConverter.convert(path)));
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<byte[], LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return executeCommand(RedisCommand.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(key, arrayConverter.convert(path)),
				(cmd)->cmd.jsonGetRaw(key, arrayConverter.convert(path)),
				Converters.stringListBinaryListConverter());
	}

	@Override
	public List<String> jsonGet(final String key, final JsonGetArgument argument, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<String, LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return executeCommand(RedisCommand.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key), new LettuceJsonGetArgs(argument),
						arrayConverter.convert(path)),
				(cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key), new LettuceJsonGetArgs(argument),
						arrayConverter.convert(path)));
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<byte[], LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return executeCommand(RedisCommand.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(key, new LettuceJsonGetArgs(argument), arrayConverter.convert(path)),
				(cmd)->cmd.jsonGetRaw(key, new LettuceJsonGetArgs(argument), arrayConverter.convert(path)),
				Converters.stringListBinaryListConverter());
	}

	@Override
	public Status jsonMerge(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(RedisCommand.JSON_MERGE, args,
				(cmd)->cmd.jsonMerge(SafeEncoder.encode(key), new LettuceJsonPath(path), value),
				(cmd)->cmd.jsonMerge(SafeEncoder.encode(key), new LettuceJsonPath(path), value),
				new OkStatusConverter());
	}

	@Override
	public Status jsonMerge(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(RedisCommand.JSON_MERGE, args,
				(cmd)->cmd.jsonMerge(key, new LettuceJsonPath(path), SafeEncoder.encode(value)),
				(cmd)->cmd.jsonMerge(key, new LettuceJsonPath(path), SafeEncoder.encode(value)),
				new OkStatusConverter());
	}

	@Override
	public List<String> jsonMGet(final String[] keys, final String path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return executeCommand(RedisCommand.JSON_MGET, args,
				(cmd)->cmd.jsonMGetRaw(new LettuceJsonPath(path), SafeEncoder.encode(keys)),
				(cmd)->cmd.jsonMGetRaw(new LettuceJsonPath(path), SafeEncoder.encode(keys)));
	}

	@Override
	public List<byte[]> jsonMGet(final byte[][] keys, final byte[] path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return executeCommand(RedisCommand.JSON_MGET, args,
				(cmd)->cmd.jsonMGetRaw(new LettuceJsonPath(path), keys),
				(cmd)->cmd.jsonMGetRaw(new LettuceJsonPath(path), keys),
				Converters.stringListBinaryListConverter());
	}

	@Override
	public Status jsonMSet(final KeyPathValue... data) {
		final CommandArguments args = CommandArguments.create(data);
		final JsonParser jsonParser = new DefaultJsonParser();
		final ArrayListConverter<KeyPathValue, JsonMsetArgs<byte[], byte[]>> arrayListConverter = new ArrayListConverter<>(
				(v)->new JsonMsetArgs<>(SafeEncoder.encode(v.getKey()), new LettuceJsonPath(v.getPath()),
						jsonParser.createJsonValue(v.getValue())));
		return executeCommand(RedisCommand.JSON_MSET, args, (cmd)->cmd.jsonMSet(arrayListConverter.convert(data)),
				(cmd)->cmd.jsonMSet(arrayListConverter.convert(data)), new OkStatusConverter());
	}

	@Override
	public List<Number> jsonNumIncrBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(RedisCommand.JSON_NUMINCRBY, args,
				(cmd)->cmd.jsonNumincrby(SafeEncoder.encode(key), new LettuceJsonPath(path), value),
				(cmd)->cmd.jsonNumincrby(SafeEncoder.encode(key), new LettuceJsonPath(path), value));
	}

	@Override
	public List<Number> jsonNumIncrBy(final byte[] key, final byte[] path, final Number value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(RedisCommand.JSON_NUMINCRBY, args,
				(cmd)->cmd.jsonNumincrby(key, new LettuceJsonPath(path), value),
				(cmd)->cmd.jsonNumincrby(key, new LettuceJsonPath(path), value));
	}

	@Override
	public List<Number> jsonNumMultBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(RedisCommand.JSON_NUMMULTBY, args);
	}

	@Override
	public List<Number> jsonNumMultBy(final byte[] key, final byte[] path, final Number value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(RedisCommand.JSON_NUMMULTBY, args);
	}

	@Override
	public List<List<String>> jsonObjKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final ByteArrayDeserializer byteArrayDeserializer = new DefaultByteArrayDeserializer();
		return executeCommand(RedisCommand.JSON_OBJKEYS, args, (cmd)->cmd.jsonObjkeys(SafeEncoder.encode(key)),
				(cmd)->cmd.jsonObjkeys(SafeEncoder.encode(key)), new ListConverter<>((v)->{
					try{
						return byteArrayDeserializer.deserialize(v);
					}catch(DeserializerException e){
						return null;
					}
				}));
	}

	@Override
	public List<List<byte[]>> jsonObjKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final ByteArrayDeserializer byteArrayDeserializer = new DefaultByteArrayDeserializer();
		return executeCommand(RedisCommand.JSON_OBJKEYS, args, (cmd)->cmd.jsonObjkeys(key),
				(cmd)->cmd.jsonObjkeys(key), new ListConverter<>((v)->{
					try{
						return byteArrayDeserializer.deserialize(v);
					}catch(DeserializerException e){
						return null;
					}
				}));
	}

	@Override
	public List<List<String>> jsonObjKeys(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		final ByteArrayDeserializer byteArrayDeserializer = new DefaultByteArrayDeserializer();
		return executeCommand(RedisCommand.JSON_OBJKEYS, args, (cmd)->cmd.jsonObjkeys(SafeEncoder.encode(key)),
				(cmd)->cmd.jsonObjkeys(SafeEncoder.encode(key), new LettuceJsonPath(path)), new ListConverter<>((v)->{
					try{
						return byteArrayDeserializer.deserialize(v);
					}catch(DeserializerException e){
						return null;
					}
				}));
	}

	@Override
	public List<List<byte[]>> jsonObjKeys(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		final ByteArrayDeserializer byteArrayDeserializer = new DefaultByteArrayDeserializer();
		return executeCommand(RedisCommand.JSON_OBJKEYS, args,
				(cmd)->cmd.jsonObjkeys(key, new LettuceJsonPath(path)),
				(cmd)->cmd.jsonObjkeys(key, new LettuceJsonPath(path)), new ListConverter<>((v)->{
					try{
						return byteArrayDeserializer.deserialize(v);
					}catch(DeserializerException e){
						return null;
					}
				}));
	}

	@Override
	public Long jsonObjLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_OBJLEN, args, (cmd)->cmd.jsonObjlen(SafeEncoder.encode(key)),
				(cmd)->cmd.jsonObjlen(SafeEncoder.encode(key)), Converters.list0Converter());
	}

	@Override
	public Long jsonObjLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_OBJLEN, args, (cmd)->cmd.jsonObjlen(key),
				(cmd)->cmd.jsonObjlen(key), Converters.list0Converter());
	}

	@Override
	public List<Long> jsonObjLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_OBJLEN, args,
				(cmd)->cmd.jsonObjlen(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				(cmd)->cmd.jsonObjlen(SafeEncoder.encode(key), new LettuceJsonPath(path)));
	}

	@Override
	public List<Long> jsonObjLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_OBJLEN, args,
				(cmd)->cmd.jsonObjlen(key, new LettuceJsonPath(path)),
				(cmd)->cmd.jsonObjlen(key, new LettuceJsonPath(path)));
	}

	@Override
	public List<Object> jsonResp(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_RESP, args);
	}

	@Override
	public List<Object> jsonResp(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_RESP, args);
	}

	@Override
	public List<Object> jsonResp(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_RESP, args);
	}

	@Override
	public List<Object> jsonResp(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_RESP, args);
	}

	@Override
	public Status jsonSet(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key, path);
		return jsonSet(SafeEncoder.encode(key), new LettuceJsonPath(path), value, args);
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, path);
		return jsonSet(key, new LettuceJsonPath(path), SafeEncoder.encode(value), args);
	}

	@Override
	public Status jsonSet(final String key, final String path, final String value, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key, path).add(nxXx);
		return jsonSet(SafeEncoder.encode(key), new LettuceJsonPath(path), value, nxXx, args);
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key, path).add(nxXx);
		return jsonSet(key, new LettuceJsonPath(path), SafeEncoder.encode(value), nxXx, args);
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.JSON_STRAPPEND, args,
				(cmd)->cmd.jsonStrappend(SafeEncoder.encode(key), value),
				(cmd)->cmd.jsonStrappend(SafeEncoder.encode(key), value));
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(RedisCommand.JSON_STRAPPEND, args,
				(cmd)->cmd.jsonStrappend(key, SafeEncoder.encode(value)),
				(cmd)->cmd.jsonStrappend(key, SafeEncoder.encode(value)));
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(RedisCommand.JSON_STRAPPEND, args,
				(cmd)->cmd.jsonStrappend(SafeEncoder.encode(key), new LettuceJsonPath(path), value),
				(cmd)->cmd.jsonStrappend(SafeEncoder.encode(key), new LettuceJsonPath(path), value));
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(RedisCommand.JSON_STRAPPEND, args,
				(cmd)->cmd.jsonStrappend(key, new LettuceJsonPath(path), SafeEncoder.encode(value)),
				(cmd)->cmd.jsonStrappend(key, new LettuceJsonPath(path), SafeEncoder.encode(value)));
	}

	@Override
	public Long jsonStrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_STRLEN, args, (cmd)->cmd.jsonStrlen(SafeEncoder.encode(key)),
				(cmd)->cmd.jsonStrlen(SafeEncoder.encode(key)), Converters.list0Converter());
	}

	@Override
	public Long jsonStrLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.JSON_STRLEN, args, (cmd)->cmd.jsonStrlen(key),
				(cmd)->cmd.jsonStrlen(key), Converters.list0Converter());
	}

	@Override
	public List<Long> jsonStrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_STRLEN, args,
				(cmd)->cmd.jsonStrlen(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				(cmd)->cmd.jsonStrlen(SafeEncoder.encode(key), new LettuceJsonPath(path)));
	}

	@Override
	public List<Long> jsonStrLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_STRLEN, args,
				(cmd)->cmd.jsonStrlen(key, new LettuceJsonPath(path)),
				(cmd)->cmd.jsonStrlen(key, new LettuceJsonPath(path)));
	}

	@Override
	public List<Status> jsonToggle(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_TOGGLE, args,
				(cmd)->cmd.jsonToggle(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				(cmd)->cmd.jsonToggle(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				new ListConverter<>(new OneStatusConverter()));
	}

	@Override
	public List<Status> jsonToggle(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(RedisCommand.JSON_TOGGLE, args,
				(cmd)->cmd.jsonToggle(key, new LettuceJsonPath(path)),
				(cmd)->cmd.jsonToggle(key, new LettuceJsonPath(path)),
				new ListConverter<>(new OneStatusConverter()));
	}

	@Override
	public JsonType jsonType(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonType(SafeEncoder.encode(key), args);
	}

	@Override
	public JsonType jsonType(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonType(key, args);
	}

	@Override
	public List<JsonType> jsonType(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return jsonType(SafeEncoder.encode(key), new LettuceJsonPath(path), args);
	}

	@Override
	public List<JsonType> jsonType(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return jsonType(key, new LettuceJsonPath(path), args);
	}

	private Status jsonSet(final byte[] key, final JsonPath path, final String value, final CommandArguments args) {
		return executeCommand(RedisCommand.JSON_SET, args, (cmd)->cmd.jsonSet(key, path, value),
				(cmd)->cmd.jsonSet(key, path, value), new OkStatusConverter());
	}

	private Status jsonSet(final byte[] key, final JsonPath path, final String value, final NxXx nxXx,
	                       final CommandArguments args) {
		return executeCommand(RedisCommand.JSON_SET, args,
				(cmd)->cmd.jsonSet(key, path, value, new LettuceJsonSetArgs(nxXx)),
				(cmd)->cmd.jsonSet(key, path, value, new LettuceJsonSetArgs(nxXx)), new OkStatusConverter());
	}

	private JsonType jsonType(final byte[] key, final CommandArguments args) {
		return executeCommand(RedisCommand.JSON_TYPE, args, (cmd)->cmd.jsonType(key), (cmd)->cmd.jsonType(key), (v)->{
			if(Validate.isEmpty(v)){
				return null;
			}else{
				final JsonTypeConverter jsonTypeConverter = new JsonTypeConverter();
				return jsonTypeConverter.convert(v.get(0));
			}
		});
	}

	private List<JsonType> jsonType(final byte[] key, final JsonPath path, final CommandArguments args) {
		return executeCommand(RedisCommand.JSON_TYPE, args, (cmd)->cmd.jsonType(key, path),
				(cmd)->cmd.jsonType(key, path), new ListConverter<>(new JsonTypeConverter()));
	}

}
