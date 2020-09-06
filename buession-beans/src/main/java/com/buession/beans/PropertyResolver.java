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

import com.buession.beans.context.DefaultIntrospectionContext;
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import org.apache.commons.beanutils.BeanIntrospector;
import org.apache.commons.beanutils.DefaultBeanIntrospector;
import org.apache.commons.beanutils.MappedPropertyDescriptor;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.SuppressPropertiesBeanIntrospector;
import org.apache.commons.beanutils.expression.DefaultResolver;
import org.apache.commons.beanutils.expression.Resolver;
import org.apache.commons.collections.FastHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 属性工具类
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class PropertyUtils {

	private final static Map<Class<?>, BeanData> DESCRIPTORS_CACHE = new HashMap<>(64);

	private final static List<BeanIntrospector> INTROSPECTORS = new CopyOnWriteArrayList<>();

	private final static Resolver RESOLVER = new DefaultResolver();

	private final static Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

	static{
		INTROSPECTORS.add(DefaultBeanIntrospector.INSTANCE);
		INTROSPECTORS.add(SuppressPropertiesBeanIntrospector.SUPPRESS_CLASS);
	}

}
