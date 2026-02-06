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
package com.buession.redis.client.lettuce.operations;

import com.buession.core.converter.ArrayConverter;
import com.buession.core.converter.ArrayListConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.JsonOperations;
import com.buession.redis.core.JsonType;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.JsonGetArgument;
import com.buession.redis.core.command.args.JsonKeyPathValueArgument;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.JsonTypeConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.lettuce.CompositeArgumentUtils;
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
 * Lettuce JSON 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceJsonOperations extends AbstractLettuceRedisOperations implements JsonOperations {

	public LettuceJsonOperations(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public List<Long> jsonArrAppend(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return jsonArrAppend((cmd)->cmd.jsonArrappend(SafeEncoder.encode(key), values), args);
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return jsonArrAppend((cmd)->cmd.jsonArrappend(key, SafeEncoder.encode(values)), args);
	}

	@Override
	public List<Long> jsonArrAppend(final String key, final String path, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(values);
		return jsonArrAppend((cmd)->cmd.jsonArrappend(SafeEncoder.encode(key), new LettuceJsonPath(path), values),
				args);
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[] path, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(values);
		return jsonArrAppend((cmd)->cmd.jsonArrappend(key, new LettuceJsonPath(path), SafeEncoder.encode(values)),
				args);
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return jsonArrIndex((cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value), args);
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return jsonArrIndex((cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value)), args);
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start);
		return jsonArrIndex((cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value,
				new LettuceJsonRangeArgs(start)), args);
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start);
		return jsonArrIndex((cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value),
				new LettuceJsonRangeArgs(start)), args);
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start,
								   final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start).add(stop);
		return jsonArrIndex((cmd)->cmd.jsonArrindex(SafeEncoder.encode(key), new LettuceJsonPath(path), value,
				new LettuceJsonRangeArgs(start, stop)), args);
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start,
								   final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start).add(stop);
		return jsonArrIndex((cmd)->cmd.jsonArrindex(key, new LettuceJsonPath(path), SafeEncoder.encode(value),
				new LettuceJsonRangeArgs(start, stop)), args);
	}

	@Override
	public List<Long> jsonArrInsert(final String key, final String path, final int index, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(index).add(values);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_ARRINSERT)
				.executor((cmd)->cmd.jsonArrinsert(SafeEncoder.encode(key), new LettuceJsonPath(path), index, values))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> jsonArrInsert(final byte[] key, final byte[] path, final int index, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(index).add(values);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_ARRINSERT)
				.executor((cmd)->cmd.jsonArrinsert(key, new LettuceJsonPath(path), index, SafeEncoder.encode(values)))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonArrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final List<Long> result = jsonArrLen((cmd)->cmd.jsonArrlen(SafeEncoder.encode(key)), args);
		return Validate.isNotEmpty(result) ? result.get(0) : null;
	}

	@Override
	public Long jsonArrLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final List<Long> result = jsonArrLen((cmd)->cmd.jsonArrlen(key), args);
		return Validate.isNotEmpty(result) ? result.get(0) : null;
	}

	@Override
	public List<Long> jsonArrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonArrLen((cmd)->cmd.jsonArrlen(SafeEncoder.encode(key), new LettuceJsonPath(path)), args);
	}

	@Override
	public List<Long> jsonArrLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonArrLen((cmd)->cmd.jsonArrlen(key, new LettuceJsonPath(path)), args);
	}

	@Override
	public Object jsonArrPop(final String key) {
		return jsonArrPop(SafeEncoder.encode(key));
	}

	@Override
	public Object jsonArrPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<String>, Object>newBuilder(client, Command.JSON_ARRPOP)
				.executor((cmd)->cmd.jsonArrpopRaw(key)).arguments(args)
				.converter((v)->Validate.isEmpty(v) ? null : v.get(0)).run();
	}

	@Override
	public List<Object> jsonArrPop(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<String>, List<Object>>newBuilder(client, Command.JSON_ARRPOP)
				.executor((cmd)->cmd.jsonArrpopRaw(SafeEncoder.encode(key), new LettuceJsonPath(path))).arguments(args)
				.converter(new ListConverter<>((v)->v)).run();
	}

	@Override
	public List<Object> jsonArrPop(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<String>, List<Object>>newBuilder(client, Command.JSON_ARRPOP)
				.executor((cmd)->cmd.jsonArrpopRaw(key, new LettuceJsonPath(path))).arguments(args)
				.converter(new ListConverter<>((v)->v)).run();
	}

	@Override
	public List<Long> jsonArrTrim(final String key, final String path, final int start, final int stop) {
		return jsonArrTrim(SafeEncoder.encode(key), SafeEncoder.encode(path), start, stop);
	}

	@Override
	public List<Long> jsonArrTrim(final byte[] key, final byte[] path, final int start, final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(start).add(stop);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_ARRTRIM)
				.executor((cmd)->cmd.jsonArrtrim(key, new LettuceJsonPath(path), new LettuceJsonRangeArgs(start, stop)))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonArrClear(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_CLEAR).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Long jsonArrClear(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_CLEAR).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Long> jsonArrClear(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_CLEAR).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Long> jsonArrClear(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_CLEAR).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Long jsonDebugMemory(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_DEBUG, SubCommand.JSON_DEBUG_MEMORY)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonDebugMemory(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_DEBUG, SubCommand.JSON_DEBUG_MEMORY)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> jsonDebugMemory(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_DEBUG,
				SubCommand.JSON_DEBUG_MEMORY).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> jsonDebugMemory(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_DEBUG,
				SubCommand.JSON_DEBUG_MEMORY).arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonDel(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonDel((cmd)->cmd.jsonDel(SafeEncoder.encode(key)), args);
	}

	@Override
	public Long jsonDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonDel((cmd)->cmd.jsonDel(key), args);
	}

	@Override
	public Long jsonDel(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonDel((cmd)->cmd.jsonDel(SafeEncoder.encode(key), new LettuceJsonPath(path)), args);
	}

	@Override
	public Long jsonDel(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonDel((cmd)->cmd.jsonDel(key, new LettuceJsonPath(path)), args);
	}

	@Override
	public Long jsonForget(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonForget((cmd)->cmd.jsonDel(SafeEncoder.encode(key)), args);
	}

	@Override
	public Long jsonForget(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonForget((cmd)->cmd.jsonDel(key), args);
	}

	@Override
	public Long jsonForget(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonForget((cmd)->cmd.jsonDel(SafeEncoder.encode(key), new LettuceJsonPath(path)), args);
	}

	@Override
	public Long jsonForget(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonForget((cmd)->cmd.jsonDel(key, new LettuceJsonPath(path)), args);
	}

	@Override
	public String jsonGet(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonStringGet((cmd)->{
			final List<String> result = cmd.jsonGetRaw(SafeEncoder.encode(key));
			return Validate.isNotEmpty(result) ? result.get(0) : null;
		}, args);
	}

	@Override
	public byte[] jsonGet(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonBinaryGet((cmd)->{
			final List<String> result = cmd.jsonGetRaw(key);
			return Validate.isNotEmpty(result) ? SafeEncoder.encode(result.get(0)) : null;
		}, args);
	}

	@Override
	public String jsonGet(final String key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return jsonStringGet((cmd)->{
			final List<String> result = cmd.jsonGetRaw(SafeEncoder.encode(key),
					CompositeArgumentUtils.jsonGetArgs(argument));
			return Validate.isNotEmpty(result) ? result.get(0) : null;
		}, args);
	}

	@Override
	public byte[] jsonGet(final byte[] key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return jsonBinaryGet((cmd)->{
			final List<String> result = cmd.jsonGetRaw(key, CompositeArgumentUtils.jsonGetArgs(argument));
			return Validate.isNotEmpty(result) ? SafeEncoder.encode(result.get(0)) : null;
		}, args);
	}

	@Override
	public List<String> jsonGet(final String key, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<String, LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return jsonStringListGet((cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key), arrayConverter.convert(path)), args);
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<byte[], LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return jsonBinaryListGet((cmd)->cmd.jsonGetRaw(key, arrayConverter.convert(path)), args);
	}

	@Override
	public List<String> jsonGet(final String key, final JsonGetArgument argument, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<String, LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return jsonStringListGet(
				(cmd)->cmd.jsonGetRaw(SafeEncoder.encode(key), CompositeArgumentUtils.jsonGetArgs(argument),
						arrayConverter.convert(path)), args);
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<byte[], LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return jsonBinaryListGet(
				(cmd)->cmd.jsonGetRaw(key, CompositeArgumentUtils.jsonGetArgs(argument), arrayConverter.convert(path)),
				args);
	}

	@Override
	public Status jsonMerge(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.JSON_MERGE)
				.executor((cmd)->cmd.jsonMerge(SafeEncoder.encode(key), new LettuceJsonPath(path), value))
				.arguments(args).converter(new OkStatusConverter()).run();
	}

	@Override
	public Status jsonMerge(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.JSON_MERGE)
				.executor((cmd)->cmd.jsonMerge(key, new LettuceJsonPath(path), SafeEncoder.encode(value)))
				.arguments(args).converter(new OkStatusConverter()).run();
	}

	@Override
	public List<String> jsonMGet(final String[] keys, final String path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return LettuceCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.JSON_MGET)
				.executor((cmd)->cmd.jsonMGetRaw(new LettuceJsonPath(path), SafeEncoder.encode(keys))).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<byte[]> jsonMGet(final byte[][] keys, final byte[] path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return LettuceCommandBuilder.<List<String>, List<byte[]>>newBuilder(client, Command.JSON_MGET)
				.executor((cmd)->cmd.jsonMGetRaw(new LettuceJsonPath(path), keys)).arguments(args)
				.converter(Converters.stringListToBinaryListConverter()).run();
	}

	@Override
	public Status jsonMSet(final JsonKeyPathValueArgument.StringJsonKeyPathValueArgument... data) {
		final CommandArguments args = CommandArguments.create(data);
		final JsonParser jsonParser = new DefaultJsonParser();
		final ArrayListConverter<JsonKeyPathValueArgument.StringJsonKeyPathValueArgument, JsonMsetArgs<byte[], byte[]>> arrayListConverter = new ArrayListConverter<>(
				(v)->new JsonMsetArgs<>(SafeEncoder.encode(v.getKey()), new LettuceJsonPath(v.getPath()),
						jsonParser.createJsonValue(v.getValue())));
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.JSON_MSET)
				.executor((cmd)->cmd.jsonMSet(arrayListConverter.convert(data))).arguments(args)
				.converter(new OkStatusConverter()).run();
	}

	@Override
	public Status jsonMSet(final JsonKeyPathValueArgument.BinaryJsonKeyPathValueArgument... data) {
		final CommandArguments args = CommandArguments.create(data);
		final JsonParser jsonParser = new DefaultJsonParser();
		final ArrayListConverter<JsonKeyPathValueArgument.BinaryJsonKeyPathValueArgument, JsonMsetArgs<byte[], byte[]>> arrayListConverter = new ArrayListConverter<>(
				(v)->new JsonMsetArgs<>(v.getKey(), new LettuceJsonPath(v.getPath()),
						jsonParser.createJsonValue(SafeEncoder.encode(v.getValue()))));
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.JSON_MSET)
				.executor((cmd)->cmd.jsonMSet(arrayListConverter.convert(data))).arguments(args)
				.converter(new OkStatusConverter()).run();
	}

	@Override
	public List<Number> jsonNumIncrBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return LettuceCommandBuilder.<List<Number>, List<Number>>newBuilder(client, Command.JSON_NUMINCRBY)
				.executor((cmd)->cmd.jsonNumincrby(SafeEncoder.encode(key), new LettuceJsonPath(path), value))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Number> jsonNumIncrBy(final byte[] key, final byte[] path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return LettuceCommandBuilder.<List<Number>, List<Number>>newBuilder(client, Command.JSON_NUMMULTBY)
				.executor((cmd)->cmd.jsonNumincrby(key, new LettuceJsonPath(path), value)).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Number> jsonNumMultBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return LettuceCommandBuilder.<List<Number>, List<Number>>newBuilder(client, Command.JSON_NUMMULTBY)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Number> jsonNumMultBy(final byte[] key, final byte[] path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return LettuceCommandBuilder.<List<Number>, List<Number>>newBuilder(client, Command.JSON_NUMINCRBY)
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<List<String>> jsonObjKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<List<String>>, List<List<String>>>newBuilder(client, Command.JSON_OBJKEYS)
				//.executor((cmd)->cmd.jsonObjkeys(key, JedisPath.ROOT_PATH))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<List<byte[]>> jsonObjKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<List<byte[]>>, List<List<byte[]>>>newBuilder(client, Command.JSON_OBJKEYS)
				//.executor((cmd)->cmd.jsonObjkeys(key, JedisPath.ROOT_PATH))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<List<String>> jsonObjKeys(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<List<String>>, List<List<String>>>newBuilder(client, Command.JSON_OBJKEYS)
				//.executor((cmd)->cmd.jsonObjkeys(key, JedisPath.ROOT_PATH))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<List<byte[]>> jsonObjKeys(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<List<byte[]>>, List<List<byte[]>>>newBuilder(client, Command.JSON_OBJKEYS)
				//.executor((cmd)->cmd.jsonObjkeys(key, JedisPath.ROOT_PATH))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonObjLen(final String key) {
		return jsonObjLen(SafeEncoder.encode(key));
	}

	@Override
	public Long jsonObjLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<Long>, Long>newBuilder(client, Command.JSON_OBJLEN)
				.executor((cmd)->cmd.jsonObjlen(key)).arguments(args).converter((v)->v == null ? null : v.get(0)).run();
	}

	@Override
	public List<Long> jsonObjLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_OBJLEN)
				.executor((cmd)->cmd.jsonObjlen(SafeEncoder.encode(key), new LettuceJsonPath(path))).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Long> jsonObjLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_OBJLEN)
				.executor((cmd)->cmd.jsonObjlen(key, new LettuceJsonPath(path))).arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public List<String> jsonResp(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.JSON_RESP).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<byte[]> jsonResp(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.JSON_RESP).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<String> jsonResp(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.JSON_RESP).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<byte[]> jsonResp(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.JSON_RESP).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Status jsonSet(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonSet((cmd)->cmd.jsonSet(SafeEncoder.encode(key), new LettuceJsonPath(path), value), args);
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return jsonSet((cmd)->cmd.jsonSet(key, new LettuceJsonPath(path), SafeEncoder.encode(value)), args);
	}

	@Override
	public Status jsonSet(final String key, final String path, final String value, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(nxXx);
		return jsonSet((cmd)->cmd.jsonSet(SafeEncoder.encode(key), new LettuceJsonPath(path), value,
				new LettuceJsonSetArgs(nxXx)), args);
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(nxXx);
		return jsonSet((cmd)->cmd.jsonSet(key, new LettuceJsonPath(path), SafeEncoder.encode(value),
				new LettuceJsonSetArgs(nxXx)), args);
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return jsonStrAppend((cmd)->cmd.jsonStrappend(SafeEncoder.encode(key), value), args);
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return jsonStrAppend((cmd)->cmd.jsonStrappend(key, SafeEncoder.encode(value)), args);
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return jsonStrAppend((cmd)->cmd.jsonStrappend(SafeEncoder.encode(key), new LettuceJsonPath(path), value), args);
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return jsonStrAppend((cmd)->cmd.jsonStrappend(key, new LettuceJsonPath(path), SafeEncoder.encode(value)), args);
	}

	@Override
	public Long jsonStrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<Long>, Long>newBuilder(client, Command.JSON_STRLEN)
				.executor((cmd)->cmd.jsonStrlen(SafeEncoder.encode(key))).arguments(args)
				.converter((v)->v == null ? null : v.get(0)).run();
	}

	@Override
	public Long jsonStrLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<Long>, Long>newBuilder(client, Command.JSON_STRLEN)
				.executor((cmd)->cmd.jsonStrlen(key)).arguments(args).converter((v)->v == null ? null : v.get(0)).run();
	}

	@Override
	public List<Long> jsonStrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_STRLEN)
				.executor((cmd)->cmd.jsonStrlen(SafeEncoder.encode(key), new LettuceJsonPath(path))).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Long> jsonStrLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_STRLEN)
				.executor((cmd)->cmd.jsonStrlen(key, new LettuceJsonPath(path))).arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public List<Status> jsonToggle(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<Long>, List<Status>>newBuilder(client, Command.JSON_TOGGLE)
				.executor((cmd)->cmd.jsonToggle(SafeEncoder.encode(key), new LettuceJsonPath(path))).arguments(args)
				.converter(new ListConverter<>(new OneStatusConverter())).run();
	}

	@Override
	public List<Status> jsonToggle(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<Long>, List<Status>>newBuilder(client, Command.JSON_TOGGLE)
				.executor((cmd)->cmd.jsonToggle(key, new LettuceJsonPath(path))).arguments(args)
				.converter(new ListConverter<>(new OneStatusConverter())).run();
	}

	@Override
	public JsonType jsonType(final String key) {
		return jsonType(SafeEncoder.encode(key));
	}

	@Override
	public JsonType jsonType(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<io.lettuce.core.json.JsonType, JsonType>newBuilder(client, Command.JSON_TYPE)
				.executor((cmd)->{
					final List<io.lettuce.core.json.JsonType> result = cmd.jsonType(key);
					return Validate.isEmpty(result) ? null : result.get(0);
				}).arguments(args).converter(new JsonTypeConverter()).run();
	}

	@Override
	public List<JsonType> jsonType(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<io.lettuce.core.json.JsonType>, List<JsonType>>newBuilder(client,
						Command.JSON_TYPE).executor((cmd)->cmd.jsonType(SafeEncoder.encode(key), new LettuceJsonPath(path)))
				.arguments(args).converter(new ListConverter<>(new JsonTypeConverter())).run();
	}

	@Override
	public List<JsonType> jsonType(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return LettuceCommandBuilder.<List<io.lettuce.core.json.JsonType>, List<JsonType>>newBuilder(client,
						Command.JSON_TYPE).executor((cmd)->cmd.jsonType(key, new LettuceJsonPath(path))).arguments(args)
				.converter(new ListConverter<>(new JsonTypeConverter())).run();
	}

	private List<Long> jsonArrAppend(
			final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, List<Long>> executor,
			final CommandArguments args) {
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_ARRAPPEND)
				.executor(executor).arguments(args).converter((v)->v).run();
	}

	private Long jsonDel(final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, Long> executor,
						 final CommandArguments args) {
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_DEL).executor(executor).arguments(args)
				.converter((v)->v).run();
	}

	public Long jsonForget(final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, Long> executor,
						   final CommandArguments args) {
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_FORGET)
				.executor(executor).arguments(args).converter((v)->v).run();
	}

	private List<Long> jsonArrIndex(
			final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, List<Long>> executor,
			final CommandArguments args) {
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_ARRINDEX)
				.executor(executor).arguments(args).converter((v)->v).run();
	}

	private List<Long> jsonArrLen(
			final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, List<Long>> executor,
			final CommandArguments args) {
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_ARRINSERT)
				.executor(executor).arguments(args).converter((v)->v).run();
	}

	private String jsonStringGet(
			final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, String> executor,
			final CommandArguments args) {
		return LettuceCommandBuilder.<String, String>newBuilder(client, Command.JSON_GET).executor(executor)
				.arguments(args).converter((v)->v).run();
	}

	private byte[] jsonBinaryGet(
			final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, byte[]> executor,
			final CommandArguments args) {
		return LettuceCommandBuilder.<byte[], byte[]>newBuilder(client, Command.JSON_GET).executor(executor)
				.arguments(args).converter((v)->v).run();
	}

	private List<String> jsonStringListGet(
			final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, List<String>> executor,
			final CommandArguments args) {
		return LettuceCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.JSON_GET).executor(executor)
				.arguments(args).converter((v)->v).run();
	}

	private List<byte[]> jsonBinaryListGet(
			final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, List<String>> executor,
			final CommandArguments args) {
		return LettuceCommandBuilder.<List<String>, List<byte[]>>newBuilder(client, Command.JSON_GET).executor(executor)
				.arguments(args).converter(Converters.stringListToBinaryListConverter()).run();
	}

	private Status jsonSet(
			final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, String> executor,
			final CommandArguments args) {
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.JSON_SET)
				.executor(executor).arguments(args)
				.converter(new OkStatusConverter()).run();
	}

	private List<Long> jsonStrAppend(
			final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, List<Long>> executor,
			final CommandArguments args) {
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_STRAPPEND)
				.executor(executor).arguments(args).converter((v)->v)
				.run();
	}

}
