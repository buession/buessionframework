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

import com.buession.redis.core.command.args.XReadArgument;
import redis.clients.jedis.params.XReadParams;

import java.util.Optional;

/**
 * Jedis {@link XReadParams} 扩展
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisXReadParams extends XReadParams {

	/**
	 * 构造函数
	 */
	public JedisXReadParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回条数
	 */
	public JedisXReadParams(final int count) {
		super();
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回条数
	 * @param block
	 * 		-
	 */
	public JedisXReadParams(final int count, final int block) {
		this(count);
		block(block);
	}

	/**
	 * 从 {@link XReadArgument} 创建 {@link XReadParams} 实例
	 *
	 * @param xReadArgument
	 *        {@link XReadArgument}
	 *
	 * @return {@link JedisXReadParams} 实例
	 */
	public static JedisXReadParams from(final XReadArgument xReadArgument) {
		final JedisXReadParams xReadParams = new JedisXReadParams();

		Optional.ofNullable(xReadArgument.getCount()).ifPresent(xReadParams::count);
		Optional.ofNullable(xReadArgument.getBlock()).ifPresent(xReadParams::block);

		return xReadParams;
	}

}
