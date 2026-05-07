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
import com.buession.core.utils.NumberUtils;
import com.buession.lang.Status;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.SetCommands;
import com.buession.redis.utils.KeyUtils;

import java.util.List;
import java.util.Set;

/**
 * 集合运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=set" target="_blank">https://redis.io/docs/latest/commands/?group=set</a></p>
 *
 * @author Yong.Teng
 */
public interface SetOperations extends SetCommands, RedisOperations {

	@Override
	default Long sAdd(final String key, final String... members) {
		return execute((client)->client.setCommands().sAdd(KeyUtils.rawKey(this, key), members));
	}

	@Override
	default Long sAdd(final byte[] key, final byte[]... members) {
		return execute((client)->client.setCommands().sAdd(KeyUtils.rawKey(this, key), members));
	}

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
	@SuppressWarnings({"unchecked"})
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
	@SuppressWarnings({"unchecked"})
	<V> Long sAdd(final byte[] key, final V... members);

	@Override
	default Long sCard(final String key) {
		return execute((client)->client.setCommands().sCard(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long sCard(final byte[] key) {
		return execute((client)->client.setCommands().sCard(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Set<String> sDiff(final String... keys) {
		return execute((client)->client.setCommands().sDiff(KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Set<byte[]> sDiff(final byte[]... keys) {
		return execute((client)->client.setCommands().sDiff(KeyUtils.rawKeys(this, keys)));
	}

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
	<V> Set<V> sDiff(final String[] keys, final Class<V> clazz);

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
	<V> Set<V> sDiff(final byte[][] keys, final Class<V> clazz);

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
	 */
	<V> Set<V> sDiff(final String[] keys, final TypeReference<V> type);

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
	 */
	<V> Set<V> sDiff(final byte[][] keys, final TypeReference<V> type);

	@Override
	default Long sDiffStore(final String destKey, final String... keys) {
		return execute((client)->client.setCommands()
				.sDiffStore(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Long sDiffStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.setCommands()
				.sDiffStore(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Set<String> sInter(final String... keys) {
		return execute((client)->client.setCommands().sInter(KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Set<byte[]> sInter(final byte[]... keys) {
		return execute((client)->client.setCommands().sInter(KeyUtils.rawKeys(this, keys)));
	}

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
	<V> Set<V> sInter(final String[] keys, final Class<V> clazz);

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
	<V> Set<V> sInter(final byte[][] keys, final Class<V> clazz);

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
	 */
	<V> Set<V> sInter(final String[] keys, final TypeReference<V> type);

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
	 */
	<V> Set<V> sInter(final byte[][] keys, final TypeReference<V> type);

	@Override
	default Long sInterCard(final String... keys) {
		return execute((client)->client.setCommands().sInterCard(KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Long sInterCard(final byte[]... keys) {
		return execute((client)->client.setCommands().sInterCard(KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Long sInterCard(final String[] keys, final int limit) {
		return execute((client)->client.setCommands().sInterCard(KeyUtils.rawKeys(this, keys), limit));
	}

	@Override
	default Long sInterCard(final byte[][] keys, final int limit) {
		return execute((client)->client.setCommands().sInterCard(KeyUtils.rawKeys(this, keys), limit));
	}

	@Override
	default Long sInterStore(final String destKey, final String... keys) {
		return execute((client)->client.setCommands()
				.sDiffStore(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Long sInterStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.setCommands()
				.sDiffStore(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Boolean sIsMember(final String key, final String member) {
		return execute((client)->client.setCommands().sIsMember(KeyUtils.rawKey(this, key), member));
	}

	@Override
	default Boolean sIsMember(final byte[] key, final byte[] member) {
		return execute((client)->client.setCommands().sIsMember(KeyUtils.rawKey(this, key), member));
	}

	@Override
	default Set<String> sMembers(final String key) {
		return execute((client)->client.setCommands().sMembers(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Set<byte[]> sMembers(final byte[] key) {
		return execute((client)->client.setCommands().sMembers(KeyUtils.rawKey(this, key)));
	}

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
	<V> Set<V> sMembers(final String key, final Class<V> clazz);

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
	<V> Set<V> sMembers(final byte[] key, final Class<V> clazz);

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
	 */
	<V> Set<V> sMembers(final String key, final TypeReference<V> type);

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
	 */
	<V> Set<V> sMembers(final byte[] key, final TypeReference<V> type);

	@Override
	default List<Boolean> smIsMember(final String key, final String... members) {
		return execute((client)->client.setCommands().smIsMember(KeyUtils.rawKey(this, key), members));
	}

	@Override
	default List<Boolean> smIsMember(final byte[] key, final byte[]... members) {
		return execute((client)->client.setCommands().smIsMember(KeyUtils.rawKey(this, key), members));
	}

	@Override
	default Status sMove(final String key, final String destKey, final String member) {
		return execute((client)->client.setCommands()
				.sMove(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), member));
	}

	@Override
	default Status sMove(final byte[] key, final byte[] destKey, final byte[] member) {
		return execute((client)->client.setCommands()
				.sMove(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), member));
	}

	@Override
	default String sPop(final String key) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key)));
	}

	@Override
	default byte[] sPop(final byte[] key) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Set<String> sPop(final String key, final int count) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key), count));
	}

	@Override
	default Set<byte[]> sPop(final byte[] key, final int count) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key), count));
	}

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
	<V> V sPop(final String key, final Class<V> clazz);

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
	<V> V sPop(final byte[] key, final Class<V> clazz);

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
	 */
	<V> V sPop(final String key, final TypeReference<V> type);

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
	 */
	<V> V sPop(final byte[] key, final TypeReference<V> type);

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
	<V> Set<V> sPop(final String key, final int count, final Class<V> clazz);

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
	<V> Set<V> sPop(final byte[] key, final int count, final Class<V> clazz);

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
	 */
	<V> Set<V> sPop(final String key, final int count, final TypeReference<V> type);

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
	 */
	<V> Set<V> sPop(final byte[] key, final int count, final TypeReference<V> type);

	@Override
	default String sRandMember(final String key) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key)));
	}

	@Override
	default byte[] sRandMember(final byte[] key) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<String> sRandMember(final String key, final int count) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key), count));
	}

	@Override
	default List<byte[]> sRandMember(final byte[] key, final int count) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key), count));
	}

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
	<V> V sRandMember(final String key, final Class<V> clazz);

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
	<V> V sRandMember(final byte[] key, final Class<V> clazz);

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
	 */
	<V> V sRandMember(final String key, final TypeReference<V> type);

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
	 */
	<V> V sRandMember(final byte[] key, final TypeReference<V> type);

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
	<V> List<V> sRandMember(final String key, final int count, final Class<V> clazz);

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
	<V> List<V> sRandMember(final byte[] key, final int count, final Class<V> clazz);

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
	 */
	<V> List<V> sRandMember(final String key, final int count, final TypeReference<V> type);

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
	 */
	<V> List<V> sRandMember(final byte[] key, final int count, final TypeReference<V> type);

	@Override
	default Long sRem(final String key, final String... members) {
		return execute((client)->client.setCommands().sRem(KeyUtils.rawKey(this, key), members));
	}

	@Override
	default Long sRem(final byte[] key, final byte[]... members) {
		return execute((client)->client.setCommands().sRem(KeyUtils.rawKey(this, key), members));
	}

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
	@SuppressWarnings({"unchecked"})
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
	@SuppressWarnings({"unchecked"})
	<V> Long sRem(final byte[] key, final V... members);

	@Override
	default ScanResult<String> sScan(final String key, final String cursor) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor));
	}

