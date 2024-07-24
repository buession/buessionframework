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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.operations;

import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.command.ConnectionCommands;
import com.buession.redis.core.command.args.ClientKillArgument;

/**
 * 连接相关命令
 *
 * <p>详情说明 <a href="http://www.redis.cn/commands.html#connection" target="_blank">http://www.redis.cn/commands.html#connection</a></p>
 *
 * @author Yong.Teng
 */
public interface ConnectionOperations extends ConnectionCommands, RedisOperations {

	/**
	 * 关闭地址为 host:port 的客户端
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_kill.html" target="_blank">http://redisdoc.com/client_and_server/client_kill.html</a></p>
	 *
	 * @param server
	 * 		客户端地址
	 *
	 * @return 当指定的客户端存在，且被成功关闭时，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status clientKill(final RedisNode server) {
		Assert.isNull(server, "Redis server cloud not be null.");
		return clientKill(server.getHost(), server.getPort());
	}

	/**
	 * 关闭地址为 host:port 的客户端
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_kill.html" target="_blank">http://redisdoc.com/client_and_server/client_kill.html</a></p>
	 *
	 * @param clientKillArgument
	 * 		客户端关闭参数
	 *
	 * @return 关闭客户端数量
	 *
	 * @since 3.0.0
	 */
	default Long clientKill(final ClientKillArgument clientKillArgument) {
		return clientKill(new ClientKillArgument[]{clientKillArgument});
	}

}
