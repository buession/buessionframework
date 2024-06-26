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
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.datetime;

import com.buession.core.utils.Assert;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期时间
 *
 * @author Yong.Teng
 */
public class DateTime {

	/**
	 * 当前 Unix 时间戳的微秒数
	 *
	 * @return 以 "msec sec" 的格式返回一个字符串
	 */
	public static String microtime() {
		long timestamp = System.currentTimeMillis();
		return String.valueOf(timestamp / 1000L) + ' ' + timestamp % 1000L * 1000L;
	}

	/**
	 * 获取当前 Unix Timestamp
	 *
	 * @return 当前 Unix Timestamp
	 *
	 * @since 1.3.1
	 */
	public static long unixtime() {
		return System.currentTimeMillis() / 1000L;
	}

	/**
	 * 获取指定年的天数
	 *
	 * @param year
	 * 		年份
	 *
	 * @return 指定年月份的天数；如果不是一个合法的年份，则返回 -1
	 *
	 * @since 2.1.1
	 */
	public static int getDays(final int year) {
		if(year <= 0){
			return -1;
		}

		int days = 0;

		for(int i = 1; i <= 12; i++){
			days += getDays(year, i);
		}

		return days;
	}

	/**
	 * 获取指定年月份的天数
	 *
	 * @param year
	 * 		年份
	 * @param month
	 * 		月份
	 *
	 * @return 指定年月份的天数；如果不是一个合法的年份或月份，则返回 -1
	 *
	 * @since 2.1.1
	 */
	public static int getDays(final int year, final int month) {
		if(year <= 0){
			return -1;
		}

		switch(month){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				return isLeapYear(year) ? 29 : 28;
			default:
				return -1;
		}
	}

	/**
	 * 判断指定年份是否为闰年
	 *
	 * @param year
	 * 		年份
	 *
	 * @return 指定年份为闰年，返回 true；否则，返回 false
	 *
	 * @since 2.1.1
	 */
	public static boolean isLeapYear(final int year) {
		if(year <= 0){
			return false;
		}

		if(year % 4 == 0){
			if(year % 100 == 0){
				return year % 400 == 0;
			}
		}

		return false;
	}

	/**
	 * 获取 {@link Date} 实例
	 *
	 * @return {@link Date} 实例
	 *
	 * @since 2.3.0
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * 获取 {@link Calendar} 实例
	 *
	 * @return {@link Calendar} 实例
	 *
	 * @since 2.3.0
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 获取 {@link Calendar} 实例
	 *
	 * @param timeZone
	 *        {@link TimeZone}
	 *
	 * @return {@link Calendar} 实例
	 *
	 * @since 2.3.0
	 */
	public static Calendar getCalendar(final TimeZone timeZone) {
		return Calendar.getInstance(timeZone);
	}

	/**
	 * 获取 {@link Calendar} 实例
	 *
	 * @param locale
	 *        {@link Locale}
	 *
	 * @return {@link Calendar} 实例
	 *
	 * @since 2.3.0
	 */
	public static Calendar getCalendar(final Locale locale) {
		return Calendar.getInstance(locale);
	}

	/**
	 * 获取 {@link Calendar} 实例
	 *
	 * @param timeZone
	 *        {@link TimeZone}
	 * @param locale
	 *        {@link Locale}
	 *
	 * @return {@link Calendar} 实例
	 *
	 * @since 2.3.0
	 */
	public static Calendar getCalendar(final TimeZone timeZone, final Locale locale) {
		return Calendar.getInstance(timeZone, locale);
	}

	/**
	 * 获取 {@link LocalDate} 实例
	 *
	 * @return {@link LocalDate} 实例
	 *
	 * @since 2.3.0
	 */
	public static LocalDate getLocalDate() {
		return LocalDate.now();
	}

	/**
	 * 获取 {@link LocalDate} 实例
	 *
	 * @param zone
	 *        {@link ZoneId}
	 *
	 * @return {@link LocalDate} 实例
	 *
	 * @since 2.3.0
	 */
	public static LocalDate getLocalDate(final ZoneId zone) {
		return LocalDate.now(zone);
	}

	/**
	 * 获取 {@link LocalDate} 实例
	 *
	 * @param clock
	 *        {@link Clock}
	 *
	 * @return {@link LocalDate} 实例
	 *
	 * @since 2.3.0
	 */
	public static LocalDate getLocalDate(final Clock clock) {
		return LocalDate.now(clock);
	}

	/**
	 * 获取 {@link LocalTime} 实例
	 *
	 * @return {@link LocalTime} 实例
	 *
	 * @since 2.3.0
	 */
	public static LocalTime getLocalTime() {
		return LocalTime.now();
	}

