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
package io.lettuce.core.providers;

import io.lettuce.core.ConnectionFactory;
import io.lettuce.core.ConnectionPool;
import io.lettuce.core.DefaultLettuceClientConfig;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.utils.IOUtils;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Lettuce Redis 连接池连接提供者
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class PooledConnectionProvider<K, V> extends AbstractConnectionProvider<K, V> {

	private volatile ConnectionPool<K, V, StatefulConnection<K, V>> pool;

	public PooledConnectionProvider(final HostAndPort hostAndPort, final RedisCodec<K, V> redisCodec) {
		this(new ConnectionFactory<>(createRedisClient(hostAndPort, DefaultLettuceClientConfig.builder().build()),
				redisCodec));
	}

	public PooledConnectionProvider(final HostAndPort hostAndPort, final LettuceClientConfig clientConfig,
	                                final RedisCodec<K, V> redisCodec) {
		this(new ConnectionPool<>(createRedisClient(hostAndPort, clientConfig), redisCodec));
	}

	public PooledConnectionProvider(final HostAndPort hostAndPort, final LettuceClientConfig clientConfig,
	                                final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                final RedisCodec<K, V> redisCodec) {
		this(new ConnectionPool<>(createRedisClient(hostAndPort, clientConfig), poolConfig, redisCodec));
	}

	public PooledConnectionProvider(final PooledObjectFactory<StatefulConnection<K, V>> factory) {
		this(new ConnectionPool<>(factory));
	}

	public PooledConnectionProvider(final PooledObjectFactory<StatefulConnection<K, V>> factory,
	                                final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig) {
		this(new ConnectionPool<>(factory, poolConfig));
	}

	private PooledConnectionProvider(ConnectionPool<K, V, StatefulConnection<K, V>> pool) {
		this.pool = pool;
	}

	public final ConnectionPool<K, V, StatefulConnection<K, V>> getPool() {
		return pool;
	}

	@Override
	public StatefulConnection<K, V> getConnection() {
		return pool.getResource();
	}

	@Override
	public StatefulConnection<K, V> getConnection(CommandArgs<K, V> commandArgs) {
		return pool.getResource();
	}

	@Override
	public void close() {
		IOUtils.closeQuietly(pool);
	}

	private static RedisClient createRedisClient(final HostAndPort hostAndPort,
	                                             final LettuceClientConfig clientConfig) {
		final RedisURI redisURI = RedisURI.create(hostAndPort.getHostText(), hostAndPort.getPort());

		propertyMapper.from(clientConfig.getCredentialsProvider()).to(redisURI::setCredentialsProvider);
		propertyMapper.from(clientConfig.getClientName()).to(redisURI::setClientName);

		if(clientConfig.getDatabase() >= 0){
			redisURI.setDatabase(clientConfig.getDatabase());
		}

		if(clientConfig.isSsl()){
			redisURI.setSsl(true);
		}

		if(clientConfig.getSocketTimeout() != null && clientConfig.getSocketTimeout().isNegative() == false){
			redisURI.setTimeout(clientConfig.getSocketTimeout());
		}

		final RedisClient redisClient = RedisClient.create(createClientResources(clientConfig), redisURI);

		propertyMapper.from(createClientOptions(clientConfig)).to(redisClient::setOptions);

		return redisClient;
	}

}
