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

import com.buession.redis.core.command.StreamCommands;
import io.lettuce.core.XClaimArgs;

import java.util.Optional;

/**
 * Lettuce {@link XClaimArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceXClaimArgs extends XClaimArgs {

	public LettuceXClaimArgs() {
		super();
	}

	public LettuceXClaimArgs(final long minIdleTime) {
		super();
		minIdleTime((minIdleTime));
	}

	public LettuceXClaimArgs(final long minIdleTime, final boolean force) {
		this(minIdleTime);
		force(force);
	}

	public LettuceXClaimArgs(final long minIdleTime, final long idle, final long time,
							 final long retryCount, final boolean force, final boolean justId) {
		this(minIdleTime, force);
		idle(idle);
		time(time);
		retryCount(retryCount);
		force(this, force);
		justId(this, justId);
	}

	public static LettuceXClaimArgs from(final StreamCommands.XClaimArgument xClaimArgument) {
		final LettuceXClaimArgs xClaimArgs = new LettuceXClaimArgs();

		if(xClaimArgument != null){
			Optional.ofNullable(xClaimArgument.getIdleTime()).ifPresent(xClaimArgs::idle);
			Optional.ofNullable(xClaimArgument.getIdleUnixTime()).ifPresent(xClaimArgs::time);
			Optional.ofNullable(xClaimArgument.getIdleUnixTime()).ifPresent(xClaimArgs::time);
			Optional.ofNullable(xClaimArgument.getRetryCount()).ifPresent(xClaimArgs::retryCount);
			force(xClaimArgs, xClaimArgument.isForce());
		}

		return xClaimArgs;
	}

	private static void force(final LettuceXClaimArgs xClaimArgs, final Boolean force) {
		if(Boolean.TRUE.equals(force)){
			xClaimArgs.force();
		}
	}

	private static void justId(final LettuceXClaimArgs xClaimArgs, final Boolean justId) {
		if(Boolean.TRUE.equals(justId)){
			xClaimArgs.justid();
		}
	}

}
