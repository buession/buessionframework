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

import com.buession.httpclient.apache.ApacheHttpAsyncClientBuilder;
import com.buession.httpclient.apache.ApacheRequestBuilder;
import com.buession.httpclient.apache.nio.DefaultCallback;
import com.buession.httpclient.apache.nio.protocol.BasicAsyncResponseConsumer;
import com.buession.httpclient.conn.ApacheNioClientConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.concurrent.Callback;
import com.buession.httpclient.exception.RequestException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
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

			final RequestConfig.Builder builder = RequestConfig.custom()
					.setConnectTimeout(configuration.getConnectTimeout())
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
			final ApacheHttpAsyncClientBuilder httpAsyncClientBuilder = new ApacheHttpAsyncClientBuilder(
					(ApacheNioClientConnectionManager) getConnectionManager());
			
			httpAsyncClient = httpAsyncClientBuilder.build((builder)->{
			});
		}

		return httpAsyncClient;
	}

	public void setHttpClient(org.apache.http.nio.client.HttpAsyncClient httpAsyncClient){
		this.httpAsyncClient = httpAsyncClient;
	}

	@Override
	public void get(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).get(), callback);
	}

	@Override
	public void get(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).get(), readTimeout, callback);
	}

	@Override
	public void post(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).post(data), callback);
	}

	@Override
	public void post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					 List<Header> headers, Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).post(data), readTimeout, callback);
	}

	@Override
	public void put(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).put(data), callback);
	}

	@Override
	public void put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					List<Header> headers, Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).put(data), readTimeout, callback);
	}

	@Override
	public void patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).patch(data), callback);
	}

	@Override
	public void patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					  List<Header> headers, Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).patch(data), readTimeout, callback);
	}

	@Override
	public void delete(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).delete(), callback);
	}

	@Override
	public void delete(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).delete(), readTimeout, callback);
	}

	@Override
	public void connect(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).connect(), callback);
	}

	@Override
	public void connect(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).connect(), readTimeout, callback);
	}

	@Override
	public void trace(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).trace(), callback);
	}

	@Override
	public void trace(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).trace(), readTimeout, callback);
	}

	@Override
	public void copy(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).copy(), callback);
	}

	@Override
	public void copy(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).copy(), readTimeout, callback);
	}

	@Override
	public void move(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).move(), callback);
	}

	@Override
	public void move(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).move(), readTimeout, callback);
	}

	@Override
	public void head(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).head(), callback);
	}

	@Override
	public void head(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).head(), readTimeout, callback);
	}

	@Override
	public void options(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).options(), callback);
	}

	@Override
	public void options(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).options(), readTimeout, callback);
	}

	@Override
	public void link(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).link(), callback);
	}

	@Override
	public void link(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).link(), readTimeout, callback);
	}

	@Override
	public void unlink(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlink(), callback);
	}

	@Override
	public void unlink(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlink(), readTimeout, callback);
	}

	@Override
	public void purge(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).purge(), callback);
	}

	@Override
	public void purge(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).purge(), readTimeout, callback);
	}

	@Override
	public void lock(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).lock(), callback);
	}

	@Override
	public void lock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).lock(), readTimeout, callback);
	}

	@Override
	public void unlock(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlock(), callback);
	}

	@Override
	public void unlock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlock(), readTimeout, callback);
	}

	@Override
	public void propfind(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).propfind(), callback);
	}

	@Override
	public void propfind(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						 Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).propfind(), readTimeout, callback);
	}

	@Override
	public void proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
						  Callback callback) throws IOException,
			RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).proppatch(data), callback);
	}

	@Override
	public void proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						  List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).proppatch(data), readTimeout, callback);
	}

	@Override
	public void report(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).report(data), callback);
	}

	@Override
	public void report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					   List<Header> headers, Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).report(data), readTimeout, callback);
	}

	@Override
	public void view(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).view(), callback);
	}

	@Override
	public void view(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).view(), readTimeout, callback);
	}

	@Override
	public void wrapped(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).wrapped(), callback);
	}

	@Override
	public void wrapped(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException{
		doRequest(ApacheRequestBuilder.create(uri, parameters, headers).wrapped(), readTimeout, callback);
	}

	protected void doRequest(final ApacheRequestBuilder builder, final Callback callback) throws IOException,
			RequestException{
		doRequest(builder, getRequestConfig(), callback);
	}

	protected void doRequest(final ApacheRequestBuilder builder, final int readTimeout, final Callback callback)
			throws IOException, RequestException{
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(getRequestConfig())
				.setSocketTimeout(readTimeout);
		doRequest(builder, requestConfigBuilder.build(), callback);
	}

	protected void doRequest(final ApacheRequestBuilder builder, final RequestConfig requestConfig,
							 final Callback callback) throws IOException, RequestException{
		final ApacheRequestBuilder.HttpComponentsRequest request = builder.setRequestConfig(requestConfig)
				.setProtocolVersion(getHttpVersion()).build();
		final HttpAsyncRequestProducer httpAsyncRequestProducer = HttpAsyncMethods.create(
				request.getHttpRequest());

		try{
			Future<Response> future = getHttpClient().execute(httpAsyncRequestProducer,
					new BasicAsyncResponseConsumer(request.getHttpRequest()),
					new DefaultCallback(callback));
			future.get();
		}catch(ExecutionException e){
			throw new RequestException(e.getMessage(), e);
		}catch(InterruptedException e){
			throw new RequestException(e.getMessage(), e);
		}finally{
			//request.getHttpRequest().releaseConnection();
		}
	}

}
