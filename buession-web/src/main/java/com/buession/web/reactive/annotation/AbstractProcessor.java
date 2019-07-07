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
package com.buession.web.reactive.annotation;

import org.aspectj.lang.JoinPoint;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.ui.Model;

/**
 * @author Yong.Teng
 */
public abstract class AbstractProcessor {

    protected final static ServerHttpRequest getServerHttpRequest(final JoinPoint pjp){
        if(pjp == null || pjp.getArgs() == null){
            return null;
        }

        for(Object argument : pjp.getArgs()){
            if(argument instanceof ServerHttpRequest){
                return (ServerHttpRequest) argument;
            }
        }

        return null;
    }

    protected final static ServerHttpResponse getServerHttpResponse(final JoinPoint pjp){
        if(pjp == null || pjp.getArgs() == null){
            return null;
        }

        for(Object argument : pjp.getArgs()){
            if(argument instanceof ServerHttpResponse){
                return (ServerHttpResponse) argument;
            }
        }

        return null;
    }

    protected final static Model getModel(final JoinPoint pjp){
        if(pjp == null || pjp.getArgs() == null){
            return null;
        }

        for(Object argument : pjp.getArgs()){
            if(argument instanceof Model){
                return (Model) argument;
            }
        }

        return null;
    }

}
