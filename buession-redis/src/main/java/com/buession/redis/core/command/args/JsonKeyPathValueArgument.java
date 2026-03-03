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

import com.buession.redis.utils.ArgStringBuilder;

/**
 * JSON.MSET 参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class JsonKeyPathValueArgument {

	/**
	 * Key
	 */
	private String key;

	/**
	 * JSONPath 表达式
	 */
	private String path;

	/**
	 * 值
	 */
	private String value;

	/**
	 * 构造函数
	 */
	public JsonKeyPathValueArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param key
	 * 		Key
	 * @param path
	 * 		JSONPath 表达式
	 * @param value
	 * 		值
	 */
	public JsonKeyPathValueArgument(final String key, final String path, final String value) {
		this.key = key;
		this.path = path;
		this.value = value;
	}

	/**
	 * 返回 Key
	 *
	 * @return Key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置 Key
	 *
	 * @param key
	 * 		Key
	 *
	 * @return {@link JsonKeyPathValueArgument}
	 */
	public JsonKeyPathValueArgument setKey(String key) {
		this.key = key;
		return this;
	}

	/**
	 * 返回 JSONPath 表达式
	 *
	 * @return JSONPath 表达式
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 设置 JSONPath 表达式
	 *
	 * @param path
	 * 		JSONPath 表达式
	 *
	 * @return {@link JsonKeyPathValueArgument}
	 */
	public JsonKeyPathValueArgument setPath(String path) {
		this.path = path;
		return this;
	}

	/**
	 * 返回值
	 *
	 * @return 值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置值
	 *
	 * @param value
	 * 		值
	 *
	 * @return {@link JsonKeyPathValueArgument}
	 */
	public JsonKeyPathValueArgument setValue(String value) {
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.append(getKey())
				.append(getPath())
				.append(getValue())
				.build();
	}

}
