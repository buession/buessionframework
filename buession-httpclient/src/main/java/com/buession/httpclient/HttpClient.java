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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient;

import com.buession.httpclient.conn.ConnectionManager;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.ConnectTimeoutException;
import com.buession.httpclient.exception.ConnectionPoolTimeoutException;
import com.buession.httpclient.exception.ReadTimeoutException;
import com.buession.httpclient.exception.RequestAbortedException;
import com.buession.httpclient.exception.RequestException;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public interface HttpClient {

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
     *         连接管理器
     */
    void setConnectionManager(ConnectionManager connectionManager);

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */

    Response get(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response get(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response get(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response get(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response get(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response get(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response get(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response get(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(String url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(URL url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(String url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(URL url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(String url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(URL url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response post(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(String url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(URL url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(String url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(URL url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(String url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(URL url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response patch(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(String url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(URL url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(String url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(URL url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(String url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(URL url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response put(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response delete(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response delete(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response delete(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response delete(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response delete(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response delete(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response delete(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response delete(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response connect(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response connect(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response connect(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response connect(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response connect(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response connect(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response connect(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response connect(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response trace(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response trace(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response trace(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response trace(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response trace(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response trace(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response trace(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response trace(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response copy(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response copy(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response copy(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response copy(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response copy(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response copy(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response copy(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response copy(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response move(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response move(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response move(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response move(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response move(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response move(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response move(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response move(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response head(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response head(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response head(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response head(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response head(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response head(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response head(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response head(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response options(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response options(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response options(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response options(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response options(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response options(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response options(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response options(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response link(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response link(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response link(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response link(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response link(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response link(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response link(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response link(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlink(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlink(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlink(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlink(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlink(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlink(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlink(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlink(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response purge(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response purge(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response purge(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response purge(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response purge(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response purge(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response purge(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response purge(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response lock(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response lock(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response lock(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response lock(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response lock(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response lock(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response lock(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response lock(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlock(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlock(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlock(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlock(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlock(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlock(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlock(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response unlock(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response propfind(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response propfind(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response propfind(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response propfind(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response propfind(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response propfind(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response propfind(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response propfind(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response view(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response view(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response view(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response view(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response view(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response view(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response view(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response view(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(String url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(URL url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(String url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(URL url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(String url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(URL url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response proppatch(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(String url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(String url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(String url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(URL url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(String url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(URL url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(String url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(URL url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response report(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response wrapped(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response wrapped(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response wrapped(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response wrapped(URL url, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response wrapped(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response wrapped(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response wrapped(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response wrapped(URL url, Map<String, Object> parameters, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(String url, RequestMethod requestMethod) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(URL url, RequestMethod requestMethod) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(String url, RequestMethod requestMethod, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(URL url, RequestMethod requestMethod, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(String url, RequestMethod requestMethod, Map<String, Object> parameters) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(String url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers)
            throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers)
            throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(String url, RequestMethod requestMethod, RequestBody data) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(URL url, RequestMethod requestMethod, RequestBody data) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(String url, RequestMethod requestMethod, RequestBody data, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param data
     *         请求数据
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(URL url, RequestMethod requestMethod, RequestBody data, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(String url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters)
            throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(URL url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(String url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters,
                     List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     * @param data
     *         请求数据
     * @param parameters
     *         请求参数
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     *
     * @throws ConnectTimeoutException
     *         连接超时异常
     * @throws ConnectionPoolTimeoutException
     *         连接池异常
     * @throws ReadTimeoutException
     *         读取超时异常
     * @throws RequestAbortedException
     *         请求终止异常
     * @throws RequestException
     *         请求异常
     */
    Response request(URL url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters,
                     List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException;

}
