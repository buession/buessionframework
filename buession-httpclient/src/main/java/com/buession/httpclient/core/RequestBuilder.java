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
package com.buession.httpclient.core;

import java.util.List;
import java.util.Map;

/**
 * 请求构建器
 *
 * @param <R>
 *        {@link Request} 具体实现
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface RequestBuilder<R extends Request> {

	/**
	 * 设置协议及版本
	 *
	 * @param protocolVersion
	 * 		协议及版本
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> setProtocolVersion(ProtocolVersion protocolVersion);

	/**
	 * 设置请求 URL
	 *
	 * @param url
	 * 		请求 URL
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> setUrl(String url);

	/**
	 * 设置请求头
	 *
	 * @param headers
	 * 		请求头
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> setHeaders(List<Header> headers);

	/**
	 * 设置请求参数
	 *
	 * @param parameters
	 * 		请求参数
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> setParameters(Map<String, Object> parameters);

	/**
	 * GET 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> get();

	/**
	 * POST 请求构建
	 *
	 * @param body
	 * 		请求体
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> post(RequestBody<?> body);

	/**
	 * PATCH 请求构建
	 *
	 * @param body
	 * 		请求体
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> patch(RequestBody<?> body);

	/**
	 * PUT 请求构建
	 *
	 * @param body
	 * 		请求体
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> put(RequestBody<?> body);

	/**
	 * DELETE 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> delete();

	/**
	 * CONNECT 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> connect();

	/**
	 * TRACE 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> trace();

	/**
	 * COPY 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> copy();

	/**
	 * MOVE 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> move();

	/**
	 * HEAD 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> head();

	/**
	 * OPTIONS 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> options();

	/**
	 * LINK 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> link();

	/**
	 * UNLINK 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> unlink();

	/**
	 * PURGE 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> purge();

	/**
	 * LOCK 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> lock();

	/**
	 * UNLOCK 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> unlock();

	/**
	 * PROPFIND 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> propfind();

	/**
	 * PROPPATCH 请求构建
	 *
	 * @param body
	 * 		请求体
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> proppatch(RequestBody<?> body);

	/**
	 * REPORT 请求构建
	 *
	 * @param body
	 * 		请求体
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> report(RequestBody<?> body);

	/**
	 * VIEW 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> view();

	/**
	 * WRAPPED 请求构建
	 *
	 * @return 请求构建器
	 */
	RequestBuilder<R> wrapped();

	/**
	 * 构建请求 {@link Request}
	 *
	 * @return 请求 {@link Request}
	 */
	R build();

}
