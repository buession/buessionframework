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
package com.buession.redis.core.command.args;

import com.buession.core.utils.Assert;
import com.buession.redis.core.Keyword;
import com.buession.redis.utils.ArgStringBuilder;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * GETEX、HGETEX 参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class GetExArgument implements ExArgument<GetExArgument> {

	/**
	 * 过期时间类型
	 */
	private GetExType type;

	/**
	 * 过期时间(戳)
	 */
	private Long expires;

	/**
	 * 构造函数
	 */
	public GetExArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间(戳)
	 */
	public GetExArgument(final GetExType type, final long expires) {
		this.type = type;
		this.expires = expires;
	}

	/**
	 * 获取过期时间类型
	 *
	 * @return 过期时间类型
	 */
	public GetExType getType() {
		return type;
	}

	/**
	 * 设置过期时间类型
	 *
	 * @param type
	 * 		过期时间类型
	 *
	 * @return {@link GetExArgument}
	 */
	public GetExArgument setType(GetExType type) {
		this.type = type;
		return this;
	}

	/**
	 * 获取设置的键过期时间(戳)
	 *
	 * @return 设置的键过期时间(戳)
	 */
	public Long getExpires() {
		return expires;
	}

	/**
	 * 设置过期时间戳
	 *
	 * @param expires
	 * 		过期时间(戳)
	 *
	 * @return {@link BaseSetExArgument}
	 */
	public GetExArgument setExpires(long expires) {
		this.expires = expires;
		return this;
	}

	/**
	 * 设置过期时间
	 *
	 * @param liftime
	 * 		过期时间（单位：秒）
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument ex(long liftime) {
		return new GetExArgument(GetExType.EX, liftime);
	}

	/**
	 * 设置过期时间
	 *
	 * @param liftime
	 * 		过期时间（单位：秒）
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument ex(Duration liftime) {
		Assert.isNull(liftime, "Liftime must not be null");
		return ex(liftime.toSeconds());
	}

	/**
	 * 设置时间
	 *
	 * @param unixTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument exAt(long unixTime) {
		return new GetExArgument(GetExType.EX, unixTime);
	}

	/**
	 * 设置时间
	 *
	 * @param date
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument exAt(Date date) {
		Assert.isNull(date, "Timestamp must not be null");
		return exAt(date.getTime() / 1000);
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument exAt(LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return exAt(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument exAt(ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return exAt(dateTime.toEpochSecond());
	}

	/**
	 * 设置时间
	 *
	 * @param instant
	 * 		时间，具体过期时间，秒时间戳
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument exAt(Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		return exAt(instant.toEpochMilli() / 1000);
	}

	/**
	 * 设置时间
	 *
	 * @param liftime
	 * 		过期时间（单位：毫秒）
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument px(long liftime) {
		return new GetExArgument(GetExType.PX, liftime);
	}

	/**
	 * 设置时间
	 *
	 * @param liftime
	 * 		过期时间（单位：毫秒）
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument px(Duration liftime) {
		Assert.isNull(liftime, "Liftime must not be null");
		return new GetExArgument(GetExType.PX, liftime.toMillis());
	}

	/**
	 * 设置时间
	 *
	 * @param unixTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument pxAt(long unixTime) {
		return new GetExArgument(GetExType.PXAT, unixTime);
	}

	/**
	 * 设置时间
	 *
	 * @param date
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument pxAt(Date date) {
		return pxAt(date.getTime());
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument pxAt(LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return pxAt(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 设置时间
	 *
	 * @param dateTime
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument pxAt(ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		return pxAt(dateTime.toInstant().toEpochMilli());
	}

	/**
	 * 设置时间
	 *
	 * @param instant
	 * 		时间，具体过期时间，毫秒时间戳
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument pxAt(Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		return pxAt(instant.toEpochMilli());
	}

	/**
	 * 设置键是否持久化
	 *
	 * @return {@link GetExArgument}
	 */
	public static GetExArgument persist() {
		return new GetExArgument(GetExType.PERSIST, 0L);
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().add(getType().name(), getType() == GetExType.PERSIST ? null : getExpires())
				.build();
	}

	/**
	 * 过期时间类型
	 */
	public enum GetExType implements Keyword {
		EX,

		EXAT,

		PX,

		PXAT,

		PERSIST;

		@Override
		public String getValue() {
			return name();
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

}
