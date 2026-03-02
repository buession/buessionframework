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
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.DelExType;
import com.buession.redis.core.LcsResult;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.LcsArgument;
import com.buession.redis.core.command.args.MSetExArgument;
import com.buession.redis.core.command.args.SetArgument;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * STRING 运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=string" target="_blank">https://redis.io/docs/latest/commands/?group=string</a></p>
 *
 * @author Yong.Teng
 */
public interface StringOperations extends StringCommands, RedisOperations {

	@Override
	default Long append(final String key, final String value) {
		return execute((client)->client.stringOperations().append(key, value));
	}

	@Override
	default Long append(final byte[] key, final byte[] value) {
		return execute((client)->client.stringOperations().append(key, value));
	}

	@Override
	default Long decr(final String key) {
		return execute((client)->client.stringOperations().decr(key));
	}

	@Override
	default Long decr(final byte[] key) {
		return execute((client)->client.stringOperations().decr(key));
	}

	@Override
	default Long decrBy(final String key, final long value) {
		return execute((client)->client.stringOperations().decrBy(key, value));
	}

	@Override
	default Long decrBy(final byte[] key, final long value) {
		return execute((client)->client.stringOperations().decrBy(key, value));
	}

	default Double decrByFloat(final String key, final double value) {
		return execute((client)->client.stringOperations().incrByFloat(key, value * -1));
	}

	default Double decrByFloat(final byte[] key, final double value) {
		return execute((client)->client.stringOperations().incrByFloat(key, value * -1));
	}

	@Override
	default Status delEx(final String key, final DelExType type, final String value) {
		return execute((client)->client.stringOperations().delEx(key, type, value));
	}

	@Override
	default Status delEx(final byte[] key, final DelExType type, final byte[] value) {
		return execute((client)->client.stringOperations().delEx(key, type, value));
	}

	@Override
	default String digest(final String key) {
		return execute((client)->client.stringOperations().digest(key));
	}

	@Override
	default byte[] digest(final byte[] key) {
		return execute((client)->client.stringOperations().digest(key));
	}

	@Override
	default String get(final String key) {
		return execute((client)->client.stringOperations().get(key));
	}

	@Override
	default byte[] get(final byte[] key) {
		return execute((client)->client.stringOperations().get(key));
	}

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/get.html" target="_blank">http://redisdoc.com/string/get.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getObject(final String key);

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/get.html" target="_blank">http://redisdoc.com/string/get.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getObject(final byte[] key);

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/get.html" target="_blank">http://redisdoc.com/string/get.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getObject(final String key, final Class<V> clazz);

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/get.html" target="_blank">http://redisdoc.com/string/get.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getObject(final byte[] key, final Class<V> clazz);

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/get.html" target="_blank">http://redisdoc.com/string/get.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getObject(final String key, final TypeReference<V> type);

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/get.html" target="_blank">http://redisdoc.com/string/get.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getObject(final byte[] key, final TypeReference<V> type);

	@Override
	default String getDel(final String key) {
		return execute((client)->client.stringOperations().getDel(key));
	}

	@Override
	default byte[] getDel(final byte[] key) {
		return execute((client)->client.stringOperations().getDel(key));
	}

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为对象；并删除该 key
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/get.html" target="_blank">http://redisdoc.com/string/get.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getDelObject(final String key);

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为对象；并删除该 key
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/get.html" target="_blank">http://redisdoc.com/string/get.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getDelObject(final byte[] key);

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为 clazz 指定的对象；并删除该 key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getdel/" target="_blank">https://redis.io/commands/getdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getDelObject(final String key, final Class<V> clazz);

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为 clazz 指定的对象；并删除该 key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getdel/" target="_blank">https://redis.io/commands/getdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getDelObject(final byte[] key, final Class<V> clazz);

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为 type 指定的对象；并删除该 key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getdel/" target="_blank">https://redis.io/commands/getdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getDelObject(final String key, final TypeReference<V> type);

