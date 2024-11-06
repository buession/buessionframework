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
package com.buession.httpclient;

import com.buession.httpclient.conn.ConnectionManager;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.RequestException;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Http 客户端
 *
 * @author Yong.Teng
 */
public interface HttpClient extends IBaseHttpClient, IHttpClient {

	/**
	 * 获取连接管理器
	 *
	 * @return 连接管理器
	 */
	ConnectionManager getConnectionManager();

	/**
	 * 设置连接管理器
	 *
	 * @param connectionManager
	 * 		连接管理器
	 */
	void setConnectionManager(ConnectionManager connectionManager);

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response get(String url) throws IOException, RequestException {
		return get(URI.create(url));
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URI uri) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response get(URL url) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response get(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return get(URI.create(url), parameters);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response get(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response get(String url, List<Header> headers) throws IOException, RequestException {
		return get(URI.create(url), headers);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response get(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response get(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return get(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response get(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response get(String url, int readTimeout) throws IOException, RequestException {
		return get(URI.create(url), readTimeout);
	}

	/**
	 * GET 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response get(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return get(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response get(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return get(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * GET 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response get(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return get(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response get(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response post(String url) throws IOException, RequestException {
		return post(URI.create(url));
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response post(URL url) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response post(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return post(URI.create(url), parameters);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response post(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response post(String url, List<Header> headers) throws IOException, RequestException {
		return post(URI.create(url), headers);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response post(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response post(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return post(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response post(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response post(String url, RequestBody<?> data) throws IOException, RequestException {
		return post(URI.create(url), data);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response post(URL url, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response post(String url, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return post(URI.create(url), data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, RequestBody<?> data, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response post(URL url, RequestBody<?> data, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response post(String url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return post(URI.create(url), data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, RequestBody<?> data, List<Header> headers) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response post(URL url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response post(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return post(URI.create(url), data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response post(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response post(String url, int readTimeout) throws IOException, RequestException {
		return post(URI.create(url), readTimeout);
	}

	/**
	 * POST 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response post(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return post(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response post(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return post(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response post(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return post(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response post(String url, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return post(URI.create(url), readTimeout, data);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, int readTimeout, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * POST 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URL url, int readTimeout, RequestBody<?> data) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response post(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return post(URI.create(url), readTimeout, data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response post(String url, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return post(URI.create(url), readTimeout, data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URL url, int readTimeout, RequestBody<?> data, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response post(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						  List<Header> headers) throws IOException, RequestException {
		return post(URI.create(url), readTimeout, data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response post(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
				  List<Header> headers) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response put(String url) throws IOException, RequestException {
		return put(URI.create(url));
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response put(URL url) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response put(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return put(URI.create(url), parameters);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response put(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response put(String url, List<Header> headers) throws IOException, RequestException {
		return put(URI.create(url), headers);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response put(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response put(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return put(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response put(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response put(String url, RequestBody<?> data) throws IOException, RequestException {
		return put(URI.create(url), data);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response put(URL url, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response put(String url, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return put(URI.create(url), data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, RequestBody<?> data, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response put(URL url, RequestBody<?> data, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response put(String url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return put(URI.create(url), data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, RequestBody<?> data, List<Header> headers) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response put(URL url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response put(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return put(URI.create(url), data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response put(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response put(String url, int readTimeout) throws IOException, RequestException {
		return put(URI.create(url), readTimeout);
	}

	/**
	 * PUT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response put(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return put(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response put(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return put(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response put(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return put(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response put(String url, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return put(URI.create(url), readTimeout, data);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, int readTimeout, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * PUT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URL url, int readTimeout, RequestBody<?> data) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response put(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return put(URI.create(url), readTimeout, data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response put(String url, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return put(URI.create(url), readTimeout, data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URL url, int readTimeout, RequestBody<?> data, List<Header> headers) throws IOException,
			RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response put(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						 List<Header> headers) throws IOException, RequestException {
		return put(URI.create(url), readTimeout, data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response put(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
				 List<Header> headers) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response patch(String url) throws IOException, RequestException {
		return patch(URI.create(url));
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response patch(URL url) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response patch(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return patch(URI.create(url), parameters);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response patch(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response patch(String url, List<Header> headers) throws IOException, RequestException {
		return patch(URI.create(url), headers);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response patch(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response patch(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return patch(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response patch(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response patch(String url, RequestBody<?> data) throws IOException, RequestException {
		return patch(URI.create(url), data);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response patch(URL url, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response patch(String url, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return patch(URI.create(url), data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, RequestBody<?> data, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response patch(URL url, RequestBody<?> data, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response patch(String url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return patch(URI.create(url), data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, RequestBody<?> data, List<Header> headers) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response patch(URL url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response patch(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return patch(URI.create(url), data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
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
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response patch(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response patch(String url, int readTimeout) throws IOException, RequestException {
		return patch(URI.create(url), readTimeout);
	}

	/**
	 * PATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response patch(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return patch(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response patch(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return patch(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response patch(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return patch(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response patch(String url, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return patch(URI.create(url), readTimeout, data);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, int readTimeout, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * PATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URL url, int readTimeout, RequestBody<?> data) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response patch(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return patch(URI.create(url), readTimeout, data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response patch(String url, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return patch(URI.create(url), readTimeout, data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URL url, int readTimeout, RequestBody<?> data, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response patch(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						   List<Header> headers) throws IOException, RequestException {
		return patch(URI.create(url), readTimeout, data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response patch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
				   List<Header> headers) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response delete(String url) throws IOException, RequestException {
		return delete(URI.create(url));
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URI uri) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response delete(URL url) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response delete(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return delete(URI.create(url), parameters);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response delete(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response delete(String url, List<Header> headers) throws IOException, RequestException {
		return delete(URI.create(url), headers);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response delete(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response delete(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return delete(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response delete(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response delete(String url, int readTimeout) throws IOException, RequestException {
		return delete(URI.create(url), readTimeout);
	}

	/**
	 * DELETE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response delete(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return delete(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response delete(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return delete(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * DELETE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response delete(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return delete(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response delete(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response connect(String url) throws IOException, RequestException {
		return connect(URI.create(url));
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URI uri) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response connect(URL url) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response connect(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return connect(URI.create(url), parameters);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response connect(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response connect(String url, List<Header> headers) throws IOException, RequestException {
		return connect(URI.create(url), headers);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response connect(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response connect(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return connect(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URI uri, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response connect(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response connect(String url, int readTimeout) throws IOException, RequestException {
		return connect(URI.create(url), readTimeout);
	}

	/**
	 * CONNECT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response connect(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return connect(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response connect(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return connect(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * CONNECT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response connect(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return connect(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response connect(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response trace(String url) throws IOException, RequestException {
		return trace(URI.create(url));
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URI uri) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response trace(URL url) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response trace(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return trace(URI.create(url), parameters);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response trace(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response trace(String url, List<Header> headers) throws IOException, RequestException {
		return trace(URI.create(url), headers);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response trace(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response trace(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return trace(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response trace(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response trace(String url, int readTimeout) throws IOException, RequestException {
		return trace(URI.create(url), readTimeout);
	}

	/**
	 * TRACE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response trace(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return trace(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response trace(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return trace(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * TRACE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response trace(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return trace(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response trace(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response copy(String url) throws IOException, RequestException {
		return copy(URI.create(url));
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URI uri) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response copy(URL url) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response copy(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return copy(URI.create(url), parameters);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response copy(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response copy(String url, List<Header> headers) throws IOException, RequestException {
		return copy(URI.create(url), headers);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response copy(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response copy(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return copy(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response copy(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response copy(String url, int readTimeout) throws IOException, RequestException {
		return copy(URI.create(url), readTimeout);
	}

	/**
	 * COPY 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response copy(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return copy(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response copy(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return copy(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * COPY 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response copy(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return copy(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response copy(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response move(String url) throws IOException, RequestException {
		return move(URI.create(url));
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URI uri) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response move(URL url) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response move(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return move(URI.create(url), parameters);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response move(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response move(String url, List<Header> headers) throws IOException, RequestException {
		return move(URI.create(url), headers);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response move(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response move(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return move(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response move(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response move(String url, int readTimeout) throws IOException, RequestException {
		return move(URI.create(url), readTimeout);
	}

	/**
	 * MOVE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response move(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return move(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response move(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return move(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * MOVE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response move(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return move(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response move(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response head(String url) throws IOException, RequestException {
		return head(URI.create(url));
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URI uri) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response head(URL url) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response head(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return head(URI.create(url), parameters);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response head(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response head(String url, List<Header> headers) throws IOException, RequestException {
		return head(URI.create(url), headers);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response head(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response head(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return head(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response head(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response head(String url, int readTimeout) throws IOException, RequestException {
		return head(URI.create(url), readTimeout);
	}

	/**
	 * HEAD 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response head(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return head(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response head(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return head(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * HEAD 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response head(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return head(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response head(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response options(String url) throws IOException, RequestException {
		return options(URI.create(url));
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URI uri) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response options(URL url) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response options(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return options(URI.create(url), parameters);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response options(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response options(String url, List<Header> headers) throws IOException, RequestException {
		return options(URI.create(url), headers);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response options(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response options(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return options(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URI uri, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response options(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response options(String url, int readTimeout) throws IOException, RequestException {
		return options(URI.create(url), readTimeout);
	}

	/**
	 * OPTIONS 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response options(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return options(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response options(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return options(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * OPTIONS 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response options(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return options(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response options(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response link(String url) throws IOException, RequestException {
		return link(URI.create(url));
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URI uri) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response link(URL url) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response link(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return link(URI.create(url), parameters);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response link(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response link(String url, List<Header> headers) throws IOException, RequestException {
		return link(URI.create(url), headers);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response link(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response link(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return link(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response link(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response link(String url, int readTimeout) throws IOException, RequestException {
		return link(URI.create(url), readTimeout);
	}

	/**
	 * LINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response link(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return link(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response link(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return link(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * LINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response link(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return link(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response link(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response unlink(String url) throws IOException, RequestException {
		return unlink(URI.create(url));
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URI uri) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response unlink(URL url) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response unlink(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return unlink(URI.create(url), parameters);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response unlink(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response unlink(String url, List<Header> headers) throws IOException, RequestException {
		return unlink(URI.create(url), headers);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response unlink(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response unlink(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return unlink(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response unlink(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response unlink(String url, int readTimeout) throws IOException, RequestException {
		return unlink(URI.create(url), readTimeout);
	}

	/**
	 * UNLINK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response unlink(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return unlink(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response unlink(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return unlink(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * UNLINK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response unlink(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return unlink(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlink(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response purge(String url) throws IOException, RequestException {
		return purge(URI.create(url));
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URI uri) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response purge(URL url) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response purge(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return purge(URI.create(url), parameters);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response purge(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response purge(String url, List<Header> headers) throws IOException, RequestException {
		return purge(URI.create(url), headers);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response purge(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response purge(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return purge(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response purge(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response purge(String url, int readTimeout) throws IOException, RequestException {
		return purge(URI.create(url), readTimeout);
	}

	/**
	 * PURGE 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response purge(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return purge(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response purge(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return purge(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * PURGE 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response purge(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return purge(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response purge(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response lock(String url) throws IOException, RequestException {
		return lock(URI.create(url));
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URI uri) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response lock(URL url) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response lock(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return lock(URI.create(url), parameters);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response lock(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response lock(String url, List<Header> headers) throws IOException, RequestException {
		return lock(URI.create(url), headers);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response lock(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response lock(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return lock(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response lock(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response lock(String url, int readTimeout) throws IOException, RequestException {
		return lock(URI.create(url), readTimeout);
	}

	/**
	 * LOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response lock(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return lock(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response lock(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return lock(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * LOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response lock(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return lock(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response lock(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response unlock(String url) throws IOException, RequestException {
		return unlock(URI.create(url));
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URI uri) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response unlock(URL url) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response unlock(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return unlock(URI.create(url), parameters);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response unlock(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response unlock(String url, List<Header> headers) throws IOException, RequestException {
		return unlock(URI.create(url), headers);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response unlock(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response unlock(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return unlock(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response unlock(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response unlock(String url, int readTimeout) throws IOException, RequestException {
		return unlock(URI.create(url), readTimeout);
	}

	/**
	 * UNLOCK 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response unlock(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return unlock(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response unlock(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return unlock(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * UNLOCK 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response unlock(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return unlock(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response unlock(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response propfind(String url) throws IOException, RequestException {
		return propfind(URI.create(url));
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URI uri) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response propfind(URL url) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response propfind(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return propfind(URI.create(url), parameters);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response propfind(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response propfind(String url, List<Header> headers) throws IOException, RequestException {
		return propfind(URI.create(url), headers);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response propfind(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response propfind(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return propfind(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URI uri, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response propfind(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response propfind(String url, int readTimeout) throws IOException, RequestException {
		return propfind(URI.create(url), readTimeout);
	}

	/**
	 * PROPFIND 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response propfind(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return propfind(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response propfind(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return propfind(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * PROPFIND 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response propfind(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return propfind(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response propfind(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response proppatch(String url) throws IOException, RequestException {
		return proppatch(URI.create(url));
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response proppatch(URL url) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response proppatch(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return proppatch(URI.create(url), parameters);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response proppatch(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response proppatch(String url, List<Header> headers) throws IOException, RequestException {
		return proppatch(URI.create(url), headers);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response proppatch(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response proppatch(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return proppatch(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response proppatch(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response proppatch(String url, RequestBody<?> data) throws IOException, RequestException {
		return proppatch(URI.create(url), data);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response proppatch(URL url, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response proppatch(String url, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return proppatch(URI.create(url), data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response proppatch(String url, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return proppatch(URI.create(url), data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, RequestBody<?> data, List<Header> headers) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response proppatch(URL url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response proppatch(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return proppatch(URI.create(url), data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
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
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response proppatch(String url, int readTimeout) throws IOException, RequestException {
		return proppatch(URI.create(url), readTimeout);
	}

	/**
	 * PROPPATCH 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response proppatch(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return proppatch(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		Disconnected from the target VM, address: '127.0.0.1:60884', transport: 'socket'
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response proppatch(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return proppatch(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response proppatch(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return proppatch(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response proppatch(String url, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return proppatch(URI.create(url), readTimeout, data);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, int readTimeout, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * PROPPATCH 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URL url, int readTimeout, RequestBody<?> data) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response proppatch(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return proppatch(URI.create(url), readTimeout, data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response proppatch(String url, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return proppatch(URI.create(url), readTimeout, data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URL url, int readTimeout, RequestBody<?> data, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response proppatch(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
							   List<Header> headers) throws IOException, RequestException {
		return proppatch(URI.create(url), readTimeout, data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					   List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response proppatch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					   List<Header> headers) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response report(String url) throws IOException, RequestException {
		return report(URI.create(url));
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response report(URL url) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response report(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return report(URI.create(url), parameters);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response report(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response report(String url, List<Header> headers) throws IOException, RequestException {
		return report(URI.create(url), headers);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response report(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response report(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return report(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response report(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response report(String url, RequestBody<?> data) throws IOException, RequestException {
		return report(URI.create(url), data);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response report(URL url, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response report(String url, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return report(URI.create(url), data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, RequestBody<?> data, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response report(URL url, RequestBody<?> data, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response report(String url, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return report(URI.create(url), data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, RequestBody<?> data, List<Header> headers) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param data
	 * 		请求数据
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response report(URL url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response report(String url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return report(URI.create(url), data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
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
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response report(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response report(String url, int readTimeout) throws IOException, RequestException {
		return report(URI.create(url), readTimeout);
	}

	/**
	 * REPORT 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response report(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return report(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response report(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return report(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response report(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return report(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response report(String url, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return report(URI.create(url), readTimeout, data);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, int readTimeout, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * REPORT 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URL url, int readTimeout, RequestBody<?> data) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response report(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return report(URI.create(url), readTimeout, data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response report(String url, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return report(URI.create(url), readTimeout, data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URL url, int readTimeout, RequestBody<?> data, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response report(String url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
							List<Header> headers) throws IOException, RequestException {
		return report(URI.create(url), readTimeout, data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response report(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response view(String url) throws IOException, RequestException {
		return view(URI.create(url));
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URI uri) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response view(URL url) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response view(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return view(URI.create(url), parameters);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response view(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response view(String url, List<Header> headers) throws IOException, RequestException {
		return view(URI.create(url), headers);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response view(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response view(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return view(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URI uri, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response view(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response view(String url, int readTimeout) throws IOException, RequestException {
		return view(URI.create(url), readTimeout);
	}

	/**
	 * VIEW 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response view(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return view(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response view(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return view(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * VIEW 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response view(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return view(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response view(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response wrapped(String url) throws IOException, RequestException {
		return wrapped(URI.create(url));
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URI uri) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response wrapped(URL url) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response wrapped(String url, Map<String, Object> parameters) throws IOException, RequestException {
		return wrapped(URI.create(url), parameters);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URI uri, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response wrapped(URL url, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response wrapped(String url, List<Header> headers) throws IOException, RequestException {
		return wrapped(URI.create(url), headers);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URI uri, List<Header> headers) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response wrapped(URL url, List<Header> headers) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param parameters
	 * 		请求参数
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response wrapped(String url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return wrapped(URI.create(url), parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URI uri, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response wrapped(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response wrapped(String url, int readTimeout) throws IOException, RequestException {
		return wrapped(URI.create(url), readTimeout);
	}

	/**
	 * WRAPPED 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URI uri, int readTimeout) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URL url, int readTimeout) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response wrapped(String url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return wrapped(URI.create(url), readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response wrapped(String url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return wrapped(URI.create(url), readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException;

	/**
	 * WRAPPED 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	default Response wrapped(String url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return wrapped(URI.create(url), readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response wrapped(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod) throws IOException, RequestException {
		return request(URI.create(url), requestMethod);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param parameters
	 * 		请求参数
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, Map<String, Object> parameters) throws
			IOException, RequestException {
		return request(URI.create(url), requestMethod, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters) throws IOException,
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters) throws
			IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, List<Header> headers) throws IOException,
			RequestException {
		return request(URI.create(url), requestMethod, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, List<Header> headers) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, List<Header> headers) throws IOException,
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, Map<String, Object> parameters,
							 List<Header> headers) throws IOException, RequestException {
		return request(URI.create(url), requestMethod, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers)
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
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, RequestBody<?> data) throws IOException,
			RequestException {
		return request(URI.create(url), requestMethod, data);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, RequestBody<?> data) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param data
	 * 		请求数据
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, RequestBody<?> data) throws IOException,
			RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, RequestBody<?> data,
							 Map<String, Object> parameters) throws IOException, RequestException {
		return request(URI.create(url), requestMethod, data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return request(URI.create(url), requestMethod, data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, RequestBody<?> data,
							 Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return request(URI.create(url), requestMethod, data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
					 List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
					 List<Header> headers) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param requestMethod
	 * 		请求方法
	 * @param readTimeout
	 * 		读取超时时间
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, int readTimeout)
			throws IOException, RequestException {
		return request(URI.create(url), requestMethod, readTimeout);
	}

	/**
	 * HTTP 请求
	 *
	 * @param uri
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param requestMethod
	 * 		请求方法
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, int readTimeout) throws IOException, RequestException;

	/**
	 * HTTP 请求
	 *
	 * @param url
	 * 		请求 URL
	 * @param readTimeout
	 * 		读取超时时间
	 * @param requestMethod
	 * 		请求方法
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, int readTimeout) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return request(URI.create(url), requestMethod, readTimeout, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, int readTimeout, List<Header> headers)
			throws IOException, RequestException {
		return request(URI.create(url), requestMethod, readTimeout, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, int readTimeout, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, int readTimeout, List<Header> headers)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
							 List<Header> headers) throws IOException, RequestException {
		return request(URI.create(url), requestMethod, readTimeout, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
					 List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
					 List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data)
			throws IOException, RequestException {
		return request(URI.create(url), requestMethod, readTimeout, data);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data)
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
							 Map<String, Object> parameters) throws IOException, RequestException {
		return request(URI.create(url), requestMethod, readTimeout, data, parameters);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
					 Map<String, Object> parameters) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
					 Map<String, Object> parameters) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
							 List<Header> headers) throws IOException, RequestException {
		return request(URI.create(url), requestMethod, readTimeout, data, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data, List<Header> headers)
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
	 * @param headers
	 * 		请求头
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	Response request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
					 List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 */
	default Response request(String url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
							 Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return request(URI.create(url), requestMethod, readTimeout, data, parameters, headers);
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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
					 Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

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
	 *
	 * @return Response {@link Response}
	 *
	 * @throws IOException
	 * 		IO 异常
	 * @throws RequestException
	 * 		请求异常
	 * @since 2.3.0
	 */
	Response request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
					 Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException;

}
