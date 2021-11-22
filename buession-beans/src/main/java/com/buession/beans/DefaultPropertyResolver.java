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
import org.apache.commons.beanutils.BeanIntrospector;
import org.apache.commons.beanutils.DefaultBeanIntrospector;
import org.apache.commons.beanutils.SuppressPropertiesBeanIntrospector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 默认属性解析器
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class DefaultPropertyResolver implements PropertyResolver {

	private final static Map<Class<?>, BeanData> DESCRIPTORS_CACHE = new ConcurrentHashMap<>(64);

	private List<BeanIntrospector> introspectors = new CopyOnWriteArrayList<>();

	private final static Logger logger = LoggerFactory.getLogger(DefaultPropertyResolver.class);

	public DefaultPropertyResolver(){
		addDefaultBeanIntrospectors();
	}

	@Override
	public void addBeanIntrospector(final BeanIntrospector introspector){
		Assert.isNull(introspector, "BeanIntrospector cloud not be null.");
		introspectors.add(introspector);
	}

	@Override
	public boolean removeBeanIntrospector(final BeanIntrospector introspector){
		return introspectors.remove(introspector);
	}

	@Override
	public PropertyDescriptor[] getPropertyDescriptors(Object bean){
		CommonHelper.checkBeanNull(bean);
		return getBeanData(bean.getClass()).getDescriptors();
	}

	@Override
	public PropertyDescriptor getPropertyDescriptor(final Object bean, final String name){
		CommonHelper.checkBeanNull(bean);
		CommonHelper.checkBeanNameNull(bean, name);

		return getBeanData(bean.getClass()).getDescriptor(name);
	}

	@Override
	public Object getProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		CommonHelper.checkBeanNull(bean);
		CommonHelper.checkBeanNameNull(bean, name);

		final PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
		if(descriptor == null){
			throw new NoSuchMethodException("Unknown property '" + name + "' on class '" + bean.getClass() + "'.");
		}

		final Method readMethod = PropertyDescriptorUtils.getReadMethod(bean.getClass(), descriptor);
		if(readMethod == null){
			throw new NoSuchMethodException("Property '" + name + "' has no getter method in class '" + bean.getClass() + "'.");
		}

		return invokeMethod(readMethod, bean, new Object[0]);
	}

	@Override
	public void setProperty(final Object bean, final String name, final Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		CommonHelper.checkBeanNull(bean);
		CommonHelper.checkBeanNameNull(bean, name);

		final PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
		if(descriptor == null){
			throw new NoSuchMethodException("Unknown property '" + name + "' on class '" + bean.getClass() + "'.");
		}

		final Method writeMethod = PropertyDescriptorUtils.getWriteMethod(bean.getClass(), descriptor);
		if(writeMethod == null){
			throw new NoSuchMethodException("Property '" + name + "' has no setter method in class '" + bean.getClass() + "'.");
		}

		if(logger.isDebugEnabled()){
			logger.debug("Invoking method {} with value {} (class {}).", writeMethod, value, value == null ? "<null>" : value.getClass().getName());
		}

		invokeMethod(writeMethod, bean, new Object[]{value});
	}

	private void addDefaultBeanIntrospectors(){
		addBeanIntrospector(DefaultBeanIntrospector.INSTANCE);
		addBeanIntrospector(SuppressPropertiesBeanIntrospector.SUPPRESS_CLASS);
	}

	private BeanData getBeanData(final Class<?> beanClass){
		Assert.isNull(beanClass, "No bean class specified.");

		BeanData data = DESCRIPTORS_CACHE.get(beanClass);
		if(data == null){
			data = fetchBeanData(beanClass);
			DESCRIPTORS_CACHE.put(beanClass, data);
		}

		return data;
	}

	private BeanData fetchBeanData(final Class<?> beanClass){
		final DefaultIntrospectionContext context = new DefaultIntrospectionContext(beanClass);

		for(final BeanIntrospector bi : introspectors){
			try{
				bi.introspect(context);
			}catch(IntrospectionException e){
				logger.error("Exception during introspection", e);
			}
		}

		return new BeanData(context.getPropertyDescriptors());
	}

	private Object invokeMethod(final Method method, final Object bean, final Object[] values) throws IllegalAccessException, InvocationTargetException{
		Assert.isNull(bean, "No bean specified this should have been checked before reaching this method.");

		try{
			return method.invoke(bean, values);
		}catch(Exception e){
			StringBuilder sb = new StringBuilder(128);

			sb.append("Method invocation failed: ");
			sb.append("Cannot invoke ").append(method.getDeclaringClass().getName()).append('.').append(method.getName());
			sb.append(" on bean class '").append(bean.getClass()).append('\'');
			sb.append(" - ").append(e.getMessage()).append(" - bad objects of type ");

			sb.append('"');
			if(values != null){
				for(int i = 0; i < values.length; i++){
					if(i > 0){
						sb.append(", ");
					}

					if(values[i] == null){
						sb.append("<null>");
					}else{
						sb.append((values[i]).getClass().getName());
					}
				}
			}
			sb.append('"');

			final Class<?>[] parameterTypes = method.getParameterTypes();

			sb.append(" but expected signature ");
			sb.append('"');

			for(int i = 0; i < parameterTypes.length; i++){
				if(i > 0){
					sb.append(", ");
				}

				sb.append(parameterTypes[i].getName());
			}

			sb.append("\".");

			throw new IllegalArgumentException(sb.toString());
		}
	}

}
