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
package com.buession.web.servlet.filter;

import com.buession.core.validator.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 打印当前请求 URL 过滤器
 *
 * @author Yong.Teng
 */
public class PrintUrlFilter extends OncePerRequestFilter {

	private final static Logger logger = LoggerFactory.getLogger(PrintUrlFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(logger.isInfoEnabled()){
			String url = parseFullUrl(request);

			if(Validate.hasText(url)){
				logger.info("Request URL: {}", url);
			}
		}

		filterChain.doFilter(request, response);
	}

	protected String parseUrl(final HttpServletRequest request) {
		return request.getRequestURL().toString();
	}

	protected String parseFullUrl(final HttpServletRequest request) {
		String url = parseUrl(request);

		if(Validate.hasText(url)){
			String queryString = request.getQueryString();

			if(Validate.hasText(queryString)){
				url += '?' + queryString;
			}
		}

		return url;
	}

}
