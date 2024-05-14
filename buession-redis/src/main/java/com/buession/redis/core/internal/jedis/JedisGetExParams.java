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

import com.buession.redis.core.command.StringCommands;
import redis.clients.jedis.params.GetExParams;

import java.util.Optional;

/**
 * Jedis {@link GetExParams} 扩展
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class JedisGetExParams extends GetExParams {

	public JedisGetExParams() {
		super();
	}

	public JedisGetExParams(final boolean persist) {
		super();
		if(persist){
			persist();
		}
	}

	public JedisGetExParams(final long ex, final long exAt, final long px, final long pxAt) {
		super();
		ex(ex);
		exAt(exAt);
		px(px);
		pxAt(pxAt);
	}

	public JedisGetExParams(final long ex, final long exAt, final long px, final long pxAt, final boolean persist) {
		this(ex, exAt, px, pxAt);
		if(persist){
			persist();
		}
	}

	public static JedisGetExParams from(final StringCommands.GetExArgument getExArgument) {
		final JedisGetExParams getExParams = new JedisGetExParams();

		if(getExArgument != null){
			Optional.ofNullable(getExArgument.getEx()).ifPresent(getExParams::ex);
			Optional.ofNullable(getExArgument.getExAt()).ifPresent(getExParams::exAt);
			Optional.ofNullable(getExArgument.getPx()).ifPresent(getExParams::px);
			Optional.ofNullable(getExArgument.getPxAt()).ifPresent(getExParams::pxAt);
		}

		return getExParams;
	}

}