	/**
	 * 获取键 key 相关联的字符串值，并将值反序列化为 type 指定的对象；并删除该 key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getdel/" target="_blank">https://redis.io/commands/getdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	<V> V getDelObject(final byte[] key, final TypeReference<V> type);

	@Override
	default String getEx(final String key, final GetExArgument getExArgument) {
		return execute((client)->client.stringOperations().getEx(key, getExArgument));
	}

	@Override
	default byte[] getEx(final byte[] key, final GetExArgument getExArgument) {
		return execute((client)->client.stringOperations().getEx(key, getExArgument));
	}

	/**
	 * 获取键 key 的值反序列化后对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param getExArgument
	 * 		Key 过期时间参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getExObject(final String key, final GetExArgument getExArgument);

	/**
	 * 获取键 key 的值反序列化后对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param getExArgument
	 * 		Key 过期时间参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getExObject(final byte[] key, final GetExArgument getExArgument);

	/**
	 * 获取键 key 的值反序列化为 clazz 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param getExArgument
	 * 		Key 过期时间参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getExObject(final String key, final GetExArgument getExArgument, final Class<V> clazz);

	/**
	 * 获取键 key 的值反序列化为 clazz 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param getExArgument
	 * 		Key 过期时间参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getExObject(final byte[] key, final GetExArgument getExArgument, final Class<V> clazz);

	/**
	 * 获取键 key 的值反序列化为 type 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param getExArgument
	 * 		Key 过期时间参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getExObject(final String key, final GetExArgument getExArgument, final TypeReference<V> type);

	/**
	 * 获取键 key 的值反序列化为 type 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param getExArgument
	 * 		Key 过期时间参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getExObject(final byte[] key, final GetExArgument getExArgument, final TypeReference<V> type);

	@Override
	default String getRange(final String key, final long start, final long end) {
		return execute((client)->client.stringOperations().getRange(key, start, end));
	}

	@Override
	default byte[] getRange(final byte[] key, final long start, final long end) {
		return execute((client)->client.stringOperations().getRange(key, start, end));
	}

	@Override
	default String getSet(final String key, final String value) {
		return execute((client)->client.stringOperations().getSet(key, value));
	}

	@Override
	default byte[] getSet(final byte[] key, final byte[] value) {
		return execute((client)->client.stringOperations().getSet(key, value));
	}

	/**
	 * 将键 key 的值设为 value ，并返回键 key 在被设置之前的旧值反序列化后对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/getset.html" target="_blank">http://redisdoc.com/string/getset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		新值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的旧值反序列化后对象
	 */
	<V> V getSet(final String key, final V value);

	/**
	 * 将键 key 的值设为 value ，并返回键 key 在被设置之前的旧值反序列化后对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/getset.html" target="_blank">http://redisdoc.com/string/getset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		新值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的旧值反序列化后对象
	 */
	<V> V getSet(final byte[] key, final V value);

	/**
	 * 将键 key 的值设为 value ，并返回键 key 在被设置之前的旧值反序列化为 clazz 指定类型后的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/getset.html" target="_blank">http://redisdoc.com/string/getset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		新值
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的旧值反序列化后对象
	 */
	<V> V getSet(final String key, final V value, final Class<V> clazz);

	/**
	 * 将键 key 的值设为 value ，并返回键 key 在被设置之前的旧值反序列化为 clazz 指定类型后的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/getset.html" target="_blank">http://redisdoc.com/string/getset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		新值
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的旧值反序列化后对象
	 */
	<V> V getSet(final byte[] key, final V value, final Class<V> clazz);

	/**
	 * 将键 key 的值设为 value ，并返回键 key 在被设置之前的旧值反序列化为 type 指定类型后的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/getset.html" target="_blank">http://redisdoc.com/string/getset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		新值
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的旧值反序列化后对象
	 */
	<V> V getSet(final String key, final V value, final TypeReference<V> type);

	/**
	 * 将键 key 的值设为 value ，并返回键 key 在被设置之前的旧值反序列化为 type 指定类型后的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/getset.html" target="_blank">http://redisdoc.com/string/getset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		新值
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的旧值反序列化后对象
	 */
	<V> V getSet(final byte[] key, final V value, final TypeReference<V> type);

	@Override
	default Long incr(final String key) {
		return execute((client)->client.stringOperations().incr(key));
	}

	@Override
	default Long incr(final byte[] key) {
		return execute((client)->client.stringOperations().incr(key));
	}

	@Override
	default Long incrBy(final String key, final long value) {
		return execute((client)->client.stringOperations().incrBy(key, value));
	}

