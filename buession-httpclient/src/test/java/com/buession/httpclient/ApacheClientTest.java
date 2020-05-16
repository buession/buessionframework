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
 * | License: http://buession.buession.com.cn/LICENSE 												       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient;

import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.JsonRawRequestBody;
import com.buession.httpclient.core.MultipartFormRequestBody;
import com.buession.httpclient.core.MultipartRequestBodyElement;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.ConnectTimeoutException;
import com.buession.httpclient.exception.ConnectionPoolTimeoutException;
import com.buession.httpclient.exception.ReadTimeoutException;
import com.buession.httpclient.exception.RequestAbortedException;
import com.buession.httpclient.exception.RequestException;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class ApacheClientTest {

	@Test
	public void get() throws RequestAbortedException, ReadTimeoutException, ConnectionPoolTimeoutException,
			ConnectTimeoutException, RequestException{
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("url", "https%3A%2F%2Fwww.aliyun.com%2F&list=pc-topbar-list");

		HttpClient httpClient = new ApacheHttpClient();
		Response response = httpClient.get("https://query.aliyun.com/rest/aliyun-config.services.black_white",
				parameters);
		System.out.print(response.getBody());
	}

	@Test
	public void requestHeaders() throws RequestAbortedException, ReadTimeoutException, ConnectionPoolTimeoutException,
			ConnectTimeoutException, RequestException{
		List<Header> headers = new ArrayList<>();

		headers.add(new Header("X-Version", "1.0"));

		HttpClient httpClient = new ApacheHttpClient();
		Response response = httpClient.post("http://www.sinokai.com/httpclient.php?action=headers", headers);
		System.out.print(response.getBody());
	}

	@Test
	public void post() throws RequestAbortedException, ReadTimeoutException, ConnectionPoolTimeoutException,
			ConnectTimeoutException, RequestException{
		EncodedFormRequestBody requestBody = new EncodedFormRequestBody();

		requestBody.addRequestBodyElement("a", "A");
		requestBody.addRequestBodyElement("b", "B");

		HttpClient httpClient = new ApacheHttpClient();
		Response response = httpClient.post("http://www.sinokai.com/httpclient.php?action=post", requestBody);
		System.out.print(response.getBody());
	}

	@Test
	public void postJson() throws RequestAbortedException, ReadTimeoutException, ConnectionPoolTimeoutException,
			ConnectTimeoutException, RequestException{
		Map<String, Object> data = new HashMap<>();

		data.put("a", "A");
		data.put("b", "B");

		JsonRawRequestBody requestBody = new JsonRawRequestBody(data);

		HttpClient httpClient = new ApacheHttpClient();
		Response response = httpClient.post("http://www.sinokai.com/httpclient.php?action=postJson", requestBody);
		System.out.print(response.getBody());
	}

	@Test
	public void uploadFile() throws RequestAbortedException, ReadTimeoutException, ConnectionPoolTimeoutException,
			ConnectTimeoutException, RequestException{
		MultipartFormRequestBody requestBody = new MultipartFormRequestBody();

		requestBody.addRequestBodyElement(new MultipartRequestBodyElement("uid", 1));
		requestBody.addRequestBodyElement(new MultipartRequestBodyElement("file", new File("/Volumes/data/htdocs" +
				"/sinokai.com/robots.txt")));

		HttpClient httpClient = new ApacheHttpClient();
		Response response = httpClient.post("http://www.sinokai.com/httpclient.php?action=uploadFile", requestBody);
		System.out.print(response.getBody());
	}

}
