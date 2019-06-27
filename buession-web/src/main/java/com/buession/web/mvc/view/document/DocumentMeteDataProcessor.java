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
package com.buession.web.mvc.view.document;

import com.buession.core.validator.Validate;
import com.buession.web.annotation.AbstractProcessor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
@Aspect
@Component
public class DocumentMeteDataProcessor extends AbstractProcessor {

    private final static String DEFAULT_ATTR_NAME = "metedata";

    private final static Logger logger = LoggerFactory.getLogger(DocumentMeteDataProcessor.class);

    @AfterReturning("@annotation(com.buession.web.mvc.view.document.DocumentMeteData)")
    public void documentMeteDataProcessAfter(JoinPoint pjp) throws Throwable{
        Model model = getModel(pjp);

        if(model == null){
            return;
        }

        Class<?> clazz = pjp.getTarget().getClass();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        if(AnnotatedElementUtils.hasAnnotation(clazz, DocumentMeteData.class)){
            addHeadToModelAttribute(model, AnnotatedElementUtils.findMergedAnnotation(clazz, DocumentMeteData
                    .class));
        }

        if(AnnotatedElementUtils.hasAnnotation(method, DocumentMeteData.class)){
            addHeadToModelAttribute(model, AnnotatedElementUtils.findMergedAnnotation(method, DocumentMeteData.class));
        }
    }

    private final static MeteData convert(final DocumentMeteData meteData){
        if(meteData == null){
            return null;
        }

        MeteData meteDataObject = new MeteData();

        meteDataObject.setTitle(meteData.title());
        meteDataObject.setAuthor(meteData.author());
        meteDataObject.setCharset(meteData.charset());
        meteDataObject.setKeywords(meteData.keywords());
        meteDataObject.setDescription(meteData.description());
        meteDataObject.setAuthor(meteDataObject.getAuthor());
        meteDataObject.setCopyright(meteDataObject.getCopyright());

        return meteDataObject;
    }

    private final static void addHeadToModelAttribute(final Model model, final DocumentMeteData meteData){
        if(model == null || meteData == null){
            return;
        }

        String attrName = Validate.hasText(meteData.attrName()) ? meteData.attrName() : DEFAULT_ATTR_NAME;
        model.addAttribute(attrName, convert(meteData));
    }

}
