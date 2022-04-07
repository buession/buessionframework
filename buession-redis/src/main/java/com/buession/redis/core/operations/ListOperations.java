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

import com.buession.core.serializer.type.TypeReference;
import com.buession.lang.Status;
import com.buession.redis.core.Direction;
import com.buession.redis.core.command.ListCommands;
import redis.clients.jedis.ListPosition;

import java.util.List;

/**
 * 列表运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/list/index.html" target="_blank">http://redisdoc.com/list/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface ListOperations extends ListCommands, RedisOperations {

	/**
	 * 获取列表 key 中，下标为 index 的元素，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lindex.html" target="_blank">http://redisdoc.com/list/lindex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param index
	 * 		下标
	 * @param <V>
	 * 		值类型
	 *
	 * @return 下标为 index 的元素反序列化后的值；如果 index 参数的值不在列表的区间范围内，返回 null
	 */
	<V> V lIndexObject(final String key, final long index);

	/**
	 * 获取列表 key 中，下标为 index 的元素，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lindex.html" target="_blank">http://redisdoc.com/list/lindex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param index
	 * 		下标
	 * @param <V>
	 * 		值类型
	 *
	 * @return 下标为 index 的元素反序列化后的值；如果 index 参数的值不在列表的区间范围内，返回 null
	 */
	<V> V lIndexObject(final byte[] key, final long index);

	/**
	 * 获取列表 key 中，下标为 index 的元素，并反序列为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lindex.html" target="_blank">http://redisdoc.com/list/lindex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param index
	 * 		下标
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 下标为 index 的元素反序列化后的值；如果 index 参数的值不在列表的区间范围内，返回 null
	 *
	 * @see java.lang.Class
	 */
	<V> V lIndexObject(final String key, final long index, final Class<V> clazz);

	/**
	 * 获取列表 key 中，下标为 index 的元素，并反序列为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lindex.html" target="_blank">http://redisdoc.com/list/lindex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param index
	 * 		下标
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 下标为 index 的元素反序列化后的值；如果 index 参数的值不在列表的区间范围内，返回 null
	 *
	 * @see java.lang.Class
	 */
	<V> V lIndexObject(final byte[] key, final long index, final Class<V> clazz);

	/**
	 * 获取列表 key 中，下标为 index 的元素，并反序列为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lindex.html" target="_blank">http://redisdoc.com/list/lindex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param index
	 * 		下标
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 下标为 index 的元素反序列化后的值；如果 index 参数的值不在列表的区间范围内，返回 null
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V lIndexObject(final String key, final long index, final TypeReference<V> type);

	/**
	 * 获取列表 key 中，下标为 index 的元素，并反序列为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lindex.html" target="_blank">http://redisdoc.com/list/lindex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param index
	 * 		下标
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 下标为 index 的元素反序列化后的值；如果 index 参数的值不在列表的区间范围内，返回 null
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V lIndexObject(final byte[] key, final long index, final TypeReference<V> type);

	/**
	 * 将值 value 序列化后，插入到列表 key 当中，位于值 pivot 之前或之后
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/linsert.html" target="_blank">http://redisdoc.com/list/linsert.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param position
	 * 		位置
	 * @param pivot
	 * 		pivot
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行成功，返回插入操作完成之后，列表的长度；
	 * 如果没有找到 pivot ，返回 -1 ；如果 key 不存在或为空列表，返回 0 。
	 */
	<V> Long lInsert(final String key, final ListPosition position, final V pivot, final V value);

	/**
	 * 将值 value 序列化后，插入到列表 key 当中，位于值 pivot 之前或之后
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/linsert.html" target="_blank">http://redisdoc.com/list/linsert.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param position
	 * 		位置
	 * @param pivot
	 * 		pivot
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行成功，返回插入操作完成之后，列表的长度；
	 * 如果没有找到 pivot ，返回 -1 ；如果 key 不存在或为空列表，返回 0 。
	 */
	<V> Long lInsert(final byte[] key, final ListPosition position, final V pivot, final V value);

	/**
	 * 将列表 key 下标为 index 的元素的值设置为 value 序列化的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lset.html" target="_blank">http://redisdoc.com/list/lset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param index
	 * 		下标
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果；设置成功时返回 Status.Success，否则返回 Status.FAILURE
	 */
	<V> Status lSet(final String key, final long index, final V value);

	/**
	 * 将列表 key 下标为 index 的元素的值设置为 value 序列化的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lset.html" target="_blank">http://redisdoc.com/list/lset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param index
	 * 		下标
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果；设置成功时返回 Status.Success，否则返回 Status.FAILURE
	 */
	<V> Status lSet(final byte[] key, final long index, final V value);

	/**
	 * 获取列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 之间的元素（包含 start 和 stop）并反序列化为对象；
	 * 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lrange.html" target="_blank">http://redisdoc.com/list/lrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内的元素反序列化后的对象；
	 * 如果 start 下标比列表的最大下标 ( LLEN list 减去 1 )还要大，那么返回一个空列表；
	 * 如果 end 下标比最大下标还要大，那么最多返回到 end 个下标
	 */
	<V> List<V> lRangeObject(final String key, final long start, final long end);

	/**
	 * 获取列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 之间的元素（包含 start 和 stop）并反序列化为对象；
	 * 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lrange.html" target="_blank">http://redisdoc.com/list/lrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内的元素反序列化后的对象；
	 * 如果 start 下标比列表的最大下标 ( LLEN list 减去 1 )还要大，那么返回一个空列表；
	 * 如果 end 下标比最大下标还要大，那么最多返回到 end 个下标
	 */
	<V> List<V> lRangeObject(final byte[] key, final long start, final long end);

	/**
	 * 获取列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 之间的元素（包含 start 和 stop）并反序列化为 clazz 指定的对象；
	 * 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lrange.html" target="_blank">http://redisdoc.com/list/lrange.html</a></p>
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
	 * @return 指定区间内的元素反序列化后的对象；
	 * 如果 start 下标比列表的最大下标 ( LLEN list 减去 1 )还要大，那么返回一个空列表；
	 * 如果 end 下标比最大下标还要大，那么最多返回到 end 个下标
	 */
	<V> List<V> lRangeObject(final String key, final long start, final long end, final Class<V> clazz);

	/**
	 * 获取列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 之间的元素（包含 start 和 stop）并反序列化为 clazz 指定的对象；
	 * 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lrange.html" target="_blank">http://redisdoc.com/list/lrange.html</a></p>
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
	 * @return 指定区间内的元素反序列化后的对象；
	 * 如果 start 下标比列表的最大下标 ( LLEN list 减去 1 )还要大，那么返回一个空列表；
	 * 如果 end 下标比最大下标还要大，那么最多返回到 end 个下标
	 */
	<V> List<V> lRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz);

	/**
	 * 获取列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 之间的元素（包含 start 和 stop）并反序列化为 type 指定的对象；
	 * 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lrange.html" target="_blank">http://redisdoc.com/list/lrange.html</a></p>
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
	 * @return 指定区间内的元素反序列化后的对象；
	 * 如果 start 下标比列表的最大下标 ( LLEN list 减去 1 )还要大，那么返回一个空列表；
	 * 如果 end 下标比最大下标还要大，那么最多返回到 end 个下标
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> List<V> lRangeObject(final String key, final long start, final long end, final TypeReference<V> type);

	/**
	 * 获取列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 之间的元素（包含 start 和 stop）并反序列化为 type 指定的对象；
	 * 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lrange.html" target="_blank">http://redisdoc.com/list/lrange.html</a></p>
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
	 * @return 指定区间内的元素反序列化后的对象；
	 * 如果 start 下标比列表的最大下标 ( LLEN list 减去 1 )还要大，那么返回一个空列表；
	 * 如果 end 下标比最大下标还要大，那么最多返回到 end 个下标
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> List<V> lRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化后的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 */
	<V> V lMoveObject(final String key, final String destKey, final Direction from, final Direction to);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化后的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 */
	<V> V lMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化为 clazz 的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 */
	<V> V lMoveObject(final String key, final String destKey, final Direction from, final Direction to,
					  final Class<V> clazz);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化为 clazz 的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 */
	<V> V lMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
					  final Class<V> clazz);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化为 type 指定的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V lMoveObject(final String key, final String destKey, final Direction from, final Direction to,
					  final TypeReference<V> type);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化为 type 指定的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V lMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
					  final TypeReference<V> type);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化后的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)；
	 * 是 lmove 的阻塞版
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * @param timeout
	 * 		超时时间
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 */
	<V> V blMoveObject(final String key, final String destKey, final Direction from, final Direction to,
					   final int timeout);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化后的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)；
	 * 是 lmove 的阻塞版
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param timeout
	 * 		超时时间
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 */
	<V> V blMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
					   final int timeout);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化为 clazz 的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)；
	 * 是 lmove 的阻塞版
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param timeout
	 * 		超时时间
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 */
	<V> V blMoveObject(final String key, final String destKey, final Direction from, final Direction to,
					   final int timeout, final Class<V> clazz);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化为 clazz 的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)；
	 * 是 lmove 的阻塞版
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param timeout
	 * 		超时时间
	 * @param clazz
	 * 		元素值对象类
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 */
	<V> V blMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
					   final int timeout, final Class<V> clazz);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化为 type 指定的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)；
	 * 是 lmove 的阻塞版
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param timeout
	 * 		超时时间
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V blMoveObject(final String key, final String destKey, final Direction from, final Direction to,
					   final int timeout, final TypeReference<V> type);

	/**
	 * 用于原子地从列表 key 中移除并返回第一个或最后一个元素反序列化为 type 指定的对象（头或尾取决于 from 参数)，然后把这个元素插入到列表 destKey 的第一个或最后一个元素（头或尾取决于 to 参数)；
	 * 是 lmove 的阻塞版
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param from
	 * 		第一个或最后一个元素
	 * @param to
	 * 		第一个或最后一个元素
	 * @param timeout
	 * 		超时时间
	 * @param type
	 * 		元素值类型引用
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被移除并再次插入的元素
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V blMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
					   final int timeout, final TypeReference<V> type);

	/**
	 * 移除并返回 key 的头元素，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
	 */
	default List<String> blPop(final String key, final int timeout){
		return blPop(new String[]{key}, timeout);
	}

	/**
	 * 移除并返回列表中 key 的头元素，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
	 */
	default List<byte[]> blPop(final byte[] key, final int timeout){
		return blPop(new byte[][]{key}, timeout);
	}

	/**
	 * 移除并返回 key 的头元素反序列化后的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化后的对象。
	 */
	default <V> List<V> blPopObject(final String key, final int timeout){
		return blPopObject(new String[]{key}, timeout);
	}

	/**
	 * 移除并返回 key 的头元素反序列化后的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化后的对象。
	 */
	default <V> List<V> blPopObject(final byte[] key, final int timeout){
		return blPopObject(new byte[][]{key}, timeout);
	}

	/**
	 * 移除并返回 key 的头元素反序列化为 clazz 指定的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化为 clazz 指定的对象。
	 *
	 * @see java.lang.Class
	 */
	default <V> List<V> blPopObject(final String key, final int timeout, final Class<V> clazz){
		return blPopObject(new String[]{key}, timeout, clazz);
	}

	/**
	 * 移除并返回 key 的头元素反序列化为 clazz 指定的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化为 clazz 指定的对象。
	 */
	default <V> List<V> blPopObject(final byte[] key, final int timeout, final Class<V> clazz){
		return blPopObject(new byte[][]{key}, timeout, clazz);
	}

	/**
	 * 移除并返回 key 的头元素反序列化为 type 指定的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化为 type 指定的对象。
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	default <V> List<V> blPopObject(final String key, final int timeout, final TypeReference<V> type){
		return blPopObject(new String[]{key}, timeout, type);
	}

	/**
	 * 移除并返回 key 的头元素反序列化为 type 指定的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化为 type 指定的对象。
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	default <V> List<V> blPopObject(final byte[] key, final int timeout, final TypeReference<V> type){
		return blPopObject(new byte[][]{key}, timeout, type);
	}

	/**
	 * 移除并返回 key 的头元素反序列化后的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化后的对象。
	 */
	<V> List<V> blPopObject(final String[] keys, final int timeout);

	/**
	 * 移除并返回 key 的头元素反序列化后的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化后的对象。
	 */
	<V> List<V> blPopObject(final byte[][] keys, final int timeout);

	/**
	 * 移除并返回 key 的头元素反序列化为 clazz 指定的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化为 clazz 指定的对象。
	 *
	 * @see java.lang.Class
	 */
	<V> List<V> blPopObject(final String[] keys, final int timeout, final Class<V> clazz);

	/**
	 * 移除并返回 key 的头元素反序列化为 clazz 指定的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化为 clazz 指定的对象。
	 */
	<V> List<V> blPopObject(final byte[][] keys, final int timeout, final Class<V> clazz);

	/**
	 * 移除并返回 key 的头元素反序列化为 type 指定的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化为 type 指定的对象。
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> List<V> blPopObject(final String[] keys, final int timeout, final TypeReference<V> type);

	/**
	 * 移除并返回 key 的头元素反序列化为 type 指定的对象，BLPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化为 type 指定的对象。
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> List<V> blPopObject(final byte[][] keys, final int timeout, final TypeReference<V> type);

	/**
	 * 移除并返回列表中 key 的尾元素，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
	 */
	default List<String> brPop(final String key, final int timeout){
		return brPop(new String[]{key}, timeout);
	}

	/**
	 * 移除并返回列表中 key 的尾元素，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
	 */
	default List<byte[]> brPop(final byte[] key, final int timeout){
		return brPop(new byte[][]{key}, timeout);
	}

	/**
	 * 移除并返回列表中 key 的尾元素反序列化后的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化后的对象。
	 */
	default <V> List<V> brPopObject(final String key, final int timeout){
		return brPopObject(new String[]{key}, timeout);
	}

	/**
	 * 移除并返回列表中 key 的尾元素反序列化后的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化后的对象。
	 */
	default <V> List<V> brPopObject(final byte[] key, final int timeout){
		return brPopObject(new byte[][]{key}, timeout);
	}

	/**
	 * 移除并返回列表中 key 的尾元素反序列化为 clazz 指定的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值序列化为 clazz 指定的对象。
	 */
	default <V> List<V> brPopObject(final String key, final int timeout, final Class<V> clazz){
		return brPopObject(new String[]{key}, timeout, clazz);
	}

	/**
	 * 移除并返回列表中 key 的尾元素反序列化为 clazz 指定的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值序列化为 clazz 指定的对象。
	 */
	default <V> List<V> brPopObject(final byte[] key, final int timeout, final Class<V> clazz){
		return brPopObject(new byte[][]{key}, timeout, clazz);
	}

	/**
	 * 移除并返回列表中 key 的尾元素反序列化为 type 指定的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值序列化为 type 指定的对象。
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	default <V> List<V> brPopObject(final String key, final int timeout, final TypeReference<V> type){
		return brPopObject(new String[]{key}, timeout, type);
	}

	/**
	 * 移除并返回列表中 key 的尾元素反序列化为 type 指定的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param key
	 * 		key
	 * @param timeout
	 * 		超时时间
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值序列化为 type 指定的对象。
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	default <V> List<V> brPopObject(final byte[] key, final int timeout, final TypeReference<V> type){
		return brPopObject(new byte[][]{key}, timeout, type);
	}

	/**
	 * 移除并返回列表中 key 的尾元素反序列化后的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化后的对象。
	 */
	<V> List<V> brPopObject(final String[] keys, final int timeout);

	/**
	 * 移除并返回列表中 key 的尾元素反序列化后的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值反序列化后的对象。
	 */
	<V> List<V> brPopObject(final byte[][] keys, final int timeout);

	/**
	 * 移除并返回列表中 key 的尾元素反序列化为 clazz 指定的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值序列化为 clazz 指定的对象。
	 */
	<V> List<V> brPopObject(final String[] keys, final int timeout, final Class<V> clazz);

	/**
	 * 移除并返回列表中 key 的尾元素反序列化为 clazz 指定的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值序列化为 clazz 指定的对象。
	 */
	<V> List<V> brPopObject(final byte[][] keys, final int timeout, final Class<V> clazz);

	/**
	 * 移除并返回列表中 key 的尾元素反序列化为 type 指定的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值序列化为 type 指定的对象。
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> List<V> brPopObject(final String[] keys, final int timeout, final TypeReference<V> type);

	/**
	 * 移除并返回列表中 key 的尾元素反序列化为 type 指定的对象，BRPOP 是列表的阻塞式(blocking)弹出原语；
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 * @param timeout
	 * 		超时时间
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值序列化为 type 指定的对象。
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> List<V> brPopObject(final byte[][] keys, final int timeout, final TypeReference<V> type);

	/**
	 * 将列表 key 中的最后尾元素弹出，并返回反序列化后的对象；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 * RPOPLPUSH 是列表的阻塞式(blocking)弹出原语
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param timeout
	 * 		超时时间（单位：秒）
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素的值反序列化后的对象 ，第二个等待时长
	 */
	<V> V brPoplPushObject(final String key, final String destKey, final int timeout);

	/**
	 * 将列表 key 中的最后尾元素弹出，并返回反序列化后的对象；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 * RPOPLPUSH 是列表的阻塞式(blocking)弹出原语
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param timeout
	 * 		超时时间（单位：秒）
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素的值反序列化后的对象 ，第二个等待时长
	 */
	<V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout);

	/**
	 * 将列表 key 中的最后尾元素弹出，并返回反序列化为 clazz 指定的对象；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 * RPOPLPUSH 是列表的阻塞式(blocking)弹出原语
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param timeout
	 * 		超时时间（单位：秒）
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素的值反序列化为 clazz 指定的对象 ，第二个等待时长
	 */
	<V> V brPoplPushObject(final String key, final String destKey, final int timeout, final Class<V> clazz);

	/**
	 * 将列表 key 中的最后尾元素弹出，并返回反序列化为 clazz 指定的对象；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 * RPOPLPUSH 是列表的阻塞式(blocking)弹出原语
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param timeout
	 * 		超时时间（单位：秒）
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素的值反序列化为 clazz 指定的对象 ，第二个等待时长
	 */
	<V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout, final Class<V> clazz);

	/**
	 * 将列表 key 中的最后尾元素弹出，并返回反序列化为 type 指定的对象；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 * RPOPLPUSH 是列表的阻塞式(blocking)弹出原语
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param timeout
	 * 		超时时间（单位：秒）
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素的值反序列化为 type 指定的对象 ，第二个等待时长
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V brPoplPushObject(final String key, final String destKey, final int timeout, final TypeReference<V> type);

	/**
	 * 将列表 key 中的最后尾元素弹出，并返回反序列化为 type 指定的对象；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 * RPOPLPUSH 是列表的阻塞式(blocking)弹出原语
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param timeout
	 * 		超时时间（单位：秒）
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素的值反序列化为 type 指定的对象 ，第二个等待时长
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout, final TypeReference<V> type);

	/**
	 * 移除并返回列表 key 的头元素，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpop.html" target="_blank">http://redisdoc.com/list/lpop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的头元素反序列化后的值；当 key 不存在时，返回 null
	 */
	<V> V lPopObject(final String key);

	/**
	 * 移除并返回列表 key 的头元素，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpop.html" target="_blank">http://redisdoc.com/list/lpop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的头元素反序列化后的值；当 key 不存在时，返回 null
	 */
	<V> V lPopObject(final byte[] key);

	/**
	 * 移除并返回列表 key 的头元素，并反序列为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpop.html" target="_blank">http://redisdoc.com/list/lpop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的头元素反序列化后的值；当 key 不存在时，返回 null
	 */
	<V> V lPopObject(final String key, final Class<V> clazz);

	/**
	 * 移除并返回列表 key 的头元素，并反序列为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpop.html" target="_blank">http://redisdoc.com/list/lpop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的头元素反序列化后的值；当 key 不存在时，返回 null
	 */
	<V> V lPopObject(final byte[] key, final Class<V> clazz);

	/**
	 * 移除并返回列表 key 的头元素，并反序列为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpop.html" target="_blank">http://redisdoc.com/list/lpop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的头元素反序列化后的值；当 key 不存在时，返回 null
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V lPopObject(final String key, final TypeReference<V> type);

	/**
	 * 移除并返回列表 key 的头元素，并反序列为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpop.html" target="_blank">http://redisdoc.com/list/lpop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的头元素反序列化后的值；当 key 不存在时，返回 null
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V lPopObject(final byte[] key, final TypeReference<V> type);

	/**
	 * 将一个值 value 插入到列表 key 的表头
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpush.html" target="_blank">http://redisdoc.com/list/lpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 执行 LPUSH 命令后，列表的长度
	 */
	default Long lPush(final String key, final String value){
		return lPush(key, new String[]{value});
	}

	/**
	 * 将一个值 value 插入到列表 key 的表头
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpush.html" target="_blank">http://redisdoc.com/list/lpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 执行 LPUSH 命令后，列表的长度
	 */
	default Long lPush(final byte[] key, final byte[] value){
		return lPush(key, new byte[][]{value});
	}

	/**
	 * 将一个值 value 序列化后，插入到列表 key 的表头
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpush.html" target="_blank">http://redisdoc.com/list/lpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 LPUSH 命令后，列表的长度
	 */
	<V> Long lPush(final String key, final V value);

	/**
	 * 将一个或多个值 value 序列化后，插入到列表 key 的表头
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpush.html" target="_blank">http://redisdoc.com/list/lpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 LPUSH 命令后，列表的长度
	 */
	<V> Long lPush(final byte[] key, final V value);

	/**
	 * 将一个或多个值 value 序列化后，插入到列表 key 的表头
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpush.html" target="_blank">http://redisdoc.com/list/lpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		一个或多个值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 LPUSH 命令后，列表的长度
	 */
	<V> Long lPush(final String key, final V... values);

	/**
	 * 将一个或多个值 value 序列化后，插入到列表 key 的表头
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpush.html" target="_blank">http://redisdoc.com/list/lpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		一个或多个值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 LPUSH 命令后，列表的长度
	 */
	<V> Long lPush(final byte[] key, final V... values);

	/**
	 * 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpushx.html" target="_blank">http://redisdoc.com/list/lpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		一值
	 *
	 * @return 执行 LPUSHX 命令后，列表的长度
	 */
	default Long lPushX(final String key, final String value){
		return lPushX(key, new String[]{value});
	}

	/**
	 * 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpushx.html" target="_blank">http://redisdoc.com/list/lpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 执行 LPUSHX 命令后，列表的长度
	 */
	default Long lPushX(final byte[] key, final byte[] value){
		return lPushX(key, new byte[][]{value});
	}

	/**
	 * 将值 value 序列化后，插入到列表 key 的表头，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpushx.html" target="_blank">http://redisdoc.com/list/lpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 LPUSHX 命令后，列表的长度
	 */
	<V> Long lPushX(final String key, final V value);

	/**
	 * 将值 value 序列化后，插入到列表 key 的表头，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpushx.html" target="_blank">http://redisdoc.com/list/lpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 LPUSHX 命令后，列表的长度
	 */
	<V> Long lPushX(final byte[] key, final V value);

	/**
	 * 将一个或多个值 value 序列化后，插入到列表 key 的表头，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpushx.html" target="_blank">http://redisdoc.com/list/lpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 LPUSHX 命令后，列表的长度
	 */
	<V> Long lPushX(final String key, final V... values);

	/**
	 * 将一个或多个值 value 序列化后，插入到列表 key 的表头，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/lpushx.html" target="_blank">http://redisdoc.com/list/lpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 LPUSHX 命令后，列表的长度
	 */
	<V> Long lPushX(final byte[] key, final V... values);

	/**
	 * 移除并返回列表 key 的尾元素，并反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的尾元素反序列化后的值；当 key 不存在时，返回 null
	 */
	<V> V rPopObject(final String key);

	/**
	 * 移除并返回列表 key 的尾元素，并反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的尾元素反序列化后的值；当 key 不存在时，返回 null
	 */
	<V> V rPopObject(final byte[] key);

	/**
	 * 移除并返回列表 key 的尾元素，并反序列化为 clazz 指定的对象对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpop.html" target="_blank">http://redisdoc.com/list/rpop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的尾元素反序列化后的值；当 key 不存在时，返回 null
	 */
	<V> V rPopObject(final String key, final Class<V> clazz);

	/**
	 * 移除并返回列表 key 的尾元素，并反序列化为 clazz 指定的对象对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpop.html" target="_blank">http://redisdoc.com/list/rpop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的尾元素反序列化后的值；当 key 不存在时，返回 null
	 */
	<V> V rPopObject(final byte[] key, final Class<V> clazz);

	/**
	 * 移除并返回列表 key 的尾元素，并反序列化为 type 指定的对象对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpop.html" target="_blank">http://redisdoc.com/list/rpop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的尾元素反序列化后的值；当 key 不存在时，返回 null
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V rPopObject(final String key, final TypeReference<V> type);

	/**
	 * 移除并返回列表 key 的尾元素，并反序列化为 type 指定的对象对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpop.html" target="_blank">http://redisdoc.com/list/rpop.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 列表的尾元素反序列化后的值；当 key 不存在时，返回 null
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V rPopObject(final byte[] key, final TypeReference<V> type);

	/**
	 * 将列表 key 中的最后尾元素弹出，并反序列化为对象后返回；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 被弹出的元素反序列化后的对象
	 */
	<V> V rPoplPushObject(final String key, final String destKey);

	/**
	 * 将列表 key 中的最后尾元素弹出，并反序列化为对象后返回；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 被弹出的元素反序列化后的对象
	 */
	<V> V rPoplPushObject(final byte[] key, final byte[] destKey);

	/**
	 * 将列表 key 中的最后尾元素弹出，并反序列化为 clazz 指定的对象后返回；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 被弹出的元素反序列化后的对象
	 */
	<V> V rPoplPushObject(final String key, final String destKey, final Class<V> clazz);

	/**
	 * 将列表 key 中的最后尾元素弹出，并反序列化为 clazz 指定的对象后返回；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 被弹出的元素反序列化后的对象
	 */
	<V> V rPoplPushObject(final byte[] key, final byte[] destKey, final Class<V> clazz);

	/**
	 * 将列表 key 中的最后尾元素弹出，并反序列化为 type 指定的对象后返回；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 被弹出的元素反序列化后的对象
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V rPoplPushObject(final String key, final String destKey, final TypeReference<V> type);

	/**
	 * 将列表 key 中的最后尾元素弹出，并反序列化为 type 指定的对象后返回；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
	 * 如果 key 不存在，值 null 被返回，并且不执行其他动作；
	 * 如果 key 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 被弹出的元素反序列化后的对象
	 *
	 * @see com.buession.core.serializer.type.TypeReference
	 */
	<V> V rPoplPushObject(final byte[] key, final byte[] destKey, final TypeReference<V> type);

	/**
	 * 将值 value 插入到列表 key 的表尾
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpush.html" target="_blank">http://redisdoc.com/list/rpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 执行 RPUSH 操作后，表的长度
	 */
	default Long rPush(final String key, final String value){
		return rPush(key, new String[]{value});
	}

	/**
	 * 将值 value 插入到列表 key 的表尾
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpush.html" target="_blank">http://redisdoc.com/list/rpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 执行 RPUSH 操作后，表的长度
	 */
	default Long rPush(final byte[] key, final byte[] value){
		return rPush(key, new byte[][]{value});
	}

	/**
	 * 将值 value 序列化后，插入到列表 key 的表尾
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpush.html" target="_blank">http://redisdoc.com/list/rpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 RPUSH 操作后，表的长度
	 */
	<V> Long rPush(final String key, final V value);

	/**
	 * 将值 value 序列化后，插入到列表 key 的表尾
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpush.html" target="_blank">http://redisdoc.com/list/rpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 RPUSH 操作后，表的长度
	 */
	<V> Long rPush(final byte[] key, final V value);

	/**
	 * 将一个或多个值 value 序列化后，插入到列表 key 的表尾
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpush.html" target="_blank">http://redisdoc.com/list/rpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		一个或多个值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 RPUSH 操作后，表的长度
	 */
	<V> Long rPush(final String key, final V... values);

	/**
	 * 将一个或多个值 value 序列化后，插入到列表 key 的表尾
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpush.html" target="_blank">http://redisdoc.com/list/rpush.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		一个或多个值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 RPUSH 操作后，表的长度
	 */
	<V> Long rPush(final byte[] key, final V... values);

	/**
	 * 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpushx.html" target="_blank">http://redisdoc.com/list/rpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 执行 RPUSHX 之后，表的长度
	 */
	default Long rPushX(final String key, final String value){
		return rPushX(key, new String[]{value});
	}

	/**
	 * 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpushx.html" target="_blank">http://redisdoc.com/list/rpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 执行 RPUSHX 之后，表的长度
	 */
	default Long rPushX(final byte[] key, final byte[] value){
		return rPushX(key, new byte[][]{value});
	}

	/**
	 * 将值 value 序列化后，插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpushx.html" target="_blank">http://redisdoc.com/list/rpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 RPUSHX 之后，表的长度
	 */
	<V> Long rPushX(final String key, final V value);

	/**
	 * 将值 value 序列化后，插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpushx.html" target="_blank">http://redisdoc.com/list/rpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 RPUSHX 之后，表的长度
	 */
	<V> Long rPushX(final byte[] key, final V value);

	/**
	 * 将一个或多个值 value 序列化后，插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpushx.html" target="_blank">http://redisdoc.com/list/rpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		一个或多个值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 RPUSHX 之后，表的长度
	 */
	<V> Long rPushX(final String key, final V... values);

	/**
	 * 将一个或多个值 value 序列化后，插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/list/rpushx.html" target="_blank">http://redisdoc.com/list/rpushx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		一个或多个值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 执行 RPUSHX 之后，表的长度
	 */
	<V> Long rPushX(final byte[] key, final V... values);

}
