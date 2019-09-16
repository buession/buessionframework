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
package com.buession.web.reactive.aop.handler;

import com.buession.aop.MethodInvocation;
import com.buession.web.aop.handler.AbstractResponseHeaderAnnotationHandler;
import com.buession.web.http.response.ResponseHeader;
import com.buession.web.reactive.aop.AopUtils;
import com.buession.web.reactive.http.ServerHttp;
import com.buession.web.reactive.http.response.ResponseUtils;
import org.springframework.http.server.reactive.ServerHttpResponse;

/**
 * @author Yong.Teng
 */
public class ReactiveResponseHeaderAnnotationHandler extends AbstractResponseHeaderAnnotationHandler {

    public ReactiveResponseHeaderAnnotationHandler(){
        super();
    }

    @Override
    public void execute(MethodInvocation mi, ResponseHeader responseHeader) throws Throwable{
        ServerHttp serverHttp = AopUtils.getServerHttp(mi);

        if(serverHttp == null || serverHttp.getResponse() == null){
            return;
        }

        setHeader(serverHttp.getResponse(), responseHeader);
    }

    private final static void setHeader(final ServerHttpResponse response, final ResponseHeader responseHeader){
        if(EXPIRES.equalsIgnoreCase(responseHeader.name()) == true){
            ResponseUtils.httpCache(response, Integer.parseInt(responseHeader.value()));
        }else{
            response.getHeaders().set(responseHeader.name(), responseHeader.value());
        }
    }

}
