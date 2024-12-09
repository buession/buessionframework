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


	private CommandArguments(final ProtocolCommand value) {
		add(value);
	}

	private CommandArguments(final ProtocolCommand... values) {
		add(values);
	}

	public static CommandArguments create(final Keyword value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final ProtocolCommand value) {
		return new CommandArguments(value);
	}

	public static CommandArguments create(final ProtocolCommand... values) {
		return new CommandArguments(values);
	}

	public CommandArguments add(final ProtocolCommand value) {
		if(value != null){
			parameters.add(value.getName());
		}

		return this;
	}

	public CommandArguments add(final ProtocolCommand... values) {
		if(Validate.isNotEmpty(values)){
			for(Object value : values){
				add(value);
			}
		}

		return this;
	}

}
