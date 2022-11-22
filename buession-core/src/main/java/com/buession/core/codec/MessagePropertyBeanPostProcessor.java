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
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.codec;

import com.buession.core.utils.ClassUtils;
import com.buession.core.utils.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;

/**
 * @author Yong.Teng
 */
public class MessagePropertyBeanPostProcessor implements BeanPostProcessor {

	private Environment environment;

	private final static Logger logger = LoggerFactory.getLogger(MessagePropertyBeanPostProcessor.class);

	public MessagePropertyBeanPostProcessor(){
	}

	public MessagePropertyBeanPostProcessor(Environment environment){
		this.environment = environment;
	}

	public Environment getEnvironment(){
		return environment;
	}

	public void setEnvironment(Environment environment){
		this.environment = environment;
	}

	@Nullable
	@Override
	public Object postProcessAfterInitialization(Object bean, @Nullable String beanName) throws BeansException{
		Class<?> clazz = bean.getClass();
		Field[] fields = ClassUtils.getAllFields(clazz);
		Message message;

		for(Field field : fields){
			message = AnnotationUtils.getAnnotation(field, Message.class);
			if(message == null){
				continue;
			}

			if(field.getType().isAssignableFrom(MessageObject.class) == false){
				throw new BeanCreationException(
						"The field " + field.getName() + " is not subclass of " + MessageObject.class.getName() +
								", on: " + beanName + "(" + bean.getClass().getName() + ").");
			}

			handleMessageInjected(clazz, bean, beanName, field, message);
		}

		return bean;
	}

	private void handleMessageInjected(final Class<?> clazz, final Object bean, final String beanName,
									   final Field field, final Message message) throws BeansException{
		final String key = message.value();
		final String text = getEnvironment().getProperty(buildProperty(key, message.textField()));

		if(message.required() && text == null){
			throw new IllegalArgumentException(
					"Could not resolve placeholder '" + key + "' in value \"${" + key + "}\", on: " + beanName + "(" +
							bean.getClass().getName() + ").");
		}

		final Integer code = getEnvironment().getProperty(buildProperty(key, message.codeField()), Integer.class);

		if(code != null){
			try{
				final Object beanObject = AopUtils.isCglibProxy(bean) ? getCglibProxyTargetObject(bean) : bean;

				FieldUtils.writeField(field, beanObject, new MessageObject(code, text), true);
			}catch(Exception e){
				throw new BeanCreationException(
						"Exception thrown when handleMessageInjected, on: " + beanName + "(" + clazz.getName() + ")",
						e);
			}
		}
		logger.debug("Parse message '{}', code: {}, text: {}", key, code, text);
	}

	private static Object getCglibProxyTargetObject(Object proxy) throws Exception{
		Field field = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");

		field.setAccessible(true);
		Object dynamicAdvisedInterceptor = field.get(proxy);

		Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
		advised.setAccessible(true);

		return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
	}

	private static String buildProperty(final String prefix, final String value){
		final StringBuilder sb = new StringBuilder(prefix.length() + value.length() + 1);

		sb.append(prefix).append('.').append(value);

		return sb.toString();
	}

}
