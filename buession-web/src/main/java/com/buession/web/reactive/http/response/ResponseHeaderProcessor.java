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
package com.buession.web.reactive.http.response;

import com.buession.core.validator.Validate;
import com.buession.web.http.response.ContentType;
import com.buession.web.http.response.ResponseHeader;
import com.buession.web.http.response.ResponseHeaders;
import com.buession.web.reactive.annotation.AbstractProcessor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

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
        ServerHttpResponse response = getServerHttpResponse(pjp);

        if(response == null){
            return;
        }

        Class<?> clazz = pjp.getTarget().getClass();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        if(clazz != null){
            process(clazz, response);
        }

        if(method != null){
            process(method, response);
        }
    }

    private final static void process(final AnnotatedElement annotatedElement, final ServerHttpResponse response){
        if(AnnotatedElementUtils.hasAnnotation(annotatedElement, ResponseHeader.class)){
            setHeader(response, AnnotatedElementUtils.findMergedAnnotation(annotatedElement, ResponseHeader.class));
        }

        if(AnnotatedElementUtils.hasAnnotation(annotatedElement, ResponseHeaders.class)){
            setHeaders(response, AnnotatedElementUtils.findMergedAnnotation(annotatedElement, ResponseHeaders.class));
        }

        if(AnnotatedElementUtils.hasAnnotation(annotatedElement, ContentType.class)){
            setContentType(response, AnnotatedElementUtils.findMergedAnnotation(annotatedElement, ContentType.class));
        }
    }

    private final static void setHeader(final ServerHttpResponse response, final String name, final String value){
        if(EXPIRES.equalsIgnoreCase(name) == true){
            ResponseUtils.httpCache(response, Integer.parseInt(value));
        }else{
            response.getHeaders().set(name, value);
        }
    }

    private final static void setHeader(final ServerHttpResponse response, final ResponseHeader responseHeader){
        setHeader(response, responseHeader.name(), responseHeader.value());
    }

    private final static void setHeaders(final ServerHttpResponse response, final ResponseHeaders responseHeaders){
        ResponseHeader[] headers = responseHeaders.value();

        if(Validate.isEmpty(headers) == false){
            for(ResponseHeader header : headers){
                setHeader(response, header);
            }
        }
    }

    private final static void setContentType(final ServerHttpResponse response, final ContentType contentType){
        String mime = contentType.mime();
        int i = mime.indexOf('/');
        Charset charset = Charset.forName(contentType.charset());
        MediaType mediaType = new MediaType(mime.substring(0, i - 1), mime.substring(i), charset);

        response.getHeaders().setContentType(mediaType);
    }

}
