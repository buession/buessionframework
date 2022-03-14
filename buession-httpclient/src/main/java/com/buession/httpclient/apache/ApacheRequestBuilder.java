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
package com.buession.httpclient.apache.request;

import com.buession.core.utils.EnumUtils;
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
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.TextRawRequestBody;
import com.buession.httpclient.core.XmlRawRequestBody;
import com.buession.httpclient.request.AbstractRequestBuilder;
import com.buession.httpclient.apache.convert.EncodedFormRequestBodyConverter;
import com.buession.httpclient.apache.convert.HtmlRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.ApacheRequestBodyConverter;
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

	private final static HttpEntity DEFAULT_HTTP_ENTITY = new UrlEncodedFormEntity(new ArrayList<>(), StandardCharsets.ISO_8859_1);

	private final static Map<Class<? extends RequestBody>, ApacheRequestBodyConverter> REQUEST_BODY_CONVERTS = new HashMap<>(16, 0.8F);

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

	private ApacheRequestBuilder(){
	}

	public static ApacheRequestBuilder create(){
		final ApacheRequestBuilder builder = new ApacheRequestBuilder();
		builder.request = new HttpComponentsRequest();
		return builder;
	}

	public static ApacheRequestBuilder create(String url){
		final ApacheRequestBuilder builder = create();
		builder.setUrl(url);
		return builder;
	}

	public static ApacheRequestBuilder create(String url, Map<String, Object> parameters){
		return create(url).setParameters(parameters);
	}

	public static ApacheRequestBuilder create(String url, List<Header> headers){
		return create(url).setHeaders(headers);
	}

	public static ApacheRequestBuilder create(String url, Map<String, Object> parameters, List<Header> headers){
		return create(url, parameters).setHeaders(headers);
	}

	@Override
	public ApacheRequestBuilder setProtocolVersion(ProtocolVersion protocolVersion){
		this.protocolVersion = protocolVersion;
		return this;
	}

	@Override
	public ApacheRequestBuilder setUrl(String url){
		this.url = url;
		return this;
	}

	@Override
	public ApacheRequestBuilder setHeaders(List<Header> headers){
		this.headers = headers;
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
		request.setHttpRequest(url == null ? new HttpGet() : new HttpGet(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder post(RequestBody body){
		HttpPost httpPost = url == null ? new HttpPost() : new HttpPost(url);

		if(body != null){
			httpPost.setEntity(parseEntity(body));
		}

		request.setHttpRequest(httpPost);

		return this;
	}

	@Override
	public ApacheRequestBuilder patch(RequestBody body){
		HttpPatch httpPatch = url == null ? new HttpPatch() : new HttpPatch(url);

		if(body != null){
			httpPatch.setEntity(parseEntity(body));
		}

		request.setHttpRequest(httpPatch);

		return this;
	}

	@Override
	public ApacheRequestBuilder put(RequestBody body){
		HttpPut httpPut = url == null ? new HttpPut() : new HttpPut(url);

		if(body != null){
			httpPut.setEntity(parseEntity(body));
		}

		request.setHttpRequest(httpPut);

		return this;
	}

	@Override
	public ApacheRequestBuilder delete(){
		request.setHttpRequest(url == null ? new HttpDelete() : new HttpDelete(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder connect(){
		request.setHttpRequest(url == null ? new HttpConnect() : new HttpConnect(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder trace(){
		request.setHttpRequest(url == null ? new HttpTrace() : new HttpTrace(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder copy(){
		request.setHttpRequest(url == null ? new HttpCopy() : new HttpCopy(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder move(){
		request.setHttpRequest(url == null ? new HttpMove() : new HttpMove(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder head(){
		request.setHttpRequest(url == null ? new HttpHead() : new HttpHead(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder options(){
		request.setHttpRequest(url == null ? new HttpOptions() : new HttpOptions(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder link(){
		request.setHttpRequest(url == null ? new HttpLink() : new HttpLink(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder unlink(){
		request.setHttpRequest(url == null ? new HttpUnlink() : new HttpUnlink(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder purge(){
		request.setHttpRequest(url == null ? new HttpPurge() : new HttpPurge(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder lock(){
		request.setHttpRequest(url == null ? new HttpLock() : new HttpLock(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder unlock(){
		request.setHttpRequest(url == null ? new HttpUnlock() : new HttpUnlock(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder propfind(){
		request.setHttpRequest(url == null ? new HttpPropfind() : new HttpPropfind(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder proppatch(RequestBody<?> body){
		HttpPropPatch httpPropPatch = url == null ? new HttpPropPatch() : new HttpPropPatch(url);

		if(body != null){
			httpPropPatch.setEntity(parseEntity(body));
		}

		request.setHttpRequest(httpPropPatch);

		return this;
	}

	@Override
	public ApacheRequestBuilder report(RequestBody<?> body){
		HttpReport httpReport = url == null ? new HttpReport() : new HttpReport(url);

		if(body != null){
			httpReport.setEntity(parseEntity(body));
		}

		request.setHttpRequest(httpReport);

		return this;
	}

	@Override
	public ApacheRequestBuilder view(){
		request.setHttpRequest(url == null ? new HttpView() : new HttpView(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder wrapped(){
		request.setHttpRequest(url == null ? new HttpWrapped() : new HttpWrapped(url));
		return this;
	}

	@Override
	public HttpComponentsRequest build(){
		HttpComponentsRequest request = super.build();

		if(protocolVersion != null){
			request.getHttpRequest().setProtocolVersion(new org.apache.http.ProtocolVersion(protocolVersion.getProtocol(), protocolVersion.getMajor(), protocolVersion.getMinor()));
		}

		if(request.getHeaders() != null){
			for(Header header : request.getHeaders()){
				request.getHttpRequest().setHeader(header.getName(), header.getValue());
			}
		}

		request.setMethod(EnumUtils.valueOf(RequestMethod.class, request.getHttpRequest().getMethod()));
		request.getHttpRequest().setURI(URI.create(request.getUrl()));

		return request;
	}

	private HttpEntity parseEntity(RequestBody<?> data){
		if(data == null){
			return null;
		}

		ApacheRequestBodyConverter<RequestBody<?>> convert = REQUEST_BODY_CONVERTS.get(data.getClass());
		return convert == null ? DEFAULT_HTTP_ENTITY : convert.convert(data);
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
