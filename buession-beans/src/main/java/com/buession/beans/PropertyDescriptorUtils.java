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
package com.buession.beans;

import org.apache.commons.beanutils.MethodUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * {@link java.beans.PropertyDescriptor} 工具类
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class PropertyDescriptorUtils {

	public static Method getReadMethod(final PropertyDescriptor descriptor){
		return MethodUtils.getAccessibleMethod(descriptor.getReadMethod());
	}

	public static Method getReadMethod(final Class<?> clazz, final PropertyDescriptor descriptor){
		return MethodUtils.getAccessibleMethod(clazz, descriptor.getReadMethod());
	}

	public static Method getWriteMethod(final PropertyDescriptor descriptor){
		return MethodUtils.getAccessibleMethod(descriptor.getWriteMethod());
	}

	public static Method getWriteMethod(final Class<?> clazz, final PropertyDescriptor descriptor){
		return MethodUtils.getAccessibleMethod(clazz, descriptor.getWriteMethod());
	}

	public static boolean equals(final PropertyDescriptor pd, final PropertyDescriptor otherPd){
		if(Objects.equals(pd.getPropertyType(), otherPd.getPropertyType()) == false){
			return false;
		}

		if(Objects.equals(pd.getPropertyEditorClass(), otherPd.getPropertyEditorClass()) == false){
			return false;
		}

		if(Objects.equals(pd.getReadMethod(), otherPd.getReadMethod()) == false){
			return false;
		}

		if(Objects.equals(pd.getWriteMethod(), otherPd.getWriteMethod()) == false){
			return false;
		}

		if(pd.isConstrained() != otherPd.isConstrained()){
			return false;
		}

		if(pd.isBound() != otherPd.isBound()){
			return false;
		}

		return true;
	}

}