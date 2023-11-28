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
package com.buession.httpclient.exception;

import com.buession.httpclient.core.RequestMethod;

/**
 * 不支持的请求异常
 *
 * @author Yong.Teng
 */
public class UnsupportedRequestMethodException extends Exception {

	private final static long serialVersionUID = 2627739916305092393L;

	public UnsupportedRequestMethodException() {
		super();
	}

	public UnsupportedRequestMethodException(String message) {
		super(message);
	}

	public UnsupportedRequestMethodException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedRequestMethodException(RequestMethod method) {
		this("Unsupported HTTP Method '" + method + "'.");
	}

	public UnsupportedRequestMethodException(Throwable cause) {
		super(cause);
	}

	public UnsupportedRequestMethodException(RequestMethod method, Throwable cause) {
		this("Unsupported HTTP Method '" + method + "'.", cause);
	}

	public UnsupportedRequestMethodException(String message, Throwable cause, boolean enableSuppression, boolean
			writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
