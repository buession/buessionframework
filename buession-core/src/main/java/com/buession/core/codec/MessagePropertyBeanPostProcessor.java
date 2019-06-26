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
 * | Copyright @ 2013-2019 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException{
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException{
        Class clazz = bean.getClass();
        List<Field> fields = getAllFields(clazz);

        for(Field field : fields){
            if(field.getType().isAssignableFrom(MessageObject.class) == false){
                throw new BeanCreationException("The field " + field.getName() + " is not subclass of " +
                        MessageObject.class.getName() + ", on: " + beanName + "(" + bean.getClass().getName() + ").");
            }

            final Message message = AnnotationUtils.getAnnotation(field, Message.class);

            try{
                handleMessageInjected(bean, beanName, field, message);
            }catch(IllegalAccessException e){
                if(message.required()){
                    throw new BeanCreationException("Exception thrown when handleMessageInjected, on: " + beanName +
                            "(" + clazz.getName() + ")", e);
                }
            }
        }

        return bean;
    }

    private void handleMessageInjected(final Object bean, final String beanName, final Field field, final Message
            message) throws IllegalAccessException{
        final String key = message.value();
        final String text = getEnvironment().getProperty(key + "." + message.textField());

        if(message.required() && text == null){
            throw new IllegalArgumentException("Could not resolve placeholder '" + key + "' in value \"${" + key +
                    "}\", on: " + beanName + "(" + bean.getClass().getName() + ").");
        }

        final int code = getEnvironment().getProperty(key + "." + message.codeField(), Integer.class);

        field.setAccessible(true);

        if(AopUtils.isCglibProxy(bean)){
            try{
                field.set(getCglibProxyTargetObject(bean), new MessageObject(code, text));
            }catch(Exception e){
            }
        }else{
            field.set(bean, new MessageObject(code, text));
        }
        logger.debug("Parse message '{}', code: {}, text: {}", key, code, text);
    }

    private final static List<Field> getAllFields(final Class<?> clazz){
        final List<Field> allFields = new ArrayList<>(4);
        Class<?> currentClass = clazz;

        while(currentClass != null){
            final Field[] declaredFields = currentClass.getDeclaredFields();

            for(final Field field : declaredFields){
                if(field.isAnnotationPresent(Message.class)){
                    allFields.add(field);
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return allFields;
    }

    private static Object getCglibProxyTargetObject(Object proxy) throws Exception{
        Field field = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");

        field.setAccessible(true);
        Object dynamicAdvisedInterceptor = field.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }

}
