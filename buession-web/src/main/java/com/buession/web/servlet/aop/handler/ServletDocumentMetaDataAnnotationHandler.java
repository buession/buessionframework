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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.servlet.aop.handler;

import com.buession.aop.MethodInvocation;
import com.buession.core.validator.Validate;
import com.buession.web.aop.handler.AbstractDocumentMetaDataAnnotationHandler;
import com.buession.web.mvc.view.document.DocumentMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

/**
 * @author Yong.Teng
 */
public class ServletDocumentMetaDataAnnotationHandler extends AbstractDocumentMetaDataAnnotationHandler {

	private final static Logger logger = LoggerFactory.getLogger(ServletDocumentMetaDataAnnotationHandler.class);

	public ServletDocumentMetaDataAnnotationHandler(){
		super();
	}

	@Override
	public Object execute(MethodInvocation mi, DocumentMetaData documentMetaData){
		if(Validate.isNotEmpty(mi.getArguments())){
			Model model = null;

			for(Object argument : mi.getArguments()){
				if(argument instanceof Model){
					model = (Model) argument;
					break;
				}
			}

			if(model == null){
				if(logger.isWarnEnabled()){
					logger.warn("Model is null");
				}
				return null;
			}

			addModelAttribute(model, documentMetaData);
		}

		return null;
	}

}
