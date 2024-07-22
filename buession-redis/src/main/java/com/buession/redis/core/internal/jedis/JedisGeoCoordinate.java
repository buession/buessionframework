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

import com.buession.lang.Geo;
import redis.clients.jedis.GeoCoordinate;

/**
 * Jedis {@link GeoCoordinate} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisGeoCoordinate extends GeoCoordinate {

	/**
	 * 构造函数
	 *
	 * @param longitude
	 * 		精度
	 * @param latitude
	 * 		纬度
	 */
	public JedisGeoCoordinate(final double longitude, final double latitude) {
		super(longitude, latitude);
	}

	/**
	 * 从 {@link Geo} 创建 {@link GeoCoordinate} 实例
	 *
	 * @param geo
	 *        {@link Geo}
	 *
	 * @return {@link JedisGeoCoordinate} 实例
	 */
	public static JedisGeoCoordinate from(final Geo geo) {
		return new JedisGeoCoordinate(geo.getLongitude(), geo.getLatitude());
	}

}
