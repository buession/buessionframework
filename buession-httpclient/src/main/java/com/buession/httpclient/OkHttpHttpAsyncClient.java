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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient;

import com.buession.httpclient.conn.OkHttpClientConnectionManager;
import com.buession.httpclient.conn.OkHttpNioClientConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.concurrent.Callback;
import com.buession.httpclient.exception.RequestException;
import com.buession.httpclient.okhttp.HttpClientBuilder;
import com.buession.httpclient.okhttp.OkHttpRequestBuilder;
import com.buession.httpclient.okhttp.OkHttpResponseBuilder;
import com.buession.httpclient.okhttp.nio.DefaultCallback;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * OkHttp(3) 异步 HttpClient
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class OkHttpHttpAsyncClient extends AbstractHttpAsyncClient {

	private okhttp3.OkHttpClient httpClient;

	/**
	 * 构造函数
	 */
	public OkHttpHttpAsyncClient(){
		super();
		setConnectionManager(new OkHttpNioClientConnectionManager());
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 */
	public OkHttpHttpAsyncClient(OkHttpNioClientConnectionManager connectionManager){
		super(connectionManager);
	}

	/**
	 * 构造函数
	 *
	 * @param httpClient
	 *        {@link okhttp3.OkHttpClient} 实例
	 */
	public OkHttpHttpAsyncClient(okhttp3.OkHttpClient httpClient){
		this.httpClient = httpClient;
	}

	public okhttp3.OkHttpClient getHttpClient(){
		if(httpClient == null){
			final Configuration configuration = getConnectionManager().getConfiguration();

			HttpClientBuilder builder = HttpClientBuilder.create()
					.setConnectionManager(
							((OkHttpClientConnectionManager) getConnectionManager()).getClientConnectionManager())
					.setConnectTimeout(configuration.getConnectTimeout())
					.setReadTimeout(configuration.getReadTimeout());

			if(configuration.isAllowRedirects() != null){
				builder.setFollowRedirects(configuration.isAllowRedirects());
			}

			httpClient = builder.build();
		}

		return httpClient;
	}

	public void setHttpClient(okhttp3.OkHttpClient httpClient){
		this.httpClient = httpClient;
	}

	@Override
	public Future<Response> get(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).get(), callback);
	}

	@Override
	public Future<Response> get(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).get(), readTimeout, callback);
	}

	@Override
	public Future<Response> post(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).post(data), callback);
	}

	@Override
	public Future<Response> post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								 List<Header> headers, Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).post(data), readTimeout, callback);
	}

	@Override
	public Future<Response> put(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).put(data), callback);
	}

	@Override
	public Future<Response> put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								List<Header> headers, Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).put(data), readTimeout, callback);
	}

	@Override
	public Future<Response> patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).patch(data), callback);
	}

	@Override
	public Future<Response> patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								  List<Header> headers, Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).patch(data), readTimeout, callback);
	}

	@Override
	public Future<Response> delete(URI uri, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).delete(), callback);
	}

	@Override
	public Future<Response> delete(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).delete(), readTimeout, callback);
	}

	@Override
	public Future<Response> connect(URI uri, Map<String, Object> parameters, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).connect(), callback);
	}

	@Override
	public Future<Response> connect(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).connect(), readTimeout, callback);
	}

	@Override
	public Future<Response> trace(URI uri, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).trace(), callback);
	}

	@Override
	public Future<Response> trace(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).trace(), readTimeout, callback);
	}

	@Override
	public Future<Response> copy(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).copy(), callback);
	}

	@Override
	public Future<Response> copy(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).copy(), readTimeout, callback);
	}

	@Override
	public Future<Response> move(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).move(), callback);
	}

	@Override
	public Future<Response> move(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).move(), readTimeout, callback);
	}

	@Override
	public Future<Response> head(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).head(), callback);
	}

	@Override
	public Future<Response> head(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).head(), readTimeout, callback);
	}

	@Override
	public Future<Response> options(URI uri, Map<String, Object> parameters, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).options(), callback);
	}

	@Override
	public Future<Response> options(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).options(), readTimeout, callback);
	}

	@Override
	public Future<Response> link(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).link(), callback);
	}

	@Override
	public Future<Response> link(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).link(), readTimeout, callback);
	}

	@Override
	public Future<Response> unlink(URI uri, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).unlink(), callback);
	}

	@Override
	public Future<Response> unlink(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).unlink(), readTimeout, callback);
	}

	@Override
	public Future<Response> purge(URI uri, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).purge(), callback);
	}

	@Override
	public Future<Response> purge(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).purge(), readTimeout, callback);
	}

	@Override
	public Future<Response> lock(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).lock(), callback);
	}

	@Override
	public Future<Response> lock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).lock(), readTimeout, callback);
	}

	@Override
	public Future<Response> unlock(URI uri, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).unlock(), callback);
	}

	@Override
	public Future<Response> unlock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).unlock(), readTimeout, callback);
	}

	@Override
	public Future<Response> propfind(URI uri, Map<String, Object> parameters, List<Header> headers,
									 Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).propfind(), callback);
	}

	@Override
	public Future<Response> propfind(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									 Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).propfind(), readTimeout, callback);
	}

	@Override
	public Future<Response> proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters,
									  List<Header> headers, Callback callback) throws IOException,
			RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).proppatch(data), callback);
	}

	@Override
	public Future<Response> proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
									  List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).proppatch(data), readTimeout, callback);
	}

	@Override
	public Future<Response> report(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).report(data), callback);
	}

	@Override
	public Future<Response> report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								   List<Header> headers, Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).report(data), readTimeout, callback);
	}

	@Override
	public Future<Response> view(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).view(), callback);
	}

	@Override
	public Future<Response> view(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).view(), readTimeout, callback);
	}

	@Override
	public Future<Response> wrapped(URI uri, Map<String, Object> parameters, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).wrapped(), callback);
	}

	@Override
	public Future<Response> wrapped(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									Callback callback)
			throws IOException, RequestException{
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).wrapped(), readTimeout, callback);
	}

	protected Future<Response> doRequest(final OkHttpRequestBuilder builder, final Callback callback)
			throws IOException, RequestException{
		final OkHttpRequestBuilder.OkHttpRequest request = builder.setProtocolVersion(getHttpVersion()).build();
		return doRequest(request, callback);
	}

	protected Future<Response> doRequest(final OkHttpRequestBuilder builder, final int readTimeout,
										 final Callback callback) throws IOException, RequestException{
		final OkHttpRequestBuilder.OkHttpRequest request = builder.setProtocolVersion(getHttpVersion()).build();
		return doRequest(request, callback);
	}

	protected Future<Response> doRequest(final OkHttpRequestBuilder.OkHttpRequest request,
										 final Callback callback) throws IOException, RequestException{
		okhttp3.Request okHttpRequest = request.getRequestBuilder().build();
		okhttp3.Response httpResponse = null;
		final OkHttpResponseBuilder httpResponseBuilder = new OkHttpResponseBuilder();

		getHttpClient().newCall(okHttpRequest).enqueue(new DefaultCallback(callback));
		return null;//httpResponseBuilder.build(httpResponse);
	}

}