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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 1.2.0
 */
public class BeanData {

	private final PropertyDescriptor[] descriptors;

	private final Map<String, String> readMethodNames;

	private final Map<String, String> writeMethodNames;

	private final static Logger logger = LoggerFactory.getLogger(BeanData.class);

	public BeanData(final PropertyDescriptor[] descriptors){
		this(descriptors, setReadMethodNames(descriptors), setWriteMethodNames(descriptors));
	}

	public BeanData(final PropertyDescriptor[] descriptors, final Map<String, String> readMethodNames, final Map<String, String> writeMethodNames){
		this.descriptors = descriptors;
		this.readMethodNames = readMethodNames;
		this.writeMethodNames = writeMethodNames;
	}

	public PropertyDescriptor[] getDescriptors(){
		return descriptors;
	}

	public PropertyDescriptor getDescriptor(final String name){
		for(PropertyDescriptor pd : descriptors){
			if(name.equals(pd.getName())){
				return pd;
			}
		}

		return null;
	}

	/**
	 * 获取 bean 指定属性的 read 方法
	 *
	 * @param beanClazz
	 * 		bean class
	 * @param descriptor
	 *
	 * @return
	 */
	public Method getReadMethod(final Class<?> beanClazz, final PropertyDescriptor descriptor){
		Method method = descriptor.getReadMethod();

		if(method == null){
			final String methodName = readMethodNames.get(descriptor.getName());

			if(methodName != null){
				method = MethodUtils.getAccessibleMethod(beanClazz, methodName, descriptor.getPropertyType());

				if(method != null){
					try{
						descriptor.setReadMethod(method);
					}catch(IntrospectionException e){
						logger.error("Call PropertyDescriptor.setReadMethod() with method: {}::{} failure: {}.", beanClazz.getName(), methodName, e.getMessage(), e);
					}
				}
			}
		}

		return method;
	}

	public Method getWriteMethod(final Class<?> beanClazz, final PropertyDescriptor descriptor){
		Method method = descriptor.getWriteMethod();

		if(method == null){
			final String methodName = writeMethodNames.get(descriptor.getName());

			if(methodName != null){
				method = MethodUtils.getAccessibleMethod(beanClazz, methodName, descriptor.getPropertyType());

				if(method != null){
					try{
						descriptor.setWriteMethod(method);
					}catch(IntrospectionException e){
						logger.error("Call PropertyDescriptor.setWriteMethod() with method: {}::{} failure: {}.", beanClazz.getName(), methodName, e.getMessage(), e);
					}
				}
			}
		}

		return method;
	}

	private static Map<String, String> setReadMethodNames(final PropertyDescriptor[] descriptors){
		final Map<String, String> methods = new HashMap<>(descriptors.length);

		for(PropertyDescriptor pd : descriptors){
			Method method = pd.getReadMethod();
			if(method != null){
				methods.put(pd.getName(), method.getName());
			}
		}

		return methods;
	}

	private static Map<String, String> setWriteMethodNames(final PropertyDescriptor[] descriptors){
		final Map<String, String> methods = new HashMap<>(descriptors.length);

		for(PropertyDescriptor pd : descriptors){
			Method method = pd.getWriteMethod();
			if(method != null){
				methods.put(pd.getName(), method.getName());
			}
		}

		return methods;
	}

}
