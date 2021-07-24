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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.apache;

import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.helper.AbstractResponseBuilder;
import com.buession.httpclient.helper.ResponseBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class ApacheResponseBuilder extends AbstractResponseBuilder {

	private final static Logger logger = LoggerFactory.getLogger(ApacheResponseBuilder.class);

	public static ResponseBuilder create(){
		return new ApacheResponseBuilder();
	}

	public static ResponseBuilder create(org.apache.http.HttpResponse httpResponse){
		final ApacheResponseBuilder responseBuilder = new ApacheResponseBuilder();
		final org.apache.http.StatusLine responseStatusLine = httpResponse.getStatusLine();
		final org.apache.http.ProtocolVersion responseProtocolVersion = responseStatusLine.getProtocolVersion();

		responseBuilder.setProtocolVersion(ProtocolVersion.createInstance(responseProtocolVersion.getProtocol(),
				responseProtocolVersion.getMajor(), responseProtocolVersion.getMinor()));
		responseBuilder.setStatusCode(responseStatusLine.getStatusCode());
		responseBuilder.setStatusText(responseStatusLine.getReasonPhrase());

		ApacheResponseHeaderParse responseHeaderParse = new ApacheResponseHeaderParse(httpResponse.getAllHeaders());
		responseBuilder.setHeaders(responseHeaderParse.parse());

		if(httpResponse.getEntity() != null){
			responseBuilder.setContentLength(httpResponse.getEntity().getContentLength());

			try{
				HttpEntity httpEntity = new BufferedHttpEntity(httpResponse.getEntity());

				responseBuilder.setInputStream(httpEntity.getContent());
				responseBuilder.setBody(EntityUtils.toString(httpEntity));

				if(httpResponse.getEntity().getContent() != null){
					httpResponse.getEntity().getContent().close();
				}
			}catch(IOException e){
				logger.error("Response entity to body error.", e);
			}
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
