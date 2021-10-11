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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient;

import com.buession.httpclient.conn.ApacheClientConnectionManager;
import com.buession.httpclient.conn.ConnectionManager;
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
import com.buession.httpclient.exception.UnknownHostException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

/**
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
	@Deprecated
	public ApacheHttpClient(ConnectionManager connectionManager){
		super(connectionManager);
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
	 * 		Apache Http Client
	 */
	public ApacheHttpClient(HttpClient httpClient){
		this.httpClient = httpClient;
	}

	/**
	 * 构造函数
	 *
	 * @param httpClient
	 * 		Apache Http Client
	 * @param requestConfig
	 * 		请求配置
	 */
	public ApacheHttpClient(HttpClient httpClient, RequestConfig requestConfig){
		this.httpClient = httpClient;
		this.requestConfig = requestConfig;
	}

	public RequestConfig getRequestConfig(){
		if(requestConfig == null){
			final Configuration configuration = getConnectionManager().getConfiguration();


			RequestConfig.Builder builder =
					RequestConfig.custom().setConnectTimeout(configuration.getConnectTimeout()).setConnectionRequestTimeout(configuration.getConnectionRequestTimeout()).setSocketTimeout(configuration.getReadTimeout()).setAuthenticationEnabled(configuration.isAuthenticationEnabled()).setContentCompressionEnabled(configuration.isContentCompressionEnabled()).setNormalizeUri(configuration.isNormalizeUri());

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
			httpClient =
					HttpClientBuilder.create().setConnectionManager((HttpClientConnectionManager) getConnectionManager().getClientConnectionManager()).build();
		}

		return httpClient;
	}

	public void setHttpClient(org.apache.http.client.HttpClient httpClient){
		this.httpClient = httpClient;
	}

	@Override
	public Response get(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).get());
	}

	@Override
	public Response post(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).post(data));
	}

	@Override
	public Response patch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).patch(data));
	}

	@Override
	public Response put(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).put(data));
	}

	@Override
	public Response delete(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).delete());
	}

	@Override
	public Response connect(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).connect());
	}

	@Override
	public Response trace(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).trace());
	}

	@Override
	public Response copy(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).copy());
	}

	@Override
	public Response move(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).move());
	}

	@Override
	public Response head(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).head());
	}

	@Override
	public Response options(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).options());
	}

	@Override
	public Response link(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).link());
	}

	@Override
	public Response unlink(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).unlink());
	}

	@Override
	public Response purge(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).purge());
	}

	@Override
	public Response lock(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).lock());
	}

	@Override
	public Response unlock(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).unlock());
	}

	@Override
	public Response propfind(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).propfind());
	}

	@Override
	public Response proppatch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).proppatch(data));
	}

	@Override
	public Response report(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).report(data));
	}

	@Override
	public Response view(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).view());
	}

	@Override
	public Response wrapped(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		return doRequest(ApacheRequestBuilder.create(url, parameters, headers).wrapped());
	}

	protected Response doRequest(final ApacheRequestBuilder builder) throws ConnectTimeoutException,
			ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
		final ApacheRequestBuilder.HttpComponentsRequest request =
				builder.setRequestConfig(getRequestConfig()).setProtocolVersion(getHttpVersion()).build();
		HttpResponse httpResponse = null;

		try{
			httpResponse = getHttpClient().execute(request.getHttpRequest());
			return ApacheResponseBuilder.create(httpResponse).build();
		}catch(IOException e){
			logger.error("Request({}) url: {} error.", request.getMethod(), request.getUrl(), e);
			if(e instanceof org.apache.http.conn.ConnectionPoolTimeoutException){
				throw new ConnectionPoolTimeoutException(e.getMessage());
			}else if(e instanceof org.apache.http.conn.ConnectTimeoutException){
				throw new ConnectTimeoutException(e.getMessage());
			}else if(e instanceof SocketTimeoutException){
				throw new ReadTimeoutException(e.getMessage());
			}else if(e instanceof org.apache.http.impl.execchain.RequestAbortedException){
				throw new RequestAbortedException(e.getMessage());
			}else if(e instanceof java.net.UnknownHostException){
				throw new UnknownHostException(e.getMessage());
			}else{
				throw new RequestException(e.getMessage(), e);
			}
		}finally{
			request.getHttpRequest().releaseConnection();
		}
	}

}
