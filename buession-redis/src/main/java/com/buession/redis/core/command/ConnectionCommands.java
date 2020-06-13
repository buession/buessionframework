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

import com.buession.lang.Status;

/**
 * 连接相关命令
 *
 * <p>详情说明
 * <a href="http://www.redis.cn/commands.html#connection" target="_blank">http://www.redis.cn/commands.html#connection</a></p>
 *
 * @author Yong.Teng
 */
public interface ConnectionCommands extends RedisCommands {

	/**
	 * 通过密码进行认证
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/client_and_server/auth.html" target="_blank">http://redisdoc.com/client_and_server/auth.html</a></p>
	 *
	 * @param password
	 * 		密码
	 *
	 * @return 密码匹配时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status auth(final String password);

	/**
	 * 通过密码进行认证
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/client_and_server/auth.html" target="_blank">http://redisdoc.com/client_and_server/auth.html</a></p>
	 *
	 * @param password
	 * 		密码
	 *
	 * @return 密码匹配时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status auth(final byte[] password);

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
	String echo(final String str);

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
	 * 使用客户端向 Redis 服务器发送一个 PING ，如果服务器运作正常的话，会返回一个 PONG；
	 * 通常用于测试与服务器的连接是否仍然生效，或者用于测量延迟值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/debug/ping.html" target="_blank">http://redisdoc.com/debug/ping.html</a>
	 * </p>
	 *
	 * @return PING 结果
	 */
	Status ping();

	/**
	 * 请求服务器关闭与当前客户端的连接
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/client_and_server/quit.html" target="_blank">http://redisdoc
	 * .com/client_and_server/quit.html</a></p>
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	Status quit();

	/**
	 * 切换到指定的数据库
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/database/select.html" target="_blank">http://redisdoc.com/database/select.html</a>
	 * </p>
	 *
	 * @param db
	 * 		数据库索引号
	 *
	 * @return 切换成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status select(final int db);

	/**
	 * 对换指定的两个数据库， 使得两个数据库的数据立即互换
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/database/swapdb.html" target="_blank">http://redisdoc.com/database/swapdb.html</a>
	 * </p>
	 *
	 * @param db1
	 * 		数据库 1 索引号
	 * @param db2
	 * 		数据库 2 索引号
	 *
	 * @return 对换成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status swapdb(final int db1, final int db2);

}
