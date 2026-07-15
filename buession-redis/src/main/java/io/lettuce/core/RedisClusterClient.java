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

import io.lettuce.core.builders.ClusterClientBuilder;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.providers.ConnectionProvider;

import java.util.Collections;
import java.util.Set;

/**
 * Lettuce Redis 集群客户端
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class RedisClusterClient<K, V> extends BaseRedisClient<K, V> {

	private RedisClusterClient(ConnectionProvider<K, V> connectionProvider) {
		super(connectionProvider);
	}

	/**
	 * Creates a RedisClusterClient instance. The provided node is used to make the first contact with
	 * the cluster.
	 * <p>
	 * Here, the default timeout of {@value io.lettuce.core.RedisURI#DEFAULT_TIMEOUT} ms
	 * is being used with {@value com.buession.redis.client.connection.datasource.ClusterDataSource#DEFAULT_MAX_ATTEMPTS} maximum
	 * attempts.
	 * <p>
	 * This is a convenience factory method that uses the builder pattern internally.
	 *
	 * @param node
	 * 		Node to first connect to.
	 *
	 * @return a new {@link RedisClusterClient} instance
	 */
	public static <K, V> RedisClusterClient<K, V> create(HostAndPort node) {
		return RedisClusterClient.<K, V>builder().nodes(Collections.singleton(node))
				.clientConfig(DefaultLettuceClientConfig.builder().timeout(RedisURI.DEFAULT_TIMEOUT_DURATION).build())
				.build();
	}

	/**
	 * Creates a RedisClusterClient with multiple entry points.
	 * <p>
	 * Here, the default timeout of {@value io.lettuce.core.RedisURI#DEFAULT_TIMEOUT} ms
	 * is being used with {@value com.buession.redis.client.connection.datasource.ClusterDataSource#DEFAULT_MAX_ATTEMPTS} maximum
	 * attempts.
	 * <p>
	 * This is a convenience factory method that uses the builder pattern internally.
	 *
	 * @param nodes
	 * 		Nodes to connect to.
	 *
	 * @return a new {@link RedisClusterClient} instance
	 */
	public static <K, V> RedisClusterClient<K, V> create(Set<HostAndPort> nodes) {
		return RedisClusterClient.<K, V>builder().nodes(nodes)
				.clientConfig(DefaultLettuceClientConfig.builder().timeout(RedisURI.DEFAULT_TIMEOUT_DURATION).build())
				.build();
	}

	/**
	 * Creates a RedisClusterClient with multiple entry points and authentication.
	 * <p>
	 * Here, the default timeout of {@value io.lettuce.core.RedisURI#DEFAULT_TIMEOUT} ms is being
	 * used with {@value com.buession.redis.client.connection.datasource.ClusterDataSource#DEFAULT_MAX_ATTEMPTS} maximum
	 * attempts.
	 * <p>
	 * This is a convenience factory method that uses the builder pattern internally.
	 *
	 * @param nodes
	 * 		Nodes to connect to.
	 * @param user
	 * 		Username for authentication.
	 * @param password
	 * 		Password for authentication.
	 *
	 * @return a new {@link RedisClusterClient} instance
	 */
	public static <K, V> RedisClusterClient<K, V> create(Set<HostAndPort> nodes, String user, String password) {
		return RedisClusterClient.<K, V>builder().nodes(nodes)
				.clientConfig(DefaultLettuceClientConfig.builder().user(user).password(password).build())
				.build();
	}

	/**
	 * Create a new builder for configuring RedisClusterClient instances.
	 *
	 * @return a new {@link Builder} instance
	 */
	public static <K, V> Builder<K, V> builder() {
		return new Builder<>();
	}

	@Override
	public Pipeline<K, V> pipelined() {
		return new Pipeline<>(connectionProvider.getConnection());
	}

	@Override
	protected RedisCommandsInvocationHandler<K, V> createRedisCommandsInvocationHandler() {
		return new StatefulRedisClusterCommandsHandler<>(
				(StatefulRedisClusterConnection<K, V>) connectionProvider.getConnection());
	}

	@Override
	protected RedisCommandsInvocationHandler<K, V> createRedisAsyncCommandsInvocationHandler() {
		return new StatefulRedisClusterCommandsHandler<>(
				(StatefulRedisClusterConnection<K, V>) connectionProvider.getConnection(), true);
	}

	/**
	 * Fluent builder for {@link RedisClusterClient} (Redis Cluster).
	 * <p>
	 * Obtain an instance via {@link #builder()}.
	 * </p>
	 */
	public static class Builder<K, V> extends ClusterClientBuilder<K, V, RedisClusterClient<K, V>> {

		@Override
		protected RedisClusterClient<K, V> createClient() {
			return new RedisClusterClient<>(connectionProvider);
		}

	}

}
