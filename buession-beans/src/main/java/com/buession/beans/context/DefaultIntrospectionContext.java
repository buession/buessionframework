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
package com.buession.beans.context;

import com.buession.core.utils.Assert;
import org.apache.commons.beanutils.IntrospectionContext;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 * @since 1.2.0
 */
public class DefaultIntrospectionContext implements IntrospectionContext {

	private final Class<?> currentClass;

	private final Map<String, PropertyDescriptor> descriptors = new HashMap<>();

	public DefaultIntrospectionContext(final Class<?> clazz){
		currentClass = clazz;
	}

	@Override
	public Class<?> getTargetClass(){
		return currentClass;
	}

	@Override
	public void addPropertyDescriptor(final PropertyDescriptor descriptor){
		Assert.isNull(descriptor, "Property descriptor cloud not be null.");
		descriptors.put(descriptor.getName(), descriptor);
	}

	@Override
	public void addPropertyDescriptors(final PropertyDescriptor[] descriptors){
		Assert.isNull(descriptors, "Descriptors cloud not be null.");

		for(PropertyDescriptor descriptor : descriptors){
			addPropertyDescriptor(descriptor);
		}
	}

	@Override
	public boolean hasProperty(final String name){
		return descriptors.containsKey(name);
	}

	@Override
	public PropertyDescriptor getPropertyDescriptor(final String name){
		return descriptors.get(name);
	}

	@Override
	public void removePropertyDescriptor(final String name){
		descriptors.remove(name);
	}

	@Override
	public Set<String> propertyNames(){
		return descriptors.keySet();
	}

	public PropertyDescriptor[] getPropertyDescriptors(){
		return descriptors.values().toArray(new PropertyDescriptor[]{});
	}

}
