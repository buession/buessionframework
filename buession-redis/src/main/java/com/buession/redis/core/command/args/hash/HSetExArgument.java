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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command.args.hash;

import com.buession.core.utils.Assert;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.command.args.BaseSetExArgument;
import com.buession.redis.core.command.args.SetExType;
import com.buession.redis.utils.ArgStringBuilder;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * HSETEX 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class HSetExArgument extends BaseSetExArgument<HSetExArgument> {

	private FnxFxx fnxFxx;

	/**
	 * 构造函数
	 */
	public HSetExArgument() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param fnxFxx
	 * 		-
	 */
	public HSetExArgument(final FnxFxx fnxFxx) {
		this.fnxFxx = fnxFxx;
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		过期时间类型
	 * @param value
	 * 		过期时间(戳)
	 */
	public HSetExArgument(final SetExType type, final long value) {
		super(type, value);
	}

	/**
	 * 构造函数
	 *
	 * @param fnxFxx
	 * 		-
	 * @param type
	 * 		过期时间类型
	 * @param value
	 * 		过期时间(戳)
	 */
	public HSetExArgument(final FnxFxx fnxFxx, final SetExType type, final long value) {
		super(type, value);
		this.fnxFxx = fnxFxx;
	}

	public FnxFxx getFnxFxx() {
		return fnxFxx;
	}

	public HSetExArgument setFnxFxx(FnxFxx fnxFxx) {
		this.fnxFxx = fnxFxx;
		return this;
	}

	/**
	 * 设置时间
	 *
	 * @param liftime
	 * 		过期时间（单位：秒）
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument ex(long liftime) {
		return new HSetExArgument(SetExType.EX, liftime);
	}

	/**
	 * 设置时间
	 *
	 * @param liftime
	 * 		过期时间（单位：秒）
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument ex(Duration liftime) {
		Assert.isNull(liftime, "Liftime argument can't be null.");
		return ex(liftime.toSeconds());
	}

	/**
	 * 设置时间
	 *
	 * @param unixTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument exAt(long unixTime) {
		return new HSetExArgument(SetExType.EX, unixTime);
	}

	/**
	 * 设置时间
	 *
	 * @param date
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument exAt(Date date) {
		Assert.isNull(date, "Timestamp must not be null");
		return exAt(date.getTime() / 1000);
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument exAt(LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return exAt(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument exAt(ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return exAt(dateTime.toEpochSecond());
	}

	/**
	 * 设置时间
	 *
	 * @param instant
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument exAt(Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		return exAt(instant.toEpochMilli() / 1000);
	}

	/**
	 * 设置时间
	 *
	 * @param liftime
	 * 		过期时间（单位：毫秒）
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument px(long liftime) {
		return new HSetExArgument(SetExType.PX, liftime);
	}

	/**
	 * 设置时间
	 *
	 * @param liftime
	 * 		过期时间（单位：毫秒）
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument px(Duration liftime) {
		Assert.isNull(liftime, "Liftime argument can't be null.");
		return px(liftime.toMillis());
	}

	/**
	 * 设置时间
	 *
	 * @param unixTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument pxAt(long unixTime) {
		return new HSetExArgument(SetExType.PXAT, unixTime);
	}

	/**
	 * 设置时间
	 *
	 * @param date
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument pxAt(Date date) {
		Assert.isNull(date, "Timestamp must not be null");
		return pxAt(date.getTime());
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument pxAt(LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return pxAt(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument pxAt(ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return pxAt(dateTime.toEpochSecond());
	}

	/**
	 * 设置时间
	 *
	 * @param instant
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument pxAt(Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		return exAt(instant.toEpochMilli());
	}

	/**
	 * 设置键是否持久化
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument keepttl() {
		return new HSetExArgument(SetExType.KEEPTTL, 0);
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param liftime
	 * 		过期时间（单位：秒）
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument ex(FnxFxx fnxFxx, long liftime) {
		return new HSetExArgument(fnxFxx, SetExType.EX, liftime);
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param liftime
	 * 		过期时间（单位：秒）
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument ex(FnxFxx fnxFxx, Duration liftime) {
		Assert.isNull(liftime, "Liftime argument can't be null.");
		return ex(fnxFxx, liftime.toSeconds());
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param unixTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument exAt(FnxFxx fnxFxx, long unixTime) {
		return new HSetExArgument(fnxFxx, SetExType.EX, unixTime);
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param date
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument exAt(FnxFxx fnxFxx, Date date) {
		Assert.isNull(date, "Timestamp must not be null");
		return exAt(fnxFxx, date.getTime() / 1000);
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param dateTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument exAt(FnxFxx fnxFxx, LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return exAt(fnxFxx, dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param dateTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument exAt(FnxFxx fnxFxx, ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return exAt(fnxFxx, dateTime.toEpochSecond());
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param instant
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument exAt(FnxFxx fnxFxx, Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		return exAt(fnxFxx, instant.toEpochMilli() / 1000);
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param liftime
	 * 		过期时间（单位：毫秒）
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument px(FnxFxx fnxFxx, long liftime) {
		return new HSetExArgument(fnxFxx, SetExType.PX, liftime);
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param liftime
	 * 		过期时间（单位：毫秒）
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument px(FnxFxx fnxFxx, Duration liftime) {
		Assert.isNull(liftime, "Liftime argument can't be null.");
		return px(fnxFxx, liftime.toMillis());
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param unixTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument pxAt(FnxFxx fnxFxx, long unixTime) {
		return new HSetExArgument(fnxFxx, SetExType.PXAT, unixTime);
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param date
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument pxAt(FnxFxx fnxFxx, Date date) {
		Assert.isNull(date, "Timestamp must not be null");
		return pxAt(fnxFxx, date.getTime());
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param dateTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument pxAt(FnxFxx fnxFxx, LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return pxAt(fnxFxx, dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param dateTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument pxAt(FnxFxx fnxFxx, ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return pxAt(fnxFxx, dateTime.toEpochSecond());
	}

	/**
	 * 设置时间
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 * @param instant
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument pxAt(FnxFxx fnxFxx, Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		return exAt(fnxFxx, instant.toEpochMilli());
	}

	/**
	 * 设置键是否持久化
	 *
	 * @param fnxFxx
	 *        {@link FnxFxx}
	 *
	 * @return {@link HSetExArgument}
	 */
	public static HSetExArgument keepttl(FnxFxx fnxFxx) {
		return new HSetExArgument(fnxFxx, SetExType.KEEPTTL, 0);
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().append(getFnxFxx())
				.add(getType().name(), getType() == SetExType.KEEPTTL ? null : getExpires()).build();
	}

	public enum FnxFxx implements Keyword {
		FNX,

		FXX;

		@Override
		public String getValue() {
			return name();
		}

		@Override
		public String toString() {
			return super.toString();
		}

	}

}
