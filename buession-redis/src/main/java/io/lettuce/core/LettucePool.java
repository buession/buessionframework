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

import io.lettuce.core.connection.StandaloneStatefulRedisConnection;
import io.lettuce.core.protocol.Protocol;
import io.lettuce.core.support.ConnectionUtils;
import io.lettuce.core.support.ObjectPoolWrapper;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class LettucePool extends Pool<StandaloneStatefulRedisConnection> {

	private final boolean wrapConnections;

	private AtomicReference<ObjectPoolWrapper<StandaloneStatefulRedisConnection>> poolRef;

	private final static Logger logger = LoggerFactory.getLogger(LettucePool.class);

	public LettucePool(){
		this(true);
	}

	public LettucePool(final String url){
		this(url, true);
	}

	public LettucePool(final String url, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){
		this(url, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final String host, final int port){
		this(host, port, true);
	}

	public LettucePool(final String host, final int port, final boolean ssl){
		this(host, port, ssl, true);
	}

	public LettucePool(final String host, final int port, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		this(host, port, ssl, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final String host, int port, String user, final String password){
		this(host, port, user, password, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig){
		this(poolConfig, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String url){
		this(poolConfig, url, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port){
		this(poolConfig, host, port, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final boolean ssl){
		this(poolConfig, host, port, ssl, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		this(poolConfig, host, port, ssl, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   int port, final String user, final String password){
		this(poolConfig, host, port, user, password, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host
			, final int port, final int timeout){
		this(poolConfig, host, port, timeout, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host
			, final int port, final int timeout, final boolean ssl){
		this(poolConfig, host, port, timeout, ssl, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		this(poolConfig, host, port, timeout, ssl, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host
			, int port, int timeout, final String password){
		this(poolConfig, host, port, timeout, password, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   int port, int timeout, final String password, final boolean ssl){
		this(poolConfig, host, port, timeout, password, ssl, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   int port, int timeout, final String password, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){
		this(poolConfig, host, port, timeout, password, ssl, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   int port, final int timeout, final String user, final String password){
		this(poolConfig, host, port, timeout, user, password, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String user, final String password, final boolean ssl){
		this(poolConfig, host, port, timeout, user, password, ssl, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String password, final int database){
		this(poolConfig, host, port, timeout, password, database, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String password, final int database, final boolean ssl){
		this(poolConfig, host, port, timeout, password, database, ssl, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String password, final int database, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){
		this(poolConfig, host, port, timeout, password, database, null, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String user, final String password, final int database){
		this(poolConfig, host, port, timeout, user, password, database, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String user, final String password, final int database,
					   final boolean ssl){
		this(poolConfig, host, port, timeout, user, password, database, ssl, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String password, final int database,
					   final String clientName){
		this(poolConfig, host, port, timeout, timeout, password, database, clientName, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String password, final int database,
					   final String clientName, final boolean ssl){
		this(poolConfig, host, port, timeout, timeout, password, database, clientName, ssl, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String password, final int database,
					   final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		this(poolConfig, host, port, timeout, timeout, password, database, clientName, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String user, final String password, final int database,
					   final String clientName){
		this(poolConfig, host, port, timeout, timeout, user, password, database, clientName, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String user, final String password, final int database,
					   final String clientName, final boolean ssl){
		this(poolConfig, host, port, timeout, timeout, user, password, database, clientName, ssl, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final String password,
					   final int database, final String clientName){
		this(poolConfig, host, port, connectionTimeout, soTimeout, password, database, clientName, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final String password,
					   final int database, final String clientName, final boolean ssl){
		this(poolConfig, host, port, connectionTimeout, soTimeout, password, database, clientName, ssl, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final String password,
					   final int database, final String clientName, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){
		this(poolConfig, host, port, connectionTimeout, soTimeout, password, database, clientName, ssl,
				sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final String user,
					   final String password, final int database, final String clientName){
		this(poolConfig, host, port, connectionTimeout, soTimeout, user, password, database, clientName, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final String user,
					   final String password, final int database, final String clientName, final boolean ssl){
		this(poolConfig, host, port, connectionTimeout, soTimeout, user, password, database, clientName, ssl, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final String user,
					   final String password, final int database, final String clientName, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){
		this(poolConfig, host, port, connectionTimeout, soTimeout, user, password, database,
				clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
					   final String password, final int database, final String clientName, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){
		this(poolConfig, host, port, connectionTimeout, soTimeout, infiniteSoTimeout, password, database,
				clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
					   final String user, final String password, final int database, final String clientName){
		this(poolConfig, host, port, connectionTimeout, soTimeout, infiniteSoTimeout, user, password, database,
				clientName, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
					   final String user, final String password, final int database, final String clientName,
					   final boolean ssl, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){
		this(poolConfig, host, port, connectionTimeout, soTimeout, infiniteSoTimeout, user, password, database,
				clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final URI uri){
		this(uri, true);
	}

	public LettucePool(final URI uri, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){
		this(uri, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final URI uri, final int timeout){
		this(uri, timeout, true);
	}

	public LettucePool(final URI uri, final int timeout, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		this(uri, timeout, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri){
		this(poolConfig, uri, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){
		this(poolConfig, uri, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final int timeout){
		this(poolConfig, uri, timeout, timeout, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final int timeout, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){
		this(poolConfig, uri, timeout, timeout, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final int connectionTimeout, final int soTimeout){
		this(poolConfig, uri, connectionTimeout, soTimeout, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final int connectionTimeout, final int soTimeout, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		this(poolConfig, uri, connectionTimeout, soTimeout, sslSocketFactory, sslParameters, hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){
		this(poolConfig, uri, connectionTimeout, soTimeout, infiniteSoTimeout, sslSocketFactory, sslParameters,
				hostnameVerifier, true);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig,
					   final PooledObjectFactory<StandaloneStatefulRedisConnection> factory){
		this(poolConfig, factory, true);
	}

	public LettucePool(final boolean wrapConnections){
		this(Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT, wrapConnections);
	}

	public LettucePool(final String url, final boolean wrapConnections){
		this(URI.create(url), wrapConnections);
	}

	public LettucePool(final String url, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier, final boolean wrapConnections){
		this(new GenericObjectPoolConfig<>(), new LettuceRedisPooledFactory<>(URI.create(url), Protocol.DEFAULT_TIMEOUT,
				Protocol.DEFAULT_TIMEOUT, null, sslSocketFactory, sslParameters, hostnameVerifier), wrapConnections);
	}

	public LettucePool(final String host, final int port, final boolean ssl, final boolean wrapConnections){
		this(new GenericObjectPoolConfig<>(), host, port, ssl, wrapConnections);
	}

	public LettucePool(final String host, final int port, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier,
					   final boolean wrapConnections){
		this(new GenericObjectPoolConfig<>(), host, port, ssl, sslSocketFactory, sslParameters, hostnameVerifier,
				wrapConnections);
	}

	public LettucePool(final String host, int port, String user, final String password, final boolean wrapConnections){
		this(new GenericObjectPoolConfig<>(), host, port, user, password, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig,
					   final boolean wrapConnections){
		this(poolConfig, Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String url,
					   final boolean wrapConnections){
		this(poolConfig, URI.create(url), wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final boolean ssl, final boolean wrapConnections){
		this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, ssl, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier,
					   final boolean wrapConnections){
		this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, ssl, sslSocketFactory, sslParameters,
				hostnameVerifier, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   int port, final String user, final String password, final boolean wrapConnections){
		this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, user, password, Protocol.DEFAULT_DATABASE,
				wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host
			, final int port, final int timeout, final boolean ssl, final boolean wrapConnections){
		this(poolConfig, host, port, timeout, null, ssl, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier,
					   final boolean wrapConnections){
		this(poolConfig, host, port, timeout, null, ssl, sslSocketFactory, sslParameters, hostnameVerifier,
				wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   int port, int timeout, final String password, final boolean ssl, final boolean wrapConnections){
		this(poolConfig, host, port, timeout, password, Protocol.DEFAULT_DATABASE, ssl, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   int port, int timeout, final String password, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier, final boolean wrapConnections){
		this(poolConfig, host, port, timeout, password, Protocol.DEFAULT_DATABASE, ssl,
				sslSocketFactory, sslParameters, hostnameVerifier, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String user, final String password, final boolean ssl,
					   final boolean wrapConnections){
		this(poolConfig, host, port, timeout, user, password, Protocol.DEFAULT_DATABASE, ssl, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String password, final int database, final boolean ssl,
					   final boolean wrapConnections){
		this(poolConfig, host, port, timeout, password, database, null, ssl, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String password, final int database, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier, final boolean wrapConnections){
		this(poolConfig, host, port, timeout, password, database, null, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String user, final String password, final int database,
					   final boolean ssl, final boolean wrapConnections){
		this(poolConfig, host, port, timeout, user, password, database, null, ssl, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String password, final int database,
					   final String clientName, final boolean ssl, final boolean wrapConnections){
		this(poolConfig, host, port, timeout, timeout, password, database, clientName, ssl, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String password, final int database,
					   final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier,
					   final boolean wrapConnections){
		this(poolConfig, host, port, timeout, timeout, password, database, clientName, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int timeout, final String user, final String password, final int database,
					   final String clientName, final boolean ssl, final boolean wrapConnections){
		this(poolConfig, host, port, timeout, timeout, user, password, database, clientName, ssl, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final String password,
					   final int database, final String clientName, final boolean ssl, final boolean wrapConnections){
		this(poolConfig, host, port, connectionTimeout, soTimeout, password, database, clientName, ssl, null, null,
				null, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final String password,
					   final int database, final String clientName, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier, final boolean wrapConnections){
		this(poolConfig,
				new LettuceRedisPooledFactory<>(host, port, connectionTimeout, soTimeout, password, database,
						clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier), wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final String user,
					   final String password, final int database, final String clientName, final boolean ssl,
					   final boolean wrapConnections){
		this(poolConfig, host, port, connectionTimeout, soTimeout, user, password, database, clientName, ssl, null,
				null, null, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final String user,
					   final String password, final int database, final String clientName, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier, final boolean wrapConnections){
		this(poolConfig, host, port, connectionTimeout, soTimeout, 0, user, password, database,
				clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
					   final String password, final int database, final String clientName, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier, final boolean wrapConnections){
		this(poolConfig, host, port, connectionTimeout, soTimeout, infiniteSoTimeout, null, password, database,
				clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
					   final String user, final String password, final int database, final String clientName,
					   final boolean wrapConnections){
		this(poolConfig,
				new LettuceRedisPooledFactory<>(host, port, connectionTimeout, soTimeout, infiniteSoTimeout, user,
						password, database, clientName), wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final String host,
					   final int port, final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
					   final String user, final String password, final int database, final String clientName,
					   final boolean ssl, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier, final boolean wrapConnections){
		this(poolConfig,
				new LettuceRedisPooledFactory<>(host, port, connectionTimeout, soTimeout, infiniteSoTimeout, user,
						password, database, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier),
				wrapConnections);
	}

	public LettucePool(final URI uri, final boolean wrapConnections){
		this(new GenericObjectPoolConfig<>(), uri, wrapConnections);
	}

	public LettucePool(final URI uri, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier, final boolean wrapConnections){
		this(new GenericObjectPoolConfig<>(), uri, sslSocketFactory, sslParameters, hostnameVerifier, wrapConnections);
	}

	public LettucePool(final URI uri, final int timeout, final boolean wrapConnections){
		this(new GenericObjectPoolConfig<>(), uri, timeout, wrapConnections);
	}

	public LettucePool(final URI uri, final int timeout, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier,
					   final boolean wrapConnections){
		this(new GenericObjectPoolConfig<>(), uri, timeout, sslSocketFactory, sslParameters, hostnameVerifier,
				wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final boolean wrapConnections){
		this(poolConfig, uri, Protocol.DEFAULT_TIMEOUT, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier, final boolean wrapConnections){
		this(poolConfig, uri, Protocol.DEFAULT_TIMEOUT, sslSocketFactory, sslParameters, hostnameVerifier,
				wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final int timeout, final boolean wrapConnections){
		this(poolConfig, uri, timeout, timeout, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final int timeout, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier, final boolean wrapConnections){
		this(poolConfig, uri, timeout, timeout, sslSocketFactory, sslParameters, hostnameVerifier, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final int connectionTimeout, final int soTimeout, final boolean wrapConnections){
		this(poolConfig, uri, connectionTimeout, soTimeout, null, null, null, wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final int connectionTimeout, final int soTimeout, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier,
					   final boolean wrapConnections){
		this(poolConfig, new LettuceRedisPooledFactory<>(uri, connectionTimeout, soTimeout, null, sslSocketFactory,
				sslParameters, hostnameVerifier), wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig, final URI uri,
					   final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier, final boolean wrapConnections){
		this(poolConfig, new LettuceRedisPooledFactory<>(uri, connectionTimeout, soTimeout, infiniteSoTimeout, null,
				sslSocketFactory, sslParameters, hostnameVerifier), wrapConnections);
	}

	public LettucePool(final GenericObjectPoolConfig<StandaloneStatefulRedisConnection> poolConfig,
					   final PooledObjectFactory<StandaloneStatefulRedisConnection> factory,
					   final boolean wrapConnections){
		super(poolConfig, factory);
		this.wrapConnections = wrapConnections;

		if(wrapConnections){
			poolRef = new AtomicReference<>();
			poolRef.set(new ObjectPoolWrapper<>(this));
		}
	}

	@Override
	public StandaloneStatefulRedisConnection getResource(){
		return wrapConnections ? ConnectionUtils.wrapConnection(super.getResource(), poolRef.get())
				: super.getResource();
	}

	@Override
	public void returnResource(final StandaloneStatefulRedisConnection resource){
		if(resource != null){
			try{
				super.returnResource(ConnectionUtils.getOriginalConnection(resource));
			}catch(RuntimeException e){
				super.returnBrokenResource(resource);
				logger.warn("Resource is returned to the pool as broken", e);
			}
		}
	}

}
