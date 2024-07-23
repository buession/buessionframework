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
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.SetCommands;

import java.util.List;
import java.util.Set;

/**
 * 集合运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/set/index.html" target="_blank">http://redisdoc.com/set/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface SetOperations extends SetCommands, RedisOperations {

	/**
	 * 将一个或多个 member 元素序列化后加入到集合 key 当中，已经存在于集合的 member 元素将被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sadd.html" target="_blank">http://redisdoc.com/set/sadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被添加到集合中的新元素的数量，不包括被忽略的元素
	 */
	<V> Long sAdd(final String key, final V... members);

	/**
	 * 将一个或多个 member 元素序列化后加入到集合 key 当中，已经存在于集合的 member 元素将被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sadd.html" target="_blank">http://redisdoc.com/set/sadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被添加到集合中的新元素的数量，不包括被忽略的元素
	 */
	<V> Long sAdd(final byte[] key, final V... members);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合之间的差集，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sdiff.html" target="_blank">http://redisdoc.com/set/sdiff.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含差集成员反序列化为对象的列表
	 */
	<V> Set<V> sDiffObject(final String[] keys);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合之间的差集，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sdiff.html" target="_blank">http://redisdoc.com/set/sdiff.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含差集成员反序列化为对象的列表
	 */
	<V> Set<V> sDiffObject(final byte[][] keys);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合之间的差集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sdiff.html" target="_blank">http://redisdoc.com/set/sdiff.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含差集成员反序列化为对象的列表
	 */
	<V> Set<V> sDiffObject(final String[] keys, final Class<V> clazz);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合之间的差集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sdiff.html" target="_blank">http://redisdoc.com/set/sdiff.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含差集成员反序列化为对象的列表
	 */
	<V> Set<V> sDiffObject(final byte[][] keys, final Class<V> clazz);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合之间的差集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sdiff.html" target="_blank">http://redisdoc.com/set/sdiff.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含差集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> Set<V> sDiffObject(final String[] keys, final TypeReference<V> type);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合之间的差集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sdiff.html" target="_blank">http://redisdoc.com/set/sdiff.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含差集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> Set<V> sDiffObject(final byte[][] keys, final TypeReference<V> type);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的交集，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sinter.html" target="_blank">http://redisdoc.com/set/sinter.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 交集成员反序列化为对象的列表
	 */
	<V> Set<V> sInterObject(final String[] keys);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的交集，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sinter.html" target="_blank">http://redisdoc.com/set/sinter.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 交集成员反序列化为对象的列表
	 */
	<V> Set<V> sInterObject(final byte[][] keys);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sinter.html" target="_blank">http://redisdoc.com/set/sinter.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 交集成员反序列化为对象的列表
	 */
	<V> Set<V> sInterObject(final String[] keys, final Class<V> clazz);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sinter.html" target="_blank">http://redisdoc.com/set/sinter.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 交集成员反序列化为对象的列表
	 */
	<V> Set<V> sInterObject(final byte[][] keys, final Class<V> clazz);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sinter.html" target="_blank">http://redisdoc.com/set/sinter.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 交集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> Set<V> sInterObject(final String[] keys, final TypeReference<V> type);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sinter.html" target="_blank">http://redisdoc.com/set/sinter.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 交集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> Set<V> sInterObject(final byte[][] keys, final TypeReference<V> type);

	/**
	 * 获取集合 key 中的所有成员反序列化后的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/smembers.html" target="_blank">http://redisdoc.com/set/smembers.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合中的所有成员
	 */
	<V> Set<V> sMembersObject(final String key);

	/**
	 * 获取集合 key 中的所有成员反序列化后的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/smembers.html" target="_blank">http://redisdoc.com/set/smembers.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合中的所有成员
	 */
	<V> Set<V> sMembersObject(final byte[] key);

	/**
	 * 获取集合 key 中的所有成员反序列化 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/smembers.html" target="_blank">http://redisdoc.com/set/smembers.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合中的所有成员
	 */
	<V> Set<V> sMembersObject(final String key, final Class<V> clazz);

	/**
	 * 获取集合 key 中的所有成员反序列化 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/smembers.html" target="_blank">http://redisdoc.com/set/smembers.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合中的所有成员
	 */
	<V> Set<V> sMembersObject(final byte[] key, final Class<V> clazz);

	/**
	 * 获取集合 key 中的所有成员反序列化 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/smembers.html" target="_blank">http://redisdoc.com/set/smembers.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合中的所有成员
	 *
	 * @see TypeReference
	 */
	<V> Set<V> sMembersObject(final String key, final TypeReference<V> type);

	/**
	 * 获取集合 key 中的所有成员反序列化 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/smembers.html" target="_blank">http://redisdoc.com/set/smembers.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合中的所有成员
	 *
	 * @see TypeReference
	 */
	<V> Set<V> sMembersObject(final byte[] key, final TypeReference<V> type);

	/**
	 * 移除并返回集合 key 中的一个随机元素反序列化后的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 */
	<V> V sPopObject(final String key);

	/**
	 * 移除并返回集合 key 中的一个随机元素反序列化后的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 */
	<V> V sPopObject(final byte[] key);

	/**
	 * 移除并返回集合 key 中的一个随机元素反序列化 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 */
	<V> V sPopObject(final String key, final Class<V> clazz);

	/**
	 * 移除并返回集合 key 中的一个随机元素反序列化 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 */
	<V> V sPopObject(final byte[] key, final Class<V> clazz);

	/**
	 * 移除并返回集合 key 中的一个随机元素反序列化 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 *
	 * @see TypeReference
	 */
	<V> V sPopObject(final String key, final TypeReference<V> type);

	/**
	 * 移除并返回集合 key 中的一个随机元素反序列化 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 *
	 * @see TypeReference
	 */
	<V> V sPopObject(final byte[] key, final TypeReference<V> type);

	/**
	 * 移除并返回集合 key 中的 count 个随机元素反序列化后的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回删除元素个数
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 */
	<V> Set<V> sPopObject(final String key, final int count);

	/**
	 * 移除并返回集合 key 中的 count 个随机元素反序列化后的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回删除元素个数
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 */
	<V> Set<V> sPopObject(final byte[] key, final int count);

	/**
	 * 移除并返回集合 key 中的 count 个随机元素反序列化 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回删除元素个数
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 */
	<V> Set<V> sPopObject(final String key, final int count, final Class<V> clazz);

	/**
	 * 移除并返回集合 key 中的 count 个随机元素反序列化 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回删除元素个数
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 */
	<V> Set<V> sPopObject(final byte[] key, final int count, final Class<V> clazz);

	/**
	 * 移除并返回集合 key 中的 count 个随机元素反序列化 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回删除元素个数
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 *
	 * @see TypeReference
	 */
	<V> Set<V> sPopObject(final String key, final int count, final TypeReference<V> type);

	/**
	 * 移除并返回集合 key 中的 count 个随机元素反序列化 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回删除元素个数
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除的随机元素反序列化后的对象
	 *
	 * @see TypeReference
	 */
	<V> Set<V> sPopObject(final byte[] key, final int count, final TypeReference<V> type);

	/**
	 * 返回集合 key 中的一个随机元素，并反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的一个随机元素，反序列化后的对象
	 */
	<V> V sRandMemberObject(final String key);

	/**
	 * 返回集合 key 中的一个随机元素，并反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的一个随机元素，反序列化后的对象
	 */
	<V> V sRandMemberObject(final byte[] key);

	/**
	 * 返回集合 key 中的一个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的一个随机元素，反序列化后的对象
	 */
	<V> V sRandMemberObject(final String key, final Class<V> clazz);

	/**
	 * 返回集合 key 中的一个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的一个随机元素，反序列化后的对象
	 */
	<V> V sRandMemberObject(final byte[] key, final Class<V> clazz);

	/**
	 * 返回集合 key 中的一个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的一个随机元素，反序列化后的对象
	 *
	 * @see TypeReference
	 */
	<V> V sRandMemberObject(final String key, final TypeReference<V> type);

	/**
	 * 返回集合 key 中的一个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的一个随机元素，反序列化后的对象
	 *
	 * @see TypeReference
	 */
	<V> V sRandMemberObject(final byte[] key, final TypeReference<V> type);

	/**
	 * 返回集合 key 中的 count 个随机元素，并反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的随机元素，反序列化后的对象列表
	 */
	<V> List<V> sRandMemberObject(final String key, final int count);

	/**
	 * 返回集合 key 中的 count 个随机元素，并反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的随机元素，反序列化后的对象列表
	 */
	<V> List<V> sRandMemberObject(final byte[] key, final int count);

	/**
	 * 返回集合 key 中的 count 个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的随机元素，反序列化后的对象列表
	 */
	<V> List<V> sRandMemberObject(final String key, final int count, final Class<V> clazz);

	/**
	 * 返回集合 key 中的 count 个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的随机元素，反序列化后的对象列表
	 */
	<V> List<V> sRandMemberObject(final byte[] key, final int count, final Class<V> clazz);

	/**
	 * 返回集合 key 中的 count 个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的随机元素，序列化后的对象列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> sRandMemberObject(final String key, final int count, final TypeReference<V> type);

	/**
	 * 返回集合 key 中的 count 个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 集合 key 中的随机元素，序列化后的对象列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> sRandMemberObject(final byte[] key, final int count, final TypeReference<V> type);

	/**
	 * 移除集合 key 中的 member 序列化后的元素，不存在的 member 元素会被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srem.html" target="_blank">http://redisdoc.com/set/srem.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功移除的元素的数量，不包括被忽略的元素
	 */
	<V> Long sRem(final String key, final V member);

	/**
	 * 移除集合 key 中的 member 序列化后的元素，不存在的 member 元素会被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srem.html" target="_blank">http://redisdoc.com/set/srem.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功移除的元素的数量，不包括被忽略的元素
	 */
	<V> Long sRem(final byte[] key, final V member);

	/**
	 * 移除集合 key 中的一个或多个 member 序列化后的元素，不存在的 member 元素会被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srem.html" target="_blank">http://redisdoc.com/set/srem.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功移除的元素的数量，不包括被忽略的元素
	 */
	<V> Long sRem(final String key, final V... members);

	/**
	 * 移除集合 key 中的一个或多个 member 序列化后的元素，不存在的 member 元素会被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srem.html" target="_blank">http://redisdoc.com/set/srem.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功移除的元素的数量，不包括被忽略的元素
	 */
	<V> Long sRem(final byte[] key, final V... members);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern,
										final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern,
										final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern,
										final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern,
										final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
										final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
										final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
										final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
										final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final int count);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final int count);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final int count, final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final int count, final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final int count,
										final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final int count,
										final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final int count);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final int count);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final int count, final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final int count, final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final int count,
										final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final int count,
										final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern, final int count);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern, final int count);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern, final int count,
										final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern, final int count,
										final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern, final int count,
										final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern, final int count,
										final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern, final int count);

	/**
	 * 迭代集合键中的元素，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern, final int count);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern, final int count,
										final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern, final int count,
										final Class<V> clazz);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern, final int count,
										final TypeReference<V> type);

	/**
	 * 迭代集合键中的元素，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	<V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern, final int count,
										final TypeReference<V> type);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的并集，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sunion.html" target="_blank">http://redisdoc.com/set/sunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> Set<V> sUnionObject(final String[] keys);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的并集，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sunion.html" target="_blank">http://redisdoc.com/set/sunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> Set<V> sUnionObject(final byte[][] keys);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sunion.html" target="_blank">http://redisdoc.com/set/sunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> Set<V> sUnionObject(final String[] keys, final Class<V> clazz);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sunion.html" target="_blank">http://redisdoc.com/set/sunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> Set<V> sUnionObject(final byte[][] keys, final Class<V> clazz);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sunion.html" target="_blank">http://redisdoc.com/set/sunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> Set<V> sUnionObject(final String[] keys, final TypeReference<V> type);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sunion.html" target="_blank">http://redisdoc.com/set/sunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> Set<V> sUnionObject(final byte[][] keys, final TypeReference<V> type);

}
