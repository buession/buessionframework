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

import com.buession.httpclient.apache.convert.utils.ApacheRequestBodyConverterUtils;
import com.buession.httpclient.apache.nio.DefaultCallback;
import com.buession.httpclient.conn.ApacheNioClientConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestBodyConverter;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.concurrent.Callback;
import com.buession.httpclient.exception.RequestException;
import com.buession.net.ssl.SslConfiguration;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.client.methods.ZeroCopyConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class Apache4AsyncClient extends AbstractApacheAsyncClient {

	private final static Map<Class<? extends RequestBody>, RequestBodyConverter> REQUEST_BODY_CONVERTS =
			ApacheRequestBodyConverterUtils.createApache4ClientRequestBodyConverter();

	private final RequestConfig requestConfig;

	private final HttpAsyncClient httpClient;

	private final org.apache.http.ProtocolVersion protocolVersion;

	public Apache4AsyncClient(final HttpAsyncClient httpClient,
							  final ApacheNioClientConnectionManager connectionManager,
							  final ProtocolVersion protocolVersion) {
		super();
		this.requestConfig = createRequestConfig(connectionManager.getConfiguration());
		this.httpClient = httpClient;
		this.protocolVersion = createProtocolVersion(protocolVersion);
	}

	public Apache4AsyncClient(final HttpAsyncClient httpClient, final RequestConfig requestConfig,
							  final ProtocolVersion protocolVersion) {
		super();
		this.requestConfig = requestConfig;
		this.httpClient = httpClient;
		this.protocolVersion = createProtocolVersion(protocolVersion);
	}

	public Apache4AsyncClient(final ApacheNioClientConnectionManager connectionManager,
							  final ProtocolVersion protocolVersion) {
		super();
		this.requestConfig = createRequestConfig(connectionManager.getConfiguration());
		this.httpClient = createHttpClient(connectionManager);
		this.protocolVersion = createProtocolVersion(protocolVersion);
	}

	public Apache4AsyncClient(final RequestConfig requestConfig,
							  final ApacheNioClientConnectionManager connectionManager,
							  final ProtocolVersion protocolVersion) {
		super();
		this.requestConfig = requestConfig;
		this.httpClient = createHttpClient(connectionManager);
		this.protocolVersion = createProtocolVersion(protocolVersion);
	}

	@Override
	public void get(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					final Callback callback) throws IOException, RequestException {
		doRequest(new HttpGet(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void get(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpGet(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void post(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					 final RequestBody<?> body, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpPost(determineRequestUri(uri, parameters)), requestConfig, headers, body, callback);
	}

	@Override
	public void post(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					 final List<Header> headers, final RequestBody<?> body, final Callback callback)
			throws IOException, RequestException {
		doRequest(new HttpPost(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers,
				body, callback);
	}

	@Override
	public void patch(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					  final RequestBody<?> body, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpPatch(determineRequestUri(uri, parameters)), requestConfig, headers, body, callback);
	}

	@Override
	public void patch(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					  final List<Header> headers, final RequestBody<?> body, final Callback callback)
			throws IOException, RequestException {
		doRequest(new HttpPatch(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers,
				body, callback);
	}

	@Override
	public void put(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					final RequestBody<?> body, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpPut(determineRequestUri(uri, parameters)), requestConfig, headers, body, callback);
	}

	@Override
	public void put(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					final List<Header> headers, final RequestBody<?> body, final Callback callback)
			throws IOException, RequestException {
		doRequest(new HttpPut(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, body,
				callback);
	}

	@Override
	public void delete(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					   final Callback callback) throws IOException, RequestException {
		doRequest(new HttpDelete(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void delete(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					   final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpDelete(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void connect(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						final Callback callback) throws IOException, RequestException {
		doRequest(new HttpConnect(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void connect(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpConnect(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void trace(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					  final Callback callback) throws IOException, RequestException {
		doRequest(new HttpTrace(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void trace(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					  final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpTrace(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void copy(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					 final Callback callback) throws IOException, RequestException {
		doRequest(new HttpCopy(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void copy(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					 final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpCopy(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void move(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					 final Callback callback) throws IOException, RequestException {
		doRequest(new HttpMove(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void move(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					 final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpMove(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void head(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					 final Callback callback) throws IOException, RequestException {
		doRequest(new HttpHead(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void head(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					 final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpHead(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void options(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						final Callback callback) throws IOException, RequestException {
		doRequest(new HttpOptions(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void options(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpOptions(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void link(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					 final Callback callback) throws IOException, RequestException {
		doRequest(new HttpLink(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void link(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					 final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpLink(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void unlink(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					   final Callback callback) throws IOException, RequestException {
		doRequest(new HttpUnlink(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void unlink(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					   final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpUnlink(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void purge(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					  final Callback callback) throws IOException, RequestException {
		doRequest(new HttpPurge(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void purge(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					  final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpPurge(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void lock(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					 final Callback callback) throws IOException, RequestException {
		doRequest(new HttpLock(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void lock(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					 final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpLock(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void unlock(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					   final Callback callback) throws IOException, RequestException {
		doRequest(new HttpUnlock(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void unlock(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					   final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpUnlock(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void propfind(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						 final Callback callback) throws IOException, RequestException {
		doRequest(new HttpPropfind(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void propfind(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpPropfind(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers,
				callback);
	}

	@Override
	public void proppatch(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						  final RequestBody<?> body, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpPropPatch(determineRequestUri(uri, parameters)), requestConfig, headers, body, callback);
	}

	@Override
	public void proppatch(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						  final List<Header> headers, final RequestBody<?> body, final Callback callback)
			throws IOException, RequestException {
		doRequest(new HttpPropPatch(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers,
				body, callback);
	}

	@Override
	public void report(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					   final RequestBody<?> body, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpReport(determineRequestUri(uri, parameters)), requestConfig, headers, body, callback);
	}

	@Override
	public void report(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					   final List<Header> headers, final RequestBody<?> body, final Callback callback)
			throws IOException, RequestException {
		doRequest(new HttpReport(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers,
				body, callback);
	}

	@Override
	public void view(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					 final Callback callback) throws IOException, RequestException {
		doRequest(new HttpView(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void view(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					 final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpView(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	@Override
	public void wrapped(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						final Callback callback) throws IOException, RequestException {
		doRequest(new HttpWrapped(determineRequestUri(uri, parameters)), requestConfig, headers, callback);
	}

	@Override
	public void wrapped(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						final List<Header> headers, final Callback callback) throws IOException, RequestException {
		doRequest(new HttpWrapped(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers, callback);
	}

	protected RequestConfig createRequestConfig(final Configuration configuration) {
		final RequestConfig.Builder builder = RequestConfig.custom()
				.setConnectTimeout(configuration.getConnectTimeout())
				.setConnectionRequestTimeout(configuration.getConnectionRequestTimeout())
				.setSocketTimeout(configuration.getReadTimeout())
				.setAuthenticationEnabled(configuration.isAuthenticationEnabled())
				.setContentCompressionEnabled(configuration.isContentCompressionEnabled())
				.setNormalizeUri(configuration.isNormalizeUri());

		propertyMapper.from(configuration.isExpectContinueEnabled()).to(builder::setExpectContinueEnabled);
		propertyMapper.from(configuration.isAllowRedirects()).to(builder::setRedirectsEnabled);
		propertyMapper.from(configuration.getMaxRedirects()).to(builder::setMaxRedirects);
		propertyMapper.from(configuration.isCircularRedirectsAllowed()).to(builder::setCircularRedirectsAllowed);
		propertyMapper.from(configuration.isRelativeRedirectsAllowed()).to(builder::setRelativeRedirectsAllowed);

		propertyMapper.alwaysApplyingWhenNonNull().from(configuration.getTargetPreferredAuthSchemes())
				.to(builder::setTargetPreferredAuthSchemes);
		propertyMapper.alwaysApplyingWhenNonNull().from(configuration.getProxyPreferredAuthSchemes())
				.to(builder::setProxyPreferredAuthSchemes);

		propertyMapper.from(configuration.getCookieSpec()).to(builder::setCookieSpec);

		final Configuration.Proxy proxy = configuration.getProxy();
		if(proxy != null){
			final String scheme = proxy.getScheme() == null ? null : proxy.getScheme().name();
			final HttpHost proxyHttpHost = proxy.getAddress() == null ? new HttpHost(proxy.getHostname(),
					proxy.getPort(), scheme) : new HttpHost(proxy.getAddress(), proxy.getHostname(), proxy.getPort(),
					scheme);

			builder.setProxy(proxyHttpHost);
		}

		return builder.build();
	}

	protected org.apache.http.ProtocolVersion createProtocolVersion(final ProtocolVersion protocolVersion) {
		if(protocolVersion != null){
			return new org.apache.http.ProtocolVersion(protocolVersion.getProtocol(),
					protocolVersion.getMajor(), protocolVersion.getMinor());
		}else{
			return null;
		}
	}

	protected HttpAsyncClient createHttpClient(final ApacheNioClientConnectionManager connectionManager) {
		final SslConfiguration sslConfiguration = connectionManager.getConfiguration().getSslConfiguration();
		final HttpAsyncClientBuilder builder = HttpAsyncClientBuilder.create()
				.setConnectionManager(connectionManager.getClientConnectionManager());

		propertyMapper.from(connectionManager.getConnectionManagerShared()).to(builder::setConnectionManagerShared);

		if(sslConfiguration != null){
			propertyMapper.from(sslConfiguration.getHostnameVerifier()).to(builder::setSSLHostnameVerifier);
			propertyMapper.from(sslConfiguration.getSslContext()).to(builder::setSSLContext);
		}

		return builder.build();
	}

	protected HttpEntity buildHttpEntity(final RequestBody<?> body) {
		final RequestBodyConverter<RequestBody<?>, HttpEntity> converter = findBodyConverter(REQUEST_BODY_CONVERTS,
				body);
		return converter == null ? new UrlEncodedFormEntity(new ArrayList<>(), StandardCharsets.ISO_8859_1) :
				converter.convert(body);
	}

	protected void doRequest(final HttpRequestBase request, final RequestConfig requestConfig,
							 final List<Header> headers, final Callback callback) throws IOException, RequestException {
		if(headers != null){
			for(Header header : headers){
				request.setHeader(header.getName(), header.getValue());
			}
		}

		Optional.ofNullable(requestConfig).ifPresent(request::setConfig);
		Optional.ofNullable(protocolVersion).ifPresent(request::setProtocolVersion);

		doRequest(request, callback);
	}

	protected void doRequest(final HttpRequestBase request, final RequestConfig requestConfig,
							 final int readTimeout, final List<Header> headers, final Callback callback)
			throws IOException, RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setSocketTimeout(readTimeout);
		doRequest(request, requestConfigBuilder.build(), headers, callback);
	}

	protected void doRequest(final HttpEntityEnclosingRequestBase request, final RequestConfig requestConfig,
							 final List<Header> headers, final RequestBody<?> body, final Callback callback)
			throws IOException, RequestException {
		Optional.ofNullable(body).map(this::buildHttpEntity).ifPresent(request::setEntity);
		doRequest(request, requestConfig, headers, callback);
	}

	protected void doRequest(final HttpEntityEnclosingRequestBase request, final RequestConfig requestConfig,
							 final int readTimeout, final List<Header> headers, final RequestBody<?> body,
							 final Callback callback) throws IOException, RequestException {
		Optional.ofNullable(body).map(this::buildHttpEntity).ifPresent(request::setEntity);
		doRequest(request, requestConfig, readTimeout, headers, callback);
	}

	protected void doRequest(final HttpRequestBase request, final Callback callback) throws IOException,
			RequestException {
		final HttpAsyncRequestProducer httpAsyncRequestProducer = HttpAsyncMethods.create(request);

		if(httpClient instanceof CloseableHttpAsyncClient){
			CloseableHttpAsyncClient closeableHttpAsyncClient = (CloseableHttpAsyncClient) httpClient;

			if(closeableHttpAsyncClient.isRunning() == false){
				closeableHttpAsyncClient.start();
			}
		}

		try{
			File tempFile = File.createTempFile("buession-httpclient-apache-client4-", null);
			Future<Response> future = httpClient.execute(httpAsyncRequestProducer,
					new DefaultZeroCopyConsumer(tempFile), new DefaultCallback(callback));
			future.get();
		}catch(ExecutionException e){
			throw new RequestException(e.getMessage(), e);
		}catch(InterruptedException e){
			throw new RequestException(e.getMessage(), e);
		}finally{
			request.releaseConnection();
		}
	}

	private final static class DefaultZeroCopyConsumer extends ZeroCopyConsumer<Response> {

		public DefaultZeroCopyConsumer(final File file) throws FileNotFoundException {
			super(file);
		}

		@Override
		protected Response process(final HttpResponse response, final File file, final ContentType contentType) {
			final ApacheResponseBuilder apacheResponseBuilder = new ApacheResponseBuilder();
			return apacheResponseBuilder.build(response);
		}

	}

}
