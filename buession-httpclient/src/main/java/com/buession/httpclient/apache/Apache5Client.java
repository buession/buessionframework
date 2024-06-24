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
import com.buession.httpclient.conn.Apache5ClientConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestBodyConverter;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.ConnectTimeoutException;
import com.buession.httpclient.exception.ReadTimeoutException;
import com.buession.httpclient.exception.RequestAbortedException;
import com.buession.httpclient.exception.RequestException;
import com.buession.net.ssl.SslConfiguration;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.util.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class Apache5Client extends AbstractApacheClient {

	private final static Map<Class<? extends RequestBody>, RequestBodyConverter> REQUEST_BODY_CONVERTS =
			ApacheRequestBodyConverterUtils.createApache5ClientRequestBodyConverter();

	private final RequestConfig requestConfig;

	private final HttpClient httpClient;

	private final org.apache.hc.core5.http.ProtocolVersion protocolVersion;

	private final static Logger logger = LoggerFactory.getLogger(Apache5Client.class);

	public Apache5Client(final HttpClient httpClient, final Apache5ClientConnectionManager connectionManager,
						 final ProtocolVersion protocolVersion) {
		super();
		this.requestConfig = createRequestConfig(connectionManager.getConfiguration());
		this.httpClient = httpClient;
		this.protocolVersion = createProtocolVersion(protocolVersion);
	}

	public Apache5Client(final HttpClient httpClient, final RequestConfig requestConfig,
						 final ProtocolVersion protocolVersion) {
		super();
		this.requestConfig = requestConfig;
		this.httpClient = httpClient;
		this.protocolVersion = createProtocolVersion(protocolVersion);
	}

	public Apache5Client(final Apache5ClientConnectionManager connectionManager,
						 final ProtocolVersion protocolVersion) {
		super();
		this.requestConfig = createRequestConfig(connectionManager.getConfiguration());
		this.httpClient = createHttpClient(connectionManager);
		this.protocolVersion = createProtocolVersion(protocolVersion);
	}

	public Apache5Client(final RequestConfig requestConfig, final Apache5ClientConnectionManager connectionManager,
						 final ProtocolVersion protocolVersion) {
		super();
		this.requestConfig = requestConfig;
		this.httpClient = createHttpClient(connectionManager);
		this.protocolVersion = createProtocolVersion(protocolVersion);
	}

	@Override
	public Response get(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpGet(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response get(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpGet(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response post(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						 final RequestBody<?> body) throws IOException, RequestException {
		return doRequest(new HttpPost(determineRequestUri(uri, parameters)), requestConfig, headers,
				body);
	}

	@Override
	public Response post(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers, final RequestBody<?> body) throws IOException, RequestException {
		return doRequest(new HttpPost(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers,
				body);
	}

	@Override
	public Response patch(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						  final RequestBody<?> body) throws IOException, RequestException {
		return doRequest(new HttpPatch(determineRequestUri(uri, parameters)), requestConfig, headers, body);
	}

	@Override
	public Response patch(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						  final List<Header> headers, final RequestBody<?> body) throws IOException, RequestException {
		return doRequest(new HttpPatch(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers,
				body);
	}

	@Override
	public Response put(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						final RequestBody<?> body) throws IOException, RequestException {
		return doRequest(new HttpPut(determineRequestUri(uri, parameters)), requestConfig, headers, body);
	}

	@Override
	public Response put(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						final List<Header> headers, final RequestBody<?> body) throws IOException, RequestException {
		return doRequest(new HttpPut(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers,
				body);
	}

	@Override
	public Response delete(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpDelete(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response delete(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						   final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpDelete(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response connect(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpConnect(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response connect(final URI uri, final int readTimeout, final Map<String, Object> parameters,
							final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpConnect(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response trace(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpTrace(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response trace(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						  final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpTrace(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response copy(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpCopy(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response copy(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpCopy(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response move(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpMove(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response move(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpMove(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response head(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpHead(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response head(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpHead(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response options(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpOptions(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response options(final URI uri, final int readTimeout, final Map<String, Object> parameters,
							final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpOptions(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response link(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpLink(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response link(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpLink(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response unlink(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpUnlink(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response unlink(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						   final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpUnlink(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response purge(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpPurge(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response purge(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						  final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpPurge(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response lock(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpLock(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response lock(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpLock(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response unlock(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpUnlock(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response unlock(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						   final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpUnlock(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response propfind(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpPropfind(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response propfind(final URI uri, final int readTimeout, final Map<String, Object> parameters,
							 final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpPropfind(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response proppatch(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
							  final RequestBody<?> body) throws IOException, RequestException {
		return doRequest(new HttpPropPatch(determineRequestUri(uri, parameters)), requestConfig, headers, body);
	}

	@Override
	public Response proppatch(final URI uri, final int readTimeout, final Map<String, Object> parameters,
							  final List<Header> headers, final RequestBody<?> body)
			throws IOException, RequestException {
		return doRequest(new HttpPropPatch(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers,
				body);
	}

	@Override
	public Response report(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
						   final RequestBody<?> body) throws IOException, RequestException {
		return doRequest(new HttpReport(determineRequestUri(uri, parameters)), requestConfig, headers, body);
	}

	@Override
	public Response report(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						   final List<Header> headers, final RequestBody<?> body)
			throws IOException, RequestException {
		return doRequest(new HttpReport(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers,
				body);
	}

	@Override
	public Response view(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpView(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response view(final URI uri, final int readTimeout, final Map<String, Object> parameters,
						 final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpView(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	@Override
	public Response wrapped(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException {
		return doRequest(new HttpWrapped(determineRequestUri(uri, parameters)), requestConfig, headers);
	}

	@Override
	public Response wrapped(final URI uri, final int readTimeout, final Map<String, Object> parameters,
							final List<Header> headers) throws IOException, RequestException {
		return doRequest(new HttpWrapped(determineRequestUri(uri, parameters)), requestConfig, readTimeout, headers);
	}

	protected RequestConfig createRequestConfig(final Configuration configuration) {
		final RequestConfig.Builder builder = RequestConfig.custom();

		propertyMapper.alwaysApplyingWhenPositiveNumber().from(configuration.getConnectionRequestTimeout())
				.as(Timeout::ofMilliseconds).to(builder::setConnectionRequestTimeout);
		propertyMapper.from(configuration.isExpectContinueEnabled()).to(builder::setExpectContinueEnabled);
		propertyMapper.from(configuration.isAllowRedirects()).to(builder::setRedirectsEnabled);
		propertyMapper.from(configuration.getMaxRedirects()).to(builder::setMaxRedirects);
		propertyMapper.from(configuration.isCircularRedirectsAllowed()).to(builder::setCircularRedirectsAllowed);
		propertyMapper.from(configuration.isHardCancellationEnabled()).to(builder::setHardCancellationEnabled);
		propertyMapper.from(configuration.isAuthenticationEnabled()).to(builder::setAuthenticationEnabled);
		propertyMapper.from(configuration.isContentCompressionEnabled()).to(builder::setContentCompressionEnabled);
		propertyMapper.alwaysApplyingWhenNonNull().from(configuration.getTargetPreferredAuthSchemes())
				.to(builder::setTargetPreferredAuthSchemes);
		propertyMapper.alwaysApplyingWhenNonNull().from(configuration.getProxyPreferredAuthSchemes())
				.to(builder::setProxyPreferredAuthSchemes);
		propertyMapper.from(configuration.getCookieSpec()).to(builder::setCookieSpec);

		return builder.build();
	}

	protected org.apache.hc.core5.http.ProtocolVersion createProtocolVersion(final ProtocolVersion protocolVersion) {
		if(protocolVersion != null){
			return new org.apache.hc.core5.http.ProtocolVersion(protocolVersion.getProtocol(),
					protocolVersion.getMajor(), protocolVersion.getMinor());
		}else{
			return null;
		}
	}

	protected HttpClient createHttpClient(final Apache5ClientConnectionManager connectionManager) {
		final SslConfiguration sslConfiguration = connectionManager.getConfiguration().getSslConfiguration();
		final HttpClientBuilder builder = HttpClientBuilder.create()
				.setConnectionManager(connectionManager.getClientConnectionManager());

		propertyMapper.from(connectionManager.getConnectionManagerShared()).to(builder::setConnectionManagerShared);
		propertyMapper.alwaysApplyingWhenNonNull().from(connectionManager.getConfiguration().getProxy())
				.as((v)->new HttpHost(v.getScheme() == null ? null : v.getScheme().name(), v.getAddress(),
						v.getHostname(), v.getPort())).to(builder::setProxy);

		if(sslConfiguration != null){
			/*
			propertyMapper.from(sslConfiguration.getHostnameVerifier()).to(builder::setSSLHostnameVerifier);
			propertyMapper.from(sslConfiguration.getSslContext()).to(builder::setSSLContext);

			if(sslConfiguration.getSslSocketFactory() != null){
				builder.setSSLSocketFactory(new SSLConnectionSocketFactory(sslConfiguration.getSslSocketFactory(),
						sslConfiguration.getHostnameVerifier()));
			}

			 */
		}

		return builder.build();
	}

	protected HttpEntity buildHttpEntity(final RequestBody<?> body) {
		final RequestBodyConverter<RequestBody<?>, HttpEntity> converter = findBodyConverter(REQUEST_BODY_CONVERTS,
				body);
		return converter == null ? new UrlEncodedFormEntity(new ArrayList<>(), StandardCharsets.ISO_8859_1) :
				converter.convert(body);
	}

	protected Response doRequest(final HttpUriRequestBase request, final RequestConfig requestConfig,
								 final List<Header> headers) throws IOException, RequestException {
		if(headers != null){
			for(Header header : headers){
				request.setHeader(header.getName(), header.getValue());
			}
		}

		Optional.ofNullable(requestConfig).ifPresent(request::setConfig);
		Optional.ofNullable(protocolVersion).ifPresent(request::setVersion);

		return doRequest(request);
	}

	protected Response doRequest(final HttpUriRequestBase request, final RequestConfig requestConfig,
								 final int readTimeout, final List<Header> headers) throws IOException,
			RequestException {
		final RequestConfig.Builder requestConfigBuilder = RequestConfig.copy(requestConfig)
				.setResponseTimeout(readTimeout, TimeUnit.MILLISECONDS);
		return doRequest(request, requestConfigBuilder.build(), headers);
	}

	protected Response doRequest(final HttpUriRequestBase request, final RequestConfig requestConfig,
								 final List<Header> headers, final RequestBody<?> body)
			throws IOException, RequestException {
		Optional.ofNullable(body).map(this::buildHttpEntity).ifPresent(request::setEntity);
		return doRequest(request, requestConfig, headers);
	}

	protected Response doRequest(final HttpUriRequestBase request, final RequestConfig requestConfig,
								 final int readTimeout, final List<Header> headers, final RequestBody<?> body)
			throws IOException, RequestException {
		Optional.ofNullable(body).map(this::buildHttpEntity).ifPresent(request::setEntity);
		return doRequest(request, requestConfig, readTimeout, headers);
	}

	protected Response doRequest(final ClassicHttpRequest request) throws IOException, RequestException {
		final Apache5ResponseBuilder apacheResponseBuilder = new Apache5ResponseBuilder();

		try{
			HttpResponse httpResponse = httpClient.executeOpen(null, request, null);
			return apacheResponseBuilder.build(httpResponse);
		}catch(IOException e){
			if(logger.isErrorEnabled()){
				logger.error("Request({}) url: {} error.", request.getMethod(), request.getRequestUri(), e);
			}

			if(e instanceof org.apache.hc.client5.http.ConnectTimeoutException){
				throw new ConnectTimeoutException(e.getMessage());
			}else if(e instanceof SocketTimeoutException){
				throw new ReadTimeoutException(e.getMessage());
			}else if(e instanceof org.apache.hc.client5.http.impl.classic.RequestAbortedException){
				throw new RequestAbortedException(e.getMessage());
			}else if(e instanceof ClientProtocolException){
				throw new RequestException(e.getMessage(), e);
			}else if(e instanceof UnknownHostException){
				throw e;
			}else{
				throw new RequestException(e.getMessage(), e);
			}
		}finally{
			//request.getHttpRequest().releaseConnection();
		}
	}

}
