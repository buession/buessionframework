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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class ReflectUtils {

    private final static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

    protected ReflectUtils(){

    }

    /**
     * 设置属性权限
     *
     * @param field
     *         属性
     */
    public final static void setFieldAccessible(Field field){
        if(field != null && field.isAccessible() == false){
            field.setAccessible(true);
        }
    }

    /**
     * 判断属性是否为静态属性
     *
     * @param field
     *         属性
     *
     * @return 属性是为静态属性，返回 true；否则返回 false
     */
    public final static boolean isStaticField(Field field){
        return field != null && Modifier.isStatic(field.getModifiers());
    }

    /**
     * 判断方法是否为静态方法
     *
     * @param method
     *         方法
     *
     * @return 方法是为静态方法，返回 true；否则返回 false
     */
    public final static boolean isStaticMethod(Method method){
        return method != null && Modifier.isStatic(method.getModifiers());
    }

    /**
     * 给对象属性的赋于新值
     *
     * @param object
     *         对象
     * @param field
     *         属性
     * @param value
     *         值
     */
    public final static void setField(@Nullable Object object, @Nullable Field field, Object value){
        setFieldAccessible(field);
        try{
            field.set(object, value);
        }catch(IllegalAccessException ex){
            handleReflectionException(ex);
        }
    }

    public final static <E> void setter(E entity, String setterName, Class<?> javaType, Object value) throws
            NoSuchFieldException{
        setter((Class<E>) entity.getClass(), entity, setterName, javaType, value);
    }

    public final static <E> void setter(Class<E> clazz, E entity, String setterName, Class<?> javaType, Object value)
            throws NoSuchFieldException{
        try{
            Method method = clazz.getMethod("set" + StringUtils.upperCase(setterName), javaType);

            if(isStaticMethod(method) == false){
                method.invoke(entity, value);
                return;
            }
        }catch(NoSuchMethodException ex){
            logger.warn("{}", ex.getMessage());
        }catch(InvocationTargetException ex){
            logger.warn("{}", ex.getMessage());
        }catch(IllegalAccessException ex){
            logger.warn("{}", ex.getMessage());
        }

        Field field = clazz.getField(setterName);

        if(isStaticField(field) == false){
            setField(entity, field, value);
        }
    }

    public final static <E, V> V getter(Class<E> clazz, E entity, String setterName, Class<?> javaType){
        return null;
    }

    /**
     * 将实体类转换为 Map
     *
     * @param entity
     *         实体类
     * @param <E>
     *         实体类类型
     *
     * @return Map 对象
     */
    public final static <E> Map<String, Object> entityConvertMap(final E entity){
        if(entity == null){
            return null;
        }

        Class<?> clazz = entity.getClass();
        String entityName = clazz.getName();
        Method[] methods = clazz.getMethods();
        Field[] fields = clazz.getFields();
        Map<String, Object> result = new HashMap<>(methods.length);

        for(Method method : methods){
            if(isStaticMethod(method)){
                continue;
            }

            String methodName = method.getName();
            if(methodName.startsWith("get") == false){
                continue;
            }

            String name = StringUtils.uncapitalize(methodName.substring(3));

            try{
                result.put(name, method.invoke(entity));
            }catch(IllegalAccessException ex){
                logger.warn("Call method {}::{} failure: {}", entityName, method.toGenericString(), ex.getMessage());
            }catch(InvocationTargetException ex){
                logger.warn("Call method {}::{} failure: {}", entityName, method.toGenericString(), ex.getMessage());
            }
        }

        for(Field field : fields){
            if(isStaticField(field)){
                continue;
            }

            if(result.containsKey(field.getName())){
                continue;
            }

            try{
                result.put(field.getName(), field.get(entity));
            }catch(IllegalAccessException ex){
                logger.warn("Read field {}::{} failure: {}", entityName, field.getName(), ex.getMessage());
            }
        }

        return result;
    }

    public static void handleReflectionException(Exception ex){
        if(ex instanceof NoSuchMethodException){
            throw new IllegalStateException("Method not found: " + ex.getMessage());
        }else if(ex instanceof IllegalAccessException){
            throw new IllegalStateException("Could not access method or field: " + ex.getMessage());
        }else if(ex instanceof InvocationTargetException){
            handleInvocationTargetException((InvocationTargetException) ex);
        }else if(ex instanceof RuntimeException){
            throw (RuntimeException) ex;
        }else{
            throw new UndeclaredThrowableException(ex);
        }
    }

    protected final static void handleInvocationTargetException(InvocationTargetException ex){
        rethrowRuntimeException(ex.getTargetException());
    }

    protected final static void rethrowRuntimeException(Throwable ex){
        if(ex instanceof RuntimeException){
            throw (RuntimeException) ex;
        }else if(ex instanceof Error){
            throw (Error) ex;
        }else{
            throw new UndeclaredThrowableException(ex);
        }
    }

}
