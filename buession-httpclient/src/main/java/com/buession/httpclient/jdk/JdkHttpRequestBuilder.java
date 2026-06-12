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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.jdk;

import com.buession.httpclient.core.ChunkedInputStreamRequestBody;
import com.buession.httpclient.core.ContentType;
import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.HtmlRawRequestBody;
import com.buession.httpclient.core.InputStreamRequestBody;
import com.buession.httpclient.core.JavaScriptRawRequestBody;
import com.buession.httpclient.core.JsonRawRequestBody;
import com.buession.httpclient.core.MultipartFormRequestBody;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.RepeatableInputStreamRequestBody;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.internal.convert.RequestBodyConverter;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.TextRawRequestBody;
import com.buession.httpclient.core.XmlRawRequestBody;
import com.buession.httpclient.core.utils.UriUtils;
import com.buession.httpclient.jdk.convert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * JDK {@link java.net.http.HttpClient} 请求构建器
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class JdkHttpRequestBuilder {

	private final static HttpRequest.BodyPublisher DEFAULT_REQUEST_BODY = HttpRequest.BodyPublishers.noBody();

	private final static Map<Class<? extends RequestBody>, RequestBodyConverter> REQUEST_BODY_CONVERTS = new HashMap<>(
			16, 0.8F);

	protected URI uri;

	protected Map<String, Object> parameters;

	protected HttpRequest.Builder requestBuilder;

	private final static Logger logger = LoggerFactory.getLogger(JdkHttpRequestBuilder.class);

	static {
		REQUEST_BODY_CONVERTS.put(ChunkedInputStreamRequestBody.class, new JdkChunkedInputStreamRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(EncodedFormRequestBody.class, new JdkEncodedFormRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(HtmlRawRequestBody.class, new JdkHtmlRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(InputStreamRequestBody.class, new JdkInputStreamRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(JavaScriptRawRequestBody.class, new JdkJavaScriptRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(JsonRawRequestBody.class, new JdkJsonRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(MultipartFormRequestBody.class, new JdkMultipartFormRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(RepeatableInputStreamRequestBody.class,
				new JdkRepeatableInputStreamRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(TextRawRequestBody.class, new JdkTextRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(XmlRawRequestBody.class, new JdkXmlRawRequestBodyConverter());
	}

	private JdkHttpRequestBuilder() {
		requestBuilder = HttpRequest.newBuilder();
	}

	/**
	 * 创建 {@link JdkHttpRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return {@link JdkHttpRequestBuilder} 实例
	 */
	public static JdkHttpRequestBuilder create(URI uri) {
		final JdkHttpRequestBuilder builder = new JdkHttpRequestBuilder();
		builder.uri = uri;
		return builder;
	}

	/**
	 * 创建 {@link JdkHttpRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return {@link JdkHttpRequestBuilder} 实例
	 */
	public static JdkHttpRequestBuilder create(URI uri, Map<String, Object> parameters) {
		return create(uri).setParameters(parameters);
	}

	/**
	 * 创建 {@link JdkHttpRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return {@link JdkHttpRequestBuilder} 实例
	 */
	public static JdkHttpRequestBuilder create(URI uri, List<Header> headers) {
		return create(uri).setHeaders(headers);
	}

	/**
	 * 创建 {@link JdkHttpRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return {@link JdkHttpRequestBuilder} 实例
	 */
	public static JdkHttpRequestBuilder create(URI uri, Map<String, Object> parameters, List<Header> headers) {
		return create(uri, parameters).setHeaders(headers);
	}

	public JdkHttpRequestBuilder setProtocolVersion(ProtocolVersion protocolVersion) {
		if(protocolVersion == ProtocolVersion.HTTP_1_1){
			requestBuilder.version(HttpClient.Version.HTTP_1_1);
		}else if(protocolVersion == ProtocolVersion.HTTP_2_0){
			requestBuilder.version(HttpClient.Version.HTTP_2);
		}
		return this;
	}

	public JdkHttpRequestBuilder timeout(long timeout) {
		if(timeout > 0){
			requestBuilder.timeout(Duration.ofMillis(timeout));
		}
		return this;
	}

	public JdkHttpRequestBuilder setHeaders(List<Header> headers) {
		if(headers != null){
			for(Header header : headers){
				requestBuilder.header(header.getName(), header.getValue());
			}
		}

		return this;
	}

	public JdkHttpRequestBuilder setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
		return this;
	}

	public JdkHttpRequestBuilder get() {
		requestBuilder.GET();
		return this;
	}

	public JdkHttpRequestBuilder post(RequestBody<?> body) {
		if(body == null){
			requestBuilder.POST(DEFAULT_REQUEST_BODY);
		}else{
			requestBuilder.POST(buildRequestBody(body));
			Optional.ofNullable(builderHeader(body))
					.ifPresent((header)->requestBuilder.header(header.getName(), header.getValue()));
		}

		return this;
	}

	public JdkHttpRequestBuilder patch(RequestBody<?> body) {
		return method(RequestMethod.PATCH, body);
	}

	public JdkHttpRequestBuilder put(RequestBody<?> body) {
		if(body == null){
			requestBuilder.PUT(DEFAULT_REQUEST_BODY);
		}else{
			requestBuilder.PUT(buildRequestBody(body));
			Optional.ofNullable(builderHeader(body))
					.ifPresent((header)->requestBuilder.header(header.getName(), header.getValue()));
		}

		return this;
	}

	public JdkHttpRequestBuilder delete() {
		requestBuilder.DELETE();
		return this;
	}

	public JdkHttpRequestBuilder connect() {
		return method(RequestMethod.CONNECT);
	}

	public JdkHttpRequestBuilder trace() {
		return method(RequestMethod.TRACE);
	}

	public JdkHttpRequestBuilder copy() {
		return method(RequestMethod.COPY);
	}

	public JdkHttpRequestBuilder move() {
		return method(RequestMethod.MOVE);
	}

	public JdkHttpRequestBuilder head() {
		return method(RequestMethod.HEAD);
	}

	public JdkHttpRequestBuilder options() {
		return method(RequestMethod.OPTIONS);
	}

	public JdkHttpRequestBuilder link() {
		return method(RequestMethod.LINK);
	}

	public JdkHttpRequestBuilder unlink() {
		return method(RequestMethod.UNLINK);
	}

	public JdkHttpRequestBuilder purge() {
		return method(RequestMethod.PURGE);
	}

	public JdkHttpRequestBuilder lock() {
		return method(RequestMethod.LOCK);
	}

	public JdkHttpRequestBuilder unlock() {
		return method(RequestMethod.UNLOCK);
	}

	public JdkHttpRequestBuilder propfind() {
		return method(RequestMethod.PROPFIND);
	}

	public JdkHttpRequestBuilder proppatch(RequestBody<?> body) {
		return method(RequestMethod.PROPPATCH, body);
	}

	public JdkHttpRequestBuilder report(RequestBody<?> body) {
		return method(RequestMethod.REPORT, body);
	}

	public JdkHttpRequestBuilder view() {
		return method(RequestMethod.VIEW);
	}

	public JdkHttpRequestBuilder wrapped() {
		return method(RequestMethod.WRAPPED);
	}

	public HttpRequest build() {
		requestBuilder.uri(UriUtils.determineRequestUri(uri, parameters));
		return requestBuilder.build();
	}

	protected JdkHttpRequestBuilder method(final RequestMethod method) {
		requestBuilder.method(method.name(), DEFAULT_REQUEST_BODY);
		return this;
	}

	protected JdkHttpRequestBuilder method(final RequestMethod method, RequestBody<?> body) {
		if(body == null){
			requestBuilder.method(method.name(), DEFAULT_REQUEST_BODY);
		}else{
			requestBuilder.method(method.name(), buildRequestBody(body));
			Optional.ofNullable(builderHeader(body))
					.ifPresent((header)->requestBuilder.header(header.getName(), header.getValue()));
		}
		return this;
	}

	private static Header builderHeader(final RequestBody<?> body) {
		ContentType contentType = body.getContentType();
		return contentType == null ? null : new Header("Content-Type", contentType.getMimeType());
	}

	@SuppressWarnings({"unchecked"})
	private HttpRequest.BodyPublisher buildRequestBody(final RequestBody<?> data) {
		if(data == null){
			return DEFAULT_REQUEST_BODY;
		}

		final RequestBodyConverter<RequestBody<?>, HttpRequest.BodyPublisher> convert = REQUEST_BODY_CONVERTS.get(
				data.getClass());
		return convert == null ? DEFAULT_REQUEST_BODY : convert.convert(data);
	}

}