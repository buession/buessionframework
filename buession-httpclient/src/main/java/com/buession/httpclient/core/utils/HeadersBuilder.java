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
package com.buession.httpclient.core.utils;

import com.buession.core.builder.ListBuilder;
import com.buession.core.datetime.TimeZone;
import com.buession.core.utils.Assert;
import com.buession.httpclient.core.Header;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * HTTP 头构建器
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
public class HeadersBuilder {

	private final ListBuilder<Header> builder = ListBuilder.create();

	private static DateTimeFormatter dateTimeFormatter = null;

	/**
	 * 构造函数
	 */
	public HeadersBuilder() {
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 */
	public HeadersBuilder(final String name, final String value) {
		add(name, value);
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 */
	public HeadersBuilder(final String name, final short value) {
		add(name, value);
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 */
	public HeadersBuilder(final String name, final int value) {
		add(name, value);
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 */
	public HeadersBuilder(final String name, final long value) {
		add(name, value);
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 */
	public HeadersBuilder(final String name, final Date value) {
		add(name, value);
	}

	/**
	 * 构造函数
	 *
	 * @param headers
	 * 		HTTP 头
	 */
	public HeadersBuilder(final List<Header> headers) {
		add(headers);
	}

	/**
	 * 添加 HTTP 头
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @return HTTP 头构建器
	 */
	public HeadersBuilder add(final String name, final String value) {
		builder.add(new Header(name, value));
		return this;
	}

	/**
	 * 添加 HTTP 头
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @return HTTP 头构建器
	 */
	public HeadersBuilder add(final String name, final short value) {
		builder.add(new Header(name, Short.toString(value)));
		return this;
	}

	/**
	 * 添加 HTTP 头
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @return HTTP 头构建器
	 */
	public HeadersBuilder add(final String name, final int value) {
		builder.add(new Header(name, Integer.toString(value)));
		return this;
	}

	/**
	 * 添加 HTTP 头
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @return HTTP 头构建器
	 */
	public HeadersBuilder add(final String name, final long value) {
		builder.add(new Header(name, Long.toString(value)));
		return this;
	}

	/**
	 * 添加日期 HTTP 头
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @return HTTP 头构建器
	 */
	public HeadersBuilder add(final String name, final Date value) {
		Assert.isNull(value, "Date cloud not be null.");

		final Instant instant = Instant.ofEpochMilli(value.getTime());
		return add(name, createDateTimeFormatter().format(instant));
	}

	/**
	 * 批量添加 HTTP 头
	 *
	 * @param headers
	 * 		HTTP 头
	 *
	 * @return HTTP 头构建器
	 */
	public HeadersBuilder add(final List<Header> headers) {
		if(headers != null){
			builder.addAll(headers);
		}

		return this;
	}

	/**
	 * 构建 HTTP 头
	 *
	 * @return HTTP 头
	 */
	public List<Header> build() {
		return builder.build();
	}

	protected static DateTimeFormatter createDateTimeFormatter() {
		if(dateTimeFormatter == null){
			dateTimeFormatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss GMT", Locale.US);
			dateTimeFormatter.withZone(TimeZone.GMT.toZoneId());
		}

		return dateTimeFormatter;
	}

}
