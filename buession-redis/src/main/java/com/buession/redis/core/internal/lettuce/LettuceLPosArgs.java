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

import com.buession.redis.core.command.args.LPosArgument;
import io.lettuce.core.LPosArgs;

import java.util.Optional;

/**
 * Lettuce {@link LPosArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceLPosArgs extends LPosArgs {

	/**
	 * 构造函数
	 */
	public LettuceLPosArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param rank
	 * 		返回第几个匹配的元素
	 */
	public LettuceLPosArgs(final int rank) {
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
	public LettuceLPosArgs(final int rank, final int maxLen) {
		super();
		rank(rank);
		maxlen(maxLen);
	}

	/**
	 * 从 {@link LPosArgument} 创建 {@link LPosArgs} 实例
	 *
	 * @param lPosArgument
	 *        {@link LPosArgument}
	 *
	 * @return {@link LettuceLPosArgs} 实例
	 */
	public static LettuceLPosArgs from(final LPosArgument lPosArgument) {
		final LettuceLPosArgs lPosArgs = new LettuceLPosArgs();

		if(lPosArgument != null){
			Optional.ofNullable(lPosArgument.getRank()).ifPresent(lPosArgs::rank);
			Optional.ofNullable(lPosArgument.getMaxLen()).ifPresent(lPosArgs::maxlen);
		}

		return lPosArgs;
	}

}
