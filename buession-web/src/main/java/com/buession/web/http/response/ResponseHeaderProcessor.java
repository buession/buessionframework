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
package com.buession.web.http.response;

import com.buession.core.validator.Validate;
import com.buession.web.annotation.AbstractProcessor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
@Aspect
@Component
public class ResponseHeaderProcessor extends AbstractProcessor {

    protected final static String EXPIRES = "Expires";

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void responseHeaderProcess(){
    }

    @After("responseHeaderProcess()")
    public void doResponseHeaderProcessAfter(JoinPoint pjp){
        HttpServletResponse response = getHttpServletResponse(pjp);

        if(response == null){
            return;
        }

        Class<?> clazz = pjp.getTarget().getClass();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        if(clazz != null){
            if(AnnotatedElementUtils.hasAnnotation(clazz, ResponseHeader.class)){
                setHeader(response, AnnotatedElementUtils.findMergedAnnotation(clazz, ResponseHeader.class));
            }

            if(AnnotatedElementUtils.hasAnnotation(clazz, ResponseHeaders.class)){
                setHeaders(response, AnnotatedElementUtils.findMergedAnnotation(clazz, ResponseHeaders.class));
            }
        }

        if(method != null){
            if(AnnotatedElementUtils.hasAnnotation(method, ResponseHeader.class)){
                setHeader(response, AnnotatedElementUtils.findMergedAnnotation(method, ResponseHeader.class));
            }

            if(AnnotatedElementUtils.hasAnnotation(method, ResponseHeaders.class)){
                setHeaders(response, AnnotatedElementUtils.findMergedAnnotation(method, ResponseHeaders.class));
            }
        }
    }

    private final static void setHeader(final HttpServletResponse response, final String name, final String value){
        if(EXPIRES.equalsIgnoreCase(name) == true){
            ResponseUtils.httpCache(response, Integer.parseInt(value));
        }else{
            response.addHeader(name, value);
        }
    }

    private final static void setHeader(final HttpServletResponse response, final ResponseHeader responseHeader){
        setHeader(response, responseHeader.name(), responseHeader.value());
    }

    private final static void setHeaders(final HttpServletResponse response, final ResponseHeaders responseHeaders){
        if(responseHeaders == null){
            return;
        }

        ResponseHeader[] headers = responseHeaders.value();

        if(Validate.isEmpty(headers) == false){
            for(ResponseHeader header : headers){
                setHeader(response, header);
            }
        }
    }

}
