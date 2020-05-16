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
package com.buession.httpclient.okhttp;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author Yong.Teng
 */
public class HttpClientBuilder {

	private OkHttpClientConnectionManager connectionManager;

	private long connectTimeout = -1;

	private long readTimeout = -1;

	private Boolean followRedirects;

	private HttpClientBuilder(){
	}

	public final static HttpClientBuilder create(){
		return new HttpClientBuilder();
	}

	public HttpClientBuilder setConnectionManager(OkHttpClientConnectionManager connectionManager){
		this.connectionManager = connectionManager;
		return this;
	}

	public HttpClientBuilder setConnectTimeout(long connectTimeout){
		this.connectTimeout = connectTimeout;
		return this;
	}

	public HttpClientBuilder setReadTimeout(long readTimeout){
		this.readTimeout = readTimeout;
		return this;
	}

	public HttpClientBuilder setFollowRedirects(Boolean followRedirects){
		this.followRedirects = followRedirects;
		return this;
	}

	public OkHttpClient build(){
		OkHttpClient.Builder okHttpClientBuilder = new okhttp3.OkHttpClient.Builder();

		if(connectTimeout > -1){
			okHttpClientBuilder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
		}

		if(readTimeout > -1){
			okHttpClientBuilder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
		}

		if(followRedirects != null){
			okHttpClientBuilder.followRedirects(followRedirects);
		}

		okHttpClientBuilder.connectionPool(connectionManager.getConnectionPool());

		return okHttpClientBuilder.build();
	}

}
