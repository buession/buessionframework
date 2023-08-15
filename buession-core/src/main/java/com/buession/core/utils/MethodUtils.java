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
package com.buession.core.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 类方法工具类
 *
 * @author Yong.Teng
 * @see org.apache.commons.lang3.reflect.MethodUtils
 */
public class MethodUtils extends org.apache.commons.lang3.reflect.MethodUtils {

	/**
	 * 判断方法是否为静态方法
	 *
	 * @param method
	 * 		方法
	 *
	 * @return 方法是为静态方法，返回 true；否则返回 false
	 */
	public static boolean isStatic(Method method) {
		Assert.isNull(method, "The method cloud not be null.");
		return Modifier.isStatic(method.getModifiers());
	}

	/**
	 * 设置方法权限
	 *
	 * @param method
	 * 		方法
	 */
	public static void setAccessible(Method method) {
		Assert.isNull(method, "The method cloud not be null.");

		if(MemberUtils.isNotAccessible(method) && method.isAccessible() == false){
			method.setAccessible(true);
		}
	}

}
