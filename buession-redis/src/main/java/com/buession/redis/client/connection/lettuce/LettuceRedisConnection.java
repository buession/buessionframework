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
package com.buession.redis.client.connection.lettuce;

import com.buession.redis.client.connection.RedisConnection;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * Lettuce Redis 连接对象
 *
 * @param <C>
 *        {@link StatefulConnection} 类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface LettuceRedisConnection<C extends StatefulConnection<byte[], byte[]>> extends RedisConnection {

	/**
	 * 返回 Redis 连接对象实例 {@link StatefulConnection}
	 *
	 * @return Redis 连接对象实例 {@link StatefulConnection}
	 *
	 * @since 4.0.0
	 */
	C getConn();

	RedisCommands<byte[], byte[]> getRedisCommands();

	RedisAsyncCommands<byte[], byte[]> getRedisAsyncCommands();

}
