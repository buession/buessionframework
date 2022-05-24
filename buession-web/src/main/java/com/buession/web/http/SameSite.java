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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.http;

/**
 * SameSite 是HTTP响应头 <a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Set-Cookie/SameSite" target="_blank"></a>Set-Cookie 的属性之一，它允许您声明该 Cookie 是否仅限于第一方或者同一站点上下文
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public enum SameSite {

	/**
	 * Cookies 将在所有上下文中发送，即允许跨站发送
	 */
	NONE,

	/**
	 * Cookies 允许与顶级导航一起发送，并将与第三方网站发起的GET请求一起发送，这是浏览器中的默认值
	 */
	LAX,

	/**
	 * Cookies 只会在第一方上下文中发送，不会与第三方网站发起的请求一起发送
	 */
	STRICT

}
