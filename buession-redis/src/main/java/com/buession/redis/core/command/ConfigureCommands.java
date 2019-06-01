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

import java.util.List;

/**
 * 配置选项命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/configure/index.html" target="_blank">http://redisdoc.com/configure/index
 * .html</a></p>
 *
 * @author Yong.Teng
 */
public interface ConfigureCommands extends RedisCommands {

    /**
     * 动态地调整 Redis 服务器的配置
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
     * .com/configure/config_set.html</a></p>
     *
     * @param parameter
     *         配置项
     * @param value
     *         配置值
     *
     * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status configSet(final String parameter, final float value);

    /**
     * 动态地调整 Redis 服务器的配置
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
     * .com/configure/config_set.html</a></p>
     *
     * @param parameter
     *         配置项
     * @param value
     *         配置值
     *
     * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status configSet(final byte[] parameter, final float value);

    /**
     * 动态地调整 Redis 服务器的配置
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
     * .com/configure/config_set.html</a></p>
     *
     * @param parameter
     *         配置项
     * @param value
     *         配置值
     *
     * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status configSet(final String parameter, final double value);

    /**
     * 动态地调整 Redis 服务器的配置
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
     * .com/configure/config_set.html</a></p>
     *
     * @param parameter
     *         配置项
     * @param value
     *         配置值
     *
     * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status configSet(final byte[] parameter, final double value);

    /**
     * 动态地调整 Redis 服务器的配置
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
     * .com/configure/config_set.html</a></p>
     *
     * @param parameter
     *         配置项
     * @param value
     *         配置值
     *
     * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status configSet(final String parameter, final int value);

    /**
     * 动态地调整 Redis 服务器的配置
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
     * .com/configure/config_set.html</a></p>
     *
     * @param parameter
     *         配置项
     * @param value
     *         配置值
     *
     * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status configSet(final byte[] parameter, final int value);

    /**
     * 动态地调整 Redis 服务器的配置
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
     * .com/configure/config_set.html</a></p>
     *
     * @param parameter
     *         配置项
     * @param value
     *         配置值
     *
     * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status configSet(final String parameter, final long value);

    /**
     * 动态地调整 Redis 服务器的配置
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
     * .com/configure/config_set.html</a></p>
     *
     * @param parameter
     *         配置项
     * @param value
     *         配置值
     *
     * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status configSet(final byte[] parameter, final long value);

    /**
     * 动态地调整 Redis 服务器的配置
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
     * .com/configure/config_set.html</a></p>
     *
     * @param parameter
     *         配置项
     * @param value
     *         配置值
     *
     * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status configSet(final String parameter, final String value);

    /**
     * 动态地调整 Redis 服务器的配置
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
     * .com/configure/config_set.html</a></p>
     *
     * @param parameter
     *         配置项
     * @param value
     *         配置值
     *
     * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status configSet(final byte[] parameter, final byte[] value);

    /**
     * 获取 Redis 服务器的配置参数
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_get.html" target="_blank">http://redisdoc
     * .com/configure/config_get.html</a></p>
     *
     * @param parameter
     *         配置项
     *
     * @return 给定配置参数的值
     */
    List<String> configGet(final String parameter);

    /**
     * 获取 Redis 服务器的配置参数
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_get.html" target="_blank">http://redisdoc
     * .com/configure/config_get.html</a></p>
     *
     * @param parameter
     *         配置项
     *
     * @return 给定配置参数的值
     */
    List<byte[]> configGet(final byte[] parameter);

    /**
     * 重置 INFO 命令中的某些统计数据，包括：
     * 1）Keyspace hits (键空间命中次数)
     * 2）Keyspace misses (键空间不命中次数)
     * 3）Number of commands processed (执行命令的次数)
     * 4）Number of connections received (连接服务器的次数)
     * 5）Number of expired keys (过期key的数量)
     * 6）Number of rejected connections (被拒绝的连接数量)
     * 7）Latest fork(2) time(最后执行 fork(2) 的时间)
     * 8）The aof_delayed_fsync counter(aof_delayed_fsync 计数器的值)
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_resetstat.html" target="_blank">http://redisdoc
     * .com/configure/config_resetstat.html</a></p>
     *
     * @return 总是返回 Status.SUCCESS
     */
    Status configResetStat();

    /**
     * 对启动 Redis 服务器时所指定的 redis.conf 文件进行改写
     *
     * <p>详情说明 <a href="http://redisdoc.com/configure/config_rewrite.html" target="_blank">http://redisdoc
     * .com/configure/config_rewrite.html</a></p>
     *
     * @return 如果配置重写成功则，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status configRewrite();

}
