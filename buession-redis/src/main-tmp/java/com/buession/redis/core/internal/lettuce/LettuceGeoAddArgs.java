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
import com.buession.redis.core.command.args.GeoAddArgument;
import io.lettuce.core.GeoAddArgs;

/**
 * Lettuce {@link GeoAddArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceGeoAddArgs extends GeoAddArgs {

	/**
	 * 构造函数
	 */
	public LettuceGeoAddArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 */
	public LettuceGeoAddArgs(final NxXx nxXx) {
		super();
		nxXx(this, nxXx);
	}

	/**
	 * 构造函数
	 *
	 * @param ch
	 * 		-
	 */
	public LettuceGeoAddArgs(final boolean ch) {
		super();
		ch(this, ch);
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param ch
	 * 		-
	 */
	public LettuceGeoAddArgs(final NxXx nxXx, final boolean ch) {
		super();
		nxXx(this, nxXx);
		ch(this, ch);
	}

	/**
	 * 从 {@link GeoAddArgument} 创建 {@link GeoAddArgs} 实例
	 *
	 * @param geoAddArgument
	 *        {@link GeoAddArgument}
	 *
	 * @return {@link LettuceGeoAddArgs} 实例
	 */
	public static LettuceGeoAddArgs from(final GeoAddArgument geoAddArgument) {
		final LettuceGeoAddArgs geoAddParam = new LettuceGeoAddArgs();

		nxXx(geoAddParam, geoAddArgument.getNxXx());
		ch(geoAddParam, geoAddArgument.isCh());

		return geoAddParam;
	}

	private static void nxXx(final LettuceGeoAddArgs geoAddArgs, final NxXx nxXx) {
		if(nxXx != null){
			switch(nxXx){
				case NX:
					geoAddArgs.nx();
					break;
				case XX:
					geoAddArgs.xx();
					break;
				default:
					break;
			}
		}
	}

	private static void ch(final LettuceGeoAddArgs geoAddArgs, final Boolean ch) {
		if(Boolean.TRUE.equals(ch)){
			geoAddArgs.ch();
		}
	}

}
