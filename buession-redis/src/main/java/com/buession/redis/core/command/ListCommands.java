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

import com.buession.core.Status;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * 列表命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/list/index.html" target="_blank">http://redisdoc.com/list/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface ListCommands extends RedisCommands {

    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lpush.html" target="_blank">http://redisdoc.com/list/lpush.html</a></p>
     *
     * @param key
     *         Key
     * @param values
     *         一个或多个值
     *
     * @return 执行 LPUSH 命令后，列表的长度
     */
    Long lPush(final String key, final String... values);

    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lpush.html" target="_blank">http://redisdoc.com/list/lpush.html</a></p>
     *
     * @param key
     *         Key
     * @param values
     *         一个或多个值
     *
     * @return 执行 LPUSH 命令后，列表的长度
     */
    Long lPush(final byte[] key, final byte[]... values);

    /**
     * 将一个或多个值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lpushx.html" target="_blank">http://redisdoc.com/list/lpushx
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param values
     *         一个或多个值
     *
     * @return 执行 LPUSHX 命令后，列表的长度
     */
    Long lPushX(final String key, final String... values);

    /**
     * 将一个或多个值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lpushx.html" target="_blank">http://redisdoc.com/list/lpushx
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param values
     *         一个或多个值
     *
     * @return 执行 LPUSHX 命令后，列表的长度
     */
    Long lPushX(final byte[] key, final byte[]... values);

    /**
     * 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/linsert.html" target="_blank">http://redisdoc.com/list/linsert
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param value
     *         值
     * @param position
     *         位置
     * @param pivot
     *         pivot
     *
     * @return 执行成功，返回插入操作完成之后，列表的长度；
     * 如果没有找到 pivot ，返回 -1 ；如果 key 不存在或为空列表，返回 0 。
     */
    Long lInsert(final String key, final String value, final ListPosition position, final String pivot);

    /**
     * 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/linsert.html" target="_blank">http://redisdoc.com/list/linsert
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param value
     *         值
     * @param position
     *         位置
     * @param pivot
     *         pivot
     *
     * @return 执行成功，返回插入操作完成之后，列表的长度；
     * 如果没有找到 pivot ，返回 -1 ；如果 key 不存在或为空列表，返回 0 。
     */
    Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot);

    /**
     * 将列表 key 下标为 index 的元素的值设置为 value
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lset.html" target="_blank">http://redisdoc.com/list/lset.html</a></p>
     *
     * @param key
     *         Key
     * @param index
     *         下标
     * @param value
     *         值
     *
     * @return 操作结果；设置成功时返回 Status.Success，否则返回 Status.FAILURE
     */
    Status lSet(final String key, final int index, final String value);

    /**
     * 将列表 key 下标为 index 的元素的值设置为 value
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lset.html" target="_blank">http://redisdoc.com/list/lset.html</a></p>
     *
     * @param key
     *         Key
     * @param index
     *         下标
     * @param value
     *         值
     *
     * @return 操作结果；设置成功时返回 Status.Success，否则返回 Status.FAILURE
     */
    Status lSet(final byte[] key, final int index, final byte[] value);

    /**
     * 将列表 key 下标为 index 的元素的值设置为 value
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lset.html" target="_blank">http://redisdoc.com/list/lset.html</a></p>
     *
     * @param key
     *         Key
     * @param index
     *         下标
     * @param value
     *         值
     *
     * @return 操作结果；设置成功时返回 Status.Success，否则返回 Status.FAILURE
     */
    Status lSet(final String key, final long index, final String value);

    /**
     * 将列表 key 下标为 index 的元素的值设置为 value
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lset.html" target="_blank">http://redisdoc.com/list/lset.html</a></p>
     *
     * @param key
     *         Key
     * @param index
     *         下标
     * @param value
     *         值
     *
     * @return 操作结果；设置成功时返回 Status.Success，否则返回 Status.FAILURE
     */
    Status lSet(final byte[] key, final long index, final byte[] value);

    /**
     * 获取列表 key 中，下标为 index 的元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lindex.html" target="_blank">http://redisdoc.com/list/lindex
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param index
     *         下标
     *
     * @return 下标为 index 的元素；如果 index 参数的值不在列表的区间范围内，返回 null
     */
    String lIndex(final String key, final int index);

    /**
     * 获取列表 key 中，下标为 index 的元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lindex.html" target="_blank">http://redisdoc.com/list/lindex
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param index
     *         下标
     *
     * @return 下标为 index 的元素；如果 index 参数的值不在列表的区间范围内，返回 null
     */
    byte[] lIndex(final byte[] key, final int index);

    /**
     * 获取列表 key 中，下标为 index 的元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lindex.html" target="_blank">http://redisdoc.com/list/lindex
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param index
     *         下标
     *
     * @return 下标为 index 的元素；如果 index 参数的值不在列表的区间范围内，返回 null
     */
    String lIndex(final String key, final long index);

    /**
     * 获取列表 key 中，下标为 index 的元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lindex.html" target="_blank">http://redisdoc.com/list/lindex
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param index
     *         下标
     *
     * @return 下标为 index 的元素；如果 index 参数的值不在列表的区间范围内，返回 null
     */
    byte[] lIndex(final byte[] key, final long index);

    /**
     * 移除并返回列表 key 的头元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lpop.html" target="_blank">http://redisdoc.com/list/lpop.html</a></p>
     *
     * @param key
     *         Key
     *
     * @return 列表的头元素；当 key 不存在时，返回 null
     */
    String lPop(final String key);

    /**
     * 移除并返回列表 key 的头元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lpop.html" target="_blank">http://redisdoc.com/list/lpop.html</a></p>
     *
     * @param key
     *         Key
     *
     * @return 列表的头元素；当 key 不存在时，返回 null
     */
    byte[] lPop(final byte[] key);

    /**
     * 移除并返回列表中一个或多个 key 的头元素，BLPOP 是列表的阻塞式(blocking)弹出原语；
     * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
     *
     * @param keys
     *         一个或多个 key
     * @param timeout
     *         超时时间
     *
     * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值
     */
    List<String> blPop(final String[] keys, final int timeout);

    /**
     * 移除并返回列表中一个或多个 key 的头元素，BLPOP 是列表的阻塞式(blocking)弹出原语；
     * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/blpop.html" target="_blank">http://redisdoc.com/list/blpop.html</a></p>
     *
     * @param keys
     *         一个或多个 key
     * @param timeout
     *         超时时间
     *
     * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值
     */
    List<byte[]> blPop(final byte[][] keys, final int timeout);

    /**
     * 移除并返回列表 key 的尾元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/rpop.html" target="_blank">http://redisdoc.com/list/rpop.html</a></p>
     *
     * @param key
     *         Key
     *
     * @return 列表的尾元素；当 key 不存在时，返回 null
     */
    String rPop(final String key);

    /**
     * 移除并返回列表 key 的尾元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/rpop.html" target="_blank">http://redisdoc.com/list/rpop.html</a></p>
     *
     * @param key
     *         Key
     *
     * @return 列表的尾元素；当 key 不存在时，返回 null
     */
    byte[] rPop(final byte[] key);

    /**
     * 将列表 source 中的最后尾元素弹出，并返回；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
     * 如果 source 不存在，值 null 被返回，并且不执行其他动作；
     * 如果 source 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush
     * .html</a></p>
     *
     * @param source
     *         Key
     * @param destKey
     *         目标 Key
     *
     * @return 被弹出的元素
     */
    String rPoplPush(final String source, final String destKey);

    /**
     * 将列表 source 中的最后尾元素弹出，并返回；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
     * 如果 source 不存在，值 null 被返回，并且不执行其他动作；
     * 如果 source 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush
     * .html</a></p>
     *
     * @param source
     *         Key
     * @param destKey
     *         目标 Key
     *
     * @return 被弹出的元素
     */
    byte[] rPoplPush(final byte[] source, final byte[] destKey);

    /**
     * 移除并返回列表中一个或多个 key 的尾元素，BRPOP 是列表的阻塞式(blocking)弹出原语；
     * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
     *
     * @param keys
     *         一个或多个 key
     * @param timeout
     *         超时时间（单位：秒）
     *
     * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值
     */
    List<String> brPop(final String[] keys, final int timeout);

    /**
     * 移除并返回列表中一个或多个 key 的尾元素，BRPOP 是列表的阻塞式(blocking)弹出原语；
     * 当给定列表内没有任何元素可供弹出的时候，连接将被阻塞，直到等待超时或发现可弹出元素为止
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/brpop.html" target="_blank">http://redisdoc.com/list/brpop.html</a></p>
     *
     * @param keys
     *         一个或多个 key
     * @param timeout
     *         超时时间（单位：秒）
     *
     * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值
     */
    List<byte[]> brPop(final byte[][] keys, final int timeout);

    /**
     * 将列表 source 中的最后尾元素弹出，并返回；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
     * 如果 source 不存在，值 null 被返回，并且不执行其他动作；
     * 如果 source 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
     * RPOPLPUSH 是列表的阻塞式(blocking)弹出原语
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush
     * .html</a></p>
     *
     * @param source
     *         Key
     * @param destKey
     *         目标 Key
     * @param timeout
     *         超时时间（单位：秒）
     *
     * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素的值 ，第二个等待时长
     */
    String brPoplPush(final String source, final String destKey, final int timeout);

    /**
     * 将列表 source 中的最后尾元素弹出，并返回；弹出的元素插入到列表 destKey ，作为 destKey 列表的的头元素；
     * 如果 source 不存在，值 null 被返回，并且不执行其他动作；
     * 如果 source 和 destKey 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
     * RPOPLPUSH 是列表的阻塞式(blocking)弹出原语
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/rpoplpush.html" target="_blank">http://redisdoc.com/list/rpoplpush
     * .html</a></p>
     *
     * @param source
     *         Key
     * @param destKey
     *         目标 Key
     * @param timeout
     *         超时时间（单位：秒）
     *
     * @return 如果列表为空，返回一个 null；否则，返回一个含有两个元素的列表，第一个元素是被弹出元素的值 ，第二个等待时长
     */
    byte[] brPoplPush(final byte[] source, final byte[] destKey, final int timeout);

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/rpush.html" target="_blank">http://redisdoc.com/list/rpush.html</a></p>
     *
     * @param key
     *         Key
     * @param values
     *         一个或多个值
     *
     * @return 执行 RPUSH 操作后，表的长度
     */
    Long rPush(final String key, final String... values);

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/rpush.html" target="_blank">http://redisdoc.com/list/rpush.html</a></p>
     *
     * @param key
     *         Key
     * @param values
     *         一个或多个值
     *
     * @return 执行 RPUSH 操作后，表的长度
     */
    Long rPush(final byte[] key, final byte[]... values);

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/rpushx.html" target="_blank">http://redisdoc.com/list/rpushx
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param values
     *         一个或多个值
     *
     * @return 执行 RPUSHX 之后，表的长度
     */
    Long rPushX(final String key, final String... values);

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/rpushx.html" target="_blank">http://redisdoc.com/list/rpushx
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param values
     *         一个或多个值
     *
     * @return 执行 RPUSHX 之后，表的长度
     */
    Long rPushX(final byte[] key, final byte[]... values);

    /**
     * 移除列表指定区间外的元素；
     * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，或者 start &gt; stop ，则移除整个列表；
     * 如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/ltrim.html" target="_blank">http://redisdoc.com/list/ltrim.html</a></p>
     *
     * @param key
     *         Key
     * @param start
     *         开始位置
     * @param end
     *         结束位置
     *
     * @return 操作结果，成功返回 Status.Success，否则返回 Status.FAILURE
     */
    Status lTrim(final String key, final int start, final int end);

    /**
     * 移除列表指定区间外的元素；
     * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，或者 start &gt; stop ，则移除整个列表；
     * 如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/ltrim.html" target="_blank">http://redisdoc.com/list/ltrim.html</a></p>
     *
     * @param key
     *         Key
     * @param start
     *         开始位置
     * @param end
     *         结束位置
     *
     * @return 操作结果，成功返回 Status.Success，否则返回 Status.FAILURE
     */
    Status lTrim(final byte[] key, final int start, final int end);

    /**
     * 移除列表指定区间外的元素；
     * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，或者 start &gt; stop ，则移除整个列表；
     * 如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/ltrim.html" target="_blank">http://redisdoc.com/list/ltrim.html</a></p>
     *
     * @param key
     *         Key
     * @param start
     *         开始位置
     * @param end
     *         结束位置
     *
     * @return 操作结果，成功返回 Status.Success，否则返回 Status.FAILURE
     */
    Status lTrim(final String key, final long start, final long end);

    /**
     * 移除列表指定区间外的元素；
     * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，或者 start &gt; stop ，则移除整个列表；
     * 如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/ltrim.html" target="_blank">http://redisdoc.com/list/ltrim.html</a></p>
     *
     * @param key
     *         Key
     * @param start
     *         开始位置
     * @param end
     *         结束位置
     *
     * @return 操作结果，成功返回 Status.Success，否则返回 Status.FAILURE
     */
    Status lTrim(final byte[] key, final long start, final long end);

    /**
     * 移除列表中与参数 value 相等的 count 个元素元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lrem.html" target="_blank">http://redisdoc.com/list/lrem.html</a></p>
     *
     * @param key
     *         Key
     * @param value
     *         值
     * @param count
     *         移除个数
     *
     * @return 被移除元素的数量
     */
    Long lRem(final String key, final String value, final int count);

    /**
     * 移除列表中与参数 value 相等的 count 个元素元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lrem.html" target="_blank">http://redisdoc.com/list/lrem.html</a></p>
     *
     * @param key
     *         Key
     * @param value
     *         值
     * @param count
     *         移除个数
     *
     * @return 被移除元素的数量
     */
    Long lRem(final byte[] key, final byte[] value, final int count);

    /**
     * 移除列表中与参数 value 相等的 count 个元素元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lrem.html" target="_blank">http://redisdoc.com/list/lrem.html</a></p>
     *
     * @param key
     *         Key
     * @param value
     *         值
     * @param count
     *         移除个数
     *
     * @return 被移除元素的数量
     */
    Long lRem(final String key, final String value, final long count);

    /**
     * 移除列表中与参数 value 相等的 count 个元素元素
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lrem.html" target="_blank">http://redisdoc.com/list/lrem.html</a></p>
     *
     * @param key
     *         Key
     * @param value
     *         值
     * @param count
     *         移除个数
     *
     * @return 被移除元素的数量
     */
    Long lRem(final byte[] key, final byte[] value, final long count);

    /**
     * 获取列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 之间的元素（包含 start 和 stop）；
     * 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lrange.html" target="_blank">http://redisdoc.com/list/lrange
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param start
     *         开始位置
     * @param end
     *         结束位置
     *
     * @return 指定区间内的元素；
     * 如果 start 下标比列表的最大下标 ( LLEN list 减去 1 )还要大，那么返回一个空列表；
     * 如果 end 下标比最大下标还要大，那么最多返回到 end 个下标
     */
    List<String> lRange(final String key, final int start, final int end);

    /**
     * 获取列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 之间的元素（包含 start 和 stop）；
     * 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lrange.html" target="_blank">http://redisdoc.com/list/lrange
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param start
     *         开始位置
     * @param end
     *         结束位置
     *
     * @return 指定区间内的元素；
     * 如果 start 下标比列表的最大下标 ( LLEN list 减去 1 )还要大，那么返回一个空列表；
     * 如果 end 下标比最大下标还要大，那么最多返回到 end 个下标
     */
    List<byte[]> lRange(final byte[] key, final int start, final int end);

    /**
     * 获取列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 之间的元素（包含 start 和 stop）；
     * 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lrange.html" target="_blank">http://redisdoc.com/list/lrange
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param start
     *         开始位置
     * @param end
     *         结束位置
     *
     * @return 指定区间内的元素；
     * 如果 start 下标比列表的最大下标 ( LLEN list 减去 1 )还要大，那么返回一个空列表；
     * 如果 end 下标比最大下标还要大，那么最多返回到 end 个下标
     */
    List<String> lRange(final String key, final long start, final long end);

    /**
     * 获取列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 之间的元素（包含 start 和 stop）；
     * 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/lrange.html" target="_blank">http://redisdoc.com/list/lrange
     * .html</a></p>
     *
     * @param key
     *         Key
     * @param start
     *         开始位置
     * @param end
     *         结束位置
     *
     * @return 指定区间内的元素；
     * 如果 start 下标比列表的最大下标 ( LLEN list 减去 1 )还要大，那么返回一个空列表；
     * 如果 end 下标比最大下标还要大，那么最多返回到 end 个下标
     */
    List<byte[]> lRange(final byte[] key, final long start, final long end);

    /**
     * 获取列表 key 的长度
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/llen.html" target="_blank">http://redisdoc.com/list/llen.html</a></p>
     *
     * @param key
     *         Key
     *
     * @return 列表 key 的长度，如果 key 不是列表类型，则返回 -1
     */
    Long lLen(final String key);

    /**
     * 获取列表 key 的长度
     *
     * <p>详情说明 <a href="http://redisdoc.com/list/llen.html" target="_blank">http://redisdoc.com/list/llen.html</a></p>
     *
     * @param key
     *         Key
     *
     * @return 列表 key 的长度，如果 key 不是列表类型，则返回 -1
     */
    Long lLen(final byte[] key);

    enum ListPosition {

        BEFORE,

        AFTER
    }

}