	@Override
	default Long incrBy(final byte[] key, final long value) {
		return execute((client)->client.stringOperations().incrBy(key, value));
	}

	@Override
	default Double incrByFloat(final String key, final double value) {
		return execute((client)->client.stringOperations().incrByFloat(key, value));
	}

	@Override
	default Double incrByFloat(final byte[] key, final double value) {
		return execute((client)->client.stringOperations().incrByFloat(key, value));
	}

	@Override
	default LcsResult lcs(final String key1, final String key2) {
		return execute((client)->client.stringOperations().lcs(key1, key2));
	}

	@Override
	default LcsResult lcs(final byte[] key1, final byte[] key2) {
		return execute((client)->client.stringOperations().lcs(key1, key2));
	}

	@Override
	default LcsResult lcs(final String key1, final String key2, final LcsArgument argument) {
		return execute((client)->client.stringOperations().lcs(key1, key2, argument));
	}

	@Override
	default LcsResult lcs(final byte[] key1, final byte[] key2, final LcsArgument argument) {
		return execute((client)->client.stringOperations().lcs(key1, key2, argument));
	}

	@Override
	default List<String> mGet(final String... keys) {
		return execute((client)->client.stringOperations().mGet(keys));
	}

	@Override
	default List<byte[]> mGet(final byte[]... keys) {
		return execute((client)->client.stringOperations().mGet(keys));
	}

	/**
	 * 获取给定的一个或多个字符串键的值，并反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/mget.html" target="_blank">http://redisdoc.com/string/mget.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回一个列表，列表中包含了所有给定键的值的反序列化对象；
	 * 如果给定键不存在 那么这个键的值将以特殊值 null 表示
	 */
	<V> List<V> mGetObject(final String... keys);

	/**
	 * 获取给定的一个或多个字符串键的值，并反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/mget.html" target="_blank">http://redisdoc.com/string/mget.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回一个列表，列表中包含了所有给定键的值的反序列化对象；
	 * 如果给定键不存在 那么这个键的值将以特殊值 null 表示
	 */
	<V> List<V> mGetObject(final byte[]... keys);

	/**
	 * 获取给定的一个或多个字符串键的值，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/mget.html" target="_blank">http://redisdoc.com/string/mget.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回一个列表，列表中包含了所有给定键的值的反序列化对象；
	 * 如果给定键不存在 那么这个键的值将以特殊值 null 表示
	 */
	<V> List<V> mGetObject(final String[] keys, final Class<V> clazz);

	/**
	 * 获取给定的一个或多个字符串键的值，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/mget.html" target="_blank">http://redisdoc.com/string/mget.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回一个列表，列表中包含了所有给定键的值的反序列化对象；
	 * 如果给定键不存在 那么这个键的值将以特殊值 null 表示
	 */
	<V> List<V> mGetObject(final byte[][] keys, final Class<V> clazz);

	/**
	 * 获取给定的一个或多个字符串键的值，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/mget.html" target="_blank">http://redisdoc.com/string/mget.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回一个列表，列表中包含了所有给定键的值的反序列化对象；
	 * 如果给定键不存在 那么这个键的值将以特殊值 null 表示
	 */
	<V> List<V> mGetObject(final String[] keys, final TypeReference<V> type);

	/**
	 * 获取给定的一个或多个字符串键的值，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/mget.html" target="_blank">http://redisdoc.com/string/mget.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回一个列表，列表中包含了所有给定键的值的反序列化对象；
	 * 如果给定键不存在 那么这个键的值将以特殊值 null 表示
	 */
	<V> List<V> mGetObject(final byte[][] keys, final TypeReference<V> type);

	@Override
	default Status mSet(final Map<String, String> values) {
		return execute((client)->client.stringOperations().mSet(values));
	}

