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
import java.util.Map;
import java.util.Set;

/**
 * 哈希表命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/hash/index.html" target="_blank">http://redisdoc.com/hash/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface BinaryHashCommands extends BinaryRedisCommands {

	/**
	 * 检查给定域 field 是否存在于哈希表 key 当中
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/hash/hexists.html" target="_blank">http://redisdoc.com/hash/hexists.html</a>
	 * </p>
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
	 * 获取哈希表 key 中的所有域
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hkeys.html" target="_blank">http://redisdoc.com/hash/hkeys.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希表 key 中的所有域
	 */
	Set<byte[]> hKeys(final byte[] key);

	/**
	 * 获取哈希表 key 中所有域的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hvals.html" target="_blank">http://redisdoc.com/hash/hvals.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希表 key 中所有域的值
	 */
	List<byte[]> hVals(final byte[] key);

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
	 * @return 操作结果
	 */
	Status hSet(final byte[] key, final byte[] field, final byte[] value);

	/**
	 * 当且仅当域 field 尚未存在于哈希表 key 中的情况下，将它的值设置为 value
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hsetnx.html" target="_blank">http://redisdoc.com/hash/hsetnx
	 * .html</a></p>
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
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmset.html" target="_blank">http://redisdoc.com/hash/hmset.html</a>
	 * </p>
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
	 * 获取哈希表 key 中，一个或多个给定域的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmget.html" target="_blank">http://redisdoc.com/hash/hmget.html</a>
	 * </p>
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
	 * 获取哈希表 key 中，所有的域和值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hgetall.html" target="_blank">http://redisdoc.com/hash/hgetall
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	Map<byte[], byte[]> hGetAll(final byte[] key);

	/**
	 * 获取哈希表 key 中，与给定域 field 相关联的值的字符串长度
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hstrlen.html" target="_blank">http://redisdoc.com/hash/hstrlen
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 *
	 * @return 哈希表 key 中，与给定域 field 相关联的值的字符串长度
	 */
	Long hStrLen(final byte[] key, final byte[] field);

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
	Long hLen(final byte[] key);

	/**
	 * 为哈希表 key 中的域 field 的值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrby.html" target="_blank">http://redisdoc.com/hash/hincrby
	 * .html</a></p>
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
	Long hIncrBy(final byte[] key, final byte[] field, final int value);

	/**
	 * 为哈希表 key 中的域 field 的值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrby.html" target="_blank">http://redisdoc.com/hash/hincrby
	 * .html</a></p>
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
	Long hIncrBy(final byte[] key, final byte[] field, final long value);

	/**
	 * 为哈希表 key 中的域 field 加上浮点数增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrbyfloat.html" target="_blank">http://redisdoc
	 * .com/hash/hincrbyfloat.html</a></p>
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
	Double hIncrByFloat(final byte[] key, final byte[] field, final float value);

	/**
	 * 为哈希表 key 中的域 field 加上浮点数增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrbyfloat.html" target="_blank">http://redisdoc
	 * .com/hash/hincrbyfloat.html</a></p>
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
	Double hIncrByFloat(final byte[] key, final byte[] field, final double value);

	/**
	 * 为哈希表 key 中的域 field 的值加上减量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrby.html" target="_blank">http://redisdoc.com/hash/hincrby
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 哈希表 key 中域 field 减量 increment 后的值
	 */
	Long hDecrBy(final byte[] key, final byte[] field, final int value);

	/**
	 * 为哈希表 key 中的域 field 的值加上减量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrby.html" target="_blank">http://redisdoc.com/hash/hincrby
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 哈希表 key 中域 field 减量 increment 后的值
	 */
	Long hDecrBy(final byte[] key, final byte[] field, final long value);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final int count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final int count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern, final int count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern, final int count);

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
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
	ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final int count);

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
	Long hDel(final byte[] key, final byte[]... fields);

}