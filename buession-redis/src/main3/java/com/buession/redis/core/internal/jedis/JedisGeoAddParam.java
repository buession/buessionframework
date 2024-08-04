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
import com.buession.redis.core.command.args.GeoAddArgument;
import redis.clients.jedis.params.GeoAddParams;

/**
 * Jedis {@link redis.clients.jedis.params.GeoAddParams} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisGeoAddParam extends GeoAddParams {

	/**
	 * 构造函数
	 */
	public JedisGeoAddParam() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 */
	public JedisGeoAddParam(final NxXx nxXx) {
		super();
		nxXx(this, nxXx);
	}

	/**
	 * 构造函数
	 *
	 * @param ch
	 * 		-
	 */
	public JedisGeoAddParam(final boolean ch) {
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
	public JedisGeoAddParam(final NxXx nxXx, final boolean ch) {
		super();
		nxXx(this, nxXx);
		ch(this, ch);
	}

	/**
	 * 从 {@link GeoAddArgument} 创建 {@link GeoAddParams} 实例
	 *
	 * @param geoAddArgument
	 *        {@link GeoAddArgument}
	 *
	 * @return {@link JedisGeoAddParam} 实例
	 */
	public static JedisGeoAddParam from(final GeoAddArgument geoAddArgument) {
		final JedisGeoAddParam geoAddParam = new JedisGeoAddParam();

		nxXx(geoAddParam, geoAddArgument.getNxXx());
		ch(geoAddParam, geoAddArgument.isCh());

		return geoAddParam;
	}

	private static void nxXx(final JedisGeoAddParam geoAddParam, final NxXx nxXx) {
		if(nxXx != null){
			switch(nxXx){
				case NX:
					geoAddParam.nx();
					break;
				case XX:
					geoAddParam.xx();
					break;
				default:
					break;
			}
		}
	}

	private static void ch(final JedisGeoAddParam geoRadiusParam, final Boolean ch) {
		if(Boolean.TRUE.equals(ch)){
			geoRadiusParam.ch();
		}
	}

}
