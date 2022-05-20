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
package com.buession.web.http.response.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Yong.Teng
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpCache {

	/**
	 * 缓存指令，详细描述：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cache-Control" target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cache-Control</a>
	 *
	 * @return 缓存指令
	 */
	@AliasFor("value")
	String cacheControl() default "";

	/**
	 * 缓存指令，详细描述：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cache-Control" target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cache-Control</a>
	 *
	 * @return 缓存指令
	 */
	@AliasFor("cacheControl")
	String value() default "";

	/**
	 * 缓存过期时间，详细描述：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Expires" target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Expires</a>
	 *
	 * @return 缓存过期时间
	 */
	String expires() default "";

	/**
	 * Pragma 是一个在 HTTP/1.0 中规定的通用首部，这个首部的效果依赖于不同的实现，所以在“请求-响应”链中可能会有不同的效果。它用来向后兼容只支持 HTTP/1.0 协议的缓存服务器，详细描述：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Pragma" target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Pragma</a>
	 *
	 * @return 缓存指令
	 */
	String pragma() default "";

}
