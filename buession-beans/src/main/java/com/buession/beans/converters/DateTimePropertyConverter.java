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

import com.buession.beans.AbstractPropertyConverter;
import com.buession.core.validator.Validate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * {@link java.util.Date} 类型 Bean 属性转换器
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public abstract class DateTimePropertyConverter<T> extends AbstractPropertyConverter<T> {

	/**
	 * {@link Locale}
	 */
	private Locale locale;

	/**
	 * {@link TimeZone}
	 */
	private TimeZone timeZone;

	/**
	 * 日期时间格式
	 */
	private String format;

	/**
	 * 构造函数
	 */
	public DateTimePropertyConverter() {
	}

	/**
	 * 构造函数
	 *
	 * @param locale
	 *        {@link Locale}
	 */
	public DateTimePropertyConverter(Locale locale) {
		this.locale = locale;
	}

	/**
	 * 构造函数
	 *
	 * @param timeZone
	 *        {@link TimeZone}
	 */
	public DateTimePropertyConverter(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * 构造函数
	 *
	 * @param locale
	 *        {@link Locale}
	 * @param format
	 * 		日期时间格式
	 */
	public DateTimePropertyConverter(Locale locale, String format) {
		this.locale = locale;
		this.format = format;
	}

	/**
	 * 构造函数
	 *
	 * @param locale
	 *        {@link Locale}
	 * @param timeZone
	 *        {@link TimeZone}
	 */
	public DateTimePropertyConverter(Locale locale, TimeZone timeZone) {
		this.locale = locale;
		this.timeZone = timeZone;
	}

	/**
	 * 构造函数
	 *
	 * @param timeZone
	 *        {@link TimeZone}
	 * @param format
	 * 		日期时间格式
	 */
	public DateTimePropertyConverter(TimeZone timeZone, String format) {
		this.timeZone = timeZone;
		this.format = format;
	}

	/**
	 * 构造函数
	 *
	 * @param format
	 * 		日期时间格式
	 */
	public DateTimePropertyConverter(String format) {
		this.format = format;
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
	public DateTimePropertyConverter(Locale locale, TimeZone timeZone, String format) {
		this(locale, timeZone);
		this.format = format;
	}

	/**
	 * 返回 {@link Locale}
	 *
	 * @return {@link Locale}
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * 设置 {@link Locale}
	 *
	 * @param locale
	 *        {@link Locale}
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * 返回 {@link TimeZone}
	 *
	 * @return {@link TimeZone}
	 */
	public TimeZone getTimeZone() {
		return timeZone;
	}

	/**
	 * 设置 {@link TimeZone}
	 *
	 * @param timeZone
	 *        {@link TimeZone}
	 */
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * 返回日期时间格式
	 *
	 * @return 日期时间格式
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * 设置日期时间格式
	 *
	 * @param format
	 * 		日期时间格式
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	protected T convertToType(final Class<?> sourceType, final Object value, final Class<T> targetType) {
		// Handle java.sql.Date
		if(value instanceof java.sql.Date){
			final java.sql.Date date = (java.sql.Date) value;
			return toDate(targetType, sourceType, date.getTime());
		}

		// Handle java.sql.Time
		if(value instanceof java.sql.Time){
			final java.sql.Time time = (java.sql.Time) value;
			return toDate(targetType, sourceType, time.getTime());
		}

		// Handle java.sql.Timestamp
		if(value instanceof java.sql.Timestamp){
			final java.sql.Timestamp timestamp = (java.sql.Timestamp) value;
			long timeInMillis = (timestamp.getTime() / 1000) * 1000;
			timeInMillis += timestamp.getNanos() / 1000000;
			return toDate(targetType, sourceType, timeInMillis);
		}

		// Handle Date
		if(value instanceof Date){
			final Date date = (Date) value;
			return toDate(targetType, sourceType, date.getTime());
		}

		// Handle Calendar
		if(value instanceof Calendar){
			final Calendar calendar = (Calendar) value;
			return toDate(targetType, sourceType, calendar.getTime().getTime());
		}

		// Handle LocalDate
		if(value instanceof LocalDate){
			final LocalDate localDate = (LocalDate) value;
			final Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

			return toDate(targetType, sourceType, instant);
		}

		// Handle LocalDateTime
		if(value instanceof LocalDateTime){
			return toDate(targetType, sourceType, (LocalDateTime) value);
		}

		// Handle Instant
		if(value instanceof Instant){
			return toDate(targetType, sourceType, (Instant) value);
		}

		// Handle Long
		if(value instanceof Long){
			return toDate(targetType, sourceType, (Long) value);
		}

		// Handle CharSequence
		if(value instanceof CharSequence){
			if(Validate.hasText(getFormat())){
				final DateTimeFormatter dft = DateTimeFormatter.ofPattern(getFormat());
				return toDate(targetType, sourceType, LocalDateTime.parse(value.toString(), dft));
			}else{
				return toDate(targetType, sourceType, LocalDateTime.parse(value.toString()));
			}
		}

		return null;
	}

	protected abstract T toDate(final Class<T> targetType, final Class<?> sourceType, final long value);

	protected T toDate(final Class<T> targetType, final Class<?> sourceType, final Instant instant) {
		return toDate(targetType, sourceType, instant.toEpochMilli());
	}

	protected T toDate(final Class<T> targetType, final Class<?> sourceType, final LocalDateTime localDateTime) {
		final OffsetDateTime odt = OffsetDateTime.now(ZoneId.systemDefault());
		final Instant instant = localDateTime.toInstant(odt.getOffset());
		return toDate(targetType, sourceType, instant);
	}

}
