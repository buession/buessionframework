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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.oss.exception;

/**
 * @author Yong.Teng
 */
public class OSSException extends Exception {

	private final static long serialVersionUID = -5373805339236195440L;

	private String requestId;

	private String errorCode;

	public OSSException(){
		super();
	}

	public OSSException(String message){
		super(message);
	}

	public OSSException(String message, String requestId, String errorCode){
		super(message);
		this.requestId = requestId;
		this.errorCode = errorCode;
	}

	public OSSException(String message, Throwable cause){
		super(message, cause);
	}

	public OSSException(String message, Throwable cause, String requestId, String errorCode){
		super(message, cause);
		this.requestId = requestId;
		this.errorCode = errorCode;
	}

	public String getErrorCode(){
		return errorCode;
	}

	public String getRequestId(){
		return requestId;
	}

}
