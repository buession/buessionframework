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
package com.buession.httpclient.core;

import java.io.InputStream;
import java.util.List;

/**
 * 响应构建器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface ResponseBuilder {

	/**
	 * 设置协议及版本
	 *
	 * @param protocolVersion
	 * 		协议及版本
	 *
	 * @return 响应构建器
	 */
	ResponseBuilder setProtocolVersion(ProtocolVersion protocolVersion);

	/**
	 * 设置状态码
	 *
	 * @param statusCode
	 * 		状态码
	 *
	 * @return 响应构建器
	 */
	ResponseBuilder setStatusCode(int statusCode);

	/**
	 * 设置状态码文本描述
	 *
	 * @param statusText
	 * 		状态码文本描述
	 *
	 * @return 响应构建器
	 */
	ResponseBuilder setStatusText(String statusText);

	/**
	 * 设置响应头
	 *
	 * @param headers
	 * 		响应头
	 *
	 * @return 响应构建器
	 */
	ResponseBuilder setHeaders(List<Header> headers);

	/**
	 * 设置响应流
	 *
	 * @param inputStream
	 * 		响应流
	 *
	 * @return 响应构建器
	 */
	ResponseBuilder setInputStream(InputStream inputStream);

	/**
	 * 设置响应体
	 *
	 * @param body
	 * 		响应体
	 *
	 * @return 响应构建器
	 */
	ResponseBuilder setBody(String body);

	/**
	 * 设置响应内容长度
	 *
	 * @param contentLength
	 * 		响应内容长度
	 *
	 * @return 响应构建器
	 */
	ResponseBuilder setContentLength(long contentLength);

	/**
	 * 构建响应 {@link Response}
	 *
	 * @return 响应 {@link Response}
	 */
	Response build();

}
