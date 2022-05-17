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
package com.buession.web.reactive.aop.handler;

import com.buession.aop.MethodInvocation;
import com.buession.core.validator.Validate;
import com.buession.web.aop.handler.AbstractCorsAnnotationHandler;
import com.buession.web.http.HttpHeader;
import com.buession.web.http.response.annotation.Cors;
import com.buession.web.reactive.aop.AopUtils;
import com.buession.web.reactive.http.ServerHttp;
import com.buession.web.reactive.http.request.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author Yong.Teng
 */
public class ReactiveCorsAnnotationHandler extends AbstractCorsAnnotationHandler {

	private final static Logger logger = LoggerFactory.getLogger(ReactiveCorsAnnotationHandler.class);

	public ReactiveCorsAnnotationHandler(){
		super();
	}

	@Override
	public Object execute(MethodInvocation mi, Cors cors){
		ServerHttp serverHttp = AopUtils.getServerHttp(mi);
		if(serverHttp == null || serverHttp.getRequest() == null || serverHttp.getResponse() == null){
			if(serverHttp == null){
				logger.debug("ServerHttp is null.");
			}else if(serverHttp.getRequest() == null){
				logger.debug("ServerHttpRequest is null.");
			}else if(serverHttp.getResponse() == null){
				logger.debug("ServerHttpResponse is null.");
			}
			return null;
		}

		ServerHttpRequest request = serverHttp.getRequest();

		if(RequestUtils.isAjaxRequest(request) == false){
			logger.warn("Request '{}' without the header 'X-Requested-With'.", request.getURI());
			return null;
		}

		HttpHeaders httpHeaders = serverHttp.getResponse().getHeaders();

		if(Validate.isNotEmpty(cors.origins())){
			for(String origin : cors.origins()){
				if(isDynamicOrigin(origin)){
					String accessControlAllowOrigin = request.getHeaders().getFirst(HttpHeader.ORIGIN.getValue());

					if(Validate.hasText(accessControlAllowOrigin)){
						httpHeaders.setAccessControlAllowOrigin(origin);
					}
				}else{
					httpHeaders.setAccessControlAllowOrigin(origin);
				}
			}
		}

		if(Validate.isNotEmpty(cors.allowedMethods())){
			httpHeaders.set(HttpHeader.ACCESS_CONTROL_ALLOW_METHODS.getValue(),
					StringUtils.collectionToCommaDelimitedString(Arrays.asList(cors.allowedMethods())));
		}

		if(Validate.isNotEmpty(cors.allowedHeaders())){
			httpHeaders.setAccessControlAllowHeaders(Arrays.asList(cors.allowedHeaders()));
		}

		if(Validate.isNotEmpty(cors.exposedHeaders())){
			httpHeaders.setAccessControlExposeHeaders(Arrays.asList(cors.exposedHeaders()));
		}

		Boolean allowCredentials = allowCredentials(cors);
		if(allowCredentials != null){
			httpHeaders.setAccessControlAllowCredentials(allowCredentials);
		}

		if(cors.maxAge() > -1){
			httpHeaders.setAccessControlMaxAge(cors.maxAge());
		}

		return null;
	}

}
