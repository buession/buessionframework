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

import com.buession.redis.core.command.BloomFilterCommands;
import redis.clients.jedis.bloom.BFReserveParams;

import java.util.Optional;

/**
 * Jedis {@link BFReserveParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisBFReserveParams extends BFReserveParams {

	/**
	 * 构造函数
	 */
	public JedisBFReserveParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param expansion
	 * 		扩容倍数
	 */
	public JedisBFReserveParams(final Integer expansion) {
		super();
		expansion(expansion);
	}

	/**
	 * 构造函数
	 *
	 * @param nonScaling
	 * 		是否禁用自动扩容机制
	 */
	public JedisBFReserveParams(final Boolean nonScaling) {
		super();
		if(Boolean.TRUE.equals(nonScaling)){
			nonScaling();
		}
	}

	/**
	 * 构造函数
	 *
	 * @param expansion
	 * 		-
	 * @param nonScaling
	 * 		是否禁用自动扩容机制
	 */
	public JedisBFReserveParams(final Integer expansion, final Boolean nonScaling) {
		this(nonScaling);
		expansion(expansion);
	}

	public static JedisBFReserveParams from(final BloomFilterCommands.BFReserveArgument bfInsertArgument) {
		final JedisBFReserveParams bfReserveParams = new JedisBFReserveParams();

		if(bfInsertArgument != null){
			Optional.ofNullable(bfInsertArgument.getExpansion()).ifPresent(bfReserveParams::expansion);

			if(bfInsertArgument.isNonScaling()){
				bfReserveParams.nonScaling();
			}
		}

		return bfReserveParams;
	}

}
