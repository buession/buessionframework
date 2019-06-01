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
import com.buession.redis.core.Client;
import com.buession.redis.core.Info;
import com.buession.redis.core.RedisServerTime;

import java.util.List;

/**
 * 客户端与服务器命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/index.html" target="_blank">http://redisdoc
 * .com/client_and_server/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface ClientAndServerCommands extends RedisCommands {

    /**
     * 通过密码进行认证
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/auth.html" target="_blank">http://redisdoc
     * .com/client_and_server/auth.html</a></p>
     *
     * @param password
     *         密码
     *
     * @return 密码匹配时返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status auth(final String password);

    /**
     * 通过密码进行认证
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/auth.html" target="_blank">http://redisdoc
     * .com/client_and_server/auth.html</a></p>
     *
     * @param password
     *         密码
     *
     * @return 密码匹配时返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status auth(final byte[] password);

    /**
     * 获取关于 Redis 服务器通过 section 指定的部分信息
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/info.html" target="_blank">http://redisdoc
     * .com/client_and_server/info.html</a></p>
     *
     * @param section
     *         InfoSection
     *
     * @return 关于 Redis 服务器的各种信息和统计数值
     */
    Info info(InfoSection section);

    /**
     * 获取关于 Redis 服务器的各种信息和统计数值
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/info.html" target="_blank">http://redisdoc
     * .com/client_and_server/info.html</a></p>
     *
     * @return 关于 Redis 服务器的各种信息和统计数值
     */
    Info info();

    /**
     * 获取当前服务器时间
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/time.html" target="_blank">http://redisdoc
     * .com/client_and_server/time.html</a></p>
     *
     * @return 当前服务器时间
     */
    RedisServerTime time();

    /**
     * 为当前连接分配一个名字
     *
     * @param name
     *         名字
     *
     * @return 设置成功时返回 Status.SUCCESS；否则，返回 Status.FAILURE
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_setname.html" target="_blank">http://redisdoc
     * .com/client_and_server/client_setname.html</a></p>
     */
    Status clientSetName(final String name);

    /**
     * 为当前连接分配一个名字
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_setname.html" target="_blank">http://redisdoc
     * .com/client_and_server/client_setname.html</a></p>
     *
     * @param name
     *         名字
     *
     * @return 设置成功时返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status clientSetName(final byte[] name);

    /**
     * 获取连接时设置的名字
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_getname.html" target="_blank">http://redisdoc
     * .com/client_and_server/client_getname.html</a></p>
     *
     * @return 连接时设置的名字
     */
    String clientGetName();

    /**
     * 获取所有连接到服务器的客户端信息和统计数据
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_list.html" target="_blank">http://redisdoc
     * .com/client_and_server/client_list.html</a></p>
     *
     * @return 所有连接到服务器的客户端信息和统计数据
     */
    List<Client> clientList();

    /**
     * 关闭地址为 host:port 的客户端
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_kill.html" target="_blank">http://redisdoc
     * .com/client_and_server/client_kill.html</a></p>
     *
     * @param host
     *         客户端地址
     * @param port
     *         客户端端口
     *
     * @return 当指定的客户端存在，且被成功关闭时，返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status clientKill(final String host, final int port);

    /**
     * 请求服务器关闭与当前客户端的连接
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/quit.html" target="_blank">http://redisdoc
     * .com/client_and_server/quit.html</a></p>
     *
     * @return 总是返回 Status.SUCCESS
     */
    Status quit();

    /**
     * SHUTDOWN 命令执行以下操作：
     * 1）停止所有客户端
     * 2）如果有至少一个保存点在等待，执行 SAVE 命令
     * 3）如果 AOF 选项被打开，更新 AOF 文件
     * 4）关闭 redis 服务器
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/shutdown.html" target="_blank">http://redisdoc
     * .com/client_and_server/shutdown.html</a></p>
     */
    void shutdown();

    /**
     * SHUTDOWN 命令执行以下操作：
     * 1）停止所有客户端
     * 2）如果有至少一个保存点在等待，执行 SAVE 命令
     * 3）如果 AOF 选项被打开，更新 AOF 文件
     * 4）关闭 redis 服务器(server)
     *
     * <p>详情说明 <a href="http://redisdoc.com/client_and_server/shutdown.html" target="_blank">http://redisdoc
     * .com/client_and_server/shutdown.html</a></p>
     *
     * @param save
     *         是否强制让数据库执行保存操作
     */
    void shutdown(final boolean save);

    enum InfoSection {
        SERVER,

        CLIENTS,

        MEMORY,

        CPU,

        PERSISTENCE,

        STATS,

        REPLICATION,

        CLUSTER,

        COMMAND_STATS,

        KEYSPACE
    }

}
