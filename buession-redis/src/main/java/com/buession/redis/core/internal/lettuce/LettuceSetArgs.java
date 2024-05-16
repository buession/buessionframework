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

import com.buession.redis.core.NxXx;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.internal.jedis.JedisSetParams;
import io.lettuce.core.SetArgs;

import java.util.Optional;

/**
 * Lettuce {@link SetArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceSetArgs extends SetArgs {

	public LettuceSetArgs() {
		super();
	}

	public LettuceSetArgs(final NxXx nx) {
		super();
		nxxx(this, nx);
	}

	public LettuceSetArgs(final boolean keepTtl) {
		super();
		keepTtl(this, keepTtl);
	}

	public LettuceSetArgs(final long ex, final long exAt, final long px, final long pxAt) {
		super();
		ex(ex);
		px(px);
	}

	public LettuceSetArgs(final long ex, final long exAt, final long px, final long pxAt, final NxXx nx) {
		this(ex, exAt, px, pxAt);
		nxxx(this, nx);
	}

	public LettuceSetArgs(final long ex, final long exAt, final long px, final long pxAt, final boolean keepTtl) {
		this(ex, exAt, px, pxAt);
		keepTtl(this, keepTtl);
	}

	public LettuceSetArgs(final long ex, final long exAt, final long px, final long pxAt, final NxXx nx,
						  final boolean keepTtl) {
		this(ex, exAt, px, pxAt, nx);
		keepTtl(this, keepTtl);
	}

	public static LettuceSetArgs from(final StringCommands.SetArgument setArgument) {
		final LettuceSetArgs setArgs = new LettuceSetArgs();

		if(setArgument != null){
			Optional.ofNullable(setArgument.getEx()).ifPresent(setArgs::ex);
			//Optional.ofNullable(setArgument.getExAt()).ifPresent(setArgs::exAt);
			Optional.ofNullable(setArgument.getPx()).ifPresent(setArgs::px);
			//Optional.ofNullable(setArgument.getPxAt()).ifPresent(setArgs::pxAt);

			nxxx(setArgs, setArgument.getNxXx());
			keepTtl(setArgs, setArgument.isKeepTtl());
		}

		return setArgs;
	}

	private static void nxxx(final LettuceSetArgs setArgs, final NxXx nx) {
		if(nx == NxXx.NX){
			setArgs.nx();
		}else if(nx == NxXx.XX){
			setArgs.xx();
		}
	}

	private static void keepTtl(final LettuceSetArgs setArgs, final Boolean keepTtl) {
		if(Boolean.TRUE.equals(keepTtl)){
			setArgs.keepttl();
		}
	}

}
