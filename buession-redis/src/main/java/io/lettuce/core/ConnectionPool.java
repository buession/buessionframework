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

import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.internal.HostAndPort;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 连接池
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ConnectionPool<K, V> extends Pool<StatefulConnection<K, V>> {

	public ConnectionPool(PooledObjectFactory<StatefulConnection<K, V>> factory) {
		super(factory);
	}

	public ConnectionPool(PooledObjectFactory<StatefulConnection<K, V>> factory,
	                      GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig) {
		super(factory, poolConfig);
	}

	public ConnectionPool(HostAndPort hostAndPort, LettuceClientConfig clientConfig, RedisCodec<K, V> redisCodec) {
		this(new ConnectionFactory<>(hostAndPort, clientConfig, redisCodec));
	}

	public ConnectionPool(HostAndPort hostAndPort, LettuceClientConfig clientConfig, RedisCodec<K, V> redisCodec,
	                      GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig) {
		this(new ConnectionFactory<>(hostAndPort, clientConfig, redisCodec), poolConfig);
	}

	@Override
	public StatefulConnection<K, V> getResource() {
		return super.getResource();
	}

	@Override
	public void close() {
		super.close();
	}

}
