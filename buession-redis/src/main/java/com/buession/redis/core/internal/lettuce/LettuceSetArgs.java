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
import com.buession.redis.core.command.args.SetArgument;
import io.lettuce.core.SetArgs;

/**
 * Lettuce {@link SetArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceSetArgs extends SetArgs {

	/**
	 * 构造函数
	 */
	public LettuceSetArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		过期时间方式
	 * @param expires
	 * 		过期时间
	 */
	public LettuceSetArgs(final SetArgument.SetType type, final long expires) {
		expx(this, type, expires);
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		过期时间方式
	 * @param expires
	 * 		过期时间
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	public LettuceSetArgs(final SetArgument.SetType type, final long expires, final NxXx nxXx) {
		this(type, expires);
		nxxx(this, nxXx);
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	public LettuceSetArgs(final NxXx nxXx) {
		super();
		nxxx(this, nxXx);
	}

	/**
	 * 构造函数
	 *
	 * @param keepTtl
	 * 		是否获取 key 的过期时间
	 */
	public LettuceSetArgs(final Boolean keepTtl) {
		super();
		keepTtl(this, keepTtl);
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 * @param keepTtl
	 * 		是否获取 key 的过期时间
	 */
	public LettuceSetArgs(final NxXx nxXx, final Boolean keepTtl) {
		super();
		nxxx(this, nxXx);
		keepTtl(this, keepTtl);
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		过期时间方式
	 * @param expires
	 * 		过期时间
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 * @param keepTtl
	 * 		是否获取 key 的过期时间
	 */
	public LettuceSetArgs(final SetArgument.SetType type, final long expires, final NxXx nxXx, final Boolean keepTtl) {
		this(type, expires, nxXx);
		keepTtl(this, keepTtl);
	}

	/**
	 * 从 {@link SetArgument} 创建 {@link SetArgs} 实例
	 *
	 * @param setArgument
	 *        {@link SetArgument}
	 *
	 * @return {@link LettuceSetArgs} 实例
	 */
	public static LettuceSetArgs from(final SetArgument setArgument) {
		final LettuceSetArgs setArgs = new LettuceSetArgs();

		if(setArgument != null){
			expx(setArgs, setArgument.getType(), setArgument.getExpires());
			nxxx(setArgs, setArgument.getNxXx());
			keepTtl(setArgs, setArgument.isKeepTtl());
		}

		return setArgs;
	}

	private static void expx(final LettuceSetArgs setArgs, final SetArgument.SetType type, final Long expires) {
		if(type != null && expires != null){
			switch(type){
				case EX:
					setArgs.ex(expires);
					break;
				case EXAT:
					setArgs.exAt(expires);
					break;
				case PX:
					setArgs.px(expires);
					break;
				case PXAT:
					setArgs.pxAt(expires);
					break;
				default:
					break;
			}
		}
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
