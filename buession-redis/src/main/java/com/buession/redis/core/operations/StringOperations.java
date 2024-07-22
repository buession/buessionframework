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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.operations;

import com.buession.core.type.TypeReference;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.command.args.SetArgument;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * STRING 运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/string/index.html" target="_blank">http://redisdoc.com/string/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface StringOperations extends StringCommands, RedisOperations {

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
	 *
	 * @see TypeReference
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
	 *
	 * @see TypeReference
	 */
	<V> V getObject(final byte[] key, final TypeReference<V> type);

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
	 *
	 * @see TypeReference
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
	 *
	 * @see TypeReference
	 */
	<V> V getExObject(final byte[] key, final GetExArgument getExArgument, final TypeReference<V> type);

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
	 *
	 * @see TypeReference
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
	 *
	 * @see TypeReference
	 */
	<V> V getSet(final byte[] key, final V value, final TypeReference<V> type);

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
	 *
	 * @see TypeReference
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
	 *
	 * @see TypeReference
	 */
	<V> V getDelObject(final byte[] key, final TypeReference<V> type);

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
	 *
	 * @see TypeReference
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
	 *
	 * @see TypeReference
	 */
	<V> List<V> mGetObject(final byte[][] keys, final TypeReference<V> type);

	/**
	 * 同时为多个键设置值，如果某个给定键已经存在 那么将使用新值去覆盖旧值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/mset.html" target="_blank">http://redisdoc.com/string/mset.html</a></p>
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
	 * 当且仅当所有给定键都不存在时， 为所有给定键设置值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/msetnx.html" target="_blank">http://redisdoc.com/string/msetnx.html</a></p>
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
	 * @param setArgument
	 * 		参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final String key, final V value, final SetArgument setArgument);

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
	 * @param setArgument
	 * 		参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	<V> Status set(final byte[] key, final V value, final SetArgument setArgument);

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

}
