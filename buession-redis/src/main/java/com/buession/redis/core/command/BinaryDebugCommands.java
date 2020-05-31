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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

/**
 * 调试命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/debug/index.html" target="_blank">http://redisdoc.com/debug/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface BinaryDebugCommands extends BinaryRedisCommands {

	/**
	 * 打印一个特定的字符串
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/debug/echo.html" target="_blank">http://redisdoc.com/debug/echo.html</a>
	 * </p>
	 *
	 * @param str
	 * 		待打印的字符串
	 *
	 * @return 字符串本身
	 */
	byte[] echo(final byte[] str);

	/**
	 * 命令允许从内部察看给定 key 的 Redis 对象，它通常用在除错(debugging)
	 * 或者了解为了节省空间而对 key 使用特殊编码的情况；
	 * 当将Redis用作缓存程序时，你也可以通过 OBJECT 命令中的信息，决定 key 的驱逐策略(eviction policies)
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/debug/object.html" target="_blank">http://redisdoc.com/debug/object.html</a>
	 * </p>
	 *
	 * @param command
	 * 		子命令
	 * @param key
	 * 		Key
	 *
	 * @return 当 objectCommand 为 ObjectCommand.REFCOUNT 时，返回给定 key 引用所储存的值的次数
	 * 当 objectCommand 为 ObjectCommand.ENCODING 时，返回给定 key 锁储存的值所使用的内部表示
	 * 当 objectCommand 为 ObjectCommand.IDLETIME 时，自储存以来的空闲时间（单位：秒）
	 */
	Object object(final DebugCommands.ObjectCommand command, final byte[] key);

	/**
	 * 查询执行时间指的是不包括像客户端响应(talking)、发送回复等 IO 操作，而单单是执行一个查询命令所耗费的时间
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/debug/slowlog.html" target="_blank">http://redisdoc.com/debug/slowlog.html</a></p>
	 *
	 * @param command
	 * 		子命令
	 * @param args
	 * 		命令参数
	 *
	 * @return 取决于不同命令，返回不同的值
	 */
	Object slowLog(final DebugCommands.SlowLogCommand command, final byte[]... args);

}
