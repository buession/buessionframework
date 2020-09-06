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
package com.buession.core.utils.beanutils.converters;

/**
 * 数据类型转换异常
 *
 * @author Yong.Teng
 * @since 1.3.0
 */
public class ConversionException extends RuntimeException {

	private final static long serialVersionUID = 7447103473093979500L;

	private Class<?> type;

	private Object value;

	public ConversionException(){
		super();
	}

	public ConversionException(String message){
		super(message);
	}

	public ConversionException(String message, Throwable cause){
		super(message, cause);
	}

	public ConversionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConversionException(Class<?> type, Object value){
		super(typeConvertMessage(type, value));
	}

	public ConversionException(Class<?> type, Object value, Throwable cause){
		super(typeConvertMessage(type, value), cause);
	}

	public ConversionException(Class<?> type, Object value, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace){
		super(typeConvertMessage(type, value), cause, enableSuppression, writableStackTrace);
	}

	public ConversionException(Throwable cause){
		super(cause);
	}

	protected static String typeConvertMessage(Class<?> type, Object value){
		StringBuilder sb = new StringBuilder(32);

		sb.append("Error converting ");

		if(value == null){
			sb.append("'null'");
		}else{
			sb.append('\'').append(value.getClass().getName()).append('\'');
		}

		sb.append(" to ").append(type.getName());

		return sb.toString();
	}

}
