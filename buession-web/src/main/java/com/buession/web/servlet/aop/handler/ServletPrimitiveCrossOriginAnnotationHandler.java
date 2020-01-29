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

import com.buession.aop.MethodInvocation;
import com.buession.core.validator.Validate;
import com.buession.web.aop.handler.AbstractPrimitiveCrossOriginAnnotationHandler;
import com.buession.web.http.HttpHeader;
import com.buession.web.http.response.annotation.PrimitiveCrossOrigin;
import com.buession.web.servlet.aop.AopUtils;
import com.buession.web.servlet.aop.MethodUtils;
import com.buession.web.servlet.http.HttpServlet;
import com.buession.web.servlet.http.request.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
public class ServletPrimitiveCrossOriginAnnotationHandler extends AbstractPrimitiveCrossOriginAnnotationHandler {

	private final static Logger logger = LoggerFactory.getLogger(ServletPrimitiveCrossOriginAnnotationHandler.class);

	public ServletPrimitiveCrossOriginAnnotationHandler(){
		super();
	}

	@Override
	public void execute(MethodInvocation mi, PrimitiveCrossOrigin primitiveCrossOrigin){
		HttpServlet httpServlet = AopUtils.getHttpServlet(mi);
		doExecute(httpServlet, primitiveCrossOrigin);
	}

	@Override
	public Object execute(Object target, Method method, Object[] arguments, PrimitiveCrossOrigin primitiveCrossOrigin){
		HttpServlet httpServlet = MethodUtils.createHttpServletFromMethodArguments(arguments);
		doExecute(httpServlet, primitiveCrossOrigin);
		return null;
	}

	private final static void doExecute(final HttpServlet httpServlet, final PrimitiveCrossOrigin
			primitiveCrossOrigin){
		if(httpServlet == null || httpServlet.getRequest() == null || httpServlet.getResponse() == null){
			if(httpServlet == null){
				logger.debug("HttpServlet is null.");
			}else if(httpServlet.getRequest() == null){
				logger.debug("HttpServletRequest is null.");
			}else if(httpServlet.getResponse() == null){
				logger.debug("HttpServletResponse is null.");
			}
			return;
		}

		HttpServletRequest request = httpServlet.getRequest();
		if(RequestUtils.isAjaxRequest(request) == false){
			logger.warn("Request '{}' without the header 'X-Requested-With'.", request.getRequestURI());
			return;
		}

		String accessControlAllowOrigin = request.getHeader(HttpHeader.ORIGIN.getValue());
		if(Validate.hasText(accessControlAllowOrigin)){
			httpServlet.getResponse().setHeader(HttpHeader.ACCESS_CONTROL_ALLOW_ORIGIN.getValue(),
					accessControlAllowOrigin);
		}
	}

}
