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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author Yong.Teng
 */
public class JedisPoolDataSource extends AbstractJedisRedisDataSource<Jedis> implements JedisDataSource,
		PoolJedisDataSource<Jedis> {

	private JedisPoolConfig poolConfig;

	private JedisPool pool;

	private final static Logger logger = LoggerFactory.getLogger(JedisPoolDataSource.class);

	public JedisPoolDataSource(){
		super();
	}

	public JedisPoolDataSource(String host, JedisPoolConfig poolConfig){
		super(host);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, String password, JedisPoolConfig poolConfig){
		super(host, password);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int database, JedisPoolConfig poolConfig){
		super(host, database);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, String password, int database, JedisPoolConfig poolConfig){
		super(host, password, database);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, String password, JedisPoolConfig poolConfig){
		super(host, port, password);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, int database, JedisPoolConfig poolConfig){
		super(host, port, database);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, String password, int database, JedisPoolConfig poolConfig){
		super(host, port, password, database);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, String password, int database, int connectTimeout, int soTimeout
			, JedisPoolConfig poolConfig){
		super(host, port, password, database, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, String password, int database, int connectTimeout, int soTimeout
			, boolean useSsl, JedisPoolConfig poolConfig){
		super(host, port, password, database, connectTimeout, soTimeout, useSsl);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, String password, int database, int connectTimeout, int soTimeout
			, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier,
							   JedisPoolConfig poolConfig){
		super(host, port, password, database, connectTimeout, soTimeout, sslSocketFactory, sslParameters,
				hostnameVerifier);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, String password, int database, int connectTimeout, int soTimeout
			, boolean useSsl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters,
							   HostnameVerifier hostnameVerifier, JedisPoolConfig poolConfig){
		super(host, port, password, database, connectTimeout, soTimeout, useSsl, sslSocketFactory, sslParameters,
				hostnameVerifier);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, String password, int database, String clientName, boolean useSsl
			, JedisPoolConfig poolConfig){
		super(host, port, password, database, clientName, useSsl);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, String password, int database, String clientName,
							   SSLSocketFactory sslSocketFactory, SSLParameters sslParameters,
							   HostnameVerifier hostnameVerifier, JedisPoolConfig poolConfig){
		super(host, port, password, database, clientName, sslSocketFactory, sslParameters, hostnameVerifier);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, String password, int database, String clientName,
							   int connectTimeout, int soTimeout, boolean useSsl, JedisPoolConfig poolConfig){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, useSsl);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, String password, int database, String clientName,
							   int connectTimeout, int soTimeout, SSLSocketFactory sslSocketFactory,
							   SSLParameters sslParameters, HostnameVerifier hostnameVerifier,
							   JedisPoolConfig poolConfig){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, sslSocketFactory, sslParameters,
				hostnameVerifier);
		this.poolConfig = poolConfig;
	}

	public JedisPoolDataSource(String host, int port, String password, int database, String clientName,
							   int connectTimeout, int soTimeout, boolean useSsl, SSLSocketFactory sslSocketFactory,
							   SSLParameters sslParameters, HostnameVerifier hostnameVerifier,
							   JedisPoolConfig poolConfig){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, useSsl, sslSocketFactory,
				sslParameters, hostnameVerifier);
		this.poolConfig = poolConfig;
	}

	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

	@Override
	public JedisPool getPool(){
		if(pool == null){
			pool = new JedisPool(getPoolConfig(), getHost(), getPort(), getConnectTimeout(), getSoTimeout(),
					getPassword(), getDatabase(), getClientName(), isUseSsl(), getSslSocketFactory(),
					getSslParameters(), getHostnameVerifier());

			logger.info("JedisPool initialize with db {} success, name: {}.", getDatabase(), getClientName());
		}

		return pool;
	}

}
