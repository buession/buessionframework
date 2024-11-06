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
package com.buession.httpclient;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.validator.Validate;
import com.buession.httpclient.conn.OkHttpClientConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.ConnectTimeoutException;
import com.buession.httpclient.exception.ReadTimeoutException;
import com.buession.httpclient.exception.RequestException;
import com.buession.httpclient.okhttp.OkHttpRequest;
import com.buession.httpclient.okhttp.OkHttpRequestBuilder;
import com.buession.httpclient.okhttp.OkHttpResponseBuilder;
import com.buession.net.ssl.SslConfiguration;
import okhttp3.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * OkHttp(3) HttpClient
 *
 * @author Yong.Teng
 */
public class OkHttpHttpClient extends AbstractHttpClient {

	private okhttp3.OkHttpClient httpClient;

	private final static Logger logger = LoggerFactory.getLogger(OkHttpHttpClient.class);

	/**
	 * 构造函数
	 */
	public OkHttpHttpClient() {
		super();
		setConnectionManager(new OkHttpClientConnectionManager());
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 */
	public OkHttpHttpClient(OkHttpClientConnectionManager connectionManager) {
		super(connectionManager);
	}

	/**
	 * 构造函数
	 *
	 * @param httpClient
	 * 		OkHttp Client
	 */
	public OkHttpHttpClient(okhttp3.OkHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public okhttp3.OkHttpClient getHttpClient() {
		if(httpClient == null){
			final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
			final OkHttpClientConnectionManager okHttpClientConnectionManager =
					(OkHttpClientConnectionManager) getConnectionManager();
			final Configuration configuration = okHttpClientConnectionManager.getConfiguration();
			final SslConfiguration sslConfiguration = configuration.getSslConfiguration();
			final HttpClientBuilder builder = HttpClientBuilder.create()
					.setConnectionManager(okHttpClientConnectionManager.getClientConnectionManager());

			propertyMapper.from(configuration.getConnectTimeout()).to(builder::setConnectTimeout);
			propertyMapper.alwaysApplyingWhenPositiveNumber().from(configuration.getReadTimeout())
					.to(builder::setReadTimeout);
			propertyMapper.alwaysApplyingWhenPositiveNumber().from(configuration.getWriteTimeout())
					.to(builder::setWriteTimeout);
			propertyMapper.from(configuration.getRetryOnConnectionFailure()).to(builder::setRetryOnConnectionFailure);
			propertyMapper.from(configuration.isAllowRedirects()).to(builder::setFollowRedirects);

			if(sslConfiguration != null){
				propertyMapper.from(sslConfiguration.getSslSocketFactory()).to(builder::setSSLSocketFactory);
				propertyMapper.from(sslConfiguration.getHostnameVerifier()).to(builder::setSSLHostnameVerifier);
				propertyMapper.from(sslConfiguration.getSslContext()).to(builder::setSSLContext);
			}

			httpClient = builder.build();
		}

		return httpClient;
	}

	public void setHttpClient(okhttp3.OkHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public Response get(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).get());
	}

	@Override
	public Response get(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).get(), readTimeout);
	}

	@Override
	public Response post(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).post(data));
	}

	@Override
	public Response post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						 List<Header> headers) throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).post(data), readTimeout);
	}

	@Override
	public Response put(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).put(data));
	}

	@Override
	public Response put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						List<Header> headers) throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).put(data), readTimeout);
	}

	@Override
	public Response patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).patch(data));
	}

	@Override
	public Response patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						  List<Header> headers) throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).patch(data), readTimeout);
	}

	@Override
	public Response delete(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).delete());
	}

	@Override
	public Response delete(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).delete(), readTimeout);
	}

	@Override
	public Response connect(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).connect());
	}

	@Override
	public Response connect(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).connect(), readTimeout);
	}

	@Override
	public Response trace(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).trace());
	}

	@Override
	public Response trace(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).trace(), readTimeout);
	}

	@Override
	public Response copy(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).copy());
	}

	@Override
	public Response copy(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).copy(), readTimeout);
	}

	@Override
	public Response move(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).move());
	}

	@Override
	public Response move(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).move(), readTimeout);
	}

	@Override
	public Response head(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).head());
	}

	@Override
	public Response head(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).head(), readTimeout);
	}

	@Override
	public Response options(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).options());
	}

	@Override
	public Response options(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).options(), readTimeout);
	}

	@Override
	public Response link(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).link());
	}

	@Override
	public Response link(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).link(), readTimeout);
	}

	@Override
	public Response unlink(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).unlink());
	}

	@Override
	public Response unlink(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).unlink(), readTimeout);
	}

	@Override
	public Response purge(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).purge());
	}

	@Override
	public Response purge(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).purge(), readTimeout);
	}

	@Override
	public Response lock(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).lock());
	}

	@Override
	public Response lock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).lock(), readTimeout);
	}

	@Override
	public Response unlock(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).unlock());
	}

	@Override
	public Response unlock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).unlock(), readTimeout);
	}

	@Override
	public Response propfind(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).propfind());
	}

	@Override
	public Response propfind(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).propfind(), readTimeout);
	}

	@Override
	public Response proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).proppatch(data));
	}

	@Override
	public Response proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
							  List<Header> headers) throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).proppatch(data), readTimeout);
	}

	@Override
	public Response report(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).report(data));
	}

	@Override
	public Response report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						   List<Header> headers) throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).report(data), readTimeout);
	}

	@Override
	public Response view(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).view());
	}

	@Override
	public Response view(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).view(), readTimeout);
	}

	@Override
	public Response wrapped(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).wrapped());
	}

	@Override
	public Response wrapped(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return doRequest(OkHttpRequestBuilder.create(uri, parameters, headers).wrapped(), readTimeout);
	}

	protected Response doRequest(final OkHttpRequestBuilder builder) throws IOException, RequestException {
		final OkHttpRequest request = builder.setProtocolVersion(getHttpVersion()).build();
		return doRequest(request);
	}

	protected Response doRequest(final OkHttpRequestBuilder builder, final int readTimeout)
			throws IOException, RequestException {
		final OkHttpRequest request = builder.setProtocolVersion(getHttpVersion()).build();
		return doRequest(request);
	}

	protected Response doRequest(final OkHttpRequest request) throws IOException, RequestException {
		okhttp3.Request okHttpRequest = request.getRequestBuilder().build();
		okhttp3.Response httpResponse = null;
		final OkHttpResponseBuilder httpResponseBuilder = new OkHttpResponseBuilder();

		try{
			httpResponse = getHttpClient().newCall(okHttpRequest).execute();
			return httpResponseBuilder.build(httpResponse);
		}catch(IOException e){
			if(logger.isErrorEnabled()){
				logger.error("Request({}) url: {} error.", request.getMethod(), request.getUri(), e);
			}

			if(e instanceof SocketTimeoutException){
				String message = e.getMessage();

				if(Validate.hasText(message)){
					message = message.toLowerCase();
				}

				if(message.contains("connect timed out")){
					throw new ConnectTimeoutException(e.getMessage());
				}else if(message.contains("read timed out")){
					throw new ReadTimeoutException(e.getMessage());
				}else{
					throw new RequestException(e.getMessage(), e);
				}
			}else if(e instanceof UnknownHostException){
				throw e;
			}else{
				throw new RequestException(e.getMessage(), e);
			}
		}finally{
			if(httpResponse != null){
				httpResponse.close();
			}
		}
	}

}
