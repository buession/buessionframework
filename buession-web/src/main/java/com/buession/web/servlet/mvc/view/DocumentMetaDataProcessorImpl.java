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
 * | Copyright @ 2013-2018 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.servlet.mvc.view;

import com.buession.core.validator.Validate;
import com.buession.web.mvc.view.document.DocumentMetaData;
import com.buession.web.mvc.view.document.DocumentMetaDataProcessor;
import com.buession.web.mvc.view.document.MetaDataConvert;
import com.buession.web.servlet.annotation.AbstractProcessor;
import com.buession.web.servlet.http.HttpServlet;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.ui.Model;

import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
@Aspect
public class DocumentMetaDataProcessorImpl extends AbstractProcessor implements DocumentMetaDataProcessor {

    @Pointcut("@annotation(com.buession.web.mvc.view.document.DocumentMetaData)")
    public void documentMetaDataProcess(){
    }

    @After("documentMetaDataProcess()")
    public void documentMetaDataProcessAfter(JoinPoint pjp) throws Throwable{
        HttpServlet httpServlet = getHttpServlet(pjp);

        if(httpServlet == null || httpServlet.getModel() == null){
            return;
        }

        Class<?> clazz = pjp.getTarget().getClass();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        if(AnnotatedElementUtils.hasAnnotation(clazz, DocumentMetaData.class)){
            addHeadToModelAttribute(httpServlet.getModel(), AnnotatedElementUtils.findMergedAnnotation(clazz,
                    DocumentMetaData.class));
        }

        if(AnnotatedElementUtils.hasAnnotation(method, DocumentMetaData.class)){
            addHeadToModelAttribute(httpServlet.getModel(), AnnotatedElementUtils.findMergedAnnotation(method,
                    DocumentMetaData.class));
        }
    }

    private final static void addHeadToModelAttribute(final Model model, final DocumentMetaData metaData){
        if(model == null || metaData == null){
            return;
        }

        String attrName = Validate.hasText(metaData.attrName()) ? metaData.attrName() : DEFAULT_ATTR_NAME;
        model.addAttribute(attrName, MetaDataConvert.convert(metaData));
    }

}
