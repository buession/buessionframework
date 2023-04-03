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

import com.buession.httpclient.apache.ApacheRequestBuilder;
import com.buession.httpclient.apache.nio.protocol.BasicAsyncResponseConsumer;
import com.buession.httpclient.conn.ApacheNioClientConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.concurrent.FutureCallback;
import com.buession.httpclient.exception.RequestException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Apache HttpComponents 异步 HttpClient
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class ApacheHttpAsyncClient extends AbstractHttpAsyncClient {

	private RequestConfig requestConfig;

	private org.apache.http.nio.client.HttpAsyncClient httpAsyncClient;

	private final static Logger logger = LoggerFactory.getLogger(ApacheHttpClient.class);

	/**
	 * 构造函数
	 */
	public ApacheHttpAsyncClient(){
		super();
		setConnectionManager(new ApacheNioClientConnectionManager());
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 */
	public ApacheHttpAsyncClient(ApacheNioClientConnectionManager connectionManager){
		super(connectionManager);
	}

	/**
	 * 构造函数
	 *
	 * @param httpAsyncClient
	 *        {@link org.apache.http.nio.client.HttpAsyncClient} 实例
	 */
	public ApacheHttpAsyncClient(org.apache.http.nio.client.HttpAsyncClient httpAsyncClient){
		this.httpAsyncClient = httpAsyncClient;
	}

	/**
	 * 构造函数
	 *
	 * @param httpAsyncClient
	 *        {@link org.apache.http.nio.client.HttpAsyncClient} 实例
	 * @param requestConfig
	 * 		请求配置
	 */
	public ApacheHttpAsyncClient(org.apache.http.nio.client.HttpAsyncClient httpAsyncClient,
								 RequestConfig requestConfig){
		this.httpAsyncClient = httpAsyncClient;
		this.requestConfig = requestConfig;
	}

	public RequestConfig getRequestConfig(){
		if(requestConfig == null){
			final Configuration configuration = getConnectionManager().getConfiguration();

			RequestConfig.Builder builder = RequestConfig.custom().setConnectTimeout(configuration.getConnectTimeout())
					.setConnectionRequestTimeout(configuration.getConnectionRequestTimeout())
					.setSocketTimeout(configuration.getReadTimeout())
					.setAuthenticationEnabled(configuration.isAuthenticationEnabled())
					.setContentCompressionEnabled(configuration.isContentCompressionEnabled())
					.setNormalizeUri(configuration.isNormalizeUri());

			if(configuration.isAllowRedirects() != null){
				builder.setRedirectsEnabled(configuration.isAllowRedirects());
			}

			if(configuration.getMaxRedirects() != null){
				builder.setMaxRedirects(configuration.getMaxRedirects());
			}

			if(configuration.isCircularRedirectsAllowed() != null){
				builder.setCircularRedirectsAllowed(configuration.isCircularRedirectsAllowed());
			}

			if(configuration.isRelativeRedirectsAllowed() != null){
				builder.setRelativeRedirectsAllowed(configuration.isRelativeRedirectsAllowed());
			}

			requestConfig = builder.build();
		}

		return requestConfig;
	}

	public void setRequestConfig(RequestConfig requestConfig){
		this.requestConfig = requestConfig;
	}

	public org.apache.http.nio.client.HttpAsyncClient getHttpClient(){
		if(httpAsyncClient == null){
			HttpAsyncClientBuilder httpClientBuilder = HttpAsyncClientBuilder.create()
					.setConnectionManager(
							((ApacheNioClientConnectionManager) getConnectionManager()).getClientConnectionManager());
			httpAsyncClient = httpClientBuilder.build();
		}

		return httpAsyncClient;
	}

	public void setHttpClient(org.apache.http.nio.client.HttpAsyncClient httpAsyncClient){
		this.httpAsyncClient = httpAsyncClient;
	}

	@Override
	public Future<Response> get(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).get(), callback);
	}

	@Override
	public Future<Response> get(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).get(), readTimeout, callback);
	}

	@Override
	public Future<Response> post(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).post(data), callback);
	}

	@Override
	public Future<Response> post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								 List<Header> headers, FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).post(data), readTimeout, callback);
	}

	@Override
	public Future<Response> put(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).put(data), callback);
	}

	@Override
	public Future<Response> put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								List<Header> headers, FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).put(data), readTimeout, callback);
	}

	@Override
	public Future<Response> patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).patch(data), callback);
	}

	@Override
	public Future<Response> patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								  List<Header> headers, FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).patch(data), readTimeout, callback);
	}

	@Override
	public Future<Response> delete(URI uri, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).delete(), callback);
	}

	@Override
	public Future<Response> delete(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).delete(), readTimeout, callback);
	}

	@Override
	public Future<Response> connect(URI uri, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).connect(), callback);
	}

	@Override
	public Future<Response> connect(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).connect(), readTimeout, callback);
	}

	@Override
	public Future<Response> trace(URI uri, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).trace(), callback);
	}

	@Override
	public Future<Response> trace(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).trace(), readTimeout, callback);
	}

	@Override
	public Future<Response> copy(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).copy(), callback);
	}

	@Override
	public Future<Response> copy(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).copy(), readTimeout, callback);
	}

	@Override
	public Future<Response> move(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).move(), callback);
	}

	@Override
	public Future<Response> move(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).move(), readTimeout, callback);
	}

	@Override
	public Future<Response> head(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).head(), callback);
	}

	@Override
	public Future<Response> head(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).head(), readTimeout, callback);
	}

	@Override
	public Future<Response> options(URI uri, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).options(), callback);
	}

	@Override
	public Future<Response> options(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).options(), readTimeout, callback);
	}

	@Override
	public Future<Response> link(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).link(), callback);
	}

	@Override
	public Future<Response> link(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).link(), readTimeout, callback);
	}

	@Override
	public Future<Response> unlink(URI uri, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlink(), callback);
	}

	@Override
	public Future<Response> unlink(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlink(), readTimeout, callback);
	}

	@Override
	public Future<Response> purge(URI uri, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).purge(), callback);
	}

	@Override
	public Future<Response> purge(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).purge(), readTimeout, callback);
	}

	@Override
	public Future<Response> lock(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).lock(), callback);
	}

	@Override
	public Future<Response> lock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).lock(), readTimeout, callback);
	}

	@Override
	public Future<Response> unlock(URI uri, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlock(), callback);
	}

	@Override
	public Future<Response> unlock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlock(), readTimeout, callback);
	}

	@Override
	public Future<Response> propfind(URI uri, Map<String, Object> parameters, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).propfind(), callback);
	}

	@Override
	public Future<Response> propfind(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).propfind(), readTimeout, callback);
	}

	@Override
	public Future<Response> proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters,
									  List<Header> headers, FutureCallback callback) throws IOException,
			RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).proppatch(data), callback);
	}

	@Override
	public Future<Response> proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
									  List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).proppatch(data), readTimeout, callback);
	}

	@Override
	public Future<Response> report(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).report(data), callback);
	}

	@Override
	public Future<Response> report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								   List<Header> headers, FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).report(data), readTimeout, callback);
	}

	@Override
	public Future<Response> view(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).view(), callback);
	}

	@Override
	public Future<Response> view(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).view(), readTimeout, callback);
	}

	@Override
	public Future<Response> wrapped(URI uri, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).wrapped(), callback);
	}

	@Override
	public Future<Response> wrapped(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).wrapped(), readTimeout, callback);
	}

	protected Future<Response> doRequest(final ApacheRequestBuilder builder, final FutureCallback callback)
			throws IOException, RequestException{
		return doRequest(builder, getRequestConfig(), callback);
	}

	protected Future<Response> doRequest(final ApacheRequestBuilder builder, final int readTimeout,
										 final FutureCallback callback) throws IOException, RequestException{
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(getRequestConfig())
				.setSocketTimeout(readTimeout);
		return doRequest(builder, requestConfigBuilder.build(), callback);
	}

	protected Future<Response> doRequest(final ApacheRequestBuilder builder, final RequestConfig requestConfig,
										 final FutureCallback callback) throws IOException, RequestException{
		final ApacheRequestBuilder.HttpComponentsRequest request = builder.setRequestConfig(requestConfig)
				.setProtocolVersion(getHttpVersion()).build();
		final HttpAsyncRequestProducer httpAsyncRequestProducer = HttpAsyncMethods.create(
				request.getHttpRequest());

		try{
			return getHttpClient().execute(httpAsyncRequestProducer, new BasicAsyncResponseConsumer(),
					futureCallback(callback));
		}finally{
			request.getHttpRequest().releaseConnection();
		}
	}

	protected org.apache.http.concurrent.FutureCallback<Response> futureCallback(final FutureCallback callback){
		return new org.apache.http.concurrent.FutureCallback<Response>() {

			@Override
			public void completed(Response response){
				callback.completed(response);
			}

			@Override
			public void failed(Exception ex){
				callback.failed(ex);
			}

			@Override
			public void cancelled(){
				callback.cancelled();
			}

		};
	}

}
