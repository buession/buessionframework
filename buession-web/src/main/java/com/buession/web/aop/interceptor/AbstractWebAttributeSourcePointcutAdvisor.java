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
package com.buession.web.aop.interceptor;

import com.buession.web.http.response.ContentType;
import com.buession.web.http.response.DisableHttpCache;
import com.buession.web.http.response.EnableHttpCache;
import com.buession.web.http.response.HttpCache;
import com.buession.web.http.response.PrimitiveCrossOrigin;
import com.buession.web.http.response.ResponseHeader;
import com.buession.web.http.response.ResponseHeaders;
import com.buession.web.mvc.view.document.DocumentMetaData;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
public abstract class AbstractWebAttributeSourcePointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

    private final static Class<? extends Annotation>[] WEB_ANNOTATION_CLASSES = new Class[]{ResponseHeader.class,
            ResponseHeaders.class, ContentType.class, PrimitiveCrossOrigin.class, EnableHttpCache.class, HttpCache
            .class, DisableHttpCache.class, DocumentMetaData.class};

    @Override
    public boolean matches(Method method, Class targetClass){
        Method m = method;

        if(hasMethodAnnotationPresent(m)){
            return true;
        }

        if(targetClass != null){
            try{
                m = targetClass.getMethod(m.getName(), m.getParameterTypes());
                return hasMethodAnnotationPresent(m) || hasClassAnnotationPresent(targetClass);
            }catch(NoSuchMethodException e){
            }
        }

        return false;
    }

    private boolean hasClassAnnotationPresent(Class<?> targetClazz){
        for(Class<? extends Annotation> clazz : WEB_ANNOTATION_CLASSES){
            Annotation annotation = AnnotationUtils.findAnnotation(targetClazz, clazz);
            if(annotation != null){
                return true;
            }
        }

        return false;
    }

    private boolean hasMethodAnnotationPresent(Method method){
        for(Class<? extends Annotation> clazz : WEB_ANNOTATION_CLASSES){
            Annotation annotation = AnnotationUtils.findAnnotation(method, clazz);
            if(annotation != null){
                return true;
            }
        }

        return false;
    }

}
