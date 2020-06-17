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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

import com.buession.lang.KeyValue;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 有序集合命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/index.html" target="_blank">http://redisdoc.com/sorted_set/index
 * .html</a></p>
 *
 * @author Yong.Teng
 */
public interface SortedSetCommands extends RedisCommands {

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final String key, final Map<String, Number> members);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final byte[] key, final Map<byte[], Number> members);

	/**
	 * 获取有序集 key 的基数
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcard.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcard.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 有序集 key 的基数
	 */
	Long zCard(final String key);

	/**
	 * 获取有序集 key 的基数
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcard.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcard.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 有序集 key 的基数
	 */
	Long zCard(final byte[] key);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final String key, final float min, final float max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final byte[] key, final float min, final float max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final String key, final int min, final int max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final byte[] key, final int min, final int max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final String key, final long min, final long max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final byte[] key, final long min, final long max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final String key, final String min, final String max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 * @param increment
	 * 		增量
	 *
	 * @return member 成员的新 score 值
	 */
	Double zIncrBy(final String key, final String member, final float increment);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 * @param increment
	 * 		增量
	 *
	 * @return member 成员的新 score 值
	 */
	Double zIncrBy(final byte[] key, final byte[] member, final float increment);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 * @param increment
	 * 		增量
	 *
	 * @return member 成员的新 score 值
	 */
	Double zIncrBy(final String key, final String member, final double increment);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 * @param increment
	 * 		增量
	 *
	 * @return member 成员的新 score 值
	 */
	Double zIncrBy(final byte[] key, final byte[] member, final double increment);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 * @param increment
	 * 		增量
	 *
	 * @return member 成员的新 score 值
	 */
	Double zIncrBy(final String key, final String member, final int increment);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 * @param increment
	 * 		增量
	 *
	 * @return member 成员的新 score 值
	 */
	Double zIncrBy(final byte[] key, final byte[] member, final int increment);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 * @param increment
	 * 		增量
	 *
	 * @return member 成员的新 score 值
	 */
	Double zIncrBy(final String key, final String member, final long increment);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 * @param increment
	 * 		增量
	 *
	 * @return member 成员的新 score 值
	 */
	Double zIncrBy(final byte[] key, final byte[] member, final long increment);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final String destKey, final String... keys);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final byte[] destKey, final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final String destKey, final double[] weights, final String... keys);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights, final String... keys);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights, final byte[]... keys);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final String key, final float min, final float max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final byte[] key, final float min, final float max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final String key, final double min, final double max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final byte[] key, final double min, final double max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final String key, final int min, final int max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final byte[] key, final int min, final int max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final String key, final long min, final long max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final byte[] key, final long min, final long max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final String key, final String min, final String max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<String> zRange(final String key, final int start, final int end);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<byte[]> zRange(final byte[] key, final int start, final int end);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<String> zRange(final String key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<byte[]> zRange(final byte[] key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的带有 score 成员；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，带有 score 有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<Tuple> zRangeWithScores(final String key, final int start, final int end);

	/**
	 * 获取有序集 key 中，指定区间内的带有 score 成员；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，带有 score 有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<Tuple> zRangeWithScores(final byte[] key, final int start, final int end);

	/**
	 * 获取有序集 key 中，指定区间内的带有 score 成员；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，带有 score 有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<Tuple> zRangeWithScores(final String key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的带有 score 成员；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，带有 score 有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	Set<String> zRangeByLex(final String key, final float min, final float max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	Set<String> zRangeByLex(final String key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	Set<String> zRangeByLex(final String key, final int min, final int max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	Set<String> zRangeByLex(final String key, final long min, final long max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	Set<String> zRangeByLex(final String key, final String min, final String max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	Set<String> zRangeByLex(final String key, final float min, final float max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	Set<String> zRangeByLex(final String key, final double min, final double max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	Set<String> zRangeByLex(final String key, final int min, final int max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	Set<String> zRangeByLex(final String key, final long min, final long max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	Set<String> zRangeByLex(final String key, final String min, final String max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRangeByScore(final String key, final float min, final float max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRangeByScore(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRangeByScore(final String key, final int min, final int max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRangeByScore(final String key, final long min, final long max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRangeByScore(final String key, final String min, final String max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<String> zRangeByScore(final String key, final float min, final float max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<String> zRangeByScore(final String key, final double min, final double max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<String> zRangeByScore(final String key, final int min, final int max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<String> zRangeByScore(final String key, final long min, final long max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<String> zRangeByScore(final String key, final String min, final String max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中成员 member 的排名；其中有序集成员按 score 值递增（从小到大）顺序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrank.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名；
	 * 如果 member 不是有序集 key 的成员，返回 null
	 */
	Long zRank(final String key, final String member);

	/**
	 * 获取有序集 key 中成员 member 的排名；其中有序集成员按 score 值递增（从小到大）顺序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrank.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名；
	 * 如果 member 不是有序集 key 的成员，返回 null
	 */
	Long zRank(final byte[] key, final byte[] member);

	/**
	 * 删除并返回有序集合 key 中的 1 个具有最高得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 *
	 * @return 弹出的元素和分数列表
	 */
	Tuple zPopMax(final String key);

	/**
	 * 删除并返回有序集合 key 中的 1 个具有最高得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 *
	 * @return 弹出的元素和分数列表
	 */
	Tuple zPopMax(final byte[] key);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最高得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	Set<Tuple> zPopMax(final String key, final int count);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最高得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	Set<Tuple> zPopMax(final byte[] key, final int count);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最高得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	Set<Tuple> zPopMax(final String key, final long count);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最高得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	Set<Tuple> zPopMax(final byte[] key, final long count);

	/**
	 * 删除并返回有序集合 key 中的 1 个具有最低得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 *
	 * @return 弹出的元素和分数列表
	 */
	Tuple zPopMin(final String key);

	/**
	 * 删除并返回有序集合 key 中的 1 个具有最低得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 *
	 * @return 弹出的元素和分数列表
	 */
	Tuple zPopMin(final byte[] key);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最低得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	Set<Tuple> zPopMin(final String key, final int count);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最低得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	Set<Tuple> zPopMin(final byte[] key, final int count);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最低得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	Set<Tuple> zPopMin(final String key, final long count);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最低得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	Set<Tuple> zPopMin(final byte[] key, final long count);

	/**
	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrem.html" target="_blank">http://redisdoc.com/sorted_set/zrem
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个成员
	 *
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	 */
	Long zRem(final String key, final String... members);

	/**
	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zrem.html" target="_blank">http://redisdoccom/sorted_set/zrem.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个成员
	 *
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	 */
	Long zRem(final byte[] key, final byte[]... members);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final String key, final float min, final float max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final byte[] key, final float min, final float max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final String key, final double min, final double max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final byte[] key, final double min, final double max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final String key, final int min, final int max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final byte[] key, final int min, final int max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final String key, final long min, final long max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final byte[] key, final long min, final long max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final String key, final String min, final String max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final String key, final float min, final float max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final byte[] key, final float min, final float max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final String key, final double min, final double max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final byte[] key, final double min, final double max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final String key, final int min, final int max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final byte[] key, final int min, final int max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final String key, final long min, final long max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final byte[] key, final long min, final long max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final String key, final String min, final String max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 移除有序集 key 中，指定排名（rank）区间内的所有成员；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyrank.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始排名
	 * @param end
	 * 		结束排名
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByRank(final String key, final int start, final int end);

	/**
	 * 移除有序集 key 中，指定排名（rank）区间内的所有成员；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyrank.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始排名
	 * @param end
	 * 		结束排名
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByRank(final byte[] key, final int start, final int end);

	/**
	 * 移除有序集 key 中，指定排名（rank）区间内的所有成员；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyrank.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始排名
	 * @param end
	 * 		结束排名
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByRank(final String key, final long start, final long end);

	/**
	 * 移除有序集 key 中，指定排名（rank）区间内的所有成员；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyrank.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zremrangebyrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始排名
	 * @param end
	 * 		结束排名
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByRank(final byte[] key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<String> zRevRange(final String key, final int start, final int end);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<byte[]> zRevRange(final byte[] key, final int start, final int end);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<String> zRevRange(final String key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<byte[]> zRevRange(final byte[] key, final long start, final long end);

	/**
	 * 获取有序集 key 中，带有 score 指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<Tuple> zRevRangeWithScores(final String key, final int start, final int end);

	/**
	 * 获取有序集 key 中，带有 score 指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<Tuple> zRevRangeWithScores(final byte[] key, final int start, final int end);

	/**
	 * 获取有序集 key 中，带有 score 指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end);

	/**
	 * 获取有序集 key 中，带有 score 指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<String> zRevRangeByLex(final String key, final float min, final float max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<String> zRevRangeByLex(final String key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<String> zRevRangeByLex(final String key, final int min, final int max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<String> zRevRangeByLex(final String key, final long min, final long max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<String> zRevRangeByLex(final String key, final String min, final String max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<String> zRevRangeByLex(final String key, final float min, final float max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
	 final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
	 final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<String> zRevRangeByLex(final String key, final int min, final int max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<String> zRevRangeByLex(final String key, final long min, final long max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max, final int offset, final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset,
	 final int count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrangebylex.html</a></p>
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRevRangeByScore(final String key, final float min, final float max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRevRangeByScore(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRevRangeByScore(final String key, final int min, final int max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRevRangeByScore(final String key, final long min, final long max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRevRangeByScore(final String key, final String min, final String max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRevRangeByScore(final String key, final float min, final float max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRevRangeByScore(final String key, final int min, final int max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRevRangeByScore(final String key, final long min, final long max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final int offset, final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrangebyscore.html</a></p>
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
	 final int count);

	/**
	 * 获取有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减（从大到小）排序
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrank.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名；
	 * 如果 member 不是有序集 key 的成员，返回 null
	 */
	Long zRevRank(final String key, final String member);

	/**
	 * 获取有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减（从大到小）排序
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrank.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zrevrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名；
	 * 如果 member 不是有序集 key 的成员，返回 null
	 */
	Long zRevRank(final byte[] key, final byte[] member);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<List<Tuple>> zScan(final String key, final int cursor);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<List<Tuple>> zScan(final String key, final long cursor);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan
	 * .html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<List<Tuple>> zScan(final String key, final String cursor);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set
	 * /zscan.html</a></p>
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
	ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan
	 * .html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final String key, final int cursor, final int count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final int count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final String key, final long cursor, final int count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final int count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set
	 * /zscan.html</a></p>
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
	ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zscan.html</a></p>
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
	ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern, final int count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern, final int count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final int count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zscan.html</a></p>
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
	ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final int count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a>
	 * </p>
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
	ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count);

	/**
	 * 获取有序集 key 中，成员 member 的 score 值
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscore.html" target="_blank">http://redisdoc.com/sorted_set/zscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return member 成员的 score 值
	 */
	Double zScore(final String key, final String member);

	/**
	 * 获取有序集 key 中，成员 member 的 score 值
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zscore.html" target="_blank">http://redisdoc.com/sorted_set/zscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return member 成员的 score 值
	 */
	Double zScore(final byte[] key, final byte[] member);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final String destKey, final String... keys);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final byte[] destKey, final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final String destKey, final double[] weights, final String... keys);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights, final String... keys);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc
	 * .com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights, final byte[]... keys);

}
