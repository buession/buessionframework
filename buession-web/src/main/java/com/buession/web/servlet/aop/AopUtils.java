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
package com.buession.web.servlet.aop;

import com.buession.aop.MethodInvocation;
import com.buession.web.servlet.http.HttpServlet;
import org.aspectj.lang.JoinPoint;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yong.Teng
 */
public class AopUtils {

    private AopUtils(){

    }

    public final static HttpServlet getHttpServlet(final MethodInvocation mi){
        if(mi == null || mi.getArguments() == null){
            return null;
        }

        HttpServlet httpServlet = new HttpServlet();

        for(Object argument : mi.getArguments()){
            if(argument instanceof HttpServletRequest){
                httpServlet.setRequest((HttpServletRequest) argument);
            }else if(argument instanceof HttpServletResponse){
                httpServlet.setResponse((HttpServletResponse) argument);
            }else if(argument instanceof Model){
                httpServlet.setModel((Model) argument);
            }
        }

        return httpServlet;
    }

    public final static HttpServlet getHttpServlet(final JoinPoint joinPoint){
        if(joinPoint == null || joinPoint.getArgs() == null){
            return null;
        }

        HttpServlet httpServlet = new HttpServlet();

        for(Object arg : joinPoint.getArgs()){
            if(arg instanceof HttpServletRequest){
                httpServlet.setRequest((HttpServletRequest) arg);
            }else if(arg instanceof HttpServletResponse){
                httpServlet.setResponse((HttpServletResponse) arg);
            }else if(arg instanceof Model){
                httpServlet.setModel((Model) arg);
            }
        }

        return httpServlet;
    }

}