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
public abstract class AbstractHttpClient implements HttpClient {

    private ConnectionManager connectionManager;

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

    @Override
    public Response get(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return get(url, null, null);
    }

    @Override
    public Response get(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return get(url, null, null);
    }

    @Override
    public Response get(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return get(url, null, headers);
    }

    @Override
    public Response get(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return get(url, null, headers);
    }

    @Override
    public Response get(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return get(url, parameters, null);
    }

    @Override
    public Response get(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return get(url, parameters, null);
    }

    @Override
    public Response get(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return get(url.toString(), parameters, headers);
    }

    @Override
    public Response post(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return post(url, null, null, null);
    }

    @Override
    public Response post(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return post(url, null, null, null);
    }

    @Override
    public Response post(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return post(url, null, null, headers);
    }

    @Override
    public Response post(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return post(url, null, null, headers);
    }

    @Override
    public Response post(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return post(url, null, parameters, null);
    }

    @Override
    public Response post(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return post(url, null, parameters, null);
    }

    @Override
    public Response post(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return post(url, null, parameters, headers);
    }

    @Override
    public Response post(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return post(url, null, parameters, headers);
    }

    @Override
    public Response post(String url, RequestBody data) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return post(url, data, null, null);
    }

    @Override
    public Response post(URL url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return post(url, data, null, null);
    }

    @Override
    public Response post(String url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return post(url, data, null, headers);
    }

    @Override
    public Response post(URL url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return post(url, data, null, headers);
    }

    @Override
    public Response post(String url, RequestBody data, Map<String, Object> parameters) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return post(url, data, parameters);
    }

    @Override
    public Response post(URL url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return post(url, data, parameters);
    }

    @Override
    public Response post(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return post(url.toString(), data, parameters, headers);
    }

    @Override
    public Response patch(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return patch(url, null, null, null);
    }

    @Override
    public Response patch(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return patch(url, null, null, null);
    }

    @Override
    public Response patch(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return patch(url, null, null, headers);
    }

    @Override
    public Response patch(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return patch(url, null, null, headers);
    }

    @Override
    public Response patch(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return patch(url, null, parameters, null);
    }

    @Override
    public Response patch(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return patch(url, null, parameters, null);
    }

    @Override
    public Response patch(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return patch(url, null, parameters, headers);
    }

    @Override
    public Response patch(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return patch(url, null, parameters, headers);
    }

    @Override
    public Response patch(String url, RequestBody data) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return patch(url, data, null, null);
    }

    @Override
    public Response patch(URL url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return patch(url, data, null, null);
    }

    @Override
    public Response patch(String url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return patch(url, data, null, headers);
    }

    @Override
    public Response patch(URL url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return patch(url, data, null, headers);
    }

    @Override
    public Response patch(String url, RequestBody data, Map<String, Object> parameters) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return patch(url, data, parameters, null);
    }

    @Override
    public Response patch(URL url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return patch(url, data, parameters, null);
    }

    @Override
    public Response patch(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return patch(url.toString(), data, parameters, headers);
    }

    @Override
    public Response put(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, null, null, null);
    }

    @Override
    public Response put(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, null, null, null);
    }

    @Override
    public Response put(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, null, null, headers);
    }

    @Override
    public Response put(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, null, null, headers);
    }

    @Override
    public Response put(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, null, parameters, null);
    }

    @Override
    public Response put(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, null, parameters, null);
    }

    @Override
    public Response put(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return put(url, null, parameters, headers);
    }

    @Override
    public Response put(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return put(url, null, parameters, headers);
    }

    @Override
    public Response put(String url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, data, null, null);
    }

    @Override
    public Response put(URL url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, data, null, null);
    }

    @Override
    public Response put(String url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, data, null, headers);
    }

    @Override
    public Response put(URL url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, data, null, headers);
    }

    @Override
    public Response put(String url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, data, parameters, null);
    }

    @Override
    public Response put(URL url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return put(url, data, parameters, null);
    }

    @Override
    public Response put(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return put(url.toString(), data, parameters, headers);
    }

    @Override
    public Response delete(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return delete(url, null, null);
    }

    @Override
    public Response delete(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return delete(url, null, null);
    }

    @Override
    public Response delete(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return delete(url, null, headers);
    }

    @Override
    public Response delete(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return delete(url, null, headers);
    }

    @Override
    public Response delete(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return delete(url, parameters, null);
    }

    @Override
    public Response delete(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return delete(url, parameters, null);
    }

    @Override
    public Response delete(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return delete(url.toString(), parameters, headers);
    }

    @Override
    public Response connect(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return connect(url, null, null);
    }

    @Override
    public Response connect(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return connect(url, null, null);
    }

    @Override
    public Response connect(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return connect(url, null, headers);
    }

    @Override
    public Response connect(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return connect(url, null, headers);
    }

    @Override
    public Response connect(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return connect(url, parameters, null);
    }

    @Override
    public Response connect(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return connect(url, parameters, null);
    }

    @Override
    public Response connect(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return connect(url.toString(), parameters, headers);
    }

    @Override
    public Response trace(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return trace(url, null, null);
    }

    @Override
    public Response trace(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return trace(url, null, null);
    }

    @Override
    public Response trace(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return trace(url, null, headers);
    }

    @Override
    public Response trace(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return trace(url, null, headers);
    }

    @Override
    public Response trace(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return trace(url, parameters, null);
    }

    @Override
    public Response trace(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return trace(url, parameters, null);
    }

    @Override
    public Response trace(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return trace(url.toString(), parameters, headers);
    }

    @Override
    public Response copy(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return copy(url, null, null);
    }

    @Override
    public Response copy(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return copy(url, null, null);
    }

    @Override
    public Response copy(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return copy(url, null, headers);
    }

    @Override
    public Response copy(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return copy(url, null, headers);
    }

    @Override
    public Response copy(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return copy(url, parameters, null);
    }

    @Override
    public Response copy(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return copy(url, parameters, null);
    }

    @Override
    public Response copy(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return copy(url.toString(), parameters, headers);
    }

    @Override
    public Response move(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return move(url, null, null);
    }

    @Override
    public Response move(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return move(url, null, null);
    }

    @Override
    public Response move(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return move(url, null, headers);
    }

    @Override
    public Response move(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return move(url, null, headers);
    }

    @Override
    public Response move(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return move(url, parameters, null);
    }

    @Override
    public Response move(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return move(url, parameters, null);
    }

    @Override
    public Response move(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return move(url.toString(), parameters, headers);
    }

    @Override
    public Response head(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return head(url, null, null);
    }

    @Override
    public Response head(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return head(url, null, null);
    }

    @Override
    public Response head(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return head(url, null, headers);
    }

    @Override
    public Response head(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return head(url, null, headers);
    }

    @Override
    public Response head(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return head(url, parameters, null);
    }

    @Override
    public Response head(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return head(url, parameters, null);
    }

    @Override
    public Response head(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return head(url.toString(), parameters, headers);
    }

    @Override
    public Response options(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return options(url, null, null);
    }

    @Override
    public Response options(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return options(url, null, null);
    }

    @Override
    public Response options(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return options(url, null, headers);
    }

    @Override
    public Response options(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return options(url, null, headers);
    }

    @Override
    public Response options(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return options(url, parameters, null);
    }

    @Override
    public Response options(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return options(url, parameters, null);
    }

    @Override
    public Response options(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return options(url.toString(), parameters, headers);
    }

    @Override
    public Response link(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return link(url, null, null);
    }

    @Override
    public Response link(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return link(url, null, null);
    }

    @Override
    public Response link(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return link(url, null, headers);
    }

    @Override
    public Response link(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return link(url, null, headers);
    }

    @Override
    public Response link(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return link(url, parameters, null);
    }

    @Override
    public Response link(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return link(url, parameters, null);
    }

    @Override
    public Response link(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return link(url.toString(), parameters, headers);
    }

    @Override
    public Response unlink(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return unlink(url, null, null);
    }

    @Override
    public Response unlink(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return unlink(url, null, null);
    }

    @Override
    public Response unlink(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return unlink(url, null, headers);
    }

    @Override
    public Response unlink(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return unlink(url, null, headers);
    }

    @Override
    public Response unlink(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return unlink(url, parameters, null);
    }

    @Override
    public Response unlink(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return unlink(url, parameters, null);
    }

    @Override
    public Response unlink(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return unlink(url.toString(), parameters, headers);
    }

    @Override
    public Response purge(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return purge(url, null, null);
    }

    @Override
    public Response purge(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return purge(url, null, null);
    }

    @Override
    public Response purge(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return purge(url, null, headers);
    }

    @Override
    public Response purge(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return purge(url, null, headers);
    }

    @Override
    public Response purge(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return purge(url, parameters, null);
    }

    @Override
    public Response purge(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return purge(url, parameters, null);
    }

    @Override
    public Response purge(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return purge(url.toString(), parameters, headers);
    }

    @Override
    public Response lock(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return lock(url, null, null);
    }

    @Override
    public Response lock(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return lock(url, null, null);
    }

    @Override
    public Response lock(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return lock(url, null, headers);
    }

    @Override
    public Response lock(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return lock(url, null, headers);
    }

    @Override
    public Response lock(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return lock(url, parameters, null);
    }

    @Override
    public Response lock(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return lock(url, parameters, null);
    }

    @Override
    public Response lock(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return lock(url.toString(), parameters, headers);
    }

    @Override
    public Response unlock(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return unlock(url, null, null);
    }

    @Override
    public Response unlock(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return unlock(url, null, null);
    }

    @Override
    public Response unlock(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return unlock(url, null, headers);
    }

    @Override
    public Response unlock(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return unlock(url, null, headers);
    }

    @Override
    public Response unlock(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return unlock(url, parameters, null);
    }

    @Override
    public Response unlock(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return unlock(url, parameters, null);
    }

    @Override
    public Response unlock(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return unlock(url.toString(), parameters, headers);
    }

    @Override
    public Response propfind(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return propfind(url, null, null);
    }

    @Override
    public Response propfind(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return propfind(url, null, null);
    }

    @Override
    public Response propfind(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return propfind(url, null, headers);
    }

    @Override
    public Response propfind(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return propfind(url, null, headers);
    }

    @Override
    public Response propfind(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return propfind(url, parameters, null);
    }

    @Override
    public Response propfind(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return propfind(url, parameters, null);
    }

    @Override
    public Response propfind(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return propfind(url.toString(), parameters, headers);
    }

    @Override
    public Response proppatch(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return proppatch(url, null, null, null);
    }

    @Override
    public Response proppatch(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return proppatch(url, null, null, null);
    }

    @Override
    public Response proppatch(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return proppatch(url, null, null, headers);
    }

    @Override
    public Response proppatch(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return proppatch(url, null, null, headers);
    }

    @Override
    public Response proppatch(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return proppatch(url, null, parameters, null);
    }

    @Override
    public Response proppatch(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return proppatch(url, null, parameters, null);
    }

    @Override
    public Response proppatch(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return proppatch(url, null, parameters, headers);
    }

    @Override
    public Response proppatch(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return proppatch(url, null, parameters, headers);
    }

    @Override
    public Response proppatch(String url, RequestBody data) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return proppatch(url, data, null, null);
    }

    @Override
    public Response proppatch(URL url, RequestBody data) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return proppatch(url, data, null, null);
    }

    @Override
    public Response proppatch(String url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return proppatch(url, data, null, headers);
    }

    @Override
    public Response proppatch(URL url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return proppatch(url, data, null, headers);
    }

    @Override
    public Response proppatch(String url, RequestBody data, Map<String, Object> parameters) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return proppatch(url, data, parameters, null);
    }

    @Override
    public Response proppatch(URL url, RequestBody data, Map<String, Object> parameters) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return proppatch(url, data, parameters, null);
    }

    @Override
    public Response proppatch(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return proppatch(url.toString(), data, parameters, headers);
    }

    @Override
    public Response report(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return report(url, null, null, null);
    }

    @Override
    public Response report(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return report(url, null, null, null);
    }

    @Override
    public Response report(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return report(url, null, null, headers);
    }

    @Override
    public Response report(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return report(url, null, null, headers);
    }

    @Override
    public Response report(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return report(url, null, parameters, null);
    }

    @Override
    public Response report(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return report(url, null, parameters, null);
    }

    @Override
    public Response report(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return report(url, null, parameters, headers);
    }

    @Override
    public Response report(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return report(url, null, parameters, headers);
    }

    @Override
    public Response report(String url, RequestBody data) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return report(url, data, null, null);
    }

    @Override
    public Response report(URL url, RequestBody data) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return report(url, data, null, null);
    }

    @Override
    public Response report(String url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return report(url, data, null, headers);
    }

    @Override
    public Response report(URL url, RequestBody data, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return report(url, data, null, headers);
    }

    @Override
    public Response report(String url, RequestBody data, Map<String, Object> parameters) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return report(url, data, parameters, null);
    }

    @Override
    public Response report(URL url, RequestBody data, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return report(url, data, parameters, null);
    }

    @Override
    public Response report(URL url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return report(url.toString(), data, parameters, headers);
    }

    @Override
    public Response view(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return view(url, null, null);
    }

    @Override
    public Response view(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return view(url, null, null);
    }

    @Override
    public Response view(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return view(url, null, headers);
    }

    @Override
    public Response view(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return view(url, null, headers);
    }

    @Override
    public Response view(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return view(url, parameters, null);
    }

    @Override
    public Response view(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return view(url, parameters, null);
    }

    @Override
    public Response view(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return view(url.toString(), parameters, headers);
    }

    @Override
    public Response wrapped(String url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return wrapped(url, null, null);
    }

    @Override
    public Response wrapped(URL url) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        return wrapped(url, null, null);
    }

    @Override
    public Response wrapped(String url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return wrapped(url, null, headers);
    }

    @Override
    public Response wrapped(URL url, List<Header> headers) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return wrapped(url, null, headers);
    }

    @Override
    public Response wrapped(String url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return wrapped(url, parameters, null);
    }

    @Override
    public Response wrapped(URL url, Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return wrapped(url, parameters, null);
    }

    @Override
    public Response wrapped(URL url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        validateURL(url);
        return wrapped(url.toString(), parameters, headers);
    }

    @Override
    public Response request(String url, RequestMethod requestMethod) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return request(url, requestMethod, null, null, null);
    }

    @Override
    public Response request(URL url, RequestMethod requestMethod) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return request(url, requestMethod, null, null, null);
    }

    @Override
    public Response request(String url, RequestMethod requestMethod, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return request(url, requestMethod, null, null, headers);
    }

    @Override
    public Response request(URL url, RequestMethod requestMethod, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return request(url, requestMethod, null, null, headers);
    }

    @Override
    public Response request(String url, RequestMethod requestMethod, Map<String, Object> parameters) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return request(url, requestMethod, null, parameters, null);
    }

    @Override
    public Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return request(url, requestMethod, null, parameters, null);
    }

    @Override
    public Response request(String url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header>
            headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException{
        return request(url, requestMethod, null, parameters, headers);
    }

    @Override
    public Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header>
            headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException{
        return request(url, requestMethod, null, parameters, headers);
    }

    @Override
    public Response request(String url, RequestMethod requestMethod, RequestBody data) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return request(url, requestMethod, data, null, null);
    }

    @Override
    public Response request(URL url, RequestMethod requestMethod, RequestBody data) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        return request(url, requestMethod, data, null, null);
    }

    @Override
    public Response request(String url, RequestMethod requestMethod, RequestBody data, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return request(url, requestMethod, data, null, headers);
    }

    @Override
    public Response request(URL url, RequestMethod requestMethod, RequestBody data, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return request(url, requestMethod, data, null, headers);
    }

    @Override
    public Response request(String url, RequestMethod requestMethod, RequestBody data, Map<String, Object>
            parameters) throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException{
        return request(url, requestMethod, data, parameters, null);
    }

    @Override
    public Response request(URL url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters)
            throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException{
        return request(url, requestMethod, data, parameters, null);
    }

    @Override
    public Response request(String url, RequestMethod requestMethod, RequestBody data, Map<String, Object>
            parameters, List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
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

    @Override
    public Response request(URL url, RequestMethod requestMethod, RequestBody data, Map<String, Object> parameters,
                            List<Header> headers) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
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
