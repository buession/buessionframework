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
package com.buession.core.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * 反射工具
 *
 * @author Yong.Teng
 * @since 2.3.2
 */
public class ReflectionUtils {

	private ReflectionUtils() {

	}

	/**
	 * Make the given constructor accessible, explicitly setting it accessible
	 * if necessary. The {@code setAccessible(true)} method is only called
	 * when actually necessary, to avoid unnecessary conflicts with a JVM
	 * SecurityManager (if active).
	 *
	 * @param ctor
	 * 		the constructor to make accessible
	 *
	 * @see java.lang.reflect.Constructor#setAccessible
	 */
	public static void makeAccessible(Constructor<?> ctor) {
		if((Modifier.isPublic(ctor.getModifiers()) == false ||
				Modifier.isPublic(ctor.getDeclaringClass().getModifiers()) == false) && ctor.isAccessible() == false){
			ctor.setAccessible(true);
		}
	}

}