	/**
	 * 同时为多个键设置值，如果某个给定键已经存在 那么将使用新值去覆盖旧值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/mset/" target="_blank">https://redis.io/docs/latest/commands/mset/</a></p>
	 *
	 * @param values
	 * 		键值对
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status mSet(final List<KeyValue<String, String>> values) {
		if(values == null){
			return Status.FAILURE;
		}else{
			Map<String, String> data = new LinkedHashMap<>(values.size());

			for(KeyValue<String, String> v : values){
				data.put(v.getKey(), v.getValue());
			}

			return mSet(data);
		}
	}

	/**
	 * 同时为多个键设置值，如果某个给定键已经存在 那么将使用新值去覆盖旧值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/mset/" target="_blank">https://redis.io/docs/latest/commands/mset/</a></p>
	 *
	 * @param values
	 * 		键值对
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status mSet(final KeyValue<String, String>... values) {
		if(values == null){
			return Status.FAILURE;
		}else{
			Map<String, String> data = new LinkedHashMap<>(values.length);

			for(KeyValue<String, String> v : values){
				data.put(v.getKey(), v.getValue());
			}

			return mSet(data);
		}
	}

	@Override
	default Status mSetEx(final Map<String, String> values, final MSetExArgument argument) {
		return execute((client)->client.stringOperations().mSetEx(values, argument));
	}

	/**
	 * Atomically sets multiple string keys with an optional shared expiration in a single operation.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/msetnx/" target="_blank">https://redis.io/docs/latest/commands/msetnx/</a></p>
	 *
	 * @param values
	 * 		键值对
	 * @param argument
	 * 		参数
	 *
	 * @return 当所有给定键都设置成功时，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status mSetEx(final List<KeyValue<String, String>> values, final MSetExArgument argument) {
		if(values == null){
			return Status.FAILURE;
		}else{
			Map<String, String> data = new LinkedHashMap<>(values.size());

			for(KeyValue<String, String> v : values){
				data.put(v.getKey(), v.getValue());
			}

			return mSetEx(data, argument);
		}
	}

	/**
	 * Atomically sets multiple string keys with an optional shared expiration in a single operation.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/msetnx/" target="_blank">https://redis.io/docs/latest/commands/msetnx/</a></p>
	 *
	 * @param values
	 * 		键值对
	 * @param argument
	 * 		参数
	 *
	 * @return 当所有给定键都设置成功时，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status mSetEx(final KeyValue<String, String>[] values, final MSetExArgument argument) {
		if(values == null){
			return Status.FAILURE;
		}else{
			Map<String, String> data = new LinkedHashMap<>(values.length);

			for(KeyValue<String, String> v : values){
				data.put(v.getKey(), v.getValue());
			}

			return mSetEx(data, argument);
		}
	}

	@Override
	default Status mSetNx(final Map<String, String> values) {
		return execute((client)->client.stringOperations().mSetNx(values));
	}

	/**
	 * 当且仅当所有给定键都不存在时， 为所有给定键设置值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/msetnx/" target="_blank">https://redis.io/docs/latest/commands/msetnx/</a></p>
	 *
	 * @param values
	 * 		键值对
	 *
	 * @return 当所有给定键都设置成功时，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status mSetNx(final List<KeyValue<String, String>> values) {
		if(values == null){
			return Status.FAILURE;
		}else{
			Map<String, String> data = new LinkedHashMap<>(values.size());

			for(KeyValue<String, String> v : values){
				data.put(v.getKey(), v.getValue());
			}

			return mSetNx(data);
		}
	}

	/**
	 * 当且仅当所有给定键都不存在时， 为所有给定键设置值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/msetnx/" target="_blank">https://redis.io/docs/latest/commands/msetnx/</a></p>
	 *
	 * @param values
	 * 		键值对
	 *
	 * @return 当所有给定键都设置成功时，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status mSetNx(final KeyValue<String, String>... values) {
		if(values == null){
			return Status.FAILURE;
		}else{
			Map<String, String> data = new LinkedHashMap<>(values.length);

			for(KeyValue<String, String> v : values){
				data.put(v.getKey(), v.getValue());
			}

			return mSetNx(data);
		}
	}

	@Override
	default Status pSetEx(final String key, String value, int lifetime) {
		return execute((client)->client.stringOperations().pSetEx(key, value, lifetime));
	}

	@Override
	default Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		return execute((client)->client.stringOperations().pSetEx(key, value, lifetime));
	}

	/**
	 * 将键 key 的值设置为 value ，并将键 key 的生存时间设置为 lifetime；
	 * 如果键 key 已经存在，那么将覆盖已有的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/psetex.html" target="_blank">http://redisdoc.com/string/psetex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param lifetime
	 * 		生存时间（单秒：毫秒）
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status pSetEx(final String key, final V value, final int lifetime);

	/**
	 * 将键 key 的值设置为 value ，并将键 key 的生存时间设置为 lifetime；
	 * 如果键 key 已经存在，那么将覆盖已有的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/psetex.html" target="_blank">http://redisdoc.com/string/psetex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param lifetime
	 * 		生存时间（单秒：毫秒）
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status pSetEx(final byte[] key, final V value, final int lifetime);

	@Override
	default Status set(final String key, final String value) {
		return execute((client)->client.stringOperations().set(key, value));
	}

	@Override
	default Status set(final byte[] key, final byte[] value) {
		return execute((client)->client.stringOperations().set(key, value));
	}

	@Override
	default Status set(final String key, final String value, final SetArgument argument) {
		return execute((client)->client.stringOperations().set(key, value, argument));
	}

	@Override
	default Status set(final byte[] key, final byte[] value, final SetArgument argument) {
		return execute((client)->client.stringOperations().set(key, value, argument));
	}

	/**
	 * 将对象 value 序列化后关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/set.html" target="_blank">http://redisdoc.com/string/set.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final String key, final V value);

	/**
	 * 将对象 value 序列化后关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/set.html" target="_blank">http://redisdoc.com/string/set.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final byte[] key, final V value);

	/**
	 * 将字符串值 value 关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/set.html" target="_blank">http://redisdoc.com/string/set.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final String key, final V value, final SetArgument argument);

	/**
	 * 将字符串值 value 关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/set.html" target="_blank">http://redisdoc.com/string/set.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final byte[] key, final V value, final SetArgument argument);

	@Override
	default Status setEx(final String key, final String value, final int lifetime) {
		return execute((client)->client.stringOperations().setEx(key, value, lifetime));
	}

	@Override
	default Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		return execute((client)->client.stringOperations().setEx(key, value, lifetime));
	}

	/**
	 * 将键 key 的值设置为 value ，并将键 key 的生存时间设置为 lifetime；
	 * 如果键 key 已经存在，那么将覆盖已有的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/setex.html" target="_blank">http://redisdoc.com/string/setex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param lifetime
	 * 		生存时间（单秒：秒）
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status setEx(final String key, final V value, final int lifetime);

	/**
	 * 将键 key 的值设置为 value ，并将键 key 的生存时间设置为 lifetime；
	 * 如果键 key 已经存在，那么将覆盖已有的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/setex.html" target="_blank">http://redisdoc.com/string/setex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param lifetime
	 * 		生存时间（单秒：秒）
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status setEx(final byte[] key, final V value, final int lifetime);

	@Override
	default Status setNx(final String key, final String value) {
		return execute((client)->client.stringOperations().setNx(key, value));
	}

	@Override
	default Status setNx(final byte[] key, final byte[] value) {
		return execute((client)->client.stringOperations().setNx(key, value));
	}

	/**
	 * 当键 key 不存在的情况下，将键 key 的值设置为 value
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/setnx.html" target="_blank">http://redisdoc.com/string/setnx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status setNx(final String key, final V value);

	/**
	 * 当键 key 不存在的情况下，将键 key 的值设置为 value
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/setnx.html" target="_blank">http://redisdoc.com/string/setnx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status setNx(final byte[] key, final V value);

	@Override
	default Long setRange(final String key, final long offset, final String value) {
		return execute((client)->client.stringOperations().setRange(key, offset, value));
	}

	@Override
	default Long setRange(final byte[] key, final long offset, final byte[] value) {
		return execute((client)->client.stringOperations().setRange(key, offset, value));
	}

	@Override
	default Long strlen(final String key) {
		return execute((client)->client.stringOperations().strlen(key));
	}

	@Override
	default Long strlen(final byte[] key) {
		return execute((client)->client.stringOperations().strlen(key));
	}

	@Override
	default String substr(final String key, final long start, final long end) {
		return execute((client)->client.stringOperations().substr(key, start, end));
	}

	@Override
	default byte[] substr(final byte[] key, final long start, final long end) {
		return execute((client)->client.stringOperations().substr(key, start, end));
	}

}
