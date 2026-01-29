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
package com.buession.redis.core.internal.jedis;

import com.buession.redis.core.command.CuckooFilterCommands;
import redis.clients.jedis.bloom.CFReserveParams;

import java.util.Optional;

/**
 * Jedis {@link CFReserveParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisCFReserveParams extends CFReserveParams {

	/**
	 * 构造函数
	 */
	public JedisCFReserveParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param bucketSize
	 * 		每个桶能存储的指纹数量
	 * @param maxIterations
	 * 		插入时最大“踢出”尝试次数
	 * @param expansion
	 * 		扩容倍数
	 */
	public JedisCFReserveParams(final Long bucketSize, final Integer maxIterations, final Integer expansion) {
		super();
		bucketSize(bucketSize);
		maxIterations(maxIterations);
		expansion(expansion);
	}

	public static JedisCFReserveParams from(final CuckooFilterCommands.CFReserveArgument cfReserveArgument) {
		final JedisCFReserveParams cfReserveParams = new JedisCFReserveParams();

		if(cfReserveArgument != null){
			Optional.ofNullable(cfReserveArgument.getBucketSize()).ifPresent(cfReserveParams::bucketSize);
			Optional.ofNullable(cfReserveArgument.getMaxIterations()).ifPresent(cfReserveParams::maxIterations);
			Optional.ofNullable(cfReserveArgument.getExpansion()).ifPresent(cfReserveParams::expansion);
		}

		return cfReserveParams;
	}

}
