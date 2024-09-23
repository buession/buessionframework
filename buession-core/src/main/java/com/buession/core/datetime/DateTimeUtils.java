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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.datetime;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.zone.ZoneRules;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 日期时间工具类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class DateTimeUtils {

	protected DateTimeUtils() {

	}

	/**
	 * 从字符串创建 {@link LocalDateTime} 实例
	 *
	 * @param value
	 * 		日期字符串
	 *
	 * @return {@link LocalDateTime} 实例
	 */
	public static LocalDateTime localDateTimeOf(final String value) {
		return localDateTimeOf(value, ZoneId.systemDefault());
	}

	/**
	 * 从字符串创建 {@link LocalDateTime} 实例
	 *
	 * @param value
	 * 		日期字符串
	 * @param zoneId
	 * 		时区 ID
	 *
	 * @return {@link LocalDateTime} 实例
	 */
	public static LocalDateTime localDateTimeOf(final String value, final ZoneId zoneId) {
		LocalDateTime result = null;

		try{
			result = LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		}catch(Exception e){
			result = null;
		}

		if(result == null){
			try{
				result = LocalDateTime.parse(value, DateTimeFormatter.ISO_ZONED_DATE_TIME);
			}catch(Exception e){
				result = null;
			}
		}

		if(result == null){
			try{
				result = LocalDateTime.parse(value);
			}catch(Exception e){
				result = null;
			}
		}

		if(result == null){
			try{
				result = LocalDateTime.parse(value.toUpperCase(), DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a"));
			}catch(Exception e){
				result = null;
			}
		}

		if(result == null){
			try{
				result = LocalDateTime.parse(value.toUpperCase(), DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a"));
			}catch(Exception e){
				result = null;
			}
		}

		if(result == null){
			try{
				result = LocalDateTime.parse(value, DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
			}catch(Exception e){
				result = null;
			}
		}

		LocalDate ld;
		if(result == null){
			try{
				ld = LocalDate.parse(value, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				result = LocalDateTime.of(ld, LocalTime.now(zoneId));
			}catch(Exception e){
				result = null;
			}
		}

		if(result == null){
			try{
				ld = LocalDate.parse(value);
				result = LocalDateTime.of(ld, LocalTime.now(zoneId));
			}catch(Exception e){
				result = null;
			}
		}

		return result;
	}

	/**
	 * 从时间戳创建 {@link LocalDateTime} 实例
	 *
	 * @param timestamp
	 * 		日期字符串
	 *
	 * @return {@link LocalDateTime} 实例
	 */
	public static LocalDateTime localDateTimeOf(final long timestamp) {
		return localDateTimeOf(timestamp, ZoneId.systemDefault());
	}

	/**
	 * 从时间戳创建 {@link LocalDateTime} 实例
	 *
	 * @param timestamp
	 * 		日期字符串
	 * @param zoneId
	 * 		时区 ID
	 *
	 * @return {@link LocalDateTime} 实例
	 */
	public static LocalDateTime localDateTimeOf(final long timestamp, final ZoneId zoneId) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId);
	}

	/**
	 * 从{@link Date}创建 {@link LocalDateTime} 实例
	 *
	 * @param date
	 *        {@link Date}
	 *
	 * @return {@link LocalDateTime} 实例
	 */
	public static LocalDateTime localDateTimeOf(final Date date) {
		return localDateTimeOf(date.getTime());
	}

	/**
	 * 从{@link Date}创建 {@link LocalDateTime} 实例
	 *
	 * @param date
	 *        {@link Date}
	 * @param zoneId
	 * 		时区 ID
	 *
	 * @return {@link LocalDateTime} 实例
	 */
	public static LocalDateTime localDateTimeOf(final Date date, final ZoneId zoneId) {
		return localDateTimeOf(date.getTime(), zoneId);
	}

	/**
	 * 从时间戳创建 {@link LocalDateTime} 实例
	 *
	 * @param timestamp
	 * 		日期字符串
	 *
	 * @return {@link LocalDate} 实例
	 */
	public static LocalDate localDateOf(final long timestamp) {
		return localDateOf(timestamp, ZoneId.systemDefault());
	}

	/**
	 * 从时间戳创建 {@link LocalDate} 实例
	 *
	 * @param timestamp
	 * 		日期字符串
	 * @param zoneId
	 * 		时区 ID
	 *
	 * @return {@link LocalDate} 实例
	 */
	public static LocalDate localDateOf(final long timestamp, final ZoneId zoneId) {
		Instant instant = Instant.ofEpochMilli(timestamp);
		ZoneRules rules = zoneId.getRules();
		ZoneOffset offset = rules.getOffset(instant);
		long localSecond = instant.getEpochSecond() + offset.getTotalSeconds();
		long localEpochDay = Math.floorDiv(localSecond, 86400);
		return LocalDate.ofEpochDay(localEpochDay);
	}

	/**
	 * 从{@link Date}创建 {@link LocalDate} 实例
	 *
	 * @param date
	 *        {@link Date}
	 *
	 * @return {@link LocalDate} 实例
	 */
	public static LocalDate localDateOf(final Date date) {
		return localDateOf(date.getTime());
	}

	/**
	 * 从{@link Date}创建 {@link LocalDate} 实例
	 *
	 * @param date
	 *        {@link Date}
	 * @param zoneId
	 * 		时区 ID
	 *
	 * @return {@link LocalDate} 实例
	 */
	public static LocalDate localDateOf(final Date date, final ZoneId zoneId) {
		return localDateOf(date.getTime(), zoneId);
	}

	/**
	 * 从字符串创建 {@link ZonedDateTime} 实例
	 *
	 * @param value
	 * 		日期时间字符串
	 *
	 * @return {@link ZonedDateTime} 实例
	 */
	public static ZonedDateTime zonedDateTimeOf(final String value) {
		List<DateTimeFormatter> parsers = Arrays.asList(DateTimeFormatter.ISO_ZONED_DATE_TIME,
				DateTimeFormatter.RFC_1123_DATE_TIME);
		return parsers.stream().map((parser)->{
			try{
				return ZonedDateTime.parse(value, parser);
			}catch(Exception e){
				return null;
			}
		}).filter(Objects::nonNull).findFirst().orElse(null);
	}

	/**
	 * 从 {@link TemporalAccessor} 创建 {@link ZonedDateTime} 实例
	 *
	 * @param temporalAccessor
	 *        {@link TemporalAccessor}
	 *
	 * @return {@link ZonedDateTime} 实例
	 */
	public static ZonedDateTime zonedDateTimeOf(final TemporalAccessor temporalAccessor) {
		return ZonedDateTime.from(temporalAccessor);
	}

	/**
	 * 从 {@link Instant} 创建 {@link ZonedDateTime} 实例
	 *
	 * @param instant
	 *        {@link Instant}
	 *
	 * @return {@link ZonedDateTime} 实例
	 */
	public static ZonedDateTime zonedDateTimeOf(final Instant instant) {
		return zonedDateTimeOf(instant, ZoneId.systemDefault());
	}

	/**
	 * 从 {@link Instant} 创建 {@link ZonedDateTime} 实例
	 *
	 * @param instant
	 *        {@link Instant}
	 * @param zoneId
	 * 		时区 ID
	 *
	 * @return {@link ZonedDateTime} 实例
	 */
	public static ZonedDateTime zonedDateTimeOf(final Instant instant, final ZoneId zoneId) {
		return instant != null ? instant.atZone(zoneId) : null;
	}

	/**
	 * 从时间戳创建 {@link ZonedDateTime} 实例
	 *
	 * @param timestamp
	 * 		时间戳
	 *
	 * @return {@link ZonedDateTime} 实例
	 */
	public static ZonedDateTime zonedDateTimeOf(final long timestamp) {
		return zonedDateTimeOf(timestamp, ZoneId.systemDefault());
	}

	/**
	 * 从时间戳创建 {@link ZonedDateTime} 实例
	 *
	 * @param timestamp
	 * 		时间戳
	 * @param zoneId
	 * 		时区 ID
	 *
	 * @return {@link ZonedDateTime} 实例
	 */
	public static ZonedDateTime zonedDateTimeOf(final long timestamp, final ZoneId zoneId) {
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId);
	}

	/**
	 * 从 {@link Date} 创建 {@link ZonedDateTime} 实例
	 *
	 * @param date
	 *        {@link Date}
	 *
	 * @return {@link ZonedDateTime} 实例
	 */
	public static ZonedDateTime zonedDateTimeOf(final Date date) {
		return zonedDateTimeOf(date, ZoneId.systemDefault());
	}

	/**
	 * 从 {@link Date} 创建 {@link ZonedDateTime} 实例
	 *
	 * @param date
	 *        {@link Date}
	 * @param zoneId
	 * 		时区 ID
	 *
	 * @return {@link ZonedDateTime} 实例
	 */
	public static ZonedDateTime zonedDateTimeOf(final Date date, final ZoneId zoneId) {
		return date != null ? zonedDateTimeOf(Instant.ofEpochMilli(date.getTime()), zoneId) : null;
	}

	/**
	 * 从 {@link Calendar} 创建 {@link ZonedDateTime} 实例
	 *
	 * @param calendar
	 *        {@link Date}
	 *
	 * @return {@link ZonedDateTime} 实例
	 */
	public static ZonedDateTime zonedDateTimeOf(final Calendar calendar) {
		return ZonedDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
	}

	/**
	 * 从 {@link ChronoZonedDateTime} 创建 {@link Date} 实例
	 *
	 * @param chronoZonedDateTime
	 *        {@link ChronoZonedDateTime}
	 *
	 * @return {@link Date} 实例
	 */
	public static Date dateOf(final ChronoZonedDateTime<?> chronoZonedDateTime) {
		return dateOf(chronoZonedDateTime.toInstant());
	}

	/**
	 * 从 {@link LocalDate} 创建 {@link Date} 实例
	 *
	 * @param localDate
	 *        {@link LocalDate}
	 *
	 * @return {@link Date} 实例
	 */
	public static Date dateOf(final LocalDate localDate) {
		return dateOf(localDate, ZoneId.systemDefault());
	}

	/**
	 * 从 {@link LocalDate} 创建 {@link Date} 实例
	 *
	 * @param localDate
	 *        {@link LocalDate}
	 * @param zoneId
	 * 		时区 ID
	 *
	 * @return {@link Date} 实例
	 */
	public static Date dateOf(final LocalDate localDate, final ZoneId zoneId) {
		return Date.from(localDate.atStartOfDay(zoneId).toInstant());
	}

	/**
	 * 从 {@link LocalDateTime} 创建 {@link Date} 实例
	 *
	 * @param localDateTime
	 *        {@link LocalDate}
	 *
	 * @return {@link Date} 实例
	 */
	public static Date dateOf(final LocalDateTime localDateTime) {
		return dateOf(localDateTime, ZoneId.systemDefault());
	}

	/**
	 * 从 {@link LocalDateTime} 创建 {@link Date} 实例
	 *
	 * @param localDateTime
	 *        {@link LocalDate}
	 * @param zoneId
	 * 		时区 ID
	 *
	 * @return {@link Date} 实例
	 */
	public static Date dateOf(final LocalDateTime localDateTime, final ZoneId zoneId) {
		return dateOf(localDateTime.toInstant(zoneId.getRules().getOffset(localDateTime)));
	}

	/**
	 * 从 {@link Instant} 创建 {@link Date} 实例
	 *
	 * @param instant
	 *        {@link Instant}
	 *
	 * @return {@link Date} 实例
	 */
	public static Date dateOf(final Instant instant) {
		return Date.from(instant);
	}

	/**
	 * 从 {@link ChronoZonedDateTime} 创建 {@link Timestamp} 实例
	 *
	 * @param chronoZonedDateTime
	 *        {@link ChronoZonedDateTime}
	 *
	 * @return {@link Timestamp} 实例
	 */
	public static Timestamp timestampOf(final ChronoZonedDateTime chronoZonedDateTime) {
		return timestampOf(chronoZonedDateTime.toInstant());
	}

	/**
	 * 从 {@link Instant} 创建 {@link Timestamp} 实例
	 *
	 * @param instant
	 *        {@link Instant}
	 *
	 * @return {@link Timestamp} 实例
	 */
	public static Timestamp timestampOf(final Instant instant) {
		return Timestamp.from(instant);
	}

	/**
	 * {@link ChronoUnit} 转换为 {@link TimeUnit}
	 *
	 * @param chronoUnit
	 *        {@link ChronoUnit}
	 *
	 * @return {@link TimeUnit} 实例
	 */
	public static TimeUnit toTimeUnit(final ChronoUnit chronoUnit) {
		if(chronoUnit == null){
			return null;
		}else{
			switch(chronoUnit){
				case DAYS:
					return TimeUnit.DAYS;
				case HOURS:
					return TimeUnit.HOURS;
				case MINUTES:
					return TimeUnit.MINUTES;
				case SECONDS:
					return TimeUnit.SECONDS;
				case MICROS:
					return TimeUnit.MICROSECONDS;
				case MILLIS:
					return TimeUnit.MILLISECONDS;
				case NANOS:
					return TimeUnit.NANOSECONDS;
				default:
					throw new UnsupportedOperationException("Temporal unit is not supported");
			}
		}
	}

	/**
	 * {@link TimeUnit} 转换为 {@link ChronoUnit}
	 *
	 * @param timeUnit
	 *        {@link TimeUnit}
	 *
	 * @return {@link ChronoUnit} 实例
	 */
	public static ChronoUnit toChronoUnit(final TimeUnit timeUnit) {
		if(timeUnit == null){
			return null;
		}else{
			switch(timeUnit){
				case DAYS:
					return ChronoUnit.DAYS;
				case HOURS:
					return ChronoUnit.HOURS;
				case MINUTES:
					return ChronoUnit.MINUTES;
				case MICROSECONDS:
					return ChronoUnit.MICROS;
				case MILLISECONDS:
					return ChronoUnit.MILLIS;
				case NANOSECONDS:
					return ChronoUnit.NANOS;
				case SECONDS:
					return ChronoUnit.SECONDS;
				default:
					return ChronoUnit.SECONDS;
			}
		}
	}

}
