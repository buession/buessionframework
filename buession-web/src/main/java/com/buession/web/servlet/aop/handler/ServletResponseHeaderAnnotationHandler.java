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
package com.buession.web.servlet.aop.handler;

import com.buession.aop.MethodInvocation;
import com.buession.web.aop.handler.AbstractResponseHeaderAnnotationHandler;
import com.buession.web.http.HttpHeader;
import com.buession.web.http.response.ResponseHeader;
import com.buession.web.servlet.aop.AopUtils;
import com.buession.web.servlet.http.HttpServlet;
import com.buession.web.servlet.http.response.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Yong.Teng
 */
public class ServletResponseHeaderAnnotationHandler extends AbstractResponseHeaderAnnotationHandler {

    private final static Logger logger = LoggerFactory.getLogger(ServletResponseHeaderAnnotationHandler.class);

    public ServletResponseHeaderAnnotationHandler(){
        super();
    }

    @Override
    public void execute(MethodInvocation mi, ResponseHeader responseHeader){
        HttpServlet httpServlet = AopUtils.getHttpServlet(mi);

        if(httpServlet == null || httpServlet.getResponse() == null){
            logger.debug("{} is null.", httpServlet == null ? "HttpServlet" : "ServerHttpResponse");
            return;
        }

        setHeader(httpServlet.getResponse(), responseHeader);
    }

    private final static void setHeader(final HttpServletResponse response, final String name, final String value){
        if(HttpHeader.EXPIRES.getValue().equalsIgnoreCase(name) == true){
            ResponseUtils.httpCache(response, Integer.parseInt(value));
        }else{
            response.addHeader(name, value);
        }
    }

    private final static void setHeader(final HttpServletResponse response, final ResponseHeader responseHeader){
        setHeader(response, responseHeader.name(), responseHeader.value());
    }

}
