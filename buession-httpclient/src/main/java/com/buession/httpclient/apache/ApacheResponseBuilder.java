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

import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.helper.AbstractResponseBuilder;
import com.buession.httpclient.helper.ResponseBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class HttpComponentsResponseBuilder extends AbstractResponseBuilder {

	private final static Logger logger = LoggerFactory.getLogger(HttpComponentsResponseBuilder.class);

	public final static ResponseBuilder create(){
		return new HttpComponentsResponseBuilder();
	}

	public final static ResponseBuilder create(org.apache.http.HttpResponse httpResponse){
		final ResponseBuilder responseBuilder = new HttpComponentsResponseBuilder();
		final org.apache.http.StatusLine responseStatusLine = httpResponse.getStatusLine();
		final org.apache.http.ProtocolVersion responseProtocolVersion = responseStatusLine.getProtocolVersion();

		responseBuilder.setProtocolVersion(ProtocolVersion.createInstance(responseProtocolVersion.getProtocol(),
				responseProtocolVersion.getMajor(), responseProtocolVersion.getMinor()));
		responseBuilder.setStatusCode(responseStatusLine.getStatusCode());
		responseBuilder.setStatusText(responseStatusLine.getReasonPhrase());

		final org.apache.http.Header[] responseHeaders = httpResponse.getAllHeaders();

		if(responseHeaders != null){
			final Map<String, String> headersMap = new LinkedHashMap<>(responseHeaders.length);

			for(org.apache.http.Header header : responseHeaders){
				if(header.getElements() != null){
					String value = headersMap.get(header.getName());

					if(value == null){
						headersMap.put(header.getName(), header.getValue());
					}else{
						headersMap.put(header.getName(), value + ", " + header.getValue());
					}
				}
			}

			responseBuilder.setHeaders(headersMap2List(headersMap));
		}

		responseBuilder.setContentLength(httpResponse.getEntity().getContentLength());

		try{
			responseBuilder.setInputStream(httpResponse.getEntity().getContent());
			responseBuilder.setBody(EntityUtils.toString(httpResponse.getEntity()));
		}catch(IOException e){
			logger.error("Response entity to body error.", e);
		}

		if(httpResponse instanceof CloseableHttpResponse){
			try{
				((CloseableHttpResponse) httpResponse).close();
			}catch(IOException e){
				logger.error("Close HTTP Response error.", e);
			}
		}

		return responseBuilder;
	}

}
