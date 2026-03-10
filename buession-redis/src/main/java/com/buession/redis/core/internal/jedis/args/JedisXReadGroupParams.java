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
package com.buession.redis.core.internal.jedis.args;

import com.buession.redis.core.command.args.XReadGroupArgument;
import redis.clients.jedis.params.XReadGroupParams;

import java.util.Optional;

/**
 * Jedis {@link XReadGroupParams} 扩展
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisXReadGroupParams extends XReadGroupParams {

	/**
	 * 构造函数
	 */
	public JedisXReadGroupParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param xReadGroupArgument
	 *        {@link XReadGroupArgument}
	 */
	public JedisXReadGroupParams(final XReadGroupArgument xReadGroupArgument) {
		super();

		if(xReadGroupArgument != null){
			Optional.ofNullable(xReadGroupArgument.getClaim()).ifPresent(this::claim);
			if(xReadGroupArgument.getBlock() != null){
				block(xReadGroupArgument.getBlock().intValue());
			}

			if(Boolean.TRUE.equals(xReadGroupArgument.getNoAck())){
				noAck();
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 */
	public JedisXReadGroupParams(final int block) {
		super();
		block(block);
	}

	/**
	 * 构造函数
	 *
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param count
	 * 		返回数量
	 */
	public JedisXReadGroupParams(final int block, final int count) {
		this(block);
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param xReadGroupArgument
	 *        {@link XReadGroupArgument}
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 */
	public JedisXReadGroupParams(final XReadGroupArgument xReadGroupArgument, final int block) {
		this(xReadGroupArgument);
		block(block);
	}

	/**
	 * 构造函数
	 *
	 * @param xReadGroupArgument
	 *        {@link XReadGroupArgument}
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param count
	 * 		返回数量
	 */
	public JedisXReadGroupParams(final XReadGroupArgument xReadGroupArgument, final int block, final int count) {
		this(xReadGroupArgument);
		block(block);
		count(count);
	}

}
