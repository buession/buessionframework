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
package com.buession.redis.client;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author Yong.Teng
 */
public class ClientConfiguration {

	public final static String DEFAULT_HOST = "localhost";

	public final static int DEFAULT_PORT = 6379;

	public final static int DEFAULT_DATABASE = 0;

	public final static int DEFAULT_TIMEOUT = 2000;

	private String host = DEFAULT_HOST;

	private int port = DEFAULT_PORT;

	private String password;

	private int database = DEFAULT_DATABASE;

	private String clientName;

	private int connectTimeout = DEFAULT_TIMEOUT;

	private int soTimeout = DEFAULT_TIMEOUT;

	private boolean useSsl;

	private SSLSocketFactory sslSocketFactory;

	private SSLParameters sslParameters;

	private HostnameVerifier hostnameVerifier;

	private int weight;

	public String getHost(){
		return host;
	}

	public void setHost(String host){
		this.host = host;
	}

	public int getPort(){
		return port;
	}

	public void setPort(int port){
		this.port = port;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public int getDatabase(){
		return database;
	}

	public void setDatabase(int database){
		this.database = database;
	}

	public String getClientName(){
		return clientName;
	}

	public void setClientName(String clientName){
		this.clientName = clientName;
	}

	public int getConnectTimeout(){
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout){
		this.connectTimeout = connectTimeout;
	}

	public int getSoTimeout(){
		return soTimeout;
	}

	public void setSoTimeout(int soTimeout){
		this.soTimeout = soTimeout;
	}

	public boolean isUseSsl(){
		return getUseSsl();
	}

	public boolean getUseSsl(){
		return useSsl;
	}

	public void setUseSsl(boolean useSsl){
		this.useSsl = useSsl;
	}

	public SSLSocketFactory getSslSocketFactory(){
		return sslSocketFactory;
	}

	public void setSslSocketFactory(SSLSocketFactory sslSocketFactory){
		this.sslSocketFactory = sslSocketFactory;
	}

	public SSLParameters getSslParameters(){
		return sslParameters;
	}

	public void setSslParameters(SSLParameters sslParameters){
		this.sslParameters = sslParameters;
	}

	public HostnameVerifier getHostnameVerifier(){
		return hostnameVerifier;
	}

	public void setHostnameVerifier(HostnameVerifier hostnameVerifier){
		this.hostnameVerifier = hostnameVerifier;
	}

	public int getWeight(){
		return weight;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}

}