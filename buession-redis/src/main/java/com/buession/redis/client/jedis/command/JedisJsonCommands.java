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

import com.buession.core.converter.ArrayConverter;
import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.deserializer.ByteArrayDeserializer;
import com.buession.core.deserializer.DefaultByteArrayDeserializer;
import com.buession.core.deserializer.DeserializerException;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.JsonType;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.JsonCommands;
import com.buession.redis.core.command.args.JsonGetArgument;
import com.buession.redis.core.command.args.JsonKeyPathValueArgument;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.jedis.response.JsonTypeConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OrgJSONArrayConverter;
import com.buession.redis.core.internal.jedis.args.JedisJsonSetParams;
import com.buession.redis.core.internal.jedis.JedisPath;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.UnifiedJedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Jedis JSON 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisJsonCommands extends AbstractJedisRedisCommands implements JsonCommands {

	public JedisJsonCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public List<Long> jsonArrAppend(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(Command.JSON_ARRAPPEND, args,
				(cmd)->cmd.jsonArrAppend(rawKey(key), JedisPath.ROOT_PATH, values), (v)->v);
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[]... values) {
		return jsonArrAppend(SafeEncoder.encode(key), SafeEncoder.encode(values));
	}

	@Override
	public List<Long> jsonArrAppend(final String key, final String path, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(values);
		return executeCommand(Command.JSON_ARRAPPEND, args,
				(cmd)->cmd.jsonArrAppend(rawKey(key), new JedisPath(path), values), (v)->v);
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[] path, final byte[]... values) {
		return jsonArrAppend(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(values));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return jsonArrIndex((cmd)->cmd.jsonArrIndex(rawKey(key), new JedisPath(path), value), args);
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value) {
		return jsonArrIndex(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start);
		return jsonArrIndex((cmd)->cmd.jsonArrIndex(rawKey(key), new JedisPath(path), value), args);
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start) {
		return jsonArrIndex(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value), start);
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start,
								   final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start, stop);
		return jsonArrIndex((cmd)->cmd.jsonArrIndex(rawKey(key), new JedisPath(path), value), args);
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start,
								   final int stop) {
		return jsonArrIndex(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value), start, stop);
	}

	@Override
	public List<Long> jsonArrInsert(final String key, final String path, final int index, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(index).add(values);
		return executeCommand(Command.JSON_ARRINSERT, args,
				(cmd)->cmd.jsonArrInsert(rawKey(key), new JedisPath(path), index, values), (v)->v);
	}

	@Override
	public List<Long> jsonArrInsert(final byte[] key, final byte[] path, final int index, final byte[]... values) {
		return jsonArrInsert(SafeEncoder.encode(key), SafeEncoder.encode(path), index, SafeEncoder.encode(values));
	}

	@Override
	public Long jsonArrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_ARRLEN, args, (cmd)->cmd.jsonArrLen(rawKey(key), JedisPath.ROOT_PATH),
				Converters.list0Converter());
	}

	@Override
	public Long jsonArrLen(final byte[] key) {
		return jsonArrLen(SafeEncoder.encode(key));
	}

	@Override
	public List<Long> jsonArrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_ARRLEN, args, (cmd)->cmd.jsonArrLen(rawKey(key), new JedisPath(path)),
				(v)->v);
	}

	@Override
	public List<Long> jsonArrLen(final byte[] key, final byte[] path) {
		return jsonArrLen(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public String jsonArrPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_ARRPOP, args, (cmd)->cmd.jsonArrPop(rawKey(key), JedisPath.ROOT_PATH),
				(v)->Validate.isEmpty(v) ? null : v.get(0).toString());
	}

	@Override
	public byte[] jsonArrPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_ARRPOP, args, (cmd)->cmd.jsonArrPop(rawStringKey(key), JedisPath.ROOT_PATH),
				(v)->Validate.isEmpty(v) ? null : SafeEncoder.encode(v.get(0).toString()));
	}

	@Override
	public List<String> jsonArrPop(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_ARRPOP, args, (cmd)->cmd.jsonArrPop(rawKey(key), new JedisPath(path)),
				new ListConverter<>((v)->v == null ? null : v.toString()));
	}

	@Override
	public List<byte[]> jsonArrPop(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_ARRPOP, args, (cmd)->cmd.jsonArrPop(rawKey(SafeEncoder.encode(key)),
				new JedisPath(path)), new ListConverter<>((v)->v == null ? null :
				SafeEncoder.encode(v.toString())));
	}

	@Override
	public List<Long> jsonArrTrim(final String key, final String path, final int start, final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(start, stop);
		return executeCommand(Command.JSON_ARRTRIM, args,
				(cmd)->cmd.jsonArrTrim(rawKey(key), new JedisPath(path), start, stop), (v)->v);
	}

	@Override
	public List<Long> jsonArrTrim(final byte[] key, final byte[] path, final int start, final int stop) {
		return jsonArrTrim(SafeEncoder.encode(key), SafeEncoder.encode(path), start, stop);
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
		return executeCommand(Command.JSON_DEBUG, args, (cmd)->cmd.jsonDebugMemory(rawKey(key), JedisPath.ROOT_PATH),
				Converters.list0Converter());
	}

	@Override
	public Long jsonDebugMemory(final byte[] key) {
		return jsonDebugMemory(SafeEncoder.encode(key));
	}

	@Override
	public List<Long> jsonDebugMemory(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_DEBUG, args, (cmd)->cmd.jsonDebugMemory(rawKey(key), new JedisPath(path)),
				(v)->v);
	}

	@Override
	public List<Long> jsonDebugMemory(final byte[] key, final byte[] path) {
		return jsonDebugMemory(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public Long jsonDel(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_DEL, args, (cmd)->cmd.jsonDel(rawKey(key)), (v)->v);
	}

	@Override
	public Long jsonDel(final byte[] key) {
		return jsonDel(SafeEncoder.encode(key));
	}

	@Override
	public Long jsonDel(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_DEL, args, (cmd)->cmd.jsonDel(rawKey(key), new JedisPath(path)), (v)->v);
	}

	@Override
	public Long jsonDel(final byte[] key, final byte[] path) {
		return jsonDel(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public Long jsonForget(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_FORGET, args, (cmd)->cmd.jsonDel(rawKey(key)), (v)->v);
	}

	@Override
	public Long jsonForget(final byte[] key) {
		return jsonForget(SafeEncoder.encode(key));
	}

	@Override
	public Long jsonForget(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_FORGET, args, (cmd)->cmd.jsonDel(rawKey(key), new JedisPath(path)), (v)->v);
	}

	@Override
	public Long jsonForget(final byte[] key, final byte[] path) {
		return jsonForget(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public String jsonGet(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_GET, args, (cmd)->cmd.jsonGet(rawKey(key)), Objects::toString);
	}

	@Override
	public byte[] jsonGet(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonBinaryGet((cmd)->cmd.jsonGet(SafeEncoder.encode(key)), args);
	}

	@Override
	public String jsonGet(final String key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.JSON_GET, args, (cmd)->cmd.jsonGet(rawKey(key)), Objects::toString);
	}

	@Override
	public byte[] jsonGet(final byte[] key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonBinaryGet((cmd)->cmd.jsonGet(rawStringKey(key)), args);
	}

	@Override
	public List<String> jsonGet(final String key, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<String, JedisPath> arrayConverter = new ArrayConverter<>(JedisPath::new, JedisPath.class);
		return jsonListStringGet((cmd)->cmd.jsonGet(rawKey(key), arrayConverter.convert(path)), args);
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<byte[], JedisPath> arrayConverter = new ArrayConverter<>(JedisPath::new, JedisPath.class);
		return jsonListBinaryGet((cmd)->cmd.jsonGet(rawStringKey(key), arrayConverter.convert(path)),
				args);
	}

	@Override
	public List<String> jsonGet(final String key, final JsonGetArgument argument, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<String, JedisPath> arrayConverter = new ArrayConverter<>(JedisPath::new, JedisPath.class);
		return jsonListStringGet((cmd)->cmd.jsonGet(rawKey(key), arrayConverter.convert(path)), args);
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<byte[], JedisPath> arrayConverter = new ArrayConverter<>(JedisPath::new, JedisPath.class);
		return jsonListBinaryGet((cmd)->cmd.jsonGet(rawStringKey(key), arrayConverter.convert(path)),
				args);
	}

	@Override
	public Status jsonMerge(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_MERGE, args, (cmd)->cmd.jsonMerge(rawKey(key), new JedisPath(path), value),
				new OkStatusConverter());
	}

	@Override
	public Status jsonMerge(final byte[] key, final byte[] path, final byte[] value) {
		return jsonMerge(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value));
	}

	@Override
	public List<String> jsonMGet(final String[] keys, final String path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return executeCommand(Command.JSON_MGET, args, (cmd)->cmd.jsonMGet(new JedisPath(path), rawKeys(keys)),
				new ListConverter<>(new OrgJSONArrayConverter.OrgJSONArrayStringConverter()));
	}

	@Override
	public List<byte[]> jsonMGet(final byte[][] keys, final byte[] path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return executeCommand(Command.JSON_MGET, args,
				(cmd)->cmd.jsonMGet(new JedisPath(path), rawStringKeys(keys)),
				new ListConverter<>(new OrgJSONArrayConverter.OrgJSONArrayBinaryConverter()));
	}

	@Override
	public Status jsonMSet(final JsonKeyPathValueArgument... data) {
		final CommandArguments args = CommandArguments.create(data);
		return executeCommand(Command.JSON_MSET, args);
	}

	@Override
	public List<Number> jsonNumIncrBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_NUMINCRBY, args,
				(cmd)->cmd.jsonNumIncrBy(rawKey(key), new JedisPath(path), value.doubleValue()), (v)->(List<Number>) v);
	}

	@Override
	public List<Number> jsonNumIncrBy(final byte[] key, final byte[] path, final Number value) {
		return jsonNumIncrBy(SafeEncoder.encode(key), SafeEncoder.encode(path), value);
	}

	@Override
	public List<Number> jsonNumMultBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_NUMMULTBY, args);
	}

	@Override
	public List<Number> jsonNumMultBy(final byte[] key, final byte[] path, final Number value) {
		return jsonNumMultBy(SafeEncoder.encode(key), SafeEncoder.encode(path), value);
	}

	@Override
	public List<List<String>> jsonObjKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_OBJKEYS, args, (cmd)->cmd.jsonObjKeys(rawKey(key), JedisPath.ROOT_PATH),
				(v)->v);
	}

	@Override
	public List<List<byte[]>> jsonObjKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_OBJKEYS, args,
				(cmd)->cmd.jsonObjKeys(rawStringKey(key), JedisPath.ROOT_PATH),
				new ListConverter<>(new ListConverter<>(SafeEncoder::encode)));
	}

	@Override
	public List<List<String>> jsonObjKeys(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_OBJKEYS, args, (cmd)->cmd.jsonObjKeys(rawKey(key), new JedisPath(path)),
				(v)->v);
	}

	@Override
	public List<List<byte[]>> jsonObjKeys(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_OBJKEYS, args, (cmd)->cmd.jsonObjKeys(rawStringKey(key),
				new JedisPath(path)), new ListConverter<>(new ListConverter<>(SafeEncoder::encode)));
	}

	@Override
	public Long jsonObjLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_OBJLEN, args, (cmd)->cmd.jsonObjLen(rawKey(key)), (v)->v);
	}

	@Override
	public Long jsonObjLen(final byte[] key) {
		return jsonObjLen(SafeEncoder.encode(key));
	}

	@Override
	public List<Long> jsonObjLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_OBJLEN, args, (cmd)->cmd.jsonObjLen(rawKey(key), new JedisPath(path)),
				(v)->v);
	}

	@Override
	public List<Long> jsonObjLen(final byte[] key, final byte[] path) {
		return jsonObjLen(SafeEncoder.encode(key), SafeEncoder.encode(path));
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
		return executeCommand(Command.JSON_SET, args, (cmd)->cmd.jsonSet(rawKey(key), new JedisPath(path), value),
				new OkStatusConverter());
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value) {
		return jsonSet(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value));
	}

	@Override
	public Status jsonSet(final String key, final String path, final String value, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(nxXx);
		return executeCommand(Command.JSON_SET, args,
				(cmd)->cmd.jsonSet(rawKey(key), new JedisPath(path), value, new JedisJsonSetParams(nxXx)),
				new OkStatusConverter());
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value, final NxXx nxXx) {
		return jsonSet(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value), nxXx);
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return executeCommand(Command.JSON_STRAPPEND, args,
				(cmd)->cmd.jsonStrAppend(rawKey(key), JedisPath.ROOT_PATH, value), (v)->v);
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] value) {
		return jsonStrAppend(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return executeCommand(Command.JSON_STRAPPEND, args,
				(cmd)->cmd.jsonStrAppend(rawKey(key), new JedisPath(path), value), (v)->v);
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] path, final byte[] value) {
		return jsonStrAppend(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value));
	}

	@Override
	public Long jsonStrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_STRLEN, args, (cmd)->cmd.jsonStrLen(rawKey(key)), (v)->v);
	}

	@Override
	public Long jsonStrLen(final byte[] key) {
		return jsonStrLen(SafeEncoder.encode(key));
	}

	@Override
	public List<Long> jsonStrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_STRLEN, args, (cmd)->cmd.jsonStrLen(rawKey(key), new JedisPath(path)),
				(v)->v);
	}

	@Override
	public List<Long> jsonStrLen(final byte[] key, final byte[] path) {
		return jsonStrLen(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public List<Status> jsonToggle(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_TOGGLE, args, (cmd)->cmd.jsonToggle(rawKey(key), new JedisPath(path)),
				new ListConverter<>(new BooleanStatusConverter()));
	}

	@Override
	public List<Status> jsonToggle(final byte[] key, final byte[] path) {
		return jsonToggle(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public JsonType jsonType(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.JSON_TYPE, args, (cmd)->cmd.jsonType(rawKey(key)), new JsonTypeConverter());
	}

	@Override
	public JsonType jsonType(final byte[] key) {
		return jsonType(SafeEncoder.encode(key));
	}

	@Override
	public List<JsonType> jsonType(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return executeCommand(Command.JSON_TYPE, args, (cmd)->cmd.jsonType(rawKey(key), new JedisPath(path)),
				new ListConverter<>(new JsonTypeConverter()));
	}

	@Override
	public List<JsonType> jsonType(final byte[] key, final byte[] path) {
		return jsonType(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	private List<Long> jsonArrIndex(final com.buession.redis.core.Command.Executor<UnifiedJedis, List<Long>> executor,
									final CommandArguments args) {
		return executeCommand(Command.JSON_ARRINDEX, args, executor, (v)->v);
	}

	private byte[] jsonBinaryGet(final com.buession.redis.core.Command.Executor<UnifiedJedis, Object> executor,
								 final CommandArguments args) {
		final ByteArrayDeserializer byteArrayDeserializer = new DefaultByteArrayDeserializer();
		return executeCommand(Command.JSON_GET, args, executor, (v)->{
			try{
				return byteArrayDeserializer.deserialize(v.toString());
			}catch(DeserializerException e){
				return null;
			}
		});
	}

	@SuppressWarnings({"unchecked"})
	private List<String> jsonListStringGet(
			final com.buession.redis.core.Command.Executor<UnifiedJedis, Object> executor,
			final CommandArguments args) {
		return executeCommand(Command.JSON_GET, args, executor, (v)->{
			final List<Object> temp = (List<Object>) v;
			return temp.stream().map(Object::toString).collect(Collectors.toList());
		});
	}

	@SuppressWarnings({"unchecked"})
	private List<byte[]> jsonListBinaryGet(
			final com.buession.redis.core.Command.Executor<UnifiedJedis, Object> executor,
			final CommandArguments args) {
		final ByteArrayDeserializer byteArrayDeserializer = new DefaultByteArrayDeserializer();
		return executeCommand(Command.JSON_GET, args, executor, (v)->{
			final List<Object> temp = (List<Object>) v;
			final List<byte[]> result = new ArrayList<>(temp.size());

			for(Object o : temp){
				try{
					result.add(byteArrayDeserializer.deserialize(o.toString()));
				}catch(DeserializerException e){
					result.add(null);
				}
			}
			return result;
		});
	}

}
