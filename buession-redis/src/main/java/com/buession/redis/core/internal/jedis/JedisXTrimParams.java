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

import com.buession.redis.core.command.args.ApproximateExactTrimming;
import com.buession.redis.core.command.args.MaxLenMinId;
import com.buession.redis.core.command.args.XTrimArgument;
import redis.clients.jedis.params.XTrimParams;

import java.util.Optional;

/**
 * Jedis {@link XTrimParams} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisXTrimParams extends XTrimParams {

	/**
	 * 构造函数
	 */
	public JedisXTrimParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 */
	public JedisXTrimParams(final MaxLenMinId<?> maxLenMinId) {
		super();
		maxLenMinId(this, maxLenMinId);
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXTrimParams(final MaxLenMinId<?> maxLenMinId, final long limit) {
		this(maxLenMinId);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 */
	public JedisXTrimParams(final MaxLenMinId<?> maxLenMinId, final ApproximateExactTrimming approximateExactTrimming) {
		super();
		maxLenMinId(this, maxLenMinId, approximateExactTrimming);
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXTrimParams(final MaxLenMinId<?> maxLenMinId, final ApproximateExactTrimming approximateExactTrimming,
							final long limit) {
		this(maxLenMinId, approximateExactTrimming);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param limit
	 * 		-
	 */
	public JedisXTrimParams(final long limit) {
		super();
		limit(limit);
	}

	/**
	 * 从 {@link XTrimArgument} 创建 {@link XTrimParams} 实例
	 *
	 * @param xTrimArgument
	 *        {@link XTrimArgument}
	 *
	 * @return {@link JedisXTrimParams} 实例
	 */
	public static JedisXTrimParams from(final XTrimArgument xTrimArgument) {
		final JedisXTrimParams xTrimParams = new JedisXTrimParams();

		if(xTrimArgument != null){
			maxLenMinId(xTrimParams, xTrimArgument.getMaxLenMinId());
			Optional.ofNullable(xTrimArgument.getLimit()).ifPresent(xTrimParams::limit);
		}

		return xTrimParams;
	}

	private static void maxLenMinId(final JedisXTrimParams xTrimParams, final MaxLenMinId<?> maxLenMinId) {
		if(maxLenMinId != null){
			if(maxLenMinId instanceof MaxLenMinId.MaxLen){
				xTrimParams.maxLen(((MaxLenMinId.MaxLen) maxLenMinId).getValue());
			}else if(maxLenMinId instanceof MaxLenMinId.MinId){
				xTrimParams.minId(((MaxLenMinId.MinId) maxLenMinId).getValue().toString());
			}
		}
	}

	private static void maxLenMinId(final JedisXTrimParams xTrimParams, final MaxLenMinId<?> maxLenMinId,
									final ApproximateExactTrimming approximateExactTrimming) {
		maxLenMinId(xTrimParams, maxLenMinId);
		if(maxLenMinId != null){
			if(approximateExactTrimming == ApproximateExactTrimming.APPROXIMATE){
				xTrimParams.approximateTrimming();
			}else if(approximateExactTrimming == ApproximateExactTrimming.EXACT){
				xTrimParams.exactTrimming();
			}
		}
	}

}
