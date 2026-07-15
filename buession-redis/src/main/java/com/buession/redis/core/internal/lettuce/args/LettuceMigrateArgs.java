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
package com.buession.redis.core.internal.lettuce.args;

import com.buession.core.validator.Validate;
import com.buession.redis.core.command.args.key.MigrateArgument;
import io.lettuce.core.MigrateArgs;

import java.util.List;

/**
 * Lettuce {@link MigrateArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceMigrateArgs<K> extends MigrateArgs<K> {

	/**
	 * 构造函数
	 */
	public LettuceMigrateArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param keys
	 * 		Key
	 */
	@SuppressWarnings({"unchecked"})
	public LettuceMigrateArgs(final K... keys) {
		super();
		keys(keys);
	}

	/**
	 * 构造函数
	 *
	 * @param keys
	 * 		Key
	 */
	public LettuceMigrateArgs(final List<K> keys) {
		super();
		keys(keys);
	}

	/**
	 * 构造函数
	 *
	 * @param argument
	 *        {@link MigrateArgument}
	 * @param keys
	 * 		Key
	 */
	@SuppressWarnings({"unchecked"})
	public LettuceMigrateArgs(final MigrateArgument argument, final K... keys) {
		super();
		applyArgument(argument);
		keys(keys);
	}

	/**
	 * 构造函数
	 *
	 * @param argument
	 *        {@link MigrateArgument}
	 * @param keys
	 * 		Key
	 */
	public LettuceMigrateArgs(final MigrateArgument argument, final List<K> keys) {
		super();
		applyArgument(argument);
		keys(keys);
	}

	private void applyArgument(final MigrateArgument argument) {
		if(argument != null){
			if(argument.getMode() == MigrateArgument.Mode.COPY){
				copy();
			}else if(argument.getMode() == MigrateArgument.Mode.REPLACE){
				replace();
			}

			if(Validate.isNotEmpty(argument.getPassword())){
				if(Validate.isNotEmpty(argument.getUsername())){
					auth2(argument.getUsername(), argument.getPassword());
				}else{
					auth(argument.getPassword());
				}
			}
		}
	}

}
