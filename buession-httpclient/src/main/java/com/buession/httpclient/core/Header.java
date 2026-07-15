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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

import com.buession.lang.NameValue;

import java.nio.charset.StandardCharsets;

/**
 * HTTP 头
 *
 * @author Yong.Teng
 */
public class Header extends NameValue<String, String> {

	private final static long serialVersionUID = 535326643150736933L;

	/**
	 * 构造函数
	 */
	public Header() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 */
	public Header(String name, String value) {
		super(name, value);
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, char value) {
		this(name, Character.toString(value));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, byte[] value) {
		this(name, new String(value, StandardCharsets.UTF_8));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, short value) {
		this(name, Short.toString(value));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, int value) {
		this(name, Integer.toString(value));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, long value) {
		this(name, Long.toString(value));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, float value) {
		this(name, Float.toString(value));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, double value) {
		this(name, Double.toString(value));
	}

	@Override
	public String toString() {
		return getName() + ": " + getValue();
	}

}
