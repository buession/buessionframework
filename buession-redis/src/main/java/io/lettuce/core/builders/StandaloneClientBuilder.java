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

import com.buession.redis.core.RedisNode;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.providers.ConnectionProvider;
import io.lettuce.core.providers.PooledConnectionProvider;

/**
 * Builder for creating RedisClient instances (standalone Redis connections).
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 * @param <C>
 * 		the client type that this builder creates
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class StandaloneClientBuilder<K, V, C>
		extends AbstractClientBuilder<K, V, StandaloneClientBuilder<K, V, C>, C> {

	private HostAndPort hostAndPort = HostAndPort.of(RedisNode.DEFAULT_HOST, RedisNode.DEFAULT_PORT);

	protected StandaloneClientBuilder() {

	}

	/**
	 * Sets the Redis server host and port.
	 *
	 * @param host
	 * 		the Redis server hostname
	 * @param port
	 * 		the Redis server port
	 *
	 * @return this builder
	 */
	public StandaloneClientBuilder<K, V, C> hostAndPort(String host, int port) {
		this.hostAndPort = HostAndPort.of(host, port);
		return this;
	}

	/**
	 * Sets the Redis server host and port.
	 *
	 * @param hostAndPort
	 * 		the Redis server host and port
	 *
	 * @return this builder
	 */
	public StandaloneClientBuilder<K, V, C> hostAndPort(HostAndPort hostAndPort) {
		this.hostAndPort = hostAndPort;
		return this;
	}

	@Override
	protected StandaloneClientBuilder<K, V, C> self() {
		return this;
	}

	@Override
	protected ConnectionProvider<K, V> createDefaultConnectionProvider() {
		return new PooledConnectionProvider<>(this.hostAndPort, this.clientConfig, this.codec, this.poolConfig);
	}

	@Override
	protected void validateSpecificConfiguration() {
		validateCommonConfiguration();

		if(hostAndPort == null){
			throw new IllegalArgumentException("Either URI or host/port must be specified");
		}
	}

}
