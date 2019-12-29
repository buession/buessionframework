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
 * 数据库命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/database/index.html" target="_blank">http://redisdoc.com/database/index
 * .html</a></p>
 *
 * @author Yong.Teng
 */
public interface DatabaseCommand extends RedisCommands {

    /**
     * 切换到指定的数据库
     *
     * <p>详情说明 <a href="http://redisdoc.com/database/select.html" target="_blank">http://redisdoc.com/database/select
     * .html</a></p>
     *
     * @param db
     *         数据库索引号
     *
     * @return 切换成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status select(final int db);

    /**
     * 对换指定的两个数据库， 使得两个数据库的数据立即互换
     *
     * <p>详情说明 <a href="http://redisdoc.com/database/swapdb.html" target="_blank">http://redisdoc.com/database/swapdb
     * .html</a></p>
     *
     * @param db1
     *         数据库 1 索引号
     * @param db2
     *         数据库 2 索引号
     *
     * @return 对换成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status swapdb(final int db1, final int db2);

    /**
     * 获取数据库的 key 的数量
     *
     * <p>详情说明 <a href="http://redisdoc.com/database/dbsize.html" target="_blank">http://redisdoc.com/database/dbsize
     * .html</a></p>
     *
     * @return 数据库的 key 的数量
     */
    Long dbSize();

    /**
     * 清空当前数据库中的所有 key
     *
     * <p>详情说明 <a href="http://redisdoc.com/database/flushdb.html" target="_blank">http://redisdoc
     * .com/database/flushdb.html</a></p>
     *
     * @return 始终返回 Status.SUCCESS
     */
    Status flushDb();

    /**
     * 清空整个 Redis 服务器的数据（删除所有数据库的所有 key ）
     *
     * <p>详情说明 <a href="http://redisdoc.com/database/flushall.html" target="_blank">http://redisdoc
     * .com/database/flushall.html</a></p>
     *
     * @return 始终返回 Status.SUCCESS
     */
    Status flushAll();

}
