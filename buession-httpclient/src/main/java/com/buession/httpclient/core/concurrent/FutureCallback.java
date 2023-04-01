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
package com.buession.httpclient.core.concurrent;

import com.buession.httpclient.core.Response;

/**
 * 用于异步 HTTP 请求响应处理
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public interface FutureCallback {

	/**
	 * 当 HTTP 请求成功完成并且服务器返回响应时调用此方法
	 *
	 * @param response
	 * 		HTTP 响应
	 */
	void completed(Response response);

	/**
	 * 当 HTTP 请求由于异常而失败时调用此方法
	 *
	 * @param ex
	 * 		导致失败的异常。
	 */
	void failed(Exception ex);

	/**
	 * 当 HTTP 请求在完成之前被取消时调用此方法
	 */
	void cancelled();

}
