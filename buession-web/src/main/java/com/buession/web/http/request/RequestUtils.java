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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.http.request;

import com.buession.core.validator.Validate;
import com.buession.web.http.HttpHeader;

import java.util.function.Function;

/**
 * HTTP 请求工具基类
 *
 * @author Yong.Teng
 */
public abstract class RequestUtils {

	public final static String DEFAULT_IP = "127.0.0.1";

	public final static String[] CLIENT_IP_HEADERS = new String[]{
			/* 微信 */
			"X-Forwarded-For-Pound",
			/* 阿里云 */
			"Ali-Cdn-Real-Ip",
			/* 网宿 */
			"Cdn-Src-Ip",
			"X-Cdn-Src-Ip",
			/* 天翼云 */
			"X-Original-Forwarded-For",
			/* */
			"X-Forwarded-For",
			/* */
			"X-Real-Ip",
			/* */
			"Proxy-Client-IP",
			/* */
			"WL-Proxy-Client-IP",
			/* */
			"Real-ClientIP",
			/* */
			"Client-IP"
	};

	public final static String[] MOBILE_MAPS = new String[]{
			/* Android*/
			"android",
			/* IPhone */
			"iphone",
			/* IPod */
			"ipod",
			/* Windows Phone */
			"windows phone",
			/* */
			"windowsce",
			/* */
			"mobile",
			/* */
			"coolpad",
			/* */
			"mmp",
			/* */
			"smartphone",
			/* */
			"midp",
			/* */
			"wap",
			/* */
			"xoom",
			/* Symbian */
			"symbian",
			/* */
			"j2me",
			/* */
			"ucweb",
			/* Opera */
			"operamini", "operamobi",
			/* 微信 */
			"MicroMessenger",
			/* QQ 浏览器 */
			"MQQBrowser", "wince",
			/* 诺基亚 */
			"Nokia",
			/* 索尼爱立信 */
			"SonyEricsson",
			/* 索尼 */
			"Sony",
			/* 爱立信 */
			"Ericsson",
			/* 摩托罗拉 */
			"Mot",
			/* 三星 */
			"Samsung",
			/* HTC */
			"HTC",
			/*  */
			"sgh",
			/* LG */
			"LG",
			/* 夏普 */
			"sharp",
			/*  */
			"sie-",
			/* 飞利浦 */
			"Philips",
			/* 海尔 */
			"Haier",
			/* 长虹 */
			"Changhong",
			/* 松下 */
			"Panasonic",
			/*  */
			"alcatel",
			/* 联想 */
			"Lenovo",
			/* 黑莓 */
			"blackberry",
			/* 魅族 */
			"meizu",
			/*  */
			"netfront",
			/*  */
			"palm",
			/* */
			"openwave",
			/*  */
			"nexusone",
			/*  */
			"cldc",
			/*  */
			"midp",
			/* 华为 */
			"Huawei",
			/* TCL */
			"TCL",
			/* CECT */
			"CECT",
			/* Compal */
			"Compal",
			/* NEC */
			"NEC",
			/* TDG */
			"TDG",
			/* 阿尔卡特 */
			"Alcatel",
			/* 波导 */
			"BIRD",
			/* 大显 */
			"DAXIAN",
			/* 迪比特 */
			"DBTEL",
			/* 东信 */
			"Eastcom",
			/* 多彩 */
			"PANTECH",
			/* 多普达 */
			"Dopod",
			/* 康佳 */
			"KONKA",
			/* 科健 */
			"Kejian",
			/* 明基 */
			"BenQ",
			/* 南方高科 */
			"Soutec",
			/* 萨基姆 */
			"SAGEM",
			/* 西门子 */
			"SIE",
			/* 夏新 */
			"Amoi",
			/* 中兴 */
			"ZTE",
			/* 小米 */
			"Xiaomi"
	};

	/**
	 * 遍历获取指定请求头值
	 *
	 * @param function
	 * 		Function
	 * @param validator
	 * 		验证器
	 * @param headerNames
	 * 		请求头名称数组
	 * @param <T>
	 * 		返回值类型
	 *
	 * @return 请求头值
	 */
	protected static <T> T iteratorRequestHeader(final Function<String, T> function,
												 final Function<T, Boolean> validator, final String... headerNames){
		T result;

		for(String headerName : headerNames){
			result = function.apply(headerName);
			if(validator.apply(result)){
				return result;
			}
		}

		return null;
	}

	/**
	 * 获取请求的真实 Scheme
	 *
	 * @param function
	 * 		Function
	 *
	 * @return 请求的真实 Scheme
	 */
	protected static String getScheme(final Function<String, String> function){
		return iteratorRequestHeader(function, Validate::hasText, HttpHeader.X_FORWARDED_PROTOCOL.getValue(),
				HttpHeader.X_FORWARDED_PROTO.getValue());
	}

	/**
	 * 获取请求的真实主机地址
	 *
	 * @param function
	 * 		Function
	 *
	 * @return 请求的真实主机地址
	 */
	protected static String getHost(final Function<String, String> function){
		return iteratorRequestHeader(function, Validate::hasText, HttpHeader.X_FORWARDED_HOST.getValue(),
				HttpHeader.HOST.getValue());
	}

	/**
	 * 获取客户端真实 IP 地址
	 *
	 * @param function
	 * 		Function
	 * @param remoteAddr
	 * 		Remote Addr
	 *
	 * @return 客户端真实 IP 地址
	 */
	protected static String getClientIp(final Function<String, String> function, final String remoteAddr){
		String ip = iteratorRequestHeader(function,
				(value)->Validate.hasText(value) && "unknown".equalsIgnoreCase(value) == false, CLIENT_IP_HEADERS);

		if(ip != null){
			return ip;
		}

		ip = remoteAddr;
		if(Validate.isBlank(ip) || "unknown".equalsIgnoreCase(ip)){
			ip = DEFAULT_IP;
		}

		return ip;
	}

	/**
	 * 判断是否为 Ajax 请求
	 *
	 * @param xRequestedWith
	 * 		X-Requested-With
	 *
	 * @return 是否为 Ajax 请求
	 */
	protected static boolean isAjaxRequest(final String xRequestedWith){
		return "XMLHttpRequest".equalsIgnoreCase(xRequestedWith);
	}

	/**
	 * 判断是否为移动端请求
	 *
	 * @param userAgent
	 * 		User-Agent
	 * @param accept
	 * 		Accept
	 *
	 * @return 是否为移动端请求
	 */
	protected static boolean isMobile(final String userAgent, final String accept){
		if(Validate.isBlank(userAgent)){
			return false;
		}

		String userAgentLower = userAgent.toLowerCase();

		for(String s : MOBILE_MAPS){
			if(userAgentLower.contains(s.toLowerCase())){
				return true;
			}
		}

		if(Validate.isBlank(accept)){
			return false;
		}

		final String wml = "vnd.wap.wml";
		final String html = "text/html";
		return accept.contains(wml) && accept.contains(html) == false || accept.indexOf(wml) > accept.indexOf(html);
	}

	protected static String getAuthority(final String scheme, final String host, final int port){
		if(port == 0){
			return host;
		}

		final StringBuilder sb = new StringBuilder(host);

		if("http".equals(scheme) && port != 80 || "https".equals(scheme) && port != 443){
			sb.append(':').append(port);
		}

		return sb.toString();
	}

}
