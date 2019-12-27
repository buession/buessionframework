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
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.ConnectTimeoutException;
import com.buession.httpclient.exception.ConnectionPoolTimeoutException;
import com.buession.httpclient.exception.ReadTimeoutException;
import com.buession.httpclient.exception.RequestAbortedException;
import com.buession.httpclient.exception.RequestException;
import com.buession.httpclient.okhttp.OkHttpRequestBuilder;
import com.buession.httpclient.okhttp.OkHttpResponseBuilder;
import com.buession.httpclient.okhttp.RequestBuilder;
import okhttp3.Headers;
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
        return doRequest(new RequestBuilder(url).get(), headers, parameters);
    }

    @Override
    public Response post(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).post(data), headers, parameters);
    }

    @Override
    public Response patch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).patch(data), headers, parameters);
    }

    @Override
    public Response put(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).put(data), headers, parameters);
    }

    @Override
    public Response delete(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).delete(), headers, parameters);
    }

    @Override
    public Response connect(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).connect(), headers, parameters);
    }

    @Override
    public Response trace(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).trace(), headers, parameters);
    }

    @Override
    public Response copy(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).copy(), headers, parameters);
    }

    @Override
    public Response move(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).move(), headers, parameters);
    }

    @Override
    public Response head(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).head(), headers, parameters);
    }

    @Override
    public Response options(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).options(), headers, parameters);
    }

    @Override
    public Response link(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).link(), headers, parameters);
    }

    @Override
    public Response unlink(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).unlink(), headers, parameters);
    }

    @Override
    public Response purge(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).purge(), headers, parameters);
    }

    @Override
    public Response lock(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).lock(), headers, parameters);
    }

    @Override
    public Response unlock(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).unlock(), headers, parameters);
    }

    @Override
    public Response propfind(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).propfind(), headers, parameters);
    }

    @Override
    public Response proppatch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers)
            throws ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException,
            RequestAbortedException, RequestException{
        return doRequest(new RequestBuilder(url).proppatch(data), headers, parameters);
    }

    @Override
    public Response report(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).report(data), headers, parameters);
    }

    @Override
    public Response view(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).view(), headers, parameters);
    }

    @Override
    public Response wrapped(String url, Map<String, Object> parameters, List<Header> headers) throws
            ConnectTimeoutException, ConnectionPoolTimeoutException, ReadTimeoutException, RequestAbortedException,
            RequestException{
        return doRequest(new RequestBuilder(url).wrapped(), headers, parameters);
    }

    protected Response doRequest(final okhttp3.Request.Builder requestBuilder, final List<Header> headers, final
    Map<String, Object> parameters) throws ConnectTimeoutException, ConnectionPoolTimeoutException,
            ReadTimeoutException, RequestAbortedException, RequestException{
        final Configuration configuration = getConnectionManager().getConfiguration();
        final Request request = OkHttpRequestBuilder.create().setParameters(parameters).setHeaders(headers).build();
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

        okhttp3.Request okHttpRequest = requestBuilder.headers(headersBuilder.build()).build();
        okhttp3.Response httpResponse = null;

        request.setUrl(okHttpRequest.url().toString());

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

}
