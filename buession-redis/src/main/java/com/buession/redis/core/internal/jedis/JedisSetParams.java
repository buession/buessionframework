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
import com.buession.redis.core.command.args.SetArgument;
import redis.clients.jedis.params.SetParams;

/**
 * Jedis {@link SetParams} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisSetParams extends SetParams {

	/**
	 * 构造函数
	 */
	public JedisSetParams() {
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
	public JedisSetParams(final SetArgument.SetType type, final long expires) {
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
	public JedisSetParams(final SetArgument.SetType type, final long expires, final NxXx nxXx) {
		this(type, expires);
		nxxx(this, nxXx);
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		只有键 key 不存在/存在的时候才会设置 key 的值
	 */
	public JedisSetParams(final NxXx nxXx) {
		super();
		nxxx(this, nxXx);
	}

	/**
	 * 构造函数
	 *
	 * @param keepTtl
	 * 		是否获取 key 的过期时间
	 */
	public JedisSetParams(final Boolean keepTtl) {
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
	public JedisSetParams(final NxXx nxXx, final Boolean keepTtl) {
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
	public JedisSetParams(final SetArgument.SetType type, final long expires, final NxXx nxXx, final boolean keepTtl) {
		this(type, expires, nxXx);
		keepTtl(this, keepTtl);
	}

	/**
	 * 从 {@link SetArgument} 创建 {@link SetParams} 实例
	 *
	 * @param setArgument
	 *        {@link SetArgument}
	 *
	 * @return {@link JedisSetParams} 实例
	 */
	public static JedisSetParams from(final SetArgument setArgument) {
		final JedisSetParams setParams = new JedisSetParams();

		if(setArgument != null){
			expx(setParams, setArgument.getType(), setArgument.getExpires());
			nxxx(setParams, setArgument.getNxXx());
			keepTtl(setParams, setArgument.isKeepTtl());
		}

		return setParams;
	}

	private static void expx(final JedisSetParams setParams, final SetArgument.SetType type, final Long expires) {
		if(type != null && expires != null){
			switch(type){
				case EX:
					setParams.ex(expires);
					break;
				case EXAT:
					setParams.exAt(expires);
					break;
				case PX:
					setParams.px(expires);
					break;
				case PXAT:
					setParams.pxAt(expires);
					break;
				default:
					break;
			}
		}
	}

	private static void nxxx(final JedisSetParams setParams, final NxXx nx) {
		if(nx == NxXx.NX){
			setParams.nx();
		}else if(nx == NxXx.XX){
			setParams.xx();
		}
	}

	private static void keepTtl(final JedisSetParams setParams, final Boolean keepTtl) {
		if(Boolean.TRUE.equals(keepTtl)){
			setParams.keepttl();
		}
	}

}
