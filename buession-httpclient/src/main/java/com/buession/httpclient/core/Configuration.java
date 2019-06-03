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
package com.buession.httpclient.core;

/**
 * HTTP 连接配置
 *
 * @author Yong.Teng
 */
public class Configuration {

    /**
     * 最大连接数
     */
    private int maxTotal = 5000;

    /**
     * 每个路由的最大连接数
     */
    private int defaultMaxPerRoute = 500;

    /**
     * 连接超时时间，单位：毫秒
     */
    private int connectTimeout = 3000;

    /**
     * 从连接池获取连接的超时时间，单位：毫秒
     */
    private int connectionRequestTimeout = 5000;

    /**
     * 读取超时时间，单位：毫秒
     */
    private int readTimeout = 3000;

    /**
     * 是否允许重定向
     */
    private boolean allowRedirects;

    private boolean relativeRedirectsAllowed;

    private boolean circularRedirectsAllowed;

    /**
     * 最大允许重定向次数
     */
    private int maxRedirects;

    /**
     * 是否开启 Http Basic 认证
     */
    private boolean authenticationEnabled;

    /**
     * 是否启用内容压缩
     */
    private boolean contentCompressionEnabled;

    private boolean normalizeUri;

    /**
     * 获取最大连接数
     *
     * @return 最大连接数
     */
    public int getMaxTotal(){
        return maxTotal;
    }

    /**
     * 设置最大连接数
     *
     * @param maxTotal
     *         最大连接数
     */
    public void setMaxTotal(int maxTotal){
        this.maxTotal = maxTotal;
    }

    /**
     * 获取每个路由的最大连接数
     *
     * @return 每个路由的最大连接数
     */
    public int getDefaultMaxPerRoute(){
        return defaultMaxPerRoute;
    }

    /**
     * 设置每个路由的最大连接数
     *
     * @param defaultMaxPerRoute
     *         每个路由的最大连接数
     */
    public void setDefaultMaxPerRoute(int defaultMaxPerRoute){
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    /**
     * 获取连接超时时间，单位：毫秒
     *
     * @return 连接超时时间
     */
    public int getConnectTimeout(){
        return connectTimeout;
    }

    /**
     * 设置连接超时时间
     *
     * @param connectTimeout
     *         连接超时时间，单位：毫秒
     */
    public void setConnectTimeout(int connectTimeout){
        this.connectTimeout = connectTimeout;
    }

    /**
     * 获取从连接池获取连接的超时时间，单位：毫秒
     *
     * @return 从连接池获取连接的超时时间
     */
    public int getConnectionRequestTimeout(){
        return connectionRequestTimeout;
    }

    /**
     * 设置从连接池获取连接的超时时间
     *
     * @param connectionRequestTimeout
     *         从连接池获取连接的超时时间，单位：毫秒
     */
    public void setConnectionRequestTimeout(int connectionRequestTimeout){
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    /**
     * 获取读取超时时间，单位：毫秒
     *
     * @return 读取超时时间
     */
    public int getReadTimeout(){
        return readTimeout;
    }

    /**
     * 设置读取超时时间，单位：毫秒
     *
     * @param readTimeout
     *         读取超时时间，单位：毫秒
     */
    public void setReadTimeout(int readTimeout){
        this.readTimeout = readTimeout;
    }

    /**
     * 获取是否允许重定向
     *
     * @return 是否允许重定向
     */
    public boolean isAllowRedirects(){
        return getAllowRedirects();
    }

    /**
     * 获取是否允许重定向
     *
     * @return 是否允许重定向
     */
    public boolean getAllowRedirects(){
        return allowRedirects;
    }

    /**
     * 设置是否允许重定向
     *
     * @param allowRedirects
     *         是否允许重定向
     */
    public void setAllowRedirects(boolean allowRedirects){
        this.allowRedirects = allowRedirects;
    }

    public boolean isRelativeRedirectsAllowed(){
        return getRelativeRedirectsAllowed();
    }

    public boolean getRelativeRedirectsAllowed(){
        return relativeRedirectsAllowed;
    }

    public void setRelativeRedirectsAllowed(boolean relativeRedirectsAllowed){
        this.relativeRedirectsAllowed = relativeRedirectsAllowed;
    }

    public boolean isCircularRedirectsAllowed(){
        return getCircularRedirectsAllowed();
    }

    public boolean getCircularRedirectsAllowed(){
        return circularRedirectsAllowed;
    }

    public void setCircularRedirectsAllowed(boolean circularRedirectsAllowed){
        this.circularRedirectsAllowed = circularRedirectsAllowed;
    }

    /**
     * 获取最大允许重定向次数
     *
     * @return 最大允许重定向次数
     */
    public int getMaxRedirects(){
        return maxRedirects;
    }

    /**
     * 设置最大允许重定向次数
     *
     * @param maxRedirects
     *         最大允许重定向次数
     */
    public void setMaxRedirects(int maxRedirects){
        this.maxRedirects = maxRedirects;
    }

    /**
     * 获取是否开启 Http Basic 认证
     *
     * @return 是否开启 Http Basic 认证
     */
    public boolean isAuthenticationEnabled(){
        return getAuthenticationEnabled();
    }

    /**
     * 获取是否开启 Http Basic 认证
     *
     * @return 是否开启 Http Basic 认证
     */
    public boolean getAuthenticationEnabled(){
        return authenticationEnabled;
    }

    /**
     * 设置是否开启 Http Basic 认证
     *
     * @param authenticationEnabled
     *         是否开启 Http Basic 认证
     */
    public void setAuthenticationEnabled(boolean authenticationEnabled){
        this.authenticationEnabled = authenticationEnabled;
    }

    /**
     * 获取是否启用内容压缩
     *
     * @return 是否启用内容压缩
     */
    public boolean isContentCompressionEnabled(){
        return getContentCompressionEnabled();
    }

    /**
     * 获取是否启用内容压缩
     *
     * @return 是否启用内容压缩
     */
    public boolean getContentCompressionEnabled(){
        return contentCompressionEnabled;
    }

    /**
     * 设置是否启用内容压缩
     *
     * @param contentCompressionEnabled
     *         是否启用内容压缩
     */
    public void setContentCompressionEnabled(boolean contentCompressionEnabled){
        this.contentCompressionEnabled = contentCompressionEnabled;
    }

    public boolean isNormalizeUri(){
        return getNormalizeUri();
    }

    public boolean getNormalizeUri(){
        return normalizeUri;
    }

    public void setNormalizeUri(boolean normalizeUri){
        this.normalizeUri = normalizeUri;
    }
}
