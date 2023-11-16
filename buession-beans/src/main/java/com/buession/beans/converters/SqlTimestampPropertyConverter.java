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

import java.sql.Timestamp;
import java.util.Locale;
import java.util.TimeZone;

/**
 * {@link Timestamp} 类型 Bean 属性转换器
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public final class SqlTimestampPropertyConverter extends DateTimePropertyConverter<Timestamp> {

	/**
	 * 构造函数
	 */
	public SqlTimestampPropertyConverter() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param locale
	 *        {@link Locale}
	 */
	public SqlTimestampPropertyConverter(Locale locale) {
		super(locale);
	}

	/**
	 * 构造函数
	 *
	 * @param timeZone
	 *        {@link TimeZone}
	 */
	public SqlTimestampPropertyConverter(TimeZone timeZone) {
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
	public SqlTimestampPropertyConverter(Locale locale, String format) {
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
	public SqlTimestampPropertyConverter(Locale locale, TimeZone timeZone) {
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
	public SqlTimestampPropertyConverter(TimeZone timeZone, String format) {
		super(timeZone, format);
	}

	/**
	 * 构造函数
	 *
	 * @param format
	 * 		日期时间格式
	 */
	public SqlTimestampPropertyConverter(String format) {
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
	public SqlTimestampPropertyConverter(Locale locale, TimeZone timeZone, String format) {
		super(locale, timeZone, format);
	}

	@Override
	protected Timestamp toDate(final Class<Timestamp> targetType, final Class<?> sourceType, final long value) {
		return new Timestamp(value);
	}

}
