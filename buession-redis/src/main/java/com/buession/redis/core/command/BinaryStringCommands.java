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

import java.util.List;

/**
 * STRING 命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/string/index.html" target="_blank">http://redisdoc.com/string/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface BinaryStringCommands extends BinaryRedisCommands {

	/**
	 * 将字符串值 value 关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/set.html" target="_blank">http://redisdoc.com/string/set.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status set(final byte[] key, final byte[] value);

	/**
	 * 将字符串值 value 关联到 key；
	 * 如果 key 已经持有其他值，SET 就覆写旧值，忽略类型
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/set.html" target="_blank">http://redisdoc.com/string/set.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param setArgument
	 * 		参数
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status set(final byte[] key, final byte[] value, final StringCommands.SetArgument setArgument);

	/**
	 * 将键 key 的值设置为 value ，并将键 key 的生存时间设置为 lifetime；
	 * 如果键 key 已经存在，那么将覆盖已有的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/setex.html" target="_blank">http://redisdoc.com/string/setex
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param lifetime
	 * 		生存时间（单秒：秒）
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status setEx(final byte[] key, final byte[] value, final int lifetime);

	/**
	 * 将键 key 的值设置为 value ，并将键 key 的生存时间设置为 lifetime；
	 * 如果键 key 已经存在，那么将覆盖已有的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/psetex.html" target="_blank">http://redisdoc.com/string/psetex
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param lifetime
	 * 		生存时间（单秒：毫秒）
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status pSetEx(final byte[] key, final byte[] value, final int lifetime);

	/**
	 * 当键 key 不存在的情况下，将键 key 的值设置为 value
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/setnx.html" target="_blank">http://redisdoc.com/string/setnx
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 如果设置操作成功，返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status setNx(final byte[] key, final byte[] value);

	/**
	 * 如果键 key 已经存在并且它的值是一个字符串，将 value 追加到键 key 现有值的末尾
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/append.html" target="_blank">http://redisdoc.com/string/append
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 追加 value 之后 键 key 的值的长度
	 */
	Long append(final byte[] key, final byte[] value);

	/**
	 * 获取键 key 相关联的字符串值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/get.html" target="_blank">http://redisdoc.com/string/get.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 如果键 key 不存在，那么返回特殊值 null ；否则，返回键 key 的值；
	 * 如果键 key 的值并非字符串类型，那么抛出异常
	 */
	byte[] get(final byte[] key);

	/**
	 * 将键 key 的值设为 value ，并返回键 key 在被设置之前的旧值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/getset.html" target="_blank">http://redisdoc.com/string/getset
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		新值
	 *
	 * @return 键 key 的旧值
	 */
	byte[] getSet(final byte[] key, final byte[] value);

	/**
	 * 获取给定的一个或多个字符串键的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/mget.html" target="_blank">http://redisdoc.com/string/mget
	 * .html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 *
	 * @return 返回一个列表，列表中包含了所有给定键的值；如果给定键不存在 那么这个键的值将以特殊值 null 表示
	 */
	List<byte[]> mGet(final byte[]... keys);

	/**
	 * 为键 key 储存的数字值加上一
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/incr.html" target="_blank">http://redisdoc.com/string/incr
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 键 key 在执行加一操作之后的值
	 */
	Long incr(final byte[] key);

	/**
	 * 为键 key 储存的数字值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/incrby.html" target="_blank">http://redisdoc.com/string/incrby
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 键 key 增量 increment 之后的值
	 */
	Long incrBy(final byte[] key, final int value);

	/**
	 * 为键 key 储存的数字值加上增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/incrby.html" target="_blank">http://redisdoc.com/string/incrby
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 键 key 增量 increment 之后的值
	 */
	Long incrBy(final byte[] key, final long value);

	/**
	 * 为键 key 储存的值加上浮点数增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/incrbyfloat.html" target="_blank">http://redisdoc
	 * .com/string/incrbyfloat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 键 key 增量 increment 之后的值
	 */
	Double incrByFloat(final byte[] key, final float value);

	/**
	 * 为键 key 储存的值加上浮点数增量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/incrbyfloat.html" target="_blank">http://redisdoc
	 * .com/string/incrbyfloat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 键 key 增量 increment 之后的值
	 */
	Double incrByFloat(final byte[] key, final double value);

	/**
	 * 键 key 储存的数字值减去一
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/decr.html" target="_blank">http://redisdoc.com/string/decr
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 键 key 在执行减一操作之后的值
	 */
	Long decr(final byte[] key);

	/**
	 * 将键 key 储存的整数值减去减量 decrement
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/decrby.html" target="_blank">http://redisdoc.com/string/decrby
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 键 key 减量 increment 之后的值
	 */
	Long decrBy(final byte[] key, final int value);

	/**
	 * 将键 key 储存的整数值减去减量 decrement
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/decrby.html" target="_blank">http://redisdoc.com/string/decrby
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 键 key 减量 increment 之后的值
	 */
	Long decrBy(final byte[] key, final long value);

	/**
	 * 从偏移量 offset 开始，用 value 参数覆写键 key 储存的字符串值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/setrange.html" target="_blank">http://redisdoc.com/string/setrange
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param offset
	 * 		偏移量
	 * @param value
	 * 		值
	 *
	 * @return 被修改之后，字符串值的长度
	 */
	Long setRange(final byte[] key, final int offset, final byte[] value);

	/**
	 * 从偏移量 offset 开始，用 value 参数覆写键 key 储存的字符串值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/setrange.html" target="_blank">http://redisdoc.com/string/setrange
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param offset
	 * 		偏移量
	 * @param value
	 * 		值
	 *
	 * @return 被修改之后，字符串值的长度
	 */
	Long setRange(final byte[] key, final long offset, final byte[] value);

	/**
	 * 获取键 key 储存的字符串值的指定部分，字符串的截取范围由 start 和 end 两个偏移量决定 (包括 start 和 end 在内)；
	 * 负数偏移量表示从字符串的末尾开始计数 -1 表示最后一个字符，-2 表示倒数第二个字符，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/getrange.html" target="_blank">http://redisdoc.com/string/getrange
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 符串值的指定部分，通过保证子字符串的值域(range)不超过实际字符串的值域来处理超出范围的值域请求
	 */
	byte[] getRange(final byte[] key, final int start, final int end);

	/**
	 * 获取键 key 储存的字符串值的指定部分，字符串的截取范围由 start 和 end 两个偏移量决定 (包括 start 和 end 在内)；
	 * 负数偏移量表示从字符串的末尾开始计数 -1 表示最后一个字符，-2 表示倒数第二个字符，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/getrange.html" target="_blank">http://redisdoc.com/string/getrange
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 符串值的指定部分，通过保证子字符串的值域(range)不超过实际字符串的值域来处理超出范围的值域请求
	 */
	byte[] getRange(final byte[] key, final long start, final long end);

	/**
	 * 获取键 key 储存的字符串值的指定部分，字符串的截取范围由 start 和 end 两个偏移量决定 (包括 start 和 end 在内)；
	 * 负数偏移量表示从字符串的末尾开始计数 -1 表示最后一个字符，-2 表示倒数第二个字符，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/substr.html" target="_blank">http://redisdoc.com/string/substr
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 符串值的指定部分，通过保证子字符串的值域(range)不超过实际字符串的值域来处理超出范围的值域请求
	 */
	byte[] substr(final byte[] key, final int start, final int end);

	/**
	 * 获取键 key 储存的字符串值的指定部分，字符串的截取范围由 start 和 end 两个偏移量决定 (包括 start 和 end 在内)；
	 * 负数偏移量表示从字符串的末尾开始计数 -1 表示最后一个字符，-2 表示倒数第二个字符，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/substr.html" target="_blank">http://redisdoc.com/string/substr
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 符串值的指定部分，通过保证子字符串的值域(range)不超过实际字符串的值域来处理超出范围的值域请求
	 */
	byte[] substr(final byte[] key, final long start, final long end);

	/**
	 * 获取键 key 储存的字符串值的长度
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/string/strlen.html" target="_blank">http://redisdoc.com/string/strlen
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 字符串值的长度；键 key 不存在时，命令返回 0 ；当 key 储存的不是字符串值时，抛出异常
	 */
	Long strlen(final byte[] key);

}