	@Override
	default ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor));
	}

	@Override
	default ScanResult<String> sScan(final String key, final String cursor, final String pattern) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern));
	}

	@Override
	default ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern));
	}

	@Override
	default ScanResult<String> sScan(final String key, final String cursor, final String pattern, final int count) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern, count));
	}

	@Override
	default ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern, count));
	}

	@Override
	default ScanResult<String> sScan(final String key, final String cursor, final int count) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, count));
	}

	@Override
	default ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor, final int count) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, count));
	}

	/**
	 * 迭代集合键中的元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 每个元素都是一个集合成员
	 */
	default ScanResult<String> sScan(final String key, final long cursor) {
		return sScan(key, Long.toString(cursor));
	}

	/**
	 * 迭代集合键中的元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 每个元素都是一个集合成员
	 */
	default ScanResult<byte[]> sScan(final byte[] key, final long cursor) {
		return sScan(key, NumberUtils.long2bytes(cursor));
	}

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
	<V> ScanResult<V> sScan(final String key, final String cursor, final Class<V> clazz);

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
	<V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final Class<V> clazz);

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
	 */
	<V> ScanResult<V> sScan(final String key, final String cursor, final TypeReference<V> type);

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
	 */
	<V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final TypeReference<V> type);

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
	default <V> ScanResult<V> sScan(final String key, final long cursor, final Class<V> clazz) {
		return sScan(key, Long.toString(cursor), clazz);
	}

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
	default <V> ScanResult<V> sScan(final byte[] key, final long cursor, final Class<V> clazz) {
		return sScan(key, NumberUtils.long2bytes(cursor), clazz);
	}

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
	 */
	default <V> ScanResult<V> sScan(final String key, final long cursor, final TypeReference<V> type) {
		return sScan(key, Long.toString(cursor), type);
	}

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
	 */
	default <V> ScanResult<V> sScan(final byte[] key, final long cursor, final TypeReference<V> type) {
		return sScan(key, NumberUtils.long2bytes(cursor), type);
	}

	/**
	 * 迭代集合键中的元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 *
	 * @return 返回和给定模式相匹配的元素
	 */
	default ScanResult<String> sScan(final String key, final long cursor, final String pattern) {
		return sScan(key, Long.toString(cursor), pattern);
	}

	/**
	 * 迭代集合键中的元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 *
	 * @return 返回和给定模式相匹配的元素
	 */
	default ScanResult<byte[]> sScan(final byte[] key, final long cursor, final byte[] pattern) {
		return sScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

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
	<V> ScanResult<V> sScan(final String key, final String cursor, final String pattern, final Class<V> clazz);

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
	<V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final Class<V> clazz);

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
	 */
	<V> ScanResult<V> sScan(final String key, final String cursor, final String pattern, final TypeReference<V> type);

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
	 */
	<V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final TypeReference<V> type);

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
	default <V> ScanResult<V> sScan(final String key, final long cursor, final String pattern, final Class<V> clazz) {
		return sScan(key, Long.toString(cursor), pattern, clazz);
	}

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
	default <V> ScanResult<V> sScan(final byte[] key, final long cursor, final byte[] pattern, final Class<V> clazz) {
		return sScan(key, NumberUtils.long2bytes(cursor), pattern, clazz);
	}

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
	 */
	default <V> ScanResult<V> sScan(final String key, final long cursor, final String pattern,
	                                final TypeReference<V> type) {
		return sScan(key, Long.toString(cursor), pattern, type);
	}

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
	 */
	default <V> ScanResult<V> sScan(final byte[] key, final long cursor, final byte[] pattern,
	                                final TypeReference<V> type) {
		return sScan(key, NumberUtils.long2bytes(cursor), pattern, type);
	}

	/**
	 * 迭代集合键中的元素
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
	 *
	 * @return 返回和给定模式相匹配指定数量的元素
	 */
	default ScanResult<String> sScan(final String key, final long cursor, final String pattern, final int count) {
		return sScan(key, Long.toString(cursor), pattern, count);
	}

	/**
	 * 迭代集合键中的元素
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
	 *
	 * @return 返回和给定模式相匹配指定数量的元素
	 */
	default ScanResult<byte[]> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count) {
		return sScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

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
	<V> ScanResult<V> sScan(final String key, final String cursor, final String pattern, final int count,
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
	<V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count,
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
	 */
	<V> ScanResult<V> sScan(final String key, final String cursor, final String pattern, final int count,
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
	 */
	<V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count,
	                        final TypeReference<V> type);

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
	default <V> ScanResult<V> sScan(final String key, final long cursor, final String pattern, final int count,
	                                final Class<V> clazz) {
		return sScan(key, Long.toString(cursor), pattern, count, clazz);
	}

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
	default <V> ScanResult<V> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count,
	                                final Class<V> clazz) {
		return sScan(key, NumberUtils.long2bytes(cursor), pattern, count, clazz);
	}

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
	 */
	default <V> ScanResult<V> sScan(final String key, final long cursor, final String pattern, final int count,
	                                final TypeReference<V> type) {
		return sScan(key, Long.toString(cursor), pattern, count, type);
	}

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
	 */
	default <V> ScanResult<V> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count,
	                                final TypeReference<V> type) {
		return sScan(key, NumberUtils.long2bytes(cursor), pattern, count, type);
	}

	/**
	 * 迭代集合键中的元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回的指定数量的键值对
	 */
	default ScanResult<String> sScan(final String key, final long cursor, final int count) {
		return sScan(key, Long.toString(cursor), count);
	}

	/**
	 * 迭代集合键中的元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sscan.html" target="_blank">http://redisdoc.com/set/sscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回的指定数量的键值对
	 */
	default ScanResult<byte[]> sScan(final byte[] key, final long cursor, final int count) {
		return sScan(key, NumberUtils.long2bytes(cursor), count);
	}

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
	<V> ScanResult<V> sScan(final String key, final String cursor, final int count, final Class<V> clazz);

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
	<V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final int count, final Class<V> clazz);

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
	 */
	<V> ScanResult<V> sScan(final String key, final String cursor, final int count, final TypeReference<V> type);

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
	 */
	<V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final int count, final TypeReference<V> type);

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
	default <V> ScanResult<V> sScan(final String key, final long cursor, final int count, final Class<V> clazz) {
		return sScan(key, Long.toString(cursor), count, clazz);
	}

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
	default <V> ScanResult<V> sScan(final byte[] key, final long cursor, final int count, final Class<V> clazz) {
		return sScan(key, NumberUtils.long2bytes(cursor), count, clazz);
	}

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
	 */
	default <V> ScanResult<V> sScan(final String key, final long cursor, final int count, final TypeReference<V> type) {
		return sScan(key, Long.toString(cursor), count, type);
	}

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
	 */
	default <V> ScanResult<V> sScan(final byte[] key, final long cursor, final int count, final TypeReference<V> type) {
		return sScan(key, NumberUtils.long2bytes(cursor), count, type);
	}

	@Override
	default Set<String> sUnion(final String... keys) {
		return execute((client)->client.setCommands().sUnion(KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Set<byte[]> sUnion(final byte[]... keys) {
		return execute((client)->client.setCommands().sUnion(KeyUtils.rawKeys(this, keys)));
	}

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
	<V> Set<V> sUnion(final String[] keys, final Class<V> clazz);

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
	<V> Set<V> sUnion(final byte[][] keys, final Class<V> clazz);

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
	 */
	<V> Set<V> sUnion(final String[] keys, final TypeReference<V> type);

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
	 */
	<V> Set<V> sUnion(final byte[][] keys, final TypeReference<V> type);

	@Override
	default Long sUnionStore(final String destKey, final String... keys) {
		return execute((client)->client.setCommands()
				.sUnionStore(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Long sUnionStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.setCommands()
				.sUnionStore(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys)));
	}

}
