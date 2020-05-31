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
package com.buession.redis.core.command;

import com.buession.lang.Status;
import com.buession.redis.core.ScanResult;

import java.util.List;
import java.util.Set;

/**
 * 集合命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/set/index.html" target="_blank">http://redisdoc.com/set/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface SetCommands extends RedisCommands {

	/**
	 * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sadd.html" target="_blank">http://redisdoc.com/set/sadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		元素
	 *
	 * @return 被添加到集合中的新元素的数量，不包括被忽略的元素
	 */
	Long sAdd(final String key, final String... members);

	/**
	 * 获取集合 key 的基数(集合中元素的数量)
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/scard.html" target="_blank">http://redisdoc.com/set/scard.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 集合的基数
	 */
	Long sCard(final String key);

	/**
	 * 检测 member 元素是否集合 key 的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sismember.html" target="_blank">http://redisdoc.com/set/sismember
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		元素
	 *
	 * @return 存在时，返回 true；否则，返回 false
	 */
	boolean sisMember(final String key, final String member);

	/**
	 * 获取集合 key 中的所有成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/smembers.html" target="_blank">http://redisdoc.com/set/smembers
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 集合中的所有成员
	 */
	Set<String> sMembers(final String key);

	/**
	 * 移除并返回集合 key 中的一个随机元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/spop.html" target="_blank">http://redisdoc.com/set/spop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 被移除的随机元素
	 */
	String sPop(final String key);

	/**
	 * 返回集合 key 中的一个随机元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 集合 key 中的一个随机元素
	 */
	String sRandMember(final String key);

	/**
	 * 返回集合 key 中的 count 个随机元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 *
	 * @return 集合 key 中的随机元素列表
	 */
	List<String> sRandMember(final String key, final int count);

	/**
	 * 返回集合 key 中的 count 个随机元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srandmember.html" target="_blank">http://redisdoc.com/set/srandmember
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 *
	 * @return 集合 key 中的随机元素列表
	 */
	List<String> sRandMember(final String key, final long count);

	/**
	 * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/srem.html" target="_blank">http://redisdoc.com/set/srem.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素
	 *
	 * @return 被成功移除的元素的数量，不包括被忽略的元素
	 */
	Long sRem(final String key, final String... members);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合之间的差集
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sdiff.html" target="_blank">http://redisdoc.com/set/sdiff.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 一个包含差集成员的列表
	 */
	Set<String> sDiff(final String... keys);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合之间的差集，并保存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sdiffstore.html" target="_blank">http://redisdoc.com/set/sdiffstore
	 * .html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 结果集中的元素数量
	 */
	Long sDiffStore(final String destKey, final String... keys);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的交集
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sinter.html" target="_blank">http://redisdoc.com/set/sinter.html</a>
	 * </p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 交集成员的列表
	 */
	Set<String> sInter(final String... keys);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的交集，并保存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sinterstore.html" target="_blank">http://redisdoc.com/set/sinterstore
	 * .html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 结果集中的元素数量
	 */
	Long sInterStore(final String destKey, final String... keys);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的并集
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sunion.html" target="_blank">http://redisdoc.com/set/sunion.html</a>
	 * </p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 并集成员的列表
	 */
	Set<String> sUnion(final String... keys);

	/**
	 * 获取一个集合的全部成员，该集合是所有给定集合的并集，并保存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/sunionstore.html" target="_blank">http://redisdoc.com/set/sunionstore
	 * .html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 结果集中的元素数量
	 */
	Long sUnionStore(final String destKey, final String... keys);

	/**
	 * 将 member 元素从 source 集合移动到 destKey 集合 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/set/smove.html" target="_blank">http://redisdoc.com/set/smove.html</a></p>
	 *
	 * @param source
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		member 元素
	 *
	 * @return 如果 member 元素被成功移除，则返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status sMove(final String source, final String destKey, final String member);

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
	ScanResult<List<String>> sScan(final String key, final int cursor);

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
	ScanResult<List<String>> sScan(final String key, final long cursor);

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
	ScanResult<List<String>> sScan(final String key, final String cursor);

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
	ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern);

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
	ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern);

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
	ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern);

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
	ScanResult<List<String>> sScan(final String key, final int cursor, final int count);

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
	ScanResult<List<String>> sScan(final String key, final long cursor, final int count);

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
	ScanResult<List<String>> sScan(final String key, final String cursor, final int count);

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
	ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count);

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
	ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count);

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
	ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern, final int count);

}
