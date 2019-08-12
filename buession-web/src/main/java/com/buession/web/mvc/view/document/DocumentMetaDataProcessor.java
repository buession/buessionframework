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
import com.buession.web.servlet.annotation.AbstractProcessor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
@Aspect
@Component
public class DocumentMetaDataProcessor extends AbstractProcessor {

    private final static String DEFAULT_ATTR_NAME = "metadata";

    @AfterReturning("@annotation(com.buession.web.mvc.view.document.DocumentMetaData)")
    public void documentMetaDataProcessAfter(JoinPoint pjp) throws Throwable{
        Model model = getModel(pjp);

        if(model == null){
            return;
        }

        Class<?> clazz = pjp.getTarget().getClass();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        if(AnnotatedElementUtils.hasAnnotation(clazz, DocumentMetaData.class)){
            addHeadToModelAttribute(model, AnnotatedElementUtils.findMergedAnnotation(clazz, DocumentMetaData.class));
        }

        if(AnnotatedElementUtils.hasAnnotation(method, DocumentMetaData.class)){
            addHeadToModelAttribute(model, AnnotatedElementUtils.findMergedAnnotation(method, DocumentMetaData.class));
        }
    }

    private final static MetaData convert(final DocumentMetaData metaData){
        if(metaData == null){
            return null;
        }

        MetaData metaDataObject = new MetaData();

        metaDataObject.setTitle(metaData.title());
        metaDataObject.setAuthor(metaData.author());
        metaDataObject.setCharset(metaData.charset());
        metaDataObject.setKeywords(metaData.keywords());
        metaDataObject.setDescription(metaData.description());
        metaDataObject.setAuthor(metaData.author());
        metaDataObject.setCopyright(metaData.copyright());

        return metaDataObject;
    }

    private final static void addHeadToModelAttribute(final Model model, final DocumentMetaData metaData){
        if(model == null || metaData == null){
            return;
        }

        String attrName = Validate.hasText(metaData.attrName()) ? metaData.attrName() : DEFAULT_ATTR_NAME;
        model.addAttribute(attrName, convert(metaData));
    }

}
