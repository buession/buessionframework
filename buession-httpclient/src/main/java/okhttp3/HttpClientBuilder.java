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
package okhttp3;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class HttpClientBuilder {

	private final OkHttpClient.Builder builder = new OkHttpClient.Builder();

	private HttpClientConnectionManager connectionManager;

	protected HttpClientBuilder() {
	}

	public static HttpClientBuilder create() {
		return new HttpClientBuilder();
	}

	public HttpClientBuilder setRetryOnConnectionFailure(Boolean retryOnConnectionFailure) {
		Optional.ofNullable(retryOnConnectionFailure).ifPresent(builder::retryOnConnectionFailure);
		return this;
	}

	public HttpClientBuilder setConnectTimeout(long connectTimeout) {
		if(connectTimeout > -1){
			builder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
		}

		return this;
	}

	public HttpClientBuilder setReadTimeout(long readTimeout) {
		if(readTimeout > 0){
			builder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
		}

		return this;
	}

	public HttpClientBuilder setWriteTimeout(long writeTimeout) {
		if(writeTimeout > 0){
			builder.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
		}

		return this;
	}

	public HttpClientBuilder setFollowRedirects(Boolean followRedirects) {
		Optional.ofNullable(followRedirects).ifPresent(builder::followRedirects);
		Optional.ofNullable(followRedirects).ifPresent(builder::followSslRedirects);
		return this;
	}

	public HttpClientBuilder setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
		Optional.ofNullable(sslSocketFactory).ifPresent(builder::sslSocketFactory);
		return this;
	}

	public HttpClientBuilder setSSLHostnameVerifier(HostnameVerifier hostnameVerifier) {
		Optional.ofNullable(hostnameVerifier).ifPresent(builder::hostnameVerifier);
		return this;
	}

	public HttpClientBuilder setSSLContext(SSLContext sslContext) {
		return this;
	}

	public HttpClientBuilder setConnectionManager(HttpClientConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		return this;
	}

	public OkHttpClient build() {
		if(connectionManager != null){
			builder.connectionPool(connectionManager.getConnectionPool());
		}

		OkHttpClient client = builder.build();

		if(connectionManager != null){
			if(connectionManager.getMaxRequests() > 0){
				client.dispatcher().setMaxRequests(connectionManager.getMaxRequests());
			}
			if(connectionManager.getMaxRequestsPerHost() > 0){
				client.dispatcher().setMaxRequestsPerHost(connectionManager.getMaxRequestsPerHost());
			}
		}

		return client;
	}

}
