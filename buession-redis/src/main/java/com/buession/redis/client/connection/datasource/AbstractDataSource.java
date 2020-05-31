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
package com.buession.redis.client.connection.datasource;

import com.buession.redis.Constants;
import com.buession.redis.core.Server;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

/**
 * Redis 数据源抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractDataSource implements DataSource {

	private int database = Server.DEFAULT_DATABASE;

	private int connectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

	private int soTimeout = Constants.DEFAULT_SO_TIMEOUT;

	private boolean useSsl;

	private SSLSocketFactory sslSocketFactory;

	private SSLParameters sslParameters;

	private HostnameVerifier hostnameVerifier;

	@Override
	public int getDatabase(){
		return database;
	}

	@Override
	public void setDatabase(int database){
		this.database = database;
	}

	@Override
	public int getConnectTimeout(){
		return connectTimeout;
	}

	@Override
	public void setConnectTimeout(int connectTimeout){
		this.connectTimeout = connectTimeout;
	}

	@Override
	public int getSoTimeout(){
		return soTimeout;
	}

	@Override
	public void setSoTimeout(int soTimeout){
		this.soTimeout = soTimeout;
	}

	@Override
	public boolean isUseSsl(){
		return useSsl;
	}

	@Override
	public void setUseSsl(boolean useSsl){
		this.useSsl = useSsl;
	}

	@Override
	public SSLSocketFactory getSslSocketFactory(){
		return sslSocketFactory;
	}

	@Override
	public void setSslSocketFactory(SSLSocketFactory sslSocketFactory){
		this.sslSocketFactory = sslSocketFactory;
	}

	@Override
	public SSLParameters getSslParameters(){
		return sslParameters;
	}

	@Override
	public void setSslParameters(SSLParameters sslParameters){
		this.sslParameters = sslParameters;
	}

	@Override
	public HostnameVerifier getHostnameVerifier(){
		return hostnameVerifier;
	}

	@Override
	public void setHostnameVerifier(HostnameVerifier hostnameVerifier){
		this.hostnameVerifier = hostnameVerifier;
	}

}
