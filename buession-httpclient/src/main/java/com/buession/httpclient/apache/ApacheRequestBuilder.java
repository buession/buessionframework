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
package com.buession.httpclient.apache;

import com.buession.core.utils.EnumUtils;
import com.buession.httpclient.apache.convert.MultipartFormRequestBodyConvert;
import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.HtmlRawRequestBody;
import com.buession.httpclient.core.JavaScriptRawRequestBody;
import com.buession.httpclient.core.JsonRawRequestBody;
import com.buession.httpclient.core.MultipartFormRequestBody;
import com.buession.httpclient.core.ObjectFormRequestBody;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.Request;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.TextRawRequestBody;
import com.buession.httpclient.core.XmlRawRequestBody;
import com.buession.httpclient.helper.AbstractRequestBuilder;
import com.buession.httpclient.apache.convert.EncodedFormRequestBodyConvert;
import com.buession.httpclient.apache.convert.HtmlRawRequestBodyConvert;
import com.buession.httpclient.apache.convert.ApacheRequestBodyConvert;
import com.buession.httpclient.apache.convert.JavaScriptRawRequestBodyConvert;
import com.buession.httpclient.apache.convert.JsonRawRequestBodyConvert;
import com.buession.httpclient.apache.convert.ObjectRequestBodyConvert;
import com.buession.httpclient.apache.convert.TextRawRequestBodyConvert;
import com.buession.httpclient.apache.convert.XmlRawRequestBodyConvert;
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
public class ApacheRequestBuilder extends AbstractRequestBuilder<ApacheRequestBuilder,
		ApacheRequestBuilder.HttpComponentsRequest> {

	private final static HttpEntity DEFAULT_HTTP_ENTITY = new UrlEncodedFormEntity(new ArrayList<>(),
			EncodedFormRequestBody.CONTENT_TYPE.getCharset());

	private final static Map<Class<? extends RequestBody>, ApacheRequestBodyConvert> REQUEST_BODY_CONVERTS =
			new HashMap<>(16, 0.8F);

	static{
		REQUEST_BODY_CONVERTS.put(EncodedFormRequestBody.class, new EncodedFormRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(MultipartFormRequestBody.class, new MultipartFormRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(TextRawRequestBody.class, new TextRawRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(HtmlRawRequestBody.class, new HtmlRawRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(JavaScriptRawRequestBody.class, new JavaScriptRawRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(JsonRawRequestBody.class, new JsonRawRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(XmlRawRequestBody.class, new XmlRawRequestBodyConvert());
		REQUEST_BODY_CONVERTS.put(ObjectFormRequestBody.class, new ObjectRequestBodyConvert());
	}

	private ApacheRequestBuilder(){
	}

	public final static ApacheRequestBuilder create(){
		final ApacheRequestBuilder builder = new ApacheRequestBuilder();

		builder.request = new HttpComponentsRequest();

		return builder;
	}

	public final static ApacheRequestBuilder create(String url){
		final ApacheRequestBuilder builder = create();

		builder.setUrl(url);

		return builder;
	}

	public final static ApacheRequestBuilder create(String url, Map<String, Object> parameters){
		final ApacheRequestBuilder builder = create(url);

		builder.setParameters(parameters);

		return builder;
	}

	public final static ApacheRequestBuilder create(String url, List<Header> headers){
		final ApacheRequestBuilder builder = create(url);

		builder.setHeaders(headers);

		return builder;
	}

	public final static ApacheRequestBuilder create(String url, Map<String, Object> parameters, List<Header> headers){
		final ApacheRequestBuilder builder = create(url, parameters);

		builder.setHeaders(headers);

		return builder;
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
	public ApacheRequestBuilder post(){
		request.setHttpRequest(url == null ? new HttpPost() : new HttpPost(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder post(RequestBody body){
		HttpPost httpPost = url == null ? new HttpPost() : new HttpPost(url);

		httpPost.setEntity(parseEntity(body));
		request.setHttpRequest(httpPost);

		return this;
	}

	@Override
	public ApacheRequestBuilder patch(){
		request.setHttpRequest(url == null ? new HttpPatch() : new HttpPatch(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder patch(RequestBody body){
		HttpPatch httpPatch = url == null ? new HttpPatch() : new HttpPatch(url);

		httpPatch.setEntity(parseEntity(body));
		request.setHttpRequest(httpPatch);

		return this;
	}

	@Override
	public ApacheRequestBuilder put(){
		request.setHttpRequest(url == null ? new HttpPut() : new HttpPut(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder put(RequestBody body){
		HttpPut httpPut = url == null ? new HttpPut() : new HttpPut(url);

		httpPut.setEntity(parseEntity(body));
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
	public ApacheRequestBuilder proppatch(){
		request.setHttpRequest(url == null ? new HttpPropPatch() : new HttpPropPatch(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder proppatch(RequestBody body){
		HttpPropPatch httpPropPatch = url == null ? new HttpPropPatch() : new HttpPropPatch(url);

		httpPropPatch.setEntity(parseEntity(body));
		request.setHttpRequest(httpPropPatch);

		return this;
	}

	@Override
	public ApacheRequestBuilder report(){
		request.setHttpRequest(url == null ? new HttpReport() : new HttpReport(url));
		return this;
	}

	@Override
	public ApacheRequestBuilder report(RequestBody body){
		HttpReport httpReport = url == null ? new HttpReport() : new HttpReport(url);

		httpReport.setEntity(parseEntity(body));
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

	private HttpEntity parseEntity(RequestBody data){
		if(data == null){
			return null;
		}

		ApacheRequestBodyConvert convert = REQUEST_BODY_CONVERTS.get(data.getClass());
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