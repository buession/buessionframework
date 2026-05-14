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
package io.lettuce.core.builders;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ConnectionPoolConfig;
import io.lettuce.core.DefaultLettuceClientConfig;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.providers.ConnectionProvider;
import io.lettuce.core.resource.ClientResources;

/**
 * Abstract base class for Redis client builders that provides common configuration options.
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractClientBuilder<K, V, T extends AbstractClientBuilder<K, V, T, C>, C> {

	protected ConnectionPoolConfig<K, V> poolConfig = new ConnectionPoolConfig<>();

	protected LettuceClientConfig clientConfig;

	protected ClientResources clientResources = ClientResources.create();

	protected ClientOptions clientOptions = ClientOptions.create();

	protected ConnectionProvider<K, V> connectionProvider = null;

	protected RedisCodec<K, V> codec;

	/**
	 * Sets the client configuration for Redis connections.
	 * <p>
	 * The client configuration includes authentication, timeouts, SSL settings, and other
	 * connection-specific parameters.
	 *
	 * @param clientConfig
	 * 		the client configuration
	 *
	 * @return this builder
	 */
	public T clientConfig(LettuceClientConfig clientConfig) {
		this.clientConfig = clientConfig;
		return self();
	}

	/**
	 * Sets the connection pool configuration.
	 * <p>
	 * The pool configuration controls how connections are managed, including maximum number of
	 * connections, idle timeout, and other pooling parameters.
	 * </p>
	 *
	 * @param poolConfig
	 * 		the pool configuration
	 *
	 * @return this builder
	 */
	public T poolConfig(ConnectionPoolConfig<K, V> poolConfig) {
		this.poolConfig = poolConfig;
		return self();
	}

	/**
	 * Sets the client resources.
	 * <p>
	 * The client resources include event loop groups, thread pools, and other resources used by the client.
	 * </p>
	 *
	 * @param clientResources
	 * 		the client resources
	 *
	 * @return this builder
	 */
	public T clientResources(ClientResources clientResources) {
		this.clientResources = clientResources;
		return self();
	}

	/**
	 * Sets the client options.
	 * <p>
	 * The client options control various client behaviors, such as auto-reconnect, timeout settings, and more.
	 * </p>
	 *
	 * @param clientOptions
	 * 		the client options
	 *
	 * @return this builder
	 */
	public T clientOptions(ClientOptions clientOptions) {
		this.clientOptions = clientOptions;
		return self();
	}

	/**
	 * Sets a custom connection provider.
	 * <p>
	 * When a custom connection provider is set, other connection-related configuration may be ignored
	 * as the provider is responsible for managing connections. The specific behavior depends on the
	 * concrete builder implementation.
	 * </p>
	 *
	 * @param connectionProvider
	 * 		the connection provider
	 *
	 * @return this builder
	 */
	public T connectionProvider(ConnectionProvider<K, V> connectionProvider) {
		this.connectionProvider = connectionProvider;
		return self();
	}

	/**
	 * Sets a encodes keys and values codec.
	 *
	 * @param codec
	 * 		the encodes keys and values codec
	 *
	 * @return this builder
	 */
	public T codec(RedisCodec<K, V> codec) {
		this.codec = codec;
		return self();
	}

	public C build() {
		// Validate configuration
		validateSpecificConfiguration();

		if(this.clientConfig == null){
			this.clientConfig = createDefaultClientConfig();
		}

		// Create default connection provider if not set
		if(this.connectionProvider == null){
			this.connectionProvider = createDefaultConnectionProvider();
		}

		// Create and return the specific client instance
		return createClient();
	}

	/**
	 * Returns the concrete builder instance for method chaining. This method must be implemented by
	 * subclasses to return their own type.
	 *
	 * @return the concrete builder instance
	 */
	protected abstract T self();

	protected abstract C createClient();

	/**
	 * Creates a default connection provider based on the current configuration.
	 *
	 * @return {@link ConnectionProvider}
	 */
	protected abstract ConnectionProvider<K, V> createDefaultConnectionProvider();

	/**
	 * Creates a default client configuration based on the current configuration.
	 *
	 * @return {@link LettuceClientConfig}
	 */
	protected LettuceClientConfig createDefaultClientConfig() {
		return DefaultLettuceClientConfig.builder().build();
	}

	/**
	 * Validates the builder-specific configuration.
	 * <p>
	 * This method is called by the generic build() method to validate configuration specific to each builder type.
	 * Implementations should call validateCommonConfiguration() and then perform their own specific validation.
	 * </p>
	 *
	 * @throws IllegalArgumentException
	 * 		if the configuration is invalid
	 */
	protected abstract void validateSpecificConfiguration();

	protected void validateCommonConfiguration() {
		if(codec == null){
			throw new IllegalArgumentException("The encodes keys and values codec cloud not be null.");
		}
	}

}
