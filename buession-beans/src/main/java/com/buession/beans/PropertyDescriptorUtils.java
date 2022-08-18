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
package com.buession.beans;

import org.apache.commons.beanutils.MethodUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * {@link java.beans.PropertyDescriptor} 工具类
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class PropertyDescriptorUtils {

	/**
	 * 返回 {@link PropertyDescriptor} 的 getter 方法
	 *
	 * @param descriptor
	 *        {@link PropertyDescriptor}
	 *
	 * @return {@link PropertyDescriptor} 的 getter 方法
	 */
	public static Method getReadMethod(final PropertyDescriptor descriptor){
		return MethodUtils.getAccessibleMethod(descriptor.getReadMethod());
	}

	/**
	 * 返回类 clazz {@link PropertyDescriptor} 的 getter 方法
	 *
	 * @param clazz
	 * 		类
	 * @param descriptor
	 *        {@link PropertyDescriptor}
	 *
	 * @return 类 clazz {@link PropertyDescriptor} 的 getter 方法
	 */
	public static Method getReadMethod(final Class<?> clazz, final PropertyDescriptor descriptor){
		return MethodUtils.getAccessibleMethod(clazz, descriptor.getReadMethod());
	}

	/**
	 * 返回 {@link PropertyDescriptor} 的 setter 方法
	 *
	 * @param descriptor
	 *        {@link PropertyDescriptor}
	 *
	 * @return {@link PropertyDescriptor} 的 setter 方法
	 */
	public static Method getWriteMethod(final PropertyDescriptor descriptor){
		return MethodUtils.getAccessibleMethod(descriptor.getWriteMethod());
	}

	/**
	 * 返回类 clazz {@link PropertyDescriptor} 的 setter 方法
	 *
	 * @param clazz
	 * 		类
	 * @param descriptor
	 *        {@link PropertyDescriptor}
	 *
	 * @return 类 clazz {@link PropertyDescriptor} 的 setter 方法
	 */
	public static Method getWriteMethod(final Class<?> clazz, final PropertyDescriptor descriptor){
		return MethodUtils.getAccessibleMethod(clazz, descriptor.getWriteMethod());
	}

}
