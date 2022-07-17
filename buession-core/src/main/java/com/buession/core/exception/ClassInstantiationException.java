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

import org.springframework.lang.Nullable;

import java.lang.reflect.Constructor;

/**
 * 类实例化异常
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ClassInstantiationException extends NestedRuntimeException {

	private final static long serialVersionUID = -7175870164520231227L;

	private final Class<?> clazz;

	@Nullable
	private final Constructor<?> constructor;

	/**
	 * 构造函数
	 *
	 * @param clazz
	 * 		实例化类
	 * @param message
	 * 		异常信息
	 */
	public ClassInstantiationException(Class<?> clazz, String message){
		this(clazz, message, null);
	}

	/**
	 * 构造函数
	 *
	 * @param clazz
	 * 		实例化类
	 * @param message
	 * 		异常信息
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 */
	public ClassInstantiationException(Class<?> clazz, String message, @Nullable Throwable cause){
		super("Failed to instantiate [" + clazz.getName() + "]: " + message, cause);
		this.clazz = clazz;
		this.constructor = null;
	}

	/**
	 * 构造函数
	 *
	 * @param constructor
	 * 		初始类时的构造函数
	 * @param message
	 * 		异常信息
	 * @param cause
	 * 		原因（保存以供以后通过Throwable.getCause()方法检索）。（允许值为null ，表示原因不存在或未知。）
	 */
	public ClassInstantiationException(Constructor<?> constructor, String message, @Nullable Throwable cause){
		super("Failed to instantiate [" + constructor.getDeclaringClass().getName() + "]: " + message, cause);
		this.clazz = constructor.getDeclaringClass();
		this.constructor = constructor;
	}

	/**
	 * 返回初始化的类
	 *
	 * @return 初始化的类
	 */
	public Class<?> getClazz(){
		return clazz;
	}

	/**
	 * 返回初始类时的构造函数
	 *
	 * @return 初始类时的构造函数
	 */
	@Nullable
	public Constructor<?> getConstructor(){
		return this.constructor;
	}

}
