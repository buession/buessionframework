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

import com.buession.core.utils.Assert;
import org.apache.commons.beanutils.NestedNullException;

/**
 * @author Yong.Teng
 * @since 1.2.0
 */
class CommonHelper {

	public final static void checkBeanNull(final Object bean){
		Assert.isNull(bean, "No bean specified.");
	}

	public final static void checkBeanNameNull(final Object bean, final String name){
		Assert.isNull(name, "No name specified for bean class '" + bean.getClass() + "'.");
	}

	public final static IllegalArgumentException invalidMappedProperty(final Object bean, final String name,
			final String message){
		return new IllegalArgumentException("Invalid mapped property '" + name + "' on bean class '" + bean.getClass() + "'" + (message == null ? "" : " " + message) + ".");
	}

	public final static NoSuchMethodException unknownProperty(final Object bean, final String name){
		return new NoSuchMethodException("Unknown property '" + name + "'+ on bean class '" + bean.getClass() + "'.");
	}

	public final static NestedNullException nullPropertyValue(final Object bean, final String name){
		return new NestedNullException("Null property value for '" + name + "' on bean class '" + bean.getClass() +
				"'" + ".");
	}

}