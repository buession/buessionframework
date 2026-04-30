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

import com.buession.core.validator.Validate;
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

	private String host = RedisNode.DEFAULT_HOST;

	private int port = RedisNode.DEFAULT_PORT;

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
		this.host = host;
		this.port = port;
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
		return hostAndPort(hostAndPort.getHostText(), hostAndPort.getPort());
	}

	@Override
	protected StandaloneClientBuilder<K, V, C> self() {
		return this;
	}

	@Override
	protected C createClient() {
		//final RedisURI redisURI = RedisURI.create(host, port);
		/*
		final RedisCredentialsProvider redisCredentialsProvider = Validate.hasText(clientConfig.getPassword()) ?
				new StaticCredentialsProvider(
						Validate.hasText(clientConfig.getUsername()) ? clientConfig.getUsername() : null,
						clientConfig.getPassword().toCharArray()) : null;

		if(dataSource.getDatabase() >= 0){
			redisURI.setDatabase(dataSource.getDatabase());
		}

		propertyMapper.from(redisCredentialsProvider).to(redisURI::setCredentialsProvider);
		propertyMapper.from(dataSource.getClientName()).to(redisURI::setClientName);

		if(dataSource.getConnectTimeout() > 0){
			redisURI.setTimeout(Duration.ofMillis(dataSource.getConnectTimeout()));
		}

		redisURI.setSsl(dataSource.getSslConfiguration() != null);

		 */

		//final RedisClient redisClient = RedisClient.create(clientResources, redisURI);

		//redisClient.setOptions(clientOptions);

		//return (C) redisClient.connect(codec);
		return null;//new RedisClient<>(this.connectionProvider);
	}

	@Override
	protected ConnectionProvider<K, V> createDefaultConnectionProvider() {
		return null;//new PooledConnectionProvider<>(this.hostAndPort, this.clientConfig, this.cache, this.poolConfig);
	}

	@Override
	protected void validateSpecificConfiguration() {
		//validateCommonConfiguration();

		if(host == null){
			throw new IllegalArgumentException("Either URI or host/port must be specified");
		}

		if(Validate.isPort(port) == false){
			throw new IllegalArgumentException("Port out of range: " + port);
		}
	}

}
