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

import com.buession.core.collect.Arrays;
import com.buession.core.validator.Validate;
import com.buession.lang.Constants;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Yong.Teng
 */
public final class CommandArguments {

	private final static String NIL = "<nil>";

	private final Map<String, Object> parameters = new LinkedHashMap<>();

	private CommandArguments() {
	}

	private CommandArguments(final String key, final Object value) {
		put(key, value);
	}

	private CommandArguments(final String key, final Object... values) {
		put(key, values);
	}

	public static CommandArguments create() {
		return new CommandArguments();
	}

	public static CommandArguments create(final String key, final Object value) {
		return new CommandArguments(key, value);
	}

	public static CommandArguments create(final String key, final Object... values) {
		return new CommandArguments(key, values);
	}

	public CommandArguments put(final String key, final Object value) {
		parameters.put(key, Optional.ofNullable(value).orElse(NIL));
		return this;
	}

	public CommandArguments put(final String key, final Object... values) {
		parameters.put(key, values == null ? NIL : Arrays.toString(values));
		return this;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public Map<String, Object> build() {
		return getParameters();
	}

	public String asString() {
		if(Validate.isEmpty(getParameters())){
			return Constants.EMPTY_STRING;
		}else{
			final StringBuilder sb = new StringBuilder(getParameters().size() * 8);

			getParameters().forEach((name, value)->{
				if(sb.length() > 0){
					sb.append(", ");
				}

				sb.append(name).append(" => ").append(value);
			});

			return sb.toString();
		}
	}

	@Override
	public String toString() {
		return asString();
	}

}
