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
package com.buession.web.aop.advice;

import com.buession.aop.advice.AnnotationMethodAdvice;
import com.buession.core.validator.Validate;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author Yong.Teng
 */
public abstract class AbstractHttpAnnotationsMethodAdvice implements HttpAnnotationsMethodAdvice {

    private Collection<AnnotationMethodAdvice> methodAdvices;

    public AbstractHttpAnnotationsMethodAdvice(){
    }

    public AbstractHttpAnnotationsMethodAdvice(Collection<AnnotationMethodAdvice> methodAdvices){
        this.methodAdvices = methodAdvices;
    }

    public Collection<AnnotationMethodAdvice> getMethodAdvices(){
        return methodAdvices;
    }

    public void setMethodAdvices(Collection<AnnotationMethodAdvice> methodAdvices){
        this.methodAdvices = methodAdvices;
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable{
        Collection<AnnotationMethodAdvice> methodAdvices = getMethodAdvices();

        if(Validate.isEmpty(methodAdvices) == false){
            for(AnnotationMethodAdvice methodAdvice : methodAdvices){
                if(methodAdvice.isSupport(target, method)){
                    methodAdvice.invoke(target, method, args);
                }
            }
        }
    }

}
