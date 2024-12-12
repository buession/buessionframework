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
package com.buession.redis.core.command;

import com.buession.core.Range;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.Constants;
import com.buession.redis.core.Keyword;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public final class CommandArguments {

	private final List<Object> parameters = new ArrayList<>();

	private CommandArguments() {
	}

	private CommandArguments(final byte[] value) {
		add(value);
	}

	private CommandArguments(final char value) {
		add(value);
	}

	private CommandArguments(final short value) {
		add(value);
	}

	private CommandArguments(final int value) {
		add(value);
	}

	private CommandArguments(final long value) {
		add(value);
	}

	private CommandArguments(final float value) {
		add(value);
	}

	private CommandArguments(final double value) {
		add(value);
	}

	private CommandArguments(final boolean value) {
		add(value);
	}

	private CommandArguments(final String value) {
		add(value);
	}

	private CommandArguments(final Collection<?> value) {
		add(value);
	}

	private CommandArguments(final Map<?, ?> value) {
		add(value);
	}

	private CommandArguments(final Range<?> value) {
		add(value);
	}

	private CommandArguments(final Enum<?> value) {
		add(value);
	}

	private CommandArguments(final Object value) {
		add(value);
	}

	private CommandArguments(final byte[]... values) {
		add(values);
	}

	private CommandArguments(final char... values) {
		add(values);
	}

	private CommandArguments(final short... values) {
		add(values);
	}

	private CommandArguments(final int... values) {
		add(values);
	}

	private CommandArguments(final long... values) {
		add(values);
	}

	private CommandArguments(final float... values) {
		add(values);
	}

	private CommandArguments(final double... values) {
		add(values);
	}

	private CommandArguments(final boolean... values) {
		add(values);
	}

	private CommandArguments(final String... values) {
		add(values);
	}

	private CommandArguments(final Collection<?>... values) {
		add(values);
	}

	private CommandArguments(final Map<?, ?>... values) {
		add(values);
	}

	private CommandArguments(final Range<?>... values) {
		add(values);
	}

	private CommandArguments(final Enum<?>... values) {
		add(values);
	}

	private CommandArguments(final Object... values) {
		add(values);
	}

	public static CommandArguments create() {
		return new CommandArguments();
	}

	public static CommandArguments create(final byte[] value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final char value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final short value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final int value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final long value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final float value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final double value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final boolean value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final String value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final Collection<?> value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final Map<?, ?> value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final Range<?> value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final Enum<?> value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final Object value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final byte[]... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final char... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final short... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final int... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final long... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final float... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final double... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final boolean... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final String... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final Collection<?>... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final Map<?, ?>... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final Range<?>... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final Enum<?>... values) {
		return new CommandArguments(values);
	}

	public static CommandArguments create(final Object... values) {
		return new CommandArguments(values);
	}

	public CommandArguments add(final byte[] value) {
		if(value != null){
			parameters.add(value);
		}

		return this;
	}

	public CommandArguments add(final char value) {
		parameters.add(value);
		return this;
	}

	public CommandArguments add(final short value) {
		parameters.add(value);
		return this;
	}

	public CommandArguments add(final int value) {
		parameters.add(value);
		return this;
	}

	public CommandArguments add(final long value) {
		parameters.add(value);
		return this;
	}

	public CommandArguments add(final float value) {
		parameters.add(value);
		return this;
	}

	public CommandArguments add(final double value) {
		parameters.add(value);
		return this;
	}

	public CommandArguments add(final boolean value) {
		parameters.add(value ? "1" : "0");
		return this;
	}

	public CommandArguments add(final String value) {
		if(value != null){
			parameters.add(value);
		}

		return this;
	}

	public CommandArguments add(final Collection<?> value) {
		if(value != null){
			value.forEach(this::add);
		}

		return this;
	}

	public CommandArguments add(final Map<?, ?> value) {
		if(value != null){
			value.forEach(this::add);
		}

		return this;
	}

	public CommandArguments add(final Range<?> value) {
		if(value != null){
			parameters.add(value.getStart());
			parameters.add(value.getEnd());
		}

		return this;
	}

	public CommandArguments add(final Enum<?> value) {
		if(value != null){
			if(value instanceof Keyword){
				parameters.add(((Keyword) value).getValue());
			}else{
				parameters.add(value.name());
			}
		}

		return this;
	}

	public CommandArguments add(final Object value) {
		if(value != null){
			parameters.add(value);
		}

		return this;
	}

	public CommandArguments add(final byte[]... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final char... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final short... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final int... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final long... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final float... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final double... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final boolean... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final String... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final Collection<?>... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final Map<?, ?>... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final Enum<?>... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final Range<?>... values) {
		return doBatchAdd(values);
	}

	public CommandArguments add(final Object... values) {
		return doBatchAdd(values);
	}

	public List<Object> getParameters() {
		return parameters;
	}

	public List<Object> build() {
		return getParameters();
	}

	public String asString() {
		if(Validate.isEmpty(getParameters())){
			return Constants.EMPTY_STRING;
		}else{
			return StringUtils.join(getParameters(), " ");
		}
	}

	@Override
	public String toString() {
		return asString();
	}

	@SafeVarargs
	private final <T> CommandArguments doBatchAdd(final T... values) {
		if(Validate.isNotEmpty(values)){
			for(T value : values){
				add(value);
			}
		}

		return this;
	}

}
