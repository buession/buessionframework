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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.http.response.annotation;

import com.buession.web.http.HttpMethod;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Http <a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CORS" target="_blank">跨源资源共享（CORS）</a> 注解
 *
 * @author Yong.Teng
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface Cors {

	/**
	 * 允许请求的域，如果值为 '$http_origin' 则设置为请求头 {@link com.buession.web.http.HttpHeader#ORIGIN} 的值，
	 * {@link #origins} 的别名
	 *
	 * @return 允许请求的域
	 */
	@AliasFor("origins")
	String[] value() default {};

	/**
	 * 允许请求的域，如果值为 '$http_origin' 则设置为请求头 {@link com.buession.web.http.HttpHeader#ORIGIN} 的值，
	 * {@link #value} 的别名
	 *
	 * @return 允许请求的域
	 */
	@AliasFor("value")
	String[] origins() default {};

	/**
	 * 允许请求的方法
	 *
	 * @return 请求的方法
	 */
	HttpMethod[] allowedMethods() default {};

	/**
	 * 实际请求中允许携带的首部字段
	 *
	 * @return 实际请求中允许携带的首部字段
	 */
	String[] allowedHeaders() default {};

	/**
	 * 允许浏览器访问的头
	 *
	 * @return 允许浏览器访问的头
	 */
	String[] exposedHeaders() default {};

	/**
	 * 当浏览器的 credentials 设置为 true 时是否允许浏览器读取 response 的内容
	 *
	 * @return 当浏览器的 credentials 设置为 true 时是否允许浏览器读取 response 的内容
	 */
	char allowCredentials() default '\0';

	/**
	 * 指定了 preflight 请求的结果能够被缓存时间（单位：秒）
	 *
	 * @return preflight 请求的结果能够被缓存时间
	 */
	long maxAge() default -1;

}
