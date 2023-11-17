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
package com.buession.beans.converters;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.TimeZone;

/**
 * {@link LocalDateTime} 类型 Bean 属性转换器
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public final class LocalDateTimePropertyConverter extends DateTimePropertyConverter<LocalDateTime> {

	/**
	 * 构造函数
	 */
	public LocalDateTimePropertyConverter() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param locale
	 *        {@link Locale}
	 */
	public LocalDateTimePropertyConverter(Locale locale) {
		super(locale);
	}

	/**
	 * 构造函数
	 *
	 * @param timeZone
	 *        {@link TimeZone}
	 */
	public LocalDateTimePropertyConverter(TimeZone timeZone) {
		super(timeZone);
	}

	/**
	 * 构造函数
	 *
	 * @param locale
	 *        {@link Locale}
	 * @param format
	 * 		日期时间格式
	 */
	public LocalDateTimePropertyConverter(Locale locale, String format) {
		super(locale, format);
	}

	/**
	 * 构造函数
	 *
	 * @param locale
	 *        {@link Locale}
	 * @param timeZone
	 *        {@link TimeZone}
	 */
	public LocalDateTimePropertyConverter(Locale locale, TimeZone timeZone) {
		super(locale, timeZone);
	}

	/**
	 * 构造函数
	 *
	 * @param timeZone
	 *        {@link TimeZone}
	 * @param format
	 * 		日期时间格式
	 */
	public LocalDateTimePropertyConverter(TimeZone timeZone, String format) {
		super(timeZone, format);
	}

	/**
	 * 构造函数
	 *
	 * @param format
	 * 		日期时间格式
	 */
	public LocalDateTimePropertyConverter(String format) {
		super(format);
	}

	/**
	 * 构造函数
	 *
	 * @param locale
	 *        {@link Locale}
	 * @param timeZone
	 *        {@link TimeZone}
	 * @param format
	 * 		日期时间格式
	 */
	public LocalDateTimePropertyConverter(Locale locale, TimeZone timeZone, String format) {
		super(locale, timeZone, format);
	}

	@Override
	protected LocalDateTime toDate(final Class<LocalDateTime> targetType, final Class<?> sourceType, final long value) {
		return LocalDateTime.from(Instant.ofEpochMilli(value));
	}

}
