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

import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

/**
 * @author Yong.Teng
 */
public class PropertyResolverTest {

	@Test
	public void findPropertyType() throws IntrospectionException{

	}

	@Test
	public void getProperty() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		User user = new User();

		user.setId(11);
		user.setMap(Collections.emptyMap());

		PropertyResolver propertyResolver = new DefaultPropertyResolver();
		System.out.println(propertyResolver.getProperty(user, "id"));
		System.out.println(propertyResolver.getProperty(user, "map"));
	}

	@Test
	public void getPropertyDescriptor() throws IllegalAccessException, NoSuchMethodException,
			InvocationTargetException{
		User user = new User();
		PropertyResolver propertyResolver = new DefaultPropertyResolver();
		PropertyDescriptor propertyDescriptor = propertyResolver.getPropertyDescriptor(user, "disable");
		System.out.println(propertyDescriptor);
	}

}
