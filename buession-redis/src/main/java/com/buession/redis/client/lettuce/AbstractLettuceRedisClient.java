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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis;

import com.buession.redis.client.AbstractRedisClient;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.core.FutureResult;
import redis.clients.jedis.Response;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Jedis Redis 客户端抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisClient extends AbstractRedisClient implements JedisRedisClient {

	private Queue<FutureResult<Response<Object>, Object, Object>> txResults = new LinkedList<>();

	/**
	 * 构造函数
	 */
	public AbstractJedisRedisClient(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Jedis Redis 连接对象 {@link JedisRedisConnection}
	 */
	public AbstractJedisRedisClient(final JedisRedisConnection connection){
		super(connection);
	}

	@Override
	public Queue<FutureResult<Response<Object>, Object, Object>> getTxResults(){
		return txResults;
	}

}