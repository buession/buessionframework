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
package com.buession.redis.core.operations;

import com.buession.core.type.TypeReference;
import com.buession.lang.Status;
import com.buession.redis.core.JsonType;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.core.command.JsonCommands;
import com.buession.redis.core.command.args.json.JsonGetArgument;
import com.buession.redis.core.command.args.json.KeyPathValue;
import com.buession.redis.utils.KeyUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * JSON 命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=json" target="_blank">https://redis.io/docs/latest/commands/?group=json</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface JsonOperations extends JsonCommands, RedisOperations {

	@Override
	default List<Long> jsonArrAppend(final String key, final String... values) {
		return doExecute((cmd)->cmd.jsonArrAppend(KeyUtils.rawKey(this, key), values));
	}

	@Override
	default List<Long> jsonArrAppend(final byte[] key, final byte[]... values) {
		return doExecute((cmd)->cmd.jsonArrAppend(KeyUtils.rawKey(this, key), values));
	}

	@Override
	default List<Long> jsonArrAppend(final String key, final String path, final String... values) {
		return doExecute((cmd)->cmd.jsonArrAppend(KeyUtils.rawKey(this, key), path, values));
	}

	@Override
	default List<Long> jsonArrAppend(final byte[] key, final byte[] path, final byte[]... values) {
		return doExecute((cmd)->cmd.jsonArrAppend(KeyUtils.rawKey(this, key), path, values));
	}

	@Override
	default List<Long> jsonArrIndex(final String key, final String path, final String value) {
		return doExecute((cmd)->cmd.jsonArrIndex(KeyUtils.rawKey(this, key), path, value));
	}

	@Override
	default List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value) {
		return doExecute((cmd)->cmd.jsonArrIndex(KeyUtils.rawKey(this, key), path, value));
	}

	@Override
	default List<Long> jsonArrIndex(final String key, final String path, final String value, final int start) {
		return doExecute((cmd)->cmd.jsonArrIndex(KeyUtils.rawKey(this, key), path, value, start));
	}

	@Override
	default List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start) {
		return doExecute((cmd)->cmd.jsonArrIndex(KeyUtils.rawKey(this, key), path, value, start));
	}

	@Override
	default List<Long> jsonArrIndex(final String key, final String path, final String value, final int start,
	                                final int stop) {
		return execute(
				(client)->client.jsonCommands().jsonArrIndex(KeyUtils.rawKey(this, key), path, value, start, stop));
	}

	@Override
	default List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start,
	                                final int stop) {
		return execute(
				(client)->client.jsonCommands().jsonArrIndex(KeyUtils.rawKey(this, key), path, value, start, stop));
	}

	@Override
	default List<Long> jsonArrInsert(final String key, final String path, final int index, final String... values) {
		return doExecute((cmd)->cmd.jsonArrInsert(KeyUtils.rawKey(this, key), path, index, values));
	}

	@Override
	default List<Long> jsonArrInsert(final byte[] key, final byte[] path, final int index, final byte[]... values) {
		return doExecute((cmd)->cmd.jsonArrInsert(KeyUtils.rawKey(this, key), path, index, values));
	}

	@Override
	default Long jsonArrLen(final String key) {
		return doExecute((cmd)->cmd.jsonArrLen(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long jsonArrLen(final byte[] key) {
		return doExecute((cmd)->cmd.jsonArrLen(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<Long> jsonArrLen(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonArrLen(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<Long> jsonArrLen(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonArrLen(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default Object jsonArrPop(final String key) {
		return doExecute((cmd)->cmd.jsonArrPop(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Object jsonArrPop(final byte[] key) {
		return doExecute((cmd)->cmd.jsonArrPop(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<String> jsonArrPop(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonArrPop(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<byte[]> jsonArrPop(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonArrPop(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<String> jsonArrPop(final String key, final String path, final int index) {
		return doExecute((cmd)->cmd.jsonArrPop(KeyUtils.rawKey(this, key), path, index));
	}

	@Override
	default List<byte[]> jsonArrPop(final byte[] key, final byte[] path, final int index) {
		return doExecute((cmd)->cmd.jsonArrPop(KeyUtils.rawKey(this, key), path, index));
	}

	@Override
	default List<Long> jsonArrTrim(final String key, final String path, final int start, final int stop) {
		return doExecute((cmd)->cmd.jsonArrTrim(KeyUtils.rawKey(this, key), path, start, stop));
	}

	@Override
	default List<Long> jsonArrTrim(final byte[] key, final byte[] path, final int start, final int stop) {
		return doExecute((cmd)->cmd.jsonArrTrim(KeyUtils.rawKey(this, key), path, start, stop));
	}

	@Override
	default Long jsonClear(final String key) {
		return doExecute((cmd)->cmd.jsonClear(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long jsonClear(final byte[] key) {
		return doExecute((cmd)->cmd.jsonClear(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long jsonClear(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonClear(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default Long jsonClear(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonClear(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default Long jsonDebugMemory(final String key) {
		return doExecute((cmd)->cmd.jsonDebugMemory(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long jsonDebugMemory(final byte[] key) {
		return doExecute((cmd)->cmd.jsonDebugMemory(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<Long> jsonDebugMemory(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonDebugMemory(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<Long> jsonDebugMemory(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonDebugMemory(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default Long jsonDel(final String key) {
		return doExecute((cmd)->cmd.jsonDel(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long jsonDel(final byte[] key) {
		return doExecute((cmd)->cmd.jsonDel(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long jsonDel(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonDel(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default Long jsonDel(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonDel(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default Long jsonForget(final String key) {
		return doExecute((cmd)->cmd.jsonForget(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long jsonForget(final byte[] key) {
		return doExecute((cmd)->cmd.jsonForget(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long jsonForget(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonForget(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default Long jsonForget(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonForget(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default String jsonGet(final String key) {
		return doExecute((cmd)->cmd.jsonGet(KeyUtils.rawKey(this, key)));
	}

	@Override
	default byte[] jsonGet(final byte[] key) {
		return doExecute((cmd)->cmd.jsonGet(KeyUtils.rawKey(this, key)));
	}

	@Override
	default String jsonGet(final String key, final JsonGetArgument argument) {
		return doExecute((cmd)->cmd.jsonGet(KeyUtils.rawKey(this, key), argument));
	}

	@Override
	default byte[] jsonGet(final byte[] key, final JsonGetArgument argument) {
		return doExecute((cmd)->cmd.jsonGet(KeyUtils.rawKey(this, key), argument));
	}

	@Override
	default List<String> jsonGet(final String key, final String... path) {
		return doExecute((cmd)->cmd.jsonGet(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<byte[]> jsonGet(final byte[] key, final byte[]... path) {
		return doExecute((cmd)->cmd.jsonGet(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<String> jsonGet(final String key, final JsonGetArgument argument, final String... path) {
		return doExecute((cmd)->cmd.jsonGet(KeyUtils.rawKey(this, key), argument, path));
	}

	@Override
	default List<byte[]> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[]... path) {
		return doExecute((cmd)->cmd.jsonGet(KeyUtils.rawKey(this, key), argument, path));
	}

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> V jsonGet(final String key, final Class<V> clazz);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> V jsonGet(final byte[] key, final Class<V> clazz);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> V jsonGet(final String key, final TypeReference<V> type);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> V jsonGet(final byte[] key, final TypeReference<V> type);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> V jsonGet(final String key, final JsonGetArgument argument, final Class<V> clazz);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> V jsonGet(final byte[] key, final JsonGetArgument argument, final Class<V> clazz);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> V jsonGet(final String key, final JsonGetArgument argument, final TypeReference<V> type);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> V jsonGet(final byte[] key, final JsonGetArgument argument, final TypeReference<V> type);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonGet(final String key, final String[] path, final Class<V> clazz);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonGet(final byte[] key, final byte[][] path, final Class<V> clazz);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonGet(final String key, final String[] path, final TypeReference<V> type);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonGet(final byte[] key, final byte[][] path, final TypeReference<V> type);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param path
	 * 		JSONPath 表达式
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonGet(final String key, final JsonGetArgument argument, final String[] path, final Class<V> clazz);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param path
	 * 		JSONPath 表达式
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[][] path, final Class<V> clazz);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param path
	 * 		JSONPath 表达式
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonGet(final String key, final JsonGetArgument argument, final String[] path,
	                    final TypeReference<V> type);

	/**
	 * 从 JSON 文档获取指定路径的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param path
	 * 		JSONPath 表达式
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[][] path,
	                    final TypeReference<V> type);

	@Override
	default Status jsonMerge(final String key, final String path, final String value) {
		return doExecute((cmd)->cmd.jsonMerge(KeyUtils.rawKey(this, key), path, value));
	}

	@Override
	default Status jsonMerge(final byte[] key, final byte[] path, final byte[] value) {
		return doExecute((cmd)->cmd.jsonMerge(KeyUtils.rawKey(this, key), path, value));
	}

	@Override
	default List<String> jsonMGet(final String[] keys, final String path) {
		return doExecute((cmd)->cmd.jsonMGet(KeyUtils.rawKeys(this, keys), path));
	}

	@Override
	default List<byte[]> jsonMGet(final byte[][] keys, final byte[] path) {
		return doExecute((cmd)->cmd.jsonMGet(KeyUtils.rawKeys(this, keys), path));
	}

	/**
	 * 从多个 Redis key 中获取相同 JSON 路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.mget/" target="_blank">https://redis.io/docs/latest/commands/json.mget/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonMGet(final String[] keys, final String path, final Class<V> clazz);

	/**
	 * 从多个 Redis key 中获取相同 JSON 路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.mget/" target="_blank">https://redis.io/docs/latest/commands/json.mget/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonMGet(final byte[][] keys, final byte[] path, final Class<V> clazz);

	/**
	 * 从多个 Redis key 中获取相同 JSON 路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.mget/" target="_blank">https://redis.io/docs/latest/commands/json.mget/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonMGet(final String[] keys, final String path, final TypeReference<V> type);

	/**
	 * 从多个 Redis key 中获取相同 JSON 路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.mget/" target="_blank">https://redis.io/docs/latest/commands/json.mget/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 文档指定路径的值
	 */
	<V> List<V> jsonMGet(final byte[][] keys, final byte[] path, final TypeReference<V> type);

	@Override
	default Status jsonMSet(final KeyPathValue... data) {
		return doExecute((cmd)->cmd.jsonMSet(data));
	}

	@Override
	default List<Number> jsonNumIncrBy(final String key, final String path, final Number value) {
		return doExecute((cmd)->cmd.jsonNumIncrBy(KeyUtils.rawKey(this, key), path, value));
	}

	@Override
	default List<Number> jsonNumIncrBy(final byte[] key, final byte[] path, final Number value) {
		return doExecute((cmd)->cmd.jsonNumIncrBy(KeyUtils.rawKey(this, key), path, value));
	}

	/**
	 * 对 JSON 文档中的数值字段执行原子递增 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.numincrby/" target="_blank">https://redis.io/docs/latest/commands/json.numincrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 递增后的新值
	 */
	default List<Number> jsonNumIncr(final String key, final String path) {
		return jsonNumIncrBy(key, path, 1);
	}

	/**
	 * 对 JSON 文档中的数值字段执行原子递增 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.numincrby/" target="_blank">https://redis.io/docs/latest/commands/json.numincrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 递增后的新值
	 */
	default List<Number> jsonNumIncr(final byte[] key, final byte[] path) {
		return jsonNumIncrBy(key, path, 1);
	}

	/**
	 * 对 JSON 文档中的数值字段执行原子递减 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.numincrby/" target="_blank">https://redis.io/docs/latest/commands/json.numincrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 递减后的新值
	 */
	default List<Number> jsonNumDecr(final String key, final String path) {
		return jsonNumIncrBy(key, path, -1);
	}

	/**
	 * 对 JSON 文档中的数值字段执行原子递减 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.numincrby/" target="_blank">https://redis.io/docs/latest/commands/json.numincrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 递减后的新值
	 */
	default List<Number> jsonNumDecrBy(final byte[] key, final byte[] path) {
		return jsonNumIncrBy(key, path, -1);
	}

	/**
	 * 对 JSON 文档中的数值字段执行原子递减
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.numincrby/" target="_blank">https://redis.io/docs/latest/commands/json.numincrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		递减的值
	 *
	 * @return 递增后的新值
	 */
	default List<Number> jsonNumDecrBy(final String key, final String path, final Number value) {
		BigDecimal bigDecimal = new BigDecimal(value.toString());
		return jsonNumIncrBy(key, path, bigDecimal.multiply(new BigDecimal("-1")).doubleValue());
	}

	/**
	 * 对 JSON 文档中的数值字段执行原子递减
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.numincrby/" target="_blank">https://redis.io/docs/latest/commands/json.numincrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		递减的值
	 *
	 * @return 递减后的新值
	 */
	default List<Number> jsonNumDecrBy(final byte[] key, final byte[] path, final Number value) {
		BigDecimal bigDecimal = new BigDecimal(value.toString());
		return jsonNumIncrBy(key, path, bigDecimal.multiply(new BigDecimal("-1")).doubleValue());
	}

	@Override
	default List<Number> jsonNumMultBy(final String key, final String path, final Number value) {
		return doExecute((cmd)->cmd.jsonNumMultBy(KeyUtils.rawKey(this, key), path, value));
	}

	@Override
	default List<Number> jsonNumMultBy(final byte[] key, final byte[] path, final Number value) {
		return doExecute((cmd)->cmd.jsonNumMultBy(KeyUtils.rawKey(this, key), path, value));
	}

	@Override
	default List<List<String>> jsonObjKeys(final String key) {
		return doExecute((cmd)->cmd.jsonObjKeys(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<List<byte[]>> jsonObjKeys(final byte[] key) {
		return doExecute((cmd)->cmd.jsonObjKeys(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<List<String>> jsonObjKeys(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonObjKeys(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<List<byte[]>> jsonObjKeys(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonObjKeys(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default Long jsonObjLen(final String key) {
		return doExecute((cmd)->cmd.jsonObjLen(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long jsonObjLen(final byte[] key) {
		return doExecute((cmd)->cmd.jsonObjLen(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<Long> jsonObjLen(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonObjLen(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<Long> jsonObjLen(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonObjLen(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<Object> jsonResp(final String key) {
		return doExecute((cmd)->cmd.jsonResp(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<Object> jsonResp(final byte[] key) {
		return doExecute((cmd)->cmd.jsonResp(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<Object> jsonResp(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonResp(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<Object> jsonResp(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonResp(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default Status jsonSet(final String key, final String path, final String value) {
		return doExecute((cmd)->cmd.jsonSet(KeyUtils.rawKey(this, key), path, value));
	}

	@Override
	default Status jsonSet(final byte[] key, final byte[] path, final byte[] value) {
		return doExecute((cmd)->cmd.jsonSet(KeyUtils.rawKey(this, key), path, value));
	}

	@Override
	default Status jsonSet(final String key, final String path, final String value, final NxXx nxXx) {
		return doExecute((cmd)->cmd.jsonSet(KeyUtils.rawKey(this, key), path, value, nxXx));
	}

	@Override
	default Status jsonSet(final byte[] key, final byte[] path, final byte[] value, final NxXx nxXx) {
		return doExecute((cmd)->cmd.jsonSet(KeyUtils.rawKey(this, key), path, value, nxXx));
	}

	/**
	 * 创建或更新 JSON 文档
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.set/" target="_blank">https://redis.io/docs/latest/commands/json.set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果
	 */
	<V> Status jsonSet(final String key, final String path, final V value);

	/**
	 * 创建或更新 JSON 文档
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.set/" target="_blank">https://redis.io/docs/latest/commands/json.set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果
	 */
	<V> Status jsonSet(final byte[] key, final byte[] path, final V value);

	/**
	 * 创建或更新 JSON 文档
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.set/" target="_blank">https://redis.io/docs/latest/commands/json.set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 * @param nxXx
	 * 		NX 仅当 key 不存在 时设置;XX 仅当 key 已存在 时设置
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果
	 */
	<V> Status jsonSet(final String key, final String path, final V value, final NxXx nxXx);

	/**
	 * 创建或更新 JSON 文档
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.set/" target="_blank">https://redis.io/docs/latest/commands/json.set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 * @param nxXx
	 * 		NX 仅当 key 不存在 时设置;XX 仅当 key 已存在 时设置
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果
	 */
	<V> Status jsonSet(final byte[] key, final byte[] path, final V value, final NxXx nxXx);

	@Override
	default List<Long> jsonStrAppend(final String key, final String value) {
		return doExecute((cmd)->cmd.jsonStrAppend(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default List<Long> jsonStrAppend(final byte[] key, final byte[] value) {
		return doExecute((cmd)->cmd.jsonStrAppend(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default List<Long> jsonStrAppend(final String key, final String path, final String value) {
		return doExecute((cmd)->cmd.jsonStrAppend(KeyUtils.rawKey(this, key), path, value));
	}

	@Override
	default List<Long> jsonStrAppend(final byte[] key, final byte[] path, final byte[] value) {
		return doExecute((cmd)->cmd.jsonStrAppend(KeyUtils.rawKey(this, key), path, value));
	}

	@Override
	default Long jsonStrLen(final String key) {
		return doExecute((cmd)->cmd.jsonStrLen(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long jsonStrLen(final byte[] key) {
		return doExecute((cmd)->cmd.jsonStrLen(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<Long> jsonStrLen(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonStrLen(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<Long> jsonStrLen(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonStrLen(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<Status> jsonToggle(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonToggle(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<Status> jsonToggle(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonToggle(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default JsonType jsonType(final String key) {
		return doExecute((cmd)->cmd.jsonType(KeyUtils.rawKey(this, key)));
	}

	@Override
	default JsonType jsonType(final byte[] key) {
		return doExecute((cmd)->cmd.jsonType(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<JsonType> jsonType(final String key, final String path) {
		return doExecute((cmd)->cmd.jsonType(KeyUtils.rawKey(this, key), path));
	}

	@Override
	default List<JsonType> jsonType(final byte[] key, final byte[] path) {
		return doExecute((cmd)->cmd.jsonType(KeyUtils.rawKey(this, key), path));
	}

	private <R> R doExecute(final Command.Executor<JsonCommands, R> executor) {
		return execute((client)->executor.execute(client.jsonCommands()));
	}

}
