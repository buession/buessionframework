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
public interface BinaryConnectionCommands extends BinaryRedisCommands {

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
	byte[] echo(final byte[] str);

}
