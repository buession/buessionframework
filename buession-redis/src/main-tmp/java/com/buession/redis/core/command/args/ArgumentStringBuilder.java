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
package com.buession.redis.core.command.args;

import com.buession.core.utils.StringJoiner;
import com.buession.lang.Constants;
import com.buession.redis.utils.SafeEncoder;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
class ArgumentStringBuilder {

	private final StringJoiner joiner = new StringJoiner(Constants.SPACING_STRING);

	private ArgumentStringBuilder() {
	}

	public static ArgumentStringBuilder create() {
		return new ArgumentStringBuilder();
	}

	public ArgumentStringBuilder add(final String name, final Boolean value) {
		if(value != null){
			joiner.add(name).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final String name, final Short value) {
		if(value != null){
			joiner.add(name).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final String name, final Integer value) {
		if(value != null){
			joiner.add(name).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final String name, final Long value) {
		if(value != null){
			joiner.add(name).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final String name, final Float value) {
		if(value != null){
			joiner.add(name).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final String name, final Double value) {
		if(value != null){
			joiner.add(name).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final String name, final byte[] value) {
		if(value != null){
			joiner.add(name).add(SafeEncoder.encode(value));
		}

		return this;
	}

	public ArgumentStringBuilder add(final String name, final CharSequence value) {
		if(value != null){
			joiner.add(name).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final String name, final Enum<?> value) {
		if(value != null){
			joiner.add(name).add(value.name());
		}

		return this;
	}

	public ArgumentStringBuilder add(final String name, final Object value) {
		if(value != null){
			joiner.add(name).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final Enum<?> name, final Boolean value) {
		if(value != null){
			joiner.add(name.name()).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final Enum<?> name, final Short value) {
		if(value != null){
			joiner.add(name.name()).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final Enum<?> name, final Integer value) {
		if(value != null){
			joiner.add(name.name()).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final Enum<?> name, final Long value) {
		if(value != null){
			joiner.add(name.name()).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final Enum<?> name, final Float value) {
		if(value != null){
			joiner.add(name.name()).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final Enum<?> name, final Double value) {
		if(value != null){
			joiner.add(name.name()).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final Enum<?> name, final byte[] value) {
		if(value != null){
			joiner.add(name.name()).add(SafeEncoder.encode(value));
		}

		return this;
	}

	public ArgumentStringBuilder add(final Enum<?> name, final CharSequence value) {
		if(value != null){
			joiner.add(name.name()).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder add(final Enum<?> name, final Enum<?> value) {
		if(value != null){
			joiner.add(name.name()).add(value.name());
		}

		return this;
	}

	public ArgumentStringBuilder add(final Enum<?> name, final Object value) {
		if(value != null){
			joiner.add(name.name()).add(value);
		}

		return this;
	}

	public ArgumentStringBuilder append(final Boolean value) {
		if(value != null){
			joiner.add(value);
		}

		return this;
	}

	public ArgumentStringBuilder append(final Short value) {
		if(value != null){
			joiner.add(value);
		}

		return this;
	}

	public ArgumentStringBuilder append(final Integer value) {
		if(value != null){
			joiner.add(value);
		}

		return this;
	}

	public ArgumentStringBuilder append(final Long value) {
		if(value != null){
			joiner.add(value);
		}

		return this;
	}

	public ArgumentStringBuilder append(final Float value) {
		if(value != null){
			joiner.add(value);
		}

		return this;
	}

	public ArgumentStringBuilder append(final Double value) {
		if(value != null){
			joiner.add(value);
		}

		return this;
	}

	public ArgumentStringBuilder append(final byte[] value) {
		if(value != null){
			joiner.add(SafeEncoder.encode(value));
		}

		return this;
	}

	public ArgumentStringBuilder append(final CharSequence value) {
		if(value != null){
			joiner.add(value);
		}

		return this;
	}

	public ArgumentStringBuilder append(final Enum<?> value) {
		if(value != null){
			joiner.add(value.name());
		}

		return this;
	}

	public ArgumentStringBuilder append(final Object value) {
		if(value != null){
			joiner.add(value);
		}

		return this;
	}

	public String build() {
		return joiner.toString();
	}

	@Override
	public String toString() {
		return joiner.toString();
	}

}
