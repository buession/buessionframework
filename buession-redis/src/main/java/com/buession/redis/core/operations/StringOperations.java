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

import com.buession.core.collect.Arrays;
import com.buession.core.type.TypeReference;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.GetExType;
import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.core.command.args.PxExType;
import com.buession.redis.core.command.args.string.CompareCondition;
import com.buession.redis.core.LcsResult;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.command.args.string.LcsArgument;
import com.buession.redis.core.command.args.string.SetType;
import com.buession.redis.utils.KeyUtils;

import java.time.Duration;
import java.util.List;

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
		return doExecute((cmd)->cmd.append(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Long append(final byte[] key, final byte[] value) {
		return doExecute((cmd)->cmd.append(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Long decr(final String key) {
		return doExecute((cmd)->cmd.decr(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long decr(final byte[] key) {
		return doExecute((cmd)->cmd.decr(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long decrBy(final String key, final long value) {
		return doExecute((cmd)->cmd.decrBy(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Long decrBy(final byte[] key, final long value) {
		return doExecute((cmd)->cmd.decrBy(KeyUtils.rawKey(this, key), value));
	}

	default Double decrByFloat(final String key, final double value) {
		return doExecute((cmd)->cmd.incrByFloat(KeyUtils.rawKey(this, key), value * -1));
	}

	default Double decrByFloat(final byte[] key, final double value) {
		return doExecute((cmd)->cmd.incrByFloat(KeyUtils.rawKey(this, key), value * -1));
	}

	@Override
	default Status delEx(final String key, final CompareCondition type, final String value) {
		return doExecute((cmd)->cmd.delEx(KeyUtils.rawKey(this, key), type, value));
	}

	@Override
	default Status delEx(final byte[] key, final CompareCondition type, final byte[] value) {
		return doExecute((cmd)->cmd.delEx(KeyUtils.rawKey(this, key), type, value));
	}

	@Override
	default String digest(final String key) {
		return doExecute((cmd)->cmd.digest(KeyUtils.rawKey(this, key)));
	}

	@Override
	default byte[] digest(final byte[] key) {
		return doExecute((cmd)->cmd.digest(KeyUtils.rawKey(this, key)));
	}

	@Override
	default String get(final String key) {
		return doExecute((cmd)->cmd.get(KeyUtils.rawKey(this, key)));
	}

	@Override
	default byte[] get(final byte[] key) {
		return doExecute((cmd)->cmd.get(KeyUtils.rawKey(this, key)));
	}

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
	<V> V get(final String key, final Class<V> clazz);

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
	<V> V get(final byte[] key, final Class<V> clazz);

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
	<V> V get(final String key, final TypeReference<V> type);

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
	<V> V get(final byte[] key, final TypeReference<V> type);

	@Override
	default String getDel(final String key) {
		return doExecute((cmd)->cmd.getDel(KeyUtils.rawKey(this, key)));
	}

	@Override
	default byte[] getDel(final byte[] key) {
		return doExecute((cmd)->cmd.getDel(KeyUtils.rawKey(this, key)));
	}

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
	<V> V getDel(final String key, final Class<V> clazz);

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
	<V> V getDel(final byte[] key, final Class<V> clazz);

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
	<V> V getDel(final String key, final TypeReference<V> type);

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
	<V> V getDel(final byte[] key, final TypeReference<V> type);

	@Override
	default String getEx(final String key) {
		return doExecute((cmd)->cmd.getEx(KeyUtils.rawKey(this, key)));
	}

	@Override
	default byte[] getEx(final byte[] key) {
		return doExecute((cmd)->cmd.getEx(KeyUtils.rawKey(this, key)));
	}

	@Override
	default String getEx(final String key, final GetExType type, final long expires) {
		return doExecute((cmd)->cmd.getEx(KeyUtils.rawKey(this, key), type, expires));
	}

	@Override
	default byte[] getEx(final byte[] key, final GetExType type, final long expires) {
		return doExecute((cmd)->cmd.getEx(KeyUtils.rawKey(this, key), type, expires));
	}

	/**
	 * 获取键 key 的值反序列化为 clazz 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getEx(final String key, final Class<V> clazz);

	/**
	 * 获取键 key 的值反序列化为 clazz 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getEx(final byte[] key, final Class<V> clazz);

	/**
	 * 获取键 key 的值反序列化为 type 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getEx(final String key, final TypeReference<V> type);

	/**
	 * 获取键 key 的值反序列化为 type 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getEx(final byte[] key, final TypeReference<V> type);

	/**
	 * 获取键 key 的值反序列化为 clazz 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param exType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getEx(final String key, final GetExType exType, final long expires, final Class<V> clazz);

	/**
	 * 获取键 key 的值反序列化为 clazz 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param exType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getEx(final byte[] key, final GetExType exType, final long expires, final Class<V> clazz);

	/**
	 * 获取键 key 的值反序列化为 type 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param exType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getEx(final String key, final GetExType exType, final long expires, final TypeReference<V> type);

	/**
	 * 获取键 key 的值反序列化为 type 指定类型后的对象，并重置 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/getex/" target="_blank">https://redis.io/commands/getex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param exType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 键 key 的值反序列化后对象
	 */
	<V> V getEx(final byte[] key, final GetExType exType, final long expires, final TypeReference<V> type);

	@Override
	default String getRange(final String key, final long start, final long end) {
		return doExecute((cmd)->cmd.getRange(KeyUtils.rawKey(this, key), start, end));
	}

	@Override
	default byte[] getRange(final byte[] key, final long start, final long end) {
		return doExecute((cmd)->cmd.getRange(KeyUtils.rawKey(this, key), start, end));
	}

	@Override
	default String getSet(final String key, final String value) {
		return doExecute((cmd)->cmd.getSet(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default byte[] getSet(final byte[] key, final byte[] value) {
		return doExecute((cmd)->cmd.getSet(KeyUtils.rawKey(this, key), value));
	}

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
		return doExecute((cmd)->cmd.incr(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long incr(final byte[] key) {
		return doExecute((cmd)->cmd.incr(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long incrBy(final String key, final long value) {
		return doExecute((cmd)->cmd.incrBy(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Long incrBy(final byte[] key, final long value) {
		return doExecute((cmd)->cmd.incrBy(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Double incrByFloat(final String key, final double value) {
		return doExecute((cmd)->cmd.incrByFloat(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Double incrByFloat(final byte[] key, final double value) {
		return doExecute((cmd)->cmd.incrByFloat(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default LcsResult lcs(final String key1, final String key2) {
		return doExecute((cmd)->cmd.lcs(KeyUtils.rawKey(this, key1), KeyUtils.rawKey(this, key2)));
	}

	@Override
	default LcsResult lcs(final byte[] key1, final byte[] key2) {
		return doExecute((cmd)->cmd.lcs(KeyUtils.rawKey(this, key1), KeyUtils.rawKey(this, key2)));
	}

	@Override
	default LcsResult lcs(final String key1, final String key2, final LcsArgument argument) {
		return doExecute((cmd)->cmd
				.lcs(KeyUtils.rawKey(this, key1), KeyUtils.rawKey(this, key2), argument));
	}

	@Override
	default LcsResult lcs(final byte[] key1, final byte[] key2, final LcsArgument argument) {
		return doExecute((cmd)->cmd
				.lcs(KeyUtils.rawKey(this, key1), KeyUtils.rawKey(this, key2), argument));
	}

	@Override
	default List<String> mGet(final String... keys) {
		return doExecute((cmd)->cmd.mGet(KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default List<byte[]> mGet(final byte[]... keys) {
		return doExecute((cmd)->cmd.mGet(KeyUtils.rawKeys(this, keys)));
	}

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
	<V> List<V> mGet(final String[] keys, final Class<V> clazz);

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
	<V> List<V> mGet(final byte[][] keys, final Class<V> clazz);

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
	<V> List<V> mGet(final String[] keys, final TypeReference<V> type);

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
	<V> List<V> mGet(final byte[][] keys, final TypeReference<V> type);

	@SuppressWarnings({"unchecked"})
	@Override
	default Status mSet(final KeyValue<String, String>... data) {
		final KeyValue<String, String>[] newData = Arrays.map(data, (item)->new KeyValue<>(KeyUtils.rawKey(this,
				item.getKey()), item.getValue()));
		return doExecute((cmd)->cmd.mSet(newData));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Status mSetEx(final KeyValue<String, String>... data) {
		final KeyValue<String, String>[] newData = Arrays.map(data, (item)->new KeyValue<>(KeyUtils.rawKey(this,
				item.getKey()), item.getValue()));
		return doExecute((cmd)->cmd.mSetEx(newData));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Status mSetEx(final NxXx nxXx, final KeyValue<String, String>... data) {
		final KeyValue<String, String>[] newData = Arrays.map(data, (item)->new KeyValue<>(KeyUtils.rawKey(this,
				item.getKey()), item.getValue()));
		return doExecute((cmd)->cmd.mSetEx(nxXx, newData));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Status mSetEx(final NxXx nxXx, final PxExType exType, final long expires,
	                      final KeyValue<String, String>... data) {
		final KeyValue<String, String>[] newData = Arrays.map(data, (item)->new KeyValue<>(KeyUtils.rawKey(this,
				item.getKey()), item.getValue()));
		return doExecute((cmd)->cmd.mSetEx(nxXx, exType, expires, newData));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Status mSetEx(final PxExType exType, final long expires, final KeyValue<String, String>... data) {
		final KeyValue<String, String>[] newData = Arrays.map(data, (item)->new KeyValue<>(KeyUtils.rawKey(this,
				item.getKey()), item.getValue()));
		return doExecute((cmd)->cmd.mSetEx(exType, expires, newData));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Status mSetNx(final KeyValue<String, String>... data) {
		final KeyValue<String, String>[] newData = Arrays.map(data, (item)->new KeyValue<>(KeyUtils.rawKey(this,
				item.getKey()), item.getValue()));
		return doExecute((cmd)->cmd.mSetNx(newData));
	}

	@Override
	default Status pSetEx(final String key, final String value, final int lifetime) {
		return doExecute((cmd)->cmd.pSetEx(KeyUtils.rawKey(this, key), value, lifetime));
	}

	@Override
	default Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		return doExecute((cmd)->cmd.pSetEx(KeyUtils.rawKey(this, key), value, lifetime));
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
	 * @param duration
	 * 		生存时间
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status pSetEx(final String key, final String value, final Duration duration) {
		return pSetEx(key, value, (int) duration.toMillis());
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
	 * @param duration
	 * 		生存时间
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status pSetEx(final byte[] key, final byte[] value, final Duration duration) {
		return pSetEx(key, value, (int) duration.toMillis());
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
	 * @param duration
	 * 		生存时间
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default <V> Status pSetEx(final String key, final V value, final Duration duration) {
		return pSetEx(key, value, (int) duration.toMillis());
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
	 * @param duration
	 * 		生存时间
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default <V> Status pSetEx(final byte[] key, final V value, final Duration duration) {
		return pSetEx(key, value, (int) duration.toMillis());
	}

	@Override
	default Status set(final String key, final String value) {
		return doExecute((cmd)->cmd.set(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Status set(final byte[] key, final byte[] value) {
		return doExecute((cmd)->cmd.set(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Status set(final String key, final String value, final SetType setType) {
		return doExecute((cmd)->cmd.set(KeyUtils.rawKey(this, key), value, setType));
	}

	@Override
	default Status set(final byte[] key, final byte[] value, final SetType setType) {
		return doExecute((cmd)->cmd.set(KeyUtils.rawKey(this, key), value, setType));
	}

	@Override
	default Status set(final String key, final String value, final SetType setType, final PxExType pxExType,
	                   final long expires) {
		return doExecute((cmd)->cmd.set(KeyUtils.rawKey(this, key), value, setType, pxExType, expires));
	}

	@Override
	default Status set(final byte[] key, final byte[] value, final SetType setType, final PxExType pxExType,
	                   final long expires) {
		return doExecute((cmd)->cmd.set(KeyUtils.rawKey(this, key), value, setType, pxExType, expires));
	}

	@Override
	default Status set(final String key, final String value, final PxExType pxExType, final long expires) {
		return doExecute((cmd)->cmd.set(KeyUtils.rawKey(this, key), value, pxExType, expires));
	}

	@Override
	default Status set(final byte[] key, final byte[] value, final PxExType pxExType, final long expires) {
		return doExecute((cmd)->cmd.set(KeyUtils.rawKey(this, key), value, pxExType, expires));
	}

	/**
	 * 将对象 value 序列化后关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/set/" target="_blank">https://redis.io/docs/latest/commands/set/</a></p>
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
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/set/" target="_blank">https://redis.io/docs/latest/commands/set/</a></p>
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
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/set/" target="_blank">https://redis.io/docs/latest/commands/set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param setType
	 * 		SET 方式
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final String key, final V value, final SetType setType);

	/**
	 * 将字符串值 value 关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/set/" target="_blank">https://redis.io/docs/latest/commands/set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param setType
	 * 		SET 方式
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final byte[] key, final V value, final SetType setType);

	/**
	 * 将字符串值 value 关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/set/" target="_blank">https://redis.io/docs/latest/commands/set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param setType
	 * 		SET 方式
	 * @param pxExType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final String key, final V value, final SetType setType, final PxExType pxExType, final long expires);

	/**
	 * 将字符串值 value 关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/set/" target="_blank">https://redis.io/docs/latest/commands/set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param setType
	 * 		SET 方式
	 * @param pxExType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final byte[] key, final V value, final SetType setType, final PxExType pxExType, final long expires);

	/**
	 * 将字符串值 value 关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/set/" target="_blank">https://redis.io/docs/latest/commands/set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param pxExType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final String key, final V value, final PxExType pxExType, final long expires);

	/**
	 * 将字符串值 value 关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/set/" target="_blank">https://redis.io/docs/latest/commands/set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param pxExType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final byte[] key, final V value, final PxExType pxExType, final long expires);

	@Override
	default Status setEx(final String key, final String value, final int lifetime) {
		return doExecute((cmd)->cmd.setEx(KeyUtils.rawKey(this, key), value, lifetime));
	}

	@Override
	default Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		return doExecute((cmd)->cmd.setEx(KeyUtils.rawKey(this, key), value, lifetime));
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
	 * @param duration
	 * 		生存时间
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status setEx(final String key, final String value, final Duration duration) {
		return setEx(key, value, (int) duration.toSeconds());
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
	 * @param duration
	 * 		生存时间
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status setEx(final byte[] key, final byte[] value, final Duration duration) {
		return setEx(key, value, (int) duration.toSeconds());
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
	 * @param duration
	 * 		生存时间
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default <V> Status setEx(final String key, final V value, final Duration duration) {
		return setEx(key, value, (int) duration.toSeconds());
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
	 * @param duration
	 * 		生存时间
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default <V> Status setEx(final byte[] key, final V value, final Duration duration) {
		return setEx(key, value, (int) duration.toSeconds());
	}

	@Override
	default Status setNx(final String key, final String value) {
		return doExecute((cmd)->cmd.setNx(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Status setNx(final byte[] key, final byte[] value) {
		return doExecute((cmd)->cmd.setNx(KeyUtils.rawKey(this, key), value));
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
		return doExecute((cmd)->cmd.setRange(KeyUtils.rawKey(this, key), offset, value));
	}

	@Override
	default Long setRange(final byte[] key, final long offset, final byte[] value) {
		return doExecute((cmd)->cmd.setRange(KeyUtils.rawKey(this, key), offset, value));
	}

	@Override
	default Long strlen(final String key) {
		return doExecute((cmd)->cmd.strlen(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long strlen(final byte[] key) {
		return doExecute((cmd)->cmd.strlen(KeyUtils.rawKey(this, key)));
	}

	@Override
	default String substr(final String key, final long start, final long end) {
		return doExecute((cmd)->cmd.substr(KeyUtils.rawKey(this, key), start, end));
	}

	@Override
	default byte[] substr(final byte[] key, final long start, final long end) {
		return doExecute((cmd)->cmd.substr(KeyUtils.rawKey(this, key), start, end));
	}

	private <R> R doExecute(final Command.Executor<StringCommands, R> executor) {
		return execute((client)->executor.execute(client.stringCommands()));
	}

}
