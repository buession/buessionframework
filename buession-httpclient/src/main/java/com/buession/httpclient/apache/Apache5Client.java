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
package com.buession.httpclient.apache;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.httpclient.conn.ApacheClientConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.ConnectTimeoutException;
import com.buession.httpclient.exception.ConnectionPoolTimeoutException;
import com.buession.httpclient.exception.ReadTimeoutException;
import com.buession.httpclient.exception.RequestAbortedException;
import com.buession.httpclient.exception.RequestException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.4.0
 */
public class Apache4Client extends AbstractApacheClient {

	private final RequestConfig requestConfig;

	private final HttpClient httpClient;

	private final static Logger logger = LoggerFactory.getLogger(Apache4Client.class);

	public Apache4Client(final HttpClient httpClient, final ApacheClientConnectionManager connectionManager,
						 final ProtocolVersion protocolVersion) {
		super(protocolVersion);

		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final Configuration configuration = connectionManager.getConfiguration();
		final RequestConfig.Builder builder = RequestConfig.custom()
				.setConnectTimeout(configuration.getConnectTimeout())
				.setConnectionRequestTimeout(configuration.getConnectionRequestTimeout())
				.setSocketTimeout(configuration.getReadTimeout())
				.setAuthenticationEnabled(configuration.isAuthenticationEnabled())
				.setContentCompressionEnabled(configuration.isContentCompressionEnabled())
				.setNormalizeUri(configuration.isNormalizeUri());

		propertyMapper.from(configuration.isAllowRedirects()).to(builder::setRedirectsEnabled);
		propertyMapper.from(configuration.getMaxRedirects()).to(builder::setMaxRedirects);
		propertyMapper.from(configuration.isCircularRedirectsAllowed()).to(builder::setCircularRedirectsAllowed);
		propertyMapper.from(configuration.isRelativeRedirectsAllowed()).to(builder::setRelativeRedirectsAllowed);

		this.requestConfig = builder.build();
		this.httpClient = httpClient;
	}

	public Apache4Client(final HttpClient httpClient, final RequestConfig requestConfig,
						 final ProtocolVersion protocolVersion) {
		super(protocolVersion);
		this.requestConfig = requestConfig;
		this.httpClient = httpClient;
	}

	public Apache4Client(final ApacheClientConnectionManager connectionManager,
						 final ProtocolVersion protocolVersion) {
		super(protocolVersion);

		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final Configuration configuration = connectionManager.getConfiguration();
		final RequestConfig.Builder builder = RequestConfig.custom()
				.setConnectTimeout(configuration.getConnectTimeout())
				.setConnectionRequestTimeout(configuration.getConnectionRequestTimeout())
				.setSocketTimeout(configuration.getReadTimeout())
				.setAuthenticationEnabled(configuration.isAuthenticationEnabled())
				.setContentCompressionEnabled(configuration.isContentCompressionEnabled())
				.setNormalizeUri(configuration.isNormalizeUri());

		propertyMapper.from(configuration.isAllowRedirects()).to(builder::setRedirectsEnabled);
		propertyMapper.from(configuration.getMaxRedirects()).to(builder::setMaxRedirects);
		propertyMapper.from(configuration.isCircularRedirectsAllowed()).to(builder::setCircularRedirectsAllowed);
		propertyMapper.from(configuration.isRelativeRedirectsAllowed()).to(builder::setRelativeRedirectsAllowed);

		this.requestConfig = builder.build();
		final ApacheHttpClientBuilder httpClientBuilder = new ApacheHttpClientBuilder(connectionManager);

		httpClient = httpClientBuilder.build((b)->{
		});
	}

	public Apache4Client(final RequestConfig requestConfig, final ApacheClientConnectionManager connectionManager,
						 final ProtocolVersion protocolVersion) {
		super(protocolVersion);
		this.requestConfig = requestConfig;
		final ApacheHttpClientBuilder httpClientBuilder = new ApacheHttpClientBuilder(connectionManager);

		httpClient = httpClientBuilder.build((builder)->{
		});
	}

	@Override
	public Response get(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).get()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response get(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).get()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response post(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						 final RequestBody<?> body) throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).post(body)
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response post(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers, final RequestBody<?> body) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).post(body)
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response patch(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						  final RequestBody<?> body) throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).patch(body)
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response patch(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						  final List<Header> headers, final RequestBody<?> body) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).patch(body)
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response put(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						final RequestBody<?> body) throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).put(body)
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response put(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						final List<Header> headers, final RequestBody<?> body) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).put(body)
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response delete(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).delete()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response delete(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						   final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).delete()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response connect(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).connect()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response connect(final URI uri, final int readTimeout, final Map<String, Object> parameters,
							final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).connect()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response trace(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).trace()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response trace(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						  final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).trace()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response copy(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).copy()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response copy(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).copy()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response move(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).move()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response move(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).move()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response head(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).head()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response head(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).head()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response options(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).options()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response options(final URI uri, final int readTimeout, final Map<String, Object> parameters,
							final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).options()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response link(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).link()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response link(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).link()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response unlink(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).unlink()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response unlink(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						   final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).unlink()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response purge(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).purge()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response purge(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						  final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).purge()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response lock(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).lock()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response lock(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).lock()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response unlock(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).unlock()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response unlock(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						   final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).unlock()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response propfind(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).propfind()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response propfind(final URI uri, final int readTimeout, final Map<String, Object> parameters,
							 final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).propfind()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response proppatch(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
							  final RequestBody<?> body) throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).proppatch(body)
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response proppatch(final URI uri, final int readTimeout, final Map<String, Object> parameters,
							  final List<Header> headers, final RequestBody<?> body)
			throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).proppatch(body)
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response report(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						   final RequestBody<?> body) throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).report(body)
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response report(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						   final List<Header> headers, final RequestBody<?> body)
			throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).report(body)
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response view(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).view()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response view(final URI uri, final int readTimeout, final Map<String, Object> parameters,
							 final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).view()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response wrapped(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).wrapped()
				.setRequestConfig(requestConfig)
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	@Override
	public Response wrapped(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		final ApacheRequest request = ApacheRequestBuilder.create(uri, parameters, headers).wrapped()
				.setRequestConfig(requestConfigBuilder.build())
				.setProtocolVersion(protocolVersion)
				.build();

		return doRequest(request);
	}

	protected Response doRequest(final ApacheRequest request) throws IOException, RequestException {
		final ApacheResponseBuilder apacheResponseBuilder = new ApacheResponseBuilder();

		try{
			HttpResponse httpResponse = httpClient.execute(request.getHttpRequest());
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
