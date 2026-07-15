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

import com.buession.lang.Status;
import com.buession.redis.core.Constants;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.RedisServer;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.builders.SentinelClientBuilder;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.providers.ConnectionProvider;
import io.lettuce.core.providers.SentinelConnectionProvider;
import io.lettuce.core.sentinel.api.sync.RedisSentinelCommands;

import java.util.List;
import java.util.Map;

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

	private RedisSentinelCommands<String, String> redisSentinelCommands;

	private RedisSentinelClient(ConnectionProvider<K, V> connectionProvider) {
		super(connectionProvider);
	}

	public HostAndPort getCurrentMaster() {
		return ((SentinelConnectionProvider<K, V>) connectionProvider).getCurrentMaster();
	}

	public String myId() {
		return null;
	}

	public List<RedisNode> sentinels(String masterName) {
		return null;
	}

	public String sentinelSet(String masterName, Map<String, String> parameters) {
		if(parameters != null){
			parameters.forEach((key, value)->getRedisSentinelCommands().set(masterName, key, value));
		}

		return null;
	}

	public List<RedisServer> masters() {
		getRedisSentinelCommands().masters();
		return null;
	}

	public RedisServer master(String masterName) {
		getRedisSentinelCommands().master(masterName);
		return null;
	}

	public List<RedisServer> slaves(String masterName) {
		getRedisSentinelCommands().slaves(masterName);
		return null;
	}

	public List<RedisServer> replicas(String masterName) {
		getRedisSentinelCommands().replicas(masterName);
		return null;
	}

	public Status failover(String masterName) {
		return Constants.OK.equals(getRedisSentinelCommands().failover(masterName)) ? Status.SUCCESS : Status.FAILURE;
	}

	public Status monitor(String masterName, String ip, int port, int quorum) {
		return Constants.OK.equals(
				getRedisSentinelCommands().monitor(masterName, ip, port, quorum)) ? Status.SUCCESS : Status.FAILURE;
	}

	public Long reset(String pattern) {
		return getRedisSentinelCommands().reset(pattern);
	}

	public Status remove(String masterName) {
		return Constants.OK.equals(getRedisSentinelCommands().remove(masterName)) ? Status.SUCCESS : Status.FAILURE;
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

	private RedisSentinelCommands<String, String> getRedisSentinelCommands() {
		if(redisSentinelCommands == null){
			redisSentinelCommands = ((SentinelConnectionProvider<K, V>) connectionProvider).getSentinelConnection()
					.sync();
		}

		return redisSentinelCommands;
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
