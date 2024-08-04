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

import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.args.LPosArgument;
import redis.clients.jedis.params.LPosParams;

import java.util.Optional;

/**
 * Jedis {@link LPosParams} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisLPosParams extends LPosParams {

	/**
	 * 构造函数
	 */
	public JedisLPosParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param rank
	 * 		返回第几个匹配的元素
	 */
	public JedisLPosParams(final int rank) {
		super();
		rank(rank);
	}

	/**
	 * 构造函数
	 *
	 * @param rank
	 * 		返回第几个匹配的元素
	 * @param maxLen
	 * 		查找成员个数
	 */
	public JedisLPosParams(final int rank, final int maxLen) {
		super();
		rank(rank);
		maxlen(maxLen);
	}

	/**
	 * 从 {@link LPosArgument} 创建 {@link LPosParams} 实例
	 *
	 * @param lPosArgument
	 *        {@link LPosArgument}
	 *
	 * @return {@link JedisLPosParams} 实例
	 */
	public static JedisLPosParams from(final LPosArgument lPosArgument) {
		final JedisLPosParams lPosParams = new JedisLPosParams();

		Optional.ofNullable(lPosArgument.getRank()).ifPresent(lPosParams::rank);
		Optional.ofNullable(lPosArgument.getMaxLen()).ifPresent(lPosParams::maxlen);

		return lPosParams;
	}

}
