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
package com.buession.web.reactive.http.response;

import com.buession.core.validator.Validate;
import com.buession.web.http.CorsConfig;
import com.buession.web.http.HttpHeader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Yong.Teng
 */
public class ResponseUtils {

	private ResponseUtils(){

	}

	public static void httpCache(final ServerHttpResponse response, final String value){
		if(response != null){
			response.getHeaders().setCacheControl(value);
		}
	}

	public static void httpCache(final ServerHttpResponse response, final int lifetime){
		if(response != null){
			long expiresAt = System.currentTimeMillis() + lifetime * 1000L;
			httpCache(response, lifetime, expiresAt);
		}
	}

	public static void httpCache(final ServerHttpResponse response, final Date date){
		if(response != null){
			long maxAge = date.getTime() - System.currentTimeMillis();
			httpCache(response, maxAge, date.getTime());
		}
	}

	public static void cors(final CorsConfig corsConfig, final ServerHttpRequest request,
							final ServerHttpResponse response){
		HttpHeaders httpHeaders = response.getHeaders();

		if(Validate.isNotEmpty(corsConfig.getOrigins())){
			for(String origin : corsConfig.getOrigins()){
				if("$http_origin".equalsIgnoreCase(origin)){
					String accessControlAllowOrigin = request.getHeaders().getFirst(HttpHeader.ORIGIN.getValue());

					if(Validate.hasText(accessControlAllowOrigin)){
						httpHeaders.setAccessControlAllowOrigin(origin);
					}
				}else{
					httpHeaders.setAccessControlAllowOrigin(origin);
				}
			}
		}

		if(Validate.isNotEmpty(corsConfig.getAllowedMethods())){
			httpHeaders.set(HttpHeader.ACCESS_CONTROL_ALLOW_METHODS.getValue(),
					StringUtils.collectionToCommaDelimitedString(new ArrayList<>(corsConfig.getAllowedMethods())));
		}

		if(Validate.isNotEmpty(corsConfig.getAllowedHeaders())){
			httpHeaders.setAccessControlAllowHeaders(new ArrayList<>(corsConfig.getAllowedHeaders()));
		}

		if(Validate.isNotEmpty(corsConfig.getExposedHeaders())){
			httpHeaders.setAccessControlExposeHeaders(new ArrayList<>(corsConfig.getExposedHeaders()));
		}

		if(corsConfig.getAllowCredentials() != null){
			httpHeaders.setAccessControlAllowCredentials(corsConfig.getAllowCredentials());
		}

		if(corsConfig.getMaxAge() != null){
			httpHeaders.setAccessControlMaxAge(corsConfig.getMaxAge());
		}
	}

	private static void httpCache(final ServerHttpResponse response, final long maxAge, final long expires){
		HttpHeaders httpHeaders = response.getHeaders();
		httpHeaders.setCacheControl(maxAge < 0 ? "no-cache" : "max-age=" + maxAge);
		httpHeaders.setExpires(expires);
		httpHeaders.setPragma(maxAge > 0 ? null : "no-cache");
	}

}
