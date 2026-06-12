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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.httpclient.conn.JdkHttpNioClientConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.concurrent.Callback;
import com.buession.httpclient.exception.RequestException;
import com.buession.httpclient.jdk.JdkHttpRequestBuilder;
import com.buession.httpclient.jdk.JdkHttpResponseBuilder;
import com.buession.httpclient.jdk.JdkNioHttpClientConnectionManager;
import com.buession.httpclient.jdk.nio.DefaultCallback;
import com.buession.net.ssl.SslConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * JDK {@link java.net.http.HttpClient} 异步
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class JdkHttpAsyncClient extends AbstractHttpAsyncClient {

	private java.net.http.HttpClient httpClient;

	private final static Logger logger = LoggerFactory.getLogger(JdkHttpAsyncClient.class);

	/**
	 * 构造函数
	 */
	public JdkHttpAsyncClient() {
		super();
		setConnectionManager(new JdkHttpNioClientConnectionManager());
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 */
	public JdkHttpAsyncClient(JdkNioHttpClientConnectionManager connectionManager) {
		super(connectionManager);
	}

	/**
	 * 构造函数
	 *
	 * @param httpClient
	 * 		OkHttp Client
	 */
	public JdkHttpAsyncClient(java.net.http.HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public java.net.http.HttpClient getHttpClient() {
		if(httpClient == null){
			final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
			final JdkNioHttpClientConnectionManager jdkHttpClientConnectionManager =
					(JdkNioHttpClientConnectionManager) getConnectionManager();
			final Configuration configuration = jdkHttpClientConnectionManager.getConfiguration();
			final SslConfiguration sslConfiguration = configuration.getSslConfiguration();

			final java.net.http.HttpClient.Builder builder = java.net.http.HttpClient.newBuilder();

			propertyMapper.from(configuration.getConnectTimeout()).as(Duration::ofMillis).to(builder::connectTimeout);
			if(Objects.equals(configuration.isAllowRedirects(), Boolean.TRUE)){
				builder.followRedirects(java.net.http.HttpClient.Redirect.ALWAYS);
			}

			if(sslConfiguration != null){
				propertyMapper.from(sslConfiguration.getSslContext()).to(builder::sslContext);
				propertyMapper.from(sslConfiguration.getSslParameters()).to(builder::sslParameters);
			}

			httpClient = builder.build();
		}

		return httpClient;
	}

	public void setHttpClient(java.net.http.HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public void get(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).get(), callback);
	}

	@Override
	public void get(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).get(), readTimeout, callback);
	}

	@Override
	public void post(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
	                 Callback callback) throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).post(data), callback);
	}

	@Override
	public void post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
	                 List<Header> headers, Callback callback) throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).post(data), readTimeout, callback);
	}

	@Override
	public void put(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
	                Callback callback) throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).put(data), callback);
	}

	@Override
	public void put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
	                List<Header> headers, Callback callback) throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).put(data), readTimeout, callback);
	}

	@Override
	public void patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
	                  Callback callback) throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).patch(data), callback);
	}

	@Override
	public void patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
	                  List<Header> headers, Callback callback) throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).patch(data), readTimeout, callback);
	}

	@Override
	public void delete(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).delete(), callback);
	}

	@Override
	public void delete(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
	                   Callback callback) throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).delete(), readTimeout, callback);
	}

	@Override
	public void connect(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).connect(), callback);
	}

	@Override
	public void connect(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
	                    Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).connect(), readTimeout, callback);
	}

	@Override
	public void trace(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).trace(), callback);
	}

	@Override
	public void trace(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).trace(), readTimeout, callback);
	}

	@Override
	public void copy(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).copy(), callback);
	}

	@Override
	public void copy(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).copy(), readTimeout, callback);
	}

	@Override
	public void move(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).move(), callback);
	}

	@Override
	public void move(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).move(), readTimeout, callback);
	}

	@Override
	public void head(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).head(), callback);
	}

	@Override
	public void head(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).head(), readTimeout, callback);
	}

	@Override
	public void options(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).options(), callback);
	}

	@Override
	public void options(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
	                    Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).options(), readTimeout, callback);
	}

	@Override
	public void link(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).link(), callback);
	}

	@Override
	public void link(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).link(), readTimeout, callback);
	}

	@Override
	public void unlink(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).unlink(), callback);
	}

	@Override
	public void unlink(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
	                   Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).unlink(), readTimeout, callback);
	}

	@Override
	public void purge(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).purge(), callback);
	}

	@Override
	public void purge(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).purge(), readTimeout, callback);
	}

	@Override
	public void lock(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).lock(), callback);
	}

	@Override
	public void lock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).lock(), readTimeout, callback);
	}

	@Override
	public void unlock(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).unlock(), callback);
	}

	@Override
	public void unlock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
	                   Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).unlock(), readTimeout, callback);
	}

	@Override
	public void propfind(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).propfind(), callback);
	}

	@Override
	public void propfind(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
	                     Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).propfind(), readTimeout, callback);
	}

	@Override
	public void proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
	                      Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).proppatch(data), callback);
	}

	@Override
	public void proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
	                      List<Header> headers, Callback callback) throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).proppatch(data), readTimeout, callback);
	}

	@Override
	public void report(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
	                   Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).report(data), callback);
	}

	@Override
	public void report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
	                   List<Header> headers, Callback callback) throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).report(data), readTimeout, callback);
	}

	@Override
	public void view(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).view(), callback);
	}

	@Override
	public void view(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).view(), readTimeout, callback);
	}

	@Override
	public void wrapped(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).wrapped(), callback);
	}

	@Override
	public void wrapped(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
	                    Callback callback) throws IOException, RequestException {
		doRequest(JdkHttpRequestBuilder.create(uri, parameters, headers).wrapped(), readTimeout, callback);
	}

	protected void doRequest(final JdkHttpRequestBuilder builder, final Callback callback)
			throws IOException, RequestException {
		final HttpRequest request = builder.setProtocolVersion(getHttpVersion()).build();
		doRequest(request, callback);
	}

	protected void doRequest(final JdkHttpRequestBuilder builder, final int readTimeout, final Callback callback)
			throws IOException, RequestException {
		final HttpRequest request = builder.setProtocolVersion(getHttpVersion()).timeout(readTimeout).build();
		doRequest(request, callback);
	}

	protected void doRequest(final HttpRequest request, final Callback callback) throws IOException, RequestException {
		CompletableFuture<HttpResponse<byte[]>> future = httpClient.sendAsync(request,
				HttpResponse.BodyHandlers.ofByteArray());
		DefaultCallback defaultCallback = new DefaultCallback(callback);

		future.thenAccept((response)->{
			JdkHttpResponseBuilder httpResponseBuilder = new JdkHttpResponseBuilder();
			defaultCallback.completed(httpResponseBuilder.build(response));
		}).exceptionally((throwable)->{
			defaultCallback.failed((Exception) throwable);
			return null;
		});
	}

}
