/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.servlet.http.response;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.web.http.CorsConfig;
import com.buession.web.http.HttpHeader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Yong.Teng
 */
public class ResponseUtils {

	private ResponseUtils(){

	}

	public static void httpCache(final HttpServletResponse response, final String value){
		if(response != null){
			response.setHeader(HttpHeader.CACHE_CONTROL.getValue(), value);
		}
	}

	public static void httpCache(final HttpServletResponse response, final int lifetime){
		if(response != null){
			Date date = new Date(System.currentTimeMillis() + lifetime * 1000L);
			httpCache(response, lifetime, date);
		}
	}

	public static void httpCache(final HttpServletResponse response, final Date date){
		if(response != null){
			long maxAge = date.getTime() - System.currentTimeMillis();
			httpCache(response, maxAge, date);
		}
	}

	public static void cors(final CorsConfig corsConfig, final HttpServletRequest request,
							final HttpServletResponse response){
		if(Validate.isNotEmpty(corsConfig.getOrigins())){
			for(String origin : corsConfig.getOrigins()){
				if("$http_origin".equalsIgnoreCase(origin)){
					String accessControlAllowOrigin = request.getHeader(HttpHeader.ORIGIN.getValue());

					if(Validate.hasText(accessControlAllowOrigin)){
						response.setHeader(HttpHeader.ACCESS_CONTROL_ALLOW_ORIGIN.getValue(), origin);
					}
				}else{
					response.setHeader(HttpHeader.ACCESS_CONTROL_ALLOW_ORIGIN.getValue(), origin);
				}
			}
		}

		if(Validate.isNotEmpty(corsConfig.getAllowedMethods())){
			response.setHeader(HttpHeader.ACCESS_CONTROL_ALLOW_METHODS.getValue(),
					StringUtils.join(corsConfig.getAllowedMethods(), ","));
		}

		if(Validate.isNotEmpty(corsConfig.getAllowedHeaders())){
			response.setHeader(HttpHeader.ACCESS_CONTROL_ALLOW_HEADERS.getValue(),
					StringUtils.join(corsConfig.getAllowedHeaders(), ","));
		}

		if(Validate.isNotEmpty(corsConfig.getExposedHeaders())){
			response.setHeader(HttpHeader.ACCESS_CONTROL_EXPOSE_HEADERS.getValue(),
					StringUtils.join(corsConfig.getExposedHeaders(), ","));
		}

		if(corsConfig.getAllowCredentials() != null){
			response.setHeader(HttpHeader.ACCESS_CONTROL_ALLOW_CREDENTIALS.getValue(),
					Boolean.toString(corsConfig.getAllowCredentials()));
		}

		if(corsConfig.getMaxAge() != null){
			response.setHeader(HttpHeader.ACCESS_CONTROL_MAX_AGE.getValue(), Long.toString(corsConfig.getMaxAge()));
		}
	}

	private static void httpCache(final HttpServletResponse response, final long maxAge, final Date expires){
		response.setHeader(HttpHeader.CACHE_CONTROL.getValue(), maxAge < 0 ? "no-cache" : "max-age=" + maxAge);
		response.setDateHeader(HttpHeader.EXPIRES.getValue(), expires.getTime());
		response.setHeader(HttpHeader.PRAGMA.getValue(), maxAge > 0 ? null : "no-cache");
	}

}
