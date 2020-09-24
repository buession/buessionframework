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

import com.buession.core.utils.ArrayUtils;
import com.buession.core.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 默认 Bean 解析器
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class DefaultBeanResolver implements BeanResolver {

	/**
	 * 属性解析器
	 */
	private PropertyResolver propertyResolver = new DefaultPropertyResolver();

	/**
	 * 属性值转换解析器
	 */
	private ConvertResolver convertResolver = new DefaultConvertResolver();

	private final static Logger logger = LoggerFactory.getLogger(BeanResolver.class);

	/**
	 * 获取属性解析器
	 *
	 * @return 属性解析器
	 */
	public PropertyResolver getPropertyResolver(){
		return propertyResolver;
	}

	/**
	 * 设置属性解析器
	 *
	 * @param propertyResolver
	 * 		属性解析器
	 */
	public void setPropertyResolver(PropertyResolver propertyResolver){
		if(propertyResolver != null){
			this.propertyResolver = propertyResolver;
		}
	}

	/**
	 * 获取属性值转换解析器
	 *
	 * @return 属性值转换解析器
	 */
	public ConvertResolver getConvertResolver(){
		return convertResolver;
	}

	/**
	 * 设置属性值转换解析器
	 *
	 * @param convertResolver
	 * 		属性值转换解析器
	 */
	public void setConvertResolver(ConvertResolver convertResolver){
		if(convertResolver != null){
			this.convertResolver = convertResolver;
		}
	}

	@Override
	public Map<String, Object> describe(final Object bean) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException{
		Assert.isNull(bean, "Describe bean cloud not be null.");

		logger.debug("Describing bean: {}.", bean.getClass().getName());

		final PropertyDescriptor[] descriptors = propertyResolver.getPropertyDescriptors(bean);
		final Class<?> clazz = bean.getClass();
		final Map<String, Object> description = new HashMap<>(descriptors.length);

		for(PropertyDescriptor descriptor : descriptors){
			final String name = descriptor.getName();
			if(PropertyDescriptorUtils.getReadMethod(clazz, descriptor) != null){
				description.put(name, getProperty(bean, name));
			}
		}

		return description;
	}

	@Override
	public void setProperty(final Object bean, final String name, final Object value) throws IllegalAccessException,
			InvocationTargetException{
		if(logger.isTraceEnabled()){
			final StringBuilder sb = new StringBuilder("  setProperty(");

			sb.append(bean).append(", ").append(name).append(", ");

			if(value == null){
				sb.append("<NULL>");
			}else if(value instanceof String){
				sb.append((String) value);
			}else if(value instanceof String[]){
				final String[] values = (String[]) value;
				sb.append('[').append(ArrayUtils.toString(values, ",")).append(']');
			}else{
				sb.append(value.toString());
			}
			sb.append(')');

			logger.trace(sb.toString());
		}

		if(logger.isTraceEnabled()){
			logger.trace("    Target bean = {}, name = {}.", bean, name);
		}

		Class<?> type;

		PropertyDescriptor descriptor = propertyResolver.getPropertyDescriptor(bean, name);
		if(descriptor == null){
			return; // Skip this property setter
		}

		if(descriptor.getWriteMethod() == null){
			logger.debug("Skipping read-only property.");
			return; // Read-only, skip this property setter
		}
		type = descriptor.getPropertyType();

		Object newValue;
		if(type.isArray()){
			if(value instanceof String || value == null){
				newValue = convertResolver.convert(type.getComponentType(), new String[]{(String) value});
			}else if(value instanceof String[]){
				newValue = convertResolver.convert(type.getComponentType(), (String[]) value);
			}else{
				newValue = convertResolver.convert(type.getComponentType(), value);
			}
		}else{
			if(value instanceof String){
				newValue = convertResolver.convert(type, (String) value);
			}else if(value instanceof String[]){
				newValue = convertResolver.convert(type, ((String[]) value)[0]);
			}else{
				newValue = convertResolver.convert(type, value);
			}
		}

		try{
			propertyResolver.setProperty(bean, name, newValue);
		}catch(NoSuchMethodException e){
			throw new InvocationTargetException(e, "Cannot set " + name);
		}
	}

	@Override
	public Object getProperty(final Object bean, final String name) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException{
		return propertyResolver.getProperty(bean, name);
	}

	@Override
	@SuppressWarnings({"unchecked"})
	public void populate(final Object bean, final Object source) throws IllegalAccessException,
			InvocationTargetException{
		Assert.isNull(bean, "No destination bean specified.");

		if(source == null){
			return;
		}

		if(source instanceof Map){
			for(Map.Entry<Object, Object> e : ((Map<Object, Object>) source).entrySet()){
				setProperty(bean, resolverPropertyName(String.valueOf(e.getKey())), e.getValue());
			}
		}else{
			final PropertyDescriptor[] sourceDescriptors = propertyResolver.getPropertyDescriptors(source);

			for(PropertyDescriptor descriptor : sourceDescriptors){
				final String name = descriptor.getName();
				if("class".equals(name)){
					continue;
				}

				final String propertyName = resolverPropertyName(name);
				try{
					final Object value = propertyResolver.getProperty(source, propertyName);
					setProperty(bean, name, value);
				}catch(NoSuchMethodException e){
					CommonHelper.unknownProperty(bean, propertyName);
				}
			}
		}
	}

	private String resolverPropertyName(final String name){
		final StringBuilder sb = new StringBuilder(name.length());

		for(int i = 0; i < name.length(); i++){
			char c = name.charAt(i);

			if(c == '_'){
				sb.append(Character.toUpperCase(name.charAt(++i)));
			}else{
				sb.append(c);
			}
		}

		return sb.toString();
	}

}
