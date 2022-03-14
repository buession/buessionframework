/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.beans;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean 工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class BeanUtils {

	private final static Map<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<>(32);

	private final static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * 属性映射，与方法 copy 最大的区别是：该方法会将原始对象中下划线属性转换成驼峰命名映射到目标对象中
	 *
	 * @param target
	 * 		目标对象
	 * @param source
	 * 		原始对象
	 */
	public static void populate(final Object target, final Object source){
		populate(target, source, null);
	}

	/**
	 * 属性映射，与方法 copy 最大的区别是：该方法会将原始对象中下划线属性转换成驼峰命名映射到目标对象中
	 *
	 * @param target
	 * 		目标对象
	 * @param source
	 * 		原始对象
	 * @param converter
	 * 		转换器
	 */
	public static void populate(final Object target, final Object source, final Converter converter){
		Assert.isNull(target, "No destination bean specified.");

		if(source == null){
			return;
		}

		if(source instanceof Map){
			HumpBeanUtilsBean humpBeanUtilsBean = new HumpBeanUtilsBean();

			try{
				humpBeanUtilsBean.populate(target, (Map<String, ? extends Object>) source);
			}catch(IllegalAccessException e){
				logger.error("Copy Map to {} error: {}.", target.getClass().getName(), e.getMessage());
			}catch(InvocationTargetException e){
				logger.error("Copy Map to {} error: {}.", target.getClass().getName(), e.getMessage());
			}
			return;
		}

		final String cacheKey = source.getClass().getName() + "_" + target.getClass().getName();
		BeanCopier copier = BEAN_COPIERS.get(cacheKey);

		if(copier == null){
			copier = BeanCopier.create(source.getClass(), target.getClass(), converter != null);
			BEAN_COPIERS.put(cacheKey, copier);
		}
		copier.copy(source, target, converter);
	}

	/**
	 * 属性拷贝；如果源对象是 Map ，将以 key 作为目标对象的属性进行拷贝
	 *
	 * @param target
	 * 		目标对象
	 * @param source
	 * 		原始对象
	 */
	public static void copyProperties(final Object target, final Object source){
		copyProperties(target, source, null);
	}

	/**
	 * 属性拷贝；如果源对象是 Map ，将以 key 作为目标对象的属性进行拷贝
	 *
	 * @param target
	 * 		目标对象
	 * @param source
	 * 		原始对象
	 * @param converter
	 * 		转换器
	 */
	public static void copyProperties(final Object target, final Object source, final Converter converter){
		Assert.isNull(target, "No destination bean specified.");

		if(source == null){
			return;
		}

		if(source instanceof Map){
			try{
				org.apache.commons.beanutils.BeanUtils.populate(target, (Map<String, ?>) source);
			}catch(IllegalAccessException e){
				logger.error("Copy Map to {} error: {}.", target.getClass().getName(), e.getMessage());
			}catch(InvocationTargetException e){
				logger.error("Copy Map to {} error: {}.", target.getClass().getName(), e.getMessage());
			}
			return;
		}

		final String cacheKey = source.getClass().getName() + "_" + target.getClass().getName();
		BeanCopier copier = BEAN_COPIERS.get(cacheKey);

		if(copier == null){
			copier = BeanCopier.create(source.getClass(), target.getClass(), converter != null);
			BEAN_COPIERS.put(cacheKey, copier);
		}
		copier.copy(source, target, converter);
	}

	/**
	 * 将 bean 对象转换成 {@link Map} 对象
	 *
	 * @param bean
	 * 		Bean 对象
	 *
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> toMap(final Object bean){
		Assert.isNull(bean, "No source bean specified.");

		if(bean instanceof Map){
			final Map<?, ?> beanMap = (Map<?, ?>) bean;
			final Map<String, Object> result = new HashMap<>(beanMap.size());

			beanMap.forEach((key, value)->{
				if(key != null){
					result.put(key.toString(), value);
				}
			});

			return result;
		}else{
			BeanMap beanMap = BeanMap.create(bean);
			return new HashMap<>(beanMap);
		}
	}

	private final static class HumpBeanUtilsBean extends BeanUtilsBean {

		private final static Logger logger = LoggerFactory.getLogger(HumpBeanUtilsBean.class);

		public HumpBeanUtilsBean(){
			super();
		}

		public HumpBeanUtilsBean(ConvertUtilsBean convertUtilsBean){
			super(convertUtilsBean);
		}

		public HumpBeanUtilsBean(ConvertUtilsBean convertUtilsBean, PropertyUtilsBean propertyUtilsBean){
			super(convertUtilsBean, propertyUtilsBean);
		}

		@Override
		public void populate(Object bean, Map<String, ? extends Object> properties) throws IllegalAccessException, InvocationTargetException{
			if(bean != null && properties != null){
				if(logger.isDebugEnabled()){
					logger.debug("BeanUtils.populate(" + bean + ", " + properties + ")");
				}

				for(Map.Entry<String, ? extends Object> e : properties.entrySet()){
					if(StringUtils.contains(e.getKey(), "_")){
						StringBuilder sb = new StringBuilder(e.getKey().length());

						for(int i = 0; i < e.getKey().length(); i++){
							char c = e.getKey().charAt(i);

							if(c == '_'){
								c = e.getKey().charAt(++i);
								sb.append((char) (c - 32));
							}else{
								sb.append(c);
							}
						}

						this.setProperty(bean, sb.toString(), e.getValue());
					}else{
						this.setProperty(bean, e.getKey(), e.getValue());
					}
				}
			}
		}

	}

}
