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
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.velocity.tools;

import org.apache.velocity.tools.generic.DateTool;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

/**
 * Tool for working with {@link Date} and {@link Calendar}
 * with request locale in Velocity templates.
 *
 * @author Yong.Teng
 * @see DateTool
 * @see Locale
 * @see HttpServletRequest
 */
public class LocaleAwareDateTool extends DateTool {

	private final static long serialVersionUID = 1077494810801235996L;

	private final HttpServletRequest request;

	public LocaleAwareDateTool(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Locale getLocale() {
		return RequestContextUtils.getLocale(request);
	}

	@Override
	public TimeZone getTimeZone() {
		return Optional.ofNullable(RequestContextUtils.getTimeZone(request)).orElse(super.getTimeZone());
	}

}
