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

import com.buession.httpclient.core.AbstractResponseBuilder;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.StatusLine;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * OkHttp Http Client 响应构建器
 *
 * @author Yong.Teng
 */
public class OkHttpResponseBuilder extends AbstractResponseBuilder<okhttp3.Response> {

	private final static Logger logger = LoggerFactory.getLogger(OkHttpResponseBuilder.class);

	@Override
	public Response build(okhttp3.Response httpResponse){
		final Response response = new Response();
		final ProtocolConverter protocolConverter = new ProtocolConverter();
		final OkHttpResponseHeaderParse okHttpResponseHeaderParse = new OkHttpResponseHeaderParse();

		response.setProtocolVersion(protocolConverter.convert(httpResponse.protocol()));

		response.setStatusCode(httpResponse.code());
		response.setStatusText(httpResponse.message());
		response.setStatusLine(new StatusLine(response.getStatusCode(), response.getStatusText()));

		response.setHeaders(okHttpResponseHeaderParse.parse(httpResponse.headers()));

		final ResponseBody responseBody = httpResponse.body();

		if(responseBody != null){
			response.setContentLength(responseBody.contentLength());

			try{
				BufferedSource source = responseBody.source();

				source.request(Long.MAX_VALUE);

				okio.Buffer sourceBuffer = source.getBuffer();
				okio.Buffer targetBuffer = new okio.Buffer();

				sourceBuffer.copyTo(targetBuffer, 0, sourceBuffer.size());

				response.setBody(responseBody.string());
				response.setInputStream(targetBuffer.inputStream());

				sourceBuffer.close();
			}catch(IOException e){
				logger.error("Response entity to body error.", e);
			}

			responseBody.close();
		}

		httpResponse.close();

		return response;
	}

}
