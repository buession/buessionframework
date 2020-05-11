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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.operations;

import com.buession.lang.KeyValue;
import com.buession.redis.core.command.SortedSetCommands;

/**
 * 有序集合运算
 *
 * <p>详情说明
 * <a href="http://redisdoc.com/sorted_set/index.html" target="_blank">http://redisdoc.com/sorted_set/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface SortedSetOperations extends SortedSetCommands, RedisOperations {

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
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
	Long zAdd(final String key, final float score, final String member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
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
	Long zAdd(final byte[] key, final float score, final byte[] member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
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
	Long zAdd(final String key, final double score, final String member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
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
	Long zAdd(final byte[] key, final double score, final byte[] member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
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
	Long zAdd(final String key, final int score, final String member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
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
	Long zAdd(final byte[] key, final int score, final byte[] member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
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
	Long zAdd(final String key, final long score, final String member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
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
	Long zAdd(final byte[] key, final long score, final byte[] member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
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
	Long zAdd(final String key, final Number score, final String member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
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
	Long zAdd(final byte[] key, final Number score, final byte[] member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		元素及其 score
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final String key, final KeyValue<String, Number> member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		元素及其 score
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final byte[] key, final KeyValue<byte[], Number> member);

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
	Double zIncr(final String key, final String member);

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
	Double zIncr(final byte[] key, final byte[] member);

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
	Long zRem(final String key, final String member);

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
	Long zRem(final byte[] key, final byte[] member);

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
	Long zInterStore(final String destKey, final String key);

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
	Long zInterStore(final byte[] destKey, final byte[] key);

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
	Long zInterStore(final String destKey, final Aggregate aggregate, final String key);

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
	Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[] key);

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
	Long zInterStore(final String destKey, final double weight, final String key);

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
	Long zInterStore(final byte[] destKey, final double weight, final byte[] key);

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
	Long zInterStore(final String destKey, final Aggregate aggregate, final double weight, final String key);

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
	Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double weight, final byte[] key);

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
	Long zUnionStore(final String destKey, final String key);

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
	Long zUnionStore(final byte[] destKey, final byte[] key);

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
	Long zUnionStore(final String destKey, final Aggregate aggregate, final String key);

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
	Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[] key);

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
	Long zUnionStore(final String destKey, final double weight, final String key);

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
	Long zUnionStore(final byte[] destKey, final double weight, final byte[] key);

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
	Long zUnionStore(final String destKey, final Aggregate aggregate, final double weight, final String key);

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
	Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double weight, final byte[] key);

}
