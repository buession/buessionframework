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

import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.concurrent.Callback;
import com.buession.httpclient.exception.RequestException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class OkHttpHttpAsyncClientTest {

	private static OkHttpHttpAsyncClient httpClient = new OkHttpHttpAsyncClient();

	@Test
	public void responseHeaders() throws IOException, RequestException {
		httpClient.get("https://www.baidu.com",
				new Callback() {

					@Override
					public void completed(Response response) {
						for(Header header : response.getHeaders()){
							System.out.println(header.toString());
						}
					}

					@Override
					public void failed(Exception ex) {
						System.out.println("failed");
					}

					@Override
					public void cancelled() {
						System.out.println("cancelled");
					}
				});
		//response.get();
	}

	@Test
	public void okhttp3Native() {
		//  构建okHttpClient，相当于请求的客户端，Builder设计模式
		okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
		// 构建一个请求体，同样也是Builder设计模式
		okhttp3.Request request =
				new okhttp3.Request.Builder().url("https://shirojs.buession.com/support.html").get().build();
		//  生成一个Call对象，该对象是接口类型，后面会说
		okhttp3.Call call = okHttpClient.newCall(request);
		call.enqueue(new okhttp3.Callback() {

			@Override
			public void onFailure(okhttp3.Call call, IOException e) {
				System.out.println(e);
			}

			@Override
			public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
				System.out.println(response.body());
			}
		});
	}

}
