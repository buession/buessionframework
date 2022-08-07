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
import com.buession.web.aop.handler.AbstractContentTypeAnnotationHandler;
import com.buession.web.http.response.annotation.ContentType;
import com.buession.web.reactive.http.request.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.nio.charset.Charset;

/**
 * @author Yong.Teng
 */
public class ReactiveContentTypeAnnotationHandler extends AbstractContentTypeAnnotationHandler {

	private final static Logger logger = LoggerFactory.getLogger(ReactiveContentTypeAnnotationHandler.class);

	public ReactiveContentTypeAnnotationHandler(){
		super();
	}

	@Override
	public void execute(MethodInvocation mi, ContentType contentType){
		ServerHttpResponse response = RequestUtils.getResponse();
		if(response == null){
			if(logger.isWarnEnabled()){
				logger.warn("ServerHttpResponse is null");
			}
			return;
		}

		String mime = contentType.mime();
		int i = mime.indexOf('/');
		Charset charset = Charset.forName(contentType.charset());
		String type = mime.substring(0, i - 1);
		String subType = mime.substring(i);

		response.getHeaders().setContentType(new MediaType(type, subType, charset));
	}

}
