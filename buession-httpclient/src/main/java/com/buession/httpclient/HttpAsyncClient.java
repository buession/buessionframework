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
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.concurrent.FutureCallback;
import com.buession.httpclient.exception.RequestException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(String url, FutureCallback callback) throws IOException, RequestException{
		return get(URI.create(url), callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> get(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(URL url, FutureCallback callback) throws IOException, RequestException{
		return HttpClientExecuteUtils.execute(()->get(url.toURI(), callback));
	}

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return get(URI.create(url), parameters, callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> get(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return HttpClientExecuteUtils.execute(()->get(url.toURI(), parameters, callback));
	}

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return get(URI.create(url), headers, callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> get(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return HttpClientExecuteUtils.execute(()->get(url.toURI(), headers, callback));
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(String url, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return get(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> get(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(URL url, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return HttpClientExecuteUtils.execute(()->get(url.toURI(), parameters, headers, callback));
	}

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(String url, int readTimeout, FutureCallback callback)
			throws IOException, RequestException{
		return get(URI.create(url), readTimeout, callback);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> get(URI uri, int readTimeout, FutureCallback callback) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(URL url, int readTimeout, FutureCallback callback)
			throws IOException, RequestException{
		return HttpClientExecuteUtils.execute(()->get(url.toURI(), readTimeout, callback));
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(String url, int readTimeout, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return get(URI.create(url), readTimeout, parameters, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> get(URI uri, int readTimeout, Map<String, Object> parameters, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(URL url, int readTimeout, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return HttpClientExecuteUtils.execute(()->get(url.toURI(), readTimeout, parameters, callback));
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(String url, int readTimeout, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return get(URI.create(url), readTimeout, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> get(URI uri, int readTimeout, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(URL url, int readTimeout, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return HttpClientExecuteUtils.execute(()->get(url.toURI(), readTimeout, headers, callback));
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return get(URI.create(url), readTimeout, parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> get(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						 FutureCallback callback) throws IOException, RequestException;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> get(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return HttpClientExecuteUtils.execute(()->get(url.toURI(), readTimeout, parameters, headers, callback));
	}

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(String url, FutureCallback callback) throws IOException, RequestException{
		return post(URI.create(url), callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> post(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return post(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return post(URI.create(url), parameters, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> post(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return post(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return post(URI.create(url), headers, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> post(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(URL url, List<Header> headers, FutureCallback callback) throws IOException,
			RequestException{
		try{
			return post(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(String url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return post(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> post(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(URL url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		try{
			return post(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(String url, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException{
		return post(URI.create(url), data, callback);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> post(URI uri, RequestBody<?> data, FutureCallback callback) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(URL url, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return post(url.toURI(), data, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(String url, RequestBody<?> data, Map<String, Object> parameters,
								  FutureCallback callback) throws IOException, RequestException{
		return post(URI.create(url), data, parameters, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> post(URI uri, RequestBody<?> data, Map<String, Object> parameters, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(URL url, RequestBody<?> data, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return post(url.toURI(), data, parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(String url, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return post(URI.create(url), data, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> post(URI uri, RequestBody<?> data, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(URL url, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return post(url.toURI(), data, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return post(URI.create(url), data, parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> post(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
						  FutureCallback callback) throws IOException, RequestException;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> post(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		try{
			return post(url.toURI(), data, parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(String url, FutureCallback callback) throws IOException, RequestException{
		return patch(URI.create(url), callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> patch(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return patch(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return patch(URI.create(url), parameters, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> patch(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return patch(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return patch(URI.create(url), headers, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> patch(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(URL url, List<Header> headers, FutureCallback callback) throws IOException,
			RequestException{
		try{
			return patch(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(String url, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		return patch(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> patch(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(URL url, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		try{
			return patch(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(String url, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException{
		return patch(URI.create(url), data, callback);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> patch(URI uri, RequestBody<?> data, FutureCallback callback) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(URL url, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return patch(url.toURI(), data, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(String url, RequestBody<?> data, Map<String, Object> parameters,
								   FutureCallback callback) throws IOException, RequestException{
		return patch(URI.create(url), data, parameters, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(URL url, RequestBody<?> data, Map<String, Object> parameters,
								   FutureCallback callback) throws IOException, RequestException{
		try{
			return patch(url.toURI(), data, parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(String url, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return patch(URI.create(url), data, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> patch(URI uri, RequestBody<?> data, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(URL url, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return patch(url.toURI(), data, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(String url, RequestBody<?> data, Map<String, Object> parameters,
								   List<Header> headers, FutureCallback callback) throws IOException, RequestException{
		return patch(URI.create(url), data, parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
						   FutureCallback callback) throws IOException, RequestException;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> patch(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		try{
			return patch(url.toURI(), data, parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(String url, FutureCallback callback) throws IOException, RequestException{
		return put(URI.create(url), callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> put(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return put(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return put(URI.create(url), parameters, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> put(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return put(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return put(URI.create(url), headers, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> put(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(URL url, List<Header> headers, FutureCallback callback) throws IOException,
			RequestException{
		try{
			return put(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(String url, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return put(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> put(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(URL url, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return put(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(String url, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException{
		return put(URI.create(url), data, callback);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> put(URI uri, RequestBody<?> data, FutureCallback callback) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(URL url, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return put(url.toURI(), data, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(String url, RequestBody<?> data, Map<String, Object> parameters,
								 FutureCallback callback) throws IOException, RequestException{
		return put(URI.create(url), data, parameters, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> put(URI uri, RequestBody<?> data, Map<String, Object> parameters, FutureCallback callback)
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
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(URL url, RequestBody<?> data, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return put(url.toURI(), data, parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(String url, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return put(URI.create(url), data, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> put(URI uri, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(URL url, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return put(url.toURI(), data, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		return put(URI.create(url), data, parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> put(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
						 FutureCallback callback) throws IOException, RequestException;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> put(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								 FutureCallback callback) throws IOException, RequestException{
		try{
			return put(url.toURI(), data, parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> delete(String url, FutureCallback callback) throws IOException, RequestException{
		return delete(URI.create(url), callback);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> delete(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> delete(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return delete(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> delete(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return delete(URI.create(url), parameters, callback);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> delete(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> delete(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return delete(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> delete(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return delete(URI.create(url), headers, callback);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> delete(URI uri, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> delete(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return delete(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> delete(String url, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		return delete(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> delete(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> delete(URL url, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		try{
			return delete(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> connect(String url, FutureCallback callback) throws IOException, RequestException{
		return connect(URI.create(url), callback);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> connect(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> connect(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return connect(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> connect(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return connect(URI.create(url), parameters, callback);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> connect(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> connect(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return connect(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> connect(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return connect(URI.create(url), headers, callback);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> connect(URI uri, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> connect(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return connect(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> connect(String url, Map<String, Object> parameters, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		return connect(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> connect(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> connect(URL url, Map<String, Object> parameters, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		try{
			return connect(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> trace(String url, FutureCallback callback) throws IOException, RequestException{
		return trace(URI.create(url), callback);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> trace(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> trace(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return trace(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> trace(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return trace(URI.create(url), parameters, callback);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> trace(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> trace(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return trace(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> trace(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return trace(URI.create(url), headers, callback);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> trace(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> trace(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return trace(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> trace(String url, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		return trace(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> trace(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> trace(URL url, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		try{
			return trace(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> copy(String url, FutureCallback callback) throws IOException, RequestException{
		return copy(URI.create(url), callback);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> copy(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> copy(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return copy(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> copy(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return copy(URI.create(url), parameters, callback);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> copy(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> copy(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return copy(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> copy(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return copy(URI.create(url), headers, callback);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> copy(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> copy(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return copy(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> copy(String url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return copy(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> copy(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> copy(URL url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		try{
			return copy(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> move(String url, FutureCallback callback) throws IOException, RequestException{
		return move(URI.create(url), callback);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> move(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> move(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return move(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> move(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return move(URI.create(url), parameters, callback);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> move(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> move(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return move(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> move(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return move(URI.create(url), headers, callback);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> move(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> move(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return move(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> move(String url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return move(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> move(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> move(URL url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		try{
			return move(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> head(String url, FutureCallback callback) throws IOException, RequestException{
		return head(URI.create(url), callback);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> head(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> head(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return head(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> head(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return head(URI.create(url), parameters, callback);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> head(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> head(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return head(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> head(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return head(URI.create(url), headers, callback);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> head(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> head(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return head(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> head(String url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return head(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> head(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> head(URL url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		try{
			return head(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> options(String url, FutureCallback callback) throws IOException, RequestException{
		return options(URI.create(url), callback);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> options(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> options(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return options(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> options(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return options(URI.create(url), parameters, callback);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> options(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> options(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return options(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> options(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return options(URI.create(url), headers, callback);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> options(URI uri, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> options(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return options(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> options(String url, Map<String, Object> parameters, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		return options(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> options(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> options(URL url, Map<String, Object> parameters, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		try{
			return options(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> link(String url, FutureCallback callback) throws IOException, RequestException{
		return link(URI.create(url), callback);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> link(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> link(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return link(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> link(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return link(URI.create(url), parameters, callback);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> link(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> link(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return link(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> link(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return link(URI.create(url), headers, callback);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> link(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> link(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return link(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> link(String url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return link(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> link(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> link(URL url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		try{
			return link(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlink(String url, FutureCallback callback) throws IOException, RequestException{
		return unlink(URI.create(url), callback);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> unlink(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlink(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return unlink(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlink(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return unlink(URI.create(url), parameters, callback);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> unlink(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlink(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return unlink(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlink(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return unlink(URI.create(url), headers, callback);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> unlink(URI uri, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlink(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return unlink(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlink(String url, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		return unlink(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> unlink(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlink(URL url, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		try{
			return unlink(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> purge(String url, FutureCallback callback) throws IOException, RequestException{
		return purge(URI.create(url), callback);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> purge(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> purge(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return purge(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> purge(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return purge(URI.create(url), parameters, callback);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> purge(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> purge(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return purge(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> purge(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return purge(URI.create(url), headers, callback);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> purge(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> purge(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return purge(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> purge(String url, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		return purge(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> purge(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> purge(URL url, Map<String, Object> parameters, List<Header> headers,
								   FutureCallback callback) throws IOException, RequestException{
		try{
			return purge(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> lock(String url, FutureCallback callback) throws IOException, RequestException{
		return lock(URI.create(url), callback);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> lock(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> lock(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return lock(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> lock(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return lock(URI.create(url), parameters, callback);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> lock(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> lock(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return lock(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> lock(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return lock(URI.create(url), headers, callback);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> lock(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> lock(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return lock(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> lock(String url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return lock(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> lock(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> lock(URL url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		try{
			return lock(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlock(String url, FutureCallback callback) throws IOException, RequestException{
		return unlock(URI.create(url), callback);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> unlock(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlock(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return unlock(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlock(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return unlock(URI.create(url), parameters, callback);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> unlock(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlock(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return unlock(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlock(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return unlock(URI.create(url), headers, callback);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> unlock(URI uri, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlock(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return unlock(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlock(String url, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		return unlock(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> unlock(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> unlock(URL url, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		try{
			return unlock(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> propfind(String url, FutureCallback callback) throws IOException, RequestException{
		return propfind(URI.create(url), callback);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> propfind(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> propfind(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return propfind(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> propfind(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return propfind(URI.create(url), parameters, callback);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> propfind(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> propfind(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return propfind(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> propfind(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return propfind(URI.create(url), headers, callback);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> propfind(URI uri, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> propfind(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return propfind(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> propfind(String url, Map<String, Object> parameters, List<Header> headers,
									  FutureCallback callback) throws IOException, RequestException{
		return propfind(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> propfind(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> propfind(URL url, Map<String, Object> parameters, List<Header> headers,
									  FutureCallback callback) throws IOException, RequestException{
		try{
			return propfind(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(String url, FutureCallback callback) throws IOException, RequestException{
		return proppatch(URI.create(url), callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> proppatch(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return proppatch(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return proppatch(URI.create(url), parameters, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> proppatch(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return proppatch(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return proppatch(URI.create(url), headers, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> proppatch(URI uri, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(URL url, List<Header> headers, FutureCallback callback) throws IOException,
			RequestException{
		try{
			return proppatch(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(String url, Map<String, Object> parameters, List<Header> headers,
									   FutureCallback callback) throws IOException, RequestException{
		return proppatch(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> proppatch(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(URL url, Map<String, Object> parameters, List<Header> headers,
									   FutureCallback callback) throws IOException, RequestException{
		try{
			return proppatch(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(String url, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException{
		return proppatch(URI.create(url), data, callback);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> proppatch(URI uri, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(URL url, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return proppatch(url.toURI(), data, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(String url, RequestBody<?> data, Map<String, Object> parameters,
									   FutureCallback callback) throws IOException, RequestException{
		return proppatch(URI.create(url), data, parameters, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters,
									   FutureCallback callback) throws IOException, RequestException{
		try{
			return proppatch(url.toURI(), data, parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(String url, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return proppatch(URI.create(url), data, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> proppatch(URI uri, RequestBody<?> data, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(URL url, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return proppatch(url.toURI(), data, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(String url, RequestBody<?> data, Map<String, Object> parameters,
									   List<Header> headers, FutureCallback callback) throws IOException,
			RequestException{
		return proppatch(URI.create(url), data, parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
							   FutureCallback callback) throws IOException, RequestException;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters,
									   List<Header> headers, FutureCallback callback) throws IOException,
			RequestException{
		try{
			return proppatch(url.toURI(), data, parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(String url, FutureCallback callback) throws IOException, RequestException{
		return report(URI.create(url), callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> report(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return report(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return report(URI.create(url), parameters, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> report(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return report(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return report(URI.create(url), headers, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> report(URI uri, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(URL url, List<Header> headers, FutureCallback callback) throws IOException,
			RequestException{
		try{
			return report(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(String url, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		return report(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> report(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(URL url, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		try{
			return report(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(String url, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException{
		return report(URI.create(url), data, callback);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> report(URI uri, RequestBody<?> data, FutureCallback callback) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(URL url, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return report(url.toURI(), data, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(String url, RequestBody<?> data, Map<String, Object> parameters,
									FutureCallback callback) throws IOException, RequestException{
		return report(URI.create(url), data, parameters, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> report(URI uri, RequestBody<?> data, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(URL url, RequestBody<?> data, Map<String, Object> parameters,
									FutureCallback callback) throws IOException, RequestException{
		try{
			return report(url.toURI(), data, parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(String url, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return report(URI.create(url), data, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> report(URI uri, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(URL url, RequestBody<?> data, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return report(url.toURI(), data, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(String url, RequestBody<?> data, Map<String, Object> parameters,
									List<Header> headers, FutureCallback callback) throws IOException, RequestException{
		return report(URI.create(url), data, parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> report(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
							FutureCallback callback) throws IOException, RequestException;

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> report(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
									FutureCallback callback) throws IOException, RequestException{
		try{
			return report(url.toURI(), data, parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> view(String url, FutureCallback callback) throws IOException, RequestException{
		return view(URI.create(url), callback);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> view(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> view(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return view(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> view(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return view(URI.create(url), parameters, callback);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> view(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> view(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return view(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> view(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return view(URI.create(url), headers, callback);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> view(URI uri, List<Header> headers, FutureCallback callback) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> view(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return view(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> view(String url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		return view(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> view(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> view(URL url, Map<String, Object> parameters, List<Header> headers,
								  FutureCallback callback) throws IOException, RequestException{
		try{
			return view(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> wrapped(String url, FutureCallback callback) throws IOException, RequestException{
		return wrapped(URI.create(url), callback);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> wrapped(URI uri, FutureCallback callback) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> wrapped(URL url, FutureCallback callback) throws IOException, RequestException{
		try{
			return wrapped(url.toURI(), callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> wrapped(String url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return wrapped(URI.create(url), parameters, callback);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> wrapped(URI uri, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> wrapped(URL url, Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return wrapped(url.toURI(), parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> wrapped(String url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return wrapped(URI.create(url), headers, callback);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> wrapped(URI uri, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 * @param callback
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> wrapped(URL url, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return wrapped(url.toURI(), headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> wrapped(String url, Map<String, Object> parameters, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		return wrapped(URI.create(url), parameters, headers, callback);
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> wrapped(URI uri, Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
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
	 * 		异步 HTTP 请求响应处理
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> wrapped(URL url, Map<String, Object> parameters, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		try{
			return wrapped(url.toURI(), parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(String url, RequestMethod requestMethod, FutureCallback callback)
			throws IOException, RequestException{
		return request(URI.create(url), requestMethod, callback);
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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> request(URI uri, RequestMethod requestMethod, FutureCallback callback)
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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(URL url, RequestMethod requestMethod, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return request(url.toURI(), requestMethod, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(String url, RequestMethod requestMethod, Map<String, Object> parameters,
									 FutureCallback callback) throws IOException, RequestException{
		return request(URI.create(url), requestMethod, parameters, callback);
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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters,
							 FutureCallback callback) throws IOException, RequestException;

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(URL url, RequestMethod requestMethod, Map<String, Object> parameters,
									 FutureCallback callback) throws IOException, RequestException{
		try{
			return request(url.toURI(), requestMethod, parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(String url, RequestMethod requestMethod, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		return request(URI.create(url), requestMethod, headers, callback);
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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> request(URI uri, RequestMethod requestMethod, List<Header> headers, FutureCallback callback)
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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(URL url, RequestMethod requestMethod, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		try{
			return request(url.toURI(), requestMethod, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(String url, RequestMethod requestMethod, Map<String, Object> parameters,
									 List<Header> headers, FutureCallback callback) throws IOException,
			RequestException{
		return request(URI.create(url), requestMethod, parameters, headers, callback);
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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers,
							 FutureCallback callback) throws IOException, RequestException;

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(URL url, RequestMethod requestMethod, Map<String, Object> parameters,
									 List<Header> headers, FutureCallback callback) throws IOException,
			RequestException{
		try{
			return request(url.toURI(), requestMethod, parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(String url, RequestMethod requestMethod, RequestBody<?> data,
									 FutureCallback callback) throws IOException, RequestException{
		return request(URI.create(url), requestMethod, data, callback);
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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> request(URI uri, RequestMethod requestMethod, RequestBody<?> data, FutureCallback callback)
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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(URL url, RequestMethod requestMethod, RequestBody<?> data, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return request(url.toURI(), requestMethod, data, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(String url, RequestMethod requestMethod, RequestBody<?> data,
									 Map<String, Object> parameters, FutureCallback callback)
			throws IOException, RequestException{
		return request(URI.create(url), requestMethod, data, parameters, callback);
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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
							 FutureCallback callback) throws IOException, RequestException;

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(URL url, RequestMethod requestMethod, RequestBody<?> data,
									 Map<String, Object> parameters, FutureCallback callback) throws IOException,
			RequestException{
		try{
			return request(url.toURI(), requestMethod, data, parameters, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(String url, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		return request(URI.create(url), requestMethod, data, headers, callback);
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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> request(URI uri, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers,
							 FutureCallback callback) throws IOException, RequestException;

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(URL url, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers,
									 FutureCallback callback) throws IOException, RequestException{
		try{
			return request(url.toURI(), requestMethod, data, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(String url, RequestMethod requestMethod, RequestBody<?> data,
									 Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		return request(URI.create(url), requestMethod, data, parameters, headers, callback);
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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Future<Response> request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
							 List<Header> headers, FutureCallback callback) throws IOException, RequestException;

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
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Future<Response> request(URL url, RequestMethod requestMethod, RequestBody<?> data,
									 Map<String, Object> parameters, List<Header> headers, FutureCallback callback)
			throws IOException, RequestException{
		try{
			return request(url.toURI(), requestMethod, data, parameters, headers, callback);
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

}
