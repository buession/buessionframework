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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.servlet.filter;

import com.buession.web.http.HttpHeader;
import com.buession.web.http.request.DefaultRequestIdService;
import com.buession.web.http.request.RequestIdService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求 ID Filter
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class RequestIdFilter extends OncePerRequestFilter {

	private RequestIdService requestIdService = new DefaultRequestIdService();

	private String key;

	private boolean sendResponse;

	public RequestIdService getRequestIdService(){
		return requestIdService;
	}

	public void setRequestIdService(RequestIdService requestIdService){
		this.requestIdService = requestIdService;
	}

	public String getKey(){
		return key;
	}

	public void setKey(String key){
		this.key = key;
	}

	public boolean isSendResponse(){
		return sendResponse;
	}

	public void setSendResponse(boolean sendResponse){
		this.sendResponse = sendResponse;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
		getRequestIdService().setKey(getKey());
		String requestId = getRequestIdService().generate();

		request.setAttribute(HttpHeader.X_REQUESTED_ID.getValue(), requestId);

		if(sendResponse){
			response.setHeader(HttpHeader.X_REQUESTED_ID.getValue(), requestId);
		}

		filterChain.doFilter(request, response);
	}

}
