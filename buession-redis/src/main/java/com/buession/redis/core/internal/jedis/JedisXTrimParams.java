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

import com.buession.redis.core.command.StreamCommands;
import redis.clients.jedis.params.XTrimParams;

import java.util.Optional;

/**
 * Jedis {@link XTrimParams} 扩展
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class JedisXTrimParams extends XTrimParams {

	public JedisXTrimParams() {
		super();
	}

	public JedisXTrimParams(final long maxLen) {
		super();
		maxLen(maxLen);
	}

	public JedisXTrimParams(final long maxLen, final String minId) {
		this(maxLen);
		minId(minId);
	}

	public JedisXTrimParams(final long maxLen, final long limit) {
		this(maxLen);
		limit(limit);
	}

	public JedisXTrimParams(final long maxLen, final String minId, final long limit) {
		this(maxLen, minId);
		limit(limit);
	}

	public JedisXTrimParams(final long maxLen, final boolean approximateTrimming, final boolean exactTrimming) {
		this(maxLen);
		approximateTrimming(this, approximateTrimming);
		exactTrimming(this, exactTrimming);
	}

	public JedisXTrimParams(final long maxLen, final String minId, final long limit, final boolean approximateTrimming,
							final boolean exactTrimming) {
		this(maxLen, approximateTrimming, exactTrimming);
		maxLen(maxLen);
		minId(minId);
		limit(limit);
	}

	public static JedisXTrimParams from(final StreamCommands.XTrimArgument xTrimArgument) {
		final JedisXTrimParams xTrimParams = new JedisXTrimParams();

		if(xTrimArgument != null){
			Optional.ofNullable(xTrimArgument.getMaxLen()).ifPresent(xTrimParams::maxLen);
			Optional.ofNullable(xTrimArgument.getMinId()).ifPresent(xTrimParams::minId);
			Optional.ofNullable(xTrimArgument.getLimit()).ifPresent(xTrimParams::limit);

			approximateTrimming(xTrimParams, xTrimArgument.isApproximateTrimming());
			exactTrimming(xTrimParams, xTrimArgument.isExactTrimming());
		}

		return xTrimParams;
	}

	private static void approximateTrimming(final JedisXTrimParams xTrimParams, final Boolean approximateTrimming) {
		if(Boolean.TRUE.equals(approximateTrimming)){
			xTrimParams.approximateTrimming();
		}
	}

	private static void exactTrimming(final JedisXTrimParams xTrimParams, final Boolean exactTrimming) {
		if(Boolean.TRUE.equals(exactTrimming)){
			xTrimParams.exactTrimming();
		}
	}

}
