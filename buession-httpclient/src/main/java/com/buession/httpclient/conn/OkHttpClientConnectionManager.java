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
package com.buession.httpclient.conn;

import com.buession.httpclient.core.Configuration;
import okhttp3.ConnectionPool;

import java.util.concurrent.TimeUnit;

/**
 * okhttp 连接管理器
 *
 * @author Yong.Teng
 */
public class OkHttpClientConnectionManager extends AbstractConnectionManager<com.buession.httpclient.okhttp
        .OkHttpClientConnectionManager> {

    /**
     * 构造函数，创建驱动默认连接管理器
     */
    public OkHttpClientConnectionManager(){
        super();
    }

    /**
     * 构造函数，创建驱动默认连接管理器
     *
     * @param configuration
     *         连接对象
     */
    public OkHttpClientConnectionManager(Configuration configuration){
        super(configuration);
    }

    /**
     * 构造函数
     *
     * @param clientConnectionManager
     *         驱动连接管理器
     */
    public OkHttpClientConnectionManager(com.buession.httpclient.okhttp.OkHttpClientConnectionManager
                                                 clientConnectionManager){
        super(clientConnectionManager);
    }

    /**
     * 构造函数
     *
     * @param configuration
     *         连接对象
     * @param clientConnectionManager
     *         驱动连接管理器
     */
    public OkHttpClientConnectionManager(Configuration configuration, com.buession.httpclient.okhttp
            .OkHttpClientConnectionManager clientConnectionManager){
        super(configuration, clientConnectionManager);
    }

    /**
     * 创建驱动默认连接管理器
     *
     * @return 连接管理器
     */
    @Override
    public com.buession.httpclient.okhttp.OkHttpClientConnectionManager createDefaultClientConnectionManager(){
        com.buession.httpclient.okhttp.OkHttpClientConnectionManager connectionManager = new com.buession.httpclient
                .okhttp.OkHttpClientConnectionManager();

        connectionManager.setConnectionPool(new ConnectionPool(getConfiguration().getMaxConnections(),
                getConfiguration().getIdleConnectionTime(), TimeUnit.MILLISECONDS));

        return connectionManager;
    }
}
