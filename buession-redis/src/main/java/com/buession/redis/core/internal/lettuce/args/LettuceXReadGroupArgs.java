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
package com.buession.redis.core.internal.lettuce.args;

import com.buession.redis.core.command.args.stream.XReadGroupArgument;
import io.lettuce.core.XReadArgs;

import java.util.Optional;

/**
 * Lettuce {@link XReadArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceXReadGroupArgs extends XReadArgs {

	/**
	 * 构造函数
	 */
	public LettuceXReadGroupArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param xReadGroupArgument
	 *        {@link XReadGroupArgument}
	 */
	public LettuceXReadGroupArgs(final XReadGroupArgument xReadGroupArgument) {
		super();

		if(xReadGroupArgument != null){
			Optional.ofNullable(xReadGroupArgument.getBlock()).ifPresent(this::block);
			Optional.ofNullable(xReadGroupArgument.getClaim()).ifPresent(this::claim);
			Optional.ofNullable(xReadGroupArgument.getNoAck()).ifPresent(this::noack);
		}
	}

	/**
	 * 构造函数
	 *
	 * @param xReadGroupArgument
	 *        {@link XReadGroupArgument}
	 * @param count
	 * 		返回数量
	 */
	public LettuceXReadGroupArgs(final XReadGroupArgument xReadGroupArgument, final int count) {
		this(xReadGroupArgument);
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 */
	public LettuceXReadGroupArgs(final long block) {
		super();
		block((int) block);
	}

	/**
	 * 构造函数
	 *
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param count
	 * 		返回数量
	 */
	public LettuceXReadGroupArgs(final long block, final int count) {
		this(block);
		count(count);
	}

}
