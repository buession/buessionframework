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

import com.buession.lang.Order;
import com.buession.redis.core.command.GeoCommands;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.Optional;

/**
 * Jedis {@link GeoRadiusParam} 扩展
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class JedisGeoRadiusParam extends GeoRadiusParam {

	public JedisGeoRadiusParam() {
		super();
	}

	public JedisGeoRadiusParam(final boolean withCoord, final boolean withDist, final boolean withHash) {
		super();
		withCoord(this, withCoord);
		withDist(this, withDist);
		withHash(this, withHash);
	}

	public JedisGeoRadiusParam(final Order order) {
		super();
		sort(this, order);
	}

	public JedisGeoRadiusParam(final int count) {
		super();
		count(count);
	}

	public JedisGeoRadiusParam(final Order order, final int count) {
		this(order);
		count(count);
	}

	public JedisGeoRadiusParam(final boolean withCoord, final boolean withDist, final boolean withHash,
							   final Order order) {
		this(withCoord, withDist, withHash);
		sort(this, order);
	}

	public JedisGeoRadiusParam(final boolean withCoord, final boolean withDist, final boolean withHash,
							   final int count) {
		this(withCoord, withDist, withHash);
		count(count);
	}

	public JedisGeoRadiusParam(final boolean withCoord, final boolean withDist, final boolean withHash,
							   final Order order, final int count) {
		this(withCoord, withDist, withHash, order);
		count(count);
	}

	public static JedisGeoRadiusParam from(final GeoCommands.GeoRadiusArgument geoRadiusArgument) {
		final JedisGeoRadiusParam geoRadiusParam = new JedisGeoRadiusParam();

		if(geoRadiusArgument != null){
			withCoord(geoRadiusParam, geoRadiusArgument.isWithCoord());
			withDist(geoRadiusParam, geoRadiusArgument.isWithDist());
			withHash(geoRadiusParam, geoRadiusArgument.isWithHash());
			sort(geoRadiusParam, geoRadiusArgument.getOrder());
			Optional.ofNullable(geoRadiusArgument.getCount()).ifPresent(geoRadiusParam::count);
		}

		return geoRadiusParam;
	}

	private static void withCoord(final JedisGeoRadiusParam geoRadiusParam, final Boolean withCoord) {
		if(Boolean.TRUE.equals(withCoord)){
			geoRadiusParam.withCoord();
		}
	}

	private static void withDist(final JedisGeoRadiusParam geoRadiusParam, final Boolean withDist) {
		if(Boolean.TRUE.equals(withDist)){
			geoRadiusParam.withDist();
		}
	}

	private static void withHash(final JedisGeoRadiusParam geoRadiusParam, final Boolean withHash) {
		if(Boolean.TRUE.equals(withHash)){
			geoRadiusParam.withHash();
		}
	}

	private static void sort(final JedisGeoRadiusParam geoRadiusParam, final Order order) {
		if(order == Order.ASC){
			geoRadiusParam.sortAscending();
		}else if(order == Order.DESC){
			geoRadiusParam.sortDescending();
		}
	}

}