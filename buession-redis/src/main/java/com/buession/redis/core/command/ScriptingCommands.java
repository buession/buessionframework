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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

import com.buession.lang.Status;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.FunctionRestoreMode;
import com.buession.redis.core.FunctionStats;
import com.buession.redis.core.LibraryInfo;
import com.buession.redis.core.ScriptDebugMode;

import java.util.List;

/**
 * LUA 脚本命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=scripting" target="_blank">https://redis.io/docs/latest/commands/?group=scripting</a></p>
 *
 * @author Yong.Teng
 */
public interface ScriptingCommands extends RedisCommands {

	/**
	 * 对 Lua 脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval.html</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 *
	 * @return 求值结果
	 */
	Object eval(final String script);

	/**
	 * 对 Lua 脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval.html</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 *
	 * @return 求值结果
	 */
	Object eval(final byte[] script);

	/**
	 * 对 Lua 脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval.html</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 求值结果
	 */
	Object eval(final String script, final String... keys);

	/**
	 * 对 Lua 脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval.html</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 求值结果
	 */
	Object eval(final byte[] script, final byte[]... keys);

	/**
	 * 对 Lua 脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval.html</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 求值结果
	 */
	Object eval(final String script, final String[] keys, final String[] arguments);

	/**
	 * 对 Lua 脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/eval.html" target="_blank">http://redisdoc.com/script/eval.html</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 求值结果
	 */
	Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments);

	/**
	 * 以只读方式执行 Lua 脚本
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/eval_ro/" target="_blank">https://redis.io/docs/latest/commands/eval_ro/</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 *
	 * @return 求值结果
	 */
	Object evalRo(final String script);

	/**
	 * 以只读方式执行 Lua 脚本
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/eval_ro/" target="_blank">https://redis.io/docs/latest/commands/eval_ro/</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 *
	 * @return 求值结果
	 */
	Object evalRo(final byte[] script);

	/**
	 * 以只读方式执行 Lua 脚本
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/eval_ro/" target="_blank">https://redis.io/docs/latest/commands/eval_ro/</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 求值结果
	 */
	Object evalRo(final String script, final String... keys);

	/**
	 * 以只读方式执行 Lua 脚本
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/eval_ro/" target="_blank">https://redis.io/docs/latest/commands/eval_ro/</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 求值结果
	 */
	Object evalRo(final byte[] script, final byte[]... keys);

	/**
	 * 以只读方式执行 Lua 脚本
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/eval_ro/" target="_blank">https://redis.io/docs/latest/commands/eval_ro/</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 求值结果
	 */
	Object evalRo(final String script, final String[] keys, final String[] arguments);

	/**
	 * 以只读方式执行 Lua 脚本
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/eval_ro/" target="_blank">https://redis.io/docs/latest/commands/eval_ro/</a></p>
	 *
	 * @param script
	 * 		脚本程序
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 求值结果
	 */
	Object evalRo(final byte[] script, final byte[][] keys, final byte[][] arguments);

	/**
	 * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha.html</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalSha(final String digest);

	/**
	 * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha.html</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalSha(final byte[] digest);

	/**
	 * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha.html</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalSha(final String digest, final String... keys);

	/**
	 * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha.html</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalSha(final byte[] digest, final byte[]... keys);

	/**
	 * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha.html</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalSha(final String digest, final String[] keys, final String[] arguments);

	/**
	 * 根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/evalsha.html" target="_blank">http://redisdoc.com/script/evalsha.html</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments);

	/**
	 * 以只读方式根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/evalsha_ro/" target="_blank">https://redis.io/docs/latest/commands/evalsha_ro/</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalShaRo(final String digest);

	/**
	 * 以只读方式根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/evalsha_ro/" target="_blank">https://redis.io/docs/latest/commands/evalsha_ro/</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalShaRo(final byte[] digest);

	/**
	 * 以只读方式根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/evalsha_ro/" target="_blank">https://redis.io/docs/latest/commands/evalsha_ro/</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalShaRo(final String digest, final String... keys);

	/**
	 * 以只读方式根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/evalsha_ro/" target="_blank">https://redis.io/docs/latest/commands/evalsha_ro/</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalShaRo(final byte[] digest, final byte[]... keys);

	/**
	 * 以只读方式根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/evalsha_ro/" target="_blank">https://redis.io/docs/latest/commands/evalsha_ro/</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalShaRo(final String digest, final String[] keys, final String[] arguments);

	/**
	 * 以只读方式根据给定的 SHA1 校验码，对缓存在服务器中的脚本进行求值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/evalsha_ro/" target="_blank">https://redis.io/docs/latest/commands/evalsha_ro/</a></p>
	 *
	 * @param digest
	 * 		SHA1 校验码
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 根据 SHA1 校验码，对脚本的求值结果
	 */
	Object evalShaRo(final byte[] digest, final byte[][] keys, final byte[][] arguments);

	/**
	 * 调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall/" target="_blank">https://redis.io/docs/latest/commands/fcall/</a></p>
	 *
	 * @param function
	 * 		函数名
	 *
	 * @return 函数执行结果
	 */
	Object fCall(final String function);

	/**
	 * 调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall/" target="_blank">https://redis.io/docs/latest/commands/fcall/</a></p>
	 *
	 * @param function
	 * 		函数名
	 *
	 * @return 函数执行结果
	 */
	Object fCall(final byte[] function);

	/**
	 * 调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall/" target="_blank">https://redis.io/docs/latest/commands/fcall/</a></p>
	 *
	 * @param function
	 * 		函数名
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 函数执行结果
	 */
	Object fCall(final String function, final String... keys);

	/**
	 * 调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall/" target="_blank">https://redis.io/docs/latest/commands/fcall/</a></p>
	 *
	 * @param function
	 * 		函数名
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 函数执行结果
	 */
	Object fCall(final byte[] function, final byte[]... keys);

	/**
	 * 调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall/" target="_blank">https://redis.io/docs/latest/commands/fcall/</a></p>
	 *
	 * @param function
	 * 		函数名
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 函数执行结果
	 */
	Object fCall(final String function, final String[] keys, final String[] arguments);

	/**
	 * 调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall/" target="_blank">https://redis.io/docs/latest/commands/fcall/</a></p>
	 *
	 * @param function
	 * 		函数名
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 函数执行结果
	 */
	Object fCall(final byte[] function, final byte[][] keys, final byte[][] arguments);

	/**
	 * 以只读方式调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall_ro/" target="_blank">https://redis.io/docs/latest/commands/fcall_ro/</a></p>
	 *
	 * @param function
	 * 		函数名
	 *
	 * @return 函数执行结果
	 */
	Object fCallRo(final String function);

	/**
	 * 以只读方式调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall_ro/" target="_blank">https://redis.io/docs/latest/commands/fcall_ro/</a></p>
	 *
	 * @param function
	 * 		函数名
	 *
	 * @return 函数执行结果
	 */
	Object fCallRo(final byte[] function);

	/**
	 * 以只读方式调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall_ro/" target="_blank">https://redis.io/docs/latest/commands/fcall_ro/</a></p>
	 *
	 * @param function
	 * 		函数名
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 函数执行结果
	 */
	Object fCallRo(final String function, final String... keys);

	/**
	 * 以只读方式调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall_ro/" target="_blank">hhttps://redis.io/docs/latest/commands/fcall_ro/</a></p>
	 *
	 * @param function
	 * 		函数名
	 * @param keys
	 * 		一个或多个键名参数
	 *
	 * @return 函数执行结果
	 */
	Object fCallRo(final byte[] function, final byte[]... keys);

	/**
	 * 以只读方式调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall_ro/" target="_blank">https://redis.io/docs/latest/commands/fcall_ro/</a></p>
	 *
	 * @param function
	 * 		函数名
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 函数执行结果
	 */
	Object fCallRo(final String function, final String[] keys, final String[] arguments);

	/**
	 * 以只读方式调用 Redis Functions（函数）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/fcall_ro/" target="_blank">https://redis.io/docs/latest/commands/fcall_ro/</a></p>
	 *
	 * @param function
	 * 		函数名
	 * @param keys
	 * 		一个或多个键名参数
	 * @param arguments
	 * 		一个或多个键参数
	 *
	 * @return 函数执行结果
	 */
	Object fCallRo(final byte[] function, final byte[][] keys, final byte[][] arguments);

	/**
	 * 删除一个已加载的 Redis Functions 函数库
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-delete/" target="_blank">https://redis.io/docs/latest/commands/function-delete/</a></p>
	 *
	 * @param libraryName
	 * 		库名称
	 *
	 * @return 操作结果
	 */
	Status functionDelete(final String libraryName);

	/**
	 * Return the serialized payload of loaded libraries. You can restore the serialized payload later with the FUNCTION RESTORE command.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-dump/" target="_blank">https://redis.io/docs/latest/commands/function-dump/</a></p>
	 *
	 * @return The serialized payload of loaded libraries. You can restore the serialized payload later with the FUNCTION RESTORE command.
	 */
	byte[] functionDump();

	/**
	 * 清空当前 Redis 实例中所有已加载的 Functions
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-flush/" target="_blank">https://redis.io/docs/latest/commands/function-flush/</a></p>
	 *
	 * @return 操作结果
	 */
	Status functionFlush();

	/**
	 * 清空当前 Redis 实例中所有已加载的 Functions
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-flush/" target="_blank">https://redis.io/docs/latest/commands/function-flush/</a></p>
	 *
	 * @param flushMode
	 * 		刷新方式
	 *
	 * @return 操作结果
	 */
	Status functionFlush(final FlushMode flushMode);

	/**
	 * Kill a function that is currently executing.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-kill/" target="_blank">https://redis.io/docs/latest/commands/function-kill/</a></p>
	 *
	 * @return 操作结果
	 */
	Status functionKill();

	/**
	 * 列出当前 Redis 实例中所有已加载的 Functions（函数库）及其元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-list/" target="_blank">https://redis.io/docs/latest/commands/function-list/</a></p>
	 *
	 * @return 已加载的 Functions（函数库）及其元信息
	 */
	List<LibraryInfo> functionList();

	/**
	 * 列出当前 Redis 实例中所有已加载的 Functions（函数库）及其元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-list/" target="_blank">https://redis.io/docs/latest/commands/function-list/</a></p>
	 *
	 * @param pattern
	 * 		匹配模式
	 *
	 * @return 已加载的 Functions（函数库）及其元信息
	 */
	List<LibraryInfo> functionList(final String pattern);

	/**
	 * 列出当前 Redis 实例中所有已加载的 Functions（函数库）及其元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-list/" target="_blank">https://redis.io/docs/latest/commands/function-list/</a></p>
	 *
	 * @param pattern
	 * 		匹配模式
	 *
	 * @return 已加载的 Functions（函数库）及其元信息
	 */
	List<LibraryInfo> functionList(final byte[] pattern);

	/**
	 * 列出当前 Redis 实例中所有已加载的 Functions（函数库）及其元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-list/" target="_blank">https://redis.io/docs/latest/commands/function-list/</a></p>
	 *
	 * @param withCode
	 * 		WITHCODE
	 *
	 * @return 已加载的 Functions（函数库）及其元信息
	 */
	List<LibraryInfo> functionList(final boolean withCode);

	/**
	 * 列出当前 Redis 实例中所有已加载的 Functions（函数库）及其元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-list/" target="_blank">https://redis.io/docs/latest/commands/function-list/</a></p>
	 *
	 * @param pattern
	 * 		匹配模式
	 * @param withCode
	 * 		WITHCODE
	 *
	 * @return 已加载的 Functions（函数库）及其元信息
	 */
	List<LibraryInfo> functionList(final String pattern, final boolean withCode);

	/**
	 * 列出当前 Redis 实例中所有已加载的 Functions（函数库）及其元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-list/" target="_blank">https://redis.io/docs/latest/commands/function-list/</a></p>
	 *
	 * @param pattern
	 * 		匹配模式
	 * @param withCode
	 * 		WITHCODE
	 *
	 * @return 已加载的 Functions（函数库）及其元信息
	 */
	List<LibraryInfo> functionList(final byte[] pattern, final boolean withCode);

	/**
	 * 向 Redis 实例加载一个 Functions（函数库）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-load/" target="_blank">https://redis.io/docs/latest/commands/function-load/</a></p>
	 *
	 * @param functionCode
	 * 		函数库名
	 *
	 * @return 成功加载后返回库名
	 */
	String functionLoad(final String functionCode);

	/**
	 * 向 Redis 实例加载一个 Functions（函数库）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-load/" target="_blank">https://redis.io/docs/latest/commands/function-load/</a></p>
	 *
	 * @param functionCode
	 * 		函数库名
	 * @param replace
	 * 		是否替换同名库
	 *
	 * @return 成功加载后返回库名
	 */
	String functionLoad(final String functionCode, final boolean replace);

	/**
	 * Restore libraries from the serialized payload.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-restore/" target="_blank">https://redis.io/docs/latest/commands/function-restore/</a></p>
	 *
	 * @param value
	 * 		值
	 *
	 * @return 操作结果
	 */
	Status functionRestore(final byte[] value);

	/**
	 * Restore libraries from the serialized payload.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-restore/" target="_blank">https://redis.io/docs/latest/commands/function-restore/</a></p>
	 *
	 * @param value
	 * 		值
	 * @param mode
	 * 		存储模式
	 *
	 * @return 操作结果
	 */
	Status functionRestore(final byte[] value, final FunctionRestoreMode mode);

	/**
	 * Return information about the function that's currently running and information about the available execution engines.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/function-stats/" target="_blank">https://redis.io/docs/latest/commands/function-stats/</a></p>
	 *
	 * @return The information about the function that's currently running and information about the available
	 * execution engines.
	 */
	FunctionStats functionStats();

	/**
	 * 用于调试 Lua 脚本的管理命令，允许你控制 Lua 脚本的执行模式（同步、异步、关闭调试）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/script-debug/" target="_blank">https://redis.io/docs/latest/commands/script-debug/</a></p>
	 *
	 * @return -
	 */
	Object scriptDebug();

	/**
	 * 用于调试 Lua 脚本的管理命令，允许你控制 Lua 脚本的执行模式（同步、异步、关闭调试）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/script-debug/" target="_blank">https://redis.io/docs/latest/commands/script-debug/</a></p>
	 *
	 * @param mode
	 * 		调试模式
	 *
	 * @return -
	 */
	Object scriptDebug(final ScriptDebugMode mode);

	/**
	 * 根据一个或多个脚本的 SHA1 校验和，检测校验和所指定的脚本是否已经被保存在缓存当中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/script_exists.html" target="_blank">http://redisdoc.com/script/script_exists.html</a></p>
	 *
	 * @param sha1
	 * 		一个或多个 SHA1 校验和
	 *
	 * @return 返回一个包含布尔值的列表，true 表示脚本已经在缓存里面了；false 表示脚本不存在于缓存
	 */
	List<Boolean> scriptExists(final String... sha1);

	/**
	 * 根据一个或多个脚本的 SHA1 校验和，检测校验和所指定的脚本是否已经被保存在缓存当中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/script_exists.html" target="_blank">http://redisdoc.com/script/script_exists.html</a></p>
	 *
	 * @param sha1
	 * 		一个或多个 SHA1 校验和
	 *
	 * @return 返回一个包含布尔值的列表，true 表示脚本已经在缓存里面了；false 表示脚本不存在于缓存
	 */
	List<Boolean> scriptExists(final byte[]... sha1);

	/**
	 * 清除所有 Lua 脚本缓存
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/script_flush.html" target="_blank">http://redisdoc.com/script/script_flush.html</a></p>
	 *
	 * @return 操作成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status scriptFlush();

	/**
	 * 清除所有 Lua 脚本缓存
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/script_flush.html" target="_blank">http://redisdoc.com/script/script_flush.html</a></p>
	 *
	 * @param mode
	 * 		刷新模式
	 *
	 * @return 操作成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status scriptFlush(final FlushMode mode);

	/**
	 * 杀死当前正在运行的 Lua 脚本，当且仅当这个脚本没有执行过任何写操作时，这个命令才生效
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/script_kill.html" target="_blank">http://redisdoc.com/script/script_kill.html</a></p>
	 *
	 * @return 操作成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status scriptKill();

	/**
	 * 将脚本 script 添加到脚本缓存中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/script_load.html" target="_blank">http://redisdoc.com/script/script_load.html</a></p>
	 *
	 * @param script
	 * 		脚本
	 *
	 * @return script 的 SHA1 校验和
	 */
	String scriptLoad(final String script);

	/**
	 * 将脚本 script 添加到脚本缓存中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/script/script_load.html" target="_blank">http://redisdoc.com/script/script_load.html</a></p>
	 *
	 * @param script
	 * 		脚本
	 *
	 * @return script 的 SHA1 校验和
	 */
	byte[] scriptLoad(final byte[] script);

}
