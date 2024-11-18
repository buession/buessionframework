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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.exception;

import com.buession.web.http.Error;

/**
 * 异常错误处理器
 *
 * @author Yong.Teng
 * @since 3.0.1
 */
@FunctionalInterface
public interface IErrorHandler<REQ, RES, EX extends Throwable> {

	/**
	 * 对异常 {code EX} 进行处理，并返回 {@link Error}
	 *
	 * @param request
	 * 		请求对象
	 * @param response
	 * 		响应对象
	 * @param handler
	 * 		handler
	 * @param ex
	 * 		异常
	 *
	 * @return {@link Error} 实例
	 */
	Error apply(final REQ request, final RES response, final Object handler, final EX ex);

}