	/**
	 * 获取 {@link LocalTime} 实例
	 *
	 * @param zone
	 *        {@link ZoneId}
	 *
	 * @return {@link LocalTime} 实例
	 *
	 * @since 2.3.0
	 */
	public static LocalTime getLocalTime(final ZoneId zone) {
		return LocalTime.now(zone);
	}

	/**
	 * 获取 {@link LocalTime} 实例
	 *
	 * @param clock
	 *        {@link Clock}
	 *
	 * @return {@link LocalTime} 实例
	 *
	 * @since 2.3.0
	 */
	public static LocalTime getLocalTime(final Clock clock) {
		return LocalTime.now(clock);
	}

	/**
	 * 获取 {@link LocalDateTime} 实例
	 *
	 * @return {@link LocalDateTime} 实例
	 *
	 * @since 2.3.0
	 */
	public static LocalDateTime getLocalDateTime() {
		return LocalDateTime.now();
	}

	/**
	 * 获取 {@link LocalDateTime} 实例
	 *
	 * @param zone
	 *        {@link ZoneId}
	 *
	 * @return {@link LocalDateTime} 实例
	 *
	 * @since 2.3.0
	 */
	public static LocalDateTime getLocalDateTime(final ZoneId zone) {
		return LocalDateTime.now(zone);
	}

	/**
	 * 获取 {@link LocalDateTime} 实例
	 *
	 * @param clock
	 *        {@link Clock}
	 *
	 * @return {@link LocalDateTime} 实例
	 *
	 * @since 2.3.0
	 */
	public static LocalDateTime getLocalDateTime(final Clock clock) {
		return LocalDateTime.now(clock);
	}

	/**
	 * 获取 {@link ZonedDateTime} 实例
	 *
	 * @return {@link ZonedDateTime} 实例
	 *
	 * @since 2.3.0
	 */
	public static ZonedDateTime getZonedDateTime() {
		return ZonedDateTime.now();
	}

	/**
	 * 获取 {@link ZonedDateTime} 实例
	 *
	 * @param zone
	 *        {@link ZoneId}
	 *
	 * @return {@link ZonedDateTime} 实例
	 *
	 * @since 2.3.0
	 */
	public static ZonedDateTime getZonedDateTime(final ZoneId zone) {
		return ZonedDateTime.now(zone);
	}

	/**
	 * 获取 {@link ZonedDateTime} 实例
	 *
	 * @param clock
	 *        {@link Clock}
	 *
	 * @return {@link ZonedDateTime} 实例
	 *
	 * @since 2.3.0
	 */
	public static ZonedDateTime getZonedDateTime(final Clock clock) {
		return ZonedDateTime.now(clock);
	}

	/**
	 * 返回 {@link Date} Unix 时间戳
	 *
	 * @param date
	 *        {@link Date}
	 *
	 * @return {@link Date} Unix 时间戳
	 *
	 * @since 2.3.1
	 */
	public static long toUnixTime(final Date date) {
		Assert.isNull(date, "java.util.Date cloud not be null.");
		return date.getTime() / 1000;
	}

	/**
	 * 返回 {@link Calendar} Unix 时间戳
	 *
	 * @param calendar
	 *        {@link Calendar}
	 *
	 * @return {@link Calendar} Unix 时间戳
	 *
	 * @since 2.3.1
	 */
	public static long toUnixTime(final Calendar calendar) {
		Assert.isNull(calendar, "java.util.Calendar cloud not be null.");
		return calendar.getTimeInMillis() / 1000;
	}

	/**
	 * 返回 {@link LocalDate} Unix 时间戳
	 *
	 * @param date
	 *        {@link LocalDate}
	 *
	 * @return {@link LocalDate} Unix 时间戳
	 *
	 * @since 2.3.1
	 */
	public static long toUnixTime(final LocalDate date) {
		Assert.isNull(date, "java.time.LocalDate cloud not be null.");
		return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
	}

	/**
	 * 返回 {@link LocalDateTime} Unix 时间戳
	 *
	 * @param dateTime
	 *        {@link LocalDateTime}
	 *
	 * @return {@link LocalDateTime} Unix 时间戳
	 *
	 * @since 2.3.1
	 */
	public static long toUnixTime(final LocalDateTime dateTime) {
		Assert.isNull(dateTime, "java.time.LocalDateTime cloud not be null.");
		return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
	}

	/**
	 * 返回 {@link Clock} Unix 时间戳
	 *
	 * @param clock
	 *        {@link Clock}
	 *
	 * @return {@link Clock} Unix 时间戳
	 *
	 * @since 2.3.1
	 */
	public static long toUnixTime(final Clock clock) {
		Assert.isNull(clock, "java.time.Clock cloud not be null.");
		return clock.millis() / 1000;
	}

}
