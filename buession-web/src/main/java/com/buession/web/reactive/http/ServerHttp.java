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
package com.buession.web.reactive.http;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.ui.Model;

/**
 * @author Yong.Teng
 */
public class ServerHttp {

    private ServerHttpRequest request;

    private ServerHttpResponse response;

    private Model model;

    public ServerHttp(){
    }

    public ServerHttp(ServerHttpRequest request, ServerHttpResponse response){
        this.request = request;
        this.response = response;
    }

    public ServerHttp(ServerHttpRequest request, ServerHttpResponse response, Model model){
        this.request = request;
        this.response = response;
        this.model = model;
    }


    public ServerHttpRequest getRequest(){
        return request;
    }

    public void setRequest(ServerHttpRequest request){
        this.request = request;
    }

    public ServerHttpResponse getResponse(){
        return response;
    }

    public void setResponse(ServerHttpResponse response){
        this.response = response;
    }

    public Model getModel(){
        return model;
    }

    public void setModel(Model model){
        this.model = model;
    }
}