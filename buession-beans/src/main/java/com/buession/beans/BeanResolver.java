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
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class BeanUtils {

	public final static Object[] EMPTY_OBJECT_ARRAY = new Object[0];

	private final static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/*public static Map<String, String> describe(final Object bean) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException{
		Assert.isNull(bean, "Describe bean cloud not be null.");

		logger.debug("Describing bean: {}.", bean.getClass().getName());

		final PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(bean);
		final Class<?> clazz = bean.getClass();
		final Map<String, String> description = new HashMap<>(descriptors.length);

		for(PropertyDescriptor descriptor : descriptors){
			final String name = descriptor.getName();
			if(PropertyDescriptorUtils.getReadMethod(clazz, descriptor) != null){
				description.put(name, getProperty(bean, name));
			}
		}

		return description;
	}

	public static String getProperty(final Object bean, final String name) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException{
		final Object value = PropertyUtils.getNestedProperty(bean, name);

	}*/

}
