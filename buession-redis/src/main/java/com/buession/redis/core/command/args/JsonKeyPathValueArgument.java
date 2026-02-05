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
import com.buession.redis.utils.SafeEncoder;

import java.util.Objects;

/**
 * JSON.MSET 参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class JsonKeyPathValueArgument<T> {

	/**
	 * Key
	 */
	private T key;

	/**
	 * JSONPath 表达式
	 */
	private T path;

	/**
	 * 值
	 */
	private T value;

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
	public JsonKeyPathValueArgument(T key, T path, T value) {
		this.key = key;
		this.path = path;
		this.value = value;
	}

	/**
	 * 返回 Key
	 *
	 * @return Key
	 */
	public T getKey() {
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
	public JsonKeyPathValueArgument<T> setKey(T key) {
		this.key = key;
		return this;
	}

	/**
	 * 返回 JSONPath 表达式
	 *
	 * @return JSONPath 表达式
	 */
	public T getPath() {
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
	public JsonKeyPathValueArgument<T> setPath(T path) {
		this.path = path;
		return this;
	}

	/**
	 * 返回值
	 *
	 * @return 值
	 */
	public T getValue() {
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
	public JsonKeyPathValueArgument<T> setValue(T value) {
		this.value = value;
		return this;
	}

	public static class StringJsonKeyPathValueArgument extends JsonKeyPathValueArgument<String> {

		/**
		 * 构造函数
		 */
		public StringJsonKeyPathValueArgument() {
			super();
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
		public StringJsonKeyPathValueArgument(String key, String path, String value) {
			super(key, path, value);
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

	public static class BinaryJsonKeyPathValueArgument extends JsonKeyPathValueArgument<byte[]> {

		/**
		 * 构造函数
		 */
		public BinaryJsonKeyPathValueArgument() {
			super();
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
		public BinaryJsonKeyPathValueArgument(byte[] key, byte[] path, byte[] value) {
			super(key, path, value);
		}

		@Override
		public String toString() {
			return ArgStringBuilder.create()
					.append(SafeEncoder.encode(getKey()))
					.append(SafeEncoder.encode(getPath()))
					.append(SafeEncoder.encode(getValue()))
					.build();
		}

	}

}
