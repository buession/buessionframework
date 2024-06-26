/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.exception;

/**
 * 运算异常
 *
 * @author Yong.Teng
 */
public class OperationException extends Exception {

	private final static long serialVersionUID = -3968930023220286656L;

	/**
	 * The error code;
	 */
	private int code = 0;

	/**
	 * 构造函数
	 */
	public OperationException(){
		super(null, null);
	}

	public OperationException(final int code){
		this();
		this.code = code;
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * 		异常信息
	 */
	public OperationException(final String message){
		super(message, null);
	}

	/**
	 * 构造函数
	 *
	 * @param code
	 * 		错误码
	 * @param message
	 * 		异常信息
	 */
	public OperationException(final int code, final String message){
		this(code, message, null);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * 		异常信息
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 */
	public OperationException(final String message, Throwable cause){
		super(message, cause);
	}

	/**
	 * 构造函数
	 *
	 * @param code
	 * 		错误码
	 * @param message
	 * 		异常信息
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 */
	public OperationException(final int code, final String message, Throwable cause){
		super(message, cause);
		this.code = code;
	}

	/**
	 * Return the error code for exception
	 *
	 * @return the error code for exception
	 */
	public int getCode(){
		return code;
	}

	/**
	 * Return the error code for exception
	 *
	 * @return the error code for exception
	 */
	public int errorCode(){
		return getCode();
	}

	@Override
	public String toString(){
		String s = getClass().getName();
		String message = getLocalizedMessage();
		return (message != null ? s + ": " + message : s) + "(code: " + getCode() + ")";
	}

}