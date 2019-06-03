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
 * | Copyright @ 2013-2019 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.http.request;

import com.buession.core.validator.Validate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class RequestUtils {

    private final static Set<String> CLIENT_IP_HEADERS = new LinkedHashSet<>();

    private final static Set<String> MOBILE_MAPS = new HashSet<>();

    static{
        CLIENT_IP_HEADERS.add("X-Forwarded-For");
        CLIENT_IP_HEADERS.add("Proxy-Client-IP");
        CLIENT_IP_HEADERS.add("WL-Proxy-Client-IP");
        CLIENT_IP_HEADERS.add("Real-ClientIP");

        MOBILE_MAPS.add("android");
        MOBILE_MAPS.add("iphone");
        MOBILE_MAPS.add("ipod");
        MOBILE_MAPS.add("windows phone");
        MOBILE_MAPS.add("mobile");
        MOBILE_MAPS.add("coolpad");
        MOBILE_MAPS.add("mmp");
        MOBILE_MAPS.add("smartphone");
        MOBILE_MAPS.add("midp");
        MOBILE_MAPS.add("wap");
        MOBILE_MAPS.add("xoom");
        MOBILE_MAPS.add("symbian");
        MOBILE_MAPS.add("j2me");
        MOBILE_MAPS.add("blackberry");
        MOBILE_MAPS.add("wince");
    }

    private RequestUtils(){
    }

    /**
     * 获取客户端真实 IP 地址
     *
     * @param request
     *         HttpServletRequest
     *
     * @return 客户端真实 IP 地址
     */
    public final static String getClientIp(final HttpServletRequest request){
        if(request == null){
            throw new IllegalArgumentException("HttpServletRequest cloud not be null.");
        }

        String ip = null;
        for(String header : CLIENT_IP_HEADERS){
            ip = request.getHeader(header);
            if(Validate.hasText(ip) == true && "unknown".equalsIgnoreCase(ip) == false){
                return ip;
            }
        }

        ip = request.getRemoteAddr();
        if(Validate.hasText(ip) == false || "unknown".equalsIgnoreCase(ip) == true){
            ip = "127.0.0.1";
        }

        return ip;
    }

    /**
     * 判断是否为 Ajax 请求
     *
     * @param request
     *         HttpServletRequest
     *
     * @return 是否为 Ajax 请求
     */
    public final static boolean isAjaxRequest(final HttpServletRequest request){
        return request == null ? false : "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 判断是否为移动端请求
     *
     * @param request
     *         HttpServletRequest
     *
     * @return 是否为移动端请求
     */
    public final static boolean isMobile(final HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent").toLowerCase();

        for(String s : MOBILE_MAPS){
            if(userAgent.contains(s)){
                return true;
            }
        }

        return false;
    }

}
