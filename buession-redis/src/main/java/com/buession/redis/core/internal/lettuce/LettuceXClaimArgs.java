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

import com.buession.redis.core.command.args.XClaimArgument;
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

	public LettuceXClaimArgs(final XClaimArgument.IdleType idleType, final Long idleTime) {
		super();
		idleTime(this, idleType, idleTime);
	}

	public LettuceXClaimArgs(final Integer retryCount) {
		super();
		Optional.ofNullable(retryCount).ifPresent(this::retryCount);
	}

	public LettuceXClaimArgs(final Boolean force) {
		super();
		force(this, force);
	}

	public LettuceXClaimArgs(final XClaimArgument.IdleType idleType, final Long idleTime, final Integer retryCount) {
		this(idleType, idleTime);
		Optional.ofNullable(retryCount).ifPresent(this::retryCount);
	}

	public LettuceXClaimArgs(final XClaimArgument.IdleType idleType, final Long idleTime, final Boolean force) {
		this(idleType, idleTime);
		force(this, force);
	}

	public LettuceXClaimArgs(final XClaimArgument.IdleType idleType, final Long idleTime, final Integer retryCount,
							 final Boolean force) {
		this(idleType, idleTime, retryCount);
		force(this, force);
	}

	/**
	 * 从 {@link XClaimArgument} 创建 {@link XClaimArgs} 实例
	 *
	 * @param xClaimArgument
	 *        {@link XClaimArgument}
	 *
	 * @return {@link LettuceXClaimArgs} 实例
	 */
	public static LettuceXClaimArgs from(final XClaimArgument xClaimArgument) {
		final LettuceXClaimArgs xClaimArgs = new LettuceXClaimArgs();

		if(xClaimArgument != null){
			idleTime(xClaimArgs, xClaimArgument.getIdleType(), xClaimArgument.getIdleTime());
			Optional.ofNullable(xClaimArgument.getRetryCount()).ifPresent(xClaimArgs::retryCount);
			force(xClaimArgs, xClaimArgument.isForce());
		}

		return xClaimArgs;
	}

	private static void idleTime(final LettuceXClaimArgs xClaimArgs, final XClaimArgument.IdleType idleType,
								 final Long idleTime) {
		if(idleType != null && idleTime != null){
			switch(idleType){
				case IDLE:
					xClaimArgs.idle(idleTime);
					break;
				case UNIX_TIME:
					xClaimArgs.time(idleTime);
					break;
				default:
					break;
			}
		}
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
