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
 * | Copyright @ 2013-2018 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.servlet.http.response;

import com.buession.web.http.HttpHeader;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Yong.Teng
 */
public class ResponseUtils {

	private ResponseUtils(){

	}

	public final static void httpCache(final HttpServletResponse response, final String value){
		if(response != null){
			response.setHeader(HttpHeader.CACHE_CONTROL.name(), value);
		}
	}

	public final static void httpCache(final HttpServletResponse response, final int lifetime){
		if(response != null){
			Date date = new Date(System.currentTimeMillis() + lifetime * 1000);
			httpCache(response, lifetime, date);
		}
	}

	public final static void httpCache(final HttpServletResponse response, final Date date){
		if(response != null){
			long maxAge = date.getTime() - System.currentTimeMillis();
			httpCache(response, maxAge, date);
		}
	}

	private final static void httpCache(final HttpServletResponse response, final long maxAge, final Date expires){
		if(maxAge <= 0){
			response.setHeader(HttpHeader.CACHE_CONTROL.name(), "no-cache");
		}else{
			response.setHeader(HttpHeader.CACHE_CONTROL.name(), "max-age=" + maxAge);
		}
		response.setDateHeader(HttpHeader.EXPIRES.name(), expires.getTime());
		response.setHeader(HttpHeader.PRAGMA.name(), maxAge > 0 ? null : "no-cache");
	}

}
