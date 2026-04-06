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

import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.core.command.args.PxExType;
import io.lettuce.core.MSetExArgs;

import java.time.Duration;
import java.time.Instant;

/**
 * Lettuce {@link MSetExArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceMSetExArgs extends MSetExArgs {

	/**
	 * 构造函数
	 */
	public LettuceMSetExArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 */
	public LettuceMSetExArgs(final NxXx nxXx) {
		super();
		nxXx(nxXx);
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param pxExType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 */
	public LettuceMSetExArgs(final NxXx nxXx, final PxExType pxExType, final long expires) {
		super();
		nxXx(nxXx);
		expires(pxExType, expires);
	}

	/**
	 * 构造函数
	 *
	 * @param pxExType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 */
	public LettuceMSetExArgs(final PxExType pxExType, final long expires) {
		super();
		expires(pxExType, expires);
	}

	private void nxXx(final NxXx nxXx) {
		if(nxXx == NxXx.NX){
			nx();
		}else if(nxXx == NxXx.XX){
			xx();
		}
	}

	private void expires(final PxExType pxExType, final long expires) {
		if(pxExType == PxExType.EX){
			ex(Duration.ofSeconds(expires));
		}else if(pxExType == PxExType.EXAT){
			exAt(Instant.ofEpochSecond(expires));
		}else if(pxExType == PxExType.PX){
			px(Duration.ofMillis(expires));
		}else if(pxExType == PxExType.PXAT){
			pxAt(Instant.ofEpochMilli(expires));
		}else if(pxExType == PxExType.KEEPTTL){
			keepttl();
		}
	}

}
