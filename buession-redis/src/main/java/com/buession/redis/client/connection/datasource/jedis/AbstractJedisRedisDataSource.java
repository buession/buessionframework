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
package com.buession.redis.client.connection.datasource.jedis;

import com.buession.redis.client.connection.datasource.AbstractRedisDataSource;
import redis.clients.jedis.commands.JedisCommands;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisDataSource<T extends JedisCommands> extends AbstractRedisDataSource {

	public AbstractJedisRedisDataSource(){
		super();
	}

	public AbstractJedisRedisDataSource(String host){
		super(host);
	}

	public AbstractJedisRedisDataSource(String host, String password){
		super(host, password);
	}

	public AbstractJedisRedisDataSource(String host, int database){
		super(host, database);
	}

	public AbstractJedisRedisDataSource(String host, String password, int database){
		super(host, password, database);
	}

	public AbstractJedisRedisDataSource(String host, int port, String password){
		super(host, port, password);
	}

	public AbstractJedisRedisDataSource(String host, int port, int database){
		super(host, port, database);
	}

	public AbstractJedisRedisDataSource(String host, int port, String password, int database){
		super(host, port, password, database);
	}

	public AbstractJedisRedisDataSource(String host, int port, String password, int database, int connectTimeout,
										int soTimeout){
		super(host, port, password, database, connectTimeout, soTimeout);
	}

	public AbstractJedisRedisDataSource(String host, int port, String password, int database, int connectTimeout,
										int soTimeout, boolean useSsl){
		super(host, port, password, database, connectTimeout, soTimeout, useSsl);
	}

	public AbstractJedisRedisDataSource(String host, int port, String password, int database, int connectTimeout,
										int soTimeout, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters,
										HostnameVerifier hostnameVerifier){
		super(host, port, password, database, connectTimeout, soTimeout, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public AbstractJedisRedisDataSource(String host, int port, String password, int database, int connectTimeout,
										int soTimeout, boolean useSsl, SSLSocketFactory sslSocketFactory,
										SSLParameters sslParameters, HostnameVerifier hostnameVerifier){
		super(host, port, password, database, connectTimeout, soTimeout, useSsl, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public AbstractJedisRedisDataSource(String host, int port, String password, int database, String clientName,
										boolean useSsl){
		super(host, port, password, database, clientName, useSsl);
	}

	public AbstractJedisRedisDataSource(String host, int port, String password, int database, String clientName,
										SSLSocketFactory sslSocketFactory, SSLParameters sslParameters,
										HostnameVerifier hostnameVerifier){
		super(host, port, password, database, clientName, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public AbstractJedisRedisDataSource(String host, int port, String password, int database, String clientName,
										int connectTimeout, int soTimeout, boolean useSsl){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, useSsl);
	}

	public AbstractJedisRedisDataSource(String host, int port, String password, int database, String clientName,
										int connectTimeout, int soTimeout, SSLSocketFactory sslSocketFactory,
										SSLParameters sslParameters, HostnameVerifier hostnameVerifier){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public AbstractJedisRedisDataSource(String host, int port, String password, int database, String clientName,
										int connectTimeout, int soTimeout, boolean useSsl,
										SSLSocketFactory sslSocketFactory, SSLParameters sslParameters,
										HostnameVerifier hostnameVerifier){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, useSsl, sslSocketFactory,
				sslParameters, hostnameVerifier);
	}

}
