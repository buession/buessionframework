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
package com.buession.core.utils;

import com.buession.lang.Constants;

/**
 * 字符串拼接处理，其可以在初始化的时候指定分隔符和前缀后缀；
 * 与 {@link java.util.StringJoiner} 不同的是，{#add} 方法可以像 {@link StringBuilder} 为其它类型的参数，以及分隔符和前后缀可以为 {@code null}。
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class StringJoiner {

	/**
	 * 前缀
	 */
	private final String prefix;

	/**
	 * 分隔符
	 */
	private final String delimiter;

	/**
	 * 后缀
	 */
	private final String suffix;

	private StringBuilder value;

	private final String emptyValue;

	/**
	 * 构造函数
	 *
	 * @param delimiter
	 * 		分隔符
	 */
	public StringJoiner(CharSequence delimiter) {
		this(delimiter, Constants.EMPTY_STRING, Constants.EMPTY_STRING);
	}

	/**
	 * 构造函数
	 *
	 * @param delimiter
	 * 		分隔符
	 * @param prefix
	 * 		前缀
	 * @param suffix
	 * 		后缀
	 */
	public StringJoiner(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
		this.prefix = prefix == null ? Constants.EMPTY_STRING : prefix.toString();
		this.delimiter = delimiter == null ? Constants.EMPTY_STRING : delimiter.toString();
		this.suffix = suffix == null ? Constants.EMPTY_STRING : suffix.toString();
		this.emptyValue = this.prefix + this.suffix;
	}

	/**
	 * 构造函数
	 *
	 * @param delimiter
	 * 		分隔符
	 * @param prefix
	 * 		前缀
	 * @param suffix
	 * 		后缀
	 */
	public StringJoiner(CharSequence delimiter, char prefix, char suffix) {
		this(delimiter, prefix + "", suffix + "");
	}

	/**
	 * 构造函数
	 *
	 * @param delimiter
	 * 		分隔符
	 */
	public StringJoiner(char delimiter) {
		this(delimiter, Constants.EMPTY_STRING, Constants.EMPTY_STRING);
	}

	/**
	 * 构造函数
	 *
	 * @param delimiter
	 * 		分隔符
	 * @param prefix
	 * 		前缀
	 * @param suffix
	 * 		后缀
	 */
	public StringJoiner(char delimiter, CharSequence prefix, CharSequence suffix) {
		this(delimiter + "", prefix, suffix);
	}

	/**
	 * 构造函数
	 *
	 * @param delimiter
	 * 		分隔符
	 * @param prefix
	 * 		前缀
	 * @param suffix
	 * 		后缀
	 */
	public StringJoiner(char delimiter, char prefix, char suffix) {
		this(delimiter + "", prefix + "", suffix + "");
	}

	public StringJoiner add(final boolean value) {
		prepareBuilder().append(value);
		return this;
	}

	public StringJoiner add(final float value) {
		prepareBuilder().append(value);
		return this;
	}

	public StringJoiner add(final double value) {
		prepareBuilder().append(value);
		return this;
	}

	public StringJoiner add(final short value) {
		prepareBuilder().append(value);
		return this;
	}

	public StringJoiner add(final int value) {
		prepareBuilder().append(value);
		return this;
	}

	public StringJoiner add(final long value) {
		prepareBuilder().append(value);
		return this;
	}

	public StringJoiner add(final byte[] str) {
		if(str != null){
			prepareBuilder().append(new String(str));
		}

		return this;
	}

	public StringJoiner add(final char[] str) {
		prepareBuilder().append(str);
		return this;
	}

	public StringJoiner add(final char[] str, final int offset, final int length) {
		prepareBuilder().append(str, offset, length);
		return this;
	}

	public StringJoiner add(final CharSequence s) {
		prepareBuilder().append(s);
		return this;
	}

	public StringJoiner add(final CharSequence s, final int start, final int end) {
		prepareBuilder().append(s, start, end);
		return this;
	}

	public StringJoiner add(final String obj) {
		prepareBuilder().append(obj);
		return this;
	}

	public StringJoiner add(final Object obj) {
		prepareBuilder().append(obj);
		return this;
	}

	public int length() {
		return (value != null ? value.length() + suffix.length() : emptyValue.length());
	}

	@Override
	public String toString() {
		if(value == null){
			return emptyValue;
		}else{
			if(Constants.EMPTY_STRING.equals(suffix) == false){
				value.append(suffix);
			}

			return value.toString();
		}
	}

	private StringBuilder prepareBuilder() {
		if(value != null){
			if(delimiter != null){
				value.append(delimiter);
			}
		}else{
			value = new StringBuilder();
			if(prefix != null){
				value.append(prefix);
			}
		}

		return value;
	}

}
