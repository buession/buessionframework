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

import java.util.List;

/**
 * LUA 脚本命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/script/index.html" target="_blank">http://redisdoc.com/script/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface LuaCommands extends RedisCommands {

    /**
     * 对 Lua 脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval
     * .html</a></p>
     *
     * @param script
     *         脚本程序
     *
     * @return 求值结果
     */
    Object eval(final String script);

    /**
     * 对 Lua 脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval
     * .html</a></p>
     *
     * @param script
     *         脚本程序
     *
     * @return 求值结果
     */
    Object eval(final byte[] script);

    /**
     * 对 Lua 脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval
     * .html</a></p>
     *
     * @param script
     *         脚本程序
     * @param params
     *         一个或多个键名参数
     *
     * @return 求值结果
     */
    Object eval(final String script, final String... params);

    /**
     * 对 Lua 脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval
     * .html</a></p>
     *
     * @param script
     *         脚本程序
     * @param params
     *         一个或多个键名参数
     *
     * @return 求值结果
     */
    Object eval(final byte[] script, final byte[]... params);

    /**
     * 对 Lua 脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval
     * .html</a></p>
     *
     * @param script
     *         脚本程序
     * @param keys
     *         一个或多个键名参数
     * @param args
     *         一个或多个键参数
     *
     * @return 求值结果
     */
    Object eval(final String script, final String[] keys, final String[] args);

    /**
     * 对 Lua 脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval
     * .html</a></p>
     *
     * @param script
     *         脚本程序
     * @param keys
     *         一个或多个键名参数
     * @param args
     *         一个或多个键参数
     *
     * @return 求值结果
     */
    Object eval(final byte[] script, final byte[][] keys, final byte[][] args);

    /**
     * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha
     * .html</a></p>
     *
     * @param digest
     *         SHA1 校验码
     *
     * @return 根据 SHA1 校验码，对脚本的求值结果
     */
    Object evalSha(final String digest);

    /**
     * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha
     * .html</a></p>
     *
     * @param digest
     *         SHA1 校验码
     *
     * @return 根据 SHA1 校验码，对脚本的求值结果
     */
    Object evalSha(final byte[] digest);

    /**
     * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha
     * .html</a></p>
     *
     * @param digest
     *         SHA1 校验码
     * @param params
     *         一个或多个键名参数
     *
     * @return 根据 SHA1 校验码，对脚本的求值结果
     */
    Object evalSha(final String digest, final String... params);

    /**
     * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha
     * .html</a></p>
     *
     * @param digest
     *         SHA1 校验码
     * @param params
     *         一个或多个键名参数
     *
     * @return 根据 SHA1 校验码，对脚本的求值结果
     */
    Object evalSha(final byte[] digest, final byte[]... params);

    /**
     * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha
     * .html</a></p>
     *
     * @param digest
     *         SHA1 校验码
     * @param keys
     *         一个或多个键名参数
     * @param args
     *         一个或多个键参数
     *
     * @return 根据 SHA1 校验码，对脚本的求值结果
     */
    Object evalSha(final String digest, final String[] keys, final String[] args);

    /**
     * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha
     * .html</a></p>
     *
     * @param digest
     *         SHA1 校验码
     * @param keys
     *         一个或多个键名参数
     * @param args
     *         一个或多个键参数
     *
     * @return 根据 SHA1 校验码，对脚本的求值结果
     */
    Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] args);

    /**
     * 根据一个或多个脚本的 SHA1 校验和，检测校验和所指定的脚本是否已经被保存在缓存当中
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/script_exists.html" target="_blank">http://redisdoc
     * .com/script/script_exists.html</a></p>
     *
     * @param sha1
     *         一个或多个 SHA1 校验和
     *
     * @return 返回一个包含布尔值的列表，true 表示脚本已经在缓存里面了；false 表示脚本不存在于缓存
     */
    List<Boolean> scriptExists(final String... sha1);

    /**
     * 根据一个或多个脚本的 SHA1 校验和，检测校验和所指定的脚本是否已经被保存在缓存当中
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/script_exists.html" target="_blank">http://redisdoc
     * .com/script/script_exists.html</a></p>
     *
     * @param sha1
     *         一个或多个 SHA1 校验和
     *
     * @return 返回一个包含布尔值的列表，true 表示脚本已经在缓存里面了；false 表示脚本不存在于缓存
     */
    List<Boolean> scriptExists(final byte[]... sha1);

    /**
     * 将脚本 script 添加到脚本缓存中
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/script_load.html" target="_blank">http://redisdoc
     * .com/script/script_load.html</a></p>
     *
     * @param script
     *         脚本
     *
     * @return script 的 SHA1 校验和
     */
    String scriptLoad(final String script);

    /**
     * 将脚本 script 添加到脚本缓存中
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/script_load.html" target="_blank">http://redisdoc
     * .com/script/script_load.html</a></p>
     *
     * @param script
     *         脚本
     *
     * @return script 的 SHA1 校验和
     */
    byte[] scriptLoad(final byte[] script);

    /**
     * 杀死当前正在运行的 Lua 脚本，当且仅当这个脚本没有执行过任何写操作时，这个命令才生效
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/script_kill.html" target="_blank">http://redisdoc
     * .com/script/script_kill.html</a></p>
     *
     * @return 操作成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status scriptKill();

    /**
     * 清除所有 Lua 脚本缓存
     *
     * <p>详情说明 <a href="http://redisdoc.com/script/script_flush.html" target="_blank">http://redisdoc
     * .com/script/script_flush.html</a></p>
     *
     * @return 操作成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
     */
    Status scriptFlush();

}
