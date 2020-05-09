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
package com.buession.httpclient;

import com.buession.httpclient.conn.ApacheClientConnectionManager;
import com.buession.httpclient.conn.ConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Request;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.ConnectTimeoutException;
import com.buession.httpclient.exception.ConnectionPoolTimeoutException;
import com.buession.httpclient.exception.ReadTimeoutException;
import com.buession.httpclient.exception.RequestAbortedException;
import com.buession.httpclient.exception.RequestException;
import com.buession.httpclient.httpcomponents.HttpComponentsRequestBuilder;
import com.buession.httpclient.httpcomponents.HttpComponentsResponseBuilder;
import com.buession.httpclient.httpcomponents.RequestBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
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
	 * @param httpClientConnectionManager
	 * 		连接管理器
	 */
	public ApacheHttpClient(ConnectionManager httpClientConnectionManager){
		super(httpClientConnectionManager);
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

			requestConfig = RequestConfig.custom().setConnectTimeout(configuration.getConnectTimeout())
					.setConnectionRequestTimeout(configuration.getConnectionRequestTimeout()).setSocketTimeout
							(configuration.getReadTimeout()).setRedirectsEnabled(configuration.isAllowRedirects())
					.setCircularRedirectsAllowed(configuration.isCircularRedirectsAllowed())
					.setRelativeRedirectsAllowed(configuration.isRelativeRedirectsAllowed()).setMaxRedirects
							(configuration.getMaxRedirects()).setAuthenticationEnabled(configuration
							.isAuthenticationEnabled()).setContentCompressionEnabled(configuration
							.isContentCompressionEnabled()).setNormalizeUri(configuration.isNormalizeUri()).build();
		}

		return requestConfig;
	}

	public void setRequestConfig(RequestConfig requestConfig){
		this.requestConfig = requestConfig;
	}

	public org.apache.http.client.HttpClient getHttpClient(){
		if(httpClient == null){
			httpClient = HttpClientBuilder.create().setConnectionManager((HttpClientConnectionManager)
					getConnectionManager().getClientConnectionManager()).build();
		}

		return httpClient;
	}

	public void setHttpClient(org.apache.http.client.HttpClient httpClient){
		this.httpClient = httpClient;
	}

	@Override
	public Response get(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).get(), headers, parameters);
	}

	@Override
	public Response post(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).post(data), headers, parameters);
	}

	@Override
	public Response patch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).patch(data), headers, parameters);
	}

	@Override
	public Response put(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).put(data), headers, parameters);
	}

	@Override
	public Response delete(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).delete(), headers, parameters);
	}

	@Override
	public Response connect(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).connect(), headers, parameters);
	}

	@Override
	public Response trace(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).trace(), headers, parameters);
	}

	@Override
	public Response copy(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).copy(), headers, parameters);
	}

	@Override
	public Response move(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).move(), headers, parameters);
	}

	@Override
	public Response head(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).head(), headers, parameters);
	}

	@Override
	public Response options(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).options(), headers, parameters);
	}

	@Override
	public Response link(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).link(), headers, parameters);
	}

	@Override
	public Response unlink(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).unlink(), headers, parameters);
	}

	@Override
	public Response purge(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).purge(), headers, parameters);
	}

	@Override
	public Response lock(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).lock(), headers, parameters);
	}

	@Override
	public Response unlock(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).unlock(), headers, parameters);
	}

	@Override
	public Response propfind(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).propfind(), headers, parameters);
	}

	@Override
	public Response proppatch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers)
			throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
			RequestAbortedException, RequestException{
		return doRequest(new RequestBuilder(url).proppatch(data), headers, parameters);
	}

	@Override
	public Response report(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).report(data), headers, parameters);
	}

	@Override
	public Response view(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).view(), headers, parameters);
	}

	@Override
	public Response wrapped(String url, Map<String, Object> parameters, List<Header> headers) throws
			ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
			RequestException{
		return doRequest(new RequestBuilder(url).wrapped(), headers, parameters);
	}

	protected Response doRequest(final HttpRequestBase httpRequest, final List<Header> headers, final Map<String,
			Object> parameters) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
			RequestAbortedException, RequestException{
		final Request request = HttpComponentsRequestBuilder.create(httpRequest).setParameters(parameters).setHeaders
				(headers).build();

		httpRequest.setConfig(getRequestConfig());

		final ProtocolVersion httpVersion = getHttpVersion();
		if(httpVersion != null){
			httpRequest.setProtocolVersion(new org.apache.http.ProtocolVersion(httpVersion.getProtocol(), httpVersion
					.getMajor(), httpVersion.getMinor()));
		}

		if(request.getHeaders() != null){
			for(Header header : request.getHeaders()){
				httpRequest.setHeader(header.getName(), header.getValue());
			}
		}

		try{
			HttpResponse httpResponse = getHttpClient().execute(httpRequest);

			return HttpComponentsResponseBuilder.create(httpResponse).build();
		}catch(IOException e){
			logger.error("Request({}) url: {} error.", httpRequest.getMethod(), httpRequest.getURI(), e);
			if(e instanceof org.apache.http.conn.ConnectTimeoutException){
				throw new ConnectTimeoutException(e.getMessage());
			}else if(e instanceof org.apache.http.conn.ConnectionPoolTimeoutException){
				throw new ConnectionPoolTimeoutException(e.getMessage());
			}else if(e instanceof SocketTimeoutException){
				throw new ReadTimeoutException(e.getMessage());
			}else if(e instanceof org.apache.http.impl.execchain.RequestAbortedException){
				throw new RequestAbortedException(e.getMessage());
			}else{
				throw new RequestException(e.getMessage(), e);
			}
		}finally{
			httpRequest.releaseConnection();
		}
	}

}
