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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.operations;

import com.buession.core.builder.MapBuilder;
import com.buession.core.serializer.type.TypeReference;
import com.buession.lang.KeyValue;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.KeyedZSetElement;
import com.buession.redis.core.command.SortedSetCommands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 有序集合运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/index.html" target="_blank">http://redisdoc.com/sorted_set/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface SortedSetOperations extends SortedSetCommands, RedisOperations {

	/**
	 * 从非空的第一个有序集中弹出得分最小的成员，按照命令中 key 出现的顺序检查；是有序集 ZPOPMIN 的阻塞变体；
	 * 当没有成员可以从任何给定的有序集中弹出时，它会阻塞连接
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/bzpopmin.html" target="_blank">https://www.redis.com.cn/commands/bzpopmin.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param timeout
	 * 		阻塞的最长时间；为 0 时，则无限期地阻塞，单位：秒
	 */
	default KeyedZSetElement bzPopMin(final String key, final int timeout){
		return bzPopMin(new String[]{key}, timeout);
	}

	/**
	 * 从非空的第一个有序集中弹出得分最小的成员，按照命令中 key 出现的顺序检查；是有序集 ZPOPMIN 的阻塞变体；
	 * 当没有成员可以从任何给定的有序集中弹出时，它会阻塞连接
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/bzpopmin.html" target="_blank">https://www.redis.com.cn/commands/bzpopmin.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param timeout
	 * 		阻塞的最长时间；为 0 时，则无限期地阻塞，单位：秒
	 */
	default KeyedZSetElement bzPopMin(final byte[] key, final int timeout){
		return bzPopMin(new byte[][]{key}, timeout);
	}

	/**
	 * 从非空的第一个有序集中弹出得分最高的成员，按照命令中 key 出现的顺序检查；是有序集 ZPOPMAX 的阻塞变体；
	 * 当没有成员可以从任何给定的有序集中弹出时，它会阻塞连接
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/bzpopmax.html" target="_blank">https://www.redis.com.cn/commands/bzpopmax.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param timeout
	 * 		阻塞的最长时间；为 0 时，则无限期地阻塞，单位：秒
	 */
	default KeyedZSetElement bzPopMax(final String key, final int timeout){
		return bzPopMax(new String[]{key}, timeout);
	}

	/**
	 * 从非空的第一个有序集中弹出得分最高的成员，按照命令中 key 出现的顺序检查；是有序集 ZPOPMAX 的阻塞变体；
	 * 当没有成员可以从任何给定的有序集中弹出时，它会阻塞连接
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/bzpopmax.html" target="_blank">https://www.redis.com.cn/commands/bzpopmax.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param timeout
	 * 		阻塞的最长时间；为 0 时，则无限期地阻塞，单位：秒
	 */
	default KeyedZSetElement bzPopMax(final byte[] key, final int timeout){
		return bzPopMax(new byte[][]{key}, timeout);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final String key, final double score, final String member){
		return zAdd(key, MapBuilder.of(member, score));
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final byte[] key, final double score, final byte[] member){
		return zAdd(key, MapBuilder.of(member, score));
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param argument
	 * 		参数
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final String key, final double score, final String member, final ZAddArgument argument){
		return zAdd(key, MapBuilder.of(member, score), argument);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param argument
	 * 		参数
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final byte[] key, final double score, final byte[] member, final ZAddArgument argument){
		return zAdd(key, MapBuilder.of(member, score), argument);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final String key, final double score, final V member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final byte[] key, final double score, final V member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param argument
	 * 		参数
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final String key, final double score, final V member, final ZAddArgument argument);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param argument
	 * 		参数
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final byte[] key, final double score, final V member, final ZAddArgument argument);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The result of the difference
	 */
	default Set<String> zDiff(final String key){
		return zDiff(key, (String) null);
	}

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The result of the difference
	 */
	default Set<byte[]> zDiff(final byte[] key){
		return zDiff(key, (byte[]) null);
	}

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	default <V> Set<V> zDiffObject(final String key){
		return zDiffObject(key, (String[]) null);
	}

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	default <V> Set<V> zDiffObject(final byte[] key){
		return zDiffObject(key, (byte[][]) null);
	}

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	default <V> Set<V> zDiffObject(final String key, final Class<V> clazz){
		return zDiffObject(key, null, clazz);
	}

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	default <V> Set<V> zDiffObject(final byte[] key, final Class<V> clazz){
		return zDiffObject(key, null, clazz);
	}

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	default <V> Set<V> zDiffObject(final String key, final TypeReference<V> type){
		return zDiffObject(key, null, type);
	}

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	default <V> Set<V> zDiffObject(final byte[] key, final TypeReference<V> type){
		return zDiffObject(key, null, type);
	}

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> Set<V> zDiffObject(final String key, final String[] keys);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> Set<V> zDiffObject(final byte[] key, final byte[][] keys);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> Set<V> zDiffObject(final String key, final String[] keys, final Class<V> clazz);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> Set<V> zDiffObject(final byte[] key, final byte[][] keys, final Class<V> clazz);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> Set<V> zDiffObject(final String key, final String[] keys, final TypeReference<V> type);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> Set<V> zDiffObject(final byte[] key, final byte[][] keys, final TypeReference<V> type);

	/**
	 * Computes the difference between the first and all successive input sorted sets and stores the result in destination
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiffstore/" target="_blank">https://redis.io/commands/zdiffstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 *
	 * @return 结果集中的元素数量
	 */
	default Long zDiffStore(final String destKey, final String key){
		return zDiffStore(destKey, new String[]{key});
	}

	/**
	 * Computes the difference between the first and all successive input sorted sets and stores the result in destination
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiffstore/" target="_blank">https://redis.io/commands/zdiffstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 *
	 * @return 结果集中的元素数量
	 */
	default Long zDiffStore(final byte[] destKey, final byte[] key){
		return zDiffStore(destKey, new byte[][]{key});
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量一
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zIncr(final String key, final String member){
		return zIncrBy(key, member, 1);
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量一
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zIncr(final byte[] key, final byte[] member){
		return zIncrBy(key, member, 1);
	}

	/**
	 * 计算给定的一个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	default Long zInterStore(final String destKey, final String key){
		return zInterStore(destKey, new String[]{key});
	}

	/**
	 * 计算给定的一个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	default Long zInterStore(final byte[] destKey, final byte[] key){
		return zInterStore(destKey, new byte[][]{key});
	}

	/**
	 * 计算给定的一个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	default Long zInterStore(final String destKey, final Aggregate aggregate, final String key){
		return zInterStore(destKey, aggregate, new String[]{key});
	}

	/**
	 * 计算给定的一个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	default Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[] key){
		return zInterStore(destKey, aggregate, new byte[][]{key});
	}

	/**
	 * 计算给定的一个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param weight
	 * 		有序集的乘法因子
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	default Long zInterStore(final String destKey, final double weight, final String key){
		return zInterStore(destKey, new double[]{weight}, new String[]{key});
	}

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param weight
	 * 		有序集的乘法因子
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	default Long zInterStore(final byte[] destKey, final double weight, final byte[] key){
		return zInterStore(destKey, new double[]{weight}, new byte[][]{key});
	}

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weight
	 * 		有序集的乘法因子
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	default Long zInterStore(final String destKey, final Aggregate aggregate, final double weight, final String key){
		return zInterStore(destKey, aggregate, new double[]{weight}, new String[]{key});
	}

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weight
	 * 		有序集的乘法因子
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	default Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double weight, final byte[] key){
		return zInterStore(destKey, aggregate, new double[]{weight}, new byte[][]{key});
	}

	/**
	 * 移除有序集 key 中的成员，不存在的成员将被忽略
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 *
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	 */
	default Long zRem(final String key, final String member){
		return zRem(key, new String[]{member});
	}

	/**
	 * 移除有序集 key 中的成员，不存在的成员将被忽略
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 *
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	 */
	default Long zRem(final byte[] key, final byte[] member){
		return zRem(key, new byte[][]{member});
	}

	/**
	 * 计算给定的一个有序集的并集，并将该并集储存到 destKey
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	default Long zUnionStore(final String destKey, final String key){
		return zUnionStore(destKey, new String[]{key});
	}

	/**
	 * 计算给定的一个有序集的并集，并将该并集储存到 destKey
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	default Long zUnionStore(final byte[] destKey, final byte[] key){
		return zUnionStore(destKey, new byte[][]{key});
	}

	/**
	 * 计算给定的一个有序集的并集，并将该并集储存到 destKey
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	default Long zUnionStore(final String destKey, final Aggregate aggregate, final String key){
		return zUnionStore(destKey, aggregate, new String[]{key});
	}

	/**
	 * 计算给定的一个有序集的并集，并将该并集储存到 destKey
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	default Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[] key){
		return zUnionStore(destKey, aggregate, new byte[][]{key});
	}

	/**
	 * 计算给定的一个有序集的并集，并将该并集储存到 destKey
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param weight
	 * 		有序集的乘法因子
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	default Long zUnionStore(final String destKey, final double weight, final String key){
		return zUnionStore(destKey, new double[]{weight}, new String[]{key});
	}

	/**
	 * 计算给定的一个有序集的并集，并将该并集储存到 destKey
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param weight
	 * 		有序集的乘法因子
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	default Long zUnionStore(final byte[] destKey, final double weight, final byte[] key){
		return zUnionStore(destKey, new double[]{weight}, new byte[][]{key});
	}

	/**
	 * 计算给定的一个有序集的并集，并将该并集储存到 destKey
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weight
	 * 		有序集的乘法因子
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	default Long zUnionStore(final String destKey, final Aggregate aggregate, final double weight, final String key){
		return zUnionStore(destKey, aggregate, new double[]{weight}, new String[]{key});
	}

	/**
	 * 计算给定的一个有序集的并集，并将该并集储存到 destKey
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weight
	 * 		有序集的乘法因子
	 * @param key
	 * 		Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	default Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double weight, final byte[] key){
		return zUnionStore(destKey, aggregate, new double[]{weight}, new byte[][]{key});
	}

}
