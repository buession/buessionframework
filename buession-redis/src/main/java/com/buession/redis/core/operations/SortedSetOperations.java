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
import com.buession.lang.KeyValue;
import com.buession.redis.core.command.args.sortedset.Aggregate;
import com.buession.redis.core.command.args.MinMax;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.command.args.sortedset.ZRangeType;
import com.buession.redis.core.command.SortedSetCommands;
import com.buession.redis.core.command.args.sortedset.ZAddArgument;

import java.util.List;

/**
 * 有序集合运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=sorted-set" target="_blank">https://redis.io/docs/latest/commands/?group=sorted-set</a></p>
 *
 * @author Yong.Teng
 */
public interface SortedSetOperations extends SortedSetCommands, RedisOperations {

	@Override
	default KeyValue<String, List<Tuple>> bzMPop(final String[] keys, final int timeout, final MinMax minMax) {
		return execute((client)->client.sortedSetCommands().bzMPop(keys, timeout, minMax));
	}

	@Override
	default KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax) {
		return execute((client)->client.sortedSetCommands().bzMPop(keys, timeout, minMax));
	}

	@Override
	default KeyValue<String, List<Tuple>> bzMPop(final String[] keys, final int timeout, final MinMax minMax,
												 final int count) {
		return execute((client)->client.sortedSetCommands().bzMPop(keys, timeout, minMax, count));
	}

	@Override
	default KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax,
												 final int count) {
		return execute((client)->client.sortedSetCommands().bzMPop(keys, timeout, minMax, count));
	}

	@Override
	default KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		return execute((client)->client.sortedSetCommands().bzPopMax(keys, timeout));
	}

	@Override
	default KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		return execute((client)->client.sortedSetCommands().bzPopMax(keys, timeout));
	}

	@Override
	default KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		return execute((client)->client.sortedSetCommands().bzPopMin(keys, timeout));
	}

	@Override
	default KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		return execute((client)->client.sortedSetCommands().bzPopMin(keys, timeout));
	}

	@Override
	default Long zAdd(final String key, final Tuple... members) {
		return execute((client)->client.sortedSetCommands().zAdd(key, members));
	}

	@Override
	default Long zAdd(final byte[] key, final Tuple... members) {
		return execute((client)->client.sortedSetCommands().zAdd(key, members));
	}

	@Override
	default Long zAdd(final String key, final Tuple[] members, final ZAddArgument argument) {
		return execute((client)->client.sortedSetCommands().zAdd(key, members, argument));
	}

	@Override
	default Long zAdd(final byte[] key, final Tuple[] members, final ZAddArgument argument) {
		return execute((client)->client.sortedSetCommands().zAdd(key, members, argument));
	}

	@Override
	default Long zCard(final String key) {
		return execute((client)->client.sortedSetCommands().zCard(key));
	}

	@Override
	default Long zCard(final byte[] key) {
		return execute((client)->client.sortedSetCommands().zCard(key));
	}

	@Override
	default Long zCount(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zCount(key, min, max));
	}

	@Override
	default Long zCount(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zCount(key, min, max));
	}

	@Override
	default List<String> zDiff(final String... keys) {
		return execute((client)->client.sortedSetCommands().zDiff(keys));
	}

	@Override
	default List<byte[]> zDiff(final byte[]... keys) {
		return execute((client)->client.sortedSetCommands().zDiff(keys));
	}

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> List<V> zDiff(final String[] keys, final Class<V> clazz);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> List<V> zDiff(final byte[][] keys, final Class<V> clazz);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> List<V> zDiff(final String[] keys, final TypeReference<V> type);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> List<V> zDiff(final byte[][] keys, final TypeReference<V> type);

	@Override
	default List<Tuple> zDiffWithScores(final String... keys) {
		return execute((client)->client.sortedSetCommands().zDiffWithScores(keys));
	}

	@Override
	default List<Tuple> zDiffWithScores(final byte[]... keys) {
		return execute((client)->client.sortedSetCommands().zDiffWithScores(keys));
	}

	@Override
	default Long zDiffStore(final String destKey, final String... keys) {
		return execute((client)->client.sortedSetCommands().zDiffStore(destKey, keys));
	}

	@Override
	default Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.sortedSetCommands().zDiffStore(destKey, keys));
	}

	@Override
	default Double zIncrBy(final String key, final double increment, final String member) {
		return execute((client)->client.sortedSetCommands().zIncrBy(key, increment, member));
	}

	@Override
	default Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		return execute((client)->client.sortedSetCommands().zIncrBy(key, increment, member));
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
	default Double zIncr(final String key, final String member) {
		return zIncrBy(key, 1, member);
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
	default Double zIncr(final byte[] key, final byte[] member) {
		return zIncrBy(key, 1, member);
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上减量一
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zDecr(final String key, final String member) {
		return zIncrBy(key, -1, member);
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上减量一
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zDecr(final byte[] key, final byte[] member) {
		return zIncrBy(key, -1, member);
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上减量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc.com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param increment
	 * 		增量
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zDecrBy(final String key, final double increment, final String member) {
		return zIncrBy(key, increment * -1, member);
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上减量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc.com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param increment
	 * 		增量
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zDecrBy(final byte[] key, final double increment, final byte[] member) {
		return zIncrBy(key, increment * -1, member);
	}

	@Override
	default List<String> zInter(final String... keys) {
		return execute((client)->client.sortedSetCommands().zInter(keys));
	}

	@Override
	default List<byte[]> zInter(final byte[]... keys) {
		return execute((client)->client.sortedSetCommands().zInter(keys));
	}

	@Override
	default List<String> zInter(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate));
	}

	@Override
	default List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate));
	}

	@Override
	default List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate, weights));
	}

	@Override
	default List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate, weights));
	}

	@Override
	default List<String> zInter(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zInter(keys, weights));
	}

	@Override
	default List<byte[]> zInter(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zInter(keys, weights));
	}

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final String[] keys, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final byte[][] keys, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final String[] keys, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final byte[][] keys, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final String[] keys, final Aggregate aggregate, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final String[] keys, final Aggregate aggregate, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final String[] keys, final Aggregate aggregate, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final String[] keys, final Aggregate aggregate, final double[] weights,
					   final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of intersection
	 */
	<V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final double[] weights,
					   final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final String[] keys, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final byte[][] keys, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final String[] keys, final double[] weights, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInter(final byte[][] keys, final double[] weights, final TypeReference<V> type);

	@Override
	default List<Tuple> zInterWithScores(final String... keys) {
		return execute((client)->client.sortedSetCommands().zInterWithScores(keys));
	}

	@Override
	default List<Tuple> zInterWithScores(final byte[]... keys) {
		return execute((client)->client.sortedSetCommands().zInterWithScores(keys));
	}

	@Override
	default List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zInterWithScores(keys, aggregate));
	}

	@Override
	default List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zInterWithScores(keys, aggregate));
	}

	@Override
	default List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetCommands().zInterWithScores(keys, aggregate, weights));
	}

	@Override
	default List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetCommands().zInterWithScores(keys, aggregate, weights));
	}

	@Override
	default List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zInterWithScores(keys, weights));
	}

	@Override
	default List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zInterWithScores(keys, weights));
	}

	@Override
	default long zInterCard(final String... keys) {
		return execute((client)->client.sortedSetCommands().zInterCard(keys));
	}

	@Override
	default long zInterCard(final byte[]... keys) {
		return execute((client)->client.sortedSetCommands().zInterCard(keys));
	}

	@Override
	default long zInterCard(final String[] keys, final int limit) {
		return execute((client)->client.sortedSetCommands().zInterCard(keys, limit));
	}

	@Override
	default long zInterCard(final byte[][] keys, final int limit) {
		return execute((client)->client.sortedSetCommands().zInterCard(keys, limit));
	}

	@Override
	default Long zInterStore(final String destKey, final String... keys) {
		return execute((client)->client.sortedSetCommands().zInterStore(destKey, keys));
	}

	@Override
	default Long zInterStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.sortedSetCommands().zInterStore(destKey, keys));
	}

	@Override
	default Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zInterStore(destKey, keys, aggregate));
	}

	@Override
	default Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zInterStore(destKey, keys, aggregate));
	}

	@Override
	default Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
							 final double... weights) {
		return execute((client)->client.sortedSetCommands().zInterStore(destKey, keys, aggregate, weights));
	}

	@Override
	default Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							 final double... weights) {
		return execute((client)->client.sortedSetCommands().zInterStore(destKey, keys, aggregate, weights));
	}

	@Override
	default Long zInterStore(final String destKey, final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zInterStore(destKey, keys, weights));
	}

	@Override
	default Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zInterStore(destKey, keys, weights));
	}

	@Override
	default Long zLexCount(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zLexCount(key, min, max));
	}

	@Override
	default Long zLexCount(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zLexCount(key, min, max));
	}

	@Override
	default KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax) {
		return execute((client)->client.sortedSetCommands().zMPop(keys, minMax));
	}

	@Override
	default KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax) {
		return execute((client)->client.sortedSetCommands().zMPop(keys, minMax));
	}

	@Override
	default KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax, final int count) {
		return execute((client)->client.sortedSetCommands().zMPop(keys, minMax, count));
	}

	@Override
	default KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax, final int count) {
		return execute((client)->client.sortedSetCommands().zMPop(keys, minMax, count));
	}

	@Override
	default List<Double> zMScore(final String key, final String... members) {
		return execute((client)->client.sortedSetCommands().zMScore(key, members));
	}

	@Override
	default List<Double> zMScore(final byte[] key, final byte[]... members) {
		return execute((client)->client.sortedSetCommands().zMScore(key, members));
	}

	@Override
	default Tuple zPopMax(final String key) {
		return execute((client)->client.sortedSetCommands().zPopMax(key));
	}

	@Override
	default Tuple zPopMax(final byte[] key) {
		return execute((client)->client.sortedSetCommands().zPopMax(key));
	}

	@Override
	default List<Tuple> zPopMax(final String key, final int count) {
		return execute((client)->client.sortedSetCommands().zPopMax(key, count));
	}

	@Override
	default List<Tuple> zPopMax(final byte[] key, final int count) {
		return execute((client)->client.sortedSetCommands().zPopMax(key, count));
	}

	@Override
	default Tuple zPopMin(final String key) {
		return execute((client)->client.sortedSetCommands().zPopMin(key));
	}

	@Override
	default Tuple zPopMin(final byte[] key) {
		return execute((client)->client.sortedSetCommands().zPopMin(key));
	}

	@Override
	default List<Tuple> zPopMin(final String key, int count) {
		return execute((client)->client.sortedSetCommands().zPopMin(key, count));
	}

	@Override
	default List<Tuple> zPopMin(final byte[] key, int count) {
		return execute((client)->client.sortedSetCommands().zPopMin(key, count));
	}

	@Override
	default String zRandMember(final String key) {
		return execute((client)->client.sortedSetCommands().zRandMember(key));
	}

	@Override
	default byte[] zRandMember(final byte[] key) {
		return execute((client)->client.sortedSetCommands().zRandMember(key));
	}

	@Override
	default List<String> zRandMember(final String key, final int count) {
		return execute((client)->client.sortedSetCommands().zRandMember(key, count));
	}

	@Override
	default List<byte[]> zRandMember(final byte[] key, final int count) {
		return execute((client)->client.sortedSetCommands().zRandMember(key, count));
	}

	/**
	 * 返回有序集合 key 中的一个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的一个随机元素反序列化后的对象
	 */
	<V> V zRandMember(final String key, final Class<V> clazz);

	/**
	 * 返回有序集合 key 中的一个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的一个随机元素反序列化后的对象
	 */
	<V> V zRandMember(final byte[] key, final Class<V> clazz);

	/**
	 * 返回有序集合 key 中的一个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的一个随机元素反序列化后的对象
	 */
	<V> V zRandMember(final String key, final TypeReference<V> type);

	/**
	 * 返回有序集合 key 中的一个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的一个随机元素反序列化后的对象
	 */
	<V> V zRandMember(final byte[] key, final TypeReference<V> type);

	/**
	 * 返回有序集合 key 中的 count 个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的随机元素反序列化后的对象
	 */
	<V> List<V> zRandMember(final String key, final int count, final Class<V> clazz);

	/**
	 * 返回有序集合 key 中的 count 个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的随机元素反序列化后的对象
	 */
	<V> List<V> zRandMember(final byte[] key, final int count, final Class<V> clazz);

	/**
	 * 返回有序集合 key 中的 count 个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的随机元素反序列化后的对象
	 */
	<V> List<V> zRandMember(final String key, final int count, final TypeReference<V> type);

	/**
	 * 返回有序集合 key 中的 count 个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的随机元素反序列化后的对象
	 */
	<V> List<V> zRandMember(final byte[] key, final int count, final TypeReference<V> type);

	@Override
	default Tuple zRandMemberWithScores(final String key) {
		return execute((client)->client.sortedSetCommands().zRandMemberWithScores(key));
	}

	@Override
	default Tuple zRandMemberWithScores(final byte[] key) {
		return execute((client)->client.sortedSetCommands().zRandMemberWithScores(key));
	}

	@Override
	default List<Tuple> zRandMemberWithScores(final String key, final int count) {
		return execute((client)->client.sortedSetCommands().zRandMemberWithScores(key, count));
	}

	@Override
	default List<Tuple> zRandMemberWithScores(final byte[] key, final int count) {
		return execute((client)->client.sortedSetCommands().zRandMemberWithScores(key, count));
	}

	@Override
	default List<String> zRange(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end));
	}

	@Override
	default List<byte[]> zRange(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end));
	}

	@Override
	default List<String> zRange(final String key, final long start, final long end, final ZRangeType type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type));
	}

	@Override
	default List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type));
	}

	@Override
	default List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
								final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, rev));
	}

	@Override
	default List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
								final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, rev));
	}

	@Override
	default List<String> zRange(final String key, final long start, final long end, final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, rev));
	}

	@Override
	default List<byte[]> zRange(final byte[] key, final long start, final long end, final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, rev));
	}

	@Override
	default List<String> zRange(final String key, final long start, final long end, final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, offset, count));
	}

	@Override
	default List<byte[]> zRange(final byte[] key, final long start, final long end, final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, offset, count));
	}

	@Override
	default List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
								final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, offset, count));
	}

	@Override
	default List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
								final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, offset, count));
	}

	@Override
	default List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
								final boolean rev, final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, rev, offset, count));
	}

	@Override
	default List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
								final boolean rev, final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, rev, offset, count));
	}

	@Override
	default List<String> zRange(final String key, final long start, final long end, final boolean rev, final int offset,
								final int count) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, rev, offset, count));
	}

	@Override
	default List<byte[]> zRange(final byte[] key, final long start, final long end, final boolean rev, final int offset,
								final int count) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, rev, offset, count));
	}

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 *        {@link ZRangeType}
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 *        {@link ZRangeType}
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param zRangeType
	 *        {@link ZRangeType}
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
					   final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param zRangeType
	 *        {@link ZRangeType}
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
					   final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 *        {@link ZRangeType}
	 * @param rev
	 * 		REV
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type, final boolean rev,
					   final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 *        {@link ZRangeType}
	 * @param rev
	 * 		REV
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type, final boolean rev,
					   final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param zRangeType
	 *        {@link ZRangeType}
	 * @param rev
	 * 		REV
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
					   final boolean rev, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param zRangeType
	 *        {@link ZRangeType}
	 * @param rev
	 * 		REV
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
					   final boolean rev, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final int offset, final int count,
					   final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final int offset, final int count,
					   final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final int offset, final int count,
					   final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final int offset, final int count,
					   final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 *        {@link ZRangeType}
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type, final int offset,
					   final int count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 *        {@link ZRangeType}
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type, final int offset,
					   final int count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param zRangeType
	 *        {@link ZRangeType}
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
					   final int offset, final int count, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param zRangeType
	 *        {@link ZRangeType}
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
					   final int offset, final int count, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 *        {@link ZRangeType}
	 * @param rev
	 * 		REV
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type, final boolean rev,
					   final int offset, final int count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 *        {@link ZRangeType}
	 * @param rev
	 * 		REV
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type, final boolean rev,
					   final int offset, final int count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param zRangeType
	 *        {@link ZRangeType}
	 * @param rev
	 * 		REV
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
					   final boolean rev, final int offset, final int count, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param zRangeType
	 *        {@link ZRangeType}
	 * @param rev
	 * 		REV
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
					   final boolean rev, final int offset, final int count, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param rev
	 * 		REV
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final boolean rev, final int offset,
					   final int count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param rev
	 * 		REV
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final boolean rev, final int offset,
					   final int count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param rev
	 * 		REV
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final String key, final long start, final long end, final boolean rev, final int offset,
					   final int count, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param rev
	 * 		REV
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRange(final byte[] key, final long start, final long end, final boolean rev, final int offset,
					   final int count, final TypeReference<V> type);

	@Override
	default List<Tuple> zRangeWithScores(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end));
	}

	@Override
	default List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end));
	}

	@Override
	default List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, type));
	}

	@Override
	default List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, type));
	}

	@Override
	default List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
										 final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, type, rev));
	}

	@Override
	default List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
										 final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, type, rev));
	}

	@Override
	default List<Tuple> zRangeWithScores(final String key, final long start, final long end, final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, rev));
	}

	@Override
	default List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, rev));
	}

	@Override
	default List<Tuple> zRangeWithScores(final String key, final long start, final long end, final int offset,
										 final int count) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, offset, count));
	}

	@Override
	default List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final int offset,
										 final int count) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, offset, count));
	}

	@Override
	default List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
										 final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, type, offset, count));
	}

	@Override
	default List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
										 final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, type, offset, count));
	}

	@Override
	default List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
										 final boolean rev, final int offset, final int count) {
		return execute(
				(client)->client.sortedSetCommands().zRangeWithScores(key, start, end, type, rev, offset, count));
	}

	@Override
	default List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
										 final boolean rev, final int offset, final int count) {
		return execute(
				(client)->client.sortedSetCommands().zRangeWithScores(key, start, end, type, rev, offset, count));
	}

	@Override
	default List<Tuple> zRangeWithScores(final String key, final long start, final long end, final boolean rev,
										 final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, rev, offset, count));
	}

	@Override
	default List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final boolean rev,
										 final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRangeWithScores(key, start, end, rev, offset, count));
	}

	@Override
	default List<String> zRangeByLex(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max));
	}

	@Override
	default List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max));
	}

	@Override
	default List<String> zRangeByLex(final String key, final double min, final double max, final int offset,
									 final int count) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max, offset, count));
	}

	@Override
	default List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
									 final int count) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max, offset, count));
	}

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLex(final String key, final double min, final double max, final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLex(final String key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLex(final String key, final double min, final double max, final int offset, final int count,
							final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final int offset, final int count,
							final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLex(final String key, final double min, final double max, final int offset, final int count,
							final TypeReference<V> type);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final int offset, final int count,
							final TypeReference<V> type);

	@Override
	default List<String> zRangeByScore(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max));
	}

	@Override
	default List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max));
	}

	@Override
	default List<String> zRangeByScore(final String key, final double min, final double max, final int offset,
									   final int count) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max, offset, count));
	}

	@Override
	default List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
									   final int count) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max, offset, count));
	}

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScore(final String key, final double min, final double max, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScore(final String key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScore(final String key, final double min, final double max, final int offset, final int count,
							  final Class<V> clazz);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final int offset, final int count,
							  final Class<V> clazz);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScore(final String key, final double min, final double max, final int offset, final int count,
							  final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final int offset, final int count,
							  final TypeReference<V> type);

	@Override
	default List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRangeByScoreWithScores(key, min, max));
	}

	@Override
	default List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRangeByScoreWithScores(key, min, max));
	}

	@Override
	default List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
												final int count) {
		return execute((client)->client.sortedSetCommands().zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	default List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
												final int count) {
		return execute((client)->client.sortedSetCommands().zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final ZRangeType type) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, type));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final ZRangeType type) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, type));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final ZRangeType type, final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, type, rev));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final ZRangeType type, final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, type, rev));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, rev));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final boolean rev) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, rev));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end, final int offset,
							 final int count) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, offset, count));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final int offset,
							 final int count) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, offset, count));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final ZRangeType type, final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, type, offset, count));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final ZRangeType type, final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, type, offset, count));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final ZRangeType type, final boolean rev, final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, type, rev, offset,
				count));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final ZRangeType type, final boolean rev, final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, type, rev, offset,
				count));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final boolean rev, final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, rev, offset,
				count));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final boolean rev, final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRangeStore(destKey, key, start, end, rev, offset,
				count));
	}

	@Override
	default Long zRank(final String key, final String member) {
		return execute((client)->client.sortedSetCommands().zRank(key, member));
	}

	@Override
	default Long zRank(final byte[] key, final byte[] member) {
		return execute((client)->client.sortedSetCommands().zRank(key, member));
	}

	@Override
	default KeyValue<Long, Double> zRankWithScores(final String key, final String member) {
		return execute((client)->client.sortedSetCommands().zRankWithScores(key, member));
	}

	@Override
	default KeyValue<Long, Double> zRankWithScores(final byte[] key, final byte[] member) {
		return execute((client)->client.sortedSetCommands().zRankWithScores(key, member));
	}

	@Override
	default Long zRem(final String key, final String... members) {
		return execute((client)->client.sortedSetCommands().zRem(key, members));
	}

	@Override
	default Long zRem(final byte[] key, final byte[]... members) {
		return execute((client)->client.sortedSetCommands().zRem(key, members));
	}

	@Override
	default Long zRemRangeByLex(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRemRangeByLex(key, min, max));
	}

	@Override
	default Long zRemRangeByLex(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRemRangeByLex(key, min, max));
	}

	@Override
	default Long zRemRangeByRank(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRemRangeByRank(key, start, end));
	}

	@Override
	default Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRemRangeByRank(key, start, end));
	}

	@Override
	default Long zRemRangeByScore(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRemRangeByScore(key, min, max));
	}

	@Override
	default Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRemRangeByScore(key, min, max));
	}

	@Override
	default List<String> zRevRange(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRevRange(key, start, end));
	}

	@Override
	default List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRevRange(key, start, end));
	}

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc.com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象后的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRevRange(final String key, final long start, final long end, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc.com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象后的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRevRange(final byte[] key, final long start, final long end, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc.com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象后的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRevRange(final String key, final long start, final long end, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc.com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象后的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRevRange(final byte[] key, final long start, final long end, final TypeReference<V> type);

	@Override
	default List<Tuple> zRevRangeWithScores(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRevRangeWithScores(key, start, end));
	}

	@Override
	default List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetCommands().zRevRangeWithScores(key, start, end));
	}

	@Override
	default List<String> zRevRangeByLex(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max));
	}

	@Override
	default List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max));
	}

	@Override
	default List<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
										final int count) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	default List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
										final int count) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max, offset, count));
	}

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLex(final String key, final double min, final double max, final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max, final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLex(final String key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLex(final String key, final double min, final double max, final int offset, final int count,
							   final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset, final int count,
							   final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLex(final String key, final double min, final double max, final int offset, final int count,
							   final TypeReference<V> type);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset, final int count,
							   final TypeReference<V> type);

	@Override
	default List<String> zRevRangeByScore(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max));
	}

	@Override
	default List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max));
	}

	default List<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
										  final int count) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max, offset, count));
	}

	default List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
										  final int count) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max, offset, count));
	}

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScore(final String key, final double min, final double max, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScore(final String key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScore(final String key, final double min, final double max, final int offset,
								 final int count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
								 final int count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScore(final String key, final double min, final double max, final int offset,
								 final int count, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
								 final int count, final TypeReference<V> type);

	@Override
	default List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	default List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	default List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												   final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	default List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												   final int offset, final int count) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	default Long zRevRank(final String key, final String member) {
		return execute((client)->client.sortedSetCommands().zRevRank(key, member));
	}

	@Override
	default Long zRevRank(final byte[] key, final byte[] member) {
		return execute((client)->client.sortedSetCommands().zRevRank(key, member));
	}

	@Override
	default KeyValue<Long, Double> zRevRankWithScore(final String key, final String member) {
		return execute((client)->client.sortedSetCommands().zRevRankWithScore(key, member));
	}

	@Override
	default KeyValue<Long, Double> zRevRankWithScore(final byte[] key, final byte[] member) {
		return execute((client)->client.sortedSetCommands().zRevRankWithScore(key, member));
	}

	@Override
	default ScanResult<Tuple> zScan(final String key, final String cursor) {
		return execute((client)->client.sortedSetCommands().zScan(key, cursor));
	}

	@Override
	default ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor) {
		return execute((client)->client.sortedSetCommands().zScan(key, cursor));
	}

	@Override
	default ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern) {
		return execute((client)->client.sortedSetCommands().zScan(key, cursor, pattern));
	}

	@Override
	default ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.sortedSetCommands().zScan(key, cursor, pattern));
	}

	@Override
	default ScanResult<Tuple> zScan(final String key, final String cursor, final int count) {
		return execute((client)->client.sortedSetCommands().zScan(key, cursor, count));
	}

	@Override
	default ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final int count) {
		return execute((client)->client.sortedSetCommands().zScan(key, cursor, count));
	}

	@Override
	default ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern, final int count) {
		return execute((client)->client.sortedSetCommands().zScan(key, cursor, pattern, count));
	}

	@Override
	default ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count) {
		return execute((client)->client.sortedSetCommands().zScan(key, cursor, pattern, count));
	}

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/zscan/" target="_blank">https://redis.io/docs/latest/commands/zscan/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default ScanResult<Tuple> zScan(final String key, final long cursor) {
		return zScan(key, Long.toString(cursor));
	}

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/zscan/" target="_blank">https://redis.io/docs/latest/commands/zscan/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default ScanResult<Tuple> zScan(final byte[] key, final long cursor) {
		return zScan(key, NumberUtils.long2bytes(cursor));
	}

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/zscan/" target="_blank">https://redis.io/docs/latest/commands/zscan/</a></p>
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
	default ScanResult<Tuple> zScan(final String key, final long cursor, final String pattern) {
		return zScan(key, Long.toString(cursor), pattern);
	}

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/zscan/" target="_blank">https://redis.io/docs/latest/commands/zscan/</a></p>
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
	default ScanResult<Tuple> zScan(final byte[] key, final long cursor, final byte[] pattern) {
		return zScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/zscan/" target="_blank">https://redis.io/docs/latest/commands/zscan/</a></p>
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
	default ScanResult<Tuple> zScan(final String key, final long cursor, final String pattern, final int count) {
		return zScan(key, Long.toString(cursor), pattern, count);
	}

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/zscan/" target="_blank">https://redis.io/docs/latest/commands/zscan/</a></p>
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
	default ScanResult<Tuple> zScan(final byte[] key, final long cursor, final byte[] pattern, final int count) {
		return zScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/zscan/" target="_blank">https://redis.io/docs/latest/commands/zscan/</a></p>
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
	default ScanResult<Tuple> zScan(final String key, final long cursor, final int count) {
		return zScan(key, Long.toString(cursor), Long.toString(count));
	}

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/zscan/" target="_blank">https://redis.io/docs/latest/commands/zscan/</a></p>
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
	default ScanResult<Tuple> zScan(final byte[] key, final long cursor, final int count) {
		return zScan(key, NumberUtils.long2bytes(cursor), NumberUtils.long2bytes(count));
	}

	@Override
	default Double zScore(final String key, final String member) {
		return execute((client)->client.sortedSetCommands().zScore(key, member));
	}

	@Override
	default Double zScore(final byte[] key, final byte[] member) {
		return execute((client)->client.sortedSetCommands().zScore(key, member));
	}

	@Override
	default List<String> zUnion(final String... keys) {
		return execute((client)->client.sortedSetCommands().zUnion(keys));
	}

	@Override
	default List<byte[]> zUnion(final byte[]... keys) {
		return execute((client)->client.sortedSetCommands().zUnion(keys));
	}

	@Override
	default List<String> zUnion(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate));
	}

	@Override
	default List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate));
	}

	@Override
	default List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate, weights));
	}

	@Override
	default List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate, weights));
	}

	@Override
	default List<String> zUnion(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, weights));
	}

	@Override
	default List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, weights));
	}

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 反序列化为对象后的集合并集
	 */
	<V> List<V> zUnion(final String[] keys, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 反序列化为对象后的集合并集
	 */
	<V> List<V> zUnion(final byte[][] keys, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 反序列化为对象后的集合并集
	 */
	<V> List<V> zUnion(final String[] keys, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 反序列化为对象后的集合并集
	 */
	<V> List<V> zUnion(final byte[][] keys, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final double[] weights,
					   final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final double[] weights,
					   final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final String[] keys, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final byte[][] keys, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final String[] keys, final double[] weights, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnion(final byte[][] keys, final double[] weights, final TypeReference<V> type);

	@Override
	default List<Tuple> zUnionWithScores(final String... keys) {
		return execute((client)->client.sortedSetCommands().zUnionWithScores(keys));
	}

	@Override
	default List<Tuple> zUnionWithScores(final byte[]... keys) {
		return execute((client)->client.sortedSetCommands().zUnionWithScores(keys));
	}

	@Override
	default List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zUnionWithScores(keys, aggregate));
	}

	@Override
	default List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zUnionWithScores(keys, aggregate));
	}

	@Override
	default List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnionWithScores(keys, aggregate, weights));
	}

	@Override
	default List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnionWithScores(keys, aggregate, weights));
	}

	@Override
	default List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnionWithScores(keys, weights));
	}

	@Override
	default List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnionWithScores(keys, weights));
	}

	@Override
	default Long zUnionStore(final String destKey, final String... keys) {
		return execute((client)->client.sortedSetCommands().zUnionStore(destKey, keys));
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.sortedSetCommands().zUnionStore(destKey, keys));
	}

	@Override
	default Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zUnionStore(destKey, keys, aggregate));
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetCommands().zUnionStore(destKey, keys, aggregate));
	}

	@Override
	default Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
							 final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnionStore(destKey, keys, aggregate, weights));
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							 final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnionStore(destKey, keys, aggregate, weights));
	}

	@Override
	default Long zUnionStore(final String destKey, final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnionStore(destKey, keys, weights));
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetCommands().zUnionStore(destKey, keys, weights));
	}

}
