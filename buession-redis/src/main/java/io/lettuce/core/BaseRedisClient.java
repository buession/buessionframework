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
package io.lettuce.core;

import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.providers.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;

/**
 *
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class BaseRedisClient<K, V> {

	protected ConnectionProvider<K, V> connectionProvider;

	protected RedisCommands<K, V> redisCommands;

	protected RedisAsyncCommands<K, V> redisAsyncCommands;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected BaseRedisClient(ConnectionProvider<K, V> connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	@SuppressWarnings({"unchecked"})
	public RedisCommands<K, V> getRedisCommands() {
		if(redisCommands == null){
			redisCommands = (RedisCommands<K, V>) Proxy.newProxyInstance(RedisCommands.class.getClassLoader(),
					new Class[]{RedisCommands.class}, createRedisCommandsInvocationHandler());
		}

		return redisCommands;
	}

	@SuppressWarnings({"unchecked"})
	public RedisAsyncCommands<K, V> getRedisAsyncCommands() {
		if(redisAsyncCommands == null){
			redisAsyncCommands = (RedisAsyncCommands<K, V>) Proxy.newProxyInstance(
					RedisAsyncCommands.class.getClassLoader(), new Class[]{RedisAsyncCommands.class},
					createRedisAsyncCommandsInvocationHandler());
		}

		return redisAsyncCommands;
	}

	public void multi() {
		getRedisCommands().multi();
	}

	public Pipeline<K, V> pipelined() {
		return null;
	}

	public void close() {
		try{
			connectionProvider.close();
		}catch(Exception e){
			logger.warn("Close redis connection error: {}", e.getMessage());
		}
	}

	protected abstract RedisCommandsInvocationHandler<K, V> createRedisCommandsInvocationHandler();

	protected abstract RedisCommandsInvocationHandler<K, V> createRedisAsyncCommandsInvocationHandler();

}
