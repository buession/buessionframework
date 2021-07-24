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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.reactive.http.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;

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

	private static void httpCache(final ServerHttpResponse response, final long maxAge, final long expires){
		HttpHeaders httpHeaders = response.getHeaders();
		if(maxAge <= 0){
			httpHeaders.setCacheControl("no-cache");
		}else{
			httpHeaders.setCacheControl("max-age=" + maxAge);
		}
		httpHeaders.setExpires(expires);
		httpHeaders.setPragma(maxAge > 0 ? null : "no-cache");
	}

}
