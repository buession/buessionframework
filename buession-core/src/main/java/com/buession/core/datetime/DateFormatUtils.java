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
package com.buession.core.datetime;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期格式化工具
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils {

	public DateFormatUtils() {
		super();
	}

	/**
	 * 格式化当前日期时间
	 *
	 * @param pattern
	 * 		格式化格式
	 *
	 * @return 格式化后当前日期时间字符
	 */
	public static String format(String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * 格式化当前日期时间
	 *
	 * @param pattern
	 * 		格式化格式
	 * @param locale
	 *        {@link Locale}
	 *
	 * @return 格式化后当前日期时间字符
	 */
	public static String format(String pattern, Locale locale) {
		return format(new Date(), pattern, locale);
	}

	/**
	 * 格式化当前日期时间
	 *
	 * @param pattern
	 * 		格式化格式
	 * @param timeZone
	 *        {@link TimeZone}
	 *
	 * @return 格式化后当前日期时间字符
	 */
	public static String format(String pattern, TimeZone timeZone) {
		return format(new Date(), pattern, timeZone);
	}

	/**
	 * 格式化当前日期时间
	 *
	 * @param pattern
	 * 		格式化格式
	 * @param timeZone
	 *        {@link TimeZone}
	 * @param locale
	 *        {@link Locale}
	 *
	 * @return 格式化后当前日期时间字符
	 */
	public static String format(String pattern, TimeZone timeZone, Locale locale) {
		return format(new Date(), pattern, timeZone, locale);
	}

	/**
	 * UTC 时区格式化当前日期时间
	 *
	 * @param pattern
	 * 		格式化格式
	 *
	 * @return 格式化后当前日期时间字符
	 */
	public static String formatUTC(String pattern) {
		return formatUTC(new Date(), pattern);
	}

	/**
	 * UTC 时区格式化当前日期时间
	 *
	 * @param pattern
	 * 		格式化格式
	 * @param locale
	 *        {@link Locale}
	 *
	 * @return 格式化后当前日期时间字符
	 */
	public static String formatUTC(String pattern, Locale locale) {
		return formatUTC(new Date(), pattern, locale);
	}

}
