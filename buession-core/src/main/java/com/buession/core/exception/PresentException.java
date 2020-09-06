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
package com.buession.core.exception;

/**
 * @author Yong.Teng
 * @since 1.2.0
 */
public class PresentException extends RuntimeException {

	private static final long serialVersionUID = -1610503662713551445L;

	public PresentException(){
		super();
	}

	public PresentException(String message){
		super(message);
	}

	public PresentException(String message, Throwable cause){
		super(message, cause);
	}

	public PresentException(Throwable cause){
		super(cause);
	}

	public PresentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PresentException(String delegate, String original){
		super(delegateExceptionMessage(delegate, original));
	}

	public PresentException(String delegate, String original, Throwable cause){
		super(delegateExceptionMessage(delegate, original), cause);
	}

	public PresentException(String delegate, String original, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace){
		super(delegateExceptionMessage(delegate, original), cause, enableSuppression, writableStackTrace);
	}

	protected static String delegateExceptionMessage(String delegate, String original){
		StringBuilder sb = new StringBuilder(32);

		sb.append(delegate).append(" must be present for ").append(original).append('.');

		return sb.toString();
	}

}
