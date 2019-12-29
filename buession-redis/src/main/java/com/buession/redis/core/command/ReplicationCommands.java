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
import com.buession.redis.core.RoleInfo;

/**
 * 复制命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/replication/index.html" target="_blank">http://redisdoc.com/replication/index
 * .html</a></p>
 *
 * @author Yong.Teng
 */
public interface ReplicationCommands extends RedisCommands {

    /**
     * 用于在 Redis 运行时动态地修改复制(replication)功能的行为；
     * 可以将当前服务器转变为指定服务器的从属服务器(slave server)
     *
     * <p>详情说明 <a href="http://redisdoc.com/replication/slaveof.html" target="_blank">http://redisdoc
     * .com/replication/slaveof.html</a></p>
     *
     * @param host
     *         Redis Slave Server 主机地址
     * @param port
     *         Redis Slave Server 端口
     *
     * @return 总是返回 Status.SUCCESS
     */
    Status slaveOf(final String host, final int port);

    /**
     * 获取实例在复制中担任的角色信息
     *
     * <p>详情说明 <a href="http://redisdoc.com/replication/role.html" target="_blank">http://redisdoc
     * .com/replication/role.html</a></p>
     *
     * @return 实例在复制中担任的角色信息
     */
    RoleInfo role();

}
