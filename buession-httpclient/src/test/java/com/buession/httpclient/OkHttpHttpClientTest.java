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
package com.buession.httpclient;

import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.RequestException;
import com.buession.lang.Gender;
import com.google.common.io.CharStreams;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class OkHttpHttpClientTest {

	private static OkHttpHttpClient httpClient = new OkHttpHttpClient();

	@Test
	public void responseHeaders() throws IOException, RequestException {
		Response response = httpClient.get("https://shirojs.buession.com/manual/1.1/index.html");
		for(Header header : response.getHeaders()){
			System.out.println(header.toString());
		}
	}

	@Test
	public void responseBody() throws IOException, RequestException {
		Response response = httpClient.get("https://shirojs.buession.com/support.html");
		System.out.println(response.getBody());

		String result = CharStreams.toString(new InputStreamReader(response.getInputStream(), StandardCharsets.UTF_8));
		System.out.println(result);
	}

	@Test
	public void encodedFormRequestBody() throws IOException, RequestException {
		EncodedFormRequestBody encodedFormRequestBody = new EncodedFormRequestBody();

		encodedFormRequestBody.addRequestBodyElement("username", "username");
		encodedFormRequestBody.addRequestBodyElement("gender", Gender.FEMALE.name());
		encodedFormRequestBody.addRequestBodyElement("age", "11");

		Response response = httpClient.post("https://www.buession.com/upload/test/encodedFormRequest",
				encodedFormRequestBody);
		System.out.println(response.getBody());
	}

}
