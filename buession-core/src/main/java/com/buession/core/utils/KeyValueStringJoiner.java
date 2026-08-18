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
package com.buession.core.utils;

/**
 * Key-Value 字符串拼接器
 *
 * @author Yong.Teng
 * @since 5.0.0
 */
public class KeyValueStringJoiner {

	private final StringJoiner joiner;

	private final String delimiter;

	/**
	 * 构造函数
	 *
	 * @param groupDelimiter
	 * 		参数组分隔符
	 */
	public KeyValueStringJoiner(char groupDelimiter) {
		this("=", groupDelimiter);
	}

	/**
	 * 构造函数
	 *
	 * @param groupDelimiter
	 * 		参数组分隔符
	 */
	public KeyValueStringJoiner(CharSequence groupDelimiter) {
		this("=", groupDelimiter);
	}

	/**
	 * 构造函数
	 *
	 * @param delimiter
	 * 		分隔符
	 * @param groupDelimiter
	 * 		参数组分隔符
	 */
	public KeyValueStringJoiner(CharSequence delimiter, CharSequence groupDelimiter) {
		this.delimiter = delimiter.toString();
		joiner = new StringJoiner(groupDelimiter);
	}

	/**
	 * 构造函数
	 *
	 * @param delimiter
	 * 		分隔符
	 * @param groupDelimiter
	 * 		参数组分隔符
	 */
	public KeyValueStringJoiner(CharSequence delimiter, char groupDelimiter) {
		this.delimiter = delimiter.toString();
		joiner = new StringJoiner(groupDelimiter);
	}

	/**
	 * 构造函数
	 *
	 * @param delimiter
	 * 		分隔符
	 * @param groupDelimiter
	 * 		参数组分隔符
	 */
	public KeyValueStringJoiner(char delimiter, char groupDelimiter) {
		this.delimiter = Character.toString(delimiter);
		joiner = new StringJoiner(groupDelimiter);
	}

	/**
	 * 构造函数
	 *
	 * @param delimiter
	 * 		分隔符
	 * @param groupDelimiter
	 * 		参数组分隔符
	 */
	public KeyValueStringJoiner(char delimiter, CharSequence groupDelimiter) {
		this.delimiter = Character.toString(delimiter);
		joiner = new StringJoiner(groupDelimiter);
	}

	public KeyValueStringJoiner add(final String name, final boolean value) {
		joiner.add(name + delimiter + value);
		return this;
	}

	public KeyValueStringJoiner add(final String name, final float value) {
		joiner.add(name + delimiter + value);
		return this;
	}

	public KeyValueStringJoiner add(final String name, final double value) {
		joiner.add(name + delimiter + value);
		return this;
	}

	public KeyValueStringJoiner add(final String name, final short value) {
		joiner.add(name + delimiter + value);
		return this;
	}

	public KeyValueStringJoiner add(final String name, final int value) {
		joiner.add(name + delimiter + value);
		return this;
	}

	public KeyValueStringJoiner add(final String name, final long value) {
		joiner.add(name + delimiter + value);
		return this;
	}

	public KeyValueStringJoiner add(final String name, final byte[] value) {
		joiner.add(name + delimiter + (value == null ? "" : new String(value)));
		return this;
	}

	public KeyValueStringJoiner add(final String name, final char[] value) {
		joiner.add(name + delimiter + (value == null ? "" : new String(value)));
		return this;
	}

	public KeyValueStringJoiner add(final String name, final CharSequence value) {
		joiner.add(name + delimiter + value);
		return this;
	}

	public KeyValueStringJoiner add(final String name, final String value) {
		joiner.add(name + delimiter + value);
		return this;
	}

	public KeyValueStringJoiner add(final String name, final Object value) {
		joiner.add(name + delimiter + value);
		return this;
	}

	public KeyValueStringJoiner addIfAbsent(final String name, final byte[] value) {
		if(value != null){
			joiner.add(name + delimiter + new String(value));
		}

		return this;
	}

	public KeyValueStringJoiner addIfAbsent(final String name, final char[] value) {
		if(value != null){
			joiner.add(name + delimiter + new String(value));
		}

		return this;
	}

	public KeyValueStringJoiner addIfAbsent(final String name, final CharSequence value) {
		if(value != null){
			joiner.add(name + delimiter + value);
		}

		return this;
	}

	public KeyValueStringJoiner addIfAbsent(final String name, final String value) {
		if(value != null){
			joiner.add(name + delimiter + value);
		}

		return this;
	}

	public KeyValueStringJoiner addIfAbsent(final String name, final Object value) {
		if(value != null){
			joiner.add(name + delimiter + value);
		}

		return this;
	}

	@Override
	public String toString() {
		return joiner.toString();
	}

}
