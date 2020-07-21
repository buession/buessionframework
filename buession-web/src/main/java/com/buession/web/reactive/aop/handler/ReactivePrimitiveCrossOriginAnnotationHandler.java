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
package com.buession.web.reactive.aop.handler;

import com.buession.core.validator.Validate;
import com.buession.web.aop.handler.AbstractPrimitiveCrossOriginAnnotationHandler;
import com.buession.web.http.HttpHeader;
import com.buession.web.http.response.annotation.PrimitiveCrossOrigin;
import com.buession.web.reactive.aop.AopUtils;
import com.buession.web.reactive.http.ServerHttp;
import com.buession.web.reactive.http.request.RequestUtils;
import com.buession.web.reactive.aop.MethodUtils;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.lang.reflect.Method;

/**
 * @author Yong.Teng
 */
public class ReactivePrimitiveCrossOriginAnnotationHandler extends AbstractPrimitiveCrossOriginAnnotationHandler {

	private final static Logger logger = LoggerFactory.getLogger(ReactivePrimitiveCrossOriginAnnotationHandler.class);

	public ReactivePrimitiveCrossOriginAnnotationHandler(){
		super();
	}

	@Override
	public Void execute(MethodInvocation mi, PrimitiveCrossOrigin primitiveCrossOrigin){
		doExecute(AopUtils.getServerHttp(mi), primitiveCrossOrigin);
		return null;
	}

	@Override
	public Void execute(Object target, Method method, Object[] arguments, PrimitiveCrossOrigin primitiveCrossOrigin){
		doExecute(MethodUtils.createServerHttpFromArguments(arguments), primitiveCrossOrigin);
		return null;
	}

	private final static void doExecute(final ServerHttp serverHttp, final PrimitiveCrossOrigin primitiveCrossOrigin){
		if(serverHttp == null || serverHttp.getRequest() == null || serverHttp.getResponse() == null){
			if(serverHttp == null){
				logger.debug("ServerHttp is null.");
			}else if(serverHttp.getRequest() == null){
				logger.debug("ServerHttpRequest is null.");
			}else if(serverHttp.getResponse() == null){
				logger.debug("ServerHttpResponse is null.");
			}
			return;
		}

		ServerHttpRequest request = serverHttp.getRequest();

		if(RequestUtils.isAjaxRequest(request) == false){
			logger.warn("Request '{}' without the header 'X-Requested-With'.", request.getURI());
			return;
		}

		String accessControlAllowOrigin = request.getHeaders().getFirst(HttpHeader.ORIGIN.getValue());
		if(Validate.hasText(accessControlAllowOrigin)){
			serverHttp.getResponse().getHeaders().setAccessControlAllowOrigin(accessControlAllowOrigin);
		}
	}

}
