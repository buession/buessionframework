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

	/**
	 * 构造函数
	 */
	public LettuceXClaimArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param idleType
	 * 		设置过期时间方式
	 * @param expires
	 * 		过期时间
	 */
	public LettuceXClaimArgs(final XClaimArgument.IdleType idleType, final long expires) {
		super();
		idleTime(this, idleType, expires);
	}

	/**
	 * 构造函数
	 *
	 * @param retryCount
	 * 		重试次数
	 */
	public LettuceXClaimArgs(final int retryCount) {
		super();
		retryCount(retryCount);
	}

	/**
	 * 构造函数
	 *
	 * @param force
	 * 		是否强制
	 */
	public LettuceXClaimArgs(final boolean force) {
		super();
		force(this, force);
	}

	/**
	 * 构造函数
	 *
	 * @param idleType
	 * 		设置过期时间方式
	 * @param expires
	 * 		过期时间
	 * @param retryCount
	 * 		重试次数
	 */
	public LettuceXClaimArgs(final XClaimArgument.IdleType idleType, final long expires, final int retryCount) {
		this(idleType, expires);
		retryCount(retryCount);
	}

	/**
	 * 构造函数
	 *
	 * @param idleType
	 * 		设置过期时间方式
	 * @param expires
	 * 		过期时间
	 * @param force
	 * 		是否强制
	 */
	public LettuceXClaimArgs(final XClaimArgument.IdleType idleType, final long expires, final boolean force) {
		this(idleType, expires);
		force(this, force);
	}

	/**
	 * 构造函数
	 *
	 * @param idleType
	 * 		设置过期时间方式
	 * @param expires
	 * 		过期时间
	 * @param retryCount
	 * 		重试次数
	 * @param force
	 * 		是否强制
	 */
	public LettuceXClaimArgs(final XClaimArgument.IdleType idleType, final long expires, final int retryCount,
							 final boolean force) {
		this(idleType, expires, retryCount);
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

		idleTime(xClaimArgs, xClaimArgument.getIdleType(), xClaimArgument.getIdleTime());
		Optional.ofNullable(xClaimArgument.getRetryCount()).ifPresent(xClaimArgs::retryCount);
		force(xClaimArgs, xClaimArgument.isForce());

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

}
