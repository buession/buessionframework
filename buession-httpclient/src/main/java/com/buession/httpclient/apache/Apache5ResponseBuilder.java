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

import com.buession.httpclient.core.AbstractResponseBuilder;
import com.buession.httpclient.core.AbstractResponseHeaderParse;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.StatusLine;
import com.google.common.collect.Multimap;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.BufferedHttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Apache Http Client 5 响应构建器
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public class Apache5ResponseBuilder extends AbstractResponseBuilder<org.apache.hc.core5.http.HttpResponse> {

	private final static Logger logger = LoggerFactory.getLogger(Apache5ResponseBuilder.class);

	@Override
	public Response build(org.apache.hc.core5.http.HttpResponse httpResponse) {
		final Response response = new Response();
		final org.apache.hc.core5.http.ProtocolVersion responseProtocolVersion = httpResponse.getVersion();
		final ApacheResponseHeaderParse responseHeaderParse = new ApacheResponseHeaderParse();

		response.setProtocolVersion(ProtocolVersion.createInstance(responseProtocolVersion.getProtocol(),
				responseProtocolVersion.getMajor(), responseProtocolVersion.getMinor()));
		response.setStatusLine(
				new StatusLine(httpResponse.getCode(), httpResponse.getReasonPhrase()));
		response.setHeaders(responseHeaderParse.parse(httpResponse.getHeaders()));

		if(httpResponse instanceof SimpleHttpResponse){
			SimpleHttpResponse simpleHttpResponse = (SimpleHttpResponse) httpResponse;

			response.setBody(simpleHttpResponse.getBodyText());
			response.setInputStream(new ByteArrayInputStream(simpleHttpResponse.getBodyBytes()));

			try{
				response.setContentLength(
						Long.parseLong(simpleHttpResponse.getFirstHeader("Content-Length").getValue()));
			}catch(NumberFormatException e){
				response.setContentLength(response.getBody().length());
			}
		}else if(httpResponse instanceof ClassicHttpResponse){
			ClassicHttpResponse classicHttpResponse = (ClassicHttpResponse) httpResponse;
			org.apache.hc.core5.http.HttpEntity entity = classicHttpResponse.getEntity();

			if(entity != null){
				response.setContentLength(entity.getContentLength());

				try{
					HttpEntity httpEntity = new BufferedHttpEntity(entity);

					response.setInputStream(httpEntity.getContent());
					response.setBody(EntityUtils.toString(httpEntity));

					if(entity.getContent() != null){
						entity.getContent().close();
					}
				}catch(ParseException e){
					logger.error("Response entity to body error.", e);
				}catch(IOException e){
					logger.error("Response entity to body error.", e);
				}
			}
		}

		if(httpResponse instanceof CloseableHttpResponse){
			try{
				((CloseableHttpResponse) httpResponse).close();
			}catch(IOException e){
				logger.error("Close HTTP Response error.", e);
			}
		}

		return response;
	}

	/**
	 * Apache HttpClient 5 响应头解析器
	 *
	 * @author Yong.Teng
	 * @since 2.4.0
	 */
	private static class ApacheResponseHeaderParse extends AbstractResponseHeaderParse<Header[]> {

		@Override
		protected void doParse(final Header[] headers, final Multimap<String, String> headersMap) {
			for(Header header : headers){
				headersMap.put(header.getName(), header.getValue());
			}
		}

	}

}
