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
     */
    Response get(String url);

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response get(URL url);

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response get(String url, List<Header> headers);

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response get(URL url, List<Header> headers);

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response get(String url, Map<String, Object> parameters);

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response get(URL url, Map<String, Object> parameters);

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
     */
    Response get(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response get(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response post(String url);

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response post(URL url);

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response post(String url, List<Header> headers);

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response post(URL url, List<Header> headers);

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response post(String url, Map<String, Object> parameters);

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response post(URL url, Map<String, Object> parameters);

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
     */
    Response post(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response post(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     */
    Response post(String url, RequestBody data);

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     */
    Response post(URL url, RequestBody data);

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
     */
    Response post(String url, RequestBody data, List<Header> headers);

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
     */
    Response post(URL url, RequestBody data, List<Header> headers);

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
     */
    Response post(String url, RequestBody data, Map<String, Object> parameters);

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
     */
    Response post(URL url, RequestBody data, Map<String, Object> parameters);

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
     */
    Response post(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response post(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers);

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response patch(String url);

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response patch(URL url);

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response patch(String url, List<Header> headers);

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response patch(URL url, List<Header> headers);

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response patch(String url, Map<String, Object> parameters);

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response patch(URL url, Map<String, Object> parameters);

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
     */
    Response patch(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response patch(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     */
    Response patch(String url, RequestBody data);

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     */
    Response patch(URL url, RequestBody data);

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
     */
    Response patch(String url, RequestBody data, List<Header> headers);

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
     */
    Response patch(URL url, RequestBody data, List<Header> headers);

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
     */
    Response patch(String url, RequestBody data, Map<String, Object> parameters);

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
     */
    Response patch(URL url, RequestBody data, Map<String, Object> parameters);

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
     */
    Response patch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response patch(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers);

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response put(String url);

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response put(URL url);

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response put(String url, List<Header> headers);

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response put(URL url, List<Header> headers);

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response put(String url, Map<String, Object> parameters);

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response put(URL url, Map<String, Object> parameters);

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
     */
    Response put(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response put(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     */
    Response put(String url, RequestBody data);

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     */
    Response put(URL url, RequestBody data);

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
     */
    Response put(String url, RequestBody data, List<Header> headers);

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
     */
    Response put(URL url, RequestBody data, List<Header> headers);

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
     */
    Response put(String url, RequestBody data, Map<String, Object> parameters);

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
     */
    Response put(URL url, RequestBody data, Map<String, Object> parameters);

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
     */
    Response put(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response put(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers);

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response delete(String url);

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response delete(URL url);

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response delete(String url, List<Header> headers);

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response delete(URL url, List<Header> headers);

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response delete(String url, Map<String, Object> parameters);

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response delete(URL url, Map<String, Object> parameters);

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
     */
    Response delete(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response delete(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response connect(String url);

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response connect(URL url);

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response connect(String url, List<Header> headers);

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response connect(URL url, List<Header> headers);

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response connect(String url, Map<String, Object> parameters);

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response connect(URL url, Map<String, Object> parameters);

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
     */
    Response connect(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response connect(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response trace(String url);

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response trace(URL url);

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response trace(String url, List<Header> headers);

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response trace(URL url, List<Header> headers);

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response trace(String url, Map<String, Object> parameters);

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response trace(URL url, Map<String, Object> parameters);

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
     */
    Response trace(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response trace(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response copy(String url);

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response copy(URL url);

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response copy(String url, List<Header> headers);

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response copy(URL url, List<Header> headers);

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response copy(String url, Map<String, Object> parameters);

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response copy(URL url, Map<String, Object> parameters);

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
     */
    Response copy(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response copy(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response move(String url);

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response move(URL url);

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response move(String url, List<Header> headers);

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response move(URL url, List<Header> headers);

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response move(String url, Map<String, Object> parameters);

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response move(URL url, Map<String, Object> parameters);

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
     */
    Response move(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response move(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response head(String url);

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response head(URL url);

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response head(String url, List<Header> headers);

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response head(URL url, List<Header> headers);

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response head(String url, Map<String, Object> parameters);

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response head(URL url, Map<String, Object> parameters);

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
     */
    Response head(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response head(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response options(String url);

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response options(URL url);

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response options(String url, List<Header> headers);

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response options(URL url, List<Header> headers);

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response options(String url, Map<String, Object> parameters);

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response options(URL url, Map<String, Object> parameters);

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
     */
    Response options(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response options(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response link(String url);

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response link(URL url);

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response link(String url, List<Header> headers);

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response link(URL url, List<Header> headers);

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response link(String url, Map<String, Object> parameters);

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response link(URL url, Map<String, Object> parameters);

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
     */
    Response link(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response link(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response unlink(String url);

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response unlink(URL url);

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response unlink(String url, List<Header> headers);

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response unlink(URL url, List<Header> headers);

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response unlink(String url, Map<String, Object> parameters);

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response unlink(URL url, Map<String, Object> parameters);

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
     */
    Response unlink(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response unlink(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response purge(String url);

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response purge(URL url);

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response purge(String url, List<Header> headers);

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response purge(URL url, List<Header> headers);

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response purge(String url, Map<String, Object> parameters);

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response purge(URL url, Map<String, Object> parameters);

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
     */
    Response purge(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response purge(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response lock(String url);

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response lock(URL url);

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response lock(String url, List<Header> headers);

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response lock(URL url, List<Header> headers);

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response lock(String url, Map<String, Object> parameters);

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response lock(URL url, Map<String, Object> parameters);

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
     */
    Response lock(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response lock(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response unlock(String url);

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response unlock(URL url);

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response unlock(String url, List<Header> headers);

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response unlock(URL url, List<Header> headers);

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response unlock(String url, Map<String, Object> parameters);

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response unlock(URL url, Map<String, Object> parameters);

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
     */
    Response unlock(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response unlock(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response propfind(String url);

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response propfind(URL url);

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response propfind(String url, List<Header> headers);

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response propfind(URL url, List<Header> headers);

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response propfind(String url, Map<String, Object> parameters);

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response propfind(URL url, Map<String, Object> parameters);

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
     */
    Response propfind(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response propfind(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response view(String url);

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response view(URL url);

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response view(String url, List<Header> headers);

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response view(URL url, List<Header> headers);

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response view(String url, Map<String, Object> parameters);

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response view(URL url, Map<String, Object> parameters);

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
     */
    Response view(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response view(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response proppatch(String url);

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response proppatch(URL url);

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response proppatch(String url, List<Header> headers);

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response proppatch(URL url, List<Header> headers);

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response proppatch(String url, Map<String, Object> parameters);

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response proppatch(URL url, Map<String, Object> parameters);

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
     */
    Response proppatch(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response proppatch(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     */
    Response proppatch(String url, RequestBody data);

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     */
    Response proppatch(URL url, RequestBody data);

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
     */
    Response proppatch(String url, RequestBody data, List<Header> headers);

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
     */
    Response proppatch(URL url, RequestBody data, List<Header> headers);

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
     */
    Response proppatch(String url, RequestBody data, Map<String, Object> parameters);

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
     */
    Response proppatch(URL url, RequestBody data, Map<String, Object> parameters);

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
     */
    Response proppatch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response proppatch(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers);

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response report(String url);

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response report(URL url);

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response report(String url, List<Header> headers);

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response report(URL url, List<Header> headers);

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response report(String url, Map<String, Object> parameters);

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response report(URL url, Map<String, Object> parameters);

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
     */
    Response report(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response report(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     */
    Response report(String url, RequestBody data);

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     * @param data
     *         请求数据
     *
     * @return Response {@link Response}
     */
    Response report(URL url, RequestBody data);

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
     */
    Response report(String url, RequestBody data, List<Header> headers);

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
     */
    Response report(URL url, RequestBody data, List<Header> headers);

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
     */
    Response report(String url, RequestBody data, Map<String, Object> parameters);

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
     */
    Response report(URL url, RequestBody data, Map<String, Object> parameters);

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
     */
    Response report(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response report(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers);

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response wrapped(String url);

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    Response wrapped(URL url);

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response wrapped(String url, List<Header> headers);

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     * @param headers
     *         请求头
     *
     * @return Response {@link Response}
     */
    Response wrapped(URL url, List<Header> headers);

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response wrapped(String url, Map<String, Object> parameters);

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     * @param parameters
     *         请求参数
     *
     * @return Response {@link Response}
     */
    Response wrapped(URL url, Map<String, Object> parameters);

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
     */
    Response wrapped(String url, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response wrapped(URL url, Map<String, Object> parameters, List<Header> headers);

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     *
     * @return Response {@link Response}
     */
    Response request(String url, RequestMethod requestMethod);

    /**
     * HTTP 请求
     *
     * @param url
     *         请求 URL
     * @param requestMethod
     *         请求方法
     *
     * @return Response {@link Response}
     */
    Response request(URL url, RequestMethod requestMethod);

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
     */
    Response request(String url, RequestMethod requestMethod, List<Header> headers);

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
     */
    Response request(URL url, RequestMethod requestMethod, List<Header> headers);

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
     */
    Response request(String url, RequestMethod requestMethod, Map<String, Object> parameters);

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
     */
    Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters);

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
     */
    Response request(String url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers);

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
     */
    Response request(String url, RequestMethod requestMethod, RequestBody data);

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
     */
    Response request(URL url, RequestMethod requestMethod, RequestBody data);

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
     */
    Response request(String url, RequestMethod requestMethod, RequestBody data, List<Header> headers);

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
     */
    Response request(URL url, RequestMethod requestMethod, RequestBody data, List<Header> headers);

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
     */
    Response request(String url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters);

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
     */
    Response request(URL url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters);

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
     */
    Response request(String url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters,
                     List<Header> headers);

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
     */
    Response request(URL url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters,
                     List<Header> headers);

}
