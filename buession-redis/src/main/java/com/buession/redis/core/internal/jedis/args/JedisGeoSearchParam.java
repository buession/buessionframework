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
package com.buession.redis.core.internal.jedis.args;

import com.buession.lang.Order;
import com.buession.redis.core.command.args.GeoSearchArgument;
import redis.clients.jedis.params.GeoSearchParam;

/**
 * Jedis {@link GeoSearchParam} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class JedisGeoSearchParam extends GeoSearchParam {

	/**
	 * 构造函数
	 */
	public JedisGeoSearchParam() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param geoSearchArgument
	 *        {@link GeoSearchArgument}
	 */
	public JedisGeoSearchParam(final GeoSearchArgument geoSearchArgument) {
		super();

		if(geoSearchArgument != null){
			if(Boolean.TRUE.equals(geoSearchArgument.isWithCoord())){
				withCoord();
			}
			if(Boolean.TRUE.equals(geoSearchArgument.isWithDist())){
				withDist();
			}
			if(Boolean.TRUE.equals(geoSearchArgument.isWithHash())){
				withHash();
			}

			if(geoSearchArgument.getOrder() == Order.ASC){
				asc();
			}else if(geoSearchArgument.getOrder() == Order.DESC){
				desc();
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param geoSearchArgument
	 *        {@link GeoSearchArgument}
	 * @param count
	 * 		返回数量
	 */
	public JedisGeoSearchParam(final GeoSearchArgument geoSearchArgument, final int count) {
		this(geoSearchArgument);
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param geoSearchArgument
	 *        {@link GeoSearchArgument}
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 */
	public JedisGeoSearchParam(final GeoSearchArgument geoSearchArgument, final int count, final boolean any) {
		this(geoSearchArgument);
		count(count, any);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 */
	public JedisGeoSearchParam(final int count) {
		super();
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 */
	public JedisGeoSearchParam(final int count, final boolean any) {
		super();
		count(count, any);
	}

}
