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
package com.buession.httpclient.apache;

import com.buession.httpclient.apache.convert.ChunkedInputStreamRequestBodyConverter;
import com.buession.httpclient.apache.convert.InputStreamRequestBodyConvert;
import com.buession.httpclient.apache.convert.MultipartFormRequestBodyConverter;
import com.buession.httpclient.apache.convert.RepeatableInputStreamRequestBodyConvert;
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
import com.buession.httpclient.apache.convert.EncodedFormRequestBodyConverter;
import com.buession.httpclient.apache.convert.HtmlRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.JavaScriptRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.JsonRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.TextRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.XmlRawRequestBodyConverter;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class ApacheRequestBuilder extends AbstractRequestBuilder<ApacheRequestBuilder.HttpComponentsRequest> {

	private final static HttpEntity DEFAULT_HTTP_ENTITY = new UrlEncodedFormEntity(new ArrayList<>(),
			StandardCharsets.ISO_8859_1);

	private final static Map<Class<? extends RequestBody>, RequestBodyConverter> REQUEST_BODY_CONVERTS =
			new HashMap<>(16, 0.8F);

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

	private ApacheRequestBuilder(){
		request = new HttpComponentsRequest();
	}

	/**
	 * 创建 {@link ApacheRequestBuilder} 实例
	 *
	 * @return {@link ApacheRequestBuilder} 实例
	 */
	public static ApacheRequestBuilder create(){
		return new ApacheRequestBuilder();
	}

	/**
	 * 创建 {@link ApacheRequestBuilder} 实例
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return {@link ApacheRequestBuilder} 实例
	 */
	@Deprecated
	public static ApacheRequestBuilder create(String url){
		final ApacheRequestBuilder builder = create();
		builder.setUrl(url);
		return builder;
	}

	/**
	 * 创建 {@link ApacheRequestBuilder} 实例
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return {@link ApacheRequestBuilder} 实例
	 */
	@Deprecated
	public static ApacheRequestBuilder create(String url, Map<String, Object> parameters){
		return create(url).setParameters(parameters);
	}

	/**
	 * 创建 {@link ApacheRequestBuilder} 实例
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return {@link ApacheRequestBuilder} 实例
	 */
	@Deprecated
	public static ApacheRequestBuilder create(String url, List<Header> headers){
		return create(url).setHeaders(headers);
	}

	/**
	 * 创建 {@link ApacheRequestBuilder} 实例
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return {@link ApacheRequestBuilder} 实例
	 */
	@Deprecated
	public static ApacheRequestBuilder create(String url, Map<String, Object> parameters, List<Header> headers){
		return create(url, parameters).setHeaders(headers);
	}

	/**
	 * 创建 {@link ApacheRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return {@link ApacheRequestBuilder} 实例
	 *
	 * @since 2.3.0
	 */
	public static ApacheRequestBuilder create(URI uri){
		final ApacheRequestBuilder builder = create();
		builder.setUri(uri);
		return builder;
	}

	/**
	 * 创建 {@link ApacheRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return {@link ApacheRequestBuilder} 实例
	 *
	 * @since 2.3.0
	 */
	public static ApacheRequestBuilder create(URI uri, Map<String, Object> parameters){
		return create(uri).setParameters(parameters);
	}

	/**
	 * 创建 {@link ApacheRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return {@link ApacheRequestBuilder} 实例
	 *
	 * @since 2.3.0
	 */
	public static ApacheRequestBuilder create(URI uri, List<Header> headers){
		return create(uri).setHeaders(headers);
	}

	/**
	 * 创建 {@link ApacheRequestBuilder} 实例
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return {@link ApacheRequestBuilder} 实例
	 *
	 * @since 2.3.0
	 */
	public static ApacheRequestBuilder create(URI uri, Map<String, Object> parameters, List<Header> headers){
		return create(uri, parameters).setHeaders(headers);
	}

	@Override
	public ApacheRequestBuilder setProtocolVersion(ProtocolVersion protocolVersion){
		if(protocolVersion != null){
			request.getHttpRequest().setProtocolVersion(
					new org.apache.http.ProtocolVersion(protocolVersion.getProtocol(), protocolVersion.getMajor(),
							protocolVersion.getMinor()));
		}

		return this;
	}

	@Override
	public ApacheRequestBuilder setHeaders(List<Header> headers){
		request.setHeaders(headers);
		return this;
	}

	@Override
	public ApacheRequestBuilder setParameters(Map<String, Object> parameters){
		this.parameters = parameters;
		return this;
	}

	public ApacheRequestBuilder setRequestConfig(RequestConfig config){
		this.request.getHttpRequest().setConfig(config);
		return this;
	}

	@Override
	public ApacheRequestBuilder get(){
		return setRequest(new HttpGet(), RequestMethod.GET);
	}

	@Override
	public ApacheRequestBuilder post(RequestBody<?> body){
		return setRequest(new HttpPost(), RequestMethod.POST, body);
	}

	@Override
	public ApacheRequestBuilder patch(RequestBody<?> body){
		return setRequest(new HttpPatch(), RequestMethod.PATCH, body);
	}

	@Override
	public ApacheRequestBuilder put(RequestBody<?> body){
		return setRequest(new HttpPut(), RequestMethod.PUT, body);
	}

	@Override
	public ApacheRequestBuilder delete(){
		return setRequest(new HttpDelete(), RequestMethod.DELETE);
	}

	@Override
	public ApacheRequestBuilder connect(){
		return setRequest(new HttpConnect(), RequestMethod.CONNECT);
	}

	@Override
	public ApacheRequestBuilder trace(){
		return setRequest(new HttpTrace(), RequestMethod.TRACE);
	}

	@Override
	public ApacheRequestBuilder copy(){
		return setRequest(new HttpCopy(), RequestMethod.COPY);
	}

	@Override
	public ApacheRequestBuilder move(){
		return setRequest(new HttpMove(), RequestMethod.MOVE);
	}

	@Override
	public ApacheRequestBuilder head(){
		return setRequest(new HttpHead(), RequestMethod.HEAD);
	}

	@Override
	public ApacheRequestBuilder options(){
		return setRequest(new HttpOptions(), RequestMethod.OPTIONS);
	}

	@Override
	public ApacheRequestBuilder link(){
		return setRequest(new HttpLink(), RequestMethod.LINK);
	}

	@Override
	public ApacheRequestBuilder unlink(){
		return setRequest(new HttpUnlink(), RequestMethod.UNLINK);
	}

	@Override
	public ApacheRequestBuilder purge(){
		return setRequest(new HttpPurge(), RequestMethod.PURGE);
	}

	@Override
	public ApacheRequestBuilder lock(){
		return setRequest(new HttpLock(), RequestMethod.LOCK);
	}

	@Override
	public ApacheRequestBuilder unlock(){
		return setRequest(new HttpUnlock(), RequestMethod.UNLOCK);
	}

	@Override
	public ApacheRequestBuilder propfind(){
		return setRequest(new HttpPropfind(), RequestMethod.PROPFIND);
	}

	@Override
	public ApacheRequestBuilder proppatch(RequestBody<?> body){
		return setRequest(new HttpPropPatch(), RequestMethod.PROPPATCH, body);
	}

	@Override
	public ApacheRequestBuilder report(RequestBody<?> body){
		return setRequest(new HttpReport(), RequestMethod.REPORT, body);
	}

	@Override
	public ApacheRequestBuilder view(){
		return setRequest(new HttpView(), RequestMethod.VIEW);
	}

	@Override
	public ApacheRequestBuilder wrapped(){
		return setRequest(new HttpWrapped(), RequestMethod.WRAPPED);
	}

	@Override
	public HttpComponentsRequest build(){
		HttpComponentsRequest request = super.build();

		if(request.getHeaders() != null){
			for(Header header : request.getHeaders()){
				request.getHttpRequest().setHeader(header.getName(), header.getValue());
			}
		}

		request.getHttpRequest().setURI(request.getUri());

		return request;
	}

	protected ApacheRequestBuilder setRequest(final HttpRequestBase httpRequest, final RequestMethod method){
		request.setHttpRequest(httpRequest);
		request.setMethod(method);
		return this;
	}

	protected ApacheRequestBuilder setRequest(final HttpEntityEnclosingRequestBase httpRequest,
											  final RequestMethod method, final RequestBody<?> body){
		setRequest(httpRequest, method);

		if(body != null){
			final RequestBodyConverter<RequestBody<?>, HttpEntity> converter = findBodyConverter(REQUEST_BODY_CONVERTS,
					body);
			httpRequest.setEntity(converter == null ? DEFAULT_HTTP_ENTITY : converter.convert(body));
		}

		return this;
	}

	public final static class HttpComponentsRequest extends Request {

		private HttpRequestBase httpRequest;

		public HttpRequestBase getHttpRequest(){
			return httpRequest;
		}

		public void setHttpRequest(HttpRequestBase httpRequest){
			this.httpRequest = httpRequest;
		}

	}

}
