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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.okhttp;

import okhttp3.HttpClientConnectionManager;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author Yong.Teng
 */
public class HttpClientBuilder {

	private final OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();

	private HttpClientBuilder(){
	}

	public static HttpClientBuilder create(){
		return new HttpClientBuilder();
	}

	public HttpClientBuilder setConnectionManager(HttpClientConnectionManager connectionManager){
		builder.connectionPool(connectionManager.getConnectionPool());
		return this;
	}

	public HttpClientBuilder setConnectTimeout(long connectTimeout){
		if(connectTimeout > -1){
			builder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
		}

		return this;
	}

	public HttpClientBuilder setReadTimeout(long readTimeout){
		if(readTimeout > -1){
			builder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
		}

		return this;
	}

	public HttpClientBuilder setFollowRedirects(Boolean followRedirects){
		if(followRedirects != null){
			builder.followRedirects(followRedirects);
		}

		return this;
	}

	public OkHttpClient build(){
		return builder.build();
	}

}
