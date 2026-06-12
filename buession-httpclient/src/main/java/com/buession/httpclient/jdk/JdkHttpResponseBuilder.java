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

import com.buession.httpclient.core.AbstractResponseBuilder;
import com.buession.httpclient.core.AbstractResponseHeaderParse;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.StatusLine;
import com.google.common.collect.Multimap;

import java.io.ByteArrayInputStream;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;

/**
 * JDK {@link java.net.http.HttpClient} 响应构建器
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class JdkHttpResponseBuilder extends AbstractResponseBuilder<HttpResponse<byte[]>> {

	@Override
	public Response build(HttpResponse<byte[]> httpResponse) {
		final Response response = new Response();

		if(httpResponse.version() != java.net.http.HttpClient.Version.HTTP_1_1){
			response.setProtocolVersion(ProtocolVersion.HTTP_1_1);
		}else if(httpResponse.version() != java.net.http.HttpClient.Version.HTTP_2){
			response.setProtocolVersion(ProtocolVersion.HTTP_2_0);
		}

		response.setStatusLine(new StatusLine(httpResponse.statusCode(), ""));

		final JdkHttpResponseHeaderParse jdkHttpResponseHeaderParse = new JdkHttpResponseHeaderParse();
		response.setHeaders(jdkHttpResponseHeaderParse.parse(httpResponse.headers()));

		final byte[] responseBody = httpResponse.body();

		if(responseBody != null){
			response.setInputStream(new ByteArrayInputStream(responseBody));
			response.setBody(new String(responseBody));
			response.setContentLength(responseBody.length);
		}

		return response;
	}

	/**
	 * JDK {@link java.net.http.HttpClient} 响应头解析器
	 *
	 * @author Yong.Teng
	 */
	private static class JdkHttpResponseHeaderParse extends AbstractResponseHeaderParse<HttpHeaders> {

		@Override
		protected void doParse(final HttpHeaders headers, final Multimap<String, String> headersMap) {
			headers.map().forEach(headersMap::putAll);
		}

	}

}
