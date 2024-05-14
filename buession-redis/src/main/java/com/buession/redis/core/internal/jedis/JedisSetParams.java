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

import com.buession.redis.core.NxXx;
import com.buession.redis.core.command.StringCommands;
import redis.clients.jedis.params.SetParams;

import java.util.Optional;

/**
 * Jedis {@link SetParams} 扩展
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class JedisSetParams extends SetParams {

	public JedisSetParams() {
		super();
	}

	public JedisSetParams(final NxXx nx) {
		super();
		nx(nx);
	}

	public JedisSetParams(final boolean keepTtl) {
		super();
		keepTtl(keepTtl);
	}

	public JedisSetParams(final long ex, final long exAt, final long px, final long pxAt) {
		super();
		ex(ex);
		exAt(exAt);
		px(px);
		pxAt(pxAt);
	}

	public JedisSetParams(final long ex, final long exAt, final long px, final long pxAt, final NxXx nx) {
		this(ex, exAt, px, pxAt);
		nx(nx);
	}

	public JedisSetParams(final long ex, final long exAt, final long px, final long pxAt, final boolean keepTtl) {
		this(ex, exAt, px, pxAt);
		keepTtl(keepTtl);
	}

	public JedisSetParams(final long ex, final long exAt, final long px, final long pxAt, final NxXx nx,
						  final boolean keepTtl) {
		this(ex, exAt, px, pxAt, nx);
		keepTtl(keepTtl);
	}

	public static JedisSetParams from(final StringCommands.SetArgument setArgument) {
		final JedisSetParams setParams = new JedisSetParams();

		if(setArgument != null){
			Optional.ofNullable(setArgument.getEx()).ifPresent(setParams::ex);
			Optional.ofNullable(setArgument.getExAt()).ifPresent(setParams::exAt);
			Optional.ofNullable(setArgument.getPx()).ifPresent(setParams::px);
			Optional.ofNullable(setArgument.getPxAt()).ifPresent(setParams::pxAt);

			if(setArgument.getNxXx() == NxXx.NX){
				setParams.nx();
			}else if(setArgument.getNxXx() == NxXx.XX){
				setParams.xx();
			}

			if(Boolean.TRUE.equals(setArgument.isKeepTtl())){
				setParams.keepttl();
			}
		}

		return setParams;
	}

	private void nx(final NxXx nx) {
		if(nx == NxXx.NX){
			nx();
		}else if(nx == NxXx.XX){
			xx();
		}
	}

	private void keepTtl(final boolean keepTtl) {
		if(keepTtl){
			keepttl();
		}
	}

}
