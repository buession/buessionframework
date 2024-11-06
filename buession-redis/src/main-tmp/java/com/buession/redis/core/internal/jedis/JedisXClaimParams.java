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
package com.buession.redis.core.internal.jedis;

import com.buession.redis.core.command.args.XClaimArgument;
import redis.clients.jedis.params.XClaimParams;

import java.util.Optional;

/**
 * Jedis {@link XClaimParams} 扩展类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class JedisXClaimParams extends XClaimParams {

	/**
	 * 构造函数
	 */
	public JedisXClaimParams() {
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
	public JedisXClaimParams(final XClaimArgument.IdleType idleType, final long expires) {
		super();
		idleTime(this, idleType, expires);
	}

	/**
	 * 构造函数
	 *
	 * @param retryCount
	 * 		重试次数
	 */
	public JedisXClaimParams(final int retryCount) {
		super();
		retryCount(retryCount);
	}

	/**
	 * 构造函数
	 *
	 * @param force
	 * 		是否强制
	 */
	public JedisXClaimParams(final boolean force) {
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
	public JedisXClaimParams(final XClaimArgument.IdleType idleType, final long expires, final int retryCount) {
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
	public JedisXClaimParams(final XClaimArgument.IdleType idleType, final long expires, final boolean force) {
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
	public JedisXClaimParams(final XClaimArgument.IdleType idleType, final long expires, final int retryCount,
							 final boolean force) {
		this(idleType, expires, retryCount);
		force(this, force);
	}

	/**
	 * 从 {@link XClaimArgument} 创建 {@link XClaimParams} 实例
	 *
	 * @param xClaimArgument
	 *        {@link XClaimArgument}
	 *
	 * @return {@link JedisXClaimParams} 实例
	 */
	public static JedisXClaimParams from(final XClaimArgument xClaimArgument) {
		final JedisXClaimParams xClaimParams = new JedisXClaimParams();

		idleTime(xClaimParams, xClaimArgument.getIdleType(), xClaimArgument.getIdleTime());
		Optional.ofNullable(xClaimArgument.getRetryCount()).ifPresent(xClaimParams::retryCount);
		force(xClaimParams, xClaimArgument.isForce());

		return xClaimParams;
	}

	private static void idleTime(final XClaimParams xClaimParams, final XClaimArgument.IdleType idleType,
								 final Long idleTime) {
		if(idleType != null && idleTime != null){
			switch(idleType){
				case IDLE:
					xClaimParams.idle(idleTime);
					break;
				case UNIX_TIME:
					xClaimParams.time(idleTime);
					break;
				default:
					break;
			}
		}
	}

	private static void force(final XClaimParams xClaimParams, final Boolean force) {
		if(Boolean.TRUE.equals(force)){
			xClaimParams.force();
		}
	}

}
