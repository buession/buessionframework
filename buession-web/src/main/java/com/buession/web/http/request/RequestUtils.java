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
package com.buession.web.http.request;

/**
 * @author Yong.Teng
 */
public class RequestUtils {

    public final static String[] CLIENT_IP_HEADERS = new String[]{"X-Forwarded-For-Pound"/* 微信 */, "X-Cdn-Src-Ip"/* 网宿
    */, "X-Forwarded-For", "X-Real-Ip", "Proxy-Client-IP", "WL-Proxy-Client-IP", "Real-ClientIP"};

    public final static String[] MOBILE_MAPS = new String[]{"android"/* Android*/, "iphone"/* IPhone*/, "ipod"/*
    IPod*/, "windows phone"/* Windows Phone*/, "windowsce", "mobile", "coolpad", "mmp", "smartphone", "midp", "wap",
            "xoom", "symbian"/* Symbian */, "j2me", "ucweb", "operamini", "operamobi", "MicroMessenger"/* 微信 */,
            "MQQBrowser"/*
             QQ 浏览器 */, "wince", "Nokia"/* 诺基亚 */, "SonyEricsson"/* 索尼爱立信 */, "Sony"/* 索尼 */, "Ericsson"/* 爱立信 */,
            "Mot"/* 摩托罗拉 */, "Samsung"/* 三星 */, "HTC"/* HTC */, "sgh"/*  */, "LG"/* LG */, "sharp"/* 夏普 */, "sie-"/*
             */, "Philips"/* 飞利浦 */, "Haier"/* 海尔 */, "Changhong"/* 长虹 */, "Panasonic"/* 松下 */, "alcatel"/*  */,
            "Lenovo"/* 联想 */, "blackberry"/* 黑莓 */, "meizu"/* 魅族 */, "netfront"/*  */, "palm"/*  */, "openwave"/*
            */, "nexusone"/*  */, "cldc"/*  */, "midp"/*  */, "Huawei"/* 华为 */, "TCL"/* TCL */, "CECT"/* CECT */,
            "Compal"/* Compal */, "NEC"/* NEC */, "TDG"/* TDG */, "Alcatel"/* 阿尔卡特 */, "BIRD"/* 波导 */, "DAXIAN"/* 大显
            */, "DBTEL"/* 迪比特 */, "Eastcom"/* 东信 */, "PANTECH"/* 多彩 */, "Dopod"/* 多普达 */, "KONKA"/* 康佳 */, "Kejian"/*
             *  科健 */, "BenQ"/* 明基 */, "Soutec"/* 南方高科 */, "SAGEM"/* 萨基姆 */, "SIE"/* 西门子 */, "Amoi"/* 夏新 */, "ZTE"/*
             *  中兴 */, "Xiaomi"/* 小米 */};

    /**
     * 判断是否为 Ajax 请求
     *
     * @param xRequestedWith
     *         X-Requested-With
     *
     * @return 是否为 Ajax 请求
     */
    protected final static boolean isAjaxRequest(final String xRequestedWith){
        return "XMLHttpRequest".equalsIgnoreCase(xRequestedWith);
    }

    /**
     * 判断是否为移动端请求
     *
     * @param userAgent
     *         User-Agent
     *
     * @return 是否为移动端请求
     */
    protected final static boolean isMobile(final String userAgent){
        if(userAgent == null){
            return false;
        }

        String userAgentLower = userAgent.toLowerCase();

        for(String s : MOBILE_MAPS){
            if(userAgentLower.contains(s.toLowerCase())){
                return true;
            }
        }

        return false;
    }

}
