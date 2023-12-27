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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.reactive.aop.handler;

import com.buession.aop.MethodInvocation;
import com.buession.core.validator.Validate;
import com.buession.web.aop.handler.AbstractHttpCacheAnnotationHandler;
import com.buession.web.http.response.annotation.HttpCache;
import com.buession.web.reactive.http.request.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringValueResolver;

/**
 * Reactive 模式注解 {@link HttpCache} 处理器
 *
 * @author Yong.Teng
 */
public class ReactiveHttpCacheAnnotationHandler extends AbstractHttpCacheAnnotationHandler {

	private final static Logger logger = LoggerFactory.getLogger(ReactiveHttpCacheAnnotationHandler.class);

	/**
	 * 构造函数
	 */
	@Deprecated
	public ReactiveHttpCacheAnnotationHandler() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param stringValueResolver
	 * 		占位符解析器
	 *
	 * @since 2.3.2
	 */
	public ReactiveHttpCacheAnnotationHandler(StringValueResolver stringValueResolver) {
		super(stringValueResolver);
	}

	@Override
	public void execute(MethodInvocation mi, HttpCache httpCache) {
		ServerHttpResponse response = RequestUtils.getResponse();
		if(response == null){
			logger.warn("ServerHttpResponse is null");
			return;
		}

		HttpHeaders httpHeaders = response.getHeaders();
		boolean isSetCacheControl = false;

		if(Validate.hasText(httpCache.cacheControl())){
			if(stringValueResolver == null){
				httpHeaders.setCacheControl(httpCache.cacheControl());
			}else{
				httpHeaders.setCacheControl(stringValueResolver.resolveStringValue(httpCache.cacheControl()));
			}
			isSetCacheControl = true;
		}

		if(Validate.hasText(httpCache.expires())){
			String expires = stringValueResolver == null ? httpCache.expires() :
					stringValueResolver.resolveStringValue(httpCache.expires());

			if(Validate.isNumeric(expires)){
				httpHeaders.setExpires(Long.parseLong(expires));

				if(isSetCacheControl == false){
					httpHeaders.setCacheControl("max-age=" + expires);
				}
			}
		}

		if(Validate.hasText(httpCache.pragma())){
			if(stringValueResolver == null){
				httpHeaders.setPragma(httpCache.pragma());
			}else{
				httpHeaders.setPragma(stringValueResolver.resolveStringValue(httpCache.pragma()));
			}
		}
	}

}
