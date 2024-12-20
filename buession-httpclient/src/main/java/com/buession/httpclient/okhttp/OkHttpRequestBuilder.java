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
package com.buession.httpclient.okhttp;

import com.buession.core.builder.MapBuilder;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
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
import com.buession.httpclient.core.RequestBodyConverter;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.TextRawRequestBody;
import com.buession.httpclient.core.XmlRawRequestBody;
import com.buession.httpclient.core.utils.UriUtils;
import com.buession.httpclient.okhttp.convert.ChunkedInputStreamRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.EncodedFormRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.HtmlRawRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.InputStreamRequestBodyConvert;
import com.buession.httpclient.okhttp.convert.JavaScriptRawRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.JsonRawRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.MultipartFormRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.RepeatableInputStreamRequestBodyConvert;
import com.buession.httpclient.okhttp.convert.TextRawRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.XmlRawRequestBodyConverter;
import okhttp3.FormBody;
import okhttp3.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class OkHttpRequestBuilder {

	private final static okhttp3.RequestBody DEFAULT_REQUEST_BODY = new FormBody.Builder().build();

	private final static Map<Class<? extends RequestBody>, RequestBodyConverter> REQUEST_BODY_CONVERTS = new HashMap<>(
			16, 0.8F);

	protected URI uri;

	protected Map<String, Object> parameters;

	protected OkHttpRequest request;

	private final static Logger logger = LoggerFactory.getLogger(OkHttpRequestBuilder.class);

	static {
		REQUEST_BODY_CONVERTS.put(ChunkedInputStreamRequestBody.class, new ChunkedInputStreamRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(EncodedFormRequestBody.class, new EncodedFormRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(HtmlRawRequestBody.class, new HtmlRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(InputStreamRequestBody.class, new InputStreamRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(JavaScriptRawRequestBody.class, new JavaScriptRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(JsonRawRequestBody.class, new JsonRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(MultipartFormRequestBody.class, new MultipartFormRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(RepeatableInputStreamRequestBody.class,
				new RepeatableInputStreamRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(TextRawRequestBody.class, new TextRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(XmlRawRequestBody.class, new XmlRawRequestBodyConverter());
	}

	private OkHttpRequestBuilder() {
		request = new OkHttpRequest();
	}

	/**
	 * 创建 {@link OkHttpRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return {@link OkHttpRequestBuilder} 实例
	 *
	 * @since 2.3.0
	 */
	public static OkHttpRequestBuilder create(URI uri) {
		final OkHttpRequestBuilder builder = new OkHttpRequestBuilder();
		builder.uri = uri;
		return builder;
	}

	/**
	 * 创建 {@link OkHttpRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return {@link OkHttpRequestBuilder} 实例
	 *
	 * @since 2.3.0
	 */
	public static OkHttpRequestBuilder create(URI uri, Map<String, Object> parameters) {
		return create(uri).setParameters(parameters);
	}

	/**
	 * 创建 {@link OkHttpRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return {@link OkHttpRequestBuilder} 实例
	 *
	 * @since 2.3.0
	 */
	public static OkHttpRequestBuilder create(URI uri, List<Header> headers) {
		return create(uri).setHeaders(headers);
	}

	/**
	 * 创建 {@link OkHttpRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return {@link OkHttpRequestBuilder} 实例
	 *
	 * @since 2.3.0
	 */
	public static OkHttpRequestBuilder create(URI uri, Map<String, Object> parameters, List<Header> headers) {
		return create(uri, parameters).setHeaders(headers);
	}

	public OkHttpRequestBuilder setProtocolVersion(ProtocolVersion protocolVersion) {
		return this;
	}

	public OkHttpRequestBuilder setHeaders(List<Header> headers) {
		request.setHeaders(headers);
		return this;
	}

	public OkHttpRequestBuilder setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
		return this;
	}

	public OkHttpRequestBuilder get() {
		return setRequest(new okhttp3.RequestBuilder().get(), RequestMethod.GET);
	}

	public OkHttpRequestBuilder post(RequestBody<?> body) {
		return setRequest(body == null ? new okhttp3.RequestBuilder().post() : new okhttp3.RequestBuilder().post(
				buildRequestBody(body)).headers(builderHeaders(body)), RequestMethod.POST);
	}

	public OkHttpRequestBuilder patch(RequestBody<?> body) {
		return setRequest(body == null ? new okhttp3.RequestBuilder().patch() : new okhttp3.RequestBuilder().patch(
				buildRequestBody(body)).headers(builderHeaders(body)), RequestMethod.PATCH);
	}

	public OkHttpRequestBuilder put(RequestBody<?> body) {
		return setRequest(body == null ? new okhttp3.RequestBuilder().put() : new okhttp3.RequestBuilder().put(
				buildRequestBody(body)).headers(builderHeaders(body)), RequestMethod.PUT);
	}

	public OkHttpRequestBuilder delete() {
		return setRequest(new okhttp3.RequestBuilder().delete(), RequestMethod.DELETE);
	}

	public OkHttpRequestBuilder connect() {
		return setRequest(new okhttp3.RequestBuilder().connect(), RequestMethod.CONNECT);
	}

	public OkHttpRequestBuilder trace() {
		return setRequest(new okhttp3.RequestBuilder().trace(), RequestMethod.TRACE);
	}

	public OkHttpRequestBuilder copy() {
		return setRequest(new okhttp3.RequestBuilder().copy(), RequestMethod.COPY);
	}

	public OkHttpRequestBuilder move() {
		return setRequest(new okhttp3.RequestBuilder().move(), RequestMethod.MOVE);
	}

	public OkHttpRequestBuilder head() {
		return setRequest(new okhttp3.RequestBuilder().head(), RequestMethod.HEAD);
	}

	public OkHttpRequestBuilder options() {
		return setRequest(new okhttp3.RequestBuilder().options(), RequestMethod.OPTIONS);
	}

	public OkHttpRequestBuilder link() {
		return setRequest(new okhttp3.RequestBuilder().link(), RequestMethod.LINK);
	}

	public OkHttpRequestBuilder unlink() {
		return setRequest(new okhttp3.RequestBuilder().unlink(), RequestMethod.UNLINK);
	}

	public OkHttpRequestBuilder purge() {
		return setRequest(new okhttp3.RequestBuilder().purge(), RequestMethod.PURGE);
	}

	public OkHttpRequestBuilder lock() {
		return setRequest(new okhttp3.RequestBuilder().lock(), RequestMethod.LOCK);
	}

	public OkHttpRequestBuilder unlock() {
		return setRequest(new okhttp3.RequestBuilder().unlock(), RequestMethod.UNLOCK);
	}

	public OkHttpRequestBuilder propfind() {
		return setRequest(new okhttp3.RequestBuilder().propfind(), RequestMethod.PROPFIND);
	}

	public OkHttpRequestBuilder proppatch(RequestBody<?> body) {
		return setRequest(
				body == null ? new okhttp3.RequestBuilder().proppatch() : new okhttp3.RequestBuilder().proppatch(
						buildRequestBody(body)).headers(builderHeaders(body)), RequestMethod.PROPPATCH);
	}

	public OkHttpRequestBuilder report(RequestBody<?> body) {
		return setRequest(
				body == null ? new okhttp3.RequestBuilder().report() : new okhttp3.RequestBuilder().report(
						buildRequestBody(body)).headers(builderHeaders(body)), RequestMethod.REPORT);
	}

	public OkHttpRequestBuilder view() {
		return setRequest(new okhttp3.RequestBuilder().view(), RequestMethod.VIEW);
	}

	public OkHttpRequestBuilder wrapped() {
		return setRequest(new okhttp3.RequestBuilder().wrapped(), RequestMethod.WRAPPED);
	}

	public com.buession.httpclient.okhttp.OkHttpRequest build() {
		final Headers.Builder headersBuilder = new Headers.Builder();

		if(request.getHeaders() != null){
			for(Header header : request.getHeaders()){
				headersBuilder.add(header.getName(), header.getValue());
			}
		}

		request.getRequestBuilder().url(determineRequestUrl(uri, parameters)).headers(headersBuilder.build());

		return request;
	}

	protected OkHttpRequestBuilder setRequest(final okhttp3.Request.Builder requestBuilder,
											  final RequestMethod method) {
		request.setRequestBuilder(requestBuilder);
		request.setMethod(method);
		return this;
	}

	private static Headers builderHeaders(final RequestBody<?> body) {
		ContentType contentType = body.getContentType();
		if(contentType == null){
			return Headers.of();
		}else{
			final Headers.Builder builder = new Headers.Builder();

			builder.add("Content-Type", contentType.getMimeType());

			return builder.build();
		}
	}

	@SuppressWarnings({"unchecked"})
	private okhttp3.RequestBody buildRequestBody(final RequestBody<?> data) {
		if(data == null){
			return DEFAULT_REQUEST_BODY;
		}

		final RequestBodyConverter<RequestBody<?>, okhttp3.RequestBody> convert = REQUEST_BODY_CONVERTS.get(
				data.getClass());
		return convert == null ? DEFAULT_REQUEST_BODY : convert.convert(data);
	}

	private static String determineRequestUrl(final URI uri, final Map<String, Object> parameters) {
		if(Validate.isEmpty(uri.getRawQuery())){
			return uri.toString();
		}

		final StringBuilder newQuery = new StringBuilder(uri.getRawQuery().length());

		newQuery.append(uri.getRawQuery());

		if(StringUtils.endsWith(uri.getRawQuery(), '&') == false){
			newQuery.append('&');
		}

		newQuery.append(UriUtils.buildQuery(parameters, false));

		try{
			return new URI(uri.getScheme(), uri.getAuthority(), uri.getHost(), uri.getPort(),
					uri.getPath(), newQuery.toString(), uri.getFragment()).toString();
		}catch(URISyntaxException e){
			if(logger.isErrorEnabled()){
				logger.error("URL {} add parameters syntax: {}, reason: {}", uri, e.getMessage(), e.getReason());
			}
			return uri.toString();
		}
	}

}