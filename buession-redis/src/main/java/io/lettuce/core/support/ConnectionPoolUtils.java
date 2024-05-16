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

import io.lettuce.core.LettucePool;
import io.lettuce.core.LettucePoolConfig;
import io.lettuce.core.api.StatefulRedisConnection;
import org.apache.commons.pool2.ObjectPool;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class ConnectionPoolUtils {

	private ConnectionPoolUtils() {

	}

	public static LettucePool createLettucePool(final LettucePoolConfig<byte[], byte[]> lettucePoolConfig,
												final String host, final int port, final int connectionTimeout,
												final int soTimeout, final String user, final String password,
												final int database, final String clientName, final boolean ssl) {
		return createLettucePool(lettucePoolConfig, host, port, connectionTimeout, soTimeout, user, password, database,
				clientName, ssl, true);
	}

	public static LettucePool createLettucePool(final LettucePoolConfig<byte[], byte[]> lettucePoolConfig,
												final String host, final int port, final int connectionTimeout,
												final int soTimeout, final String user, final String password,
												final int database, final String clientName, final boolean ssl, final
												boolean wrapConnections) {
		AtomicReference<ConnectionWrapping.Origin<StatefulRedisConnection<byte[], byte[]>>> poolRef = new AtomicReference<>();

		LettucePool pool = new LettucePool(lettucePoolConfig, host, port, connectionTimeout, soTimeout, user,
				password, database, clientName, ssl) {

			@Override
			public StatefulRedisConnection<byte[], byte[]> borrowObject() throws Exception {
				return wrapConnections ? ConnectionWrapping.wrapConnection(super.borrowObject(), poolRef.get())
						: super.borrowObject();
			}

			@Override
			public void returnObject(StatefulRedisConnection<byte[], byte[]> obj) {
				if(wrapConnections && obj instanceof ConnectionWrapping.HasTargetConnection){
					super.returnObject(
							(StatefulRedisConnection<byte[], byte[]>) ((ConnectionWrapping.HasTargetConnection) obj).getTargetConnection());
					return;
				}

				super.returnObject(obj);
			}
		};

		poolRef.set(new ObjectPoolWrapper<>(pool));

		return pool;
	}

	public static LettucePool createLettucePool(final LettucePoolConfig<byte[], byte[]> lettucePoolConfig,
												final String host, final int port, final int connectionTimeout,
												final int soTimeout, final String user, final String password,
												final int database, final String clientName, final boolean ssl,
												final SSLSocketFactory sslSocketFactory,
												final SSLParameters sslParameters,
												final HostnameVerifier hostnameVerifier) {
		return createLettucePool(lettucePoolConfig, host, port, connectionTimeout, soTimeout, user, password, database,
				clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public static LettucePool createLettucePool(final LettucePoolConfig<byte[], byte[]> lettucePoolConfig,
												final String host, final int port, final int connectionTimeout,
												final int soTimeout, final String user, final String password,
												final int database, final String clientName, final boolean ssl,
												final SSLSocketFactory sslSocketFactory,
												final SSLParameters sslParameters,
												final HostnameVerifier hostnameVerifier,
												final boolean wrapConnections) {
		AtomicReference<ConnectionWrapping.Origin<StatefulRedisConnection<byte[], byte[]>>> poolRef = new AtomicReference<>();

		LettucePool pool = new LettucePool(lettucePoolConfig, host, port, connectionTimeout, soTimeout, user,
				password, database, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier) {

			@Override
			public StatefulRedisConnection<byte[], byte[]> borrowObject() throws Exception {
				return wrapConnections ? ConnectionWrapping.wrapConnection(super.borrowObject(), poolRef.get())
						: super.borrowObject();
			}

			@Override
			public void returnObject(StatefulRedisConnection<byte[], byte[]> obj) {
				if(wrapConnections && obj instanceof ConnectionWrapping.HasTargetConnection){
					super.returnObject(
							(StatefulRedisConnection<byte[], byte[]>) ((ConnectionWrapping.HasTargetConnection) obj).getTargetConnection());
					return;
				}

				super.returnObject(obj);
			}
		};

		poolRef.set(new ObjectPoolWrapper<>(pool));

		return pool;
	}

	private static class ObjectPoolWrapper<T> implements ConnectionWrapping.Origin<T> {

		private static final CompletableFuture<Void> COMPLETED = CompletableFuture.completedFuture(null);

		private final ObjectPool<T> pool;

		ObjectPoolWrapper(ObjectPool<T> pool) {
			this.pool = pool;
		}

		@Override
		public void returnObject(T o) throws Exception {
			pool.returnObject(o);
		}

		@Override
		public CompletableFuture<Void> returnObjectAsync(T o) throws Exception {
			pool.returnObject(o);
			return COMPLETED;
		}

	}

}
