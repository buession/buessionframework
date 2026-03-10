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
import com.buession.redis.core.NxXx;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.JsonCommands;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.JsonGetArgument;
import com.buession.redis.core.command.args.JsonKeyPathValue;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.StringListBinaryListConverter;
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
		return executeCommand(Command.JSON_ARRAPPEND, args, (cmd)->cmd.jsonArrappend(rawBinaryKey(key), values));
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(Command.JSON_ARRAPPEND, args,
				(cmd)->cmd.jsonArrappend(rawKey(key), SafeEncoder.encode(values)));
	}

	@Override
	public List<Long> jsonArrAppend(final String key, final String path, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(values);
		return executeCommand(Command.JSON_ARRAPPEND, args,
				(cmd)->cmd.jsonArrappend(rawBinaryKey(key), new LettuceJsonPath(path), values));
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[] path, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(values);
		return executeCommand(Command.JSON_ARRAPPEND, args,
				(cmd)->cmd.jsonArrappend(rawKey(key), new LettuceJsonPath(path), SafeEncoder.encode(values)));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path, value);
		return executeCommand(Command.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(rawBinaryKey(key), new LettuceJsonPath(path), value));
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path, value);
		return executeCommand(Command.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(rawKey(key), new LettuceJsonPath(path), SafeEncoder.encode(value)));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start) {
		final CommandArguments args = CommandArguments.create(key).add(path, value).add(start);
		return executeCommand(Command.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(rawBinaryKey(key), new LettuceJsonPath(path), value,
						new LettuceJsonRangeArgs(start)));
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start) {
		final CommandArguments args = CommandArguments.create(key).add(path, value).add(start);
		return executeCommand(Command.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(rawKey(key), new LettuceJsonPath(path), SafeEncoder.encode(value),
						new LettuceJsonRangeArgs(start)));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start,
								   final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path, value).add(start, stop);
		return executeCommand(Command.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(rawBinaryKey(key), new LettuceJsonPath(path), value,
						new LettuceJsonRangeArgs(start, stop)));
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start,
								   final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path, value).add(start, stop);
		return executeCommand(Command.JSON_ARRINDEX, args,
				(cmd)->cmd.jsonArrindex(rawKey(key), new LettuceJsonPath(path), SafeEncoder.encode(value),
						new LettuceJsonRangeArgs(start, stop)));
	}

	@Override
	public List<Long> jsonArrInsert(final String key, final String path, final int index, final String... values) {
		final CommandArguments args = CommandArguments.create(key, path).add(index).add(values);
		return executeCommand(Command.JSON_ARRINSERT, args,
				(cmd)->cmd.jsonArrinsert(rawBinaryKey(key), new LettuceJsonPath(path), index, values));
	}

	@Override
	public List<Long> jsonArrInsert(final byte[] key, final byte[] path, final int index, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key, path).add(index).add(values);
		return executeCommand(Command.JSON_ARRINSERT, args,
				(cmd)->cmd.jsonArrinsert(rawKey(key), new LettuceJsonPath(path), index, SafeEncoder.encode(values)));
	}

	@Override
	public Long jsonArrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_ARRLEN, args, (cmd)->cmd.jsonArrlen(rawBinaryKey(key)),
				Converters.list0Converter());
	}

	@Override
	public Long jsonArrLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_ARRLEN, args, (cmd)->cmd.jsonArrlen(rawKey(key)),
				Converters.list0Converter());
	}

	@Override
	public List<Long> jsonArrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_ARRLEN, args,
				(cmd)->cmd.jsonArrlen(rawBinaryKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public List<Long> jsonArrLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_ARRLEN, args, (cmd)->cmd.jsonArrlen(rawKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public String jsonArrPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_ARRPOP, args, (cmd)->cmd.jsonArrpopRaw(rawBinaryKey(key)),
				Converters.list0Converter());
	}

	@Override
	public byte[] jsonArrPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_ARRPOP, args, (cmd)->cmd.jsonArrpopRaw(rawKey(key)),
				(v)->Validate.isEmpty(v) ? null : SafeEncoder.encode(v.get(0)));
	}

	@Override
	public List<String> jsonArrPop(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_ARRPOP, args,
				(cmd)->cmd.jsonArrpopRaw(rawBinaryKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public List<byte[]> jsonArrPop(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_ARRPOP, args,
				(cmd)->cmd.jsonArrpopRaw(rawKey(key), new LettuceJsonPath(path)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<String> jsonArrPop(final String key, final String path, final int index) {
		final CommandArguments args = CommandArguments.create(key, path).add(index);
		return executeCommand(Command.JSON_ARRPOP, args,
				(cmd)->cmd.jsonArrpopRaw(rawBinaryKey(key), new LettuceJsonPath(path), index));
	}

	@Override
	public List<byte[]> jsonArrPop(final byte[] key, final byte[] path, final int index) {
		final CommandArguments args = CommandArguments.create(key, path).add(index);
		return executeCommand(Command.JSON_ARRPOP, args,
				(cmd)->cmd.jsonArrpopRaw(rawKey(key), new LettuceJsonPath(path), index),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<Long> jsonArrTrim(final String key, final String path, final int start, final int stop) {
		final CommandArguments args = CommandArguments.create(key, path).add(start, stop);
		return executeCommand(Command.JSON_ARRTRIM, args,
				(cmd)->cmd.jsonArrtrim(rawBinaryKey(key), new LettuceJsonPath(path),
						new LettuceJsonRangeArgs(start, stop)));
	}

	@Override
	public List<Long> jsonArrTrim(final byte[] key, final byte[] path, final int start, final int stop) {
		final CommandArguments args = CommandArguments.create(key, path).add(start, stop);
		return executeCommand(Command.JSON_ARRTRIM, args,
				(cmd)->cmd.jsonArrtrim(rawKey(key), new LettuceJsonPath(path), new LettuceJsonRangeArgs(start, stop)));
	}

	@Override
	public Long jsonClear(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_CLEAR, args, (cmd)->cmd.jsonClear(rawBinaryKey(key)));
	}

	@Override
	public Long jsonClear(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_CLEAR, args, (cmd)->cmd.jsonClear(rawKey(key)));
	}

	@Override
	public Long jsonClear(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_CLEAR, args,
				(cmd)->cmd.jsonClear(rawBinaryKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonClear(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_CLEAR, args, (cmd)->cmd.jsonClear(rawKey(key), new LettuceJsonPath(path)));
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
		return executeCommand(Command.JSON_DEL, args, (cmd)->cmd.jsonDel(rawBinaryKey(key)));
	}

	@Override
	public Long jsonDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_DEL, args, (cmd)->cmd.jsonDel(rawKey(key)));
	}

	@Override
	public Long jsonDel(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_DEL, args, (cmd)->cmd.jsonDel(rawBinaryKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonDel(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_DEL, args, (cmd)->cmd.jsonDel(rawKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonForget(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_FORGET, args, (cmd)->cmd.jsonDel(rawBinaryKey(key)));
	}

	@Override
	public Long jsonForget(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_FORGET, args, (cmd)->cmd.jsonDel(rawKey(key)));
	}

	@Override
	public Long jsonForget(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_FORGET, args,
				(cmd)->cmd.jsonDel(rawBinaryKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public Long jsonForget(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_FORGET, args, (cmd)->cmd.jsonDel(rawKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public String jsonGet(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_GET, args, (cmd)->cmd.jsonGetRaw(rawBinaryKey(key)),
				Converters.list0Converter());
	}

	@Override
	public byte[] jsonGet(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_GET, args, (cmd)->cmd.jsonGetRaw(rawKey(key)),
				(v)->Validate.isEmpty(v) ? null : SafeEncoder.encode(v.get(0)));
	}

	@Override
	public String jsonGet(final String key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(rawBinaryKey(key), new LettuceJsonGetArgs(argument)),
				Converters.list0Converter());
	}

	@Override
	public byte[] jsonGet(final byte[] key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.JSON_ARRINSERT, args,
				(cmd)->cmd.jsonGetRaw(rawKey(key), new LettuceJsonGetArgs(argument)),
				(v)->Validate.isEmpty(v) ? null : SafeEncoder.encode(v.get(0)));
	}

	@Override
	public List<String> jsonGet(final String key, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<String, LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return executeCommand(Command.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(rawBinaryKey(key), arrayConverter.convert(path)));
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<byte[], LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return executeCommand(Command.JSON_GET, args, (cmd)->cmd.jsonGetRaw(rawKey(key), arrayConverter.convert(path)),
				new StringListBinaryListConverter());
	}

	@Override
	public List<String> jsonGet(final String key, final JsonGetArgument argument, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<String, LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return executeCommand(Command.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(rawBinaryKey(key), new LettuceJsonGetArgs(argument),
						arrayConverter.convert(path)));
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<byte[], LettuceJsonPath> arrayConverter = new ArrayConverter<>(LettuceJsonPath::new,
				LettuceJsonPath.class);
		return executeCommand(Command.JSON_GET, args,
				(cmd)->cmd.jsonGetRaw(rawKey(key), new LettuceJsonGetArgs(argument),
						arrayConverter.convert(path)), new StringListBinaryListConverter());
	}

	@Override
	public Status jsonMerge(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_MERGE, args,
				(cmd)->cmd.jsonMerge(rawBinaryKey(key), new LettuceJsonPath(path), value), new OkStatusConverter());
	}

	@Override
	public Status jsonMerge(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_MERGE, args,
				(cmd)->cmd.jsonMerge(rawKey(key), new LettuceJsonPath(path), SafeEncoder.encode(value)),
				new OkStatusConverter());
	}

	@Override
	public List<String> jsonMGet(final String[] keys, final String path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return executeCommand(Command.JSON_MGET, args,
				(cmd)->cmd.jsonMGetRaw(new LettuceJsonPath(path), rawBinaryKeys(keys)));
	}

	@Override
	public List<byte[]> jsonMGet(final byte[][] keys, final byte[] path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return executeCommand(Command.JSON_MGET, args, (cmd)->cmd.jsonMGetRaw(new LettuceJsonPath(path), rawKeys(keys)),
				new StringListBinaryListConverter());
	}

	@Override
	public Status jsonMSet(final JsonKeyPathValue... data) {
		final CommandArguments args = CommandArguments.create(data);
		final JsonParser jsonParser = new DefaultJsonParser();
		final ArrayListConverter<JsonKeyPathValue, JsonMsetArgs<byte[], byte[]>> arrayListConverter = new ArrayListConverter<>(
				(v)->new JsonMsetArgs<>(rawBinaryKey(v.getKey()), new LettuceJsonPath(v.getPath()),
						jsonParser.createJsonValue(v.getValue())));
		return executeCommand(Command.JSON_MSET, args, (cmd)->cmd.jsonMSet(arrayListConverter.convert(data)),
				new OkStatusConverter());
	}

	@Override
	public List<Number> jsonNumIncrBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(Command.JSON_NUMINCRBY, args,
				(cmd)->cmd.jsonNumincrby(rawBinaryKey(key), new LettuceJsonPath(path), value));
	}

	@Override
	public List<Number> jsonNumIncrBy(final byte[] key, final byte[] path, final Number value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(Command.JSON_NUMINCRBY, args,
				(cmd)->cmd.jsonNumincrby(rawKey(key), new LettuceJsonPath(path), value));
	}

	@Override
	public List<Number> jsonNumMultBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(Command.JSON_NUMMULTBY, args);
	}

	@Override
	public List<Number> jsonNumMultBy(final byte[] key, final byte[] path, final Number value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(Command.JSON_NUMMULTBY, args);
	}

	@Override
	public List<List<String>> jsonObjKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final ByteArrayDeserializer byteArrayDeserializer = new DefaultByteArrayDeserializer();
		return executeCommand(Command.JSON_OBJKEYS, args,
				(cmd)->cmd.jsonObjkeys(rawBinaryKey(key)), new ListConverter<>((v)->{
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
		return executeCommand(Command.JSON_OBJKEYS, args,
				(cmd)->cmd.jsonObjkeys(rawKey(key)), new ListConverter<>((v)->{
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
		return executeCommand(Command.JSON_OBJKEYS, args,
				(cmd)->cmd.jsonObjkeys(rawBinaryKey(key), new LettuceJsonPath(path)),
				new ListConverter<>((v)->{
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
		return executeCommand(Command.JSON_OBJKEYS, args,
				(cmd)->cmd.jsonObjkeys(rawKey(key), new LettuceJsonPath(path)),
				new ListConverter<>((v)->{
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
		return executeCommand(Command.JSON_OBJLEN, args, (cmd)->cmd.jsonObjlen(rawBinaryKey(key)),
				Converters.list0Converter());
	}

	@Override
	public Long jsonObjLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_OBJLEN, args, (cmd)->cmd.jsonObjlen(rawKey(key)),
				Converters.list0Converter());
	}

	@Override
	public List<Long> jsonObjLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_OBJLEN, args,
				(cmd)->cmd.jsonObjlen(rawBinaryKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public List<Long> jsonObjLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_OBJLEN, args, (cmd)->cmd.jsonObjlen(rawKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public List<Object> jsonResp(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_RESP, args);
	}

	@Override
	public List<Object> jsonResp(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_RESP, args);
	}

	@Override
	public List<Object> jsonResp(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_RESP, args);
	}

	@Override
	public List<Object> jsonResp(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_RESP, args);
	}

	@Override
	public Status jsonSet(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key, path);
		return jsonSet(rawBinaryKey(key), new LettuceJsonPath(path), value, args);
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, path);
		return jsonSet(rawKey(key), new LettuceJsonPath(path), SafeEncoder.encode(value), args);
	}

	@Override
	public Status jsonSet(final String key, final String path, final String value, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key, path).add(nxXx);
		return jsonSet(rawBinaryKey(key), new LettuceJsonPath(path), value, nxXx, args);
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key, path).add(nxXx);
		return jsonSet(rawKey(key), new LettuceJsonPath(path), SafeEncoder.encode(value), nxXx, args);
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.JSON_STRAPPEND, args, (cmd)->cmd.jsonStrappend(rawBinaryKey(key), value));
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.JSON_STRAPPEND, args,
				(cmd)->cmd.jsonStrappend(rawKey(key), SafeEncoder.encode(value)));
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(Command.JSON_STRAPPEND, args,
				(cmd)->cmd.jsonStrappend(rawBinaryKey(key), new LettuceJsonPath(path), value));
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] path, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key, path).add(value);
		return executeCommand(Command.JSON_STRAPPEND, args,
				(cmd)->cmd.jsonStrappend(rawKey(key), new LettuceJsonPath(path), SafeEncoder.encode(value)));
	}

	@Override
	public Long jsonStrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_STRLEN, args, (cmd)->cmd.jsonStrlen(rawBinaryKey(key)),
				Converters.list0Converter());
	}

	@Override
	public Long jsonStrLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_STRLEN, args, (cmd)->cmd.jsonStrlen(rawKey(key)),
				Converters.list0Converter());
	}

	@Override
	public List<Long> jsonStrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_STRLEN, args,
				(cmd)->cmd.jsonStrlen(rawBinaryKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public List<Long> jsonStrLen(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_STRLEN, args, (cmd)->cmd.jsonStrlen(rawKey(key), new LettuceJsonPath(path)));
	}

	@Override
	public List<Status> jsonToggle(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_TOGGLE, args,
				(cmd)->cmd.jsonToggle(rawBinaryKey(key), new LettuceJsonPath(path)),
				new ListConverter<>(new OneStatusConverter()));
	}

	@Override
	public List<Status> jsonToggle(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return executeCommand(Command.JSON_TOGGLE, args, (cmd)->cmd.jsonToggle(rawKey(key), new LettuceJsonPath(path)),
				new ListConverter<>(new OneStatusConverter()));
	}

	@Override
	public JsonType jsonType(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonType(rawBinaryKey(key), args);
	}

	@Override
	public JsonType jsonType(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonType(rawKey(key), args);
	}

	@Override
	public List<JsonType> jsonType(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return jsonType(rawBinaryKey(key), new LettuceJsonPath(path), args);
	}

	@Override
	public List<JsonType> jsonType(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key, path);
		return jsonType(rawKey(key), new LettuceJsonPath(path), args);
	}

	private Status jsonSet(final byte[] key, final JsonPath path, final String value, final CommandArguments args) {
		return executeCommand(Command.JSON_SET, args,
				(cmd)->cmd.jsonSet(key, path, value), new OkStatusConverter());
	}

	private Status jsonSet(final byte[] key, final JsonPath path, final String value, final NxXx nxXx,
						   final CommandArguments args) {
		return executeCommand(Command.JSON_SET, args,
				(cmd)->cmd.jsonSet(key, path, value, new LettuceJsonSetArgs(nxXx)),
				new OkStatusConverter());
	}

	private JsonType jsonType(final byte[] key, final CommandArguments args) {
		return executeCommand(Command.JSON_TYPE, args, (cmd)->{
			final List<io.lettuce.core.json.JsonType> result = cmd.jsonType(key);
			return Validate.isEmpty(result) ? null : result.get(0);
		}, new JsonTypeConverter());
	}

	private List<JsonType> jsonType(final byte[] key, final JsonPath path, final CommandArguments args) {
		return executeCommand(Command.JSON_TYPE, args, (cmd)->cmd.jsonType(key, path),
				new ListConverter<>(new JsonTypeConverter()));
	}

}
