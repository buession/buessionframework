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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import org.springframework.lang.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author Yong.Teng
 */
public class ReflectUtils {

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
