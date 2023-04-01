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
package com.buession.httpclient.okhttp;

import com.buession.httpclient.core.ChunkedInputStreamRequestBody;
import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.HtmlRawRequestBody;
import com.buession.httpclient.core.InputStreamRequestBody;
import com.buession.httpclient.core.JavaScriptRawRequestBody;
import com.buession.httpclient.core.JsonRawRequestBody;
import com.buession.httpclient.core.MultipartFormRequestBody;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.RepeatableInputStreamRequestBody;
import com.buession.httpclient.core.Request;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestBodyConverter;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.TextRawRequestBody;
import com.buession.httpclient.core.XmlRawRequestBody;
import com.buession.httpclient.core.AbstractRequestBuilder;
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

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class OkHttpRequestBuilder extends AbstractRequestBuilder<OkHttpRequestBuilder.OkHttpRequest> {

	private final static okhttp3.RequestBody DEFAULT_REQUEST_BODY = new FormBody.Builder().build();

	private final static Map<Class<? extends RequestBody>, RequestBodyConverter> REQUEST_BODY_CONVERTS = new HashMap<>(
			16, 0.8F);

	static{
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

	private OkHttpRequestBuilder(){
		request = new OkHttpRequest();
	}

	/**
	 * 创建 {@link OkHttpRequestBuilder} 实例
	 *
	 * @return {@link OkHttpRequestBuilder} 实例
	 */
	public static OkHttpRequestBuilder create(){
		return new OkHttpRequestBuilder();
	}

	/**
	 * 创建 {@link OkHttpRequestBuilder} 实例
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return {@link OkHttpRequestBuilder} 实例
	 */
	@Deprecated
	public static OkHttpRequestBuilder create(String url){
		final OkHttpRequestBuilder builder = create();
		builder.setUrl(url);
		return builder;
	}

	/**
	 * 创建 {@link OkHttpRequestBuilder} 实例
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return {@link OkHttpRequestBuilder} 实例
	 */
	@Deprecated
	public static OkHttpRequestBuilder create(String url, Map<String, Object> parameters){
		return create(url).setParameters(parameters);
	}

	/**
	 * 创建 {@link OkHttpRequestBuilder} 实例
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return {@link OkHttpRequestBuilder} 实例
	 */
	@Deprecated
	public static OkHttpRequestBuilder create(String url, List<Header> headers){
		return create(url).setHeaders(headers);
	}

	/**
	 * 创建 {@link OkHttpRequestBuilder} 实例
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return {@link OkHttpRequestBuilder} 实例
	 */
	@Deprecated
	public static OkHttpRequestBuilder create(String url, Map<String, Object> parameters, List<Header> headers){
		return create(url, parameters).setHeaders(headers);
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
	public static OkHttpRequestBuilder create(URI uri){
		final OkHttpRequestBuilder builder = create();
		builder.setUri(uri);
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
	public static OkHttpRequestBuilder create(URI uri, Map<String, Object> parameters){
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
	public static OkHttpRequestBuilder create(URI uri, List<Header> headers){
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
	public static OkHttpRequestBuilder create(URI uri, Map<String, Object> parameters, List<Header> headers){
		return create(uri, parameters).setHeaders(headers);
	}

	@Override
	public OkHttpRequestBuilder setProtocolVersion(ProtocolVersion protocolVersion){
		return this;
	}

	@Override
	public OkHttpRequestBuilder setHeaders(List<Header> headers){
		request.setHeaders(headers);
		return this;
	}

	@Override
	public OkHttpRequestBuilder setParameters(Map<String, Object> parameters){
		this.parameters = parameters;
		return this;
	}

	@Override
	public OkHttpRequestBuilder get(){
		return setRequest(new okhttp3.RequestBuilder().get(), RequestMethod.GET);
	}

	@Override
	public OkHttpRequestBuilder post(RequestBody<?> body){
		return setRequest(body == null ? new okhttp3.RequestBuilder().post() : new okhttp3.RequestBuilder().post(
				buildRequestBody(body)), RequestMethod.POST);
	}

	@Override
	public OkHttpRequestBuilder patch(RequestBody<?> body){
		return setRequest(body == null ? new okhttp3.RequestBuilder().patch() : new okhttp3.RequestBuilder().patch(
				buildRequestBody(body)), RequestMethod.PATCH);
	}

	@Override
	public OkHttpRequestBuilder put(RequestBody<?> body){
		return setRequest(body == null ? new okhttp3.RequestBuilder().put() : new okhttp3.RequestBuilder().put(
				buildRequestBody(body)), RequestMethod.PUT);
	}

	@Override
	public OkHttpRequestBuilder delete(){
		return setRequest(new okhttp3.RequestBuilder().delete(), RequestMethod.DELETE);
	}

	@Override
	public OkHttpRequestBuilder connect(){
		return setRequest(new okhttp3.RequestBuilder().connect(), RequestMethod.CONNECT);
	}

	@Override
	public OkHttpRequestBuilder trace(){
		return setRequest(new okhttp3.RequestBuilder().trace(), RequestMethod.TRACE);
	}

	@Override
	public OkHttpRequestBuilder copy(){
		return setRequest(new okhttp3.RequestBuilder().copy(), RequestMethod.COPY);
	}

	@Override
	public OkHttpRequestBuilder move(){
		return setRequest(new okhttp3.RequestBuilder().move(), RequestMethod.MOVE);
	}

	@Override
	public OkHttpRequestBuilder head(){
		return setRequest(new okhttp3.RequestBuilder().head(), RequestMethod.HEAD);
	}

	@Override
	public OkHttpRequestBuilder options(){
		return setRequest(new okhttp3.RequestBuilder().options(), RequestMethod.OPTIONS);
	}

	@Override
	public OkHttpRequestBuilder link(){
		return setRequest(new okhttp3.RequestBuilder().link(), RequestMethod.LINK);
	}

	@Override
	public OkHttpRequestBuilder unlink(){
		return setRequest(new okhttp3.RequestBuilder().unlink(), RequestMethod.UNLINK);
	}

	@Override
	public OkHttpRequestBuilder purge(){
		return setRequest(new okhttp3.RequestBuilder().purge(), RequestMethod.PURGE);
	}

	@Override
	public OkHttpRequestBuilder lock(){
		return setRequest(new okhttp3.RequestBuilder().lock(), RequestMethod.LOCK);
	}

	@Override
	public OkHttpRequestBuilder unlock(){
		return setRequest(new okhttp3.RequestBuilder().unlock(), RequestMethod.UNLOCK);
	}

	@Override
	public OkHttpRequestBuilder propfind(){
		return setRequest(new okhttp3.RequestBuilder().propfind(), RequestMethod.PROPFIND);
	}

	@Override
	public OkHttpRequestBuilder proppatch(RequestBody<?> body){
		return setRequest(
				body == null ? new okhttp3.RequestBuilder().proppatch() : new okhttp3.RequestBuilder().proppatch(
						buildRequestBody(body)), RequestMethod.PROPPATCH);
	}

	@Override
	public OkHttpRequestBuilder report(RequestBody<?> body){
		return setRequest(
				body == null ? new okhttp3.RequestBuilder().report() : new okhttp3.RequestBuilder().report(
						buildRequestBody(body)), RequestMethod.REPORT);
	}

	@Override
	public OkHttpRequestBuilder view(){
		return setRequest(new okhttp3.RequestBuilder().view(), RequestMethod.VIEW);
	}

	@Override
	public OkHttpRequestBuilder wrapped(){
		return setRequest(new okhttp3.RequestBuilder().wrapped(), RequestMethod.WRAPPED);
	}

	@Override
	public OkHttpRequest build(){
		OkHttpRequest request = super.build();

		final Headers.Builder headersBuilder = new Headers.Builder();

		if(request.getHeaders() != null){
			for(Header header : request.getHeaders()){
				headersBuilder.add(header.getName(), header.getValue());
			}
		}

		request.getRequestBuilder().url(request.getUrl()).headers(headersBuilder.build());

		return request;
	}

	protected OkHttpRequestBuilder setRequest(final okhttp3.Request.Builder okhttp3RequestBuilder,
											  final RequestMethod method){
		request.setRequestBuilder(okhttp3RequestBuilder);
		request.setMethod(method);
		return this;
	}

	@SuppressWarnings({"unchecked"})
	private okhttp3.RequestBody buildRequestBody(final RequestBody<?> data){
		if(data == null){
			return DEFAULT_REQUEST_BODY;
		}

		final RequestBodyConverter<RequestBody<?>, okhttp3.RequestBody> convert = REQUEST_BODY_CONVERTS.get(
				data.getClass());
		return convert == null ? DEFAULT_REQUEST_BODY : convert.convert(data);
	}

	public final static class OkHttpRequest extends Request {

		private okhttp3.Request.Builder builder;

		public okhttp3.Request.Builder getRequestBuilder(){
			return builder;
		}

		public void setRequestBuilder(okhttp3.Request.Builder builder){
			this.builder = builder;
		}

	}

}