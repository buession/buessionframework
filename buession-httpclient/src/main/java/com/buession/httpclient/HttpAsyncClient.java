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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient;

import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.concurrent.Callback;
import com.buession.httpclient.exception.RequestException;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Http 异步客户端
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public interface HttpAsyncClient extends IBaseHttpClient {

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void get(String url, Callback callback) throws IOException, RequestException{
		get(URI.create(url), callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void get(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		get(URI.create(url), parameters, callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void get(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		get(URI.create(url), headers, callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void get(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		get(URI.create(url), parameters, headers, callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void get(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		get(URI.create(url), readTimeout, callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void get(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		get(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void get(String url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		get(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void get(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException{
		get(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void get(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
			 Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, Callback callback) throws IOException, RequestException{
		post(URI.create(url), callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		post(URI.create(url), parameters, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		post(URI.create(url), headers, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		post(URI.create(url), parameters, headers, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, RequestBody<?> data, Callback callback) throws IOException, RequestException{
		post(URI.create(url), data, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		post(URI.create(url), data, parameters, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		post(URI.create(url), data, headers, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException{
		post(URI.create(url), data, parameters, headers, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		post(URI.create(url), readTimeout, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		post(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		post(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException{
		post(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException{
		post(URI.create(url), readTimeout, data, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, int readTimeout, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					  Callback callback) throws IOException, RequestException{
		post(URI.create(url), readTimeout, data, parameters, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		post(URI.create(url), readTimeout, data, headers, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void post(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					  List<Header> headers, Callback callback) throws IOException, RequestException{
		post(URI.create(url), readTimeout, data, parameters, headers, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
			  Callback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void post(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
			  Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, Callback callback) throws IOException, RequestException{
		put(URI.create(url), callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		put(URI.create(url), parameters, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		put(URI.create(url), headers, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		put(URI.create(url), parameters, headers, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, RequestBody<?> data, Callback callback) throws IOException, RequestException{
		put(URI.create(url), data, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		put(URI.create(url), data, parameters, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		put(URI.create(url), data, headers, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException{
		put(URI.create(url), data, parameters, headers, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		put(URI.create(url), readTimeout, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		put(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		put(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException{
		put(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException{
		put(URI.create(url), readTimeout, data, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, int readTimeout, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					 Callback callback) throws IOException, RequestException{
		put(URI.create(url), readTimeout, data, parameters, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		put(URI.create(url), readTimeout, data, headers, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void put(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					 List<Header> headers, Callback callback) throws IOException, RequestException{
		put(URI.create(url), readTimeout, data, parameters, headers, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
			 Callback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void put(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
			 Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, Callback callback) throws IOException, RequestException{
		patch(URI.create(url), callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		patch(URI.create(url), parameters, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		patch(URI.create(url), headers, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		patch(URI.create(url), parameters, headers, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, RequestBody<?> data, Callback callback) throws IOException, RequestException{
		patch(URI.create(url), data, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		patch(URI.create(url), data, parameters, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		patch(URI.create(url), data, headers, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException{
		patch(URI.create(url), data, parameters, headers, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
			   Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
			   Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		patch(URI.create(url), readTimeout, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		patch(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		patch(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException{
		patch(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException{
		patch(URI.create(url), readTimeout, data, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, int readTimeout, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					   Callback callback) throws IOException, RequestException{
		patch(URI.create(url), readTimeout, data, parameters, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		patch(URI.create(url), readTimeout, data, headers, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void patch(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					   List<Header> headers, Callback callback) throws IOException, RequestException{
		patch(URI.create(url), readTimeout, data, parameters, headers, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
			   Callback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void patch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
			   Callback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void delete(String url, Callback callback) throws IOException, RequestException{
		delete(URI.create(url), callback);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void delete(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		delete(URI.create(url), parameters, callback);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void delete(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		delete(URI.create(url), headers, callback);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void delete(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		delete(URI.create(url), parameters, headers, callback);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void delete(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		delete(URI.create(url), readTimeout, callback);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void delete(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		delete(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void delete(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		delete(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void delete(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException{
		delete(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void delete(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void connect(String url, Callback callback) throws IOException, RequestException{
		connect(URI.create(url), callback);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void connect(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		connect(URI.create(url), parameters, callback);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void connect(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		connect(URI.create(url), headers, callback);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void connect(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		connect(URI.create(url), parameters, headers, callback);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void connect(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		connect(URI.create(url), readTimeout, callback);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void connect(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		connect(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void connect(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		connect(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void connect(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						 Callback callback) throws IOException, RequestException{
		connect(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void connect(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void trace(String url, Callback callback) throws IOException, RequestException{
		trace(URI.create(url), callback);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void trace(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		trace(URI.create(url), parameters, callback);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void trace(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		trace(URI.create(url), headers, callback);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void trace(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		trace(URI.create(url), parameters, headers, callback);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void trace(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		trace(URI.create(url), readTimeout, callback);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void trace(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		trace(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void trace(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		trace(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void trace(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException{
		trace(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void trace(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void copy(String url, Callback callback) throws IOException, RequestException{
		copy(URI.create(url), callback);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void copy(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		copy(URI.create(url), parameters, callback);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void copy(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		copy(URI.create(url), headers, callback);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void copy(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		copy(URI.create(url), parameters, headers, callback);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void copy(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		copy(URI.create(url), readTimeout, callback);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void copy(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		copy(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void copy(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		copy(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void copy(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException{
		copy(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void copy(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void move(String url, Callback callback) throws IOException, RequestException{
		move(URI.create(url), callback);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void move(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		move(URI.create(url), parameters, callback);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void move(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		move(URI.create(url), headers, callback);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void move(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		move(URI.create(url), parameters, headers, callback);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void move(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		move(URI.create(url), readTimeout, callback);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void move(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		move(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void move(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		move(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void move(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException{
		move(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void move(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void head(String url, Callback callback) throws IOException, RequestException{
		head(URI.create(url), callback);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void head(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		head(URI.create(url), parameters, callback);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void head(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		head(URI.create(url), headers, callback);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void head(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		head(URI.create(url), parameters, headers, callback);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void head(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		head(URI.create(url), readTimeout, callback);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void head(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		head(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void head(String url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		head(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void head(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException{
		head(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void head(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void options(String url, Callback callback) throws IOException, RequestException{
		options(URI.create(url), callback);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void options(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		options(URI.create(url), parameters, callback);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void options(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		options(URI.create(url), headers, callback);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void options(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		options(URI.create(url), parameters, headers, callback);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void options(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		options(URI.create(url), readTimeout, callback);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void options(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		options(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void options(String url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		options(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void options(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						 Callback callback) throws IOException, RequestException{
		options(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void options(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void link(String url, Callback callback) throws IOException, RequestException{
		link(URI.create(url), callback);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void link(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		link(URI.create(url), parameters, callback);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void link(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		link(URI.create(url), headers, callback);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void link(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		link(URI.create(url), parameters, headers, callback);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void link(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		link(URI.create(url), readTimeout, callback);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void link(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		link(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void link(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		link(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void link(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException{
		link(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void link(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlink(String url, Callback callback) throws IOException, RequestException{
		unlink(URI.create(url), callback);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlink(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		unlink(URI.create(url), parameters, callback);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlink(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		unlink(URI.create(url), headers, callback);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlink(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		unlink(URI.create(url), parameters, headers, callback);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlink(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		unlink(URI.create(url), readTimeout, callback);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlink(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		unlink(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlink(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		unlink(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlink(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException{
		unlink(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlink(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void purge(String url, Callback callback) throws IOException, RequestException{
		purge(URI.create(url), callback);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void purge(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		purge(URI.create(url), parameters, callback);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void purge(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		purge(URI.create(url), headers, callback);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void purge(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		purge(URI.create(url), parameters, headers, callback);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void purge(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		purge(URI.create(url), readTimeout, callback);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void purge(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		purge(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void purge(String url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		purge(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void purge(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException{
		purge(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void purge(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void lock(String url, Callback callback) throws IOException, RequestException{
		lock(URI.create(url), callback);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void lock(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		lock(URI.create(url), parameters, callback);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void lock(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		lock(URI.create(url), headers, callback);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void lock(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		lock(URI.create(url), parameters, headers, callback);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void lock(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		lock(URI.create(url), readTimeout, callback);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void lock(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		lock(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void lock(String url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		lock(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void lock(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException{
		lock(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void lock(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlock(String url, Callback callback) throws IOException, RequestException{
		unlock(URI.create(url), callback);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlock(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		unlock(URI.create(url), parameters, callback);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlock(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		unlock(URI.create(url), headers, callback);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlock(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		unlock(URI.create(url), parameters, headers, callback);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlock(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		unlock(URI.create(url), readTimeout, callback);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlock(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		unlock(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlock(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		unlock(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void unlock(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException{
		unlock(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void unlock(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void propfind(String url, Callback callback) throws IOException, RequestException{
		propfind(URI.create(url), callback);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void propfind(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		propfind(URI.create(url), parameters, callback);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void propfind(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		propfind(URI.create(url), headers, callback);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void propfind(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		propfind(URI.create(url), parameters, headers, callback);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void propfind(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		propfind(URI.create(url), readTimeout, callback);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void propfind(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		propfind(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void propfind(String url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		propfind(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void propfind(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						  Callback callback) throws IOException, RequestException{
		propfind(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void propfind(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, Callback callback) throws IOException, RequestException{
		proppatch(URI.create(url), callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		proppatch(URI.create(url), parameters, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		proppatch(URI.create(url), headers, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		proppatch(URI.create(url), parameters, headers, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, RequestBody<?> data, Callback callback) throws IOException, RequestException{
		proppatch(URI.create(url), data, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		proppatch(URI.create(url), data, parameters, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		proppatch(URI.create(url), data, headers, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
						   Callback callback)
			throws IOException, RequestException{
		proppatch(URI.create(url), data, parameters, headers, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
				   Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
				   Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		proppatch(URI.create(url), readTimeout, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		proppatch(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		proppatch(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						   Callback callback) throws IOException, RequestException{
		proppatch(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException{
		proppatch(URI.create(url), readTimeout, data, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						   Callback callback) throws IOException, RequestException{
		proppatch(URI.create(url), readTimeout, data, parameters, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		proppatch(URI.create(url), readTimeout, data, headers, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void proppatch(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						   List<Header> headers, Callback callback) throws IOException, RequestException{
		proppatch(URI.create(url), readTimeout, data, parameters, headers, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
				   List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void proppatch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
				   List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, Callback callback) throws IOException, RequestException{
		report(URI.create(url), callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		report(URI.create(url), parameters, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		report(URI.create(url), headers, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		report(URI.create(url), parameters, headers, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, RequestBody<?> data, Callback callback) throws IOException, RequestException{
		report(URI.create(url), data, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		report(URI.create(url), data, parameters, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		report(URI.create(url), data, headers, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException{
		report(URI.create(url), data, parameters, headers, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
				Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
				Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		report(URI.create(url), readTimeout, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		report(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		report(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException{
		report(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException{
		report(URI.create(url), readTimeout, data, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, int readTimeout, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						Callback callback) throws IOException, RequestException{
		report(URI.create(url), readTimeout, data, parameters, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		report(URI.create(url), readTimeout, data, headers, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void report(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						List<Header> headers, Callback callback) throws IOException, RequestException{
		report(URI.create(url), readTimeout, data, parameters, headers, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
				Callback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void report(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
				Callback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void view(String url, Callback callback) throws IOException, RequestException{
		view(URI.create(url), callback);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void view(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		view(URI.create(url), parameters, callback);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void view(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		view(URI.create(url), headers, callback);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void view(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		view(URI.create(url), parameters, headers, callback);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void view(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		view(URI.create(url), readTimeout, callback);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void view(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		view(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void view(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		view(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void view(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException{
		view(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void view(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void wrapped(String url, Callback callback) throws IOException, RequestException{
		wrapped(URI.create(url), callback);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URI uri, Callback callback) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URL url, Callback callback) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void wrapped(String url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException{
		wrapped(URI.create(url), parameters, callback);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void wrapped(String url, List<Header> headers, Callback callback) throws IOException, RequestException{
		wrapped(URI.create(url), headers, callback);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URL url, List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void wrapped(String url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		wrapped(URI.create(url), parameters, headers, callback);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void wrapped(String url, int readTimeout, Callback callback) throws IOException, RequestException{
		wrapped(URI.create(url), readTimeout, callback);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URI uri, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URL url, int readTimeout, Callback callback) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void wrapped(String url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		wrapped(URI.create(url), readTimeout, parameters, callback);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URL url, int readTimeout, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void wrapped(String url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException{
		wrapped(URI.create(url), readTimeout, headers, callback);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void wrapped(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						 Callback callback) throws IOException, RequestException{
		wrapped(URI.create(url), readTimeout, parameters, headers, callback);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理回调
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void wrapped(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, Callback callback) throws IOException,
			RequestException{
		request(URI.create(url), requestMethod, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		request(URI.create(url), requestMethod, parameters, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		request(URI.create(url), requestMethod, headers, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, List<Header> headers, Callback callback) throws IOException,
			RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, Map<String, Object> parameters,
						 List<Header> headers, Callback callback) throws IOException, RequestException{
		request(URI.create(url), requestMethod, parameters, headers, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers,
				 Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers,
				 Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		request(URI.create(url), requestMethod, data, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, RequestBody<?> data, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
						 Callback callback) throws IOException, RequestException{
		request(URI.create(url), requestMethod, data, parameters, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
				 Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
				 Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers,
						 Callback callback) throws IOException, RequestException{
		request(URI.create(url), requestMethod, data, headers, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
						 List<Header> headers, Callback callback) throws IOException, RequestException{
		request(URI.create(url), requestMethod, data, parameters, headers, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
				 List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
				 List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, int readTimeout, Callback callback)
			throws IOException, RequestException{
		request(URI.create(url), requestMethod, readTimeout, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, int readTimeout, Callback callback) throws IOException,
			RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, int readTimeout, Callback callback) throws IOException,
			RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
						 Callback callback) throws IOException, RequestException{
		request(URI.create(url), requestMethod, readTimeout, parameters, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
				 Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
				 Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, int readTimeout, List<Header> headers,
						 Callback callback) throws IOException, RequestException{
		request(URI.create(url), requestMethod, readTimeout, headers, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
						 List<Header> headers, Callback callback) throws IOException, RequestException{
		request(URI.create(url), requestMethod, readTimeout, parameters, headers, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
				 List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
				 List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						 Callback callback) throws IOException, RequestException{
		request(URI.create(url), requestMethod, readTimeout, data, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						 Map<String, Object> parameters, Callback callback) throws IOException, RequestException{
		request(URI.create(url), requestMethod, readTimeout, data, parameters, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
				 Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
				 Map<String, Object> parameters, Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						 List<Header> headers, Callback callback) throws IOException, RequestException{
		request(URI.create(url), requestMethod, readTimeout, data, headers, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
				 List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
				 List<Header> headers, Callback callback) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default void request(String url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						 Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		request(URI.create(url), requestMethod, readTimeout, data, parameters, headers, callback);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
				 Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	void request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
				 Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException;

}
