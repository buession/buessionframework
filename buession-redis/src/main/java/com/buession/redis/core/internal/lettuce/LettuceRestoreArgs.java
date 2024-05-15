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

import com.buession.redis.core.command.KeyCommands;
import io.lettuce.core.RestoreArgs;

/**
 * Lettuce {@link RestoreArgs} 扩展
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class LettuceRestoreArgs extends RestoreArgs {

	public LettuceRestoreArgs() {
		super();
	}

	public LettuceRestoreArgs(final boolean replace) {
		super();
		replace(this, replace);
	}

	public LettuceRestoreArgs(final boolean replace, final long ttl) {
		this(replace);
		ttl(ttl);
	}

	public LettuceRestoreArgs(final boolean replace, final long ttl, final long idleTime, final long frequency) {
		this(replace, ttl);
	}

	public static LettuceRestoreArgs from(final KeyCommands.RestoreArgument restoreArgument) {
		final LettuceRestoreArgs restoreArgs = new LettuceRestoreArgs();

		if(restoreArgument != null){
			replace(restoreArgs, restoreArgument.isReplace());
		}

		return restoreArgs;
	}

	private static void replace(final LettuceRestoreArgs restoreArgs, final Boolean replace) {
		if(Boolean.TRUE.equals(replace)){
			restoreArgs.replace();
		}
	}

}
