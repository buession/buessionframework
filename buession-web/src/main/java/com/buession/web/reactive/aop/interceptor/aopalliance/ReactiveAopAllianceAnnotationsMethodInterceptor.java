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
package com.buession.web.reactive.aop.interceptor.aopalliance;

import com.buession.aop.aopalliance.AopAllianceUtils;
import com.buession.aop.interceptor.AnnotationMethodInterceptor;
import com.buession.aop.resolver.AnnotationResolver;
import com.buession.aop.resolver.SpringAnnotationResolver;
import com.buession.web.reactive.aop.interceptor.AbstractReactiveAnnotationsMethodInterceptor;
import com.buession.web.reactive.aop.interceptor.ReactiveContentTypeAnnotationMethodInterceptor;
import com.buession.web.reactive.aop.interceptor.ReactiveDisableHttpCacheAnnotationMethodInterceptor;
import com.buession.web.reactive.aop.interceptor.ReactiveDocumentMetaDataAnnotationMethodInterceptor;
import com.buession.web.reactive.aop.interceptor.ReactiveEnableHttpCacheAnnotationMethodInterceptor;
import com.buession.web.reactive.aop.interceptor.ReactivePrimitiveCrossOriginAnnotationMethodInterceptor;
import com.buession.web.reactive.aop.interceptor.ReactiveResponseHeaderAnnotationMethodInterceptor;
import com.buession.web.reactive.aop.interceptor.ReactiveResponseHeadersAnnotationMethodInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class ReactiveAopAllianceAnnotationsMethodInterceptor extends AbstractReactiveAnnotationsMethodInterceptor
        implements MethodInterceptor {

    public ReactiveAopAllianceAnnotationsMethodInterceptor(){
        List<AnnotationMethodInterceptor> methodInterceptors = new ArrayList<>(7);
        AnnotationResolver resolver = new SpringAnnotationResolver();

        methodInterceptors.add(new ReactiveResponseHeadersAnnotationMethodInterceptor(resolver));
        methodInterceptors.add(new ReactiveResponseHeaderAnnotationMethodInterceptor(resolver));
        methodInterceptors.add(new ReactiveContentTypeAnnotationMethodInterceptor(resolver));
        methodInterceptors.add(new ReactivePrimitiveCrossOriginAnnotationMethodInterceptor(resolver));
        methodInterceptors.add(new ReactiveEnableHttpCacheAnnotationMethodInterceptor(resolver));
        methodInterceptors.add(new ReactiveDisableHttpCacheAnnotationMethodInterceptor(resolver));
        methodInterceptors.add(new ReactiveDocumentMetaDataAnnotationMethodInterceptor(resolver));

        setMethodInterceptors(methodInterceptors);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable{
        return super.invoke(AopAllianceUtils.createMethodInvocation(mi));
    }

}
