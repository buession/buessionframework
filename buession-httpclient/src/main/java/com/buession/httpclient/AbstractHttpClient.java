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

import com.buession.core.utils.Assert;
import com.buession.httpclient.conn.ConnectionManager;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public abstract class AbstractHttpClient implements HttpClient {

    private ConnectionManager connectionManager;

    private final static Logger logger = LoggerFactory.getLogger(AbstractHttpClient.class);

    /**
     * 构造函数
     */
    public AbstractHttpClient(){

    }

    /**
     * 构造函数
     *
     * @param connectionManager
     *         连接管理器
     */
    public AbstractHttpClient(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }

    /**
     * 获取连接管理器
     *
     * @return 连接管理器
     */
    @Override
    public ConnectionManager getConnectionManager(){
        return connectionManager;
    }

    /**
     * 设置连接管理器
     *
     * @param connectionManager
     *         连接管理器
     */
    @Override
    public void setConnectionManager(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response get(String url){
        return get(url, null, null);
    }

    /**
     * GET 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response get(URL url){
        return get(url, null, null);
    }

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
    @Override
    public Response get(String url, List<Header> headers){
        return get(url, null, headers);
    }

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
    @Override
    public Response get(URL url, List<Header> headers){
        return get(url, null, headers);
    }

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
    @Override
    public Response get(String url, Map<String, Object> parameters){
        return get(url, parameters, null);
    }

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
    @Override
    public Response get(URL url, Map<String, Object> parameters){
        return get(url, parameters, null);
    }

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
    @Override
    public Response get(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return get(url.toString(), parameters, headers);
    }

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response post(String url){
        return post(url, null, null, null);
    }

    /**
     * POST 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response post(URL url){
        return post(url, null, null, null);
    }

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
    @Override
    public Response post(String url, List<Header> headers){
        return post(url, null, null, headers);
    }

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
    @Override
    public Response post(URL url, List<Header> headers){
        return post(url, null, null, headers);
    }

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
    @Override
    public Response post(String url, Map<String, Object> parameters){
        return post(url, null, parameters, null);
    }

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
    @Override
    public Response post(URL url, Map<String, Object> parameters){
        return post(url, null, parameters, null);
    }

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
    @Override
    public Response post(String url, Map<String, Object> parameters, List<Header> headers){
        return post(url, null, parameters, headers);
    }

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
    @Override
    public Response post(URL url, Map<String, Object> parameters, List<Header> headers){
        return post(url, null, parameters, headers);
    }

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
    @Override
    public Response post(String url, RequestBody data){
        return post(url, data, null, null);
    }

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
    @Override
    public Response post(URL url, RequestBody data){
        return post(url, data, null, null);
    }

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
    @Override
    public Response post(String url, RequestBody data, List<Header> headers){
        return post(url, data, null, headers);
    }

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
    @Override
    public Response post(URL url, RequestBody data, List<Header> headers){
        return post(url, data, null, headers);
    }

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
    @Override
    public Response post(String url, RequestBody data, Map<String, Object> parameters){
        return post(url, data, parameters);
    }

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
    @Override
    public Response post(URL url, RequestBody data, Map<String, Object> parameters){
        return post(url, data, parameters);
    }

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
    @Override
    public Response post(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return post(url.toString(), data, parameters, headers);
    }

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response patch(String url){
        return patch(url, null, null, null);
    }

    /**
     * PATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response patch(URL url){
        return patch(url, null, null, null);
    }

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
    @Override
    public Response patch(String url, List<Header> headers){
        return patch(url, null, null, headers);
    }

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
    @Override
    public Response patch(URL url, List<Header> headers){
        return patch(url, null, null, headers);
    }

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
    @Override
    public Response patch(String url, Map<String, Object> parameters){
        return patch(url, null, parameters, null);
    }

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
    @Override
    public Response patch(URL url, Map<String, Object> parameters){
        return patch(url, null, parameters, null);
    }

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
    @Override
    public Response patch(String url, Map<String, Object> parameters, List<Header> headers){
        return patch(url, null, parameters, headers);
    }

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
    @Override
    public Response patch(URL url, Map<String, Object> parameters, List<Header> headers){
        return patch(url, null, parameters, headers);
    }

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
    @Override
    public Response patch(String url, RequestBody data){
        return patch(url, data, null, null);
    }

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
    @Override
    public Response patch(URL url, RequestBody data){
        return patch(url, data, null, null);
    }

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
    @Override
    public Response patch(String url, RequestBody data, List<Header> headers){
        return patch(url, data, null, headers);
    }

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
    @Override
    public Response patch(URL url, RequestBody data, List<Header> headers){
        return patch(url, data, null, headers);
    }

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
    @Override
    public Response patch(String url, RequestBody data, Map<String, Object> parameters){
        return patch(url, data, parameters, null);
    }

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
    @Override
    public Response patch(URL url, RequestBody data, Map<String, Object> parameters){
        return patch(url, data, parameters, null);
    }

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
    @Override
    public Response patch(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return patch(url.toString(), data, parameters, headers);
    }

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response put(String url){
        return put(url, null, null, null);
    }

    /**
     * PUT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response put(URL url){
        return put(url, null, null, null);
    }

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
    @Override
    public Response put(String url, List<Header> headers){
        return put(url, null, null, headers);
    }

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
    @Override
    public Response put(URL url, List<Header> headers){
        return put(url, null, null, headers);
    }

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
    @Override
    public Response put(String url, Map<String, Object> parameters){
        return put(url, null, parameters, null);
    }

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
    @Override
    public Response put(URL url, Map<String, Object> parameters){
        return put(url, null, parameters, null);
    }

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
    @Override
    public Response put(String url, Map<String, Object> parameters, List<Header> headers){
        return put(url, null, parameters, headers);
    }

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
    @Override
    public Response put(URL url, Map<String, Object> parameters, List<Header> headers){
        return put(url, null, parameters, headers);
    }

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
    @Override
    public Response put(String url, RequestBody data){
        return put(url, data, null, null);
    }

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
    @Override
    public Response put(URL url, RequestBody data){
        return put(url, data, null, null);
    }

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
    @Override
    public Response put(String url, RequestBody data, List<Header> headers){
        return put(url, data, null, headers);
    }

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
    @Override
    public Response put(URL url, RequestBody data, List<Header> headers){
        return put(url, data, null, headers);
    }

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
    @Override
    public Response put(String url, RequestBody data, Map<String, Object> parameters){
        return put(url, data, parameters, null);
    }

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
    @Override
    public Response put(URL url, RequestBody data, Map<String, Object> parameters){
        return put(url, data, parameters, null);
    }

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
    @Override
    public Response put(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return put(url.toString(), data, parameters, headers);
    }

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response delete(String url){
        return delete(url, null, null);
    }

    /**
     * DELETE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response delete(URL url){
        return delete(url, null, null);
    }

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
    @Override
    public Response delete(String url, List<Header> headers){
        return delete(url, null, headers);
    }

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
    @Override
    public Response delete(URL url, List<Header> headers){
        return delete(url, null, headers);
    }

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
    @Override
    public Response delete(String url, Map<String, Object> parameters){
        return delete(url, parameters, null);
    }

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
    @Override
    public Response delete(URL url, Map<String, Object> parameters){
        return delete(url, parameters, null);
    }

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
    @Override
    public Response delete(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return delete(url.toString(), parameters, headers);
    }

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response connect(String url){
        return connect(url, null, null);
    }

    /**
     * CONNECT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response connect(URL url){
        return connect(url, null, null);
    }

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
    @Override
    public Response connect(String url, List<Header> headers){
        return connect(url, null, headers);
    }

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
    @Override
    public Response connect(URL url, List<Header> headers){
        return connect(url, null, headers);
    }

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
    @Override
    public Response connect(String url, Map<String, Object> parameters){
        return connect(url, parameters, null);
    }

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
    @Override
    public Response connect(URL url, Map<String, Object> parameters){
        return connect(url, parameters, null);
    }

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
    @Override
    public Response connect(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return connect(url.toString(), parameters, headers);
    }

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response trace(String url){
        return trace(url, null, null);
    }

    /**
     * TRACE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response trace(URL url){
        return trace(url, null, null);
    }

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
    @Override
    public Response trace(String url, List<Header> headers){
        return trace(url, null, headers);
    }

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
    @Override
    public Response trace(URL url, List<Header> headers){
        return trace(url, null, headers);
    }

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
    @Override
    public Response trace(String url, Map<String, Object> parameters){
        return trace(url, parameters, null);
    }

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
    @Override
    public Response trace(URL url, Map<String, Object> parameters){
        return trace(url, parameters, null);
    }

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
    @Override
    public Response trace(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return trace(url.toString(), parameters, headers);
    }

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response copy(String url){
        return copy(url, null, null);
    }

    /**
     * COPY 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response copy(URL url){
        return copy(url, null, null);
    }

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
    @Override
    public Response copy(String url, List<Header> headers){
        return copy(url, null, headers);
    }

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
    @Override
    public Response copy(URL url, List<Header> headers){
        return copy(url, null, headers);
    }

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
    @Override
    public Response copy(String url, Map<String, Object> parameters){
        return copy(url, parameters, null);
    }

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
    @Override
    public Response copy(URL url, Map<String, Object> parameters){
        return copy(url, parameters, null);
    }

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
    @Override
    public Response copy(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return copy(url.toString(), parameters, headers);
    }

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response move(String url){
        return move(url, null, null);
    }

    /**
     * MOVE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response move(URL url){
        return move(url, null, null);
    }

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
    @Override
    public Response move(String url, List<Header> headers){
        return move(url, null, headers);
    }

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
    @Override
    public Response move(URL url, List<Header> headers){
        return move(url, null, headers);
    }

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
    @Override
    public Response move(String url, Map<String, Object> parameters){
        return move(url, parameters, null);
    }

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
    @Override
    public Response move(URL url, Map<String, Object> parameters){
        return move(url, parameters, null);
    }

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
    @Override
    public Response move(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return move(url.toString(), parameters, headers);
    }

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response head(String url){
        return head(url, null, null);
    }

    /**
     * HEAD 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response head(URL url){
        return head(url, null, null);
    }

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
    @Override
    public Response head(String url, List<Header> headers){
        return head(url, null, headers);
    }

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
    @Override
    public Response head(URL url, List<Header> headers){
        return head(url, null, headers);
    }

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
    @Override
    public Response head(String url, Map<String, Object> parameters){
        return head(url, parameters, null);
    }

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
    @Override
    public Response head(URL url, Map<String, Object> parameters){
        return head(url, parameters, null);
    }

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
    @Override
    public Response head(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return head(url.toString(), parameters, headers);
    }

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response options(String url){
        return options(url, null, null);
    }

    /**
     * OPTIONS 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response options(URL url){
        return options(url, null, null);
    }

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
    @Override
    public Response options(String url, List<Header> headers){
        return options(url, null, headers);
    }

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
    @Override
    public Response options(URL url, List<Header> headers){
        return options(url, null, headers);
    }

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
    @Override
    public Response options(String url, Map<String, Object> parameters){
        return options(url, parameters, null);
    }

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
    @Override
    public Response options(URL url, Map<String, Object> parameters){
        return options(url, parameters, null);
    }

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
    @Override
    public Response options(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return options(url.toString(), parameters, headers);
    }

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response link(String url){
        return link(url, null, null);
    }

    /**
     * LINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response link(URL url){
        return link(url, null, null);
    }

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
    @Override
    public Response link(String url, List<Header> headers){
        return link(url, null, headers);
    }

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
    @Override
    public Response link(URL url, List<Header> headers){
        return link(url, null, headers);
    }

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
    @Override
    public Response link(String url, Map<String, Object> parameters){
        return link(url, parameters, null);
    }

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
    @Override
    public Response link(URL url, Map<String, Object> parameters){
        return link(url, parameters, null);
    }

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
    @Override
    public Response link(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return link(url.toString(), parameters, headers);
    }

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response unlink(String url){
        return unlink(url, null, null);
    }

    /**
     * UNLINK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response unlink(URL url){
        return unlink(url, null, null);
    }

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
    @Override
    public Response unlink(String url, List<Header> headers){
        return unlink(url, null, headers);
    }

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
    @Override
    public Response unlink(URL url, List<Header> headers){
        return unlink(url, null, headers);
    }

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
    @Override
    public Response unlink(String url, Map<String, Object> parameters){
        return unlink(url, parameters, null);
    }

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
    @Override
    public Response unlink(URL url, Map<String, Object> parameters){
        return unlink(url, parameters, null);
    }

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
    @Override
    public Response unlink(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return unlink(url.toString(), parameters, headers);
    }

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response purge(String url){
        return purge(url, null, null);
    }

    /**
     * PURGE 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response purge(URL url){
        return purge(url, null, null);
    }

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
    @Override
    public Response purge(String url, List<Header> headers){
        return purge(url, null, headers);
    }

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
    @Override
    public Response purge(URL url, List<Header> headers){
        return purge(url, null, headers);
    }

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
    @Override
    public Response purge(String url, Map<String, Object> parameters){
        return purge(url, parameters, null);
    }

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
    @Override
    public Response purge(URL url, Map<String, Object> parameters){
        return purge(url, parameters, null);
    }

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
    @Override
    public Response purge(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return purge(url.toString(), parameters, headers);
    }

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response lock(String url){
        return lock(url, null, null);
    }

    /**
     * LOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response lock(URL url){
        return lock(url, null, null);
    }

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
    @Override
    public Response lock(String url, List<Header> headers){
        return lock(url, null, headers);
    }

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
    @Override
    public Response lock(URL url, List<Header> headers){
        return lock(url, null, headers);
    }

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
    @Override
    public Response lock(String url, Map<String, Object> parameters){
        return lock(url, parameters, null);
    }

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
    @Override
    public Response lock(URL url, Map<String, Object> parameters){
        return lock(url, parameters, null);
    }

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
    @Override
    public Response lock(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return lock(url.toString(), parameters, headers);
    }

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response unlock(String url){
        return unlock(url, null, null);
    }

    /**
     * UNLOCK 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response unlock(URL url){
        return unlock(url, null, null);
    }

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
    @Override
    public Response unlock(String url, List<Header> headers){
        return unlock(url, null, headers);
    }

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
    @Override
    public Response unlock(URL url, List<Header> headers){
        return unlock(url, null, headers);
    }

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
    @Override
    public Response unlock(String url, Map<String, Object> parameters){
        return unlock(url, parameters, null);
    }

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
    @Override
    public Response unlock(URL url, Map<String, Object> parameters){
        return unlock(url, parameters, null);
    }

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
    @Override
    public Response unlock(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return unlock(url.toString(), parameters, headers);
    }

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response propfind(String url){
        return propfind(url, null, null);
    }

    /**
     * PROPFIND 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response propfind(URL url){
        return propfind(url, null, null);
    }

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
    @Override
    public Response propfind(String url, List<Header> headers){
        return propfind(url, null, headers);
    }

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
    @Override
    public Response propfind(URL url, List<Header> headers){
        return propfind(url, null, headers);
    }

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
    @Override
    public Response propfind(String url, Map<String, Object> parameters){
        return propfind(url, parameters, null);
    }

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
    @Override
    public Response propfind(URL url, Map<String, Object> parameters){
        return propfind(url, parameters, null);
    }

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
    @Override
    public Response propfind(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return propfind(url.toString(), parameters, headers);
    }

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response proppatch(String url){
        return proppatch(url, null, null, null);
    }

    /**
     * PROPPATCH 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response proppatch(URL url){
        return proppatch(url, null, null, null);
    }

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
    @Override
    public Response proppatch(String url, List<Header> headers){
        return proppatch(url, null, null, headers);
    }

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
    @Override
    public Response proppatch(URL url, List<Header> headers){
        return proppatch(url, null, null, headers);
    }

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
    @Override
    public Response proppatch(String url, Map<String, Object> parameters){
        return proppatch(url, null, parameters, null);
    }

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
    @Override
    public Response proppatch(URL url, Map<String, Object> parameters){
        return proppatch(url, null, parameters, null);
    }

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
    @Override
    public Response proppatch(String url, Map<String, Object> parameters, List<Header> headers){
        return proppatch(url, null, parameters, headers);
    }

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
    @Override
    public Response proppatch(URL url, Map<String, Object> parameters, List<Header> headers){
        return proppatch(url, null, parameters, headers);
    }

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
    @Override
    public Response proppatch(String url, RequestBody data){
        return proppatch(url, data, null, null);
    }

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
    @Override
    public Response proppatch(URL url, RequestBody data){
        return proppatch(url, data, null, null);
    }

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
    @Override
    public Response proppatch(String url, RequestBody data, List<Header> headers){
        return proppatch(url, data, null, headers);
    }

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
    @Override
    public Response proppatch(URL url, RequestBody data, List<Header> headers){
        return proppatch(url, data, null, headers);
    }

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
    @Override
    public Response proppatch(String url, RequestBody data, Map<String, Object> parameters){
        return proppatch(url, data, parameters, null);
    }

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
    @Override
    public Response proppatch(URL url, RequestBody data, Map<String, Object> parameters){
        return proppatch(url, data, parameters, null);
    }

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
    @Override
    public Response proppatch(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return proppatch(url.toString(), data, parameters, headers);
    }

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response report(String url){
        return report(url, null, null, null);
    }

    /**
     * REPORT 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response report(URL url){
        return report(url, null, null, null);
    }

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
    @Override
    public Response report(String url, List<Header> headers){
        return report(url, null, null, headers);
    }

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
    @Override
    public Response report(URL url, List<Header> headers){
        return report(url, null, null, headers);
    }

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
    @Override
    public Response report(String url, Map<String, Object> parameters){
        return report(url, null, parameters, null);
    }

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
    @Override
    public Response report(URL url, Map<String, Object> parameters){
        return report(url, null, parameters, null);
    }

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
    @Override
    public Response report(String url, Map<String, Object> parameters, List<Header> headers){
        return report(url, null, parameters, headers);
    }

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
    @Override
    public Response report(URL url, Map<String, Object> parameters, List<Header> headers){
        return report(url, null, parameters, headers);
    }

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
    @Override
    public Response report(String url, RequestBody data){
        return report(url, data, null, null);
    }

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
    @Override
    public Response report(URL url, RequestBody data){
        return report(url, data, null, null);
    }

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
    @Override
    public Response report(String url, RequestBody data, List<Header> headers){
        return report(url, data, null, headers);
    }

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
    @Override
    public Response report(URL url, RequestBody data, List<Header> headers){
        return report(url, data, null, headers);
    }

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
    @Override
    public Response report(String url, RequestBody data, Map<String, Object> parameters){
        return report(url, data, parameters, null);
    }

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
    @Override
    public Response report(URL url, RequestBody data, Map<String, Object> parameters){
        return report(url, data, parameters, null);
    }

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
    @Override
    public Response report(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return report(url.toString(), data, parameters, headers);
    }

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response view(String url){
        return view(url, null, null);
    }

    /**
     * VIEW 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response view(URL url){
        return view(url, null, null);
    }

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
    @Override
    public Response view(String url, List<Header> headers){
        return view(url, null, headers);
    }

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
    @Override
    public Response view(URL url, List<Header> headers){
        return view(url, null, headers);
    }

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
    @Override
    public Response view(String url, Map<String, Object> parameters){
        return view(url, parameters, null);
    }

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
    @Override
    public Response view(URL url, Map<String, Object> parameters){
        return view(url, parameters, null);
    }

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
    @Override
    public Response view(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return view(url.toString(), parameters, headers);
    }

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response wrapped(String url){
        return wrapped(url, null, null);
    }

    /**
     * WRAPPED 请求
     *
     * @param url
     *         请求 URL
     *
     * @return Response {@link Response}
     */
    @Override
    public Response wrapped(URL url){
        return wrapped(url, null, null);
    }

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
    @Override
    public Response wrapped(String url, List<Header> headers){
        return wrapped(url, null, headers);
    }

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
    @Override
    public Response wrapped(URL url, List<Header> headers){
        return wrapped(url, null, headers);
    }

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
    @Override
    public Response wrapped(String url, Map<String, Object> parameters){
        return wrapped(url, parameters, null);
    }

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
    @Override
    public Response wrapped(URL url, Map<String, Object> parameters){
        return wrapped(url, parameters, null);
    }

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
    @Override
    public Response wrapped(URL url, Map<String, Object> parameters, List<Header> headers){
        validateURL(url);
        return wrapped(url.toString(), parameters, headers);
    }

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
    @Override
    public Response request(String url, RequestMethod requestMethod){
        return request(url, requestMethod, null, null, null);
    }

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
    @Override
    public Response request(URL url, RequestMethod requestMethod){
        return request(url, requestMethod, null, null, null);
    }

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
    @Override
    public Response request(String url, RequestMethod requestMethod, List<Header> headers){
        return request(url, requestMethod, null, null, headers);
    }

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
    @Override
    public Response request(URL url, RequestMethod requestMethod, List<Header> headers){
        return request(url, requestMethod, null, null, headers);
    }

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
    @Override
    public Response request(String url, RequestMethod requestMethod, Map<String, Object> parameters){
        return request(url, requestMethod, null, parameters, null);
    }

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
    @Override
    public Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters){
        return request(url, requestMethod, null, parameters, null);
    }

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
    @Override
    public Response request(String url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header>
            headers){
        return request(url, requestMethod, null, parameters, headers);
    }

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
    @Override
    public Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers){
        return request(url, requestMethod, null, parameters, headers);
    }

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
    @Override
    public Response request(String url, RequestMethod requestMethod, RequestBody data){
        return request(url, requestMethod, data, null, null);
    }

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
    @Override
    public Response request(URL url, RequestMethod requestMethod, RequestBody data){
        return request(url, requestMethod, data, null, null);
    }

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
    @Override
    public Response request(String url, RequestMethod requestMethod, RequestBody data, List<Header> headers){
        return request(url, requestMethod, data, null, headers);
    }

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
    @Override
    public Response request(URL url, RequestMethod requestMethod, RequestBody data, List<Header> headers){
        return request(url, requestMethod, data, null, headers);
    }

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
    @Override
    public Response request(String url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters){
        return request(url, requestMethod, data, parameters, null);
    }

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
    @Override
    public Response request(URL url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters){
        return request(url, requestMethod, data, parameters, null);
    }

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
    @Override
    public Response request(String url, RequestMethod requestMethod, RequestBody data, Map<String, Object>
            parameters, List<Header> headers){
        Assert.isBlank(url, "Request url could not be null or empty.");

        if(requestMethod == null){
            requestMethod = RequestMethod.GET;
        }

        if(requestMethod == RequestMethod.GET){
            return get(url, parameters, headers);
        }else if(requestMethod == RequestMethod.POST){
            return post(url, data, parameters, headers);
        }else if(requestMethod == RequestMethod.PUT){
            return put(url, data, parameters, headers);
        }else if(requestMethod == RequestMethod.PATCH){
            return patch(url, data, parameters, headers);
        }else if(requestMethod == RequestMethod.DELETE){
            return delete(url, parameters, headers);
        }else if(requestMethod == RequestMethod.CONNECT){
            return connect(url, parameters, headers);
        }else if(requestMethod == RequestMethod.TRACE){
            return trace(url, parameters, headers);
        }else if(requestMethod == RequestMethod.COPY){
            return copy(url, parameters, headers);
        }else if(requestMethod == RequestMethod.MOVE){
            return move(url, parameters, headers);
        }else if(requestMethod == RequestMethod.HEAD){
            return head(url, parameters, headers);
        }else if(requestMethod == RequestMethod.OPTIONS){
            return options(url, parameters, headers);
        }else if(requestMethod == RequestMethod.LINK){
            return link(url, parameters, headers);
        }else if(requestMethod == RequestMethod.UNLINK){
            return unlink(url, parameters, headers);
        }else if(requestMethod == RequestMethod.PURGE){
            return purge(url, parameters, headers);
        }else if(requestMethod == RequestMethod.LOCK){
            return lock(url, parameters, headers);
        }else if(requestMethod == RequestMethod.UNLOCK){
            return unlock(url, parameters, headers);
        }else if(requestMethod == RequestMethod.PROPFIND){
            return propfind(url, parameters, headers);
        }else if(requestMethod == RequestMethod.VIEW){
            return view(url, parameters, headers);
        }else if(requestMethod == RequestMethod.WRAPPED){
            return wrapped(url, parameters, headers);
        }

        return null;
    }

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
    @Override
    public Response request(URL url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters,
                            List<Header> headers){
        Assert.isNull(url, "Request url could not be null.");

        if(requestMethod == null){
            requestMethod = RequestMethod.GET;
        }

        if(requestMethod == RequestMethod.GET){
            return get(url, parameters, headers);
        }else if(requestMethod == RequestMethod.POST){
            return post(url, data, parameters, headers);
        }else if(requestMethod == RequestMethod.PUT){
            return put(url, data, parameters, headers);
        }else if(requestMethod == RequestMethod.PATCH){
            return patch(url, data, parameters, headers);
        }else if(requestMethod == RequestMethod.DELETE){
            return delete(url, parameters, headers);
        }else if(requestMethod == RequestMethod.CONNECT){
            return connect(url, parameters, headers);
        }else if(requestMethod == RequestMethod.TRACE){
            return trace(url, parameters, headers);
        }else if(requestMethod == RequestMethod.COPY){
            return copy(url, parameters, headers);
        }else if(requestMethod == RequestMethod.MOVE){
            return move(url, parameters, headers);
        }else if(requestMethod == RequestMethod.HEAD){
            return head(url, parameters, headers);
        }else if(requestMethod == RequestMethod.OPTIONS){
            return options(url, parameters, headers);
        }else if(requestMethod == RequestMethod.LINK){
            return link(url, parameters, headers);
        }else if(requestMethod == RequestMethod.UNLINK){
            return unlink(url, parameters, headers);
        }else if(requestMethod == RequestMethod.PURGE){
            return purge(url, parameters, headers);
        }else if(requestMethod == RequestMethod.LOCK){
            return lock(url, parameters, headers);
        }else if(requestMethod == RequestMethod.UNLOCK){
            return unlock(url, parameters, headers);
        }else if(requestMethod == RequestMethod.PROPFIND){
            return propfind(url, parameters, headers);
        }else if(requestMethod == RequestMethod.VIEW){
            return view(url, parameters, headers);
        }else if(requestMethod == RequestMethod.WRAPPED){
            return wrapped(url, parameters, headers);
        }

        return null;
    }

    protected final static void validateURL(final URL url){
        Assert.isNull(url, "Request URL cloud not be null.");
    }

}
