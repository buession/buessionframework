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
package com.buession.core.exception;

/**
 * 嵌套运行时异常
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class NestedRuntimeException extends RuntimeException {

	private final static long serialVersionUID = -1165918720723049728L;

	/**
	 * 构造函数
	 */
	public NestedRuntimeException(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * 		异常信息
	 */
	public NestedRuntimeException(String message){
		super(message);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * 		异常信息
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 */
	public NestedRuntimeException(String message, Throwable cause){
		super(message, cause);
	}

	/**
	 * 构造函数
	 *
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 */
	public NestedRuntimeException(Throwable cause){
		super(cause);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * 		异常信息
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 * @param enableSuppression
	 * 		是否启用抑制
	 * @param writableStackTrace
	 * 		堆栈跟踪是否应该是可写的
	 */
	public NestedRuntimeException(String message, Throwable cause, boolean enableSuppression,
								  boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
