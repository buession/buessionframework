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

import io.lettuce.core.api.StatefulConnection;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import redis.clients.jedis.exceptions.InvalidURIException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class RedisPooledFactory<T extends StatefulConnection<?, ?>> extends BasePooledObjectFactory<T> {

	private Supplier<T> connectionSupplier;

	public RedisPooledFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
							  final String password, final int database, final String clientName){
		this(host, port, connectionTimeout, soTimeout, password, database, clientName, false, null, null, null);
	}

	public RedisPooledFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
							  final String user, final String password, final int database, final String clientName){
		this(host, port, connectionTimeout, soTimeout, 0, user, password, database, clientName);
	}

	public RedisPooledFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
							  final int infiniteSoTimeout, final String user, final String password, final int database,
							  final String clientName){
		this(host, port, connectionTimeout, soTimeout, infiniteSoTimeout, user, password, database, clientName, false,
				null, null, null);
	}

	public RedisPooledFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
							  final String password, final int database, final String clientName, final boolean ssl,
							  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
							  final HostnameVerifier hostnameVerifier){
		this(host, port, connectionTimeout, soTimeout, null, password, database, clientName, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier);
	}

	public RedisPooledFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
							  final String user, final String password, final int database, final String clientName,
							  final boolean ssl, final SSLSocketFactory sslSocketFactory,
							  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		this(host, port, connectionTimeout, soTimeout, 0, user, password, database, clientName, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier);
	}

	public RedisPooledFactory(final RedisURI redisURI){
		final RedisClient redisClient = RedisClient.create(redisURI);
		setConnectionSupplier(redisClient::connect);
	}

	public RedisPooledFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
							  final int infiniteSoTimeout, final String user, final String password, final int database,
							  final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
							  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		final RedisURI.Builder redisUriBuilder = RedisURI.builder()
				.withHost(host)
				.withPort(port)
				.withDatabase(database)
				.withSsl(ssl)
				.withVerifyPeer(hostnameVerifier != null)
				.withTimeout(Duration.of(connectionTimeout, ChronoUnit.MILLIS));

		if(password != null){
			redisUriBuilder.withPassword(password);
		}
		if(clientName != null){
			redisUriBuilder.withClientName(clientName);
		}

		initialize(redisUriBuilder.build());
	}

	public RedisPooledFactory(final URI uri, final int connectionTimeout, final int soTimeout, final String clientName){
		this(uri, connectionTimeout, soTimeout, clientName, null, null, null);
	}

	public RedisPooledFactory(final URI uri, final int connectionTimeout, final int soTimeout,
							  final String clientName, final SSLSocketFactory sslSocketFactory,
							  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		this(uri, connectionTimeout, soTimeout, 0, clientName, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public RedisPooledFactory(final URI uri, final int connectionTimeout, final int soTimeout,
							  final int infiniteSoTimeout, final String clientName,
							  final SSLSocketFactory sslSocketFactory,
							  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		RedisURI redisURI;

		try{
			redisURI = RedisURI.create(uri);
		}catch(Exception e){
			throw new InvalidURIException(String.format("Cannot open Redis connection due invalid URI. %s", uri));
		}

		initialize(redisURI);
	}

	private void initialize(final RedisURI redisURI){
		final RedisClient redisClient = RedisClient.create(redisURI);
		setConnectionSupplier(redisClient::connect);
	}

	private void setConnectionSupplier(Supplier<T> connectionSupplier){
		this.connectionSupplier = connectionSupplier;
	}

	@Override
	public T create() throws Exception{
		return connectionSupplier.get();
	}

	@Override
	public void destroyObject(PooledObject<T> p) throws Exception{
		p.getObject().close();
	}

	@Override
	public PooledObject<T> wrap(T obj){
		return new DefaultPooledObject<>(obj);
	}

	@Override
	public boolean validateObject(PooledObject<T> p){
		return p.getObject().isOpen();
	}

	public class RedisPooledFactory<T extends StatefulConnection<?, ?>> extends BasePooledObjectFactory<T> {

		private final Supplier<T> connectionSupplier;

		public RedisPooledFactory(final Supplier<T> connectionSupplier){
			this.connectionSupplier = connectionSupplier;
		}

		@Override
		public T create() throws Exception{
			return connectionSupplier.get();
		}

		@Override
		public void destroyObject(PooledObject<T> p) throws Exception{
			p.getObject().close();
		}

		@Override
		public PooledObject<T> wrap(T obj){
			return new DefaultPooledObject<>(obj);
		}

		@Override
		public boolean validateObject(PooledObject<T> p){
			return p.getObject().isOpen();
		}

	}

}
