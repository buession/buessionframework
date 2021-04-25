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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.jedis;

import com.buession.redis.client.connection.RedisConnection;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.util.Pool;

/**
 * Jedis Redis 连接对象
 *
 * @author Yong.Teng
 */
public interface JedisRedisConnection<T extends JedisCommands> extends RedisConnection {

	/**
	 * 获取连接池配置
	 *
	 * @return 连接池配置
	 */
	JedisPoolConfig getPoolConfig();

	/**
	 * 设置连接池配置
	 *
	 * @param poolConfig
	 * 		连接池配置
	 */
	void setPoolConfig(JedisPoolConfig poolConfig);

	/**
	 * 获取 Redis 连接池
	 *
	 * @return 连接池
	 */
	Pool<T> getPool();

}
