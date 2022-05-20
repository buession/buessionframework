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
import com.buession.core.collect.Arrays;
import com.buession.core.validator.Validate;
import com.buession.web.aop.handler.AbstractCorsAnnotationHandler;
import com.buession.web.http.CorsConfig;
import com.buession.web.http.response.annotation.Cors;
import com.buession.web.servlet.aop.AopUtils;
import com.buession.web.servlet.http.HttpServlet;
import com.buession.web.servlet.http.request.RequestUtils;
import com.buession.web.servlet.http.response.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yong.Teng
 */
public class ServletCorsAnnotationHandler extends AbstractCorsAnnotationHandler {

	private final static Logger logger = LoggerFactory.getLogger(ServletCorsAnnotationHandler.class);

	public ServletCorsAnnotationHandler(){
		super();
	}

	@Override
	public Object execute(MethodInvocation mi, Cors cors){
		HttpServlet httpServlet = AopUtils.getHttpServlet(mi);
		if(httpServlet == null || httpServlet.getRequest() == null || httpServlet.getResponse() == null){
			if(httpServlet == null){
				logger.debug("HttpServlet is null.");
			}else if(httpServlet.getRequest() == null){
				logger.debug("HttpServletRequest is null.");
			}else if(httpServlet.getResponse() == null){
				logger.debug("HttpServletResponse is null.");
			}
			return null;
		}

		HttpServletRequest request = httpServlet.getRequest();
		if(RequestUtils.isAjaxRequest(request) == false){
			logger.warn("Request '{}' without the header 'X-Requested-With'.", request.getRequestURI());
			return null;
		}

		HttpServletResponse response = httpServlet.getResponse();

		CorsConfig.Builder corsConfigBuilder = CorsConfig.Builder.create();

		if(Validate.isNotEmpty(cors.origins())){
			corsConfigBuilder.origins(Arrays.toSet(cors.origins()));
		}

		if(Validate.isNotEmpty(cors.allowedMethods())){
			corsConfigBuilder.allowedMethods(Arrays.toSet(cors.allowedMethods()));
		}

		if(Validate.isNotEmpty(cors.allowedHeaders())){
			corsConfigBuilder.allowedHeaders(Arrays.toSet(cors.allowedHeaders()));
		}

		if(Validate.isNotEmpty(cors.exposedHeaders())){
			corsConfigBuilder.exposedHeaders(Arrays.toSet(cors.exposedHeaders()));
		}

		corsConfigBuilder.allowCredentials(allowCredentials(cors));

		if(cors.maxAge() > -1){
			corsConfigBuilder.maxAge(cors.maxAge());
		}

		ResponseUtils.cors(corsConfigBuilder.build(), request, response);

		return null;
	}

}
