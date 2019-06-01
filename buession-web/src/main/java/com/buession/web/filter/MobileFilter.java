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
 * | Copyright @ 2013-2017 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.buession.core.validator.Validate;

/**
 * @author Yong.Teng
 */
public class MobileFilter extends OncePerRequestFilter {

    private boolean isMobile = false;

    private final static Set<String> mobileBrowserKeywords = new HashSet<String>();

    private final static Logger logger = LoggerFactory.getLogger(MobileFilter.class);

    static{
        /* 诺基亚 */
        mobileBrowserKeywords.add("Nokia");
        /* 索尼爱立信 */
        mobileBrowserKeywords.add("SonyEricsson");
        /* 索尼 */
        mobileBrowserKeywords.add("Sony");
        /* 爱立信 */
        mobileBrowserKeywords.add("Ericsson");
        /* 摩托罗拉 */
        mobileBrowserKeywords.add("Mot");
        /* 三星 */
        mobileBrowserKeywords.add("Samsung");
        /* HTC */
        mobileBrowserKeywords.add("HTC");
        /*  */
        mobileBrowserKeywords.add("sgh");
        /* LG */
        mobileBrowserKeywords.add("LG");
        /* 夏普 */
        mobileBrowserKeywords.add("sharp");
        /*  */
        mobileBrowserKeywords.add("sie-");
        /* 飞利浦 */
        mobileBrowserKeywords.add("Philips");
        /* 海尔 */
        mobileBrowserKeywords.add("Haier");
        /* 长虹 */
        mobileBrowserKeywords.add("Changhong");
        /* 松下 */
        mobileBrowserKeywords.add("Panasonic");
        /*  */
        mobileBrowserKeywords.add("alcatel");
        /* 联想 */
        mobileBrowserKeywords.add("Lenovo");
        /* iPhone */
        mobileBrowserKeywords.add("iPhone");
        /* iPod */
        mobileBrowserKeywords.add("iPod");
        /* 黑莓 */
        mobileBrowserKeywords.add("blackberry");
        /* 魅族 */
        mobileBrowserKeywords.add("meizu");
        /*  */
        mobileBrowserKeywords.add("netfront");
        /*  */
        mobileBrowserKeywords.add("palm");
        /*  */
        mobileBrowserKeywords.add("openwave");
        /*  */
        mobileBrowserKeywords.add("nexusone");
        /*  */
        mobileBrowserKeywords.add("cldc");
        /*  */
        mobileBrowserKeywords.add("midp");
        /* 华为 */
        mobileBrowserKeywords.add("Huawei");
        /* TCL */
        mobileBrowserKeywords.add("TCL");
        /* CECT */
        mobileBrowserKeywords.add("CECT");
        /* Compal */
        mobileBrowserKeywords.add("Compal");
        /* NEC */
        mobileBrowserKeywords.add("NEC");
        /* TDG */
        mobileBrowserKeywords.add("TDG");
        /* 阿尔卡特 */
        mobileBrowserKeywords.add("Alcatel");
        /* 波导 */
        mobileBrowserKeywords.add("BIRD");
        /* 大显 */
        mobileBrowserKeywords.add("DAXIAN");
        /* 迪比特 */
        mobileBrowserKeywords.add("DBTEL");
        /* 东信 */
        mobileBrowserKeywords.add("Eastcom");
        /* 多彩 */
        mobileBrowserKeywords.add("PANTECH");
        /* 多普达 */
        mobileBrowserKeywords.add("Dopod");
        /* 康佳 */
        mobileBrowserKeywords.add("KONKA");
        /* 科健 */
        mobileBrowserKeywords.add("Kejian");
        /* 明基 */
        mobileBrowserKeywords.add("BenQ");
        /* 南方高科 */
        mobileBrowserKeywords.add("Soutec");
        /* 萨基姆 */
        mobileBrowserKeywords.add("SAGEM");
        /* 西门子 */
        mobileBrowserKeywords.add("SIE");
        /* 夏新 */
        mobileBrowserKeywords.add("Amoi");
        /* 中兴 */
        mobileBrowserKeywords.add("ZTE");
        /*  */
        mobileBrowserKeywords.add("wap");
        /*  */
        mobileBrowserKeywords.add("mobile");
        /*  */
        mobileBrowserKeywords.add("ucweb");
        /*  */
        mobileBrowserKeywords.add("operamini");
        /*  */
        mobileBrowserKeywords.add("operamobi");
        /* 小米 */
        mobileBrowserKeywords.add("Xiaomi");
        /* 微信 */
        mobileBrowserKeywords.add("MicroMessenger");
        /* QQValidator 浏览器 */
        mobileBrowserKeywords.add("MQQBrowser");
        /*  */
        mobileBrowserKeywords.add("Android");
        /*  */
        mobileBrowserKeywords.add("Symbian");
        /*  */
        mobileBrowserKeywords.add("windowsce");
    }

    public boolean isMobile(){
        return isMobile;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain
            filterChain) throws ServletException, IOException{
        if(request == null){
            filterChain.doFilter(request, response);
            return;
        }

        String userAgent = request.getHeader("User-Agent");

        if(Validate.hasText(userAgent) == true){
            userAgent = userAgent.toLowerCase();
            Iterator<String> iterator = mobileBrowserKeywords.iterator();

            while(iterator.hasNext()){
                final String keywords = iterator.next();

                isMobile = userAgent.contains(keywords.toLowerCase());
                if(isMobile == true){
                    logger.debug("Match keywords {}", keywords);
                    request.setAttribute("isMobile", true);
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            final String accept = request.getHeader("Accept");
            isMobile = accept != null && (accept.contains("vnd.wap.wml") == true && accept.contains("text/html") ==
                    false || accept.indexOf("vnd.wap.wml") > accept.indexOf("text/html"));
        }

        request.setAttribute("isMobile", isMobile);

        filterChain.doFilter(request, response);
    }

}