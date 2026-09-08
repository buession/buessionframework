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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.aop.handler;

import com.buession.aop.MethodInvocation;
import com.buession.aop.handler.AbstractAnnotationHandler;
import com.buession.core.validator.Validate;
import com.buession.web.mvc.view.document.DocumentMetaData;
import com.buession.web.mvc.view.document.MetaData;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.util.StringValueResolver;

/**
 * 注解 {@link DocumentMetaData} 处理器抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractDocumentMetaDataAnnotationHandler extends AbstractAnnotationHandler<DocumentMetaData>
		implements DocumentMetaDataAnnotationHandler {

	/**
	 * 构造函数
	 */
	public AbstractDocumentMetaDataAnnotationHandler() {
		super(DocumentMetaData.class);
	}

	/**
	 * 构造函数
	 *
	 * @param stringValueResolver
	 * 		占位符解析器
	 *
	 * @since 2.3.2
	 */
	public AbstractDocumentMetaDataAnnotationHandler(StringValueResolver stringValueResolver) {
		super(DocumentMetaData.class, stringValueResolver);
	}

	@Override
	public void execute(MethodInvocation mi, DocumentMetaData documentMetaData) {
		if(Validate.isNotEmpty(mi.getArguments())){
			for(Object argument : mi.getArguments()){
				if(argument instanceof Model){
					addModelAttribute((Model) argument, documentMetaData);
					break;
				}
			}
		}
	}

	protected void addModelAttribute(final Model model, final DocumentMetaData metaData) {
		final String attrName = Validate.hasText(metaData.attrName()) ? metaData.attrName() :
				DocumentMetaData.DEFAULT_ATTR_NAME;
		model.addAttribute(attrName, metaDataConvert(metaData));
	}

	private MetaData metaDataConvert(final DocumentMetaData documentMetaData) {
		if(stringValueResolver == null){
			return new MetaData(documentMetaData.title(), documentMetaData.charset(), documentMetaData.keywords(),
					documentMetaData.description(), documentMetaData.author(), documentMetaData.copyright());
		}else{
			return new MetaData(stringValueResolver.resolveStringValue(documentMetaData.title()),
					stringValueResolver.resolveStringValue(documentMetaData.charset()),
					stringValueResolver.resolveStringValue(documentMetaData.keywords()),
					stringValueResolver.resolveStringValue(documentMetaData.description()),
					stringValueResolver.resolveStringValue(documentMetaData.author()),
					stringValueResolver.resolveStringValue(documentMetaData.copyright()));
		}
	}

}
