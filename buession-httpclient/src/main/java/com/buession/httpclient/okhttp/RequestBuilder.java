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
package com.buession.httpclient.okhttp;

import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestMethod;
import okhttp3.Request;

/**
 * @author Yong.Teng
 */
public class RequestBuilder extends okhttp3.Request.Builder {

    public RequestBuilder(){
        super();
    }

    public RequestBuilder(String url){
        this();
        url(url);
    }

    public RequestBuilder(Request request){
        super(request);
    }

    public Request.Builder post(){
        return this.method(RequestMethod.POST);
    }

    public Request.Builder post(RequestBody body){
        return this.method(RequestMethod.POST, body);
    }

    public Request.Builder patch(){
        return this.method(RequestMethod.PATCH);
    }

    public Request.Builder patch(RequestBody body){
        return this.method(RequestMethod.PATCH, body);
    }

    public Request.Builder put(){
        return this.method(RequestMethod.PUT);
    }

    public Request.Builder put(RequestBody body){
        return this.method(RequestMethod.PUT, body);
    }

    public Request.Builder connect(){
        return this.method(RequestMethod.CONNECT);
    }

    public Request.Builder trace(){
        return this.method(RequestMethod.TRACE);
    }

    public Request.Builder copy(){
        return this.method(RequestMethod.COPY);
    }

    public Request.Builder move(){
        return this.method(RequestMethod.MOVE);
    }

    public Request.Builder options(){
        return this.method(RequestMethod.OPTIONS);
    }

    public Request.Builder link(){
        return this.method(RequestMethod.LINK);
    }

    public Request.Builder unlink(){
        return this.method(RequestMethod.UNLINK);
    }

    public Request.Builder purge(){
        return this.method(RequestMethod.PURGE);
    }

    public Request.Builder lock(){
        return this.method(RequestMethod.LOCK);
    }

    public Request.Builder unlock(){
        return this.method(RequestMethod.UNLOCK);
    }

    public Request.Builder propfind(){
        return this.method(RequestMethod.PROPFIND);
    }

    public Request.Builder proppatch(){
        return this.method(RequestMethod.PROPPATCH);
    }

    public Request.Builder proppatch(RequestBody body){
        return this.method(RequestMethod.PROPPATCH, body);
    }

    public Request.Builder report(){
        return this.method(RequestMethod.REPORT);
    }

    public Request.Builder report(RequestBody body){
        return this.method(RequestMethod.REPORT, body);
    }

    public Request.Builder view(){
        return this.method(RequestMethod.VIEW);
    }

    public Request.Builder wrapped(){
        return this.method(RequestMethod.WRAPPED);
    }

    private Request.Builder method(final RequestMethod method){
        return this.method(method.name(), null);
    }

    private Request.Builder method(final RequestMethod method, final RequestBody body){
        return this.method(method.name(), OkHttpRequestBuilder.buildRequestBody(body));
    }

}
