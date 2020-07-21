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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.servlet.aop.handler;

import com.buession.core.validator.Validate;
import com.buession.web.aop.handler.AbstractResponseHeaderAnnotationHandler;
import com.buession.web.http.HttpHeader;
import com.buession.web.http.response.annotation.ResponseHeader;
import com.buession.web.servlet.aop.AopUtils;
import com.buession.web.servlet.aop.MethodUtils;
import com.buession.web.servlet.http.HttpServlet;
import com.buession.web.servlet.http.response.ResponseUtils;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
public class ServletResponseHeaderAnnotationHandler extends AbstractResponseHeaderAnnotationHandler {

	private final static Logger logger = LoggerFactory.getLogger(ServletResponseHeaderAnnotationHandler.class);

	public ServletResponseHeaderAnnotationHandler(){
		super();
	}

	@Override
	public Void execute(MethodInvocation mi, ResponseHeader responseHeader){
		doExecute(AopUtils.getHttpServlet(mi), responseHeader);
		return null;
	}

	@Override
	public Void execute(Object target, Method method, Object[] arguments, ResponseHeader responseHeader){
		doExecute(MethodUtils.createHttpServletFromArguments(arguments), responseHeader);
		return null;
	}

	private final static void doExecute(final HttpServlet httpServlet, final ResponseHeader responseHeader){
		if(httpServlet == null || httpServlet.getResponse() == null){
			logger.debug("{} is null.", httpServlet == null ? "HttpServlet" : "HttpServletResponse");
			return;
		}

		HttpServletResponse response = httpServlet.getResponse();

		if(HttpHeader.EXPIRES.getValue().equalsIgnoreCase(responseHeader.name()) == true){
			if(Validate.isNumeric(responseHeader.value())){
				ResponseUtils.httpCache(response, Integer.parseInt(responseHeader.value()));
			}else{
				ResponseUtils.httpCache(response, responseHeader.value());
			}
		}else{
			response.setHeader(responseHeader.name(), responseHeader.value());
		}
	}

}
