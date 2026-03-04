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

import com.buession.lang.Order;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.Optional;

/**
 * Jedis {@link GeoRadiusParam} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisGeoRadiusParam extends GeoRadiusParam {

	/**
	 * 构造函数
	 */
	public JedisGeoRadiusParam() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param geoRadiusArgument
	 *        {@link GeoRadiusArgument}
	 */
	public JedisGeoRadiusParam(final GeoRadiusArgument geoRadiusArgument) {
		super();

		if(geoRadiusArgument != null){
			if(Boolean.TRUE.equals(geoRadiusArgument.isWithCoord())){
				withCoord();
			}
			if(Boolean.TRUE.equals(geoRadiusArgument.isWithDist())){
				withDist();
			}
			if(Boolean.TRUE.equals(geoRadiusArgument.isWithHash())){
				withHash();
			}

			if(geoRadiusArgument.getOrder() == Order.ASC){
				sortAscending();
			}else if(geoRadiusArgument.getOrder() == Order.DESC){
				sortDescending();
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param geoRadiusArgument
	 *        {@link GeoRadiusArgument}
	 * @param count
	 * 		返回数量
	 */
	public JedisGeoRadiusParam(final GeoRadiusArgument geoRadiusArgument, final int count) {
		this(geoRadiusArgument);
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param geoRadiusArgument
	 *        {@link GeoRadiusArgument}
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 */
	public JedisGeoRadiusParam(final GeoRadiusArgument geoRadiusArgument, final int count, final boolean any) {
		this(geoRadiusArgument);
		count(count, any);
	}

}
