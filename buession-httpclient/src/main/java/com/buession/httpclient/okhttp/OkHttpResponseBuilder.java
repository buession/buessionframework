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
package com.buession.httpclient.okhttp;

import com.buession.core.utils.KeyValueParser;
import com.buession.core.utils.StringUtils;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.helper.AbstractResponseBuilder;
import com.buession.httpclient.helper.ResponseBuilder;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class OkHttpResponseBuilder extends AbstractResponseBuilder {

	private final static Logger logger = LoggerFactory.getLogger(OkHttpResponseBuilder.class);

	public final static ResponseBuilder create(){
		return new OkHttpResponseBuilder();
	}

	public final static ResponseBuilder create(okhttp3.Response httpResponse){
		final ResponseBuilder responseBuilder =
				new OkHttpResponseBuilder().setStatusCode(httpResponse.code()).setStatusText(httpResponse.message());

		KeyValueParser keyValueParser = new KeyValueParser(httpResponse.protocol().toString(), '/');
		String protocolName = keyValueParser.getKey();
		String[] versionTemp = StringUtils.splitByWholeSeparatorPreserveAllTokens(keyValueParser.getValue(), ".");
		int majorVersion = Integer.parseInt(versionTemp[0]);
		int minorVersion = versionTemp.length > 1 ? Integer.parseInt(versionTemp[1]) : 0;

		responseBuilder.setProtocolVersion(ProtocolVersion.createInstance(protocolName, majorVersion, minorVersion));

		OkHttpResponseHeaderParse okHttpResponseHeaderParse = new OkHttpResponseHeaderParse(httpResponse.headers());
		responseBuilder.setHeaders(okHttpResponseHeaderParse.parse());

		final ResponseBody responseBody = httpResponse.body();

		if(responseBody != null){
			responseBuilder.setContentLength(responseBody.contentLength());

			try{
				BufferedSource source = responseBody.source();

				source.request(Long.MAX_VALUE);

				okio.Buffer sourceBuffer = source.getBuffer();
				okio.Buffer targetBuffer = new okio.Buffer();

				sourceBuffer.copyTo(targetBuffer, 0, sourceBuffer.size());

				responseBuilder.setBody(responseBody.string());
				responseBuilder.setInputStream(targetBuffer.inputStream());

				sourceBuffer.close();
			}catch(IOException e){
				logger.error("Response entity to body error.", e);
			}

			responseBody.close();
		}

		httpResponse.close();

		return responseBuilder;
	}

}
