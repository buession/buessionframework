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
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.exception;

/**
 * 数据类型转换异常
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class ConversionException extends RuntimeException {

	private final static long serialVersionUID = 7447103473093979500L;

	/**
	 * 待转换源类型
	 *
	 * @since 2.3.1
	 */
	private Class<?> sourceType;

	/**
	 * 待转换目标类型
	 *
	 * @since 2.3.1
	 */
	private Class<?> targetType;

	/**
	 * 待转换值
	 */
	private Object value;

	/**
	 * 构造函数
	 */
	public ConversionException() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * 		异常信息
	 */
	public ConversionException(String message) {
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
	public ConversionException(String message, Throwable cause) {
		super(message, cause);
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
	public ConversionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		待转换的类
	 * @param value
	 * 		待转换的值
	 */
	public ConversionException(Class<?> type, Object value) {
		this(null, type, value, typeConvertMessage(type, value));
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		待转换的类
	 * @param value
	 * 		待转换的值
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 */
	public ConversionException(Class<?> type, Object value, Throwable cause) {
		this(null, type, value, typeConvertMessage(type, value), cause);
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		待转换的类
	 * @param value
	 * 		待转换的值
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 * @param enableSuppression
	 * 		是否启用抑制
	 * @param writableStackTrace
	 * 		堆栈跟踪是否应该是可写的
	 */
	public ConversionException(Class<?> type, Object value, Throwable cause, boolean enableSuppression,
							   boolean writableStackTrace) {
		this(null, type, value, typeConvertMessage(type, value), cause, enableSuppression, writableStackTrace);
	}

	/**
	 * 构造函数
	 *
	 * @param targetType
	 * 		待转换目标类型
	 * @param type
	 * 		待转换的源类型
	 * @param value
	 * 		待转换的值
	 *
	 * @since 2.3.1
	 */
	public ConversionException(Class<?> targetType, Class<?> type, Object value) {
		this(type, value);
		this.targetType = targetType;
	}

	/**
	 * 构造函数
	 *
	 * @param targetType
	 * 		待转换目标类型
	 * @param type
	 * 		待转换的源类型
	 * @param value
	 * 		待转换的值
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 *
	 * @since 2.3.1
	 */
	public ConversionException(Class<?> targetType, Class<?> type, Object value, Throwable cause) {
		this(type, value, cause);
		this.targetType = targetType;
	}

	/**
	 * 构造函数
	 *
	 * @param targetType
	 * 		待转换目标类型
	 * @param type
	 * 		待转换的源类型
	 * @param value
	 * 		待转换的值
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 * @param enableSuppression
	 * 		是否启用抑制
	 * @param writableStackTrace
	 * 		堆栈跟踪是否应该是可写的
	 *
	 * @since 2.3.1
	 */
	public ConversionException(Class<?> targetType, Class<?> type, Object value, Throwable cause,
							   boolean enableSuppression, boolean writableStackTrace) {
		this(type, value, cause, enableSuppression, writableStackTrace);
		this.targetType = targetType;
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		待转换的类
	 * @param value
	 * 		待转换的值
	 * @param message
	 * 		异常信息
	 *
	 * @since 2.3.1
	 */
	public ConversionException(Class<?> type, Object value, String message) {
		super(message);
		this.sourceType = type;
		this.value = value;
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		待转换的类
	 * @param value
	 * 		待转换的值
	 * @param message
	 * 		异常信息
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 *
	 * @since 2.3.1
	 */
	public ConversionException(Class<?> type, Object value, String message, Throwable cause) {
		super(message, cause);
		this.sourceType = type;
		this.value = value;
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		待转换的类
	 * @param value
	 * 		待转换的值
	 * @param message
	 * 		异常信息
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 * @param enableSuppression
	 * 		是否启用抑制
	 * @param writableStackTrace
	 * 		堆栈跟踪是否应该是可写的
	 *
	 * @since 2.3.1
	 */
	public ConversionException(Class<?> type, Object value, String message, Throwable cause, boolean enableSuppression,
							   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.sourceType = type;
		this.value = value;
	}

	/**
	 * 构造函数
	 *
	 * @param targetType
	 * 		待转换目标类型
	 * @param type
	 * 		待转换的源类型
	 * @param value
	 * 		待转换的值
	 * @param message
	 * 		异常信息
	 *
	 * @since 2.3.1
	 */
	public ConversionException(Class<?> targetType, Class<?> type, Object value, String message) {
		this(type, value, message);
		this.targetType = targetType;
	}

	/**
	 * 构造函数
	 *
	 * @param targetType
	 * 		待转换目标类型
	 * @param type
	 * 		待转换的源类型
	 * @param value
	 * 		待转换的值
	 * @param message
	 * 		异常信息
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 *
	 * @since 2.3.1
	 */
	public ConversionException(Class<?> targetType, Class<?> type, Object value, String message, Throwable cause) {
		this(type, value, message, cause);
		this.targetType = targetType;
	}

	/**
	 * 构造函数
	 *
	 * @param targetType
	 * 		待转换目标类型
	 * @param type
	 * 		待转换的源类型
	 * @param value
	 * 		待转换的值
	 * @param message
	 * 		异常信息
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 * @param enableSuppression
	 * 		是否启用抑制
	 * @param writableStackTrace
	 * 		堆栈跟踪是否应该是可写的
	 *
	 * @since 2.3.1
	 */
	public ConversionException(Class<?> targetType, Class<?> type, Object value, String message, Throwable cause,
							   boolean enableSuppression, boolean writableStackTrace) {
		this(type, value, message, cause, enableSuppression, writableStackTrace);
		this.targetType = targetType;
	}

	/**
	 * 构造函数
	 *
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 */
	public ConversionException(Throwable cause) {
		super(cause);
	}

	/**
	 * 返回待转换源类型
	 *
	 * @return 待转换源类型
	 *
	 * @since 2.3.1
	 */
	public Class<?> getSourceType() {
		return sourceType;
	}

	/**
	 * 返回待转换目标类型
	 *
	 * @return 待转换目标类型
	 *
	 * @since 2.3.1
	 */
	public Class<?> getTargetType() {
		return targetType;
	}

	/**
	 * 返回待转换换值
	 *
	 * @return 待转换换值
	 *
	 * @since 2.3.1
	 */
	public Object getValue() {
		return value;
	}

	protected static String typeConvertMessage(Class<?> type, Object value) {
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
