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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.http;

/**
 * HTTP X-XSS-Protection 响应头是 Internet Explorer，Chrome 和 Safari 的一个特性，当检测到跨站脚本攻击 (XSS) 时，浏览器将停止加载页面。
 * <p>https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Reference/Headers/X-XSS-Protection</p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public enum XssProtection {

	/**
	 * 禁止 XSS 过滤
	 */
	DISABLED("0"),

	/**
	 * 启用 XSS 过滤。如果检测到跨站脚本攻击，浏览器将清除页面并使用 CSP report-uri指令的功能发送违规报告。
	 */
	ENABLED("1"),

	/**
	 * 启用 XSS 过滤。如果检测到攻击，浏览器将不会清除页面，而是阻止页面加载。
	 */
	ENABLED_MODE_BLOCK("1; mode=block");

	private final String value;

	XssProtection(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}
