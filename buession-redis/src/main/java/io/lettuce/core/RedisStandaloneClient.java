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

import com.buession.core.utils.Assert;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.builders.StandaloneClientBuilder;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.providers.ConnectionProvider;
import io.lettuce.core.utils.LettuceURIHelper;

import java.net.URI;

/**
 * Lettuce Redis 单机客户端
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class RedisStandaloneClient<K, V> extends BaseRedisClient<K, V> {

	private RedisStandaloneClient(ConnectionProvider<K, V> connectionProvider) {
		super(connectionProvider);
	}

	/**
	 * Creates a RedisStandaloneClient with default host and port (localhost:6379).
	 * <p>
	 * This is a convenience factory method that uses the builder pattern internally.
	 * </p>
	 *
	 * @return a new {@link RedisStandaloneClient} instance
	 */
	public static <K, V> RedisStandaloneClient<K, V> create() {
		return RedisStandaloneClient.<K, V>builder().build();
	}

	/**
	 * Creates a RedisStandaloneClient with the specified host and port.
	 * <p>
	 * This is a convenience factory method that uses the builder pattern internally.
	 * </p>
	 *
	 * @param host
	 * 		the Redis server hostname
	 * @param port
	 * 		the Redis server port
	 *
	 * @return a new {@link RedisStandaloneClient} instance
	 */
	public static <K, V> RedisStandaloneClient<K, V> create(final String host, final int port) {
		return RedisStandaloneClient.<K, V>builder().hostAndPort(host, port).build();
	}

	/**
	 * Creates a RedisStandaloneClient with the specified host and port.
	 * <p>
	 * This is a convenience factory method that uses the builder pattern internally.
	 * </p>
	 *
	 * @param hostAndPort
	 * 		the Redis server host and port
	 *
	 * @return a new {@link RedisStandaloneClient} instance
	 */
	public static <K, V> RedisStandaloneClient<K, V> create(final HostAndPort hostAndPort) {
		return RedisStandaloneClient.<K, V>builder().hostAndPort(hostAndPort).build();
	}

	/**
	 * Creates a RedisClient with the specified host, port, user, and password.
	 * <p>
	 * This is a convenience factory method that uses the builder pattern internally.
	 *
	 * @param host
	 * 		the Redis server hostname
	 * @param port
	 * 		the Redis server port
	 * @param user
	 * 		the username for authentication
	 * @param password
	 * 		the password for authentication
	 *
	 * @return a new {@link RedisStandaloneClient} instance
	 */
	public static <K, V> RedisStandaloneClient<K, V> create(final String host, final int port, final String user,
	                                                        final String password) {
		return RedisStandaloneClient.<K, V>builder().hostAndPort(host, port)
				.clientConfig(DefaultLettuceClientConfig.builder().user(user).password(password).build())
				.build();
	}

	/**
	 * Creates a RedisClient from a Redis URI.
	 * <p>
	 * The URI must be in the format: {@code redis[s]://[[user][:password]@]host[:port][/database]}
	 * <p>
	 * Examples:
	 * <ul>
	 * <li>{@code redis://localhost:6379}</li>
	 * <li>{@code redis://user:password@localhost:6379/0}</li>
	 * <li>{@code rediss://localhost:6380} (SSL)</li>
	 * </ul>
	 * <p>
	 * <b>Note:</b> To connect using just a hostname and port without a URI, use
	 * {@link #create(String, int)} instead.
	 * <p>
	 * This is a convenience factory method that uses the builder pattern internally.
	 *
	 * @param url
	 * 		Redis URI string (not just a hostname)
	 *
	 * @return a new {@link RedisStandaloneClient} instance
	 *
	 * @throws IllegalArgumentException
	 * 		if the URI format is invalid
	 */
	public static <K, V> RedisStandaloneClient<K, V> create(final String url) {
		return RedisStandaloneClient.create(URI.create(url));
	}

	/**
	 * Creates a RedisClient from a Redis URI.
	 * <p>
	 * This is a convenience factory method that uses the builder pattern internally.
	 *
	 * @param uri
	 * 		the Redis server URI
	 *
	 * @return a new {@link RedisStandaloneClient} instance
	 */
	public static <K, V> RedisStandaloneClient<K, V> create(final URI uri) {
		Assert.isNull(uri, "Redis URI must not be null");
		Assert.isFalse(LettuceURIHelper.isValid(uri), "Invalid Redis URI");

		LettuceClientConfig clientConfig = DefaultLettuceClientConfig.builder(uri).build();
		com.buession.core.HostAndPort hostAndPort = LettuceURIHelper.getHostAndPort(uri);

		return RedisStandaloneClient.<K, V>builder()
				.hostAndPort(HostAndPort.of(hostAndPort.getHost(), hostAndPort.getPort()))
				.clientConfig(clientConfig).build();
	}

	/**
	 * Create a new builder for configuring RedisClient instances.
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
		return new StatefulRedisCommandsHandler<>((StatefulRedisConnection<K, V>) connectionProvider.getConnection());
	}

	@Override
	protected RedisCommandsInvocationHandler<K, V> createRedisAsyncCommandsInvocationHandler() {
		return new StatefulRedisCommandsHandler<>(
				(StatefulRedisConnection<K, V>) connectionProvider.getConnection(), true);
	}

	/**
	 * Fluent builder for {@link RedisStandaloneClient} (standalone).
	 * <p>
	 * Obtain an instance via {@link #builder()}.
	 * </p>
	 */
	public static class Builder<K, V> extends StandaloneClientBuilder<K, V, RedisStandaloneClient<K, V>> {

		@Override
		protected RedisStandaloneClient<K, V> createClient() {
			return new RedisStandaloneClient<>(connectionProvider);
		}

	}

}
