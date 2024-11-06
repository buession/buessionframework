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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package io.lettuce.core.support;

import com.buession.net.HostAndPort;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.LettuceClusterPool;
import io.lettuce.core.LettucePool;
import io.lettuce.core.LettucePoolConfig;
import io.lettuce.core.LettuceSentinelPool;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class ConnectionPoolUtils {

	private ConnectionPoolUtils() {

	}

	public static LettucePool createLettucePool(
			final LettucePoolConfig<byte[], byte[], StatefulRedisConnection<byte[], byte[]>> lettucePoolConfig,
			final String host, final int port, final LettuceClientConfig lettuceClientConfig) {
		return createLettucePool(lettucePoolConfig, host, port, lettuceClientConfig, true);
	}

	public static LettucePool createLettucePool(
			final LettucePoolConfig<byte[], byte[], StatefulRedisConnection<byte[], byte[]>> lettucePoolConfig,
			final String host, final int port, final LettuceClientConfig lettuceClientConfig, final
			boolean wrapConnections) {
		AtomicReference<ConnectionWrapping.Origin<StatefulRedisConnection<byte[], byte[]>>> poolRef = new AtomicReference<>();

		LettucePool pool = new LettucePool(lettucePoolConfig, host, port, lettuceClientConfig) {

			@Override
			public StatefulRedisConnection<byte[], byte[]> borrowObject() throws Exception {
				return wrapConnections ? ConnectionWrapping.wrapConnection(super.borrowObject(), poolRef.get())
						: super.borrowObject();
			}

			@SuppressWarnings({"unchecked"})
			@Override
			public void returnObject(StatefulRedisConnection<byte[], byte[]> obj) {
				if(wrapConnections && obj instanceof ConnectionWrapping.HasTargetConnection){
					StatefulRedisConnection<byte[], byte[]> connection =
							(StatefulRedisConnection<byte[], byte[]>) ((ConnectionWrapping.HasTargetConnection) obj).getTargetConnection();
					super.returnObject(connection);
					return;
				}

				super.returnObject(obj);
			}
		};

		poolRef.set(new ObjectPoolWrapper<>(pool));

		return pool;
	}

	public static LettuceSentinelPool createLettuceSentinelPool(final String masterName,
																final LettucePoolConfig<byte[], byte[], StatefulRedisSentinelConnection<byte[], byte[]>> lettucePoolConfig,
																final Set<HostAndPort> sentinels,
																final LettuceClientConfig lettuceClientConfig,
																final LettuceClientConfig sentinelClientConfig) {
		return createLettuceSentinelPool(masterName, lettucePoolConfig, sentinels, lettuceClientConfig,
				sentinelClientConfig, true);
	}

	public static LettuceSentinelPool createLettuceSentinelPool(final String masterName,
																final LettucePoolConfig<byte[], byte[], StatefulRedisSentinelConnection<byte[], byte[]>> lettucePoolConfig,
																final Set<HostAndPort> sentinels,
																final LettuceClientConfig lettuceClientConfig,
																final LettuceClientConfig sentinelClientConfig,
																final boolean wrapConnections) {
		AtomicReference<ConnectionWrapping.Origin<StatefulRedisSentinelConnection<byte[], byte[]>>> poolRef = new AtomicReference<>();

		final LettuceSentinelPool pool = new LettuceSentinelPool(masterName, sentinels, lettucePoolConfig,
				lettuceClientConfig, sentinelClientConfig) {

			@Override
			public StatefulRedisSentinelConnection<byte[], byte[]> borrowObject() throws Exception {
				return wrapConnections ? ConnectionWrapping.wrapConnection(super.borrowObject(), poolRef.get())
						: super.borrowObject();
			}

			@SuppressWarnings({"unchecked"})
			@Override
			public void returnObject(StatefulRedisSentinelConnection<byte[], byte[]> obj) {
				if(wrapConnections && obj instanceof ConnectionWrapping.HasTargetConnection){
					StatefulRedisSentinelConnection<byte[], byte[]> connection =
							(StatefulRedisSentinelConnection<byte[], byte[]>) ((ConnectionWrapping.HasTargetConnection) obj).getTargetConnection();
					super.returnObject(connection);
					return;
				}

				super.returnObject(obj);
			}
		};

		poolRef.set(new ObjectPoolWrapper<>(pool));

		return pool;
	}

	public static LettuceClusterPool createLettuceClusterPool(
			final LettucePoolConfig<byte[], byte[], StatefulRedisClusterConnection<byte[], byte[]>> lettucePoolConfig,
			final Set<HostAndPort> nodes, final LettuceClientConfig lettuceClientConfig) {
		return createLettuceClusterPool(lettucePoolConfig, nodes, lettuceClientConfig, true);
	}

	public static LettuceClusterPool createLettuceClusterPool(
			final LettucePoolConfig<byte[], byte[], StatefulRedisClusterConnection<byte[], byte[]>> lettucePoolConfig,
			final Set<HostAndPort> nodes, final LettuceClientConfig lettuceClientConfig,
			final boolean wrapConnections) {
		AtomicReference<ConnectionWrapping.Origin<StatefulRedisClusterConnection<byte[], byte[]>>> poolRef = new AtomicReference<>();

		final LettuceClusterPool pool = new LettuceClusterPool(nodes, lettucePoolConfig, lettuceClientConfig) {

			@Override
			public StatefulRedisClusterConnection<byte[], byte[]> borrowObject() throws Exception {
				return wrapConnections ? ConnectionWrapping.wrapConnection(super.borrowObject(), poolRef.get())
						: super.borrowObject();
			}

			@SuppressWarnings({"unchecked"})
			@Override
			public void returnObject(StatefulRedisClusterConnection<byte[], byte[]> obj) {
				if(wrapConnections && obj instanceof ConnectionWrapping.HasTargetConnection){
					StatefulRedisClusterConnection<byte[], byte[]> connection =
							(StatefulRedisClusterConnection<byte[], byte[]>) ((ConnectionWrapping.HasTargetConnection) obj).getTargetConnection();
					super.returnObject(connection);
					return;
				}

				super.returnObject(obj);
			}
		};

		poolRef.set(new ObjectPoolWrapper<>(pool));

		return pool;
	}

}
