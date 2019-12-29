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

import com.buession.lang.Status;

/**
 * HyperLogLog 命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/hyperloglog/index.html" target="_blank">http://redisdoc.com/hyperloglog/index
 * .html</a></p>
 *
 * @author Yong.Teng
 */
public interface HyperLogLogCommands extends RedisCommands {

    /**
     * 将任意数量的元素添加到指定的 HyperLogLog 里面
     *
     * <p>详情说明 <a href="http://redisdoc.com/hyperloglog/pfadd.html" target="_blank">http://redisdoc
     * .com/hyperloglog/pfadd.html</a></p>
     *
     * @param key
     *         Key
     * @param elements
     *         一个或多个元素
     *
     * @return 操作结果，如果 HyperLogLog 的内部储存被修改了，返回 Status.SUCCESS；否则返回 Status.FAILURE
     */
    Status pfAdd(final String key, final String... elements);

    /**
     * 将任意数量的元素添加到指定的 HyperLogLog 里面
     *
     * <p>详情说明 <a href="http://redisdoc.com/hyperloglog/pfadd.html" target="_blank">http://redisdoc
     * .com/hyperloglog/pfadd.html</a></p>
     *
     * @param key
     *         Key
     * @param elements
     *         一个或多个元素
     *
     * @return 操作结果，如果 HyperLogLog 的内部储存被修改了，返回 Status.SUCCESS；否则返回 Status.FAILURE
     */
    Status pfAdd(final byte[] key, final byte[]... elements);

    /**
     * 将多个 HyperLogLog 合并（merge）为一个 HyperLogLog，并保存到 destKey 中
     *
     * <p>详情说明 <a href="http://redisdoc.com/hyperloglog/pfmerge.html" target="_blank">http://redisdoc
     * .com/hyperloglog/pfmerge.html</a></p>
     *
     * @param destKey
     *         目标 Key
     * @param keys
     *         一个或多个待合并的 Key
     *
     * @return 操作结果
     */
    Status pfMerge(final String destKey, final String... keys);

    /**
     * 将多个 HyperLogLog 合并（merge）为一个 HyperLogLog，并保存到 destKey 中
     *
     * <p>详情说明 <a href="http://redisdoc.com/hyperloglog/pfmerge.html" target="_blank">http://redisdoc
     * .com/hyperloglog/pfmerge.html</a></p>
     *
     * @param destKey
     *         目标 Key
     * @param keys
     *         一个或多个待合并的 Key
     *
     * @return 操作结果
     */
    Status pfMerge(final byte[] destKey, final byte[]... keys);

    /**
     * 获取所有给定 HyperLogLog 的并集的近似基数，这个近似基数是通过将所有给定 HyperLogLog 合并至一个临时 HyperLogLog 来计算得出的
     *
     * <p>详情说明 <a href="http://redisdoc.com/hyperloglog/pfmerge.html" target="_blank">http://redisdoc
     * .com/hyperloglog/pfmerge.html</a></p>
     *
     * @param keys
     *         一个或多个 Key
     *
     * @return 所有给定 HyperLogLog 的并集的近似基数
     */
    Long pfCount(final String... keys);

    /**
     * 获取所有给定 HyperLogLog 的并集的近似基数，这个近似基数是通过将所有给定 HyperLogLog 合并至一个临时 HyperLogLog 来计算得出的
     *
     * <p>详情说明 <a href="http://redisdoc.com/hyperloglog/pfmerge.html" target="_blank">http://redisdoc
     * .com/hyperloglog/pfmerge.html</a></p>
     *
     * @param keys
     *         一个或多个 Key
     *
     * @return 所有给定 HyperLogLog 的并集的近似基数
     */
    Long pfCount(final byte[]... keys);

}
