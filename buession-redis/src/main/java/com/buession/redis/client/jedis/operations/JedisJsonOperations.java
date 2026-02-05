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
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.ArrayConverter;
import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.deserializer.ByteArrayDeserializer;
import com.buession.core.deserializer.DefaultByteArrayDeserializer;
import com.buession.core.deserializer.DeserializerException;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.JsonOperations;
import com.buession.redis.core.JsonType;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.JsonGetArgument;
import com.buession.redis.core.command.args.JsonKeyPathValueArgument;
import com.buession.redis.core.internal.convert.jedis.response.JsonTypeConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.jedis.JedisJsonSetParams;
import com.buession.redis.core.internal.jedis.JedisPath;
import com.buession.redis.utils.SafeEncoder;
import org.json.JSONArray;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.json.Path2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jedis JSON 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisJsonOperations extends AbstractJedisRedisOperations implements JsonOperations {

	public JedisJsonOperations(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public List<Long> jsonArrAppend(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return jsonArrAppend(key, Path2.ROOT_PATH, values, args);
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[]... values) {
		return jsonArrAppend(SafeEncoder.encode(key), SafeEncoder.encode(values));
	}

	@Override
	public List<Long> jsonArrAppend(final String key, final String path, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(values);
		return jsonArrAppend(key, new Path2(path), values, args);
	}

	@Override
	public List<Long> jsonArrAppend(final byte[] key, final byte[] path, final byte[]... values) {
		return jsonArrAppend(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(values));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return jsonArrIndex((cmd)->cmd.jsonArrIndex(key, new Path2(path), value), args);
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value) {
		return jsonArrIndex(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value));
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start);
		return jsonArrIndex((cmd)->cmd.jsonArrIndex(key, new Path2(path), value), args);
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start) {
		return jsonArrIndex(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value), start);
	}

	@Override
	public List<Long> jsonArrIndex(final String key, final String path, final String value, final int start,
								   final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value).add(start).add(stop);
		return jsonArrIndex((cmd)->cmd.jsonArrIndex(key, new Path2(path), value), args);
	}

	@Override
	public List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start,
								   final int stop) {
		return jsonArrIndex(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value), start, stop);
	}

	@Override
	public List<Long> jsonArrInsert(final String key, final String path, final int index, final String... values) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(index).add(values);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_ARRINSERT)
				.executor((cmd)->cmd.jsonArrInsert(key, new Path2(path), index, values)).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Long> jsonArrInsert(final byte[] key, final byte[] path, final int index, final byte[]... values) {
		return jsonArrInsert(SafeEncoder.encode(key), SafeEncoder.encode(path), index, SafeEncoder.encode(values));
	}

	@Override
	public Long jsonArrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_ARRLEN)
				.executor((cmd)->cmd.jsonArrLen(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonArrLen(final byte[] key) {
		return jsonArrLen(SafeEncoder.encode(key));
	}

	@Override
	public List<Long> jsonArrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_ARRLEN)
				.executor((cmd)->cmd.jsonArrLen(key, new Path2(path))).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> jsonArrLen(final byte[] key, final byte[] path) {
		return jsonArrLen(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public Object jsonArrPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.newBuilder(client, Command.JSON_ARRPOP).executor((cmd)->cmd.jsonArrPop(key))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Object jsonArrPop(final byte[] key) {
		return jsonArrPop(SafeEncoder.encode(key));
	}

	@Override
	public List<Object> jsonArrPop(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<List<Object>, List<Object>>newBuilder(client, Command.JSON_ARRPOP)
				.executor((cmd)->cmd.jsonArrPop(key, new Path2(path))).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Object> jsonArrPop(final byte[] key, final byte[] path) {
		return jsonArrPop(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public List<Long> jsonArrTrim(final String key, final String path, final int start, final int stop) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(start).add(stop);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_ARRTRIM)
				.executor((cmd)->cmd.jsonArrTrim(key, new Path2(path), start, stop)).arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public List<Long> jsonArrTrim(final byte[] key, final byte[] path, final int start, final int stop) {
		return jsonArrTrim(SafeEncoder.encode(key), SafeEncoder.encode(path), start, stop);
	}

	@Override
	public Long jsonArrClear(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_CLEAR).arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public Long jsonArrClear(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_CLEAR).arguments(args).converter((v)->v)
				.run();
	}

	@Override
	public List<Long> jsonArrClear(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_CLEAR).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Long> jsonArrClear(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_CLEAR).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Long jsonDebugMemory(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_DEBUG).executor((cmd)->{
			final List<Long> result = cmd.jsonDebugMemory(key, Path2.ROOT_PATH);
			return Validate.isNotEmpty(result) ? result.get(0) : null;
		}).arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonDebugMemory(final byte[] key) {
		return jsonDebugMemory(SafeEncoder.encode(key));
	}

	@Override
	public List<Long> jsonDebugMemory(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_DEBUG)
				.executor((cmd)->cmd.jsonDebugMemory(key, new Path2(path))).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> jsonDebugMemory(final byte[] key, final byte[] path) {
		return jsonDebugMemory(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public Long jsonDel(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_DEL).executor((cmd)->cmd.jsonDel(key))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonDel(final byte[] key) {
		return jsonDel(SafeEncoder.encode(key));
	}

	@Override
	public Long jsonDel(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_DEL)
				.executor((cmd)->cmd.jsonDel(key, new Path2(path))).arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonDel(final byte[] key, final byte[] path) {
		return jsonDel(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public Long jsonForget(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_FORGET).executor((cmd)->cmd.jsonDel(key))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonForget(final byte[] key) {
		return jsonForget(SafeEncoder.encode(key));
	}

	@Override
	public Long jsonForget(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_FORGET)
				.executor((cmd)->cmd.jsonDel(key, new Path2(path))).arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonForget(final byte[] key, final byte[] path) {
		return jsonForget(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public String jsonGet(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Object, String>newBuilder(client, Command.JSON_GET)
				.executor((cmd)->cmd.jsonGet(key)).arguments(args).converter(Object::toString).run();
	}

	@Override
	public byte[] jsonGet(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonGet((cmd)->cmd.jsonGet(SafeEncoder.encode(key)), args);
	}

	@Override
	public String jsonGet(final String key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return JedisCommandBuilder.<Object, String>newBuilder(client, Command.JSON_GET)
				.executor((cmd)->cmd.jsonGet(key)).arguments(args).converter(Object::toString).run();
	}

	@Override
	public byte[] jsonGet(final byte[] key, final JsonGetArgument argument) {
		final CommandArguments args = CommandArguments.create(key);
		return jsonGet((cmd)->cmd.jsonGet(SafeEncoder.encode(key)), args);
	}

	@Override
	public List<String> jsonGet(final String key, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<String, Path2> arrayConverter = new ArrayConverter<>(Path2::new, Path2.class);
		return jsonStringGet((cmd)->cmd.jsonGet(key, arrayConverter.convert(path)), args);
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		final ArrayConverter<byte[], JedisPath> arrayConverter = new ArrayConverter<>(JedisPath::new, JedisPath.class);
		return jsonBinaryGet((cmd)->cmd.jsonGet(SafeEncoder.encode(key), arrayConverter.convert(path)), args);
	}

	@Override
	public List<String> jsonGet(final String key, final JsonGetArgument argument, final String... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<String, Path2> arrayConverter = new ArrayConverter<>(Path2::new, Path2.class);
		return jsonStringGet((cmd)->cmd.jsonGet(key, arrayConverter.convert(path)), args);
	}

	@Override
	public List<byte[]> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[]... path) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(path);
		final ArrayConverter<byte[], JedisPath> arrayConverter = new ArrayConverter<>(JedisPath::new, JedisPath.class);
		return jsonBinaryGet((cmd)->cmd.jsonGet(SafeEncoder.encode(key), arrayConverter.convert(path)), args);
	}

	@Override
	public Status jsonMerge(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.JSON_MERGE)
				.executor((cmd)->cmd.jsonMerge(key, new JedisPath(path), value)).arguments(args)
				.converter(new OkStatusConverter()).run();
	}

	@Override
	public Status jsonMerge(final byte[] key, final byte[] path, final byte[] value) {
		return jsonMerge(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value));
	}

	@Override
	public List<String> jsonMGet(final String[] keys, final String path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return JedisCommandBuilder.<List<JSONArray>, List<String>>newBuilder(client, Command.JSON_MGET)
				.executor((cmd)->cmd.jsonMGet(new JedisPath(path), keys)).arguments(args).converter((v)->{
					final List<String> result = new ArrayList<>();

					for(JSONArray a : v){
						if(a == null){
							result.add(null);
						}else{
							result.add(a.toString());
						}
					}

					return result;
				}).run();
	}

	@Override
	public List<byte[]> jsonMGet(final byte[][] keys, final byte[] path) {
		final CommandArguments args = CommandArguments.create(keys).add(path);
		return JedisCommandBuilder.<List<JSONArray>, List<byte[]>>newBuilder(client, Command.JSON_MGET)
				.executor((cmd)->cmd.jsonMGet(new JedisPath(path), SafeEncoder.encode(keys))).arguments(args)
				.converter((v)->{
					final List<byte[]> result = new ArrayList<>();

					for(JSONArray a : v){
						if(a == null){
							result.add(null);
						}else{
							result.add(SafeEncoder.encode(a.toString()));
						}
					}

					return result;
				}).run();
	}

	@Override
	public Status jsonMSet(final JsonKeyPathValueArgument.StringJsonKeyPathValueArgument... data) {
		final CommandArguments args = CommandArguments.create(data);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.JSON_MSET).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Status jsonMSet(final JsonKeyPathValueArgument.BinaryJsonKeyPathValueArgument... data) {
		final CommandArguments args = CommandArguments.create(data);
		return JedisCommandBuilder.<Status, Status>newBuilder(client, Command.JSON_MSET).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Number> jsonNumIncrBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return JedisCommandBuilder.<Object, List<Number>>newBuilder(client, Command.JSON_NUMINCRBY)
				.executor((cmd)->cmd.jsonNumIncrBy(key, new JedisPath(path), value.doubleValue())).arguments(args)
				.converter((v)->(List<Number>) v).run();
	}

	@Override
	public List<Number> jsonNumIncrBy(final byte[] key, final byte[] path, final Number value) {
		return jsonNumIncrBy(SafeEncoder.encode(key), SafeEncoder.encode(path), value);
	}

	@Override
	public List<Number> jsonNumMultBy(final String key, final String path, final Number value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return JedisCommandBuilder.<Object, List<Number>>newBuilder(client, Command.JSON_NUMMULTBY).arguments(args)
				.converter((v)->(List<Number>) v).run();
	}

	@Override
	public List<Number> jsonNumMultBy(final byte[] key, final byte[] path, final Number value) {
		return jsonNumMultBy(SafeEncoder.encode(key), SafeEncoder.encode(path), value);
	}

	@Override
	public List<List<String>> jsonObjKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<List<List<String>>, List<List<String>>>newBuilder(client, Command.JSON_OBJKEYS)
				.executor((cmd)->cmd.jsonObjKeys(key, JedisPath.ROOT_PATH)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<List<byte[]>> jsonObjKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<List<List<String>>, List<List<byte[]>>>newBuilder(client, Command.JSON_OBJKEYS)
				.executor((cmd)->cmd.jsonObjKeys(SafeEncoder.encode(key), JedisPath.ROOT_PATH)).arguments(args)
				.converter(new ListConverter<>(new ListConverter<>(SafeEncoder::encode))).run();
	}

	@Override
	public List<List<String>> jsonObjKeys(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<List<List<String>>, List<List<String>>>newBuilder(client, Command.JSON_OBJKEYS)
				.executor((cmd)->cmd.jsonObjKeys(key, new JedisPath(path))).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<List<byte[]>> jsonObjKeys(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<List<List<String>>, List<List<byte[]>>>newBuilder(client, Command.JSON_OBJKEYS)
				.executor((cmd)->cmd.jsonObjKeys(SafeEncoder.encode(key), new JedisPath(path))).arguments(args)
				.converter(new ListConverter<>(new ListConverter<>(SafeEncoder::encode))).run();
	}

	@Override
	public Long jsonObjLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_OBJLEN)
				.executor((cmd)->cmd.jsonObjLen(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonObjLen(final byte[] key) {
		return jsonObjLen(SafeEncoder.encode(key));
	}

	@Override
	public List<Long> jsonObjLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_OBJLEN)
				.executor((cmd)->cmd.jsonObjLen(key, new JedisPath(path))).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> jsonObjLen(final byte[] key, final byte[] path) {
		return jsonObjLen(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public List<String> jsonResp(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.JSON_RESP).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<byte[]> jsonResp(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.JSON_RESP).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<String> jsonResp(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.JSON_RESP).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<byte[]> jsonResp(final byte[] key, final byte[] path) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.JSON_RESP).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Status jsonSet(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.JSON_SET)
				.executor((cmd)->cmd.jsonSet(key, new JedisPath(path), value)).arguments(args)
				.converter(new OkStatusConverter()).run();
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value) {
		return jsonSet(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value));
	}

	@Override
	public Status jsonSet(final String key, final String path, final String value, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(nxXx);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.JSON_SET)
				.executor((cmd)->cmd.jsonSet(key, new JedisPath(path), value, new JedisJsonSetParams(nxXx)))
				.arguments(args).converter(new OkStatusConverter()).run();
	}

	@Override
	public Status jsonSet(final byte[] key, final byte[] path, final byte[] value, final NxXx nxXx) {
		return jsonSet(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value), nxXx);
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(value);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_STRAPPEND)
				.executor((cmd)->cmd.jsonStrAppend(key, JedisPath.ROOT_PATH, value))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] value) {
		return jsonStrAppend(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	@Override
	public List<Long> jsonStrAppend(final String key, final String path, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(path).add(value);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_STRAPPEND)
				.executor((cmd)->cmd.jsonStrAppend(key, new JedisPath(path), value))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> jsonStrAppend(final byte[] key, final byte[] path, final byte[] value) {
		return jsonStrAppend(SafeEncoder.encode(key), SafeEncoder.encode(path), SafeEncoder.encode(value));
	}

	@Override
	public Long jsonStrLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.JSON_STRLEN)
				.executor((cmd)->cmd.jsonStrLen(key))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long jsonStrLen(final byte[] key) {
		return jsonStrLen(SafeEncoder.encode(key));
	}

	@Override
	public List<Long> jsonStrLen(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_STRLEN)
				.executor((cmd)->cmd.jsonStrLen(key, new JedisPath(path)))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> jsonStrLen(final byte[] key, final byte[] path) {
		return jsonStrLen(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public List<Status> jsonToggle(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<List<Boolean>, List<Status>>newBuilder(client, Command.JSON_TOGGLE)
				.executor((cmd)->cmd.jsonToggle(key, new JedisPath(path)))
				.arguments(args).converter(new ListConverter<>(new BooleanStatusConverter())).run();
	}

	@Override
	public List<Status> jsonToggle(final byte[] key, final byte[] path) {
		return jsonToggle(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	@Override
	public JsonType jsonType(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Class<?>, JsonType>newBuilder(client, Command.JSON_TYPE)
				.executor((cmd)->cmd.jsonType(key))
				.arguments(args).converter(new JsonTypeConverter()).run();
	}

	@Override
	public JsonType jsonType(final byte[] key) {
		return jsonType(SafeEncoder.encode(key));
	}

	@Override
	public List<JsonType> jsonType(final String key, final String path) {
		final CommandArguments args = CommandArguments.create(key).add(path);
		return JedisCommandBuilder.<List<Class<?>>, List<JsonType>>newBuilder(client, Command.JSON_TYPE)
				.executor((cmd)->cmd.jsonType(key, new JedisPath(path)))
				.arguments(args).converter(new ListConverter<>(new JsonTypeConverter())).run();
	}

	@Override
	public List<JsonType> jsonType(final byte[] key, final byte[] path) {
		return jsonType(SafeEncoder.encode(key), SafeEncoder.encode(path));
	}

	private List<Long> jsonArrAppend(final String key, final Path2 path, final String[] values,
									 final CommandArguments args) {
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_ARRAPPEND)
				.executor((cmd)->cmd.jsonArrAppend(key, path, values)).arguments(args).converter((v)->v).run();
	}

	private List<Long> jsonArrIndex(final com.buession.redis.core.Command.Executor<UnifiedJedis, List<Long>> executor,
									final CommandArguments args) {
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.JSON_ARRINDEX).executor(executor)
				.arguments(args).converter((v)->v).run();
	}

	private List<String> jsonStringGet(final com.buession.redis.core.Command.Executor<UnifiedJedis, Object> executor,
									   final CommandArguments args) {
		return JedisCommandBuilder.<Object, List<String>>newBuilder(client, Command.JSON_GET).executor(executor)
				.arguments(args).converter((v)->{
					final List<Object> temp = (List<Object>) v;
					return temp.stream().map(Object::toString).collect(Collectors.toList());
				}).run();
	}

	private byte[] jsonGet(final com.buession.redis.core.Command.Executor<UnifiedJedis, Object> executor,
						   final CommandArguments args) {
		final ByteArrayDeserializer byteArrayDeserializer = new DefaultByteArrayDeserializer();
		return JedisCommandBuilder.<Object, byte[]>newBuilder(client, Command.JSON_GET).executor(executor)
				.arguments(args).converter((v)->{
					try{
						return byteArrayDeserializer.deserialize(v.toString());
					}catch(DeserializerException e){
						throw null;
					}
				}).run();
	}

	private List<byte[]> jsonBinaryGet(final com.buession.redis.core.Command.Executor<UnifiedJedis, Object> executor,
									   final CommandArguments args) {
		final ByteArrayDeserializer byteArrayDeserializer = new DefaultByteArrayDeserializer();
		return JedisCommandBuilder.<Object, List<byte[]>>newBuilder(client, Command.JSON_GET).executor(executor)
				.arguments(args).converter((v)->{
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
				}).run();
	}

}
