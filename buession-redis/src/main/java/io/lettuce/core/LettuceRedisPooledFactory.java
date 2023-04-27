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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package io.lettuce.core;

import com.buession.redis.core.internal.lettuce.RedisClientBuilder;
import io.lettuce.core.api.StatefulConnection;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;
import java.util.function.Supplier;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class LettuceRedisPooledFactory<T extends StatefulConnection<?, ?>> extends BasePooledObjectFactory<T> {

	private RedisPooledFactory<T> factory;

	public LettuceRedisPooledFactory(final String host, final int port, final int connectionTimeout,
									 final int soTimeout, final String password, final int database,
									 final String clientName){
		this(host, port, connectionTimeout, soTimeout, password, database, clientName, false, null, null, null);
	}

	public LettuceRedisPooledFactory(final String host, final int port, final int connectionTimeout,
									 final int soTimeout, final String user, final String password, final int database,
									 final String clientName){
		this(host, port, connectionTimeout, soTimeout, 0, user, password, database, clientName);
	}

	public LettuceRedisPooledFactory(final String host, final int port, final int connectionTimeout,
									 final int soTimeout, final int infiniteSoTimeout, final String user,
									 final String password, final int database, final String clientName){
		this(host, port, connectionTimeout, soTimeout, infiniteSoTimeout, user, password, database, clientName, false,
				null, null, null);
	}

	public LettuceRedisPooledFactory(final String host, final int port, final int connectionTimeout,
									 final int soTimeout, final String password, final int database,
									 final String clientName, final boolean ssl,
									 final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
									 final HostnameVerifier hostnameVerifier){
		this(host, port, connectionTimeout, soTimeout, null, password, database, clientName, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier);
	}

	public LettuceRedisPooledFactory(final String host, final int port, final int connectionTimeout,
									 final int soTimeout, final String user, final String password, final int database,
									 final String clientName, final boolean ssl,
									 final SSLSocketFactory sslSocketFactory,
									 final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		this(host, port, connectionTimeout, soTimeout, 0, user, password, database, clientName, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier);
	}

	public LettuceRedisPooledFactory(final RedisURI redisURI){
		final RedisClient redisClient = RedisClient.create(redisURI);
		initialize(redisClient);
	}

	public LettuceRedisPooledFactory(final String host, final int port, final int connectionTimeout,
									 final int soTimeout, final int infiniteSoTimeout, final String user,
									 final String password, final int database, final String clientName,
									 final boolean ssl, final SSLSocketFactory sslSocketFactory,
									 final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		final RedisClientBuilder redisClientBuilder = RedisClientBuilder.create().host(host).port(port)
				.connectionTimeout(connectionTimeout).soTimeout(soTimeout).infiniteSoTimeout(infiniteSoTimeout)
				.user(user).password(password).database(database).clientName(clientName).ssl(ssl)
				.sslSocketFactory(sslSocketFactory).sslParameters(sslParameters).hostnameVerifier(hostnameVerifier);
		final RedisClient redisClient = redisClientBuilder.build();

		initialize(redisClient);
	}

	public LettuceRedisPooledFactory(final URI uri, final int connectionTimeout, final int soTimeout,
									 final String clientName){
		this(uri, connectionTimeout, soTimeout, clientName, null, null, null);
	}

	public LettuceRedisPooledFactory(final URI uri, final int connectionTimeout, final int soTimeout,
									 final String clientName, final SSLSocketFactory sslSocketFactory,
									 final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		this(uri, connectionTimeout, soTimeout, 0, clientName, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceRedisPooledFactory(final URI uri, final int connectionTimeout, final int soTimeout,
									 final int infiniteSoTimeout, final String clientName,
									 final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
									 final HostnameVerifier hostnameVerifier){
		this(uri.getHost(), uri.getPort(), connectionTimeout, soTimeout, infiniteSoTimeout, null, null, 0, clientName,
				sslSocketFactory != null, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	private void initialize(final RedisClient redisClient){
		factory = new RedisPooledFactory(redisClient::connect);
	}

	@Override
	public T create() throws Exception{
		return factory.create();
	}

	@Override
	public void destroyObject(PooledObject<T> p) throws Exception{
		factory.destroyObject(p);
	}

	@Override
	public PooledObject<T> wrap(T obj){
		return factory.wrap(obj);
	}

	@Override
	public boolean validateObject(PooledObject<T> p){
		return factory.validateObject(p);
	}

	private final static class RedisPooledFactory<P extends StatefulConnection<?, ?>>
			extends BasePooledObjectFactory<P> {

		private final Supplier<P> connectionSupplier;

		public RedisPooledFactory(final Supplier<P> connectionSupplier){
			this.connectionSupplier = connectionSupplier;
		}

		@Override
		public P create() throws Exception{
			return connectionSupplier.get();
		}

		@Override
		public void destroyObject(PooledObject<P> p) throws Exception{
			p.getObject().close();
		}

		@Override
		public PooledObject<P> wrap(P obj){
			return new DefaultPooledObject<>(obj);
		}

		@Override
		public boolean validateObject(PooledObject<P> p){
			return p.getObject().isOpen();
		}

	}

}
