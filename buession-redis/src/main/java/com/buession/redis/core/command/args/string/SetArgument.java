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
package com.buession.redis.core.command.args.string;

import com.buession.core.utils.Assert;
import com.buession.redis.core.command.args.BaseSetExArgument;
import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.core.command.args.SetExType;
import com.buession.redis.core.command.args.hash.HSetExArgument;
import com.buession.redis.utils.ArgStringBuilder;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * {@code SET} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class SetArgument extends BaseSetExArgument<SetArgument> {

	/**
	 * 只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	private NxXx nxXx;

	/**
	 * 构造函数
	 */
	public SetArgument() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		过期时间方式
	 * @param expires
	 * 		过期时间
	 */
	public SetArgument(final SetExType type, final long expires) {
		super(type, expires);
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 * @param type
	 * 		过期时间方式
	 * @param expires
	 * 		过期时间
	 */
	public SetArgument(final NxXx nxXx, final SetExType type, final long expires) {
		this(type, expires);
		this.nxXx = nxXx;
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	public SetArgument(final NxXx nxXx) {
		this.nxXx = nxXx;
	}

	/**
	 * 返回只有键 key 不存在/存在的时候才会设置 key 的值
	 *
	 * @return 只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	public NxXx getNxXx() {
		return nxXx;
	}

	/**
	 * 设置只有键 key 不存在/存在的时候才会设置 key 的值
	 *
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	public SetArgument setNxXx(NxXx nxXx) {
		this.nxXx = nxXx;
		return this;
	}

	/**
	 * 设置时间
	 *
	 * @param liftime
	 * 		过期时间（单位：秒）
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument ex(long liftime) {
		return new SetArgument(SetExType.EX, liftime);
	}

	/**
	 * 设置时间
	 *
	 * @param liftime
	 * 		过期时间（单位：秒）
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument ex(Duration liftime) {
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
	public static SetArgument exAt(long unixTime) {
		return new SetArgument(SetExType.EX, unixTime);
	}

	/**
	 * 设置时间
	 *
	 * @param date
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument exAt(Date date) {
		Assert.isNull(date, "Timestamp must not be null");
		return exAt(date.getTime() / 1000);
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument exAt(LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return exAt(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument exAt(ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return exAt(dateTime.toEpochSecond());
	}

	/**
	 * 设置时间
	 *
	 * @param instant
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument exAt(Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		return exAt(instant.toEpochMilli() / 1000);
	}

	/**
	 * 设置时间
	 *
	 * @param liftime
	 * 		过期时间（单位：毫秒）
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument px(long liftime) {
		return new SetArgument(SetExType.PX, liftime);
	}

	/**
	 * 设置时间
	 *
	 * @param liftime
	 * 		过期时间（单位：毫秒）
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument px(Duration liftime) {
		Assert.isNull(liftime, "Liftime argument can't be null.");
		return px(liftime.toMillis());
	}

	/**
	 * 设置时间
	 *
	 * @param unixTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument pxAt(long unixTime) {
		return new SetArgument(SetExType.PXAT, unixTime);
	}

	/**
	 * 设置时间
	 *
	 * @param date
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument pxAt(Date date) {
		Assert.isNull(date, "Timestamp must not be null");
		return pxAt(date.getTime());
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument pxAt(LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return pxAt(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument pxAt(ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return pxAt(dateTime.toEpochSecond());
	}

	/**
	 * 设置时间
	 *
	 * @param instant
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument pxAt(Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		return exAt(instant.toEpochMilli());
	}

	/**
	 * 设置键是否持久化
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument keepttl() {
		return new SetArgument(SetExType.KEEPTTL, 0);
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param liftime
	 * 		过期时间（单位：秒）
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument ex(NxXx nxXx, long liftime) {
		return new SetArgument(nxXx, SetExType.EX, liftime);
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param liftime
	 * 		过期时间（单位：秒）
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument ex(NxXx nxXx, Duration liftime) {
		Assert.isNull(liftime, "Liftime argument can't be null.");
		return ex(nxXx, liftime.toSeconds());
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param unixTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static SetArgument exAt(NxXx nxXx, long unixTime) {
		return new SetArgument(nxXx, SetExType.EX, unixTime);
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param date
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument exAt(NxXx nxXx, Date date) {
		Assert.isNull(nxXx, "Timestamp must not be null");
		return exAt(nxXx, date.getTime() / 1000);
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param dateTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument exAt(NxXx nxXx, LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return exAt(nxXx, dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param dateTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link HSetExArgument}
	 */
	public static SetArgument exAt(NxXx nxXx, ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return exAt(nxXx, dateTime.toEpochSecond());
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param instant
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument exAt(NxXx nxXx, Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		return exAt(nxXx, instant.toEpochMilli() / 1000);
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param liftime
	 * 		过期时间（单位：毫秒）
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument px(NxXx nxXx, long liftime) {
		return new SetArgument(nxXx, SetExType.PX, liftime);
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param liftime
	 * 		过期时间（单位：毫秒）
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument px(NxXx nxXx, Duration liftime) {
		Assert.isNull(liftime, "Liftime argument can't be null.");
		return px(nxXx, liftime.toMillis());
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param unixTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument pxAt(NxXx nxXx, long unixTime) {
		return new SetArgument(nxXx, SetExType.PXAT, unixTime);
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param date
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument pxAt(NxXx nxXx, Date date) {
		Assert.isNull(date, "Timestamp must not be null");
		return pxAt(nxXx, date.getTime());
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param dateTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument pxAt(NxXx nxXx, LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return pxAt(nxXx, dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param dateTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument pxAt(NxXx nxXx, ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return pxAt(nxXx, dateTime.toEpochSecond());
	}

	/**
	 * 设置时间
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param instant
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument pxAt(NxXx nxXx, Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		return exAt(nxXx, instant.toEpochMilli());
	}

	/**
	 * 设置键是否持久化
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 *
	 * @return {@link SetArgument}
	 */
	public static SetArgument keepttl(NxXx nxXx) {
		return new SetArgument(nxXx, SetExType.KEEPTTL, 0);
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.append(getNxXx())
				.add(getType().name(), getType() == SetExType.KEEPTTL ? null : getExpires())
				.build();
	}

}
