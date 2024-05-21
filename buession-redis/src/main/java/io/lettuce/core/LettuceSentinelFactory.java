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
package io.lettuce.core;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.ByteArrayCodec;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

/**
 * Lettuce 连接池对象工厂
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceFactory extends AbstractLettuceFactory<StatefulRedisConnection<byte[], byte[]>> {

	public LettuceFactory(final String host, final int port) {
		super(host, port);
	}

	public LettuceFactory(final String host, final int port, int database) {
		super(host, port, database);
	}

	public LettuceFactory(final String host, final int port, final String user, final String password) {
		super(host, port, user, password);
	}

	public LettuceFactory(final String host, final int port, final String clientName) {
		super(host, port, clientName);
	}

	public LettuceFactory(final String host, final int port, int database, final String clientName) {
		super(host, port, database, clientName);
	}

	public LettuceFactory(final String host, final int port, final String user, final String password,
						  final String clientName) {
		super(host, port, user, password, clientName);
	}

	public LettuceFactory(final String host, final int port, final String user, final String password, int database,
						  final String clientName) {
		super(host, port, user, password, database, clientName);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout) {
		super(host, port, connectionTimeout, soTimeout);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  int database) {
		super(host, port, connectionTimeout, soTimeout, database);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password) {
		super(host, port, connectionTimeout, soTimeout, user, password);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password, int database) {
		this(host, port, connectionTimeout, soTimeout, user, password, database, null);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String clientName) {
		super(host, port, connectionTimeout, soTimeout, clientName);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  int database, final String clientName) {
		super(host, port, connectionTimeout, soTimeout, database, clientName);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password, final String clientName) {
		super(host, port, connectionTimeout, soTimeout, user, password, clientName);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password, int database, final String clientName) {
		super(host, port, connectionTimeout, soTimeout, user, password, database, clientName);
	}

	public LettuceFactory(final String host, final int port, final boolean ssl) {
		super(host, port, ssl);
	}

	public LettuceFactory(final String host, final int port, int database, final boolean ssl) {
		super(host, port, database, ssl);
	}

	public LettuceFactory(final String host, final int port, final String user, final String password,
						  final boolean ssl) {
		super(host, port, user, password, ssl);
	}

	public LettuceFactory(final String host, final int port, final String clientName, final boolean ssl) {
		super(host, port, clientName, ssl);
	}

	public LettuceFactory(final String host, final int port, int database, final String clientName, final boolean ssl) {
		super(host, port, database, clientName, ssl);
	}

	public LettuceFactory(final String host, final int port, final String user, final String password,
						  final String clientName, final boolean ssl) {
		super(host, port, user, password, clientName, ssl);
	}

	public LettuceFactory(final String host, final int port, final String user, final String password, int database,
						  final String clientName, final boolean ssl) {
		super(host, port, user, password, database, clientName, ssl);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final boolean ssl) {
		super(host, port, connectionTimeout, soTimeout, ssl);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  int database, final boolean ssl) {
		super(host, port, connectionTimeout, soTimeout, database, ssl);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password, final boolean ssl) {
		super(host, port, connectionTimeout, soTimeout, user, password, ssl);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password, int database, final boolean ssl) {
		super(host, port, connectionTimeout, soTimeout, database, ssl);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String clientName, final boolean ssl) {
		super(host, port, connectionTimeout, soTimeout, clientName, ssl);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  int database, final String clientName, final boolean ssl) {
		super(host, port, connectionTimeout, soTimeout, database, clientName, ssl);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password, final String clientName, final boolean ssl) {
		super(host, port, connectionTimeout, soTimeout, user, password, clientName, ssl);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password, int database, final String clientName,
						  final boolean ssl) {
		super(host, port, connectionTimeout, soTimeout, user, password, database, clientName, ssl);
	}

	public LettuceFactory(final String host, final int port, final boolean ssl, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(host, port, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, int database, final boolean ssl,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(host, port, database, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final String user, final String password,
						  final boolean ssl, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(host, port, user, password, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final String clientName, final boolean ssl,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(host, port, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, int database, final String clientName, final boolean ssl,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(host, port, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final String user, final String password,
						  final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(host, port, user, password, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final String user, final String password, int database,
						  final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(host, port, user, password, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final boolean ssl, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(host, port, connectionTimeout, soTimeout, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  int database, final boolean ssl, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(host, port, connectionTimeout, soTimeout, database, ssl, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password, final boolean ssl,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(host, port, connectionTimeout, soTimeout, user, password, ssl, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password, int database, final boolean ssl,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(host, port, connectionTimeout, soTimeout, user, password, database, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(host, port, connectionTimeout, soTimeout, clientName, ssl, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  int database, final String clientName, final boolean ssl,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(host, port, connectionTimeout, soTimeout, database, clientName, ssl, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password, final String clientName, final boolean ssl,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(host, port, connectionTimeout, soTimeout, user, password, clientName, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final String host, final int port, final int connectionTimeout, final int soTimeout,
						  final String user, final String password, int database, final String clientName,
						  final boolean ssl, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(host, port, connectionTimeout, soTimeout, user, password, database, clientName, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri) {
		super(uri);
	}

	public LettuceFactory(final RedisURI uri, int database) {
		super(uri, database);
	}

	public LettuceFactory(final RedisURI uri, final String clientName) {
		super(uri, clientName);
	}

	public LettuceFactory(final RedisURI uri, int database, final String clientName) {
		super(uri, database, clientName);
	}

	public LettuceFactory(final RedisURI uri, final String user, final String password) {
		super(uri, user, password);
	}

	public LettuceFactory(final RedisURI uri, final String user, final String password, int database) {
		super(uri, user, password, database);
	}

	public LettuceFactory(final RedisURI uri, final String user, final String password, final String clientName) {
		super(uri, user, password, clientName);
	}

	public LettuceFactory(final RedisURI uri, final String user, final String password, int database,
						  final String clientName) {
		super(uri, user, password, database, clientName);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout) {
		super(uri, connectionTimeout, soTimeout);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, int database) {
		super(uri, connectionTimeout, soTimeout, database);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, final String user,
						  final String password) {
		super(uri, connectionTimeout, soTimeout, user, password);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, final String user,
						  final String password, int database) {
		super(uri, connectionTimeout, soTimeout, user, password, database);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout,
						  final String clientName) {
		super(uri, connectionTimeout, soTimeout, clientName);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, int database,
						  final String clientName) {
		super(uri, connectionTimeout, soTimeout, database, clientName);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, final String user,
						  final String password, final String clientName) {
		super(uri, connectionTimeout, soTimeout, user, password, clientName);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, final String user,
						  final String password, int database, final String clientName) {
		super(uri, connectionTimeout, soTimeout, user, password, database, clientName);
	}

	public LettuceFactory(final RedisURI uri, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(uri, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, int database, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(uri, database, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final String clientName, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(uri, clientName, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, int database, final String clientName,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(uri, database, clientName, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final String user, final String password,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(uri, user, password, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final String user, final String password, int database,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(uri, user, password, database, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final String user, final String password, final String clientName,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(uri, user, password, clientName, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final String user, final String password, int database,
						  final String clientName, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(uri, user, password, database, clientName, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(uri, connectionTimeout, soTimeout, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, int database,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(uri, connectionTimeout, soTimeout, database, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, final String user,
						  final String password, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(uri, connectionTimeout, soTimeout, user, password, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, final String user,
						  final String password, int database, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(uri, connectionTimeout, soTimeout, user, password, database, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout,
						  final String clientName, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(uri, connectionTimeout, soTimeout, clientName, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, int database,
						  final String clientName, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(uri, connectionTimeout, soTimeout, clientName, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, final String user,
						  final String password, final String clientName, final SSLSocketFactory sslSocketFactory,
						  final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		super(uri, connectionTimeout, soTimeout, user, password, clientName, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public LettuceFactory(final RedisURI uri, final int connectionTimeout, final int soTimeout, final String user,
						  final String password, int database, final String clientName,
						  final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
						  final HostnameVerifier hostnameVerifier) {
		super(uri, connectionTimeout, soTimeout, user, password, database, clientName, sslSocketFactory,
				sslParameters, hostnameVerifier);
	}

	@Override
	public StatefulRedisConnection<byte[], byte[]> create() throws Exception {
		return getClient().connect(new ByteArrayCodec());
	}

}
