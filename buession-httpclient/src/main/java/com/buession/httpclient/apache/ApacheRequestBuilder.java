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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.httpcomponents;

import com.buession.core.utils.EnumUtils;
import com.buession.httpclient.core.ChunkedInputStreamRequestBody;
import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.HtmlRawRequestBody;
import com.buession.httpclient.core.JavaScriptRawRequestBody;
import com.buession.httpclient.core.JsonRawRequestBody;
import com.buession.httpclient.core.ObjectFormRequestBody;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.RepeatableInputStreamRequestBody;
import com.buession.httpclient.core.Request;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.TextRawRequestBody;
import com.buession.httpclient.core.XmlRawRequestBody;
import com.buession.httpclient.helper.AbstractRequestBuilder;
import com.buession.httpclient.httpcomponents.convert.ChunkedInputStreamRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.EncodedFormRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.HtmlRawRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.HttpComponentsRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.JavaScriptRawRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.JsonRawRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.ObjectRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.RepeatableInputStreamRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.TextRawRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.XmlRawRequestBodyConvert;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class HttpComponentsRequestBuilder extends AbstractRequestBuilder<HttpComponentsRequestBuilder,
		HttpComponentsRequestBuilder.HttpComponentsRequest> {

	private final static HttpEntity DEFAULT_HTTP_ENTITY = new UrlEncodedFormEntity(new ArrayList<>(),
			EncodedFormRequestBody.CONTENT_TYPE.getCharset());

	private final static Map<Class<? extends RequestBody>, HttpComponentsRequestBodyConvert> REQUEST_BODY_CONVERTS =
			new HashMap<>(16, 0.8F);

	static{
		REQUEST_BODY_CONVERTS.put(EncodedFormRequestBody.class, new EncodedFormRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(ChunkedInputStreamRequestBody.class, new ChunkedInputStreamRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(RepeatableInputStreamRequestBody.class,
				new RepeatableInputStreamRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(TextRawRequestBody.class, new TextRawRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(HtmlRawRequestBody.class, new HtmlRawRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(JavaScriptRawRequestBody.class, new JavaScriptRawRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(JsonRawRequestBody.class, new JsonRawRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(XmlRawRequestBody.class, new XmlRawRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(ObjectFormRequestBody.class, new ObjectRequestBodyConvert());
	}

	private HttpComponentsRequestBuilder(){

	}

	public final static HttpComponentsRequestBuilder create(){
		final HttpComponentsRequestBuilder builder = new HttpComponentsRequestBuilder();

		builder.request = new HttpComponentsRequest();

		return builder;
	}

	public final static HttpComponentsRequestBuilder create(String url){
		final HttpComponentsRequestBuilder builder = create();

		builder.setUrl(url);

		return builder;
	}

	public final static HttpComponentsRequestBuilder create(String url, Map<String, Object> parameters){
		final HttpComponentsRequestBuilder builder = create(url);

		builder.setParameters(parameters);

		return builder;
	}

	public final static HttpComponentsRequestBuilder create(String url, List<Header> headers){
		final HttpComponentsRequestBuilder builder = create(url);

		builder.setHeaders(headers);

		return builder;
	}

	public final static HttpComponentsRequestBuilder create(String url, Map<String, Object> parameters,
															List<Header> headers){
		final HttpComponentsRequestBuilder builder = create(url, parameters);

		builder.setHeaders(headers);

		return builder;
	}

	@Override
	public HttpComponentsRequestBuilder setProtocolVersion(ProtocolVersion protocolVersion){
		this.protocolVersion = protocolVersion;
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder setUrl(String url){
		this.url = url;
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder setHeaders(List<Header> headers){
		this.headers = headers;
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder setParameters(Map<String, Object> parameters){
		this.parameters = parameters;
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder setBody(RequestBody body){
		this.body = body;
		return this;
	}

	public HttpComponentsRequestBuilder setRequestConfig(RequestConfig config){
		this.request.getHttpRequest().setConfig(config);
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder get(){
		request.setHttpRequest(url == null ? new HttpGet() : new HttpGet(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder post(){
		request.setHttpRequest(url == null ? new HttpPost() : new HttpPost(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder post(RequestBody body){
		HttpPost httpPost = url == null ? new HttpPost() : new HttpPost(url);

		httpPost.setEntity(parseEntity(body));
		request.setHttpRequest(httpPost);

		return this;
	}

	@Override
	public HttpComponentsRequestBuilder patch(){
		request.setHttpRequest(url == null ? new HttpPatch() : new HttpPatch(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder patch(RequestBody body){
		HttpPatch httpPatch = url == null ? new HttpPatch() : new HttpPatch(url);

		httpPatch.setEntity(parseEntity(body));
		request.setHttpRequest(httpPatch);

		return this;
	}

	@Override
	public HttpComponentsRequestBuilder put(){
		request.setHttpRequest(url == null ? new HttpPut() : new HttpPut(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder put(RequestBody body){
		HttpPut httpPut = url == null ? new HttpPut() : new HttpPut(url);

		httpPut.setEntity(parseEntity(body));
		request.setHttpRequest(httpPut);

		return this;
	}

	@Override
	public HttpComponentsRequestBuilder delete(){
		request.setHttpRequest(url == null ? new HttpDelete() : new HttpDelete(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder connect(){
		request.setHttpRequest(url == null ? new HttpConnect() : new HttpConnect(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder trace(){
		request.setHttpRequest(url == null ? new HttpTrace() : new HttpTrace(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder copy(){
		request.setHttpRequest(url == null ? new HttpCopy() : new HttpCopy(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder move(){
		request.setHttpRequest(url == null ? new HttpMove() : new HttpMove(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder head(){
		request.setHttpRequest(url == null ? new HttpHead() : new HttpHead(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder options(){
		request.setHttpRequest(url == null ? new HttpOptions() : new HttpOptions(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder link(){
		request.setHttpRequest(url == null ? new HttpLink() : new HttpLink(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder unlink(){
		request.setHttpRequest(url == null ? new HttpUnlink() : new HttpUnlink(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder purge(){
		request.setHttpRequest(url == null ? new HttpPurge() : new HttpPurge(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder lock(){
		request.setHttpRequest(url == null ? new HttpLock() : new HttpLock(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder unlock(){
		request.setHttpRequest(url == null ? new HttpUnlock() : new HttpUnlock(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder propfind(){
		request.setHttpRequest(url == null ? new HttpPropfind() : new HttpPropfind(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder proppatch(){
		request.setHttpRequest(url == null ? new HttpPropPatch() : new HttpPropPatch(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder proppatch(RequestBody body){
		HttpPropPatch httpPropPatch = url == null ? new HttpPropPatch() : new HttpPropPatch(url);

		httpPropPatch.setEntity(parseEntity(body));
		request.setHttpRequest(httpPropPatch);

		return this;
	}

	@Override
	public HttpComponentsRequestBuilder report(){
		request.setHttpRequest(url == null ? new HttpReport() : new HttpReport(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder report(RequestBody body){
		HttpReport httpReport = url == null ? new HttpReport() : new HttpReport(url);

		httpReport.setEntity(parseEntity(body));
		request.setHttpRequest(httpReport);

		return this;
	}

	@Override
	public HttpComponentsRequestBuilder view(){
		request.setHttpRequest(url == null ? new HttpView() : new HttpView(url));
		return this;
	}

	@Override
	public HttpComponentsRequestBuilder wrapped(){
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

	private HttpEntity parseEntity(RequestBody data){
		if(data == null){
			return null;
		}

		HttpComponentsRequestBodyConvert convert = REQUEST_BODY_CONVERTS.get(data.getClass());
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
