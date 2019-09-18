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
package com.buession.aop.advice;

import com.buession.aop.handler.AnnotationHandler;
import com.buession.aop.resolver.AnnotationResolver;
import com.buession.aop.resolver.DefaultAnnotationResolver;
import com.buession.core.utils.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author Yong.Teng
 */
public abstract class AbstractAnnotationMethodAdvice<A extends Annotation> extends AbstractAdvice implements
        AnnotationMethodAdvice<A> {

    private AnnotationHandler<A> handler;

    private AnnotationResolver resolver;

    private final static HashMap<String, Annotation> ANNOTATIONS_CACHE = new HashMap<>(64);

    public AbstractAnnotationMethodAdvice(AnnotationHandler<A> handler){
        this(handler, new DefaultAnnotationResolver());
    }

    public AbstractAnnotationMethodAdvice(AnnotationHandler<A> handler, AnnotationResolver resolver){
        Assert.isNull(handler, "AnnotationHandler argument cloud not be null.");
        setHandler(handler);
        setResolver(resolver != null ? resolver : new DefaultAnnotationResolver());
    }

    public AnnotationHandler<A> getHandler(){
        return handler;
    }

    public void setHandler(AnnotationHandler<A> handler){
        this.handler = handler;
    }

    public AnnotationResolver getResolver(){
        return resolver;
    }

    public void setResolver(AnnotationResolver resolver){
        this.resolver = resolver;
    }

    @Override
    public boolean isSupport(Object target, Method method){
        return getAnnotation(target, method) != null;
    }

    @Override
    protected Object doInvoke(Object target, Method method, Object[] arguments) throws Throwable{
        return getHandler().execute(target, method, arguments, (A) getAnnotation(target, method));
    }

    protected Annotation getAnnotation(Object object, Method method){
        final Class<A> annotationClazz = getHandler().getAnnotationClass();
        final String key = annotationCacheKey(object, method, annotationClazz);

        Annotation annotation = ANNOTATIONS_CACHE.get(key);

        if(annotation == null){
            annotation = getResolver().getAnnotation(method, annotationClazz);
            if(annotation != null){
                ANNOTATIONS_CACHE.put(key, annotation);
            }
        }

        return annotation;
    }

    protected final String annotationCacheKey(final Object object, final Method method, final Class<A> annotation){
        final StringBuffer sb = new StringBuffer();

        sb.append(object.toString()).append("::").append(method.getName());
        sb.append('_').append(annotation.getName());

        return sb.toString();
    }

}
