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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.okhttp;

import com.buession.core.utils.FieldUtils;
import com.buession.core.utils.EnumUtils;
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
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.TextRawRequestBody;
import com.buession.httpclient.core.XmlRawRequestBody;
import com.buession.httpclient.helper.AbstractRequestBuilder;
import com.buession.httpclient.okhttp.convert.ChunkedInputStreamRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.EncodedFormRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.HtmlRawRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.InputStreamRequestBodyConvert;
import com.buession.httpclient.okhttp.convert.JavaScriptRawRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.JsonRawRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.MultipartFormRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.OkHttpRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.RepeatableInputStreamRequestBodyConvert;
import com.buession.httpclient.okhttp.convert.TextRawRequestBodyConverter;
import com.buession.httpclient.okhttp.convert.XmlRawRequestBodyConverter;
import okhttp3.FormBody;
import okhttp3.Headers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class OkHttpRequestBuilder extends AbstractRequestBuilder<OkHttpRequestBuilder, OkHttpRequestBuilder.OkHttpRequest> {

	private final static okhttp3.RequestBody DEFAULT_REQUEST_BODY = new FormBody.Builder().build();

	private final static Map<Class<? extends RequestBody>, OkHttpRequestBodyConverter> REQUEST_BODY_CONVERTS = new HashMap<>(16, 0.8F);

	static{
		REQUEST_BODY_CONVERTS.put(ChunkedInputStreamRequestBody.class, new ChunkedInputStreamRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(EncodedFormRequestBody.class, new EncodedFormRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(HtmlRawRequestBody.class, new HtmlRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(InputStreamRequestBody.class, new InputStreamRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(JavaScriptRawRequestBody.class, new JavaScriptRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(JsonRawRequestBody.class, new JsonRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(MultipartFormRequestBody.class, new MultipartFormRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(RepeatableInputStreamRequestBody.class, new RepeatableInputStreamRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(TextRawRequestBody.class, new TextRawRequestBodyConverter());
		REQUEST_BODY_CONVERTS.put(XmlRawRequestBody.class, new XmlRawRequestBodyConverter());
	}

	private OkHttpRequestBuilder(){
	}

	public static OkHttpRequestBuilder create(){
		final OkHttpRequestBuilder builder = new OkHttpRequestBuilder();
		builder.request = new OkHttpRequest();
		return builder;
	}

	public static OkHttpRequestBuilder create(String url){
		return create().setUrl(url);
	}

	public static OkHttpRequestBuilder create(String url, Map<String, Object> parameters){
		return create(url).setParameters(parameters);
	}

	public static OkHttpRequestBuilder create(String url, List<Header> headers){
		return create(url).setHeaders(headers);
	}

	public static OkHttpRequestBuilder create(String url, Map<String, Object> parameters, List<Header> headers){
		return create(url, parameters).setHeaders(headers);
	}

	@Override
	public OkHttpRequestBuilder setProtocolVersion(ProtocolVersion protocolVersion){
		this.protocolVersion = protocolVersion;
		return this;
	}

	@Override
	public OkHttpRequestBuilder setUrl(String url){
		this.url = url;
		return this;
	}

	@Override
	public OkHttpRequestBuilder setHeaders(List<Header> headers){
		this.headers = headers;
		return this;
	}

	@Override
	public OkHttpRequestBuilder setParameters(Map<String, Object> parameters){
		this.parameters = parameters;
		return this;
	}

	@Override
	public OkHttpRequestBuilder get(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().get());
		return this;
	}

	@Override
	public OkHttpRequestBuilder post(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().post());
		return this;
	}

	@Override
	public OkHttpRequestBuilder post(RequestBody body){
		request.setRequestBuilder(new okhttp3.RequestBuilder().post(buildRequestBody(body)));
		return this;
	}

	@Override
	public OkHttpRequestBuilder patch(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().patch());
		return this;
	}

	@Override
	public OkHttpRequestBuilder patch(RequestBody body){
		request.setRequestBuilder(new okhttp3.RequestBuilder().patch(buildRequestBody(body)));
		return this;
	}

	@Override
	public OkHttpRequestBuilder put(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().put());
		return this;
	}

	@Override
	public OkHttpRequestBuilder put(RequestBody body){
		request.setRequestBuilder(new okhttp3.RequestBuilder().put(buildRequestBody(body)));
		return this;
	}

	@Override
	public OkHttpRequestBuilder delete(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().delete());
		return this;
	}

	@Override
	public OkHttpRequestBuilder connect(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().connect());
		return this;
	}

	@Override
	public OkHttpRequestBuilder trace(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().trace());
		return this;
	}

	@Override
	public OkHttpRequestBuilder copy(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().copy());
		return this;
	}

	@Override
	public OkHttpRequestBuilder move(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().move());
		return this;
	}

	@Override
	public OkHttpRequestBuilder head(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().head());
		return this;
	}

	@Override
	public OkHttpRequestBuilder options(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().options());
		return this;
	}

	@Override
	public OkHttpRequestBuilder link(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().link());
		return this;
	}

	@Override
	public OkHttpRequestBuilder unlink(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().unlink());
		return this;
	}

	@Override
	public OkHttpRequestBuilder purge(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().purge());
		return this;
	}

	@Override
	public OkHttpRequestBuilder lock(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().lock());
		return this;
	}

	@Override
	public OkHttpRequestBuilder unlock(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().unlock());
		return this;
	}

	@Override
	public OkHttpRequestBuilder propfind(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().propfind());
		return this;
	}

	@Override
	public OkHttpRequestBuilder proppatch(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().proppatch());
		return this;
	}

	@Override
	public OkHttpRequestBuilder proppatch(RequestBody body){
		request.setRequestBuilder(new okhttp3.RequestBuilder().proppatch(buildRequestBody(body)));
		return this;
	}

	@Override
	public OkHttpRequestBuilder report(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().report());
		return this;
	}

	@Override
	public OkHttpRequestBuilder report(RequestBody body){
		request.setRequestBuilder(new okhttp3.RequestBuilder().report(buildRequestBody(body)));
		return this;
	}

	@Override
	public OkHttpRequestBuilder view(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().view());
		return this;
	}

	@Override
	public OkHttpRequestBuilder wrapped(){
		request.setRequestBuilder(new okhttp3.RequestBuilder().wrapped());
		return this;
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

		try{
			String methodName = String.valueOf(FieldUtils.readDeclaredField(request.getRequestBuilder(), "method", true));
			request.setMethod(EnumUtils.valueOf(RequestMethod.class, methodName.toUpperCase()));
		}catch(Exception e){
		}

		request.getRequestBuilder().url(request.getUrl());
		request.getRequestBuilder().headers(headersBuilder.build());

		return request;
	}

	@SuppressWarnings({"unchecked"})
	private okhttp3.RequestBody buildRequestBody(RequestBody data){
		if(data == null){
			return DEFAULT_REQUEST_BODY;
		}

		OkHttpRequestBodyConverter convert = REQUEST_BODY_CONVERTS.get(data.getClass());
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