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
package com.buession.redis.core.command;

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.TopKInfo;

import java.util.List;

/**
 * TOP K 命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=topk" target="_blank">https://redis.io/docs/latest/commands/?group=topk</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface TopKCommands extends RedisCommands {

	/**
	 * 向 Top-K 数据结构中添加元素或增加现有元素的计数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.add/" target="_blank">https://redis.io/docs/latest/commands/topk.add</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		一个或多个要添加的项
	 *
	 * @return 每个元素对应你添加的每一个项的反馈
	 */
	List<String> topKAdd(final String key, final String... items);

	/**
	 * 向 Top-K 数据结构中添加元素或增加现有元素的计数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.add/" target="_blank">https://redis.io/docs/latest/commands/topk.add</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		一个或多个要添加的项
	 *
	 * @return 每个元素对应你添加的每一个项的反馈
	 */
	List<byte[]> topKAdd(final byte[] key, final byte[]... items);

	/**
	 * 查询指定元素在 Top-K 数据结构中的估算计数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.count/" target="_blank">https://redis.io/docs/latest/commands/topk.count</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		一个或多个要查询的元素
	 *
	 * @return 元素在 Top-K 数据结构中的估算计数
	 */
	List<Long> topKCount(final String key, final String... items);

	/**
	 * 查询指定元素在 Top-K 数据结构中的估算计数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.count/" target="_blank">https://redis.io/docs/latest/commands/topk.count</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		一个或多个要查询的元素
	 *
	 * @return 元素在 Top-K 数据结构中的估算计数
	 */
	List<Long> topKCount(final byte[] key, final byte[]... items);

	/**
	 * 按指定的增量（权重）增加 Top-K 数据结构中元素的计数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.count/" target="_blank">https://redis.io/docs/latest/commands/topk.count</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		一个或多个要增加计数的元素及值
	 *
	 * @return 每个元素对应一个操作的反馈
	 */
	@SuppressWarnings({"unchecked"})
	List<String> topKIncrBy(final String key, final KeyValue<String, Long>... items);

	/**
	 * 按指定的增量（权重）增加 Top-K 数据结构中元素的计数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.incrby/" target="_blank">https://redis.io/docs/latest/commands/topk.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		一个或多个要增加计数的元素及值
	 *
	 * @return 每个元素对应一个操作的反馈
	 */
	@SuppressWarnings({"unchecked"})
	List<byte[]> topKIncrBy(final byte[] key, final KeyValue<byte[], Long>... items);

	/**
	 * 查询 Top-K 数据结构的元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.info/" target="_blank">https://redis.io/docs/latest/commands/topk.info/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return Top-K 数据结构的元信息
	 */
	TopKInfo topKInfo(final String key);

	/**
	 * 查询 Top-K 数据结构的元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.info/" target="_blank">https://redis.io/docs/latest/commands/topk.info/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return Top-K 数据结构的元信息
	 */
	TopKInfo topKInfo(final byte[] key);

	/**
	 * 获取 Top-K 数据结构中当前所有上榜的元素列表
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.list/" target="_blank">https://redis.io/docs/latest/commands/topk.list/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return Top-K 数据结构中当前所有上榜的元素列表
	 */
	List<String> topKList(final String key);

	/**
	 * 获取 Top-K 数据结构中当前所有上榜的元素列表
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.list/" target="_blank">https://redis.io/docs/latest/commands/topk.list/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return Top-K 数据结构中当前所有上榜的元素列表
	 */
	List<String> topKList(final byte[] key);

	/**
	 * 获取 Top-K 数据结构中当前所有上榜的元素列表
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.list/" target="_blank">https://redis.io/docs/latest/commands/topk.list/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return Top-K 数据结构中当前所有上榜的元素列表
	 */
	List<KeyValue<String, Long>> topKListWithCount(final String key);

	/**
	 * 获取 Top-K 数据结构中当前所有上榜的元素列表
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.list/" target="_blank">https://redis.io/docs/latest/commands/topk.list/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return Top-K 数据结构中当前所有上榜的元素列表
	 */
	List<KeyValue<byte[], Long>> topKListWithCount(final byte[] key);

	/**
	 * 快速判断一个或多个元素是否存在于 Top-K 列表中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.query/" target="_blank">https://redis.io/docs/latest/commands/topk.query/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		一个或多个要查询的元素
	 *
	 * @return 元素是否存在于 Top-K 列表中
	 */
	List<Boolean> topKQuery(final String key, final String... items);

	/**
	 * 快速判断一个或多个元素是否存在于 Top-K 列表中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.query/" target="_blank">https://redis.io/docs/latest/commands/topk.query/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		一个或多个要查询的元素
	 *
	 * @return 元素是否存在于 Top-K 列表中
	 */
	List<Boolean> topKQuery(final byte[] key, final byte[]... items);

	/**
	 * 创建一个空的 Top-K 数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.reserve/" target="_blank">https://redis.io/docs/latest/commands/topk.reserve/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param topK
	 * 		数量，即榜单的大小，K值
	 *
	 * @return 创建结果
	 */
	Status topKReserve(final String key, final long topK);

	/**
	 * 创建一个空的 Top-K 数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.reserve/" target="_blank">https://redis.io/docs/latest/commands/topk.reserve/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param topK
	 * 		数量，即榜单的大小，K值
	 *
	 * @return 创建结果
	 */
	Status topKReserve(final byte[] key, final long topK);

	/**
	 * 创建一个空的 Top-K 数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.reserve/" target="_blank">https://redis.io/docs/latest/commands/topk.reserve/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param topK
	 * 		数量，即榜单的大小，K值
	 * @param width
	 * 		底层 Count-Min Sketch 的宽度（列数）
	 * @param depth
	 * 		底层 Count-Min Sketch 的深度（行数/哈希函数数量）
	 * @param decay
	 * 		衰减因子，用于实现“滑动窗口”或“近期热门”效果
	 *
	 * @return 创建结果
	 */
	Status topKReserve(final String key, final long topK, final long width, final long depth, final double decay);

	/**
	 * 创建一个空的 Top-K 数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.reserve/" target="_blank">https://redis.io/docs/latest/commands/topk.reserve/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param topK
	 * 		数量，即榜单的大小，K值
	 * @param width
	 * 		底层 Count-Min Sketch 的宽度（列数）
	 * @param depth
	 * 		底层 Count-Min Sketch 的深度（行数/哈希函数数量）
	 * @param decay
	 * 		衰减因子，用于实现“滑动窗口”或“近期热门”效果
	 *
	 * @return 创建结果
	 */
	Status topKReserve(final byte[] key, final long topK, final long width, final long depth, final double decay);

}
