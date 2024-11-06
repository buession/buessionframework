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

import com.buession.redis.core.BitType;
import com.buession.redis.core.internal.convert.jedis.params.BitTypeConverter;
import redis.clients.jedis.args.BitCountOption;
import redis.clients.jedis.params.BitPosParams;

/**
 * Jedis {@link BitPosParams} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisBitPosParams extends BitPosParams {

	private final static BitTypeConverter bitTypeConverter = new BitTypeConverter();

	/**
	 * 构造函数
	 */
	public JedisBitPosParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		开始位置
	 */
	public JedisBitPosParams(final long start) {
		super(start);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 */
	public JedisBitPosParams(final long start, final long end) {
		super(start, end);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		开始位置
	 * @param modifier
	 *        {@link BitCountOption}
	 */
	public JedisBitPosParams(final long start, final BitCountOption modifier) {
		super(start);
		modifier(modifier);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		开始位置
	 * @param bitType
	 *        {@link BitType}
	 */
	public JedisBitPosParams(final long start, final BitType bitType) {
		super(start);
		modifier(bitType);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param modifier
	 *        {@link BitCountOption}
	 */
	public JedisBitPosParams(final long start, final long end, final BitCountOption modifier) {
		super(start, end);
		modifier(modifier);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param bitType
	 *        {@link BitType}
	 */
	public JedisBitPosParams(final long start, final long end, final BitType bitType) {
		super(start, end);
		modifier(bitType);
	}

	public JedisBitPosParams modifier(final BitType bitType) {
		modifier(bitTypeConverter.convert(bitType));
		return this;
	}

}
