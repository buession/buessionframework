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

import com.buession.redis.core.command.args.XReadGroupArgument;
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
	 * @param count
	 * 		返回数量
	 */
	public LettuceXReadGroupArgs(final int count) {
		super();
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 * @param block
	 * 		阻塞时间
	 */
	public LettuceXReadGroupArgs(final int count, final int block) {
		this(count);
		block(block);
	}

	/**
	 * 构造函数
	 *
	 * @param noAck
	 * 		-
	 */
	public LettuceXReadGroupArgs(final boolean noAck) {
		super();
		noack(noAck);
	}

	/**
	 * 构造函数
	 *
	 * @param noAck
	 * 		-
	 * @param count
	 * 		返回数量
	 */
	public LettuceXReadGroupArgs(final boolean noAck, final int count) {
		this(count);
		noack(noAck);
	}

	/**
	 * 构造函数
	 *
	 * @param noAck
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param block
	 * 		阻塞时间
	 */
	public LettuceXReadGroupArgs(final boolean noAck, final int count, final int block) {
		this(block, count);
		noack(noAck);
	}

	/**
	 * 从 {@link XReadGroupArgument} 创建 {@link XReadArgs} 实例
	 *
	 * @param xReadArgument
	 *        {@link XReadGroupArgument}
	 *
	 * @return {@link LettuceXReadGroupArgs} 实例
	 */
	public static LettuceXReadGroupArgs from(final XReadGroupArgument xReadArgument) {
		final LettuceXReadGroupArgs xReadArgs = new LettuceXReadGroupArgs();

		Optional.ofNullable(xReadArgument.getCount()).ifPresent(xReadArgs::count);
		Optional.ofNullable(xReadArgument.getBlock()).ifPresent(xReadArgs::block);
		Optional.ofNullable(xReadArgument.isNoAck()).ifPresent(xReadArgs::noack);

		return xReadArgs;
	}

}
