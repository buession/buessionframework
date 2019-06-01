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
package com.buession.redis.core.operations;

import com.buession.redis.core.command.LuaCommands;

/**
 * LUA 脚本运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/script/index.html" target="_blank">http://redisdoc.com/script/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface LuaOperations extends LuaCommands, RedisOperations {

    /**
     * 对 Lua 脚本进行求值
     *
     * @param script
     *         脚本程序
     * @param params
     *         键名参数
     *
     * @return 求值结果
     */
    Object eval(final String script, final String params);

    /**
     * 对 Lua 脚本进行求值
     *
     * @param script
     *         脚本程序
     * @param param
     *         键名参数
     *
     * @return 求值结果
     */
    Object eval(final byte[] script, final byte[] param);

    /**
     * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
     *
     * @param digest
     *         SHA1 校验码
     * @param param
     *         键名参数
     *
     * @return 根据 SHA1 校验码，对脚本的求值结果
     */
    Object evalSha(final String digest, final String param);

    /**
     * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
     *
     * @param digest
     *         SHA1 校验码
     * @param param
     *         键名参数
     *
     * @return 根据 SHA1 校验码，对脚本的求值结果
     */
    Object evalSha(final byte[] digest, final byte[] param);

    /**
     * 根据 SHA1 校验和，检测校验和所指定的脚本是否已经被保存在缓存当中
     *
     * @param sha1
     *         SHA1 校验和
     *
     * @return 返回一个包含布尔值，true 表示脚本已经在缓存里面了；false 表示脚本不存在于缓存
     */
    Boolean scriptExists(final String sha1);

    /**
     * 根据 SHA1 校验和，检测校验和所指定的脚本是否已经被保存在缓存当中
     *
     * @param sha1
     *         SHA1 校验和
     *
     * @return 返回一个包含布尔值，true 表示脚本已经在缓存里面了；false 表示脚本不存在于缓存
     */
    Boolean scriptExists(final byte[] sha1);

}
