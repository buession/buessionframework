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
package com.buession.redis.core.command;

import com.buession.lang.Status;
import com.buession.redis.core.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 哈希表命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/hash/index.html" target="_blank">http://redisdoc.com/hash/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface HashCommands extends RedisCommands {

	/**
	 * 删除哈希表 key 中的一个或多个指定域
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hdel.html" target="_blank">http://redisdoc.com/hash/hdel.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return 被成功删除的域的数量
	 */
	long hDel(final String key, final String... fields);

	/**
	 * 删除哈希表 key 中的一个或多个指定域
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hdel.html" target="_blank">http://redisdoc.com/hash/hdel.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return 被成功删除的域的数量
	 */
	long hDel(final byte[] key, final byte[]... fields);

	/**
	 * 检查给定域 field 是否存在于哈希表 key 当中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hexists.html" target="_blank">http://redisdoc.com/hash/hexists
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 *
	 * @return 域 field 是否存在于哈希表 key 中返回 true，否则返回 false
	 */
	boolean hExists(final String key, final String field);

	/**
	 * 检查给定域 field 是否存在于哈希表 key 当中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hexists.html" target="_blank">http://redisdoc.com/hash/hexists
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 *
	 * @return 域 field 是否存在于哈希表 key 中返回 true，否则返回 false
	 */
	boolean hExists(final byte[] key, final byte[] field);

	/**
	 * 获取哈希表中给定域的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hget.html" target="_blank">http://redisdoc.com/hash/hget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 *
	 * @return 哈希表中给定域的值，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 */
	String hGet(final String key, final String field);

	/**
	 * 获取哈希表中给定域的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hget.html" target="_blank">http://redisdoc.com/hash/hget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 *
	 * @return 哈希表中给定域的值，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 */
	byte[] hGet(final byte[] key, final byte[] field);

	/**
	 * 获取哈希表 key 中，所有的域和值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hgetall.html" target="_blank">http://redisdoc.com/hash/hgetall.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	Map<String, String> hGetAll(final String key);

	/**
	 * 获取哈希表 key 中，所有的域和值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hgetall.html" target="_blank">http://redisdoc.com/hash/hgetall.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	Map<byte[], byte[]> hGetAll(final byte[] key);

	/**
	 * 为哈希表 key 中的域 field 的值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrby.html" target="_blank">http://redisdoc.com/hash/hincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 哈希表 key 中域 field 增量 increment 后的值
	 */
	long hIncrBy(final String key, final String field, final long value);

	/**
	 * 为哈希表 key 中的域 field 的值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrby.html" target="_blank">http://redisdoc.com/hash/hincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 哈希表 key 中域 field 增量 increment 后的值
	 */
	long hIncrBy(final byte[] key, final byte[] field, final long value);

	/**
	 * 为哈希表 key 中的域 field 加上浮点数增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrbyfloat.html" target="_blank">http://redisdoc.com/hash/hincrbyfloat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 哈希表 key 中域 field 增量 increment 后的值
	 */
	double hIncrByFloat(final String key, final String field, final double value);

	/**
	 * 为哈希表 key 中的域 field 加上浮点数增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrbyfloat.html" target="_blank">http://redisdoc.com/hash/hincrbyfloat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 哈希表 key 中域 field 增量 increment 后的值
	 */
	double hIncrByFloat(final byte[] key, final byte[] field, final double value);

	/**
	 * 获取哈希表 key 中的所有域
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hkeys.html" target="_blank">http://redisdoc.com/hash/hkeys.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希表 key 中的所有域
	 */
	Set<String> hKeys(final String key);

	/**
	 * 获取哈希表 key 中的所有域
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hkeys.html" target="_blank">http://redisdoc.com/hash/hkeys.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希表 key 中的所有域
	 */
	Set<byte[]> hKeys(final byte[] key);

	/**
	 * 获取哈希表 key 中域的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hlen.html" target="_blank">http://redisdoc.com/hash/hlen.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希表 key 中域的数量
	 */
	long hLen(final String key);

	/**
	 * 获取哈希表 key 中域的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hlen.html" target="_blank">http://redisdoc.com/hash/hlen.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希表 key 中域的数量
	 */
	long hLen(final byte[] key);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmget.html" target="_blank">http://redisdoc.com/hash/hmget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 */
	List<String> hMGet(final String key, final String... fields);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmget.html" target="_blank">http://redisdoc.com/hash/hmget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 */
	List<byte[]> hMGet(final byte[] key, final byte[]... fields);

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmset.html" target="_blank">http://redisdoc.com/hash/hmset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	Status hMSet(final String key, final Map<String, String> data);

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmset.html" target="_blank">http://redisdoc.com/hash/hmset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	Status hMSet(final byte[] key, final Map<byte[], byte[]> data);

	/**
	 * 随机返回一个哈希表中存在的域
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 随机获取的哈希表中存在的域
	 */
	String hRandField(final String key);

	/**
	 * 随机返回一个哈希表中存在的域
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 随机获取的哈希表中存在的域
	 */
	byte[] hRandField(final byte[] key);

	/**
	 * 随机返回一个哈希表中指定数量的存在的域
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		数量
	 *
	 * @return 随机获取指定数量的哈希表中存在的域
	 */
	List<String> hRandField(final String key, final long count);

	/**
	 * 随机返回一个哈希表中指定数量的存在的域
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		数量
	 *
	 * @return 随机获取指定数量的哈希表中存在的域
	 */
	List<byte[]> hRandField(final byte[] key, final long count);

	/**
	 * When called with just the key argument, return a random field from the hash value stored at key.
	 * If the provided count argument is positive, return an array of distinct fields.
	 * The array’s length is either count or the hash’s number of fields (HLEN), whichever is lower.
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回数量
	 *
	 * @return A list fields and their values from the hash
	 */
	Map<String, String> hRandFieldWithValues(final String key, final long count);

	/**
	 * When called with just the key argument, return a random field from the hash value stored at key.
	 * If the provided count argument is positive, return an array of distinct fields.
	 * The array’s length is either count or the hash’s number of fields (HLEN), whichever is lower.
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回数量
	 *
	 * @return A list fields and their values from the hash
	 */
	Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<Map<String, String>> hScan(final String key, final long cursor);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<Map<String, String>> hScan(final String key, final String cursor);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<String, String>> hScan(final String key, final long cursor, final long count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final long count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern, final long count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern, final long count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
										  final long count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final long count);

	/**
	 * 将哈希表 key 中域 field 的值设置为 value。
	 * 如果给定的哈希表并不存在，那么一个新的哈希表；
	 * 如果域 field 已经存在于哈希表中，那么 value 将覆盖旧值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hset.html" target="_blank">http://redisdoc.com/hash/hset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 被修改或增加的 field 个数
	 */
	long hSet(final String key, final String field, final String value);

	/**
	 * 将哈希表 key 中域 field 的值设置为 value。
	 * 如果给定的哈希表并不存在，那么一个新的哈希表；
	 * 如果域 field 已经存在于哈希表中，那么 value 将覆盖旧值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hset.html" target="_blank">http://redisdoc.com/hash/hset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 被修改或增加的 field 个数
	 */
	long hSet(final byte[] key, final byte[] field, final byte[] value);

	/**
	 * 当且仅当域 field 尚未存在于哈希表 key 中的情况下，将它的值设置为 value
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hsetnx.html" target="_blank">http://redisdoc.com/hash/hsetnx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 操作结果；设置成功时返回 Status.Success，在给定域已经存在而放弃执行设置操作时返回 Status.FAILURE
	 */
	Status hSetNx(final String key, final String field, final String value);

	/**
	 * 当且仅当域 field 尚未存在于哈希表 key 中的情况下，将它的值设置为 value
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hsetnx.html" target="_blank">http://redisdoc.com/hash/hsetnx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 操作结果；设置成功时返回 Status.Success，在给定域已经存在而放弃执行设置操作时返回 Status.FAILURE
	 */
	Status hSetNx(final byte[] key, final byte[] field, final byte[] value);

	/**
	 * 获取哈希表 key 中，与给定域 field 相关联的值的字符串长度
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hstrlen.html" target="_blank">http://redisdoc.com/hash/hstrlen.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 *
	 * @return 哈希表 key 中，与给定域 field 相关联的值的字符串长度
	 */
	long hStrLen(final String key, final String field);

	/**
	 * 获取哈希表 key 中，与给定域 field 相关联的值的字符串长度
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hstrlen.html" target="_blank">http://redisdoc.com/hash/hstrlen.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 *
	 * @return 哈希表 key 中，与给定域 field 相关联的值的字符串长度
	 */
	long hStrLen(final byte[] key, final byte[] field);

	/**
	 * 获取哈希表 key 中所有域的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hvals.html" target="_blank">http://redisdoc.com/hash/hvals.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希表 key 中所有域的值
	 */
	List<String> hVals(final String key);

	/**
	 * 获取哈希表 key 中所有域的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hvals.html" target="_blank">http://redisdoc.com/hash/hvals.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希表 key 中所有域的值
	 */
	List<byte[]> hVals(final byte[] key);

}
