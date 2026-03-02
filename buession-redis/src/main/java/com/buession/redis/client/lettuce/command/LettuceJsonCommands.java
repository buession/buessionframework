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
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.JsonType;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.JsonCommands;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.JsonGetArgument;
import com.buession.redis.core.command.args.JsonKeyPathValueArgument;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.params.JsonGetArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.response.JsonTypeConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.lettuce.LettuceJsonPath;
import com.buession.redis.core.internal.lettuce.LettuceJsonRangeArgs;
import com.buession.redis.core.internal.lettuce.LettuceJsonSetArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.RedisCommands;
import io.lettuce.core.json.DefaultJsonParser;
import io.lettuce.core.json.JsonParser;
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
		return jsonArrAppend(args, (cmd)->cmd.jsonArrappend(SafeEncoder.encode(key), values));
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return jsonArrAppend(args, (cmd)->cmd.jsonArrappend(key, SafeEncoder.encode(values)));
	}

	@Override
	public List<Long> jsonArrAppend(final String key, final String path, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(values);
		return jsonArrAppend(args,
				(cmd)->cmd.jsonArrappend(SafeEncoder.encode(key), new LettuceJsonPath(path), values));
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[] path, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(values);
		return jsonArrAppend(args,
				(cmd)->cmd.jsonArrappend(key, new LettuceJsonPath(path), SafeEncoder.encode(values)));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return jsonArrIndex(args, (cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value));
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return jsonArrIndex(args, (cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value)));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start);
		return jsonArrIndex(args, (cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value,
				new LettuceJsonRangeArgs(start)));
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start);
		return jsonArrIndex(args, (cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value),
				new LettuceJsonRangeArgs(start)));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start,
								   final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start).add(stop);
		return jsonArrIndex(args, (cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value,
				new LettuceJsonRangeArgs(start, stop)));
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start,
								   final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start).add(stop);
		return jsonArrIndex(args, (cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value),
				new LettuceJsonRangeArgs(start, stop)));
	}

	@Override
	public List<Long> jsonArrInsert(final String key, final String path, final int index, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(index).add(values);
		return executeCommand(Command.JSON_ARRINSERT, args,
				(cmd)->cmd.jsonArrinsert(SafeEncoder.encode(key), new LettuceJsonPath(path), index, values), (v)->v);
	}

	@Override
	public List<Long> jsonArrInsert(final byte[] key, final byte[] path, final int index, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(index).add(values);
		return executeCommand(Command.JSON_ARRINSERT, args,
				(cmd)->cmd.jsonArrinsert(key, new LettuceJsonPath(path), index, SafeEncoder.encode(values)), (v)->v);
	}

	@Override
	public Long jsonArrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final List<Long> result = jsonArrLen(args, (cmd)->cmd.jsonArrlen(SafeEncoder.encode(key)));
		return Validate.isNotEmpty(result) ? result.get(0) : null;
	}

	@Override
	public Long jsonArrLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final List<Long> result = jsonArrLen(args, (cmd)->cmd.jsonArrlen(key));
		return Validate.isNotEmpty(result) ? result.get(0) : null;
	}

	@Override
	public List<Long> jsonArrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonArrLen(args, (cmd)->cmd.jsonArrlen(SafeEncoder.encode(key), new LettuceJsonPath(path)));
	}

	@Override
	public List<Long> jsonArrLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonArrLen(args, (cmd)->cmd.jsonArrlen(key, new LettuceJsonPath(path)));
	}

	@Override
	public Object jsonArrPop(final String key) {
		return jsonArrPop(SafeEncoder.encode(key));
	}

	@Override
	public Object jsonArrPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_ARRPOP, args, (cmd)->cmd.jsonArrpopRaw(key), Converters.list0Converter());
	}

	@Override
	public List<Object> jsonArrPop(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_ARRPOP, args,
				(cmd)->cmd.jsonArrpopRaw(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				new ListConverter<>((v)->v));
	}

	@Override
	public List<Object> jsonArrPop(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_ARRPOP, args, (cmd)->cmd.jsonArrpopRaw(key, new LettuceJsonPath(path)),
				new ListConverter<>((v)->v));
	}

	@Override
	public List<Long> jsonArrTrim(final String key, final String path, final int start, final int stop) {
		return jsonArrTrim(SafeEncoder.encode(key), SafeEncoder.encode(path), start, stop);
	}

	@Override
	public List<Long> jsonArrTrim(final byte[] key, final byte[] path, final int start, final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(start).add(stop);
		return executeCommand(Command.JSON_ARRTRIM, args,
				(cmd)->cmd.jsonArrtrim(key, new LettuceJsonPath(path), new LettuceJsonRangeArgs(start, stop)), (v)->v);
	}

	@Override
	public Long jsonArrClear(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_CLEAR, args);
	}

	@Override
	public Long jsonArrClear(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_CLEAR, args);
	}

	@Override
	public List<Long> jsonArrClear(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_CLEAR, args);
	}

	@Override
	public List<Long> jsonArrClear(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_CLEAR, args);
	}

	@Override
	public Long jsonDebugMemory(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_DEBUG, SubCommand.JSON_DEBUG_MEMORY, args);
	}

	@Override
	public Long jsonDebugMemory(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_DEBUG, SubCommand.JSON_DEBUG_MEMORY, args);
	}

	@Override
	public List<Long> jsonDebugMemory(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_DEBUG, SubCommand.JSON_DEBUG_MEMORY, args);
	}

	@Override
	public List<Long> jsonDebugMemory(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_DEBUG, SubCommand.JSON_DEBUG_MEMORY, args);
	}

	@Override
	public Long jsonDel(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonDel(args, (cmd)->cmd.jsonDel(SafeEncoder.encode(key)));
	}

	@Override
	public Long jsonDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonDel(args, (cmd)->cmd.jsonDel(key));
	}

	@Override
	public Long jsonDel(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonDel(args, (cmd)->cmd.jsonDel(SafeEncoder.encode(key), new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonDel(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonDel(args, (cmd)->cmd.jsonDel(key, new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonForget(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonForget(args, (cmd)->cmd.jsonDel(SafeEncoder.encode(key)));
	}

	@Override
	public Long jsonForget(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonForget(args, (cmd)->cmd.jsonDel(key));
	}

	@Override
	public Long jsonForget(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonForget(args, (cmd)->cmd.jsonDel(SafeEncoder.encode(key), new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonForget(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonForget(args, (cmd)->cmd.jsonDel(key, new LettuceJsonPath(path)));
	}

	@Override
	public String jsonGet(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_GET, args, (cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key)),
				Converters.list0Converter());
	}

	@Override
	public byte[] jsonGet(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_GET, args, (cmd)->cmd.jsonGetRaw(key),
				(v)->Validate.isEmpty(v) ? null : SafeEncoder.encode(v.get(0)));
	}

	@Override
	public String jsonGet(final String key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final JsonGetArgumentConverter jsonGetArgumentConverter = new JsonGetArgumentConverter();
		return executeCommand(Command.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key), jsonGetArgumentConverter.convert(argument)),
				Converters.list0Converter());
	}

	@Override
	public byte[] jsonGet(final byte[] key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final JsonGetArgumentConverter jsonGetArgumentConverter = new JsonGetArgumentConverter();
		return executeCommand(Command.JSON_ARRINSERT, args,
				(cmd)->cmd.jsonGetRaw(key, jsonGetArgumentConverter.convert(argument)),
				(v)->Validate.isEmpty(v) ? null : SafeEncoder.encode(v.get(0)));
	}

	@Override
	public List<String> jsonGet(final String key, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<String, LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return executeCommand(Command.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key), arrayConverter.convert(path)), (v)->v);
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<byte[], LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return executeCommand(Command.JSON_GET, args, (cmd)->cmd.jsonGetRaw(key, arrayConverter.convert(path)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<String> jsonGet(final String key, final JsonGetArgument argument, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<String, LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		final JsonGetArgumentConverter jsonGetArgumentConverter = new JsonGetArgumentConverter();
		return executeCommand(Command.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key), jsonGetArgumentConverter.convert(argument),
						arrayConverter.convert(path)), (v)->v);
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<byte[], LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		final JsonGetArgumentConverter jsonGetArgumentConverter = new JsonGetArgumentConverter();
		return executeCommand(Command.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(key, jsonGetArgumentConverter.convert(argument), arrayConverter.convert(path)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public Status jsonMerge(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_MERGE, args,
				(cmd)->cmd.jsonMerge(SafeEncoder.encode(key), new LettuceJsonPath(path), value),
				new OkStatusConverter());
	}

	@Override
	public Status jsonMerge(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_MERGE, args,
				(cmd)->cmd.jsonMerge(key, new LettuceJsonPath(path), SafeEncoder.encode(value)),
				new OkStatusConverter());
	}

	@Override
	public List<String> jsonMGet(final String[] keys, final String path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return executeCommand(Command.JSON_MGET, args,
				(cmd)->cmd.jsonMGetRaw(new LettuceJsonPath(path), SafeEncoder.encode(keys)), (v)->v);
	}

	@Override
	public List<byte[]> jsonMGet(final byte[][] keys, final byte[] path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return executeCommand(Command.JSON_MGET, args, (cmd)->cmd.jsonMGetRaw(new LettuceJsonPath(path), keys),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public Status jsonMSet(final JsonKeyPathValueArgument.StringJsonKeyPathValueArgument... data) {
		final CommandArguments args = CommandArguments.create(data);
		final JsonParser jsonParser = new DefaultJsonParser();
		final ArrayListConverter<JsonKeyPathValueArgument.StringJsonKeyPathValueArgument, JsonMsetArgs<byte[], byte[]>> arrayListConverter = new ArrayListConverter<>(
				(v)->new JsonMsetArgs<>(SafeEncoder.encode(v.getKey()), new LettuceJsonPath(v.getPath()),
						jsonParser.createJsonValue(v.getValue())));
		return executeCommand(Command.JSON_MSET, args, (cmd)->cmd.jsonMSet(arrayListConverter.convert(data)),
				new OkStatusConverter());
	}

	@Override
	public Status jsonMSet(final JsonKeyPathValueArgument.BinaryJsonKeyPathValueArgument... data) {
		final CommandArguments args = CommandArguments.create(data);
		final JsonParser jsonParser = new DefaultJsonParser();
		final ArrayListConverter<JsonKeyPathValueArgument.BinaryJsonKeyPathValueArgument, JsonMsetArgs<byte[], byte[]>> arrayListConverter = new ArrayListConverter<>(
				(v)->new JsonMsetArgs<>(v.getKey(), new LettuceJsonPath(v.getPath()),
						jsonParser.createJsonValue(SafeEncoder.encode(v.getValue()))));
		return executeCommand(Command.JSON_MSET, args, (cmd)->cmd.jsonMSet(arrayListConverter.convert(data)),
				new OkStatusConverter());
	}

	@Override
	public List<Number> jsonNumIncrBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_NUMINCRBY, args,
				(cmd)->cmd.jsonNumincrby(SafeEncoder.encode(key), new LettuceJsonPath(path), value), (v)->v);
	}

	@Override
	public List<Number> jsonNumIncrBy(final byte[] key, final byte[] path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_NUMINCRBY, args,
				(cmd)->cmd.jsonNumincrby(key, new LettuceJsonPath(path), value), (v)->v);
	}

	@Override
	public List<Number> jsonNumMultBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_NUMMULTBY, args);
	}

	@Override
	public List<Number> jsonNumMultBy(final byte[] key, final byte[] path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_NUMMULTBY, args);
	}

	@Override
	public List<List<String>> jsonObjKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_OBJKEYS, args);
	}

	@Override
	public List<List<byte[]>> jsonObjKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_OBJKEYS, args);
	}

	@Override
	public List<List<String>> jsonObjKeys(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_OBJKEYS, args);
	}

	@Override
	public List<List<byte[]>> jsonObjKeys(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_OBJKEYS, args);
	}

	@Override
	public Long jsonObjLen(final String key) {
		return jsonObjLen(SafeEncoder.encode(key));
	}

	@Override
	public Long jsonObjLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_OBJLEN, args, (cmd)->cmd.jsonObjlen(key), Converters.list0Converter());
	}

	@Override
	public List<Long> jsonObjLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_OBJLEN, args, (cmd)->cmd.jsonObjlen(SafeEncoder.encode(key),
				new LettuceJsonPath(path)), (v)->v);
	}

	@Override
	public List<Long> jsonObjLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_OBJLEN, args, (cmd)->cmd.jsonObjlen(key, new LettuceJsonPath(path)), (v)->v);
	}

	@Override
	public List<String> jsonResp(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_RESP, args);
	}

	@Override
	public List<byte[]> jsonResp(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_RESP, args);
	}

	@Override
	public List<String> jsonResp(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_RESP, args);
	}

	@Override
	public List<byte[]> jsonResp(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_RESP, args);
	}

	@Override
	public Status jsonSet(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonSet(args, (cmd)->cmd.jsonSet(SafeEncoder.encode(key), new LettuceJsonPath(path), value));
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonSet(args, (cmd)->cmd.jsonSet(key, new LettuceJsonPath(path), SafeEncoder.encode(value)));
	}

	@Override
	public Status jsonSet(final String key, final String path, final String value, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(nxXx);
		return jsonSet(args, (cmd)->cmd.jsonSet(SafeEncoder.encode(key), new LettuceJsonPath(path), value,
				new LettuceJsonSetArgs(nxXx)));
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(nxXx);
		return jsonSet(args, (cmd)->cmd.jsonSet(key, new LettuceJsonPath(path), SafeEncoder.encode(value),
				new LettuceJsonSetArgs(nxXx)));
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return jsonStrAppend(args, (cmd)->cmd.jsonStrappend(SafeEncoder.encode(key), value));
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return jsonStrAppend(args, (cmd)->cmd.jsonStrappend(key, SafeEncoder.encode(value)));
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return jsonStrAppend(args, (cmd)->cmd.jsonStrappend(SafeEncoder.encode(key), new LettuceJsonPath(path), value));
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return jsonStrAppend(args, (cmd)->cmd.jsonStrappend(key, new LettuceJsonPath(path), SafeEncoder.encode(value)));
	}

	@Override
	public Long jsonStrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_STRLEN, args, (cmd)->cmd.jsonStrlen(SafeEncoder.encode(key)),
				Converters.list0Converter());
	}

	@Override
	public Long jsonStrLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_STRLEN, args, (cmd)->cmd.jsonStrlen(key),
				Converters.list0Converter());
	}

	@Override
	public List<Long> jsonStrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_STRLEN, args,
				(cmd)->cmd.jsonStrlen(SafeEncoder.encode(key), new LettuceJsonPath(path)), (v)->v);
	}

	@Override
	public List<Long> jsonStrLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_STRLEN, args, (cmd)->cmd.jsonStrlen(key, new LettuceJsonPath(path)), (v)->v);
	}

	@Override
	public List<Status> jsonToggle(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_TOGGLE, args,
				(cmd)->cmd.jsonToggle(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				new ListConverter<>(new OneStatusConverter()));
	}

	@Override
	public List<Status> jsonToggle(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_TOGGLE, args, (cmd)->cmd.jsonToggle(key, new LettuceJsonPath(path)),
				new ListConverter<>(new OneStatusConverter()));
	}

	@Override
	public JsonType jsonType(final String key) {
		return jsonType(SafeEncoder.encode(key));
	}

	@Override
	public JsonType jsonType(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_TYPE, args, (cmd)->{
			final List<io.lettuce.core.json.JsonType> result = cmd.jsonType(key);
			return Validate.isEmpty(result) ? null : result.get(0);
		}, new JsonTypeConverter());
	}

	@Override
	public List<JsonType> jsonType(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_TYPE, args,
				(cmd)->cmd.jsonType(SafeEncoder.encode(key), new LettuceJsonPath(path)),
				new ListConverter<>(new JsonTypeConverter()));
	}

	@Override
	public List<JsonType> jsonType(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_TYPE, args, (cmd)->cmd.jsonType(key, new LettuceJsonPath(path)),
				new ListConverter<>(new JsonTypeConverter()));
	}

	private List<Long> jsonArrAppend(final CommandArguments args,
									 final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, List<Long>> executor) {
		return executeCommand(Command.JSON_ARRAPPEND, args, executor, (v)->v);
	}

	private List<Long> jsonArrIndex(final CommandArguments args,
									final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, List<Long>> executor) {
		return executeCommand(Command.JSON_ARRINDEX, args, executor, (v)->v);
	}

	private List<Long> jsonArrLen(final CommandArguments args,
								  final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, List<Long>> executor) {
		return executeCommand(Command.JSON_ARRINSERT, args, executor, (v)->v);
	}

	private Long jsonDel(final CommandArguments args,
						 final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, Long> executor) {
		return executeCommand(Command.JSON_DEL, args, executor, (v)->v);
	}

	private Long jsonForget(final CommandArguments args,
							final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, Long> executor) {
		return executeCommand(Command.JSON_FORGET, args, executor, (v)->v);
	}

	private Status jsonSet(final CommandArguments args,
						   final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, String> executor) {
		return executeCommand(Command.JSON_SET, args, executor, new OkStatusConverter());
	}

	private List<Long> jsonStrAppend(final CommandArguments args,
									 final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, List<Long>> executor) {
		return executeCommand(Command.JSON_STRAPPEND, args, executor, (v)->v);
	}

}
