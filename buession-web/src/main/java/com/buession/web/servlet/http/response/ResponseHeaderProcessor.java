/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2018 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.servlet.http.response;

import com.buession.core.validator.Validate;
import com.buession.web.http.HttpHeader;
import com.buession.web.http.response.ContentType;
import com.buession.web.http.response.PrimitiveCrossOrigin;
import com.buession.web.http.response.ResponseHeader;
import com.buession.web.http.response.ResponseHeaders;
import com.buession.web.servlet.annotation.AbstractProcessor;
import com.buession.web.servlet.http.HttpServlet;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
@Aspect
@Component
public class ResponseHeaderProcessor extends AbstractProcessor {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void responseHeaderProcess(){
    }

    @After("responseHeaderProcess()")
    public void doResponseHeaderProcessAfter(JoinPoint pjp){
        HttpServlet httpServlet = getHttpServlet(pjp);

        if(httpServlet == null || httpServlet.getResponse() == null){
            return;
        }

        Class<?> clazz = pjp.getTarget().getClass();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        if(clazz != null){
            process(clazz, httpServlet.getRequest(), httpServlet.getResponse());
        }

        if(method != null){
            process(method, httpServlet.getRequest(), httpServlet.getResponse());
        }
    }

    private final static void process(final AnnotatedElement annotatedElement, final HttpServletRequest request,
                                      final HttpServletResponse response){
        if(AnnotatedElementUtils.hasAnnotation(annotatedElement, ResponseHeader.class)){
            setHeader(response, AnnotatedElementUtils.findMergedAnnotation(annotatedElement, ResponseHeader.class));
        }

        if(AnnotatedElementUtils.hasAnnotation(annotatedElement, ResponseHeaders.class)){
            setHeaders(response, AnnotatedElementUtils.findMergedAnnotation(annotatedElement, ResponseHeaders.class));
        }

        if(AnnotatedElementUtils.hasAnnotation(annotatedElement, ContentType.class)){
            setContentType(response, AnnotatedElementUtils.findMergedAnnotation(annotatedElement, ContentType.class));
        }

        if(request != null && AnnotatedElementUtils.hasAnnotation(annotatedElement, PrimitiveCrossOrigin.class)){
            response.setHeader(HttpHeader.ACCESS_CONTROL_ALLOW_ORIGIN.getValue(), request.getHeader(HttpHeader.ORIGIN
                    .getValue()));
        }
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

    private final static void setHeaders(final HttpServletResponse response, final ResponseHeaders responseHeaders){
        ResponseHeader[] headers = responseHeaders.value();

        if(Validate.isEmpty(headers) == false){
            for(ResponseHeader header : headers){
                setHeader(response, header);
            }
        }
    }

    private final static void setContentType(final HttpServletResponse response, final ContentType contentType){
        StringBuffer sb = new StringBuffer();

        sb.append(contentType.mime());

        if(Validate.hasText(contentType.encoding()) == false){
            sb.append("; charset=");
            sb.append(contentType.encoding());
        }

        setHeader(response, HttpHeader.CONTENT_TYPE.getValue(), sb.toString());
    }

}
