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

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.builders.SentinelClientBuilder;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.providers.ConnectionProvider;
import io.lettuce.core.providers.SentinelConnectionProvider;

/**
 * Lettuce Redis Sentinel 客户端
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class RedisSentinelClient<K, V> extends BaseRedisClient<K, V> {

	private RedisSentinelClient(ConnectionProvider<K, V> connectionProvider) {
		super(connectionProvider);
	}

	public HostAndPort getCurrentMaster() {
		return ((SentinelConnectionProvider<K, V>) connectionProvider).getCurrentMaster();
	}

	/**
	 * Create a new builder for configuring RedisSentinelClient instances.
	 *
	 * @return a new {@link io.lettuce.core.RedisSentinelClient.Builder} instance
	 */
	public static <K, V> Builder<K, V> builder() {
		return new Builder<>();
	}

	@Override
	protected RedisCommandsInvocationHandler<K, V> createRedisCommandsInvocationHandler() {
		return new StatefulRedisCommandsHandler<>((StatefulRedisConnection<K, V>) connectionProvider.getConnection());
	}

	@Override
	protected RedisCommandsInvocationHandler<K, V> createRedisAsyncCommandsInvocationHandler() {
		return new StatefulRedisCommandsHandler<>(
				(StatefulRedisConnection<K, V>) connectionProvider.getConnection(), true);
	}

	/**
	 * Fluent builder for {@link io.lettuce.core.RedisSentinelClient} (Redis Sentinel).
	 * <p>
	 * Obtain an instance via {@link #builder()}.
	 * </p>
	 */
	public static class Builder<K, V> extends SentinelClientBuilder<K, V, RedisSentinelClient<K, V>> {

		@Override
		protected RedisSentinelClient<K, V> createClient() {
			return new RedisSentinelClient<>(connectionProvider);
		}

	}

}
