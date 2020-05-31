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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package io.lettuce.core;

import com.buession.redis.core.Server;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;

/**
 * @author Yong.Teng
 */
public class LettucePool extends AbstractLettucePool {

	public LettucePool(){
		this(Server.DEFAULT_HOST, Server.DEFAULT_PORT);
	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host){
		this(poolConfig, host, Server.DEFAULT_PORT);
	}

	public LettucePool(String host, int port){
		this(new GenericObjectPoolConfig(), host, port);
	}

	public LettucePool(final String host){
		RedisURI uri = RedisURI.builder().withHost(host).build();

	}

	public LettucePool(final String host, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){

	}

	public LettucePool(final URI uri){

	}

	public LettucePool(final URI uri, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){

	}

	public LettucePool(final URI uri, final int timeout){

	}

	public LettucePool(final URI uri, final int timeout, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifie){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
					   final String password){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
					   final String password, final boolean ssl){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
					   final String password, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, final int port){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, final int port, final boolean ssl){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, final int port, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, final int port, final int timeout){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, final int port, final int timeout,
					   final boolean ssl){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, final int port, final int timeout,
					   final boolean ssl, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
					   final String password, final int database){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
					   final String password, final int database, final boolean ssl){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
					   final String password, final int database, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
					   final String password, final int database, final String clientName){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
					   final String password, final int database, final String clientName, final boolean ssl){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
					   final String password, final int database, final String clientName, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, int port,
					   final int connectionTimeout, final int soTimeout, final String password, final int database,
					   final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig){

	}

	public LettucePool(final String host, final int port, final boolean ssl){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, int port,
					   final int connectionTimeout, final int soTimeout, final String password, final int database,
					   final String clientName){

	}

	public LettucePool(final String host, final int port, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final String host, final int port,
					   final int connectionTimeout, final int soTimeout, final String password, final int database,
					   final String clientName, final boolean ssl){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final URI uri){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final URI uri,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final URI uri, final int timeout){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final URI uri, final int timeout,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final URI uri, final int connectionTimeout,
					   final int soTimeout){

	}

	public LettucePool(final GenericObjectPoolConfig poolConfig, final URI uri, final int connectionTimeout,
					   final int soTimeout, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters
			, final HostnameVerifier hostnameVerifier){

	}

}
