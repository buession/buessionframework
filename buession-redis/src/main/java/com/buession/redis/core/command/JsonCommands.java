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

import com.buession.lang.Status;
import com.buession.redis.core.JsonType;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.command.args.JsonGetArgument;
import com.buession.redis.core.command.args.JsonKeyPathValueArgument;

import java.util.List;

/**
 *
 * JSON 命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=json" target="_blank">https://redis.io/docs/latest/commands/?group=json</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface JsonCommands extends RedisCommands {

	/**
	 * 向 JSON 文档的数组字段末尾追加一个或多个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrappend/" target="_blank">https://redis.io/docs/latest/commands/json.arrappend/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		一个或多个要追加的 JSON 值
	 *
	 * @return 在每个匹配路径上追加后的数组新长度
	 */
	List<Long> jsonArrAppend(final String key, final String... values);

	/**
	 * 向 JSON 文档的数组字段末尾追加一个或多个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrappend/" target="_blank">https://redis.io/docs/latest/commands/json.arrappend/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		一个或多个要追加的 JSON 值
	 *
	 * @return 在每个匹配路径上追加后的数组新长度
	 */
	List<Long> jsonArrAppend(final byte[] key, final byte[]... values);

	/**
	 * 向 JSON 文档的数组字段末尾追加一个或多个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrappend/" target="_blank">https://redis.io/docs/latest/commands/json.arrappend/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param values
	 * 		一个或多个要追加的 JSON 值
	 *
	 * @return 在每个匹配路径上追加后的数组新长度
	 */
	List<Long> jsonArrAppend(final String key, final String path, final String... values);

	/**
	 * 向 JSON 文档的数组字段末尾追加一个或多个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrappend/" target="_blank">https://redis.io/docs/latest/commands/json.arrappend/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param values
	 * 		一个或多个要追加的 JSON 值
	 *
	 * @return 在每个匹配路径上追加后的数组新长度
	 */
	List<Long> jsonArrAppend(final byte[] key, final byte[] path, final byte[]... values);

	/**
	 * 在 JSON 文档的数组中查找某个值首次出现的索引位置
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrindex/" target="_blank">https://redis.io/docs/latest/commands/json.arrindex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 *
	 * @return 值在 JSON 文档中首次出现的索引位置
	 */
	List<Long> jsonArrIndex(final String key, final String path, final String value);

	/**
	 * 在 JSON 文档的数组中查找某个值首次出现的索引位置
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrindex/" target="_blank">https://redis.io/docs/latest/commands/json.arrindex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 *
	 * @return 值在 JSON 文档中首次出现的索引位置
	 */
	List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value);

	/**
	 * 在 JSON 文档的数组中查找某个值首次出现的索引位置
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrindex/" target="_blank">https://redis.io/docs/latest/commands/json.arrindex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 * @param start
	 * 		起始位置
	 *
	 * @return 值在 JSON 文档中首次出现的索引位置
	 */
	List<Long> jsonArrIndex(final String key, final String path, final String value, final int start);

	/**
	 * 在 JSON 文档的数组中查找某个值首次出现的索引位置
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrindex/" target="_blank">https://redis.io/docs/latest/commands/json.arrindex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 * @param start
	 * 		起始位置
	 *
	 * @return 值在 JSON 文档中首次出现的索引位置
	 */
	List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start);

	/**
	 * 在 JSON 文档的数组中查找某个值首次出现的索引位置
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrindex/" target="_blank">https://redis.io/docs/latest/commands/json.arrindex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 * @param start
	 * 		起始位置
	 * @param stop
	 * 		结束位置
	 *
	 * @return 值在 JSON 文档中首次出现的索引位置
	 */
	List<Long> jsonArrIndex(final String key, final String path, final String value, final int start, final int stop);

	/**
	 * 在 JSON 文档的数组中查找某个值首次出现的索引位置
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrindex/" target="_blank">https://redis.io/docs/latest/commands/json.arrindex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 * @param start
	 * 		起始位置
	 * @param stop
	 * 		结束位置
	 *
	 * @return 值在 JSON 文档中首次出现的索引位置
	 */
	List<Long> jsonArrIndex(final byte[] key, final byte[] path, final byte[] value, final int start, final int stop);

	/**
	 * 在 JSON 文档的数组指定索引位置插入一个或多个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrinsert/" target="_blank">https://redis.io/docs/latest/commands/json.arrinsert/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param index
	 * 		起始位置
	 * @param values
	 * 		一个或多个要追加的 JSON 值
	 *
	 * @return 在每个匹配路径上追加后的数组新长度
	 */
	List<Long> jsonArrInsert(final String key, final String path, final int index, final String... values);

	/**
	 * 在 JSON 文档的数组指定索引位置插入一个或多个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrinsert/" target="_blank">https://redis.io/docs/latest/commands/json.arrinsert/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param index
	 * 		起始位置
	 * @param values
	 * 		一个或多个要追加的 JSON 值
	 *
	 * @return 在每个匹配路径上追加后的数组新长度
	 */
	List<Long> jsonArrInsert(final byte[] key, final byte[] path, final int index, final byte[]... values);

	/**
	 * 获取 JSON 文档中数组的长度
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrlen/" target="_blank">https://redis.io/docs/latest/commands/json.arrlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return JSON 文档中数组的长度
	 */
	Long jsonArrLen(final String key);

	/**
	 * 获取 JSON 文档中数组的长度
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrlen/" target="_blank">https://redis.io/docs/latest/commands/json.arrlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return JSON 文档中数组的长度
	 */
	Long jsonArrLen(final byte[] key);

	/**
	 * 获取 JSON 文档中数组的长度
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrlen/" target="_blank">https://redis.io/docs/latest/commands/json.arrlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return JSON 文档中数组的长度
	 */
	List<Long> jsonArrLen(final String key, final String path);

	/**
	 * 获取 JSON 文档中数组的长度
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrlen/" target="_blank">https://redis.io/docs/latest/commands/json.arrlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return JSON 文档中数组的长度
	 */
	List<Long> jsonArrLen(final byte[] key, final byte[] path);

	/**
	 * 从 JSON 文档的数组末尾（或指定位置）弹出并返回一个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrpop/" target="_blank">https://redis.io/docs/latest/commands/json.arrpop/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 返回一个数组，每个元素对应 path 匹配到的每个数组中被弹出的值；如果数组为空或索引越界，返回 null 对应项
	 */
	Object jsonArrPop(final String key);

	/**
	 * 从 JSON 文档的数组末尾（或指定位置）弹出并返回一个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrpop/" target="_blank">https://redis.io/docs/latest/commands/json.arrpop/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 返回一个数组，每个元素对应 path 匹配到的每个数组中被弹出的值；如果数组为空或索引越界，返回 null 对应项
	 */
	Object jsonArrPop(final byte[] key);

	/**
	 * 从 JSON 文档的数组末尾（或指定位置）弹出并返回一个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrpop/" target="_blank">https://redis.io/docs/latest/commands/json.arrpop/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 返回一个数组，每个元素对应 path 匹配到的每个数组中被弹出的值；如果数组为空或索引越界，返回 null 对应项；如果 path 不指向数组，返回 null
	 */
	List<Object> jsonArrPop(final String key, final String path);

	/**
	 * 从 JSON 文档的数组末尾（或指定位置）弹出并返回一个元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrpop/" target="_blank">https://redis.io/docs/latest/commands/json.arrpop/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 返回一个数组，每个元素对应 path 匹配到的每个数组中被弹出的值；如果数组为空或索引越界，返回 null 对应项；如果 path 不指向数组，返回 null
	 */
	List<Object> jsonArrPop(final byte[] key, final byte[] path);

	/**
	 * 裁剪 JSON 数组，仅保留指定索引范围内的元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrtrim/" target="_blank">https://redis.io/docs/latest/commands/json.arrtrim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param start
	 * 		起始索引
	 * @param stop
	 * 		结束索引
	 *
	 * @return 返回一个数组，表示每个匹配路径上裁剪后的数组新长度
	 */
	List<Long> jsonArrTrim(final String key, final String path, final int start, final int stop);

	/**
	 * 裁剪 JSON 数组，仅保留指定索引范围内的元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.arrtrim/" target="_blank">https://redis.io/docs/latest/commands/json.arrtrim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param start
	 * 		起始索引
	 * @param stop
	 * 		结束索引
	 *
	 * @return 返回一个数组，表示每个匹配路径上裁剪后的数组新长度
	 */
	List<Long> jsonArrTrim(final byte[] key, final byte[] path, final int start, final int stop);

	/**
	 * 将 JSON 文档中指定路径的数值（number）或布尔（boolean）字段重置为零值，或将数组/对象清空
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.clear/" target="_blank">https://redis.io/docs/latest/commands/json.clear/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 被成功清除的字段数量；路径不匹配任何可清除类型，返回 0；如果 key 不存在，返回 0
	 */
	Long jsonArrClear(final String key);

	/**
	 * 将 JSON 文档中指定路径的数值（number）或布尔（boolean）字段重置为零值，或将数组/对象清空
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.clear/" target="_blank">https://redis.io/docs/latest/commands/json.clear/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 被成功清除的字段数量；路径不匹配任何可清除类型，返回 0；如果 key 不存在，返回 0
	 */
	Long jsonArrClear(final byte[] key);

	/**
	 * 将 JSON 文档中指定路径的数值（number）或布尔（boolean）字段重置为零值，或将数组/对象清空
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.clear/" target="_blank">https://redis.io/docs/latest/commands/json.clear/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 被成功清除的字段数量；路径不匹配任何可清除类型，返回 0；如果 key 不存在，返回 0
	 */
	List<Long> jsonArrClear(final String key, final String path);

	/**
	 * 将 JSON 文档中指定路径的数值（number）或布尔（boolean）字段重置为零值，或将数组/对象清空
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.clear/" target="_blank">https://redis.io/docs/latest/commands/json.clear/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 被成功清除的字段数量；路径不匹配任何可清除类型，返回 0；如果 key 不存在，返回 0
	 */
	List<Long> jsonArrClear(final byte[] key, final byte[] path);

	/**
	 * 获取 JSON 数据在 Redis 内部的内存使用情况或内部表示信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.debug/" target="_blank">https://redis.io/docs/latest/commands/json.debug/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 获取 JSON 数据在 Redis 内部的内存使用情况或内部表示信息
	 */
	Long jsonDebugMemory(final String key);

	/**
	 * 获取 JSON 数据在 Redis 内部的内存使用情况或内部表示信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.debug/" target="_blank">https://redis.io/docs/latest/commands/json.debug/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 获取 JSON 数据在 Redis 内部的内存使用情况或内部表示信息
	 */
	Long jsonDebugMemory(final byte[] key);

	/**
	 * 获取 JSON 数据在 Redis 内部的内存使用情况或内部表示信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.debug/" target="_blank">https://redis.io/docs/latest/commands/json.debug/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 获取 JSON 数据在 Redis 内部的内存使用情况或内部表示信息
	 */
	List<Long> jsonDebugMemory(final String key, final String path);

	/**
	 * 获取 JSON 数据在 Redis 内部的内存使用情况或内部表示信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.debug/" target="_blank">https://redis.io/docs/latest/commands/json.debug/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 获取 JSON 数据在 Redis 内部的内存使用情况或内部表示信息
	 */
	List<Long> jsonDebugMemory(final byte[] key, final byte[] path);

	/**
	 * 从 JSON 文档中删除指定路径的元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.del/" target="_blank">https://redis.io/docs/latest/commands/json.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 如果删除整个文档，则返回 1
	 */
	Long jsonDel(final String key);

	/**
	 * 从 JSON 文档中删除指定路径的元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.del/" target="_blank">https://redis.io/docs/latest/commands/json.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 如果删除整个文档，则返回 1
	 */
	Long jsonDel(final byte[] key);

	/**
	 * 从 JSON 文档中删除指定路径的字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.del/" target="_blank">https://redis.io/docs/latest/commands/json.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 被成功删除的路径数量；如果路径匹配多个节点（如通配符），返回匹配并删除的数量；如果 path 不匹配任何节点，返回 0；如果 key 不存在，返回 0
	 */
	Long jsonDel(final String key, final String path);

	/**
	 * 从 JSON 文档中删除指定路径的字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.del/" target="_blank">https://redis.io/docs/latest/commands/json.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 被成功删除的路径数量；如果路径匹配多个节点（如通配符），返回匹配并删除的数量；如果 path 不匹配任何节点，返回 0；如果 key 不存在，返回 0
	 */
	Long jsonDel(final byte[] key, final byte[] path);

	/**
	 * 从 JSON 文档中删除指定路径的元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.forget/" target="_blank">https://redis.io/docs/latest/commands/json.forget/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 如果删除整个文档，则返回 1
	 */
	Long jsonForget(final String key);

	/**
	 * 从 JSON 文档中删除指定路径的元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.forget/" target="_blank">https://redis.io/docs/latest/commands/json.forget/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 如果删除整个文档，则返回 1
	 */
	Long jsonForget(final byte[] key);

	/**
	 * 从 JSON 文档中删除指定路径的字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.forget/" target="_blank">https://redis.io/docs/latest/commands/json.forget/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 被成功删除的路径数量；如果路径匹配多个节点（如通配符），返回匹配并删除的数量；如果 path 不匹配任何节点，返回 0；如果 key 不存在，返回 0
	 */
	Long jsonForget(final String key, final String path);

	/**
	 * 从 JSON 文档中删除指定路径的字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.forget/" target="_blank">https://redis.io/docs/latest/commands/json.forget/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 被成功删除的路径数量；如果路径匹配多个节点（如通配符），返回匹配并删除的数量；如果 path 不匹配任何节点，返回 0；如果 key 不存在，返回 0
	 */
	Long jsonForget(final byte[] key, final byte[] path);

	/**
	 * 从 JSON 文档获取指定路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 文档指定路径的值
	 */
	String jsonGet(final String key);

	/**
	 * 从 JSON 文档获取指定路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 文档指定路径的值
	 */
	byte[] jsonGet(final byte[] key);

	/**
	 * 从 JSON 文档获取指定路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 *
	 * @return 文档指定路径的值
	 */
	String jsonGet(final String key, final JsonGetArgument argument);

	/**
	 * 从 JSON 文档获取指定路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 *
	 * @return 文档指定路径的值
	 */
	byte[] jsonGet(final byte[] key, final JsonGetArgument argument);

	/**
	 * 从 JSON 文档获取指定路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 文档指定路径的值
	 */
	List<String> jsonGet(final String key, final String... path);

	/**
	 * 从 JSON 文档获取指定路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 文档指定路径的值
	 */
	List<byte[]> jsonGet(final byte[] key, final byte[]... path);

	/**
	 * 从 JSON 文档获取指定路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 文档指定路径的值
	 */
	List<String> jsonGet(final String key, final JsonGetArgument argument, final String... path);

	/**
	 * 从 JSON 文档获取指定路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.get/" target="_blank">https://redis.io/docs/latest/commands/json.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 文档指定路径的值
	 */
	List<byte[]> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[]... path);

	/**
	 * 对 JSON 文档执行 RFC 7396 定义的 JSON Merge Patch 操作
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.merge/" target="_blank">https://redis.io/docs/latest/commands/json.merge/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 操作结果
	 */
	Status jsonMerge(final String key, final String path, final String value);

	/**
	 * 对 JSON 文档执行 RFC 7396 定义的 JSON Merge Patch 操作
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.merge/" target="_blank">https://redis.io/docs/latest/commands/json.merge/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 操作结果
	 */
	Status jsonMerge(final byte[] key, final byte[] path, final byte[] value);

	/**
	 * 从多个 Redis key 中获取相同 JSON 路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.mget/" target="_blank">https://redis.io/docs/latest/commands/json.mget/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 文档指定路径的值
	 */
	List<String> jsonMGet(final String[] keys, final String path);

	/**
	 * 从多个 Redis key 中获取相同 JSON 路径的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.mget/" target="_blank">https://redis.io/docs/latest/commands/json.mget/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 文档指定路径的值
	 */
	List<byte[]> jsonMGet(final byte[][] keys, final byte[] path);

	/**
	 * Set or update one or more JSON values according to the specified key-path-value triplets
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.mset/" target="_blank">https://redis.io/docs/latest/commands/json.mset/</a></p>
	 *
	 * @param data
	 * 		JSON 数据
	 *
	 * @return 操作结果
	 */
	Status jsonMSet(final JsonKeyPathValueArgument.StringJsonKeyPathValueArgument... data);

	/**
	 * Set or update one or more JSON values according to the specified key-path-value triplets
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.mset/" target="_blank">https://redis.io/docs/latest/commands/json.mset/</a></p>
	 *
	 * @param data
	 * 		JSON 数据
	 *
	 * @return 操作结果
	 */
	Status jsonMSet(final JsonKeyPathValueArgument.BinaryJsonKeyPathValueArgument... data);

	/**
	 * 对 JSON 文档中的数值字段执行原子递增
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.numincrby/" target="_blank">https://redis.io/docs/latest/commands/json.numincrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		递增的值
	 *
	 * @return 递增后的新值
	 */
	List<Number> jsonNumIncrBy(final String key, final String path, final Number value);

	/**
	 * 对 JSON 文档中的数值字段执行原子递增
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.numincrby/" target="_blank">https://redis.io/docs/latest/commands/json.numincrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		递增的值
	 *
	 * @return 递增后的新值
	 */
	List<Number> jsonNumIncrBy(final byte[] key, final byte[] path, final Number value);

	/**
	 * 对 JSON 文档中的数值字段执行原子乘法操作
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.nummultby/" target="_blank">https://redis.io/docs/latest/commands/json.nummultby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		递增的值
	 *
	 * @return 相乘后的新值
	 */
	List<Number> jsonNumMultBy(final String key, final String path, final Number value);

	/**
	 * 对 JSON 文档中的数值字段执行原子乘法操作
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.nummultby/" target="_blank">https://redis.io/docs/latest/commands/json.nummultby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		递增的值
	 *
	 * @return 相乘后的新值
	 */
	List<Number> jsonNumMultBy(final byte[] key, final byte[] path, final Number value);

	/**
	 * 获取 JSON 对象在指定路径下的所有字段名
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.objkeys/" target="_blank">https://redis.io/docs/latest/commands/json.objkeys/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return JSON 对象在指定路径下的所有字段名
	 */
	List<List<String>> jsonObjKeys(final String key);

	/**
	 * 获取 JSON 对象在指定路径下的所有字段名
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.objkeys/" target="_blank">https://redis.io/docs/latest/commands/json.objkeys/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return JSON 对象在指定路径下的所有字段名
	 */
	List<List<byte[]>> jsonObjKeys(final byte[] key);

	/**
	 * 获取 JSON 对象在指定路径下的所有字段名
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.objkeys/" target="_blank">https://redis.io/docs/latest/commands/json.objkeys/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return JSON 对象在指定路径下的所有字段名
	 */
	List<List<String>> jsonObjKeys(final String key, final String path);

	/**
	 * 获取 JSON 对象在指定路径下的所有字段名
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.objkeys/" target="_blank">https://redis.io/docs/latest/commands/json.objkeys/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return JSON 对象在指定路径下的所有字段名
	 */
	List<List<byte[]>> jsonObjKeys(final byte[] key, final byte[] path);

	/**
	 * 获取 JSON 对象在指定路径下的字段数量
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.objkeys/" target="_blank">https://redis.io/docs/latest/commands/json.objkeys/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return JSON 对象在指定路径下的字段数量
	 */
	Long jsonObjLen(final String key);

	/**
	 * 获取 JSON 对象在指定路径下的字段数量
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.objlen/" target="_blank">https://redis.io/docs/latest/commands/json.objlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return JSON 对象在指定路径下的字段数量
	 */
	Long jsonObjLen(final byte[] key);

	/**
	 * 获取 JSON 对象在指定路径下的字段数量
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.objlen/" target="_blank">https://redis.io/docs/latest/commands/json.objlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return JSON 对象在指定路径下的字段数量
	 */
	List<Long> jsonObjLen(final String key, final String path);

	/**
	 * 获取 JSON 对象在指定路径下的字段数量
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.objlen/" target="_blank">https://redis.io/docs/latest/commands/json.objlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return JSON 对象在指定路径下的字段数量
	 */
	List<Long> jsonObjLen(final byte[] key, final byte[] path);

	/**
	 * 以 Redis RESP（REdis Serialization Protocol）格式返回 JSON 数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.resp/" target="_blank">https://redis.io/docs/latest/commands/json.resp/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return RESP（REdis Serialization Protocol）格式 JSON 数据
	 */
	List<String> jsonResp(final String key);

	/**
	 * 获取 JSON 对象在指定路径下的字段数量
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.resp/" target="_blank">https://redis.io/docs/latest/commands/json.resp/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return RESP（REdis Serialization Protocol）格式 JSON 数据
	 */
	List<byte[]> jsonResp(final byte[] key);

	/**
	 * 获取 JSON 对象在指定路径下的字段数量
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.resp/" target="_blank">https://redis.io/docs/latest/commands/json.resp/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return RESP（REdis Serialization Protocol）格式 JSON 数据
	 */
	List<String> jsonResp(final String key, final String path);

	/**
	 * 获取 JSON 对象在指定路径下的字段数量
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.resp/" target="_blank">https://redis.io/docs/latest/commands/json.resp/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return RESP（REdis Serialization Protocol）格式 JSON 数据
	 */
	List<byte[]> jsonResp(final byte[] key, final byte[] path);

	/**
	 * 创建或更新 JSON 文档
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.set/" target="_blank">https://redis.io/docs/latest/commands/json.set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 *
	 * @return 操作结果
	 */
	Status jsonSet(final String key, final String path, final String value);

	/**
	 * 创建或更新 JSON 文档
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.set/" target="_blank">https://redis.io/docs/latest/commands/json.set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 *
	 * @return 操作结果
	 */
	Status jsonSet(final byte[] key, final byte[] path, final byte[] value);

	/**
	 * 创建或更新 JSON 文档
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.set/" target="_blank">https://redis.io/docs/latest/commands/json.set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 * @param nxXx
	 * 		NX 仅当 key 不存在 时设置;XX 仅当 key 已存在 时设置
	 *
	 * @return 操作结果
	 */
	Status jsonSet(final String key, final String path, final String value, final NxXx nxXx);

	/**
	 * 创建或更新 JSON 文档
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.set/" target="_blank">https://redis.io/docs/latest/commands/json.set/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 * @param nxXx
	 * 		NX 仅当 key 不存在 时设置;XX 仅当 key 已存在 时设置
	 *
	 * @return 操作结果
	 */
	Status jsonSet(final byte[] key, final byte[] path, final byte[] value, final NxXx nxXx);

	/**
	 * 向 JSON 文档中的字符串字段追加内容
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.strappend/" target="_blank">https://redis.io/docs/latest/commands/json.strappend/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 追加后字符串的新长度
	 */
	List<Long> jsonStrAppend(final String key, final String value);

	/**
	 * 向 JSON 文档中的字符串字段追加内容
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.strappend/" target="_blank">https://redis.io/docs/latest/commands/json.strappend/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 追加后字符串的新长度
	 */
	List<Long> jsonStrAppend(final byte[] key, final byte[] value);

	/**
	 * 向 JSON 文档中的字符串字段追加内容
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.strappend/" target="_blank">https://redis.io/docs/latest/commands/json.strappend/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 *
	 * @return 追加后字符串的新长度
	 */
	List<Long> jsonStrAppend(final String key, final String path, final String value);

	/**
	 * 向 JSON 文档中的字符串字段追加内容
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.strappend/" target="_blank">https://redis.io/docs/latest/commands/json.strappend/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 *
	 * @return 追加后字符串的新长度
	 */
	List<Long> jsonStrAppend(final byte[] key, final byte[] path, final byte[] value);

	/**
	 * 获取 JSON 文档中字符串字段的长度
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.strlen/" target="_blank">https://redis.io/docs/latest/commands/json.strlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return JSON 文档中字符串字段的长度
	 */
	Long jsonStrLen(final String key);

	/**
	 * 获取 JSON 文档中字符串字段的长度
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.strlen/" target="_blank">https://redis.io/docs/latest/commands/json.strlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return JSON 文档中字符串字段的长度
	 */
	Long jsonStrLen(final byte[] key);

	/**
	 * 获取 JSON 文档中字符串字段的长度
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.strlen/" target="_blank">https://redis.io/docs/latest/commands/json.strlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return JSON 文档中字符串字段的长度
	 */
	List<Long> jsonStrLen(final String key, final String path);

	/**
	 * 获取 JSON 文档中字符串字段的长度
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.strlen/" target="_blank">https://redis.io/docs/latest/commands/json.strlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return JSON 文档中字符串字段的长度
	 */
	List<Long> jsonStrLen(final byte[] key, final byte[] path);

	/**
	 * 原子地切换 JSON 文档中的布尔值字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.toggle/" target="_blank">https://redis.io/docs/latest/commands/json.toggle/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 操作结果
	 */
	List<Status> jsonToggle(final String key, final String path);

	/**
	 * 原子地切换 JSON 文档中的布尔值字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.toggle/" target="_blank">https://redis.io/docs/latest/commands/json.toggle/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return 操作结果
	 */
	List<Status> jsonToggle(final byte[] key, final byte[] path);

	/**
	 * 获取 JSON 文档中指定路径的值的数据类型
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.type/" target="_blank">https://redis.io/docs/latest/commands/json.type/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return JSON 文档中指定路径的值的数据类型
	 */
	JsonType jsonType(final String key);

	/**
	 * 获取 JSON 文档中指定路径的值的数据类型
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.type/" target="_blank">https://redis.io/docs/latest/commands/json.type/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return JSON 文档中指定路径的值的数据类型
	 */
	JsonType jsonType(final byte[] key);

	/**
	 * 获取 JSON 文档中指定路径的值的数据类型
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.type/" target="_blank">https://redis.io/docs/latest/commands/json.type/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return JSON 文档中指定路径的值的数据类型
	 */
	List<JsonType> jsonType(final String key, final String path);

	/**
	 * 获取 JSON 文档中指定路径的值的数据类型
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/json.type/" target="_blank">https://redis.io/docs/latest/commands/json.type/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return JSON 文档中指定路径的值的数据类型
	 */
	List<JsonType> jsonType(final byte[] key, final byte[] path);

}
