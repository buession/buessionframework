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
package com.buession.redis.core.internal.jedis.args;

import com.buession.core.utils.Assert;
import com.buession.redis.core.command.args.GetExArgument;
import redis.clients.jedis.params.GetExParams;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Jedis {@link GetExParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisGetExParams extends GetExParams {

	/**
	 * 构造函数
	 */
	public JedisGetExParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param getExArgument
	 *        {@link GetExArgument}
	 */
	public JedisGetExParams(final GetExArgument getExArgument) {
		super();

		if(getExArgument != null && getExArgument.getExpires() != null){
			switch(getExArgument.getType()){
				case EX -> ex(getExArgument.getExpires());
				case EXAT -> exAt(getExArgument.getExpires());
				case PX -> px(getExArgument.getExpires());
				case PXAT -> pxAt(getExArgument.getExpires());
				case PERSIST -> persist();
			}
		}
	}

	public JedisGetExParams ex(Duration timeout) {
		Assert.isNull(timeout, "Timeout must not be null");
		ex(timeout.getSeconds());
		return this;
	}

	public JedisGetExParams px(Duration timeout) {
		Assert.isNull(timeout, "Timeout must not be null");
		px(timeout.toMillis());
		return this;
	}

	public JedisGetExParams exAt(Date date) {
		Assert.isNull(date, "Timestamp must not be null");
		exAt(date.getTime() / 1000);
		return this;
	}

	public JedisGetExParams exAt(LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		exAt(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		return this;
	}

	public JedisGetExParams exAt(ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		exAt(dateTime.toEpochSecond());
		return this;
	}

	public JedisGetExParams exAt(Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		exAt(instant.toEpochMilli() / 1000);
		return this;
	}

	public JedisGetExParams pxAt(Date date) {
		Assert.isNull(date, "Timestamp must not be null");
		pxAt(date.getTime());
		return this;
	}

	public JedisGetExParams pxAt(LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		pxAt(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		return this;
	}

	public JedisGetExParams pxAt(ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Timestamp must not be null");
		exAt(dateTime.toInstant().toEpochMilli());
		return this;
	}

	public JedisGetExParams pxAt(Instant instant) {
		Assert.isNull(instant, "Timestamp must not be null");
		exAt(instant.toEpochMilli());
		return this;
	}

}
