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
import com.buession.httpclient.conn.OkHttpClientConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Request;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.ConnectTimeoutException;
import com.buession.httpclient.exception.ConnectionPoolTimeoutException;
import com.buession.httpclient.exception.ReadTimeoutException;
import com.buession.httpclient.exception.RequestAbortedException;
import com.buession.httpclient.exception.RequestException;
import com.buession.httpclient.okhttp.OkHttpRequestBuilder;
import com.buession.httpclient.okhttp.OkHttpResponseBuilder;
import okhttp3.Headers;
import okhttp3.internal.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Yong.Teng
 */
public class OkHttpClient extends AbstractHttpClient {

    private okhttp3.OkHttpClient httpClient;

    private final static Logger logger = LoggerFactory.getLogger(OkHttpClient.class);

    /**
     * 构造函数
     */
    public OkHttpClient(){
        super();
        setConnectionManager(new OkHttpClientConnectionManager());
    }

    /**
     * 构造函数
     *
     * @param connectionManager
     *         连接管理器
     */
    public OkHttpClient(ConnectionManager connectionManager){
        super(connectionManager);
    }

    public okhttp3.OkHttpClient getHttpClient(){
        return httpClient;
    }

    public void setHttpClient(okhttp3.OkHttpClient httpClient){
        this.httpClient = httpClient;
    }

    @Override
    public Response get(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new okhttp3.Request.Builder().get(), url, headers, parameters);
    }

    @Override
    public Response post(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        final okhttp3.RequestBody requestBody = OkHttpRequestBuilder.buildRequestBody(data);
        return doRequest((new okhttp3.Request.Builder()).post(requestBody), url, headers, parameters);
    }

    @Override
    public Response patch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        final okhttp3.RequestBody requestBody = OkHttpRequestBuilder.buildRequestBody(data);
        return doRequest((new okhttp3.Request.Builder()).patch(requestBody), url, headers, parameters);
    }

    @Override
    public Response put(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        final okhttp3.RequestBody requestBody = OkHttpRequestBuilder.buildRequestBody(data);
        return doRequest((new okhttp3.Request.Builder()).put(requestBody), url, headers, parameters);
    }

    @Override
    public Response delete(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new okhttp3.Request.Builder().delete(), url, headers, parameters);
    }

    @Override
    public Response connect(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.CONNECT), url, headers, parameters);
    }

    @Override
    public Response trace(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.TRACE), url, headers, parameters);
    }

    @Override
    public Response copy(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.COPY), url, headers, parameters);
    }

    @Override
    public Response move(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.MOVE), url, headers, parameters);
    }

    @Override
    public Response head(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new okhttp3.Request.Builder().head(), url, headers, parameters);
    }

    @Override
    public Response options(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.OPTIONS), url, headers, parameters);
    }

    @Override
    public Response link(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.LINK), url, headers, parameters);
    }

    @Override
    public Response unlink(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.UNLINK), url, headers, parameters);
    }

    @Override
    public Response purge(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.PURGE), url, headers, parameters);
    }

    @Override
    public Response lock(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.LOCK), url, headers, parameters);
    }

    @Override
    public Response unlock(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.UNLOCK), url, headers, parameters);
    }

    @Override
    public Response propfind(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.PROPFIND), url, headers, parameters);
    }

    @Override
    public Response proppatch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers)
            throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException{
        final okhttp3.RequestBody requestBody = OkHttpRequestBuilder.buildRequestBody(data);
        return doRequest(createNoneRequestBuilder(RequestMethod.PROPPATCH, requestBody), url, headers, parameters);
    }

    @Override
    public Response report(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        final okhttp3.RequestBody requestBody = OkHttpRequestBuilder.buildRequestBody(data);
        return doRequest(createNoneRequestBuilder(RequestMethod.REPORT, requestBody), url, headers, parameters);
    }

    @Override
    public Response view(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.VIEW), url, headers, parameters);
    }

    @Override
    public Response wrapped(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(createNoneRequestBuilder(RequestMethod.WRAPPED), url, headers, parameters);
    }

    protected Response doRequest(final okhttp3.Request.Builder requestBuilder, final String url, final List<Header>
            headers, final Map<String, Object> parameters) throws ConnectTimeoutException,
            ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException, RequestException{
        final Configuration configuration = getConnectionManager().getConfiguration();
        final Request request = OkHttpRequestBuilder.create().setUrl(url).setParameters(parameters).setHeaders
                (headers).build();
        final Headers.Builder headersBuilder = new Headers.Builder();

        if(httpClient == null){
            com.buession.httpclient.okhttp.OkHttpClientConnectionManager okHttpClientConnectionManager = (
                    (OkHttpClientConnectionManager) getConnectionManager()).getClientConnectionManager();

            httpClient = new okhttp3.OkHttpClient.Builder().connectTimeout(configuration.getConnectTimeout(),
                    TimeUnit.MILLISECONDS).readTimeout(configuration.getReadTimeout(), TimeUnit.MILLISECONDS)
                    .followRedirects(configuration.getAllowRedirects()).connectionPool(okHttpClientConnectionManager
                            .getConnectionPool()).build();
        }

        if(request.getHeaders() != null){
            for(Header header : request.getHeaders()){
                headersBuilder.add(header.getName(), header.getValue());
            }
        }

        okhttp3.Request okHttpRequest = requestBuilder.url(request.getUrl()).headers(headersBuilder.build()).build();
        okhttp3.Response httpResponse = null;

        try{
            httpResponse = httpClient.newCall(okHttpRequest).execute();

            return OkHttpResponseBuilder.create(httpResponse).build();
        }catch(IOException e){
            logger.error("Request({}) url: {} error.", okHttpRequest.method(), request.getUrl(), e);

            if(e instanceof SocketTimeoutException){
                String message = e.getMessage();

                if(message.contains("connect timed out")){
                    throw new ConnectTimeoutException(e.getMessage());
                }else if(message.contains("Read timed out")){
                    throw new ReadTimeoutException(e.getMessage());
                }else{
                    throw new RequestException(e.getMessage(), e);
                }
            }else{
                throw new RequestException(e.getMessage(), e);
            }
        }finally{
            if(httpResponse != null){
                httpResponse.close();
            }
        }
    }

    protected static okhttp3.Request.Builder createNoneRequestBuilder(final RequestMethod requestMethod){
        return new okhttp3.Request.Builder().method(requestMethod.name(), Util.EMPTY_REQUEST);
    }

    protected static okhttp3.Request.Builder createNoneRequestBuilder(final RequestMethod requestMethod, final
    okhttp3.RequestBody body){
        return new okhttp3.Request.Builder().method(requestMethod.name(), body);
    }
}
