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
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.Pool;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.protocol.CommandArgs;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.Collections;
import java.util.Map;

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
public class PooledConnectionProvider<K, V> implements ConnectionProvider<K, V> {

	private final Pool<StatefulConnection<K, V>> pool;

	private Object connectionMapKey = "";

	public PooledConnectionProvider(HostAndPort hostAndPort) {
		this(new ConnectionFactory<>(hostAndPort));
		this.connectionMapKey = hostAndPort;
	}

	public PooledConnectionProvider(HostAndPort hostAndPort, LettuceClientConfig clientConfig) {
		this(new ConnectionPool<>(hostAndPort, clientConfig));
		this.connectionMapKey = hostAndPort;
	}

	public PooledConnectionProvider(HostAndPort hostAndPort, LettuceClientConfig clientConfig,
	                                GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig) {
		this(new ConnectionPool<>(hostAndPort, clientConfig, poolConfig));
		this.connectionMapKey = hostAndPort;
	}

	public PooledConnectionProvider(PooledObjectFactory<StatefulConnection<K, V>> factory) {
		this(new ConnectionPool<>(factory));
		this.connectionMapKey = factory;
	}

	public PooledConnectionProvider(PooledObjectFactory<StatefulConnection<K, V>> factory,
	                                GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig) {
		this(new ConnectionPool<>(factory, poolConfig));
		this.connectionMapKey = factory;
	}

	private PooledConnectionProvider(Pool<StatefulConnection<K, V>> pool) {
		this.pool = pool;
	}

	@Override
	public void close() {
		pool.close();
	}

	public final Pool<StatefulConnection<K, V>> getPool() {
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
	public Map<?, Pool<StatefulConnection<K, V>>> getConnectionMap() {
		return Collections.singletonMap(connectionMapKey, pool);
	}

}
