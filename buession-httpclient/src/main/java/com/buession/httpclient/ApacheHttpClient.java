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

import com.buession.httpclient.conn.ApacheClientConnectionManager;
import com.buession.httpclient.conn.ConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Request;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.httpcomponents.HttpComponentsRequestBuilder;
import com.buession.httpclient.httpcomponents.HttpComponentsResponseBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class ApacheHttpClient extends AbstractHttpClient {

    private RequestConfig requestConfig;

    private org.apache.http.client.HttpClient httpClient;

    private final static Logger logger = LoggerFactory.getLogger(ApacheHttpClient.class);

    /**
     * 构造函数
     */
    public ApacheHttpClient(){
        super();
        setConnectionManager(new ApacheClientConnectionManager());
    }

    /**
     * 构造函数
     *
     * @param httpClientConnectionManager
     *         连接管理器
     */
    public ApacheHttpClient(ConnectionManager httpClientConnectionManager){
        super(httpClientConnectionManager);
    }

    public RequestConfig getRequestConfig(){
        if(requestConfig == null){
            Configuration configuration = getConnectionManager().getConfiguration();
            requestConfig = RequestConfig.custom().setConnectTimeout(configuration.getConnectTimeout())
                    .setConnectionRequestTimeout(configuration.getConnectionRequestTimeout()).setSocketTimeout
                            (configuration.getReadTimeout()).setRedirectsEnabled(configuration.isAllowRedirects())
                    .setCircularRedirectsAllowed(configuration.isCircularRedirectsAllowed())
                    .setRelativeRedirectsAllowed(configuration.isRelativeRedirectsAllowed()).setMaxRedirects
                            (configuration.getMaxRedirects()).setAuthenticationEnabled(configuration
                            .isAuthenticationEnabled()).setContentCompressionEnabled(configuration
                            .isContentCompressionEnabled()).setNormalizeUri(configuration.isNormalizeUri()).build();
        }

        return requestConfig;
    }

    public void setRequestConfig(RequestConfig requestConfig){
        this.requestConfig = requestConfig;
    }

    public org.apache.http.client.HttpClient getHttpClient(){
        if(httpClient == null){
            httpClient = HttpClientBuilder.create().setConnectionManager((HttpClientConnectionManager)
                    getConnectionManager().getClientConnectionManager()).build();
        }

        return httpClient;
    }

    public void setHttpClient(org.apache.http.client.HttpClient httpClient){
        this.httpClient = httpClient;
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
    public Response get(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(new HttpGet(), url, headers, parameters);
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
    public Response post(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers){
        final HttpPost httpPost = new HttpPost();

        httpPost.setEntity(HttpComponentsRequestBuilder.buildRequestBody(data));

        return doRequest(httpPost, url, headers, parameters);
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
    public Response patch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers){
        final HttpPatch httpPatch = new HttpPatch();

        httpPatch.setEntity(HttpComponentsRequestBuilder.buildRequestBody(data));

        return doRequest(httpPatch, url, headers, parameters);
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
    public Response put(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers){
        final HttpPut httpPut = new HttpPut();

        httpPut.setEntity(HttpComponentsRequestBuilder.buildRequestBody(data));

        return doRequest(httpPut, url, headers, parameters);
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
    public Response delete(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(new HttpDelete(), url, headers, parameters);
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
    public Response connect(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(createNoneHttpRequest(RequestMethod.CONNECT), url, headers, parameters);
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
    public Response trace(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(new HttpTrace(), url, headers, parameters);
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
    public Response copy(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(createNoneHttpRequest(RequestMethod.COPY), url, headers, parameters);
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
    public Response move(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(createNoneHttpRequest(RequestMethod.MOVE), url, headers, parameters);
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
    public Response head(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(new HttpHead(), url, headers, parameters);
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
    public Response options(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(new HttpOptions(), url, headers, parameters);
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
    public Response link(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(createNoneHttpRequest(RequestMethod.LINK), url, headers, parameters);
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
    public Response unlink(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(createNoneHttpRequest(RequestMethod.UNLINK), url, headers, parameters);
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
    public Response purge(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(createNoneHttpRequest(RequestMethod.PURGE), url, headers, parameters);
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
    public Response lock(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(createNoneHttpRequest(RequestMethod.LOCK), url, headers, parameters);
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
    public Response unlock(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(createNoneHttpRequest(RequestMethod.UNLOCK), url, headers, parameters);
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
    public Response propfind(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(createNoneHttpRequest(RequestMethod.PROPFIND), url, headers, parameters);
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
    public Response proppatch(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers){
        final HttpRequestBase httpRequestBase = createNoneHttpRequest(RequestMethod.PROPPATCH,
                HttpComponentsRequestBuilder.buildRequestBody(data));
        return doRequest(httpRequestBase, url, headers, parameters);
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
    public Response report(String url, RequestBody data, Map<String, Object> parameters, List<Header> headers){
        final HttpRequestBase httpRequestBase = createNoneHttpRequest(RequestMethod.REPORT,
                HttpComponentsRequestBuilder.buildRequestBody(data));
        return doRequest(httpRequestBase, url, headers, parameters);
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
    public Response view(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(createNoneHttpRequest(RequestMethod.VIEW), url, headers, parameters);
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
    public Response wrapped(String url, Map<String, Object> parameters, List<Header> headers){
        return doRequest(createNoneHttpRequest(RequestMethod.WRAPPED), url, headers, parameters);
    }

    protected Response doRequest(final HttpRequestBase httpRequest, final String url, final List<Header> headers,
                                 final Map<String, Object> parameters){
        final Request request = HttpComponentsRequestBuilder.create(httpRequest).setUrl(url).setParameters
                (parameters).setHeaders(headers).build();

        httpRequest.setConfig(getRequestConfig());

        try{
            httpRequest.setURI(new URI(request.getUrl()));
        }catch(URISyntaxException e){
            logger.error("URL [{}] create {} error: {}.", request.getUrl(), URI.class.getName(), e.getMessage());
        }

        if(request.getHeaders() != null){
            for(Header header : request.getHeaders()){
                httpRequest.setHeader(header.getName(), header.getValue());
            }
        }

        try{
            HttpResponse httpResponse = getHttpClient().execute(httpRequest);

            return HttpComponentsResponseBuilder.create(httpResponse).build();
        }catch(IOException e){
            logger.error("Request({}) url: {} error.", httpRequest.getMethod(), httpRequest.getURI(), e);
        }finally{
            httpRequest.releaseConnection();
        }

        return null;
    }

    protected static HttpRequestBase createNoneHttpRequest(final RequestMethod requestMethod){
        return new HttpRequestBase() {

            @Override
            public String getMethod(){
                return requestMethod.name();
            }
        };
    }

    protected static HttpRequestBase createNoneHttpRequest(final RequestMethod requestMethod, final HttpEntity entity){
        return new HttpEntityEnclosingRequestBase() {

            @Override
            public String getMethod(){
                return requestMethod.name();
            }

            @Override
            public HttpEntity getEntity(){
                return entity;
            }
        };
    }

}
