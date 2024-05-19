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
package com.buession.redis.utils;

import java.util.StringJoiner;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class ObjectStringBuilder {

	private final StringJoiner joiner = new StringJoiner(", ", "{", "}");

	private ObjectStringBuilder() {
	}

	public static ObjectStringBuilder create() {
		return new ObjectStringBuilder();
	}

	public ObjectStringBuilder add(final String name, final boolean value) {
		joiner.add(name + '=' + value);
		return this;
	}

	public ObjectStringBuilder add(final String name, final short value) {
		joiner.add(name + '=' + value);
		return this;
	}

	public ObjectStringBuilder add(final String name, final int value) {
		joiner.add(name + '=' + value);
		return this;
	}

	public ObjectStringBuilder add(final String name, final long value) {
		joiner.add(name + '=' + value);
		return this;
	}

	public ObjectStringBuilder add(final String name, final float value) {
		joiner.add(name + '=' + value);
		return this;
	}

	public ObjectStringBuilder add(final String name, final double value) {
		joiner.add(name + '=' + value);
		return this;
	}

	public ObjectStringBuilder add(final String name, final byte[] value) {
		if(value != null){
			joiner.add(name + '=' + SafeEncoder.encode(value));
		}

		return this;
	}

	public ObjectStringBuilder add(final String name, final Object value) {
		joiner.add(name + '=');
		return this;
	}

	public ObjectStringBuilder addIfAbsent(final String name, final Object value) {
		if(value != null){
			joiner.add(name + '=' + value);
		}

		return this;
	}

	public ObjectStringBuilder append(final CharSequence seq) {
		if(seq != null){
			joiner.add(seq);
		}

		return this;
	}

	public String build() {
		return joiner.toString();
	}

}
