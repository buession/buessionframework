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

import com.buession.httpclient.conn.ApacheClientConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.ConnectTimeoutException;
import com.buession.httpclient.exception.ConnectionPoolTimeoutException;
import com.buession.httpclient.exception.ReadTimeoutException;
import com.buession.httpclient.exception.RequestAbortedException;
import com.buession.httpclient.exception.RequestException;
import com.buession.httpclient.apache.ApacheRequestBuilder;
import com.buession.httpclient.apache.ApacheResponseBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * Apache HttpComponents HttpClient
 *
 * @author Yong.Teng
 */
public class ApacheHttpClient extends AbstractHttpClient {

	private RequestConfig requestConfig;

	private org.apache.http.client.HttpClient httpClient;

	private final static Logger logger = LoggerFactory.getLogger(ApacheHttpClient.class);

	/**
	 * 构造函数
	 */
	public ApacheHttpClient(){
		super();
		setConnectionManager(new ApacheClientConnectionManager());
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 */
	public ApacheHttpClient(ApacheClientConnectionManager connectionManager){
		super(connectionManager);
	}

	/**
	 * 构造函数
	 *
	 * @param httpClient
	 *        {@link org.apache.http.client.HttpClient} 实例
	 */
	public ApacheHttpClient(org.apache.http.client.HttpClient httpClient){
		this.httpClient = httpClient;
	}

	/**
	 * 构造函数
	 *
	 * @param httpClient
	 *        {@link org.apache.http.client.HttpClient} 实例
	 * @param requestConfig
	 * 		请求配置
	 */
	public ApacheHttpClient(org.apache.http.client.HttpClient httpClient, RequestConfig requestConfig){
		this.httpClient = httpClient;
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

	public org.apache.http.client.HttpClient getHttpClient(){
		if(httpClient == null){
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create()
					.setConnectionManager(
							((ApacheClientConnectionManager) getConnectionManager()).getClientConnectionManager());
			httpClient = httpClientBuilder.build();
		}

		return httpClient;
	}

	public void setHttpClient(org.apache.http.client.HttpClient httpClient){
		this.httpClient = httpClient;
	}

	@Override
	public Response get(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).get());
	}

	@Override
	public Response get(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).get(), readTimeout);
	}

	@Override
	public Response post(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).post(data));
	}

	@Override
	public Response post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						 List<Header> headers) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).post(data), readTimeout);
	}

	@Override
	public Response put(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).put(data));
	}

	@Override
	public Response put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						List<Header> headers) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).put(data), readTimeout);
	}

	@Override
	public Response patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).patch(data));
	}

	@Override
	public Response patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						  List<Header> headers) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).patch(data), readTimeout);
	}

	@Override
	public Response delete(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).delete());
	}

	@Override
	public Response delete(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).delete(), readTimeout);
	}

	@Override
	public Response connect(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).connect());
	}

	@Override
	public Response connect(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).connect(), readTimeout);
	}

	@Override
	public Response trace(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).trace());
	}

	@Override
	public Response trace(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).trace(), readTimeout);
	}

	@Override
	public Response copy(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).copy());
	}

	@Override
	public Response copy(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).copy(), readTimeout);
	}

	@Override
	public Response move(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).move());
	}

	@Override
	public Response move(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).move(), readTimeout);
	}

	@Override
	public Response head(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).head());
	}

	@Override
	public Response head(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).head(), readTimeout);
	}

	@Override
	public Response options(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).options());
	}

	@Override
	public Response options(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).options(), readTimeout);
	}

	@Override
	public Response link(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).link());
	}

	@Override
	public Response link(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).link(), readTimeout);
	}

	@Override
	public Response unlink(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlink());
	}

	@Override
	public Response unlink(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlink(), readTimeout);
	}

	@Override
	public Response purge(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).purge());
	}

	@Override
	public Response purge(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).purge(), readTimeout);
	}

	@Override
	public Response lock(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).lock());
	}

	@Override
	public Response lock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).lock(), readTimeout);
	}

	@Override
	public Response unlock(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlock());
	}

	@Override
	public Response unlock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).unlock(), readTimeout);
	}

	@Override
	public Response propfind(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).propfind());
	}

	@Override
	public Response propfind(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).propfind(), readTimeout);
	}

	@Override
	public Response proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).proppatch(data));
	}

	@Override
	public Response proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
							  List<Header> headers) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).proppatch(data), readTimeout);
	}

	@Override
	public Response report(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).report(data));
	}

	@Override
	public Response report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						   List<Header> headers) throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).report(data), readTimeout);
	}

	@Override
	public Response view(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).view());
	}

	@Override
	public Response view(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).view(), readTimeout);
	}

	@Override
	public Response wrapped(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).wrapped());
	}

	@Override
	public Response wrapped(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException{
		return doRequest(ApacheRequestBuilder.create(uri, parameters, headers).wrapped(), readTimeout);
	}

	protected Response doRequest(final ApacheRequestBuilder builder)
			throws IOException, RequestException{
		return doRequest(builder, getRequestConfig());
	}

	protected Response doRequest(final ApacheRequestBuilder builder, final int readTimeout)
			throws IOException, RequestException{
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(getRequestConfig())
				.setSocketTimeout(readTimeout);
		return doRequest(builder, requestConfigBuilder.build());
	}

	protected Response doRequest(final ApacheRequestBuilder builder, final RequestConfig requestConfig)
			throws IOException, RequestException{
		final ApacheRequestBuilder.HttpComponentsRequest request = builder.setRequestConfig(requestConfig)
				.setProtocolVersion(getHttpVersion()).build();
		final ApacheResponseBuilder apacheResponseBuilder = new ApacheResponseBuilder();

		try{
			HttpResponse httpResponse = getHttpClient().execute(request.getHttpRequest());
			return apacheResponseBuilder.build(httpResponse);
		}catch(IOException e){
			if(logger.isErrorEnabled()){
				logger.error("Request({}) url: {} error.", request.getMethod(), request.getUrl(), e);
			}

			if(e instanceof org.apache.http.conn.ConnectionPoolTimeoutException){
				throw new ConnectionPoolTimeoutException(e.getMessage());
			}else if(e instanceof org.apache.http.conn.ConnectTimeoutException){
				throw new ConnectTimeoutException(e.getMessage());
			}else if(e instanceof SocketTimeoutException){
				throw new ReadTimeoutException(e.getMessage());
			}else if(e instanceof org.apache.http.impl.execchain.RequestAbortedException){
				throw new RequestAbortedException(e.getMessage());
			}else if(e instanceof ClientProtocolException){
				throw new RequestException(e.getMessage(), e);
			}else if(e instanceof UnknownHostException){
				throw e;
			}else{
				throw new RequestException(e.getMessage(), e);
			}
		}finally{
			request.getHttpRequest().releaseConnection();
		}
	}

}
