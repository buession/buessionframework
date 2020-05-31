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

import com.buession.redis.core.Server;
import redis.clients.jedis.Jedis;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

/**
 * Jedis 单节点数据源抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractGenericJedisDataSource extends AbstractJedisDataSource<Jedis> implements JedisDataSource {

	private String host = Server.DEFAULT_HOST;

	private int port = Server.DEFAULT_PORT;

	private String password;

	private String clientName;

	public AbstractGenericJedisDataSource(){
		super();
	}

	public AbstractGenericJedisDataSource(String host){
		this.host = host;
	}

	public AbstractGenericJedisDataSource(String host, String password){
		this.host = host;
		this.password = password;
	}

	public AbstractGenericJedisDataSource(String host, int database){
		this.host = host;
		setDatabase(database);
	}

	public AbstractGenericJedisDataSource(String host, String password, int database){
		this(host, password);
		setDatabase(database);
	}

	public AbstractGenericJedisDataSource(String host, int port, String password){
		this(host, password);
		this.port = port;
	}

	public AbstractGenericJedisDataSource(String host, int port, int database){
		this(host, database);
		this.port = port;
	}

	public AbstractGenericJedisDataSource(String host, int port, String password, int database){
		this(host, password, database);
		this.port = port;
	}

	public AbstractGenericJedisDataSource(String host, int port, String password, int database, int connectTimeout,
										  int soTimeout){
		this(host, port, password, database);
		setConnectTimeout(connectTimeout);
		setSoTimeout(soTimeout);
	}

	public AbstractGenericJedisDataSource(String host, int port, String password, int database, int connectTimeout,
										  int soTimeout, boolean useSsl){
		this(host, port, password, database);
		setConnectTimeout(connectTimeout);
		setSoTimeout(soTimeout);
		setUseSsl(useSsl);
	}

	public AbstractGenericJedisDataSource(String host, int port, String password, int database, int connectTimeout,
										  int soTimeout, SSLSocketFactory sslSocketFactory,
										  SSLParameters sslParameters, HostnameVerifier hostnameVerifier){
		this(host, port, password, database, connectTimeout, soTimeout, checkUseSSL(sslSocketFactory, sslParameters,
				hostnameVerifier), sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public AbstractGenericJedisDataSource(String host, int port, String password, int database, int connectTimeout,
										  int soTimeout, boolean useSsl, SSLSocketFactory sslSocketFactory,
										  SSLParameters sslParameters, HostnameVerifier hostnameVerifier){
		this(host, port, password, database, connectTimeout, soTimeout, useSsl);
		setSslSocketFactory(sslSocketFactory);
		setSslParameters(sslParameters);
		setHostnameVerifier(hostnameVerifier);
	}

	public AbstractGenericJedisDataSource(String host, int port, String password, int database, String clientName,
										  boolean useSsl){
		this(host, port, password, database);
		this.clientName = clientName;
		setUseSsl(useSsl);
	}

	public AbstractGenericJedisDataSource(String host, int port, String password, int database, String clientName,
										  SSLSocketFactory sslSocketFactory, SSLParameters sslParameters,
										  HostnameVerifier hostnameVerifier){
		this(host, port, password, database, clientName, checkUseSSL(sslSocketFactory, sslParameters,
				hostnameVerifier));
		setSslSocketFactory(sslSocketFactory);
		setSslParameters(sslParameters);
		setHostnameVerifier(hostnameVerifier);
	}

	public AbstractGenericJedisDataSource(String host, int port, String password, int database, String clientName,
										  int connectTimeout, int soTimeout, boolean useSsl){
		this(host, port, password, database, clientName, useSsl);
		setConnectTimeout(connectTimeout);
		setSoTimeout(soTimeout);
	}

	public AbstractGenericJedisDataSource(String host, int port, String password, int database, String clientName,
										  int connectTimeout, int soTimeout, SSLSocketFactory sslSocketFactory,
										  SSLParameters sslParameters, HostnameVerifier hostnameVerifier){
		this(host, port, password, database, clientName, connectTimeout, soTimeout, checkUseSSL(sslSocketFactory,
				sslParameters, hostnameVerifier), sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public AbstractGenericJedisDataSource(String host, int port, String password, int database, String clientName,
										  int connectTimeout, int soTimeout, boolean useSsl,
										  SSLSocketFactory sslSocketFactory, SSLParameters sslParameters,
										  HostnameVerifier hostnameVerifier){
		this(host, port, password, database, clientName, connectTimeout, soTimeout, useSsl);
		setSslSocketFactory(sslSocketFactory);
		setSslParameters(sslParameters);
		setHostnameVerifier(hostnameVerifier);
	}

	@Override
	public String getHost(){
		return host;
	}

	@Override
	public void setHost(String host){
		this.host = host;
	}

	@Override
	public int getPort(){
		return port;
	}

	@Override
	public void setPort(int port){
		this.port = port;
	}

	@Override
	public String getPassword(){
		return password;
	}

	@Override
	public void setPassword(String password){
		this.password = password;
	}

	@Override
	public String getClientName(){
		return clientName;
	}

	@Override
	public void setClientName(String clientName){
		this.clientName = clientName;
	}

}
