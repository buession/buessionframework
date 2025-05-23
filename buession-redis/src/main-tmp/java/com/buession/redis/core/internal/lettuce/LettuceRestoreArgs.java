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
package com.buession.redis.core.internal.lettuce;

import com.buession.redis.core.command.args.RestoreArgument;
import io.lettuce.core.RestoreArgs;

import java.util.Optional;

/**
 * Lettuce {@link RestoreArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceRestoreArgs extends RestoreArgs {

	/**
	 * 构造函数
	 */
	public LettuceRestoreArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param replace
	 * 		是否替换已存在 key
	 */

	public LettuceRestoreArgs(final boolean replace) {
		super();
		replace(this, replace);
	}

	/**
	 * 构造函数
	 *
	 * @param replace
	 * 		是否替换已存在 key
	 * @param absTtl
	 * 		-
	 */
	public LettuceRestoreArgs(final boolean replace, final boolean absTtl) {
		super();
		replace(this, replace);
		absTtl(this, absTtl);
	}

	/**
	 * 构造函数
	 *
	 * @param replace
	 * 		是否替换已存在 key
	 * @param idleTime
	 * 		-
	 * @param frequency
	 * 		-
	 */
	public LettuceRestoreArgs(final boolean replace, final long idleTime, final long frequency) {
		super();
		replace(this, replace);
		idleTime(idleTime);
		frequency(frequency);
	}

	/**
	 * 构造函数
	 *
	 * @param replace
	 * 		是否替换已存在 key
	 * @param absTtl
	 * 		-
	 * @param idleTime
	 * 		-
	 * @param frequency
	 * 		-
	 */
	public LettuceRestoreArgs(final boolean replace, final boolean absTtl, final long idleTime, final long frequency) {
		this(replace, idleTime, frequency);
		absTtl(this, absTtl);
	}

	/**
	 * 构造函数
	 *
	 * @param idleTime
	 * 		-
	 * @param frequency
	 * 		-
	 */
	public LettuceRestoreArgs(final long idleTime, final long frequency) {
		super();
		idleTime(idleTime);
		frequency(frequency);
	}

	/**
	 * 从 {@link RestoreArgument} 创建 {@link RestoreArgs} 实例
	 *
	 * @param restoreArgument
	 *        {@link RestoreArgument}
	 *
	 * @return {@link LettuceRestoreArgs} 实例
	 */
	public static LettuceRestoreArgs from(final RestoreArgument restoreArgument) {
		final LettuceRestoreArgs restoreArgs = new LettuceRestoreArgs();

		replace(restoreArgs, restoreArgument.isReplace());
		absTtl(restoreArgs, restoreArgument.isAbsTtl());
		Optional.ofNullable(restoreArgument.getIdleTime()).ifPresent(restoreArgs::idleTime);
		Optional.ofNullable(restoreArgument.getFrequency()).ifPresent(restoreArgs::frequency);

		return restoreArgs;
	}

	private static void replace(final LettuceRestoreArgs restoreArgs, final Boolean replace) {
		if(Boolean.TRUE.equals(replace)){
			restoreArgs.replace();
		}
	}

	private static void absTtl(final LettuceRestoreArgs restoreArgs, final Boolean absTtl) {
		if(Boolean.TRUE.equals(absTtl)){
			restoreArgs.absttl();
		}
	}

}
